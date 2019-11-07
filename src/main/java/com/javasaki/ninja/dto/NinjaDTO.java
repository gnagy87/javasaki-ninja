package com.javasaki.ninja.dto;

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
  private int armor;
  private int offence;
  private int defence;
  private int speed;
  private int money;
  private boolean isInJail;
  private boolean isTraining;
  private long finishedAt;
  private List<WeaponDTO> weapons;

  public NinjaDTO(NinjaHero ninjaHero) {
    this.id = ninjaHero.getId();
    this.name = ninjaHero.getName();
    this.hp = ninjaHero.getHp();
    this.armor = ninjaHero.getArmor();
    this.offence = ninjaHero.getOffence();
    this.defence = ninjaHero.getDefence();
    this.speed = ninjaHero.getSpeed();
    this.money = ninjaHero.getMoney();
    this.isInJail = ninjaHero.isInJail();
    this.isTraining = ninjaHero.isTraining();
    this.finishedAt = ninjaHero.getFinishedAt();
    this.weapons = weaponList(ninjaHero.getWeapons());
  }

  private List<WeaponDTO> weaponList(List<Weapon> weapons) {
    return weapons.stream()
            .map(weapon -> new WeaponDTO(weapon))
            .collect(Collectors.toList());
  }
}
