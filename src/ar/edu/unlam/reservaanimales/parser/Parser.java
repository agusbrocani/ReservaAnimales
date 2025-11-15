package ar.edu.unlam.reservaanimales.parser;

import java.util.List;

public class Parser {
	private Parser() {
		// Evita instanciación
	}
	
	public static ResultadoParseo parsear(List<String> lineas) {
		
		String primeraLinea[] = lineas.get(0).split(" ");
		int cantidadDeMiradores = Integer.parseInt(primeraLinea[0]);
		int cantidadDeTramos = Integer.parseInt(primeraLinea[1]);

		int tramos[][] = new int[cantidadDeTramos][2];

		for (int i = 1; i <= cantidadDeTramos; i++) {
			String partes[] = lineas.get(i).split(" ");
			tramos[i - 1][0] = Integer.parseInt(partes[0]);
			tramos[i - 1][1] = Integer.parseInt(partes[1]);
		}

		return new ResultadoParseo(cantidadDeMiradores, cantidadDeTramos, tramos);
	}
}
