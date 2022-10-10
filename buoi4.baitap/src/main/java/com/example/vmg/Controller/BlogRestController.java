package com.example.vmg.Controller;

import com.example.vmg.exception.NoDataFoundException;
import com.example.vmg.model.*;
import com.example.vmg.service.BlogService;
import com.example.vmg.service.CategoryService;
import com.example.vmg.service.CoverService;
import com.example.vmg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class BlogRestController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CoverService coverService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Value("${upload.path}")
    private String fileUpload;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/blogs")
    public ResponseEntity<Map<String, Object>> getBlogList(@RequestParam(value = "title", required = false) String title,
                                                  @RequestParam(value = "author", required = false) String author,
                                                  @RequestParam(value = "category", required = false) String category,
                                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Blog> blogPage;
        List<Blog> blog = new ArrayList<>();
        int i = 0;
        System.out.println("check" + author == null);
        if (title == null && author == null && category == null) {
            i = 1;
            blogPage = blogService.getAll(pageable);
        } else if (title != null && author == null && category == null) {
            i = 2;
            blogPage = blogService.getBlogByTitle(pageable, title);
        } else if (title == null && author != null && category == null){
            blogPage = blogService.getAll(pageable);
            i = 3;
            blogService.findBlogByAuthor(author).forEach(blog::add);
        } else {
            i = 4;
            blogPage = blogService.findBlogByCategory(pageable, category);
        }

        blogPage.getContent().forEach(blog::add);

        if (blog.isEmpty()) {
            if (i == 2)
                throw new NoDataFoundException("title");
            else if (i == 3)
                throw new NoDataFoundException("author");
            else {
                throw new NoDataFoundException("Category");
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("blogs", blog);
        response.put("currentPage", blogPage.getNumber());
        response.put("totalItems", blogPage.getTotalElements());
        response.put("totalPage", blogPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable Long id) {
        return new ResponseEntity<>(blogService.getBlog(id), HttpStatus.OK);
    }

    @PostMapping("/blogs")
    public ResponseEntity<Blog> createBlog(@ModelAttribute BlogForm blogForm,
                                           BindingResult result) {
        System.out.println("add blog running...");
        System.out.println(blogForm.getContent());
        Category category = categoryService.getCategory(blogForm.getCategory().getId());
        System.out.println(category.getName());
        blogForm.setCategory(category);
        System.out.println(blogForm.getTitle());
        if (result.hasErrors()) {
            for (Object object : result.getAllErrors()) {
                if(object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;

                    /**
                     * Use null as second parameter if you do not use i18n (internationalization)
                     */

                    String message = messageSource.getMessage(fieldError, null);
                    System.out.println(message);
                }
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Blog blog = new Blog.BlogBuilder(blogForm.getTitle())
                .content(blogForm.getContent())
                .build();
//        Category category1 = blog.getCategory();
        blog.setCategory(category);
        blogService.add(blog);
        addFile(blogForm.getFiles(), blog);

        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @PostMapping ("/update")
    public ResponseEntity<Blog> updateBlog(@RequestPart BlogForm blogForm,
                                           BindingResult result) {
        System.out.println("update");
        System.out.println(blogForm.getCategory());
        if (result.hasErrors()) {
            for (Object object : result.getAllErrors()) {
                if (object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    String message = messageSource.getMessage(fieldError, null);
                    System.out.println(message);
                }
            }
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Blog blog = blogService.getBlog(blogForm.getId());
        blog.setTitle(blogForm.getTitle());
        blog.setCategory(blogForm.getCategory());
        System.out.println("file: " + blogForm.getFiles().size());
        if (blogForm.getFiles().size() > 0) {
            coverService.remove(blog);
            addFile(blogForm.getFiles(), blog);
        }
        if (blogService.add(blog) != null) {
            return new ResponseEntity<>(blog, HttpStatus.OK);
        }
        return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<HttpStatus> deleteBlog(@PathVariable("id") long id) {
        try {
            System.out.println("id: " + id);
            blogService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> register(@ModelAttribute User user) {
        System.out.println("register running ...");
        if (userService.register(user) != null)
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    private void addFile(List<MultipartFile> files, Blog blog) {
        for (MultipartFile file : files) {
            try {
                var fileName = file.getOriginalFilename();
                var is = file.getInputStream();
                Files.copy(is, Paths.get(this.fileUpload + fileName), StandardCopyOption.REPLACE_EXISTING);
                Cover cover = new Cover(fileName, blog);
                coverService.save(cover);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
