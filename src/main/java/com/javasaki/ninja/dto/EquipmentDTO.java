package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentDTO {

  private String weapon;
  private String armor;

  public EquipmentDTO(String weapon, String armor) {
    this.weapon = weapon;
    this.armor = armor;
  }
}
