import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Main.java
 * Proposito: Llama los metodos de las otras clases
 * @author fernando.caimanque@alumnos.ucn.cl
 * @version 1.0 6/4/18
 */
public class Main {
    /**
     * @param cantRuedas La cantidad de ruedas del tragamonedas
     * @param saldo dinero con el que se cuenta para jugar
     */
    private static final int cantRuedas = 3;
    private static final int saldo = 1000;


    /**
     * Main recibe el input, verifica, y llama a los metodos de las otras clases
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        maquina slotM = new maquina(cantRuedas, saldo);
        int apuesta =-1;//
        while (apuesta!=0&&slotM.getSaldo()>0) {
            slotM.mostrarSaldoApuesta();
            try {//verificación de input
                apuesta=input.nextInt();
                slotM.ingreso(apuesta);
            } catch (InputMismatchException e) {
                System.out.print("Escriba un numero por favor");
                input.next();
            }
        }
    }
}