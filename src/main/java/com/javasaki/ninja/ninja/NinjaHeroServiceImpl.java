package com.javasaki.ninja.ninja;

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
  public int dailyBonus(long id) throws TimeException {
    NinjaHero ninja = ninjaHeroRepository.findById(id).get();
    if (!timeService.expiredOtNot(ninja.getDailyBonusTime())) {
      throw new TimeException("You have to wait " + ((ninja.getDailyBonusTime() - java.time.Instant.now().getEpochSecond()) / 60) + " minutes to the next bonus");
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
