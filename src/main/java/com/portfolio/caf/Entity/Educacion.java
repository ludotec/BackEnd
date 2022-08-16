package com.portfolio.caf.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class Educacion {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nombreE;
    private String descripcionE;

    public Educacion(String nombreE, String descripcionE) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
    }
}
