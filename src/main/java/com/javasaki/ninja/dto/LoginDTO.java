package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {

  private String username;
  private String password;

  public LoginDTO(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
