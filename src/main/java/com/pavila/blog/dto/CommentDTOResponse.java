package com.pavila.blog.dto;

import java.time.LocalDateTime;

public interface CommentDTOResponse {
    Long getId();
    String getContent();
    LocalDateTime getDateComment();
}
