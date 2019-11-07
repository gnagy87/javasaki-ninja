package com.javasaki.ninja.controller;

import com.javasaki.ninja.ninja.NinjaHeroService;
import com.javasaki.ninja.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GamePlayControllerAPI {

  private NinjaHeroService ninjaHeroService;
  private JwtProvider jwtProvider;

  @Autowired
  public GamePlayControllerAPI(NinjaHeroService ninjaHeroService, JwtProvider jwtProvider) {
    this.ninjaHeroService = ninjaHeroService;
    this.jwtProvider = jwtProvider;
  }

//  @PutMapping("/bonus")
//  public ResponseEntity dailyBonus() {
//
//  }

  @PostMapping("/robbery")
  public ResponseEntity performRobbery(HttpServletRequest request) {

    if (ninjaHeroService.performRobberyByUserId(jwtProvider.getIdFromToken(
        request.getHeader("Authorization").substring(7)))) {
      return ResponseEntity.status(200).body("successful robbery made");
    } else {
     return ResponseEntity.status(200).body("unsuccessful robbery made");
    }
  }

}
