package com.javasaki.ninja.ninja;

import com.javasaki.ninja.user.UserNinja;
import org.springframework.data.repository.CrudRepository;

public interface NinjaHeroRepository extends CrudRepository<NinjaHero, Long> {

  NinjaHero findNinjaHeroByUserNinja(UserNinja userNinja);
}
