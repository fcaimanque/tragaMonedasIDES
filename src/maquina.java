import java.util.Calendar;
/**
 *Clase que crea el juego del tragamonedas.
 */
public class maquina {

    private int saldo;
    private int cantRuedas;
    private Rueda[] ruedas;

    /**
     * Método para obtener la cantidad de dinero actual. Verifica que haya saldo
     * para una nueva apuesta.
     * @return la cantidad de dinero actual para apostar
     */
    public int getSaldo(){
        return this.saldo;
    }

    /**
     * @param cantRuedas int configurable para determinar la cantidad de ruedas
     * @param saldo cantidad de dinero actual,
     *              si llega a 0 el programa se detiene.
     */
    public maquina(int cantRuedas, int saldo) {
        this.cantRuedas = cantRuedas;
        this.saldo = saldo;
        this.ruedas = new Rueda[cantRuedas];
        for (int i = 0; i < cantRuedas; i++)
            ruedas[i] = new Rueda();

        System.out.println("Bienvenido al Tragamonedas Ingenieria de SW");
        System.out.println("============================================");
    }

    /**
     *El método muestra los valores en cada una de las ruedas
     */
    public void imprimirInterfaz(){
        String carril = "+", separacion = "|";
        for (Rueda r : ruedas){
            carril += "---+";
            separacion += " ";
            if (r.getValor() == 0)
                separacion += "*";
            else
                separacion += r.getValor();
            separacion += " |";
        }
        System.out.println(carril);
        System.out.println(separacion);
        System.out.println(carril);
    }

    /**
     *El método muestra la cantidad de dinero actual
     * con el que se cuenta para jugar. Muestra el dialogo
     * para hacer la entrada de la apuesta.
     */
    public void mostrarSaldoApuesta() {
        System.out.println("\nSu saldo es de $" + this.saldo + "." + " ¿Cuanto desea apostar?");
    }

    /**
     * El metodo valida y registra la apuesta. Si el valor es 0,
     * el jugador se ha retirado.
     * @param dineroApostado valor de la apuesta. Si es igual a 0, retira al jugador.
     */
    public void ingreso(int dineroApostado) {
        if (dineroApostado<=this.saldo&&dineroApostado>0) {
            this.saldo -= dineroApostado;
            girar();
            imprimirInterfaz();
            this.saldo += pagoPremio(dineroApostado);

            if (this.saldo == 0) {
                System.out.println("Muchas gracias por jugar. Mejor suerte la proxima vez");
            }
        } else if (dineroApostado==0) {
            Calendar cal=Calendar.getInstance();
            int horaActual=(int)cal.get( Calendar.HOUR_OF_DAY );
            if( horaActual>6 && horaActual < 12 ) {
                System.out.print("Buenos dias");
            }
            else if(horaActual>11&&horaActual<20) {
                System.out.print("Buenas tardes");
            }
            else if(horaActual>19||horaActual< 6) {
                System.out.print("Buenas noches");
            }
            System.out.println(", gracias por jugar. Su saldo final es de $"+saldo+".");
        } else {
            System.out.println("Cantidad no valida");
        }
    }

    /**
     *El método que comienza el giro de las ruedas del tragamonedas.
     */
    public void girar(){
        for (Rueda r : ruedas)
            r.girar();
    }

    /**
     *El método calcula los premios del sorteo del tragamonedas.
     * @param dineroApostado valor de la apuesta. Se usa para calcular uno de los premios.
     * @return el valor del premio
     */
    public int pagoPremio(int dineroApostado){
        int[] nums=new int[cantRuedas];
        int jackpots = 0;
        int premio = 0;

        for (int i=0; i<cantRuedas; i++) {
            nums[i]=ruedas[i].getValor();
            if (nums[i]==0)
                jackpots++;
        }
        if (jackpots==0){
           boolean flag=false;
            for(int i=0; i<cantRuedas-1;i++){
                for (int j=i+1; j<cantRuedas; j++){
                    if (ruedas[i].getValor()!=ruedas[j].getValor()) {
                        flag = true;
                    }
                }if (flag)break;
            }if (!flag) premio=ruedas[0].getValor()*dineroApostado;
        }
        else if (jackpots==1) premio=50;
        else if (jackpots==2) premio=300;
        else if (jackpots==3) premio=500;
        if (premio!=0) System.out.println("Usted obtiene $"+premio+".");
        return premio;
    }
}
