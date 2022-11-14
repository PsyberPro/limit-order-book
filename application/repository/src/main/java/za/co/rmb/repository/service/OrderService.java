package za.co.rmb.repository.service;

import za.co.rmb.common.model.Side;
import za.co.rmb.repository.domain.Order;

import java.util.List;

public interface OrderService {
    Order add(final Order order);
    List<Order> findAll();
    List<Order> findOrdersBySide(final Side side);
    Order update(final Order orderDto);
    void delete(final long orderId);
}
