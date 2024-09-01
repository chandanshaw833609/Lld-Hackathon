package UserModule;

import BookSearch.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seller extends User{
    private final List<String> inventory;

    private final Map<String, Integer> bookSaleMap;

    public Seller() {
        super(Role.SELLER);
        bookSaleMap = new HashMap<>();
        inventory = new ArrayList<>();
    }

    public void recordSale(String bookId) {
        bookSaleMap.put(bookId, bookSaleMap.getOrDefault(bookId,0)+1);
    }

    public Map<String, Integer> getBookSaleMap() {
        return bookSaleMap;
    }

    // address
    // bank details
    public List<String> getInventory() {
        return inventory;
    }
    public void updateInventory(String bookId) {
        inventory.add(bookId);
    }

}
