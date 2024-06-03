package com.fms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.fms.bean.Transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class TransactionDAOImpl implements TransactionDAO{
	
	@Autowired
	private EntityManager em;

	@Override
	public Transaction findById(Integer id) {
		return em.find(Transaction.class, id);
	}

	@Override
	public Transaction save(Transaction transaction) {
		em.persist(transaction);
		return transaction;
	}

	@Override
	public void delete(Transaction transaction) {
		em.remove(transaction);
	}

	@Override
	public List<Transaction> getTransactionByUserId(Integer userId) {
		Query query = em.createQuery("Select t FROM Transaction t WHERE t.user.userId=:userId", Transaction.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

}
