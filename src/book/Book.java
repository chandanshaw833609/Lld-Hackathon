package book;

import java.util.UUID;

public class Book {
    private final String bookId;
    private final String author;
    private final String title;
    private final BookCategory bookCategory;
    private double price;
    private final String sellerId;

    public String getSellerId() {
        return sellerId;
    }


    public Book (String title, String author, BookCategory bookCategory, double price, String sellerId) {
        this.bookId = UUID.randomUUID().toString();
        this.author = author;
        this.title = title;
        this.bookCategory = bookCategory;
        this.price=price;
        this.sellerId = sellerId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Book {" +
                "author='" + author + '\'' +
                ", name='" + title + '\'' +
                ", bookCategory='" +  bookCategory.getName() + '\''+
                ", price=" + price +
                '}';
    }
}
