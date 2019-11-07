package com.javasaki.ninja.user;

import com.javasaki.ninja.dto.RegisterDTO;
import com.javasaki.ninja.dto.RegisterResponseDTO;
import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.exception.UserNinjaException;

public interface UserNinjaService {

  boolean isUserExists(String username);

  RegisterResponseDTO registration(RegisterDTO registerDTO) throws UserNinjaException, NinjaException;

  UserNinja saveUserNinja(RegisterDTO registerDTO) throws NinjaException;

  UserNinja findUserNinjaByUsername(String username);
}
