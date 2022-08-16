package com.portfolio.caf.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Persona {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50, message = "no cumple con la longitud")
    private String nombre;

    @NotNull
    @Size(min = 1, max = 50, message = "no cumple con la longitud")
    private String apellido;

    @NotNull
    @Size(min = 1, max = 50, message = "no cumple con la longitud")
    private String img;

}
