package com.javasaki.ninja.user;

import com.javasaki.ninja.dto.RegisterDTO;
import com.javasaki.ninja.dto.RegisterResponseDTO;
import com.javasaki.ninja.email.EmailService;
import com.javasaki.ninja.email.VerificationToken;
import com.javasaki.ninja.exception.EmailVerificationException;
import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.exception.UserNinjaException;
import com.javasaki.ninja.factory.Factory;
import com.javasaki.ninja.ninja.NinjaHero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserNinjaServiceImpl implements UserNinjaService {

  private UserNinjaRepository userNinjaRepository;
  private Factory factory;
  private EmailService emailService;
  private PasswordEncoder encoder;

  @Autowired
  public UserNinjaServiceImpl(UserNinjaRepository userNinjaRepository, Factory factory, EmailService emailService,
      PasswordEncoder encoder) {
    this.userNinjaRepository = userNinjaRepository;
    this.factory = factory;
    this.emailService = emailService;
    this.encoder = encoder;
  }

  @Override
  public boolean isUserExists(String username) {
    return userNinjaRepository.findUserNinjaByUsername(username).isPresent();
  }

  @Override
  public RegisterResponseDTO registration(RegisterDTO registerDTO) throws UserNinjaException, NinjaException {
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
  public UserNinja saveUserNinja(RegisterDTO registerDTO) throws NinjaException {
    NinjaHero hero = factory.createNinja(registerDTO.getHeroType(), registerDTO.getHeroName());
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
}
