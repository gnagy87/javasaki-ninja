package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarketResponseDTO {

  private String status;

  public MarketResponseDTO(String status) {
    this.status = status;
  }
}
