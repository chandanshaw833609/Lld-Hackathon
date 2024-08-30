package UserModule;

import BookSearch.Book;
import cart.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String id;
    private String name;
    private Role role;
    private String password;
    private String email;
    private Cart cart;
    private List<PurchaseHistory> history;

    public User(String name, Role role) {
        this.id =  UUID.randomUUID().toString();
        this.name = name;
        this.role = role;
        this.history = new ArrayList<>();
    }

    public User() {
        this.cart = new Cart();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PurchaseHistory> getHistory() {
        return history;
    }

    public void setHistory(List<PurchaseHistory> history) {
        this.history = history;
    }
}
