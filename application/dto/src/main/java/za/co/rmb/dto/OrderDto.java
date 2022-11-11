package za.co.rmb.dto;

public class OrderDto {

    private long id;
    private double price;
    private int quantity;
    private String orderdate;

    private SideDto side;

    public OrderDto() {
    }

    public OrderDto(long id, double price, int quantity, String orderdate, SideDto side) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.orderdate = orderdate;
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

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public SideDto getSide() {
        return side;
    }

    public void setSide(SideDto side) {
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
