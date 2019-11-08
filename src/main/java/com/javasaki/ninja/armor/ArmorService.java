package com.javasaki.ninja.armor;

import com.javasaki.ninja.exception.ArmorException;

import java.util.List;

public interface ArmorService {

  Armor findArmorByType(String type) throws ArmorException;

  List<Armor> findAllArmor();
}
