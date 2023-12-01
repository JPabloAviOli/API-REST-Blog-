package com.pavila.blog.service.impl;

import com.pavila.blog.dto.RegisterUserDTORequest;
import com.pavila.blog.exception.InvalidPasswordException;
import com.pavila.blog.model.User;
import com.pavila.blog.model.util.Role;
import com.pavila.blog.repository.IUserRepository;
import com.pavila.blog.service.IUserService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerOneUser(RegisterUserDTORequest userRequest) {
        validatePassword(userRequest);

        User user = new User();
        user.setName(userRequest.getName());
        user.setLastName(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);

    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void validatePassword(RegisterUserDTORequest userRequest) {
       if(!userRequest.getPassword().equals(userRequest.getRepeatedPassword())){
           throw new InvalidPasswordException("Password don't match!");
       }
    }
}
