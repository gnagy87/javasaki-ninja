package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArmorMarketDTO {

  private long armodId;
  private int price;

  public ArmorMarketDTO(long armodId, int price) {
    this.armodId = armodId;
    this.price = price;
  }
}
