package com.javasaki.ninja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDTO {

  @NotNull(message = "Please provide a username!")
  private String username;
  @NotNull(message = "Please provide a name of your hero!")
  private String heroName;
  @NotNull(message = "Please provide an email!")
  private String email;
  @NotNull(message = "Please provide a password!")
  private String password;

  public RegisterDTO(String username, String heroName, String email, String password) {
    this.username = username;
    this.heroName = heroName;
    this.email = email;
    this.password = password;
  }
}
