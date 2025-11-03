package ar.edu.unlam.reservaanimales;

import java.io.IOException;
import java.util.List;

import ar.edu.unlam.reservaanimales.io.LectorArchivo;
import ar.edu.unlam.reservaanimales.io.EscritorArchivo;

public class ReservaDeAnimales {

	public static void main(String[] args) {
		String rutaArchivoEntrada = "./io/reserva.in";
		String rutaArchivoSalida = "./io/reserva.out";

		try {
			// Leer archivo original
			List<String> lineas = LectorArchivo.leer(rutaArchivoEntrada);

			System.out.println("Archivo leído correctamente.\n");
			System.out.println("Contenido original:");
			lineas.forEach(System.out::println);

			// Agregar nuevos registros
			lineas.add("10 10");
			lineas.add("11 12");
			lineas.add("13 14");

			// Escribir archivo actualizado
			EscritorArchivo.escribir(rutaArchivoSalida, lineas);

			System.out.println("\nNuevos registros agregados y archivo actualizado.");

		} catch (IllegalArgumentException e) {
			System.err.println("Error de validación: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error de lectura/escritura: " + e.getMessage());
		}
	}
}
