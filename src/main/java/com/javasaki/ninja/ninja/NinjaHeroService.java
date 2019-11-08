package com.javasaki.ninja.ninja;

import com.javasaki.ninja.dto.*;
import com.javasaki.ninja.exception.*;

public interface NinjaHeroService {

  PrizeDTO performRobberyByUserId(Long authorization) throws TimeException;

  NinjaHero findNinjaById(long id);

  int dailyBonus(long id) throws TimeException;

  NinjaDTO trainNinjaHero(long id, TrainDTO trainDTO) throws NinjaException, MoneyException;

  void setEquipment(NinjaHero ninjaHero, EquipmentDTO equipmentDTO) throws ArmorException, WeaponException;

  void setArmomr(NinjaHero ninjaHero, String type) throws ArmorException;

  void setWeapon(NinjaHero ninjaHero, String type) throws WeaponException;

  PrizeDTO performWorkByUserId(Long userId) throws TimeException;

  MarketResponseDTO putWeaponToMarket(NinjaHero hero, WeaponMarketDTO weaponMarketDTO) throws WeaponException;
}
