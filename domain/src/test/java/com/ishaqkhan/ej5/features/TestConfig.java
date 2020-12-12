package com.ishaqkhan.ej5.features;

import com.ishaqkhan.ej5.bookstore.BookstoreCreator;
import com.ishaqkhan.ej5.bookstore.BookstoresFinder;
import com.ishaqkhan.ej5.features.stepdefs.TestContext;
import com.ishaqkhan.ej5.stubs.InMemoryBookstores;

import cucumber.runtime.java.picocontainer.PicoFactory;

public class TestConfig extends PicoFactory{
	
	public TestConfig() {
		addClass(TestContext.class);
		addClass(BookstoreCreator.class);
		addClass(InMemoryBookstores.class);
		addClass(BookstoresFinder.class);
	}
}
