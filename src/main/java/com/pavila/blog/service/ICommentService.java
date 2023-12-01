
package com.pavila.blog.service;

import com.pavila.blog.dto.CommentDTORequest;
import com.pavila.blog.dto.CommentDTOResponse;
import java.util.List;
import java.util.Optional;


public interface ICommentService {


    Optional<CommentDTOResponse> save(CommentDTORequest in);

    List<CommentDTOResponse> getAll();

    Optional<CommentDTOResponse> findTagById(Long id);

    void delete(Long id);

    Optional<CommentDTOResponse> update(Long id, CommentDTORequest in);

    
}
