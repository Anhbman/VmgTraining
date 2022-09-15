package com.example.vmg.Repository;

import com.example.vmg.model.Blog;
import com.example.vmg.model.Cover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverRepository extends CrudRepository<Cover, Long> {
    void deleteByBlog(Blog blog);

//    @Query("DELETE FROM Cover Where Cover.blog.id = :blog_id")
//    void removeCover(@Param("blog_id") long blog_id);

    void removeCoverByBlog_Id(Long blog_id);


}
