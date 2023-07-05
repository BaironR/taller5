package Utils;

import Form.IniciarSesion;
import Entities.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

/**
 * Clase del sistema
 */
public class SistemaImpl implements Sistema {

    LinkedList<Libro> listaLibros = new LinkedList<>();
    LinkedList<Usuario> listaUsuarios = new LinkedList<>();

    /**
     * Constructor del sistema
     */
    public SistemaImpl() {
        leerArchivoLibros();
        leerArchivoUsuarios();
        iniciarSesion();
    }

    /**
     * Método encargado de leer el archivo de "libros.txt".
     */
    @Override
    public void leerArchivoLibros() {
        // Leer el archivo "libros.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("libros.txt"))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] chain = line.split(",");
                String isbn = chain[0];
                String titulo = chain[1];
                String autor = chain[2];
                String categoria = chain[3];
                int cantPaginas = Integer.parseInt(chain[4]);
                int stock = Integer.parseInt(chain[5]);
                listaLibros.add(new Libro(isbn,titulo,autor,categoria,cantPaginas,stock));
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Método encargado de leer el archivo de "usuarios.txt".
     */
    @Override
    public void leerArchivoUsuarios() {
        // Leer el archivo "usuarios.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] chain = line.split(",");
                String rut = chain[0];
                String name = chain[1];
                String lastname = chain[2];
                String password = chain[3];
                listaUsuarios.add(new Usuario(rut,name,lastname,password));
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Metodo encargado de abrir el form en la pestania de inicio de sesion
     */
    @Override
    public void iniciarSesion(){
        IniciarSesion iniciarSesion = new IniciarSesion(listaUsuarios, listaLibros);
    }

}