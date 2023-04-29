package tdtu.edu.vn.shoes_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.edu.vn.shoes_store.model.Order;
import tdtu.edu.vn.shoes_store.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
