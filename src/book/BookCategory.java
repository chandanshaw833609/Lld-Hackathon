package book;

import java.util.UUID;

public class BookCategory {
    private final String bookCategoryId;

    private final String name;

    public BookCategory(String name) {
        bookCategoryId = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getBookCategoryId() {
        return bookCategoryId;
    }

    public String getName() {
        return name;
    }
}
