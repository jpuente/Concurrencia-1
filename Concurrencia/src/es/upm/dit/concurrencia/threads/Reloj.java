package es.upm.dit.concurrencia.threads;

/**
 * Minutero con interrupción
 * 
 * @author jpuente
 * @version 2013-02-28
 *
 */public class Reloj {

	public static void main (String args[]) {
	    int tiempoEspera = 30; // Segundos de espera
	    Minutero minutero = new Minutero();
	    minutero.start();
	    try { 
	       Thread.sleep(tiempoEspera * 1000);
		    minutero.interrupt(); // Fin de la espera
	    } catch (Exception e) {
	        System.err.println("Error esperando ");
	    }
	  }
}
