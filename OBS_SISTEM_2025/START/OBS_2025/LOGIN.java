package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.poi.sl.usermodel.Background;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.icons.FlatClearIcon;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGrayIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHighContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonocaiIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSpacegrayIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneLightIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatDraculaIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMonokaiProIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

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
import OBS_C_2025.ScrollBarWin11UI;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.KERESTE_MSSQL;
import OBS_C_2025.KERESTE_MYSQL;
import OBS_C_2025.Server_Bilgi;
import OBS_C_2025.sayiyiYaziyaCevir;
import raven.toast.Notifications;

import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
@SuppressWarnings({"static-access","unused"})
public class LOGIN extends JDialog {
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
	boolean KER_DOS_VAR;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtpwd;
	private JProgressBar progressBar;
	private static  JCheckBox chckbxhatirla;
	private JButton btndevam;
	private JButton btncdizin;
	private JLabel lblModul ;
	OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	BAGLAN bAGLAN = new BAGLAN();
	BAGLAN_LOG bAGLAN_LOG = new BAGLAN_LOG();
	GLOBAL glb = new GLOBAL();
	JButton btngiris ;
	boolean vt = false;
	boolean ds = false;
	boolean tx = false;
	boolean em = false;
	public Thread t = null ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{	
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					new LOGIN().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LOGIN() throws IOException {
		//************************************************************************
		try 
		{ 
			FlatLaf.registerCustomDefaultsSource("OBS_2025");
			LOGIN.setDefaultLookAndFeelDecorated(true);

			if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("TextureLookAndFeel")) 
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel"); 
			}
			else if (GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("SmartLookAndFeel")) 
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
			} else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("AeroLookAndFeel"))
			{ 
				UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel"); 
			} else  if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("AluminiumLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			} 
			else if (  GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("BernsteinLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
			} 
			else if (GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("FastLookAndFeel")) 
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel"); 
			} 
			else if( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("GraphiteLookAndFeel")) 
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel"); }
			else if (GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("HiFiLookAndFeel")) 
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel"); 
			} 
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("LunaLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel"); 
			} 
			else if  ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("McWinLookAndFeel"))
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel"); 
			} 
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("MintLookAndFeel"))
			{ 
				UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel"); 
			} 
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("Windows")) 
			{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
			} 
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("FlatSolarizedLightIJTheme")) 
			{
				//FlatAnimatedLafChange.showSnapshot();
				FlatSolarizedLightIJTheme.setup();
				//	FlatDarkLaf.setup();
				 FlatLaf.updateUI();
				// FlatAnimatedLafChange.hideSnapshotWithAnimation();

			} 
			else if (GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("FlatArcOrangeIJTheme"))
			{ 
				FlatArcOrangeIJTheme.setup();
				FlatLaf.updateUI();
			} 
			else if (GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("FlatSpacegrayIJTheme"))
			{ 
				FlatSpacegrayIJTheme.setup();
				FlatLaf.updateUI();
			} 
			else if (GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("FlatArcIJTheme")) 
			{
				FlatArcIJTheme.setup();
				FlatLaf.updateUI();
			} else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("FlatCyanLightIJTheme"))
			{ 
				FlatCyanLightIJTheme.setup();
				FlatLaf.updateUI();
			} 
			else if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("FlatGradiantoNatureGreenIJTheme")) 
			{
				FlatGradiantoNatureGreenIJTheme.setup();
				FlatLaf.updateUI();
			}
			else if (GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("FlatHighContrastIJTheme" )) 
			{ 
				FlatHighContrastIJTheme.setup();
				FlatLaf.updateUI();
			} 
			else if (GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("FlatGrayIJTheme"))
			{
				FlatGrayIJTheme.setup();
				FlatLaf.updateUI();
			} 
			else if (GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("FlatNordIJTheme")) 
			{
				FlatNordIJTheme.setup();
				FlatLaf.updateUI();
			}
			SwingUtilities.updateComponentTreeUI(this); //UIManager.setLookAndFeel(new
			//FlatSolarizedLightIJTheme());

		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util. logging.Level.SEVERE, null, ex); 
		}
 		//************************************************************************
		setIconImage(Toolkit.getDefaultToolkit().getImage(LOGIN.class.getResource("/ICONLAR/icon-obs-32.png")));
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
		lblicon.setBounds(29, 13, 137, 134);
		panel.add(lblicon);
		lblicon.setHorizontalAlignment(SwingConstants.CENTER);
		lblicon.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/icons8-application-96.png")));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/userb-16.png")));
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBounds(284, 15, 16, 16);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/password-16.png")));

		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setBounds(284, 41, 16, 16);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		txtUser = new JTextField();
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{	
					btngiris.doClick();
				}
			}
		});
		txtUser.setBounds(310, 11, 110, 20);
		txtUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtUser.setDocument(new JTextFieldLimit(10));
		
		txtUser.setColumns(10);
		JTextFieldRegularPopupMenu.addTo(txtUser);
		txtUser.setColumns(10);
		txtUser.setOpaque(false);
		txtUser.setBorder(new EmptyBorder(0, 0, 0, 0));

		panel.add(txtUser);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(309, 31, 110, 7);
		panel.add(separator);
		
		txtpwd = new JPasswordField();
		txtpwd.setHorizontalAlignment(SwingConstants.LEFT);
		txtpwd.setEchoChar('*');
		txtpwd.setBounds(310, 37, 110, 20);
		txtpwd.setOpaque(false);
		txtpwd.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtpwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{	
					btngiris.doClick();
				}
			}
		});

		panel.add(txtpwd);
		txtpwd.setFont(new Font("Tahoma", Font.BOLD, 12));
		JTextFieldRegularPopupMenu.addTo(txtpwd);

		chckbxhatirla = new JCheckBox("Beni Hatirla");
		chckbxhatirla.setForeground(new Color(0, 0, 128));
		chckbxhatirla.setBounds(307, 60, 112, 23);
		panel.add(chckbxhatirla);

		JButton btncikis = new JButton("Cikis");
		btncikis.setBounds(190, 92, 110, 25);
		panel.add(btncikis);
		btncikis.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/exit.png")));
		btncikis.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (contentPane.getCursor() == Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) ) return ;
				System.exit(0);
			}
		});
		btncikis.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btncdizin = new JButton("Calisma Dizini");
		btncdizin.setBounds(190, 122, 110, 25);
		panel.add(btncdizin);
		btncdizin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (contentPane.getCursor() == Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) ) return ;
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

		btngiris = new JButton("Giris");
		btngiris.setBounds(310, 92, 110, 25);
		panel.add(btngiris);
		btngiris.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/add-user-16.png")));
		btngiris.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (contentPane.getCursor() == Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) ) return ;
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
						{
							GLOBAL.setting_yaz("BENI_HATIRLA", "");	
						}
						btndevam.requestFocusInWindow();
						contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
					else
					{
						contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						OBS_MAIN.mesaj_goster(7000,Notifications.Type.ERROR,   "Kullanici veya Sifresi Yanlis......!");
						txtUser.requestFocus();
					}
				} catch (Exception ex) {
					OBS_MAIN.mesaj_goster(7000,Notifications.Type.ERROR,  ex.getMessage());
				}
			}
		});
		btngiris.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btngiris.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorRemoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorMoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorAdded(AncestorEvent pEvent) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						btngiris.requestFocusInWindow();
					}
				});
			}
		});

		btndevam = new JButton("Devam");
		btndevam.setBounds(310, 122, 110, 25);
		panel.add(btndevam);
		btndevam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contentPane.getCursor() == Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) ) return ;
				
				Runnable runner = new Runnable()
				{ public void run() {
					contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					try
					{
						calisma_dizini_oku();
						int say  = 9 ;
						Login_Progres_Bar_Temizle();
						progressBar.setMaximum(say);
						progressBar.setStringPainted(true);
						Lgn_Progres_Bar(say, 1);
						lblModul.setText("Cari Baglanti");
						CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
						c_Access.baglan();
						Lgn_Progres_Bar(say, 2);
						lblModul.setText("Kur Baglanti");
						KUR_ACCESS  k_Access = new KUR_ACCESS(OBS_SIS_2025_ANA_CLASS._IKur, OBS_SIS_2025_ANA_CLASS._IKur_Loger);
						k_Access.baglan();
						Lgn_Progres_Bar(say, 3);
						lblModul.setText("Sms Baglanti");
						SMS_ACCESS  sms_Access = new SMS_ACCESS(OBS_SIS_2025_ANA_CLASS._ISms, OBS_SIS_2025_ANA_CLASS._ISms_Loger);
						sms_Access.baglan();
						Lgn_Progres_Bar(say, 4);
						lblModul.setText("Adres Baglanti");
						ADRES_ACCESS  a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres, OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
						a_Access.baglan();
						Lgn_Progres_Bar(say, 5);
						lblModul.setText("Stok Baglanti");
						STOK_ACCESS  s_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok,OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
						s_Access.baglan();
						Lgn_Progres_Bar(say, 6);
						lblModul.setText("Kambiyo Baglanti");
						KAMBIYO_ACCESS  ka_Access = new KAMBIYO_ACCESS(OBS_SIS_2025_ANA_CLASS._IKambiyo, OBS_SIS_2025_ANA_CLASS._IKambiyo_Loger);
						ka_Access.baglan();
						Lgn_Progres_Bar(say, 7);
						lblModul.setText("Gunluk Baglanti");
						GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(OBS_SIS_2025_ANA_CLASS._IGunluk, OBS_SIS_2025_ANA_CLASS._IGunluk_Loger);
						g_Access.baglan();
						Lgn_Progres_Bar(say, 8);
						lblModul.setText("Kereste Baglanti");
						KERESTE_ACCESS  ker_Access = new KERESTE_ACCESS(OBS_SIS_2025_ANA_CLASS._IKereste, OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
						ker_Access.baglan();
						Lgn_Progres_Bar(say, 9);

						OBS_MAIN obmain = new OBS_MAIN();
						Login_Progres_Bar_Temizle();
						progressBar.setMaximum(say);
						progressBar.setStringPainted(true);
						Lgn_Progres_Bar(say, 1);
						lblModul.setText("Cari Bilgi Okuma");
						cari_kont();
						Lgn_Progres_Bar(say, 2);
						lblModul.setText("Kur Bilgi Okuma");
						kur_kont();
						Lgn_Progres_Bar(say, 3);
						lblModul.setText("Sms Bilgi Okuma");
						sms_kont();
						Lgn_Progres_Bar(say, 4);
						lblModul.setText("Adres Bilgi Okuma");
						adr_kont(); 
						Lgn_Progres_Bar(say, 5);
						lblModul.setText("Stok Bilgi Okuma");
						fat_kont();
						Lgn_Progres_Bar(say, 6);
						lblModul.setText("Kambiyo Bilgi Okuma");
						kam_kont();
						Lgn_Progres_Bar(say, 7);
						lblModul.setText("Gunluk Bilgi Okuma");
						gun_kont();
						Lgn_Progres_Bar(say, 8);
						lblModul.setText("Kereste Bilgi Okuma");
						ker_kont();
						Lgn_Progres_Bar(say, 9);
						lblModul.setText("");
						Login_Progres_Bar_Temizle();

						//LOGLAMA DOSYALARI KONTROL*************************************************************************
						//CARI LOG KONTROL
						String dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.cariLogDizin.mODUL) ;
						if (! glb.dos_kontrol(dsy))
						{   
							GLOBAL.create_table_log(dsy,oac._ICar.cari_firma_adi(),BAGLAN_LOG.cariLogDizin);
						}
						//KUR LOG KONTROL
						dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.kurLogDizin.mODUL) ;
						if (! glb.dos_kontrol(dsy))
						{   
							GLOBAL.create_table_log(dsy,"",BAGLAN_LOG.kurLogDizin);
						}
						//SMS LOG KONTROL
						dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.smsLogDizin.mODUL) ;
						if (! glb.dos_kontrol(dsy))
						{   
							GLOBAL.create_table_log(dsy,"",BAGLAN_LOG.smsLogDizin);
						}
						//ADRES LOG KONTROL
						dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.adrLogDizin.mODUL) ;
						if (! glb.dos_kontrol(dsy))
						{   
							GLOBAL.create_table_log(dsy,oac._IAdres.adr_firma_adi(),BAGLAN_LOG.adrLogDizin);
						}
						//STOK LOG KONTROL
						dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.fatLogDizin.mODUL) ;
						if (! glb.dos_kontrol(dsy))
						{   
							GLOBAL.create_table_log(dsy,oac._IStok.fat_firma_adi(),BAGLAN_LOG.fatLogDizin);
						}
						//KAMBIYO LOG KONTROL
						dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.kamLogDizin.mODUL) ;
						if (! glb.dos_kontrol(dsy))
						{   
							GLOBAL.create_table_log(dsy,oac._IKambiyo.kam_firma_adi(),BAGLAN_LOG.kamLogDizin);
						}
						//GUNLUK LOG KONTROL
						dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.gunLogDizin.mODUL) ;
						if (! glb.dos_kontrol(dsy))
						{   
							GLOBAL.create_table_log(dsy,oac._IGunluk.gun_firma_adi(),BAGLAN_LOG.gunLogDizin);
						}
						//KERESTE LOG KONTROL
						dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.kerLogDizin.mODUL) ;
						if (! glb.dos_kontrol(dsy))
						{   
							GLOBAL.create_table_log(dsy,oac._IKereste.ker_firma_adi(),BAGLAN_LOG.kerLogDizin);
						}
						//KERESTE LOG KONTROL
						dsy =  GLOBAL.SURUCU +  GLOBAL.EKSTRE_DOSYA ;
						if (! glb.dos_kontrol(dsy))
						{   
							GLOBAL.ekstre_dosya_olustur();
						}
						dispose();
						obmain.setExtendedState(JFrame.MAXIMIZED_BOTH);
						obmain.setFont(new Font("Tahoma", Font.BOLD, 11));
						obmain.lblUser.setText(GLOBAL.KULL_ADI);
						obmain.setVisible(true);
					}
					catch (Exception ex)
					{
						contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
					}
				}
				};
				t = new Thread(runner, "OBS Login");
				t.start();
			}
		});
		btndevam.setVisible(false);
		btndevam.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/icons8-jog-forward-16.png")));
		btndevam.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		lblModul = new JLabel();
		lblModul.setForeground(new Color(0, 139, 139));
		lblModul.setBounds(1, 136, 189, 16);
		panel.add(lblModul);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(310, 57, 110, 7);
		panel.add(separator_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Sifre Goster");
		btnNewButton.setIcon(new ImageIcon(LOGIN.class.getResource("/ICONLAR/show-pwd-16.png")));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				txtpwd .setEchoChar((char) 0);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				txtpwd .setEchoChar('*');
			}
		});
		btnNewButton.setBounds(438, 34, 23, 23);
		panel.add(btnNewButton);
		
		
	
		progressBar = new JProgressBar();
		splitPane.setRightComponent(progressBar);
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
				deger = GLOBAL.setting_oku("PRG_GRID_RENK").toString();
				String[] parts;
				parts = deger.split(",");
				OBS_SIS_2025_ANA_CLASS.gridcolor =  new Color( Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				deger = GLOBAL.setting_oku("PRG_GRID_BACK_RENK").toString();
				parts = deger.split(",");
				OBS_SIS_2025_ANA_CLASS.satBackColor =  new Color( Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				deger = GLOBAL.setting_oku("PRG_GRID_FORE_RENK").toString();
				parts = deger.split(",");
				OBS_SIS_2025_ANA_CLASS.satForeColor =  new Color( Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				txtpwd.setText( ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes));
			} catch (Exception e1) 
			{
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, e1.getMessage());
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
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  "Calisilan Cari -" + BAGLAN.cariDizin.kOD + "- Nolu Dosya Bulunamadi.....");
				qwe = BAGLAN.cariDizin.yER.equals("S") ?  BAGLAN.cariDizin.sERVER : "Lokal" ;
				OBS_MAIN.tabbedPane.setEnabledAt(0,false);
			}
			else 
			{ 
				BAGLAN.cariDizin.fIRMA_ADI =  oac._ICar.cari_firma_adi() ;
				qwe = BAGLAN.cariDizin.yER.equals("S") ?  BAGLAN.cariDizin.sERVER : "Lokal" ;
				OBS_MAIN.lblCariBilgi.setText (BAGLAN.cariDizin.kOD + "  /  " + BAGLAN.cariDizin.fIRMA_ADI + "  /  " + qwe  + " / "+ BAGLAN.cariDizin.hAN_SQL );
				Dimension size = OBS_MAIN.lblCariBilgi.getPreferredSize();
				OBS_MAIN.lblCariBilgi.setBounds(10, 55, size.width +10, 14);
				OBS_MAIN.lblCariBilgi.setForeground(new Color(0, 0, 128));
				OBS_MAIN.lblCariBilgi.setFont(new Font("Tahoma", Font.BOLD, 11));
				OBS_MAIN.tabbedPane.setEnabledAt(0, true);
			}
		}
		else
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Cari Baglanti kurulamadi.....");
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
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Calisilan Kur -" + BAGLAN.kurDizin.kOD + "- Nolu Dosya Bulunamadi.....");
				OBS_MAIN.tabbedPane.setEnabledAt(1, false);
			}
			else
			{
				qwe =   BAGLAN.kurDizin.yER.equals("S") ?  BAGLAN.kurDizin.sERVER : "Lokal";
				OBS_MAIN.lblKurBilgi.setText (BAGLAN.kurDizin.kOD + "/ "  + qwe + " / "+ BAGLAN.kurDizin.hAN_SQL );
				Dimension size = OBS_MAIN.lblKurBilgi.getPreferredSize();
				OBS_MAIN.lblKurBilgi.setBounds(10, 55, size.width +10, 14);
				OBS_MAIN.lblKurBilgi.setForeground(new Color(0, 0, 128));
				OBS_MAIN.lblKurBilgi.setFont(new Font("Tahoma", Font.BOLD, 11));
				OBS_MAIN.tabbedPane.setEnabledAt(1, true);
			}
		}
		else
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Kur Baglanti kurulamadi.....");
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
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Calisilan Sms -" + BAGLAN.smsDizin.kOD + "- Nolu Dosya Bulunamadi.....");
				OBS_MAIN.tabbedPane.setEnabledAt(6, false);
			}
			else
			{
				qwe = BAGLAN.smsDizin.yER.equals("S") ? BAGLAN.smsDizin.sERVER : "Lokal" ;
				OBS_MAIN.lblSmsBilgi.setText (BAGLAN.smsDizin.kOD + "/ "  + qwe + " / "+ BAGLAN.smsDizin.hAN_SQL );
				Dimension size = OBS_MAIN.lblSmsBilgi.getPreferredSize();
				OBS_MAIN.lblSmsBilgi.setBounds(10, 55, size.width +10, 14);
				OBS_MAIN.lblSmsBilgi.setForeground(new Color(0, 0, 128));
				OBS_MAIN.lblSmsBilgi.setFont(new Font("Tahoma", Font.BOLD, 11));
				OBS_MAIN.tabbedPane.setEnabledAt(6, true);
			}
		}
		else
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Sms Baglanti kurulamadi.....");
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
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Calisilan Adres -" + BAGLAN.adrDizin.kOD + "- Nolu Dosya Bulunamadi.....");
				OBS_MAIN.tabbedPane.setEnabledAt(2, false);
			}
			else
			{
				BAGLAN.adrDizin.fIRMA_ADI =    oac._IAdres.adr_firma_adi() ;
				qwe = BAGLAN.adrDizin.yER.equals("S") ? BAGLAN.adrDizin.sERVER : "Lokal" ;
				OBS_MAIN.lblAdresBilgi.setText  (BAGLAN.adrDizin.kOD + "/ " + BAGLAN.adrDizin.fIRMA_ADI + "/ " + qwe + " / "+ BAGLAN.adrDizin.hAN_SQL );
				Dimension size = OBS_MAIN.lblAdresBilgi.getPreferredSize();
				OBS_MAIN.lblAdresBilgi.setBounds(10, 55, size.width +10, 14);
				OBS_MAIN.lblAdresBilgi.setForeground(new Color(0, 0, 128));
				OBS_MAIN.lblAdresBilgi.setFont(new Font("Tahoma", Font.BOLD, 11));
				OBS_MAIN.tabbedPane.setEnabledAt(2, true);
			}
		}
		else
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  "Adres Baglanti kurulamadi.....");
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
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Calisilan Stok -" + BAGLAN.fatDizin.kOD + "- Nolu Dosya Bulunamadi.....");
				OBS_MAIN.tabbedPane.setEnabledAt(3, false);
			}
			else
			{
				BAGLAN.fatDizin.fIRMA_ADI = oac._IStok.fat_firma_adi() ;
				qwe = BAGLAN.fatDizin.yER.equals("S") ? BAGLAN.fatDizin.sERVER : "Lokal" ;
				OBS_MAIN.lblStokBilgi.setText  ( BAGLAN.fatDizin.kOD + "/ " + BAGLAN.fatDizin.fIRMA_ADI + "/ " + qwe + " / "+ BAGLAN.fatDizin.hAN_SQL );
				Dimension size = OBS_MAIN.lblStokBilgi.getPreferredSize();
				OBS_MAIN.lblStokBilgi.setBounds(10, 55, size.width +10, 14);
				OBS_MAIN.lblStokBilgi.setForeground(new Color(0, 0, 128));
				OBS_MAIN.lblStokBilgi.setFont(new Font("Tahoma", Font.BOLD, 11));
				OBS_MAIN.tabbedPane.setEnabledAt(3, true);
			}
		}
		else
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  "Stok Baglanti kurulamadi.....");
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
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  "Calisilan Kambiyo -" +BAGLAN.kamDizin.kOD + "- Nolu Dosya Bulunamadi.....");
				OBS_MAIN.tabbedPane.setEnabledAt(4, false);
			}
			else
			{
				BAGLAN.kamDizin.fIRMA_ADI =  oac._IKambiyo.kam_firma_adi() ;
				qwe =  BAGLAN.kamDizin.yER.equals("S") ? BAGLAN.kamDizin.sERVER : "Lokal" ;
				OBS_MAIN.lblKambiyoBilgi.setText(BAGLAN.kamDizin.kOD + "/ " + BAGLAN.kamDizin.fIRMA_ADI + "/ " + qwe + " / "+ BAGLAN.kamDizin.hAN_SQL );
				Dimension size = OBS_MAIN.lblKambiyoBilgi.getPreferredSize();
				OBS_MAIN.lblKambiyoBilgi.setBounds(10, 55, size.width +10, 14);
				OBS_MAIN.lblKambiyoBilgi.setForeground(new Color(0, 0, 128));
				OBS_MAIN.lblKambiyoBilgi.setFont(new Font("Tahoma", Font.BOLD, 11));
				OBS_MAIN.tabbedPane.setEnabledAt(4, true);
			}
		}
		else
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,   "Kambiyo Baglanti kurulamadi.....");
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
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,   "Calisilan Gunluk -" + BAGLAN.gunDizin.kOD + "- Nolu Dosya Bulunamadi.....");
				OBS_MAIN.tabbedPane.setEnabledAt(5, false);
			}
			else
			{
				BAGLAN.gunDizin.fIRMA_ADI = oac._IGunluk.gun_firma_adi() ;
				qwe = BAGLAN.gunDizin.yER.equals("S") ? BAGLAN.gunDizin.sERVER : "Lokal" ;
				OBS_MAIN.lblGunlukBilgi.setText(BAGLAN.gunDizin.kOD + "/ " + BAGLAN.gunDizin.fIRMA_ADI + "/ " + qwe + " / "+ BAGLAN.gunDizin.hAN_SQL );
				Dimension size = OBS_MAIN.lblGunlukBilgi.getPreferredSize();
				OBS_MAIN.lblGunlukBilgi.setBounds(10, 55, size.width +10, 14);
				OBS_MAIN.lblGunlukBilgi.setForeground(new Color(0, 0, 128));
				OBS_MAIN.lblGunlukBilgi.setFont(new Font("Tahoma", Font.BOLD, 11));
				OBS_MAIN.tabbedPane.setEnabledAt(5, true);
			}
		}
		else
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,    "Gunluk Baglanti kurulamadi.....");
			OBS_MAIN.tabbedPane.setEnabledAt(5, false);
		}
	}
	void ker_kont() throws ClassNotFoundException, SQLException
	{
		String qwe = "" ;
		if (OBS_SIS_2025_ANA_CLASS.KER_CONN == true)
		{
			if (KER_DOS_VAR == false)
			{
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,   "Calisilan Kereste -" + BAGLAN.kerDizin.kOD + "- Nolu Dosya Bulunamadi.....");
				OBS_MAIN.tabbedPane.setEnabledAt(7, false);
			}
			else
			{
				BAGLAN.kerDizin.fIRMA_ADI = oac._IKereste.ker_firma_adi() ;
				qwe = BAGLAN.kerDizin.yER.equals("S") ? BAGLAN.kerDizin.sERVER : "Lokal" ;
				OBS_MAIN.lblKeresteBilgi.setText(BAGLAN.kerDizin.kOD + "/ " + BAGLAN.kerDizin.fIRMA_ADI + "/ " + qwe + " / "+ BAGLAN.kerDizin.hAN_SQL );
				Dimension size = OBS_MAIN.lblKeresteBilgi.getPreferredSize();
				OBS_MAIN.lblKeresteBilgi.setBounds(10, 55, size.width +10, 14);
				OBS_MAIN.lblKeresteBilgi.setForeground(new Color(0, 0, 128));
				OBS_MAIN.lblKeresteBilgi.setFont(new Font("Tahoma", Font.BOLD, 11));
				//OBS_MAIN.tabbedPane.setEnabledAt(5, true);
				OBS_MAIN.tabbedPane.setEnabledAt(7, true);
			}
		}
		else
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  "Kereste Baglanti kurulamadi.....");
			OBS_MAIN.tabbedPane.setEnabledAt(7, false);
		}
	}
	void calisma_dizini_oku() 
	{
		try 
		{
			int say = 9;
			bAGLAN.cONNECT(txtUser.getText());
			// Cari
			cONN_AKTAR(BAGLAN.cariDizin.hAN_SQL);
			String hangi_sql =  BAGLAN.cariDizin.hAN_SQL;
			oac._ICariCon = oac._IConn ;
			oac._ICar = hangi_sql.equals("MS SQL") ? new  CARI_HESAP_MSSQL() : new  CARI_HESAP_MYSQL();
			//Kur
			cONN_AKTAR( BAGLAN.kurDizin.hAN_SQL );
			hangi_sql =  BAGLAN.kurDizin.hAN_SQL;
			oac._IKurCon = oac._IConn ;
			oac._IKur = hangi_sql.equals("MS SQL") ? new KUR_MSSQL() : new KUR_MYSQL();
			//SMS
			cONN_AKTAR(BAGLAN.smsDizin.hAN_SQL );
			hangi_sql =  BAGLAN.smsDizin.hAN_SQL;
			oac._ISmsCon = oac._IConn ;
			oac._ISms = hangi_sql.equals("MS SQL") ? new SMS_MSSQL() : new SMS_MYSQL();
			//ADRES
			cONN_AKTAR( BAGLAN.adrDizin.hAN_SQL );
			hangi_sql =  BAGLAN.adrDizin.hAN_SQL;
			oac._IAdresCon = oac._IConn ;
			oac._IAdres = hangi_sql.equals("MS SQL") ? new ADRES_MSSQL() : new ADRES_MYSQL();
			//FAT
			cONN_AKTAR(BAGLAN.fatDizin.hAN_SQL );
			hangi_sql =  BAGLAN.fatDizin.hAN_SQL;
			oac._IStokCon = oac._IConn ;
			oac._IStok = hangi_sql.equals("MS SQL") ? new STOK_MSSQL() : new STOK_MYSQL();
			//KAMB
			cONN_AKTAR(BAGLAN.kamDizin.hAN_SQL );
			hangi_sql =  BAGLAN.kamDizin.hAN_SQL;
			oac._IKambiyoCon = oac._IConn ;
			oac._IKambiyo = hangi_sql.equals("MS SQL") ? new KAMBIYO_MSSQL() : new KAMBIYO_MYSQL();
			//GUN
			cONN_AKTAR( BAGLAN.gunDizin.hAN_SQL );
			hangi_sql =  BAGLAN.gunDizin.hAN_SQL;
			oac._IGunlukCon = oac._IConn ;
			oac._IGunluk = hangi_sql.equals("MS SQL") ? new GUNLUK_MSSQL() : new GUNLUK_MYSQL();
			//KERESTE
			cONN_AKTAR( BAGLAN.kerDizin.hAN_SQL );
			hangi_sql =  BAGLAN.kerDizin.hAN_SQL;
			oac._IKeresteCon = oac._IConn ;
			oac._IKereste = hangi_sql.equals("MS SQL") ? new KERESTE_MSSQL() : new KERESTE_MYSQL();

			bAGLAN_LOG.cONNECT(); // LOG Bilgileri AKTAR
			oac.uSER_ISL.mail_bak(); // MAIL AYARLARI OKUMA
			LOG_MAIL_OKU.mail_oku(); // LOGLAMA MAILI OKUMA
			progressBar.setMaximum(9);
			progressBar.setStringPainted(true);

			Lgn_Progres_Bar(say, 1);
			lblModul.setText("Cari Hesap Calisma Dizini");
			cari_calisma_dizini_oku();
			Lgn_Progres_Bar(say, 2);
			lblModul.setText("Kur Calisma Dizini");
			kur_calisma_dizini_oku();
			Lgn_Progres_Bar(say, 3);
			lblModul.setText("Sms Calisma Dizini");
			sms_calisma_dizini_oku();
			Lgn_Progres_Bar(say, 4);
			lblModul.setText("Adres Calisma Dizini");
			adr_calisma_dizini_oku();
			Lgn_Progres_Bar(say, 5);
			lblModul.setText("Stok Calisma Dizini");
			fat_calisma_dizini_oku();
			Lgn_Progres_Bar(say, 6);
			lblModul.setText("Kambiyo Calisma Dizini");
			kam_calisma_dizini_oku();
			Lgn_Progres_Bar(say, 7);
			lblModul.setText("Gunluk Calisma Dizini");
			gun_calisma_dizini_oku();
			Lgn_Progres_Bar(say, 8);
			lblModul.setText("Kereste Calisma Dizini");
			ker_calisma_dizini_oku();
			Lgn_Progres_Bar(say, 9);
			lblModul.setText("");
		} catch (Exception e) {
			OBS_MAIN.mesaj_goster(7000,Notifications.Type.ERROR, e.getMessage());
		}
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
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setIns(BAGLAN.cariDizin.iNSTANCE); 
		sBilgi.setKull(BAGLAN.cariDizin.kULLANICI) ;
		sBilgi.setSifre(BAGLAN.cariDizin.sIFRESI);
		sBilgi.setPort(BAGLAN.cariDizin.sERVER);
		sBilgi.setServer( BAGLAN.cariDizin.sERVER);
		sBilgi.setDb("OK_Car" + BAGLAN.cariDizin.kOD);
		if (BAGLAN.cariDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(sBilgi) == true)   
			{
				if (s_CONN.Dosya_kontrol_L( sBilgi) == false)
				{
					CAR_DOS_VAR = false;
				}
				else
				{
					lOGG_AKTAR("Cari Hesap",BAGLAN.cariDizin.hAN_SQL,BAGLAN.cariDizin.lOG,BAGLAN.cariDizin.lOGLAMA_YERI);
					CAR_DOS_VAR = true;
					OBS_SIS_2025_ANA_CLASS.CARI_CONN = true;
				}
			}
			else
			{
				OBS_SIS_2025_ANA_CLASS.CARI_CONN = false;
			}
		}
		else if (s_CONN.Server_kontrol_S(sBilgi) == true )
		{
			if (s_CONN.Dosya_kontrol_S(sBilgi) == false)
			{
				CAR_DOS_VAR = false;
			}
			else
			{
			lOGG_AKTAR("Cari Hesap",BAGLAN.cariDizin.hAN_SQL,BAGLAN.cariDizin.lOG,BAGLAN.cariDizin.lOGLAMA_YERI);
			CAR_DOS_VAR = true;
			OBS_SIS_2025_ANA_CLASS.CARI_CONN = true;
			}
		}
		else
		{
			OBS_SIS_2025_ANA_CLASS.CARI_CONN = false;
		}
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
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setIns(BAGLAN.kurDizin.iNSTANCE) ;
		sBilgi.setKull(BAGLAN.kurDizin.kULLANICI);
		sBilgi.setSifre(BAGLAN.kurDizin.sIFRESI);
		sBilgi.setPort(BAGLAN.kurDizin.sERVER);
		sBilgi.setServer(BAGLAN.kurDizin.sERVER);
		sBilgi.setDb("OK_Kur" + BAGLAN.kurDizin.kOD); 
		if (BAGLAN.kurDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(sBilgi) == true)   
			{
				if (s_CONN.Dosya_kontrol_L(sBilgi) == false)
				{
					KUR_DOS_VAR = false;
				}
				else
				{
					lOGG_AKTAR("Kur",BAGLAN.kurDizin.hAN_SQL,BAGLAN.kurDizin.lOG,BAGLAN.kurDizin.lOGLAMA_YERI);
					KUR_DOS_VAR = true;
					OBS_SIS_2025_ANA_CLASS.KUR_CONN = true;
				}
			}
			else
			{
				OBS_SIS_2025_ANA_CLASS.KUR_CONN = false;
			}
		}
		else if (s_CONN.Server_kontrol_S(sBilgi) == true )
		{
			if (s_CONN.Dosya_kontrol_S(sBilgi) == false)
			{
				KUR_DOS_VAR = false;
			}
			else
			{
				lOGG_AKTAR("Kur",BAGLAN.kurDizin.hAN_SQL,BAGLAN.kurDizin.lOG,BAGLAN.kurDizin.lOGLAMA_YERI);
				KUR_DOS_VAR = true;
				OBS_SIS_2025_ANA_CLASS.KUR_CONN = true;
			}
		}
		else
		{
			OBS_SIS_2025_ANA_CLASS.KUR_CONN = false;
		}
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
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setDb("OK_Sms" + BAGLAN.smsDizin.kOD); 
		sBilgi.setIns(BAGLAN.smsDizin.iNSTANCE );
		sBilgi.setKull(BAGLAN.smsDizin.kULLANICI);
		sBilgi.setSifre(BAGLAN.smsDizin.sIFRESI);
		sBilgi.setPort( BAGLAN.smsDizin.sERVER.toString());
		sBilgi.setServer( BAGLAN.smsDizin.sERVER);
		if (BAGLAN.smsDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(sBilgi) == true)   
			{
				if (s_CONN.Dosya_kontrol_L(sBilgi) == false)
				{
					SMS_DOS_VAR = false;
				}
				else
				{
					lOGG_AKTAR("Sms",BAGLAN.smsDizin.hAN_SQL,BAGLAN.smsDizin.lOG,BAGLAN.smsDizin.lOGLAMA_YERI);
					SMS_DOS_VAR = true;
					OBS_SIS_2025_ANA_CLASS.SMS_CONN = true;
				}
			}
			else
			{
				OBS_SIS_2025_ANA_CLASS.SMS_CONN = false;
			}
		}
		else if (s_CONN.Server_kontrol_S(sBilgi) == true )
		{
			if (s_CONN.Dosya_kontrol_S(sBilgi) == false)
			{
				SMS_DOS_VAR = false;
			}
			else
			{
				lOGG_AKTAR("Sms",BAGLAN.smsDizin.hAN_SQL,BAGLAN.smsDizin.lOG,BAGLAN.smsDizin.lOGLAMA_YERI);
				SMS_DOS_VAR = true;
				OBS_SIS_2025_ANA_CLASS.SMS_CONN = true;
			}
		}
		else
		{
			OBS_SIS_2025_ANA_CLASS.SMS_CONN = false;
		}
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
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setDb("OK_Adr" + BAGLAN.adrDizin.kOD);
		sBilgi.setIns(BAGLAN.adrDizin.iNSTANCE );
		sBilgi.setKull(BAGLAN.adrDizin.kULLANICI );
		sBilgi.setSifre(BAGLAN.adrDizin.sIFRESI);
		sBilgi.setPort( BAGLAN.adrDizin.sERVER);
		sBilgi.setServer( BAGLAN.adrDizin.sERVER);
		if (BAGLAN.adrDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(sBilgi) == true)   
			{
				if (s_CONN.Dosya_kontrol_L(sBilgi) == false)
				{
					ADR_DOS_VAR = false;
				}
				else
				{
					lOGG_AKTAR("Adres",BAGLAN.adrDizin.hAN_SQL,BAGLAN.adrDizin.lOG,BAGLAN.adrDizin.lOGLAMA_YERI);
					ADR_DOS_VAR = true;
					OBS_SIS_2025_ANA_CLASS.ADR_CONN = true;
				}
			}
			else
				OBS_SIS_2025_ANA_CLASS.ADR_CONN = false;
		}
		else if (s_CONN.Server_kontrol_S(sBilgi) == true )
		{
			if (s_CONN.Dosya_kontrol_S(sBilgi) == false)
			{
				ADR_DOS_VAR = false;
			}
			else
			{
			lOGG_AKTAR("Adres",BAGLAN.adrDizin.hAN_SQL,BAGLAN.adrDizin.lOG,BAGLAN.adrDizin.lOGLAMA_YERI);
			ADR_DOS_VAR = true;
			OBS_SIS_2025_ANA_CLASS.ADR_CONN = true;
			}
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
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setIns(BAGLAN.fatDizin.iNSTANCE );
		sBilgi.setKull(BAGLAN.fatDizin.kULLANICI );
		sBilgi.setSifre(BAGLAN.fatDizin.sIFRESI);
		sBilgi.setPort(BAGLAN.fatDizin.sERVER);
		sBilgi.setDb("OK_Fat" + BAGLAN.fatDizin.kOD );				
		sBilgi.setServer( BAGLAN.fatDizin.sERVER);
		if (BAGLAN.fatDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(sBilgi) == true)   
			{
				if (s_CONN.Dosya_kontrol_L(sBilgi) == false)
				{
					FAT_DOS_VAR = false;
				}
				else
				{
					lOGG_AKTAR("Fatura",BAGLAN.fatDizin.hAN_SQL,BAGLAN.fatDizin.lOG,BAGLAN.fatDizin.lOGLAMA_YERI);
					FAT_DOS_VAR = true;
					OBS_SIS_2025_ANA_CLASS.FAT_CONN = true;
				}
			}
			else
				OBS_SIS_2025_ANA_CLASS.FAT_CONN = false;
		}
		else if (s_CONN.Server_kontrol_S(sBilgi) == true )
		{
			if (s_CONN.Dosya_kontrol_S(sBilgi) == false)
			{
				FAT_DOS_VAR = false;
			}
			else
			{
			lOGG_AKTAR("Fatura",BAGLAN.fatDizin.hAN_SQL,BAGLAN.fatDizin.lOG,BAGLAN.fatDizin.lOGLAMA_YERI);
			FAT_DOS_VAR = true;
			OBS_SIS_2025_ANA_CLASS.FAT_CONN = true;
			}
		}
		else
		{
			OBS_SIS_2025_ANA_CLASS.FAT_CONN = false;
		}
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
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setIns(BAGLAN.kamDizin.iNSTANCE );
		sBilgi.setKull(BAGLAN.kamDizin.kULLANICI );
		sBilgi.setSifre( BAGLAN.kamDizin.sIFRESI);
		sBilgi.setPort( BAGLAN.kamDizin.sERVER);
		sBilgi.setDb("OK_Kam" + BAGLAN.kamDizin.kOD );				
		sBilgi.setServer(BAGLAN.kamDizin.sERVER);
		if (BAGLAN.kamDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(sBilgi) == true)   
			{
				if (s_CONN.Dosya_kontrol_L(sBilgi) == false)
				{
					KAM_DOS_VAR = false;
				}
				else
				{
					lOGG_AKTAR("Kambiyo",BAGLAN.kamDizin.hAN_SQL,BAGLAN.kamDizin.lOG,BAGLAN.kamDizin.lOGLAMA_YERI);
					KAM_DOS_VAR = true;
					OBS_SIS_2025_ANA_CLASS.KAM_CONN = true;
				}
			}
			else
			{
				OBS_SIS_2025_ANA_CLASS.KAM_CONN = false;
			}
		}
		else if (s_CONN.Server_kontrol_S(sBilgi) == true )
		{
			if (s_CONN.Dosya_kontrol_S(sBilgi) == false)
			{
				KAM_DOS_VAR = false;
			}
			else
			{
			lOGG_AKTAR("Kambiyo",BAGLAN.kamDizin.hAN_SQL,BAGLAN.kamDizin.lOG,BAGLAN.kamDizin.lOGLAMA_YERI);
			KAM_DOS_VAR = true;
			OBS_SIS_2025_ANA_CLASS.KAM_CONN = true;
			}
		}
		else
		{
			OBS_SIS_2025_ANA_CLASS.KAM_CONN = false;
		}
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
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setIns( BAGLAN.gunDizin.iNSTANCE );
		sBilgi.setKull( BAGLAN.gunDizin.kULLANICI );
		sBilgi.setSifre( BAGLAN.gunDizin.sIFRESI);
		sBilgi.setPort(BAGLAN.gunDizin.sERVER);
		sBilgi.setDb( "OK_Gun" + BAGLAN.gunDizin.kOD);				
		sBilgi.setServer( BAGLAN.gunDizin.sERVER);
		if (BAGLAN.gunDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(sBilgi) == true)   
			{
				if (s_CONN.Dosya_kontrol_L(sBilgi) == false)
				{
					GUN_DOS_VAR = false;
				}
				else
				{
					lOGG_AKTAR("Gunluk",BAGLAN.gunDizin.hAN_SQL,BAGLAN.gunDizin.lOG,BAGLAN.gunDizin.lOGLAMA_YERI);
					GUN_DOS_VAR = true;
					OBS_SIS_2025_ANA_CLASS.GUN_CONN = true;
				}
			}
			else
			{
				OBS_SIS_2025_ANA_CLASS.GUN_CONN = false;
			}
		}
		else if (s_CONN.Server_kontrol_S(sBilgi) == true )
		{
			if (s_CONN.Dosya_kontrol_S(sBilgi) == false)
				GUN_DOS_VAR = false;
			else
			{
			lOGG_AKTAR("Gunluk",BAGLAN.gunDizin.hAN_SQL,BAGLAN.gunDizin.lOG,BAGLAN.gunDizin.lOGLAMA_YERI);
			GUN_DOS_VAR = true;
			OBS_SIS_2025_ANA_CLASS.GUN_CONN = true;
			}
		}
		else
		{
			OBS_SIS_2025_ANA_CLASS.GUN_CONN = false;
		}
	}
	void ker_calisma_dizini_oku() throws ClassNotFoundException, SQLException
	{
		CONNECT s_CONN = new CONNECT(oac._IKeresteCon);
		if (BAGLAN.kerDizin.yER.equals(""))
		{
			OBS_SIS_2025_ANA_CLASS.KER_CONN = false;
			KER_DOS_VAR = false;
			return;
		}
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setIns( BAGLAN.kerDizin.iNSTANCE );
		sBilgi.setKull( BAGLAN.kerDizin.kULLANICI );
		sBilgi.setSifre( BAGLAN.kerDizin.sIFRESI);
		sBilgi.setPort(BAGLAN.kerDizin.sERVER);
		sBilgi.setDb( "OK_Ker" + BAGLAN.kerDizin.kOD);				
		sBilgi.setServer( BAGLAN.kerDizin.sERVER);
		if (BAGLAN.kerDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(sBilgi) == true)   
			{
				if (s_CONN.Dosya_kontrol_L(sBilgi) == false)
				{
					KER_DOS_VAR = false;
				}
				else
				{
					lOGG_AKTAR("Kereste",BAGLAN.kerDizin.hAN_SQL,BAGLAN.kerDizin.lOG,BAGLAN.kerDizin.lOGLAMA_YERI);
					KER_DOS_VAR = true;
					OBS_SIS_2025_ANA_CLASS.KER_CONN = true;
				}
			}
			else
			{
				OBS_SIS_2025_ANA_CLASS.KER_CONN = false;
			}
		}
		else if (s_CONN.Server_kontrol_S(sBilgi) == true )
		{
			if (s_CONN.Dosya_kontrol_S(sBilgi) == false)
			{
				KER_DOS_VAR = false;
			}
			else
			{
			lOGG_AKTAR("Kereste",BAGLAN.kerDizin.hAN_SQL,BAGLAN.kerDizin.lOG,BAGLAN.kerDizin.lOGLAMA_YERI);
			KER_DOS_VAR = true;
			OBS_SIS_2025_ANA_CLASS.KER_CONN = true;
			}
		}
		else
		{
			OBS_SIS_2025_ANA_CLASS.KER_CONN = false;
		}
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
			//
			String serverAddress = "78.189.76.247";
			String userId ="hamitadmin";
			String password ="SDFks9hfji3#DEd";
			
			//***********************************************************************************
//			File fILEFTP = new File( GLOBAL.SURUCU + "/OBS_SISTEM_FTP.txt"); 
//			fileReader =  new FileReader(fILEFTP); 
//			bufferedReader = new BufferedReader(fileReader);
//			counter = 0; 
//			while((line = bufferedReader.readLine()) != null) 
//			{ 
//				counter++;
//				if(counter == 1) 
//				{ 
//					serverAddress = line.toString(); 
//				} 
//				else if(counter == 2) 
//				{
//					userId = line.toString(); 
//				} 
//				else if(counter == 3) 
//				{ 
//					password =  line.toString(); 
//				}
//			}
//			bufferedReader.close();
			//*************************************************************************************
			FTPClient ftp = new FTPClient();
			ftp.connect(serverAddress);
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
			File downloadFile1 = new File( GLOBAL.SURUCU + "/OBS_VERSIONS.txt");
			OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
			boolean success = ftp.retrieveFile(remoteFile1, outputStream1);
			outputStream1.close();
			if (success == false )
				{
				File f= new File(GLOBAL.SURUCU + "/OBS_VERSIONS.txt");           //file to be delete  
				success = f.delete();  
				return ;
				}
			//************************************
			fileName = "" ;
			fileName = GLOBAL.SURUCU + "/OBS_VERSIONS.txt";
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
				File f= new File(GLOBAL.SURUCU +"/OBS_VERSIONS.txt");           //file to be delete  
				success = f.delete();  
			}
			else
			{
				File f= new File(GLOBAL.SURUCU + "OBS_VERSIONS.txt");           //file to be delete  
				f.delete();  
				String html = "<html><body >"
						+ "Yeni Versiyon Mevcut"
						+ "<br><br> "
						+ "Mevcut Version = " + eskiver + "      "
						+ "<br><br> "
						+ "Yeni Version = " + yeniver + "" ;
				OBS_MAIN.mesaj_goster(15000,Notifications.Type.INFO,  String.format(html));
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(15000,Notifications.Type.INFO,  ex.getMessage());
		}
	}
	private void cONN_AKTAR( String dIZIN )
	{
		oac._IConn = dIZIN.equals("MS SQL") ? new OBS_ORTAK_MSSQL() : new OBS_ORTAK_MYSQL();
	}
	private void lOGG_AKTAR(String mODUL, String hangiSQL , Boolean log , String hANGI_LOG) throws ClassNotFoundException, SQLException
	{
		String[] token = hANGI_LOG.split(",");
		vt = (token[0].equals("true") ? true:false);
		ds = (token[1].equals("true") ? true:false);
		tx = (token[2].equals("true") ? true:false);
		em = (token[3].equals("true") ? true:false);
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
		switch(mODUL) {
		case "Cari Hesap":
			oac._ICari_Loger = ilogg;
			break;
		case "Kur":
			oac._IKur_Loger = ilogg;
			break;
		case "Adres":
			oac._IAdres_Loger = ilogg;
			break;
		case "Fatura":
			oac._IFatura_Loger = ilogg;
			break;
		case "Sms":
			oac._ISms_Loger = ilogg;
			break;
		case "Gunluk":
			oac._IGunluk_Loger = ilogg;
			break;
		case "Kambiyo":
			oac._IKambiyo_Loger = ilogg;
			break;
		case "Kereste":
			oac._IKereste_Loger = ilogg;
			break;
		}
	}
}
// String username = System.getProperty("user.name");
// System.out.println("username = " + username);
// System.setProperty("password", "myPassword");
// System.out.println(System.getProperty("password"));




