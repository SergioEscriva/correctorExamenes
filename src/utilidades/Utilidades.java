package utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utilidades {
	/*
	 * public ArrayList<String[]> leerJson() throws Exception {
	 * 
	 * 
	 * 
	 * ArrayList<String[]> preguntasRespuestas = new ArrayList<>();
	 * 
	 * FileReader fileReader = null; BufferedReader bufferedReader = null; try {
	 * fileReader = new FileReader(nombreArchivo); bufferedReader = new
	 * BufferedReader(fileReader); String linea; while ((linea =
	 * bufferedReader.readLine()) != null) { String[] preguntaRespuesta =
	 * linea.split(SEPARADOR_CAMPO); preguntasRespuestas.add(preguntaRespuesta);
	 * 
	 * } } catch (IOException e) { System.out.println("Excepción leyendo archivo: "
	 * + e.getMessage()); } finally { try { if (fileReader != null) {
	 * fileReader.close(); } if (bufferedReader != null) { bufferedReader.close(); }
	 * } catch (IOException e) { System.out.println("Excepción cerrando: " +
	 * e.getMessage()); }
	 * 
	 * return preguntasRespuestas; } }
	 * 
	 * public static ArrayList<String> CrearListaStrings(String root, String
	 * fileName, String fileFormat, int size) { ArrayList<String> ListaTemp = new
	 * ArrayList<>(); for (int i = 0; i < size; i++) { ListaTemp.add(root + fileName
	 * + i + fileFormat); } return ListaTemp; }
	 */
	// JSON
	private static final String JSON_FILE_PATH = "src/Json/codigos.json";

	public static String codigoTest(String indexCodigo) throws JSONException, IOException {
		System.out.println("leyendoJson");
		StringBuilder sb;
		try (BufferedReader br = new BufferedReader(new FileReader(JSON_FILE_PATH))) // Lee el json con BufferedReader

		{

			// Convierte la lectura del json en un string con StringBuilder
			sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(" SB " + sb);

			JSONArray jsonArray = new JSONArray(sb.toString());
			System.out.println(jsonArray.getJSONObject(0).get(indexCodigo));

			return jsonArray.toString();
		} catch (IOException e) { // | JSONException e) {

			System.out.println("BR error");
			return "Error al leer el archivo JSON";
		}
	}

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
}
