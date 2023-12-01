package com.pavila.blog.mapper;

import com.pavila.blog.dto.CommentDTORequest;
import com.pavila.blog.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDTORequestToComment implements IMapper<CommentDTORequest, Comment>{
    @Override
    public Comment map(CommentDTORequest in) {
        return Comment.builder()
                .content(in.getContent())
                .article(in.getArticle())
                .build();
    }
}
