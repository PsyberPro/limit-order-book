package za.co.rmb.processor.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import za.co.rmb.common.dto.Order;
import za.co.rmb.common.model.Side;
import za.co.rmb.processor.client.OrderRepoClient;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class LimitOrderBookServiceImplTest {

    private LimitOrderBookServiceImpl limitOrderBookService;

    @Mock
    private OrderRepoClient orderRepoClient;

    @Before
    public void setup() {
        limitOrderBookService = new LimitOrderBookServiceImpl(orderRepoClient);
    }

    @Test
    public void testAddOrderOnBuySide() {
        final Order order = new Order(1L, 45.00, 100, new Date(), Side.BUY);

        Mockito.when(orderRepoClient.addOrder(order)).thenReturn(order);
        limitOrderBookService.addOrder(order);

        Assert.assertTrue(limitOrderBookService.getBuyOrders().contains(order));
        Assert.assertTrue(!limitOrderBookService.getSellOrders().contains(order));

        Assert.assertTrue(limitOrderBookService.getOrderMap().containsKey(order.getId()));
    }

    @Test
    public void testAddOrderOSellSide() {
        final Order order = new Order(1L, 45.00, 100, new Date(), Side.SELL);

        Mockito.when(orderRepoClient.addOrder(order)).thenReturn(order);
        limitOrderBookService.addOrder(order);

        Assert.assertTrue(!limitOrderBookService.getBuyOrders().contains(order));
        Assert.assertTrue(limitOrderBookService.getSellOrders().contains(order));

        Assert.assertTrue(limitOrderBookService.getOrderMap().containsKey(order.getId()));
    }
}
