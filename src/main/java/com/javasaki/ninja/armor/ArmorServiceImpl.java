package com.javasaki.ninja.armor;

import com.javasaki.ninja.exception.ArmorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArmorServiceImpl implements ArmorService{

  private ArmorRepository armorRepository;

  @Autowired
  public ArmorServiceImpl(ArmorRepository armorRepository) {
    this.armorRepository = armorRepository;
  }

  @Override
  public Armor findArmorByType(String type) throws ArmorException {
    return armorRepository.findArmorByType(type).orElseThrow(
            () -> new ArmorException("There is no such armor!"));
  }
}
