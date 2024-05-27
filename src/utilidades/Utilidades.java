package utilidades;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import correctorExamenes.BusquedaCirculos;
import correctorExamenes.PantallaPrincipal;

public class Utilidades {

    private static final String JSON_FILE_PATH = "src/Json/codigos.json";

    public void calcularNota(String indexCodigo) throws JSONException, IOException {
	JSONArray plantillaString = json(indexCodigo);

	BusquedaCirculos bCirculos = new BusquedaCirculos();
	Map<Integer, String> examenAlumno = bCirculos.buscarRespuestas();
	ArrayList<Integer> resultado = new ArrayList<>();

	for (int i = 1; i <= 40; i++) {
	    String preguntaPlantilla = plantillaString.getString(i - 1).toUpperCase();
	    String preguntaExamen = examenAlumno.get(i);
	    if (preguntaPlantilla.equals(preguntaExamen)) {
		resultado.add(1);
	    } else {
		resultado.add(0);
	    }

	}
	double notaFinal = resultado.stream().reduce(0, (a, b) -> a + b);
	System.out.print(notaFinal / 4);
	System.out.println(resultado);
    }

    public JSONArray json(String codigo) throws JSONException, IOException {
	System.out.println("json");
	String filePath = "src/Json/codigos.json"; // Reemplaza con la ruta correcta
	JSONArray list = new JSONArray();
	try {
	    // Cargar el archivo JSON
	    FileReader is = new FileReader(filePath);
	    JSONTokener tokener = new JSONTokener(is);
	    JSONArray jsonArray = new JSONArray(tokener);

	    // Buscar y extraer la lista correspondiente al código "00001"
	    for (int i = 0; i < jsonArray.length(); i++) {
		JSONObject jsonObject = jsonArray.getJSONObject(i);
		if (jsonObject.has(codigo)) {
		    list = jsonObject.getJSONArray(codigo);
		    return list;
		    // comprueba si la plantilla de correción está escaneada y guardada
		} else {
		    JOptionPane.showMessageDialog(null, "Plantilla indicada no existe.", "Error Plantilla",
			    JOptionPane.INFORMATION_MESSAGE);
		    PantallaPrincipal principal = new PantallaPrincipal();
		    return null;
		}
	    }

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	return null;
    }

}
