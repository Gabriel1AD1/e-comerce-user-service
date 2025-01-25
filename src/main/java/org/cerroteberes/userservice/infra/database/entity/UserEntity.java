package org.cerroteberes.userservice.infra.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users") // Nombre de la tabla en la base de datos
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementable
    private Long id;

    @Column(nullable = false) // Campo obligatorio
    private String name;

    @Column(nullable = false)
    private String password;

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
