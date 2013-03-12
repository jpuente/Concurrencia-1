package es.upm.dit.adsw.lab1C;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Clase para pintar en una ventana en la pantalla. Una ventana es una matriz
 * rectangular de pixels. Un pixel es un puntito de color en la pantalla. Las
 * pantallas tipicas tienen 800x600 pixels (horizontal x vertical).
 * <p/>
 * En esta clase, el origen, pixel(0, 0), se encuentra en la esquina superior
 * izquierda de la ventana. El eje X crece hacia la derecha. El eje Y crece
 * hacia abajo. De esta forma, el pixel(ANCHO, ALTO) es el extremo inferior
 * derecho.
 * 
 * @author Jose A. Manas
 * @version 28.10.2008
 */
@SuppressWarnings("serial")
public class Ventana extends JPanel {
	private static final Color FONDO = Color.WHITE;
	private final JFrame frame;
	private final java.util.List<Elemento> elementos;

	/**
	 * Constructor: prepara una ventana para pintar encima.
	 * 
	 * @param ancho
	 *            numero de pixels en horizontal.
	 * @param alto
	 *            numero de pixels en vertical.
	 */
	public Ventana(int ancho, int alto) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = frame.getContentPane();

		container.add(this);
		setPreferredSize(new Dimension(ancho, alto));
		frame.pack();
		elementos = new ArrayList<Elemento>();
	}

	/**
	 * @return altura de la pantalla en este momento.
	 */
	public int getAlto() {
		return frame.getContentPane().getHeight();
	}

	/**
	 * @return anchura de la pantalla en este momento.
	 */
	public int getAncho() {
		return frame.getContentPane().getWidth();
	}

	/**
	 * Añade algo que pintar.
	 * 
	 * @param elemento
	 *            a incorporar al dibujo.
	 */
	private void add(Elemento elemento) {
		synchronized (elementos) {
			elementos.add(elemento);
		}
	}

	/**
	 * Elimina todo lo que hay pintado: ventana en blanco.
	 */
	public void borra() {
		synchronized (elementos) {
			elementos.clear();
		}
	}

	/**
	 * Elimina un objeto concreto de la pantalla.
	 * 
	 * @param obj
	 *            objeto que queremos eliminar.
	 */
	public void borra(Object obj) {
		synchronized (elementos) {
			elementos.remove(obj);
		}
	}

	/**
	 * Dibuja un rectangulo.
	 * 
	 * @param nwx
	 *            coordenada X del extremo superior izquierdo.
	 * @param nwy
	 *            coordenada Y del extremo superior izquierdo.
	 * @param base
	 *            tamano horizontal del rect?ngulo.
	 * @param altura
	 *            tamano vertical del rectangulo.
	 * @param borde
	 *            color del marco.
	 * @param fondo
	 *            color del fondo.
	 * @return objeto que acaba de anadir.
	 */
	public Object rectangulo(int nwx, int nwy, int base, int altura,
			Color borde, Color fondo) {
		Elemento obj = new Rectangulo(nwx, nwy, base, altura, borde, fondo);
		add(obj);
		return obj;
	}

	/**
	 * Dibuja una elipse.
	 * 
	 * @param nwx
	 *            coordenada X del extremo superior izquierdo del rectangulo
	 *            circunscrito a la elipse.
	 * @param nwy
	 *            coordenada Y del extremo superior izquierdo del rectangulo
	 *            circunscrito a la elipse.
	 * @param base
	 *            eje horizontal.
	 * @param altura
	 *            eje vertical.
	 * @param borde
	 *            color del marco.
	 * @param fondo
	 *            color del fondo.
	 * @return objeto que acaba de anadir.
	 */
	public Object elipse(int nwx, int nwy, int base, int altura, Color borde,
			Color fondo) {
		Elemento obj = new Elipse(nwx, nwy, base, altura, borde, fondo);
		add(obj);
		return obj;
	}

	/**
	 * Traza una linea.
	 * 
	 * @param x1
	 *            coordenada X del origen.
	 * @param y1
	 *            coordenada Y del origen.
	 * @param x2
	 *            coordenada X del destino.
	 * @param y2
	 *            coordenada Y del destino.
	 * @param color
	 *            color de la linea.
	 * @return objeto que acaba de anadir.
	 */
	public Object linea(int x1, int y1, int x2, int y2, Color color) {
		Elemento obj = new Linea(x1, y1, x2, y2, color);
		add(obj);
		return obj;
	}

	/**
	 * Escribe un texto en la ventana.
	 * 
	 * @param texto
	 *            texto a escribir.
	 * @param swx
	 *            coordenada X del extremo inferior izquierdo del rectangulo
	 *            circunscrito al texto.
	 * @param swy
	 *            coordenada Y del extremo inferior izquierdo del rectangulo
	 *            circunscrito al texto.
	 * @return objeto que acaba de anadir.
	 */
	public Object texto(String texto, int swx, int swy) {
		Elemento obj = new Texto(texto, swx, swy);
		add(obj);
		return obj;
	}

	/**
	 * Imprime una imagen en la ventana.
	 * 
	 * @param image
	 *            imagen a imprimir.
	 * @param nwx
	 *            coordenada X del extremo superior izquierdo del rectangulo
	 *            circunscrito a la imagen.
	 * @param nwy
	 *            coordenada Y del extremo superior izquierdo del rectangulo
	 *            circunscrito a la imagen.
	 * @param s
	 *            escala a la que se imprime.
	 * @return objeto que acaba de anadir.
	 */
	public Object imagen(Image image, int nwx, int nwy, double s) {
		Elemento obj = new Imagen(image, nwx, nwy, s);
		add(obj);
		return obj;
	}

	/**
	 * Provoca que los elementos de la ventana se plasmen en la pantalla. Si no
	 * se pinta, la ventana se limita a ir apuntando cosas a pintar.
	 */
	public void pinta() {
		frame.repaint();
		frame.setVisible(true);
	}

	public void paint(Graphics g) {
		synchronized (elementos) {
			Graphics2D g2 = (Graphics2D) g;

			g.setColor(FONDO);
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
			for (Elemento elemento : elementos) {
				elemento.pinta(g2);
			}
		}
	}

	/**
	 * Cosas que podemos pintar en la ventana.
	 */
	private interface Elemento {
		public void pinta(Graphics2D g);
	}

	private class Rectangulo implements Elemento {
		private final Shape shape;
		private final Color borde;
		private final Color fondo;

		Rectangulo(int nwx, int nwy, int base, int altura, Color borde,
				Color fondo) {
			this.shape = new Rectangle2D.Double(nwx, nwy, base, altura);
			this.borde = borde;
			this.fondo = fondo;
		}

		public void pinta(Graphics2D g) {
			if (fondo != null) {
				g.setColor(fondo);
				g.fill(shape);
			}
			if (borde != null) {
				g.setColor(borde);
				g.draw(shape);
			}
		}
	}

	private class Elipse implements Elemento {
		private final Shape shape;
		private final Color borde;
		private final Color fondo;

		Elipse(int nwx, int nwy, int base, int altura, Color borde, Color fondo) {
			this.shape = new Ellipse2D.Double(nwx, nwy, base, altura);
			this.borde = borde;
			this.fondo = fondo;
		}

		public void pinta(Graphics2D g) {
			if (fondo != null) {
				g.setColor(fondo);
				g.fill(shape);
			}
			if (borde != null) {
				g.setColor(borde);
				g.draw(shape);
			}
		}
	}

	private class Linea implements Elemento {
		private final Shape shape;
		private final Color color;

		Linea(int x1, int y1, int x2, int y2, Color color) {
			this.shape = new Line2D.Double(x1, y1, x2, y2);
			this.color = color;
		}

		public void pinta(Graphics2D g) {
			if (color != null) {
				g.setColor(color);
				g.draw(shape);
			}
		}
	}

	private class Texto implements Elemento {
		private final String texto;
		private final int x;
		private final int y;

		Texto(String texto, int x, int y) {
			this.texto = texto;
			this.x = x;
			this.y = y;
		}

		public void pinta(Graphics2D g) {
			g.setColor(Color.BLACK);
			g.drawString(texto, x, y);
		}
	}

	private class Imagen implements Elemento {
		private final Image image;
		private AffineTransform at;

		Imagen(Image image, int x, int y, double s) {
			this.image = image;
			at = new AffineTransform(s, 0, 0, s, x, y);
		}

		public void pinta(Graphics2D g) {
			g.drawImage(image, at, null);
		}
	}
}
