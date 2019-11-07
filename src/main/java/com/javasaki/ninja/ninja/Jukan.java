package com.javasaki.ninja.ninja;

import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Jukan extends NinjaHero {

  public Jukan(String name) {
    super(name);
    super.name = name;
    super.hp = 40;
    super.defence = 6;
    super.offence = 9;
    super.speed = 4;
  }
}
