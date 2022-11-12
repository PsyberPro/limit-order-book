package za.co.rmb.common.dto;

import za.co.rmb.common.model.Side;

public class OrderDto {

    private long id;
    private double price;
    private int quantity;
    private String timestamp;

    private Side side;

    public OrderDto() {
    }

    public OrderDto(long id, double price, int quantity, String timestamp, Side side) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.side = side;
    }

    public OrderDto(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
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
