package com.javasaki.ninja.ninja;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Naikan extends NinjaHero {

  public Naikan(String name) {
    super(name);
    super.hp = 70;
    super.defence = 5;
    super.offence = 8;
    super.speed = 4;
  }
}
