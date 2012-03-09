package es.upm.dit.adsw.concurrencia;

import java.util.Random;

/**
 * @author jpuente
 * @version 20120309
 */
public class ProductorConsumidor {

	static class Productor implements Runnable {

		private Buffer<Number> b; 

		public Productor(Buffer<Number> b) {
			this.b = b;
		}

		public void run() {
			Random random = new Random();
			Number n = 0;
			while (true) { 
				n = random.nextInt(); // producir 
				System.out.println("El productor envía " + n);
				try {
					b.enviar(n);           
					Thread.sleep(1000);
				} catch (InterruptedException e) {return;}
			}
		}
	}

	static class Consumidor implements Runnable {

		private Buffer<Number> b; 

		public Consumidor(Buffer<Number> b) {
			this.b = b;
		}

		public void run() {
			Number n = 0;
			while (true) { 
				try {
					n = b.recibir(); 
				} catch (InterruptedException e) {return;}
				System.out.println("El consumidor recibe " + n);
			}
		}
	}


	public static void main(String[] args) {
		Buffer<Number> b = new BufferSimple<Number>();
		Productor p = new Productor(b);
		Consumidor c = new Consumidor(b);
		new Thread(p).start();
		new Thread(c).start();
	}

}
