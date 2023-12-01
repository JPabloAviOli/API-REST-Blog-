package com.pavila.blog.controller;

import com.pavila.blog.dto.UserProfileResponse;
import com.pavila.blog.dto.auth.AuthenticationRequest;
import com.pavila.blog.dto.auth.AuthenticationResponse;
import com.pavila.blog.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        AuthenticationResponse authResponse = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestParam String jwt){
        boolean isTokenValid = authenticationService.validateToke(jwt);
        return ResponseEntity.ok(isTokenValid);
    }


}
