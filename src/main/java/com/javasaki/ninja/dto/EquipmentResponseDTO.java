package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentResponseDTO {

  private String status;

  public EquipmentResponseDTO() {
    this.status = "Equipment(s) changed";
  }
}
