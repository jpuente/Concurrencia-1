package es.upm.dit.concurrencia.threads;

import java.awt.Toolkit;
import java.util.Scanner;

/**
 * Emite un sonido al pulsar la tecla intro
 * 
 * @author jpuente
 * @version 2013-02-28
 *
 */public class Sonido extends Thread {

	 @Override
	 public void run() {
		 Scanner sc = new Scanner(System.in);
		 while(true) {
			 sc.nextLine();
			 Toolkit.getDefaultToolkit().beep(); 
		 }
	 }
 }
