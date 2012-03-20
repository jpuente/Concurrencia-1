package es.upm.dit.adsw.concurrencia.productorconsumidor;

/**
 * @author jpuente
 * @version 20120320
 */
public class ProductorConsumidor {

	public static void main(String[] args) {
		Buffer<String> buffer = new BufferSimple<String>();
		Productor productor1 = new Productor("p1", buffer, 1000);
		Consumidor consumidor = new Consumidor("c", buffer);
		productor1.start();
		consumidor.start();
	}
}
