package correctorExamenes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Busqueda {

    public Map<Integer, String> busquedaLetras(List<Par> allCircles, List<Par> whiteCircles, int x, int y) {
	List<Par> listaFinal = new ArrayList<>();

	for (Par circuloReal : allCircles) {
	    System.out.println("CirculoReal " + circuloReal);
	    for (Par circuloMarcado : whiteCircles) {
		// System.out.println("CirculoMarcado " + circuloMarcado);
		if (circuloReal == circuloMarcado) {

		    // System.out.println("CirculoMarcado " + circuloMarcado);
		}
	    }
	}

	Map<Integer, String> listaNumeros = new HashMap<>();
	for (int i = 1; i <= 40; i++) {
	    listaNumeros.put(i, "Empty");
	}

	// Extrae las filas y le pone la letra según la distacia en x
	for (Par fila : whiteCircles) {
	    String filaString = fila.toString();
	    double stringX = fila.getNumeroX(); // x izquierda a derecha
	    // double stringY = fila.getNumeroY(); // y arriba a bajo

	    /// La X tiene una media de 807
	    // Primera Fila
	    int intA = x - 750;
	    int intB = intA + 130; // intA + 68; // -- - 680
	    int intC = intB + 61; // intB + 62; // -- -65
	    int intD = intC + 60; // intC + 61; // -- 60

	    int entreColumnas = 470; // entre columnas de a - a 485 ESTO es X
	    System.out.println(intA + " < > " + intB + " < > " + intC + " < > " + intD + " < > ");

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

	    } // else {
	      // System.out.println("Error de lectura " + fila);
	      // }

	    if (stringX < (intB + entreColumnas) && stringX > 400) {
		int numeroPregunta = numeroPregunta(fila, y);
		if (listaNumeros.get(numeroPregunta + 10).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 10, "A");
		    System.out.println(fila + " A Segunda empty " + (numeroPregunta + 10));
		} else {
		    listaNumeros.put(numeroPregunta + 10, "Nula");
		    System.out.println(fila + " A Segunda Nula " + (numeroPregunta + 10));
		}

		listaFinal.add(fila);
		System.out.println(fila + " A Segunda " + (numeroPregunta + 10));

	    } else if (stringX > (intB + entreColumnas) && stringX < (intC + entreColumnas)) {
		int numeroPregunta = numeroPregunta(fila, y);
		if (listaNumeros.get(numeroPregunta + 10).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 10, "B");
		    System.out.println(fila + " B Segunda empty " + (numeroPregunta + 10));
		} else {
		    listaNumeros.put(numeroPregunta + 10, "Nula");
		    System.out.println(fila + " B Segunda Nula " + (numeroPregunta + 10));
		}

		listaFinal.add(fila);
		System.out.println(fila + " B Segunda " + (numeroPregunta + 10));

	    } else if (stringX > (intC + entreColumnas) && stringX < (intD + entreColumnas)) {

		int numeroPregunta = numeroPregunta(fila, y);
		if (listaNumeros.get(numeroPregunta + 10).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 10, "C");
		    System.out.println(fila + " C Segunda empty " + (numeroPregunta + 10));
		} else {
		    listaNumeros.put(numeroPregunta + 10, "Nula");
		    System.out.println(fila + " C Segunda Nula " + (numeroPregunta + 10));
		}

		listaFinal.add(fila);
		System.out.println(fila + " C Segunda " + (numeroPregunta + 10));

	    } else if (stringX > (intD + entreColumnas) && stringX < 900) {
		int numeroPregunta = numeroPregunta(fila, y);

		if (listaNumeros.get(numeroPregunta + 10).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 10, "D");
		    System.out.println(fila + "D  Segunda empty " + (numeroPregunta + 10));
		} else {
		    listaNumeros.put(numeroPregunta + 10, "Nula");
		    System.out.println(fila + " D Segunda Nula " + (numeroPregunta + 10));

		}

		listaFinal.add(fila);
		System.out.println(fila + " D Segunda " + (numeroPregunta + 10));

	    }

	    int terceraColumna = (entreColumnas * 2); // 675

	    if (stringX < (intB + terceraColumna) && stringX > 1000) {
		System.out.println((intB + terceraColumna) + " < A Tercera > ");
		int numeroPregunta = numeroPregunta(fila, y);
		listaNumeros.put(numeroPregunta + 20, "A");
		listaFinal.add(fila);
		System.out.println(fila + " A Tercera " + (numeroPregunta + 20));
	    } else if (stringX > (intB + terceraColumna) && stringX < (intC + terceraColumna)) {
		int numeroPregunta = numeroPregunta(fila, y);
		if (listaNumeros.get(numeroPregunta + 20).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 20, "B");
		} else {
		    listaNumeros.put(numeroPregunta + 20, "NulaB");
		}

		listaFinal.add(fila);
		System.out.println(fila + " B Tercera " + (numeroPregunta + 20));
	    } else if (stringX > (intC + terceraColumna) && stringX < (intD + terceraColumna)) {

		int numeroPregunta = numeroPregunta(fila, y);
		if (listaNumeros.get(numeroPregunta + 20).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 20, "C");
		} else {
		    listaNumeros.put(numeroPregunta + 20, "NulaC");
		}

		listaFinal.add(fila);
		System.out.println(fila + " C Tercera " + (numeroPregunta + 20) + " <> menor " + (intC + terceraColumna)
			+ " mayor " + (intD + terceraColumna));
	    } else if (stringX > (intD + terceraColumna) && stringX < 1385) {
		int numeroPregunta = numeroPregunta(fila, y);
		if (listaNumeros.get(numeroPregunta + 20).equals("Empty")) {
		    listaNumeros.put(numeroPregunta + 20, "D");
		} else {
		    listaNumeros.put(numeroPregunta + 20, "NulaD");
		}

		listaFinal.add(fila);
		System.out.println(fila + " D Tercera " + (numeroPregunta + 20) + " <> mayor " + ("1385"));
	    }

	    int cuartaColumna = (entreColumnas * 3); // +800

	    if (stringX < (intB + cuartaColumna) && stringX > 1400) {
		int numeroPregunta = numeroPregunta(fila, y);
		listaNumeros.put(numeroPregunta + 30, "A");
		System.out.println(fila + " A Cuarta " + (numeroPregunta + 30) + " <> mayor " + (intB + cuartaColumna));
	    } else if (stringX > (intB + cuartaColumna) && stringX < (intC + cuartaColumna)) {
		int numeroPregunta = numeroPregunta(fila, y);
		listaNumeros.put(numeroPregunta + 30, "B");
		System.out.println(fila + " B Cuarta " + (numeroPregunta + 30) + " <> mayor" + (intB + cuartaColumna)
			+ " menor " + (intC + cuartaColumna));
	    } else if (stringX > (intC + cuartaColumna) && stringX < (intD + cuartaColumna)) {
		int numeroPregunta = numeroPregunta(fila, y);
		listaNumeros.put(numeroPregunta + 30, "C");
		System.out.println(fila + " C Cuarta " + (numeroPregunta + 30) + " <> mayor" + (intC + cuartaColumna)
			+ "menor" + (intD + cuartaColumna));
	    } else if (stringX > (intD + cuartaColumna)) {
		int numeroPregunta = numeroPregunta(fila, y);
		listaNumeros.put(numeroPregunta + 30, "D");
		System.out.println(fila + " D Cuarta " + (numeroPregunta + 30) + " <> mayor" + (intD + cuartaColumna));
	    }

	}

	System.out.println(listaNumeros);
	return listaNumeros;
    }

    public Integer numeroPregunta(Par fila, int y) {

	int horquillaInicial = (y + 155); // Altura de "A" normalmente y+55
	int horquillaSize = 95; // es la media de separación entre filas

	double numero = fila.getNumeroY();
	int horquilla = (int) Math.ceil((numero - horquillaInicial) / horquillaSize) + 1;
	return horquilla;
    }

}
