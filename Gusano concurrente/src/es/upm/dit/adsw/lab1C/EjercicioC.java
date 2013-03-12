package es.upm.dit.adsw.lab1C;

import java.awt.*;

/**
 * @author Jose A. Manas
 * @version 10.2.2012
 */
public class EjercicioC {
    private static final int VENTANA_ANCHO = 600;
    private static final int VENTANA_ALTO = 600;
    private static final double X_MIN = -4;
    private static final double X_MAX = +4;
    private static final double Y_MIN = -4;
    private static final double Y_MAX = +4;

    /**
     * Para arrancar desde consola.
     * Ejemplo: java es.upm.dit.adsw.lab1.Ejercicio 30 0 100 0.05
     *
     * @param args recibe 3 argumentos: longitud, t_inicial, t_final y delta_t.
     */
    public static void main(String[] args) {
        Ventana ventana = new Ventana(VENTANA_ANCHO, VENTANA_ALTO);
        final Papel papel = new Papel(ventana, X_MIN, X_MAX, Y_MIN, Y_MAX);

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                FuncionP funcion = new Funcion00();
                Gusano gusano = new Gusano(papel, Color.BLUE, funcion, 30);
                gusano.start(0, 1000, 0.05);
            }
        };
        thread1.start();

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                FuncionP funcion = new Funcion01();
                Gusano gusano = new Gusano(papel, Color.RED, funcion, 15);
                gusano.start(100, 300, 0.05);
            }
        };
        thread2.start();

        Thread thread3 = new Thread() {
            @Override
            public void run() {
                FuncionP funcion = new Funcion02();
                Gusano gusano = new Gusano(papel, Color.BLACK, funcion, 10);
                gusano.start(0, 100, 0.02);
            }
        };
        thread3.start();
    }
}

