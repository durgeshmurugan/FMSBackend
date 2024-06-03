package com.fms.service;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.bean.EMICards;
import com.fms.bean.Product;
import com.fms.bean.Transaction;
import com.fms.bean.User;
import com.fms.dao.EMICardsDAO;
import com.fms.dao.ProductDAO;
import com.fms.dao.TransactionDAO;
import com.fms.dao.UserDAO;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private TransactionDAO transactionDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private EMICardsDAO emiDAO;

	@Override
	public Transaction findById(Integer id) {
		return transactionDAO.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		Transaction transaction = transactionDAO.findById(id);
        if (transaction != null) {
            transactionDAO.delete(transaction);
        }
	}

	@Override
	public Transaction save(Transaction transaction, Integer userId, Integer productId) {
		
//		User user = userDAO.findById(userId);
//		transaction.setUser(user);
//		
//		Product product = productDAO.findById(productId);
//		transaction.setProduct(product);
//		
//		transaction.setDate(LocalDate.now());
//		
//		return transactionDAO.save(transaction);
		
		User user = userDAO.findById(userId);
        
 
        Product product = productDAO.findById(productId);
        
        EMICards emiCard = emiDAO.findByUserId(userId);
 
        if (emiCard.getBalance() < product.getPrice()) {
            return transaction;
        }
 
        emiCard.setBalance(emiCard.getBalance() - product.getPrice());
        emiDAO.save(emiCard);
 
        transaction.setUser(user);
        transaction.setProduct(product);
        transaction.setDate(LocalDate.now());
        transaction.setStatus("PAID");
 
        transactionDAO.save(transaction);
 
        return transaction;
	}

	@Override
	public List<Transaction> getTransactionByUser(Integer userId) {
		return transactionDAO.getTransactionByUserId(userId);
	}

}
