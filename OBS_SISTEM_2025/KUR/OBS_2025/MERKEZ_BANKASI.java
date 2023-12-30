package OBS_2025;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MERKEZ_BANKASI extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MERKEZ_BANKASI frame = new MERKEZ_BANKASI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MERKEZ_BANKASI() {
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("MERKEZ");
		setBounds(100, 100, 800, 600);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0,50));
		splitPane.setLeftComponent(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
	      try {
	         editorPane.setPage("https://www.tutorialspoint.com");
	      } catch (IOException e) {
	         editorPane.setContentType("text/html");
	         editorPane.setText("<html>Connection issues!</html>");
	      }
		
		
		scrollPane.add(editorPane );
	}

}
