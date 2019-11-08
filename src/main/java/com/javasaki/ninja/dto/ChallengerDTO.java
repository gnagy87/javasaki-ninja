package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChallengerDTO {

  private String challengedName;
  private int bet;

  public ChallengerDTO(String challengedName, int bet) {
    this.challengedName = challengedName;
    this.bet = bet;
  }
}
