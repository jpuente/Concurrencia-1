package es.upm.dit.adsw.lab1C;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Simula un gusano en la pantalla. Para ello usa una serie finita de puntos de
 * una funcion definida parametricamente (x e y son funcion del tiempo t).
 *
 * @author Jose A. Manas
 * @version 10.2.2012
 */
public class Gusano {

    private final Papel papel;
    private final Color color;
    private final FuncionP funcion;
    private final Cola cola;

    private List<Object> misObjetosPintados = new ArrayList<Object>();

    /**
     * Constructor. Prepara el gusano.
     *
     * @param papel    para pintar.
     * @param color    color de los trazos.
     * @param funcion  funfion parametrica que va dando valores sucesivos.
     * @param longitud numero maximo de tramos que componen el gusano.
     */
    public Gusano(Papel papel, Color color, FuncionP funcion, int longitud) {
        this.papel = papel;
        this.color = color;
        this.funcion = funcion;
        this.cola = new Cola(longitud);
    }

    /**
     * Pone en marcha al gusano.
     *
     * @param desde punto de partida: instante de tiempo en el que arranca.
     * @param hasta punto final: instante de tiempo en el que para.
     * @param dt    incrementos de tiempo entre calculos de posicion.
     */
    public void start(double desde, double hasta, double dt) {
        double t = desde;
        while (t < hasta) {
            double x = funcion.fx(t);
            double y = funcion.fy(t);
            Punto2D punto = new Punto2D(x, y);
            cola.mete(punto);
            pinta(cola.getPuntos());
            Tiempos.sleep(100);
            t += dt;
        }
    }

    /**
     * Limpia el papel y pinta las lineas que usen los sucesivos puntos de la
     * lista.
     *
     * @param puntos serie de puntos que componen el trazo a pintar.
     */
    public void pinta(List<Punto2D> puntos) {
        try {
            for (Object x : misObjetosPintados)
                synchronized(papel){papel.borra(x);}
            misObjetosPintados.clear();

            Punto2D p1 = null;
            for (Punto2D p2 : puntos) {
            	synchronized(papel){papel.setColor(color);}
                if (p1 != null) {
                	synchronized(papel){
                		Object x = papel.linea(p1.getX(), p1.getY(), p2.getX(), p2.getY());
                		misObjetosPintados.add(x);
                	}
                }
                p1 = p2;
            }
            synchronized(papel){papel.pinta();}
        } catch (Exception ignored) {
        }
    }
}

