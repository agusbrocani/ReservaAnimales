package ar.edu.unlam.reservaanimales;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.reservaanimales.io.*;
import ar.edu.unlam.reservaanimales.parser.*;
import ar.edu.unlam.reservaanimales.algoritmo.ContarCaminosDAG;

public class ReservaDeAnimales {

	public static void main(String[] args) {

//		Prueba con archivo de 100.000 nodos y 1.000.000 de aristas
//		final String rutaArchivoEntrada = "./io/reserva-mega.in";
		final String rutaArchivoEntrada = "./io/reserva.in";
		final String rutaArchivoSalida = "./io/reserva.out";

		try {
			// Leer archivo
			List<String> lineas = LectorArchivo.leer(rutaArchivoEntrada);
			System.out.println("Contenido de " + "'" + rutaArchivoEntrada + "':");
			lineas.forEach(System.out::println);

			// Parsear archivo -> obtener miradores, tramos y matriz
			ResultadoParseo datos = Parser.parsear(lineas);
			int cantidadDeMiradores = datos.getCantidadDeMiradores();
			int tramos[][] = datos.getTramos();

			// Resolver el problema
			int entrada = 1;
			int salida = cantidadDeMiradores;

			ContarCaminosDAG resolutor = new ContarCaminosDAG(cantidadDeMiradores, tramos);
			long resultado = resolutor.contarCaminos(entrada, salida);

			// Grabar resultado
			List<String> salidaArchivo = new ArrayList<>();
			salidaArchivo.add(String.valueOf(resultado));

			EscritorArchivo.escribir(rutaArchivoSalida, salidaArchivo);

			System.out.println("Resultado escrito en " + "'" + rutaArchivoSalida + "'");
			System.out.println("Cantidad de caminos: " + resultado);

		} catch (IllegalArgumentException e) {
			System.err.println("Error de validación: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error de lectura/escritura: " + e.getMessage());
		}
	}
}
