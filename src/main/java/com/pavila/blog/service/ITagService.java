
package com.pavila.blog.service;

import com.pavila.blog.dto.TagDTORequest;
import com.pavila.blog.dto.TagDTOResponse;
import java.util.List;
import java.util.Optional;

public interface ITagService {
    
    Optional<TagDTOResponse> save(TagDTORequest in);

    List<TagDTOResponse> getAll();

    Optional<TagDTOResponse> findTagById(Long id);

    void delete(Long id);

    Optional<TagDTOResponse> update(Long id, TagDTORequest in);


}
