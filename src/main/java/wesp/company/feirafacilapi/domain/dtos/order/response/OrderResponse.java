package wesp.company.feirafacilapi.domain.dtos.order.response;

import java.util.List;

import wesp.company.feirafacilapi.domain.entities.Product;
import wesp.company.feirafacilapi.domain.enums.EnumMovimentStock;
import wesp.company.feirafacilapi.domain.enums.EnumOrderStatus;

public record OrderResponse (
        String customer,
        List<Product> products,
        int quantity,
        String suppliers,
        EnumMovimentStock stock,
        EnumOrderStatus status
){
}
