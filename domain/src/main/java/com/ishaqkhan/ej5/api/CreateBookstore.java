/**
 * 
 */
package com.ishaqkhan.ej5.api;

import java.util.Collection;

import com.ishaqkhan.ej5.bookstore.Bookstore;

/**
 * @author ishaqkhan
 * 
 */
@FunctionalInterface
public interface CreateBookstore {
	Bookstore forResource(String url, String name, Collection<String> books);
}
