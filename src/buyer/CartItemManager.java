package buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemManager {
    private static CartItemManager instance;
    private final Map<String, CartItem> cartItems;

    private CartItemManager() {
        cartItems = new HashMap<>();
    }

    public static CartItemManager getInstance() {
        if (instance == null) {
            instance = new CartItemManager();
        }
        return instance;
    }

    public void saveCartItem(CartItem cartItem) {
        cartItems.put(cartItem.getId(), cartItem);
    }

    public CartItem getCartItemById(String cartItemId) {
        return cartItems.get(cartItemId);
    }

    public List<CartItem> getCartItemByUser(String userId) {
        return cartItems.values()
                .stream()
                .filter(cartItem -> cartItem.getUserId().equals(userId))
                .toList();
    }

    public void removeCartItems(List<CartItem> cartItemList) {
        cartItemList.forEach(cartItem -> cartItems.remove(cartItem.getId()));
    }
}
