/**
 * 
 */
package com.ishaqkhan.ej5.bookstore;

import java.util.Collection;
import java.util.Optional;


/**
 * @author ishaqkhan
 * 
 */
public interface Bookstores {
	
	Bookstore save(Bookstore bookstore) throws AlreadyAvailableBookstoreException;
	
	Optional<Bookstore> getBy(String url);
	
	Collection<Bookstore> getAll();
}
