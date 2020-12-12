package com.ishaqkhan.ej5.junit5.resolvers;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import com.ishaqkhan.ej5.rest.BookstorePayload;

public class BookstorePayloadResolver implements ParameterResolver{

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		
		return parameterContext.getParameter().getType() == BookstorePayload.class;
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		
		if(parameterContext.isAnnotated(Invalid.class)) {
			return new BookstorePayload(
					"invalid://url.com",
	    			"An invalid link",
	    			emptyList());
		}
		
		return new BookstorePayload(
	    		"http://www.example.com",
	    		"A test link",
	    		singletonList("Java in Action")
	    		);
	}

	
}
