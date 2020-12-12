package com.ishaqkhan.ej5.features.stepdefs;

import java.util.Collection;
import java.util.Set;

import com.ishaqkhan.ej5.bookstore.Bookstore;

public class TestContext {
	String link;
	String name;
	boolean alreadyAvailableBookstore;
	Set<String> books;
	String searchedBook;
	Collection<Bookstore> searchResults;
}
