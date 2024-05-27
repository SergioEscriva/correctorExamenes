package correctorExamenes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Busqueda {

	public void busquedaLetras(List<Par> coordenadas) {
		List<Par> listaFinal = new ArrayList<>();
		Map<Integer, String> listaNumeros = new HashMap<>();
		for (int i = 1; i <= 40; i++) {
			listaNumeros.put(i, "Empty");
		}

		// Extrae las filas y le pone la letra según la distacia en x
		for (Par fila : coordenadas) {
			String filaString = fila.toString();
			double stringX = fila.getNumeroX(); // x izquierda a derecha
			double stringY = fila.getNumeroY(); // y arriba a bajo

			// Primera Fila
			int intB = 235; // 235 -- 235
			int intC = 300; // 330 -- 300
			int intD = 360; // 390 --360

			int entreColumnas = 485; // entre columnas de a - a 485

			String letra = "A";
			if (stringX < intB) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta, "A");

			} else if (stringX > intB && stringX < intC) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta, "B");
				listaFinal.add(fila);

			} else if (stringX > intC && stringX < intD) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta, "C");
				listaFinal.add(fila);

			} else if (stringX > intD && stringX < 400) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta, "D");
				listaFinal.add(fila);

			} // else {
				// System.out.println("Error de lectura " + fila);
				// }

			if (stringX < (intB + entreColumnas) && stringX > 400) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta + 10, "A");
				listaFinal.add(fila);

			} else if (stringX > (intB + entreColumnas) && stringX < (intC + entreColumnas)) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta + 10, "B");
				listaFinal.add(fila);

			} else if (stringX > (intC + entreColumnas) && stringX < (intD + entreColumnas)) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta + 10, "C");
				listaFinal.add(fila);
			} else if (stringX > (intD + entreColumnas) && stringX < 900) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta + 10, "D");
				listaFinal.add(fila);
			}

			int terceraColumna = (entreColumnas * 2);

			if (stringX < (intB + terceraColumna) && stringX > 900) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta + 20, "A");
				listaFinal.add(fila);
			} else if (stringX > (intB + terceraColumna) && stringX < (intC + terceraColumna)) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta + 20, "B");
				listaFinal.add(fila);
			} else if (stringX > (intC + terceraColumna) && stringX < (intD + terceraColumna)) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta + 20, "C");
				listaFinal.add(fila);
			} else if (stringX > (intD + terceraColumna) && stringX < 1385) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta + 20, "D");
				listaFinal.add(fila);
			}

			int cuartaColumna = (entreColumnas * 3);

			if (stringX < (intB + cuartaColumna) && stringX > 1395) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta + 30, "A");
				letra = "A";
			} else if (stringX > (intB + cuartaColumna) && stringX < (intC + cuartaColumna)) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta + 30, "B");
				letra = "B";
			} else if (stringX > (intC + cuartaColumna) && stringX < (intD + cuartaColumna)) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta + 30, "C");
				letra = "C";
			} else if (stringX > (intD + cuartaColumna)) {
				int numeroPregunta = numeroPregunta(fila);
				listaNumeros.put(numeroPregunta + 30, "D");
			}

		}

		System.out.println(listaNumeros);

	}

	public Integer numeroPregunta(Par fila) {
		int horquillaSize = 90;
		int horquillaInicial = 250;

		double numero = fila.getNumeroY();
		int horquilla = (int) Math.ceil((numero - horquillaInicial) / horquillaSize) + 1;
		return horquilla;
	}

}
