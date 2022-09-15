package com.example.vmg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Cover {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    @JsonIgnore
    private Blog blog;

    public Cover() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Cover(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Cover(String name, Blog blog) {
        this.name = name;
        this.blog = blog;
    }

}
