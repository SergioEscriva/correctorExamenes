package correctorExamenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PantallaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

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
     */
    public PantallaPrincipal() {
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
	btnPlantilla.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
	    }
	});
	btnPlantilla.setBounds(68, 198, 117, 25);
	panel.add(btnPlantilla);
    }
}
