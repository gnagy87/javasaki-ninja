package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeaponMarketDTO {

  private String weaponType;
  private int price;

  public WeaponMarketDTO(String weaponType, int price) {
    this.weaponType = weaponType;
    this.price = price;
  }
}
