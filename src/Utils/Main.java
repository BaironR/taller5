package Utils;

/**
 * Utils.Main class
 */
public class Main {

    /**
     * The Utils.Main
     *
     * @param args to use
     */
    public static void main(String[] args) {

        try{
            SistemaImpl sistema = new SistemaImpl();

        } catch (Exception e) {
            System.out.println("Hubo un error en el sistema");
        }
    }
}
