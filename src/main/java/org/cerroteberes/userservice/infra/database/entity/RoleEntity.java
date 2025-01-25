package org.cerroteberes.userservice.infra.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.cerroteberes.userservice.domain.entity.enums.NameRole;
import org.hibernate.proxy.HibernateProxy;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private NameRole nameRole;

    @Column(nullable = false)
    private String descriptionRole;

    @Column(nullable = false, updatable = false)
    private Instant createAt;

    @Column(nullable = false)
    private Instant updateAt;

    @PrePersist
    public void prePersist() {
        Instant now = Instant.now();
        this.createAt = now;
        this.updateAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updateAt = Instant.now();
    }


}
