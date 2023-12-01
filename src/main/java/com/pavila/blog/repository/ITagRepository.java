
package com.pavila.blog.repository;

import com.pavila.blog.dto.TagDTOResponse;
import com.pavila.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITagRepository extends JpaRepository<Tag, Long>{
    
    List<TagDTOResponse> findBy();
    Optional<TagDTOResponse> findTagById(Long id);
    
}
