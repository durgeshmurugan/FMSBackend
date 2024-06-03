package com.fms.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fms.bean.Request;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class RequestDAOImpl implements RequestDAO{
	
	@Autowired
	private EntityManager em;

	@Override
	public Request save(Request request) {
		em.persist(request);
		return request;
	}

	@Override
	public boolean update(Request request) {
		em.merge(request);
		return true;
	}

	@Override
	public Request findById(Integer requestId) {
		return em.find(Request.class, requestId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Request> findAll() {
		return em.createQuery(" FROM Request r").getResultList();
	}

	@Override
	public Request findByUserId(Integer userId) {
		 TypedQuery<Request> query = em.createQuery(
	                "SELECT isApplied FROM Request a WHERE a.user.userId = :userId", Request.class);
	        query.setParameter("userId", userId);
	        try {
	            return query.getSingleResult();
	        } catch (Exception ex) {
	            return null;
	        }
	    }

}
