package com.ishaqkhan.ej5.rest;
import java.net.URL;
import java.util.Collection;

import com.ishaqkhan.ej5.bookstore.Bookstore;


/**
 * @author ishaqkhan
 * 
 */
public class BookstorePayload {

	public String url;
	public String name;
	public Collection<String> books;
	
	public BookstorePayload(String url, String name, Collection<String> books) {
		this.url = url;
		this.name = name;
		this.books = books;
	}
	
	public Bookstore toBookstore() {
		return Bookstore.create(url, name, books);
	}
}
