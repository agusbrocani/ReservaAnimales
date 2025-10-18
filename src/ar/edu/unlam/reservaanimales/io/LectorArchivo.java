package ar.edu.unlam.reservaanimales.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.reservaanimales.constante.MensajeError;
import ar.edu.unlam.reservaanimales.util.PathUtils;

public class LectorArchivo {
	/**
	 * Lee todas las líneas de un archivo de texto. Valida y normaliza la ruta antes
	 * de abrirlo.
	 *
	 * @param rutaArchivo Ruta del archivo (relativa o absoluta)
	 * @return Lista con las líneas del archivo
	 * @throws IOException              Si hay error de lectura
	 * @throws IllegalArgumentException Si la ruta es inválida
	 */
	public static List<String> leerArchivo(String rutaArchivo) throws IOException {
		// Validar y construir ruta absoluta
		String rutaAbsoluta = PathUtils.construirRutaAbsoluta(rutaArchivo);

		List<String> lineas = new ArrayList<>();

		// Intentar leer el archivo
		try (BufferedReader lector = new BufferedReader(new FileReader(rutaAbsoluta))) {
			String linea;
			boolean tieneContenido = false;

			while ((linea = lector.readLine()) != null) {
				String lineaLimpia = linea.trim();
				if (!lineaLimpia.isEmpty()) {
					tieneContenido = true;
					lineas.add(lineaLimpia);
				}
			}

			// Si no se encontró ninguna línea con contenido
			if (!tieneContenido) {
				throw new IOException(MensajeError.ARCHIVO_VACIO);
			}
		}

		return lineas;
	}
}
