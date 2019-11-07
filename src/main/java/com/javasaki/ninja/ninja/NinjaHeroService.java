package com.javasaki.ninja.ninja;

import com.javasaki.ninja.dto.NinjaDTO;
import com.javasaki.ninja.dto.TrainDTO;
import com.javasaki.ninja.exception.MoneyException;
import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.dto.PrizeDTO;
import com.javasaki.ninja.exception.TimeException;

public interface NinjaHeroService {

  PrizeDTO performRobberyByUserId(Long authorization) throws TimeException;

  NinjaHero findNinjaById(long id);

  int dailyBonus(long id) throws TimeException;

  NinjaDTO trainNinjaHero(long id, TrainDTO trainDTO) throws NinjaException, MoneyException;

  PrizeDTO performWorkByUserId(Long userId) throws TimeException;
}
