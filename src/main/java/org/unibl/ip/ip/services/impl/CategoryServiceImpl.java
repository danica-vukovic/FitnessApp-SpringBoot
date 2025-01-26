package org.unibl.ip.ip.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.ip.ip.models.dto.Category;
import org.unibl.ip.ip.models.entities.CategoryEntity;
import org.unibl.ip.ip.repositories.CategoryEntityRepository;
import org.unibl.ip.ip.services.CategoryService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryEntityRepository categoryRepository;
    private final ModelMapper modelMapper;
    public CategoryServiceImpl(ModelMapper modelMapper, CategoryEntityRepository categoryEntityRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryEntityRepository;
    }
    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, Category.class))
                .collect(Collectors.toList());
    }
}
