package com.example.vmg.Repository;

import com.example.vmg.model.ERole;
import com.example.vmg.model.Role;
import com.example.vmg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByUser(User user);

    Optional<Role> findByName(ERole name);
}
