package com.javasaki.ninja.weapon;

import javax.persistence.Entity;

@Entity
public class BambooStick extends Weapon{

  public BambooStick() {
    super("Bamboo stick");
    super.damage = 3;
    super.offense = 5;
    super.defense = 6;
    super.price = 0;
  }
}
