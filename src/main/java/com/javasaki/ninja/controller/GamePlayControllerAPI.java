package com.javasaki.ninja.controller;

import com.javasaki.ninja.dto.PrizeDTO;
import com.javasaki.ninja.errors.RobberyError;
import com.javasaki.ninja.exception.TimeException;
import com.javasaki.ninja.ninja.NinjaHeroService;
import com.javasaki.ninja.security.JwtProvider;
import com.javasaki.ninja.user.UserNinjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GamePlayControllerAPI {

  private NinjaHeroService ninjaHeroService;
  private JwtProvider jwtProvider;
  private UserNinjaService userNinjaService;

  @Autowired
  public GamePlayControllerAPI(NinjaHeroService ninjaHeroService, JwtProvider jwtProvider,
                               UserNinjaService userNinjaService) {
    this.ninjaHeroService = ninjaHeroService;
    this.jwtProvider = jwtProvider;
    this.userNinjaService = userNinjaService;
  }

  @PostMapping("/robbery")
  public ResponseEntity performRobbery(HttpServletRequest request) {

    try {
      PrizeDTO prizeDTO = ninjaHeroService.performRobberyByUserId(jwtProvider.getIdFromToken(
          request.getHeader("Authorization").substring(7)));
      return ResponseEntity.status(200).body(prizeDTO);
    } catch (Exception e) {
      return ResponseEntity.status(400).body(new RobberyError("Unable to perform this activity.", e.getMessage())) ;
    }
  }

  @PutMapping("/bonus")
  public ResponseEntity dailyBonus(HttpServletRequest request) {
    try {
      return ResponseEntity.status(200).body(new PrizeDTO("You won", ninjaHeroService.dailyBonus(
          userNinjaService.getIdFromToken(request))));
    } catch (TimeException e) {
      return ResponseEntity.status(409).body(e.getMessage());
    }
  }
}
