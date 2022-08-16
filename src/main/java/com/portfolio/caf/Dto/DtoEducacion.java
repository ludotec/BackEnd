package com.portfolio.caf.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoEducacion {

    @NotBlank
    private String nombreE;
    @NotBlank
    private String descripcionE;
}
