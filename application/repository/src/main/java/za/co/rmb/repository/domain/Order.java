package za.co.rmb.repository.domain;

import za.co.rmb.common.model.Side;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double price;
    private int quantity;
    @Column(name = "TIME_STAMP")
    private String timestamp;
    @Enumerated(EnumType.STRING)
    private Side side;

    public Order() {
    }

    public Order(long id, double price, int quantity, String timestamp, Side side) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.timestamp = timestamp;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
                ", timestamp='" + timestamp + '\'' +
                ", side=" + side +
                '}';
    }
}
