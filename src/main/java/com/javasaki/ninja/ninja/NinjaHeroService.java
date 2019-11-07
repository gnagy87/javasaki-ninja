package com.javasaki.ninja.ninja;

import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.exception.TimeException;
import com.javasaki.ninja.user.UserNinja;

public interface NinjaHeroService {

  int dailyBonus(long id) throws TimeException;

  NinjaHeroDTO trainNinjaHero(UserNinja userNinja, String trainableType) throws NinjaException;

  NinjaHeroDTO improveOffence(NinjaHero ninjaHero);

  NinjaHeroDTO improveDefence(NinjaHero ninjaHero);

  NinjaHeroDTO improveSpeed(NinjaHero ninjaHero);
}
