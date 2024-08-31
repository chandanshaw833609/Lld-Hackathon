package UserModule;

import BookSearch.Book;

import java.util.List;

public class Sale {
    private final Book book;
    private int totalPurchase;
    private double totalAmount;

    public Sale(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public int getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(int totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Sale {" +
                "book =" + book.getName() +
                ", totalPurchase=" + totalPurchase +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
