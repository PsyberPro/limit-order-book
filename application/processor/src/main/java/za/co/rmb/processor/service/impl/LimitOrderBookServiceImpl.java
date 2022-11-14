package za.co.rmb.processor.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.rmb.common.dto.Order;
import za.co.rmb.common.model.Side;
import za.co.rmb.processor.client.OrderRepoClient;
import za.co.rmb.processor.service.LimitOrderBookService;

import java.util.*;

@Service
public class LimitOrderBookServiceImpl implements LimitOrderBookService {

    private static final Logger logger = LoggerFactory.getLogger(LimitOrderBookServiceImpl.class);

    private PriorityQueue<Order> buyOrders, sellOrders;
    private Map<Long, Order> orderMap;

    private OrderRepoClient orderRepoClient;

    @Autowired
    public LimitOrderBookServiceImpl(final OrderRepoClient orderRepoClient) {
        this.orderRepoClient = orderRepoClient;
        buyOrders = new PriorityQueue<>(Comparator.comparing(Order::getPrice)
                .thenComparing(Order::getTimestamp));

        sellOrders = new PriorityQueue<>(Comparator.comparing(Order::getPrice)
                .thenComparing(Order::getTimestamp));

        orderMap = new HashMap<>();
    }

    @Override
    public void deleteOrder(final Long orderId) {
        if (orderMap.containsKey(orderId)) {
            if (orderMap.get(orderId).getSide().equals(Side.SELL)) {
                sellOrders.remove(orderMap.get(orderId));
            } else {
                buyOrders.remove(orderMap.get(orderId));
            }
            orderMap.remove(orderId);

            //Also deleting on the db, asynchronously though to avoid waiting for the operation to complete.
            orderRepoClient.deleteOrder(orderId);
        }
    }

    @Override
    public void modifyOrder(final Long orderId, final Integer qty) {
        if (orderMap.containsKey(orderId)) {
            // Updating the order record on the database and returning the updated order.
            final Order order = orderRepoClient.updateOrder(orderId, qty);

            //For the priority of the updated order to change, we remove firstly the old order and then add the new one.
            if (orderMap.get(orderId).getSide().equals(Side.SELL)) {
                sellOrders.remove(orderMap.get(orderId));
                sellOrders.add(order);
            } else {
                buyOrders.remove(orderMap.get(orderId));
                buyOrders.add(order);
            }
        }
    }

    @Override
    public void addOrder(final Order newOrder) {
        // Adding order to h2 database table (ORDER_), this returns the persisted object with the generated orderId
        final Order order = orderRepoClient.addOrder(newOrder);

        //Adding the persisted order to the respective queue, and the tracking map (orderMap)
        if (order.getSide().equals(Side.SELL)) {
            addOrder(order, sellOrders);
            orderMap.put(order.getId(), order);
        } else {
            addOrder(order, buyOrders);
            orderMap.put(order.getId(), order);
        }
    }

    @Override
    public Queue findBySide(final Side side) {
       if (Side.SELL.equals(side)) {
           return sellOrders;
       }
       return buyOrders;
    }

    private void addOrder(final Order newOrder, final Queue<Order> ordersQueue) {
        ordersQueue.add(newOrder);
    }

    public PriorityQueue<Order> getBuyOrders() {
        return buyOrders;
    }

    public PriorityQueue<Order> getSellOrders() {
        return sellOrders;
    }

    public Map<Long, Order> getOrderMap() {
        return orderMap;
    }
}
