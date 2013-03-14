package es.upm.dit.adsw.mutex;

/**
 * Ejemplo elemental: condiciones de carrera en variables compartidas.
 * 
 * @author jpuente
 * @version 20130314
 */
public class Carreras {

	/**
	 * variable compartida.
	 */
	static long cuenta = 0;


	static final long nVeces = 1000000;
	static final int nThreads = 10;

	/**
	 * Thread que incrementa cuenta nVeces
	 */
	private static class Contador extends Thread {
		public void run() {
			for (long i = 0; i<nVeces; i++)
				cuenta++;    // región crítica
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
		System.out.println("; debería ser " + nThreads*nVeces);
	}

}
