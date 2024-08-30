package cart;

import BookSearch.Book;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private List<Book> books = new ArrayList<>();

    public int getId()
    {
        return this.id;
    }
    public List<Book> getBooks()
    {
        return this.books;
    }

    public void setBooks(List<Book> books)
    {
        this.books.addAll(books);
    }
}
