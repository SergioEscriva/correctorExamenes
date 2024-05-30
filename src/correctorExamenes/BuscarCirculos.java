package correctorExamenes; //src/correctorExamenes/examen2.jpg

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TessAPI;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;
import utilidades.Utilidades;

public class BuscarCirculos {
    static {
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    private static Utilidades utilidades = new Utilidades();
    private static String imagePath;
    private static Map<Integer, String> examenAlumno;
    private static List<Par> allCircles;

    public static Map<Integer, String> buscarCirculos(int y, int x) throws JSONException, IOException {
	// Cargar la imagen
	String imagePathInv = "./bnarchivo-negro.jpg";//
	Mat srcBlack = Imgcodecs.imread(imagePathInv);
	Mat srcWhite = Imgcodecs.imread(imagePathInv);

	if (srcBlack.empty()) {
	    System.out.println("No se pudo cargar la imagen");
	    return null;
	}

	allCircles = rebuscarCirculos(srcBlack, "all");
	List<Par> white1Circles = rebuscarCirculos(srcWhite, "white");
	// Convertir la imagen a escala de grises necesario para que se vea mejor

	// pasa a la clase que buscará las letras de los circulos
	NumerarCirculos numerarCirculos = new NumerarCirculos();
	numerarCirculos.busquedaLetras(allCircles, allCircles, x, y);
	CirculosMarcados busqueda = new CirculosMarcados();
	examenAlumno = busqueda.busquedaLetras(allCircles, white1Circles, x, y);

	return examenAlumno;

    }

    public static void invertirOscurecer(String imagePath, int intY) throws IOException, JSONException {
	File file = new File(imagePath);
	BufferedImage img = ImageIO.read(file);

	// Invierte los valores RGB de cada pixel
	for (int y = 0; y < img.getHeight(); y++) {
	    for (int x = 0; x < img.getWidth(); x++) {
		int pixel = img.getRGB(x, y);
		int r = (pixel >> 16) & 0xff;
		int g = (pixel >> 8) & 0xff;
		int b = pixel & 0xff;
		int nuevoPixel = (255 - r) << 16 | (255 - g) << 8 | (255 - b);
		img.setRGB(x, y, nuevoPixel);
	    }
	}

	// conviert blanco y negro
	BufferedImage imagenNegra = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
	List<Double> lista = new ArrayList<>();
	for (int i = 0; i < img.getWidth(); i++) {
	    for (int j = 0; j < img.getHeight(); j++) {
		int pixel = img.getRGB(i, j);
		int luminosidad = (pixel >> 16) & 0xFF;
		if (luminosidad > 127) {
		    imagenNegra.setRGB(i, j, 0xFFFFFFFF);
		} else {
		    imagenNegra.setRGB(i, j, 0x00000000);
		}
	    }
	    // lista.add(imagenNegra);
	}

	ImageIO.write(imagenNegra, "jpg", new File("./bnarchivo-negro.jpg")); // no tocar
	// buscarCirculos(intY);

    }

    public static Map<Integer, String> buscarRespuestas(String imagePath) throws IOException, JSONException {
	// imagePath = "src/correctorExamenes/examen3.jpg"; /// este es la imagen del
	// examen que leerá

	// Crear una instancia de Tesseract
	Map<Integer, String> blancoMap = new HashMap<Integer, String>();
	ITesseract tesseract = new Tesseract();

	// Configurar la ruta del idioma (opcional)
	String datapath = "src/resources/tessdata_best";
	tesseract.setDatapath(new File(datapath).getPath());

	// Configurar el idioma
	tesseract.setLanguage("eng");

	// Configurar para buscar solo números
	tesseract.setTessVariable("tessedit_char_whitelist", "RESPUESTAS");
	int y = 0;
	int x = 0;
	int tamanoImagen = 0;
	try {
	    // Leer el archivo de imagen y convertirlo a BufferedImage
	    BufferedImage image = ImageIO.read(new File(imagePath));
	    tamanoImagen = image.getWidth();
	    // Realizar OCR en la imagen y obtener las palabras
	    List<Word> words = tesseract.getWords(image, TessAPI.TessPageIteratorLevel.RIL_WORD);

	    // Imprimir las coordenadas de cada palabra
	    for (Word word : words) {
		String text = word.getText();
		if (text.equals("RESPUESTAS")) {
		    x = word.getBoundingBox().x;
		    y = word.getBoundingBox().y;
		    int width = word.getBoundingBox().width;
		    int height = word.getBoundingBox().height;

		    System.out.println("Palabra: " + text);
		    System.out.println("Coordenadas: (" + x + ", " + y + ")");
		    System.out.println("Tamaño: " + width + "x" + height);
		    System.out.println();
		}
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// Comprueba que la imagen esté centrada según la palabra Respuestas.
	int referenciaRespuesta = tamanoImagen - 234;
	int tamanoReferenciaDerecha = referenciaRespuesta - x;
	int comparacion = tamanoReferenciaDerecha - x;
	if (comparacion >= 60 || comparacion <= -60) {
//			JOptionPane.showMessageDialog(null,
//					"Error en la resolución de la imagen, intenta escanearla lo más centrada posible, gracias.",
//					"Resolución Erronea", JOptionPane.INFORMATION_MESSAGE);
//			PantallaPrincipal principal = new PantallaPrincipal();
//			principal.abrirExamen();

	} else {
	    System.out.println("final");
	    invertirOscurecer(imagePath, y);
	    examenAlumno = buscarCirculos(y, x);

	    return examenAlumno;
	}
	return blancoMap;
    }

    public Map<String, String> calcularNota(JSONArray plantillaString) throws JSONException, IOException {
	// JSONArray plantillaString = utilidades.json(indexCodigo);

	// BusquedaCirculos bCirculos = new BusquedaCirculos();
	// Map<Integer, String> examenAlumno = bCirculos.buscarRespuestas(imagePath);
	Map<String, String> notas = new HashMap<>();
	System.out.println("leerNotas");
	ArrayList<Integer> resultado = new ArrayList<>();
	int aciertos = 0;
	int falladas = 0;
	int blanco = 0;
	int nulas = 0;

	for (int i = 1; i <= 40; i++) {
	    String preguntaPlantilla = plantillaString.getString(i - 1).toUpperCase();
	    String preguntaExamen = examenAlumno.get(i);
	    if (preguntaPlantilla.equals(preguntaExamen)) {
		resultado.add(1);
		aciertos += 1;
	    } else if (preguntaExamen.equals("Nula")) {
		nulas += 1;

	    } else if (preguntaExamen.equals("Empty")) {
		blanco += 1;

	    } else {
		resultado.add(0);
		falladas += 1;

	    }
	}
	double notaFinal = resultado.stream().reduce(0, (a, b) -> a + b);
	double notaReal = notaFinal / 4;

	System.out.print(notaFinal / 4);

	notas.put("notaFinal", String.valueOf(notaReal));
	notas.put("aciertos", String.valueOf(aciertos));
	notas.put("fallos", String.valueOf(falladas));
	notas.put("blanco", String.valueOf(blanco));
	notas.put("nulas", String.valueOf(nulas));
	return notas;
    }

    public static List<Par> rebuscarCirculos(Mat src, String circulos) {

	double radio = 0.0;
	String rutaCirculos = "./blancos.jpg";
	radio = 0.8;
	List<Par> lista = new ArrayList<>();
	if (circulos.equals("all")) {
	    radio = 0.0;
	    rutaCirculos = "./todos.jpg";
	}
	Mat gray = new Mat();
	Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
	// Aplicar desenfoque para reducir el ruido
	Imgproc.GaussianBlur(gray, gray, new Size(9, 9), 2, 2);

	// Detectar círculos utilizando la transformada de Hough
	Mat circles = new Mat();
	Imgproc.HoughCircles(gray, circles, Imgproc.HOUGH_GRADIENT, 1, gray.rows() / 25, 100, 25, 15, 50); // números
	// que hay
	// que jugar
	// para que
	// localize
	// los
	// circulos

	// Crear una lista para almacenar los círculos blancos detectados
	List<Point> whiteCircles = new ArrayList<>();

	// Verificar cada círculo detectado
	for (int i = 0; i < circles.cols(); i++) {
	    double[] circle = circles.get(0, i);
	    if (circle == null)
		continue;
	    Point center = new Point(Math.round(circle[0]), Math.round(circle[1]));
	    int radius = (int) Math.round(circle[2]);

	    // Crear una máscara para el círculo
	    Mat mask = Mat.zeros(gray.size(), CvType.CV_8UC1);
	    Imgproc.circle(mask, center, radius, new Scalar(255, 255, 255), -1);

	    // Extraer la región del círculo de la imagen original
	    Mat circleROI = new Mat();
	    src.copyTo(circleROI, mask);

	    // Convertir la región del círculo a escala de grises y umbralizar
	    Mat circleGray = new Mat();
	    Imgproc.cvtColor(circleROI, circleGray, Imgproc.COLOR_BGR2GRAY);
	    Imgproc.threshold(circleGray, circleGray, 200, 255, Imgproc.THRESH_BINARY);

	    // Calcular la cantidad de píxeles blancos en el círculo
	    int whitePixels = Core.countNonZero(circleGray);

	    // Si la mayoría de los píxeles son blancos, añadimos el círculo a la lista de
	    // círculos blancos
	    if (whitePixels > (Math.PI * radius * radius * radio)) { // 70% de los píxeles son blancos Poner 0.6 para
								     // que 0.8
								     // pille más
		whiteCircles.add(center);
		// Dibujar el círculo detectado en la imagen original
		Imgproc.circle(src, center, radius, new Scalar(0, 255, 0), 3);
	    }

	    // Ordenar los círculos detectados de izquierda a derecha y de arriba a abajo
	    Collections.sort(whiteCircles, new Comparator<Point>() {
		@Override
		public int compare(Point p1, Point p2) {
		    if (p1.x > p2.x) {
			return -1;
		    } else if (p1.x < p2.x) {
			return 1;
		    } else {
			return Double.compare(p1.y, p2.x);
		    }
		}
	    });
	}
//	    // Mostrar resultados con numeración
//	    for (int ii = 0; ii < whiteCircles.size(); ii++) {
//		Point p = whiteCircles.get(ii);
//		Par pares = new Par(p.x, p.y);
//		lista.add(pares);
//		// Dibujar el número en el centro del círculo
//		Imgproc.putText(src, String.valueOf(ii + 1), new Point(p.x - radius / 2, p.y + radius / 2),
//			Imgproc.FONT_HERSHEY_PLAIN, 1, new Scalar(255, 0, 0), 2);
//	    }
//
//	}

//	Collections.sort(whiteCircles, new Comparator<Point>() {
//	    @Override
//	    public int compare(Point p1, Point p2) {
//		if (p1.y != p2.y) {
//		    return Double.compare(p1.y, p2.y);
//		} else {
//		    return Double.compare(p1.x, p2.x);
//		}
//	    }
//	});

	// Guardar la imagen resultante con los círculos detectados
	Imgcodecs.imwrite(rutaCirculos, src);

	// Mostrar resultados
	for (Point p : whiteCircles) {
	    Par pares = new Par(p.x, p.y);
	    lista.add(pares);
	}
	System.out.println("Lista maldita " + lista);

	return lista;

    }

}
