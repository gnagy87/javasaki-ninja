package com.javasaki.ninja.ninja;

public enum NinjaType {
  NAIKAN {
    @Override
    public NinjaHero createNinja(String name) {
      return new Naikan(name);
    }
  },
  JUKAN {
    @Override
    public NinjaHero createNinja(String name) {
      return new Jukan(name);
    }
  },
  INKAN {
    @Override
    public NinjaHero createNinja(String name) {
      return new Inkan(name);
    }
  };
  public abstract NinjaHero createNinja(String name);
}
