package za.co.rmb.repository.service;

import za.co.rmb.repository.domain.Order;

import java.util.List;

public interface OrderService {
    Order add(final Order order);
    List<Order> findAll();
    Order update(final Order orderDto);
    void delete(final long orderId);
}
