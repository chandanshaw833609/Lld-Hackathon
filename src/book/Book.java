package book;

import book_category.BookCategory;

import java.util.UUID;

public class Book {
    private final String bookId;
    private final String author;
    private final String name;
    private final BookCategory bookCategory;

    private double price;

    private String sellerId;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Book (String name, String author, BookCategory bookCategory, double price, String sellerId) {
        this.bookId = UUID.randomUUID().toString();
        this.author = author;
        this.name = name;
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

    public String getName() {
        return name;
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
                ", name='" + name + '\'' +
                ", bookCategory='" +  bookCategory.getName() + '\''+
                ", price=" + price +
                '}';
    }
}
