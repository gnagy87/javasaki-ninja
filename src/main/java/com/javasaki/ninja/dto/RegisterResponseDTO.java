package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterResponseDTO {

  private String status;
  private String message;

  public RegisterResponseDTO(String status, String message) {
    this.status = status;
    this.message = message;
  }
}
