package com.portfolio.caf.Security.Entity;

import com.portfolio.caf.Security.Enums.RolNombre;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }


}
