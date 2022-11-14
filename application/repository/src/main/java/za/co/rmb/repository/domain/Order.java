package za.co.rmb.repository.domain;

import za.co.rmb.common.model.Side;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDER_")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private Integer quantity;
    @Column(name = "TIME_STAMP")
    private Date timestamp;
    @Enumerated(EnumType.STRING)
    private Side side;

    public Order() {
    }

    public Order(Long id, Double price, Integer quantity, Date timestamp, Side side) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.side = side;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
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
