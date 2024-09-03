package buyer;

import user.Role;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class Buyer extends User {
    private List<PurchaseHistory> history;

    private final List<CartItem> cartItems;

    //phone no

    public Buyer() {
        super(Role.BUYER);
        history = new ArrayList<>();
        cartItems = new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void updateCartItems(CartItem cartItem) {
        cartItems.add(cartItem);
    }



    public List<PurchaseHistory> getHistory() {
        return history;
    }

    public void setHistory(List<PurchaseHistory> history) {
        this.history = history;
    }
}
