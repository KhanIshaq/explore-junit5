package com.ishaqkhan.ej5.junit4.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ishaqkhan.ej5.bookstore.AlreadyAvailableBookstoreException;
import com.ishaqkhan.ej5.bookstore.Bookstore;
import com.ishaqkhan.ej5.repository.BookstoreEntity;
import com.ishaqkhan.ej5.repository.BookstoreRepository;
import com.ishaqkhan.ej5.repository.JpaBookstoreRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BookstoreRepositoryTest {

private static final String URL = "http://www.test.com";
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Autowired
	private JpaBookstoreRepository jpaBookstoreRepository;
	
	private BookstoreRepository bookstoreRepository;
	private Bookstore bookstore;
	
	@Before
	public void set_up() {
		bookstoreRepository = new BookstoreRepository(jpaBookstoreRepository);
	}
	
	@Test
	public void should_save_bookstore() {
		Bookstore saved = bookstoreRepository.save(bookstore);
		assertThat(saved).isEqualTo(bookstore);
	}
	
	@Test
	public void should_not_save_an_already_existent_bookstore() {
		bookstoreRepository.save(bookstore);
		
		exception.expect(AlreadyAvailableBookstoreException.class);
		bookstoreRepository.save(bookstore);
	}
	
	@Test
	public void should_retrieve_bookstore_by_url() {
		jpaBookstoreRepository.save(BookstoreEntity.from(bookstore));
		
		Optional<Bookstore> retrieved = bookstoreRepository.getBy(URL);
		assertThat(retrieved).hasValue(bookstore);
		
	}
	
	@Test
	public void should_retrieve_all_bookstores() {
		jpaBookstoreRepository.save(BookstoreEntity.from(bookstore));
		
		Collection<Bookstore> retrieved = bookstoreRepository.getAll();
		assertThat(retrieved).contains(bookstore);
	}
}
