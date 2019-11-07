package com.javasaki.ninja.factory;

import com.javasaki.ninja.character.NinjaHero;
import com.javasaki.ninja.character.NinjaType;
import com.javasaki.ninja.exception.NinjaException;

public class NinjaFactory implements Factory {
  @Override
  public NinjaHero createNinja(String type, String name) throws NinjaException {
    try {
      return NinjaType.valueOf(type.toUpperCase()).createNinja(name);
    } catch (IllegalArgumentException err) {
      throw new NinjaException(err.getMessage());
    }
  }
}
