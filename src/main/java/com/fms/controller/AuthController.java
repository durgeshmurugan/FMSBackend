package com.fms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fms.bean.User;
import com.fms.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins =  "http://localhost:3000/", allowCredentials = "true")
public class AuthController {

	@Autowired
	private UserService service;
	
	@PostMapping("/login")
    public Map<String, Object> login(@RequestParam(value = "username", required = false) String username, 
    		@RequestParam(value = "password", required = false) String password, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
 
        // Fetch the user from the database
        User user = service.findByUsername(username);
 
        // Validate the user
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getUserId());
            response.put("username", user.getUsername());
            response.put("userId", user.getUserId());
            response.put("status", "success");
        } else {
            response.put("status", "error");
            response.put("message", "Invalid username or password");
        }
 
        return response;
    }
 
    @GetMapping("/validateSession")
    public Map<String, Object> validateSession(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        String username = (String) session.getAttribute("username");
        Integer userId = (Integer) session.getAttribute("userId");
 
        if (username != null && userId != null) {
            response.put("username", username);
            response.put("userId", userId);
            response.put("status", "success");
        } else {
            response.put("status", "error");
            response.put("message", "No active session");
        }
 
        return response;
    }
}

