package BookSearch;

import jdk.jfr.Category;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Book {
    private final String bookId;
    private final String author;

    private final String name;

    private final BookCategory bookCategory;

    public Book (String name, String author, BookCategory bookCategory) {
        this.bookId = UUID.randomUUID().toString();
        this.author = author;
        this.name = name;
        this.bookCategory = bookCategory;
    }

    public String getBookId() {
        return bookId;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }


    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
