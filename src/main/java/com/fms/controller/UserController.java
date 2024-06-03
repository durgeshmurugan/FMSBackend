package com.fms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fms.bean.User;
import com.fms.dao.UserDAO;
import com.fms.service.UserService;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDAO dao;

	@PostMapping("/addUsers")
	public String add(@RequestBody User user) {
		userService.addUser(user);
		return "Add User";
	}

	@GetMapping("/getUserId/{userId}")
	public User getUserById(@PathVariable Integer userId) {
		return userService.findById(userId);
	}

	@GetMapping("/getUsername/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}

	@GetMapping("/getAllUser")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@DeleteMapping("/deleteUser/{userId}")
	public void delete(@PathVariable Integer userId) {
		userService.deleteUser(userId);
	}

//	@GetMapping("/validatelogin/{username}/{password}")
//    public Map<String, Object> validateSession(HttpSession session, String username, String password) {
//        Map<String, Object> response = new HashMap<>();
//        String uname = (String) session.getAttribute("username");
//        Integer userId = (Integer) session.getAttribute("userId");
// 
//        if (uname != null && userId != null) {
//            response.put("username", uname);
//            response.put("userId", userId);
//            response.put("status", "success");
//        } else {
//            response.put("status", "error");
//            response.put("message", "No active session");
//        }
// 
//        return response;
//    }

	@PostMapping("/validateLogin")
	public ResponseEntity<?> validateLogin(@RequestBody User user) {

		User emailCheck = dao.findUserByEmail(user.getEmail());

		if (emailCheck == null) {
			return ResponseEntity.badRequest().body("Invalid user");
		}

		if (!Objects.equals(emailCheck.getPassword() , user.getPassword())) {
			return ResponseEntity.badRequest().body("Invalid Password");
		}

		Map<String, Integer> map = new HashMap<>();
		
		map.put("userId", emailCheck.getUserId());

		return ResponseEntity.ok(map);
	}

}
