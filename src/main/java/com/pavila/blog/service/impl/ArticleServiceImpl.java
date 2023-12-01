
package com.pavila.blog.service.impl;

import com.pavila.blog.dto.*;
import com.pavila.blog.exception.ObjectNotFoundException;
import com.pavila.blog.mapper.ArticleDTORequestToArticle;
import com.pavila.blog.model.Article;
import com.pavila.blog.model.User;
import com.pavila.blog.repository.IArticleRepository;
import com.pavila.blog.service.IArticleService;
import com.pavila.blog.service.IUserService;
import com.pavila.blog.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements IArticleService {
    
    @Autowired
    private IArticleRepository arRepo;
    @Autowired
    private ArticleDTORequestToArticle articleDTOToArticle;
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private IUserService userService;


    @Override
    public Optional<ArticleDTOResponse> save(ArticleDTORequest in) {
        UserProfileResponse userProfile = authenticationService.findLoggedInUser();
        User user = userService.findByUsername(userProfile.getUsername()).get();
        Article article = articleDTOToArticle.map(in);
        article.setUser(user);
        Article articleSave = arRepo.save(article);
        return arRepo.findArticleById(articleSave.getId());
    }

    @Override
    public List<ArticleDTOResponse> getAll() {
        List<ArticleDTOResponse> articleList = arRepo.findBy();
        if(articleList.isEmpty()){
            throw new ObjectNotFoundException("Article List is empty");
        }

        return articleList;
    }

    @Override
    public Optional<ArticleDTOResponse> findTagById(Long id) {
        Optional<ArticleDTOResponse> articleDTO = arRepo.findArticleById(id);
        if(articleDTO.isPresent()){
            return articleDTO;
        }
        throw new ObjectNotFoundException("Article not found with id: " + id);
    }

    @Override
    public void delete(Long id) {
        arRepo.findById(id).orElseThrow( ()-> new ObjectNotFoundException("Article not found with id: " + id));
    }

    @Override
    public Optional<ArticleDTOResponse> update(Long id, ArticleDTORequest in) {
        Article articleSaveDb = arRepo.findById(id).orElseThrow(()-> new ObjectNotFoundException("Article not found with id: " + id));
        articleSaveDb.setId(id);
        articleSaveDb.setTitle(in.getTitle());
        articleSaveDb.setContent(in.getContent());
        arRepo.save(articleSaveDb);
        return arRepo.findArticleById(id);
    }
}
