package com.fms.service;

import java.util.List;

import com.fms.bean.Transaction;

public interface TransactionService {
	
	Transaction findById(Integer id);
	 
    Transaction save(Transaction transaction, Integer userId, Integer productId);
 
    void deleteById(Integer id);
    
    List<Transaction> getTransactionByUser(Integer userId);
}


