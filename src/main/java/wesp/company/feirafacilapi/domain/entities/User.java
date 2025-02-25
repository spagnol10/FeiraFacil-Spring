package wesp.company.feirafacilapi.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import wesp.company.feirafacilapi.domain.enums.EnumUserType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WS_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @ToString.Exclude
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumUserType role;

    private String imageUrl;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false, updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void update() {
        this.updatedAt = LocalDateTime.now();
    }

    public void activate() {
        this.enabled = true;
        this.update();
    }

    public void deactivate() {
        this.enabled = false;
        this.update();
    }
}