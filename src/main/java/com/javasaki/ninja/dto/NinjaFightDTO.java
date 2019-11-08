package com.javasaki.ninja.dto;

import com.javasaki.ninja.ninja.NinjaHero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NinjaFightDTO {

  private String name;
  private int hp;
  private int offence;
  private int defence;
  private int speed;

  public NinjaFightDTO(NinjaHero ninjaHero) {
    this.name = ninjaHero.getName();
    this.hp = ninjaHero.getHp();
    this.offence = ninjaHero.getOffence();
    this.defence = ninjaHero.getDefence();
    this.speed = ninjaHero.getSpeed();
  }
}
