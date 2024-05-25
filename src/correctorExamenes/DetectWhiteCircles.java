package correctorExamenes; //src/correctorExamenes/examen2.jpg

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class DetectWhiteCircles {
    static {
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) throws IOException {
	invertirOscurecer();

    }

    public static void buscarCirculos() {
	// Cargar la imagen
	String filePath = "./bnarchivo-negro.jpg";// "src/correctorExamenes/examen22.jpg";
	Mat src = Imgcodecs.imread(filePath);

	if (src.empty()) {
	    System.out.println("No se pudo cargar la imagen");
	    return;
	}

	// Convertir la imagen a escala de grises
	Mat gray = new Mat();
	Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);

	// Aplicar desenfoque para reducir el ruido
	Imgproc.GaussianBlur(gray, gray, new Size(9, 9), 2, 2);

	// Detectar círculos utilizando la transformada de Hough
	Mat circles = new Mat();
	Imgproc.HoughCircles(gray, circles, Imgproc.HOUGH_GRADIENT, 1, gray.rows() / 50, 100, 30, 15, 50);

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
	    if (whitePixels > (Math.PI * radius * radius * 0.7)) { // 70% de los píxeles son blancos
		whiteCircles.add(center);
		// Dibujar el círculo detectado en la imagen original
		Imgproc.circle(src, center, radius, new Scalar(0, 255, 0), 3);
	    }
	}

	// Guardar la imagen resultante con los círculos detectados
	Imgcodecs.imwrite("./blancos.jpg", src);

	// Mostrar resultados
	for (Point p : whiteCircles) {
	    System.out.println("Círculo blanco detectado en: (" + p.x + ", " + p.y + ")");
	}
    }

    public static void invertirOscurecer() throws IOException {
	File file = new File("src/correctorExamenes/examen2.jpg");
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
	}

	ImageIO.write(imagenNegra, "jpg", new File("./bnarchivo-negro.jpg"));
	buscarCirculos();

    }
}
