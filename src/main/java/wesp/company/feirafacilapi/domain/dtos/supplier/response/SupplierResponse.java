package wesp.company.feirafacilapi.domain.dtos.supplier.response;

import java.util.UUID;

public record SupplierResponse(UUID id, String code, String name, String email, String phone, String imagemUrl) {
}
