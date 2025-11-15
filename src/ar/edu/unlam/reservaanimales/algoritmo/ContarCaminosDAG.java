package ar.edu.unlam.reservaanimales.algoritmo;

import java.util.*;

import ar.edu.unlam.reservaanimales.constante.MensajeError;

public class ContarCaminosDAG {

	private static final int MOD = 1_000_000;
	private final Map<Integer, List<Integer>> grafo;

	public ContarCaminosDAG(int cantidadDeVertices, int tramos[][]) {
		this.grafo = new HashMap<>();

		for (int i = 1; i <= cantidadDeVertices; i++) {
			grafo.put(i, new ArrayList<>());
		}

		for (int tramo[] : tramos) {
			int a = tramo[0];
			int b = tramo[1];
			grafo.get(a).add(b);
		}
	}

	// ====================================================================================
	// MÉTODO PÚBLICO: calcula caminos desde inicio hasta fin
	// ====================================================================================
	public long contarCaminos(int inicio, int fin) {
		Map<Integer, Integer> gradosDeEntrada = calcularGradosDeEntrada();

		List<Integer> ordenTopologico = ordenarTopologicamente(gradosDeEntrada);

		Map<Integer, Long> dp = inicializarDP(fin);

		return resolverDP(ordenTopologico, dp, inicio);
	}

	// ====================================================================================
	// FASE 1: CALCULAR GRADO DE ENTRADA DE CADA NODO
	// ====================================================================================
	private Map<Integer, Integer> calcularGradosDeEntrada() {
		Map<Integer, Integer> gradosDeEntrada = new HashMap<Integer, Integer>();

		Set<Integer> verticesTotalesDelGrafo = grafo.keySet();
		for (Integer verticeAnalizado : verticesTotalesDelGrafo) {
			gradosDeEntrada.putIfAbsent(verticeAnalizado, 0);

			List<Integer> verticesConectadosAlAnalizado = grafo.get(verticeAnalizado);
			for (Integer vertice : verticesConectadosAlAnalizado) {
				gradosDeEntrada.put(vertice, gradosDeEntrada.getOrDefault(vertice, 0) + 1);
			}
		}
		return gradosDeEntrada;
	}

	// ====================================================================================
	// FASE 2: ORDEN TOPOLOGICO CON KAHN
	// ====================================================================================
	private List<Integer> ordenarTopologicamente(Map<Integer, Integer> gradosDeEntrada) {

		// Cola de vértices listos para procesar (los que tienen grado de entrada 0)
		Queue<Integer> colaDeVerticesSinDependencias = new ArrayDeque<>();

		// Agrego todas las entradas iniciales (vértices sin dependencias)
		Set<Integer> verticesTotalesDelGrafo = grafo.keySet();
		for (Integer vertice : verticesTotalesDelGrafo) {
			if (gradosDeEntrada.getOrDefault(vertice, 0) == 0) {
				colaDeVerticesSinDependencias.add(vertice);
			}
		}

		List<Integer> verticesOrdenadosTopologicamente = new ArrayList<>();

		// Algoritmo de Kahn
		while (!colaDeVerticesSinDependencias.isEmpty()) {

			// Tomo un vértice sin dependencias pendientes
			int verticeActual = colaDeVerticesSinDependencias.poll();
			verticesOrdenadosTopologicamente.add(verticeActual);

			// Recorro todos los vértices a los que 'verticeActual' apunta
			List<Integer> verticesAdyacentes = grafo.getOrDefault(verticeActual, Collections.emptyList());

			for (Integer verticeDestino : verticesAdyacentes) {

				// Reduzco el grado de entrada porque estoy procesando a su predecesor
				int gradoActualizado = gradosDeEntrada.get(verticeDestino) - 1;
				gradosDeEntrada.put(verticeDestino, gradoActualizado);

				// Si ahora quedó sin dependencias, se agrega a la cola
				if (gradoActualizado == 0) {
					colaDeVerticesSinDependencias.add(verticeDestino);
				}
			}
		}

		// Detectar ciclos en DAG
		if (verticesOrdenadosTopologicamente.size() != grafo.size()) {
			throw new IllegalStateException(MensajeError.GRAFO_CON_CICLOS);
		}

		return verticesOrdenadosTopologicamente;
	}

	// ====================================================================================
	// FASE 3: INICIALIZAR DP
	// ====================================================================================
	private Map<Integer, Long> inicializarDP(int salida) {
		Map<Integer, Long> formasDeLlegarDP = new HashMap<>();

		Set<Integer> verticesTotalesDelGrafo = grafo.keySet();
		for (Integer vertice : verticesTotalesDelGrafo) {
			formasDeLlegarDP.put(vertice, 0L);
		}

		formasDeLlegarDP.put(salida, 1L);
		return formasDeLlegarDP;
	}

	// ====================================================================================
	// FASE 4: RESOLVER DP INVERTIDA - BOTTOM UP
	// ====================================================================================
	private long resolverDP(List<Integer> ordenTopologico, Map<Integer, Long> formasDeLlegarDP, int entrada) {

		Collections.reverse(ordenTopologico);

		for (Integer verticeAnalizado : ordenTopologico) {
			List<Integer> verticesAdyacentes = grafo.getOrDefault(verticeAnalizado, Collections.emptyList());
			for (Integer vertice : verticesAdyacentes) {
				long nuevo = (formasDeLlegarDP.get(verticeAnalizado) + formasDeLlegarDP.get(vertice)) % MOD;
				formasDeLlegarDP.put(verticeAnalizado, nuevo);
			}
		}

		return formasDeLlegarDP.getOrDefault(entrada, 0L);
	}
}
