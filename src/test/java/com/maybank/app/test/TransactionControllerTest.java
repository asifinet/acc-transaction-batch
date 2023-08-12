package com.maybank.app.test;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maybank.app.dto.UpdateModelRequest;
import com.maybank.app.model.Transaction;
import com.maybank.app.repository.TransactionRepository;
import com.maybank.app.services.TransactionService;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SqlGroup({
        @Sql(value = "classpath:transactionData.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
})
@TestMethodOrder(OrderAnnotation.class)
public class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TransactionService transactionService;

	@MockBean
	private TransactionRepository transactionRepository;


	   
	@Test
	@WithMockUser(username = "asif", password = "asif" , roles = "ADMIN")
	@Order(1)
	public void shouldRespondToGetRequest() throws Exception {
              mockMvc.perform(get("/api/transactions")
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().is2xxSuccessful()).andDo(print());
	}

	@Test
	@WithMockUser(username = "asif", password = "asif" , roles = "ADMIN")
	@Order(2)
	public void shouldGetTransactionByCustomerId() throws Exception {
		 MvcResult result  =   mockMvc.perform(get("/api/transactions") 
				    .param("customerId", "222")
		            .param("page", "0")
		            .param("size", "10"))
		            .andExpect(
	                status().isOk())
		            .andExpect(jsonPath("$.content").isArray())
		            .andExpect(jsonPath("$.content", hasSize(0)))
		            .andDo(print()).andReturn();
		
		  String responseContent = result.getResponse().getContentAsString();
		    System.out.println("Response content: " + responseContent);

	}

	@Test
	@WithMockUser(username = "asif", password = "asif" , roles = "ADMIN")
	@Order(3)
	public void shouldGetTransactionByAccountNumber() throws Exception {
		   mockMvc.perform(get("/api/transactions")
				     .param("accountNumber", "8872838283")
		            .param("page", "0")
		            .param("size", "10"))
		            .andExpect(status().isOk())
		   .andExpect(jsonPath("$.content").isArray())
           .andExpect(jsonPath("$.content", hasSize(0))).andDo(print());
	}
	
	
	
	@Test
	@WithMockUser(username = "asif", password = "asif" , roles = "ADMIN")
	@Order(4)
	public void shouldGetTransactionByDescription() throws Exception {
		   mockMvc.perform(get("/api/transactions") // .with(httpBasic("asif", "asif"))  // Add basic authentication
				    .param("description", "FUND TRANSFER")
		            .param("page", "0")
		            .param("size", "10"))
		            .andExpect(status().isOk()).andDo(print());
	}
	
	
	
	@Test
	@WithMockUser(username = "asif", password = "asif" , roles = "ADMIN")
	@Order(5)
	public void testUpdateTransaction() throws Exception {
		// Mock the behavior of
	
	    UpdateModelRequest updateModelRequest = new UpdateModelRequest(22L, "FUND TRANSFER 42");
        Transaction updatedTransaction = new Transaction();
        // Mock the behavior of transactionService.updateTransaction
        when(transactionService.updateTransaction(updateModelRequest))
            .thenReturn(updatedTransaction);

        mockMvc.perform(put("/api/transactions/updatedescription")
          //  .with(httpBasic("asif", "asif"))  // Add basic authentication
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(updateModelRequest)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.description").value("FUND TRANSFER 42"));

        verify(transactionService, times(1)).updateTransaction(updateModelRequest);
    }
	
	
	// Utility method to convert objects to JSON strings
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
