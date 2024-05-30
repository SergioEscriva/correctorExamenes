package correctorExamenes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumerarMarcados {
    public <K> Map<String, Par> busquedaLetras(List<Par> allCircles, List<Par> whiteCircles, int x, int y) {
	NumerarTodos numerarTodos = new NumerarTodos();
	NumerarMarcados numerarMarcados = new NumerarMarcados();

	Map<String, Par> circulosNumeradosMap = numerarTodos.busquedaLetras(allCircles, y);
	Map<String, Par> circulosCorrectosMap = new HashMap<>();

	Map<String, Par> circulosMarcadosMap = numerarMarcados.busquedaLetras(allCircles, whiteCircles, x, y);

//	for (Map.Entry<String, Par> entry : circulosNumeradosMap.entrySet()) {
//	    String llave = entry.getKey();
//	    for (Par respuestas : whiteCircles) {
//		// System.out.println("entry " + entry.getValue() + " respuestas " +
//		// respuestas);
//		if (respuestas.toString().contains(entry.getValue().toString())) {
////					System.out.println("Entrooo");
//		    circulosCorrectosMap.put(llave, respuestas);
//		}
//
//	    }
//
//	}

//		System.out.println(" allCircles " + circulosNumeradosMap);
//		System.out.println(" whiteCircles " + whiteCircles.size());
	System.out.println("CirculosCorrectos " + circulosCorrectosMap.size());
	System.out.println("CirculosCorrectos " + circulosCorrectosMap);
	return circulosNumeradosMap;

    }
}
