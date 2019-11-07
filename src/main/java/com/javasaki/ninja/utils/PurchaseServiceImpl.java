package com.javasaki.ninja.utils;

import com.javasaki.ninja.armor.Armor;
import com.javasaki.ninja.exception.MoneyException;
import com.javasaki.ninja.ninja.NinjaHero;
import com.javasaki.ninja.weapon.Weapon;

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
}
