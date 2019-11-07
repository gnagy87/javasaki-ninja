package com.javasaki.ninja.ninja;

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

  public NinjaHero findNinjaById(long id) {
    return ninjaHeroRepository.findById(id).get();
  }

  @Override
  public int dailyBonus(long id) throws TimeException {
    NinjaHero ninja = ninjaHeroRepository.findById(id).get();
    if (!timeService.expiredOrNot(ninja.getDailyBonusTime())) {
      throw new TimeException("You have to wait until the next bonus round!");
    }
    int prize = bonusMoney();
    ninja.setMoney(ninja.getMoney() + prize);
    ninja.setDailyBonusTime(java.time.Instant.now().getEpochSecond() + 86400);
    ninjaHeroRepository.save(ninja);
    return prize;
  }

  private int bonusMoney() {
    int[] prize = {0, 1, 20, 42, 50, 120, 400, 8, 150, 70, 350, 100, 160};
    int first = (int) (Math.random() * 13);
    int second = (int) (Math.random() * 13);
    if (first == second) {
      return 4000;
    }
    return prize[first];
  }
}
