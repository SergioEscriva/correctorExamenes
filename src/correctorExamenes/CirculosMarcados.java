package correctorExamenes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CirculosMarcados {
    private int numero1 = 0;

    public Map<Integer, String> busquedaLetras(List<Par> allCircles, List<Par> whiteCircles, int x, int y) {
	List<Par> listaFinal = new ArrayList<>();

	Map<Integer, String> listaNumeros = new HashMap<>();
	for (int i = 1; i <= 40; i++) {
	    listaNumeros.put(i, "Empty");
	}

//	// Extrae las filas y le pone la letra según la distacia en x
	for (Par fila : whiteCircles) {
	    String filaString = fila.toString();
	    double stringX = fila.getNumeroX(); // x izquierda a derecha

	    /// La X tiene una media de 807
	    // Primera Fila
	    int intA = x - 750;
	    int intB = intA + 130; // intA + 68; // -- - 680
	    int intC = intB + 61; // intB + 62; // -- -65
	    int intD = intC + 60; // intC + 61; // -- 60

	    int entreColumnas = 470; // entre columnas de a - a 485 ESTO es X

	    String letra = "A";
	    if (stringX < intB) {
		int numeroPregunta = numeroPregunta(fila, y);
		if (listaNumeros.get(numeroPregunta).equals("Empty")) {
		    listaNumeros.put(numeroPregunta, "A");

		} else {
		    listaNumeros.put(numeroPregunta, "Nula");
		}
		listaFinal.add(fila);
	    } else if (stringX > intB && stringX < intC) {
		int numeroPregunta = numeroPregunta(fila, y);
		listaNumeros.put(numeroPregunta, "B");
		listaFinal.add(fila);

	    } else if (stringX > intC && stringX < intD) {
		int numeroPregunta = numeroPregunta(fila, y);
		listaNumeros.put(numeroPregunta, "C");
		listaFinal.add(fila);

	    } else if (stringX > intD && stringX < 400) {
		int numeroPregunta = numeroPregunta(fila, y);
		listaNumeros.put(numeroPregunta, "D");
		listaFinal.add(fila);

	    }

	    if (stringX < (intB + entreColumnas) && stringX > 400) {
		int numeroPregunta = numeroPregunta(fila, y);
		if (listaNumeros.get(numeroPregunta + 10).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 10, "A");
		} else {
		    listaNumeros.put(numeroPregunta + 10, "Nula");
		}

		listaFinal.add(fila);

	    } else if (stringX > (intB + entreColumnas) && stringX < (intC + entreColumnas)) {
		int numeroPregunta = numeroPregunta(fila, y);
		if (listaNumeros.get(numeroPregunta + 10).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 10, "B");
		} else {
		    listaNumeros.put(numeroPregunta + 10, "Nula");

		}

		listaFinal.add(fila);

	    } else if (stringX > (intC + entreColumnas) && stringX < (intD + entreColumnas)) {

		int numeroPregunta = numeroPregunta(fila, y);
		if (listaNumeros.get(numeroPregunta + 10).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 10, "C");
		} else {
		    listaNumeros.put(numeroPregunta + 10, "Nula");
		}

		listaFinal.add(fila);

	    } else if (stringX > (intD + entreColumnas) && stringX < 900) {
		int numeroPregunta = numeroPregunta(fila, y);

		if (listaNumeros.get(numeroPregunta + 10).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 10, "D");
		} else {
		    listaNumeros.put(numeroPregunta + 10, "Nula");

		}

		listaFinal.add(fila);

	    }

	    int terceraColumna = (entreColumnas * 2); // 675

	    if (stringX < (intB + terceraColumna) && stringX > 1000) {
		int numeroPregunta = numeroPregunta(fila, y);
		listaNumeros.put(numeroPregunta + 20, "A");
		listaFinal.add(fila);
	    } else if (stringX > (intB + terceraColumna) && stringX < (intC + terceraColumna)) {
		int numeroPregunta = numeroPregunta(fila, y);
		if (listaNumeros.get(numeroPregunta + 20).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 20, "B");
		} else {
		    listaNumeros.put(numeroPregunta + 20, "NulaB");
		}

		listaFinal.add(fila);
	    } else if (stringX > (intC + terceraColumna) && stringX < (intD + terceraColumna)) {

		int numeroPregunta = numeroPregunta(fila, y);
		if (listaNumeros.get(numeroPregunta + 20).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 20, "C");
		} else {
		    listaNumeros.put(numeroPregunta + 20, "NulaC");
		}

		listaFinal.add(fila);

	    } else if (stringX > (intD + terceraColumna) && stringX < 1385) {
		int numeroPregunta = numeroPregunta(fila, y);
		if (listaNumeros.get(numeroPregunta + 20).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 20, "D");
		} else {
		    listaNumeros.put(numeroPregunta + 20, "NulaD");
		}

		listaFinal.add(fila);

	    }

	    int cuartaColumna = (entreColumnas * 3); // +800

	    if (stringX < (intB + cuartaColumna) && stringX > 1400) {
		int numeroPregunta = numeroPregunta(fila, y);
		listaNumeros.put(numeroPregunta + 30, "A");

	    } else if (stringX > (intB + cuartaColumna) && stringX < (intC + cuartaColumna)) {
		int numeroPregunta = numeroPregunta(fila, y);
		listaNumeros.put(numeroPregunta + 30, "B");

	    } else if (stringX > (intC + cuartaColumna) && stringX < (intD + cuartaColumna)) {
		int numeroPregunta = numeroPregunta(fila, y);
		listaNumeros.put(numeroPregunta + 30, "C");

	    } else if (stringX > (intD + cuartaColumna)) {
		int numeroPregunta = numeroPregunta(fila, y);
		listaNumeros.put(numeroPregunta + 30, "D");

	    }

	}

	System.out.println(listaNumeros);

	return listaNumeros;
	// return null;

    }

    public Integer numeroPregunta(Par fila, int y) {

	int horquillaInicial = (y + 155); // Altura de "A" normalmente y+55
	int horquillaSize = 95; // es la media de separación entre filas

	double numero = fila.getNumeroY();
	int horquilla = (int) Math.ceil((numero - horquillaInicial) / horquillaSize) + 1;

	return horquilla;
    }
}
