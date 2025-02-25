package wesp.company.feirafacilapi.service;

import org.springframework.stereotype.Service;

import wesp.company.feirafacilapi.domain.dtos.order.request.OrderRequest;
import wesp.company.feirafacilapi.domain.dtos.order.response.OrderResponse;
import wesp.company.feirafacilapi.domain.entities.Order;
import wesp.company.feirafacilapi.domain.entities.Supplier;
import wesp.company.feirafacilapi.domain.entities.User;
import wesp.company.feirafacilapi.mapper.OrderMapper;
import wesp.company.feirafacilapi.repository.OrderRepository;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private UserService customerService;
    private SupplierService supplierService;

    public OrderService(OrderRepository orderRepository, UserService customerService, SupplierService supplierService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.supplierService = supplierService;
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        User customer = customerService.findUserByName(orderRequest.customer().getName());
        Supplier supplier = supplierService.getSupplierByName(orderRequest.suppliers().getName());

        Order order = OrderMapper.toEntity(orderRequest, customer, supplier);

        order = orderRepository.save(order);

        return OrderMapper.toResponse(order);
    }

//    public List<OrderResponse> getAllOrders() {
//        return orderRepository.findAllOrderResponse();
//    }
}
