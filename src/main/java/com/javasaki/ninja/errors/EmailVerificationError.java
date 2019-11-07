package com.javasaki.ninja.errors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailVerificationError {

  private String status;
  private String message;

  public EmailVerificationError(String errorMessage, String appUrl, String token) {
    this.status = errorMessage;
    this.message = appUrl + "createNewVerificationToken/" + token;
  }
}
