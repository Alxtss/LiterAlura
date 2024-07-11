package com.alurachallenge.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;
    private String nombreAutor;
    private Integer anioNacimiento;
    private Integer anioFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libro> libros;

    public Autor(){
    }

    public Autor (DatosAutor autor){
        this.nombreAutor = autor.nombreAutor();
        this.anioNacimiento = autor.anioNacimiento();
        this.anioFallecimiento = autor.anioFallecimiento();
    }

    @Override
    public String toString() {
        return
                "idAutor=" + idAutor + '\'' +
                ", nombreAutor='" + nombreAutor + '\'' +
                ", anioNacimiento=" + anioNacimiento + '\'' +
                ", anioFallecimiento=" + anioFallecimiento + '\'';
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    /*public Libro getLibros() {
        return (Libro) libros;
    }

    public void setLibros(Libro libros) {
        this.libros = (List<Libro>) libros;
    }*/

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
