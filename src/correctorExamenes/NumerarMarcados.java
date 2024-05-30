package correctorExamenes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumerarMarcados {
    public Map<Integer, String> busquedaLetras(List<Par> allCircles, List<Par> whiteCircles, int x, int y) {
	NumerarTodos numerarTodos = new NumerarTodos();
	NumerarMarcados numerarMarcados = new NumerarMarcados();

	Map<String, Par> todosNumeradosMap = numerarTodos.busquedaLetras(allCircles, y); // Todos los circulos

	// Map<Integer, String> circulosMarcados = new HashMap<>();
	int u = 0;
	Map<Integer, String> circulosMarcados = new HashMap<>();
	for (int i = 1; i <= 40; i++) {
	    circulosMarcados.put(i, "Empty");
	    u = i;
	}

	for (Map.Entry<String, Par> entry : todosNumeradosMap.entrySet()) {
	    String llave = entry.getKey();
	    // String str = "removea";
	    String llaveNumero = llave.replaceFirst(".$", "");
	    String llaveLetra = "";
	    if (Integer.valueOf(llaveNumero) < 10) {
		llaveLetra = String.valueOf(llave.charAt(1));
	    } else {
		llaveLetra = String.valueOf(llave.charAt(2));
	    }

	    Par value = entry.getValue();
	    for (Par respuestas : whiteCircles) {
		if (respuestas.toString().contains(value.toString())) {
		    System.out.println("EEEEEntroooooo");
		    circulosMarcados.put(Integer.valueOf(llaveNumero), llaveLetra);
		}

	    }

	}

	// Map<Integer, String> examenAlumno;

	System.out.println(" allCircles " + allCircles);
	System.out.println(" whiteCircles " + whiteCircles);
	System.out.println("CirculosCorrectos " + todosNumeradosMap.size());
	System.out.println("CirculosCorrectos " + circulosMarcados);
	return circulosMarcados;

    }
}
