package com.javasaki.ninja.ninja;

import com.javasaki.ninja.dto.PrizeDTO;
import com.javasaki.ninja.exception.TimeException;

public interface NinjaHeroService {

  PrizeDTO performRobberyByUserId(Long authorization) throws TimeException;

  NinjaHero findNinjaById(long id);

  int dailyBonus(long id) throws TimeException;

}
