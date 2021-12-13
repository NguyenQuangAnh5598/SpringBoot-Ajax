package com.example.test.service.CategoryService;

import com.example.test.model.Category;
import java.util.Optional;

public interface ICategoryService {
    Iterable<Category> findAll();

    Optional<Category> findByID(Long id);

    Category save(Category category);

    void remove(Long id);
}
