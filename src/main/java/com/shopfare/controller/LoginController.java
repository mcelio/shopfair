package com.shopfare.controller;

import com.shopfare.security.ApplicationUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("/login")
public class LoginController {

  @PostMapping
  public void login(@RequestBody ApplicationUser user) {
  }
}
