package za.co.rmb.repository.cntrlr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rmb.repository.domain.Order;
import za.co.rmb.repository.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestBody final Order orderDto) {
        return ResponseEntity.ok(orderService.add(orderDto));
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody final Order orderDto) {
        return ResponseEntity.ok(orderService.update(orderDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Order> deleteOrder(@Param("orderId") long orderId) {
        orderService.delete(orderId);
        return ResponseEntity.ok().build();
    }
}
