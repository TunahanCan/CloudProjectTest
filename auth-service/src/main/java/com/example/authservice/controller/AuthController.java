package com.example.authservice.controller;

import com.example.authservice.common.exception.ApiException;
import com.example.authservice.domain.payload.AuthRequest;
import com.example.authservice.domain.payload.AuthResponse;
import com.example.authservice.domain.payload.RefreshTokenRequest;
import com.example.authservice.domain.payload.SignupRequest;
import com.example.authservice.domain.User;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.security.JwtUserDetailService;
import com.example.authservice.services.RefreshTokenService;
import com.example.authservice.services.UserService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtUserDetailService userDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) throws Exception {
        log.debug("REST request to authenticate : {}", request.getEmail());
        AuthResponse response = userDetailService.createJwtToken(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody SignupRequest signupRequest) {
        log.debug("REST request to signup : {}", signupRequest.getEmail());
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new ApiException("Email address already in use." , HttpStatus.FORBIDDEN);
        }
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new ApiException("Email address already in use." , HttpStatus.FORBIDDEN);
        }
        User user = userService.createNewUser(signupRequest);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }


    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        Claims refreshTokenClaims = null;
        try {
           refreshTokenClaims =
                  refreshTokenService.verifyRefreshToken(request.getRefreshToken(), request.getUserName());
            AuthResponse response = refreshTokenService.tokenRefresh(refreshTokenClaims);
            return ResponseEntity.ok(response);
        } catch (ApiException apix) {
            throw apix;
        } catch (Exception ex) {
            throw new ApiException("Token verify exception -" + ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/allUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUser() {
        List<User> userList = userRepository.findAll();
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

}