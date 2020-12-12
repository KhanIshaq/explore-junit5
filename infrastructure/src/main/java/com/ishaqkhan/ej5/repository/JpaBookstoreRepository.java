/**
 * 
 */
package com.ishaqkhan.ej5.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ishaqkhan
 * 
 */
@Repository
public interface JpaBookstoreRepository extends CrudRepository<BookstoreEntity, String> {
	
	Optional<BookstoreEntity> findByUrl(String url);
}
