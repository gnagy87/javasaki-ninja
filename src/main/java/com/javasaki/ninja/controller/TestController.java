package com.javasaki.ninja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

  @GetMapping("/index")
  public String getIndex() {
    return "index";
  }
}
