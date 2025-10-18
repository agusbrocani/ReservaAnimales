package ar.edu.unlam.reservaanimales.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ar.edu.unlam.reservaanimales.constante.MensajeError;

class PathUtilsTest {

    @Test
    @DisplayName("Debe lanzar excepción si la ruta es nula")
    void testRutaNula() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            PathUtils.construirRutaAbsoluta(null);
        });
        assertTrue(ex.getMessage().contains(MensajeError.RUTA_NULA_O_VACIA));
    }

    @Test
    @DisplayName("Debe lanzar excepción si la ruta está vacía o tiene solo espacios")
    void testRutaVacia() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            PathUtils.construirRutaAbsoluta("   ");
        });
        assertTrue(ex.getMessage().contains(MensajeError.RUTA_NULA_O_VACIA));
    }

    @Test
    @DisplayName("Debe lanzar excepción si el archivo no existe")
    void testRutaInexistente() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            PathUtils.construirRutaAbsoluta("ruta/falsa/inexistente.txt");
        });
        assertTrue(ex.getMessage().contains(MensajeError.RUTA_INEXISTENTE));
    }

    @Test
    @DisplayName("Debe devolver ruta absoluta válida cuando el archivo existe")
    void testRutaValida() throws IOException {
        Path archivoTemporal = Files.createTempFile("archivoExistente", ".in");
        String ruta = PathUtils.construirRutaAbsoluta(archivoTemporal.toString());

        assertTrue(ruta.endsWith(".in"), "Debe devolver una ruta válida y existente");

        Files.deleteIfExists(archivoTemporal);
    }

    @Test
    @DisplayName("El constructor de PathUtils debe ser privado y ejecutable mediante reflexión")
    void testConstructorPrivado() throws Exception {
        Constructor<PathUtils> constructor = PathUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance(); // ejecuta el constructor para cobertura total
    }
}
