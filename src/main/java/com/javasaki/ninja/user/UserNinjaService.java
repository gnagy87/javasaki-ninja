package com.javasaki.ninja.user;

import com.javasaki.ninja.dto.RegisterDTO;
import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.exception.UserNinjaException;

import javax.servlet.http.HttpServletRequest;

public interface UserNinjaService {

  boolean isUserExists(String username);

  void registration(RegisterDTO registerDTO) throws UserNinjaException, NinjaException;

  void saveUserNinja(RegisterDTO registerDTO) throws NinjaException;

  long getIdFromToken(HttpServletRequest req);
}
