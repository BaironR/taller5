package Form;
import Entities.*;
import javax.swing.*;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Clase de iniciar sesion
 */
public class IniciarSesion extends JFrame{
    private JButton iniciarSesionButton;
    private JPanel panelInicioSesion;
    private JTextField contraseniaField;
    private JTextField rutField;
    private Usuario usuario;

    /**
     * Constructor del inicio de sesion
     *
     * @param usuarios: lista de usuarios registrados en el sistema
     * @param libros: lista de libros registrados en el sistema
     */
    public IniciarSesion(LinkedList<Usuario> usuarios, LinkedList<Libro> libros) {

        setContentPane(panelInicioSesion);
        setTitle("Iniciar sesión");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        iniciarSesionButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                inicio(usuarios, libros);
            }
        });
    }

    /**
     * Se solicitan rut y contrasenia para ingresar al menu principal, debe estar ingresado en la base de datos del archivo usuarios.txt
     *
     * @param usuarios: lista de usuarios del sistema
     * @param libros: lista de libros del sistema
     */
    public void inicio(LinkedList<Usuario> usuarios, LinkedList<Libro> libros){
        try{
            // Solicitud de los datos
            String rut = rutField.getText();
            String contrasenia = contraseniaField.getText();

            if (!rut.isEmpty() && !contrasenia.isEmpty()){ // Confirmacion de que los campos no hayan estado vacios

                // Busqueda del usuario en la base de datos
                for (Usuario usuario : usuarios) {
                    if (usuario.getContrasenia().equalsIgnoreCase(contrasenia) && usuario.getRut().equalsIgnoreCase(rut)) {
                        this.usuario = usuario;
                        break;
                    }
                }

                if (this.usuario == null){ // Usuario no encontrado
                    vaciarTexto();
                    JOptionPane.showMessageDialog(panelInicioSesion, "Credenciales inválidas",
                            "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);

                } else { // Usuario encontrado
                    vaciarTexto();
                    setVisible(false);
                    MenuPrincipal menuPrincipal = new MenuPrincipal(libros,this.usuario,usuarios);
                }
            } else { // Alguno de los campos estaba vacio
                vaciarTexto();
                JOptionPane.showMessageDialog(panelInicioSesion, "Credenciales inválidas",
                        "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e){ // Ante cualquier error en el sistema
            JOptionPane.showMessageDialog(panelInicioSesion,"Ha ocurrido un error"+e.getMessage());
        }
    }

    /**
     * Metodo que vacia el texto en los campos de contrasenia y rut
     */
    public void vaciarTexto(){
        contraseniaField.setText("");
        rutField.setText("");
    }
}
