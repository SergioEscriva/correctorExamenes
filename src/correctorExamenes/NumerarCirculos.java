package correctorExamenes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumerarCirculos {
	private int numero1 = 0;

	public Map<String, Par> busquedaLetras(List<Par> allCircles, List<Par> whiteCircles, int x, int y) {
		List<Par> listaFinal = new ArrayList<>();

		// Convertir la lista de pares a un array bidimensional
		double[][] puntos = new double[allCircles.size()][2];
		for (int i = 0; i < allCircles.size(); i++) {
			puntos[i][0] = allCircles.get(i).getNumeroX();
			puntos[i][1] = allCircles.get(i).getNumeroY();
		}

		// Ordenar el array de puntos x primero
		Arrays.sort(puntos, (double[] punto1, double[] punto2) -> {
			// Comparar por valor de x y luego por valor de y
			if (punto1[0] < punto2[0]) {
				return -1;
			} else if (punto1[0] > punto2[0]) {
				return 1;
			} else {
				if (punto1[1] < punto2[1]) {
					return -1;
				} else if (punto1[1] > punto2[1]) {
					return 1;
				} else {
					return 0; // Los puntos son iguales
				}
			}
		});

		/// ordena por la y primero de x mas a y menos

//	Arrays.sort(puntos, (double[] punto1, double[] punto2) -> {
//	    // Comparar primero por valor de y y luego por valor de x
//	    if (punto1[1] < punto2[1]) {
//		return -1;
//	    } else if (punto1[1] > punto2[1]) {
//		return 1;
//	    } else {
//		// Si los valores de y son iguales, comparar por valor de x
//		if (punto1[0] < punto2[0]) {
//		    return -1;
//		} else if (punto1[0] > punto2[0]) {
//		    return 1;
//		} else {
//		    return 0; // Los puntos son iguales
//		}
//	    }
//	});

		// ... (Previous code for converting the list to a double array remains the
		// same)

		// Ordenar el array de puntos x primero
		// ... (Sorting logic remains the same)

		// Empieza por y de menos a mas
//		Arrays.sort(puntos, (double[] punto1, double[] punto2) -> {
//			// Comparar primero por valor de y en orden ascendente
//			int comparacionY = Double.compare(punto1[1], punto2[1]);
//			if (comparacionY != 0) {
//				return comparacionY; // Si los valores de y son diferentes, retornarlos
//			} else {
//				// Si los valores de y son iguales, comparar por valor de x en orden ascendente
//				int comparacionX = Double.compare(punto1[0], punto2[0]);
//				return comparacionX; // Retornar la comparación de x
//			}
//		});

		// List<double[]> puntosCategoria = new ArrayList<double[]>();

//	Map<String, List<double[]>> puntosPorCategoria = new HashMap<>();
//	List<double[]> puntosCategoria = new ArrayList<double[]>();
//	int categoria = 4;
//	String categoriaActual;
//	for (double[] punto : puntos) {
//	    // Crear la clave de la categoría actual
//	    if (categoria == 4) {
//		categoriaActual = categoria + "A";
//	    } else if (categoria == 3) {
//		categoriaActual = categoria + "B";
//	    } else if (categoria == 2) {
//		categoriaActual = categoria + "C";
//	    } else {
//		categoriaActual = categoria + "D";
//	    }
//
//	    // Obtener la lista de puntos para la categoría actual
//	    // List<double[]> puntosCategoria =
//	    // puntosPorCategoria.getOrDefault(categoriaActual, new ArrayList<>());
//
//	    // Agregar el punto a la lista
//	    puntosCategoria.add(punto);
////	    for (double[] punto2 : puntosCategoria) {
////		System.out.println("Punto: " + Arrays.toString(punto2));
////	    }
//	    System.out.println("OOOOOOOOOOOOO " + categoriaActual + " - " + punto);
//	    puntosPorCategoria.put(categoriaActual, puntosCategoria);
//
//	    // Actualizar la categoría para el siguiente punto
//	    categoria--;
//
//	    // Si la categoría llega a 0, reiniciarla a 4
//	    if (categoria == 0) {
//		categoria = 4;
//	    }
//	}
//
//	for (Map.Entry<String, List<double[]>> entry : puntosPorCategoria.entrySet()) {
//	    String categoria1 = entry.getKey();
//	    List<double[]> puntosCategoria1 = entry.getValue();
//
//	    System.out.println("Categoría: " + categoria1);
//	    for (double[] punto : puntosCategoria1) {
//		System.out.println("Punto: " + Arrays.toString(punto));
//	    }
//	}
//
//	System.out.println("PPPPPUNTOS" + puntosPorCategoria + " puntos > " + puntos);
		List<Par> circulosList = new ArrayList<Par>();
		for (double[] puntossDoubles : puntos) {
			double xx = puntossDoubles[0];
			double yy = puntossDoubles[1];
			Par fila = new Par(xx, yy);
			circulosList.add(fila);
		}

		System.out.println("CL " + circulosList);
		System.out.println("CL1" + circulosList.get(0));

		Map<String, Par> listaNumerosLetras = new HashMap<>();

		listaNumerosLetras = numeroRespuesta(circulosList, y);

//	

		return listaNumerosLetras;
		// return null;

	}

	public Map<String, Par> numeroRespuesta(List<Par> circulosList, int y) {
		System.out.println("puntos1" + circulosList.size());
		Map<String, Par> listaNumerosLetras = new HashMap<>();
		int a1 = 0;
		int a2 = 0;

		String[] letrasAutomaticas = { "A", "B", "C", "D" };
		for (String letra : letrasAutomaticas) {

			switch (letra) {

			case "A":
				a1 = 0;
				a2 = 9;
				break;
			case "B":
				a1 = 10;
				a2 = 19;
				break;
			case "C":
				a1 = 20;
				a2 = 29;
				break;
			case "D":
				a1 = 30;
				a2 = 39;
				break;
			}

			for (int i = a1; i <= a2; i++) {
				Par parNumeradosPar = circulosList.get(i);
				int number = numeroPregunta(circulosList.get(i), y);
				String valorLetra = String.valueOf(number) + letra;
				listaNumerosLetras.put(valorLetra, parNumeradosPar);

			}
			for (int i = a1 + 40; i <= a2 + 40; i++) {
				Par parNumeradosPar = circulosList.get(i);
				int number = numeroPregunta(circulosList.get(i), y);
				String valorLetra = String.valueOf(number) + letra;
				listaNumerosLetras.put(valorLetra, parNumeradosPar);

			}

			for (int i = a1 + 80; i <= a2 + 80; i++) {
				Par parNumeradosPar = circulosList.get(i);
				int number = numeroPregunta(circulosList.get(i), y);
				String valorLetra = String.valueOf(number) + letra;
				listaNumerosLetras.put(valorLetra, parNumeradosPar);

			}
			for (int i = a1 + 120; i <= a2 + 120; i++) {
				Par parNumeradosPar = circulosList.get(i);
				int number = numeroPregunta(circulosList.get(i), y);
				String valorLetra = String.valueOf(number) + letra;
				listaNumerosLetras.put(valorLetra, parNumeradosPar);

			}
		}

		System.out.println("lista " + listaNumerosLetras.size());
		System.out.println(" whiteCircles " + listaNumerosLetras);
		return listaNumerosLetras;

	}

	public Integer numeroPregunta(Par fila, int y) {

		int horquillaInicial = (y + 155); // Altura de "A" normalmente y+55
		int horquillaSize = 95; // es la media de separación entre filas

		double numero = fila.getNumeroY();
		int horquilla = (int) Math.ceil((numero - horquillaInicial) / horquillaSize) + 1;

		if (horquilla == 10) {
			numero1 = numero1 + 10;
			// System.out.println("numero1 " + numero1);
		}

//		if (numero1 > 1 && numero1 <= 10) {
//			Integer numeroNuevo = horquilla;
//			horquilla = horquilla + numero1;
//
//			System.out.println(" horquilla1 " + horquilla);
//			return numeroNuevo;
//		}

		horquilla = horquilla + numero1;
//		if (horquilla + numero1 == 41) {
//			horquilla = 1;
//
//		}
		if (horquilla == 20 || horquilla == 30 || horquilla == 40 || horquilla == 50) {
			horquilla = horquilla - 10;
		}
		if (horquilla == 40) {
			numero1 = 0;
		}

		return horquilla;
	}

}
