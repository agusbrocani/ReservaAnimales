package ar.edu.unlam.reservaanimales.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ar.edu.unlam.reservaanimales.constante.MensajeError;

class LectorArchivoTest {

	@Test
	@DisplayName("Debe leer correctamente un archivo con contenido válido")
	void testLeerArchivoConContenido() throws IOException {
		// Crear archivo temporal con contenido válido
		Path archivoTemporal = Files.createTempFile("entrada", ".in");
		Files.write(archivoTemporal, List.of("10 5", "1 2", "2 3", "3 4"));

		// Ejecutar lectura
		List<String> lineas = LectorArchivo.leer(archivoTemporal.toString());

		// Validar resultado
		assertEquals(4, lineas.size(), "Debe tener 4 líneas (1 cabecera + 3 aristas)");
		assertEquals("1 2", lineas.get(1));

		// Limpiar archivo temporal
		Files.deleteIfExists(archivoTemporal);
	}

	@Test
	@DisplayName("Debe lanzar excepción si el archivo no existe")
	void testArchivoInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			LectorArchivo.leer("ruta/que/no/existe.txt");
		});
	}

	@Test
	@DisplayName("Debe lanzar excepción si el archivo está vacío")
	void testArchivoVacio() throws IOException {
		// Crear archivo vacío
		Path archivoTemporal = Files.createTempFile("vacio", ".in");

		Exception ex = assertThrows(IOException.class, () -> {
			LectorArchivo.leer(archivoTemporal.toString());
		});

		assertTrue(ex.getMessage().contains(MensajeError.ARCHIVO_VACIO));

		Files.deleteIfExists(archivoTemporal);
	}

	@Test
	@DisplayName("Debe ignorar líneas vacías o con espacios en blanco")
	void testIgnoraLineasVacias() throws IOException {
		// Archivo con líneas vacías y válidas
		Path archivoTemporal = Files.createTempFile("blanco", ".in");
		Files.write(archivoTemporal, List.of("10 5", "  ", "1 2", "", "3 4   "));

		List<String> lineas = LectorArchivo.leer(archivoTemporal.toString());

		assertEquals(3, lineas.size(), "Debe ignorar líneas vacías");
		assertFalse(lineas.contains(""));

		Files.deleteIfExists(archivoTemporal);
	}

	@Test
	@DisplayName("Debe procesar correctamente líneas vacías y no vacías en el mismo archivo")
	void testArchivoMixtoParaCoberturaCompleta() throws IOException {
		// Cubrir ambas ramas del if interno
		Path archivoTemporal = Files.createTempFile("mixto", ".in");
		Files.write(archivoTemporal, List.of("1 2", "   ", "3 4"));

		List<String> lineas = LectorArchivo.leer(archivoTemporal.toString());

		assertEquals(2, lineas.size(), "Debe ignorar las vacías y conservar las válidas");
		assertEquals("1 2", lineas.get(0));
		assertEquals("3 4", lineas.get(1));

		Files.deleteIfExists(archivoTemporal);
	}

	@Test
	@DisplayName("Debe poder instanciar LectorArchivo (para cobertura completa)")
	void testInstanciacion() {
		new LectorArchivo(); // cubrir constructor implícito
	}
}
