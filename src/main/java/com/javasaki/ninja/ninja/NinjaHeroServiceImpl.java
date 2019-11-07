package com.javasaki.ninja.ninja;

import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.exception.TimeException;
import com.javasaki.ninja.timeservice.TimeService;
import com.javasaki.ninja.user.UserNinja;
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
      throw new TimeException("You have to wait until the next bonus round!");
    }
    int prize = bonusMoney();
    ninja.setMoney(ninja.getMoney() + prize);
    ninja.setDailyBonusTime(java.time.Instant.now().getEpochSecond() + 86400);
    ninjaHeroRepository.save(ninja);
    return prize;
  }

  @Override
  public NinjaHeroDTO trainNinjaHero(UserNinja userNinja, String trainableType) throws NinjaException {
    NinjaHero ninjaHero = ninjaHeroRepository.findNinjaHeroByUserNinja(userNinja);

    if (!timeService.expiredOtNot(ninjaHero.getFinishedAt())) {
      if (trainableType.equals("offence")) {
        improveOffence(ninjaHero);
      }
      if (trainableType.equals("defence")) {
        improveDefence(ninjaHero);
      }
      if (trainableType.equals("speed")) {
        improveSpeed(ninjaHero);
      }
      ninjaHero.setFinishedAt(ninjaHero.getFinishedAt() + 60);
      ninjaHero.setMoney(ninjaHero.getMoney() - 100);
      ninjaHeroRepository.save(ninjaHero);
      return new NinjaHeroDTO(ninjaHero);
    }
    throw new NinjaException("You can't train, because you have another activity!");
  }

  @Override
  public NinjaHeroDTO improveOffence(NinjaHero ninjaHero) {
    ninjaHero.setHp(ninjaHero.getHp() + 1);
    ninjaHero.setOffence(ninjaHero.getOffence() + 3);
    return new NinjaHeroDTO(ninjaHero);
  }

  @Override
  public NinjaHeroDTO improveDefence(NinjaHero ninjaHero) {
    ninjaHero.setHp(ninjaHero.getHp() + 1);
    ninjaHero.setDefence(ninjaHero.getDefence() + 3);
    return new NinjaHeroDTO(ninjaHero);
  }

  @Override
  public NinjaHeroDTO improveSpeed(NinjaHero ninjaHero) {
    ninjaHero.setHp(ninjaHero.getHp() + 1);
    ninjaHero.setSpeed(ninjaHero.getSpeed() + 3);
    return new NinjaHeroDTO(ninjaHero);
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
