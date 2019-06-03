package org.caalpeva.report.utils;

import java.io.IOException;
import java.util.List;

/**
 * Clase encargada de proporcionar funcionalidad para interactuar con la consola de usuario  
 * @author Alberto
 */
public class ConsoleUtils {

	/**
	 * Método encargada de formatear de forma sencilla el conteo de pedidos
	 * @param groupedOrders
	 */
	public static void printSumOrderReport(List<Object[]> groupedOrders) {
		long total = 0;
		if (groupedOrders != null && groupedOrders.size() > 0) {
			System.out.println("----------------------");
			for(Object[] group: groupedOrders) {
				total += Long.parseLong(group[1].toString());
				System.out.println(String.format("|%10s | %s", group[1], group[0]));
			} // for
		}
		
		System.out.println("----------------------");
		System.out.println(String.format("|%10s | Order total", total));
		System.out.println("----------------------");
		waitForAnyPressedKeyToContinue();
	}
	
	/**
	 * Método encargado de realizar una pausa en el programa
	 */
	public static void waitForAnyPressedKeyToContinue() {
		System.out.println("\nPress ENTER to continue..");
		try {
			System.in.read();
		} catch (IOException e) {
			// Do nothing
		}
	}
}
