package buyer;

import book.Book;

import java.util.UUID;

public class CartItem {
    private final String id;
    private final Book book;
    private final String userId;
    public CartItem(String userId, Book book) {
        this.id = UUID.randomUUID().toString();
        this.book = book;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public String getUserId() {
        return userId;
    }
}
