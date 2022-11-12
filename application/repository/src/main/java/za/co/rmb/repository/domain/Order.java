package za.co.rmb.repository.domain;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double price;
    private int quantity;
    private String orderdate;

    @ManyToOne
    @JoinColumn(name = "SIDE_ID", insertable = false, updatable = false)
    private Side side;

    public Order() {
    }

    public Order(long id, double price, int quantity, String orderdate, Side side) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.orderdate = orderdate;
        this.side = side;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", orderdate='" + orderdate + '\'' +
                ", side=" + side +
                '}';
    }
}
