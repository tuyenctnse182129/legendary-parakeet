package application.aicomic.controllers;

import application.aicomic.dataAccess.CommentsDTO;
import application.aicomic.dataAccess.OrderDetailsDTO;
import application.aicomic.models.Comments;
import application.aicomic.models.OrderDetails;
import application.aicomic.services.OrderDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/orderDetail")
@RestController
public class OrderDetailsController {
    private OrderDetailsService orderDetailsService;

    @GetMapping("/getAll")
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsService.getAllOrderDetails();
    }

    @PostMapping("/post")
    public OrderDetails addOrderDetail(@RequestBody OrderDetails orderDetails) {
        return orderDetailsService.addOrderDetail(orderDetails);
    }

    @PutMapping("/update")
    public OrderDetails updateOrderDetail(@PathVariable String id, @RequestBody OrderDetailsDTO orderDetailsDTO) {
        return orderDetailsService.updateOrderDetail(id, orderDetailsDTO);
    }

    @GetMapping("/getById")
    public OrderDetails getOrderDetailById(@PathVariable String id) {
        return orderDetailsService.getOrderDetailById(id);
    }
//    @DeleteMapping("/{delete}")
//    public OrderDetails deleteComment(@PathVariable String id) {
//        return orderDetailsService.deleteComment(id);
//    }
}
