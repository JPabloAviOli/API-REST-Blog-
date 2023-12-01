package com.pavila.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserDTORequest {


    @NotBlank
    @Size(min = 4, max = 50)
    private String name;
    @NotBlank
    @Size(min = 4, max = 50)
    private String lastname;
    @NotBlank
    @Size(min = 4, max = 10)
    private String username;
    @NotBlank
    @Email
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters")
    @NotBlank
    private String password;
    @Size(min = 8, message = "Password must be at least 8 characters")
    @NotBlank
    private String repeatedPassword;
}
