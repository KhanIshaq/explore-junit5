package com.ishaqkhan.ej5.junit4.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishaqkhan.ej5.repository.BookstoreRepository;
import com.ishaqkhan.ej5.rest.BookstorePayload;

import static java.util.Collections.singletonList;
import static java.util.Collections.emptyList;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookstoreControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookstoreRepository bookstoreRepository;
    
    private static final BookstorePayload bookstorePayload = new BookstorePayload(
    		"http://www.example.com",
    		"A test link",
    		singletonList("Java in Action")
    		);
    
    
    @Test
    public void should_respond_201_when_bookstore_is_created() throws JsonProcessingException, Exception {
    	mockMvc.perform(
    			post("/bookstores")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(bookstorePayload)))
    			.andExpect(status().isCreated());
    }
    
    @Test
    public void should_respond_400_when_the_request_is_invalid() throws JsonProcessingException, Exception {
    	
    	BookstorePayload bookstorePayload = new BookstorePayload(
    			"invalid://url.com",
    			"An invalid link",
    			emptyList()
    			);
    	
    	mockMvc.perform(
    			post("/bookstores")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(bookstorePayload)))
    			.andExpect(status().isBadRequest());
    }
    
    @Test
    public void should_respond_409_when_the_bookstore_already_exists() throws JsonProcessingException, Exception {
    	bookstoreRepository.save(bookstorePayload.toBookstore());
    	
    	mockMvc.perform(
    			post("/bookstores")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(bookstorePayload)))
    			.andExpect(status().isConflict());
    }
    
    @Test
    public void should_respond_200_when_a_search_is_successfully_done() throws Exception {
    	bookstoreRepository.save(bookstorePayload.toBookstore());
    	
    	mockMvc.perform(
    			get("/bookstores")
    		    	.param("book", "Java in Action"))
    			.andExpect(status().isOk());
    }
    
}
