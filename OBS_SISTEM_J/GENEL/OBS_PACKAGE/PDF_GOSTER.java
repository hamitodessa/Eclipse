package OBS_PACKAGE;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.text.Document;




public class PDF_GOSTER extends JInternalFrame {

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
	public PDF_GOSTER() {
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);

		
		
	}
	

}
