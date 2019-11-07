package com.javasaki.ninja.weapon;

public enum WeaponType {
  SWORD {
    @Override
    public Weapon createWeapon() {
      return new NinjaSword();
    }
  },
  NUNCHAKU {
    @Override
    public Weapon createWeapon() {
      return new Nunchaku();
    }
  },
  SURIKEN {
    @Override
    public Weapon createWeapon() {
      return new Suriken();
    }
  },
  BAMBOO {
    @Override
    public Weapon createWeapon() {
      return new BambooStick();
    }
  };
  public abstract Weapon createWeapon();
}
