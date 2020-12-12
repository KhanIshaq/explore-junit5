package com.ishaqkhan.ej5.junit5.resolvers;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import com.ishaqkhan.ej5.bookstore.Bookstore;
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

public class BookstoreResolver implements ParameterResolver{

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		
		return parameterContext.getParameter().getType() == Bookstore.class;
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		
		if (parameterContext.isAnnotated(Random.class)) {
            return randomBookmark();
        }
		return Bookstore.create("http://www.google.com", "Default bookmark", emptySet());
	}

	private Object randomBookmark() {
		List<Bookstore> bookstores = new ArrayList<>();
		bookstores.add(Bookstore.create("http://www.junit.org", "JUnit", singleton("test")));
		bookstores.add(Bookstore.create("http://www.github.com", "Github", emptySet()));
		bookstores.add(Bookstore.create("http://www.udemy.com", "Udemy", singleton("videos")));
		
		Collections.shuffle(bookstores);
		return bookstores.get(0);
	}

}
