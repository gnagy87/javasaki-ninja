package com.javasaki.ninja.ninja;

import com.javasaki.ninja.armor.Armor;
import com.javasaki.ninja.armor.ArmorService;
import com.javasaki.ninja.dto.*;
import com.javasaki.ninja.exception.*;
import com.javasaki.ninja.timeservice.TimeService;
import com.javasaki.ninja.utils.PurchaseService;
import com.javasaki.ninja.weapon.Weapon;
import com.javasaki.ninja.weapon.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NinjaHeroServiceImpl implements NinjaHeroService {

  private NinjaHeroRepository ninjaHeroRepository;
  private TimeService timeService;
  private PurchaseService purchaseService;
  private ArmorService armorService;
  private WeaponService weaponService;

  @Autowired
  public NinjaHeroServiceImpl(NinjaHeroRepository ninjaHeroRepository, TimeService timeService,
                              PurchaseService purchaseService, ArmorService armorService,
                              WeaponService weaponService) {
    this.ninjaHeroRepository = ninjaHeroRepository;
    this.timeService = timeService;
    this.purchaseService = purchaseService;
    this.armorService = armorService;
    this.weaponService = weaponService;
  }

  @Override
  public PrizeDTO performRobberyByUserId(Long userId) throws TimeException {
    NinjaHero ninjaHero = ninjaHeroRepository.findNinjaHeroByUserNinjaId(userId);
    if (!timeService.expiredOrNot(ninjaHero.getFinishedAt())) {
      throw new TimeException("Time need to be elapsed until perform next activity (in sec): "
          + (ninjaHero.getFinishedAt() - java.time.Instant.now().getEpochSecond()));
    }
    if (rndGenerator() < 8) {
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
  public NinjaDTO trainNinjaHero(long id, TrainDTO trainDTO) throws NinjaException, MoneyException {
    NinjaHero ninjaHero = ninjaHeroRepository.findById(id).get();

    purchaseService.enoughMoneyForTrain(ninjaHero, 100);
    String trainType = trainDTO.getTrain();
    if (timeService.expiredOrNot(ninjaHero.getFinishedAt())) {
      if (trainType.equals("offence")) {
        improveOffence(ninjaHero);
      }
      if (trainType.equals("defence")) {
        improveDefence(ninjaHero);
      }
      if (trainType.equals("speed")) {
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
  public void setEquipment(NinjaHero ninjaHero, EquipmentDTO equipmentDTO) throws ArmorException, WeaponException {

    setArmomr(ninjaHero, equipmentDTO.getArmor());
    setWeapon(ninjaHero, equipmentDTO.getWeapon());
  }

  @Override
  public void setArmomr(NinjaHero ninjaHero, String type) throws ArmorException {
    if (!(type == null || type == "")) {
      Armor foundArmor = armorService.findArmorByType(type);
      for (Armor armor : ninjaHero.getArmors()) {
        if (armor == foundArmor) {
          armor.setUsed(true);
        } else {
          armor.setUsed(false);
        }
      }
      ninjaHeroRepository.save(ninjaHero);
    }
  }

  @Override
  public void setWeapon(NinjaHero ninjaHero, String type) throws WeaponException {
    if (!(type == null || type == "")) {
      Weapon foundWeapon = weaponService.findWeaponByType(type);
      for (Weapon weapon : ninjaHero.getWeapons()) {
        if (weapon == foundWeapon) {
          weapon.setUsed(true);
        } else {
          weapon.setUsed(false);
        }
      }
    }
  }

  @Override
  public PrizeDTO performWorkByUserId(Long userId) throws TimeException {
    NinjaHero ninjaHero = ninjaHeroRepository.findNinjaHeroByUserNinjaId(userId);
    if (!timeService.expiredOrNot(ninjaHero.getFinishedAt())) {
      throw new TimeException("Time need to be elapsed until perform next activity (in sec): "
          + (ninjaHero.getFinishedAt() - java.time.Instant.now().getEpochSecond()));
    }

    int oldMoney = ninjaHero.getMoney();
    int money = oldMoney + (rndGenerator() * 60);
    ninjaHero.setMoney(money);
    ninjaHero.setFinishedAt(java.time.Instant.now().getEpochSecond() + 900);
    ninjaHeroRepository.save(ninjaHero);
    return new PrizeDTO("Work has been done. Time to have a rest.", ninjaHero.getMoney() - oldMoney);
  }

  private NinjaDTO improveOffence(NinjaHero ninjaHero) {
    ninjaHero.setHp(ninjaHero.hp + 1);
    ninjaHero.setOffence(ninjaHero.offence + 3);
    return new NinjaDTO(ninjaHero);
  }

  private NinjaDTO improveDefence(NinjaHero ninjaHero) {
    ninjaHero.setHp(ninjaHero.hp + 1);
    ninjaHero.setDefence(ninjaHero.defence + 3);
    return new NinjaDTO(ninjaHero);
  }

  private NinjaDTO improveSpeed(NinjaHero ninjaHero) {
    ninjaHero.setHp(ninjaHero.hp + 1);
    ninjaHero.setSpeed(ninjaHero.speed + 3);
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
