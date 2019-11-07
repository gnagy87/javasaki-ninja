package com.javasaki.ninja.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

  @Value("${ninja.app.jwtSecret}")
  private String jwtSecret;

  private int jwtExpiration = 864000000;

  public String generateJwtToken(String username) {

    return Jwts.builder()
            .setSubject(/*username*/username)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody().getSubject();
  }

  public String getIdFromToken(String token) {
    return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody().getId();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature -> Message: {0} ", e);
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token -> Message: {0}", e);
    } catch (ExpiredJwtException e) {
      logger.error("Expired JWT token -> Message: {0}", e);
    } catch (UnsupportedJwtException e) {
      logger.error("Unsupported JWT token -> Message: {0}", e);
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty -> Message: {0}", e);
    }

    return false;
  }
}
