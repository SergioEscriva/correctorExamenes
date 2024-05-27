package utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import correctorExamenes.BusquedaCirculos;

public class Utilidades {

    private static final String JSON_FILE_PATH = "src/Json/codigos.json";

//	public static JSONArray codigoTest() throws JSONException, IOException {
//		System.out.println("leyendoJson");
//		StringBuilder sb;
//		try (BufferedReader br = new BufferedReader(new FileReader(JSON_FILE_PATH))) // Lee el json con BufferedReader
//
//		{
//
//			// Convierte la lectura del json en un string con StringBuilder
//			sb = new StringBuilder();
//			String line;
//			while ((line = br.readLine()) != null) {
//				sb.append(line);
//			}
//			System.out.println(" SB " + sb);
//
//			JSONArray jsonArray = new JSONArray(sb.toString());
//
//			// System.out.println(jsonArray.getJSONObject(0).get(indexCodigo));
//
//			return jsonArray;
//		} catch (IOException e) { // | JSONException e) {
//
//			System.out.println("BR error");
//			return null;
//		}
//	}

    public static String respuestasCorrectas(int indexCodigo) throws FileNotFoundException {
	try (BufferedReader br = new BufferedReader(new FileReader(JSON_FILE_PATH))) // Lee el JSON con BufferedReader
	{
	    // Convierte la lectura del JSON en un string con StringBuilder
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = br.readLine()) != null) {
		sb.append(line);
	    }

	    // Utiliza las librerias de JSON para acceder hasta la variable
	    JSONObject juego = new JSONObject(sb.toString()).getJSONArray("codigo").getJSONObject(indexCodigo)
		    .getJSONArray("respuestas").getJSONObject(indexCodigo);

	    return juego.getString("resuestasCorrectas");
	} catch (IOException | JSONException e) {
	    return "Error al leer el archivo JSON";
	}
    }

    private static List<String> jsonArrayToList(JSONArray jsonArray) throws JSONException {
	List<String> list = new ArrayList<>();
	for (int i = 0; i < jsonArray.length(); i++) {
	    list.add(jsonArray.getString(i));
	}
	return list;
    }

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

    public JSONArray json(String codigo) throws JSONException {
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
		    break;
		} else {

		}
	    }

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	return list;
    }

}
