package za.co.rmb.processor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import za.co.rmb.dto.OrderDto;
import za.co.rmb.processor.service.InstanceLocator;
import za.co.rmb.processor.service.OrderService;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private InstanceLocator instanceLocator;
    private RestTemplate restTemplate;

    @Value("${spring.application.repository.name}")
    private String repoUrl;
    @Value("${spring.application.repository.api.orders.add}")
    private String addOrdersEndpoint;
    @Value("${spring.application.repository.api.orders.find.all}")
    private String findAllOrdersEndpoint;
    @Value("${spring.application.repository.api.orders.delete}")
    private String deleteOrdersEndpoint;
    @Value("${spring.application.repository.api.orders.update}")
    private String updateOrdersEndpoint;

    @Autowired
    public OrderServiceImpl(final InstanceLocator instanceLocator,
                            final RestTemplate restTemplate) {
        this.instanceLocator = instanceLocator;
        this.restTemplate = restTemplate;
    }

    @Override
    public OrderDto addOrder(final OrderDto orderDto) {
        final StringBuilder url = new StringBuilder(instanceLocator.getInstanceUrl(repoUrl, false))
                .append(addOrdersEndpoint);
        return restTemplate.postForObject(url.toString(), orderDto, OrderDto.class);
    }

    @Override
    public List<OrderDto> findAll() {
        final StringBuilder url = new StringBuilder(instanceLocator.getInstanceUrl(repoUrl, false))
                .append(findAllOrdersEndpoint);
        final OrderDto[] orderDtos = restTemplate.getForObject(url.toString(), OrderDto[].class);
        return Arrays.asList(orderDtos);
    }

    @Override
    public OrderDto updateOrder(final long orderId, final int qty) {
        final StringBuilder url = new StringBuilder(instanceLocator.getInstanceUrl(repoUrl, false))
                .append(updateOrdersEndpoint);
        final OrderDto orderDto = new OrderDto(orderId, qty);
        restTemplate.put(url.toString(), orderDto);
        return orderDto;
    }

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
