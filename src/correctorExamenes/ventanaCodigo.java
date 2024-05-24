package correctorExamenes;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ventanaCodigo {

    public static void main(String[] args) {
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

	JLabel userLabel = new JLabel("Código de Test");
	userLabel.setBounds(20, 10, 200, 25);
	panel.add(userLabel);

	JTextField userText = new JTextField(20);
	userText.setBounds(20, 35, 180, 25);
	panel.add(userText);

	JButton loginButton = new JButton("Abrir");
	loginButton.setBounds(20, 80, 80, 25);
	panel.add(loginButton);

	JButton registerButton = new JButton("Nuevo Código");
	registerButton.setBounds(125, 80, 80, 25);
	panel.add(registerButton);
    }

}
