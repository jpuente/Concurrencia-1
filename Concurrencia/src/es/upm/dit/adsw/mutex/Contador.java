/**
 * Contador simple
 * No es seguro en un entorno concurrente
 */
package es.upm.dit.adsw.mutex;

/**
 * @author jpuente
 * @version 20130314
 */
public class Contador {

	private long cuenta = 0;

	public Contador(long valorInicial){
		cuenta = valorInicial;
	}

	public void incrementar() {
		cuenta++;                
	}

	public long valor() {
		return cuenta;
	}
}
