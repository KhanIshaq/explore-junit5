package com.ishaqkhan.ej5.rest;
import com.ishaqkhan.ej5.api.CreateBookstore;
import com.ishaqkhan.ej5.api.FindBookstores;
import com.ishaqkhan.ej5.bookstore.AlreadyAvailableBookstoreException;
import com.ishaqkhan.ej5.bookstore.Bookstore;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ishaqkhan
 * 
 */

@RestController
@RequestMapping("/bookstores")
public class BookstoreController {
	
	private CreateBookstore createBookstore;
	private FindBookstores findBookstores;
	
	public BookstoreController(CreateBookstore createBookstore, FindBookstores findBookstores) {
		this.createBookstore = createBookstore;
		this.findBookstores = findBookstores;	
	}
	
	@PostMapping
	public ResponseEntity createBookstore(@RequestBody BookstorePayload bookstorePayload) {
		createBookstore.forResource(bookstorePayload.url, bookstorePayload.name, bookstorePayload.books);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Collection<Bookstore>> getBookstoresByBooks(@RequestParam("book") String book) {
        Collection<Bookstore> bookmarks = findBookstores.by(book);
        return ResponseEntity.ok(bookmarks);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handle(HttpServletResponse response, IllegalArgumentException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(AlreadyAvailableBookstoreException.class)
    public void handle(HttpServletResponse response, AlreadyAvailableBookstoreException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_CONFLICT, exception.getMessage());
    }

}
