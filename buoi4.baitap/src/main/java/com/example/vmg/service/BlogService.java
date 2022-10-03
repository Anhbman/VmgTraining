package com.example.vmg.service;

import com.example.vmg.Repository.BlogRepository;
import com.example.vmg.exception.BlogNotFoundException;
import com.example.vmg.model.Blog;
import com.example.vmg.model.Category;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    public Page<Blog> getAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    public Page<Blog> getBlogByTitle(Pageable pageable, String title) {
        return blogRepository.findAllByTitleContainingIgnoreCaseOrderByCreatedDate(pageable, title);
    }

    public Blog add(Blog blog) {
        return blogRepository.save(blog);
    }

    public Boolean delete(long id) {
        try {
            blogRepository.deleteById(id);
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
            return false;
        }
        return true;
    }

    public Blog update(long id, Blog blog) {
        Blog blog1 = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Blog not exits " + id));
        if (blog1 != null) {
            blog1.setTitle(blog.getTitle());
            blog1.setCovers(blog.getCovers());
            blog1.setContent(blog.getContent());
            return blogRepository.save(blog1);
        }
        return null;
    }

    public Blog getBlog(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException(id));
    }

    public List<Blog> findBlogByAuthor(String author) {
        return blogRepository.findAllByUser_Fullname(author);
    }

    public Page<Blog> findBlogByCategory(Pageable pageable, String category) {
        return blogRepository.findAllByCategory_NameOrderByCreatedDate(pageable, category);
    }
}
