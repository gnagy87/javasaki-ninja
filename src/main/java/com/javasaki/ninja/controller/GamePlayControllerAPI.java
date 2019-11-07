package com.javasaki.ninja.controller;

import com.javasaki.ninja.ninja.NinjaHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GamePlayControllerAPI {

  private NinjaHeroService ninjaHeroService;

  @Autowired
  public GamePlayControllerAPI(NinjaHeroService ninjaHeroService) {
    this.ninjaHeroService = ninjaHeroService;
  }

  @GetMapping("/ninja")
  public ResponseEntity getNinjaHero(HttpServletRequest request) {
    try {
      return null;
    } catch (Exception err) {
      return ResponseEntity.status(400).body(err.getMessage());
    }
  }
}
