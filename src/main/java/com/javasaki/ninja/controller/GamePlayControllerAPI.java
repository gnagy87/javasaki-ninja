package com.javasaki.ninja.controller;

import com.javasaki.ninja.ninja.NinjaHeroService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GamePlayControllerAPI {

  private NinjaHeroService ninjaHeroService;

  @Autowired
  public GamePlayControllerAPI(NinjaHeroService ninjaHeroService) {
    this.ninjaHeroService = ninjaHeroService;
  }

//  @PutMapping("/bonus")
//  public ResponseEntity dailyBonus() {
//
//  }
}
