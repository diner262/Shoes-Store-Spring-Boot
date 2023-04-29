package tdtu.edu.vn.shoes_store.service;

import org.springframework.stereotype.Service;
import tdtu.edu.vn.shoes_store.model.Order;

import java.util.List;

@Service
public interface OrderService {

    void addOrder(Order order);

    List<Order> getAllOrders();

    List<Order> findOrderByEmail(String email);
}
