package com.javasaki.ninja.email;

import com.javasaki.ninja.exception.EmailVerificationException;
import com.javasaki.ninja.timeservice.TimeService;
import com.javasaki.ninja.user.UserNinja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

  private VerificationTokenRepository tokenRepository;
  private TimeService timeService;

  @Autowired
  public EmailServiceImpl(VerificationTokenRepository tokenRepository, TimeService timeService) {
    this.tokenRepository = tokenRepository;
    this.timeService = timeService;
  }

  @Override
  public void createVerificationToken(UserNinja user, String token) {
    VerificationToken newVerificationToken = new VerificationToken(token, user);
    tokenRepository.save(newVerificationToken);
  }

  @Override
  public VerificationToken getVerificationToken(UserNinja user) {
    return tokenRepository.findTokenByUserId(user.getId());
  }

  @Override
  public UserNinja enableRegisteredUser(VerificationToken verificationToken) throws EmailVerificationException {
    return null;
  }

  @Override
  public VerificationToken findVerificationTokenByToken(String token) {
    return null;
  }

  @Override
  public void substituteUsersExistingToken(VerificationToken verificationToken, String toString) throws EmailVerificationException {

  }
}
