
package com.pavila.blog.controller;

import com.pavila.blog.dto.CategoryDTORequest;
import com.pavila.blog.dto.CategoryDTOResponse;
import com.pavila.blog.dto.TagDTORequest;
import com.pavila.blog.dto.TagDTOResponse;
import com.pavila.blog.model.Category;
import com.pavila.blog.service.ICategoryService;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
public class CategoryController {
    
    @Autowired
    private ICategoryService categoryService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("create")
    public ResponseEntity<Optional<CategoryDTOResponse>> save(
            @RequestBody @Valid CategoryDTORequest  categoryDTORequest){
        Optional<CategoryDTOResponse> categoryDTO = categoryService.save(categoryDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("all")
    public ResponseEntity<List<CategoryDTOResponse>> getAll(){
        List<CategoryDTOResponse> categoryList = categoryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(categoryList);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("{id}")
    public ResponseEntity<Optional<CategoryDTOResponse>> findOne(@PathVariable Long id){
        Optional<CategoryDTOResponse> categoryDTO = categoryService.findTagById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("update/{id}")
    public ResponseEntity<Optional<CategoryDTOResponse>> updateOne(
            @PathVariable Long id, @RequestBody @Valid CategoryDTORequest categoryDTORequest){

        Optional<CategoryDTOResponse> category = categoryService.update(id, categoryDTORequest);
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }
}
