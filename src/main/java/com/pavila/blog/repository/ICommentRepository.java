
package com.pavila.blog.repository;

import com.pavila.blog.dto.CommentDTOResponse;
import com.pavila.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long>{
    List<CommentDTOResponse> findBy();
    Optional<CommentDTOResponse> findCommentById(Long id);
}
