package es.upm.dit.adsw.concurrencia;

public interface Buffer<E> {
	void enviar(E dato) throws InterruptedException;
	E recibir() throws InterruptedException;
}
