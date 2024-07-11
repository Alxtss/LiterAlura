package com.alurachallenge.literalura.repository;

import com.alurachallenge.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long>{

    @Query("Select a from Autor a where :anio between a.anioNacimiento and a.anioFallecimiento")
    List<Autor> buscarPorAnio(int anio);
}
