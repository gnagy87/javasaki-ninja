package com.javasaki.ninja.weapon;

import com.javasaki.ninja.exception.WeaponException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeaponServiceImpl implements WeaponService {

  private WeaponRepository weaponRepository;

  @Autowired
  public WeaponServiceImpl(WeaponRepository weaponRepository) {
    this.weaponRepository = weaponRepository;
  }

  @Override
  public Weapon findWeaponByType(String type) throws WeaponException {
    return weaponRepository.findWeaponByWeaponType(type).orElseThrow(() -> new WeaponException("There is no such weapon!"));
  }

  @Override
  public List<Weapon> findAllWeapon() {
    List<Weapon> result = new ArrayList<>();
    weaponRepository.findAll().forEach(result::add);
    return result;
  }

  @Override
  public Weapon findWeaponById(long weaponId) throws WeaponException {
    return weaponRepository.findById(weaponId).orElseThrow(() -> new WeaponException("There is no such weapon with id: " + weaponId));
  }
}
