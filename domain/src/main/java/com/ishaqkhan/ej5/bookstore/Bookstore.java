/**
 * 
 */
package com.ishaqkhan.ej5.bookstore;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * @author ishaqkhan
 * 
 */
public class Bookstore {

	private URL url;
    private String name;
    private Books books;

    public Bookstore(URL url, String name, Books books) {
		this.url = url;
		this.name = name;
		this.books = books;
	}

	public static Bookstore create(String url, String name, Collection<String> books) {
        checkIfEmpty(name);
        URL validUrl = checkIfValid(url);
        return new Bookstore(validUrl, name, Books.of(books));
    }

    private static void checkIfEmpty(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name: it should not be empty");
        }
    }

    private static URL checkIfValid(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid url: " + e.getMessage());
        }
    }

    public String getUrl() {
        return url.toString();
    }

    public String getName() {
        return name;
    }

    public Set<String> getBooks() {
        return books.toSet();
    }

    public boolean hasBook(String book) {
        return books.contains(book);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bookstore bookmark = (Bookstore) o;
        return Objects.equals(url, bookmark.url)
                && Objects.equals(name, bookmark.name)
                && Objects.equals(books, bookmark.books);
    }

    @Override
    public int hashCode() {
        int result = (url != null ? url.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (books != null ? books.hashCode() : 0);
        return result;
    }
}
