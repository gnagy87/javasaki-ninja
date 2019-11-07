package com.javasaki.ninja.ninja;

import com.javasaki.ninja.exception.TimeException;

public interface NinjaHeroService {

  int dailyBonus(long id) throws TimeException;
}
