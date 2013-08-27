package es.upm.dit.concurrencia.threads;

/**
 * Cuenta los minutos transcurridos
 * 
 * @author jpuente
 * @version 2013-02-28
 *
 */public class Minutero extends Thread {

	 @Override 
	 public void run() {
		 int minutos=0;
		 try {
			 while (true) {  
				 // sleep(60000);
				 sleep(6000);    // más corto para que no se haga pesado
				 minutos++;
				 System.out.println("Minutos: " + minutos);
			 }
		 } catch (InterruptedException e) {
			 System.out.println("Fin");
			 return; 
		 }
	 }
 }