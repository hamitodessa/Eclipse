package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import LOGER_KAYIT.DOSYA_MSSQL;
import LOGER_KAYIT.DOSYA_MYSQL;
import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.ADRES_MSSQL;
import OBS_C_2025.ADRES_MYSQL;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.CARI_HESAP_MSSQL;
import OBS_C_2025.CARI_HESAP_MYSQL;
import OBS_C_2025.CONNECT;
import OBS_C_2025.DOSYA_YAZ;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.GUNLUK_MSSQL;
import OBS_C_2025.GUNLUK_MYSQL;
import OBS_C_2025.ILOGGER;
import OBS_C_2025.JTextFieldLimit;

import OBS_C_2025.JTextFieldRegularPopupMenu;
import OBS_C_2025.KAMBIYO_ACCESS;
import OBS_C_2025.KAMBIYO_MSSQL;
import OBS_C_2025.KAMBIYO_MYSQL;
import OBS_C_2025.KUR_ACCESS;
import OBS_C_2025.KUR_MSSQL;
import OBS_C_2025.KUR_MYSQL;
import OBS_C_2025.LOG_MAIL_OKU;
import OBS_C_2025.MAIL_AT;
import OBS_C_2025.OBS_ORTAK_MSSQL;
import OBS_C_2025.OBS_ORTAK_MYSQL;
import OBS_C_2025.SMS_ACCESS;
import OBS_C_2025.SMS_MSSQL;
import OBS_C_2025.SMS_MYSQL;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.STOK_MSSQL;
import OBS_C_2025.STOK_MYSQL;



public class LOGIN extends JFrame {
	
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
	OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	BAGLAN bAGLAN = new BAGLAN();
	BAGLAN_LOG bAGLAN_LOG = new BAGLAN_LOG();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	EventQueue.invokeLater(new Runnable() {
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
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				CAL_DIZIN frame = new CAL_DIZIN();
				frame.setVisible(true);
				dispose();
				
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try {
	
			String passText = new String(txtpwd.getPassword());
			String encodedString = Base64.getEncoder().encodeToString(passText.getBytes());
			boolean varmi =	oac.uSER_ISL.user_var(txtUser.getText(),encodedString);
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
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			else
			{
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
			    	contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try
            {
            calisma_dizini_oku();
            //*** CARI
           CARI_ACCESS  c_Access = new CARI_ACCESS(oac._ICar , oac._ICari_Loger);
           c_Access.baglan();
          //*** KUR
           KUR_ACCESS  k_Access = new KUR_ACCESS(oac._IKur, oac._IKur_Loger);
           k_Access.baglan();
          //*** ADRES
           ADRES_ACCESS  a_Access = new ADRES_ACCESS(oac._IAdres, oac._IAdres_Loger);
           a_Access.baglan();
          //*** FATURA
            STOK_ACCESS  s_Access = new STOK_ACCESS(oac._IStok,oac._IFatura_Loger);
            s_Access.baglan();
          //*** KAMBIYO
            KAMBIYO_ACCESS  ka_Access = new KAMBIYO_ACCESS(oac._IKambiyo, oac._IKambiyo_Loger);
            ka_Access.baglan();
          //*** GUNLUK
           GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(oac._IGunluk, oac._IGunluk_Loger);
            g_Access.baglan();
          //*** SMS
            SMS_ACCESS  sms_Access = new SMS_ACCESS(oac._ISms, oac._ISms_Loger);
            sms_Access.baglan();
            //***buraya 
            
            OBS_MAIN obmain = new OBS_MAIN();
            Login_Progres_Bar_Temizle();
            String qwe = "";
                if (OBS_SIS_2025_ANA_CLASS .CARI_CONN == true)
                {
                    if (CAR_DOS_VAR == false)
                        {
                    	contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    	JOptionPane.showMessageDialog(null, "Calisilan Cari -" + BAGLAN.cariDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
                    	qwe = BAGLAN.cariDizin.yER.equals("S") ?  BAGLAN.cariDizin.sERVER : "Lokal" ;
         	           OBS_MAIN.lblNewLabel_1.setText ( "Cari:" + BAGLAN.cariDizin.kOD + "/ " + BAGLAN.cariDizin.fIRMA_ADI + "/ " + qwe );
        	           OBS_MAIN.tabbedPane.setEnabledAt(0, true);
                        }
                    else 
                        { 
                    	BAGLAN.cariDizin.fIRMA_ADI =  oac._ICar.cari_firma_adi() ;
                       qwe = BAGLAN.cariDizin.yER.equals("S") ?  BAGLAN.cariDizin.sERVER : "Lokal" ;
                       OBS_MAIN.lblNewLabel_1.setText ( "Cari:" + BAGLAN.cariDizin.kOD + "/ " + BAGLAN.cariDizin.fIRMA_ADI + "/ " + qwe );
        	           OBS_MAIN.tabbedPane.setEnabledAt(0, true);
                        }
                 }
                else
                {
                    	JOptionPane.showMessageDialog(null,  "Cari Baglanti kurulamadi.....",  "ServerBaglanti", JOptionPane.ERROR_MESSAGE);        
                    	OBS_MAIN.tabbedPane.setEnabledAt(0, false);
                }
                if (OBS_SIS_2025_ANA_CLASS.KUR_CONN == true)
                {
                	if (KUR_DOS_VAR == false)
                		{
                		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                		JOptionPane.showMessageDialog(null, "Calisilan Kur -" + BAGLAN.kurDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
                		}
                	else
                		{
                		qwe =   BAGLAN.kurDizin.yER.equals("S") ?  BAGLAN.kurDizin.sERVER : "Lokal";
                		OBS_MAIN.lblNewLabel_3.setText ("Kur:" + BAGLAN.kurDizin.kOD + "/ "  + qwe );
                		OBS_MAIN.tabbedPane.setEnabledAt(1, true);
                		}
                }
                else
                {
                		JOptionPane.showMessageDialog(null,  "Kur Baglanti kurulamadi.....",  "ServerBaglanti", JOptionPane.ERROR_MESSAGE);        
                		OBS_MAIN.tabbedPane.setEnabledAt(1, false);
                }
                if (OBS_SIS_2025_ANA_CLASS.SMS_CONN == true)
                {
                	if (SMS_DOS_VAR == false)
                	{
                		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                  	JOptionPane.showMessageDialog(null, "Calisilan Sms -" + BAGLAN.smsDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
                	}
                	else
                	{
                		qwe = BAGLAN.smsDizin.yER.equals("S") ? BAGLAN.smsDizin.sERVER : "Lokal" ;
                		OBS_MAIN.lblNewLabel_21.setText ("Sms:" + BAGLAN.smsDizin.kOD + "/ "  + qwe);
                		OBS_MAIN.tabbedPane.setEnabledAt(6, true);
                	}
                }
                else
                {
                	JOptionPane.showMessageDialog(null,  "Sms Baglanti kurulamadi.....",  "ServerBaglanti", JOptionPane.ERROR_MESSAGE);        
                	OBS_MAIN.tabbedPane.setEnabledAt(6, true);
                }
                if (OBS_SIS_2025_ANA_CLASS.ADR_CONN == true)
                {
                	if (ADR_DOS_VAR == false)
                	{
                		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                  	JOptionPane.showMessageDialog(null, "Calisilan Adres -" + BAGLAN.adrDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
                	}
                	else
                	{
                		BAGLAN.adrDizin.fIRMA_ADI =    oac._IAdres.adr_firma_adi() ;
                    qwe = BAGLAN.adrDizin.yER.equals("S") ? BAGLAN.adrDizin.sERVER : "Lokal" ;
                	OBS_MAIN.lblNewLabel_22.setText ("Adres:" + BAGLAN.adrDizin.kOD + "/ " + BAGLAN.adrDizin.fIRMA_ADI + "/ " + qwe);
                	OBS_MAIN.tabbedPane.setEnabledAt(2, true);
                	}
                }
                else
                {
                	JOptionPane.showMessageDialog(null,  "Adres Baglanti kurulamadi.....",  "ServerBaglanti", JOptionPane.ERROR_MESSAGE);        
                	OBS_MAIN.tabbedPane.setEnabledAt(2, true);
                }
                if (OBS_SIS_2025_ANA_CLASS.FAT_CONN == true)
                {
                if (FAT_DOS_VAR == false)
                	{
                	contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                  	 JOptionPane.showMessageDialog(null, "Calisilan Stok -" + BAGLAN.fatDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
                	}
                	else
                	{
                		BAGLAN.fatDizin.fIRMA_ADI = oac._IStok.fat_firma_adi() ;
                    qwe = BAGLAN.fatDizin.yER.equals("S") ? BAGLAN.fatDizin.sERVER : "Lokal" ;
                    OBS_MAIN.lblNewLabel_2.setText ( "Stok:" + BAGLAN.fatDizin.kOD + "/ " + BAGLAN.fatDizin.fIRMA_ADI + "/ " + qwe);
                    OBS_MAIN.tabbedPane.setEnabledAt(3, true);
                	}
                }
                else
                {
                	JOptionPane.showMessageDialog(null,  "Stok Baglanti kurulamadi.....",  "ServerBaglanti", JOptionPane.ERROR_MESSAGE);        
                	OBS_MAIN.tabbedPane.setEnabledAt(3, true);
                }
            if (OBS_SIS_2025_ANA_CLASS.KAM_CONN == true)
            	{
                if (KAM_DOS_VAR == false)
                {
                	contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                  	 JOptionPane.showMessageDialog(null, "Calisilan Kambiyo -" +BAGLAN.kamDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
                }
                else
                {
                	BAGLAN.kamDizin.fIRMA_ADI =  oac._IKambiyo.kam_firma_adi() ;
                    qwe =  BAGLAN.kamDizin.yER.equals("S") ? BAGLAN.kamDizin.sERVER : "Lokal" ;
                	OBS_MAIN.lblNewLabel_23.setText ("Kambiyo:" + BAGLAN.kamDizin.kOD + "/ " + BAGLAN.kamDizin.fIRMA_ADI + "/ " + qwe);
                	OBS_MAIN.tabbedPane.setEnabledAt(4, true);
                }
            	}
            	else
            	{
                JOptionPane.showMessageDialog(null,  "Kambiyo Baglanti kurulamadi.....",  "ServerBaglanti", JOptionPane.ERROR_MESSAGE);        
                OBS_MAIN.tabbedPane.setEnabledAt(4, true);
            	}
            if (OBS_SIS_2025_ANA_CLASS.GUN_CONN == true)
            {
                if (GUN_DOS_VAR == false)
                {
                	contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                  	 JOptionPane.showMessageDialog(null, "Calisilan Gunluk -" + BAGLAN.gunDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
                }
                else
                {
                	BAGLAN.gunDizin.fIRMA_ADI = oac._IGunluk.gun_firma_adi() ;
                   qwe =BAGLAN.gunDizin.yER.equals("S") ? BAGLAN.gunDizin.sERVER : "Lokal" ;
                 OBS_MAIN.lblNewLabel_24.setText ( "Gunluk:" + BAGLAN.gunDizin.kOD + "/ " + BAGLAN.gunDizin.fIRMA_ADI + "/ " + qwe);
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
            
            
            // 
            
           dispose();
           //*************************************
       	contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            catch (Exception ex)
            {
            	contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
			contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
}
	void calisma_dizini_oku() throws ClassNotFoundException, SQLException, InterruptedException
{
    int say = 8;
	bAGLAN.cONNECT(txtUser.getText());
	bAGLAN_LOG.cONNECT();
	// Cari
	cONN_AKTAR( BAGLAN.cariDizin.hAN_SQL );
	String hangi_sql =  BAGLAN.cariDizin.hAN_SQL;
    if (hangi_sql.equals("MS SQL"))
    {
    	oac._ICar = new  CARI_HESAP_MSSQL();
    }
    else
    {
    	oac._ICar = new  CARI_HESAP_MYSQL();
    }
    //Kur
    cONN_AKTAR( BAGLAN.kurDizin.hAN_SQL );
    hangi_sql =  BAGLAN.kurDizin.hAN_SQL;
    if (hangi_sql.equals("MS SQL"))
    {
    	oac._IKur = new KUR_MSSQL();
    }
    else
    {
    	oac._IKur = new KUR_MYSQL();
    }
    //SMS
    cONN_AKTAR(BAGLAN.smsDizin.hAN_SQL );
    hangi_sql =  BAGLAN.smsDizin.hAN_SQL;
    if (hangi_sql.equals("MS SQL"))
    {
    	oac._ISms = new SMS_MSSQL();
    }
    else
    {
    	oac._ISms = new SMS_MYSQL();
    }
    //ADRES
    cONN_AKTAR( BAGLAN.adrDizin.hAN_SQL );
    hangi_sql =  BAGLAN.adrDizin.hAN_SQL;
    if (hangi_sql.equals("MS SQL"))
    {
    	oac._IAdres = new ADRES_MSSQL();
    }
    else
    {
    	oac._IAdres = new ADRES_MYSQL();
    }
    //FAT
    cONN_AKTAR(BAGLAN.fatDizin.hAN_SQL );
    hangi_sql =  BAGLAN.fatDizin.hAN_SQL;
    if (hangi_sql.equals("MS SQL"))
    {
    	oac._IStok = new STOK_MSSQL();
    }
    else
    {
    	oac._IStok = new STOK_MYSQL();
    }
    //KAMB
    cONN_AKTAR(BAGLAN.kamDizin.hAN_SQL );
    hangi_sql =  BAGLAN.kamDizin.hAN_SQL;
    if (hangi_sql.equals("MS SQL"))
    {
    	oac._IKambiyo = new KAMBIYO_MSSQL();
    }
    else
    {
    	oac._IKambiyo = new KAMBIYO_MYSQL();
    }
    //GUN
    cONN_AKTAR( BAGLAN.gunDizin.hAN_SQL );
    hangi_sql =  BAGLAN.gunDizin.hAN_SQL;
    if (hangi_sql.equals("MS SQL"))
    {
    	oac._IGunluk = new GUNLUK_MSSQL();
    }
    else
    {
    	oac._IGunluk = new GUNLUK_MYSQL();
    }
    lOG_AKTAR();
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
    CONNECT s_CONN = new CONNECT(oac._IConn);
    Tema_Cari.conn =    Tema_Cari.myTemaConnection();
    if (BAGLAN.cariDizin.yER.equals(""))
    {
        OBS_SIS_2025_ANA_CLASS.CARI_CONN = false;
        CAR_DOS_VAR = false;
        return;
    }
    if (BAGLAN.cariDizin.yER.equals("L"))
    	
    {
    
    	if (s_CONN.Server_kontrol_L(BAGLAN.cariDizin.iNSTANCE, BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true)   
    	{
    		if (s_CONN.Dosya_kontrol_L("OK_Car" + BAGLAN.cariDizin.kOD,BAGLAN.cariDizin.iNSTANCE, BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == false)
            {
            	CAR_DOS_VAR = false;
            	}
            else
            {
           	lOGG_AKTAR("Cari Hesap",BAGLAN.cariDizin.hAN_SQL,BAGLAN.cariDizin.lOG,BAGLAN.cariDizin.lOGLAMA_YERI);
            CAR_DOS_VAR = true;
            OBS_SIS_2025_ANA_CLASS.CARI_CONN = true;}
    	}
    	 else
             OBS_SIS_2025_ANA_CLASS.CARI_CONN = false;
    	}
    else if (s_CONN.Server_kontrol_S(BAGLAN.cariDizin.sERVER, BAGLAN.cariDizin.iNSTANCE,BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true )
    {
            if (s_CONN.Dosya_kontrol_S(BAGLAN.cariDizin.sERVER,BAGLAN.cariDizin.iNSTANCE, BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI,"OK_Car" + BAGLAN.cariDizin.kOD,BAGLAN.cariDizin.sERVER) == false)
            CAR_DOS_VAR = false;
        else
        	lOGG_AKTAR("Cari Hesap",BAGLAN.cariDizin.hAN_SQL,BAGLAN.cariDizin.lOG,BAGLAN.cariDizin.lOGLAMA_YERI);
            CAR_DOS_VAR = true;
            OBS_SIS_2025_ANA_CLASS.CARI_CONN = true;
    }
    else
    	OBS_SIS_2025_ANA_CLASS.CARI_CONN = false;
	}
	void kur_calisma_dizini_oku() throws ClassNotFoundException, SQLException
{
	
	    CONNECT s_CONN = new CONNECT(oac._IConn);
	    
    if (BAGLAN.kurDizin.yER.equals(""))
    {
    	OBS_SIS_2025_ANA_CLASS.KUR_CONN = false;
        KUR_DOS_VAR = false;
        return;
    }
    if (BAGLAN.kurDizin.yER.equals("L"))
    {
    	if (s_CONN.Server_kontrol_L(BAGLAN.kurDizin.iNSTANCE, BAGLAN.kurDizin.kULLANICI, BAGLAN.kurDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true)   
    	{
    		if (s_CONN.Dosya_kontrol_L("OK_Kur" + BAGLAN.kurDizin.kOD,BAGLAN.kurDizin.iNSTANCE, BAGLAN.kurDizin.kULLANICI, BAGLAN.kurDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == false)
            {
            	KUR_DOS_VAR = false;
            	}
            else
            {
            	lOGG_AKTAR("Kur",BAGLAN.kurDizin.hAN_SQL,BAGLAN.kurDizin.lOG,BAGLAN.kurDizin.lOGLAMA_YERI);
            KUR_DOS_VAR = true;
            OBS_SIS_2025_ANA_CLASS.KUR_CONN = true;}
    	}
    	 else
             OBS_SIS_2025_ANA_CLASS.KUR_CONN = false;
    }
    else if (s_CONN.Server_kontrol_S(BAGLAN.kurDizin.sERVER, BAGLAN.kurDizin.iNSTANCE,BAGLAN.kurDizin.kULLANICI, BAGLAN.kurDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true )
    {
            if (s_CONN.Dosya_kontrol_S(BAGLAN.kurDizin.sERVER,BAGLAN.kurDizin.iNSTANCE, BAGLAN.kurDizin.kULLANICI, BAGLAN.kurDizin.sIFRESI,"OK_Kur" + BAGLAN.kurDizin.kOD,BAGLAN.cariDizin.sERVER) == false)
            KUR_DOS_VAR = false;
        else
        	lOGG_AKTAR("Kur",BAGLAN.kurDizin.hAN_SQL,BAGLAN.kurDizin.lOG,BAGLAN.kurDizin.lOGLAMA_YERI);
            KUR_DOS_VAR = true;
            OBS_SIS_2025_ANA_CLASS.KUR_CONN = true;
    }
    else
    	OBS_SIS_2025_ANA_CLASS.KUR_CONN = false;
}
	void sms_calisma_dizini_oku() throws ClassNotFoundException, SQLException
{
	
	    CONNECT s_CONN = new CONNECT(oac._IConn);
	    
	    if (BAGLAN.smsDizin.yER.equals(""))
	    {
	    	OBS_SIS_2025_ANA_CLASS.SMS_CONN = false;
	    	SMS_DOS_VAR = false;
	    	return;
	    }
	    if (BAGLAN.smsDizin.yER.equals("L"))
	    {
	    	if (s_CONN.Server_kontrol_L(BAGLAN.smsDizin.iNSTANCE, BAGLAN.smsDizin.kULLANICI, BAGLAN.smsDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true)   
	    	{
	    		if (s_CONN.Dosya_kontrol_L("OK_Sms" + BAGLAN.smsDizin.kOD,BAGLAN.smsDizin.iNSTANCE, BAGLAN.smsDizin.kULLANICI, BAGLAN.smsDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == false)
	    		{
	    			SMS_DOS_VAR = false;
	    		}
	    		else
	    		{
	    			lOGG_AKTAR("Sms",BAGLAN.smsDizin.hAN_SQL,BAGLAN.smsDizin.lOG,BAGLAN.smsDizin.lOGLAMA_YERI);
	    			SMS_DOS_VAR = true;
	    			OBS_SIS_2025_ANA_CLASS.SMS_CONN = true;}
	    	}
	    	else
          OBS_SIS_2025_ANA_CLASS.SMS_CONN = false;
	    }
	    else if (s_CONN.Server_kontrol_S(BAGLAN.smsDizin.sERVER, BAGLAN.smsDizin.iNSTANCE,BAGLAN.smsDizin.kULLANICI, BAGLAN.smsDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true )
	    {
         if (s_CONN.Dosya_kontrol_S(BAGLAN.smsDizin.sERVER,BAGLAN.smsDizin.iNSTANCE, BAGLAN.smsDizin.kULLANICI, BAGLAN.smsDizin.sIFRESI,"OK_Sms" + BAGLAN.smsDizin.kOD,BAGLAN.cariDizin.sERVER) == false)
        	 SMS_DOS_VAR = false;
         else
        	 	lOGG_AKTAR("Sms",BAGLAN.smsDizin.hAN_SQL,BAGLAN.smsDizin.lOG,BAGLAN.smsDizin.lOGLAMA_YERI);
         		SMS_DOS_VAR = true;
         		OBS_SIS_2025_ANA_CLASS.SMS_CONN = true;
	    }
	    else
	    	OBS_SIS_2025_ANA_CLASS.SMS_CONN = false;
}
	void adr_calisma_dizini_oku() throws ClassNotFoundException, SQLException
{
	 
	    CONNECT s_CONN = new CONNECT(oac._IConn);
	    
	    if (BAGLAN.adrDizin.yER.equals(""))
	    {
	OBS_SIS_2025_ANA_CLASS.ADR_CONN = false;
	ADR_DOS_VAR = false;
	return;
	    }
	    if (BAGLAN.adrDizin.yER.equals("L"))
	    {
	if (s_CONN.Server_kontrol_L(BAGLAN.adrDizin.iNSTANCE, BAGLAN.adrDizin.kULLANICI, BAGLAN.adrDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true)   
	{
		if (s_CONN.Dosya_kontrol_L("OK_Adr" + BAGLAN.adrDizin.kOD,BAGLAN.adrDizin.iNSTANCE, BAGLAN.adrDizin.kULLANICI, BAGLAN.adrDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == false)
      {
      	ADR_DOS_VAR = false;
      	}
      else
      {
   	  lOGG_AKTAR("Adres",BAGLAN.adrDizin.hAN_SQL,BAGLAN.adrDizin.lOG,BAGLAN.adrDizin.lOGLAMA_YERI);
    	  			ADR_DOS_VAR = true;
    	  			OBS_SIS_2025_ANA_CLASS.ADR_CONN = true;}
	}
	 else
       OBS_SIS_2025_ANA_CLASS.ADR_CONN = false;
	    }
	    else if (s_CONN.Server_kontrol_S(BAGLAN.adrDizin.sERVER, BAGLAN.adrDizin.iNSTANCE,BAGLAN.adrDizin.kULLANICI, BAGLAN.adrDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true )
	    {
	    	if (s_CONN.Dosya_kontrol_S(BAGLAN.adrDizin.sERVER,BAGLAN.adrDizin.iNSTANCE, BAGLAN.adrDizin.kULLANICI, BAGLAN.adrDizin.sIFRESI,"OK_Adr" + BAGLAN.adrDizin.kOD,BAGLAN.cariDizin.sERVER) == false)
	    		ADR_DOS_VAR = false;
	    	else
	   		lOGG_AKTAR("Adres",BAGLAN.adrDizin.hAN_SQL,BAGLAN.adrDizin.lOG,BAGLAN.adrDizin.lOGLAMA_YERI);
	    	ADR_DOS_VAR = true;
	    	OBS_SIS_2025_ANA_CLASS.ADR_CONN = true;
	    }
	    else
	OBS_SIS_2025_ANA_CLASS.ADR_CONN = false;
}
	void fat_calisma_dizini_oku() throws ClassNotFoundException, SQLException
{
	    CONNECT s_CONN = new CONNECT(oac._IConn);
    
	    if (BAGLAN.fatDizin.yER.equals(""))
	    {
	    	OBS_SIS_2025_ANA_CLASS.FAT_CONN = false;
	    	FAT_DOS_VAR = false;
	    	return;
	    }
	    if (BAGLAN.fatDizin.yER.equals("L"))
	    {
	    	if (s_CONN.Server_kontrol_L(BAGLAN.fatDizin.iNSTANCE, BAGLAN.fatDizin.kULLANICI, BAGLAN.fatDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true)   
	    	{
	    		if (s_CONN.Dosya_kontrol_L("OK_Fat" + BAGLAN.fatDizin.kOD,BAGLAN.fatDizin.iNSTANCE, BAGLAN.fatDizin.kULLANICI, BAGLAN.fatDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == false)
	    		{
	    			FAT_DOS_VAR = false;
	    		}
	    		else
	    		{
	    		  lOGG_AKTAR("Fatura",BAGLAN.fatDizin.hAN_SQL,BAGLAN.fatDizin.lOG,BAGLAN.fatDizin.lOGLAMA_YERI);
	    			FAT_DOS_VAR = true;
	    			OBS_SIS_2025_ANA_CLASS.FAT_CONN = true;}
	    	}
	    	else
	    		OBS_SIS_2025_ANA_CLASS.FAT_CONN = false;
	    }
	    else if (s_CONN.Server_kontrol_S(BAGLAN.fatDizin.sERVER, BAGLAN.fatDizin.iNSTANCE,BAGLAN.fatDizin.kULLANICI, BAGLAN.fatDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true )
	    {
	    	if (s_CONN.Dosya_kontrol_S(BAGLAN.fatDizin.sERVER,BAGLAN.fatDizin.iNSTANCE, BAGLAN.fatDizin.kULLANICI, BAGLAN.fatDizin.sIFRESI,"OK_Fat" + BAGLAN.fatDizin.kOD,BAGLAN.cariDizin.sERVER) == false)
	    		FAT_DOS_VAR = false;
	    	else
	    		 lOGG_AKTAR("Fatura",BAGLAN.fatDizin.hAN_SQL,BAGLAN.fatDizin.lOG,BAGLAN.fatDizin.lOGLAMA_YERI);
	    		FAT_DOS_VAR = true;
	    	OBS_SIS_2025_ANA_CLASS.FAT_CONN = true;
	    }
	    else
	    	OBS_SIS_2025_ANA_CLASS.FAT_CONN = false;
	}
	void kam_calisma_dizini_oku() throws ClassNotFoundException, SQLException
{
	
    CONNECT s_CONN = new CONNECT(oac._IConn);
    
    if (BAGLAN.kamDizin.yER.equals(""))
    {
    	OBS_SIS_2025_ANA_CLASS.KAM_CONN = false;
    	KAM_DOS_VAR = false;
    	return;
    }
    if (BAGLAN.kamDizin.yER.equals("L"))
    {
    	if (s_CONN.Server_kontrol_L(BAGLAN.kamDizin.iNSTANCE, BAGLAN.kamDizin.kULLANICI, BAGLAN.kamDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true)   
    	{
    		if (s_CONN.Dosya_kontrol_L("OK_Kam" + BAGLAN.kamDizin.kOD,BAGLAN.kamDizin.iNSTANCE, BAGLAN.kamDizin.kULLANICI, BAGLAN.kamDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == false)
    		{
    			KAM_DOS_VAR = false;
    		}
    		else
    		{
    			 lOGG_AKTAR("Kambiyo",BAGLAN.kamDizin.hAN_SQL,BAGLAN.kamDizin.lOG,BAGLAN.kamDizin.lOGLAMA_YERI);
    			KAM_DOS_VAR = true;
    			OBS_SIS_2025_ANA_CLASS.KAM_CONN = true;}
    	}
    	else
    		OBS_SIS_2025_ANA_CLASS.KAM_CONN = false;
    }
    else if (s_CONN.Server_kontrol_S(BAGLAN.kamDizin.sERVER, BAGLAN.kamDizin.iNSTANCE,BAGLAN.kamDizin.kULLANICI, BAGLAN.kamDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true )
    {
    	if (s_CONN.Dosya_kontrol_S(BAGLAN.kamDizin.sERVER,BAGLAN.kamDizin.iNSTANCE, BAGLAN.kamDizin.kULLANICI, BAGLAN.kamDizin.sIFRESI,"OK_Kam" + BAGLAN.kamDizin.kOD,BAGLAN.cariDizin.sERVER) == false)
    		KAM_DOS_VAR = false;
    	else
   		 lOGG_AKTAR("Kambiyo",BAGLAN.kamDizin.hAN_SQL,BAGLAN.kamDizin.lOG,BAGLAN.kamDizin.lOGLAMA_YERI);
    		KAM_DOS_VAR = true;
    	OBS_SIS_2025_ANA_CLASS.KAM_CONN = true;
    }
    else
    	OBS_SIS_2025_ANA_CLASS.KAM_CONN = false;
	}
	void gun_calisma_dizini_oku() throws ClassNotFoundException, SQLException
	{
	
    CONNECT s_CONN = new CONNECT(oac._IConn);
    
    if (BAGLAN.gunDizin.yER.equals(""))
    {
    	OBS_SIS_2025_ANA_CLASS.GUN_CONN = false;
    	GUN_DOS_VAR = false;
    	return;
    }
    if (BAGLAN.gunDizin.yER.equals("L"))
    {
    	if (s_CONN.Server_kontrol_L(BAGLAN.gunDizin.iNSTANCE, BAGLAN.gunDizin.kULLANICI, BAGLAN.gunDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true)   
    	{
    		if (s_CONN.Dosya_kontrol_L("OK_Gun" + BAGLAN.gunDizin.kOD,BAGLAN.gunDizin.iNSTANCE, BAGLAN.gunDizin.kULLANICI, BAGLAN.gunDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == false)
    		{
    			GUN_DOS_VAR = false;
    		}
    		else
    		{
    			 lOGG_AKTAR("Gunluk",BAGLAN.gunDizin.hAN_SQL,BAGLAN.gunDizin.lOG,BAGLAN.gunDizin.lOGLAMA_YERI);
    			GUN_DOS_VAR = true;
    			OBS_SIS_2025_ANA_CLASS.GUN_CONN = true;}
    	}
    	else
    		OBS_SIS_2025_ANA_CLASS.GUN_CONN = false;
    }
    else if (s_CONN.Server_kontrol_S(BAGLAN.gunDizin.sERVER, BAGLAN.gunDizin.iNSTANCE,BAGLAN.gunDizin.kULLANICI, BAGLAN.gunDizin.sIFRESI,BAGLAN.cariDizin.sERVER) == true )
    {
    	if (s_CONN.Dosya_kontrol_S(BAGLAN.gunDizin.sERVER,BAGLAN.gunDizin.iNSTANCE, BAGLAN.gunDizin.kULLANICI, BAGLAN.gunDizin.sIFRESI,"OK_Gun" + BAGLAN.gunDizin.kOD,BAGLAN.cariDizin.sERVER) == false)
    		GUN_DOS_VAR = false;
    	else
   		 lOGG_AKTAR("Gunluk",BAGLAN.gunDizin.hAN_SQL,BAGLAN.gunDizin.lOG,BAGLAN.gunDizin.lOGLAMA_YERI);
    		GUN_DOS_VAR = true;
    	OBS_SIS_2025_ANA_CLASS.GUN_CONN = true;
    }
    else
    	OBS_SIS_2025_ANA_CLASS.GUN_CONN = false;
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
	 
    	String serverAddress = "ftp.okumus.gen.tr";
    	String userId ="u5789784";
    	String password ="4wX.5Wx53-Y..nlG";
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
	private void cONN_AKTAR( String dIZIN )
	{
	 String hangi = dIZIN  ;
		if (hangi.equals("MS SQL"))
		{
			oac._IConn = new OBS_ORTAK_MSSQL();
		}
		else
		{
			oac._IConn = new OBS_ORTAK_MYSQL();
		}
	}
	private void lOG_AKTAR() {
	      BAGLAN_LOG _blog = new BAGLAN_LOG();
          _blog.cONNECT();
	}
	private void lOGG_AKTAR(String mODUL, String hangiSQL , Boolean log , String hANGI_LOG) throws ClassNotFoundException, SQLException
	{
		if (log == false)
		{
			ILOGGER[] ilogg = {};
			lAktar(mODUL , ilogg);
		}
		else
		{
			if (hANGI_LOG.equals("Dosyaya Kayit"))
			{
				if (hangiSQL.equals("MS SQL"))
				{
					ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL())};
					lAktar(mODUL , ilogg);
				}
				else if (hangiSQL.equals("MY SQL"))
				{
					ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL())};
					lAktar(mODUL , ilogg);
				}
			}
			else if (hANGI_LOG.equals("Email Atma"))
			{
	
				LOG_MAIL_OKU.mail_oku();
				if (GLOBAL.Log_Mail.equals(""))
				{
					ILOGGER[] ilogg = {};
					lAktar(mODUL , ilogg);
				}
				else
				{
					ILOGGER[] ilogg = {new MAIL_AT()};
					lAktar(mODUL , ilogg);
				}
		}
		}
	}
	private void lAktar(String mODUL , ILOGGER[] ilogg)
	{
		if (mODUL.equals("Cari Hesap"))
		{oac._ICari_Loger = ilogg;}
		else if (mODUL.equals("Kur"))
		{oac._IKur_Loger = ilogg;}
		else if (mODUL.equals("Adres"))
		{oac._IAdres_Loger = ilogg;}
		else if (mODUL.equals("Fatura"))
		{oac._IFatura_Loger = ilogg;}
		else if (mODUL.equals("Sms"))
		{oac._ISms_Loger = ilogg;}
		else if (mODUL.equals("Gunluk"))
		{oac._IGunluk_Loger = ilogg;}
		else if (mODUL.equals("Kambiyo"))
		{oac._IKambiyo_Loger = ilogg;}
	}
	
}


