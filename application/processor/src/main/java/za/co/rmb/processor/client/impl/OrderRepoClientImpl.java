package za.co.rmb.processor.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import za.co.rmb.common.dto.Order;
import za.co.rmb.common.model.Side;
import za.co.rmb.processor.service.InstanceLocator;
import za.co.rmb.processor.client.OrderRepoClient;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderRepoClientImpl implements OrderRepoClient {

    private InstanceLocator instanceLocator;
    private RestTemplate restTemplate;

    @Value("${spring.application.repository.name}")
    private String repoUrl;
    @Value("${spring.application.repository.api.orders.add}")
    private String addOrdersEndpoint;
    @Value("${spring.application.repository.api.orders.find.all}")
    private String findAllOrdersEndpoint;

    @Value("${spring.application.repository.api.orders.find.by-side}")
    private String findAllOrdersBySideEndpoint;
    @Value("${spring.application.repository.api.orders.delete}")
    private String deleteOrdersEndpoint;
    @Value("${spring.application.repository.api.orders.update}")
    private String updateOrdersEndpoint;

    @Autowired
    public OrderRepoClientImpl(final InstanceLocator instanceLocator,
                               final RestTemplate restTemplate) {
        this.instanceLocator = instanceLocator;
        this.restTemplate = restTemplate;
    }

    @Override
    public Order addOrder(final Order orderDto) {
        final StringBuilder url = new StringBuilder(instanceLocator.getInstanceUrl(repoUrl, false))
                .append(addOrdersEndpoint);
        return restTemplate.postForObject(url.toString(), orderDto, Order.class);
    }

    @Override
    public List<Order> findAll() {
        final StringBuilder url = new StringBuilder(instanceLocator.getInstanceUrl(repoUrl, false))
                .append(findAllOrdersEndpoint);
        final Order[] orderDtos = restTemplate.getForObject(url.toString(), Order[].class);
        return Arrays.asList(orderDtos);
    }

    @Override
    public List<Order> findOrdersBySide(final Side side) {
        final StringBuilder url = new StringBuilder(instanceLocator.getInstanceUrl(repoUrl, false))
                .append(findAllOrdersBySideEndpoint)
                .append("?side=")
                .append(side);
        final Order[] orderDtos = restTemplate.getForObject(url.toString(), Order[].class);
        return Arrays.asList(orderDtos);
    }


    @Override
    public Order updateOrder(final long orderId, final int qty) {
        final StringBuilder url = new StringBuilder(instanceLocator.getInstanceUrl(repoUrl, false))
                .append(updateOrdersEndpoint);
        final Order orderDto = new Order(orderId, qty);
        restTemplate.put(url.toString(), orderDto);
        return orderDto;
    }

    @Async
    @Override
    public long deleteOrder(final long orderId) {
        final StringBuilder url = new StringBuilder(instanceLocator.getInstanceUrl(repoUrl, false))
                .append(deleteOrdersEndpoint)
                .append("?orderId=")
                .append(orderId);
        restTemplate.delete(url.toString());
        return orderId;
    }
}
