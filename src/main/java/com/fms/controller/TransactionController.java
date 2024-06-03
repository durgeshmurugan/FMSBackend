package com.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fms.bean.Transaction;
import com.fms.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin("http://localhost:3000/")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/transaction/{userId}/{productId}")
	public Transaction postMethodName(@RequestBody Transaction transaction, @PathVariable Integer userId, @PathVariable Integer productId) {
		return transactionService.save(transaction, userId, productId);
	}
	
	
	@GetMapping("/getTransaction/{id}")
    public Transaction getTransactionById(@PathVariable Integer id) {
        return transactionService.findById(id);
    }
	
	@GetMapping("/getTransaction/user/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionByUserId(@PathVariable Integer userId) {
        List<Transaction> transaction =  transactionService.getTransactionByUser(userId);
		return ResponseEntity.ok(transaction);
    }

	@DeleteMapping("/delete/{id}")
    public void deleteTransaction(@PathVariable Integer id) {
        transactionService.deleteById(id);
    }
	
	
}
