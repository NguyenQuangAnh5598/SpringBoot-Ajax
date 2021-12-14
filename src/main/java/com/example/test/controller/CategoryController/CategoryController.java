package com.example.test.controller.CategoryController;
import com.example.test.model.Category;
import com.example.test.service.CategoryService.ICategoryService;
import com.example.test.service.blogService.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/category")

public class CategoryController {
@Autowired
    private ICategoryService categoryService;

@Autowired
    private IBlogService blogService;

@GetMapping("/list")
    public ResponseEntity<Iterable<Category>> showAllCategory() {
    List<Category> categoryList = (List<Category>) categoryService.findAll();
    if (categoryList.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(categoryList,HttpStatus.OK);
}

@PostMapping("/create")
    public ResponseEntity<Category> CreateNewCate(@RequestBody Category category) {
    return new ResponseEntity<>(categoryService.save(category),HttpStatus.CREATED);
}

@PutMapping("/edit/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Long id) {
    Optional<Category> categoryOptional = categoryService.findByID(id);
    if (!categoryOptional.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
   category.setId(categoryOptional.get().getId());
    return new ResponseEntity<>(categoryService.save(category),HttpStatus.OK);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<Category> deleteCata(@PathVariable Long id) {
    Optional<Category> categoryOptional = categoryService.findByID(id);
    if (!categoryOptional.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    categoryService.remove(id);
    return new ResponseEntity<>(categoryOptional.get(),HttpStatus.OK);
}
}
