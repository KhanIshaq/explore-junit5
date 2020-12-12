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
public interface FindBookstores {
	Collection<Bookstore> by(String books);
}
