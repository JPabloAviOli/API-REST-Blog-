
package com.pavila.blog.repository;

import com.pavila.blog.dto.CategoryDTOResponse;
import com.pavila.blog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long>{

    List<CategoryDTOResponse> findBy();

    Optional<CategoryDTOResponse> findCategoryById(Long id);

}
