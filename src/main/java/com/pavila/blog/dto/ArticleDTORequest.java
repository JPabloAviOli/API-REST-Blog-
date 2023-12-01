package com.pavila.blog.dto;

import com.pavila.blog.model.Category;
import com.pavila.blog.model.Comment;
import com.pavila.blog.model.Tag;
import com.pavila.blog.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTORequest {
    @NotBlank
    @Size(min = 3, message = "Title is required")
    private String title;
    @NotBlank
    @Size(min = 5, max = 255, message = "Content is required")
    private String content;
    @NotNull
    @Valid
    private Category category;

    @Valid
    private List<Tag> tags;

    @Size(min = 60, message = "Comment must be {min} characters")
    private List<Comment> comments;
}
