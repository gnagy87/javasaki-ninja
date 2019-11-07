package com.javasaki.ninja.controller;

import com.javasaki.ninja.dto.PrizeDTO;
import com.javasaki.ninja.exception.TimeException;
import com.javasaki.ninja.ninja.NinjaHeroService;
import com.javasaki.ninja.user.UserNinjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GamePlayControllerAPI {

  private NinjaHeroService ninjaHeroService;
  private UserNinjaService userNinjaService;

  @Autowired
  public GamePlayControllerAPI(NinjaHeroService ninjaHeroService, UserNinjaService userNinjaService) {
    this.ninjaHeroService = ninjaHeroService;
    this.userNinjaService = userNinjaService;
  }

  @PutMapping("/bonus")
  public ResponseEntity dailyBonus(HttpServletRequest request) {
    try {
      return ResponseEntity.status(200).body(new PrizeDTO("You won", ninjaHeroService.dailyBonus(userNinjaService.getIdFromToken(request))));
    } catch (TimeException e) {
      return ResponseEntity.status(409).body(new PrizeDTO(e.getMessage(), 0));
    }
  }
}
