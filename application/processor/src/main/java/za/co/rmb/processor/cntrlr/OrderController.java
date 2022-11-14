package za.co.rmb.processor.cntrlr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.rmb.common.dto.Order;
import za.co.rmb.common.model.Side;
import za.co.rmb.processor.service.LimitOrderBookService;

import java.util.Date;
import java.util.Queue;

@RestController
@RequestMapping("/order")
public class OrderController {

    private LimitOrderBookService limitOrderBookService;

    @Autowired
    public OrderController(final LimitOrderBookService limitOrderBookService) {
        this.limitOrderBookService = limitOrderBookService;
    }

    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody final Order orderDto) {
        orderDto.setTimestamp(new Date());
        limitOrderBookService.addOrder(orderDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/by-side")
    public ResponseEntity<Queue> findOrdersBySide(@Param("side") Side side) {
        return ResponseEntity.ok(limitOrderBookService.findBySide(side));
    }

    @PutMapping("/modify")
    public ResponseEntity<Order> modifyOrder(@Param("orderId") long orderId, @Param("qty") int qty) {
        limitOrderBookService.modifyOrder(orderId, qty);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> deleteOrder(@Param("orderId") long orderId) {
        limitOrderBookService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
