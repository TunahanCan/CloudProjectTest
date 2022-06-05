package com.example.authservice.repository;



import com.example.authservice.domain.Erole;
import com.example.authservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRoleName(Erole roleName);
}