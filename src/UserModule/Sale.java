package UserModule;

import BookSearch.Book;

public class Sale {
    private final Book book;
    private int totalPurchase;
    private double totalProfit;

    public Sale(Book book) {
        this.book = book;
        this.totalProfit = book.getPrice();
        this.totalPurchase = 1;
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

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    @Override
    public String toString() {
        return "Sale {" +
                "book = " + book.getName() +
                ", totalPurchase = " + totalPurchase +
                ", totalProfit = Rs." + totalProfit +
                '}';
    }
}
