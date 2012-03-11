package es.upm.dit.adsw.concurrencia;

public class BufferSimple <E> implements Buffer<E> {

	private E almacen;
	private boolean lleno = false;

	@Override
	public synchronized void enviar(E dato) throws InterruptedException {
		while (lleno) wait();     // espera que haya sitio
		almacen = dato;
		lleno = true;
		notifyAll();              // avisa de que hay un valor
	}

	@Override
	public synchronized E recibir() throws InterruptedException {
		E dato = null;
		while (!lleno) wait(); // espera que haya un valor
		dato = almacen;
		lleno = false;
		notifyAll();              // avisa de que hay sitio
		return dato;
	}

}
