package es.upm.dit.concurrencia.threads;

import java.util.Date;

/**
 * Escribe la hora cada 1 s
 * 
 * @author jpuente
 * @version 2013-02-28
 *
 */public class Hora extends Thread {
		
		@Override
		public void run () {
			try {
				while (true) {
					Date date = new Date();
					System.out.println(date.toString());
					sleep(1000);         /* espera 1000 ms */
				}
			} catch (InterruptedException e) {
				return;                  /* terminar esta hebra */
			} 
		}
	}
	
