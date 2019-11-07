package com.javasaki.ninja.weapon;

import com.javasaki.ninja.exception.WeaponException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
