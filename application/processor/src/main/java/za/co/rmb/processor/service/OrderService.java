package za.co.rmb.processor.service;

import za.co.rmb.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto addOrder(final OrderDto orderDto);
    List<OrderDto> findAll();
    OrderDto updateOrder(final long orderId, final int qty);
    long deleteOrder(final long orderId);
}
