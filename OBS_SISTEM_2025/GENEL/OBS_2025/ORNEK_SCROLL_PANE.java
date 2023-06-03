package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ORNEK_SCROLL_PANE extends JInternalFrame {

	static JSplitPane splitPane ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ORNEK_SCROLL_PANE frame = new ORNEK_SCROLL_PANE();
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
	public ORNEK_SCROLL_PANE() {
		
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(0, 0, 1063, 600);
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new LineBorder(new Color(0, 191, 255)));
		leftPanel.setMinimumSize(new Dimension(0, 50));
		leftPanel.setMaximumSize(new Dimension(0, 50));
		JScrollPane centerPanel = new JScrollPane();
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new LineBorder(new Color(0, 191, 255)));
		rightPanel.setMinimumSize(new Dimension(0, 20));
		rightPanel.setMaximumSize(new Dimension(0, 20));
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, leftPanel, centerPanel);
		leftPanel.setLayout(null);
		JSplitPane sp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp, rightPanel);
		rightPanel.setLayout(null);
		sp2.setDividerSize(1);
		sp2.setResizeWeight(1.0);
		getContentPane().add(sp2, BorderLayout.CENTER);
		
	
	}

}
