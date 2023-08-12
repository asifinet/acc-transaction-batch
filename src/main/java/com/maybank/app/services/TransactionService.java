package com.maybank.app.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maybank.app.dto.UpdateModelRequest;
import com.maybank.app.model.Transaction;
import com.maybank.app.repository.TransactionRepository;

@Service
public class TransactionService {
	

	@Autowired
	private TransactionRepository transactionRepository;

	@Transactional
	public synchronized Transaction updateTransaction(UpdateModelRequest updatemodel) {

		// Check if the transaction exists based on the provided ID
		Optional<Transaction> optionalTransaction = Optional.of(transactionRepository.findById(updatemodel.id)
				.orElseThrow(() -> new EntityNotFoundException("Transaction not found")));

		if (optionalTransaction.isPresent()) {
			
			// Get the existing transaction from the Optional
			Transaction transaction = optionalTransaction.get();
			
	
            
			// Update the description of the transaction
			transaction.setDescription(updatemodel.getDescription());
			
			  // Save the updated transaction using the repository
			 try {
		            // Save the updated transaction using the repository
		            return transactionRepository.save(transaction);
		        } catch (PessimisticLockingFailureException ex) {
		            // Handle optimistic locking failure
		            throw new ConcurrencyFailureException("Concurrent update detected for transaction with id: " + updatemodel.id);
		        }
		}
		  // If the transaction doesn't exist, throw an exception
		throw new EntityNotFoundException("Transaction not found with id: " + updatemodel.id);
	}
}