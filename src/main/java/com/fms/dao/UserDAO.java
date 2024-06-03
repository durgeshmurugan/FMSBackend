package com.fms.dao;

import java.util.List;

import com.fms.bean.User;

public interface UserDAO {
	
	public User findByUsername(String username);

	public boolean addUser(User user);

	public User findById(Integer userId);

	public void delete(User user);
	
	public List<User> getAll();

	public boolean validateLogin(String username,String password);
	
	public User findUserByEmail(String email);

}
