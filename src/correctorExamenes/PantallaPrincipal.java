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
    private static BuscarCirculos busquedaCirculos = new BuscarCirculos();

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

    private static JLabel lblExamen_1;
    private static JLabel lblNota;
    private static JLabel lblAcertadas;
    private static JLabel lblFalladas;
    private static JLabel lblNulas;
    private static JLabel lblBlanco;
    private static JLabel lblPlantilla;

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
	setBounds(100, 100, 523, 571);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.setLocationRelativeTo(null);

	setContentPane(contentPane);
	contentPane.setLayout(null);

	panel = new JPanel();
	panel.setBounds(0, 0, 520, 539);
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

		lblExamen_1.setVisible(true);
		abrirExamen();

	    }
	});

	btnExamen.setBounds(284, 434, 206, 62);
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

		    try {
			listaPlantillas = utilidades.json(tfIntroducirPlantilla.getText());
			String numeroPlantillaString = tfIntroducirPlantilla.getText();
			lblPlantillaCorrecion.setText("Plantilla " + numeroPlantillaString + " cargada correctamente.");
			lblPlantillaCorrecion.setBackground(Color.GREEN);
		    } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		    }
		} catch (Exception e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
		btnExamen.setEnabled(true);
		lblPlantilla.setVisible(true);
		lblPlantillaCorrecion.setVisible(true);
	    }

	});
	btnPlantilla.setBounds(33, 471, 183, 25);
	panel.add(btnPlantilla);

	lblPlantilla = new JLabel("Plantilla de correción:");
	lblPlantilla.setVisible(false);
	lblPlantilla.setForeground(new Color(255, 255, 255));
	lblPlantilla.setBounds(33, 70, 235, 15);
	panel.add(lblPlantilla);

	lblPlantillaCorrecion = new JLabel("Cargar plantilla para comenzar a corregir. ");
	lblPlantillaCorrecion.setVisible(false);
	lblPlantillaCorrecion.setForeground(Color.WHITE);
	lblPlantillaCorrecion.setBounds(33, 86, 471, 30);
	panel.add(lblPlantillaCorrecion);

	lblExamenCorrecion = new JLabel("Cargar examen para comenzar a corregir.");
	lblExamenCorrecion.setVisible(false);
	lblExamenCorrecion.setFont(new Font("Dialog", Font.BOLD, 12));
	lblExamenCorrecion.setForeground(Color.WHITE);
	lblExamenCorrecion.setBounds(33, 150, 475, 30);
	panel.add(lblExamenCorrecion);

	lblExamen_1 = new JLabel("Examen a corregir:");
	lblExamen_1.setVisible(false);
	lblExamen_1.setForeground(Color.WHITE);
	lblExamen_1.setBounds(33, 138, 235, 15);
	panel.add(lblExamen_1);

	lblNota = new JLabel("Nota de Examen");
	lblNota.setVisible(false);
	lblNota.setForeground(Color.WHITE);
	lblNota.setBounds(94, 204, 125, 15);
	panel.add(lblNota);

	lblNotaCalculada = new JLabel("Sin Calificación");
	lblNotaCalculada.setVisible(false);
	lblNotaCalculada.setFont(new Font("Dialog", Font.BOLD, 16));
	lblNotaCalculada.setForeground(Color.WHITE);
	lblNotaCalculada.setBounds(94, 220, 139, 62);
	panel.add(lblNotaCalculada);

	lblAcertadas = new JLabel("Acertadas: ");
	lblAcertadas.setVisible(false);
	lblAcertadas.setForeground(Color.WHITE);
	lblAcertadas.setBounds(299, 205, 88, 15);
	panel.add(lblAcertadas);

	lblFalladas = new JLabel("Falladas: ");
	lblFalladas.setVisible(false);
	lblFalladas.setForeground(Color.WHITE);
	lblFalladas.setBounds(299, 230, 88, 15);
	panel.add(lblFalladas);

	lblBlanco = new JLabel("En Blanco: ");
	lblBlanco.setVisible(false);
	lblBlanco.setForeground(Color.WHITE);
	lblBlanco.setBounds(299, 257, 88, 15);
	panel.add(lblBlanco);

	lblNulas = new JLabel("Nulas:  ");
	lblNulas.setVisible(false);
	lblNulas.setForeground(Color.WHITE);
	lblNulas.setBounds(299, 284, 88, 15);
	panel.add(lblNulas);

	lblNulas_2 = new JLabel("0");
	lblNulas_2.setVisible(false);
	lblNulas_2.setForeground(Color.WHITE);
	lblNulas_2.setBounds(402, 283, 88, 15);
	panel.add(lblNulas_2);

	lblEnBlanco = new JLabel("0");
	lblEnBlanco.setVisible(false);
	lblEnBlanco.setForeground(Color.WHITE);
	lblEnBlanco.setBounds(402, 256, 88, 15);
	panel.add(lblEnBlanco);

	lblFalladas_2 = new JLabel("0");
	lblFalladas_2.setVisible(false);
	lblFalladas_2.setForeground(Color.WHITE);
	lblFalladas_2.setBounds(402, 229, 88, 15);
	panel.add(lblFalladas_2);

	lblAcertadas_2 = new JLabel("0");
	lblAcertadas_2.setVisible(false);
	lblAcertadas_2.setForeground(Color.WHITE);
	lblAcertadas_2.setBounds(402, 204, 88, 15);
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
	tfIntroducirPlantilla.setText("00001"); // "Introducir N.º Plantilla"
	tfIntroducirPlantilla.setBounds(33, 434, 183, 25);
	panel.add(tfIntroducirPlantilla);
	tfIntroducirPlantilla.setColumns(10);

	JLabel lblNmeroDePlantilla = new JLabel("Número de Plantilla:");
	lblNmeroDePlantilla.setForeground(Color.WHITE);
	lblNmeroDePlantilla.setBounds(33, 417, 235, 15);
	panel.add(lblNmeroDePlantilla);

	JLabel lblExamen_1_1 = new JLabel("Examen Alumno");
	lblExamen_1_1.setForeground(Color.WHITE);
	lblExamen_1_1.setBounds(284, 417, 189, 15);
	panel.add(lblExamen_1_1);

	JLabel lblTitulo = new JLabel("Corrector de Exámenes");
	lblTitulo.setFont(new Font("Dialog", Font.BOLD, 24));
	lblTitulo.setForeground(new Color(246, 245, 244));
	lblTitulo.setBounds(95, 12, 325, 29);
	panel.add(lblTitulo);

    }

    public void abrirExamen() {
	try {

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
		lblExamenCorrecion.setVisible(true);
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

		    lblExamenCorrecion.setText("Examen cargado correctamente.");
		} else {
		    lblNotaCalculada.setText("Nota de Examen");
		    lblNotaCalculada.setForeground(Color.WHITE);
		    lblExamenCorrecion.setForeground(Color.RED);
		    lblExamenCorrecion.setVisible(true);
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

	lblNota.setVisible(true);
	lblAcertadas.setVisible(true);
	lblFalladas.setVisible(true);
	lblFalladas.setVisible(true);
	lblNulas.setVisible(true);
	lblNotaCalculada.setVisible(true);
	lblNulas_2.setVisible(true);
	lblEnBlanco.setVisible(true);
	lblFalladas_2.setVisible(true);
	lblAcertadas_2.setVisible(true);
	lblBlanco.setVisible(true);

    }
}
