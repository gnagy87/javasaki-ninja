package com.javasaki.ninja.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNinjaServiceImpl implements UserNinjaService {

  private UserNinjaRepository userNinjaRepository;

  @Autowired
  public UserNinjaServiceImpl(UserNinjaRepository userNinjaRepository) {
    this.userNinjaRepository = userNinjaRepository;
  }
}
