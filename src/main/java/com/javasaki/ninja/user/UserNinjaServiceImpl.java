package com.javasaki.ninja.user;

import com.javasaki.ninja.armor.Armor;
import com.javasaki.ninja.challenger.Challenger;
import com.javasaki.ninja.challenger.ChallengerRepository;
import com.javasaki.ninja.dto.ChallengerDTO;
import com.javasaki.ninja.dto.RegisterDTO;
import com.javasaki.ninja.dto.RegisterResponseDTO;
import com.javasaki.ninja.email.EmailService;
import com.javasaki.ninja.email.VerificationToken;
import com.javasaki.ninja.exception.*;
import com.javasaki.ninja.factory.Factory;
import com.javasaki.ninja.ninja.NinjaHero;
import com.javasaki.ninja.weapon.Weapon;
import com.javasaki.ninja.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class UserNinjaServiceImpl implements UserNinjaService {

  private UserNinjaRepository userNinjaRepository;
  private Factory factory;
  private EmailService emailService;
  private PasswordEncoder encoder;
  private JwtProvider jwtProvider;
  private ChallengerRepository challengerRepository;

  @Autowired
  public UserNinjaServiceImpl(UserNinjaRepository userNinjaRepository,
                              Factory factory, PasswordEncoder encoder,
                              JwtProvider jwtProvider, EmailService emailService,
                              ChallengerRepository challengerRepository) {
    this.userNinjaRepository = userNinjaRepository;
    this.factory = factory;
    this.encoder = encoder;
    this.jwtProvider = jwtProvider;
    this.emailService = emailService;
    this.challengerRepository = challengerRepository;

  }

  @Override
  public boolean isUserExists(String username) {
    return userNinjaRepository.findUserNinjaByUsername(username).isPresent();
  }

  @Override
  public RegisterResponseDTO registration(RegisterDTO registerDTO) throws UserNinjaException, NinjaException, WeaponException, ArmorException {
    if (!isUserExists(registerDTO.getUsername())) {
      UserNinja userNinja = saveUserNinja(registerDTO);

      emailService.createVerificationToken(userNinja, UUID.randomUUID().toString());

      return new RegisterResponseDTO("ok", "Successful registration accomplished. "
          + "Verification email sent to " + userNinja.getEmail());
    } else {
      throw new UserNinjaException("Username is already taken");
    }
  }

  @Override
  public UserNinja saveUserNinja(RegisterDTO registerDTO) throws NinjaException, WeaponException, ArmorException {
    NinjaHero hero = factory.createNinja(registerDTO.getHeroType(), registerDTO.getHeroName());
    hero.setMoney(1000);
    Weapon weapon = factory.createWeapon("bamboo");
    List<Weapon> weapons = new ArrayList<>();
    weapon.setNinjaHero(hero);
    weapon.setUsed(true);
    weapons.add(weapon);
    hero.setWeapons(weapons);
    List<Armor> armors = new ArrayList<>();
    Armor armor = factory.createArmor("shirt");
    armor.setNinjaHero(hero);
    armor.setUsed(true);
    armors.add(armor);
    hero.setArmors(armors);
    UserNinja user = new UserNinja(registerDTO.getUsername(), encoder.encode(registerDTO.getPassword()), registerDTO.getEmail());
    user.setNinjaHero(hero);
    hero.setUserNinja(user);
    return userNinjaRepository.save(user);
  }

  @Override
  public UserNinja findUserNinjaByUsername(String username) {
    return userNinjaRepository.findUserNinjaByUsername(username).get();
  }

  @Override
  public VerificationToken findVerificationTokenByUser(UserNinja user) {
    return emailService.getVerificationToken(user);
  }

  @Override
  public UserNinja generateNewTokenForNotEnabledUser(String token) throws EmailVerificationException {
    VerificationToken verificationToken = emailService.findVerificationTokenByToken(token);
    emailService.substituteUsersExistingToken(verificationToken, UUID.randomUUID().toString());
    return verificationToken.getUser();
  }

  @Override
  public void enableUserByVerificationToken(String token) throws EmailVerificationException {
    VerificationToken verificationToken = emailService.findVerificationTokenByToken(token);
    UserNinja user = emailService.enableRegisteredUser(verificationToken);
    simplySaveUserNinjaAfterEnabledProcess(user);
  }

  private void simplySaveUserNinjaAfterEnabledProcess(UserNinja user) {
    userNinjaRepository.save(user);
  }

  @Override
  public long getIdFromToken(HttpServletRequest req) {
    return jwtProvider.getIdFromToken(req.getHeader("Authorization").substring(7));
  }

  @Override
  public String getUsernameFromToken(HttpServletRequest request) {
    return jwtProvider.getUserNameFromJwtToken(request.getHeader("Authorization").substring(7));
  }

  @Override
  public UserNinja saveChallenger(ChallengerDTO challenger, UserNinja user) throws UserNinjaException {
    if (userNinjaRepository.findUserNinjaByUsername(challenger.getChallengedName()).isPresent()) {

      Challenger challenger1 = new Challenger(challenger.getChallengedName(), challenger.getBet());
      challenger1.setUserNinja(user);
      challengerRepository.save(challenger1);
      userNinjaRepository.save(user);
      return user;
    }
    throw  new UserNinjaException("User with name: " + challenger.getChallengedName() + " doesn't exists!");
  }
}
