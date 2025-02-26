package wesp.company.feirafacilapi.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import wesp.company.feirafacilapi.domain.enums.EnumUserType;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WS_USER")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String email;

    private String phoneNumber;

    @ToString.Exclude
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumUserType role;

    private String imageUrl;

    private boolean enabled;

    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    public User(String email, String password, EnumUserType role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.enabled = true;
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == EnumUserType.SELLER) {
            return List.of(new SimpleGrantedAuthority("ROLE_SELLER"),
                            new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else {
            return List.of(new SimpleGrantedAuthority("ROLE_SELLER"));
        }
    }

    @Override
    public String getUsername() {
        return "";
    }
}