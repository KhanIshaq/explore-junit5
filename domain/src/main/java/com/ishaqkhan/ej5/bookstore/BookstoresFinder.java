/**
 * 
 */
package com.ishaqkhan.ej5.bookstore;

import java.util.Collection;
import java.util.stream.Collectors;

import com.ishaqkhan.ej5.api.FindBookstores;

/**
 * @author ishaqkhan
 * 
 */
public class BookstoresFinder implements FindBookstores {
	
	private Bookstores bookstores;
	
	public BookstoresFinder(Bookstores bookstores) {
		this.bookstores = bookstores;
	}

	public Collection<Bookstore> by(String books) {	
		return bookstores.getAll().stream()
						.filter(bookstore -> bookstore.hasBook(books))
						.collect(Collectors.toSet());
	}
}
