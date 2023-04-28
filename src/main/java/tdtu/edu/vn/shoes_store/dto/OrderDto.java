package tdtu.edu.vn.shoes_store.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tdtu.edu.vn.shoes_store.model.OrderDetail;
import tdtu.edu.vn.shoes_store.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private Long id;

    private User user;

    private Date date;

    private String status;

    private String payment;

    private double totalPrice;

    private List<OrderDetail> orderDetail = new ArrayList<>();

    @Builder
    public OrderDto(Long id, User user, Date date, String status, String payment, double totalPrice, List<OrderDetail> orderDetail) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.status = status;
        this.payment = payment;
        this.totalPrice = totalPrice;
        this.orderDetail = orderDetail;
    }
}
