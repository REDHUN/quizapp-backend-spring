package com.redhun.auth_service.service;


import com.redhun.auth_service.model.LoginResponse;
import com.redhun.auth_service.model.TokenValidationResponse;
import com.redhun.auth_service.model.User;
import com.redhun.auth_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo repo;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public User saveUser(User user) {

        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return repo.save(user);

    }


    public ResponseEntity<LoginResponse> verify(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            // Reload user to ensure the ID is populated
            User authenticatedUser = loadUserByUsername(user.getUsername());

            String token = jwtService.generateToken(authenticatedUser);
            LoginResponse response = new LoginResponse(token, authenticatedUser.getId().toString());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(null, null));
        }
    }

    private User loadUserByUsername(String username) {
        User user = repo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public ResponseEntity<TokenValidationResponse> validateToken(String token) {
        System.out.println(token);
        String username = jwtService.extractUserName(token);
        boolean isTokenExpired = jwtService.isTokenExpired(token);

        return ResponseEntity.ok(new TokenValidationResponse.Builder()
                .valid(!isTokenExpired)
                .username(username)
                .build());
    }


    public User getUserById(Long userId) {
System.out.println("user id is "+userId);
        return repo.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new UsernameNotFoundException("User with ID "));
    }


    public ResponseEntity<User> getUserDetailsByToken(String token) {
        // Extract the username from the token
        String username = jwtService.extractUserName(token);

        // Load the user by username
        User user = loadUserByUsername(username);

        // Return the user details as a ResponseEntity
        return ResponseEntity.ok(user);
    }
}
