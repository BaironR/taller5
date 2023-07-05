package Form;
import javax.swing.*;
import Entities.*;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Clase del menu principal
 */
public class MenuPrincipal extends JFrame{
    private JButton agregarLibroButton;
    private JButton devolverLibroButton;
    private JButton prestarLibroButton;
    private JButton buscarLibroButton;
    private JPanel panelMenu;
    private JButton cerrarSesionButton;

    /**
     * Constructor del menu principal
     *
     * @param libros: base de dato de los libros
     * @param usuario: usuario que ingreso al sistema
     * @param usuarios: lista completa de usuarios, necesario para volver a iniciar sesion al cerrar la sesion actual
     */
    public MenuPrincipal(LinkedList<Libro> libros, Usuario usuario, LinkedList<Usuario> usuarios) {
        setContentPane(panelMenu);
        setTitle("Menú de Biblioteca");
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // Accion del boton de buscar libro en el form
        buscarLibroButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                BuscarLibro buscarLibro = new BuscarLibro(libros, usuario, usuarios);
            }
        });

        // Accion del boton de prestar libro en el form
        prestarLibroButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PrestarLibro prestarLibro = new PrestarLibro(libros, usuario, usuarios);
            }
        });

        // Accion del boton de agregar libro en el form
        agregarLibroButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AgregarLibro agregarLibro = new AgregarLibro(libros, usuario, usuarios);
            }
        });

        // Accion del boton de devolver libro en el form
        devolverLibroButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                DevolverLibro devolverLibro = new DevolverLibro(libros, usuario, usuarios);
            }
        });

        // Accion del boton cerrar sesion en el form
        cerrarSesionButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                // Al cerrar sesion se escribe el nuevo archivo en base a las modificaciones por el usuario
                try{
                    BufferedWriter bw = new BufferedWriter(new FileWriter("libros.txt",false));

                    for (Libro libro: libros){
                        String isbn = libro.getIsbn();
                        String titulo = libro.getNombre();
                        String autor = libro.getAutor();
                        String categoria = libro.getCategoria();
                        String stock = String.valueOf(libro.getStock());
                        String cantPag = String.valueOf(libro.getCantPaginas());
                        String[] data = {isbn,titulo,autor,categoria,stock,cantPag};
                        bw.write(String.join(",",data));
                        bw.newLine();
                    }
                    bw.close();

                } catch (Exception ex) { // Ante cualquier error al escribir archivo
                    System.out.println("Error al escribir el archivo: " + ex.getMessage());
                }

                IniciarSesion iniciarSesion = new IniciarSesion(usuarios,libros);
            }
        });
    }
}
