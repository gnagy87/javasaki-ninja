package com.javasaki.ninja.ninja;

import com.javasaki.ninja.dto.NinjaDTO;
import com.javasaki.ninja.dto.TrainDTO;
import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.exception.TimeException;

public interface NinjaHeroService {

  NinjaHero findNinjaById(long id);

  int dailyBonus(long id) throws TimeException;

  NinjaDTO trainNinjaHero(long id, TrainDTO trainDTO) throws NinjaException;

  NinjaDTO improveOffence(NinjaHero ninjaHero);

  NinjaDTO improveDefence(NinjaHero ninjaHero);

  NinjaDTO improveSpeed(NinjaHero ninjaHero);

}
