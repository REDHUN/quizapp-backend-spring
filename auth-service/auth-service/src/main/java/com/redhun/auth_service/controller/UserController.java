package com.redhun.auth_service.controller;

import com.redhun.auth_service.model.LoginResponse;
import com.redhun.auth_service.model.TokenValidationResponse;
import com.redhun.auth_service.model.User;
import com.redhun.auth_service.model.UserResponse;
import com.redhun.auth_service.service.JwtService;
import com.redhun.auth_service.service.MyUserDetailsService;
import com.redhun.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;
    @PostMapping("api/auth/register")
    public User register(@RequestBody User user){


        return  service.saveUser(user);

    }

    @PostMapping("api/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User user){

       return service.verify(user);
    }

    @GetMapping("api/user/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        User user = service.getUserById(userId);
System.out.println(user);
        UserResponse response = new UserResponse(
                user.getUsername(),
                user.getEmail(),
                user.getTotalScore(),
                user.getBadges(),
                user.getRoles()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("api/auth/validate")
    public ResponseEntity<TokenValidationResponse> validateToken(@RequestParam String token) {


        return service.validateToken(token);
    }



    @GetMapping("api/auth/getUserDetails")
    public ResponseEntity<User> getUserDetails(@RequestHeader("Authorization") String token) {
        // Remove "Bearer " prefix from token if it exists
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return service.getUserDetailsByToken(token);
    }

}
