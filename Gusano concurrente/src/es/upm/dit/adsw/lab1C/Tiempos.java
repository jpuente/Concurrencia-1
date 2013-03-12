package es.upm.dit.adsw.lab1C;

/**
 * Clase auxiliar para que el programa se pare unos milisegundos. 
 * Se usa para ralentizar la ejecucion.
 * 
 * @author jam
 * @version 10.2.2012
 * 
 */
public class Tiempos {

	/**
	 * Metodo auxiliar. Espera un cierto tiempo antes de devolver el control.
	 * 
	 * @param ms
	 *            tiempo de espera en milisegundos.
	 */
	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ignored) {
		}
	}
}