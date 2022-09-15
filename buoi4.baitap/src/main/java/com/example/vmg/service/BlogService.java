package com.example.vmg.service;

import com.example.vmg.Repository.AuthorRepository;
import com.example.vmg.Repository.BlogRepository;
import com.example.vmg.exception.BlogNotFoundException;
import com.example.vmg.exception.NoDataFoundException;
import com.example.vmg.model.Blog;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Blog> getAll() {
//        List<Blog> blogs = (List<Blog>) blogRepository.findAll();
//        if (blogs.isEmpty()) {
//            throw new NoDataFoundException();
//        }
//        return blogs;
        return blogRepository.findAll();
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
        return blogRepository.findAllByAuthor_Name(author);
    }

    public List<Blog> findBlogByCategory(String category) {
        return blogRepository.findAllByCategory_Name(category);
    }

    public List<Blog> findBlogByTitle(String title) {
        return blogRepository.findAllByTitleContainingIgnoreCase(title);
    }
}
