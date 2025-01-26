package org.unibl.ip.ip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.ip.ip.models.dto.Attribute;
import org.unibl.ip.ip.models.dto.Category;
import org.unibl.ip.ip.services.AttributeService;
import org.unibl.ip.ip.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final AttributeService attributeService;

    public CategoryController(CategoryService categoryService, AttributeService attributeService){
        this.categoryService=categoryService;
        this.attributeService = attributeService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}/attributes")
    public ResponseEntity<List<Attribute>> getAllAttributesByCategoryId(@PathVariable Integer id) {
        List<Attribute> attributes = attributeService.getAllByCategoryId(id);
        return ResponseEntity.ok(attributes);
    }

}
