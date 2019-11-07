package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDTO {

  private String status;
  private String message;
  private String token;

  public LoginResponseDTO(String message, String token) {
    this.status = "ok";
    this.message = message;
    this.token = token;
  }

  public LoginResponseDTO(String message) {
    this.status = "error";
    this.message = message;
  }
}
