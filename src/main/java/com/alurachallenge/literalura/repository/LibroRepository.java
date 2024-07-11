package com.alurachallenge.literalura.repository;

import com.alurachallenge.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreSerie);

    @Query("Select l from Libro l where l.idioma = :idioma")
    List<Libro> buscarPorIdioma(String idioma);
}
