package correctorExamenes; // "src/correctorExamenes/xi-img-respuestas-examen-3-2.jpg"

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class FindRectangles {
    static {
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
	// Cargar la imagen
	String imagePath = "src/correctorExamenes/xi-img-respuestas-examen-3-2.jpg"; // Cambia esta ruta a la ubicación
										     // de tu imagen
	Mat src = Imgcodecs.imread(imagePath);

	if (src.empty()) {
	    System.out.println("No se pudo cargar la imagen");
	    return;
	}

	// Convertir la imagen a escala de grises
	Mat gray = new Mat();
	Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);

	// Aplicar desenfoque para reducir el ruido
	Imgproc.GaussianBlur(gray, gray, new Size(5, 5), 0);

	// Aplicar detección de bordes
	Mat edges = new Mat();
	Imgproc.Canny(gray, edges, 50, 150);

	// Encontrar los contornos en la imagen
	List<MatOfPoint> contours = new ArrayList<>();
	Mat hierarchy = new Mat();
	Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

	// Filtrar contornos que no son lo suficientemente grandes
	List<Rect> rectangles = new ArrayList<>();
	for (MatOfPoint contour : contours) {
	    Rect rect = Imgproc.boundingRect(contour);
	    if (rect.area() > 1000) {
		rectangles.add(rect);
	    }
	}

	// Suponiendo que las esquinas se encuentran en un orden (podría necesitar
	// ajuste dependiendo de la detección)
	if (rectangles.size() < 4) {
	    System.out.println("No se detectaron suficientes recuadros.");
	    return;
	}

	// Ordenar los rectángulos de izquierda a derecha, de arriba a abajo
	rectangles.sort((r1, r2) -> {
	    if (r1.y != r2.y) {
		return Integer.compare(r1.y, r2.y);
	    } else {
		return Integer.compare(r1.x, r2.x);
	    }
	});

	// Obtener las esquinas de los recuadros 1 y 4
	Rect rect1 = rectangles.get(0);
	Rect rect4 = rectangles.get(3);

	// Esquinas del recuadro 1
	Point topLeft1 = new Point(rect1.x, rect1.y);
	Point topRight1 = new Point(rect1.x + rect1.width, rect1.y);
	Point bottomLeft1 = new Point(rect1.x, rect1.y + rect1.height);
	Point bottomRight1 = new Point(rect1.x + rect1.width, rect1.y + rect1.height);

	// Esquinas del recuadro 4
	Point topLeft4 = new Point(rect4.x, rect4.y);
	Point topRight4 = new Point(rect4.x + rect4.width, rect4.y);
	Point bottomLeft4 = new Point(rect4.x, rect4.y + rect4.height);
	Point bottomRight4 = new Point(rect4.x + rect4.width, rect4.y + rect4.height);

	// Calcular la distancia desde el borde exterior de la imagen
	int imageWidth = src.width();
	int imageHeight = src.height();

	double distanceTopLeft1 = distance(topLeft1, new Point(0, 0));
	double distanceTopRight1 = distance(topRight1, new Point(imageWidth, 0));
	double distanceBottomLeft1 = distance(bottomLeft1, new Point(0, imageHeight));
	double distanceBottomRight1 = distance(bottomRight1, new Point(imageWidth, imageHeight));

	double distanceTopLeft4 = distance(topLeft4, new Point(0, 0));
	double distanceTopRight4 = distance(topRight4, new Point(imageWidth, 0));
	double distanceBottomLeft4 = distance(bottomLeft4, new Point(0, imageHeight));
	double distanceBottomRight4 = distance(bottomRight4, new Point(imageWidth, imageHeight));

	System.out.println("Distancia desde la esquina superior izquierda del recuadro 1 al borde superior izquierdo: "
		+ distanceTopLeft1);
	System.out.println("Distancia desde la esquina superior derecha del recuadro 1 al borde superior derecho: "
		+ distanceTopRight1);
	System.out.println("Distancia desde la esquina inferior izquierda del recuadro 1 al borde inferior izquierdo: "
		+ distanceBottomLeft1);
	System.out.println("Distancia desde la esquina inferior derecha del recuadro 1 al borde inferior derecho: "
		+ distanceBottomRight1);

	System.out.println("Distancia desde la esquina superior izquierda del recuadro 4 al borde superior izquierdo: "
		+ distanceTopLeft4);
	System.out.println("Distancia desde la esquina superior derecha del recuadro 4 al borde superior derecho: "
		+ distanceTopRight4);
	System.out.println("Distancia desde la esquina inferior izquierda del recuadro 4 al borde inferior izquierdo: "
		+ distanceBottomLeft4);
	System.out.println("Distancia desde la esquina inferior derecha del recuadro 4 al borde inferior derecho: "
		+ distanceBottomRight4);
    }

    // Método para calcular la distancia entre dos puntos
    public static double distance(Point p1, Point p2) {
	return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
}