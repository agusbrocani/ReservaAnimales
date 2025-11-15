package ar.edu.unlam.reservaanimales.constante;

public class MensajeError {
	// LecturaArchivo
	public static final String ARCHIVO_VACIO = "El archivo está vacío.";

	// EscritorArchivo
	public static final String LISTA_LINEAS_VACIA = "La lista de líneas a escribir no puede ser nula ni vacía.";

	// PathUtils
	public static final String RUTA_NULA_O_VACIA = "La ruta del archivo no puede ser nula ni vacía.";
	public static final String RUTA_INEXISTENTE = "La ruta indicada no existe en el sistema de archivos o no es accesible.";

	// ContarCaminosDAG
	public static final String GRAFO_CON_CICLOS = "El grafo contiene ciclos y no es un DAG.";
	
	private MensajeError() {
		// Evita instanciación
	}
}
