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
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import LOGER_KAYIT.DOSYA_MSSQL;
import LOGER_KAYIT.DOSYA_MYSQL;
import LOGER_KAYIT.SQLITE_LOG;
import LOGER_KAYIT.TXT_LOG;
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
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
@SuppressWarnings("static-access")
public class LOGIN extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	boolean vt = false;
	boolean ds = false;
	boolean tx = false;
	boolean em = false;
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

		//************************************************************************
		try {
			LOGIN.setDefaultLookAndFeelDecorated(true);
			if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("TextureLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("SmartLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("NoireLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("AcrylLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("AeroLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("AluminiumLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("BernsteinLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("FastLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("GraphiteLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("HiFiLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("LunaLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("McWinLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			}
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("MintLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
			}
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

		//************************************************************************
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
		txtUser.setBounds(310, 11, 110, 20);
		txtUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtUser.setDocument(new JTextFieldLimit(10));
		txtUser.setColumns(10);
		JTextFieldRegularPopupMenu.addTo(txtUser);
		txtUser.setColumns(10);
		panel.add(txtUser);

		txtpwd = new JPasswordField();
		txtpwd.setBounds(310, 37, 110, 20);
		panel.add(txtpwd);
		txtpwd.setFont(new Font("Tahoma", Font.BOLD, 12));
		JTextFieldRegularPopupMenu.addTo(txtpwd);

		chckbxhatirla = new JCheckBox("Beni Hatirla");
		chckbxhatirla.setForeground(new Color(0, 0, 128));
		chckbxhatirla.setBounds(307, 60, 112, 23);
		panel.add(chckbxhatirla);

		JButton btncikis = new JButton("Cikis");
		btncikis.setBounds(190, 92, 110, 23);
		panel.add(btncikis);
		btncikis.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/exit.png")));
		btncikis.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(1);
			}
		});
		btncikis.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btncdizin = new JButton("Calisma Dizini");
		btncdizin.setBounds(190, 122, 110, 23);
		panel.add(btncdizin);
		btncdizin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
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
		btngiris.setBounds(310, 92, 110, 23);
		panel.add(btngiris);
		btngiris.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/add-user-16.png")));
		btngiris.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try 
				{
					byte[]  qaz =	ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(oac.sDONDUR.sDONDUR(txtpwd)) ;
					String response = Arrays.toString(qaz);
					boolean varmi =	oac.uSER_ISL.user_var(txtUser.getText(),response);
					if (varmi == true)
					{
						btndevam.setVisible(true);
						btncdizin.setVisible(true);
						GLOBAL.KULL_ADI = txtUser.getText();
						if (chckbxhatirla.isSelected())
						{ 
							GLOBAL.setting_yaz("BENI_HATIRLA", "E");	
							GLOBAL.setting_yaz("ISIM", txtUser.getText());
							GLOBAL.setting_yaz("SIFRE", response);
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
		btndevam.setBounds(310, 122, 110, 23);
		panel.add(btndevam);
		btndevam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runnable runner = new Runnable()
				{ public void run() {
					contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					try
					{
						calisma_dizini_oku();
						CARI_ACCESS  c_Access = new CARI_ACCESS(oac._ICar , oac._ICari_Loger);
						c_Access.baglan();
						KUR_ACCESS  k_Access = new KUR_ACCESS(oac._IKur, oac._IKur_Loger);
						k_Access.baglan();
						ADRES_ACCESS  a_Access = new ADRES_ACCESS(oac._IAdres, oac._IAdres_Loger);
						a_Access.baglan();
						STOK_ACCESS  s_Access = new STOK_ACCESS(oac._IStok,oac._IFatura_Loger);
						s_Access.baglan();
						KAMBIYO_ACCESS  ka_Access = new KAMBIYO_ACCESS(oac._IKambiyo, oac._IKambiyo_Loger);
						ka_Access.baglan();
						GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(oac._IGunluk, oac._IGunluk_Loger);
						g_Access.baglan();
						SMS_ACCESS  sms_Access = new SMS_ACCESS(oac._ISms, oac._ISms_Loger);
						sms_Access.baglan();
						OBS_MAIN obmain = new OBS_MAIN();
						Login_Progres_Bar_Temizle();
						cari_kont();
						kur_kont();
						sms_kont();
						adr_kont(); 
						fat_kont();
						kam_kont();
						gun_kont();
						Thread.currentThread().isInterrupted();
						obmain.setFont(new Font("Tahoma", Font.BOLD, 11));
						obmain.setTitle("OBS SISTEM" + "               " + GLOBAL.KULL_ADI);
						obmain.setVisible(true);
						dispose();
						contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
					catch (Exception ex)
					{
						contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						JOptionPane.showMessageDialog(null,  ex.getMessage().toString(),  "OBS SISTEM1", JOptionPane.ERROR_MESSAGE);        
					}
				}
				};
				Thread t = new Thread(runner, "Code Executer");
				t.start();
			}
		});
		btndevam.setVisible(false);
		btndevam.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/icons8-jog-forward-16.png")));
		btndevam.setFont(new Font("Tahoma", Font.PLAIN, 11));

		progressBar = new JProgressBar();
		splitPane.setRightComponent(progressBar);
		//progressBar.setForeground(new Color(166, 55, 55));
		progressBar.setBorder(new LineBorder(new Color(0, 191, 255)));
		progressBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 11));
		progressBar.setMinimumSize(new Dimension(0, 25));
		progressBar.setMaximumSize(new Dimension(0, 25));
		
		//************SURUCU KONTROL**************************
		GLOBAL.surucu_kontrol();
		//VERSION KONTROL/////
		versiyon_oku();
		//************BENI_HATIRLA**	
		String deger;
		deger = GLOBAL.setting_oku("BENI_HATIRLA").toString();
		if (new String(deger).equals("E") == true) 
		{
			chckbxhatirla.setSelected(true);
			String decodedString = GLOBAL.setting_oku("SIFRE").toString();
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) {
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			txtUser.setText(GLOBAL.setting_oku("ISIM").toString());
			try 
			{
				txtpwd.setText( ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes));
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
					| UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e1) {
				e1.printStackTrace();
			}
			contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	void cari_kont() throws ClassNotFoundException, SQLException
	{
		String qwe = "" ;
		if (OBS_SIS_2025_ANA_CLASS .CARI_CONN == true)
		{
			if (CAR_DOS_VAR == false)
			{
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(null, "Calisilan Cari -" + BAGLAN.cariDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
				qwe = BAGLAN.cariDizin.yER.equals("S") ?  BAGLAN.cariDizin.sERVER : "Lokal" ;
				OBS_MAIN.lblNewLabel_1.setText ( "Cari:" + BAGLAN.cariDizin.kOD + "/ " + BAGLAN.cariDizin.fIRMA_ADI + "/ " + qwe );
				OBS_MAIN.tabbedPane.setEnabledAt(0,false);
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
	}
	void kur_kont()
	{
		String qwe = "" ;
		if (OBS_SIS_2025_ANA_CLASS.KUR_CONN == true)
		{
			if (KUR_DOS_VAR == false)
			{
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(null, "Calisilan Kur -" + BAGLAN.kurDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
				OBS_MAIN.tabbedPane.setEnabledAt(1, false);
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
	}
	void sms_kont()
	{
		String qwe = "" ;
		if (OBS_SIS_2025_ANA_CLASS.SMS_CONN == true)
		{
			if (SMS_DOS_VAR == false)
			{
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(null, "Calisilan Sms -" + BAGLAN.smsDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
				OBS_MAIN.tabbedPane.setEnabledAt(6, false);
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
			OBS_MAIN.tabbedPane.setEnabledAt(6, false);
		}
	}
	void adr_kont() throws ClassNotFoundException, SQLException
	{
		String qwe = "" ;
		if (OBS_SIS_2025_ANA_CLASS.ADR_CONN == true)
		{
			if (ADR_DOS_VAR == false)
			{
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(null, "Calisilan Adres -" + BAGLAN.adrDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
				OBS_MAIN.tabbedPane.setEnabledAt(2, false);
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
			OBS_MAIN.tabbedPane.setEnabledAt(2, false);
		}
	}
	void fat_kont() throws ClassNotFoundException, SQLException
	{
		String qwe = "" ;
		if (OBS_SIS_2025_ANA_CLASS.FAT_CONN == true)
		{
			if (FAT_DOS_VAR == false)
			{
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(null, "Calisilan Stok -" + BAGLAN.fatDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
				OBS_MAIN.tabbedPane.setEnabledAt(3, false);
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
			OBS_MAIN.tabbedPane.setEnabledAt(3, false);
		}
	}
	void kam_kont() throws ClassNotFoundException, SQLException
	{
		String qwe = "" ;
		if (OBS_SIS_2025_ANA_CLASS.KAM_CONN == true)
		{
			if (KAM_DOS_VAR == false)
			{
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(null, "Calisilan Kambiyo -" +BAGLAN.kamDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
				OBS_MAIN.tabbedPane.setEnabledAt(4, false);
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
			OBS_MAIN.tabbedPane.setEnabledAt(4, false);
		}
	}
	void gun_kont() throws ClassNotFoundException, SQLException
	{
		String qwe = "" ;
		if (OBS_SIS_2025_ANA_CLASS.GUN_CONN == true)
		{
			if (GUN_DOS_VAR == false)
			{
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(null, "Calisilan Gunluk -" + BAGLAN.gunDizin.kOD + "- Nolu Dosya Bulunamadi.....",  "Dosya Baglanti", JOptionPane.ERROR_MESSAGE);        
				OBS_MAIN.tabbedPane.setEnabledAt(5, false);
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
			OBS_MAIN.tabbedPane.setEnabledAt(5, false);
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
		oac._ICariCon = oac._IConn ;
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
		oac._IKurCon = oac._IConn ;
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
		oac._ISmsCon = oac._IConn ;
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
		oac._IAdresCon = oac._IConn ;
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
		oac._IStokCon = oac._IConn ;
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
		oac._IKambiyoCon = oac._IConn ;
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
		oac._IGunlukCon = oac._IConn ;
		if (hangi_sql.equals("MS SQL"))
		{
			oac._IGunluk = new GUNLUK_MSSQL();
		}
		else
		{
			oac._IGunluk = new GUNLUK_MYSQL();
		}
		lOG_AKTAR();
		oac.uSER_ISL.mail_bak(); // MAIL AYARLARI OKUMA
		LOG_MAIL_OKU.mail_oku(); // LOGLAMA MAILI OKUMA
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
		CONNECT s_CONN = new CONNECT( oac._ICariCon);
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
			if (s_CONN.Dosya_kontrol_S(BAGLAN.cariDizin.sERVER,BAGLAN.cariDizin.iNSTANCE, BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI,"ok_car" + BAGLAN.cariDizin.kOD,BAGLAN.cariDizin.sERVER) == false)
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
		CONNECT s_CONN = new CONNECT(oac._IKurCon);
		if (BAGLAN.kurDizin.yER.equals(""))
		{
			OBS_SIS_2025_ANA_CLASS.KUR_CONN = false;
			KUR_DOS_VAR = false;
			return;
		}
		if (BAGLAN.kurDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(BAGLAN.kurDizin.iNSTANCE, BAGLAN.kurDizin.kULLANICI, BAGLAN.kurDizin.sIFRESI,BAGLAN.kurDizin.sERVER) == true)   
			{
				if (s_CONN.Dosya_kontrol_L("OK_Kur" + BAGLAN.kurDizin.kOD,BAGLAN.kurDizin.iNSTANCE, BAGLAN.kurDizin.kULLANICI, BAGLAN.kurDizin.sIFRESI,BAGLAN.kurDizin.sERVER) == false)
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
		else if (s_CONN.Server_kontrol_S(BAGLAN.kurDizin.sERVER, BAGLAN.kurDizin.iNSTANCE,BAGLAN.kurDizin.kULLANICI, BAGLAN.kurDizin.sIFRESI,BAGLAN.kurDizin.sERVER) == true )
		{
			if (s_CONN.Dosya_kontrol_S(BAGLAN.kurDizin.sERVER,BAGLAN.kurDizin.iNSTANCE, BAGLAN.kurDizin.kULLANICI, BAGLAN.kurDizin.sIFRESI,"OK_Kur" + BAGLAN.kurDizin.kOD,BAGLAN.kurDizin.sERVER) == false)
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
		CONNECT s_CONN = new CONNECT(oac._ISmsCon);
		if (BAGLAN.smsDizin.yER.equals(""))
		{
			OBS_SIS_2025_ANA_CLASS.SMS_CONN = false;
			SMS_DOS_VAR = false;
			return;
		}
		if (BAGLAN.smsDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(BAGLAN.smsDizin.iNSTANCE, BAGLAN.smsDizin.kULLANICI, BAGLAN.smsDizin.sIFRESI,BAGLAN.smsDizin.sERVER) == true)   
			{
				if (s_CONN.Dosya_kontrol_L("OK_Sms" + BAGLAN.smsDizin.kOD,BAGLAN.smsDizin.iNSTANCE, BAGLAN.smsDizin.kULLANICI, BAGLAN.smsDizin.sIFRESI,BAGLAN.smsDizin.sERVER) == false)
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
		else if (s_CONN.Server_kontrol_S(BAGLAN.smsDizin.sERVER, BAGLAN.smsDizin.iNSTANCE,BAGLAN.smsDizin.kULLANICI, BAGLAN.smsDizin.sIFRESI,BAGLAN.smsDizin.sERVER) == true )
		{
			if (s_CONN.Dosya_kontrol_S(BAGLAN.smsDizin.sERVER,BAGLAN.smsDizin.iNSTANCE, BAGLAN.smsDizin.kULLANICI, BAGLAN.smsDizin.sIFRESI,"OK_Sms" + BAGLAN.smsDizin.kOD,BAGLAN.smsDizin.sERVER) == false)
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
		CONNECT s_CONN = new CONNECT(oac._IAdresCon);
		if (BAGLAN.adrDizin.yER.equals(""))
		{
			OBS_SIS_2025_ANA_CLASS.ADR_CONN = false;
			ADR_DOS_VAR = false;
			return;
		}
		if (BAGLAN.adrDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(BAGLAN.adrDizin.iNSTANCE, BAGLAN.adrDizin.kULLANICI, BAGLAN.adrDizin.sIFRESI,BAGLAN.adrDizin.sERVER) == true)   
			{
				if (s_CONN.Dosya_kontrol_L("OK_Adr" + BAGLAN.adrDizin.kOD,BAGLAN.adrDizin.iNSTANCE, BAGLAN.adrDizin.kULLANICI, BAGLAN.adrDizin.sIFRESI,BAGLAN.adrDizin.sERVER) == false)
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
		else if (s_CONN.Server_kontrol_S(BAGLAN.adrDizin.sERVER, BAGLAN.adrDizin.iNSTANCE,BAGLAN.adrDizin.kULLANICI, BAGLAN.adrDizin.sIFRESI,BAGLAN.adrDizin.sERVER) == true )
		{
			if (s_CONN.Dosya_kontrol_S(BAGLAN.adrDizin.sERVER,BAGLAN.adrDizin.iNSTANCE, BAGLAN.adrDizin.kULLANICI, BAGLAN.adrDizin.sIFRESI,"OK_Adr" + BAGLAN.adrDizin.kOD,BAGLAN.adrDizin.sERVER) == false)
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
		CONNECT s_CONN = new CONNECT(oac._IStokCon);
		if (BAGLAN.fatDizin.yER.equals(""))
		{
			OBS_SIS_2025_ANA_CLASS.FAT_CONN = false;
			FAT_DOS_VAR = false;
			return;
		}
		if (BAGLAN.fatDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(BAGLAN.fatDizin.iNSTANCE, BAGLAN.fatDizin.kULLANICI, BAGLAN.fatDizin.sIFRESI,BAGLAN.fatDizin.sERVER) == true)   
			{
				if (s_CONN.Dosya_kontrol_L("OK_Fat" + BAGLAN.fatDizin.kOD,BAGLAN.fatDizin.iNSTANCE, BAGLAN.fatDizin.kULLANICI, BAGLAN.fatDizin.sIFRESI,BAGLAN.fatDizin.sERVER) == false)
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
		else if (s_CONN.Server_kontrol_S(BAGLAN.fatDizin.sERVER, BAGLAN.fatDizin.iNSTANCE,BAGLAN.fatDizin.kULLANICI, BAGLAN.fatDizin.sIFRESI,BAGLAN.fatDizin.sERVER) == true )
		{
			if (s_CONN.Dosya_kontrol_S(BAGLAN.fatDizin.sERVER,BAGLAN.fatDizin.iNSTANCE, BAGLAN.fatDizin.kULLANICI, BAGLAN.fatDizin.sIFRESI,"OK_Fat" + BAGLAN.fatDizin.kOD,BAGLAN.fatDizin.sERVER) == false)
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
		CONNECT s_CONN = new CONNECT(oac._IKambiyoCon);
		if (BAGLAN.kamDizin.yER.equals(""))
		{
			OBS_SIS_2025_ANA_CLASS.KAM_CONN = false;
			KAM_DOS_VAR = false;
			return;
		}
		if (BAGLAN.kamDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(BAGLAN.kamDizin.iNSTANCE, BAGLAN.kamDizin.kULLANICI, BAGLAN.kamDizin.sIFRESI,BAGLAN.kamDizin.sERVER) == true)   
			{
				if (s_CONN.Dosya_kontrol_L("OK_Kam" + BAGLAN.kamDizin.kOD,BAGLAN.kamDizin.iNSTANCE, BAGLAN.kamDizin.kULLANICI, BAGLAN.kamDizin.sIFRESI,BAGLAN.kamDizin.sERVER) == false)
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
		else if (s_CONN.Server_kontrol_S(BAGLAN.kamDizin.sERVER, BAGLAN.kamDizin.iNSTANCE,BAGLAN.kamDizin.kULLANICI, BAGLAN.kamDizin.sIFRESI,BAGLAN.kamDizin.sERVER) == true )
		{
			if (s_CONN.Dosya_kontrol_S(BAGLAN.kamDizin.sERVER,BAGLAN.kamDizin.iNSTANCE, BAGLAN.kamDizin.kULLANICI, BAGLAN.kamDizin.sIFRESI,"OK_Kam" + BAGLAN.kamDizin.kOD,BAGLAN.kamDizin.sERVER) == false)
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
		CONNECT s_CONN = new CONNECT(oac._IGunlukCon);
		if (BAGLAN.gunDizin.yER.equals(""))
		{
			OBS_SIS_2025_ANA_CLASS.GUN_CONN = false;
			GUN_DOS_VAR = false;
			return;
		}
		if (BAGLAN.gunDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(BAGLAN.gunDizin.iNSTANCE, BAGLAN.gunDizin.kULLANICI, BAGLAN.gunDizin.sIFRESI,BAGLAN.gunDizin.sERVER) == true)   
			{
				if (s_CONN.Dosya_kontrol_L("OK_Gun" + BAGLAN.gunDizin.kOD,BAGLAN.gunDizin.iNSTANCE, BAGLAN.gunDizin.kULLANICI, BAGLAN.gunDizin.sIFRESI,BAGLAN.gunDizin.sERVER) == false)
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
		else if (s_CONN.Server_kontrol_S(BAGLAN.gunDizin.sERVER, BAGLAN.gunDizin.iNSTANCE,BAGLAN.gunDizin.kULLANICI, BAGLAN.gunDizin.sIFRESI,BAGLAN.gunDizin.sERVER) == true )
		{
			if (s_CONN.Dosya_kontrol_S(BAGLAN.gunDizin.sERVER,BAGLAN.gunDizin.iNSTANCE, BAGLAN.gunDizin.kULLANICI, BAGLAN.gunDizin.sIFRESI,"OK_Gun" + BAGLAN.gunDizin.kOD,BAGLAN.gunDizin.sERVER) == false)
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
	@SuppressWarnings("unused")
	private void versiyon_oku()
	{
		if (oac.glb.internet_kontrol() == false)
		{
			return ;
		}
		try 
		{
			String eskitar = "" ;
			String eskiver = "";
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
			//String serverAddress = "ftp.okumus.gen.tr";
			//String userId ="u5789784";
			//String password ="4wX.5Wx53-Y..nlG";
			String serverAddress = "78.189.76.247";
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
			String remoteFile1 = ftp.printWorkingDirectory() + "/OBS_SISTEM_2025/OBS_VERSION.txt";
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
			String yenitar = "" ;
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
				File f= new File("C:/OBS_SISTEM/" + "OBS_VERSIONS.txt");           //file to be delete  
				f.delete();  
				String html = "<html><body width='%1s'><h1>OBS SISTEM</h1>"
						+ "Yeni Versiyon Mevcut.......	 "
						+ "<br><br> "
						+ "Mevcut Version = " +eskiver + "      "
						+ "<br><br> "
						+ "Yeni Version = " + yeniver + "" ;
				JOptionPane.showMessageDialog(null, String.format(html, "OBS SISTEM", JOptionPane.PLAIN_MESSAGE));
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
	private void lOG_AKTAR() 
	{
		BAGLAN_LOG _blog = new BAGLAN_LOG();
		_blog.cONNECT();
	}
	private void lOGG_AKTAR(String mODUL, String hangiSQL , Boolean log , String hANGI_LOG) throws ClassNotFoundException, SQLException
	{
		String[] token = hANGI_LOG.split(",");
		vt =  (token[0].equals("true") ? true:false);
		ds = (token[1].equals("true") ? true:false);
		tx = (token[2].equals("true") ? true:false);
		em =  (token[3].equals("true") ? true:false);
		if (log == false)
		{
			ILOGGER[] ilogg = {};
			lAktar(mODUL , ilogg);
		}
		else
		{
			if (vt ) // VERITABANI KONTROL
			{
				if (hangiSQL.equals("MS SQL"))
				{
					if (ds) // SQLITE DOSYA KONTROL
					{
						if(tx) // TEXT DOSYA KONTROL
						{
							if(em) // EMAIL KONTROL
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new DOSYA_YAZ(new SQLITE_LOG()),new DOSYA_YAZ(new TXT_LOG()),new MAIL_AT()};
								lAktar(mODUL , ilogg);
							}
							else
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new DOSYA_YAZ(new SQLITE_LOG()),new DOSYA_YAZ(new TXT_LOG())};
								lAktar(mODUL , ilogg);
							}
						}
						else
						{
							if(em)
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new DOSYA_YAZ(new SQLITE_LOG()),new MAIL_AT()};
								lAktar(mODUL , ilogg);
							}
							else
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new DOSYA_YAZ(new SQLITE_LOG())};
								lAktar(mODUL , ilogg);
							}
						}
					}
					else // DOSYA YOK 
					{
						if(tx)
						{
							if(em)
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new DOSYA_YAZ(new TXT_LOG()),new MAIL_AT()};
								lAktar(mODUL , ilogg);
							}
							else
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new DOSYA_YAZ(new TXT_LOG())};
								lAktar(mODUL , ilogg);
							}
						}
						else
						{
							if(em)
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new MAIL_AT()};
								lAktar(mODUL , ilogg);
							}
							else
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL())};
								lAktar(mODUL , ilogg);
							}
						}
					}
				}
				else  // MYSQL
				{
					if (ds)
					{
						if(tx)
						{
							if(em)
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new DOSYA_YAZ(new SQLITE_LOG()),new DOSYA_YAZ(new TXT_LOG()),new MAIL_AT()};
								lAktar(mODUL , ilogg);
							}
							else
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new DOSYA_YAZ(new SQLITE_LOG()),new DOSYA_YAZ(new TXT_LOG())};
								lAktar(mODUL , ilogg);
							}
						}
						else
						{
							if(em)
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new DOSYA_YAZ(new SQLITE_LOG()),new MAIL_AT()};
								lAktar(mODUL , ilogg);
							}
							else
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new DOSYA_YAZ(new SQLITE_LOG())};
								lAktar(mODUL , ilogg);
							}
						}
					}
					else // DOSYA YOK 
					{
						if(tx)
						{
							if(em)
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new DOSYA_YAZ(new TXT_LOG()),new MAIL_AT()};
								lAktar(mODUL , ilogg);
							}
							else
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new DOSYA_YAZ(new TXT_LOG())};
								lAktar(mODUL , ilogg);
							}
						}
						else
						{
							if(em)
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new MAIL_AT()};
								lAktar(mODUL , ilogg);
							}
							else
							{
								ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL())};
								lAktar(mODUL , ilogg);
							}
						}
					}
				}
			}
			else // VT YOK
			{
				if (ds)
				{
					if(tx)
					{
						if(em)
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new SQLITE_LOG()),new DOSYA_YAZ(new TXT_LOG()),new MAIL_AT()};
							lAktar(mODUL , ilogg);
						}
						else
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new SQLITE_LOG()),new DOSYA_YAZ(new TXT_LOG())};
							lAktar(mODUL , ilogg);
						}
					}
					else
					{
						if(em)
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new SQLITE_LOG()),new MAIL_AT()};
							lAktar(mODUL , ilogg);
						}
						else
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new SQLITE_LOG())};
							lAktar(mODUL , ilogg);
						}
					}
				}
				else // DOSYA YOK 
				{
					if(tx)
					{
						if(em)
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new TXT_LOG()),new MAIL_AT()};
							lAktar(mODUL , ilogg);
						}
						else
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new TXT_LOG())};
							lAktar(mODUL , ilogg);
						}
					}
					else
					{
						if(em)
						{
							ILOGGER[] ilogg = {new MAIL_AT()};
							lAktar(mODUL , ilogg);
						}
						else
						{
							ILOGGER[] ilogg = {};
							lAktar(mODUL , ilogg);
						}
					}
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


