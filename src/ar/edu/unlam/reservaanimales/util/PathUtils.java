package ar.edu.unlam.reservaanimales.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ar.edu.unlam.reservaanimales.constante.MensajeError;

public class PathUtils {

	private PathUtils() {
		// Evita instanciación
	}

	/**
	 * Convierte una ruta relativa o absoluta en una ruta absoluta válida y verifica
	 * su existencia. Lanza una excepción si la ruta es nula, vacía, inválida o
	 * inexistente.
	 *
	 * @param rutaArchivo Ruta ingresada (relativa o absoluta)
	 * @return Ruta absoluta y normalizada como String
	 * @throws IllegalArgumentException Si la ruta no es válida o no existe
	 */
	public static String construirRutaAbsoluta(String rutaArchivo) {
		if (rutaArchivo == null || rutaArchivo.trim().isEmpty()) {
			throw new IllegalArgumentException(MensajeError.RUTA_NULA_O_VACIA);
		}

		Path ruta = Paths.get(rutaArchivo.trim());

		// Si la ruta es relativa, la convierte a absoluta con base en el directorio
		// actual
		if (!ruta.isAbsolute()) {
			ruta = Paths.get(System.getProperty("user.dir")).resolve(ruta);
		}

		// Normaliza (elimina ./, ../, dobles barras, etc.)
		ruta = ruta.normalize();

		// Verifica existencia (archivo o directorio)
		if (!Files.exists(ruta)) {
			throw new IllegalArgumentException(MensajeError.RUTA_INEXISTENTE + "\n Ruta: " + ruta);
		}

		return ruta.toAbsolutePath().toString();
	}
}
