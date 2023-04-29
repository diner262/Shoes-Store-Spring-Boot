package tdtu.edu.vn.shoes_store.model;

import java.util.List;

public class Checkout {
    private String payment;
    private double totalPrice;
    private List<DetailCheckout> detailCheckout;

    private String address;


    public Checkout(String payment, double totalPrice, List<DetailCheckout> detailCheckout, String address) {
        this.payment = payment;
        this.totalPrice = totalPrice;
        this.detailCheckout = detailCheckout;
        this.address = address;
    }

    public List<DetailCheckout> getDetailCheckout() {
        return detailCheckout;
    }

    public void setDetailCheckout(List<DetailCheckout> detailCheckout) {
        this.detailCheckout = detailCheckout;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public void setOrderDetail(List<DetailCheckout> detailCheckout) {
        this.detailCheckout = detailCheckout;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

