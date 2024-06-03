package com.fms.dao;

import java.util.List;

import com.fms.bean.Transaction;

public interface TransactionDAO {
	
	Transaction findById(Integer id);

    Transaction save(Transaction transaction);

    void delete(Transaction transaction);
    
    List<Transaction> getTransactionByUserId(Integer userId);

}
