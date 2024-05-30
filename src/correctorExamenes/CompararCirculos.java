package correctorExamenes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompararCirculos {
	public <K> Map<String, Par> busquedaLetras(List<Par> allCircles, List<Par> whiteCircles, int x, int y) {
		NumerarCirculos numerarCirculos = new NumerarCirculos();

		Map<String, Par> circulosNumeradosMap = numerarCirculos.busquedaLetras(allCircles, whiteCircles, x, y);
		Map<String, Par> circulosCorrectosMap = new HashMap<>();

		for (Map.Entry<String, Par> entry : circulosNumeradosMap.entrySet()) {
			String llave = entry.getKey();
			for (Par respuestas : whiteCircles) {
				// System.out.println("entry " + entry.getValue() + " respuestas " +
				// respuestas);
				if (respuestas.toString().contains(entry.getValue().toString())) {
//					System.out.println("Entrooo");
					circulosCorrectosMap.put(llave, respuestas);
				}

			}

		}
//		System.out.println(" allCircles " + circulosNumeradosMap);
//		System.out.println(" whiteCircles " + whiteCircles.size());
//		System.out.println("CirculosCorrectos " + circulosCorrectosMap.size());
		return circulosNumeradosMap;

	}
}
