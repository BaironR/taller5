package Form;
import Entities.*;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase de buscar libro
 */
public class BuscarLibro extends JFrame{
    private JButton buscarButton;
    private JTextField isbnField;
    private JPanel panelBuscarLibro;
    private JTextArea infoLibroArea;
    private JButton volverAlMenuButton;

    /**
     * Constructor de la opcion de buscar libro
     *
     * @param libros: base de dato de los libros
     * @param usuario: usuario que eligio esta opcion
     * @param usuarios: lista completa de usuarios, necesario para volver al menu principal
     */
    public BuscarLibro(LinkedList<Libro> libros, Usuario usuario, LinkedList<Usuario> usuarios) {
        setContentPane(panelBuscarLibro);
        setTitle("Menú de Biblioteca");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // Accion del boton de buscar libro en el form
        buscarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {buscarLibro(libros);
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
     * Metodo de busqueda de libros en la base de datos, al encontrarlo imprime los datos del libro
     *
     * @param libros: base de dato de los libros a consultar
     */
    public void buscarLibro(LinkedList<Libro> libros){

        try{
            String isbn = isbnField.getText();

            if (!isbn.isEmpty()){ // El campo de isbn no esta vacio

                for (Libro libro : libros){ // Busqueda del libro por su isbn
                    if (libro.getIsbn().equalsIgnoreCase(isbnField.getText())){ // Si lo encuentra, lo imprime y retorna
                        infoLibroArea.setText(libro + "\n");
                        return;
                    }
                }
            } else { // El campo de isbn esta vacio
                JOptionPane.showMessageDialog(panelBuscarLibro, "No ingreso un ISBN",
                        "Error al buscar libro", JOptionPane.ERROR_MESSAGE);
            }

            infoLibroArea.setText("Libro no encontrado"); // No se encontro el libro en la base de datos

        }catch (Exception e){ // Ante cualquier error en el sistema
            JOptionPane.showMessageDialog(panelBuscarLibro,"Ha ocurrido un error"+e.getMessage());
        }
    }
}
