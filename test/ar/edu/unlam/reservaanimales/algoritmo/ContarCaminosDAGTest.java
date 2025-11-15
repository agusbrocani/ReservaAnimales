package ar.edu.unlam.reservaanimales.algoritmo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

class ContarCaminosDAGTest {

	@Test
	void testGrafoSinTramos() {
		int[][] tramos = {};
		ContarCaminosDAG dag = new ContarCaminosDAG(5, tramos);

		assertEquals(0, dag.contarCaminos(1, 5));
		assertEquals(1, dag.contarCaminos(3, 3));
	}

	@Test
	void testUnCaminoDirecto() {
		int[][] tramos = { { 1, 2 } };
		ContarCaminosDAG dag = new ContarCaminosDAG(2, tramos);

		assertEquals(1, dag.contarCaminos(1, 2));
		assertEquals(0, dag.contarCaminos(2, 1));
	}

	@Test
	void testMultiplesCaminos() {
		int[][] tramos = { { 1, 2 }, { 2, 4 }, { 1, 3 }, { 3, 4 } };

		ContarCaminosDAG dag = new ContarCaminosDAG(4, tramos);
		assertEquals(2, dag.contarCaminos(1, 4));
	}

	@Test
	void testModuloAplicado() {
		int[][] tramos = { { 1, 3 }, { 2, 3 } };
		ContarCaminosDAG dag = new ContarCaminosDAG(3, tramos);

		assertEquals(1, dag.contarCaminos(1, 3));
	}

	@Test
	void testOrdenTopologicoCorrecto() throws Exception {
		int[][] tramos = { { 1, 2 }, { 2, 3 } };

		ContarCaminosDAG dag = new ContarCaminosDAG(3, tramos);

		Map<Integer, Integer> grados = new HashMap<>();
		grados.put(1, 0);
		grados.put(2, 1);
		grados.put(3, 1);

		var metodo = ContarCaminosDAG.class.getDeclaredMethod("ordenarTopologicamente", Map.class);
		metodo.setAccessible(true);

		@SuppressWarnings("unchecked")
		List<Integer> orden = (List<Integer>) metodo.invoke(dag, grados);

		assertEquals(Arrays.asList(1, 2, 3), orden);
	}

	@Test
	void testDetectarCiclo() {
		int[][] tramos = { { 1, 2 }, { 2, 3 }, { 3, 1 } };

		ContarCaminosDAG dag = new ContarCaminosDAG(3, tramos);

		assertThrows(IllegalStateException.class, () -> dag.contarCaminos(1, 3));
	}

	@Test
	void testVerticesAislados() {
		int[][] tramos = { { 1, 2 } };

		ContarCaminosDAG dag = new ContarCaminosDAG(5, tramos);

		assertEquals(0, dag.contarCaminos(3, 5));
		assertEquals(1, dag.contarCaminos(4, 4));
	}

	@Test
	void testCaminoLargoUnico() {
		int[][] tramos = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };

		ContarCaminosDAG dag = new ContarCaminosDAG(5, tramos);

		assertEquals(1, dag.contarCaminos(1, 5));
	}

	@Test
	void testSinCamino() {
		int[][] tramos = { { 1, 2 } };

		ContarCaminosDAG dag = new ContarCaminosDAG(5, tramos);

		assertEquals(0, dag.contarCaminos(1, 5));
	}
}
