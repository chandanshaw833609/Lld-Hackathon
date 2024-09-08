package buyer;

import book.Book;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private final String userId;
    private final List<Book> books;
    private final double amount;

    private final Date date = new Date();

    public Order(String userId, List<Book> orderItems, double amount) {
        id = UUID.randomUUID().toString();
        books = orderItems;
        this.userId = userId;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
