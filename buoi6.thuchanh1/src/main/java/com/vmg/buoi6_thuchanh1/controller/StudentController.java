package com.vmg.buoi6_thuchanh1.controller;

import com.vmg.buoi6_thuchanh1.dto.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class StudentController {

    @GetMapping("/")
    public String getForm(Model model) {
        model.addAttribute("student", new Student());
        return "index";
    }

    @PostMapping("save-student")
    public String submitStudentDetails(@Valid @ModelAttribute("student") Student student,
                                       BindingResult result,
                                       Model model) {
        System.out.println("name: " + student.getName().equals(null));
        System.out.println(result.hasErrors());
        if (result.hasErrors()) {
            return "index";
        } else {
            model.addAttribute("successMsg", "Details saved successfully");
            return "success";
        }
    }
}
