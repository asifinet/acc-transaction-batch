package com.maybank.app.processor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.batch.item.ItemProcessor;

import com.maybank.app.model.Transaction;

public class TransactionItemProcessor implements ItemProcessor<String, Transaction> {

	
	// This method processes each input line and converts it into a Transaction object
	
    @Override
    public Transaction process(String line) throws Exception {
    	
    	// Split the input line using the "|" delimiter
        String[] fields = line.split("\\|");
        
        // Create a new Transaction object
        Transaction transaction = new Transaction();
        
        transaction.setAccountNumber(fields[0]);
        transaction.setTrxAmount(new BigDecimal(fields[1]));
        transaction.setDescription(fields[2]);
        transaction.setTrxDate(LocalDate.parse(fields[3]));
        transaction.setTrxTime(LocalTime.parse(fields[4]));
        transaction.setCustomerId(fields[5]);
        
        // Return the processed Transaction object
        return transaction;
    }
}
