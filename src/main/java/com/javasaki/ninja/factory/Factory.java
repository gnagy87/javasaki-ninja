package com.javasaki.ninja.factory;

import com.javasaki.ninja.exception.WeaponException;
import com.javasaki.ninja.ninja.NinjaHero;
import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.weapon.Weapon;

public interface Factory {

  NinjaHero createNinja(String type, String name) throws NinjaException;

  Weapon createWeapon(String type) throws WeaponException;
}