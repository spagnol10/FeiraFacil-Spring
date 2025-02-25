package wesp.company.feirafacilapi.domain.dtos.supplier.request;

import java.util.UUID;

public record SupplierRequest(UUID id, String code, String name, String email, String phone, String imagemUrl ) {
}
