package es.upm.dit.adsw.productorconsumidor;

/**
 * @author jpuente
 * @version 20120320
 * 
 * @param <E> tipo de elementos que se envían a través del buffer
 */
public interface Buffer<E> {
	
	void enviar(E dato) 
			throws InterruptedException;
	
	E recibir() 
			throws InterruptedException;
}
