package tdtu.edu.vn.shoes_store.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.shoes_store.model.*;
import tdtu.edu.vn.shoes_store.repository.OrderDetailRepository;
import tdtu.edu.vn.shoes_store.repository.OrderRepository;
import tdtu.edu.vn.shoes_store.security.jwt.JwtTokenUtil;
import tdtu.edu.vn.shoes_store.service.OrderService;
import tdtu.edu.vn.shoes_store.service.ProductService;
import tdtu.edu.vn.shoes_store.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllOrders(){
        Map<String, Object> result = new HashMap<>();

        List<Order> orderList = orderService.getAllOrders();
        result.put("statusCode", HttpStatus.OK.value());
        result.put("message", "Create new user successfully!");
        result.put("content", orderList);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }



}
