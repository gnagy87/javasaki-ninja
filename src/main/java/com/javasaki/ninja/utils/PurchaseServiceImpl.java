package com.javasaki.ninja.utils;

import com.javasaki.ninja.armor.Armor;
import com.javasaki.ninja.exception.MoneyException;
import com.javasaki.ninja.ninja.NinjaHero;
import com.javasaki.ninja.weapon.Weapon;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

  @Override
  public void checkMoneyForWeapon(NinjaHero ninjaHero, Weapon weapon) throws MoneyException {
    if (ninjaHero.getMoney() < weapon.getPrice()) {
      throw new MoneyException("You don't have enough money");
    }
  }

  @Override
  public void checkMoneyForArmor(NinjaHero ninjaHero, Armor armor) throws MoneyException {
    if (ninjaHero.getMoney() < armor.getPrice()) {
      throw new MoneyException("You don't have enough money");
    }
  }

  @Override
  public void enoughMoneyForTrain(NinjaHero ninjaHero, int price) throws MoneyException {
    if (ninjaHero.getMoney() < price) {
      throw new MoneyException("Go to work, you don't have money to train");
    }
  }
}
