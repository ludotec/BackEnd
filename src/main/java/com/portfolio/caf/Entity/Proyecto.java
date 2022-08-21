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
public class Proyecto {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nombreP;
    private String descripcionP;

    private String lenguage;
    private  String linkP;



    public Proyecto(String nombreP, String lenguage, String descripcionP, String linkP) {
        this.nombreP = nombreP;
        this.descripcionP = descripcionP;
        this.lenguage = lenguage;
        this.linkP = linkP;
    }
}
