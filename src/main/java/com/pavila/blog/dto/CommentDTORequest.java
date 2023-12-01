package com.pavila.blog.dto;
import com.pavila.blog.model.Article;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTORequest implements Serializable {

    @NotBlank(message = " Content is required")
    @Size(min = 3, max = 255, message = " Minimum must be {min} and maximum {max} characters")
    private String content;
    @NotNull
    @Valid
    private Article article;

}
