package es.upm.dit.adsw.concurrencia;

/**
 * Condiciones de carrera en variables compartidas.
 * @author jpuente
 * @version 20120309
 */

/**
 * @author jpuente
 *
 */
public class Contadores {

	/**
	 * variable compartida.
	 */
	static long cuenta = 0;


	static final long nVeces = 1000000;
	static final int nThreads = 10;

	/**
	 * Thread que incrementa contador nVeces
	 */
	private static class Contador extends Thread {
		public void run() {
			for (long i = 0; i<nVeces; i++)
				cuenta++;    // regi—n cr’tica
		}
	}

	public static void main(String[] args) {
		System.out.println(nThreads + " contadores incrementando " 
				+ "la cuenta " + nVeces +" veces cada uno" );
		Contador[] contador = new Contador[nThreads];
		for (int id = 0; id < nThreads; id++) {
			contador[id] = new Contador();
			contador[id].start();
		}
		for (int id = 0; id < nThreads; id++) {
			try{contador[id].join();}
			catch (InterruptedException e) {return;}
		}
		System.out.print("cuenta = " + cuenta);
		System.out.println("; deber’a ser " + nThreads*nVeces);
	}

}
