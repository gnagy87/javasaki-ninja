package com.javasaki.ninja.ninja;

import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Inkan extends NinjaHero {

  public Inkan(String name) {
    super(name);
    super.hp = 60;
    super.defence = 8;
    super.offence = 5;
    super.speed = 5;
  }
}
