package com.maybank.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
	    
	    
	    @GetMapping("/concurrent-test")
	    public ResponseEntity<String> concurrentTest() {
	        int numThreads = 50; // Number of concurrent threads

	        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
	        List<Callable<String>> tasks = new ArrayList<>();

	        // Create concurrent tasks that update the same transaction concurrently
	        for (int i = 0; i < numThreads; i++) {
	            tasks.add(() -> {
	                UpdateModelRequest request = new UpdateModelRequest();
	                request.setId(22L); // Update the transaction with ID 1
	                request.setDescription("Concurrent Update " + Thread.currentThread().getId());

	                ResponseEntity<Transaction> response = updateTransaction(request);
	                return "Thread " + Thread.currentThread().getId() + " - " + response.getBody().getDescription();
	            });
	        }

	        try {
	            List<Future<String>> results = executorService.invokeAll(tasks);
	            executorService.shutdown();
                   
	             System.out.println("Print the result "+ results);
	             
	            StringBuilder responseText = new StringBuilder();
	            for (Future<String> result : results) {
	            	   System.out.println("Line Number95 - " + result.get());
	                responseText.append(result.get()).append("\n");
	            }
	            System.out.println("Line Number 97 - "+ results);
	            return ResponseEntity.ok(responseText.toString());
	        } catch (InterruptedException | ExecutionException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during concurrent test.");
	        }
	    }
	    
	    
	    
	}
