package cart;

import BookSearch.Book;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private List<Book> books = new ArrayList<>();

    private double totalCartAmount;

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

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalCartAmount() {
        return totalCartAmount;
    }

    public void setTotalCartAmount(double totalCartAmount) {
        this.totalCartAmount = totalCartAmount;
    }
}
