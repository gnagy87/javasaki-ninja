package com.javasaki.ninja.armor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArmorRepository extends CrudRepository<Armor, Long> {

  Optional<Armor> findArmorByType(String type);

  @Override
  Iterable<Armor> findAll();
}
