package com.javasaki.ninja.weapon;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeaponRepository extends CrudRepository<Weapon, Long> {

  Optional<Weapon> findWeaponByWeaponType(String type);

  @Override
  Iterable<Weapon> findAll();
}
