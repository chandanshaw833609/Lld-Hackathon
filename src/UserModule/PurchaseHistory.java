package UserModule;

import BookSearch.Book;

import java.util.ArrayList;
import java.util.List;

public class PurchaseHistory {
    List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "PurchaseHistory {" +
                "books=" + books +
                '}';
    }
}
