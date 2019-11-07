package com.javasaki.ninja.factory;

import com.javasaki.ninja.character.NinjaHero;
import com.javasaki.ninja.exception.NinjaException;

public interface Factory {

  NinjaHero createNinja(String type, String name) throws NinjaException;
}
