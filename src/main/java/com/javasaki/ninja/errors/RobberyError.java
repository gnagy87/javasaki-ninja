package com.javasaki.ninja.errors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RobberyError {

  private String status;
  private String message;

  public RobberyError(String status, String message) {
    this.status = status;
    this.message = message;
  }
}
