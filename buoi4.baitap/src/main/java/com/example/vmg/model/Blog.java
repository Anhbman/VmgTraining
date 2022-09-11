package com.example.vmg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "title is mandatory")
    private String title;

    @NotBlank(message = "cover is mandatory")
    private String cover;

    @NotBlank(message = "content is mandatory")
    private String content;

    public Blog(){}

    public Blog(long id, String title, String cover, String content) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.content = content;
    }

    public Blog(String title, String cover, String content) {
        this.title = title;
        this.cover = cover;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
