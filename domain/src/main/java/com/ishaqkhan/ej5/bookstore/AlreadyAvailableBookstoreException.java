/**
 * 
 */
package com.ishaqkhan.ej5.bookstore;

/**
 * @author ishaqkhan
 * 
 */
public class AlreadyAvailableBookstoreException extends RuntimeException {

    private static final String MESSAGE = "%s is already available";

    public AlreadyAvailableBookstoreException(String url) {
        super(String.format(MESSAGE, url));
    }
}
