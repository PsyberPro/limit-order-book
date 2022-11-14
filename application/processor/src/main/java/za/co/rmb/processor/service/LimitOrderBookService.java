package za.co.rmb.processor.service;

import za.co.rmb.common.dto.Order;
import za.co.rmb.common.model.Side;

import java.util.Queue;

public interface LimitOrderBookService {
    void addOrder(final Order newOrder);
    Queue findBySide(final Side side);
    void modifyOrder(final Long orderId, final Integer qty);
    void deleteOrder(final Long orderId);
}
