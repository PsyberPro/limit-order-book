package za.co.rmb.repository.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rmb.common.model.Side;
import za.co.rmb.repository.domain.Order;
import za.co.rmb.repository.repo.OrderRepository;
import za.co.rmb.repository.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order add(final Order order) {
        logger.info("persisting new order: {}", order);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        logger.info("querying all orders");
        List<Order> result = new ArrayList<>();
        orderRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public List<Order> findOrdersBySide(final Side side) {
        logger.info("querying orders by side: " + side);
        return orderRepository.findAllBySide(side);
    }

    @Override
    public Order update(final Order orderDto) {
        logger.info("updating order with Id: {}", orderDto.getId());
        final Optional<Order> optOrder = orderRepository.findById(orderDto.getId());
        if (!optOrder.isPresent()) {
            throw new RuntimeException("The target entity with orderId: "+orderDto.getId()+" to be update is not found");
        }
        final Order order = optOrder.get();
        order.setQuantity(orderDto.getQuantity());
        return orderRepository.save(order);
    }

    @Override
    public void delete(final long orderId) {
        logger.info("deleting order with Id: {}", orderId);
        orderRepository.deleteById(orderId);
    }
}
