package user;

import cart.Cart;

import java.util.ArrayList;
import java.util.List;

public class Buyer extends User{
    private final Cart cart = new Cart();
    private List<PurchaseHistory> history = new ArrayList<>();

    //phone no

    public Buyer() {
        super(Role.BUYER);
    }


    public Cart getCart() {
        return cart;
    }

    public List<PurchaseHistory> getHistory() {
        return history;
    }

    public void setHistory(List<PurchaseHistory> history) {
        this.history = history;
    }
}
