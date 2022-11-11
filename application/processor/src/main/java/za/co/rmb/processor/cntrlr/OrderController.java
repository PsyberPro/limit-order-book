package za.co.rmb.processor.cntrlr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rmb.dto.OrderDto;
import za.co.rmb.processor.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<OrderDto> addOrder(@RequestBody final OrderDto orderDto) {
        return ResponseEntity.ok(orderService.addOrder(orderDto));
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<OrderDto>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }


    @PutMapping("/update")
    public ResponseEntity<OrderDto> updateOrder(@Param("orderId") long orderId, @Param("qty") int qty) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, qty));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> deleteOrder(@Param("orderId") long orderId) {
        return ResponseEntity.ok(orderService.deleteOrder(orderId));
    }
}
