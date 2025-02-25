package wesp.company.feirafacilapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wesp.company.feirafacilapi.domain.entities.Supplier;

import java.util.Optional;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    Optional<Supplier> findByName(String name);
    Optional<Supplier> findByEmail(String email);
}
