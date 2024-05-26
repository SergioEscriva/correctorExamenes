package correctorExamenes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Busqueda {

    public void busquedaLetras(List<Par> coordenadas) {
	List<Par> listaFinal = new ArrayList<>();
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

	    String letra = "A";
	    if (stringX < intB) {
		System.out.println("Letra A Primera Columna" + fila);
		listaFinal.add(fila);
		letra = "A";
	    } else if (stringX > intB && stringX < intC) {
		listaFinal.add(fila);
		System.out.println("Letra B Primera Columna " + fila);
		letra = "B";
	    } else if (stringX > intC && stringX < intD) {
		listaFinal.add(fila);
		System.out.println("Letra C Primera Columna " + fila);
		letra = "C";
	    } else if (stringX > intD && stringX < 400) {
		listaFinal.add(fila);
		System.out.println("Letra D Primera Columna " + fila);
		letra = "D";
	    } // else {
	      // System.out.println("Error de lectura " + fila);
	      // }
	    int segundaColumna = 500;

	    if (stringX < (intB + segundaColumna) && stringX > 400) {
		listaFinal.add(fila);
		System.out.println("Letra A Segunda Columna" + fila);
		letra = "A";
	    } else if (stringX > (intB + segundaColumna) && stringX < (intC + segundaColumna)) {
		listaFinal.add(fila);
		System.out.println("Letra B Segunda Columna " + fila);
		letra = "B";
	    } else if (stringX > (intC + segundaColumna) && stringX < (intD + segundaColumna)) {
		listaFinal.add(fila);
		System.out.println("Letra C Segunda Columna " + fila);
		letra = "C";
	    } else if (stringX > (intD + segundaColumna) && stringX < 900) {
		listaFinal.add(fila);
		System.out.println("Letra D Segunda Columna " + fila);
		letra = "D";
	    } // else {
	      // System.out.println("Error de lectura " + fila);
	      // }

	    int terceraColumna = 990;

	    if (stringX < (intB + terceraColumna) && stringX > 900) {
		listaFinal.add(fila);
		System.out.println("Letra A Tercera Columna" + fila);
		letra = "A";
	    } else if (stringX > (intB + terceraColumna) && stringX < (intC + terceraColumna)) {
		listaFinal.add(fila);
		System.out.println("Letra B Tercera Columna " + fila);
		letra = "B";
	    } else if (stringX > (intC + terceraColumna) && stringX < (intD + terceraColumna)) {
		listaFinal.add(fila);
		System.out.println("Letra C Tercera Columna " + fila);
		letra = "C";
	    } else if (stringX > (intD + terceraColumna) && stringX < 1450) {
		listaFinal.add(fila);
		System.out.println("Letra D Tercera Columna " + fila);
		letra = "D";
	    }

	    int cuartaColumna = 1450;

	    if (stringX < (intB + cuartaColumna) && stringX > 1450) {
		listaFinal.add(fila);
		System.out.println("Letra A Cuarta Columna" + fila);
		letra = "A";
	    } else if (stringX > (intB + cuartaColumna) && stringX < (intC + cuartaColumna)) {
		listaFinal.add(fila);
		System.out.println("Letra B Cuarta Columna " + fila);
		letra = "B";
	    } else if (stringX > (intC + cuartaColumna) && stringX < (intD + cuartaColumna)) {
		listaFinal.add(fila);
		System.out.println("Letra C Cuarta Columna " + fila);
		letra = "C";
	    } else if (stringX > (intD + cuartaColumna)) {
		System.out.println("Letra D Cuarta Columna " + fila);
		listaFinal.add(fila);
		letra = "D";
	    }

	}
	System.out.println(listaFinal);

    }

}
