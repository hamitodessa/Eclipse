package OBS_PACKAGE;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class PDF_GOSTERR extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PDF_GOSTER frame = new PDF_GOSTER();
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
	public PDF_GOSTERR() {
		setClosable(true);
		setTitle("PDF GOSTER");
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setBounds(0, 0, 1000, 600);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		// System.out.println("fdsfdsfsd");
		
	
      
	}

}
