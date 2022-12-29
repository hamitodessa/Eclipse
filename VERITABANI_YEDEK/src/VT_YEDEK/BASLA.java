package VT_YEDEK;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


import OBS_C_2025.GLOBAL;

import javax.swing.JSplitPane;

public class BASLA extends JFrame {

	private JPanel contentPane;
	GLOBAL glb = new GLOBAL();
	VT_ANA_CLASS oac = new VT_ANA_CLASS();
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
	 * @throws Exception 
	 */
	public BASLA() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(150,0));
		panel.setLayout(null);
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
			btnNewButton_1.setBounds(10, 204, 89, 23);
			panel.add(btnNewButton_1);
			
			JButton btnNewButton = new JButton("Yeni Emir");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					oac.yENI_EMIR = true ;
					EMIR emr = new EMIR();
				
					emr.setVisible(true);
					dispose();
				}
			});
			btnNewButton.setBounds(10, 26, 130, 23);
			panel.add(btnNewButton);
			
		//
			@SuppressWarnings("static-access")
			File tmpDir = new File(glb.SURUCU + glb.SQL_BACKUP);
			boolean exists = tmpDir.exists();
			if (exists == false )
			{   
				glb.backup_dosya_olustur();
			}
			
			oac.EMIR_ADI = "hamit";
	}
}