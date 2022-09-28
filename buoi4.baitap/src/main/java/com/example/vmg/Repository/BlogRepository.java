package com.example.vmg.Repository;

import com.example.vmg.model.Blog;
import com.example.vmg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>{


    List<Blog> findAllByTitleContainingIgnoreCase(String title);

    List<Blog> findAllByUser_Fullname(String author);

    List<Blog> findAllByCategory_Name(String category);

    List<Blog> findByUser(User user);
}
//<expression> or DISTINCT expected, got '*'