package com.javasaki.ninja.user;

import com.javasaki.ninja.dto.ChallengerDTO;
import com.javasaki.ninja.dto.RegisterDTO;
import com.javasaki.ninja.dto.RegisterResponseDTO;
import com.javasaki.ninja.email.VerificationToken;
import com.javasaki.ninja.exception.*;

import javax.servlet.http.HttpServletRequest;

public interface UserNinjaService {

  boolean isUserExists(String username);

  RegisterResponseDTO registration(RegisterDTO registerDTO) throws UserNinjaException, NinjaException, WeaponException, ArmorException;

  UserNinja saveUserNinja(RegisterDTO registerDTO) throws NinjaException, WeaponException, ArmorException;

  UserNinja findUserNinjaByUsername(String username);

  VerificationToken findVerificationTokenByUser(UserNinja user);

  long getIdFromToken(HttpServletRequest req);

  String getUsernameFromToken(HttpServletRequest request);
  
  UserNinja generateNewTokenForNotEnabledUser(String token) throws EmailVerificationException;

  void enableUserByVerificationToken(String token) throws EmailVerificationException;

  UserNinja saveChallenger(ChallengerDTO challenger, UserNinja user) throws UserNinjaException;
}
