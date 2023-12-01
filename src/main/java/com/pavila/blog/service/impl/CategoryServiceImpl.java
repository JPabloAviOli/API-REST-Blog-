
package com.pavila.blog.service.impl;

import com.pavila.blog.dto.CategoryDTORequest;
import com.pavila.blog.dto.CategoryDTOResponse;
import com.pavila.blog.exception.ObjectNotFoundException;
import com.pavila.blog.mapper.CategoryDTORequestToCategory;
import com.pavila.blog.model.Category;
import com.pavila.blog.repository.ICategoryRepository;
import java.util.List;
import java.util.Optional;

import com.pavila.blog.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {
    
    @Autowired
    private ICategoryRepository cateRepo;

    @Autowired
    private CategoryDTORequestToCategory categoryDTOToCategory;

    @Override
    public Optional<CategoryDTOResponse> save(CategoryDTORequest in) {
        Category categoryMap = categoryDTOToCategory.map(in);
        Category categorySaveDb = cateRepo.save(categoryMap);
        return cateRepo.findCategoryById(categorySaveDb.getId());
    }

    @Override
    public List<CategoryDTOResponse> getAll() {
        List<CategoryDTOResponse> categoryList = cateRepo.findBy();
        if(categoryList.isEmpty()){
            throw new ObjectNotFoundException("Category List is empty");
        }

        return categoryList;
    }

    @Override
    public Optional<CategoryDTOResponse> findTagById(Long id) {
        Optional<CategoryDTOResponse> category = cateRepo.findCategoryById(id);
        if(category.isPresent()){
            return category;
        }
        throw new ObjectNotFoundException("Category not found with id: " + id);
    }

    @Override
    public void delete(Long id) {
        cateRepo.findById(id).orElseThrow( ()-> new ObjectNotFoundException("Category not found with id: " + id));
    }

    @Override
    public Optional<CategoryDTOResponse> update(Long id, CategoryDTORequest in) {
        Category categorySaveDb = cateRepo.findById(id).orElseThrow(()-> new ObjectNotFoundException("Category not found with id: " + id));
        categorySaveDb.setId(id);
        categorySaveDb.setName(in.getName());
        cateRepo.save(categorySaveDb);
        return cateRepo.findCategoryById(id);
    }
}
