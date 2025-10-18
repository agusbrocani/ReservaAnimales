package ar.edu.unlam.reservaanimales.constante;

public class MensajeError {
    // LecturaArchivo
	public static final String ARCHIVO_VACIO =
            "El archivo está vacío.";
    
    // PathUtils
    public static final String RUTA_NULA_O_VACIA = 
    	"La ruta del archivo no puede ser nula ni vacía.";
    public static final String RUTA_INEXISTENTE =
    	    "La ruta indicada no existe en el sistema de archivos o no es accesible.";

    
    private MensajeError() {
        // Evita instanciación
    }
}
