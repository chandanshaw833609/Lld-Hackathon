package buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderManager {

    private static OrderManager instance;
    private final Map<String, Order> orders;
    private OrderManager() {
        orders = new HashMap<>();
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public Order getOrderById(String orderId) {
        return orders.get(orderId);
    }

    public List<Order> getOrderByUser(String userId) {
        return orders.values()
                .stream()
                .filter(order -> order.getUserId().equals(userId))
                .toList();
    }

    public void saveOrder(Order order) {
        orders.put(order.getId(), order);
    }
}
