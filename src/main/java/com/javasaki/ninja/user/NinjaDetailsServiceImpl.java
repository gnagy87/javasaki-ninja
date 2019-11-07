package com.javasaki.ninja.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class NinjaDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserNinjaRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username)
          throws UsernameNotFoundException {

    UserNinja user = userRepository.findUserNinjaByUsername(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException("User Not Found: " + username)
            );

    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
            new ArrayList<>());
  }
}

