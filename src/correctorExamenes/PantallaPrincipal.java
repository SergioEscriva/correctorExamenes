package correctorExamenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONException;

import utilidades.DialogoFicheros;
import utilidades.Utilidades;

public class PantallaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panel;
    private static Utilidades utilidades = new Utilidades();
    private static JSONArray listaPlantillas;
    private static JLabel lblPlantillaCorrecion;
    private static JFrame frame;
    private static JLabel lblExamenCorrecion;
    private static JLabel lblNotaCalculada;
    private static JButton btnExamen;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    PantallaPrincipal frame = new PantallaPrincipal();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     * 
     * @throws JSONException
     * @throws IOException
     */
    public PantallaPrincipal() throws JSONException, IOException {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 1091, 571);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	setContentPane(contentPane);
	contentPane.setLayout(null);

	panel = new JPanel();
	panel.setBounds(-14, -29, 1088, 568);
	panel.setPreferredSize(new Dimension(450, 300));
	panel.setBackground(new Color(9, 37, 72));
	contentPane.add(panel);
	panel.setLayout(null);

	btnExamen = new JButton("Cargar Examen...");
	btnExamen.setEnabled(false);
	btnExamen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    }
	});
	btnExamen.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		abrirExamen();
	    }
	});

	btnExamen.setBounds(58, 508, 183, 25);
	panel.add(btnExamen);

	JButton btnPlantilla = new JButton("Cargar Plantilla...");
	btnPlantilla.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
	    }
	});
	btnPlantilla.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		crearVentanaExamen();
	    }
	});
	btnPlantilla.setBounds(58, 471, 183, 25);
	panel.add(btnPlantilla);

	JButton btnCorregir = new JButton("Corregir Examen");
	btnCorregir.setBounds(316, 471, 220, 62);
	panel.add(btnCorregir);

	JLabel lblPlantilla = new JLabel("Plantilla Número ??????");
	lblPlantilla.setForeground(new Color(255, 255, 255));
	lblPlantilla.setBounds(37, 76, 235, 15);
	panel.add(lblPlantilla);

	lblPlantillaCorrecion = new JLabel("Plantilla Número ??????");
	lblPlantillaCorrecion.setForeground(Color.WHITE);
	lblPlantillaCorrecion.setBounds(37, 104, 1039, 30);
	panel.add(lblPlantillaCorrecion);

	lblExamenCorrecion = new JLabel("Examen a corregir");
	lblExamenCorrecion.setForeground(Color.WHITE);
	lblExamenCorrecion.setBounds(37, 156, 1039, 30);
	panel.add(lblExamenCorrecion);

	JLabel lblExamen_1 = new JLabel("Examen Alumno");
	lblExamen_1.setForeground(Color.WHITE);
	lblExamen_1.setBounds(37, 142, 235, 15);
	panel.add(lblExamen_1);

	JLabel lblNota = new JLabel("Nota de Examen");
	lblNota.setForeground(Color.WHITE);
	lblNota.setBounds(37, 224, 235, 15);
	panel.add(lblNota);

	lblNotaCalculada = new JLabel("Nota de Examen");
	lblNotaCalculada.setFont(new Font("Dialog", Font.BOLD, 16));
	lblNotaCalculada.setForeground(Color.RED);
	lblNotaCalculada.setBounds(37, 238, 259, 62);
	panel.add(lblNotaCalculada);

	JLabel lblAcertadas = new JLabel("Acertadas: ");
	lblAcertadas.setForeground(Color.WHITE);
	lblAcertadas.setBounds(509, 238, 88, 15);
	panel.add(lblAcertadas);

	JLabel lblFalladas = new JLabel("Falladas: ");
	lblFalladas.setForeground(Color.WHITE);
	lblFalladas.setBounds(509, 263, 88, 15);
	panel.add(lblFalladas);

	JLabel lblBlanco = new JLabel("En Blanco: ");
	lblBlanco.setForeground(Color.WHITE);
	lblBlanco.setBounds(509, 290, 88, 15);
	panel.add(lblBlanco);

	JLabel lblNulas = new JLabel("Nulas:  ");
	lblNulas.setForeground(Color.WHITE);
	lblNulas.setBounds(509, 317, 88, 15);
	panel.add(lblNulas);

	JLabel lblNulas_2 = new JLabel("Nulas");
	lblNulas_2.setForeground(Color.WHITE);
	lblNulas_2.setBounds(612, 316, 88, 15);
	panel.add(lblNulas_2);

	JLabel lblEnBlanco = new JLabel("En Blanco");
	lblEnBlanco.setForeground(Color.WHITE);
	lblEnBlanco.setBounds(612, 289, 88, 15);
	panel.add(lblEnBlanco);

	JLabel lblFalladas_2 = new JLabel("Falladas");
	lblFalladas_2.setForeground(Color.WHITE);
	lblFalladas_2.setBounds(612, 262, 88, 15);
	panel.add(lblFalladas_2);

	JLabel lblAcertadas_2 = new JLabel("Acertadas");
	lblAcertadas_2.setForeground(Color.WHITE);
	lblAcertadas_2.setBounds(612, 237, 88, 15);
	panel.add(lblAcertadas_2);
	// utilidades.codigoTest("00001");

    }

    public static void crearVentanaExamen() {
	frame = new JFrame("Recuperar Plantilla");
	frame.setSize(220, 150);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JPanel panel = new JPanel();
	frame.getContentPane().add(panel);
	placeComponents(panel);

	frame.setVisible(true);

    }

    private static void placeComponents(JPanel panel) {

	panel.setLayout(null);

	JLabel plantillaLabel = new JLabel("Código de Test");
	plantillaLabel.setBounds(20, 10, 200, 25);
	panel.add(plantillaLabel);

	JTextField plantillaText = new JTextField(20);
	plantillaText.setBounds(20, 35, 180, 25);
	panel.add(plantillaText);

	// Botón de abrir plantilla
	JButton cargarPlantillaButton = new JButton("Abrir");
	cargarPlantillaButton.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		try {
		    System.out.println("abrir");
		    try {
			listaPlantillas = utilidades.json(plantillaText.getText());
			lblPlantillaCorrecion.setText(listaPlantillas.toString());
		    } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		    }
		} catch (Exception e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
		btnExamen.setEnabled(true);
		frame.setVisible(false);
	    }
	});
	cargarPlantillaButton.setBounds(20, 80, 80, 25);
	panel.add(cargarPlantillaButton);

	JButton nuevaPlantillaButton = new JButton("Nueva Plantilla");
	nuevaPlantillaButton.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {

	    }
	});
	nuevaPlantillaButton.setBounds(125, 80, 80, 25);
	panel.add(nuevaPlantillaButton);
    }

    public void abrirExamen() {
	try {
	    System.out.println("abrir");
	    try {
		lblExamenCorrecion.setForeground(Color.YELLOW);
		lblExamenCorrecion.setText("Cargando y corrigiendo examen");
		DialogoFicheros dialogoFicheros = new DialogoFicheros();
		String rutaExamen = dialogoFicheros.abrirExplorador();

		BusquedaCirculos busquedaCirculos = new BusquedaCirculos();
		Map<Integer, String> examenalumnoMap = busquedaCirculos.buscarRespuestas(rutaExamen);

		double notaFinal = busquedaCirculos.calcularNota(listaPlantillas);
		lblNotaCalculada.setText(String.valueOf(notaFinal));
		if (notaFinal >= 5) {
		    lblNotaCalculada.setForeground(Color.green);
		}

		lblExamenCorrecion.setForeground(Color.WHITE);
		lblExamenCorrecion.setText(examenalumnoMap.toString());
	    } catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	} catch (Exception e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	frame.setVisible(false);
	panel.repaint();

    }
}
