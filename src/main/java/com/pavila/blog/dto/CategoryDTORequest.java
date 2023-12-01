package com.pavila.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTORequest implements Serializable {
    @NotBlank(message = " Name is required")
    //@Size(min = 3, max = 50, message = " Minimum must be {min} and maximum {max} characters")
    private String name;
}
