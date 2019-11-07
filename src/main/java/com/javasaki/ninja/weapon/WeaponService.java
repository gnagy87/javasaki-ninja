package com.javasaki.ninja.weapon;

import com.javasaki.ninja.exception.WeaponException;

public interface WeaponService {

  Weapon findWeaponByType(String type) throws WeaponException;
}
