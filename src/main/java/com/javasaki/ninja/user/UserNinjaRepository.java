package com.javasaki.ninja.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserNinjaRepository extends CrudRepository<UserNinja, Long> {

  Optional<UserNinja> findUserNinjaByUsername(String username);
}
