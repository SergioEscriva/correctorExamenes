package correctorExamenes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Busqueda {

    public void busquedaLetras(List<Par> coordenadas) {
	List<Par> listaFinal = new ArrayList<>();
	Map<Integer, String> listaNumeros = new HashMap<>();
	// Ordenar la lista
	Collections.sort(coordenadas, new Comparator<Par>() {
	    @Override
	    public int compare(Par p1, Par p2) {
		int compareFirst = Double.compare(p1.getNumeroX(), p2.getNumeroX());
		if (compareFirst != 0) {
		    return compareFirst;
		} else {
		    return Double.compare(p1.getNumeroY(), p2.getNumeroY());
		}
	    }
	});
	System.out.println(coordenadas);
	// Extrae las filas y le pone la letra según la distacia en x
	for (Par fila : coordenadas) {
	    String filaString = fila.toString();

	    double stringX = fila.getNumeroX(); // x izquierda a derecha
	    double stringY = fila.getNumeroY(); // y arriba a bajo

	    // Primera Columna
	    int intB = 235; // 265
	    int intC = 300; // 330
	    int intD = 360; // 390

	    int entreColumnas = 485; // entre columnas de a - a 485

	    String letra = "A";
	    if (stringX < intB) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta, "A");
		System.out.println("Pregunta :" + numeroPregunta + " A Primera Columna" + fila);
		listaFinal.add(fila);
		letra = "A";
	    } else if (stringX > intB && stringX < intC) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta, "B");
		listaFinal.add(fila);
		System.out.println("Pregunta :" + numeroPregunta + " B Primera Columna" + fila);
		letra = "B";
	    } else if (stringX > intC && stringX < intD) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta, "C");
		listaFinal.add(fila);
		System.out.println("Pregunta :" + numeroPregunta + " C Primera Columna" + fila);
		letra = "C";
	    } else if (stringX > intD && stringX < 400) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta, "D");
		listaFinal.add(fila);
		System.out.println("Pregunta :" + numeroPregunta + " D Primera Columna" + fila);
		letra = "D";
	    } // else {
	      // System.out.println("Error de lectura " + fila);
	      // }

	    if (stringX < (intB + entreColumnas) && stringX > 400) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta + 10, "A");
		listaFinal.add(fila);
		System.out.println("Letra A Segunda Columna" + fila);
		letra = "A";
	    } else if (stringX > (intB + entreColumnas) && stringX < (intC + entreColumnas)) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta + 10, "B");
		listaFinal.add(fila);
		System.out.println("Letra B Segunda Columna " + fila);
		letra = "B";
	    } else if (stringX > (intC + entreColumnas) && stringX < (intD + entreColumnas)) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta + 10, "C");
		listaFinal.add(fila);
		System.out.println("Letra C Segunda Columna " + fila);
		letra = "C";
	    } else if (stringX > (intD + entreColumnas) && stringX < 900) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta + 10, "D");
		listaFinal.add(fila);
		System.out.println("Letra D Segunda Columna " + fila);
		letra = "D";
	    } // else {
	      // System.out.println("Error de lectura " + fila);
	      // }

	    int terceraColumna = (entreColumnas * 2);

	    if (stringX < (intB + terceraColumna) && stringX > 900) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta + 20, "A");
		listaFinal.add(fila);
		System.out.println("Letra A Tercera Columna" + fila);
		letra = "A";
	    } else if (stringX > (intB + terceraColumna) && stringX < (intC + terceraColumna)) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta + 20, "B");
		listaFinal.add(fila);
		System.out.println("Letra B Tercera Columna " + fila);
		letra = "B";
	    } else if (stringX > (intC + terceraColumna) && stringX < (intD + terceraColumna)) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta + 20, "C");
		listaFinal.add(fila);
		System.out.println("Letra C Tercera Columna " + fila);
		letra = "C";
	    } else if (stringX > (intD + terceraColumna) && stringX < 1335) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta + 20, "D");
		listaFinal.add(fila);
		System.out.println("Letra D Tercera Columna " + fila);
		letra = "D";
	    }

	    int cuartaColumna = (entreColumnas * 3);

	    if (stringX < (intB + cuartaColumna) && stringX > 1335) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta + 30, "A");
		listaFinal.add(fila);
		System.out.println("Letra A Cuarta Columna" + fila);
		letra = "A";
	    } else if (stringX > (intB + cuartaColumna) && stringX < (intC + cuartaColumna)) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta + 30, "B");
		listaFinal.add(fila);
		System.out.println("Letra B Cuarta Columna " + fila);
		letra = "B";
	    } else if (stringX > (intC + cuartaColumna) && stringX < (intD + cuartaColumna)) {
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta + 30, "C");
		listaFinal.add(fila);
		System.out.println("Letra C Cuarta Columna " + fila);
		letra = "C";
	    } else if (stringX > (intD + cuartaColumna)) {
		System.out.println("Letra D Cuarta Columna " + fila);
		int numeroPregunta = numeroPregunta(fila);
		listaNumeros.put(numeroPregunta + 30, "D");
		listaFinal.add(fila);
		letra = "D";
	    }

	}
	System.out.println(listaFinal);
	System.out.println(listaNumeros);
	// Ordenar la lista
	Collections.sort(listaFinal, new Comparator<Par>() {
	    @Override
	    public int compare(Par p1, Par p2) {
		int compareFirst = Double.compare(p2.getNumeroY(), p1.getNumeroY());
		if (compareFirst != 0) {
		    return compareFirst;
		} else {
		    return Double.compare(p2.getNumeroX(), p1.getNumeroX());
		}
	    }
	});
	System.out.println(listaFinal);

	// numeroPregunta(listaFinal);
    }

    public Integer numeroPregunta(Par fila) {
	int horquillaSize = 90;
	int horquillaInicial = 200;

	double numero = fila.getNumeroY();
	int horquilla = (int) Math.ceil((numero - horquillaInicial) / horquillaSize) + 1;
	return horquilla;
    }

    public void numerar(List<Par> listaFinal) {
	Map<Integer, String> listaNumeros = new HashMap<>();
	for (Par fila : listaFinal) {
	    if (fila.getNumeroY() < 208) {

	    }

	}

    }

}
