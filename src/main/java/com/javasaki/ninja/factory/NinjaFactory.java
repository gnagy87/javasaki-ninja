package com.javasaki.ninja.factory;

import com.javasaki.ninja.exception.WeaponException;
import com.javasaki.ninja.ninja.NinjaHero;
import com.javasaki.ninja.ninja.NinjaType;
import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.weapon.Weapon;
import com.javasaki.ninja.weapon.WeaponType;
import org.springframework.stereotype.Component;

@Component
public class NinjaFactory implements Factory {
  @Override
  public NinjaHero createNinja(String type, String name) throws NinjaException {
    try {
      return NinjaType.valueOf(type.toUpperCase()).createNinja(name);
    } catch (IllegalArgumentException err) {
      throw new NinjaException(err.getMessage());
    }
  }

  @Override
  public Weapon createWeapon(String type) throws WeaponException {
    try {
      return WeaponType.valueOf(type.toUpperCase()).createWeapon();
    } catch (IllegalArgumentException err) {
      throw new WeaponException((err.getMessage()));
    }
  }
}
