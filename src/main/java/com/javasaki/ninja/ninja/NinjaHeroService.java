package com.javasaki.ninja.ninja;

import com.javasaki.ninja.exception.TimeException;

public interface NinjaHeroService {

  NinjaHero findNinjaById(long id);

  int dailyBonus(long id) throws TimeException;

}
