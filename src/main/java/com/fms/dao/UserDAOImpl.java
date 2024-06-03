package com.fms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fms.bean.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private EntityManager em;

	@Override
	public User findByUsername(String username) {
		Query query = em.createQuery("from User u where u.username=:username", User.class);
		query.setParameter("username", username);
		return (User) query.getSingleResult();
	}
	
	@Override
	public User findUserByEmail(String email) {
		try {
			TypedQuery<User> query=em.createQuery("Select u from User u where u.email=:email", User.class);
			query.setParameter("email", email);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	
	
	@Override
	public boolean addUser(User user) {
		em.persist(user);
		return true;
	}

	@Override
	public User findById(Integer userId) {
		return em.find(User.class, userId);
	}

	@Override
	public void delete(User user) {
		em.remove(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		return em.createQuery("SELECT u FROM User u").getResultList();
	}

	@Override
	public boolean validateLogin(String username, String password) {
		Query qry2 = em.createQuery("from User u where u.username = ?1 and u.password = ?2");
		qry2.setParameter(1, username);
		qry2.setParameter(2, password);

		@SuppressWarnings("unchecked")
		List<User> users = qry2.getResultList();
		return !users.isEmpty();
	}

}
