
package com.pavila.blog.controller;

import com.pavila.blog.dto.CategoryDTORequest;
import com.pavila.blog.dto.CategoryDTOResponse;
import com.pavila.blog.dto.CommentDTORequest;
import com.pavila.blog.dto.CommentDTOResponse;
import com.pavila.blog.model.Comment;
import com.pavila.blog.service.ICommentService;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommentController {
    
    @Autowired
    private ICommentService comSer;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("create")
    public ResponseEntity<Optional<CommentDTOResponse>> save(
            @RequestBody @Valid CommentDTORequest commentDTORequest){
        Optional<CommentDTOResponse> commentDTO = comSer.save(commentDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDTO);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("all")
    public ResponseEntity<List<CommentDTOResponse>> getAll(){
        List<CommentDTOResponse> commentList = comSer.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("{id}")
    public ResponseEntity<Optional<CommentDTOResponse>> findOne(@PathVariable Long id){
        Optional<CommentDTOResponse> commentDTO = comSer.findTagById(id);
        return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id){
        comSer.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("update/{id}")
    public ResponseEntity<Optional<CommentDTOResponse>> updateOne(
            @PathVariable Long id, @RequestBody @Valid CommentDTORequest commentDTORequest){

        Optional<CommentDTOResponse> comment = comSer.update(id, commentDTORequest);
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

}
