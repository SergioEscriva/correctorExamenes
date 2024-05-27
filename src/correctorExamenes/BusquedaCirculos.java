package correctorExamenes; //src/correctorExamenes/examen2.jpg

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

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

public class BusquedaCirculos {
    static {
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    private static String imagePath;

    public static Map<Integer, String> buscarCirculos() throws JSONException, IOException {
	// Cargar la imagen
	String imagePathInv = "./bnarchivo-negro.jpg";//
	Mat src = Imgcodecs.imread(imagePathInv);

	if (src.empty()) {
	    System.out.println("No se pudo cargar la imagen");
	    return null;
	}

	// Convertir la imagen a escala de grises necesario para que se vea mejor
	Mat gray = new Mat();
	Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);

	// Contar la cantidad de píxeles blancos para saber si está mal escaneada
	int whitePixelsTotal = Core.countNonZero(gray);

	// Calcular el porcentaje de píxeles negros porque la imagen está en negativo
	double totalPixelsTotal = gray.rows() * gray.cols();
	double whitePercentage = (whitePixelsTotal / totalPixelsTotal) * 100;

	System.out.println("Cantidad de píxeles blancos: " + whitePixelsTotal);
	System.out.println("Porcentaje de píxeles blancos: " + whitePercentage + "%");

	if (whitePercentage < 24.6) {
	    JOptionPane.showMessageDialog(null,
		    "Error en la resolución de la imagen, intenta escanearla lo más centrada posible, gracias.",
		    "Resolución Erronea", JOptionPane.INFORMATION_MESSAGE);
	    PantallaPrincipal principal = new PantallaPrincipal();
	    return null;
	}

	// Aplicar desenfoque para reducir el ruido
	Imgproc.GaussianBlur(gray, gray, new Size(9, 9), 2, 2);

	// Detectar círculos utilizando la transformada de Hough
	Mat circles = new Mat();
	Imgproc.HoughCircles(gray, circles, Imgproc.HOUGH_GRADIENT, 1, gray.rows() / 25, 100, 30, 15, 50); // números
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
	    if (whitePixels > (Math.PI * radius * radius * 0.8)) { // 70% de los píxeles son blancos
		whiteCircles.add(center);
		// Dibujar el círculo detectado en la imagen original
		Imgproc.circle(src, center, radius, new Scalar(0, 255, 0), 3);
	    }
	}

	// Guardar la imagen resultante con los círculos detectados
	Imgcodecs.imwrite("./blancos.jpg", src);
	List<Par> lista = new ArrayList<>();

	// Mostrar resultados
	for (Point p : whiteCircles) {
	    Par pares = new Par(p.x, p.y);
	    lista.add(pares);
	}
	// pasa a la clase que buscará las letras de los circulos
	Busqueda busqueda = new Busqueda();
	Map<Integer, String> examenAlumno = busqueda.busquedaLetras(lista);
	return examenAlumno;

    }

    public static void invertirOscurecer() throws IOException, JSONException {
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
	buscarCirculos();

    }

    public static Map<Integer, String> buscarRespuestas() throws IOException, JSONException {
	imagePath = "src/correctorExamenes/examen3.jpg"; /// este es la imagen del examen que leerá

	// Crear una instancia de Tesseract
	ITesseract tesseract = new Tesseract();

	// Configurar la ruta del idioma (opcional)
	String datapath = "src/resources/tessdata_best";
	tesseract.setDatapath(new File(datapath).getPath());

	// Configurar el idioma
	tesseract.setLanguage("eng");

	// Configurar para buscar solo números
	tesseract.setTessVariable("tessedit_char_whitelist", "RESPUESTAS");

	try {
	    // Leer el archivo de imagen y convertirlo a BufferedImage
	    BufferedImage image = ImageIO.read(new File(imagePath));

	    // Realizar OCR en la imagen y obtener las palabras
	    List<Word> words = tesseract.getWords(image, TessAPI.TessPageIteratorLevel.RIL_WORD);

	    // Imprimir las coordenadas de cada palabra
	    for (Word word : words) {
		String text = word.getText();
		int x = word.getBoundingBox().x;
		int y = word.getBoundingBox().y;
		int width = word.getBoundingBox().width;
		int height = word.getBoundingBox().height;

		System.out.println("Palabra: " + text);
		System.out.println("Coordenadas: (" + x + ", " + y + ")");
		System.out.println("Tamaño: " + width + "x" + height);
		System.out.println();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	invertirOscurecer();
	Map<Integer, String> examenAlumno = buscarCirculos();
	return examenAlumno;
	////// leeeeeeee

    }
}
