package com.javasaki.ninja.dto;

import com.javasaki.ninja.armor.Armor;
import com.javasaki.ninja.ninja.NinjaHero;
import com.javasaki.ninja.weapon.Weapon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class NinjaDTO {

  private long id;
  private String name;
  private int hp;
  private int offence;
  private int defence;
  private int speed;
  private int money;
  private boolean isInJail;
  private boolean isTraining;
  private long finishedAt;
  private List<WeaponDTO> weapons;
  private List<ArmorDTO> armors;

  public NinjaDTO(NinjaHero ninjaHero) {
    this.id = ninjaHero.getId();
    this.name = ninjaHero.getName();
    this.hp = ninjaHero.getHp();
    this.offence = ninjaHero.getOffence();
    this.defence = ninjaHero.getDefence();
    this.speed = ninjaHero.getSpeed();
    this.money = ninjaHero.getMoney();
    this.isInJail = ninjaHero.isInJail();
    this.isTraining = ninjaHero.isTraining();
    this.finishedAt = ninjaHero.getFinishedAt();
    this.weapons = weaponList(ninjaHero.getWeapons());
    this.armors = armorList(ninjaHero.getArmors());
  }

  private List<ArmorDTO> armorList(List<Armor> armors) {
    return armors.stream()
            .map(armor -> new ArmorDTO(armor))
            .collect(Collectors.toList());
  }

  private List<WeaponDTO> weaponList(List<Weapon> weapons) {
    return weapons.stream()
            .map(weapon -> new WeaponDTO(weapon))
            .collect(Collectors.toList());
  }
}
