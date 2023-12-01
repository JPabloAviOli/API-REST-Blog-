
package com.pavila.blog.controller;

import com.pavila.blog.dto.TagDTORequest;
import com.pavila.blog.dto.TagDTOResponse;
import com.pavila.blog.model.Tag;
import com.pavila.blog.service.ITagService;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.StyledEditorKit;

@RestController
@RequestMapping("tag")
public class TagController {
    
    @Autowired
    private ITagService tagService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("create")
    public ResponseEntity<Optional<TagDTOResponse>> save(@RequestBody @Valid TagDTORequest tagDTORequest){
       Optional<TagDTOResponse> tagDTOResponse = tagService.save(tagDTORequest);
       return ResponseEntity.status(HttpStatus.CREATED).body(tagDTOResponse);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("all")
    public ResponseEntity<List<TagDTOResponse>> getAll(){
        List<TagDTOResponse> tags = tagService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(tags);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("{id}")
    public ResponseEntity<Optional<TagDTOResponse>> findOne(@PathVariable Long id){
        Optional<TagDTOResponse> tagDTOResponse = tagService.findTagById(id);
        return ResponseEntity.status(HttpStatus.OK).body(tagDTOResponse);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteOne(@PathVariable Long id){
        tagService.delete(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Optional<TagDTOResponse>> updateOne(@PathVariable Long id, @RequestBody @Valid TagDTORequest tagDTORequest){
      Optional<TagDTOResponse> tagDTOResponse = tagService.update(id, tagDTORequest);
      return ResponseEntity.status(HttpStatus.OK).body(tagDTOResponse);
    }
    
}
