package com.javasaki.ninja.weapon;

import javax.persistence.Entity;

@Entity
public class NinjaSword extends Weapon {

  public NinjaSword() {
    super("Ninja Sword");
    super.damage = 10;
    super.offense = 15;
    super.defense = 20;
    super.price = 30;
  }
}
