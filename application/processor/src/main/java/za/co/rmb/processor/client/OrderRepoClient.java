package za.co.rmb.processor.client;

import za.co.rmb.common.dto.Order;
import za.co.rmb.common.model.Side;

import java.util.List;

public interface OrderRepoClient {
    Order addOrder(final Order orderDto);
    List<Order> findAll();
    List<Order> findOrdersBySide(final Side side);
    Order updateOrder(final long orderId, final int qty);
    long deleteOrder(final long orderId);
}
