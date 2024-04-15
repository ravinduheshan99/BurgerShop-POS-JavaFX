package Model;

public class orders {
    //Attributes
    private String orderId;
    private int orderStatus;
    private int orderQty;
    private double orderValue;

    //All args Constructor
    public orders(String orderId, int orderStatus, int orderQty, double orderValue) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderQty = orderQty;
        this.orderValue = orderValue;
    }
    //Default Constructor
    public orders() {

    }

    //Getters and Setters
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public int getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
    public int getOrderQty() {
        return orderQty;
    }
    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }
    public double getOrderValue() {
        return orderValue;
    }
    public void setOrderValue(double orderValue) {
        this.orderValue = orderValue;
    }

    //Override toString Method
    @Override
    public String toString() {
        return "orders{" + "orderId='" + orderId + '\'' + ", orderStatus=" + orderStatus + ", orderQty=" + orderQty + ", orderValue=" + orderValue + '}';
    }
}
