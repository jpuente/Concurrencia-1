package es.upm.dit.concurrencia.threads;

public class Minutero extends Thread {

	@Override 
	public void run() {
		int minutos=0;
		try {
			while (true) {  
				System.out.println("Minutos: " + minutos);
				// sleep(5000); // La hebra se bloquea un minuto
				sleep(6000);
				minutos++;
			}
		} catch (InterruptedException e) {
			System.out.println("Fin");
			return;  // La hebra ha sido desbloqueada mediante una
		}          // invocación a interrupt
	}


}
