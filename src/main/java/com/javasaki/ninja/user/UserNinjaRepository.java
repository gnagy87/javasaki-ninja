package com.javasaki.ninja.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNinjaRepository extends CrudRepository<UserNinja, Long> {
}
