package es.upm.dit.adsw.concurrencia;


/**
 * @author jpuente
 * @version 20120309
 *
 */
public class ContadoresSincronizados {
	
	static final long nVeces = 1000000;
	static final int nThreads = 10;
	
	private static class CuentaSincronizada {
		
		private long cuenta = 0;
		
		public CuentaSincronizada(long valorInicial){
			cuenta = valorInicial;
		}
		
		public synchronized void incrementar () {
		    cuenta++;                
		  }
			
		  public synchronized long valor () {
		    return cuenta;
		  }

	}

	private static class Contador extends Thread {
		CuentaSincronizada cuenta;
		
		public Contador (CuentaSincronizada cuenta){
			this.cuenta = cuenta;
		}
		
		public void run() {
			for (long i = 0; i<nVeces; i++)
				cuenta.incrementar();    // región crítica
		}
	}
	
	public static void main(String[] args) {
		System.out.println(nThreads + " contadores incrementando " 
				+ "la cuenta " + nVeces +" veces cada uno" );
		
		CuentaSincronizada cuenta = new CuentaSincronizada(0);
		Contador[] contador = new Contador[nThreads];
		for (int id = 0; id < nThreads; id++) {
			contador[id] = new Contador(cuenta);
			contador[id].start();
		}
		for (int id = 0; id < nThreads; id++) {
			try{contador[id].join();}
			catch (InterruptedException e) {return;}
		}
		System.out.print("cuenta = " + cuenta.valor());
		System.out.println("; debería ser " + nThreads*nVeces);
	}
	
}
