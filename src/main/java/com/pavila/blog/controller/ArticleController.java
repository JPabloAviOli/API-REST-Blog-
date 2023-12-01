
package com.pavila.blog.controller;

import com.pavila.blog.dto.ArticleDTORequest;
import com.pavila.blog.dto.ArticleDTOResponse;
import com.pavila.blog.service.IArticleService;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("create")
    public ResponseEntity<Optional<ArticleDTOResponse>> save(
            @RequestBody @Valid ArticleDTORequest articleDTORequest){
        Optional<ArticleDTOResponse> articleDTO = articleService.save(articleDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(articleDTO);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("all")
    public ResponseEntity<List<ArticleDTOResponse>> getAll(){
        List<ArticleDTOResponse> articleList = articleService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(articleList);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("{id}")
    public ResponseEntity<Optional<ArticleDTOResponse>> findOne(@PathVariable Long id){
        Optional<ArticleDTOResponse> articleDTO = articleService.findTagById(id);
        return ResponseEntity.status(HttpStatus.OK).body(articleDTO);
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id){
        articleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("update/{id}")
    public ResponseEntity<Optional<ArticleDTOResponse>> updateOne(
            @PathVariable Long id, @RequestBody @Valid ArticleDTORequest articleDTORequest){

        Optional<ArticleDTOResponse> article = articleService.update(id, articleDTORequest);
        return ResponseEntity.status(HttpStatus.OK).body(article);
    }
}
