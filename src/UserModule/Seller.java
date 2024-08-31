package UserModule;

import BookSearch.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Seller {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private String username;
    private String email;
    private String password;
    private List<Sale> sales = new ArrayList<>();
    // address
    // bank details

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        sales.addAll(this.sales);
        this.sales = sales;
    }
}
