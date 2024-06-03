package com.fms.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.bean.User;
import com.fms.dao.UserDAO;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	@Override
	public boolean addUser(User user) {
		return userDAO.addUser(user);
	}

	@Override
	public User findById(Integer id) {
		return userDAO.findById(id);
	}

	@Override
	public void deleteUser(Integer id) {
		User user = userDAO.findById(id);
		if (user != null) {
			userDAO.delete(user);
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAll();
	}

	@Override
	public boolean validateLogin(String username, String password) {
		return userDAO.validateLogin(username, password);
	}

	

}
