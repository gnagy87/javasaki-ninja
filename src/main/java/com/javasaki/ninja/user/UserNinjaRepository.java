package com.javasaki.ninja.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserNinjaRepository extends CrudRepository<UserNinja, Long> {

  Optional<UserNinja> findUserNinjaByUsername(String username);
}
