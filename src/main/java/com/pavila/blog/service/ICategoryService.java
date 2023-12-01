
package com.pavila.blog.service;

import com.pavila.blog.dto.CategoryDTORequest;
import com.pavila.blog.dto.CategoryDTOResponse;
import java.util.List;
import java.util.Optional;


public interface ICategoryService {

    Optional<CategoryDTOResponse> save(CategoryDTORequest in);

    List<CategoryDTOResponse> getAll();

    Optional<CategoryDTOResponse> findTagById(Long id);

    void delete(Long id);

    Optional<CategoryDTOResponse> update(Long id, CategoryDTORequest in);
}
