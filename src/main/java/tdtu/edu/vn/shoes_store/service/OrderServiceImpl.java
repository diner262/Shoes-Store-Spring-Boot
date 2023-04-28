package tdtu.edu.vn.shoes_store.service;

import org.springframework.security.core.Transient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.shoes_store.model.Order;
import tdtu.edu.vn.shoes_store.repository.OrderRepository;
import tdtu.edu.vn.shoes_store.repository.RoleRepository;
import tdtu.edu.vn.shoes_store.repository.UserRepository;

import java.util.Date;

@Service
@Transient
public class OrderServiceImpl implements OrderService{

    OrderRepository orderRepository;
    @Override
    public void addOrder(Order order) {
//        Order order = new Order();
//
////        order.setUser(order.getUser().getId());
//        order.setDate(new Date());
//        order.setStatus("new");
//        order.setPayment(order.getPayment());
//        order.setTotalPrice(order.getTotalPrice());
//
//        // Lưu đơn hàng mới vào bảng order
//        orderRepository.save(order);
    }
}
