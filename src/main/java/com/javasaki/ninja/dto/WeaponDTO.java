package com.javasaki.ninja.dto;

import com.javasaki.ninja.weapon.Weapon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeaponDTO {
  private long id;
  private String weaponType;
  private int damage;
  private int offense;
  private int defense;
  private int price;

  public WeaponDTO(Weapon weapon) {
    this.id = weapon.getId();
    this.weaponType = weapon.getWeaponType();
    this.damage = weapon.getDamage();
    this.offense = weapon.getOffense();
    this.defense = weapon.getDefense();
    this.price = weapon.getPrice();
  }
}
