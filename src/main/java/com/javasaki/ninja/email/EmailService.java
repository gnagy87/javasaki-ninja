package com.javasaki.ninja.email;

import com.javasaki.ninja.exception.EmailVerificationException;
import com.javasaki.ninja.user.UserNinja;

public interface EmailService {
  void createVerificationToken(UserNinja user, String token);

  VerificationToken getVerificationToken(UserNinja user);

  UserNinja enableRegisteredUser(VerificationToken verificationToken) throws EmailVerificationException;

  VerificationToken findVerificationTokenByToken(String token);

  void substituteUsersExistingToken(VerificationToken verificationToken, String toString)
      throws EmailVerificationException;
}
