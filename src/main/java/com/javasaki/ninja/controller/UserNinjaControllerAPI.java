package com.javasaki.ninja.controller;

import com.javasaki.ninja.dto.LoginDTO;
import com.javasaki.ninja.dto.LoginResponseDTO;
import com.javasaki.ninja.dto.RegisterDTO;
import com.javasaki.ninja.dto.RegisterResponseDTO;
import com.javasaki.ninja.email.OnRegistrationCompleteEvent;
import com.javasaki.ninja.email.VerificationToken;
import com.javasaki.ninja.errors.EmailVerificationError;
import com.javasaki.ninja.exception.EmailVerificationException;
import com.javasaki.ninja.security.JwtProvider;
import com.javasaki.ninja.user.UserNinja;
import com.javasaki.ninja.user.UserNinjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserNinjaControllerAPI {

  private UserNinjaService userNinjaService;
  private AuthenticationManager authenticationManager;
  private JwtProvider jwtProvider;
  private ApplicationEventPublisher eventPublisher;

  @Value("${dragonite.app.appDomain:}")
  String appUrl;

  @Autowired
  public UserNinjaControllerAPI(UserNinjaService userNinjaService, AuthenticationManager authenticationManager,
                                JwtProvider jwtProvider, ApplicationEventPublisher eventPublisher) {
    this.userNinjaService = userNinjaService;
    this.authenticationManager = authenticationManager;
    this.jwtProvider = jwtProvider;
    this.eventPublisher = eventPublisher;
  }

  @PostMapping("/register")
  public ResponseEntity registration(@Valid @RequestBody RegisterDTO registerDTO) {
    try {
      RegisterResponseDTO registerResponseDTO = userNinjaService.registration(registerDTO);
      eventPublisher.publishEvent(new OnRegistrationCompleteEvent(
          userNinjaService.findUserNinjaByUsername(registerDTO.getUsername()),
          appUrl));
      return ResponseEntity.status(200).body(registerResponseDTO);
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

      UserNinja userNinja = userNinjaService.findUserNinjaByUsername(loginDTO.getUsername());
      if (!userNinja.isEnable()) {
        VerificationToken token = userNinjaService.findVerificationTokenByUser(userNinja);
        return ResponseEntity.status(400).body(new EmailVerificationError(
            "User is not enabled!", appUrl, token.getToken()));
      }

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String token = jwtProvider.generateJwtToken(loginDTO.getUsername());
      return ResponseEntity.status(200).body(new LoginResponseDTO("login successfully", token));
    } catch (Exception err) {
      return ResponseEntity.status(401).body("Wrong password");
    }
  }

  @GetMapping("/confirmRegistration/{token}")
  public ResponseEntity confirmRegistration(@PathVariable String token) {
    try {
      userNinjaService.enableUserByVerificationToken(token);
      return ResponseEntity.status(200).body("Successful verification. User enabled.");
    } catch (EmailVerificationException e) {
      return ResponseEntity.status(500).body(new EmailVerificationError(e.getMessage(),appUrl, token));
    }
  }

  @GetMapping("/createNewVerificationToken/{token}")
  public ResponseEntity createNewVerificationToken(@PathVariable String token) {
    try {
      eventPublisher.publishEvent(new OnRegistrationCompleteEvent(
          userNinjaService.generateNewTokenForNotEnabledUser(token), appUrl));
      return ResponseEntity.status(200).body("New verification token sent.");
    } catch (EmailVerificationException e) {
      return ResponseEntity.status(500).body(e.getMessage());
    }
  }



}
