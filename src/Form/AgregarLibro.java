package Form;
import Entities.*;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase de agregar libro
 */
public class AgregarLibro extends JFrame{
    private JTextField isbnField;
    private JTextField stockField;
    private JTextField autorField;
    private JTextField categoriaField;
    private JTextField tituloField;
    private JTextField cantPagField;
    private JButton agregarLibroButton;
    private JPanel agregarLibroPanel;
    private JButton volverAlMenuButton;

    /**
     * Constructor de la opcion agregar libro
     *
     * @param libros: base de dato de los libros
     * @param usuario: usuario que eligio esta opcion
     * @param usuarios: lista completa de usuarios, necesario para volver al menu principal
     */
    public AgregarLibro(LinkedList<Libro> libros, Usuario usuario, LinkedList<Usuario> usuarios) {
        setContentPane(agregarLibroPanel);
        setTitle("Menú de Biblioteca");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // Accion del boton de agregar libro en el form
        agregarLibroButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibro(libros);
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
     * Metodo de busqueda del libro en la base de datos, si no se encuentra, es agregado
     *
     * @param libros: base de dato de los libros, donde debe ser agregado el libro
     */
    public void agregarLibro(LinkedList<Libro> libros){

        try{
            String isbn = isbnField.getText();

            if (!isbn.isEmpty()){ // Si el campo de isbn no estaba vacio

                // Busqueda del libro en la base de datos
                for (Libro libro : libros){
                    if (libro.getIsbn().equalsIgnoreCase(isbnField.getText())){ // Se encontro, no puede ser agregado

                        JOptionPane.showMessageDialog(agregarLibroPanel, "Ya se encuentra registrado el libro, no puede agregarlo al sistema",
                                "Error al agregar libro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            } else { // El campo de isbn estaba vacio
                JOptionPane.showMessageDialog(agregarLibroPanel, "Rellene todos los campos",
                        "Error al agregar libro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Una vez pasada la primera prueba, se solicita el resto de datos
            String stock = stockField.getText();
            String autor = autorField.getText();
            String categoria = categoriaField.getText();
            String titulo = tituloField.getText();
            String cantPag = cantPagField.getText();

            // El resto de datos no estan vacios
            if (!stock.isEmpty() && !autor.isEmpty() && !categoria.isEmpty() && !titulo.isEmpty() && !cantPag.isEmpty()){

                // Variable que almacenara el stock y la cantidad de paginas
                int stockInt;
                int cantPagInt;

                // Si el usuario ingreso una letra en vez de un numero para el stock, se vacia el campo y retorna
                try{
                    stockInt = Integer.parseInt(stock);
                } catch (IllegalArgumentException e){
                    stockField.setText("");
                    JOptionPane.showMessageDialog(agregarLibroPanel, "Ingrese una cantidad numérica para el stock",
                            "Error al agregar libro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Si el usuario ingreso una letra en vez de un numero para la cantidad de paginas, se vacia el campo y retorna
                try{
                    cantPagInt= Integer.parseInt(cantPag);
                } catch (IllegalArgumentException e){
                    cantPagField.setText("");
                    JOptionPane.showMessageDialog(agregarLibroPanel, "Ingrese una cantidad numérica de páginas",
                            "Error al agregar libro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Los datos se agregan al arreglo que almacena los libros, posteriormente se escribe en la base de datos al cerrar sesion

                Libro libro = new Libro(isbn,titulo,autor, categoria,stockInt,cantPagInt);
                libros.add(libro);
                isbnField.setText("");
                stockField.setText("");
                autorField.setText("");
                categoriaField.setText("");
                tituloField.setText("");
                cantPagField.setText("");
                JOptionPane.showMessageDialog(agregarLibroPanel, "Libro agregado exitosamente",
                        "Menú de Biblioteca", JOptionPane.INFORMATION_MESSAGE);

            } else { // Alguno de los datos esta vacio
                JOptionPane.showMessageDialog(agregarLibroPanel, "Rellene todos los campos",
                        "Error al agregar libro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e){ // Ante cualquier error en el sistema
            JOptionPane.showMessageDialog(agregarLibroPanel,"Ha ocurrido un error: "+e.getMessage());
        }
    }
}
