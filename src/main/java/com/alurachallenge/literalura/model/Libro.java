package com.alurachallenge.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer numeroDeDescargas;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro(){
    }

    public Libro (DatosLibro libro){
        this.titulo = libro.titulo();
        if(!libro.idiomas().isEmpty()){
            this.idioma = libro.idiomas().get(0);
        }
        if(!libro.autor().isEmpty()){
            for(DatosAutor autor : libro.autor()){
                this.autor = new Autor(autor);
                break;
            }
        }
        this.numeroDeDescargas = libro.numeroDeDescargas();
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idiomas='" + idioma + '\'' +
                ", numeroDeDescargas=" + numeroDeDescargas + '\'';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idiomas) {
        this.idioma = idiomas;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }


    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
