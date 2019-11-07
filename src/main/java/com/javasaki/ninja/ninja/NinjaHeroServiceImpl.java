package com.javasaki.ninja.ninja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NinjaHeroServiceImpl implements NinjaHeroService {

  private NinjaHeroRepository ninjaHeroRepository;

  @Autowired
  public NinjaHeroServiceImpl(NinjaHeroRepository ninjaHeroRepository) {
    this.ninjaHeroRepository = ninjaHeroRepository;
  }

  @Override
  public NinjaHero findNinjaById(long id) {
    return ninjaHeroRepository.findById(id).get();
  }
}
