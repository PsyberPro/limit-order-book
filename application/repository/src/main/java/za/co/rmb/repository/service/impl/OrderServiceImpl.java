package za.co.rmb.repository.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rmb.repository.domain.Order;
import za.co.rmb.repository.repo.OrderRepository;
import za.co.rmb.repository.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order add(final Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        List<Order> result = new ArrayList<>();
        orderRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Order update(final Order orderDto) {
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
        orderRepository.deleteById(orderId);
    }
}
