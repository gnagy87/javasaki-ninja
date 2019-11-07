package com.javasaki.ninja.armor;

import javax.persistence.Entity;

@Entity
public class Chain extends Armor {

  public Chain() {
    super("Chain");
    super.price = 600;
    super.armor = 7;
  }
}
