package com.pavila.blog.controller;

import com.pavila.blog.dto.RegisterUserDTORequest;
import com.pavila.blog.dto.RegisteredUserDTOResponse;
import com.pavila.blog.dto.UserProfileResponse;


import com.pavila.blog.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<RegisteredUserDTOResponse> saveUser(@RequestBody @Valid RegisterUserDTORequest userRequest){
        RegisteredUserDTOResponse registeredUser = authenticationService.registerOneUser(userRequest);
        return ResponseEntity.ok(registeredUser);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("profile")
    public ResponseEntity<UserProfileResponse> findMyProfile(){
        UserProfileResponse userProfileResponse = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(userProfileResponse);
    }

    
}
