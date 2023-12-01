
package com.pavila.blog.repository;

import com.pavila.blog.dto.ArticleDTOResponse;
import com.pavila.blog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IArticleRepository extends JpaRepository<Article, Long>{
    List<ArticleDTOResponse> findBy();

    Optional<ArticleDTOResponse> findArticleById(Long id);
}
