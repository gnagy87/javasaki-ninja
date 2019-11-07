package com.javasaki.ninja.weapon;

import javax.persistence.Entity;

@Entity
public class Suriken extends Weapon {

  public Suriken() {
    super("Suriken");
    super.damage = 5;
    super.offense = 20;
    super.defense = 5;
    super.price = 5;
  }
}
