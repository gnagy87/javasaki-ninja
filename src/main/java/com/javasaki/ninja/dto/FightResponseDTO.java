package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class FightResponseDTO {

  private List<NinjaFightDTO> fighters;

  public void addFighter(NinjaFightDTO fighter) {
    fighters.add(fighter);
  }
}
