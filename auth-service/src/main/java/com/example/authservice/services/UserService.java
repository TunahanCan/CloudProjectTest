package com.example.authservice.services;

import com.example.authservice.domain.Role;
import com.example.authservice.domain.payload.SignupRequest;
import com.example.authservice.domain.User;
import com.example.authservice.domain.Erole;
import com.example.authservice.repository.RoleRepository;
import com.example.authservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;


    public User createNewUser(SignupRequest signUpRequest) {
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        List<Role> userRoleListDb = roleRepository.findAll();
        if ((strRoles == null) || strRoles.isEmpty()) {
            Role userRole = userRoleListDb.stream().filter(itr -> itr.getRoleName().equals(Erole.ROLE_USER))
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("Error: User Role is not found."));
            roles.add(userRole);
        } else {
            for (String reqRole : strRoles) {
                Role userRole = userRoleListDb.stream().filter(itr -> itr.getRoleName().equals("ROLE_"+reqRole))
                        .findAny().orElse(null);
                if (userRole != null) roles.add(userRole);
            }
        }
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }


}