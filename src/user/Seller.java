package user;

import BookSearch.Book;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User{
    private final List<Sale> salesHistory = new ArrayList<>();
    private final List<Book> inventory = new ArrayList<>();

    public Seller() {
        super(Role.SELLER);
    }


    // address
    // bank details

    public List<Sale> getSalesHistory() {
        return salesHistory;
    }

    public void updateSalesHistory(Sale sale) {
        salesHistory.add(sale);
    }

    public List<Book> getInventory() {
        return inventory;
    }
    public void updateInventory(Book book) {
        inventory.add(book);
    }
}
