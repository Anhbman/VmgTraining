package com.example.vmg.Controller;

import com.example.vmg.model.Blog;
import com.example.vmg.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("blogs", blogService.getAll());
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("blog", new Blog());
        return "add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("blog") Blog blog,
                      BindingResult result,
                      Model model) {
        System.out.println("add blog");
        if (result.hasErrors()) {
            model.addAttribute("blog", blog);
            return "add";
        }
        blogService.add(blog);
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
