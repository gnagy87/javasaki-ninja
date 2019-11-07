package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PrizeDTO {

  private String msg;
  private int amount;

  public PrizeDTO(String msg, int amount) {
    this.msg = msg;
    this.amount = amount;
  }
}
