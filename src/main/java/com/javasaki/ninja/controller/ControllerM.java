package com.javasaki.ninja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerM {

  @GetMapping("/")
  public String main(){
    return "index";
  }
}
