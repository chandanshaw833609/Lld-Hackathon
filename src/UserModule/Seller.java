package UserModule;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User{
    private final List<Sale> sales = new ArrayList<>();

    public Seller() {
        super(Role.SELLER);
    }

    // address
    // bank details

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(Sale sale) {
        sales.add(sale);
    }

}
