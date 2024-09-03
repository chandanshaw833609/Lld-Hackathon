package buyer;

import java.util.ArrayList;
import java.util.List;

public record PurchaseHistory(List<String> books) {
    public PurchaseHistory(List<String> books) {
        this.books = new ArrayList<>(books);
    }

}
