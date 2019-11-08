package com.javasaki.ninja.armor;

import javax.persistence.Entity;

@Entity
public class Shirt extends Armor {

  public Shirt() {
    super("shirt");
    super.price = 10;
    super.armor = 1;
  }
}
