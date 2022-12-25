package VT_YEDEK;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSplitPane;

public class BASLA extends JFrame {

	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BASLA frame = new BASLA();
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
	public BASLA() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(100,0));
		panel.setLayout(new BorderLayout(0, 0));
		splitPane.setLeftComponent(panel);
	
		
		  JPanel pList = new JPanel(new GridLayout(0, 1, 3, 3));
	        pList.setBorder(new TitledBorder("GridLayout"));
        
	        JScrollPane scrollPane = new JScrollPane(pList,
	                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        
	    	splitPane.setRightComponent(scrollPane);
	    	
	    	
	    	JButton btnNewButton_1 = new JButton("New button");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				     for (int ii = 1; ii < 6; ii++) {
				            pList.add(GOREV.getShowRoomPanel(ii,"hhh"));
				        }
				     scrollPane.revalidate();
				     scrollPane.repaint();
				}
			});
			panel.add(btnNewButton_1, BorderLayout.CENTER);
		//
	
		
	}
	
}
