package OBS_2025;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TAH_DOKUM extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TAH_DOKUM() {
		setTitle("TAHSILAT DOKUM");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0,0, 1100, 600);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 30));
		panel.setMaximumSize(new Dimension(0, 30));
		panel.setLayout(null);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		
		JPanel panelalt = new JPanel();
		panelalt.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane_1.setRightComponent(panelalt);
		panelalt.setMinimumSize(new Dimension(0, 100));
		panelalt.setMaximumSize(new Dimension(0, 100));
		panelalt.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane_1.setLeftComponent(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
