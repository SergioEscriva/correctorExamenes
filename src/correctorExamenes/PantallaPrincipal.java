package correctorExamenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.json.JSONException;

import utilidades.Utilidades;

public class PantallaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static Utilidades utilidades = new Utilidades();

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
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	setContentPane(contentPane);
	contentPane.setLayout(null);

	JPanel panel = new JPanel();
	panel.setBounds(-14, -29, 463, 300);
	panel.setPreferredSize(new Dimension(450, 300));
	panel.setBackground(new Color(9, 37, 72));
	contentPane.add(panel);
	panel.setLayout(null);

	JButton btnCorregir = new JButton("Corregir...");
	btnCorregir.setBounds(278, 198, 117, 25);
	panel.add(btnCorregir);

	JButton btnPlantilla = new JButton("Plantilla...");
	btnPlantilla.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		crearVentanaExamen();
	    }
	});
	btnPlantilla.setBounds(68, 198, 117, 25);
	panel.add(btnPlantilla);
	// utilidades.codigoTest("00001");

    }

    public void crearVentanaExamen() {
	JFrame frame = new JFrame("Demo application");
	frame.setSize(220, 150);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JPanel panel = new JPanel();
	frame.add(panel);
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

	JButton nuevaPlantillaButton = new JButton("Abrir");
	nuevaPlantillaButton.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		try {
		    System.out.println("abrir");
		    utilidades.json(plantillaText.getText());
		} catch (Exception e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }
	});
	nuevaPlantillaButton.setBounds(20, 80, 80, 25);
	panel.add(nuevaPlantillaButton);

	JButton registerButton = new JButton("Nuevo Código");
	registerButton.setBounds(125, 80, 80, 25);
	panel.add(registerButton);
    }

}
