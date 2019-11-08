package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeaponMarketDTO {

  private long weaponId;
  private int price;

  public WeaponMarketDTO(Long weaponId, int price) {
    this.weaponId = weaponId;
    this.price = price;
  }
}
