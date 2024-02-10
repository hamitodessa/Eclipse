package obs.backup.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.Position;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.io.CopyStreamAdapter;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGrayIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHighContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonocaiIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDeepOceanIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialPalenightIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import LOGER_KAYIT.TXT_LOG;
import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.CheckListItem;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldRegularPopupMenu;
import OBS_C_2025.MaterialTabbed;
import javazoom.jl.player.Player;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.gorev.gOREV_TAKIP;
import obs.backup.other.Ayarlar;
import obs.backup.other.Bilgilendirme;
import obs.backup.other.DownloadFile;
import obs.backup.other.EmirAnaGiris;
import obs.backup.other.EmirKopyala;
import obs.backup.other.Hakkinda;
import obs.backup.other.KayitliEmirler;
import obs.backup.other.LoglamaRapor;
import obs.backup.other.RestoreDatabases;
import obs.backup.other.ServerBilgileri;
import obs.backup.other.SifreGiris;
import obs.backup.other.SifreYenile;
import obs.backup.other.SunucuAyarlari;
import obs.backup.other.UploadPanel;
import obs.backup.other.YedeklemeAraligi;
import raven.toast.Notifications;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JSeparator;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import OBS_C_2025.SIFRE_DONDUR;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.Title_Bar;
import OBS_C_2025.bilgilendirme_bilgiler;
import OBS_C_2025.db_List;
import OBS_C_2025.emir_bilgiler;
import OBS_C_2025.ftp_bilgiler;
import OBS_C_2025.remote_filelist;
import OBS_C_2025.sayiyiYaziyaCevir;
import OBS_C_2025.server_bilgiler;
import OBS_C_2025.FORMATLAMA;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

@SuppressWarnings({ "static-access" ,"resource","unused","deprecation"})
public class OBS_BACKUP extends JFrame {
	private static final long serialVersionUID = 1L;
	GLOBAL glb = new GLOBAL();
	static BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	public static List<String> gorevLER = new ArrayList<String>();

	Timer timerr;

	public JPanel contentPane;
	public static JPanel container ;
	public static JToolBar toolBar;
	public static JTabbedPane tabbedPane ;
	public static MaterialTabbed tabbedPane_1;
	int x ,y ;
	public static YedeklemeAraligi yedekaraligiPanel;
	public static SunucuAyarlari sunucuayarPanel;
	public static Bilgilendirme bilgilendirmePanel;
	public static ServerBilgileri serverBilgileriPanel;
	public static EmirKopyala emirKopyalaPanel ;
	public static EmirAnaGiris emirAnaGirisPanel;

	public static LoglamaRapor loglamaPanel;
	public static KayitliEmirler kayitliEmirlerPanelEmirler;
	public static UploadPanel uplpnl ;
	public static SifreGiris sifreGirisPanel;
	public static SifreYenile sifreYenilePanel;
	public static DownloadFile downloadFilePanel;
	public static RestoreDatabases restoreDatabasesPanel;
	public static Hakkinda hakkindaPanel;
	public static Ayarlar ayarlarPanel;

	private ScrollPaneWin11 scrollPane;
	public static JButton btnYeni_Gorev;
	public static JButton btnLoglama;
	public static JButton btnKayitliEmirler;
	public static JButton btnGorevler;
	public static JButton btnHepsiYukari;
	public static JButton btnHepsiAsagi;
	public static JButton btnYeniSifre;
	public static JButton btnUploadAll ;
	public static JButton btnStartAll ;
	public static JButton btnStopAll ;
	public static JButton btnFileIndir;
	public static JButton btnRestore;
	
	public static JButton btnSifreEkrani;
	public static JButton btnHepsiAktiv;
	public static JButton btnHepsiPasiv;
	public static JButton btnHakkinda;
	public static JButton btnAyarlar;
	public static JButton btnKapat;
	
	public static JLabel lblemirSAYI;
	public static JLabel lblEmir ;
	private static JLabel pidlb ;

	public static JButton btntry;
	public static JButton btnfont_tema;
	static JButton btnBuyult;
	static JButton btnMinimize;
	
	static TrayIcon trayIcon = null ;
	
	public static String dILS = "" ;
	public static String zipSIFRE = "" ;
	public static String gelenISIM ="";
	public static boolean sifRELE = false;
	
	static Component horizontalGlue = null ;
	private static String diltemaString[] = new String[8];
	
	public static boolean backupTime = false;
	/**
	 * Hamit.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OBS_BACKUP frame = new OBS_BACKUP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public OBS_BACKUP() {
		setUndecorated(true);
		FlatLaf.registerCustomDefaultsSource("obs.backup.theme");
		try 
		{
			glb.backup_surucu_kontrol();
			pidlb = new JLabel("");
			pidKONTROL(); // PID yaz
			secondRUN(); // Baska Calisan Kopya varsa kendini kapa
			diltemaString = bckp.ayar_oku();
			dILS = diltemaString[0];
			tema(diltemaString[1]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX(); 
				y = e.getY(); 
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen(); 
				setLocation(xx-x,yy-y);
			}
		});
		setBounds(0, 0, 900, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(OBS_BACKUP.class.getResource("/obs/backup/icons/backup-100.png")));
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Title_Bar tBAR = new Title_Bar(null,true,false,"OBS BACKUP",0,0);
		ActionListener btnCLOSED = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	btnKapat.doClick();
		    }
		};
		tBAR.btnClose.addActionListener(btnCLOSED);
		ActionListener btnMINIMIZ = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	btntry.doClick();
		    }
		};
		tBAR.btnMin.addActionListener(btnMINIMIZ);
		tBAR.setBorder(new EmptyBorder(0, 0, 10, 00));
		contentPane.add(tBAR, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(45);
		splitPane.setDividerSize(0);
		if(diltemaString[7].equals("1"))
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);

		ScrollPaneWin11 jScrollPaneToolBar = new ScrollPaneWin11();
		splitPane.setLeftComponent(jScrollPaneToolBar);
		JPanel panel = new JPanel();
		jScrollPaneToolBar.setViewportView(panel);
		//***************
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		if(diltemaString[7].equals("0"))
			toolBar.setOrientation(SwingConstants.VERTICAL);
		panel.setLayout(new BorderLayout());
		panel.add(toolBar,BorderLayout.CENTER);
		//***************
		JLabel spr = new JLabel("   ");
		spr.setSize(new Dimension(20,20));
		
		toolBar.add(spr);
		btnGorevler = new JButton();
		btnGorevler.setToolTipText("Gorevler");
		btnGorevler.setEnabled(false);

		btnGorevler.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/emirler.png")));
		btnGorevler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				if (backupTime ) return;
				try 
				{
					gelenISIM = "" ;
					contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					emirleriSTOPYAP();
					emirYukle("EMIR_ISMI") ;
					contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(btnGorevler);

		btnYeni_Gorev = new JButton();
		btnYeni_Gorev.setToolTipText("Yeni Gorev");
		btnYeni_Gorev.setEnabled(false);
		btnYeni_Gorev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (backupTime ) return;
				gelenISIM = "" ;
				emirSayiCount();
				tabbedPane.setSelectedIndex(1);
				tabbedPane_1.setSelectedIndex(0);
				emirAnaGirisPanel.emirDOLDUR();
			}
		});
		btnYeni_Gorev.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/yeniemir.png")));
		toolBar.add(btnYeni_Gorev);

		btnLoglama = new JButton();
		btnLoglama.setToolTipText("Log Goruntule");
		btnLoglama.setEnabled(false);
		btnLoglama .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (backupTime ) return;
				tabbedPane.setSelectedIndex(2);
				try 
				{
					contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					loglamaPanel.doldur();
					contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLoglama.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/log.png")));
		toolBar.add(btnLoglama );

		btnKayitliEmirler = new JButton();
		btnKayitliEmirler.setEnabled(false);
		btnKayitliEmirler.setToolTipText("Kayitli Emirler");
		btnKayitliEmirler.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/jobs.png")));
		btnKayitliEmirler .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (backupTime ) return;
				emirSayiCount();
				tabbedPane.setSelectedIndex(3);
				try 
				{
					contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					kayitliEmirlerPanelEmirler.doldur();
					contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(btnKayitliEmirler );

		btnHepsiYukari = new JButton();
		btnHepsiYukari.setEnabled(false);
		btnHepsiYukari.setToolTipText("Gorev Paneli Yukari");
		btnHepsiYukari.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/up.png")));
		btnHepsiYukari .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				emirSayiCount();
				Component[] components = container.getComponents();
				for (Component component : components) 
				{
					if(component.getName() != null)
						component.setPreferredSize(new Dimension(00,70));
				}
				container.revalidate();
				tabbedPane.setSelectedIndex(0);
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		toolBar.add(btnHepsiYukari );

		btnHepsiAsagi = new JButton();
		btnHepsiAsagi.setEnabled(false);
		btnHepsiAsagi.setToolTipText("Gorev Paneli Asagi");
		btnHepsiAsagi.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/down.png")));
		btnHepsiAsagi .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				emirSayiCount();
				Component[] components = container.getComponents();
				for (Component component : components) {
					if(component.getName() != null)
						component.setPreferredSize(new Dimension(00,175));
				}
				container.revalidate();
				tabbedPane.setSelectedIndex(0);
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		toolBar.add(btnHepsiAsagi );

		btnStartAll= new JButton("");
		btnStartAll.setToolTipText("Emirleri Baslat");
		btnStartAll.setEnabled(false);
		btnStartAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (backupTime ) return;
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				emirSayiCount();
				Component[] components = container.getComponents();
				for (Component component : components) {
					if (component.getName()!= null)
					{
						JPanel qweJPanel = (JPanel) component ; 
						Component[] componentt = qweJPanel.getComponents();
						for (Component compo : componentt) {
							if(compo.getName() != null)
							{
								if(compo.getName().equals("btnStart"))
								{
									JButton strt = (JButton) compo;
									strt.doClick();
								}
							}
						}
					}
					component.revalidate();
				}
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnStartAll.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/start.png")));
		toolBar.add(btnStartAll);

		btnStopAll= new JButton("");
		btnStopAll.setToolTipText("Emirleri Durdur");
		btnStopAll.setEnabled(false);
		btnStopAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (backupTime ) return;
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				emirSayiCount();
				emirleriSTOPYAP();
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnStopAll.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/stop.png")));
		toolBar.add(btnStopAll);

		btnHepsiAktiv= new JButton("");
		btnHepsiAktiv.setToolTipText("Hepsini Aktivlestir");
		btnHepsiAktiv.setEnabled(false);
		btnHepsiAktiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					if (backupTime ) return;
					emirSayiCount();
					contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					Component[] components = container.getComponents();
					for (Component component : components) 
					{
						if (component.getName()!= null)
						{
							JPanel qweJPanel = (JPanel) component ; 
							Component[] componentt = qweJPanel.getComponents();
							for (Component compo : componentt) {
								if(compo.getName() != null)
								{
									if(compo.getName().equals("lblDurum"))
									{
										JLabel lblDURUM = (JLabel) compo;
										if(lblDURUM.getText().equals("Pasiv Durumda"))
										{
											bckp.durum_kayit_durum(component.getName().toString(), true,"Durum Aktivlestirildi...");
										}
									}
								}
							}
						}
						component.revalidate();
					}
					emirYukle("EMIR_ISMI");
					contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
					contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		btnHepsiAktiv.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/activ-24.png")));
		toolBar.add(btnHepsiAktiv);

		btnHepsiPasiv = new JButton("");
		btnHepsiPasiv.setToolTipText("Hepsini Pasivlestir");
		btnHepsiPasiv.setEnabled(false);
		btnHepsiPasiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					if (backupTime ) return;
					emirSayiCount();
					contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					Component[] components = container.getComponents();
					for (Component component : components) 
					{
						if (component.getName()!= null)
						{
							JPanel qweJPanel = (JPanel) component ; 
							Component[] componentt = qweJPanel.getComponents();
							for (Component compo : componentt) 
							{
								if(compo.getName() != null)
								{
									if(compo.getName().equals("lblDurum"))
									{
										JLabel lblDURUM = (JLabel) compo;
										if(! lblDURUM.getText().equals("Pasiv Durumda"))
										{
											bckp.durum_kayit_durum(component.getName().toString(), false,"Durum Pasivlestirildi...");
										}
									}
								}
							}
							for (Component compo : componentt) {
								if(compo.getName() != null)
								{
									if(compo.getName().equals("btnHepsiPasivden"))
									{
										JButton stp = (JButton) compo;
										stp.doClick();
									}
								}
							}
						}
						component.revalidate();
					}
					contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
					contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		btnHepsiPasiv.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/pasiv-24.png")));
		toolBar.add(btnHepsiPasiv);

		btnUploadAll= new JButton("");
		btnUploadAll.setEnabled(false);
		btnUploadAll.setToolTipText("Aktif Emirleri Yedekle");
		btnUploadAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (backupTime ) return;
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				emirSayiCount();
				Component[] components = container.getComponents();
				for (Component component : components) 
				{
					if (component.getName()!= null)
					{
						JPanel qweJPanel = (JPanel) component ; 
						Component[] componentt = qweJPanel.getComponents();
						for (Component compo : componentt) {
							if(compo.getName() != null)
							{
								if(compo.getName().equals("btnYedekle"))
								{
									JButton btnydkl = (JButton) compo;
									btnydkl.doClick();
								}
							}
						}
					}
					component.revalidate();
				}
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnUploadAll.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/upload.png")));
		toolBar.add(btnUploadAll);

		btnFileIndir= new JButton("");
		btnFileIndir.setEnabled(false);
		btnFileIndir.setToolTipText("FTP Dosya Indirme");
		btnFileIndir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (backupTime ) return;
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				emirSayiCount();
				tabbedPane.setSelectedIndex(6);
				try {
					GRID_TEMIZLE.grid_temizle(downloadFilePanel.tblFile);
					downloadFilePanel.comboBox.removeAllItems();
					downloadFilePanel.eismiDOLDUR();
				} catch (ClassNotFoundException | SQLException e1) {

					e1.printStackTrace();
				}
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnFileIndir.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/download-30.png")));
		toolBar.add(btnFileIndir);
		
		btnRestore = new JButton("");
		btnRestore.setEnabled(false);
		btnRestore.setToolTipText("Restore");
		btnRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (backupTime ) return;
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				tabbedPane.setSelectedIndex(9);
				restoreDatabasesPanel.doldur();
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnRestore.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/restore.png")));
		toolBar.add(btnRestore);


		btnYeniSifre = new JButton();
		btnYeniSifre.setEnabled(false);
		btnYeniSifre.setToolTipText("Sifre Yenile");
		btnYeniSifre.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/password.png")));
		btnYeniSifre .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (backupTime ) return;
				emirSayiCount();
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				sifreYenilePanel.txtsif.setText("");
				tabbedPane.setSelectedIndex(5);
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		toolBar.add(btnYeniSifre );
		if(diltemaString[7].equals("0"))
		{
			Component verticalGlue = Box.createVerticalGlue();
			toolBar.add(verticalGlue);
		}
		else {
			Component horizontalGlue = Box.createHorizontalGlue();
			toolBar.add(horizontalGlue);
		}
		btnSifreEkrani= new JButton("");
		btnSifreEkrani.setToolTipText("Sifre Ekrani");
		btnSifreEkrani.setVisible(false);
		btnSifreEkrani.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/protect-24.png")));
		btnSifreEkrani .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				emirSayiCount();
				btnSifreEkrani.setVisible(false);
				btnGorevler.setEnabled(false);
				btnYeni_Gorev.setEnabled(false);
				btnLoglama.setEnabled(false);
				btnKayitliEmirler.setEnabled(false);
				btnHepsiYukari.setEnabled(false);
				btnHepsiAsagi.setEnabled(false);
				btnYeniSifre.setEnabled(false);
				btnUploadAll.setEnabled(false);
				btnStartAll.setEnabled(false);
				btnStopAll.setEnabled(false);
				btnFileIndir.setEnabled(false);
				btnRestore.setEnabled(false);
				btnHepsiAktiv.setEnabled(false);
				btnHepsiPasiv.setEnabled(false);
				btnHakkinda.setEnabled(false);
				btnAyarlar.setEnabled(false);
				sifreGirisPanel.passwordField.setText("");
				tabbedPane.setSelectedIndex(4);
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		toolBar.add(btnSifreEkrani );
		//
		btnHakkinda= new JButton("");
		btnHakkinda.setToolTipText("Hakkinda");
		btnHakkinda.setEnabled(false);
		btnHakkinda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emirSayiCount();
				tabbedPane.setSelectedIndex(7);
			}
		});
		btnHakkinda.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/about-24.png")));
		toolBar.add(btnHakkinda);
		//
		//
		btnAyarlar= new JButton("");
		btnAyarlar.setToolTipText("Ayarlar");
		btnAyarlar.setEnabled(false);
		btnAyarlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (backupTime ) return;
				ayarlarPanel.comboBox_1.setSelectedItem(diltemaString[0]);
				ayarlarPanel.comboBox.setSelectedItem(diltemaString[1]);
				ayarlarPanel.chckbxPrgSifre.setSelected(Integer.valueOf(diltemaString[4]) == 0 ? false:true);
				ayarlarPanel.chckbxWinStart.setSelected(Integer.valueOf(diltemaString[5]) == 0 ? false:true);
				ayarlarPanel.chckbxVersion.setSelected(Integer.valueOf(diltemaString[6]) == 0 ? false:true);
				ayarlarPanel.chckbxMenu.setSelected(Integer.valueOf(diltemaString[7]) == 0 ? false:true);
				ayarlarPanel.passwordText.setVisible(ayarlarPanel.chckbxSifrele.isSelected());
				emirSayiCount();
				tabbedPane.setSelectedIndex(8);
			}
		});
		btnAyarlar.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/settings-24.png")));
		toolBar.add(btnAyarlar);
		
		if(diltemaString[7].equals("0"))
		{
			Component verticalGlue = Box.createVerticalGlue();
			toolBar.add(verticalGlue);
		}
		else {
			Component horizontalGlue = Box.createHorizontalGlue();
			toolBar.add(horizontalGlue);
		}
		JButton btnkapat= new JButton("");
		btnkapat.setToolTipText("Kapat");
		btnkapat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnkapat.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/exit.png")));
		toolBar.add(btnkapat);
		JLabel sprt = new JLabel("   ");
		sprt.setSize(new Dimension(20,20));
		toolBar.add(sprt );

	
		//*********************************************************************************
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		splitPane.setRightComponent(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Gorevler", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));

		uplpnl = new UploadPanel();
		uplpnl.setPreferredSize(new Dimension(0,00));
		panel_1.add(uplpnl, BorderLayout.SOUTH);

		scrollPane = new ScrollPaneWin11();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		//**************************************************************************************
		container = new JPanel(); 
		scrollPane.setViewportView(container);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Yeni Gorev", null, panel_3, null);
		
		panel_3.setLayout(new BorderLayout(0, 0));

		tabbedPane_1 = new MaterialTabbed();
		tabbedPane_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(tabbedPane_1.getSelectedIndex()==4)
				{
					if(OBS_BACKUP.emirAnaGirisPanel.lblNewLabel_6.getText().equals("Ms Sql"))
						OBS_BACKUP.serverBilgileriPanel.tabbedPane.setSelectedIndex(0);
					else if(OBS_BACKUP.emirAnaGirisPanel.lblNewLabel_6.getText().equals("My Sql"))
						OBS_BACKUP.serverBilgileriPanel.tabbedPane.setSelectedIndex(1);
				}
				else if(tabbedPane_1.getSelectedIndex() == 5)
				{
					OBS_BACKUP.emirKopyalaPanel.lblEmirIsmi.setText(OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText());
					OBS_BACKUP.emirKopyalaPanel.textField.requestFocus();
				}
			}
		});

		panel_3.add(tabbedPane_1, BorderLayout.CENTER);
		
		JPanel altPane = new JPanel();
		altPane.setPreferredSize(new Dimension(0,25));
		altPane.setLayout(null);
		contentPane.add(altPane, BorderLayout.SOUTH);

		lblEmir = new JLabel("");
		lblEmir.setBounds(40, 5, 75, 14);
		altPane.add(lblEmir);

		lblemirSAYI = new JLabel("0");
		lblemirSAYI.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblemirSAYI.setBounds(125, 5, 48, 14);
		altPane.add(lblemirSAYI);
		
		pidlb = new JLabel("");
		pidlb.setFont(new Font("Tahoma", Font.PLAIN, 11));
		pidlb.setHorizontalAlignment(SwingConstants.RIGHT);
		pidlb.setBounds(780, 5, 95, 14);
		altPane.add(pidlb);

		emirAnaGirisPanel = new EmirAnaGiris();
		tabbedPane_1.addTab("Emir", null,emirAnaGirisPanel, null);
		sunucuayarPanel = new SunucuAyarlari();
		tabbedPane_1.addTab("Surucu Ayarlari", null,sunucuayarPanel, null);
		bilgilendirmePanel = new Bilgilendirme();
		tabbedPane_1.addTab("Bilgilendirme", null, bilgilendirmePanel, null);
		yedekaraligiPanel = new YedeklemeAraligi();
		tabbedPane_1.addTab("Yedekleme Araligi", null, yedekaraligiPanel, null);
		serverBilgileriPanel = new ServerBilgileri();
		tabbedPane_1.addTab("Server Bilgileri", null, serverBilgileriPanel, null);
		emirKopyalaPanel = new EmirKopyala();
		tabbedPane_1.addTab("Emir Kopyala", null, emirKopyalaPanel, null);
		//***********************************************************************************
		loglamaPanel = new LoglamaRapor();
		tabbedPane.addTab("Loglama", null, loglamaPanel, null);
		kayitliEmirlerPanelEmirler = new KayitliEmirler();
		tabbedPane.addTab("Emirler", null, kayitliEmirlerPanelEmirler, null);
		sifreGirisPanel = new SifreGiris();
		tabbedPane.addTab("Sifre", null, sifreGirisPanel, null);
		sifreYenilePanel = new SifreYenile();
		tabbedPane.addTab("Sifre Yenile", null, sifreYenilePanel, null);
		downloadFilePanel = new DownloadFile();
		tabbedPane.addTab("Dosya Indir", null, downloadFilePanel, null);
		hakkindaPanel = new Hakkinda();
		tabbedPane.addTab("Hakkinda", null, hakkindaPanel, null);
		ayarlarPanel = new Ayarlar();
		tabbedPane.addTab("Ayarlar", null, ayarlarPanel, null);
		restoreDatabasesPanel = new RestoreDatabases();
		tabbedPane.addTab("Restore", null, restoreDatabasesPanel, null);
		//*************************************************************************************************************
		sifreDurumu();
		//*************************************************************************************************************
		final boolean showTabsHeader = false; tabbedPane.setUI(new javax.swing.plaf.metal.MetalTabbedPaneUI() {
			@Override protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) { if (showTabsHeader) {return
			super.calculateTabAreaHeight(tabPlacement, horizRunCount, maxTabHeight); }	else {return 0;} } protected void paintTabArea(Graphics g,int
			tabPlacement,int selectedIndex){} });
		for(int i = 0;i <= tabbedPane.getTabCount() - 1;i++)
		{
			tabbedPane.setEnabledAt(i, false);
		}
		
		btntry= new JButton("");
		btntry.setVisible(false);
		btntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				systemTRY();
			}
		});
		btnKapat = new JButton("");
		btnKapat.setVisible(false);
		btnKapat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emirleriSTOPYAP();
				if(timerr != null)
				{
					timerr.cancel();
		            timerr.purge();
		            timerr = null;
				}
				System.exit(0);
			}
		});
		
		btnBuyult = new JButton("");
		btnBuyult.setVisible(false);
		btnBuyult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 SystemTray tray = SystemTray.getSystemTray();
				 if (trayIcon != null)
					 tray.remove(trayIcon); 
				setVisible(true);
			}
		});
		btnMinimize = new JButton("");
		btnMinimize.setVisible(false);
		btnMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(Frame.ICONIFIED);
			}
		});
		
		btnfont_tema  = new JButton("");
		btnfont_tema.setVisible(false);
		btnfont_tema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FONT_TEMA frame = new FONT_TEMA();
				dispose();
			}
		});
		//******************************* VERSIYON KONTROL *************************************************************************
		if(Integer.valueOf(diltemaString[6]) == 1 )
			versiyon_oku();
		//***********************************BASLAMA********************************************************************************
		try 
		{
			bckp.log_kayit("System", new Date(),dilSecenek.dil(dILS,"Program Baslangici"));
			dil();
			emirYukle("EMIR_ISMI") ;
			jobTimerBasla();
			// SIFRE EKRANI KONTROL***************************************
			if(Integer.valueOf(diltemaString[4]) == 1 )
				tabbedPane.setSelectedIndex(4);
			else
				buttonlariGOSTER();
		//************************************************************
		} catch (Exception ex) 
		{
			try 
			{
				bckp.log_kayit("System", new Date(), ex.getMessage());
				mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		//**************************************************************************************************************************
	}
	private void jobTimerBasla()
	{
		timerr = new Timer();  
		TimerTask tt = new TimerTask() {  
			@Override  
			public void run() {  
				try {
					yedekLE();
					secondRUN();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};  
		};  
		timerr.scheduleAtFixedRate(tt, 100, 5000);
	}
	private void yedekLE() throws ClassNotFoundException, SQLException
	{
		String eISMI = "" ;
		try
		{
			if(gorevLER.size() == 0) return;
			timerr.cancel();
			timerr.purge();
			timerr = null;
			while(gorevLER.size() > 0)
			{
				backupTime = true;
				eISMI = gorevLER.get(0);
				gorevLER.remove(eISMI);
				Thread.sleep(1000);
				List<emir_bilgiler> ebilgiler = bckp.emir_tek(eISMI);
				uplpnl.temizLE();
				if (ebilgiler.get(0).isSQL_YEDEK())
					sQL(eISMI , ebilgiler); // Ms Sql - My Sql
				else
					dosyaSurucu(eISMI, ebilgiler);   //Dosya - Surucu
				eISMI = "" ;
			}
			backupTime = false;
			jobTimerBasla();
		}
		catch (Exception ex)
		{
			backupTime = false;
			bckp.log_kayit(eISMI, new Date(), ex.getMessage());
			jobTimerBasla();
		}
	}
	public static void genelKayit() 
	{
		Boolean drm = false;
		if (yedekaraligiPanel.chckbxPtesi.isSelected())
			drm = true;
		else if (yedekaraligiPanel.chckbxSali.isSelected())
			drm = true;
		else if (yedekaraligiPanel.chckbxCarsamba.isSelected())
			drm = true;
		else if (yedekaraligiPanel.chckbxPersembe.isSelected())
			drm = true;
		else if (yedekaraligiPanel.chckbxCuma.isSelected())
			drm = true;
		else if (yedekaraligiPanel.chckbxCumartesi.isSelected())
			drm = true;
		else if (yedekaraligiPanel.chckbxPazar.isSelected())
			drm = true;
		if (drm == false) // ' Isaretli olan yok 
		{
			emirAnaGirisPanel. chckbxDurum.setSelected(false);
			mesajGoster(5000,Notifications.Type.WARNING,dilSecenek.dil(dILS,"Yedekleme Icin Gun secilmediginden ")  + System.lineSeparator() + System.lineSeparator()  +
					dilSecenek.dil(dILS,"Emir durumu Pasiv olarak Degistirildi"));
		}
		try
		{
			if (emirAnaGirisPanel.chckbxServerDosya.isSelected()) //= True Then ' SQL DOSYALARI
			{
				bckp.db_adi_kayit_sil(emirAnaGirisPanel.txtEmir.getText());
				for (int i = 0; i <= emirAnaGirisPanel.list.getModel().getSize() - 1; i++)
				{
					CheckListItem item = (CheckListItem) emirAnaGirisPanel.list.getModel().getElementAt(i);
					if (item.isSelected)
					{
						bckp.db_ismi_kayit(emirAnaGirisPanel.txtEmir.getText(), item.toString());
						bckp.diger_dosya_adi_kayit_sil(emirAnaGirisPanel.txtEmir.getText());
					}
				}
			}
			else
			{
				bckp.diger_dosya_adi_kayit_sil(emirAnaGirisPanel.txtEmir.getText());
				for (int i = 0; i <= emirAnaGirisPanel.list.getModel().getSize()  - 1; i++)
				{
					CheckListItem item = (CheckListItem) emirAnaGirisPanel.list.getModel().getElementAt(i);
					if (item.isSelected)
					{
						bckp.diger_dosya_ismi_kayit(emirAnaGirisPanel.txtEmir.getText(), item.toString(),  item.surucu());
						bckp.db_adi_kayit_sil(emirAnaGirisPanel.txtEmir.getText());
						bckp.server_kayit_sil(emirAnaGirisPanel.txtEmir.getText());
					}
				}
				bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText(), new Date(),dilSecenek.dil(dILS,"Emir Islemi Kaydedildi") );
			}
			List<emir_bilgiler> emirBilgiler =  bckp.emir_tek(emirAnaGirisPanel.txtEmir.getText());
			boolean sondurum = false;
			boolean kontrol = false;
			Date ilkkayit =  new Date();
			String mesaj = "";
			//
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR,1900);
			cal.set(Calendar.MONTH,1);
			cal.set(Calendar.DAY_OF_YEAR,1);
			cal.set(Calendar.HOUR_OF_DAY,0);
			cal.set(Calendar.MINUTE,0);
			cal.set(Calendar.SECOND,0);
			cal.set(Calendar.MILLISECOND,0);
			Date sonyuk = cal.getTime();
			if (emirBilgiler.size() == 0 )
				kontrol = false;
			else
			{
				sondurum = emirBilgiler.get(0).isSON_DURUM();
				SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
				Date tarih = df.parse(emirBilgiler.get(0).getSON_YUKLEME().toString());
				sonyuk = tarih ; 
				tarih = df.parse(emirBilgiler.get(0).getOLUSTURMA().toString());
				ilkkayit = tarih;
				mesaj = emirBilgiler.get(0).getMESAJ();
				kontrol = true;
			}
			bckp.genel_kayit_sil(emirAnaGirisPanel.txtEmir.getText());
			if (kontrol)
				bckp.genel_kayit(emirAnaGirisPanel.txtEmir.getText(), emirAnaGirisPanel.chckbxDurum.isSelected(),emirAnaGirisPanel. textAciklama.getText(), emirAnaGirisPanel.lblNewLabel_6.getText(), sondurum, sonyuk,emirAnaGirisPanel. chckbxServerDosya.isSelected(), mesaj, ilkkayit);
			else
				bckp.genel_kayit(emirAnaGirisPanel.txtEmir.getText(),emirAnaGirisPanel. chckbxDurum.isSelected(),emirAnaGirisPanel. textAciklama.getText(), emirAnaGirisPanel.lblNewLabel_6.getText(), false, sonyuk, emirAnaGirisPanel. chckbxServerDosya.isSelected(), mesaj, ilkkayit);
		}
		catch (Exception ex)
		{
			try {
				bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText(), new Date(), ex.getMessage());
				mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void yedeklemeKaydet() throws ClassNotFoundException, SQLException
	{
		Boolean drm = false; // Yedekleme
		try
		{
			if (yedekaraligiPanel.chckbxPtesi.isSelected())
				drm = true;
			else if (yedekaraligiPanel.chckbxSali.isSelected())
				drm = true;
			else if (yedekaraligiPanel.chckbxCarsamba.isSelected())
				drm = true;
			else if (yedekaraligiPanel.chckbxPersembe.isSelected())
				drm = true;
			else if (yedekaraligiPanel.chckbxCuma.isSelected())
				drm = true;
			else if (yedekaraligiPanel.chckbxCumartesi.isSelected())
				drm = true;
			else if (yedekaraligiPanel.chckbxPazar.isSelected())
				drm = true;
			if (drm == false) // ' Isaretli olan yok 
			{
				emirAnaGirisPanel. chckbxDurum.setSelected(false);
				mesajGoster(5000,Notifications.Type.WARNING,dilSecenek.dil(dILS,"Yedekleme Icin Gun secilmediginden ")  + System.lineSeparator() + System.lineSeparator()  +
					dilSecenek.dil(dILS,"Emir durumu Pasiv olarak Degistirildi"));			}
			if(drm)
			{
				if(yedekaraligiPanel.textHerDakka.getText().equals(""))
				{
					mesajGoster(5000,Notifications.Type.WARNING,dilSecenek.dil(dILS,"Yedekleme Aralik Zamani Girilmemis") );
					return;
				}
			}
				
			Date date = (Date) (yedekaraligiPanel.timeBaslangic.getValue());
			Date date2 = (Date) (yedekaraligiPanel.timeBitis.getValue());
			date2.setYear(date.getYear());
			date2.setMonth(date.getMonth());
			date2.setDate(date.getDate());
			bckp.yedekleme_kayit_sil(emirAnaGirisPanel.txtEmir.getText());
			bckp.yedekleme_ismi_kayit(emirAnaGirisPanel.txtEmir.getText(), 
					yedekaraligiPanel.textHerDakka.getText(), 
					yedekaraligiPanel.chckbxPtesi.isSelected(),
					yedekaraligiPanel.chckbxSali.isSelected(), 
					yedekaraligiPanel.chckbxCarsamba.isSelected(), 
					yedekaraligiPanel.chckbxPersembe.isSelected(),
					yedekaraligiPanel.chckbxCuma.isSelected(),
					yedekaraligiPanel.chckbxCumartesi.isSelected(),
					yedekaraligiPanel.chckbxPazar.isSelected(), 
					date, date2);
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText(), new Date(), dilSecenek.dil(dILS,"Emir Yedekleme Bilgileri  Kaydedildi"));
		}
		catch (Exception ex)
		{
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText(), new Date(), ex.getMessage());
			mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());	
		}
	}
	public static void bilgilendirmeKaydet() throws ClassNotFoundException, SQLException
	{
		try
		{
			bckp.bilgilendirme_kayit_sil(emirAnaGirisPanel.txtEmir.getText());
			SIFRE_DONDUR sdon = new SIFRE_DONDUR();
			String response =sdon.sDONDUR(bilgilendirmePanel.textSifre);
			bckp.bilgilendirme_ismi_kayit(emirAnaGirisPanel.txtEmir.getText(),bilgilendirmePanel.chckbxAktifPasif.isSelected() ,
					bilgilendirmePanel.chckbxIslem.isSelected(), bilgilendirmePanel.chckbxHata.isSelected(), 
					bilgilendirmePanel.textGonIsim.getText(), bilgilendirmePanel.textGonHesap.getText() , 
					bilgilendirmePanel.textAlici.getText(), bilgilendirmePanel.textKonu.getText(), 
					bilgilendirmePanel.textSmtp.getText(), bilgilendirmePanel.textPort.getText(),
					bilgilendirmePanel.textKull.getText(), response, 
					bilgilendirmePanel.chckbxSSL.isSelected(), 
					bilgilendirmePanel.chckbxTSL.isSelected());
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText(), new Date(), dilSecenek.dil(dILS,"Emir Bilgilendirme Bilgileri Kaydedildi") );
			tabbedPane_1.setSelectedIndex(0);
		}
		catch (Exception ex)
		{
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText(), new Date(), ex.getMessage());
			mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());			 
		}
	}
	public static void sunucuKaydet() throws ClassNotFoundException, SQLException
	{
		try
		{
			if (emirAnaGirisPanel.txtEmir.getText().toString().equals("")) return;
			bckp.ftp_kayit_sil(emirAnaGirisPanel.txtEmir.getText().toString());
			String neresi = "SUR";
			if (sunucuayarPanel.chckbxFtp.isSelected())
				neresi = "FTP";
			SIFRE_DONDUR sdon = new SIFRE_DONDUR();
			bckp.ftp_ismi_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), 
					sunucuayarPanel.textHost.getText(), 
					sunucuayarPanel.textKull.getText(), 
					sdon.sDONDUR(sunucuayarPanel.textSifre), 
					sunucuayarPanel.textFtpSurucu.getText(),  
					sunucuayarPanel.textPort.getText(),
					sunucuayarPanel.textZmnasm.getText().equals("") ? 120 : Integer.parseInt(sunucuayarPanel.textZmnasm.getText())  ,
					sunucuayarPanel.textEskisilme.getText().equals("") ? 0 :Integer.parseInt(sunucuayarPanel.textEskisilme.getText()) , 
					neresi, sunucuayarPanel.textSurucu.getText());
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(),new Date(),  dilSecenek.dil(dILS,"Emir FTP Bilgileri  Kaydedildi"));
			tabbedPane_1.setSelectedIndex(1);
		}
		catch (Exception ex)
		{
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), ex.getMessage());
			mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());
		}
	}
	public static void msServerKayit() throws ClassNotFoundException, SQLException
	{
		if (emirAnaGirisPanel.txtEmir.getText().toString().equals(""))
		{
			mesajGoster(5000,Notifications.Type.ERROR, dilSecenek.dil(dILS,"Emir Adi Bos Olamaz") );
			return;
		}
		SIFRE_DONDUR sdon = new SIFRE_DONDUR();
		String response =sdon.sDONDUR(serverBilgileriPanel.textMSsifre);
		bckp.MsSql_baglan( serverBilgileriPanel.textMSServer.getText() ,
				serverBilgileriPanel.textMSkull.getText(),
				response,
				serverBilgileriPanel.textMSPort.getText());
		ResultSet rs = bckp.db_ismi();
		emirAnaGirisPanel.chckbxServerDosya.setSelected(true);
		emirAnaGirisPanel.model.clear();
		emirAnaGirisPanel.lblNewLabel_5.setText(Integer.toString(0));
		while (rs.next())
		{
			emirAnaGirisPanel.model.addElement(new CheckListItem(rs.getString("name"),""));
			emirAnaGirisPanel.list.repaint();
		}
		List<String> dbliste =  bckp.db_liste(emirAnaGirisPanel.txtEmir.getText().toString());

		int sayi = emirAnaGirisPanel.list.getModel().getSize() - 1;
		int dosyaSAYI = 0;
		for (int r = 0; r <= sayi; r++)
		{
			for (int i = 0 ; i <= dbliste.size() -1; i++)
			{
				CheckListItem item = (CheckListItem) emirAnaGirisPanel.list.getModel().getElementAt(r);
				if (item.toString().equals(dbliste.get(i)))
				{
					emirAnaGirisPanel.model.remove(r);
					item.setSelected(true);
					emirAnaGirisPanel.model.insertElementAt(item, dosyaSAYI);
					dosyaSAYI += 1;
				}
			}
			emirAnaGirisPanel.list.repaint();
		}
		emirAnaGirisPanel.dosyaSAYI();
		bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(),dilSecenek.dil(dILS,"Veritabani Isimleri yuklendi") );
		emirAnaGirisPanel.lblNewLabel_6.setText("Ms Sql");
		try
		{
			bckp.server_kayit_sil(emirAnaGirisPanel.txtEmir.getText().toString());
			bckp.server_ismi_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), serverBilgileriPanel.textMSServer.getText(), true, true, serverBilgileriPanel.textMSkull.getText(), response, "Ms Sql", "","");
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(),dilSecenek.dil(dILS,"SQL SERVER Instance Bilgileri Kaydedildi") );
			bckp.instance_update(emirAnaGirisPanel.txtEmir.getText().toString(), "Ms Sql");
			tabbedPane_1.setSelectedIndex(0);
		}
		catch (Exception ex)
		{
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), ex.getMessage());
			mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());		
		}
	}
	public static void myServerKayit() throws ClassNotFoundException, SQLException
	{
		if (emirAnaGirisPanel.txtEmir.getText().toString().equals(""))
		{
			mesajGoster(5000,Notifications.Type.ERROR,dilSecenek.dil(dILS,"Emir Adi Bos Olamaz") );
			return;
		}
		SIFRE_DONDUR sdon = new SIFRE_DONDUR();
		String response = sdon.sDONDUR(serverBilgileriPanel.textMySifre);
		bckp.MySql_baglan(serverBilgileriPanel.textMykull.getText(),response,serverBilgileriPanel.textMYPort.getText());
		ResultSet rs;
		rs = bckp.db_ismiMySql();
		emirAnaGirisPanel.chckbxServerDosya.setSelected(true);
		emirAnaGirisPanel.model.clear();
		emirAnaGirisPanel.lblNewLabel_5.setText(Integer.toString(0));
		while (rs.next())
		{
			emirAnaGirisPanel.model.addElement(new CheckListItem(rs.getString("Database"),""));
			emirAnaGirisPanel.list.repaint();
		}
		List<String> dbliste =  bckp.db_liste(emirAnaGirisPanel.txtEmir.getText().toString());
		int sayi = emirAnaGirisPanel.list.getModel().getSize() - 1;
		int dosyaSAYI = 0;
		for (int r = 0; r <= sayi; r++)
		{
			for (int i = 0 ; i <= dbliste.size() -1; i++)
			{
				CheckListItem item = (CheckListItem) emirAnaGirisPanel.list.getModel().getElementAt(r);
				if (item.toString().equals(dbliste.get(i)))
				{
					emirAnaGirisPanel.model.remove(r);
					item.setSelected(true);
					emirAnaGirisPanel.model.insertElementAt(item, dosyaSAYI);
					dosyaSAYI += 1;
				}
			}
			emirAnaGirisPanel.list.repaint();
		}
		bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(),dilSecenek.dil(dILS,"Veritabani Isimleri yuklendi") );
		emirAnaGirisPanel.lblNewLabel_6.setText("My Sql");
		emirAnaGirisPanel.dosyaSAYI();
		try
		{
			bckp.server_kayit_sil(emirAnaGirisPanel.txtEmir.getText().toString());
			bckp.server_ismi_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), 
					"localhost", true, true, 
					serverBilgileriPanel.textMykull.getText(), 
					response, "My Sql",  
					serverBilgileriPanel.textMYPort.getText(),
					serverBilgileriPanel.textMyDump.getText());
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(),dilSecenek.dil(dILS,"MY SQL SERVER Instance Bilgileri Kaydedildi") );
			bckp.instance_update(emirAnaGirisPanel.txtEmir.getText().toString(), "My Sql");
			tabbedPane_1.setSelectedIndex(1);
		}
		catch (Exception ex)
		{
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), ex.getMessage());
			mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());		
		}
	}
	public void emirYukle(String siralama) throws ClassNotFoundException, SQLException
	{
		try
		{
			container.removeAll();
			container.revalidate();
			container.repaint();
			List<emir_bilgiler> emirliste = bckp.emir_liste(siralama);
			if (emirliste.size() == 0 ) 
			{  
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			else {
				int toplam = emirliste.size();
				for (int i = 0; i<=  emirliste.size() -1 ; i++)
				{
					emirTekyukle( emirliste.get(i).getEMIR_ISMI(),"ana");
					container.add(Box.createRigidArea(new Dimension(0, 5)));
				}
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				container.add(Box.createVerticalGlue());
			}
		}
		catch (Exception ex)
		{
			contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), ex.getMessage());
			mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());	
		}
	}
	public void emirTekyukle(String eismi,String nerden)
	{
		if (eismi.equals("")) return;
		gOREV_TAKIP emir = new gOREV_TAKIP(eismi);
		emir.setName(eismi);
		emir.lblemirISMI.setText(eismi);
		emir.setMaximumSize(new Dimension(Integer.MAX_VALUE, emir.getPreferredSize().height));
		container.add(emir);
		emirSayiCount();
	}
	private static void emirSayiCount()
	{
		Component[] components = container.getComponents();
		int say = 0 ;
		for (Component component : components) 
		{
			if(component.getName() != null)
				say += 1 ;			
		}
		lblemirSAYI.setText(Integer.toString(say));
		lblEmir.setText( dilSecenek.dil(dILS,"Emir Sayisi")); ;
	}
	public void emirTeksilHata(String eadi)
	{
		Component[] components = container.getComponents();
		for (Component component : components) 
		{
			if(component.getName()!= null)
			{
				if (component.getName().toString().equals(eadi))
					container.remove(component);
			}
			else if(component.getName()== null)
			{
				container.remove(component);
			}
		}
		container.revalidate();
		container.repaint();
		emirTekyukle(eadi,"");
	}
	public static void emirSil(String eadi) throws ClassNotFoundException, SQLException
	{
		Component[] components = container.getComponents();
		for (Component component : components) 
		{
			if(component.getName()!= null)
			{
				if (component.getName().toString().equals(eadi))
					container.remove(component);
			}
		}
		container.repaint();
		emirSayiCount();
		try
		{
			bckp.log_kayit(eadi, new Date(),dilSecenek.dil(dILS,"Emir Silme Islemine Baslandi") );
			bckp.genel_kayit_sil(eadi);
			bckp.db_adi_kayit_sil(eadi);
			bckp.ftp_kayit_sil(eadi);
			bckp.bilgilendirme_kayit_sil(eadi);
			bckp.yedekleme_kayit_sil(eadi);
			bckp.server_kayit_sil(eadi);
			bckp.diger_dosya_adi_kayit_sil(eadi);
			bckp.log_kayit(eadi, new Date(), dilSecenek.dil(dILS,"Emir Islemi Silindi"));
			for (int gg = 0; gg < gorevLER.size() ; gg++)
			{
				if (gorevLER.get(gg).toString() == eadi)
					gorevLER.remove(gg);
			}
		}
		catch (Exception ex)
		{
			bckp.log_kayit(eadi,new Date(), ex.getMessage());
			mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());			
		}
	}
	public static void gorevYukari(String eadi) {
		Component[] components = container.getComponents();
		for (Component component : components) {
			if(component.getName() != null)
			{
				if( component.getName().toString().equals(eadi))
					component.setPreferredSize(new Dimension(00,70));
			}
		}
		container.revalidate();
	}
	public static void gorevAsagi(String eadi) {
		Component[] components = container.getComponents();
		for (Component component : components) {
			if(component.getName() != null)
			{
				if( component.getName().toString().equals(eadi))
					component.setPreferredSize(new Dimension(00,175));
			}
		}
		container.revalidate();
	}
	public static void mesajGoster(int zaman, Notifications.Type tipType , String mesaj)
	{
		InputStream stream = null ;
		try {
			Notifications.getInstance().show(tipType,Notifications.Location.BOTTOM_RIGHT ,zaman ,mesaj);
			stream = OBS_BACKUP.class.getClassLoader().getResourceAsStream("obs/backup/dosya/hata.mp3"); //whts
			Player player = new Player(stream);
			player.play();
			if(stream != null)
				stream.close();
		} catch (Exception ex) {
		}
	}
	private void sQL(String emirADI,List<emir_bilgiler> emirBilgi) throws ClassNotFoundException, SQLException, ParseException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NumberFormatException, SocketException, IOException, InterruptedException
	{
		uplpnl.setPreferredSize(new Dimension(0,100));
		uplpnl.setMaximumSize(new Dimension(0,100));
		uplpnl.revalidate();
		Date sonyuk ;
		if(emirBilgi.get(0).getSON_YUKLEME().toString().equals("Mon Jan 01 00:00:00 TRT 1900"))
		{
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR,1900);
			cal.set(Calendar.MONTH,1);
			cal.set(Calendar.DAY_OF_YEAR,1);
			cal.set(Calendar.HOUR_OF_DAY,0);
			cal.set(Calendar.MINUTE,0);
			cal.set(Calendar.SECOND,0);
			cal.set(Calendar.MILLISECOND,0);
			sonyuk = cal.getTime();
		}
		else 
		{
			SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
			sonyuk = df.parse(emirBilgi.get(0).getSON_YUKLEME().toString());
		}
		bckp.genel_kayit_durum(emirADI, false, sonyuk, "");// Herhalukarda islem baslangici yedeklenemdi notu
		List<String> dbliste = bckp.db_liste(emirADI);
		if (dbliste.size() == 0)
		{
			bckp.log_kayit(emirADI, new Date(), dilSecenek.dil(dILS,"Yuklenecek Dosya Secilmemis") );
			bckp.genel_kayit_durum(emirADI, false, sonyuk, dilSecenek.dil(dILS,"Yuklenecek Dosya Secilmemis"));
			uplpnl.setVisible(false);
			bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS,"Emir Yuklendi") );
			emirYukle("EMIR_ISMI") ;
			return;
		}
		uplpnl.lblEmirAdi.setText(emirADI);
		uplpnl.lblDosAdet.setText(Integer.toString(dbliste.size()));
		uplpnl.lblAciklama.setText(emirBilgi.get(0).getEMIR_ACIKLAMA());
		List<ftp_bilgiler> ftpBilgi = new ArrayList<ftp_bilgiler>();	// *************FTP BILGILERI AL
		ftpBilgi = bckp.ftp_bilgi(emirADI);
		String neresi = ftpBilgi.get(0).getNERESI();
		if(neresi.equals("FTP"))
		{
			if(ftpBilgi.get(0).getSURUCU().equals("")) // "FTP Surucu Secilmemis"
			{
				bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS,"FTP Surucu Secilmemis") );
				bckp.genel_kayit_durum(emirADI, false, sonyuk, dilSecenek.dil(dILS,"FTP Surucu Secilmemis"));
				uplpnl.setVisible(false);
				bckp.log_kayit(emirADI, new Date(), dilSecenek.dil(dILS,"Emir Yuklendi"));
				mesajGoster(5000,Notifications.Type.WARNING, dilSecenek.dil(dILS,"FTP Surucu Secilmemis"));	
				emirYukle("EMIR_ISMI") ;
				return;
			}
			sqlFtp(emirADI,ftpBilgi);
		}
		else 
		{
			if(ftpBilgi.get(0).getSURUCU_YER().equals(""))  // "Surucu Secilmemis"
			{
				bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS,"Surucu Secilmemis") );
				bckp.genel_kayit_durum(emirADI, false, sonyuk, dilSecenek.dil(dILS,"Surucu Secilmemis"));
				uplpnl.setVisible(false);
				bckp.log_kayit(emirADI, new Date(), dilSecenek.dil(dILS,"Emir Yuklendi"));
				mesajGoster(5000,Notifications.Type.WARNING, dilSecenek.dil(dILS,"Surucu Secilmemis"));	
				emirYukle("EMIR_ISMI") ;
				return;
			}
			sqlYerelsurucu( emirADI  ,ftpBilgi);
		}
	}
	private void sqlYerelsurucu( String emirADI,List<ftp_bilgiler> ftpBilgi) 
	{
		try {
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			gorevSETCURSOR(Cursor.WAIT_CURSOR);
			List<server_bilgiler> serverBilgi = new ArrayList<server_bilgiler>();
			serverBilgi = bckp.server_bilgi(emirADI);
			String decodedString = serverBilgi.get(0).getSIFRE();
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len = bytes.length; i<len; i++)
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			String ftp, kull, sifre, surucu, port, neresi, surucu_yer;
			int eskiyedek, zmnasimi;
			eskiyedek =  Integer.valueOf(ftpBilgi.get(0).getESKI_YEDEK());
			zmnasimi = Integer.valueOf(ftpBilgi.get(0).getZMN_ASIMI());
			surucu_yer = ftpBilgi.get(0).getSURUCU_YER();
			uplpnl.lblSurucu.setText( ftpBilgi.get(0).getSURUCU_YER().replace("/", "\\"));
			String sqlsifre = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
			if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
				bckp.MsSql_baglan(serverBilgi.get(0).getINSTANCE() ,serverBilgi.get(0).getKULLANICI(),sqlsifre,serverBilgi.get(0).getPORT());
			else //My sql
				bckp.MySql_baglan( serverBilgi.get(0).getKULLANICI(),sqlsifre,serverBilgi.get(0).getPORT());   
			String tarr =  TARIH_CEVIR.tarihddMMyyyyHHmm(new Date());
			String dosADI = "";
			bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS,"Yedeklemeye Baslandi") );
			List<String> dbliste = bckp.db_liste(emirADI);
			uplpnl.RPB1.setMaximum(dbliste.size());
			uplpnl.RPB1.setStringPainted(true);
			for (int i = 0; i <= dbliste.size() - 1; i++)
			{
				dosADI = dbliste.get(i); // Dosya Adi
				uplpnl.lblDosyaAdi.setText(dosADI);
				uplpnl.Progres_Bar_1(i + 1);
				if(serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql") ? bckp.dosyaKontrolMS(dosADI) : bckp.dosyaKontrolMY(dosADI))
				{
					if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
						bckp.backup_al(dosADI, tarr + "_" + dosADI);
					else
						bckp.mySQL_backup(serverBilgi.get(0).getMY_DUMP(), serverBilgi.get(0).getKULLANICI(),sqlsifre,dosADI, glb.BACKUP_YERI + tarr + "_" + dosADI + ".sql");
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS," Backup Alindi") );
					String dosya, dzip;
					if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
						dosya = tarr + "_" + dosADI + ".bak";
					else
						dosya = tarr + "_" + dosADI + ".sql";
					dzip = tarr + "_" + dosADI + ".zip";
					bckp.zip_yap_sifrele(dosya, glb.BACKUP_YERI, dzip, sifRELE , zipSIFRE );
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS," Zip Haline Getirildi") );
					File tmpDir = new File(ftpBilgi.get(0).getSURUCU_YER());
					if (! tmpDir.exists())
						tmpDir.mkdirs();
					glb.dos_sil(surucu_yer + "\\" + dzip);
					fileCOPY(glb.BACKUP_YERI + dzip,surucu_yer + "\\" + dzip);
					glb.dos_sil(glb.BACKUP_YERI + dzip);
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS," Surucuye Yuklendi")  );
					if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
					{
						if (glb.dos_sil(glb.BACKUP_YERI + tarr + "_" + dosADI + ".bak"))
							bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS," bak Dosyasi Silindi") );
					}
					else
					{
						if (glb.dos_sil(glb.BACKUP_YERI + tarr + "_" + dosADI + ".sql"))
							bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS," sql Dosyasi Silindi"));
					}
				}
				else
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS, " Dosya Bulunamadi***************")  );
			}
			Date nowwDate = new Date();
			durumYAZ(emirADI,nowwDate);	
			uplpnl.lblDosyaAdi.setText("");
			bckp.genel_kayit_durum(emirADI, true, nowwDate, dilSecenek.dil(dILS,"Yedeklendi") );
			if (eskiyedek > 0) // **************SURUCU ESKILERI SIL ************************************************
			{
				uplpnl.Progres_Bar_Temizle_1();
				uplpnl.Progres_Bar_Temizle_2();
				dosADI = "";
				List<remote_filelist> ls = new ArrayList<remote_filelist>();
				ls = bckp. sur_liste(surucu_yer);
				uplpnl.RPB1.setMaximum(dbliste.size());
				uplpnl.RPB1.setStringPainted(true);
				uplpnl.RPB2.setMaximum(ls.size());
				uplpnl.RPB2.setStringPainted(true);
				for (int i = 0; i <= dbliste.size() - 1; i++)
				{
					dosADI = dbliste.get(i);
					uplpnl.lblDosyaAdi.setText(dosADI);
					uplpnl. Progres_Bar_1( i + 1);
					for (int r = 0; r <= ls.size() - 1; r++)
					{
						uplpnl.  Progres_Bar_2( r + 1);
						String ftpDOSYA = ls.get(r).getDosyaADI();
						if (fileESITMI(ftpDOSYA,dosADI))
						{
							Date ftar = dosyaTAIRIHI(ls.get(r).getDosyaADI().toString());
							long dateBeforeInMs = ftar.getTime();
							long dateAfterInMs = new Date().getTime();
							long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
							int araFARK = (int)TimeUnit.HOURS.convert(timeDiff, TimeUnit.MILLISECONDS);
							if (araFARK> (eskiyedek*24)) 
							{
								bckp.log_kayit(emirADI, new Date(),ls.get(r).getDosyaADI() + dilSecenek.dil(dILS," Surucuye Silmeye Gitti") );
								glb.dos_sil(surucu_yer + "\\" +  ls.get(r).getDosyaADI());
								bckp.log_kayit(emirADI, new Date(),ls.get(r).getDosyaADI() + dilSecenek.dil(dILS," Dosya Surucuden Eski Tarihli Silindi") );
							}
						}
					}
				}
			}
			yapildiMAILI(emirADI);
			uplpnl.setPreferredSize(new Dimension(0,00));
			uplpnl.setMaximumSize(new Dimension(0,0));
			uplpnl.revalidate();
			bckp.log_kayit(emirADI, new Date(), dilSecenek.dil(dILS,"Yedekleme Islemi Sona Erdi") );
			emirYENIDENBASLAT(emirADI);
			gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		catch (Exception ex)
		{
			try {
				bckp.genel_kayit_durum(emirADI, false, new Date(),  ex.getMessage().length() > 40 ? ex.getMessage().substring(0, 40) : ex.getMessage());
				bckp.log_kayit(emirADI, new Date(), ex.getMessage());
				uplpnl.Progres_Bar_Temizle_1();
				uplpnl.Progres_Bar_Temizle_2();
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				yapilmadiMAILI(emirADI,ex.getMessage());
				bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS,"Hata Durumundan Emir Bosaldi") );
				emirTeksilHata(emirADI);
				gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			} catch (Exception e) 
			{
				gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				e.printStackTrace();
			}
		} 
	}
	private void sqlFtp(String emirADI,List<ftp_bilgiler> ftpBilgi) 
	{
		try {
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			gorevSETCURSOR(Cursor.WAIT_CURSOR);
			String ftp, kull, sifre, surucu, neresi, surucu_yer;
			int eskiyedek, zmnasimi;
			ftp = ftpBilgi.get(0).getHOST();
			kull = ftpBilgi.get(0).getKULLANICI();

			String decodedString = ftpBilgi.get(0).getSIFRE();
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) 
			{
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			sifre = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
			surucu = ftpBilgi.get(0).getSURUCU();
			int port = Integer.valueOf( ftpBilgi.get(0).getPORT());
			eskiyedek = Integer.valueOf(ftpBilgi.get(0).getESKI_YEDEK());
			zmnasimi = Integer.valueOf(ftpBilgi.get(0).getZMN_ASIMI());
			neresi = ftpBilgi.get(0).getNERESI();
			surucu_yer = ftpBilgi.get(0).getSURUCU_YER();
			uplpnl.lblSurucu.setText( ftp + "\\" + surucu.replace("/", "\\"));
			if (glb.internet_kontrol() == false)
			{
				bckp.genel_kayit_durum(emirADI, false, new Date(),dilSecenek.dil(dILS,"Yedeklenmedi Internet Baglantisi Yok")  );
				bckp.log_kayit(emirADI, new Date(), dilSecenek.dil(dILS,"Yedeklenmedi Internet Baglantisi Yok"));
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				emirTeksilHata(emirADI);
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
				return;
			}
			boolean result = bckp.DoesFtpDirectoryExist(ftp ,surucu,Integer.valueOf(port),kull, sifre);
			if (result == false)
			{
				bckp.genel_kayit_durum(emirADI, false, new Date(),dilSecenek.dil(dILS,"Yedeklenmedi FTP Surucu Bulunamadi") );
				bckp.log_kayit(emirADI, new Date(), dilSecenek.dil(dILS,"Yedeklenmedi FTP Surucu Bulunamadi"));
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				emirTeksilHata(emirADI);
				gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			// SERVER baglanti Ogren
			List<server_bilgiler> serverBilgi = new ArrayList<server_bilgiler>();
			serverBilgi = bckp.server_bilgi(emirADI);
			decodedString = serverBilgi.get(0).getSIFRE();
			byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) {
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			String sqlsifre = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
			if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
				bckp.MsSql_baglan(serverBilgi.get(0).getINSTANCE() ,serverBilgi.get(0).getKULLANICI(),sqlsifre,serverBilgi.get(0).getPORT());
			else //My sql
				bckp.MySql_baglan( serverBilgi.get(0).getKULLANICI(),sqlsifre,serverBilgi.get(0).getPORT());   
			String tarr =  TARIH_CEVIR.tarihddMMyyyyHHmm(new Date());
			bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS,"Yedeklemeye Baslandi")  );
			String dosADI = "";
			List<String> dbliste = bckp.db_liste(emirADI);
			uplpnl.RPB1.setMaximum(dbliste.size());
			uplpnl.RPB1.setStringPainted(true);
			for (int i = 0; i <= dbliste.size() - 1; i++)
			{
				dosADI = dbliste.get(i); // Dosya Adi
				uplpnl.lblDosyaAdi.setText(dosADI);
				uplpnl.Progres_Bar_1( i + 1);
				if(serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql") ? bckp.dosyaKontrolMS(dosADI) : bckp.dosyaKontrolMY(dosADI))
				{
					if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
						bckp.backup_al(dosADI, tarr + "_" + dosADI);
					else
						bckp.mySQL_backup(serverBilgi.get(0).getMY_DUMP(), serverBilgi.get(0).getKULLANICI(),sqlsifre,dosADI, glb.BACKUP_YERI + tarr + "_" + dosADI + ".sql");	
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS," Backup Alindi")  );
					String dosya, dzip;
					if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
						dosya = tarr + "_" + dosADI + ".bak";
					else
						dosya = tarr + "_" + dosADI + ".sql";
					dzip = tarr + "_" + dosADI + ".zip";
					bckp.zip_yap_sifrele(dosya, glb.BACKUP_YERI, dzip, sifRELE, zipSIFRE);
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS," Zip Haline Getirildi") );
					uploadFTPFiles( ftp, surucu, glb.BACKUP_YERI, tarr + "_" + dosADI + ".zip", kull, sifre, port, zmnasimi);// FTP Yukleme
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS," FTP Yuklendi")  );
					if( serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
					{
						glb.dos_sil(glb.BACKUP_YERI + tarr + "_" + dosADI + ".bak");
						bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS," bak Dosyasi Silindi"));
					}
					else
					{
						glb.dos_sil(glb.BACKUP_YERI + tarr + "_" + dosADI + ".sql");
						bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS," sql Dosyasi Silindi"));
					}
					glb.dos_sil(glb.BACKUP_YERI + tarr + "_" + dosADI + ".zip");
					bckp.log_kayit(emirADI, new Date(), dosADI +  dilSecenek.dil(dILS," ZIP Dosyasi Silindi"));
				}
				else
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS, " Dosya Bulunamadi***************")  );
			}
			Date nowwDate = new Date();
			durumYAZ(emirADI,nowwDate);		
			bckp.genel_kayit_durum(emirADI, true,nowwDate, dilSecenek.dil(dILS,"Yedeklendi")  );
			uplpnl.lblDosyaAdi.setText("");
			if (eskiyedek > 0) //**************FTP ESKILERI SIL
			{
				uplpnl.Progres_Bar_Temizle_1();
				uplpnl.Progres_Bar_Temizle_2();
				dosADI = "";
				List<remote_filelist> ls = new ArrayList<remote_filelist>();
				ls = bckp.ListRmtFiles( ftp , surucu, kull, sifre,port);
				uplpnl.RPB1.setMaximum(dbliste.size());
				uplpnl.RPB1.setStringPainted(true);
				uplpnl.RPB2.setMaximum(ls.size());
				uplpnl.RPB2.setStringPainted(true);
				for (int i = 0; i <= dbliste.size() - 1; i++)
				{
					dosADI = dbliste.get(i);
					uplpnl. Progres_Bar_1( i + 1);
					uplpnl.lblDosyaAdi.setText(dosADI);
					for (int r = 0; r <= ls.size() - 1; r++)
					{
						uplpnl.Progres_Bar_2(r + 1);
						String ftpDOSYA = ls.get(r).getDosyaADI();
						if (fileESITMI(ftpDOSYA,dosADI))
						{
							Date ftar = dosyaTAIRIHI(ls.get(r).getDosyaADI().toString());
							long dateBeforeInMs = ftar.getTime();
							long dateAfterInMs = new Date().getTime();
							long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
							int araFARK = (int)TimeUnit.HOURS.convert(timeDiff, TimeUnit.MILLISECONDS);
							if (araFARK > (eskiyedek * 24)) 
							{
								bckp.log_kayit(emirADI, new Date(),  dosADI + dilSecenek.dil(dILS," FTP Silmeye Gitti") );
								bckp.ftp_sil( ftp, surucu, ls.get(r).getDosyaADI(), kull, sifre, port);
								bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS," FTP Eski Tarihli Silindi") );
							}
						}
					}
				}
			}
			yapildiMAILI(emirADI);
			uplpnl.setPreferredSize(new Dimension(0,0));
			uplpnl.setMaximumSize(new Dimension(0,0));
			uplpnl.revalidate();
			bckp.log_kayit(emirADI, new Date(), dilSecenek.dil(dILS,"Yedekleme Islemi Sona Erdi") );
			emirYENIDENBASLAT(emirADI);
			gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		catch (Exception ex)
		{
			try 
			{
				bckp.genel_kayit_durum(emirADI, false, new Date(),  ex.getMessage().length() > 40 ? ex.getMessage().substring(0, 40) : ex.getMessage());
				bckp.log_kayit(emirADI, new Date(), ex.getMessage());
				uplpnl.Progres_Bar_Temizle_1();
				uplpnl.Progres_Bar_Temizle_2();
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				yapilmadiMAILI( emirADI,ex.getMessage());
				bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS,"Hata Durumundan Emir Bosaldi")  );
				emirTeksilHata(emirADI);
				gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			} catch (Exception e) {
				gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				e.printStackTrace();
			}
		} 
	}
	private void bilgilendirmeOku(String emir, String mesaj ,List<bilgilendirme_bilgiler> bilgiBilgi) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException, SQLException
	{
		if (bilgiBilgi.size() > 0)
			mailAt( bilgiBilgi , mesaj,emir);
	}
	private void dosyaSurucu(String emirADI,List<emir_bilgiler> emirBilgi) throws ClassNotFoundException, SQLException, ParseException, InterruptedException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NumberFormatException, SocketException, IOException
	{
		uplpnl.setPreferredSize(new Dimension(0,100));
		uplpnl.setMaximumSize(new Dimension(0,100));
		uplpnl.revalidate();
		Date sonyuk ;
		if(emirBilgi.get(0).getSON_YUKLEME().toString().equals("Mon Jan 01 00:00:00 TRT 1900"))
		{
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR,1900);
			cal.set(Calendar.MONTH,1);
			cal.set(Calendar.DAY_OF_YEAR,1);
			cal.set(Calendar.HOUR_OF_DAY,0);
			cal.set(Calendar.MINUTE,0);
			cal.set(Calendar.SECOND,0);
			cal.set(Calendar.MILLISECOND,0);
			sonyuk = cal.getTime();
		}
		else 
		{
			SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
			sonyuk = df.parse(emirBilgi.get(0).getSON_YUKLEME().toString());
		}
		bckp.genel_kayit_durum(emirADI, false, sonyuk, "");// Herhalukarda islem baslangici yedeklenemdi notu
		List<db_List> dbliste = bckp.diger_dosya_liste(emirADI);

		if (dbliste.size() == 0)
		{
			bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS, "Yuklenecek Dosya Secilmemis")  );
			bckp.genel_kayit_durum(emirADI, false, sonyuk, dilSecenek.dil(dILS, "Yuklenecek Dosya Secilmemis"));
			uplpnl.setVisible(false);
			bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS, "Emir Yuklendi")  );
			emirYukle("EMIR_ISMI") ;
			return;
		}
		uplpnl.lblEmirAdi.setText(emirADI);
		uplpnl.lblDosAdet.setText(Integer.toString(dbliste.size()));
		uplpnl.lblAciklama.setText(emirBilgi.get(0).getEMIR_ACIKLAMA());
		// ***FTP BILGILERI AL
		List<ftp_bilgiler> ftpBilgi = new ArrayList<ftp_bilgiler>();
		ftpBilgi = bckp.ftp_bilgi(emirADI);
		String neresi =ftpBilgi.get(0).getNERESI();
		if(neresi.equals("FTP"))
		{
			if(ftpBilgi.get(0).getSURUCU().equals(""))
			{
				bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS, "FTP Surucu Secilmemis")  );
				bckp.genel_kayit_durum(emirADI, false, sonyuk, dilSecenek.dil(dILS, "FTP Surucu Secilmemis"));
				uplpnl.setVisible(false);
				bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS, "Emir Yuklendi")  );
				emirYukle("EMIR_ISMI") ;
				return;
			}
			dosyasurucuFtp(emirADI,ftpBilgi);
		}
		else
			dosyasurucuYerelsurucu( emirADI ,ftpBilgi );
	}
	private void dosyasurucuYerelsurucu(String emirADI,List<ftp_bilgiler> ftpBilgi )
	{
		try {
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			gorevSETCURSOR(Cursor.WAIT_CURSOR);
			int eskiyedek;
			eskiyedek = Integer.valueOf(ftpBilgi.get(0).getESKI_YEDEK());
			uplpnl.lblSurucu.setText(ftpBilgi.get(0).getSURUCU_YER().replace("/", "\\"));
			String tarr = TARIH_CEVIR.tarihddMMyyyyHHmm(new Date());
			bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS, "Yedeklemeye Baslandi")  );
			String dosADI = "";
			Boolean folderMI = false;
			List<db_List>  dbliste = bckp.diger_dosya_liste (emirADI);
			uplpnl.RPB1.setMaximum(dbliste.size());
			uplpnl.RPB1.setStringPainted(true);
			uplpnl.RPB2.setStringPainted(true);
			for (int i = 0; i <= dbliste.size() - 1; i++)
			{
				uplpnl. Progres_Bar_1( i + 1);
				uplpnl.lblDosyaAdi.setText(dosADI);
				dosADI = dbliste.get(i).getAdi();
				File file = new File(dbliste.get(i).getPath() +"\\" + dbliste.get(i).getAdi());
				if(file.exists())
				{
					folderMI = file.isDirectory(); // Check if it's a directory
					uplpnl.Progres_Bar_1( i + 1);
					String dosya, dzip,  dpath,uzantisiz = "";
					String input = dosADI;
					int index = dosADI.lastIndexOf(".");
					if (index >= 0)
						uzantisiz = input.substring(0, index); // or index + 1 to keep slash
					dosya = dbliste.get(i).getPath() + "\\" + dosADI;
					dpath = dbliste.get(i).getPath() + "\\";
					dzip = tarr + "_" + uzantisiz + ".zip";
					if (folderMI)
					{
						uplpnl.RPB2.setString(dilSecenek.dil(dILS, "Zip Haline Getiriliyor")  );
						dzip = tarr + "_" + dbliste.get(i).getAdi() + ".zip";
						String okumadosyaadi = dbliste.get(i).getPath() +"\\"+dbliste.get(i).getAdi()+"\\";
						Path pathokuma = Paths.get(dbliste.get(i).getPath() +"\\"+dbliste.get(i).getAdi()); 
						Path pathyazma = Paths.get(glb.BACKUP_YERI, dzip); 
						bckp. zip_folder_sifrele(pathokuma,pathyazma,sifRELE, zipSIFRE);
						uplpnl.RPB2.setString("");
					}
					else
					{
						uplpnl.RPB2.setString(dilSecenek.dil(dILS, "Zip Haline Getiriliyor"));
						String okumadosyaadi = dbliste.get(i).getPath() + "\\" + dbliste.get(i).getAdi();
						bckp.diger_zip_yap_sifrele(okumadosyaadi, glb.BACKUP_YERI, dzip, sifRELE, zipSIFRE);
						uplpnl.RPB2.setString("");
					}
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS, " Zip Haline Getirildi")  );
					File tmpDir = new File(ftpBilgi.get(0).getSURUCU_YER());
					if (! tmpDir.exists())
						tmpDir.mkdirs();
					fileCOPY(glb.BACKUP_YERI + dzip, ftpBilgi.get(0).getSURUCU_YER() + "\\" + dzip);
					glb.dos_sil(glb.BACKUP_YERI + "\\" + dzip);
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS, " Surucuye Yuklendi")  );
					bckp.log_kayit(emirADI,new Date(), uzantisiz + dilSecenek.dil(dILS, " ZIP Dosyasi Silindi")  );
				}
				else 
				{
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS, " Dosya Bulunamadi***************")  );
				}
			}
			Date nowwDate = new Date();
			durumYAZ(emirADI,nowwDate);
			uplpnl.lblDosyaAdi.setText("");
			bckp.genel_kayit_durum(emirADI, true,nowwDate, dilSecenek.dil(dILS, "Yedeklendi") );
			if (eskiyedek > 0) // **************FTP ESKILERI SIL
			{
				uplpnl.Progres_Bar_Temizle_1();
				uplpnl.Progres_Bar_Temizle_2();
				dosADI = "";
				List<remote_filelist> ls = new ArrayList<remote_filelist>();
				ls = bckp. sur_liste(ftpBilgi.get(0).getSURUCU_YER());
				uplpnl.RPB1.setMaximum(dbliste.size());
				uplpnl.RPB1.setStringPainted(true);
				uplpnl.RPB2.setMaximum(ls.size());
				uplpnl.RPB2.setStringPainted(true);
				for (int i = 0; i <= dbliste.size() - 1; i++)
				{
					dosADI = dbliste.get(i).getAdi();
					uplpnl.lblDosyaAdi.setText(dosADI);
					uplpnl.Progres_Bar_1( i + 1);
					for (int r = 0; r <= ls.size() - 1; r++)
					{
						uplpnl.Progres_Bar_2( r + 1);
						String ftpDOSYA = ls.get(r).getDosyaADI();
						int index = dosADI.lastIndexOf(".");
						if (index >= 0)
							dosADI = dosADI.substring(0, index); 
						if (fileESITMI(ftpDOSYA,dosADI))
						{
							Date ftar = dosyaTAIRIHI(ls.get(r).getDosyaADI().toString());
							long dateBeforeInMs = ftar.getTime();
							long dateAfterInMs = new Date().getTime();
							long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
							int araFARK = (int)TimeUnit.HOURS.convert(timeDiff, TimeUnit.MILLISECONDS);
							if (araFARK > (eskiyedek * 24)) 
							{
								bckp.log_kayit(emirADI, new Date(),ls.get(r).getDosyaADI() + dilSecenek.dil(dILS, " Surucuye Silmeye Gitti") );
								glb.dos_sil(ls.get(r).getFilePATH() + "\\" + ls.get(r).getDosyaADI());
								bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS, " Dosya Surucuden Eski Tarihli Silindi") );
							}
						}
					}
				}
			}
			yapildiMAILI(emirADI);
			uplpnl.setPreferredSize(new Dimension(0,00));
			uplpnl.setMaximumSize(new Dimension(0,0));
			uplpnl.revalidate();
			bckp.log_kayit(emirADI, new Date(), dilSecenek.dil(dILS, "Yedekleme Islemi Sona Erdi")  );
			emirYENIDENBASLAT(emirADI);
			gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		catch (Exception ex)
		{
			try {
				bckp.genel_kayit_durum(emirADI, false, new Date(),  ex.getMessage().length() > 40 ? ex.getMessage().substring(0, 40) : ex.getMessage());
				bckp.log_kayit(emirADI, new Date(), ex.getMessage());
				uplpnl.Progres_Bar_Temizle_1();
				uplpnl.Progres_Bar_Temizle_2();
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				yapilmadiMAILI( emirADI,ex.getMessage());
				bckp.log_kayit(emirADI, new Date(), dilSecenek.dil(dILS, "Hata Durumundan Emir Bosaldi") );
				emirTeksilHata(emirADI);
				gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			} catch (Exception e) {
				gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				e.printStackTrace();
			}
		} 
	}
	private void dosyasurucuFtp(String emirADI,List<ftp_bilgiler> ftpBilgi )
	{
		try {
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			gorevSETCURSOR(Cursor.WAIT_CURSOR);
			String ftp, kull, sifre, surucu, neresi, surucu_yer;
			int eskiyedek, zmnasimi;
			ftp = ftpBilgi.get(0).getHOST();
			kull = ftpBilgi.get(0).getKULLANICI();
			String decodedString = ftpBilgi.get(0).getSIFRE();
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) 
			{
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			sifre = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
			surucu = ftpBilgi.get(0).getSURUCU();
			int port = Integer.valueOf( ftpBilgi.get(0).getPORT());
			eskiyedek =  Integer.valueOf(ftpBilgi.get(0).getESKI_YEDEK());
			zmnasimi = Integer.valueOf(ftpBilgi.get(0).getZMN_ASIMI());
			neresi = ftpBilgi.get(0).getNERESI();
			surucu_yer = ftpBilgi.get(0).getSURUCU_YER();
			uplpnl.lblSurucu.setText( ftp + "\\" + surucu.replace("/", "\\"));
			if (glb.internet_kontrol() == false)
			{
				bckp.genel_kayit_durum(emirADI, false, new Date(), dilSecenek.dil(dILS, "Yedeklenmedi Internet Baglantisi Yok") );
				bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS, "Yedeklenmedi Internet Baglantisi Yok"));
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				emirTeksilHata(emirADI);
				gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			boolean result =   bckp.DoesFtpDirectoryExist(ftp ,surucu,Integer.valueOf(port),kull, sifre);
			if (result == false)
			{
				bckp.genel_kayit_durum(emirADI, false, new Date(),  dilSecenek.dil(dILS, "Yedeklenmedi FTP Surucu Bulunamadi")  );
				bckp.log_kayit(emirADI, new Date(), dilSecenek.dil(dILS, "Yedeklenmedi FTP Surucu Bulunamadi"));
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				emirTeksilHata(emirADI);
				gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			String tarr =  TARIH_CEVIR.tarihddMMyyyyHHmm(new Date());
			bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS, "Yedeklemeye Baslandi")  );
			String dosADI = "";
			Boolean folderMI = false;
			List<db_List>  dbliste = bckp.diger_dosya_liste (emirADI);
			uplpnl.Progres_Bar_Temizle_1();
			uplpnl.RPB1.setMaximum(dbliste.size());
			uplpnl.RPB1.setStringPainted(true);
			for (int i = 0; i <= dbliste.size() - 1; i++)
			{
				uplpnl. Progres_Bar_1( i + 1);
				uplpnl.lblDosyaAdi.setText(dosADI);
				dosADI = dbliste.get(i).getAdi();
				File file = new File(dbliste.get(i).getPath() +"\\"+dbliste.get(i).getAdi());
				if(file.exists())
				{
					folderMI = file.isDirectory(); // Check if it's a directory
					uplpnl.Progres_Bar_1( i + 1);
					String dosya, dzip,  dpath,uzantisiz = "";
					String input = dosADI;
					int index = input.lastIndexOf(".");
					if (index >= 0)
						uzantisiz = input.substring(0, index); // or index + 1 to keep slash
					dosya = dbliste.get(i).getPath() + "\\" + dosADI;
					dpath = dbliste.get(i).getPath() + "\\";
					dzip = tarr + "_" + uzantisiz + ".zip";
					uplpnl.Progres_Bar_Temizle_2();
					if (folderMI)
					{
						dzip = tarr + "_" + dbliste.get(i).getAdi() + ".zip";
						String okumadosyaadi = dbliste.get(i).getPath() + "\\" + dbliste.get(i).getAdi()+"\\";
						Path pathokuma = Paths.get(dbliste.get(i).getPath() + "\\" + dbliste.get(i).getAdi()); 
						Path pathyazma = Paths.get(glb.BACKUP_YERI, dzip); 
						bckp. zip_folder_sifrele(pathokuma,pathyazma,sifRELE, zipSIFRE);
					}
					else
					{
						String okumadosyaadi = dbliste.get(i).getPath() + "\\" + dbliste.get(i).getAdi();
						bckp.diger_zip_yap_sifrele(okumadosyaadi, glb.BACKUP_YERI, dzip, sifRELE, zipSIFRE);
					}
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS, " Zip Haline Getirildi")  );
					uploadFTPFiles(ftp, surucu, glb.BACKUP_YERI, dzip, kull, sifre, port, zmnasimi);
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS,  " FTP Yuklendi") );
					glb.dos_sil(glb.BACKUP_YERI + dzip);
					bckp.log_kayit(emirADI,new Date(), uzantisiz + dilSecenek.dil(dILS,  " ZIP Dosyasi Silindi") );
				}
				else {
					bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS, " Dosya Bulunamadi***************")  );
				}
			}
			Date nowwDate = new Date();
			durumYAZ(emirADI,nowwDate);		
			uplpnl.lblDosyaAdi.setText("");
			bckp.genel_kayit_durum(emirADI, true,nowwDate,dilSecenek.dil(dILS, "Yedeklendi")  );
			if (eskiyedek > 0) // **************FTP ESKILERI SIL
			{
				uplpnl.Progres_Bar_Temizle_1();
				uplpnl.Progres_Bar_Temizle_2();
				uplpnl.lblDosyaAdi.setText(dosADI);
				dosADI = "";
				List<remote_filelist> ls = new ArrayList<remote_filelist>();
				ls = bckp.ListRmtFiles( ftp , surucu, kull, sifre,port);
				uplpnl.RPB1.setMaximum(dbliste.size());
				uplpnl.RPB1.setStringPainted(true);
				uplpnl.RPB2.setMaximum(ls.size());
				uplpnl.RPB2.setStringPainted(true);
				for (int i = 0; i <= dbliste.size() - 1; i++)
				{
					uplpnl.Progres_Bar_1( i + 1);
					for (int r = 0; r <= ls.size() - 1; r++)
					{
						uplpnl.Progres_Bar_2( r + 1);
						dosADI = dbliste.get(i).getAdi();
						String ftpDOSYA = ls.get(r).getDosyaADI();
						if (fileESITMI(ftpDOSYA,dosADI))
						{
							Date ftar = dosyaTAIRIHI(ls.get(r).getDosyaADI().toString());
							long dateBeforeInMs = ftar.getTime();
							long dateAfterInMs = new Date().getTime();
							long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
							int araFARK = (int)TimeUnit.HOURS.convert(timeDiff, TimeUnit.MILLISECONDS);
							if (araFARK > (eskiyedek * 24)) 
							{
								bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS, " FTP ye Silmeye Gitti")  );
								bckp.ftp_sil( ftp, surucu, ls.get(r).getDosyaADI(), kull, sifre, port);
								bckp.log_kayit(emirADI, new Date(), dosADI + dilSecenek.dil(dILS, " FTP Eski Tarihli Silindi") );
							}
						}
					}
				}
			}
			yapildiMAILI(emirADI);
			uplpnl.setPreferredSize(new Dimension(0,00));
			uplpnl.setMaximumSize(new Dimension(0,0));
			uplpnl.revalidate();
			bckp.log_kayit(emirADI, new Date(), dilSecenek.dil(dILS, "Yedekleme Islemi Sona Erdi") );
			emirYENIDENBASLAT(emirADI);
			gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		catch (Exception ex)
		{
			try {
				bckp.genel_kayit_durum(emirADI, false, new Date(),ex.getMessage().length() > 40 ? ex.getMessage().substring(0, 40) : ex.getMessage());
				bckp.log_kayit(emirADI, new Date(), ex.getMessage());
				uplpnl.Progres_Bar_Temizle_1();
				uplpnl.Progres_Bar_Temizle_2();
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				yapilmadiMAILI(emirADI,ex.getMessage());
				bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS, "Hata Durumundan Emir Bosaldi") );
				emirTeksilHata(emirADI);
				gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			} catch (Exception e) {
				gorevSETCURSOR(Cursor.DEFAULT_CURSOR);
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				e.printStackTrace();
			}
		} 
	}
	private void uploadFTPFiles(String ftpp, String ftpsurucu, String dosyayolu, String dosadi, String kull, String sifre, int port, int zmn)
	{
		FTPClient ftp = new FTPClient();
		try 
		{
			ftp.setConnectTimeout(zmn * 1000);
			ftp.connect(ftpp,port);
			ftp.login(kull, sifre);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			ftp.changeWorkingDirectory(ftpsurucu);
			File secondLocalFile = new File(dosyayolu + dosadi );
			String secondRemoteFile = dosadi;
			OutputStream outputStream = ftp.storeFileStream(secondRemoteFile);
			InputStream inputStream = new FileInputStream(secondLocalFile);
			byte[] bytesIn = new byte[1024]; 
			int read = 0;
			long toplam = 0 ;
			int fileLenght = (int) secondLocalFile.length();
			double speedInKBps = 0.00;
			uplpnl.RPB2.setMaximum((int) fileLenght);
			uplpnl.RPB2.setStringPainted(true);
			Instant start = Instant.now();
			while ((read = inputStream.read(bytesIn)) != -1) {
				outputStream.write(bytesIn, 0, read);
				toplam += read;
				uplpnl.Progres_Bar_2((int)toplam);
				Instant finish = Instant.now();
				long timeElapsed = Duration.between(start, finish).toMillis();
				int seconds = (int)((timeElapsed / 1000) % 60);
				double speedInBytesPerSecond =  toplam / (timeElapsed / 1000.0);
				uplpnl.lblHiz.setText(FORMATLAMA.doub_0(speedInBytesPerSecond/1024) + " KBytes");
			}
			outputStream.flush();
			outputStream.close();
			ftp.completePendingCommand();
			inputStream.close();
			ftp.logout();
			ftp.disconnect();
			uplpnl.RPB2.setStringPainted(false);
			uplpnl.Progres_Bar_2( 0);
			secondLocalFile = null ;
			inputStream = null;
			outputStream = null;

		} catch (Exception ex) 
		{
			try 
			{
				bckp.log_kayit("System", new Date(), "2301=" + ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void emirBOSALT(String emirADI)
	{
		Component[] components = container.getComponents();
		for (Component component : components) 
		{
			if(component.getName()!= null)
			{
				if (component.getName().toString().equals(emirADI))
					container.remove(component);
			}
			else if (component.getName() == null)
			{
				container.remove(component);
			}
		}
		container.revalidate();
		container.repaint();
		emirSayiCount();
	}
	private static void mailAt(List<bilgilendirme_bilgiler> bilgiBilgi,String mesaj ,String eADI) throws ClassNotFoundException, SQLException
	{
		try {
			String gonisim, gonhesap, alici, konu, smtp, port, kull, sifre;
			boolean ssl, tsl;
			gonisim = bilgiBilgi.get(0).getGON_ISIM();
			gonhesap = bilgiBilgi.get(0).getGON_HESAP();
			alici = bilgiBilgi.get(0).getALICI();
			konu = bilgiBilgi.get(0).getKONU();
			smtp = bilgiBilgi.get(0).getSMTP();
			port = bilgiBilgi.get(0).getSMTP_PORT();
			kull = bilgiBilgi.get(0).getKULLANICI();
			String decodedString = bilgiBilgi.get(0).getSIFRE();
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) {
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			sifre = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
			if (bilgiBilgi.get(0).isSSL())
				ssl = true;
			else
				ssl = false;
			if (bilgiBilgi.get(0).isTSL())
				tsl = true;
			else
				tsl = false;
			String[] to = { alici };
			MimeBodyPart messagePart = null ;
			Properties props = System.getProperties();
			props.put("mail.smtp.host", smtp);
			props.put("mail.smtp.user", kull);
			props.put("mail.smtp.password",sifre);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			props.put("mail.smtp.starttls.enable", tsl);
			if (ssl)
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
				//props.put("mail.smtp.startsls.enable", SSL);
			Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(kull, sifre);
				}
			});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(gonhesap ,gonisim ));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}
			for (int i = 0; i < toAddress.length; i++) {
				message.setRecipient(RecipientType.TO,  toAddress[i]);
			}
			messagePart = new MimeBodyPart();
			DatagramSocket socket = new DatagramSocket();
			socket.connect(new InetSocketAddress("google.com", 80));
			messagePart.setText(mesaj ,"UTF-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messagePart);
			message.setSentDate(new Date());
			message.setSubject("OBS BACKUP " , "UTF-8");
			message.setContent(multipart);
			Transport.send(message);
			message= null;
			session = null;
		}
		catch (Exception ex)
		{
			bckp.log_kayit(eADI, new Date(), dilSecenek.dil(dILS, "Mail Gonderirken Hata Olustu")  );
		}
	}
	private void durumYAZ(String emirADI,Date nowwDate)
	{
		Component[] components = container.getComponents();
		for (Component component : components) {
			if (component.getName()!= null)
			{
				if (component.getName().toString().equals(emirADI)) {
					JPanel qweJPanel = (JPanel) component ; 
					Component[] componentt = qweJPanel.getComponents();
					for (Component compo : componentt) {
						if(compo.getName() != null)
						{
							if(compo.getName().equals("lblSonDurum"))
							{
								JLabel sndrm = (JLabel) compo;
								sndrm.setText( dilSecenek.dil(dILS, "Yedeklendi") );
							}
							if(compo.getName().equals("lblSonYedek"))
							{
								JLabel sndrm = (JLabel) compo;
								SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
								sndrm.setText(df.format(nowwDate));
							}
						}
					}
				}
			}
			component.revalidate();
		}
	}
	private void yapildiMAILI(String emirADI) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException, SQLException
	{
		List<bilgilendirme_bilgiler> bilgiBilgi = new ArrayList<bilgilendirme_bilgiler>();
		bilgiBilgi  = bckp.bilgilendirme_bilgi(emirADI);
		if ( bilgiBilgi.size() > 0)
		{
			if ( bilgiBilgi.get(0).isDURUM() )
			{
				if (bilgiBilgi.get(0).isGONDERILDIGINDE())
				{
					SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm:ss");
					Date today = new Date();        
					bilgilendirmeOku(emirADI, emirADI + "    " + df.format(today) +  "     " + dilSecenek.dil(dILS, "Yedeklendi"),bilgiBilgi);
					bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS, "Yedekleme Yapildi Maili gonderildi")  );
				}
			}
		}
	}
	private void yapilmadiMAILI(String emirADI,String mesaj) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		List<bilgilendirme_bilgiler> bilgiBilgi = new ArrayList<bilgilendirme_bilgiler>();
		bilgiBilgi  = bckp.bilgilendirme_bilgi(emirADI);
		if ( bilgiBilgi.size() > 0)
		{
			if ( bilgiBilgi.get(0).isDURUM() )
			{
				if (bilgiBilgi.get(0).isHATA_DURUMUNDA())
				{
					SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm:ss");
					Date today = new Date();        
					bilgilendirmeOku(emirADI, emirADI + "    " + df.format(today) + " =" + mesaj,bilgiBilgi);
					bckp.log_kayit(emirADI, new Date(),dilSecenek.dil(dILS, "Yedekleme Yapilamadi Maili gonderildi") );
				}
			}
		}
	}
	private void fileCOPY(String from, String tO) throws IOException, InterruptedException
	{
		File sourceFile = new File(from);
		long  toplam = (int) sourceFile.length();
		File destFile =new File(tO);
		OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(destFile));
		InputStream inputStream =new FileInputStream(sourceFile);
		long inen= 0;
		byte[] bytesArray = new byte[4096];
		int bytesRead = -1;
		uplpnl.RPB2.setMaximum((int) toplam);
		uplpnl.RPB2.setStringPainted(true);
		double speedInKBps = 0.00;
		Instant start = Instant.now();
		while ((bytesRead = inputStream.read(bytesArray)) != -1)
		{
			outputStream2.write(bytesArray, 0, bytesRead);
			inen +=(long) bytesRead ;
			double yuzde =( Double.valueOf(inen)/ Double.valueOf(toplam))*100;
			uplpnl.Progres_Bar_2((int)inen);
			Instant finish = Instant.now();
			long timeElapsed = Duration.between(start, finish).toMillis();
			int seconds = (int)((timeElapsed / 1000) % 60);
			double speedInBytesPerSecond =  inen / (timeElapsed / 1000.0);
			uplpnl.lblHiz.setText(FORMATLAMA.doub_0(speedInBytesPerSecond/1024) + " KBytes");
		}
		uplpnl.RPB2.setStringPainted(false);
		uplpnl.Progres_Bar_2( 0);
		outputStream2.close();
		inputStream.close();
	}
	private void emirYENIDENBASLAT(String emirADI)
	{
		Component[] components = container.getComponents();
		for (Component component : components) {
			if (component.getName()!= null)
			{
				if (component.getName().toString().equals(emirADI)) {
					JPanel qweJPanel = (JPanel) component ; 
					Component[] componentt = qweJPanel.getComponents();
					for (Component compo : componentt) {
						if(compo.getName() != null)
						{
							if(compo.getName().equals("btnyenidenBASLAT"))
							{
								JButton yndbslt = (JButton) compo;
								yndbslt.doClick();
							}
						}
					}
				}
			}
			component.revalidate();
		}
		container.revalidate();
	}
	private void gorevSETCURSOR(int crsr)
	{
		Component[] components = container.getComponents();
		for (Component component : components) 
		{
			if (component.getName()!= null)
				component.setCursor( Cursor.getPredefinedCursor(crsr));
			component.revalidate();
		}
	}
	private boolean fileESITMI(String ftpDOSYA , String dosYA)
	{
		boolean result = false;
		if(ftpDOSYA.length() <= 13) return result;
		String dosADIOGREN = ftpDOSYA.substring(13,ftpDOSYA.lastIndexOf("."));
		if(dosADIOGREN.equals(dosYA))
			result = true ;
		return result;
	}
	private Date dosyaTAIRIHI(String ftpDOSYA) throws ParseException
	{
		Date ftar = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ENGLISH);
		String dateInString = ftpDOSYA.substring(4,8) + "." +  
							  ftpDOSYA.substring(2,4) + "." + 
							  ftpDOSYA.substring(0,2) + " " + 
							  ftpDOSYA.substring(8,10) + ":" + 
							  ftpDOSYA.substring(10,12) ;
		TARIH_CEVIR trhCevir = new TARIH_CEVIR();
		if(trhCevir.isValidDate(dateInString))
			ftar = formatter.parse(dateInString);
		return ftar;
	}
	public static void emirleriSTOPYAP()
	{
		Component[] components = container.getComponents();
		for (Component component : components) {
			if (component.getName()!= null)
			{
				JPanel qweJPanel = (JPanel) component ; 
				Component[] componentt = qweJPanel.getComponents();
				for (Component compo : componentt) {
					if(compo.getName() != null)
					{
						if(compo.getName().equals("btnStop"))
						{
							JButton stp = (JButton) compo;
							stp.doClick();
						}
					}
				}
			}
		}
	}
	public static void emirTekStop(String eadi)
	{
		Component[] components = container.getComponents();
		for (Component component : components) {
			if (component.getName()!= null)
			{
				if (component.getName().equals(eadi))
				{
					JPanel qweJPanel = (JPanel) component ; 
					Component[] componentt = qweJPanel.getComponents();
					for (Component compo : componentt) {
						if(compo.getName() != null)
						{
							if(compo.getName().equals("btnStop"))
							{
								JButton stp = (JButton) compo;
								stp.doClick();
							}
						}
					}
				}
			}
		}
	}
	public static void systemTRY()
	{
		PopupMenu popup;
		Image image;
		if (SystemTray.isSupported()) 
		{
			SystemTray tray = SystemTray.getSystemTray();
			image = Toolkit.getDefaultToolkit().getImage(OBS_BACKUP.class.getResource("/obs/backup/icons/backup-100.png"));
			ActionListener listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnBuyult.doClick();
				}
			};
			ActionListener kapat = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			};
			popup = new PopupMenu();
			Font defaultFont = new Font("Tahoma", Font.PLAIN, 11); 
			float adjustmentRatio = 1.2f; 
			float newFontSize = defaultFont.getSize() * adjustmentRatio ; 
			Font derivedFont = defaultFont.deriveFont(newFontSize);
			
			MenuItem buyultITEM = new MenuItem(dilSecenek.dil(dILS,"Buyult"));
			buyultITEM.setFont(derivedFont);
			buyultITEM.addActionListener(listener);
			MenuItem kapatItem = new MenuItem(dilSecenek.dil(dILS,"Kapat"));
			kapatItem.setFont(derivedFont);
			kapatItem.addActionListener(kapat);
			popup.addSeparator();
			popup.add(buyultITEM);
			popup.addSeparator();
			popup.add(kapatItem);
			popup.addSeparator();
			trayIcon = new TrayIcon(image, "OBS_BACKUP", popup);
			trayIcon.setImageAutoSize(true);			
			trayIcon.addActionListener(listener);
			try {
				tray.add(trayIcon);
				trayIcon.displayMessage("OBS BACKUP", dilSecenek.dil(dILS, "Arka Plan basladi")    , MessageType.INFO);
				trayIcon.setToolTip("OBS BACKUP");
			} catch (AWTException ex) {
				mesajGoster(5000,Notifications.Type.WARNING, ex.getMessage());	
			}
			if (trayIcon != null)
				trayIcon.setImage(Toolkit.getDefaultToolkit().getImage(OBS_BACKUP.class.getResource("/obs/backup/icons/backup-100.png")));
		} else {
			btnMinimize.doClick();
		}
	}
	private void pidKONTROL()
	{
		try {
			bckp.pid_kayit((int) ProcessHandle.current().pid());
		} catch (Exception ex) 
		{
			try {
				bckp.log_kayit("System", new Date(), ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void secondRUN() 
	{
		try {
			int dosyaPID = bckp.pid_oku() ; 
			pidlb.setText("PID : " + String.valueOf(dosyaPID));
			if(dosyaPID == 0) return ; // Dosyada Kayit Yok 
			if(dosyaPID != (int) ProcessHandle.current().pid())
				btnKapat.doClick();
		} catch (Exception ex) 
		{
			try {
				bckp.log_kayit("System", new Date(), ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void sifreDurumu()
	{
		if(Integer.valueOf(diltemaString[2].toString()) == 1 ? true:false)
		{
			sifRELE = true;
			String decodedString = diltemaString[3];
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) 
			{
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			try 
			{
				ayarlarPanel.chckbxSifrele.setSelected(true);
				ayarlarPanel.passwordText.setText(ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes));
				zipSIFRE = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes);
			} catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		else {
			ayarlarPanel.passwordText.setText("");
			ayarlarPanel.chckbxSifrele.setSelected(false);
			sifRELE = false ;
			zipSIFRE = "" ;
		}
	}
	public static void dil() throws ClassNotFoundException, SQLException
	{
		if(dILS.equals("")) dILS = "Turkce";
		btnGorevler.setToolTipText(dilSecenek.dil(dILS,"Gorevler"));
		btnYeni_Gorev.setToolTipText(dilSecenek.dil(dILS,"Yeni Gorev"));
		btnLoglama.setToolTipText(dilSecenek.dil(dILS,"Loglama"));
		btnKayitliEmirler.setToolTipText(dilSecenek.dil(dILS,"Kayitli Emirler"));
		btnHepsiYukari.setToolTipText(dilSecenek.dil(dILS,"Hepsini Kucult"));
		btnHepsiAsagi.setToolTipText(dilSecenek.dil(dILS,"Hepsini Buyult"));
		btnYeniSifre.setToolTipText(dilSecenek.dil(dILS,"Sifre Yenile"));
		btnUploadAll.setToolTipText(dilSecenek.dil(dILS,"Gorevleri Yedekle"));
		btnStartAll.setToolTipText(dilSecenek.dil(dILS,"Gorevleri Baslat"));
		btnStopAll.setToolTipText(dilSecenek.dil(dILS,"Gorevleri Durdur"));
		btnFileIndir.setToolTipText(dilSecenek.dil(dILS,"Dosya Indir"));
		btnSifreEkrani.setToolTipText(dilSecenek.dil(dILS,"Sifre Ekrani"));
		btnHepsiAktiv.setToolTipText(dilSecenek.dil(dILS,"Gorevleri Aktif Yap"));
		btnHepsiPasiv.setToolTipText(dilSecenek.dil(dILS,"Gorevleri Pasiv Yap"));
		lblEmir.setText(dilSecenek.dil(dILS,"Emir Sayisi"));

		btnHakkinda.setToolTipText(dilSecenek.dil(dILS,"Hakkinda"));
		btnAyarlar.setToolTipText(dilSecenek.dil(dILS,"Ayarlar"));
		btnKapat.setToolTipText(dilSecenek.dil(dILS,"Kapat"));

		sifreGirisPanel.lblNewLabel.setText(dilSecenek.dil(dILS,"Sifre"));
		sifreGirisPanel.lblDefaultpwd.setText(dilSecenek.dil(dILS,"Varsayilan Sifre :       obs"));
		//************************************************************************************************
		bilgilendirmePanel.lblNewLabel.setText(dilSecenek.dil(dILS,"Durum"));
		bilgilendirmePanel.chckbxAktifPasif.setText(dilSecenek.dil(dILS,"Aktif / Pasif"));
		bilgilendirmePanel.chckbxIslem.setText(dilSecenek.dil(dILS,"Islem Gerceklestiginde"));
		bilgilendirmePanel.chckbxHata.setText(dilSecenek.dil(dILS,"Hata Durumunda"));
		TitledBorder titledBorder = (TitledBorder)bilgilendirmePanel.panel_1.getBorder();
		titledBorder.setTitle(dilSecenek.dil(dILS,"Mail Bilgileri"));
		bilgilendirmePanel.panel_1.setBorder(titledBorder);
		bilgilendirmePanel.lblNewLabel_1.setText(dilSecenek.dil(dILS,"Gonderen Isim"));
		bilgilendirmePanel.lblNewLabel_2.setText(dilSecenek.dil(dILS,"Gonderen Hesap"));
		titledBorder = (TitledBorder)bilgilendirmePanel.panel.getBorder();
		titledBorder.setTitle(dilSecenek.dil(dILS,"Gonderme Durumu"));
		bilgilendirmePanel.panel.setBorder(titledBorder);
		bilgilendirmePanel.lblNewLabel_3.setText(dilSecenek.dil(dILS,"Alici"));
		bilgilendirmePanel.lblNewLabel_4.setText(dilSecenek.dil(dILS,"Konu"));
		titledBorder = (TitledBorder)bilgilendirmePanel.panel_1_1.getBorder();
		titledBorder.setTitle(dilSecenek.dil(dILS,"Server Ayarlari"));
		bilgilendirmePanel.panel_1_1.setBorder(titledBorder);
		bilgilendirmePanel.btnDenemeMail.setText(dilSecenek.dil(dILS,"Deneme Maili"));
		bilgilendirmePanel.lblNewLabel_7.setText(dilSecenek.dil(dILS,"Kullanici"));
		bilgilendirmePanel.lblNewLabel_8.setText(dilSecenek.dil(dILS,"Sifre"));
		bilgilendirmePanel.btnNewButton_9.setText(dilSecenek.dil(dILS,"Kaydet"));
		//*****************************************************************************************
		yedekaraligiPanel.lblNewLabel.setText(dilSecenek.dil(dILS,"Her"));
		yedekaraligiPanel.lblNewLabel_1.setText(dilSecenek.dil(dILS,"dakkada bir"));
		yedekaraligiPanel.lblNewLabel_2.setText(dilSecenek.dil(dILS,"Gunler"));
		yedekaraligiPanel.chckbxPtesi.setText(dilSecenek.dil(dILS,"Pazartesi"));
		yedekaraligiPanel.chckbxSali.setText(dilSecenek.dil(dILS,"Sali"));
		yedekaraligiPanel.chckbxCarsamba.setText(dilSecenek.dil(dILS,"Carsamba"));
		yedekaraligiPanel.chckbxPersembe.setText(dilSecenek.dil(dILS,"Persembe"));
		yedekaraligiPanel.chckbxCuma.setText(dilSecenek.dil(dILS,"Cuma"));
		yedekaraligiPanel.chckbxCumartesi.setText(dilSecenek.dil(dILS,"Cumartesi"));
		yedekaraligiPanel.chckbxPazar.setText(dilSecenek.dil(dILS,"Pazar"));
		yedekaraligiPanel.lblNewLabel_3.setText(dilSecenek.dil(dILS,"Baslangic"));
		yedekaraligiPanel.lblNewLabel_3_1.setText(dilSecenek.dil(dILS,"Bitis"));
		titledBorder = (TitledBorder)yedekaraligiPanel.panel.getBorder();
		titledBorder.setTitle(dilSecenek.dil(dILS,"Aralik"));
		yedekaraligiPanel.panel.setBorder(titledBorder);
		yedekaraligiPanel.btnNewButton_9.setText(dilSecenek.dil(dILS,"Kaydet"));
		//****************************************************************************************
		emirKopyalaPanel.lblNewLabel.setText(dilSecenek.dil(dILS,"Yeni Emir Ismi"));
		emirKopyalaPanel.btnNewButton_9.setText(dilSecenek.dil(dILS,"Kaydet"));
		//****************************************************************************************
		sifreYenilePanel.lblNewLabel.setText(dilSecenek.dil(dILS,"Gecerli Sifreniz"));
		sifreYenilePanel.lblysif.setText(dilSecenek.dil(dILS,"Yeni Sifreniz"));
		sifreYenilePanel.lblNewLabel_1.setText(dilSecenek.dil(dILS,"Sifre Yenileme"));
		//****************************************************************************************
		loglamaPanel.lblNewLabel.setText(dilSecenek.dil(dILS,"Arama"));
		loglamaPanel.btnNewButton.setText(dilSecenek.dil(dILS,"Sil"));
		loglamaPanel.btnExcell.setToolTipText(dilSecenek.dil(dILS,"Excell Aktarma"));
		//****************************************************************************************
		downloadFilePanel.btnNewButton.setText(dilSecenek.dil(dILS,"Indir"));
		//****************************************************************************************
		kayitliEmirlerPanelEmirler.lblNewLabel.setText(dilSecenek.dil(dILS,"Arama"));
		//****************************************************************************************
		emirAnaGirisPanel.btnDosyaSec.setText(dilSecenek.dil(dILS,"Dosya Sec"));
		emirAnaGirisPanel.btnSurucuSec.setText(dilSecenek.dil(dILS,"Surucu Sec"));
		emirAnaGirisPanel.btnServer.setText(dilSecenek.dil(dILS,"Server Baglanti"));
		emirAnaGirisPanel.lblNewLabel.setText(dilSecenek.dil(dILS,"Durum"));
		emirAnaGirisPanel.lblNewLabel_1.setText(dilSecenek.dil(dILS,"Emir Ismi"));
		emirAnaGirisPanel.lblNewLabel_4.setText(dilSecenek.dil(dILS,"Dosya Sayisi"));
		emirAnaGirisPanel.chckbxServerDosya.setText(dilSecenek.dil(dILS,"Sql Veritabani / Dosya-Surucu Yedekleme"));
		emirAnaGirisPanel.lblNewLabel_2.setText(dilSecenek.dil(dILS,"Aciklama"));
		//***************************************************************************************
		serverBilgileriPanel.lblNewLabel_1.setText(dilSecenek.dil(dILS,"Kullanici"));
		serverBilgileriPanel.lblNewLabel_2.setText(dilSecenek.dil(dILS,"Sifre"));
		titledBorder = (TitledBorder)serverBilgileriPanel.panel_2.getBorder();
		titledBorder.setTitle(dilSecenek.dil(dILS,"Baglanti"));
		serverBilgileriPanel.panel_2.setBorder(titledBorder);
		serverBilgileriPanel.btnMSkaydet.setText(dilSecenek.dil(dILS,"Kaydet"));
		serverBilgileriPanel.btnMSTest.setText(dilSecenek.dil(dILS,"Baglanti Test"));
		titledBorder = (TitledBorder)serverBilgileriPanel.panel_2_2.getBorder();
		titledBorder.setTitle(dilSecenek.dil(dILS,"Baglanti"));
		serverBilgileriPanel.panel_2_2.setBorder(titledBorder);
		serverBilgileriPanel.lblNewLabel_1_2.setText(dilSecenek.dil(dILS,"Kullanici"));
		serverBilgileriPanel.lblNewLabel_2_2.setText(dilSecenek.dil(dILS,"Sifre"));
		serverBilgileriPanel.btnDumpSec.setText(dilSecenek.dil(dILS,"Sec"));
		serverBilgileriPanel.btnMyKaydet.setText(dilSecenek.dil(dILS,"Kaydet"));
		serverBilgileriPanel.btnMyTest.setText(dilSecenek.dil(dILS,"Baglanti Test"));
		//**************************************************************************************
		sunucuayarPanel.chckbxYerel.setText(dilSecenek.dil(dILS,"Yerel Surucu"));
		titledBorder = (TitledBorder)sunucuayarPanel.panel_12.getBorder();
		titledBorder.setTitle(dilSecenek.dil(dILS,"FTP Ayarlari"));
		sunucuayarPanel.panel_12.setBorder(titledBorder);
		sunucuayarPanel.lblNewLabel_1.setText(dilSecenek.dil(dILS,"Kullanici"));
		sunucuayarPanel.lblNewLabel_2.setText(dilSecenek.dil(dILS,"Sifre"));
		titledBorder = (TitledBorder)sunucuayarPanel.panel_12_1.getBorder();
		titledBorder.setTitle(dilSecenek.dil(dILS,"FTP Diger Ayarlar"));
		sunucuayarPanel.panel_12_1.setBorder(titledBorder);
		sunucuayarPanel.lblNewLabel_3.setText(dilSecenek.dil(dILS,"Surucu"));
		sunucuayarPanel.lblNewLabel_5.setText(dilSecenek.dil(dILS,"Zaman Asimi"));
		sunucuayarPanel.btnNewButton_6.setText(dilSecenek.dil(dILS,"Surucu Kontrol"));
		titledBorder = (TitledBorder)sunucuayarPanel.panel_12_1_1.getBorder();
		titledBorder.setTitle(dilSecenek.dil(dILS,"Yerel Surucu"));
		sunucuayarPanel.panel_12_1_1.setBorder(titledBorder);
		sunucuayarPanel.lblNewLabel_8.setText(dilSecenek.dil(dILS,"Surucu"));
		sunucuayarPanel.btnNewButton_7.setText(dilSecenek.dil(dILS,"Surucu Sec"));
		titledBorder = (TitledBorder)sunucuayarPanel.panel_12_1_1_1.getBorder();
		titledBorder.setTitle(dilSecenek.dil(dILS,"Eski Yedek"));
		sunucuayarPanel.panel_12_1_1_1.setBorder(titledBorder);
		sunucuayarPanel.lblNewLabel_6.setText(dilSecenek.dil(dILS,"Eski Yed.Silme"));
		sunucuayarPanel.lblNewLabel_7.setText(dilSecenek.dil(dILS,"gunden eski olanari silme (0 Silinmez)"));
		sunucuayarPanel.btnftpkont.setText(dilSecenek.dil(dILS,"Ftp Kontrol"));
		sunucuayarPanel.btnNewButton_9.setText(dilSecenek.dil(dILS,"Kaydet"));
		sunucuayarPanel.lblNewLabel_9.setText(dilSecenek.dil(dILS,"sn."));
		//**********************************************************************************************
		ayarlarPanel.lblNewLabel.setText(dilSecenek.dil(dILS,"Tema"));
		ayarlarPanel.lblNewLabel_1.setText(dilSecenek.dil(dILS,"Dil"));
		ayarlarPanel.btnKaydet.setText(dilSecenek.dil(dILS,"Kaydet"));
		ayarlarPanel.lblNewLabel_2.setText(dilSecenek.dil(dILS,"ZIP Sifrele"));
		ayarlarPanel.lblNewLabel_3.setText(dilSecenek.dil(dILS,"Acilis Sifre Sor"));
		ayarlarPanel.chckbxWinStart.setText(dilSecenek.dil(dILS,"Windows ile Baslat")); 
		ayarlarPanel.lblNewLabel_4.setText(dilSecenek.dil(dILS,"Version Kontrol")); 
		//**********************************************************************************************
		if(dILS.equals("Turkce"))
		{
			tabbedPane_1.setTitleAt(0, "Emir");
			tabbedPane_1.setTitleAt(1, "Sunucu Ayarlari");
			tabbedPane_1.setTitleAt(2, "Bilgilendirme");
			tabbedPane_1.setTitleAt(3, "Yedekleme Araligi");
			tabbedPane_1.setTitleAt(4, "Server Bilgileri");
			tabbedPane_1.setTitleAt(5, "Emir Kopyala");
		}
		else {
			tabbedPane_1.setTitleAt(0, "Job");
			tabbedPane_1.setTitleAt(1, "Setting Ftp/Folders");
			tabbedPane_1.setTitleAt(2, "Send Confirmation");
			tabbedPane_1.setTitleAt(3, "Schedule Backups");
			tabbedPane_1.setTitleAt(4, "Server Settings");
			tabbedPane_1.setTitleAt(5, "Copy Job");
		}
	}
	private void tema(String tema)
	{
		if (tema ==null)
		{
			FlatCarbonIJTheme.setup();
			return;
		}
		switch (tema) {
		case "FlatCarbonIJ" -> FlatCarbonIJTheme.setup();
		case "FlatMonocaiIJ" ->	FlatMonocaiIJTheme.setup();
		case "FlatMacDarkLaf" -> FlatMacDarkLaf.setup();
		case "FlatNordIJ" -> FlatNordIJTheme.setup();
		case "FlatHighContrastIJ" -> FlatHighContrastIJTheme.setup();
		case "FlatMaterialPalenightIJ" -> FlatMaterialPalenightIJTheme.setup();
		case "FlatMaterialDeepOceanIJ" -> FlatMaterialDeepOceanIJTheme.setup();
		case "FlatArcDarkIJ" -> FlatArcDarkIJTheme.setup();
		case "FlatGradiantoNatureGreenIJ" -> FlatGradiantoNatureGreenIJTheme.setup();
		case "FlatGrayIJ" -> FlatGrayIJTheme.setup();
		case "FlatMaterial" -> FlatMaterialLighterIJTheme.setup();
		case "FlatArcOrangeIJ" -> FlatArcOrangeIJTheme.setup();
		}
	}
	public static void buttonlariGOSTER()
	{
		 tabbedPane.setSelectedIndex(0);
		 btnGorevler.setEnabled(true);
		 btnYeni_Gorev.setEnabled(true);
		 btnLoglama.setEnabled(true);
		 btnKayitliEmirler.setEnabled(true);
		 btnHepsiYukari.setEnabled(true);
		 btnHepsiAsagi.setEnabled(true);
		 btnYeniSifre.setEnabled(true);
		 btnUploadAll.setEnabled(true);
		 btnStartAll.setEnabled(true);
		 btnStopAll.setEnabled(true);
		 btnFileIndir.setEnabled(true);
		 btnRestore.setEnabled(true);
		 btnSifreEkrani.setVisible(true);
		 btnHepsiAktiv.setEnabled(true);
		 btnHepsiPasiv.setEnabled(true);
		 btnHakkinda.setEnabled(true);
		 btnAyarlar.setEnabled(true);
		 btnHepsiYukari.doClick();
	}
	private void versiyon_oku() 
	{
		try 
		{
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String ip = in.readLine(); //you get the IP as a String
			//System.out.println(ip);
			if(ip.equals("78.189.76.247")) return;
			if (glb.internet_kontrol() == false)
				return ;
			String eskitar = "" ;
			String eskiver = "";
			String yeniver = "";
			String fileName = GLOBAL.SURUCU  + "\\OBS_BACKUP_VERSION.txt";
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
				ftp.disconnect();
				return ;
			}
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply))
			{
				ftp.disconnect();
				return ;
			}
			ftp.enterLocalPassiveMode();
			String remoteFile1 = ftp.printWorkingDirectory() + "/OBS_BACKUP_VERSION.txt";
			File downloadFile1 = new File( GLOBAL.SURUCU + "\\OBS_BACKUP_VERSIONS.txt");
			OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
			boolean success = ftp.retrieveFile(remoteFile1, outputStream1);
			outputStream1.close();
			if (success == false)
				{
	            glb.dos_sil(GLOBAL.SURUCU +"\\OBS_BACKUP_VERSIONS.txt");
				ftp.logout();
				ftp.disconnect();
				return ;
				}
			ftp.logout();
			ftp.disconnect();
			fileName = "" ;
			fileName = GLOBAL.SURUCU + "\\OBS_BACKUP_VERSIONS.txt";
			fileReader = null;
			fileReader =  new FileReader(fileName);
			bufferedReader = null;
			bufferedReader =  new BufferedReader(fileReader);
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
				glb.dos_sil(GLOBAL.SURUCU + "\\OBS_BACKUP_VERSIONS.txt");
			}
			else
			{
				glb.dos_sil(GLOBAL.SURUCU + "\\OBS_BACKUP_VERSIONS.txt");
				String html = "Yeni Versiyon Mevcut"
						+ System.lineSeparator()
						+ "Mevcut Version = " + eskiver + "      "
						+ System.lineSeparator()
						+ "Yeni Version = " + yeniver 
						+ System.lineSeparator();
				mesajGoster(15000,Notifications.Type.INFO,  String.format(html));
			}
		}
		catch (Exception ex)
		{
			try {
				bckp.log_kayit("System", new Date(), ex.getMessage());
				mesajGoster(15000,Notifications.Type.INFO,  ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
