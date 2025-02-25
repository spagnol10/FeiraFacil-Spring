package wesp.company.feirafacilapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import wesp.company.feirafacilapi.domain.entities.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
//    List<OrderResponse> findAllOrderResponse();
}
