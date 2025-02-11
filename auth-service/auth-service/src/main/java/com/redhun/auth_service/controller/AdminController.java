package com.redhun.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/user")
public class AdminController {


    @GetMapping("/dashboard")
    public  String adminDashboard(){
        return  "Welcome to admin dashboard";
    }
}
