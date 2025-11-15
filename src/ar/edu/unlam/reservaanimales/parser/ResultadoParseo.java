package ar.edu.unlam.reservaanimales.parser;

public class ResultadoParseo {

	private final int cantidadDeMiradores;
	private final int cantidadDeTramos;
	private final int tramos[][];

	public ResultadoParseo(int cantidadDeMiradores, int cantidadDeTramos, int tramos[][]) {
		this.cantidadDeMiradores = cantidadDeMiradores;
		this.cantidadDeTramos = cantidadDeTramos;
		this.tramos = tramos;
	}

	public int getCantidadDeMiradores() {
		return cantidadDeMiradores;
	}

	public int getCantidadDeTramos() {
		return cantidadDeTramos;
	}

	public int[][] getTramos() {
		return tramos;
	}
}
