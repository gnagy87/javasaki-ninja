package com.javasaki.ninja.ninja;

import com.javasaki.ninja.dto.NinjaDTO;
import com.javasaki.ninja.dto.TrainDTO;
import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.dto.PrizeDTO;
import com.javasaki.ninja.exception.TimeException;
import com.javasaki.ninja.timeservice.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NinjaHeroServiceImpl implements NinjaHeroService {

  private NinjaHeroRepository ninjaHeroRepository;
  private TimeService timeService;

  @Autowired
  public NinjaHeroServiceImpl(NinjaHeroRepository ninjaHeroRepository, TimeService timeService) {
    this.ninjaHeroRepository = ninjaHeroRepository;
    this.timeService = timeService;
  }

  @Override
  public PrizeDTO performRobberyByUserId(Long userId) throws TimeException {
    NinjaHero ninjaHero = ninjaHeroRepository.findNinjaHeroByUserNinjaId(userId);
    if (!timeService.expiredOrNot(ninjaHero.getFinishedAt())) {
      throw new TimeException("Time need to be elapsed until perform next activity (in sec): "
          + (ninjaHero.getFinishedAt() - java.time.Instant.now().getEpochSecond()));
    }
    if (rndGenerator() < 7) {
      int oldMoney = ninjaHero.getMoney();
      int money = oldMoney + (rndGenerator() * 100);
      ninjaHero.setMoney(money);
      ninjaHero.setFinishedAt(java.time.Instant.now().getEpochSecond() + 120);
      ninjaHeroRepository.save(ninjaHero);
      return new PrizeDTO("Successful robbery performed.", ninjaHero.getMoney() - oldMoney);
    } else {
      ninjaHero.setInJail(true);
      ninjaHero.setFinishedAt(java.time.Instant.now().getEpochSecond() + 7200);
      ninjaHeroRepository.save(ninjaHero);
      return new PrizeDTO("You have been arrested. Time need to be elapsed until perform next activity (in sec): "
          + (ninjaHero.getFinishedAt() - java.time.Instant.now().getEpochSecond()), 0);
    }
  }

  private int rndGenerator() {
    return (int) (Math.random() * 10) + 1;
  }

  @Override
  public NinjaHero findNinjaById(long id) {
    return ninjaHeroRepository.findById(id).get();
  }

  @Override
  public int dailyBonus(long id) throws TimeException {
    NinjaHero ninja = ninjaHeroRepository.findById(id).get();
    if (!timeService.expiredOrNot(ninja.getDailyBonusTime())) {
      throw new TimeException("You have to wait " + ((ninja.getDailyBonusTime() - java.time.Instant.now().getEpochSecond()) / 60) + " minute(s) until the next bonus round!");
    }
    int prize = bonusMoney();
    ninja.setMoney(ninja.getMoney() + prize);
    ninja.setDailyBonusTime(java.time.Instant.now().getEpochSecond() + 86400);
    ninjaHeroRepository.save(ninja);
    return prize;
  }

  @Override
  public NinjaDTO trainNinjaHero(long id, TrainDTO trainDTO) throws NinjaException {
    NinjaHero ninjaHero = ninjaHeroRepository.findById(id).get();

    if (!timeService.expiredOrNot(ninjaHero.getFinishedAt())) {
      if (trainDTO.getTrain().equals("offence")) {
        improveOffence(ninjaHero);
      }
      if (trainDTO.getTrain().equals("defence")) {
        improveDefence(ninjaHero);
      }
      if (trainDTO.getTrain().equals("speed")) {
        improveSpeed(ninjaHero);
      }
      ninjaHero.setFinishedAt(ninjaHero.getFinishedAt() + 60);
      ninjaHero.setMoney(ninjaHero.getMoney() - 100);
      ninjaHero.setTraining(true);
      ninjaHeroRepository.save(ninjaHero);
      return new NinjaDTO(ninjaHero);
    }
    throw new NinjaException("You can't train, because you have another activity!");
  }

  @Override
  public NinjaDTO improveOffence(NinjaHero ninjaHero) {
    ninjaHero.setHp(ninjaHero.getHp() + 1);
    ninjaHero.setOffence(ninjaHero.getOffence() + 3);
    return new NinjaDTO(ninjaHero);
  }

  @Override
  public NinjaDTO improveDefence(NinjaHero ninjaHero) {
    ninjaHero.setHp(ninjaHero.getHp() + 1);
    ninjaHero.setDefence(ninjaHero.getDefence() + 3);
    return new NinjaDTO(ninjaHero);
  }

  @Override
  public NinjaDTO improveSpeed(NinjaHero ninjaHero) {
    ninjaHero.setHp(ninjaHero.getHp() + 1);
    ninjaHero.setSpeed(ninjaHero.getSpeed() + 3);
    return new NinjaDTO(ninjaHero);
  }

  private int bonusMoney() {
    int[] prize = {2, 1, 20, 42, 50, 120, 400, 8, 150, 70, 350, 100, 160};
    int first = (int) (Math.random() * 13);
    int second = (int) (Math.random() * 13);
    if (first == second) {
      return 4000;
    }
    return prize[first];
  }
}
