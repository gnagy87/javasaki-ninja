package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArmorMarketDTO {

  private String armorType;
  private int price;

  public ArmorMarketDTO(String armorType, int price) {
    this.armorType = armorType;
    this.price = price;
  }
}
