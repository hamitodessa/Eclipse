package KER_RAPOR;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class KER_GRUP_RAPOR extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KER_GRUP_RAPOR frame = new KER_GRUP_RAPOR();
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
	public KER_GRUP_RAPOR() {
		setTitle("KERESTE GRUP RAPOR");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1100,800);

	}

}
