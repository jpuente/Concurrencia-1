package es.upm.dit.adsw.concurrencia;

public class ControlMinutero {

	public static void main (String args[]) {
	    int tiempoEspera = 30; // Segundos de espera
	    Minutero unMinutero = new Minutero();
	    unMinutero.start();
	    try { 
	       Thread.sleep(tiempoEspera * 1000);
		    unMinutero.interrupt(); // Fin de la espera
	    } catch (Exception e) {
	        System.err.println("Error esperando ");
	    }
	  }
}
