package correctorExamenes;

import java.util.List;

public class Busqueda {

    public String busquedaLetras(List coordenadas) {

	//
	for (Object fila : coordenadas) {
	    String filaString = fila.toString();
	    String[] coordenadasList = filaString.toString().split(",");

	    String stringX = coordenadasList[0]; // izquierda a derecha
	    String stringY = coordenadasList[1]; // arriba a bajo
	    String stringXLimpio = stringX.replaceAll("[{}]", "");// .replaceAll("[", "");// .replace("{", "");
	    double x = Double.valueOf(stringXLimpio);
	    System.out.println(x);

	    // stringX.replaceAll("{}()", "");

	    // System.out.println(" as " + yLimpio);
	    // int x = Integer.valueOf(stringX);

	    // System.out.println("Coorde y " + y);
	    int intB = 235; // 265
	    int intC = 300; // 330
	    int intD = 360; // 390

	    String letra = "A";
	    if (x < intB) {
		System.out.println("Letra A Primera Columna " + stringY);
		return letra = "A";
	    }
	    if (x > intB && x < intC) {
		System.out.println("Letra B Primera Columna " + stringY);
		return letra = "B";
	    }
	    if (x < intC && x > intD) {
		System.out.println("Letra C Primera Columna " + stringY);
		return letra = "C";
	    }
	    if (x > intD && x < 700) {
		System.out.println("Letra D Primera Columna " + stringY);
		return letra = "D";
	    }
	}

	return null;
    }

}
