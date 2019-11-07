package com.javasaki.ninja.user;

import com.javasaki.ninja.dto.RegisterDTO;
import com.javasaki.ninja.dto.RegisterResponseDTO;
import com.javasaki.ninja.email.EmailService;
import com.javasaki.ninja.email.VerificationToken;
import com.javasaki.ninja.exception.EmailVerificationException;
import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.exception.UserNinjaException;
import com.javasaki.ninja.exception.WeaponException;
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

  @Autowired
  public UserNinjaServiceImpl(UserNinjaRepository userNinjaRepository,
                              Factory factory, PasswordEncoder encoder,
                              JwtProvider jwtProvider, EmailService emailService) {
    this.userNinjaRepository = userNinjaRepository;
    this.factory = factory;
    this.encoder = encoder;
    this.jwtProvider = jwtProvider;
    this.emailService = emailService;

  }

  @Override
  public boolean isUserExists(String username) {
    return userNinjaRepository.findUserNinjaByUsername(username).isPresent();
  }

  @Override
  public RegisterResponseDTO registration(RegisterDTO registerDTO) throws UserNinjaException, NinjaException, WeaponException {
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
  public UserNinja saveUserNinja(RegisterDTO registerDTO) throws NinjaException, WeaponException {
    NinjaHero hero = factory.createNinja(registerDTO.getHeroType(), registerDTO.getHeroName());
    hero.setMoney(1000);
    List<Weapon> weapons = new ArrayList<>();
    Weapon weapon = factory.createWeapon("bamboo");
    weapon.setNinjaHero(hero);
    weapons.add(weapon);
    hero.setWeapons(weapons);
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
}
