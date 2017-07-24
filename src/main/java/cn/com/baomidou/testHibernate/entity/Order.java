package cn.com.baomidou.testHibernate.entity;

/**
 * Created by zzx on 2017/7/24.
 */
public class Order {
    private Long id;
    private String orderNumber;
    private Double price;
    private Customer customer;

    public Order() {
    }

    public Order(Long id) {
        this.id = id;
    }

    public Order(String orderNumber, Double price, Customer customer) {
        this.orderNumber = orderNumber;
        this.price = price;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", price=" + price +
                ", customer=" + customer +
                '}';
    }
}
