package com.pavila.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisteredUserDTOResponse {

    private Long id;
    private String name;
    private String username;
    private String role;
    private String jwt;
}
