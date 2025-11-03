package ar.edu.unlam.reservaanimales.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import ar.edu.unlam.reservaanimales.constante.MensajeError;

public class EscritorArchivo {

	private EscritorArchivo() {
		// Evita instanciación
	}

	/**
	 * Escribe una lista de líneas en un archivo de texto. Si el archivo no existe,
	 * se crea. Si existe, se sobrescribe.
	 *
	 * @param rutaArchivo Ruta del archivo (relativa o absoluta)
	 * @param lineas      Lista de líneas a escribir
	 * @throws IOException              Si ocurre un error de escritura
	 * @throws IllegalArgumentException Si la ruta o las líneas son inválidas
	 */
	public static void escribir(String rutaArchivo, List<String> lineas) throws IOException {
		if (rutaArchivo == null || rutaArchivo.trim().isEmpty()) {
			throw new IllegalArgumentException(MensajeError.RUTA_NULA_O_VACIA);
		}

		if (lineas == null || lineas.isEmpty()) {
			throw new IllegalArgumentException(MensajeError.LISTA_LINEAS_VACIA);
		}

		Path ruta = Paths.get(rutaArchivo).toAbsolutePath();
		Path directorioPadre = ruta.getParent();

		// Validar existencia del directorio
		if (directorioPadre != null && !Files.exists(directorioPadre)) {
			throw new IllegalArgumentException(MensajeError.RUTA_INEXISTENTE + "\n Directorio: " + directorioPadre);
		}

		// Crear archivo si no existe
		if (!Files.exists(ruta)) {
			Files.createFile(ruta);
		}

		// Escribir el contenido (sobrescribe)
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta.toFile(), false))) {
			for (String linea : lineas) {
				escritor.write(linea);
				escritor.newLine();
			}
		}
	}
}
