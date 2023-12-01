
package com.pavila.blog.service.impl;

import com.pavila.blog.dto.TagDTORequest;
import com.pavila.blog.dto.TagDTOResponse;
import com.pavila.blog.exception.ObjectNotFoundException;
import com.pavila.blog.mapper.TagDTORequestToTag;
import com.pavila.blog.model.Tag;
import com.pavila.blog.repository.ITagRepository;

import com.pavila.blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements ITagService {
    
    @Autowired
    private ITagRepository tagRepo;

    @Autowired
    private TagDTORequestToTag tagDTORequestToTag;

    @Override
    public Optional<TagDTOResponse> save(TagDTORequest tagDTORequest) {
        Tag tagMap = tagDTORequestToTag.map(tagDTORequest);
        Tag tagSaveDb = tagRepo.save(tagMap);
        return tagRepo.findTagById(tagSaveDb.getId());
    }

    @Override
    public List<TagDTOResponse> getAll() {
        List<TagDTOResponse> tagDTOResponses = tagRepo.findBy();
        if(tagDTOResponses.isEmpty()){
            throw new ObjectNotFoundException("Tag List is empty");
        }

        return tagDTOResponses;
    }

    @Override
    public Optional<TagDTOResponse> findTagById(Long id) {
       Optional<TagDTOResponse> tagDTOResponse = tagRepo.findTagById(id);
       if(tagDTOResponse.isPresent()){
          return tagDTOResponse;
       }
       throw new ObjectNotFoundException("Tag not found with id: " + id);
    }

    @Override
    public void delete(Long id) {
        tagRepo.findById(id).orElseThrow( ()-> new ObjectNotFoundException("Tag not found with id: " + id));
    }

    @Override
    public Optional<TagDTOResponse> update(Long id, TagDTORequest in) {
        Tag tagSaveDb = tagRepo.findById(id).orElseThrow(()-> new ObjectNotFoundException("Tag not found with id: " + id));
        tagSaveDb.setId(id);
        tagSaveDb.setName(in.getName());
        tagRepo.save(tagSaveDb);
        return tagRepo.findTagById(id);
    }
}
