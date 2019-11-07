package com.javasaki.ninja.controller;

import com.javasaki.ninja.dto.NinjaDTO;
import com.javasaki.ninja.dto.TrainDTO;
import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.ninja.NinjaHeroService;
import com.javasaki.ninja.user.UserNinjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.javasaki.ninja.dto.PrizeDTO;
import com.javasaki.ninja.exception.TimeException;
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

  @GetMapping("/ninja")
  public ResponseEntity getNinjaHero(HttpServletRequest request) {
    try {
      long id = userNinjaService.getIdFromToken(request);
      NinjaDTO dto = new NinjaDTO(ninjaHeroService.findNinjaById(id));
      return ResponseEntity.status(200).body(dto);
    } catch (Exception err) {
      return ResponseEntity.status(400).body(err.getMessage());
    }
  }


  @PutMapping("/bonus")
  public ResponseEntity dailyBonus(HttpServletRequest request) {
    try {
      return ResponseEntity.status(200).body(new PrizeDTO("You won", ninjaHeroService.dailyBonus(userNinjaService.getIdFromToken(request))));
    } catch (TimeException e) {
      return ResponseEntity.status(409).body(e.getMessage());
    }
  }

  @PutMapping("/train")
  public ResponseEntity trainNinja(HttpServletRequest request, TrainDTO trainDTO) {
    try {
      NinjaDTO ninjaDTO = ninjaHeroService.trainNinjaHero(userNinjaService.getIdFromToken(request), trainDTO);
      return ResponseEntity.status(200).body(ninjaDTO);
    } catch (NinjaException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }
}

