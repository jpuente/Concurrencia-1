package es.upm.dit.adsw.threads;

import java.util.*;
import java.awt.*;

/**
 * Escribe la hora cada 1 s y emite un sonido al pulsar intro
 * 
 * @author jpuente
 * @version 2012-02-28
 *
 */
public class HoraSonido {
	
	static class Hora extends Thread {
		@Override
		public void run () {
			try {
				while (true) {
					Date date = new Date();
					System.out.println(date.toString());
					sleep(1000);       /* espera 1000 ms */
				}
			} catch (InterruptedException e) {
				return;                  /* terminar esta hebra */
			} 
		}
	}
	
	static class Sonido extends Thread {
		@Override
		public void run() {
			Scanner sc = new Scanner(System.in);
			while(true) {
				sc.nextLine();
				Toolkit.getDefaultToolkit().beep(); 
			}
		}
	}


	public static void main(String[] args) {
		Thread hora = new Hora () ;
		Thread sonido = new Sonido();
		hora.start();
		sonido.start();
	}
}
