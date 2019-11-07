package com.javasaki.ninja.armor;

import javax.persistence.Entity;

@Entity
public class Skin extends Armor {

  public Skin() {
    super("Skin");
    super.price = 300;
    super.armor = 3;
  }
}
