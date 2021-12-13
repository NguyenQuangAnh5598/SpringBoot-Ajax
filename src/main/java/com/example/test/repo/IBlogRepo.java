package com.example.test.repo;

import com.example.test.model.Blog;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepo extends PagingAndSortingRepository<Blog,Long> {
}
