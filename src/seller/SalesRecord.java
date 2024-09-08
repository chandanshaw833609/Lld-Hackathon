package seller;

import book.Book;

import java.util.UUID;

public class SalesRecord {
    private final String id = UUID.randomUUID().toString();
    private String sellerId;
    private Book book;

    private int timesSold;

    public SalesRecord (String sellerId, Book book) {
        this.sellerId = sellerId;
        this.book = book;
        this.timesSold = 1;
    }

    public String getId() {
        return id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getTimesSold() {
        return timesSold;
    }

    public void setTimesSold(int timesSold) {
        this.timesSold = timesSold;
    }
}
