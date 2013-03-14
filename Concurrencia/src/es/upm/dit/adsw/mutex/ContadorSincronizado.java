/**
 * Contador sincronizado
 * Se puede usar en un entorno concurrente (thread safe)
 */
package es.upm.dit.adsw.mutex;

/**
 * @author jpuente
 * @version 20130314
 */
public class ContadorSincronizado  {
	
	private long cuenta = 0;

	public ContadorSincronizado (long valorInicial){
		cuenta = valorInicial;
	}

	public synchronized void incrementar() {
		cuenta++;                
	}

	public synchronized long valor() {
		return cuenta;
	}
}
