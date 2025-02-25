package wesp.company.feirafacilapi.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wesp.company.feirafacilapi.domain.dtos.order.request.OrderRequest;
import wesp.company.feirafacilapi.domain.dtos.order.response.OrderResponse;
import wesp.company.feirafacilapi.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createProduct(@RequestBody OrderRequest orderRequest) {
        OrderResponse response = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @GetMapping
//    public ResponseEntity<List<OrderResponse>> getAllProducts() {
//        return ResponseEntity.ok(orderService.getAllOrders());
//    }

}