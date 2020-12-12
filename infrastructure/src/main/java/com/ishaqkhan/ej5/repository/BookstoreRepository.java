/**
 * 
 */
package com.ishaqkhan.ej5.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.ishaqkhan.ej5.bookstore.AlreadyAvailableBookstoreException;
import com.ishaqkhan.ej5.bookstore.Bookstore;
import com.ishaqkhan.ej5.bookstore.Bookstores;

/**
 * @author ishaqkhan
 * 
 */
public class BookstoreRepository implements Bookstores{
	
	private JpaBookstoreRepository repository;
	
	public BookstoreRepository(JpaBookstoreRepository repository) {
		this.repository = repository;
	}

	
	@Override public Bookstore save(Bookstore bookstore) throws AlreadyAvailableBookstoreException {
		String url = bookstore.getUrl();
		repository.findByUrl(url).ifPresent(b -> {
			throw new AlreadyAvailableBookstoreException(url);
		});
		BookstoreEntity entity = BookstoreEntity.from(bookstore);
		return repository.save(entity).toValueObject();
	}

	@Override public Optional<Bookstore> getBy(String url) {
		return repository.findByUrl(url)
						  .map(BookstoreEntity::toValueObject);
	}

	@Override public Collection<Bookstore> getAll() {
		return StreamSupport.stream(repository.findAll().spliterator(), false)
							.map(BookstoreEntity::toValueObject)
							.collect(Collectors.toList());
	}

}
