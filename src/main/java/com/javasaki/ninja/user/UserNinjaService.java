package com.javasaki.ninja.user;

import com.javasaki.ninja.dto.RegisterDTO;
import com.javasaki.ninja.dto.RegisterResponseDTO;
import com.javasaki.ninja.email.VerificationToken;
import com.javasaki.ninja.exception.EmailVerificationException;
import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.exception.UserNinjaException;
import com.javasaki.ninja.exception.WeaponException;

public interface UserNinjaService {

  boolean isUserExists(String username);

  RegisterResponseDTO registration(RegisterDTO registerDTO) throws UserNinjaException, NinjaException, WeaponException;

  UserNinja saveUserNinja(RegisterDTO registerDTO) throws NinjaException, WeaponException;

  UserNinja findUserNinjaByUsername(String username);

  VerificationToken findVerificationTokenByUser(UserNinja user);

  UserNinja generateNewTokenForNotEnabledUser(String token) throws EmailVerificationException;

  void enableUserByVerificationToken(String token) throws EmailVerificationException;
}
