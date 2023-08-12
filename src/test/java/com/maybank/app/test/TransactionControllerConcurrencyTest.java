package com.maybank.app.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maybank.app.dto.UpdateModelRequest;
import com.maybank.app.model.Transaction;
import com.maybank.app.repository.TransactionRepository;
import com.maybank.app.services.TransactionService;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerConcurrencyTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;
/*
    @Test
    @WithMockUser
    public void testConcurrentUpdateTransactions() throws Exception {
        int numThreads = 5;
        
        CountDownLatch latch = new CountDownLatch(numThreads);
        
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        
        UpdateModelRequest updatemodel = new UpdateModelRequest();
        updatemodel.setId(22L);
        updatemodel.setDescription("FUND TRANSFER CONCURRENT");
        
        // Mock the behavior of transactionService.updateTransaction
        Mockito.when(transactionService.updateTransaction(updatemodel))
               .thenAnswer(invocation -> {
                    // Simulate some delay to simulate database processing
                    Thread.sleep(100);
                    return new Transaction();
                });

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                try {
                    mockMvc.perform(put("/api/transactions/updatedescription")
                    		 .with(httpBasic("asif", "asif"))  // Add basic authentication
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(new UpdateModelRequest(22L, "New Description"))))
                            .andReturn();

                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        // Verify that transactionService.updateTransaction is called 5 times (or as per numThreads)
        verify(transactionService, times(numThreads)).updateTransaction(updatemodel);
    }

    // Utility method to convert objects to JSON strings
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    */
}
