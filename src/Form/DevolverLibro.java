package Form;
import Entities.*;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Clase de la opcion devolver libro
 */
public class DevolverLibro extends JFrame{
    private JTextField isbnField;
    private JButton devolverButton;
    private JButton volverAlMenuButton;
    private JPanel panelDevolver;

    /**
     * Constructor de la opcion devolver libro
     *
     * @param libros: base de dato de los libros
     * @param usuario: usuario que eligio esta opcion
     * @param usuarios: lista completa de usuarios, necesario para volver al menu principal
     */
    public DevolverLibro(LinkedList<Libro> libros, Usuario usuario, LinkedList<Usuario> usuarios) {
        setContentPane(panelDevolver);
        setTitle("Menú de Biblioteca");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // Accion del boton de devolver libro en el form
        devolverButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverLibro(libros, usuario);
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
     * Metodo de busqueda del libro por su isbn, consulta si tiene stock para realizar la devolucion y se guarda la transaccion en registro.txt
     *
     * @param libros: base de dato de los libros, para buscar si existe el isbn ingresado por el usuario
     * @param usuario: usuario que realizo la transaccion
     */
    public void devolverLibro(LinkedList<Libro> libros, Usuario usuario){

        try {
            String isbnFieldText = isbnField.getText();
            isbnField.setText("");

            if (!isbnFieldText.isEmpty()){ // El usuario ingreso un isbn para buscar

                for (Libro libro : libros){ // Busqueda del isbn ingresado en los libros de la base de datos
                    String isbnLibro = libro.getIsbn();
                    int stockLibro = libro.getStock();

                    if (isbnLibro.equalsIgnoreCase(isbnFieldText)){ // Se encontro el isbn que se desea devolver

                        String titulo = libro.getNombre();
                        String rut = usuario.getRut();
                        String nombre = usuario.getNombre();
                        String apellido = usuario.getApellido();
                        // Aumenta el stock de dicho libro al realizar devolucion
                        libro.setStock(stockLibro+1);
                        // Se escribe la transaccion realizada por el usuario en registro.txt
                        BufferedWriter bw = new BufferedWriter(new FileWriter("registro.txt",true));
                        String[] data = {rut,nombre,apellido,isbnLibro,titulo,"devolucion"};
                        bw.write(String.join(",",data));
                        bw.newLine();
                        bw.close();
                        JOptionPane.showMessageDialog(panelDevolver, "Se ha realizado la devolución exitosamente",
                                "Menú de Biblioteca", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                // Si no hay return, entonces no se encontro el libro
                JOptionPane.showMessageDialog(panelDevolver, "No se encontró el libro",
                        "Error al devolver libro", JOptionPane.ERROR_MESSAGE);

            } else { // El usuario no ingreso un isbn para buscar
                JOptionPane.showMessageDialog(panelDevolver, "No ingreso un ISBN",
                        "Error al devolver libro", JOptionPane.ERROR_MESSAGE);
            }

        }catch (Exception e){ // Ante cualquier error al ejecutar el programa
            JOptionPane.showMessageDialog(panelDevolver,"Ha ocurrido un error"+e.getMessage());
        }
    }
}
