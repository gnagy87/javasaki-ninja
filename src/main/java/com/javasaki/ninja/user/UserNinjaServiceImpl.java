package com.javasaki.ninja.user;

import com.javasaki.ninja.dto.RegisterDTO;
import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.exception.UserNinjaException;
import com.javasaki.ninja.factory.Factory;
import com.javasaki.ninja.ninja.NinjaHero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNinjaServiceImpl implements UserNinjaService {

  private UserNinjaRepository userNinjaRepository;
  private Factory factory;

  @Autowired
  public UserNinjaServiceImpl(UserNinjaRepository userNinjaRepository, Factory factory) {
    this.userNinjaRepository = userNinjaRepository;
    this.factory = factory;
  }

  @Override
  public boolean isUserExists(String username) {
    return userNinjaRepository.findUserNinjaByUsername(username).isPresent();
  }

  @Override
  public void registration(RegisterDTO registerDTO) throws UserNinjaException, NinjaException {
    if (isUserExists(registerDTO.getUsername())) {
      throw new UserNinjaException("Username is already taken");
    }
    saveUserNinja(registerDTO);
  }

  @Override
  public void saveUserNinja(RegisterDTO registerDTO) throws NinjaException {
    NinjaHero hero = factory.createNinja(registerDTO.getHeroType(), registerDTO.getHeroName());
    UserNinja user = new UserNinja(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getEmail());
    user.setNinjaHero(hero);
    hero.setUserNinja(user);
    userNinjaRepository.save(user);
  }
}
