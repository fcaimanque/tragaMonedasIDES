import java.util.Random;
/**
 *Componente de la maquina tragamonedas que registra el numero aleatorio
 *@param rgen objeto que genera numeros aleatorios con limite maximo
 *@param valor valor numerico que está en la rueda
 */
public class Rueda {
    private Random rgen = new Random();
    private int valor;

    /**
     *
     * @return el parámetro valor
     */
    public int getValor(){
        return this.valor;
    }

    /**
     *constructor genera valor aleatorio
     */
    public Rueda(){
        this.valor = rgen.nextInt(10);
    }

    /**
     *método para llamados desde maquina.java
     */
    public void girar(){
        this.valor = rgen.nextInt(10);
    }
}