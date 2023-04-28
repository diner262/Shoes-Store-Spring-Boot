package tdtu.edu.vn.shoes_store.model;

public class Checkout {
    private Long user_id;
    private String payment;
    private double totalPrice;

    private Long[] product_id;

    private int[] quantity;

    private int[] size;

    private double[] price;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public Long[] getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long[] product_id) {
        this.product_id = product_id;
    }

    public int[] getQuantity() {
        return quantity;
    }

    public void setQuantity(int[] quantity) {
        this.quantity = quantity;
    }

    public int[] getSize() {
        return size;
    }

    public void setSize(int[] size) {
        this.size = size;
    }

    public double[] getPrice() {
        return price;
    }

    public void setPrice(double[] price) {
        this.price = price;
    }

    public Checkout(Long user_id, String payment, double totalPrice, Long[] product_id, int[] quantity, int[] size, double[] price) {
        this.user_id = user_id;
        this.payment = payment;
        this.totalPrice = totalPrice;
        this.product_id = product_id;
        this.quantity = quantity;
        this.size = size;
        this.price = price;
    }
}
