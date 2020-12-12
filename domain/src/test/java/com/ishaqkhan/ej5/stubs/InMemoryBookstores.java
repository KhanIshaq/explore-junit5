package com.ishaqkhan.ej5.stubs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.ishaqkhan.ej5.bookstore.AlreadyAvailableBookstoreException;
import com.ishaqkhan.ej5.bookstore.Bookstore;
import com.ishaqkhan.ej5.bookstore.Bookstores;

public class InMemoryBookstores implements Bookstores{

	private final Map<String, Bookstore> bookstores = new HashMap<>();
	
	@Override
	public Bookstore save(Bookstore bookstore) {
        String url = bookstore.getUrl();
        if (bookstores.containsKey(url)) {
            throw new AlreadyAvailableBookstoreException(url);
        }
        bookstores.put(url, bookstore);
        return bookstore;


	}

	@Override
	public Optional<Bookstore> getBy(String url) {
		Bookstore bookstore = bookstores.get(url);
		return Optional.ofNullable(bookstore);
	}

	@Override
	public Collection<Bookstore> getAll() {
		return bookstores.values();
	}

}
