package wesp.company.feirafacilapi.domain.dtos.order.request;

import wesp.company.feirafacilapi.domain.entities.Product;
import wesp.company.feirafacilapi.domain.entities.Supplier;
import wesp.company.feirafacilapi.domain.entities.User;
import wesp.company.feirafacilapi.domain.enums.EnumMovimentStock;
import wesp.company.feirafacilapi.domain.enums.EnumOrderStatus;

import java.util.List;

public record OrderRequest(
        User customer,
        List<Product> products,
        int quantity,
        Supplier suppliers,
        EnumMovimentStock stock,
        EnumOrderStatus status){
}
