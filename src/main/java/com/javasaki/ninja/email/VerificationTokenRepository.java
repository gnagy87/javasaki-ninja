package com.javasaki.ninja.email;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {

  VerificationToken findUserNinjaByToken(String token);

  VerificationToken findTokenByUserId(Long id);

}
