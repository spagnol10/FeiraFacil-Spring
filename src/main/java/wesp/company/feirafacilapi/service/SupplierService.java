package wesp.company.feirafacilapi.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import wesp.company.feirafacilapi.domain.dtos.supplier.request.SupplierRequest;
import wesp.company.feirafacilapi.domain.dtos.supplier.response.SupplierResponse;
import wesp.company.feirafacilapi.domain.entities.Supplier;
import wesp.company.feirafacilapi.exception.ApiException;
import wesp.company.feirafacilapi.mapper.SuppliersMapper;
import wesp.company.feirafacilapi.repository.SupplierRepository;

@Service
public class SupplierService {

    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public SupplierResponse createSupplier(SupplierRequest supplierRequest) {
        Supplier newSupplier = SuppliersMapper.toEntity(supplierRequest);
        supplierRepository.save(newSupplier);
        return SuppliersMapper.toResponse(newSupplier);
    }

    public List<SupplierResponse> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(SuppliersMapper::toResponse)
                .toList();
    }

    public void deleteUser(String email) {
        Supplier supplier = supplierRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException(
                        HttpStatus.NOT_FOUND.value(),
                        "User not found",
                        "The user with Email " + email + " does not exist"
                ));
        supplierRepository.delete(supplier);
    }

    public SupplierResponse updateUser(String email, SupplierRequest dto) {
        Supplier supplier = supplierRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException(
                        HttpStatus.NOT_FOUND.value(),
                        "supplier not found",
                        "The supplier with Email " + email + " does not exist"
                ));

        if (dto.name() != null) supplier.setName(dto.name());
        if (dto.email() != null) supplier.setEmail(dto.email());
        if (dto.code() != null) supplier.setCode(dto.code());
        if (dto.phone() != null) supplier.setPhone(dto.phone());
        if (dto.imagemUrl() != null) supplier.setImageUrl(dto.imagemUrl());

        supplierRepository.save(supplier);

        return SuppliersMapper.toResponse(supplier);
    }

    public Supplier getUserByName(Supplier supplierDTO) {
        Supplier supplier = supplierRepository.findByName(supplierDTO.getName())
                .orElseThrow(() -> new ApiException(
                        HttpStatus.NOT_FOUND.value(),
                        "User not found",
                        "The user with Name " + " does not exist"
                ));

        return supplier;
    }

    public Supplier getSupplierByName(String name) {
        Supplier supplier = supplierRepository.findByName(name)
                .orElseThrow(() -> new ApiException(
                        HttpStatus.NOT_FOUND.value(),
                        "User not found",
                        "The user with Name " + " does not exist"
                ));

        return supplier;
    }
}
