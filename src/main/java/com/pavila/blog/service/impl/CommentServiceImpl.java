
package com.pavila.blog.service.impl;

import com.pavila.blog.dto.CommentDTORequest;
import com.pavila.blog.dto.CommentDTOResponse;
import com.pavila.blog.dto.UserProfileResponse;
import com.pavila.blog.exception.ObjectNotFoundException;
import com.pavila.blog.mapper.CommentDTORequestToComment;
import com.pavila.blog.model.Comment;
import com.pavila.blog.model.User;
import com.pavila.blog.repository.ICommentRepository;
import com.pavila.blog.repository.IUserRepository;
import com.pavila.blog.service.ICommentService;
import java.util.List;
import java.util.Optional;

import com.pavila.blog.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements ICommentService {
    
    @Autowired
    private ICommentRepository comRepo;

    @Autowired
    private CommentDTORequestToComment commentDTOToComment;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<CommentDTOResponse> save(CommentDTORequest in) {
        UserProfileResponse userProfile = authenticationService.findLoggedInUser();
        User user = userRepository.findByUsername(userProfile.getUsername()).get();
        Comment commentMap = commentDTOToComment.map(in);
        commentMap.setUser(user);
        Comment commentSaveDb = comRepo.save(commentMap);
        return comRepo.findCommentById(commentSaveDb.getId());
    }

    @Override
    public List<CommentDTOResponse> getAll() {
        List<CommentDTOResponse> commentList = comRepo.findBy();
        if(commentList.isEmpty()){
            throw new ObjectNotFoundException("Comment List is empty");
        }

        return commentList;
    }

    @Override
    public Optional<CommentDTOResponse> findTagById(Long id) {
        Optional<CommentDTOResponse> comment = comRepo.findCommentById(id);
        if(comment.isPresent()){
            return comment;
        }
        throw new ObjectNotFoundException("Category not found with id: " + id);
    }

    @Override
    public void delete(Long id) {
        comRepo.findById(id).orElseThrow( ()-> new ObjectNotFoundException("Comment not found with id: " + id));
    }

    @Override
    public Optional<CommentDTOResponse> update(Long id, CommentDTORequest in) {
        Comment commentSaveDb = comRepo.findById(id).orElseThrow(()-> new ObjectNotFoundException("Comment not found with id: " + id));
        commentSaveDb.setId(id);
        commentSaveDb.setContent(in.getContent());
        comRepo.save(commentSaveDb);
        return comRepo.findCommentById(id);
    }
}
