package correctorExamenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
    private static BusquedaCirculos busquedaCirculos = new BusquedaCirculos();

    private static JSONArray listaPlantillas;
    private static JLabel lblPlantillaCorrecion;
    private static JFrame frame;
    private static JLabel lblExamenCorrecion;
    private static JLabel lblNotaCalculada;
    private static JLabel lblAcertadas_2;
    private static JLabel lblFalladas_2;
    private static JLabel lblEnBlanco;
    private static JLabel lblNulas_2;
    private static JButton btnExamen;
    private JTextField tfIntroducirPlantilla;

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

    public PantallaPrincipal() throws JSONException, IOException {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 1091, 571);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.setLocationRelativeTo(null);

	setContentPane(contentPane);
	contentPane.setLayout(null);

	panel = new JPanel();
	panel.setBounds(-14, -29, 1088, 568);
	panel.setPreferredSize(new Dimension(450, 300));
	panel.setBackground(new Color(9, 37, 72));
	contentPane.add(panel);
	panel.setLayout(null);

	// Botón cargar y corregir examen
	btnExamen = new JButton();
	String texto = "Cargar y Corregir...";
	btnExamen.setText(texto);
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

	btnExamen.setBounds(283, 434, 206, 62);
	panel.add(btnExamen);

	// Botón cargar plantilla
	JButton btnPlantilla = new JButton("Cargar Plantilla...");
	btnPlantilla.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
	    }
	});
	btnPlantilla.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {

		try {
		    System.out.println("abrir");
		    try {
			listaPlantillas = utilidades.json(tfIntroducirPlantilla.getText());
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

	    }

	});
	btnPlantilla.setBounds(58, 471, 183, 25);
	panel.add(btnPlantilla);

	JLabel lblPlantilla = new JLabel("Plantilla de Correción:");
	lblPlantilla.setForeground(new Color(255, 255, 255));
	lblPlantilla.setBounds(37, 76, 235, 15);
	panel.add(lblPlantilla);

	lblPlantillaCorrecion = new JLabel("Plantilla ");
	lblPlantillaCorrecion.setForeground(Color.WHITE);
	lblPlantillaCorrecion.setBounds(37, 104, 1039, 30);
	panel.add(lblPlantillaCorrecion);

	lblExamenCorrecion = new JLabel("Examen a corregir");
	lblExamenCorrecion.setFont(new Font("Dialog", Font.BOLD, 12));
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

	lblNotaCalculada = new JLabel("Sin Calificación");
	lblNotaCalculada.setFont(new Font("Dialog", Font.BOLD, 16));
	lblNotaCalculada.setForeground(Color.WHITE);
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

	lblNulas_2 = new JLabel("0");
	lblNulas_2.setForeground(Color.WHITE);
	lblNulas_2.setBounds(612, 316, 88, 15);
	panel.add(lblNulas_2);

	lblEnBlanco = new JLabel("0");
	lblEnBlanco.setForeground(Color.WHITE);
	lblEnBlanco.setBounds(612, 289, 88, 15);
	panel.add(lblEnBlanco);

	lblFalladas_2 = new JLabel("0");
	lblFalladas_2.setForeground(Color.WHITE);
	lblFalladas_2.setBounds(612, 262, 88, 15);
	panel.add(lblFalladas_2);

	lblAcertadas_2 = new JLabel("0");
	lblAcertadas_2.setForeground(Color.WHITE);
	lblAcertadas_2.setBounds(612, 237, 88, 15);
	panel.add(lblAcertadas_2);

	// Selecciona el texto de la ventana de numero de plantilla
	tfIntroducirPlantilla = new JTextField();
	tfIntroducirPlantilla.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent arg0) {
		tfIntroducirPlantilla.selectAll();
		tfIntroducirPlantilla.getSelectedText();
	    }
	});
	tfIntroducirPlantilla.addFocusListener(new FocusAdapter() {
	    @Override
	    public void focusGained(FocusEvent arg0) {
		tfIntroducirPlantilla.selectAll();
		tfIntroducirPlantilla.getSelectedText();
	    }
	});
	tfIntroducirPlantilla.setText("Introducir N.º Plantilla");
	tfIntroducirPlantilla.setBounds(58, 434, 183, 25);
	panel.add(tfIntroducirPlantilla);
	tfIntroducirPlantilla.setColumns(10);

	JLabel lblNmeroDePlantilla = new JLabel("Número de Plantilla:");
	lblNmeroDePlantilla.setForeground(Color.WHITE);
	lblNmeroDePlantilla.setBounds(58, 417, 235, 15);
	panel.add(lblNmeroDePlantilla);

	JLabel lblExamen_1_1 = new JLabel("Examen Alumno");
	lblExamen_1_1.setForeground(Color.WHITE);
	lblExamen_1_1.setBounds(283, 417, 235, 15);
	panel.add(lblExamen_1_1);

    }

    public void abrirExamen() {
	try {
	    System.out.println("abrir");
	    try {
		// poner todo a cero
		lblNotaCalculada.setText(String.valueOf("Sin Calificación"));
		lblAcertadas_2.setText(String.valueOf("0"));
		lblFalladas_2.setText(String.valueOf("0"));
		lblEnBlanco.setText(String.valueOf("0"));
		lblNulas_2.setText(String.valueOf("0"));

		lblExamenCorrecion.setFont(new Font("Dialog", Font.BOLD, 12));
		lblExamenCorrecion.setForeground(Color.YELLOW);
		lblExamenCorrecion.setText("Cargando y corrigiendo examen");
		DialogoFicheros dialogoFicheros = new DialogoFicheros();
		String rutaExamen = dialogoFicheros.abrirExplorador();

		// BusquedaCirculos busquedaCirculos = new BusquedaCirculos();
		Map<Integer, String> examenalumnoMap = busquedaCirculos.buscarRespuestas(rutaExamen);
		if (!examenalumnoMap.isEmpty()) {
		    Map<String, String> notaFinal = busquedaCirculos.calcularNota(listaPlantillas);
		    lblNotaCalculada.setForeground(Color.RED);
		    lblNotaCalculada.setText(String.valueOf(notaFinal.get("notaFinal")));
		    lblAcertadas_2.setText(String.valueOf(notaFinal.get("aciertos")));
		    lblFalladas_2.setText(String.valueOf(notaFinal.get("fallos")));
		    lblEnBlanco.setText(String.valueOf(notaFinal.get("blanco")));
		    lblNulas_2.setText(String.valueOf(notaFinal.get("nulas")));

		    if (Double.valueOf(notaFinal.get("notaFinal")) >= 5) {
			lblNotaCalculada.setForeground(Color.green);
		    }

		    lblExamenCorrecion.setForeground(Color.WHITE);
		    lblExamenCorrecion.setText(examenalumnoMap.toString());
		} else {
		    lblNotaCalculada.setText("Nota de Examen");
		    lblNotaCalculada.setForeground(Color.WHITE);
		    lblExamenCorrecion.setForeground(Color.RED);
		    lblExamenCorrecion.setText("Imagen no válida. Por favor Introduzca una imagen centrada");
		    lblExamenCorrecion.setFont(new Font("Dialog", Font.BOLD, 16));

		}

	    } catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	} catch (Exception e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

    }
}
