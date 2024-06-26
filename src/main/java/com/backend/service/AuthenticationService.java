package com.backend.service;

import com.backend.auth.AuthenticationRequest;
import com.backend.auth.RegisterRequest;
import com.backend.config.JwtService;
import com.backend.repository.UserRepository;
import com.backend.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  public String authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
    );
    var user = repository.findByUsername(request.getUsername()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return jwtToken;
  }


  public String register(RegisterRequest request) {
    var user = User
              .builder()
              .username(request.getUsername())
              .password(passwordEncoder.encode(request.getPassword()))
              .build();

    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return jwtToken;
  }
}
