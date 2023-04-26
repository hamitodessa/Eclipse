package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ETIKET extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ETIKET frame = new ETIKET();
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
	public ETIKET() {
		setTitle("ETIKET");
	
		setIconifiable(true);
		setResizable(true);
	
		setClosable(true);
		setBounds(0, 0, 500, 438);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(1);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);



	}

}
