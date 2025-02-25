package wesp.company.feirafacilapi.mapper;

import java.util.UUID;

import wesp.company.feirafacilapi.domain.dtos.order.request.OrderRequest;
import wesp.company.feirafacilapi.domain.dtos.order.response.OrderResponse;
import wesp.company.feirafacilapi.domain.entities.Order;
import wesp.company.feirafacilapi.domain.entities.Supplier;
import wesp.company.feirafacilapi.domain.entities.User;

public class OrderMapper {

    public static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getCustomer().getName(),
                order.getProducts(),
                order.getQuantity(),
                order.getSuppliers().getName(),
                order.getStock(),
                order.getStatus()
        );
    }

    public static Order toEntity(OrderRequest orderRequest, User customer, Supplier supplier) {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setCustomer(customer);
        order.setProducts(orderRequest.products());
        order.setQuantity(orderRequest.quantity());
        order.setSuppliers(supplier);
        order.setStock(orderRequest.stock());
        order.setStatus(orderRequest.status());
        return order;
    }
}
