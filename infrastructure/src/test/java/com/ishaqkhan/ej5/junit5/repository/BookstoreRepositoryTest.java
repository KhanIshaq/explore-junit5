package com.ishaqkhan.ej5.junit5.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ishaqkhan.ej5.bookstore.AlreadyAvailableBookstoreException;
import com.ishaqkhan.ej5.bookstore.Bookstore;
import com.ishaqkhan.ej5.junit5.resolvers.InjectBookstore;
import com.ishaqkhan.ej5.junit5.resolvers.Random;
import com.ishaqkhan.ej5.repository.BookstoreEntity;
import com.ishaqkhan.ej5.repository.BookstoreRepository;
import com.ishaqkhan.ej5.repository.JpaBookstoreRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@InjectBookstore
/*
InjectBookmark can be set globally and removed from this test by adding in META-INF/services/ org.junit.jupiter.api.extension.Extension
and set junit.jupiter.extensions.autodetection.enabled to true in the surefire configuration
 */

public class BookstoreRepositoryTest {

	@Autowired
	private JpaBookstoreRepository jpaBookstoreRepository;
	
	private BookstoreRepository bookstoreRepository;
	
	@BeforeEach
	public void set_up() {
		bookstoreRepository = new BookstoreRepository(jpaBookstoreRepository);
	}
	
	@Test
    @DisplayName("Should save a bookmark")
	public void should_save_bookstore(Bookstore bookstore) {
		Bookstore saved = bookstoreRepository.save(bookstore);
		assertThat(saved).isEqualTo(bookstore);
	}
	
	
	@Nested
    @DisplayName("When a bookmark is saved")
    class WhenBookmarkIsSaved {
		
		private Bookstore savedBookstore;
		
		@BeforeEach
		void set_up(@Random Bookstore bookstore) {
			jpaBookstoreRepository.save(BookstoreEntity.from(bookstore));
			savedBookstore = bookstore;
		}
		
		@Test
		@DisplayName("it's not possible to save it again")
		void should_not_save_an_already_existent_bookstore() {
			
			assertThrows(
                    AlreadyAvailableBookstoreException.class,
                    () -> bookstoreRepository.save(savedBookstore),
                    savedBookstore.getUrl() + " is already available");
			bookstoreRepository.save(savedBookstore);
		}
		
		@Test
		@DisplayName("it should be accessible")
		void should_retrieve_bookstore_by_url() {
			
			String url = savedBookstore.getUrl();
			
			Optional<Bookstore> retrieved = bookstoreRepository.getBy(url);
			assertThat(retrieved).hasValue(savedBookstore);
			
		}
		
		@Test
		@DisplayName("it should be accessible among all bookmarks")
		void should_retrieve_all_bookstores() {
			
			Collection<Bookstore> retrieved = bookstoreRepository.getAll();
			assertThat(retrieved).contains(savedBookstore);
		}
	}
	
}
