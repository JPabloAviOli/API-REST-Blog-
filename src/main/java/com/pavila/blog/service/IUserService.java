
package com.pavila.blog.service;

import com.pavila.blog.dto.RegisterUserDTORequest;
import com.pavila.blog.model.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    User registerOneUser(RegisterUserDTORequest userRequest);

    Optional<User> findByUsername(String username);
}
