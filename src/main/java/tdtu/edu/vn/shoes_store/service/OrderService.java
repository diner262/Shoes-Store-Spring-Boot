package tdtu.edu.vn.shoes_store.service;

import org.springframework.stereotype.Service;
import tdtu.edu.vn.shoes_store.dto.OrderDto;
import tdtu.edu.vn.shoes_store.model.Order;

import java.util.List;

@Service
public interface OrderService {

<<<<<<< HEAD
    void addOrder(Order order);

    List<Order> getAllOrders();

    List<Order> findOrderByEmail(String email);
=======
    List<OrderDto> getAllOrders();
>>>>>>> 6c82b16 (view All Orders admin)
}
