package com.example.vmg.Repository;

import com.example.vmg.model.Cover;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverRepository extends CrudRepository<Cover, Long> {
}
