package com.pavila.blog.dto;

import com.pavila.blog.model.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagDTORequest implements Serializable {

    @NotBlank(message = " Name is required")
    @Size(min = 3, max = 50, message = " Minimum must be {min} and maximum {max} characters")
    private String name;

    private List<Article> articles;
}
