package com.javasaki.ninja.weapon;

import javax.persistence.Entity;

@Entity
public class Nunchaku extends Weapon {

  public Nunchaku() {
    super("Nunchaku");
    super.damage = 7;
    super.offense = 17;
    super.defense = 10;
    super.price = 20;
  }
}
