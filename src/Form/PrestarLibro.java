package Form;
import Entities.*;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Clase de la opcion prestar libro
 */
public class PrestarLibro extends JFrame{
    private JTextField isbnField;
    private JButton arrendarButton;
    private JButton volverAlMenuButton;
    private JPanel panelPrestarLibro;

    /**
     * Constructor de la opcion prestar libro
     *
     * @param libros: base de dato de los libros
     * @param usuario: usuario que eligio esta opcion
     * @param usuarios: lista completa de usuarios, necesario para volver al menu principal
     */
    public PrestarLibro(LinkedList<Libro> libros, Usuario usuario, LinkedList<Usuario> usuarios) {
        setContentPane(panelPrestarLibro);
        setTitle("Menú de Biblioteca");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // Accion del boton de arrendar libro en el form
        arrendarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                prestarLibro(libros, usuario);
            }
        });

        // Accion del boton de volver al menu principal en el form
        volverAlMenuButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MenuPrincipal menuPrincipal = new MenuPrincipal(libros, usuario, usuarios);
            }
        });
    }

    /**
     * Metodo de busqueda del libro por su isbn, consulta si tiene stock para realizar el prestamo y se guarda la transaccion en registro.txt
     *
     * @param libros: base de dato de los libros, para buscar si existe el isbn ingresado por el usuario
     * @param usuario: usuario que realizo la transaccion
     */
    public void prestarLibro(LinkedList<Libro> libros, Usuario usuario){

        try {
            String isbnFieldText = isbnField.getText();
            isbnField.setText("");

            if (!isbnFieldText.isEmpty()){ // El usuario ingreso un isbn para buscar

                for (Libro libro : libros){ // Busqueda del isbn ingresado en los libros de la base de datos
                    String isbn = libro.getIsbn();
                    int stock = libro.getStock();

                    if (isbn.equalsIgnoreCase(isbnFieldText)){ // Se encontro el isbn que se desea arrendar

                        if (stock > 0){ // Hay stock del libro encontrado
                            String titulo = libro.getNombre();
                            String rut = usuario.getRut();
                            String nombre = usuario.getNombre();
                            String apellido = usuario.getApellido();
                            // Disminuye stock del libro al realizar prestamo
                            libro.setStock(stock-1);
                            // Se escribe la transaccion realizada por el usuario en registro.txt
                            BufferedWriter bw = new BufferedWriter(new FileWriter("registro.txt",true));
                            String[] data = {rut,nombre,apellido,isbn,titulo,"préstamo"};
                            bw.write(String.join(",",data));
                            bw.newLine();
                            bw.close();
                            JOptionPane.showMessageDialog(panelPrestarLibro, "Se ha realizado el préstamo exitosamente",
                                    "Menú de Biblioteca", JOptionPane.INFORMATION_MESSAGE);
                            return;

                        } else { // No hay stock del libro encontrado
                            JOptionPane.showMessageDialog(panelPrestarLibro, "No hay stock de ese libro",
                                    "Error al prestar libro", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                // Si no hay return, entonces no se encontro el libro
                JOptionPane.showMessageDialog(panelPrestarLibro, "No se encontró el libro",
                        "Error al prestar libro", JOptionPane.ERROR_MESSAGE);

            } else { // El usuario no ingreso un isbn para buscar
                JOptionPane.showMessageDialog(panelPrestarLibro, "No ingreso un ISBN",
                        "Error al prestar libro", JOptionPane.ERROR_MESSAGE);
            }

        }catch (Exception e){ // Ante cualquier error al ejecutar el programa
            JOptionPane.showMessageDialog(panelPrestarLibro,"Ha ocurrido un error"+e.getMessage());
        }
    }
}
