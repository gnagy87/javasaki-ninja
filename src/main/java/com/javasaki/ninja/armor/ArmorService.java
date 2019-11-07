package com.javasaki.ninja.armor;

import com.javasaki.ninja.exception.ArmorException;

public interface ArmorService {

  Armor findArmorByType(String type) throws ArmorException;
}
