package com.example.test.controller.blogController;

import com.example.test.model.Blog;
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
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categoryList")
    public Iterable<Category> categoryList() {
        return categoryService.findAll();
    }


    @PostMapping("/create")
    public ResponseEntity<Blog> addNewBlog(@RequestBody Blog blog) {
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.CREATED);
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Blog> findById(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findByID(id);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findByID(id);
        if (!blog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blogService.remove(id);
        return new ResponseEntity<>(blog.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/home")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/home");
//        modelAndView.addObject("blogList", blogService.findAll());
        return modelAndView;
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Blog>> list() {
        List<Blog> blogList = (List<Blog>) blogService.findAll();
        if (blogList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) {
//        Optional<Blog> blogOptional = blogService.findByID(id);
//        if (!blogOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        blog.setId(blogOptional.get().getId());
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.OK);
    }
}
