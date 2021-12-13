package com.example.test.service.blogService;

import com.example.test.model.Blog;
import com.example.test.repo.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepo blogRepo;

    @Override
    public Iterable<Blog> findAll() {
        return blogRepo.findAll();
    }


    @Override
    public Optional<Blog> findByID(Long id) {
        return blogRepo.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepo.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepo.deleteById(id);
    }
}
