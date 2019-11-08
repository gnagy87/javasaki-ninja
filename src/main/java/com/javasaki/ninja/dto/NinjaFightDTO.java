package com.javasaki.ninja.dto;

import com.javasaki.ninja.armor.Armor;
import com.javasaki.ninja.ninja.NinjaHero;
import com.javasaki.ninja.weapon.Weapon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NinjaFightDTO {

  private String type;
  private String name;
  private int hp;
  private int offence;
  private int defence;
  private int speed;
  private Weapon weapon;
  private Armor armor;

  public NinjaFightDTO(NinjaHero ninjaHero) {
    this.name = ninjaHero.getName();
    this.hp = ninjaHero.getHp();
    this.offence = ninjaHero.getOffence();
    this.defence = ninjaHero.getDefence();
    this.speed = ninjaHero.getSpeed();
    this.weapon = getUsingWeapon(ninjaHero);
    this.armor = getUsingArmor(ninjaHero);
  }

  private Weapon getUsingWeapon(NinjaHero ninjaHero) {
    for (int i = 0; i < ninjaHero.getWeapons().size(); i++) {
      if (ninjaHero.getWeapons().get(i).isUsed()) {
        return ninjaHero.getWeapons().get(i);
      }
    }
    return ninjaHero.getWeapons().get(0);
  }

  private Armor getUsingArmor(NinjaHero ninjaHero) {
    for (int i = 0; i < ninjaHero.getArmors().size(); i++) {
      if (ninjaHero.getArmors().get(i).isUsed()) {
        return ninjaHero.getArmors().get(i);
      }
    }
    return null;
  }
}
