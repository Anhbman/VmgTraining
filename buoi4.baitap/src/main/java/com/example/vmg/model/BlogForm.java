package com.example.vmg.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
public class BlogForm {
    private Long id;

    @NotBlank(message = "title is mandatory")
    @Size(min = 3, max = 20)
    private String title;

    @NotBlank(message = "content is mandatory")
    private String content;

    private Set<Cover> covers;

    private Category category;

    private List<MultipartFile> files;

    public BlogForm() {
    }

    public BlogForm(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.covers = blog.getCovers();
//        this.files = blog.getFiles();
        this.category = blog.getCategory();
    }

    public BlogForm(BlogFormBuilder blogFormBuilder) {
        this.title = blogFormBuilder.title;
        this.content = blogFormBuilder.content;
        this.covers = blogFormBuilder.covers;
    }

    public static class BlogFormBuilder {
        private final String title;
        private String content;
        private Set<Cover> covers;
        private Category category;

        public BlogFormBuilder(String title) {
            this.title = title;
        }

        public  BlogFormBuilder content(String content) {
            this.content = content;
            return this;
        }

        public  BlogFormBuilder cover(Set<Cover> cover) {
            this.covers = cover;
            return this;
        }

        public BlogFormBuilder category(Category category) {
            this.category = category;
            return this;
        }

        public BlogForm build() {
            return new BlogForm(this);
        }
    }
}
