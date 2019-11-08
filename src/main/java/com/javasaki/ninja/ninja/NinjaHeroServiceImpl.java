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
              + (ninjaHero.getFinishedAt() - (System.currentTimeMillis() / 1000)));
    }
    if (rndGenerator() < 8) {
      int oldMoney = ninjaHero.getMoney();
      int money = oldMoney + (rndGenerator() * 100);
      ninjaHero.setMoney(money);
      ninjaHero.setFinishedAt((System.currentTimeMillis() / 1000) + 120);
      ninjaHeroRepository.save(ninjaHero);
      return new PrizeDTO("Successful robbery performed.", ninjaHero.getMoney() - oldMoney);
    } else {
      ninjaHero.setInJail(true);
      ninjaHero.setFinishedAt((System.currentTimeMillis() / 1000) + 7200);
      ninjaHeroRepository.save(ninjaHero);
      return new PrizeDTO("You have been arrested. Time need to be elapsed until perform next activity (in sec): "
              + (ninjaHero.getFinishedAt() - (System.currentTimeMillis() / 1000)), 0);
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
      throw new TimeException("You have to wait " + ((ninja.getDailyBonusTime() - (System.currentTimeMillis() / 1000)) / 60) + " minute(s) until the next bonus round!");
    }
    int prize = bonusMoney();
    ninja.setMoney(ninja.getMoney() + prize);
    ninja.setDailyBonusTime((System.currentTimeMillis() / 1000) + 86400);
    ninjaHeroRepository.save(ninja);
    return prize;
  }

  @Override
  public NinjaDTO trainNinjaHero(long id, TrainDTO trainDTO) throws TimeException, MoneyException {
    NinjaHero ninjaHero = ninjaHeroRepository.findById(id).get();

    purchaseService.enoughMoneyForTrain(ninjaHero, 100);
    String trainType = trainDTO.getTrain();
    if (!timeService.expiredOrNot(ninjaHero.getFinishedAt())) {
      throw new TimeException("You can't train because you have another activity, you just train in a minute! You have to wait: "
              + (ninjaHero.getFinishedAt() - System.currentTimeMillis() / 1000) + " second");
    }
    if (trainType.equals("offence")) {
      improveOffence(ninjaHero);
    }
    if (trainType.equals("defence")) {
      improveDefence(ninjaHero);
    }
    if (trainType.equals("speed")) {
      improveSpeed(ninjaHero);
    }
    ninjaHero.setFinishedAt(System.currentTimeMillis() / 1000 + 60);
    ninjaHero.setMoney(ninjaHero.getMoney() - 100);
    ninjaHero.setTraining(true);
    ninjaHeroRepository.save(ninjaHero);
    return new NinjaDTO(ninjaHero);
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
        if (armor == foundArmor && !armor.isOnMarket()) {
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
        if (weapon == foundWeapon && !weapon.isOnMarket()) {
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
              + (ninjaHero.getFinishedAt() - System.currentTimeMillis() / 1000));
    }

    int oldMoney = ninjaHero.getMoney();
    int money = oldMoney + (rndGenerator() * 60);
    ninjaHero.setMoney(money);
    ninjaHero.setFinishedAt((System.currentTimeMillis() / 1000) + 900);
    ninjaHeroRepository.save(ninjaHero);
    return new PrizeDTO("Work has been done. Time to have a rest.", ninjaHero.getMoney() - oldMoney);
  }

  @Override
  public MarketResponseDTO putWeaponToMarket(NinjaHero hero, WeaponMarketDTO weaponMarketDTO) throws WeaponException {
    for (Weapon weapon : hero.getWeapons()) {
      if (weapon.getId() == weaponMarketDTO.getWeaponId()) {
        weapon.setOnMarket(true);
        weapon.setUsed(false);
        weapon.setPrice(weaponMarketDTO.getPrice());
        return new MarketResponseDTO("You put your weapon to the market");
      }
    }
    throw new WeaponException("There is no such weapon");
  }

  @Override
  public MarketResponseDTO putArmorToMarket(NinjaHero ninjaHero, ArmorMarketDTO armorMarketDTO) throws ArmorException {
    for (Armor armor : ninjaHero.getArmors()) {
      if (armor.getId() == armorMarketDTO.getArmodId()) {
        armor.setOnMarket(true);
        armor.setUsed(false);
        armor.setPrice(armorMarketDTO.getPrice());
        return new MarketResponseDTO("You put your weapon to the market");
      }
    }
    throw new ArmorException("There is no such armor");
  }

  @Override
  public MarketResponseDTO buyWeapon(NinjaHero ninjaHero, long weaponId) throws WeaponException, MoneyException {
    Weapon weapon = weaponService.findWeaponById(weaponId);
    purchaseService.checkMoneyForWeapon(ninjaHero, weapon);
    ninjaHero.setMoney(ninjaHero.getMoney() - weapon.getPrice());
    weapon.setNinjaHero(ninjaHero);
    weapon.setOnMarket(false);
    ninjaHero.addWeapon(weapon);
    ninjaHeroRepository.save(ninjaHero);
    return new MarketResponseDTO("You bought a " + weapon.getWeaponType());
  }

  @Override
  public MarketResponseDTO buyArmor(NinjaHero ninjaHero, long armorId) throws ArmorException, MoneyException {
    Armor armor = armorService.findArmorById(armorId);
    purchaseService.checkMoneyForArmor(ninjaHero,armor);
    ninjaHero.setMoney(ninjaHero.getMoney() - armor.getPrice());
    armor.setNinjaHero(ninjaHero);
    armor.setOnMarket(false);
    ninjaHero.addArmor(armor);
    ninjaHeroRepository.save(ninjaHero);
    return new MarketResponseDTO("You bought a " + armor.getClass().getSimpleName());
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
