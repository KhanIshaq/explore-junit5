/**
 * 
 */
package com.ishaqkhan.ej5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ishaqkhan.ej5.api.CreateBookstore;
import com.ishaqkhan.ej5.api.FindBookstores;
import com.ishaqkhan.ej5.bookstore.BookstoreCreator;
import com.ishaqkhan.ej5.bookstore.Bookstores;
import com.ishaqkhan.ej5.bookstore.BookstoresFinder;
import com.ishaqkhan.ej5.repository.BookstoreRepository;
import com.ishaqkhan.ej5.repository.JpaBookstoreRepository;

/**
 * @author ishaqkhan
 * 
 */
@Configuration
public class BeanConfiguration {

	@Bean
	public Bookstores bookstores(JpaBookstoreRepository jpaBookstoreRepository) {
		return new BookstoreRepository(jpaBookstoreRepository);
	}
	
	@Bean
	public CreateBookstore createBookstore(Bookstores bookstores) {
		return new BookstoreCreator(bookstores);
	}
	
	@Bean
	public FindBookstores findBookstores(Bookstores bookstores) {
		return new BookstoresFinder(bookstores);
	}
}
