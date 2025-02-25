package wesp.company.feirafacilapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import wesp.company.feirafacilapi.domain.dtos.user.response.UserResponse;
import wesp.company.feirafacilapi.domain.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    Page<UserResponse> findAllUsersByOrderByNameAsc(Pageable pageable);
    boolean existsByEmail(String email);
}
