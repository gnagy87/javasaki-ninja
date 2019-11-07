package com.javasaki.ninja.utils;

import com.javasaki.ninja.armor.Armor;
import com.javasaki.ninja.exception.MoneyException;
import com.javasaki.ninja.ninja.NinjaHero;
import com.javasaki.ninja.weapon.Weapon;

public interface PurchaseService {

  void checkMoneyForWeapon(NinjaHero ninjaHero, Weapon weapon) throws MoneyException;

  void checkMoneyForArmor(NinjaHero ninjaHero, Armor armor) throws MoneyException;
}
