package OBS_PACKAGE;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.ComponentOrientation;
import java.awt.Color;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LOGIN extends JDialog {

	String nusr = "hammit";
	boolean CAR_DOS_VAR;
	boolean KUR_DOS_VAR;
	boolean SMS_DOS_VAR;
	boolean ADR_DOS_VAR;
	boolean FAT_DOS_VAR;
	boolean KAM_DOS_VAR;
	boolean GUN_DOS_VAR;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtpwd;
	private JProgressBar progressBar;
	
	private static  JCheckBox chckbxhatirla;
	private JButton btndevam;
	private JButton btncdizin;
	CONNECTION CONN;
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGIN frame = new LOGIN();
					frame.setVisible(true);
					
				
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public LOGIN() throws IOException {
		
		LOGIN.setDefaultLookAndFeelDecorated(true);

		try {
			if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("Metal"))
			{
				MetalLookAndFeel.setCurrentTheme(new  DefaultMetalTheme());
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				oac.txt_yukseklik = 20;
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("Metal Klasik"))
			{
				MetalLookAndFeel.setCurrentTheme(new OceanTheme());
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				oac.txt_yukseklik = 20;
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("Windows"))
			{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				oac.txt_yukseklik = 20;
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("Nimbus"))
			{
				for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) 
					{
							javax.swing.UIManager.setLookAndFeel(info.getClassName());
							oac.txt_yukseklik = 25;
		            }
			        }

			}
		
		} 
		catch (Exception ex) 
		{
			   JOptionPane.showMessageDialog(null,  ex.getMessage().toString(),  "PRG_GORUNUM", JOptionPane.ERROR_MESSAGE);        
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(LOGIN.class.getResource("/ICONLAR/obs_p.png")));
		setResizable(false);
		setTitle("OBS SISTEM GIRIS");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 493, 229);
		
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setBorder(null);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
	
		
		JLabel lblicon = new JLabel("");
		lblicon.setBounds(29, 11, 137, 134);
		panel.add(lblicon);
		lblicon.setHorizontalAlignment(SwingConstants.CENTER);
		lblicon.setIcon(new ImageIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/renksiz.png")).getImage().getScaledInstance(134, 137, Image.SCALE_DEFAULT)));
		
		JLabel lblNewLabel = new JLabel("Kullanici");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBounds(229, 15, 46, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblNewLabel_1 = new JLabel("Sifre");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setBounds(229, 41, 46, 14);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		txtUser = new JTextField();
		txtUser.setBounds(311, 11, 118, oac.txt_yukseklik);
		txtUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtUser.setDocument(new JTextFieldLimit(10));
		txtUser.setColumns(10);
		JTextFieldRegularPopupMenu.addTo(txtUser);
		txtUser.setColumns(10);
		panel.add(txtUser);
		
			txtpwd = new JPasswordField();
			txtpwd.setBounds(311, 37, 118, oac.txt_yukseklik);
			panel.add(txtpwd);
			txtpwd.setFont(new Font("Tahoma", Font.BOLD, 12));
			JTextFieldRegularPopupMenu.addTo(txtpwd);
			
			chckbxhatirla = new JCheckBox("Beni Hatirla");
			chckbxhatirla.setForeground(new Color(0, 0, 128));
			chckbxhatirla.setBounds(308, 60, 112, 23);
			panel.add(chckbxhatirla);
			
			JButton btncikis = new JButton("Cikis");
			btncikis.setBounds(207, 92, 110, 23);
			panel.add(btncikis);
			btncikis.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/exit.png")));
			btncikis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
							System.exit(1);
					}
			});
			btncikis.setFont(new Font("Tahoma", Font.PLAIN, 11));
			
			btncdizin = new JButton("Calisma Dizini");
			btncdizin.setBounds(207, 122, 110, 23);
			panel.add(btncdizin);
			btncdizin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.setCursor(WAIT_CURSOR);
					CAL_DIZIN frame = new CAL_DIZIN();
					frame.setVisible(true);
					dispose();
					contentPane.setCursor(DEFAULT_CURSOR);
				}
			});
			btncdizin.setVisible(false);
			btncdizin.setMargin(new Insets(2, 1, 2, 14));
			btncdizin.setIconTextGap(0);
			btncdizin.setHorizontalAlignment(SwingConstants.LEFT);
			btncdizin.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/db.png")));
			btncdizin.setFont(new Font("Tahoma", Font.PLAIN, 11));
			
			JButton btngiris = new JButton("Giris");
			btngiris.setBounds(324, 92, 110, 23);
			panel.add(btngiris);
			btngiris.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/add-user-16.png")));
			btngiris.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(WAIT_CURSOR);
			try {
		
				String passText = new String(txtpwd.getPassword());
				String encodedString = Base64.getEncoder().encodeToString(passText.getBytes());
				boolean varmi =	oac.glb.user_var(txtUser.getText(),encodedString);
			if (varmi == true)
				{
				btndevam.setVisible(true);
				btncdizin.setVisible(true);
				GLOBAL.KULL_ADI = txtUser.getText();
				 if (chckbxhatirla.isSelected())
				 { 
					 GLOBAL.setting_yaz("BENI_HATIRLA", "E");	
					 GLOBAL.setting_yaz("ISIM", txtUser.getText());
					 GLOBAL.setting_yaz("SIFRE", encodedString);
				 }
				 else
				 {GLOBAL.setting_yaz("BENI_HATIRLA", "");	
				 }
				 contentPane.setCursor(DEFAULT_CURSOR);
				}
				else
				{
				 contentPane.setCursor(DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null, "Kullanici veya Sifresi Yanlis......!",  "OBS SISTEM", JOptionPane.ERROR_MESSAGE);   
				 txtUser.requestFocus();
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(),  "OBS SISTEM", JOptionPane.ERROR_MESSAGE);   
			}
				}
			});
			btngiris.setFont(new Font("Tahoma", Font.PLAIN, 11));
			
			btndevam = new JButton("Devam");
			btndevam.setBounds(324, 122, 110, 23);
			panel.add(btndevam);
			btndevam.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
							///// Progres Bsr olayi
					Runnable runner = new Runnable()
				    { public void run() {
				        /////  
					contentPane.setCursor(WAIT_CURSOR);
	            try
	            {
	            calisma_dizini_oku();
	            //*** CARI
	            if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
	   			{
	            oac.cARI_HESAP_MSSQL.baglan();
	   			}
	            else if (CONNECTION.caridizinbilgi.han_sql.equals("MY SQL"))
	            {
	            	oac.cARI_HESAP_MYSQL.baglan();
	            }
	          //*** KUR
	            if (CONNECTION.kurdizinbilgi.han_sql.equals("MS SQL"))
	   			{
	            	oac.kUR_MSSQL.baglan();
	   			}
	            else if (CONNECTION.kurdizinbilgi.han_sql.equals("MY SQL"))
	            {
	            	oac.kUR_MYSQL.baglan();
	            }
	          //*** ADRES
	            if (CONNECTION.adrdizinbilgi.han_sql.equals("MS SQL"))
	   			{
	            	oac.aDRES_MSSQL.baglan();
	   			}
	            else if (CONNECTION.adrdizinbilgi.han_sql.equals("MY SQL"))
	            {
	            	oac.aDRES_MYSQL.baglan();
	            }
	          //*** FATURA
	            if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
	   			{
	            	oac.sTOK_MSSQL.baglan();
	   			}
	            else if (CONNECTION.fatdizinbilgi.han_sql.equals("MY SQL"))
	            {
	            	oac.sTOK_MYSQL.baglan();
	            }
	          //*** KAMBIYO
	            if (CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL"))
	   			{
	            	oac.kAMBIYO_MSSQL.baglan();
	   			}
	            else if (CONNECTION.kamdizinbilgi.han_sql.equals("MY SQL"))
	            {
	            	oac.kAMBIYO_MYSQL.baglan();
	            }
	          //*** GUNLUK
	            if (CONNECTION.gundizinbilgi.han_sql.equals("MS SQL"))
	   			{
	            	//
	   			}
	            else if (CONNECTION.gundizinbilgi.han_sql.equals("MY SQL"))
	            {
	            	//
	            }
	          //*** SMS
	            if (CONNECTION.smsdizinbilgi.han_sql.equals("MS SQL"))
	   			{
	            	oac.sMS_MSSQL.baglan();
	   			}
	            else if (CONNECTION.smsdizinbilgi.han_sql.equals("MY SQL"))
	            {
	            	oac.sMS_MYSQL.baglan();
	            }
	            //***
	            OBS_MAIN obmain = new OBS_MAIN();
	            Login_Progres_Bar_Temizle();
	            String qwe = "";
	                if (OBS_SIS_ANA_CLAS .CARI_CONN == true)
	                {
	                    if (CAR_DOS_VAR == false)
	                        {
	                    	contentPane.setCursor(DEFAULT_CURSOR);
	                    	JOptionPane.showMessageDialog(null, "Calisilan Cari -" + CONNECTION.caridizinbilgi.kod + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
	                    	qwe = CONNECTION.caridizinbilgi.yer.equals("S") ?  CONNECTION.caridizinbilgi.server : "Lokal" ;
	         	           OBS_MAIN.lblNewLabel_1.setText ( "Cari:" + CONNECTION.caridizinbilgi.kod + "/ " + CONNECTION.caridizinbilgi.firma_adi + "/ " + qwe );
	        	           OBS_MAIN.tabbedPane.setEnabledAt(0, true);
	                        }
	                    else 
	                        { 
	                       CONNECTION.caridizinbilgi.firma_adi =  CONNECTION.caridizinbilgi.han_sql.equals("MS SQL") ?  oac.cARI_HESAP_MSSQL.cari_firma_adi():oac.cARI_HESAP_MYSQL.cari_firma_adi();
	                       qwe = CONNECTION.caridizinbilgi.yer.equals("S") ?  CONNECTION.caridizinbilgi.server : "Lokal" ;
	                       OBS_MAIN.lblNewLabel_1.setText ( "Cari:" + CONNECTION.caridizinbilgi.kod + "/ " + CONNECTION.caridizinbilgi.firma_adi + "/ " + qwe );
	        	           OBS_MAIN.tabbedPane.setEnabledAt(0, true);
	                        }
	                 }
	                else
	                {
	                    	JOptionPane.showMessageDialog(null,  "Cari Baglanti kurulamadi.....",  "ServerBaglanti", JOptionPane.ERROR_MESSAGE);        
	                    	OBS_MAIN.tabbedPane.setEnabledAt(0, false);
	                }
	                if (OBS_SIS_ANA_CLAS.KUR_CONN == true)
	                {
	                	if (KUR_DOS_VAR == false)
	                		{
	                		contentPane.setCursor(DEFAULT_CURSOR);
	                		JOptionPane.showMessageDialog(null, "Calisilan Kur -" + CONNECTION.kurdizinbilgi.kod + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
	                		}
	                	else
	                		{
	                		qwe =   CONNECTION.kurdizinbilgi.yer.equals("S") ?  CONNECTION.kurdizinbilgi.server : "Lokal";
	                		OBS_MAIN.lblNewLabel_3.setText ("Kur:" + CONNECTION.kurdizinbilgi.kod + "/ "  + qwe );
	                		OBS_MAIN.tabbedPane.setEnabledAt(1, true);
	                		}
	                }
	                else
	                {
	                		JOptionPane.showMessageDialog(null,  "Kur Baglanti kurulamadi.....",  "ServerBaglanti", JOptionPane.ERROR_MESSAGE);        
	                		OBS_MAIN.tabbedPane.setEnabledAt(1, false);
	                }
	                if (OBS_SIS_ANA_CLAS.SMS_CONN == true)
	                {
	                	if (SMS_DOS_VAR == false)
	                	{
	                	contentPane.setCursor(DEFAULT_CURSOR);
	                  	JOptionPane.showMessageDialog(null, "Calisilan Sms -" + CONNECTION.smsdizinbilgi.kod + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
	                	}
	                	else
	                	{
	                		qwe = CONNECTION.smsdizinbilgi.yer.equals("S") ? CONNECTION.smsdizinbilgi.server : "Lokal" ;
	                		OBS_MAIN.lblNewLabel_21.setText ("Sms:" + CONNECTION.smsdizinbilgi.kod + "/ "  + qwe);
	                		OBS_MAIN.tabbedPane.setEnabledAt(6, true);
	                	}
	                }
	                else
	                {
	                	JOptionPane.showMessageDialog(null,  "Sms Baglanti kurulamadi.....",  "ServerBaglanti", JOptionPane.ERROR_MESSAGE);        
	                	OBS_MAIN.tabbedPane.setEnabledAt(6, true);
	                }
	                if (OBS_SIS_ANA_CLAS.ADR_CONN == true)
	                {
	                	if (ADR_DOS_VAR == false)
	                	{
	                	contentPane.setCursor(DEFAULT_CURSOR);
	                  	JOptionPane.showMessageDialog(null, "Calisilan Adres -" + CONNECTION.adrdizinbilgi.kod + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
	                	}
	                	else
	                	{
	                    CONNECTION.adrdizinbilgi.firma_adi =  CONNECTION.adrdizinbilgi.han_sql.equals("MS SQL") ?  oac.aDRES_MSSQL.adr_firma_adi():oac.aDRES_MYSQL.adr_firma_adi();
	                    qwe = CONNECTION.adrdizinbilgi.yer.equals("S") ? CONNECTION.adrdizinbilgi.server : "Lokal" ;
	                	OBS_MAIN.lblNewLabel_22.setText ("Adres:" + CONNECTION.adrdizinbilgi.kod + "/ " + CONNECTION.adrdizinbilgi.firma_adi + "/ " + qwe);
	                	OBS_MAIN.tabbedPane.setEnabledAt(2, true);
	                	}
	                }
	                else
	                {
	                	JOptionPane.showMessageDialog(null,  "Adres Baglanti kurulamadi.....",  "ServerBaglanti", JOptionPane.ERROR_MESSAGE);        
	                	OBS_MAIN.tabbedPane.setEnabledAt(2, true);
	                }
	                if (OBS_SIS_ANA_CLAS.FAT_CONN == true)
	                {
	                if (FAT_DOS_VAR == false)
	                	{
	                	contentPane.setCursor(DEFAULT_CURSOR);
	                  	 JOptionPane.showMessageDialog(null, "Calisilan Stok -" + CONNECTION.fatdizinbilgi.kod + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
	                	}
	                	else
	                	{
	                    CONNECTION.fatdizinbilgi.firma_adi = CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL") ?  oac.sTOK_MSSQL.fat_firma_adi():oac.sTOK_MYSQL.fat_firma_adi();
	                    qwe = CONNECTION.fatdizinbilgi.yer.equals("S") ? CONNECTION.fatdizinbilgi.server : "Lokal" ;
	                    OBS_MAIN.lblNewLabel_2.setText ( "Stok:" + CONNECTION.fatdizinbilgi.kod + "/ " + CONNECTION.fatdizinbilgi.firma_adi + "/ " + qwe);
	                    OBS_MAIN.tabbedPane.setEnabledAt(3, true);
	                	}
	                }
	                else
	                {
	                	JOptionPane.showMessageDialog(null,  "Stok Baglanti kurulamadi.....",  "ServerBaglanti", JOptionPane.ERROR_MESSAGE);        
	                	OBS_MAIN.tabbedPane.setEnabledAt(3, true);
	                }
	            if (OBS_SIS_ANA_CLAS.KAM_CONN == true)
	            	{
	                if (KAM_DOS_VAR == false)
	                {
	                	contentPane.setCursor(DEFAULT_CURSOR);
	                  	 JOptionPane.showMessageDialog(null, "Calisilan Kambiyo -" + CONNECTION.kamdizinbilgi.kod + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
	                }
	                else
	                {
	                    CONNECTION.kamdizinbilgi.firma_adi = CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL") ? oac.kAMBIYO_MSSQL .kam_firma_adi():oac.kAMBIYO_MYSQL .kam_firma_adi();
	                    qwe =  CONNECTION.kamdizinbilgi.yer.equals("S") ? CONNECTION.kamdizinbilgi.server : "Lokal" ;
	                	OBS_MAIN.lblNewLabel_23.setText ("Kambiyo:" + CONNECTION.kamdizinbilgi.kod + "/ " + CONNECTION.kamdizinbilgi.firma_adi + "/ " + qwe);
	                	OBS_MAIN.tabbedPane.setEnabledAt(4, true);
	                }
	            	}
	            	else
	            	{
	                JOptionPane.showMessageDialog(null,  "Kambiyo Baglanti kurulamadi.....",  "ServerBaglanti", JOptionPane.ERROR_MESSAGE);        
	                OBS_MAIN.tabbedPane.setEnabledAt(4, true);
	            	}
	            if (OBS_SIS_ANA_CLAS.GUN_CONN == true)
	            {
	                if (GUN_DOS_VAR == false)
	                {
	                	contentPane.setCursor(DEFAULT_CURSOR);
	                  	 JOptionPane.showMessageDialog(null, "Calisilan Gunluk -" + CONNECTION.gundizinbilgi.kod + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
	                }
	                else
	                {
	                   CONNECTION.gundizinbilgi.firma_adi = CONNECTION.gundizinbilgi.han_sql.equals("MS SQL") ? oac.gUNLUK_MSSQL .gun_firma_adi():oac.gUNLUK_MYSQL .gun_firma_adi();
	                   qwe =CONNECTION.gundizinbilgi.yer.equals("S") ? CONNECTION.gundizinbilgi.server : "Lokal" ;
	                 OBS_MAIN.lblNewLabel_24.setText ( "Gunluk:" + CONNECTION.gundizinbilgi.kod + "/ " + CONNECTION.gundizinbilgi.firma_adi + "/ " + qwe);
	              	 OBS_MAIN.tabbedPane.setEnabledAt(5, true);
	                }
	            }
	            else
	            {
	                JOptionPane.showMessageDialog(null,  "Gunluk Baglanti kurulamadi.....",  "ServerBaglanti", JOptionPane.ERROR_MESSAGE);        
	                OBS_MAIN.tabbedPane.setEnabledAt(5, true);
	            }
	          
	            Thread.currentThread().isInterrupted();
	           ///************************************
	           obmain.setFont(new Font("Tahoma", Font.BOLD, 11));
	           obmain.setTitle("OBS SISTEM" + "               " + GLOBAL.KULL_ADI);
	           obmain.setVisible(true);
	           dispose();
	           //*************************************
	            contentPane.setCursor(DEFAULT_CURSOR);
	            }
	            catch (Exception ex)
	            {
	            	contentPane.setCursor(DEFAULT_CURSOR);
	                JOptionPane.showMessageDialog(null,  ex.getMessage().toString(),  "OBS SISTEM", JOptionPane.ERROR_MESSAGE);        
	            }
				        }
				    };
				    //// Progress Bar
				    Thread t = new Thread(runner, "Code Executer");
				    t.start();
				 	 ////
				}
			});
			btndevam.setVisible(false);
			btndevam.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/icons8-jog-forward-16.png")));
			btndevam.setFont(new Font("Tahoma", Font.PLAIN, 11));
			
			progressBar = new JProgressBar();
			splitPane.setRightComponent(progressBar);
			progressBar.setForeground(new Color(166, 55, 55));
			progressBar.setBorder(new LineBorder(new Color(0, 191, 255)));
			progressBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			progressBar.setFont(new Font("Tahoma", Font.BOLD, 11));
			progressBar.setMinimumSize(new Dimension(0, 25));
			progressBar.setMaximumSize(new Dimension(0, 25));
		//VERSION KONTROL/////
		versiyon_oku();
		//************SURUCU KONTROL**************************
		GLOBAL.surucu_kontrol();
		//************BENI_HATIRLA**	
			String deger;
			deger = GLOBAL.setting_oku("BENI_HATIRLA").toString();
			if (new String(deger).equals("E") == true) 
			{
				chckbxhatirla.setSelected(true);
				byte[] decodedBytes = Base64.getDecoder().decode(GLOBAL.setting_oku("SIFRE").toString());
				String decodedString = new String(decodedBytes);
				txtUser.setText(GLOBAL.setting_oku("ISIM").toString());
 				txtpwd.setText(decodedString);
 				contentPane.setCursor(DEFAULT_CURSOR);
			}

	}
	void calisma_dizini_oku() throws ClassNotFoundException, SQLException, InterruptedException
    {
        int say = 8;
    
        progressBar.setMaximum(8);
        progressBar.setStringPainted(true);
        Lgn_Progres_Bar(say, 1);
        cari_calisma_dizini_oku();
        Lgn_Progres_Bar(say, 2);
        kur_calisma_dizini_oku();
        Lgn_Progres_Bar(say, 3);
        sms_calisma_dizini_oku();
        Lgn_Progres_Bar(say, 4);
        adr_calisma_dizini_oku();
        Lgn_Progres_Bar(say, 5);
        fat_calisma_dizini_oku();
        Lgn_Progres_Bar(say, 6);
        kam_calisma_dizini_oku();
        Lgn_Progres_Bar(say, 7);
        gun_calisma_dizini_oku();
        Lgn_Progres_Bar(say, 8);
 
    }
    void cari_calisma_dizini_oku() throws ClassNotFoundException, SQLException
    {
        CONN = new CONNECTION(txtUser.getText(), "Cari Hesap");
        String hangi_sql =  CONNECTION.caridizinbilgi.han_sql;
        if (CONNECTION.caridizinbilgi.yer.equals(""))
        {
            OBS_SIS_ANA_CLAS.CARI_CONN = false;
            CAR_DOS_VAR = false;
            return;
        }
        if (CONNECTION.caridizinbilgi.yer.equals("L"))
        {
             if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_L(CONNECTION.caridizinbilgi.instance,CONNECTION.caridizinbilgi.kullanici,CONNECTION.caridizinbilgi.sifresi):oac.obsort_mysql.Server_kontrol_L(CONNECTION.caridizinbilgi.instance,CONNECTION.caridizinbilgi.kullanici,CONNECTION.caridizinbilgi.sifresi)== true)
            {
               if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_L("OK_Car" + CONNECTION.caridizinbilgi.kod, CONNECTION.caridizinbilgi.instance,CONNECTION.caridizinbilgi.kullanici,CONNECTION.caridizinbilgi.sifresi)== false :oac.obsort_mysql.dosyakontrol_L("OK_Car" + CONNECTION.caridizinbilgi.kod, CONNECTION.caridizinbilgi.instance,CONNECTION.caridizinbilgi.kullanici,CONNECTION.caridizinbilgi.sifresi)== false)
                {
                	CAR_DOS_VAR = false;}
                else
                {
                CAR_DOS_VAR = true;
                OBS_SIS_ANA_CLAS.CARI_CONN = true;}
            }
            else
                OBS_SIS_ANA_CLAS.CARI_CONN = false;
        }
        else if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_S(CONNECTION.caridizinbilgi.server, CONNECTION.caridizinbilgi.instance, CONNECTION.caridizinbilgi.kullanici, CONNECTION.caridizinbilgi.sifresi) :oac.obsort_mysql.Server_kontrol_S(CONNECTION.caridizinbilgi.server, CONNECTION.caridizinbilgi.instance, CONNECTION.caridizinbilgi.kullanici, CONNECTION.caridizinbilgi.sifresi) == true)
        {
                if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_S(CONNECTION.caridizinbilgi.server, CONNECTION.caridizinbilgi.instance, CONNECTION.caridizinbilgi.kullanici, CONNECTION.caridizinbilgi.sifresi, "OK_Car" + CONNECTION.caridizinbilgi.kod) == false:oac.obsort_mysql.dosyakontrol_S(CONNECTION.caridizinbilgi.server, CONNECTION.caridizinbilgi.instance, CONNECTION.caridizinbilgi.kullanici, CONNECTION.caridizinbilgi.sifresi, "OK_Car" + CONNECTION.caridizinbilgi.kod) == false)
                CAR_DOS_VAR = false;
            else
                CAR_DOS_VAR = true;
                OBS_SIS_ANA_CLAS.CARI_CONN = true;
        }
        else
                OBS_SIS_ANA_CLAS.CARI_CONN = false;
    	}
    void kur_calisma_dizini_oku() throws ClassNotFoundException, SQLException
    {
        CONN = new CONNECTION(txtUser.getText(), "Kur");
        String hangi_sql =  CONNECTION.kurdizinbilgi.han_sql;
        if (CONNECTION.kurdizinbilgi.yer.equals(""))
        {
        	OBS_SIS_ANA_CLAS.KUR_CONN = false;
            KUR_DOS_VAR = false;
            return;
        }
        if (CONNECTION.kurdizinbilgi.yer.equals("L"))
        {
            if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_L(CONNECTION.kurdizinbilgi.instance,CONNECTION.kurdizinbilgi.kullanici,CONNECTION.kurdizinbilgi.sifresi):oac.obsort_mysql.Server_kontrol_L(CONNECTION.kurdizinbilgi.instance,CONNECTION.kurdizinbilgi.kullanici,CONNECTION.kurdizinbilgi.sifresi) == true)
            {
                if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_L("OK_Kur" + CONNECTION.kurdizinbilgi.kod, CONNECTION.kurdizinbilgi.instance,CONNECTION.kurdizinbilgi.kullanici,CONNECTION.kurdizinbilgi.sifresi) == false :oac.obsort_mysql.dosyakontrol_L("OK_Kur" + CONNECTION.kurdizinbilgi.kod, CONNECTION.kurdizinbilgi.instance,CONNECTION.kurdizinbilgi.kullanici,CONNECTION.kurdizinbilgi.sifresi) == false)
               	KUR_DOS_VAR = false;
                else
                KUR_DOS_VAR = true;
                OBS_SIS_ANA_CLAS.KUR_CONN = true;
            }
            else
                OBS_SIS_ANA_CLAS.KUR_CONN = false;
        }
        else if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_S(CONNECTION.kurdizinbilgi.server, CONNECTION.kurdizinbilgi.instance, CONNECTION.kurdizinbilgi.kullanici, CONNECTION.kurdizinbilgi.sifresi) :oac.obsort_mysql.Server_kontrol_S(CONNECTION.kurdizinbilgi.server, CONNECTION.kurdizinbilgi.instance, CONNECTION.kurdizinbilgi.kullanici, CONNECTION.kurdizinbilgi.sifresi) == true)
        {
            if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_S(CONNECTION.kurdizinbilgi.server, CONNECTION.kurdizinbilgi.instance, CONNECTION.kurdizinbilgi.kullanici, CONNECTION.kurdizinbilgi.sifresi, "OK_Kur" + CONNECTION.kurdizinbilgi.kod) == false :oac.obsort_mysql.dosyakontrol_S(CONNECTION.kurdizinbilgi.server, CONNECTION.kurdizinbilgi.instance, CONNECTION.kurdizinbilgi.kullanici, CONNECTION.kurdizinbilgi.sifresi, "OK_Kur" + CONNECTION.kurdizinbilgi.kod) == false)
                KUR_DOS_VAR = false;
            else
                KUR_DOS_VAR = true;
            OBS_SIS_ANA_CLAS.KUR_CONN = true;
        }
        else
            OBS_SIS_ANA_CLAS.KUR_CONN = false;
    }
    void sms_calisma_dizini_oku() throws ClassNotFoundException, SQLException
    {
        CONN = new CONNECTION(txtUser.getText(), "Sms");
        String hangi_sql =  CONNECTION.smsdizinbilgi.han_sql;
        if (CONNECTION.smsdizinbilgi.yer.equals(""))
        {
            OBS_SIS_ANA_CLAS.SMS_CONN = false;
            SMS_DOS_VAR = false;
            return;
        }
        if (CONNECTION.smsdizinbilgi.yer.equals("L"))
        {
            if (hangi_sql.equals("MS SQL") ?  oac.obsort_mssql.Server_kontrol_L(CONNECTION.smsdizinbilgi.instance,CONNECTION.smsdizinbilgi.kullanici,CONNECTION.smsdizinbilgi.sifresi):oac.obsort_mysql.Server_kontrol_L(CONNECTION.smsdizinbilgi.instance,CONNECTION.smsdizinbilgi.kullanici,CONNECTION.smsdizinbilgi.sifresi) == true)
            {
                if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_L("OK_Sms" + CONNECTION.smsdizinbilgi.kod, CONNECTION.smsdizinbilgi.instance,CONNECTION.smsdizinbilgi.kullanici,CONNECTION.smsdizinbilgi.sifresi) ==  false :oac.obsort_mysql.dosyakontrol_L("OK_Sms" + CONNECTION.smsdizinbilgi.kod, CONNECTION.smsdizinbilgi.instance,CONNECTION.smsdizinbilgi.kullanici,CONNECTION.smsdizinbilgi.sifresi) == false)
                    SMS_DOS_VAR = false;
                else
                    SMS_DOS_VAR = true;
                OBS_SIS_ANA_CLAS.SMS_CONN = true;
            }
            else
                OBS_SIS_ANA_CLAS.SMS_CONN = false;
        }
        else if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_S(CONNECTION.smsdizinbilgi.server, CONNECTION.smsdizinbilgi.instance, CONNECTION.smsdizinbilgi.kullanici, CONNECTION.smsdizinbilgi.sifresi):oac.obsort_mysql.Server_kontrol_S(CONNECTION.smsdizinbilgi.server, CONNECTION.smsdizinbilgi.instance, CONNECTION.smsdizinbilgi.kullanici, CONNECTION.smsdizinbilgi.sifresi) == true)
        {
            if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_S(CONNECTION.smsdizinbilgi.server, CONNECTION.smsdizinbilgi.instance, CONNECTION.smsdizinbilgi.kullanici, CONNECTION.smsdizinbilgi.sifresi, "OK_Sms" + CONNECTION.smsdizinbilgi.kod) == false :oac.obsort_mysql.dosyakontrol_S(CONNECTION.smsdizinbilgi.server, CONNECTION.smsdizinbilgi.instance, CONNECTION.smsdizinbilgi.kullanici, CONNECTION.smsdizinbilgi.sifresi, "OK_Sms" + CONNECTION.smsdizinbilgi.kod) == false)
                SMS_DOS_VAR = false;
            else
                SMS_DOS_VAR = true;
            OBS_SIS_ANA_CLAS.SMS_CONN = true;
        }
        else
            OBS_SIS_ANA_CLAS.SMS_CONN = false;
    }
    void adr_calisma_dizini_oku() throws ClassNotFoundException, SQLException
    {
        CONN = new CONNECTION(txtUser.getText(), "Adres");
        String hangi_sql =  CONNECTION.smsdizinbilgi.han_sql;
        if (CONNECTION.adrdizinbilgi.yer.equals(""))
        {
            OBS_SIS_ANA_CLAS.ADR_CONN = false;
            ADR_DOS_VAR = false;
            return;
        }
        if (CONNECTION.adrdizinbilgi.yer.equals("L"))
        {
            if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_L(CONNECTION.adrdizinbilgi.instance,CONNECTION.adrdizinbilgi.kullanici,CONNECTION.adrdizinbilgi.sifresi):oac.obsort_mysql.Server_kontrol_L(CONNECTION.adrdizinbilgi.instance,CONNECTION.adrdizinbilgi.kullanici,CONNECTION.adrdizinbilgi.sifresi) == true)
            {
                if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_L("OK_Adr" + CONNECTION.adrdizinbilgi.kod, CONNECTION.adrdizinbilgi.instance,CONNECTION.adrdizinbilgi.kullanici,CONNECTION.adrdizinbilgi.sifresi) == false :oac.obsort_mysql.dosyakontrol_L("OK_Adr" + CONNECTION.adrdizinbilgi.kod, CONNECTION.adrdizinbilgi.instance,CONNECTION.adrdizinbilgi.kullanici,CONNECTION.adrdizinbilgi.sifresi) == false)
                    ADR_DOS_VAR = false;
                else
                    ADR_DOS_VAR = true;
                OBS_SIS_ANA_CLAS.ADR_CONN = true;
            }
            else
                OBS_SIS_ANA_CLAS.ADR_CONN = false;
        }
        else if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_S(CONNECTION.adrdizinbilgi.server, CONNECTION.adrdizinbilgi.instance, CONNECTION.adrdizinbilgi.kullanici, CONNECTION.adrdizinbilgi.sifresi):oac.obsort_mysql.Server_kontrol_S(CONNECTION.adrdizinbilgi.server, CONNECTION.adrdizinbilgi.instance, CONNECTION.adrdizinbilgi.kullanici, CONNECTION.adrdizinbilgi.sifresi) == true)
        {
            if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_S(CONNECTION.adrdizinbilgi.server, CONNECTION.adrdizinbilgi.instance, CONNECTION.adrdizinbilgi.kullanici, CONNECTION.adrdizinbilgi.sifresi, "OK_Adr" + CONNECTION.adrdizinbilgi.kod) == false :oac.obsort_mysql.dosyakontrol_S(CONNECTION.adrdizinbilgi.server, CONNECTION.adrdizinbilgi.instance, CONNECTION.adrdizinbilgi.kullanici, CONNECTION.adrdizinbilgi.sifresi, "OK_Adr" + CONNECTION.adrdizinbilgi.kod) == false)
                ADR_DOS_VAR = false;
            else
                ADR_DOS_VAR = true;
            OBS_SIS_ANA_CLAS.ADR_CONN = true;
        }
        else
            OBS_SIS_ANA_CLAS.ADR_CONN = false;
    }
    void fat_calisma_dizini_oku() throws ClassNotFoundException, SQLException
    {
        CONN = new CONNECTION(txtUser.getText(), "Fatura");
        String hangi_sql =  CONNECTION.smsdizinbilgi.han_sql;
        if (CONNECTION.fatdizinbilgi.yer.equals(""))
        { 
            OBS_SIS_ANA_CLAS.FAT_CONN = false;
            FAT_DOS_VAR = false;
            return;
        }
        if (CONNECTION.fatdizinbilgi.yer.equals("L"))
        {
            if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_L(CONNECTION.fatdizinbilgi.instance,CONNECTION.fatdizinbilgi.kullanici,CONNECTION.fatdizinbilgi.sifresi) :oac.obsort_mysql.Server_kontrol_L(CONNECTION.fatdizinbilgi.instance,CONNECTION.fatdizinbilgi.kullanici,CONNECTION.fatdizinbilgi.sifresi) == true)
            {
                if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_L("OK_Fat" + CONNECTION.fatdizinbilgi.kod, CONNECTION.fatdizinbilgi.instance,CONNECTION.fatdizinbilgi.kullanici,CONNECTION.fatdizinbilgi.sifresi) == false :oac.obsort_mysql.dosyakontrol_L("OK_Fat" + CONNECTION.fatdizinbilgi.kod, CONNECTION.fatdizinbilgi.instance,CONNECTION.fatdizinbilgi.kullanici,CONNECTION.fatdizinbilgi.sifresi) == false)
                    FAT_DOS_VAR = false;
                else
                    FAT_DOS_VAR = true;
                OBS_SIS_ANA_CLAS.FAT_CONN = true;
            }
            else
                OBS_SIS_ANA_CLAS.FAT_CONN = false;
        }
        else if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_S(CONNECTION.fatdizinbilgi.server, CONNECTION.fatdizinbilgi.instance, CONNECTION.fatdizinbilgi.kullanici, CONNECTION.fatdizinbilgi.sifresi) :oac.obsort_mysql.Server_kontrol_S(CONNECTION.fatdizinbilgi.server, CONNECTION.fatdizinbilgi.instance, CONNECTION.fatdizinbilgi.kullanici, CONNECTION.fatdizinbilgi.sifresi) == true)
        {
            if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_S(CONNECTION.fatdizinbilgi.server, CONNECTION.fatdizinbilgi.instance, CONNECTION.fatdizinbilgi.kullanici, CONNECTION.fatdizinbilgi.sifresi, "OK_Fat" + CONNECTION.fatdizinbilgi.kod) == false:oac.obsort_mysql.dosyakontrol_S(CONNECTION.fatdizinbilgi.server, CONNECTION.fatdizinbilgi.instance, CONNECTION.fatdizinbilgi.kullanici, CONNECTION.fatdizinbilgi.sifresi, "OK_Fat" + CONNECTION.fatdizinbilgi.kod) == false)
                FAT_DOS_VAR = false;
            else
                FAT_DOS_VAR = true;
            OBS_SIS_ANA_CLAS.FAT_CONN = true;
        }
        else
            OBS_SIS_ANA_CLAS.FAT_CONN = false;
    }
    void kam_calisma_dizini_oku() throws ClassNotFoundException, SQLException
    {
        CONN = new CONNECTION(txtUser.getText(), "Kambiyo");
        String hangi_sql =  CONNECTION.smsdizinbilgi.han_sql;
        if (CONNECTION.kamdizinbilgi.yer.equals(""))
        {
            OBS_SIS_ANA_CLAS.KAM_CONN = false;
            KAM_DOS_VAR = false;
            return;
        }
        if (CONNECTION.kamdizinbilgi.yer.equals("L"))
        {
            if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_L(CONNECTION.kamdizinbilgi.instance,CONNECTION.kamdizinbilgi.kullanici,CONNECTION.kamdizinbilgi.sifresi):oac.obsort_mysql.Server_kontrol_L(CONNECTION.kamdizinbilgi.instance,CONNECTION.kamdizinbilgi.kullanici,CONNECTION.kamdizinbilgi.sifresi) == true)
            {
                if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_L("OK_Kam" + CONNECTION.kamdizinbilgi.kod, CONNECTION.kamdizinbilgi.instance,CONNECTION.kamdizinbilgi.kullanici,CONNECTION.kamdizinbilgi.sifresi) == false :oac.obsort_mysql.dosyakontrol_L("OK_Kam" + CONNECTION.kamdizinbilgi.kod, CONNECTION.kamdizinbilgi.instance,CONNECTION.kamdizinbilgi.kullanici,CONNECTION.kamdizinbilgi.sifresi) == false)
                    KAM_DOS_VAR = false;
                else
                    KAM_DOS_VAR = true;
                OBS_SIS_ANA_CLAS.KAM_CONN = true;
            }
            else
                OBS_SIS_ANA_CLAS.KAM_CONN = false;
        }
        else if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_S(CONNECTION.kamdizinbilgi.server, CONNECTION.kamdizinbilgi.instance, CONNECTION.kamdizinbilgi.kullanici, CONNECTION.kamdizinbilgi.sifresi) :oac.obsort_mysql.Server_kontrol_S(CONNECTION.kamdizinbilgi.server, CONNECTION.kamdizinbilgi.instance, CONNECTION.kamdizinbilgi.kullanici, CONNECTION.kamdizinbilgi.sifresi) == true)
        {
            if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_S(CONNECTION.kamdizinbilgi.server, CONNECTION.kamdizinbilgi.instance, CONNECTION.kamdizinbilgi.kullanici, CONNECTION.kamdizinbilgi.sifresi, "OK_Kam" + CONNECTION.kamdizinbilgi.kod) == false :oac.obsort_mysql.dosyakontrol_S(CONNECTION.kamdizinbilgi.server, CONNECTION.kamdizinbilgi.instance, CONNECTION.kamdizinbilgi.kullanici, CONNECTION.kamdizinbilgi.sifresi, "OK_Kam" + CONNECTION.kamdizinbilgi.kod) == false)
                KAM_DOS_VAR = false;
            else
                KAM_DOS_VAR = true;
            OBS_SIS_ANA_CLAS.KAM_CONN = true;
        }
        else
            OBS_SIS_ANA_CLAS.KAM_CONN = false;
    }
    void gun_calisma_dizini_oku() throws ClassNotFoundException, SQLException
    {
        CONN = new CONNECTION(txtUser.getText(), "Gunluk");
        String hangi_sql =  CONNECTION.smsdizinbilgi.han_sql;
        if (CONNECTION.gundizinbilgi.yer.equals(""))
        {
            OBS_SIS_ANA_CLAS.GUN_CONN = false;
            GUN_DOS_VAR = false;
            return;
        }
        if (CONNECTION.gundizinbilgi.yer.equals("L"))
        {
            if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_L(CONNECTION.gundizinbilgi.instance,CONNECTION.gundizinbilgi.kullanici,CONNECTION.gundizinbilgi.sifresi):oac.obsort_mysql.Server_kontrol_L(CONNECTION.gundizinbilgi.instance,CONNECTION.gundizinbilgi.kullanici,CONNECTION.gundizinbilgi.sifresi) == true)
            {
                if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_L("OK_Gun" + CONNECTION.gundizinbilgi.kod, CONNECTION.gundizinbilgi.instance,CONNECTION.gundizinbilgi.kullanici,CONNECTION.gundizinbilgi.sifresi) == false :oac.obsort_mysql.dosyakontrol_L("OK_Gun" + CONNECTION.gundizinbilgi.kod, CONNECTION.gundizinbilgi.instance,CONNECTION.gundizinbilgi.kullanici,CONNECTION.gundizinbilgi.sifresi) == false)
                    GUN_DOS_VAR = false;
                else
                    GUN_DOS_VAR = true;
                OBS_SIS_ANA_CLAS.GUN_CONN = true;
            }
            else
                OBS_SIS_ANA_CLAS.GUN_CONN = false;
        }
        else if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_S(CONNECTION.gundizinbilgi.server, CONNECTION.gundizinbilgi.instance, CONNECTION.gundizinbilgi.kullanici, CONNECTION.gundizinbilgi.sifresi) :oac.obsort_mysql.Server_kontrol_S(CONNECTION.gundizinbilgi.server, CONNECTION.gundizinbilgi.instance, CONNECTION.gundizinbilgi.kullanici, CONNECTION.gundizinbilgi.sifresi) == true)
        {
            if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_S(CONNECTION.gundizinbilgi.server, CONNECTION.gundizinbilgi.instance, CONNECTION.gundizinbilgi.kullanici, CONNECTION.gundizinbilgi.sifresi, "OK_Gun" + CONNECTION.gundizinbilgi.kod) == false:oac.obsort_mysql.dosyakontrol_S(CONNECTION.gundizinbilgi.server, CONNECTION.gundizinbilgi.instance, CONNECTION.gundizinbilgi.kullanici, CONNECTION.gundizinbilgi.sifresi, "OK_Gun" + CONNECTION.gundizinbilgi.kod) == false)
                GUN_DOS_VAR = false;
            else
                GUN_DOS_VAR = true;
            OBS_SIS_ANA_CLAS.GUN_CONN = true;
        }
        else
            OBS_SIS_ANA_CLAS.GUN_CONN = false;
    }
    void Lgn_Progres_Bar(int max, int deger) throws InterruptedException
    {
 	   	progressBar.setValue(deger);
    }
    void Login_Progres_Bar_Temizle()
    {
    	progressBar.setMaximum(0);
    	progressBar.setValue(0);
    	progressBar.setStringPainted(false);
    }
    private void versiyon_oku()
    {
    	if (oac.glb.internet_kontrol() == false)
    	{
    		return ;
    	}
    	  try 
    	  {
    	 //************************************
        String eskitar = "" ;
    	String eskiver = "";
    	String yenitar = "" ;
     	String yeniver = "";
    	String fileName = "C:/OBS_SISTEM" + "/OBS_VERSION.txt";
    	String line = null;
    	FileReader fileReader = null;
    	
    	 int counter = 0;
    	fileReader =  new FileReader(fileName);
            BufferedReader bufferedReader =    new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                counter++;
                if(counter == 1)
                {
                	 eskitar = line.toString();
                 }
                else  if(counter == 2)
                {
               	 eskiver = line.toString();
               }
            }   
        	bufferedReader.close();
        //******************************************************************
    	// DOSYA INDIR
    	String serverAddress = "78.26.149.175";
        String userId ="hamitadmin";
        String password ="SDFks9hfji3#DEd";
        FTPClient ftp = new FTPClient();
        ftp.connect(serverAddress);
        //login to server
        if(!ftp.login(userId, password))
        {
            ftp.logout();
        	return ;
        }
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply))
        {
            ftp.disconnect();
        	return ;
        }
    
      ftp.enterLocalPassiveMode();
      String remoteFile1 = ftp.printWorkingDirectory() + "/OBS_VERSION.txt";
      File downloadFile1 = new File( "C:/OBS_SISTEM" + "/OBS_VERSIONS.txt");
      OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
      boolean success = ftp.retrieveFile(remoteFile1, outputStream1);
      outputStream1.close();
      if (success == false ) return ;
   	//************************************
      fileName = "" ;
      fileName = "C:/OBS_SISTEM" + "/OBS_VERSIONS.txt";
      fileReader = null;
      fileReader =  new FileReader(fileName);
       // Always wrap FileReader in BufferedReader.
      bufferedReader = null;
      bufferedReader =    new BufferedReader(fileReader);
      counter = 0;
    	while((line = bufferedReader.readLine()) != null) {
            counter++;
            if(counter == 1)
            {
            	 yenitar = line.toString();
            }
            else  if(counter == 2)
            {
           	 yeniver = line.toString();
           }
        }   
    	bufferedReader.close();
     	if (eskiver.equals(yeniver))
    	{
    		File f= new File("C:/OBS_SISTEM/OBS_VERSIONS.txt");           //file to be delete  
    		success = f.delete();  
    	}
    	else
    	{
    		File f= new File("C:/OBS_SISTEM" + "/OBS_VERSIONS.txt");           //file to be delete  
    		f.delete();  
    		
    		String html = "<html><body width='%1s'><h1>OBS SISTEM</h1>"
                   + "Yeni Versiyon Mevcut.......	 "
                   + "<br><br> "
                    + "Mevcut Version = " +eskiver + "      "
                    + "<br><br> "
                     + "Yeni Version = " + yeniver + "" ;
    		   JOptionPane.showMessageDialog(null, String.format(html, "OBS SISTEM", JOptionPane.PLAIN_MESSAGE));
    		//JOptionPane.showMessageDialog(null,"Yeni Versiyon Mevcut.......		Versiyon=" + yeniver,  "OBS SISTEM", JOptionPane.PLAIN_MESSAGE);   
    	}
    	  }
          catch (Exception ex)
          {
            	return ;
          }
    }
   	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}


