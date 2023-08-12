package com.maybank.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maybank.app.dto.UpdateModelRequest;
import com.maybank.app.model.Transaction;
import com.maybank.app.repository.TransactionRepository;
import com.maybank.app.services.TransactionService;

	@RestController
	@RequestMapping("/api/transactions")
	public class TransactionController {	 
		    
		    
	    @Autowired
	    private TransactionService transactionService;
	    
	    @Autowired
	    TransactionRepository  transactionRepository;
	    
	    
	    @GetMapping
	    public ResponseEntity<Page<Transaction>> getTransactions(
	            @RequestParam(required = false) String customerId,
	            @RequestParam(required = false) String accountNumber,
	            @RequestParam(required = false) String description,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size) {
	    	
	    /*	System.out.println("THIS METHOD IS CALLED customerId - " + customerId 
	    			+ " accountNumber - " + accountNumber + " description - " + description 
	    			+ " page - "  + page  + " size - " + size);
	    	*/
	        PageRequest pageable = PageRequest.of(page, size);
	     
	        Page<Transaction> transactions = transactionRepository.findByCustomerIdOrAccountNumberOrDescriptionContaining(customerId, accountNumber, description, pageable);
	       // System.out.println(transactions);
	        return  new ResponseEntity<>(transactions, HttpStatus.OK);
	        //return ResponseEntity.ok(transactions);
	    }
	
	    @PutMapping("/updatedescription")
	    public ResponseEntity<Transaction> updateTransaction(@RequestBody UpdateModelRequest updatemodel) {
	
	    	Transaction updatedTransaction = transactionService.updateTransaction(updatemodel);
	        return ResponseEntity.ok(updatedTransaction);
	    }
	}
