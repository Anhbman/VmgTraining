package com.example.vmg.Controller;

import com.example.vmg.exception.NoDataFoundException;
import com.example.vmg.model.Blog;
import com.example.vmg.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class BlogRestController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getBlogList(@RequestParam(value = "title", required = false) String title,
                                                  @RequestParam(value = "author", required = false) String author,
                                                  @RequestParam(value = "category", required = false) String category) {
        List<Blog> blog = new ArrayList<>();
        int i = 0;
        System.out.println("check" + author == null);
        if (title == null && author == null && category == null) {
            i = 1;
            blogService.getAll().forEach(blog::add);
        } else if (title != null && author == null && category == null) {
            i = 2;
            blogService.findBlogByTitle(title).forEach(blog::add);
        } else if (title == null && author != null && category == null){
            i = 3;
            blogService.findBlogByAuthor(author).forEach(blog::add);
        } else {
            i = 4;
            blogService.findBlogByCategory(category).forEach(blog::add);
        }

        if (blog.isEmpty()) {
            if (i == 2)
                throw new NoDataFoundException("title");
            else if (i == 3)
                throw new NoDataFoundException("author");
            else {
                throw new NoDataFoundException("Category");
            }
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable Long id) {
        return new ResponseEntity<>(blogService.getBlog(id), HttpStatus.OK);
    }

//    @PostMapping("/blogs")
//    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
//
//    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<HttpStatus> deleteBlog(@PathVariable("id") long id) {
        try {
            blogService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
