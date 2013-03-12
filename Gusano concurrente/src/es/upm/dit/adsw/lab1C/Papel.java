package es.upm.dit.adsw.lab1C;

import java.awt.*;

/**
 * Recubre la clase Ventana para trabajar sobre las dimensiones que quiera el
 * usuario. El usuario establece minimos y maximos en X e Y, calculandose el
 * centro (x0, y0) y la escala necesaria (escalaX, escalaY).
 * 
 * @author Jose A. Manas
 * @version 25.11.2009
 */
public class Papel {
	private final Ventana ventana;
	private final double escalaX;
	private final double escalaY;

	// origen
	private final double x0;
	private final double y0;

	// color de las lineas
	private Color color;

	/**
	 * Constructor.
	 * 
	 * @param ventana
	 *            sobre la que se pinta.
	 * @param x_min
	 *            valor de X en el extremo izquierdo.
	 * @param x_max
	 *            valor de X en el extremo derecho.
	 * @param y_min
	 *            valor de Y en el borde inferior.
	 * @param y_max
	 *            valor de Y en el borde superior.
	 */
	public Papel(Ventana ventana, double x_min, double x_max, double y_min,
			double y_max) {
		this.ventana = ventana;
		double ancho = ventana.getAncho();
		double alto = ventana.getAlto();
		escalaX = ancho / (x_max - x_min);
		escalaY = alto / (y_max - y_min);
		x0 = -x_min * escalaX;
		y0 = y_max * escalaY;
		color = Color.BLACK;
	}

	/**
	 * Conversion de coordenadas en el eje X.
	 * 
	 * @param x
	 *            valor del usuario.
	 * @return posicion en pixels.
	 */
	private int px(double x) {
		return (int) (x0 + (escalaX * x));
	}

	/**
	 * Conversion de coordenadas en el eje Y.
	 * 
	 * @param y
	 *            valor del usuario.
	 * @return posicion en pixels.
	 */
	private int py(double y) {
		return (int) (y0 - (escalaY * y));
	}

	/**
	 * Setter.
	 * 
	 * @param color
	 *            marca el color de las lineas que se pinten.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Pinta el eje X, de un lado al otro de la ventana.
	 * 
	 * @return el objeto dibujado, para poder eliminarlo si hiciera falta.
	 */
	public Object ejeX() {
		int x1 = 0;
		int y1 = py(0);
		int x2 = ventana.getAncho();
		int y2 = y1;
		return ventana.linea(x1, y1, x2, y2, color);
	}

	/**
	 * Pinta el eje Y, de arriba a abajo de la ventana.
	 * 
	 * @return el objeto dibujado, para poder eliminarlo si hiciera falta.
	 */
	public Object ejeY() {
		int x1 = px(0);
		int y1 = 0;
		int x2 = x1;
		int y2 = ventana.getAlto();
		return ventana.linea(x1, y1, x2, y2, color);
	}

	/**
	 * Traza una linea.
	 * 
	 * @param x1
	 *            abscisa del punto de salida.
	 * @param y1
	 *            ordenada del punto de salida.
	 * @param x2
	 *            abscisa del punto de llegada.
	 * @param y2
	 *            ordenada del punto de llegada.
	 * @return el objeto dibujado, para poder eliminarlo si hiciera falta.
	 */
	public Object linea(double x1, double y1, double x2, double y2) {
		if (color != null) {
			return ventana.linea(px(x1), py(y1), px(x2), py(y2), color);
		}
		return null;
	}

	/**
	 * Pinta un punto.
	 * 
	 * @param cx
	 *            coordenada X del centro.
	 * @param cy
	 *            coordenada Y del centro.
	 * @param diametro
	 *            en pixels.
	 * @return el objeto dibujado, para poder eliminarlo si hiciera falta.
	 */
	public Object punto(double cx, double cy, int diametro) {
		int nwx = px(cx) - diametro / 2;
		int nwy = py(cy) - diametro / 2;
		return ventana.elipse(nwx, nwy, diametro, diametro, color, color);
	}

	/**
	 * Pinta un rectangulo.
	 * 
	 * @param cx
	 *            coordenada X del centro.
	 * @param cy
	 *            coordenada Y del centro.
	 * @param ancho
	 *            base.
	 * @param alto
	 *            altura.
	 * @param fondo
	 *            color del fondo: relleno.
	 * @return el objeto dibujado, para poder eliminarlo si hiciera falta.
	 */
	public Object rectangulo(double cx, double cy, double ancho, double alto,
			Color fondo) {
		int nwx = px(cx - ancho / 2);
		int nwy = py(cy + alto / 2);
		return ventana.rectangulo(nwx, nwy, (int) (ancho * escalaX),
				(int) (alto * escalaY), color, fondo);
	}

	/**
	 * Pinta una elipse.
	 * 
	 * @param cx
	 *            coordenada X del centro.
	 * @param cy
	 *            coordenada Y del centro.
	 * @param ancho
	 *            base.
	 * @param alto
	 *            altura.
	 * @param fondo
	 *            color del fondo: relleno.
	 * @return el objeto dibujado, para poder eliminarlo si hiciera falta.
	 */
	public Object elipse(double cx, double cy, double ancho, double alto,
			Color fondo) {
		int nwx = px(cx - ancho / 2);
		int nwy = py(cy + alto / 2);
		return ventana.elipse(nwx, nwy, (int) (ancho * escalaX),
				(int) (alto * escalaY), color, fondo);
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
	 * @return el objeto dibujado, para poder eliminarlo si hiciera falta.
	 */
	public Object texto(String texto, double swx, double swy) {
		return ventana.texto(texto, px(swx), py(swy));
	}

	/**
	 * Lleva una imagen a la pantalla.
	 * 
	 * @param image
	 *            imagen a pintar.
	 * @param cx
	 *            centro de la imagen.
	 * @param cy
	 *            centro de la imagen.
	 * @return el objeto dibujado, para poder eliminarlo si hiciera falta.
	 */
	public Object imagen(Image image, double cx, double cy) {
		int ancho = image.getWidth(null);
		int alto = image.getHeight(null);
		int nwx = px(cx) - ancho / 2;
		int nwy = py(cy) - alto / 2;
		return ventana.imagen(image, nwx, nwy, 1.0);
	}

	/**
	 * Para ir viendo: pinta lo que hay ahora mismo.
	 */
	public void pinta() {
		ventana.pinta();
	}

	/**
	 * Elimina todos los objetos de la ventana.
	 */
	public void borra() {
		ventana.borra();
	}

	/**
	 * Elimina un objeto de la ventana.
	 * 
	 * @param object
	 *            objeto a eleiminar.
	 */
	public void borra(Object object) {
		ventana.borra(object);
	}
}

