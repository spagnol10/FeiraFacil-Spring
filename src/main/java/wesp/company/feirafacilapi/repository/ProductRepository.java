package wesp.company.feirafacilapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wesp.company.feirafacilapi.domain.entities.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByCode(String code);
}
