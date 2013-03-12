package es.upm.dit.adsw.lab1C;

/**
 * Funciones parametricas.
 *
 * @author Jose A. Manas
 * @version 10.2.2012
 */
public class Funcion02
        implements FuncionP {

    /**
     * Constructor.
     */
    public Funcion02() {
    }

    /**
     * Valor de X en función de t.
     *
     * @param t parametro.
     * @return x(t).
     */
    public double fx(double t) {
        return t + 2 * Math.sin(2 * t) - 4;
    }

    /**
     * Valor de Y en función de t.
     *
     * @param t parametro.
     * @return y(t).
     */
    public double fy(double t) {
        return t + 2 * Math.cos(5 * t) - 4;
    }
}

