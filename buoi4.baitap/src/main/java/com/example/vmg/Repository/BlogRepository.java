package com.example.vmg.Repository;

import com.example.vmg.model.Blog;
import com.example.vmg.model.Category;
import com.example.vmg.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>{

    @Override
    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findAllByTitleContainingIgnoreCaseOrderByCreatedDate(Pageable pageable, String title);

    Page<Blog> findAllByCategory_NameOrderByCreatedDate(Pageable pageable, String category);

    List<Blog> findAllByUser_Fullname(String author);

    List<Blog> findAllByCategory_Name(String category);

    List<Blog> findByUser(User user);
}
//<expression> or DISTINCT expected, got '*'