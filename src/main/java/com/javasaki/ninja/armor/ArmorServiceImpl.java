package com.javasaki.ninja.armor;

import com.javasaki.ninja.exception.ArmorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArmorServiceImpl implements ArmorService {

  private ArmorRepository armorRepository;

  @Autowired
  public ArmorServiceImpl(ArmorRepository armorRepository) {
    this.armorRepository = armorRepository;
  }

  @Override
  public Armor findArmorByType(String type) throws ArmorException {
    return armorRepository.findArmorByType(type).orElseThrow(() -> new ArmorException("There is no such armor!"));
  }

  @Override
  public List<Armor> findAllArmor() {
    List<Armor> result = new ArrayList<>();
    armorRepository.findAll().forEach(result::add);
    return result;
  }

  @Override
  public Armor findArmorById(long armorId) throws ArmorException {
    return armorRepository.findById(armorId).orElseThrow(() -> new ArmorException("There is no such armor with id: " + armorId));
  }
}
