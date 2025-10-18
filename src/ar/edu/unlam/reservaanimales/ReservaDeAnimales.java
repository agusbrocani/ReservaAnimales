package ar.edu.unlam.reservaanimales;

import java.io.IOException;
import java.util.List;

import ar.edu.unlam.reservaanimales.io.LectorArchivo;

public class ReservaDeAnimales {

	public static void main(String[] args) {
		// Eclipse ejecuta el programa con el directorio de trabajo en la raíz del
		// proyecto.
		// Por eso "./" hace referencia a la carpeta ReservaAnimales (no a src/ ni a
		// bin/).
		
		String rutaArchivo = "./io/reserva.in";
//    	String rutaArchivo = "src/test/resources/reserva.txt";

		try {
			List<String> lineas = LectorArchivo.leerArchivo(rutaArchivo);

			System.out.println("Archivo leído correctamente.\n");
			System.out.println("Contenido del archivo:");
			lineas.forEach(System.out::println);

		} catch (IllegalArgumentException e) {
			// Errores de validación de ruta (nula, inexistente, etc.)
			System.err.println("Error de validación: " + e.getMessage());

		} catch (IOException e) {
			// Errores al leer el archivo (problemas de permisos, E/S, etc.)
			System.err.println("Error de lectura: " + e.getMessage());
		}
	}
}
