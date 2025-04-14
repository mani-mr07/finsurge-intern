package OrderService.src.main.java.com.microServices.OrderService.Entity;

public class Order {
    private int orderId;
    private int productId;
    private String status;

    public Order(int orderId, int productId, String status) {
        this.orderId = orderId;
        this.productId = productId;
        this.status = status;
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public int getProductId() { return productId; }
    public String getStatus() { return status; }
}
