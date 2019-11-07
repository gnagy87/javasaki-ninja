package com.javasaki.ninja.armor;

public enum ArmorType {
  CHAIN {
    @Override
    public Armor createArmor() {
      return new Chain();
    }
  },
  SKIN {
    @Override
    public Armor createArmor() {
      return new Skin();
    }
  },
  STEEL {
    @Override
    public Armor createArmor() {
      return new Steel();
    }
  };
  public abstract Armor createArmor();
}
