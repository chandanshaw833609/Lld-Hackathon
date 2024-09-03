package book_category;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookCategory {
    private final String bookCategoryId;

    private final List<String> books;
    private final String name;

    public BookCategory(String name) {
        bookCategoryId = UUID.randomUUID().toString();
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(String bookId) {
        books.add(bookId);
    }

    public List<String> getBooks() {
        return books;
    }

    public String getBookCategoryId() {
        return bookCategoryId;
    }

    public String getName() {
        return name;
    }
}
