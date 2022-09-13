package com.example.vmg.Controller;

import com.example.vmg.model.*;
import com.example.vmg.service.BlogService;
import com.example.vmg.service.CategoryService;
import com.example.vmg.service.CoverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CoverService coverService;

    @Value("${upload.path}")
    private String fileUpload;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("blogs", blogService.getAll());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "index";
    }

    @GetMapping("/{id}")
    public String getBlogByCategory(@PathVariable(value = "id") Long id, Model model) {
        System.out.println("id=" + id);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("blogs", categoryService.getCategory(id).getBlogs());
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("blog", new BlogForm());
        System.out.println(categoryService.getAllCategories());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("blog") BlogForm blogForm,
                      BindingResult result,
                      Model model) {
        System.out.println("add blog");
        System.out.println("file: " + blogForm.getFiles().toArray().toString());

        if (result.hasErrors()) {
            model.addAttribute("blog", blogForm);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "add";
        }
        Blog blog = new Blog.BlogBuilder(blogForm.getTitle())
                .content(blogForm.getContent())
                .build();
        Category category = categoryService.getCategory(blogForm.getCategory().getId());
        blog.setCategory(category);
        blogService.add(blog);

        for (MultipartFile file : blogForm.getFiles()) {
//            String fileName = file.getOriginalFilename();
            try {
                var fileName = file.getOriginalFilename();
                var is = file.getInputStream();

                Files.copy(is, Paths.get(this.fileUpload + fileName), StandardCopyOption.REPLACE_EXISTING);
                Cover cover = new Cover(fileName, blog);
//                FileCopyUtils.copy(file.getName().getBytes(), new File(this.fileUpload + fileName));
                System.out.println("file: " + fileName);
//                cover.setBlog(blog);
                coverService.save(cover);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/";
    }

    @DeleteMapping(path = "/{id}/delete")
    public @ResponseBody Boolean delete(@PathVariable("id") long id) {
        if (blogService.delete(id)) {
            return true;
        }
        return false;
    }

    @GetMapping(path = "/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("blog", blogService.getBlog(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "edit";
    }

    @PostMapping(path = "/edit")
    public String edit(@Valid @ModelAttribute("blog") Blog blog,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            model.addAttribute("blog", blog);
            return "edit";
        }
        blogService.update(blog.getId(), blog);
        return "redirect:/";
    }
}
