package com.alurachallenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor (
    @JsonAlias("name") String nombreAutor,
    @JsonAlias("birth_year") Integer anioNacimiento,
    @JsonAlias("death_year") Integer anioFallecimiento){
}
