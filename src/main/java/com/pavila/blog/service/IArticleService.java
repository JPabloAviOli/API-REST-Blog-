
package com.pavila.blog.service;

import com.pavila.blog.dto.ArticleDTORequest;
import com.pavila.blog.dto.ArticleDTOResponse;

import java.util.List;
import java.util.Optional;


public interface IArticleService {

    Optional<ArticleDTOResponse> save(ArticleDTORequest in);

    List<ArticleDTOResponse> getAll();

    Optional<ArticleDTOResponse> findTagById(Long id);

    void delete(Long id);

    Optional<ArticleDTOResponse> update(Long id, ArticleDTORequest in);
    
}
