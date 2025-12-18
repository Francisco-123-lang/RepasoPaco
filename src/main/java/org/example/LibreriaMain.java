package org.example;


import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class LibreriaMain {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        AutorReader autorReader = new AutorReader(scanner);
        LibroReader libroReader = new LibroReader(scanner, autorReader);
        BibliotecaReader bibliotecaReader = new BibliotecaReader(scanner, libroReader);
        Biblioteca biblioteca = bibliotecaReader.read();
        System.out.println(biblioteca);
    }
}

class Libro {
    private Long ISBN;
    private String titulo;
    private int año;
    private Autor[] autores;

    public Libro (Long ISBN, String titulo, int año, Autor[] autores) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.año = año;
        this.autores = autores;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public Autor[] getAutores() {
        return autores;
    }

    public void setAutores(Autor[] autores) {
        this.autores = autores;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return año == libro.año && Objects.equals(ISBN, libro.ISBN) && Objects.equals(titulo, libro.titulo) && Objects.deepEquals(autores, libro.autores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, titulo, año, Arrays.hashCode(autores));
    }

    @Override
    public String toString() {
        return "Libro{" +
                "ISBN=" + ISBN +
                ", titulo='" + titulo + '\'' +
                ", año=" + año +
                ", autores=" + Arrays.toString(autores) +
                '}';
    }
}

class Autor {
    private String NIF;
    private String nombre;
    private String apellidos;

    public Autor (String NIF, String nombre, String apellidos) {
        this.NIF = NIF;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(NIF, autor.NIF) && Objects.equals(nombre, autor.nombre) && Objects.equals(apellidos, autor.apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NIF, nombre, apellidos);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}

class Biblioteca {
    private String name;
    private Libro[] libros;

    public Biblioteca (String name, Libro[] libros) {
        this.name = name;
        this.libros = libros;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Libro[] getLibros() {
        return libros;
    }

    public void setLibros(Libro[] libros) {
        this.libros = libros;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Biblioteca that = (Biblioteca) o;
        return Objects.equals(name, that.name) && Objects.deepEquals(libros, that.libros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Arrays.hashCode(libros));
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "name='" + name + '\'' +
                ", libros=" + Arrays.toString(libros) +
                '}';
    }
}

class BibliotecaReader {
    private final Scanner scanner;
    private final LibroReader libroReader;

    public BibliotecaReader(Scanner scanner, LibroReader libroReader) {
        this.scanner = scanner;
        this.libroReader = libroReader;
    }

    public Biblioteca read() {
        System.out.println("Nombre de la Bibloteca: ");
        String nombre = scanner.nextLine();
        int cantidad;
        do {
            System.out.println("¿Cuántos libros tiene?: ");
            cantidad = scanner.nextInt();
            scanner.nextLine();
        } while (cantidad < 1);
        Libro[] libros = new Libro[cantidad];
        for (int i = 0 ; i < libros.length ; i++) {
            libros[i] = libroReader.read();
        }
        return new Biblioteca(nombre, libros);
    }
}

class LibroReader {
    private final Scanner scanner;
    private final AutorReader autorReader;

    public LibroReader (Scanner scanner, AutorReader autorReader){
        this.scanner = scanner;
        this.autorReader = autorReader;
    }

    public Libro read() {
        System.out.println("Dime el ISBN: ");
        Long isbn = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Dime el título: ");
        String titulo = scanner.nextLine();
        System.out.println("Dime el año de publicación: ");
        int año = scanner.nextInt();
        scanner.nextLine();
        int cantidad;
        do {
            System.out.println("Dime cuántos autores tiene el libro: ");
            cantidad = scanner.nextInt();
            scanner.nextLine();
        } while (cantidad < 1);
        Autor[] autores = new Autor[cantidad];
        for (int i = 0; i < autores.length; i++) {
            autores[i] = autorReader.read();
        }
        return new Libro(isbn, titulo, año, autores);
    }
}

class AutorReader {
    private final Scanner scanner;

    public AutorReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Autor read() {
     System.out.println("NIF del autor: ");
     String nif = scanner.nextLine();
     System.out.println("Nombre del autor: ");
     String autor = scanner.nextLine();
     System.out.println("Apellidos del autor");
     String apellidos = scanner.nextLine();
     return new Autor (nif, autor, apellidos);
    }
}