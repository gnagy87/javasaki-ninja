package com.javasaki.ninja.dto;

import com.javasaki.ninja.weapon.Weapon;

public class WeaponDTO {
  long id;
  String weaponType;
  int damage;
  int offense;
  int defense;
  int price;

  public WeaponDTO(Weapon weapon) {
    this.id = weapon.getId();
    this.weaponType = weapon.getWeaponType();
    this.damage = weapon.getDamage();
    this.offense = weapon.getOffense();
    this.defense = weapon.getDefense();
    this.price = weapon.getPrice();
  }
}
