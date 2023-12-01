package com.pavila.blog.service.auth;

import com.pavila.blog.dto.RegisterUserDTORequest;
import com.pavila.blog.dto.RegisteredUserDTOResponse;
import com.pavila.blog.dto.UserProfileResponse;
import com.pavila.blog.dto.auth.AuthenticationRequest;
import com.pavila.blog.dto.auth.AuthenticationResponse;
import com.pavila.blog.exception.ObjectNotFoundException;
import com.pavila.blog.model.User;
import com.pavila.blog.repository.IUserRepository;
import com.pavila.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public RegisteredUserDTOResponse registerOneUser(RegisterUserDTORequest userRequest) {
        User user = userService.registerOneUser(userRequest);

        RegisteredUserDTOResponse registeredUser = new RegisteredUserDTOResponse();
        registeredUser.setId(user.getId());
        registeredUser.setName(user.getName());
        registeredUser.setUsername(user.getUsername());
        registeredUser.setRole(user.getRole().name());

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        registeredUser.setJwt(jwt);
        return registeredUser;
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());
        return extraClaims;
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()

        );

        authenticationManager.authenticate(authentication);

        User user = userService.findByUsername(authenticationRequest.getUsername()).get();

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return AuthenticationResponse.builder()
                .jwt(jwt)
                .build();
    }

    public UserProfileResponse findLoggedInUser() {

        UsernamePasswordAuthenticationToken auth =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        String  username = (String) auth.getPrincipal();
        User user = userService.findByUsername(username)
                .orElseThrow(()-> new ObjectNotFoundException("User not found. Username: " + username));

        return UserProfileResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .role(user.getRole())
                .credentialsNonExpired(user.isCredentialsNonExpired())
                .accountNonExpired(user.isAccountNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .build();
    }


    public Boolean validateToke(String jwt){ //prop class
        try {
            jwtService.extractUsername(jwt);
                    return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}


