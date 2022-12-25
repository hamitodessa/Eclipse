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
				            pList.add(getShowRoomPanel(ii,"hhh"));
				        }
				     scrollPane.revalidate();
				     scrollPane.repaint();
				}
			});
			panel.add(btnNewButton_1, BorderLayout.CENTER);
		//
	
		
	}
	private  JPanel getShowRoomPanel(int num,String isim) {
	        JPanel p = new JPanel(new GridBagLayout());
	        p.setBorder(new TitledBorder("GridBagLayout"));

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5);
	        gbc.anchor = GridBagConstraints.WEST;

	        p.add(new JLabel("Name:Honda Showroom" + num + isim), gbc);

	        gbc.gridx = 1;
	        p.add(new JLabel("City:Mandsaur"), gbc);

	        gbc.gridx = 2;
	        p.add(new JLabel("Address:25 Chakrawati Colony Railway Station Road"), gbc);

	        gbc.gridy = 1;
	        gbc.gridx = 0;
	        p.add(new JLabel("Vehicle Name:Activa"), gbc);

	        gbc.gridx = 1;
	        p.add(new JLabel("Vehicle Version:2017"), gbc);

	        gbc.gridx = 2;
	        p.add(new JLabel("Vehicle Companies:Honda"), gbc);

	        gbc.gridy = 2;
	        gbc.gridx = 0;
	        JButton btnNewButton = new JButton("go");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					   JOptionPane.showMessageDialog(null,  "Name:Honda Showroom" + num,  "Eksik Kur Okuma", JOptionPane.ERROR_MESSAGE);
					
				}
			});
	        p.add(btnNewButton, gbc);

	        return p;
	    }
}
