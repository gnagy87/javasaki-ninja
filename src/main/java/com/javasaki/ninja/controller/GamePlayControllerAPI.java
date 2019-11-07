package com.javasaki.ninja.controller;

import com.javasaki.ninja.dto.NinjaDTO;
import com.javasaki.ninja.ninja.NinjaHeroService;
import com.javasaki.ninja.user.UserNinja;
import com.javasaki.ninja.user.UserNinjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GamePlayControllerAPI {

  private NinjaHeroService ninjaHeroService;
  private UserNinjaService userNinjaService;

  @Autowired
  public GamePlayControllerAPI(NinjaHeroService ninjaHeroService) {
    this.ninjaHeroService = ninjaHeroService;
  }

  @GetMapping("/ninja")
  public ResponseEntity getNinjaHero(HttpServletRequest request) {
    try {
      //long id = userNinj
      //return ResponseEntity.status(200).body(new NinjaDTO(ninjaHeroService))
    } catch (Exception err) {
      return ResponseEntity.status(400).body(err.getMessage());
    }
  }
}
