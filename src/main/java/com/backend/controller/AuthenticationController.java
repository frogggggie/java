package com.backend.controller;

import com.backend.auth.AuthenticationRequest;
import com.backend.auth.RegisterRequest;
import com.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor()
public class AuthenticationController {

  private final AuthenticationService service;


  @PostMapping("/register")
  public ResponseEntity register (
          @RequestBody RegisterRequest request
          )  {
    String jwt = service.register(request);
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("jwt", jwt);
    return ResponseEntity
            .ok()
            .headers(responseHeaders)
            .build();
  }

  @PostMapping("/login")
  public ResponseEntity authenticate (
          @RequestBody AuthenticationRequest request
  )  {
    String jwt = service.authenticate(request);
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("jwt", jwt);
    return ResponseEntity
            .ok()
            .headers(responseHeaders)
            .build();
  }
}
