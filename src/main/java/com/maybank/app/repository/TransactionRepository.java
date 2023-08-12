package com.maybank.app.repository;

import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.maybank.app.model.Transaction;


@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
	
	// Acquire a pessimistic read lock on the entity with the specified ID
	@Lock(LockModeType.PESSIMISTIC_READ)
	@QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
	public Optional<Transaction> findById(Long transactionId);
	
	 // Search for transactions by customer ID, account number, or description
    Page<Transaction> findByCustomerIdOrAccountNumberOrDescriptionContaining(String customerId, String accountNumber, String description, PageRequest pageable);
}


