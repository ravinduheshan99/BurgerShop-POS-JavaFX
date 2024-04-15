package Model;

public class customer {
    //Attributes
    private String customerId;
    private String customerName;
    private double total;

    //All args Constructor
    public customer(String customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }
    //Custome args Constructor
    public customer(String customerId, String customerName, double total) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.total = total;
    }
    //Default Constructor
    public customer() {

    }

    //Getters and Setters
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    //Override toString Method
    @Override
    public String toString() {
        return "customer{" + "customerId='" + customerId + '\'' + ", customerName='" + customerName + '\'' + ", total=" + total + '}';
    }
}
