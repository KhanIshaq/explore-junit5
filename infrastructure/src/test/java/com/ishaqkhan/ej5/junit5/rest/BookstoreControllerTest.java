package com.ishaqkhan.ej5.junit5.rest;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishaqkhan.ej5.junit5.resolvers.BookstorePayloadResolver;
import com.ishaqkhan.ej5.junit5.resolvers.Invalid;
import com.ishaqkhan.ej5.repository.BookstoreRepository;
import com.ishaqkhan.ej5.rest.BookstorePayload;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(BookstorePayloadResolver.class)
public class BookstoreControllerTest {

	@Autowired
    private MockMvc mockMvc;
    
    
    @Test
    @Order(1)
    public void should_respond_201_when_bookstore_is_created(
    		@Autowired ObjectMapper objectMapper, 
    		BookstorePayload bookstorePayload) throws JsonProcessingException, Exception {
    	
    	mockMvc.perform(
    			post("/bookstores")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(bookstorePayload)))
    			.andExpect(status().isCreated());
    }
    
    @Test
    public void should_respond_400_when_the_request_is_invalid(
    		@Autowired ObjectMapper objectMapper, 
    		@Invalid BookstorePayload bookstorePayload) throws JsonProcessingException, Exception {
    	
    	mockMvc.perform(
    			post("/bookstores")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(bookstorePayload)))
    			.andExpect(status().isBadRequest());
    }
    
    @Test
    public void should_respond_409_when_the_bookstore_already_exists(
    		@Autowired ObjectMapper objectMapper, 
    		BookstorePayload bookstorePayload) throws JsonProcessingException, Exception {
    	
    	mockMvc.perform(
    			post("/bookstores")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(bookstorePayload)))
    			.andExpect(status().isConflict());
    }
    
    @Test
    public void should_respond_200_when_a_search_is_successfully_done() throws Exception {
    	
    	mockMvc.perform(
    			get("/bookstores")
    		    	.param("book", "Java in Action"))
    			.andExpect(status().isOk());
    }
}
