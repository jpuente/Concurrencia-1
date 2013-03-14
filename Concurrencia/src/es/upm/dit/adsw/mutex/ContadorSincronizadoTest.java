package es.upm.dit.adsw.mutex;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Batería de test para mostrar cómo la clase 
 * ContadorSincronizado 
 * es segura con threads
 * 
 * @author jpuente
 * @version 200130314
 */public class ContadorSincronizadoTest {

		/**
		 * Número de threads que se crean
		 */
		static int nThreads = 10;

		/**
		 * Número de veces que cada hebra incrementa el contador
		 */
		static long nVeces = 1000000;

		/**
		 * Objeto contador (ahora está sincronizado)
		 */
		ContadorSincronizado contador;

		@Before
		public void setUp() {
			contador = new ContadorSincronizado(0);
		}

		/**
		 * Prueba con una sola hebra.
		 * No hay concurrencia, y por lo tanto no hay carreras
		 */
		@Test
		public void test00() {
			for (long i=0; i<nVeces; i++) {
				contador.incrementar();
			}
			assertEquals(nVeces,contador.valor());
		}

		/**
		 * Prueba con hebras concurrentes. 
		 * Ahora tampoco hay carreras.
		 */
		@Test
		public void test01() {
			// arrancar hebras
			Thread[] t = new Thread[nThreads];
			for (int i = 0; i < nThreads; i++) {
				t[i] = new Thread() {
					public void run() {
						for (long i = 0; i<nVeces; i++)
							contador.incrementar();    // región crítica
					};
				};
				t[i].start();
			}
			// esperar que terminen todas
			for (int i = 0; i < nThreads; i++) {
				try{t[i].join();}
				catch (InterruptedException e) {return;}
			}
			// probar resultado
			assertEquals(nThreads*nVeces, contador.valor());
		}

	}


