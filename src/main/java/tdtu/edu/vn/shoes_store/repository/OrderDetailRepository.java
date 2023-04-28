package tdtu.edu.vn.shoes_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.edu.vn.shoes_store.model.OrderDetail;
import tdtu.edu.vn.shoes_store.model.User;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
