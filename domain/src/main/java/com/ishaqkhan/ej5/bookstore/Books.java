/**
 * 
 */
package com.ishaqkhan.ej5.bookstore;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ishaqkhan
 * 
 */
public class Books {

	private Set<String> books;

	public Books(Collection<String> books) {
		this.books = new HashSet<String>(books);
	}
	
	public static Books of(Collection<String> books) {
		return new Books(new HashSet<String>(books));
	}
	
	Set<String> toSet(){
		return Collections.unmodifiableSet(books);
	}
	
	boolean contains(String book) {
		return books.contains(book);
	}
	
	@Override public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass())return false;
		return books.equals(((Books) o).books);
	}
	
	@Override public int hashCode() {
		return books.hashCode();
	}
}
