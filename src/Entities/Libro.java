package Entities;

/**
 * Clase de libro
 */
public class Libro {

    private final String isbn;
    private final String nombre;
    private final String autor;
    private final String categoria;
    private final int cantPaginas;
    private int stock;

    /**
     * Constructor de libro
     *
     * @param isbn: isbn del libro
     * @param nombre: nombre o titulo del libro
     * @param autor: autor del libro
     * @param categoria: categoria del libro
     * @param cantPaginas: cantidad de paginas del libro
     * @param stock: stock del libro
     */
    public Libro(String isbn, String nombre, String autor, String categoria, int cantPaginas, int stock) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.autor = autor;
        this.categoria = categoria;
        this.cantPaginas = cantPaginas;
        this.stock = stock;
    }

    /**
     *
     * @return informacion del libro como String
     */
    public String toString(){
        return "Titulo: "+ this.nombre+"\n"+ "Autor: "+ this.autor+"\n"+ "Categoria: "+ this.categoria+"\n"+ "Número de copias: "+ this.stock;
    }

    /**
     *
     * @return isbn del libro
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     *
     * @return nombre del libro
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return autor del libro
     */
    public String getAutor() {
        return autor;
    }

    /**
     *
     * @return categoria del libro
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     *
     * @return cantPaginas: cantidad de paginas del libro
     */
    public int getCantPaginas() {
        return cantPaginas;
    }

    /**
     *
     * @return stock del libro
     */
    public int getStock() {
        return stock;
    }

    /**
     * Set del stock del libro
     *
     * @param stock: stock actualizado del libro
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
