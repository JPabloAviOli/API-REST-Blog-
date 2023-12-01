package com.pavila.blog.dto;

import com.pavila.blog.model.Category;
import com.pavila.blog.model.Comment;
import com.pavila.blog.model.Tag;
import com.pavila.blog.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleDTOResponse {
    Long getId();
    String getTitle();
    String getContent();
    LocalDateTime getArticleDate();

    CategoryDTOResponse getCategory();

    CommentDTOResponse getComments();

    TagDTOResponse getTags();
}
