package VT_YEDEK;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


import OBS_C_2025.GLOBAL;
import OBS_C_2025.SQL_BACKUP;

import javax.swing.JSplitPane;

public class BASLA extends JFrame {
	static SQL_BACKUP sqll = new SQL_BACKUP();
	private JPanel contentPane;
	GLOBAL glb = new GLOBAL();
	public   JPanel pPanel;
	public  static List<String> gorevLER  = new ArrayList<>();
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
		setBounds(100, 100, 900, 600);
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
		 pPanel = new JPanel(new GridLayout(0, 1, 3, 3));
	        pPanel.setBorder(new TitledBorder("Emirler"));
        
	        JScrollPane scrollPane = new JScrollPane(pPanel,
	                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        
	    	splitPane.setRightComponent(scrollPane);
	    	
	    	
	    	JButton btnNewButton_1 = new JButton("New button");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				  //   for (int ii = 1; ii < 6; ii++) {
				//            pPanel.add(GOREV.getShowRoomPanel("hamit","Yedeklendi"  , 8  ,"05.01.2022 15.04"  ,"06.01.2023 15.04"   ,"C:\\OBS_SISTEM\\","Deneme Yuklemesi" ,"Aktiv"));
				      //  }
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
			
			JButton btnNewButton_2 = new JButton("olan emir");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					oac.yENI_EMIR = false ;
					VT_ANA_CLASS.EMIR_ADI="hamit" ;
					EMIR emr = new EMIR();
				
					emr.setVisible(true);
					dispose();
				}
			});
			btnNewButton_2.setBounds(10, 60, 130, 23);
			panel.add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("deger degis");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Component[] components = pPanel.getComponents();
					/////////////////////
					 String compoName;
					 for (Component compoo : components) {
				            compoName = compoo.getClass().getName();
						                if (compoo  instanceof JPanel)
					                {
					                   JPanel dp = (JPanel) compoo;
					                   String emirAdi = "hamit";
					                System.out.println(   ((TitledBorder) dp.getBorder()).getTitle());
					                if( ((TitledBorder) dp.getBorder()).getTitle().equals(emirAdi))
					                {
					           //     Font font2 = new Font("Verdana", Font.ITALIC, 12);
					          //         TitledBorder border = new TitledBorder("hamit");
					          //         border.setTitleFont(font2);
					                   ((TitledBorder) dp.getBorder()).setTitle("Hamit Emir");
					                }
					                dp.repaint();
					                }
				            ///
					 }
					
					/////////////////////
					
					Component[] emirPanel = ((Container) components[0]).getComponents();
					
					 String componentName;
					 for (Component compo : emirPanel) {
				            componentName = compo.getClass().getName();
					         //   System.out.println(compo.getClass().getName().substring(componentName.indexOf("swing.") + "swing.".length(), componentName.length()));
					           ///
					                if (compo  instanceof JButton)
					                {
					                   JButton dp = (JButton) compo;
					                   System.out.println(dp.getText());
					               dp.setText("Deneme");
					                }
				            ///
					 }
					       
				}
			});
			btnNewButton_3.setBounds(10, 275, 89, 23);
			panel.add(btnNewButton_3);
			
			JButton btnNewButton_4 = new JButton("emiradi donus");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			
					for (int i = 0; i < gorevLER.size(); i++) {
					    String stringss = gorevLER.get(i);
					   
					    System.out.println(stringss);
					}
				
				}
			});
			btnNewButton_4.setBounds(10, 330, 130, 23);
			panel.add(btnNewButton_4);
			
		//
			@SuppressWarnings("static-access")
			File tmpDir = new File(glb.SURUCU + glb.SQL_BACKUP);
			boolean exists = tmpDir.exists();
			if (exists == false )
			{   
				glb.backup_dosya_olustur();
			}
			
			oac.EMIR_ADI = "hamit";
			
			emirDOLDUR();
	}
	public void emirDOLDUR() throws ClassNotFoundException, SQLException, InterruptedException
	{
		ResultSet rss ;
		rss = sqll.emirLER();

		pPanel.removeAll();
		pPanel.revalidate();
		pPanel.repaint();
		if (!rss.isBeforeFirst() ) {  
			return;
		} 

		while(rss.next())
		{
			String  tarih ="" ;
			if(rss.getDate("SON_YUKLEME") != null)
			{
				SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				tarih = f.format(	rss.getDate("SON_YUKLEME"));
			}
		
			pPanel.add(GOREV.getShowRoomPanel(rss.getString("EMIR_ISMI"),rss.getBoolean("SON_DURUM") == true ? "Yedeklendi" : "Yedeklenmedi"  , 
					sqll.dbSAYISI(rss.getString("EMIR_ISMI"))  ,
					tarih,"06.01.2023 15.04"   ,rss.getString("EMIR_ACIKLAMA") ,rss.getBoolean("DURUM") == true ? "Aktiv" : "Pasiv",
							sqll.ftp_NERESI(rss.getString("EMIR_ISMI"))));
		}
	}
}
