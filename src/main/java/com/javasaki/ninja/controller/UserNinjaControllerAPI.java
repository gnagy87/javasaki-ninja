package com.javasaki.ninja.controller;

import com.javasaki.ninja.dto.LoginDTO;
import com.javasaki.ninja.dto.LoginResponseDTO;
import com.javasaki.ninja.dto.RegisterDTO;
import com.javasaki.ninja.dto.RegisterResponseDTO;
import com.javasaki.ninja.security.JwtProvider;
import com.javasaki.ninja.user.UserNinjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserNinjaControllerAPI {

  private UserNinjaService userNinjaService;
  private AuthenticationManager authenticationManager;
  private JwtProvider jwtProvider;

  @Autowired
  public UserNinjaControllerAPI(UserNinjaService userNinjaService, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
    this.userNinjaService = userNinjaService;
    this.authenticationManager = authenticationManager;
    this.jwtProvider = jwtProvider;
  }

  @PostMapping("/register")
  public ResponseEntity registration(@Valid @RequestBody RegisterDTO registerDTO) {
    try {
      return ResponseEntity.status(200).body(new RegisterResponseDTO("ok", "registered successfully"));
    } catch (Exception err) {
      return ResponseEntity.status(400).body(new RegisterResponseDTO("error", err.getMessage()));
    }
  }

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
    if (!userNinjaService.isUserExists(loginDTO.getUsername())) {
      return ResponseEntity.status(401).body("Username is not exists!");
    }
    try {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String token = jwtProvider.generateJwtToken(loginDTO.getUsername());
      return ResponseEntity.status(200).body(new LoginResponseDTO("login successfully", token));
    } catch (Exception err) {
      return ResponseEntity.status(401).body(new LoginResponseDTO(err.getMessage()));
    }
  }
}
