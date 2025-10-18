package ar.edu.unlam.reservaanimales.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ar.edu.unlam.reservaanimales.constante.MensajeError;

class EscritorArchivoTest {

	@Test
	@DisplayName("Debe escribir correctamente las líneas en un archivo existente")
	void testEscrituraExitosa() throws IOException {
		Path archivoTemporal = Files.createTempFile("escritor", ".in");
		List<String> contenido = List.of("Linea 1", "Linea 2", "Linea 3");

		EscritorArchivo.escribir(archivoTemporal.toString(), contenido);

		List<String> resultado = Files.readAllLines(archivoTemporal);
		assertEquals(contenido, resultado, "El contenido escrito debe coincidir con la lista original");

		Files.deleteIfExists(archivoTemporal);
	}

	@Test
	@DisplayName("Debe crear el archivo si no existe")
	void testCreaArchivoSiNoExiste() throws IOException {
		Path directorio = Files.createTempDirectory("directorioTest");
		Path archivo = directorio.resolve("nuevoArchivo.in");

		List<String> contenido = List.of("A", "B");
		EscritorArchivo.escribir(archivo.toString(), contenido);

		assertTrue(Files.exists(archivo), "El archivo debe haberse creado automáticamente");
		List<String> leido = Files.readAllLines(archivo);
		assertEquals(contenido, leido);

		Files.deleteIfExists(archivo);
		Files.deleteIfExists(directorio);
	}

	@Test
	@DisplayName("Debe lanzar excepción si la ruta es nula o vacía")
	void testRutaNulaOVacia() {
		Exception ex1 = assertThrows(IllegalArgumentException.class,
				() -> EscritorArchivo.escribir(null, List.of("x")));
		assertTrue(ex1.getMessage().contains(MensajeError.RUTA_NULA_O_VACIA));

		Exception ex2 = assertThrows(IllegalArgumentException.class,
				() -> EscritorArchivo.escribir("   ", List.of("x")));
		assertTrue(ex2.getMessage().contains(MensajeError.RUTA_NULA_O_VACIA));
	}

	@Test
	@DisplayName("Debe lanzar excepción si la lista de líneas es nula o vacía")
	void testListaVaciaONula() {
		Exception ex1 = assertThrows(IllegalArgumentException.class,
				() -> EscritorArchivo.escribir("archivo.in", null));
		assertTrue(ex1.getMessage().contains(MensajeError.LISTA_LINEAS_VACIA));

		Exception ex2 = assertThrows(IllegalArgumentException.class,
				() -> EscritorArchivo.escribir("archivo.in", List.of()));
		assertTrue(ex2.getMessage().contains(MensajeError.LISTA_LINEAS_VACIA));
	}

	@Test
	@DisplayName("Debe lanzar excepción si el directorio padre no existe")
	void testDirectorioInexistente() {
		String rutaInvalida = "Z:/carpetaInexistente/archivo.in";
		Exception ex = assertThrows(IllegalArgumentException.class,
				() -> EscritorArchivo.escribir(rutaInvalida, List.of("x")));
		assertTrue(ex.getMessage().contains(MensajeError.RUTA_INEXISTENTE));
	}
}
