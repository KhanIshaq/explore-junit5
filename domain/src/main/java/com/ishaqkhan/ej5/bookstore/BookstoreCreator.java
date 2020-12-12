/**
 * 
 */
package com.ishaqkhan.ej5.bookstore;

import java.util.Collection;

import com.ishaqkhan.ej5.api.CreateBookstore;

/**
 * @author ishaqkhan
 * 
 */
public class BookstoreCreator implements CreateBookstore {

    private Bookstores bookstores;

    public BookstoreCreator(Bookstores bookstores) {
        this.bookstores = bookstores;
    }

    @Override
	public Bookstore forResource(String url, String name, Collection<String> books) {
    	Bookstore bookstore = Bookstore.create(url, name, books);
        return bookstores.save(bookstore);
	}
}
