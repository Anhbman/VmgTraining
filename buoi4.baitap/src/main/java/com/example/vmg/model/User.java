package com.example.vmg.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "UserName cannot null")
    @Column(unique = true)
    @Size(min = 6, max = 20)
    private String username;

    @NotNull(message = "Password cannot null")
    private String password;

    @NotNull(message = "fullname cannot null")
    private String fullname;

    @ManyToMany
    private Set<Role> role;
}
