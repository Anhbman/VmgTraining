package com.vmg.buoi4_thuchanh1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping(path = "/addUser")
    public String addUser(@Valid @ModelAttribute("user") User user,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "add-user";
        }

        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping(path = "/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping(path = "/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id,
                                 Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));

        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping(path = "/update/{id}")
    public String updateUser(@PathVariable("id") long id,
                             @Valid User user,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/index";
    }
}
