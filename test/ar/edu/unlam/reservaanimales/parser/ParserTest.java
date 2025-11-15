package ar.edu.unlam.reservaanimales.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ParserTest {

	@Test
	void testParsearConDatosValidos() {
		List<String> lineas = List.of("10 3", "1 2", "2 3", "3 4");

		ResultadoParseo r = Parser.parsear(lineas);

		assertEquals(10, r.getCantidadDeMiradores());
		assertEquals(3, r.getCantidadDeTramos());
		assertArrayEquals(new int[] { 1, 2 }, r.getTramos()[0]);
		assertArrayEquals(new int[] { 2, 3 }, r.getTramos()[1]);
		assertArrayEquals(new int[] { 3, 4 }, r.getTramos()[2]);
	}

	@Test
	void testParsearConUnSoloTramo() {
		List<String> lineas = List.of("5 1", "7 9");

		ResultadoParseo r = Parser.parsear(lineas);

		assertEquals(5, r.getCantidadDeMiradores());
		assertEquals(1, r.getCantidadDeTramos());
		assertArrayEquals(new int[] { 7, 9 }, r.getTramos()[0]);
	}

	@Test
	void testParsearCantidadExactaDeLineas() {
		List<String> lineas = List.of("3 2", "1 2", "2 3");

		ResultadoParseo r = Parser.parsear(lineas);

		assertEquals(3, r.getCantidadDeMiradores());
		assertEquals(2, r.getCantidadDeTramos());
		assertEquals(2, r.getTramos().length);
	}

	@Test
	void testParsearListaVaciaLanzaExcepcion() {
		List<String> lineas = List.of();

		assertThrows(IndexOutOfBoundsException.class, () -> Parser.parsear(lineas));
	}

	@Test
	void testParsearPrimeraLineaInvalida() {
		List<String> lineas = List.of("abc xyz", "1 2");

		assertThrows(NumberFormatException.class, () -> Parser.parsear(lineas));
	}

	@Test
	void testParsearTramoInvalido() {
		List<String> lineas = List.of("4 2", "1 2", "x y");

		assertThrows(NumberFormatException.class, () -> Parser.parsear(lineas));
	}
}
