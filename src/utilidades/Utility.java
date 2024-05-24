package utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    public ArrayList<String[]> leerPreguntasCSV() throws Exception {

	// Comprueba si ya tenemos la carpeta abierta y muestra ruta
	String nombreArchivo = null;// existeZip();
	// recupera archivo extraido zip

	final String SEPARADOR_CAMPO = ";";
	ArrayList<String[]> preguntasRespuestas = new ArrayList<>();
	// String[] preguntasRespuestas = {};
	FileReader fileReader = null;
	BufferedReader bufferedReader = null;
	try {
	    fileReader = new FileReader(nombreArchivo);
	    bufferedReader = new BufferedReader(fileReader);
	    String linea;
	    while ((linea = bufferedReader.readLine()) != null) {
		String[] preguntaRespuesta = linea.split(SEPARADOR_CAMPO);
		preguntasRespuestas.add(preguntaRespuesta);

	    }
	} catch (IOException e) {
	    System.out.println("Excepción leyendo archivo: " + e.getMessage());
	} finally {
	    try {
		if (fileReader != null) {
		    fileReader.close();
		}
		if (bufferedReader != null) {
		    bufferedReader.close();
		}
	    } catch (IOException e) {
		System.out.println("Excepción cerrando: " + e.getMessage());
	    }

	    return preguntasRespuestas;
	}
    }

    public static ArrayList<String> CrearListaStrings(String root, String fileName, String fileFormat, int size) {
	ArrayList<String> ListaTemp = new ArrayList<>();
	for (int i = 0; i < size; i++) {
	    ListaTemp.add(root + fileName + i + fileFormat);
	}
	return ListaTemp;
    }

    // JSON
    private static final String JSON_FILE_PATH = "src/Launcher/BaseDeDatos.json";

    public static String NombreGrado(int indexGrado) throws FileNotFoundException {
	try (BufferedReader br = new BufferedReader(new FileReader(JSON_FILE_PATH))) // Lee el json con BufferedReader
	{
	    // Convierte la lectura del json en un string con StringBuilder
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = br.readLine()) != null) {
		sb.append(line);
	    }

	    // Utiliza las librerias de JSON para acceder hasta la variable
	    JSONObject grado = new JSONObject(sb.toString()).getJSONArray("grados").getJSONObject(indexGrado);

	    return grado.getString("titulo");
	} catch (IOException | JSONException e) {
	    return "Error al leer el archivo JSON";
	}
    }

    public static String NombreJuego(int indexGrado, int indexJuego) throws FileNotFoundException {
	try (BufferedReader br = new BufferedReader(new FileReader(JSON_FILE_PATH))) // Lee el JSON con BufferedReader
	{
	    // Convierte la lectura del JSON en un string con StringBuilder
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = br.readLine()) != null) {
		sb.append(line);
	    }

	    // Utiliza las librerias de JSON para acceder hasta la variable
	    JSONObject juego = new JSONObject(sb.toString()).getJSONArray("grados").getJSONObject(indexGrado)
		    .getJSONArray("juegos").getJSONObject(indexJuego);

	    return juego.getString("titulo");
	} catch (IOException | JSONException e) {
	    return "Error al leer el archivo JSON";
	}
    }

    public static String ImagenJuego(int indexGrado, int indexJuego) throws FileNotFoundException {
	try (BufferedReader br = new BufferedReader(new FileReader(JSON_FILE_PATH))) // Lee el JSON con BufferedReader
	{
	    // Convierte la lectura del JSON en un string con StringBuilder
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = br.readLine()) != null) {
		sb.append(line);
	    }

	    // Utiliza las librerias de JSON para acceder hasta la variable
	    JSONObject juego = new JSONObject(sb.toString()).getJSONArray("grados").getJSONObject(indexGrado)
		    .getJSONArray("juegos").getJSONObject(indexJuego);

	    return juego.getString("imagen");
	} catch (IOException | JSONException e) {
	    return "Error al leer el archivo JSON";
	}
    }

    public static String DescripcionJuego(int indexGrado, int indexJuego) throws FileNotFoundException {
	try (BufferedReader br = new BufferedReader(new FileReader(JSON_FILE_PATH))) // Lee el JSON con BufferedReader
	{
	    // Convierte la lectura del JSON en un string con StringBuilder
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = br.readLine()) != null) {
		sb.append(line);
	    }

	    // Utiliza las librerias de JSON para acceder hasta la variable
	    JSONObject juego = new JSONObject(sb.toString()).getJSONArray("grados").getJSONObject(indexGrado)
		    .getJSONArray("juegos").getJSONObject(indexJuego);

	    return juego.getString("descripcion");
	} catch (IOException | JSONException e) {
	    return "Error al leer el archivo JSON";
	}
    }

    public static String RutaJuego(int indexGrado, int indexJuego) throws FileNotFoundException {
	try (BufferedReader br = new BufferedReader(new FileReader(JSON_FILE_PATH))) // Lee el JSON con BufferedReader
	{
	    // Convierte la lectura del JSON en un string con StringBuilder
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = br.readLine()) != null) {
		sb.append(line);
	    }

	    // Utiliza las librerias de JSON para acceder hasta la variable
	    JSONObject juego = new JSONObject(sb.toString()).getJSONArray("grados").getJSONObject(indexGrado)
		    .getJSONArray("juegos").getJSONObject(indexJuego);

	    return juego.getString("ruta");
	} catch (IOException | JSONException e) {
	    return "Error al leer el archivo JSON";
	}
    }
}
