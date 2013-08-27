package es.upm.dit.concurrencia.threads;

/**
 * Escribe la hora cada 1 s y emite un sonido al pulsar intro
 * 
 * @author jpuente
 * @version 2013-02-28
 *
 */
public class HoraSonido {

	public static void main(String[] args) {
		Thread hora = new Hora () ;
		Thread sonido = new Sonido();
		hora.start();
		sonido.start();
	}
}
