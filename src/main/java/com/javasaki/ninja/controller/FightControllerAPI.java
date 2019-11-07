package com.javasaki.ninja.controller;

import com.javasaki.ninja.dto.FightDTO;
import com.javasaki.ninja.dto.ChallengerDTO;
import com.javasaki.ninja.exception.UserNinjaException;
import com.javasaki.ninja.fight.FightService;
import com.javasaki.ninja.user.UserNinja;
import com.javasaki.ninja.user.UserNinjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FightControllerAPI {

  private UserNinjaService userNinjaService;
  private FightService fightService;

  @Autowired
  public FightControllerAPI(UserNinjaService userNinjaService, FightService fightService) {
    this.userNinjaService = userNinjaService;
    this.fightService = fightService;
  }

  @PutMapping("/challenge")
  public ResponseEntity challengePlayer(@RequestBody ChallengerDTO challengerDTO, HttpServletRequest request) {
    UserNinja userNinja = userNinjaService.findUserNinjaByUsername(userNinjaService.getUsernameFromToken(request));
    try {
      userNinjaService.saveChallenger(challengerDTO, userNinja);
      return ResponseEntity.status(200).body(userNinjaService.findUserNinjaByUsername(challengerDTO.getChallengedName()));
    } catch (UserNinjaException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }

  @PutMapping("/fight")
  public ResponseEntity fight(@RequestBody FightDTO fightDTO, HttpServletRequest request) {
    try {
      fightService.fighters(fightDTO.getChallenger(), fightDTO.getChallenged());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(200).build();
  }
}
