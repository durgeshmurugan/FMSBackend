package com.fms.service;

import java.util.List;

import com.fms.bean.User;

public interface UserService {
	
    User findByUsername(String username);
    
    boolean addUser(User user);
 
    User findById(Integer id);
 
    void deleteUser(Integer id);
 
    List<User> getAllUsers();

	public boolean validateLogin(String username, String password);
	

}
