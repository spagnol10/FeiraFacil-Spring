package wesp.company.feirafacilapi.presentation.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wesp.company.feirafacilapi.domain.dtos.supplier.request.SupplierRequest;
import wesp.company.feirafacilapi.domain.dtos.supplier.response.SupplierResponse;
import wesp.company.feirafacilapi.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> createSupplier(@RequestBody @Valid SupplierRequest supplierRequest) {
        SupplierResponse response = supplierService.createSupplier(supplierRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAllSuppliers() {
        return  new ResponseEntity<>(supplierService.getAllSuppliers(), HttpStatus.OK);
    }
}
