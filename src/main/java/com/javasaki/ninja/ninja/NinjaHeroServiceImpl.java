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
  public boolean performRobberyByUserId(Long userId) {
    NinjaHero ninjaHero = ninjaHeroRepository.findNinjaHeroByUserNinjaId(userId);
    boolean result = rndGenerator() < 7;
    if (result) {
      int money = ninjaHero.getMoney() + (rndGenerator() * 100);
      ninjaHero.setMoney(money);
    } else {
      ninjaHero.setInJail(true);
    }
    return result;
  }

  private int rndGenerator() {
    return (int) (Math.random() * 10) + 1;
  }
}
