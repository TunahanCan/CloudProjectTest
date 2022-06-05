package com.example.authservice.controller;

import com.example.authservice.domain.Role;
import com.example.authservice.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {

    private RoleRepository roleRepository;

    @PostMapping(value = "/createRole")
    @PreAuthorize("hasRole('ADMIN')")
    public Role createNewRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    @GetMapping(value = "getAllRole")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

}