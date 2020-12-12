/**
 * 
 */
package com.ishaqkhan.ej5.repository;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import com.ishaqkhan.ej5.bookstore.Bookstore;

/**
 * @author ishaqkhan
 * 
 */
@Entity
public class BookstoreEntity {
	
	@Id
	private String url;
	
	private String name;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> books;
	
	public BookstoreEntity() {
	}

	public BookstoreEntity(String url, String name, Set<String> books) {
		this.url = url;
		this.name = name;
		this.books = books;
	}

	public static BookstoreEntity from(Bookstore bookstore) {
		return new BookstoreEntity(
				bookstore.getUrl(),
				bookstore.getName(),
				bookstore.getBooks());
	}
	
	Bookstore toValueObject() {
		return Bookstore.create(url, name, books);
	}
	
}
