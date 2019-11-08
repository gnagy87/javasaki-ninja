package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FightDTO {

  private String challenger;
  private String challenged;

  public FightDTO(String challenger, String challenged) {
    this.challenger = challenger;
    this.challenged = challenged;
  }
}
