package com.javasaki.ninja.armor;

import javax.persistence.Entity;

@Entity
public class Steel extends Armor {

  public Steel() {
    super("Steel");
    super.price = 1000;
    super.armor = 10;
  }
}
