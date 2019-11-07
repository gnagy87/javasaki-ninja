package com.javasaki.ninja.controller;

import com.javasaki.ninja.dto.LoginDTO;
import com.javasaki.ninja.dto.LoginResponseDTO;
import com.javasaki.ninja.dto.RegisterDTO;
import com.javasaki.ninja.dto.RegisterResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserNinjaControllerAPI {

  @PostMapping("/register")
  public ResponseEntity registration(@Valid @RequestBody RegisterDTO registerDTO) {
    try {
      return ResponseEntity.status(200).body(new RegisterResponseDTO("ok", "registered successfully"));
    } catch (Exception err) {
      return ResponseEntity.status(400).body(new RegisterResponseDTO("error", err.getMessage()));
    }
  }

  @PostMapping("/login")
  public ResponseEntity legin(@RequestBody LoginDTO loginDTO) {
    try {
      return ResponseEntity.status(200).body(new LoginResponseDTO("ok","login successfully", ""));
    } catch (Exception err) {
      return ResponseEntity.status(401).body(new LoginResponseDTO("error", err.getMessage()));
    }
  }
}
