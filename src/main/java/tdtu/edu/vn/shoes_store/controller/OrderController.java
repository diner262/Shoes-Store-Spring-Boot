package tdtu.edu.vn.shoes_store.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdtu.edu.vn.shoes_store.model.Order;
import tdtu.edu.vn.shoes_store.service.OrderService;
import tdtu.edu.vn.shoes_store.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<?> getAllOrders(){
        Map<String, Object> result = new HashMap<>();

        List<Order> orderList = orderService.getAllOrders();
        result.put("statusCode", HttpStatus.OK.value());
        result.put("message", "Create new user successfully!");
        result.put("content", orderList);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
}
