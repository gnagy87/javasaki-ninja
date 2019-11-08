package com.javasaki.ninja.weapon;

import com.javasaki.ninja.exception.WeaponException;

import java.util.List;

public interface WeaponService {

  Weapon findWeaponByType(String type) throws WeaponException;

  List<Weapon> findAllWeapon();

  Weapon findWeaponById(long weaponId) throws WeaponException;
}
