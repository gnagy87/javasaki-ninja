package com.javasaki.ninja.controller;

import com.javasaki.ninja.armor.ArmorService;
import com.javasaki.ninja.dto.*;
import com.javasaki.ninja.errors.RobberyError;
import com.javasaki.ninja.errors.StatusMessageDTO;
import com.javasaki.ninja.exception.MoneyException;
import com.javasaki.ninja.exception.TimeException;
import com.javasaki.ninja.exception.NinjaException;
import com.javasaki.ninja.ninja.NinjaHero;
import com.javasaki.ninja.ninja.NinjaHeroService;
import com.javasaki.ninja.security.JwtProvider;
import com.javasaki.ninja.user.UserNinjaService;
import com.javasaki.ninja.weapon.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GamePlayControllerAPI {

  private NinjaHeroService ninjaHeroService;
  private JwtProvider jwtProvider;
  private UserNinjaService userNinjaService;
//  private WeaponService weaponService;
//  private ArmorService armorService;

  @Autowired
  public GamePlayControllerAPI(NinjaHeroService ninjaHeroService, JwtProvider jwtProvider,
                               UserNinjaService userNinjaService/*, WeaponService weaponService,
                               ArmorService armorService*/) {
    this.ninjaHeroService = ninjaHeroService;
    this.jwtProvider = jwtProvider;
    this.userNinjaService = userNinjaService;
//    this.weaponService = weaponService;
//    this.armorService = armorService;
  }

  @PostMapping("/robbery")
  public ResponseEntity performRobbery(HttpServletRequest request) {

    try {
      return ResponseEntity.status(200).body(ninjaHeroService.performRobberyByUserId(
          userNinjaService.getIdFromToken(request)));
    } catch (Exception e) {
      return ResponseEntity.status(409).body(new RobberyError(
          "Unable to perform this activity.", e.getMessage()));
    }
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
      return ResponseEntity.status(200).body(new PrizeDTO("You won", ninjaHeroService.dailyBonus(
          userNinjaService.getIdFromToken(request))));
    } catch (TimeException e) {
      return ResponseEntity.status(409).body(new PrizeDTO(e.getMessage(), 0));
    }
  }

  @PutMapping("/train")
  public ResponseEntity trainNinja(HttpServletRequest request, @RequestBody TrainDTO trainDTO) {
    try {
      NinjaDTO ninjaDTO = ninjaHeroService.trainNinjaHero(userNinjaService.getIdFromToken(request), trainDTO);
      return ResponseEntity.status(200).body(ninjaDTO);
    } catch (NinjaException | MoneyException e) {
      return ResponseEntity.status(409).body(e.getMessage());
    }
  }

  @PostMapping("/work")
  public ResponseEntity performWork(HttpServletRequest request) {
    try {
      return ResponseEntity.status(200).body(ninjaHeroService.performWorkByUserId(
          userNinjaService.getIdFromToken(request)));
    } catch (Exception e) {
      return ResponseEntity.status(409).body(new StatusMessageDTO(e.getMessage()));
    }
  }

  @PutMapping("/equipment")
  public ResponseEntity setupEquipment(HttpServletRequest request, @RequestBody EquipmentDTO equipmentDTO) {
    try {
      NinjaHero hero = ninjaHeroService.findNinjaById(userNinjaService.getIdFromToken(request));
      ninjaHeroService.setEquipment(hero, equipmentDTO);
      return ResponseEntity.status(200).body(new EquipmentResponseDTO());
    } catch (Exception err) {
      return ResponseEntity.status(400).body(err.getMessage());
    }
  }

//  @GetMapping("/market")
//  public ResponseEntity getMatket() {
//    return ResponseEntity.status(200).body(new MarketDTO(weaponService.findAllWeapon(),armorService.findAllArmor()));
//  }
}

