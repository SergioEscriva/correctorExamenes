package correctorExamenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONException;

import utilidades.Utilidades;

public class PantallaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static Utilidades utilidades = new Utilidades();
    private static JSONArray listaPlantillas;
    private static JLabel lblPlantillaCorrecion;
    private static JFrame frame;

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

	JPanel panel = new JPanel();
	panel.setBounds(-14, -29, 1088, 568);
	panel.setPreferredSize(new Dimension(450, 300));
	panel.setBackground(new Color(9, 37, 72));
	contentPane.add(panel);
	panel.setLayout(null);

	JButton btnExamen = new JButton("Cargar Examen...");
	btnExamen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
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

	JButton btnCorregir = new JButton("Corregir Exámen");
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

	JLabel lblExamenCorrecion = new JLabel("Examen a corregir");
	lblExamenCorrecion.setForeground(Color.WHITE);
	lblExamenCorrecion.setBounds(37, 156, 1039, 30);
	panel.add(lblExamenCorrecion);
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
	JButton nuevaPlantillaButton = new JButton("Abrir");
	nuevaPlantillaButton.addMouseListener(new MouseAdapter() {
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
		frame.setVisible(false);
	    }
	});
	nuevaPlantillaButton.setBounds(20, 80, 80, 25);
	panel.add(nuevaPlantillaButton);

	JButton registerButton = new JButton("Nuevo Código");
	registerButton.setBounds(125, 80, 80, 25);
	panel.add(registerButton);
    }
}
