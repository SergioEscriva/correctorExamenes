package correctorExamenes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumerarCirculos {
    private int numero1 = 0;

    public void busquedaLetras(List<Par> allCircles, List<Par> whiteCircles, int x, int y) {
	List<Par> listaFinal = new ArrayList<>();

	Map<String, Par> listaNumerosLetras = new HashMap<>();
	for (int i = 0; i <= 10; i++) {
	    Par parNumeradosPar = allCircles.get(i);
	    System.out.println("Ntras> " + allCircles.get(i));
	    String valorLetra = String.valueOf(i + 1) + " D";
	    listaNumerosLetras.put(valorLetra, parNumeradosPar);
	}
	for (int i = 11; i <= 20; i++) {
	    Par parNumeradosPar = allCircles.get(i);
	    System.out.println("Ntras> " + allCircles.get(i));
	    String valorLetra = String.valueOf(i + 1) + " C";
	    listaNumerosLetras.put(valorLetra, parNumeradosPar);
	}
	for (int i = 21; i <= 30; i++) {
	    Par parNumeradosPar = allCircles.get(i);
	    System.out.println("Ntras> " + allCircles.get(i));
	    String valorLetra = String.valueOf(i + 1) + " B";
	    listaNumerosLetras.put(valorLetra, parNumeradosPar);
	}
	for (int i = 31; i <= 40; i++) {
	    Par parNumeradosPar = allCircles.get(i);
	    System.out.println("Ntras> " + allCircles.get(i));
	    String valorLetra = String.valueOf(i + 1) + " D";
	    listaNumerosLetras.put(valorLetra, parNumeradosPar);
	}
	System.out.println("NumeradosLetras> " + listaNumerosLetras);
	System.out.println("Ntras> " + allCircles.get(0));
//	

	// return listaNumeros;
	// return null;

    }

    public Integer numeroPregunta(Par fila, int y) {

	int horquillaInicial = (y + 155); // Altura de "A" normalmente y+55
	int horquillaSize = 95; // es la media de separación entre filas

	double numero = fila.getNumeroY();
	int horquilla = (int) Math.ceil((numero - horquillaInicial) / horquillaSize) + 1;
	if (horquilla == 10) {
	    numero1 = numero1 + 10;

	}

	if (numero1 > 1 && numero1 <= 10) {
	    String numeroNuevo = String.valueOf(numero1);
	}

	horquilla = horquilla + (numero1);

	return horquilla;
    }
}
