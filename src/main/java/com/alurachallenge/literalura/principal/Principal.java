package com.alurachallenge.literalura.principal;

import com.alurachallenge.literalura.model.Autor;
import com.alurachallenge.literalura.model.DatosLibro;
import com.alurachallenge.literalura.model.Libro;
import com.alurachallenge.literalura.model.RespuestaAPI;
import com.alurachallenge.literalura.repository.AutorRepository;
import com.alurachallenge.literalura.repository.LibroRepository;
import com.alurachallenge.literalura.service.ConsumoAPI;
import com.alurachallenge.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "http://gutendex.com/books/?";
    private ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository){
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija una opcion del menu a travéz de su número:
                    
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    bucarLibrosPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private RespuestaAPI getDatosLibro(){
        System.out.println("Que libro deseas buscar?");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+"search="+nombreLibro.replace(" ", "%20"));
        System.out.println(json);
        var datosBusqueda = conversor.obtenerDatos(json, RespuestaAPI.class);
        return datosBusqueda;
    }

    private void bucarLibrosPorTitulo() {
        RespuestaAPI datos = getDatosLibro();
        if(!datos.resultados().isEmpty()){
            Libro libro = new Libro(datos.resultados().get(0));
            /*libro =*/ libroRepository.save(libro);
        }
        System.out.println(datos);
    }

    private void listarLibrosRegistrados(){
        List<Libro> libros = libroRepository.findAll();
        if(!libros.isEmpty()){
            for(Libro libro : libros){
                System.out.println("-------------------------------------------");
                System.out.println(" Titulo: " + libro.getTitulo());
                System.out.println(" Autor: " + libro.getAutor().getNombreAutor());
                System.out.println(" Idioma: " + libro.getIdioma());
                System.out.println(" Descargas: " + libro.getNumeroDeDescargas());
                System.out.println("-------------------------------------------");
            }
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
        }
    }

    private void listarAutoresRegistrados(){
        List<Autor> autores = autorRepository.findAll();
        if(!autores.isEmpty()){
            for(Autor autor : autores){
                System.out.println("-------------------------------------------");
                System.out.println(" Autor: " + autor.getNombreAutor());
                System.out.println(" Fecha de Nacimiento: " + autor.getAnioNacimiento());
                System.out.println(" Fecha de Fallecimiento: " + autor.getAnioFallecimiento());
                System.out.println("-------------------------------------------");
            }
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
        }
    }

    private void listarAutoresVivosPorAnio(){
        System.out.println("Escriba un año para realizar la busqueda de autores: ");
        var anio = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autores = autorRepository.buscarPorAnio(anio);

        if(!autores.isEmpty()){
            for(Autor autor : autores){
                System.out.println("-------------------------------------------");
                System.out.println(" Autor: " + autor.getNombreAutor());
                System.out.println(" Fecha de Nacimiento: " + autor.getAnioNacimiento());
                System.out.println(" Fecha de Fallecimiento: " + autor.getAnioFallecimiento());
                System.out.println("-------------------------------------------");
            }
        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
        }
    }

    private void listarLibrosPorIdioma(){
        System.out.println("Ingrese Idioma en el que quiere buscar: \n");
        System.out.println("|***********************************|");
        System.out.println("|  Opción - es : Libros en español. |");
        System.out.println("|  Opción - en : Libros en ingles.  |");
        System.out.println("|***********************************|\n");

        var idioma = teclado.nextLine();
        List<Libro> libros = libroRepository.buscarPorIdioma(idioma);

        if(!libros.isEmpty()){
            for(Libro libro : libros){
                System.out.println("-------------------------------------------");
                System.out.println(" Titulo: " + libro.getTitulo());
                System.out.println(" Autor: " + libro.getAutor().getNombreAutor());
                System.out.println(" Idioma: " + libro.getIdioma());
                System.out.println(" Descargas: " + libro.getNumeroDeDescargas());
                System.out.println("-------------------------------------------");
            }
        }
    }
}
