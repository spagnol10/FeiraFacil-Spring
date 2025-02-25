package wesp.company.feirafacilapi.mapper;

import wesp.company.feirafacilapi.domain.dtos.supplier.request.SupplierRequest;
import wesp.company.feirafacilapi.domain.dtos.supplier.response.SupplierResponse;
import wesp.company.feirafacilapi.domain.entities.Supplier;

public class SuppliersMapper {

    public static SupplierResponse toResponse(Supplier supplier) {
        return new SupplierResponse(
                supplier.getId(),
                supplier.getCode(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getPhone(),
                supplier.getImageUrl()
        );
    }


        public static Supplier toEntity(SupplierRequest dto){
            Supplier supplier = new Supplier();
            supplier.setCode(dto.code());
            supplier.setName(dto.name());
            supplier.setEmail(dto.email());
            supplier.setPhone(dto.phone());
            supplier.setImageUrl(dto.imagemUrl());
            return supplier;
        }

}
