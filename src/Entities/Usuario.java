package Entities;

/**
 * Clase del usuario
 */
public class Usuario {

    private final String rut;
    private final String nombre;
    private final String apellido;
    private final String contrasenia;

    /**
     * Constructor del usuario
     *
     * @param rut: rut del usuario
     * @param nombre: nombre del usuario
     * @param apellido: apellido del usuario
     * @param contrasenia: contrasenia del usuario
     */
    public Usuario(String rut, String nombre, String apellido, String contrasenia) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
    }

    /**
     *
     * @return rut del usuario
     */
    public String getRut() {
        return rut;
    }

    /**
     *
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return apellido del usuario
     */
    public String getApellido() {
        return apellido;
    }

    /**
     *
     * @return contrasenia del usuario
     */
    public String getContrasenia() {
        return contrasenia;
    }
}
