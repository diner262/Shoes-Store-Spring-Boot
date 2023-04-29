package tdtu.edu.vn.shoes_store.service;

import org.springframework.stereotype.Service;
import tdtu.edu.vn.shoes_store.model.Order;

@Service
public interface OrderService {

    void addOrder(Order order);
}
