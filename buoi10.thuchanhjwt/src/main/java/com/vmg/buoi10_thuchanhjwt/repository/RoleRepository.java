package com.vmg.buoi10_thuchanhjwt.repository;

import com.vmg.buoi10_thuchanhjwt.model.ERole;
import com.vmg.buoi10_thuchanhjwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
