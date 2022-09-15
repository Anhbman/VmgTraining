package com.vmg.buoi6_thuchanh1.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class Student {

    @NotBlank(message = "LastName can not blank!!")
    @NotNull(message = "LastName can not be null!!")
    @NotEmpty(message = "LastName can not be empty!!!")
    private String name;

    @NotNull(message = "Chose the subject count you are going ti study!")
    @Min(value = 4, message = "Student should enroll to minium 4 subjects!!")
    @Max(value = 8, message = "Student can enroll to maximun 8 subjects!!")
    private int subjectCount;

    @NotNull
    @Min(1)
    @Max(12)
    private int grade;

    @NotNull
    @Size(max = 10, min = 0, message = "Mobile number should be of 10 digits")
    @Pattern(regexp = "[7-9][0-9]{9}", message = "Mobile number is invalid!!")
    private String mobileNo;

    @NotNull(message = "Please enter birth date")
    @Past(message = "Birth date should be less than current date!!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
}
