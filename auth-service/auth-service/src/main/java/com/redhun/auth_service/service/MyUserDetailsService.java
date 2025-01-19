package com.redhun.auth_service.service;

import com.redhun.auth_service.model.TokenValidationResponse;
import com.redhun.auth_service.model.User;
import com.redhun.auth_service.model.UserPrincipal;
import com.redhun.auth_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("dddd");
      User user= repo.findByUsername(username);
      if(user==null){
          System.out.println("User 404");
          throw new UsernameNotFoundException("User 404");
      }
      System.out.println(user);
        return new UserPrincipal(user);
    }


}
