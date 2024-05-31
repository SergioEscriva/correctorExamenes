package utilidades;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogoFicheros extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    DialogoFicheros frame = new DialogoFicheros();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    public DialogoFicheros() {
	getContentPane().setLayout(null);

	JPanel Jpanel1 = new JPanel();
	Jpanel1.setBounds(0, 0, 428, 118);
	getContentPane().add(Jpanel1);
	Jpanel1.setLayout(null);

	JButton botonOpen = new JButton("Abrir");
	botonOpen.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		abrirExplorador();
	    }
	});
	botonOpen.setBounds(90, 66, 117, 25);
	Jpanel1.add(botonOpen);

	JButton botonSave = new JButton("Guardar");
	botonOpen.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		abrirExplorador();
	    }
	});
	botonSave.setBounds(230, 66, 117, 25);
	Jpanel1.add(botonSave);

	JLabel label1 = new JLabel("Selecciona Exámen en JPG para Corregir");
	label1.setBounds(90, 12, 234, 15);
	Jpanel1.add(label1);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 443, 150);
	contentPane = new JPanel();
    }

    // Abre el Explorador de archivos ....
    public String abrirExplorador() {
	JFileChooser chooser = new JFileChooser();

	// Filtro para que muestre sólo los zip
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes jpg", "jpg");
	chooser.setFileFilter(filter);

	int sel = chooser.showOpenDialog(contentPane);
	if (sel == JFileChooser.APPROVE_OPTION) {
	    File selectedFile = chooser.getSelectedFile();

	    // JOptionPane.showMessageDialog(contentPane, selectedFile.getAbsolutePath());

	    return selectedFile.getAbsolutePath();
	} else {
	    return null;
	}
    }

}
