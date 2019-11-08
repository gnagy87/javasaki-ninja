package com.javasaki.ninja.ninja;

import com.javasaki.ninja.dto.*;
import com.javasaki.ninja.exception.*;

public interface NinjaHeroService {

  PrizeDTO performRobberyByUserId(Long authorization) throws TimeException;

  NinjaHero findNinjaById(long id);

  int dailyBonus(long id) throws TimeException;

  NinjaDTO trainNinjaHero(long id, TrainDTO trainDTO) throws MoneyException, TimeException;

  void setEquipment(NinjaHero ninjaHero, EquipmentDTO equipmentDTO) throws ArmorException, WeaponException;

  void setArmomr(NinjaHero ninjaHero, String type) throws ArmorException;

  void setWeapon(NinjaHero ninjaHero, String type) throws WeaponException;

  PrizeDTO performWorkByUserId(Long userId) throws TimeException;

  MarketResponseDTO putWeaponToMarket(NinjaHero hero, WeaponMarketDTO weaponMarketDTO) throws WeaponException;

  MarketResponseDTO putArmorToMarket(NinjaHero ninjaHero, ArmorMarketDTO armorMarketDTO) throws ArmorException;

  MarketResponseDTO buyWeapon(NinjaHero ninjaHero, long weaponId) throws WeaponException, MoneyException;

  MarketResponseDTO buyArmor(NinjaHero ninjaHero, long armorId) throws ArmorException, MoneyException;
}
