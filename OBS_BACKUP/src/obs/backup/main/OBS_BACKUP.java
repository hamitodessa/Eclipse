package obs.backup.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import LOGER_KAYIT.TXT_LOG;

import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.CheckListItem;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.GLOBAL;
import javazoom.jl.player.Player;
import obs.backup.gorev.gOREV_TAKIP;
import obs.backup.other.Bilgilendirme;
import obs.backup.other.EmirAnaGiris;
import obs.backup.other.EmirKopyala;
import obs.backup.other.KayitliEmirler;
import obs.backup.other.LoglamaRapor;
import obs.backup.other.ServerBilgileri;
import obs.backup.other.SunucuAyarlari;
import obs.backup.other.Title_Bar;
import obs.backup.other.UploadPanel;
import obs.backup.other.YedeklemeAraligi;
import raven.toast.Notifications;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import OBS_C_2025.SIFRE_DONDUR;
import OBS_C_2025.TARIH_CEVIR;
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

@SuppressWarnings({ "static-access" ,"resource","unused","deprecation"})
public class OBS_BACKUP extends JFrame {

	GLOBAL glb = new GLOBAL();
	public static List<String> gorevLER = new ArrayList<String>();
	Timer timerr;
	public static String gelenISIM ="";
	static BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public static JPanel container ;

	public static JTabbedPane tabbedPane ;
	public static JTabbedPane tabbedPane_1;
	int x ,y ;
	public static YedeklemeAraligi yedekaraligiPanel;
	public static SunucuAyarlari sunucuayarPanel;
	public static Bilgilendirme bilgilendirmePanel;
	public static ServerBilgileri serverBilgileriPanel;
	public static EmirKopyala emirKopyalaPanel ;
	public static EmirAnaGiris emirAnaGirisPanel;

	public static LoglamaRapor loglamaPanel;
	public static KayitliEmirler kayitliEmirlerPanelEmirler;
	UploadPanel uplpnl ;

	private JScrollPane scrollPane;
	public static JButton btnYeni_Gorev;
	public static JButton btnNewButton_2;
	public static JButton btnGorevler;
	private static JLabel lblemirSAYI;

	static Component horizontalGlue = null ;
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

	/**
	 * Create the frame.
	 */


	public OBS_BACKUP() {
		setUndecorated(true);
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("obs.backup.theme");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
		FlatMacDarkLaf.setup();

		//FlatCarbonIJTheme.setup();
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

		setBounds(100, 100, 900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(new Title_Bar(this), BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(30,0));//160
		splitPane.setLeftComponent(panel);

		//***************
				JToolBar toolBar = new JToolBar();
				toolBar.setFloatable(false);
				toolBar.setOrientation(SwingConstants.VERTICAL);
				//toolBar.setBounds(100, 5, 130, 27);
				panel.add(toolBar );
		//***************
				
		btnGorevler = new JButton();
		btnGorevler.setToolTipText("Gorevler");
		//btnGorevler.setPreferredSize(new Dimension(25,25));
		btnGorevler.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/emirler.png")));
		btnGorevler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gelenISIM = "" ;
				try {
					contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					emir_yukle("EMIR_ISMI") ;
					contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					tabbedPane.setSelectedIndex(0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.setLayout(new BorderLayout());
		panel.add(toolBar,BorderLayout.CENTER);
		toolBar.add(btnGorevler);
		
		btnYeni_Gorev = new JButton();
		btnYeni_Gorev.setToolTipText("Yeni Gorev");
		btnYeni_Gorev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gelenISIM = "" ;
				tabbedPane.setSelectedIndex(1);
				tabbedPane_1.setSelectedIndex(0);
				emirAnaGirisPanel.emirDOLDUR();
			}
		});
		btnYeni_Gorev.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/yeniemir.png")));
		toolBar.add(btnYeni_Gorev);

		JButton btnLoglama = new JButton();
		btnLoglama.setToolTipText("Log Goruntule");
		btnLoglama .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				try {
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
		JButton btnKayitliEmirler = new JButton();
		btnKayitliEmirler.setToolTipText("Kayitli Emirler");
		btnKayitliEmirler .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(3);
				try {
					contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					kayitliEmirlerPanelEmirler.doldur();
					contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(btnKayitliEmirler );

		JButton btnHepsiYukari = new JButton();
		btnHepsiYukari.setToolTipText("Gorev Paneli Yukari");
		btnHepsiYukari.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/up.png")));
		btnHepsiYukari .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Component[] components = container.getComponents();
				for (Component component : components) {
					if(component.getName() != null)
					{
						component.setPreferredSize(new Dimension(00,70));
					}
				}
				container.revalidate();
				tabbedPane.setSelectedIndex(0);
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		toolBar.add(btnHepsiYukari );

		JButton btnHepsiAsagi = new JButton();
		btnHepsiAsagi.setToolTipText("Gorev Paneli Asagi");
		btnHepsiAsagi.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/down.png")));
		btnHepsiAsagi .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Component[] components = container.getComponents();
				for (Component component : components) {
					if(component.getName() != null)
					{
						component.setPreferredSize(new Dimension(00,175));
					}
				}
				container.revalidate();
				tabbedPane.setSelectedIndex(0);
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		toolBar.add(btnHepsiAsagi );
		
		Component horizontalGlue = Box.createVerticalGlue();
		toolBar.add(horizontalGlue);
		
		JButton btnkapat= new JButton("");
		btnkapat.setToolTipText("Kapat");
		btnkapat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				}
		});
		btnkapat.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/exit.png")));
		toolBar.add(btnkapat);

//*********************************************************************************
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		final boolean showTabsHeader = false; tabbedPane.setUI(new
				javax.swing.plaf.metal.MetalTabbedPaneUI() {
			@Override protected int calculateTabAreaHeight(int tabPlacement, int
					horizRunCount, int maxTabHeight) { if (showTabsHeader) {return
							super.calculateTabAreaHeight(tabPlacement, horizRunCount, maxTabHeight); }
					else {return 0;} } protected void paintTabArea(Graphics g,int
							tabPlacement,int selectedIndex){} });

		splitPane.setRightComponent(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Gorevler", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));

		uplpnl = new UploadPanel();
		uplpnl.setPreferredSize(new Dimension(0,00));
		panel_1.add(uplpnl, BorderLayout.SOUTH);

		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		//**************************************************************************************
		container = new JPanel(); 
		scrollPane.setViewportView(container);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		//container.setLayout(new GridLayout(4, 1, 5, 5));
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Yeni Gorev", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));

		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel_3.add(tabbedPane_1, BorderLayout.CENTER);


		JPanel altPane = new JPanel();
		altPane.setPreferredSize(new Dimension(0,25));
		altPane.setLayout(null);
		contentPane.add(altPane, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("Emir Sayisi");
		lblNewLabel.setBounds(10, 5, 75, 14);
		altPane.add(lblNewLabel);

		lblemirSAYI = new JLabel("0");
		lblemirSAYI.setBounds(95, 5, 48, 14);
		altPane.add(lblemirSAYI);


		//		if (list.getModel().getSize() != 0) 
		//		{
		//			for (int i =0; i< list.getModel().getSize(); i++)
		//			{
		//				CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
		//				
		//				System.out.println(item.isSelected + "=="+    item.surucu() + "=="+item.toString());
		//			//	System.out.println(item.surucu() + "=="+list.getModel().getElementAt(i).toString());
		//			} 
		//		}



		emirAnaGirisPanel = new EmirAnaGiris();
		tabbedPane_1.addTab("Yeni Emir", null,emirAnaGirisPanel, null);
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
		loglamaPanel = new LoglamaRapor();
		tabbedPane.addTab("Loglama", null, loglamaPanel, null);
		kayitliEmirlerPanelEmirler = new KayitliEmirler();
		tabbedPane.addTab("Emirler", null, kayitliEmirlerPanelEmirler, null);
		//***********************************************************************************
		try {
			glb.backup_surucu_kontrol();
			emir_yukle("EMIR_ISMI") ;

			jobTimerBasla();
		} catch (Exception e1) {

		}

	}
	private void jobTimerBasla()
	{
		timerr = new Timer();  
		TimerTask tt = new TimerTask() {  
			@Override  
			public void run() {  
				try {
					yEDEKLE();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};  
		};  
		timerr.scheduleAtFixedRate(tt, 1000, 1000);
	}
	private void yEDEKLE() throws ClassNotFoundException, SQLException
	{
		String hataEMIR = "";
		try
		{
			timerr.cancel();;
			for (int g = 0; g <= gorevLER.size()-1; g++)
			{
				String eISMI = gorevLER.get(g);
				hataEMIR = eISMI;
				for (int i = 0; i <= gorevLER.size() - 1; i++)
				{
					if (gorevLER.get(i).equals(eISMI))
					{
						gorevLER.remove(eISMI);
					}
					if (gorevLER.size() == 0)
					{
						break;
					}
				}
				Thread.sleep(1000);
				List<emir_bilgiler> ebilgiler = bckp.emir_tek(eISMI);
				boolean SQL_YEDEK_MI;
				SQL_YEDEK_MI = ebilgiler.get(0).isSQL_YEDEK();
				if (SQL_YEDEK_MI == false)
				{
					diger_dosya(eISMI, ebilgiler.get(0).getEMIR_ACIKLAMA());   // DIGER DOSYA 
				}
				else
				{
					yedekLEE_SQL(eISMI); // Ms Sql ve My Sql
				}
			}
			jobTimerBasla();
		}
		catch (Exception ex)
		{
			bckp.log_kayit(hataEMIR, new Date(), ex.getMessage());
			jobTimerBasla();
		}
	}
	public static void genelKayit() throws ClassNotFoundException, SQLException
	{
		if (emirAnaGirisPanel.txtEmir.getText().toString().equals("")) return;
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
			mesaj_goster(5000,Notifications.Type.WARNING, "Yedekleme Icin Gun secilmediginden " + System.lineSeparator() + System.lineSeparator()  + "Emir durumu Pasiv olarak Degistirildi");
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
				bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText(), new Date(), "Emir Islemi Kaydedildi...");
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
			if (emirBilgiler.size() == 0 ) {  
				kontrol = false;
			}
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
			{
				bckp.genel_kayit(emirAnaGirisPanel.txtEmir.getText(), emirAnaGirisPanel.chckbxDurum.isSelected(),emirAnaGirisPanel. textAciklama.getText(), emirAnaGirisPanel.lblNewLabel_6.getText(), sondurum, sonyuk,emirAnaGirisPanel. chckbxServerDosya.isSelected(), mesaj, ilkkayit);
			}
			else
			{
				bckp.genel_kayit(emirAnaGirisPanel.txtEmir.getText(),emirAnaGirisPanel. chckbxDurum.isSelected(),emirAnaGirisPanel. textAciklama.getText(), emirAnaGirisPanel.lblNewLabel_6.getText(), false, sonyuk, emirAnaGirisPanel. chckbxServerDosya.isSelected(), mesaj, ilkkayit);
			}

			//contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		catch (Exception ex)
		{
			// contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText(), new Date(), ex.getMessage());
			mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
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
				mesaj_goster(5000,Notifications.Type.WARNING, "Yedekleme Icin Gun secilmediginden " + System.lineSeparator() + System.lineSeparator()  + "Emir durumu Pasiv olarak Degistirildi");
			}

			Date date = (Date) (yedekaraligiPanel.timeBaslangic.getValue());
			Date date2 = (Date) (yedekaraligiPanel.timeBitis.getValue());
			date2.setYear(date.getYear());
			date2.setMonth(date.getMonth());
			date2.setDate(date.getDate());
			if (date.after(date2) )
			{
				mesaj_goster(5000,Notifications.Type.WARNING,  "Bitis Zamani Baslangic Zamanindan Kucuk olamaz");
				return;
			}
			bckp.yedekleme_kayit_sil(emirAnaGirisPanel.txtEmir.getText());
			bckp.yedekleme_ismi_kayit(emirAnaGirisPanel.txtEmir.getText(), yedekaraligiPanel.textHerDakka.getText(), yedekaraligiPanel.chckbxPtesi.isSelected(),
					yedekaraligiPanel.chckbxSali.isSelected(), 
					yedekaraligiPanel.chckbxCarsamba.isSelected(), 
					yedekaraligiPanel.chckbxPersembe.isSelected(),
					yedekaraligiPanel.chckbxCuma.isSelected(),
					yedekaraligiPanel.chckbxCumartesi.isSelected(),
					yedekaraligiPanel.chckbxPazar.isSelected(), date, date2);
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText(), new Date(), "Emir Yedekleme Bilgileri  Kaydedildi...");
			tabbedPane_1.setSelectedIndex(1); 
		}
		catch (Exception ex)
		{
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText(), new Date(), ex.getMessage());
			mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());	
		}
	}
	public static void BilgilendirmeKaydet() throws ClassNotFoundException, SQLException
	{
		try
		{
			bckp.bilgilendirme_kayit_sil(emirAnaGirisPanel.txtEmir.getText());
			SIFRE_DONDUR sdon = new SIFRE_DONDUR();
			String response =sdon.sDONDUR(bilgilendirmePanel.textSifre);
			bckp.bilgilendirme_ismi_kayit(emirAnaGirisPanel.txtEmir.getText(),bilgilendirmePanel.chckbxAktifPasif.isSelected() ,
					bilgilendirmePanel.chckbxIslem.isSelected(), bilgilendirmePanel.chckbxHata.isSelected(), 
					bilgilendirmePanel.textGonIsim.getText(), bilgilendirmePanel.textGonHesap.getText() , bilgilendirmePanel.textAlici.getText(), bilgilendirmePanel.textKonu.getText(), bilgilendirmePanel.textSmtp.getText(), bilgilendirmePanel.textPort.getText(),bilgilendirmePanel.textKull.getText(), response, bilgilendirmePanel.chckbxSSL.isSelected(), bilgilendirmePanel.chckbxTSL.isSelected());
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText(), new Date(), "Bilgilendirme Bilgileri  Kaydedildi...");
			tabbedPane_1.setSelectedIndex(0);
		}
		catch (Exception ex)
		{
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText(), new Date(), ex.getMessage());
			mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());			 }
	}
	public static void sunucuKaydet() throws ClassNotFoundException, SQLException
	{
		try
		{
			if (emirAnaGirisPanel.txtEmir.getText().toString().equals("")) return;
			bckp.ftp_kayit_sil(emirAnaGirisPanel.txtEmir.getText().toString());
			String neresi = "";
			if ( sunucuayarPanel.chckbxFtp.isSelected())
			{
				neresi = "FTP";
			}
			else
			{
				neresi = "SUR";
			}
			SIFRE_DONDUR sdon = new SIFRE_DONDUR();
			bckp.ftp_ismi_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), sunucuayarPanel.textHost.getText(), sunucuayarPanel.textKull.getText(), sdon.sDONDUR(sunucuayarPanel.textSifre), sunucuayarPanel.textFtpSurucu.getText(),  sunucuayarPanel.textPort.getText(),sunucuayarPanel.textZmnasm.getText().equals("") ? 0 : Integer.parseInt( sunucuayarPanel.textZmnasm.getText())  , sunucuayarPanel.textEskisilme.getText(), neresi,  sunucuayarPanel.textSurucu.getText());
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(),new Date(), "Emir FTP Bilgileri  Kaydedildi...");
			tabbedPane_1.setSelectedIndex(1);
		}
		catch (Exception ex)
		{
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), ex.getMessage());
			mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		}

	}
	public static void MS_Server_Kayit() throws ClassNotFoundException, SQLException
	{
		if (emirAnaGirisPanel.txtEmir.getText().toString().equals(""))
		{
			mesaj_goster(5000,Notifications.Type.ERROR, "Emir Adi Bos Olamaz");
			return;
		}
		SIFRE_DONDUR sdon = new SIFRE_DONDUR();
		String response =sdon.sDONDUR(serverBilgileriPanel.textMSsifre);

		bckp.MsSql_baglan( serverBilgileriPanel.textMSServer.getText() ,serverBilgileriPanel.textMSkull.getText(),response,serverBilgileriPanel.textMSPort.getText());
		ResultSet rs;
		rs = bckp.db_ismi();
		emirAnaGirisPanel.list.removeAll();
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
		bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), "Veritabani Isimleri yuklendi...");
		emirAnaGirisPanel.lblNewLabel_6.setText("Ms Sql");
		try
		{
			bckp.server_kayit_sil(emirAnaGirisPanel.txtEmir.getText().toString());
			bckp.server_ismi_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), serverBilgileriPanel.textMSServer.getText(), true, true, serverBilgileriPanel.textMSkull.getText(), response, "Ms Sql", "","");
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), "SQL SERVER Instance Bilgileri Kaydedildi...");
			bckp.instance_update(emirAnaGirisPanel.txtEmir.getText().toString(), "Ms Sql");
			tabbedPane_1.setSelectedIndex(0);
		}
		catch (Exception ex)
		{
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), ex.getMessage());
			mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());		
		}
	}
	public static void MY_Server_Kayit() throws ClassNotFoundException, SQLException
	{
		if (emirAnaGirisPanel.txtEmir.getText().toString().equals(""))
		{
			mesaj_goster(5000,Notifications.Type.ERROR, "Emir Adi Bos Olamaz");
			return;
		}
		SIFRE_DONDUR sdon = new SIFRE_DONDUR();
		String response =sdon.sDONDUR(serverBilgileriPanel.textMySifre);

		bckp.MySql_baglan( serverBilgileriPanel.textMykull.getText(),response,serverBilgileriPanel.textMYPort.getText());
		ResultSet rs;
		rs = bckp.db_ismiMySql();
		emirAnaGirisPanel.list.removeAll();
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
		bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), "Veritabani Isimleri yuklendi...");
		emirAnaGirisPanel.lblNewLabel_6.setText("My Sql");
		try
		{
			bckp.server_kayit_sil(emirAnaGirisPanel.txtEmir.getText().toString());
			bckp.server_ismi_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), "localhost", true, true, serverBilgileriPanel.textMykull.getText(), response, "My Sql",  serverBilgileriPanel.textMYPort.getText(),
					serverBilgileriPanel.textMyDump.getText());
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), "MY SQL SERVER Instance Bilgileri Kaydedildi...");
			bckp.instance_update(emirAnaGirisPanel.txtEmir.getText().toString(), "My Sql");
			tabbedPane_1.setSelectedIndex(1);
		}
		catch (Exception ex)
		{
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), ex.getMessage());
			mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());		
		}
	}
	public void emir_yukle(String siralama) throws ClassNotFoundException, SQLException
	{
		//System.out.println("==="+siralama);
		try
		{
			container.removeAll();
			container.revalidate();
			container.repaint();
			// DURUM DESC , EMIR_ISMI 
			///

			List<emir_bilgiler> emirliste = bckp.emir_liste(siralama);
			if (emirliste.size() == 0 ) {  
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			else {
				int toplam = emirliste.size();
				for (int i = 0; i<=  emirliste.size() -1 ; i++)
				{
					emirTEKYUKLE( emirliste.get(i).getEMIR_ISMI(),"ana");
					
				}
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				//				int yuk = 175;
				//				switch(toplam) {
				//				case 1:
				//					horizontalGlue = Box.createVerticalStrut(625 - yuk);
				//					break;
				//				case 2:
				//					horizontalGlue = Box.createVerticalStrut(625 - (yuk * 2));
				//					break;
				//				case 3:
				//					horizontalGlue = Box.createVerticalStrut(625 - (yuk *3));
				//					break;
				//				case 4:
				//					horizontalGlue = Box.createVerticalStrut(625 - (yuk *4));
				//					break; 
				//				default:
				//					
				//				}
				horizontalGlue = Box.createVerticalStrut(555);
				container.add(horizontalGlue);
			}
		}
		catch (Exception ex)
		{
			contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), ex.getMessage());
			mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());	
		}
	}
	public void emirTEKYUKLE(String eismi,String nerden)
	{
		if (eismi.equals("")) return;
		gOREV_TAKIP emir = new gOREV_TAKIP(eismi);
		emir.setName(eismi);
		emir.lblemirISMI.setText(eismi);
		container.add(emir);
		if(!nerden.equals("ana"))
		{
			horizontalGlue = Box.createVerticalStrut(555);
			container.add(horizontalGlue);
		}
		emirSAYI_COUNT();
	}
	private static void emirSAYI_COUNT()
	{
		Component[] components = container.getComponents();
		int say = 0 ;
		for (Component component : components) {
			say +=1 ;			
		}
		lblemirSAYI.setText(Integer.toString(say));
	}
	private void emirtekSIL_HATA(String eadi)
	{
		Component[] components = container.getComponents();
		for (Component component : components) 
		{
			if(component.getName()!= null)
			{
				if (component.getName().toString().equals(eadi)) 
				{
					container.remove(component);
				}
			}
			else if(component.getName()== null)
			{
				container.remove(component);
			}
		}
		container.revalidate();
		container.repaint();
		emirTEKYUKLE(eadi,"");

	}
	public static void emirSIL(String eadi) throws ClassNotFoundException, SQLException
	{
		Component[] components = container.getComponents();
		for (Component component : components) 
		{
			if(component.getName()!= null)
			{
				if (component.getName().toString().equals(eadi)) 
				{
					container.remove(component);
				}
			}
		}
		container.repaint();
		emirSAYI_COUNT();

		try
		{
			//   timer1.Stop();
			//   timer1.Dispose();

			bckp.log_kayit(eadi, new Date(), "Emir Silme Islemine Baslandi...");
			bckp.genel_kayit_sil(eadi);
			bckp.db_adi_kayit_sil(eadi);
			bckp.ftp_kayit_sil(eadi);
			bckp.bilgilendirme_kayit_sil(eadi);
			bckp.yedekleme_kayit_sil(eadi);
			bckp.server_kayit_sil(eadi);
			bckp.diger_dosya_adi_kayit_sil(eadi);
			bckp.log_kayit(eadi, new Date(), "Emir Islemi Silindi...");

			for (int gg = 0; gg < gorevLER.size() ; gg++)
			{
				if (gorevLER.get(gg).toString() == eadi)
				{
					gorevLER.remove(gg);
				}
			}
		}
		catch (Exception ex)
		{
			bckp.log_kayit(eadi,new Date(), ex.getMessage());
			mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());			
		}
	}
	public static void gorevYUKARI(String eadi) {
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
	public static void gorevASAGI(String eadi) {
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

	public static void mesaj_goster(int zaman, Notifications.Type tipType , String mesaj)
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
	private void yedekLEE_SQL(String emirADI) throws ClassNotFoundException, SQLException, ParseException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NumberFormatException, SocketException, IOException, InterruptedException
	{
		uplpnl.setPreferredSize(new Dimension(0,100));
		uplpnl.setMaximumSize(new Dimension(0,100));
		uplpnl.revalidate();
		List<emir_bilgiler> emirBilgi = new ArrayList<emir_bilgiler>();

		emirBilgi = bckp.emir_tek(emirADI);
		///
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
		else {
			SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
			Date tarih = df.parse(emirBilgi.get(0).getSON_YUKLEME().toString());
			sonyuk = tarih ; 
		}
		bckp.genel_kayit_durum(emirADI, false, sonyuk, "");// Herhalukarda islem baslangici yedeklenemdi notu
		List<String> dbliste = bckp.db_liste(emirADI);

		if (dbliste.size() == 0)
		{
			bckp.log_kayit(emirADI, new Date(), "Yuklenecek Dosya Secilmemis....");
			bckp.genel_kayit_durum(emirADI, false, sonyuk, "Yuklenecek Dosya Secilmemis....");

			uplpnl.setVisible(false);
			//emirBOSALT(emirADI);
			bckp.log_kayit(emirADI, new Date(), "Dosya Secilmemis...");
			bckp.log_kayit(emirADI, new Date(), "Emir Yuklendi...");
			emir_yukle("EMIR_ISMI") ;
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
				bckp.log_kayit(emirADI, new Date(), "FTP Surucu Secilmemis....");
				bckp.genel_kayit_durum(emirADI, false, sonyuk, "FTP Surucu Secilmemis....");
				uplpnl.setVisible(false);
				bckp.log_kayit(emirADI, new Date(), "FTP Surucu Secilmemis....");
				bckp.log_kayit(emirADI, new Date(), "Emir Yuklendi...");
				mesaj_goster(5000,Notifications.Type.WARNING, "FTP Surucu Secilmemis....");	
				emir_yukle("EMIR_ISMI") ;
				return;
			}
			sql_ftp_yukleme(emirADI,ftpBilgi);
		}
		else 
		{
			sql_yerel_surucu( emirADI  ,ftpBilgi);
		}

	}
	private void sql_yerel_surucu( String emirADI,     List<ftp_bilgiler> ftpBilgi) 
	{
		//		Runnable runner = new Runnable()
		//		{ 
		//			public void run() {
		/////
		try {
			///
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			List<server_bilgiler> serverBilgi = new ArrayList<server_bilgiler>();
			serverBilgi = bckp.server_bilgi(emirADI);
			String decodedString = serverBilgi.get(0).getSIFRE();
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) {
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			String ftp, kull, sifre, surucu, port, neresi, surucu_yer;
			int eskiyedek, zmnasimi;
			eskiyedek =  Integer.valueOf(ftpBilgi.get(0).getESKI_YEDEK());
			zmnasimi = Integer.valueOf(ftpBilgi.get(0).getZMN_ASIMI());
			surucu_yer =ftpBilgi.get(0).getSURUCU_YER();
			String sqlsifre = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
			if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
			{
				bckp.MsSql_baglan(serverBilgi.get(0).getINSTANCE() ,serverBilgi.get(0).getKULLANICI(),sqlsifre,serverBilgi.get(0).getPORT());
			}
			else  //My sql
			{
				bckp.MySql_baglan( serverBilgi.get(0).getKULLANICI(),sqlsifre,serverBilgi.get(0).getPORT());   
			}
			String tarr =  TARIH_CEVIR.tarihddMMyyyyHHmm(new Date());
			String dosADI = "";
			bckp.log_kayit(emirADI, new Date(), "Yedeklemeye Baslandi....");
			List<String> dbliste = bckp.db_liste(emirADI);
			uplpnl.RPB1.setMaximum(dbliste.size());
			uplpnl.RPB1.setStringPainted(true);
			for (int i = 0; i <= dbliste.size() - 1; i++)
			{
				dosADI = dbliste.get(i); // Dosya Adi
				uplpnl.Progres_Bar_1( i + 1);
				if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
				{
					bckp.backup_al(dosADI, tarr + "_" + dosADI);
				}
				else
				{
					bckp.mySQL_backup(serverBilgi.get(0).getMY_DUMP(), serverBilgi.get(0).getKULLANICI(),
							sqlsifre,dosADI, glb.BACKUP_YERI + tarr + "_" + dosADI + ".sql");
				}
				bckp.log_kayit(emirADI, new Date(), dosADI + " Backup Alindi...");
				String dosya, dzip;
				if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
				{
					dosya = tarr + "_" + dosADI + ".bak";
				}
				else
				{
					dosya = tarr + "_" + dosADI + ".sql";
				}
				dzip = tarr + "_" + dosADI + ".zip";
				bckp.zip_yap(dosya, glb.BACKUP_YERI, dzip, false, "");
				bckp.log_kayit(emirADI, new Date(), dosADI + " Zip Haline Getirildi...");
				File tmpDir = new File(ftpBilgi.get(0).getSURUCU_YER());
				boolean exists = tmpDir.exists();
				if (!exists)
				{
					tmpDir.mkdirs();
				}
				if (glb.dos_kontrol(surucu_yer + "\\" + dzip))
				{ 
					glb.dos_sil(surucu_yer + "\\" + dzip);
				}
				File okunanFile = new File(glb.BACKUP_YERI + dzip);
				okunanFile.renameTo(new File(surucu_yer + "\\" + dzip));
				if (glb.dos_kontrol(glb.BACKUP_YERI + dzip))
				{ 
					glb.dos_sil(glb.BACKUP_YERI + dzip);
				}
				bckp.log_kayit(emirADI, new Date(), dosADI + " Surucuye Yuklendi...");
				if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
				{
					if (glb.dos_kontrol(glb.BACKUP_YERI + tarr + "_" + dosADI + ".bak"))
					{ 
						glb.dos_sil(glb.BACKUP_YERI + tarr + "_" + dosADI + ".bak");
						bckp.log_kayit(emirADI, new Date(), dosADI + " BAK Dosyasi Silindi...");
					}
				}
				else
				{
					if (glb.dos_kontrol(glb.BACKUP_YERI + tarr + "_" + dosADI + ".sql"))
					{ 
						glb.dos_sil(glb.BACKUP_YERI + tarr + "_" + dosADI + ".sql");
						bckp.log_kayit(emirADI, new Date(), dosADI + " BAK Dosyasi Silindi...");
					}
				}
			}
			Date nowwDate = new Date();
			durumYAZ(emirADI,nowwDate);					
			bckp.genel_kayit_durum(emirADI, true, nowwDate, "Yedeklendi.....");
			if (eskiyedek > 0) // **************SURUCU ESKILERI SIL
			{
				uplpnl.Progres_Bar_Temizle_1();
				uplpnl.Progres_Bar_Temizle_2();
				dosADI = "";
				int gunfark;
				List<remote_filelist> ls = new ArrayList<remote_filelist>();
				ls = bckp. sur_liste(surucu_yer);
				uplpnl.RPB1.setMaximum(dbliste.size());
				uplpnl.RPB1.setStringPainted(true);
				uplpnl.RPB2.setMaximum(ls.size());
				uplpnl.RPB2.setStringPainted(true);
				for (int i = 0; i <= dbliste.size() - 1; i++)
				{

					dosADI = dbliste.get(i);
					uplpnl. Progres_Bar_1( i + 1);
					for (int r = 0; r <= ls.size() - 1; r++)
					{
						uplpnl.  Progres_Bar_2( r + 1);
						String ftpDOSYA = ls.get(r).getDosyaADI();
						boolean found = ftpDOSYA.contains(dosADI);
						if (found)
						{
							SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
							BasicFileAttributes attr;
							Path path = Paths.get(ls.get(r).getFilePATH()+"\\"+ ls.get(r).getDosyaADI().toString());
							attr = Files.readAttributes(path, BasicFileAttributes.class);
							String dateInString = attr.creationTime().toString().substring(8,10) + "." +  attr.creationTime().toString().substring(5,7) +"."+attr.creationTime().toString().substring(0,4)  ;
							Date ftar = formatter.parse(dateInString);
							long dateBeforeInMs = ftar.getTime();
							long dateAfterInMs = new Date().getTime();
							long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
							long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
							//long daysDiff1 = TimeUnit.HOURS.convert(timeDiff, TimeUnit.MILLISECONDS);
							gunfark = (int)daysDiff;
							if (gunfark > eskiyedek)
							{
								bckp.log_kayit(emirADI, new Date(),ls.get(r).getDosyaADI() + " Surucuye Silmeye Gitti...");
								if (glb.dos_kontrol(ls.get(r).getDosyaADI()))
								{ 
									glb.dos_sil(ls.get(r).getDosyaADI());
									bckp.log_kayit(emirADI, new Date(), dosADI + " Dosyasi Silindi...");
								}


								bckp.log_kayit(emirADI, new Date(), dosADI + " Dosya Surucuden Eski Tarihli Silindi...");
							}
						}
					}
				}
			}
			yapildiMAILI(emirADI);
			uplpnl.setPreferredSize(new Dimension(0,00));
			uplpnl.setMaximumSize(new Dimension(0,0));
			uplpnl.revalidate();
			//emirBOSALT(emirADI);
			//emirTEKYUKLE(emirADI,"");
			emirYENIDENBASLAT(emirADI);
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		catch (Exception ex)
		{

			try {
				bckp.genel_kayit_durum(emirADI, false, new Date(), ex.getMessage().substring(0, 40).toString());

				bckp.log_kayit(emirADI, new Date(), ex.getMessage());
				uplpnl. Progres_Bar_Temizle_1();
				uplpnl. Progres_Bar_Temizle_2();
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();

				yapilmadiMAILI( emirADI);
				//hataDURUMUNDA(emirADI);

				bckp.log_kayit(emirADI, new Date(), "Hata Durumundan Emir Bosaldi...");
				emirtekSIL_HATA(emirADI);

				//emirBOSALT(emirADI);
				//emirTEKYUKLE(emirADI,"");
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			} catch (Exception e) {
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				e.printStackTrace();
			}
		} 
		//////
		//			}
		//		};
		//		Thread t = new Thread(runner, "Code Executer");
		//		t.start();
	}
	private void sql_ftp_yukleme(String emirADI,List<ftp_bilgiler> ftpBilgi) 
	{
		//Runnable runner = new Runnable()
		//{ 
		//	public void run() {
		//////////////////////////
		try {
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			String ftp, kull, sifre, surucu, port, neresi, surucu_yer;
			int eskiyedek, zmnasimi;
			ftp = ftpBilgi.get(0).getHOST();
			kull = ftpBilgi.get(0).getKULLANICI();

			String decodedString = ftpBilgi.get(0).getSIFRE();
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) {
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			sifre = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
			surucu = ftpBilgi.get(0).getSURUCU();
			port = ftpBilgi.get(0).getPORT();
			eskiyedek =  Integer.valueOf(ftpBilgi.get(0).getESKI_YEDEK());
			zmnasimi = Integer.valueOf(ftpBilgi.get(0).getZMN_ASIMI());
			neresi =ftpBilgi.get(0).getNERESI();
			surucu_yer =ftpBilgi.get(0).getSURUCU_YER();

			uplpnl.lblSurucu.setText( ftp + "\\" + surucu.replace("/", "\\"));

			if (glb.internet_kontrol() == false)
			{
				bckp.genel_kayit_durum(emirADI, false, new Date(), "Yedeklenmedi Internet Baglantisi Yok...");
				bckp.log_kayit(emirADI, new Date(), "Yedeklenmedi Internet Baglantisi Yok...");
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				bckp.log_kayit(emirADI, new Date(), "Internet Baglantisi Yok...");
				emirtekSIL_HATA(emirADI);
				bckp.log_kayit(emirADI, new Date(), "Emir Yuklendi...");
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			boolean result =   bckp.DoesFtpDirectoryExist(ftp ,surucu,Integer.valueOf(port),kull, sifre);
			if (result == false)
			{
				bckp.genel_kayit_durum(emirADI, false, new Date(), "Yedeklenmedi FTP Surucu Bulunamadi...");
				bckp.log_kayit(emirADI, new Date(), "Yedeklenmedi FTP Surucu Bulunamadi...");
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				bckp.log_kayit(emirADI, new Date(), "FTP Surucu Bulunamadi...");
				emirtekSIL_HATA(emirADI);
				bckp.log_kayit(emirADI, new Date(), "Emir Yuklendi...");
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
			{
				bckp.MsSql_baglan(serverBilgi.get(0).getINSTANCE() ,serverBilgi.get(0).getKULLANICI(),sqlsifre,serverBilgi.get(0).getPORT());
			}
			else  //My sql
			{
				bckp.MySql_baglan( serverBilgi.get(0).getKULLANICI(),sqlsifre,serverBilgi.get(0).getPORT());   
			}
			String tarr =  TARIH_CEVIR.tarihddMMyyyyHHmm(new Date());
			bckp.log_kayit(emirADI, new Date(), "Yedeklemeye Baslandi....");
			String dosADI = "";
			List<String> dbliste = bckp.db_liste(emirADI);
			uplpnl.RPB1.setMaximum(dbliste.size());
			uplpnl.RPB1.setStringPainted(true);
			for (int i = 0; i <= dbliste.size() - 1; i++)
			{
				dosADI = dbliste.get(i); // Dosya Adi
				uplpnl.Progres_Bar_1( i + 1);
				if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
				{
					bckp.backup_al(dosADI, tarr + "_" + dosADI);
				}
				else
				{
					bckp.mySQL_backup(serverBilgi.get(0).getMY_DUMP(), serverBilgi.get(0).getKULLANICI(),
							sqlsifre,dosADI, glb.BACKUP_YERI + tarr + "_" + dosADI + ".sql");
				}
				bckp.log_kayit(emirADI, new Date(), dosADI + " Backup Alindi...");
				String dosya, dzip;
				if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
				{
					dosya = tarr + "_" + dosADI + ".bak";
				}
				else
				{
					dosya = tarr + "_" + dosADI + ".sql";
				}
				dzip = tarr + "_" + dosADI + ".zip";

				bckp.zip_yap(dosya, glb.BACKUP_YERI, dzip, false, "");
				bckp.log_kayit(emirADI, new Date(), dosADI + " Zip Haline Getirildi...");
				UploadFTPFiles( ftp, surucu, glb.BACKUP_YERI, tarr + "_" + dosADI + ".zip", kull, sifre, port, zmnasimi);

				bckp.log_kayit(emirADI, new Date(), dosADI + " FTP Yuklendi...");

				if( serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
				{
					File tmpDir = new File(glb.BACKUP_YERI + tarr + "_" + dosADI + ".bak");
					boolean exists = tmpDir.exists();
					if(exists)
						tmpDir.delete();
					bckp.log_kayit(emirADI, new Date(), dosADI + " BAK Dosyasi Silindi...");
				}
				else
				{
					File tmpDir = new File(glb.BACKUP_YERI + tarr + "_" + dosADI + ".sql");
					boolean exists = tmpDir.exists();
					if(exists)
						tmpDir.delete();
					bckp.log_kayit(emirADI, new Date(), dosADI + ".sql" + " Dosyasi Silindi...");
				}

				File tmpDir = new File(glb.BACKUP_YERI + tarr + "_" + dosADI + ".zip");
				boolean exists = tmpDir.exists();

				if(exists)
					tmpDir.delete();
				bckp.log_kayit(emirADI, new Date(), dosADI + " ZIP Dosyasi Silindi...");
			}
			Date nowwDate = new Date();
			durumYAZ(emirADI,nowwDate);		

			bckp.genel_kayit_durum(emirADI, true,nowwDate, "Yedeklendi.....");
			if (eskiyedek > 0) // **************FTP ESKILERI SIL
			{
				uplpnl.Progres_Bar_Temizle_1();
				uplpnl.Progres_Bar_Temizle_2();
				dosADI = "";
				int gunfark;
				List<remote_filelist> ls = new ArrayList<remote_filelist>();
				ls = bckp.ListRmtFiles( ftp , surucu, kull, sifre);
				uplpnl.RPB1.setMaximum(dbliste.size());
				uplpnl.RPB1.setStringPainted(true);
				uplpnl.RPB2.setMaximum(ls.size());
				uplpnl.RPB2.setStringPainted(true);
				for (int i = 0; i <= dbliste.size() - 1; i++)
				{
					dosADI = dbliste.get(i);
					uplpnl. Progres_Bar_1( i + 1);
					for (int r = 0; r <= ls.size() - 1; r++)
					{
						uplpnl.  Progres_Bar_2( r + 1);
						String ftpDOSYA = ls.get(r).getDosyaADI();
						boolean found = ftpDOSYA.contains(dosADI);
						if (found)
						{
							SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
							String dateInString = ls.get(r).getTaRIH().substring(8,10) +"."+ls.get(r).getTaRIH().substring(5,7) +"."+ ls.get(r).getTaRIH().substring(0,4);
							Date ftar = formatter.parse(dateInString);
							long dateBeforeInMs = ftar.getTime();
							long dateAfterInMs = new Date().getTime();
							long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
							long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
							gunfark = (int)daysDiff;
							if (gunfark > eskiyedek)
							{
								bckp.log_kayit(emirADI, new Date(), ls.get(r) + " FTP ye Silmeye Gitti...");
								bckp.ftp_sil( ftp, surucu, ls.get(r).getDosyaADI(), kull, sifre, port);
								bckp.log_kayit(emirADI, new Date(), dosADI + " Dosya FTP Eski Tarihli Silindi...");
							}
						}
					}
				}
			}
			yapildiMAILI(emirADI);
			uplpnl.setPreferredSize(new Dimension(0,00));
			uplpnl.setMaximumSize(new Dimension(0,0));
			uplpnl.revalidate();

			//emirBOSALT(emirADI);
			//emirTEKYUKLE(emirADI,"");
			emirYENIDENBASLAT(emirADI);
			////
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		catch (Exception ex)
		{
			try {
				bckp.genel_kayit_durum(emirADI, false, new Date(), ex.getMessage().toString().length() > 40 ?  ex.getMessage().substring(0, 40).toString(): ex.getMessage());

				bckp.log_kayit(emirADI, new Date(), ex.getMessage());
				uplpnl. Progres_Bar_Temizle_1();
				uplpnl. Progres_Bar_Temizle_2();
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();

				yapilmadiMAILI( emirADI);

				bckp.log_kayit(emirADI, new Date(), "Hata Durumundan Emir Bosaldi...");
				emirtekSIL_HATA(emirADI);

				//emirBOSALT(emirADI);
				//emirTEKYUKLE(emirADI,"");
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			} catch (Exception e) {
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				e.printStackTrace();
			}
		} 
		//////
		//			}
		//		};
		//		Thread t = new Thread(runner, "Code Executer");
		//		t.start();
	}
	private void bilgilendirme_oku(String emir, String mesaj ,List<bilgilendirme_bilgiler> bilgiBilgi) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		if (bilgiBilgi.size() > 0)
		{
			mail_at( bilgiBilgi , mesaj);
		}
	}
	private void diger_dosya(String emirADI, String aciklama) throws ClassNotFoundException, SQLException, ParseException, InterruptedException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NumberFormatException, SocketException, IOException
	{
		uplpnl.setPreferredSize(new Dimension(0,100));
		uplpnl.setMaximumSize(new Dimension(0,100));
		uplpnl.revalidate();
		List<emir_bilgiler> emirBilgi = new ArrayList<emir_bilgiler>();

		emirBilgi = bckp.emir_tek(emirADI);
		///
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
		else {
			SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
			Date tarih = df.parse(emirBilgi.get(0).getSON_YUKLEME().toString());
			sonyuk = tarih ; 
		}
		bckp.genel_kayit_durum(emirADI, false, sonyuk, "");// Herhalukarda islem baslangici yedeklenemdi notu
		List<db_List> dbliste = bckp.diger_dosya_liste(emirADI);

		if (dbliste.size() == 0)
		{
			bckp.log_kayit(emirADI, new Date(), "Yuklenecek Dosya Secilmemis....");
			bckp.genel_kayit_durum(emirADI, false, sonyuk, "Yuklenecek Dosya Secilmemis....");
			uplpnl.setVisible(false);

			bckp.log_kayit(emirADI, new Date(), "Dosya Secilmemis...");
			bckp.log_kayit(emirADI, new Date(), "Emir Yuklendi...");
			emir_yukle("EMIR_ISMI") ;
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
				bckp.log_kayit(emirADI, new Date(), "FTP Surucu Secilmemis....");
				bckp.genel_kayit_durum(emirADI, false, sonyuk, "FTP Surucu Secilmemis....");
				uplpnl.setVisible(false);
				//emirBOSALT(emirADI);
				bckp.log_kayit(emirADI, new Date(), "FTP Surucu Secilmemis....");
				bckp.log_kayit(emirADI, new Date(), "Emir Yuklendi...");
				mesaj_goster(5000,Notifications.Type.WARNING, "FTP Surucu Secilmemis....");	
				emir_yukle("EMIR_ISMI") ;
				return;
			}
			diger_ftp_yukleme(emirADI,ftpBilgi);
		}
		else 
		{
			diger_yerel_surucu( emirADI ,ftpBilgi );
		}

	}
	private void diger_yerel_surucu(String emirADI,List<ftp_bilgiler> ftpBilgi )
	{
		//		Runnable runner = new Runnable()
		//		{ 
		//			public void run() {
		try {
			//
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			int eskiyedek;
			eskiyedek =  Integer.valueOf(ftpBilgi.get(0).getESKI_YEDEK());
			uplpnl.lblSurucu.setText( ftpBilgi.get(0).getSURUCU_YER().replace("/", "\\"));
			String tarr =  TARIH_CEVIR.tarihddMMyyyyHHmm(new Date());
			bckp.log_kayit(emirADI, new Date(), "Yedeklemeye Baslandi....");
			String dosADI = "";
			Boolean folderMI = false;
			List<db_List>  dbliste = bckp.diger_dosya_liste (emirADI);
			uplpnl.RPB1.setMaximum(dbliste.size());
			uplpnl.RPB1.setStringPainted(true);
			uplpnl.RPB2.setStringPainted(true);
			for (int i = 0; i <= dbliste.size() - 1; i++)
			{
				uplpnl. Progres_Bar_1( i + 1);
				dosADI = dbliste.get(i).getAdi();
				File file = new File(dbliste.get(i).getPath() +"\\"+dbliste.get(i).getAdi());
				boolean deGER = file.isDirectory(); // Check if it's a directory
				if (deGER)
				{
					folderMI = true;
				}
				else
				{
					folderMI = false;
				}
				uplpnl.Progres_Bar_1( i + 1);
				String dosya, dzip,  dpath,uzantisiz = "";
				String input = dosADI;
				int index = input.lastIndexOf(".");
				if (index >= 0)
				{
					uzantisiz = input.substring(0, index); // or index + 1 to keep slash
				}
				dosya = dbliste.get(i).getPath() + "\\" + dosADI;
				dpath = dbliste.get(i).getPath() + "\\";
				dzip = tarr + "_" + uzantisiz + ".zip";
				if (folderMI)
				{
					uplpnl.RPB2.setString("Zip Haline Getiriliyor..........");
					dzip = tarr + "_" + dbliste.get(i).getAdi() + ".zip";
					String okumadosyaadi = dbliste.get(i).getPath() +"\\"+dbliste.get(i).getAdi()+"\\";
					Path pathokuma = Paths.get(dbliste.get(i).getPath() +"\\"+dbliste.get(i).getAdi()); 
					Path pathyazma = Paths.get(glb.BACKUP_YERI, dzip); 
					bckp. zipFolder(pathokuma,pathyazma);
					uplpnl.RPB2.setString("");
				}
				else
				{
					String okumadosyaadi = dbliste.get(i).getPath() +"\\"+dbliste.get(i).getAdi();
					bckp.diger_zip_yap(okumadosyaadi, glb.BACKUP_YERI, dzip, false, "");
				}
				bckp.log_kayit(emirADI, new Date(), dosADI + " Zip Haline Getirildi...");

				File tmpDir = new File(ftpBilgi.get(0).getSURUCU_YER());
				boolean exists = tmpDir.exists();
				if (!exists)
				{
					tmpDir.mkdirs();
				}

				File okunanFile = new File(glb.BACKUP_YERI + dzip);
				okunanFile.renameTo(new File(ftpBilgi.get(0).getSURUCU_YER() + "\\" + dzip));
				if (glb.dos_kontrol( glb.BACKUP_YERI + "\\" + dzip))
				{ 
					glb.dos_sil( glb.BACKUP_YERI + "\\" + dzip);
				}
				bckp.log_kayit(emirADI, new Date(), dosADI + " Surucuye Yuklendi...");

				bckp.log_kayit(emirADI,new Date(), uzantisiz + " ZIP Dosyasi Silindi...");
			}
			Date nowwDate = new Date();
			durumYAZ(emirADI,nowwDate);
			bckp.genel_kayit_durum(emirADI, true,nowwDate, "Yedeklendi.....");

			if (eskiyedek > 0) // **************FTP ESKILERI SIL
			{
				uplpnl.Progres_Bar_Temizle_1();
				uplpnl.Progres_Bar_Temizle_2();
				dosADI = "";
				int gunfark;
				List<remote_filelist> ls = new ArrayList<remote_filelist>();
				ls = bckp. sur_liste(ftpBilgi.get(0).getSURUCU_YER());
				uplpnl.RPB1.setMaximum(dbliste.size());
				uplpnl.RPB1.setStringPainted(true);
				uplpnl.RPB2.setMaximum(ls.size());
				uplpnl.RPB2.setStringPainted(true);
				for (int i = 0; i <= dbliste.size() - 1; i++)
				{

					dosADI = dbliste.get(i).getAdi();
					uplpnl. Progres_Bar_1( i + 1);
					for (int r = 0; r <= ls.size() - 1; r++)
					{
						uplpnl.  Progres_Bar_2( r + 1);
						String ftpDOSYA = ls.get(r).getDosyaADI();
						boolean found = ftpDOSYA.contains(dosADI);
						if (found)
						{
							SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
							BasicFileAttributes attr;
							Path path = Paths.get(ls.get(r).getFilePATH()+"\\"+ ls.get(r).getDosyaADI().toString());
							attr = Files.readAttributes(path, BasicFileAttributes.class);
							String dateInString = attr.creationTime().toString().substring(8,10) + "." +  attr.creationTime().toString().substring(5,7) +"."+attr.creationTime().toString().substring(0,4)  ;
							Date ftar = formatter.parse(dateInString);
							long dateBeforeInMs = ftar.getTime();
							long dateAfterInMs = new Date().getTime();
							long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
							long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
							//long daysDiff1 = TimeUnit.HOURS.convert(timeDiff, TimeUnit.MILLISECONDS);
							gunfark = (int)daysDiff;
							if (gunfark > eskiyedek)
							{
								bckp.log_kayit(emirADI, new Date(),ls.get(r).getDosyaADI() + " Surucuye Silmeye Gitti...");
								if (glb.dos_kontrol(ls.get(r).getFilePATH()+"\\"+  ls.get(r).getDosyaADI()))
								{ 
									glb.dos_sil(ls.get(r).getFilePATH()+"\\"+ ls.get(r).getDosyaADI());
									bckp.log_kayit(emirADI, new Date(), dosADI + " Dosyasi Silindi...");
								}
								bckp.log_kayit(emirADI, new Date(), dosADI + " Dosya Surucuden Eski Tarihli Silindi...");
							}
						}
					}
				}
			}
			yapildiMAILI(emirADI);
			uplpnl.setPreferredSize(new Dimension(0,00));
			uplpnl.setMaximumSize(new Dimension(0,0));
			uplpnl.revalidate();
			//emirBOSALT(emirADI);
			//emirTEKYUKLE(emirADI,"");
			emirYENIDENBASLAT(emirADI);
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			////
		}
		catch (Exception ex)
		{
			try {
				bckp.genel_kayit_durum(emirADI, false, new Date(), ex.getMessage().toString().length() > 40 ?  ex.getMessage().substring(0, 40).toString(): ex.getMessage());

				bckp.log_kayit(emirADI, new Date(), ex.getMessage());
				uplpnl. Progres_Bar_Temizle_1();
				uplpnl. Progres_Bar_Temizle_2();
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();

				yapilmadiMAILI( emirADI);

				bckp.log_kayit(emirADI, new Date(), "Hata Durumundan Emir Bosaldi...");
				emirtekSIL_HATA(emirADI);

				//emirBOSALT(emirADI);
				//emirTEKYUKLE(emirADI,"");
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			} catch (Exception e) {
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				e.printStackTrace();
			}

		} 
		//////
		//			}
		//		};
		//		Thread t = new Thread(runner, "Code Executer");
		//		t.start();
	}
	private void diger_ftp_yukleme(String emirADI,List<ftp_bilgiler> ftpBilgi )
	{
		//		Runnable runner = new Runnable()
		//		{ 
		//			public void run() {
		try {
			//
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			String ftp, kull, sifre, surucu, port, neresi, surucu_yer;
			int eskiyedek, zmnasimi;
			ftp = ftpBilgi.get(0).getHOST();
			kull = ftpBilgi.get(0).getKULLANICI();

			String decodedString = ftpBilgi.get(0).getSIFRE();
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) {
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			sifre = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
			surucu = ftpBilgi.get(0).getSURUCU();
			port = ftpBilgi.get(0).getPORT();
			eskiyedek =  Integer.valueOf(ftpBilgi.get(0).getESKI_YEDEK());
			zmnasimi = Integer.valueOf(ftpBilgi.get(0).getZMN_ASIMI());
			neresi =ftpBilgi.get(0).getNERESI();
			surucu_yer =ftpBilgi.get(0).getSURUCU_YER();

			uplpnl.lblSurucu.setText( ftp + "\\" + surucu.replace("/", "\\"));

			if (glb.internet_kontrol() == false)
			{
				bckp.genel_kayit_durum(emirADI, false, new Date(), "Yedeklenmedi Internet Baglantisi Yok...");
				bckp.log_kayit(emirADI, new Date(), "Yedeklenmedi Internet Baglantisi Yok...");
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				bckp.log_kayit(emirADI, new Date(), "Internet Baglantisi Yok...");
				emirtekSIL_HATA(emirADI);
				bckp.log_kayit(emirADI, new Date(), "Emir Yuklendi...");
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			boolean result =   bckp.DoesFtpDirectoryExist(ftp ,surucu,Integer.valueOf(port),kull, sifre);
			if (result == false)
			{
				bckp.genel_kayit_durum(emirADI, false, new Date(), "Yedeklenmedi FTP Surucu Bulunamadi...");
				bckp.log_kayit(emirADI, new Date(), "Yedeklenmedi FTP Surucu Bulunamadi...");
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				bckp.log_kayit(emirADI, new Date(), "FTP Surucu Bulunamadi...");
				emirtekSIL_HATA(emirADI);
				bckp.log_kayit(emirADI, new Date(), "Emir Yuklendi...");
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				return;
			}

			String tarr =  TARIH_CEVIR.tarihddMMyyyyHHmm(new Date());
			bckp.log_kayit(emirADI, new Date(), "Yedeklemeye Baslandi....");

			String dosADI = "";
			Boolean folderMI = false;
			List<db_List>  dbliste = bckp.diger_dosya_liste (emirADI);


			uplpnl.RPB1.setMaximum(dbliste.size());
			uplpnl.RPB1.setStringPainted(true);
			//uplpnl.RPB2.setStringPainted(true);
			for (int i = 0; i <= dbliste.size() - 1; i++)
			{
				uplpnl. Progres_Bar_1( i + 1);
				dosADI = dbliste.get(i).getAdi();
				File file = new File(dbliste.get(i).getPath() +"\\"+dbliste.get(i).getAdi());
				boolean deGER = file.isDirectory(); // Check if it's a directory
				if (deGER)
				{
					folderMI = true;
				}
				else
				{
					folderMI = false;
				}
				uplpnl.Progres_Bar_1( i + 1);
				String dosya, dzip,  dpath,uzantisiz = "";
				String input = dosADI;
				int index = input.lastIndexOf(".");
				if (index >= 0)
				{
					uzantisiz = input.substring(0, index); // or index + 1 to keep slash
				}
				dosya = dbliste.get(i).getPath() + "\\" + dosADI;
				dpath = dbliste.get(i).getPath() + "\\";
				dzip = tarr + "_" + uzantisiz + ".zip";
				if (folderMI)
				{
					//
					uplpnl.Progres_Bar_Temizle_2();
					uplpnl.RPB2.setStringPainted(true);
					uplpnl.RPB2.setString("Zip Haline Getiriliyor..........");
					dzip = tarr + "_" + dbliste.get(i).getAdi() + ".zip";
					String okumadosyaadi = dbliste.get(i).getPath() +"\\"+dbliste.get(i).getAdi()+"\\";
					Path pathokuma = Paths.get(dbliste.get(i).getPath() +"\\"+dbliste.get(i).getAdi()); 
					Path pathyazma = Paths.get(glb.BACKUP_YERI, dzip); 
					bckp. zipFolder(pathokuma,pathyazma);
					uplpnl.Progres_Bar_Temizle_2();
					//uplpnl.RPB2.setString("");

				}
				else
				{
					String okumadosyaadi = dbliste.get(i).getPath() +"\\"+dbliste.get(i).getAdi();
					bckp.diger_zip_yap(okumadosyaadi, glb.BACKUP_YERI, dzip, false, "");
				}
				bckp.log_kayit(emirADI, new Date(), dosADI + " Zip Haline Getirildi...");
				UploadFTPFiles(ftp, surucu, glb.BACKUP_YERI, dzip, kull, sifre, port, zmnasimi);
				bckp.log_kayit(emirADI, new Date(), dosADI + " FTP Yuklendi...");
				bckp.log_kayit(emirADI,new Date(), uzantisiz + " ZIP Dosyasi Silindi...");

				File tmpDir = new File(glb.BACKUP_YERI + dzip);
				boolean exists = tmpDir.exists();
				if(exists)
					tmpDir.delete();
			}

			Date nowwDate = new Date();
			durumYAZ(emirADI,nowwDate);		
			bckp.genel_kayit_durum(emirADI, true,nowwDate, "Yedeklendi.....");

			if (eskiyedek > 0) // **************FTP ESKILERI SIL
			{
				uplpnl.Progres_Bar_Temizle_1();
				uplpnl.Progres_Bar_Temizle_2();
				dosADI = "";
				int gunfark;
				List<remote_filelist> ls = new ArrayList<remote_filelist>();
				ls = bckp.ListRmtFiles( ftp , surucu, kull, sifre);
				uplpnl.RPB1.setMaximum(dbliste.size());
				uplpnl.RPB1.setStringPainted(true);
				uplpnl.RPB2.setMaximum(ls.size());
				uplpnl.RPB2.setStringPainted(true);

				for (int i = 0; i <= dbliste.size() - 1; i++)
				{
					uplpnl. Progres_Bar_1( i + 1);
					for (int r = 0; r <= ls.size() - 1; r++)
					{
						uplpnl.  Progres_Bar_2( r + 1);
						dosADI = dbliste.get(i).getAdi();
						String ftpDOSYA = ls.get(r).getDosyaADI();
						boolean found = ftpDOSYA.contains(dosADI);
						if (found)
						{
							SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
							String dateInString = ls.get(r).getTaRIH().substring(8,10) +"."+ls.get(r).getTaRIH().substring(5,7) +"."+ ls.get(r).getTaRIH().substring(0,4);
							Date ftar = formatter.parse(dateInString);
							long dateBeforeInMs = ftar.getTime();
							long dateAfterInMs = new Date().getTime();
							long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
							long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
							gunfark = (int)daysDiff;
							if (gunfark > eskiyedek)
							{
								bckp.log_kayit(emirADI, new Date(), ls.get(r) + " FTP ye Silmeye Gitti...");
								bckp.ftp_sil( ftp, surucu, ls.get(r).getDosyaADI(), kull, sifre, port);
								bckp.log_kayit(emirADI, new Date(), dosADI + " Dosya FTP Eski Tarihli Silindi...");
							}
						}
					}
				}
			}
			yapildiMAILI(emirADI);
			uplpnl.setPreferredSize(new Dimension(0,00));
			uplpnl.setMaximumSize(new Dimension(0,0));
			uplpnl.revalidate();
			//emirBOSALT(emirADI);
			//emirTEKYUKLE(emirADI,"");
			emirYENIDENBASLAT(emirADI);
			contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			////
		}
		catch (Exception ex)
		{
			try {
				bckp.genel_kayit_durum(emirADI, false, new Date(), ex.getMessage().toString().length() > 40 ?  ex.getMessage().substring(0, 40).toString(): ex.getMessage());

				bckp.log_kayit(emirADI, new Date(), ex.getMessage());
				uplpnl. Progres_Bar_Temizle_1();
				uplpnl. Progres_Bar_Temizle_2();
				uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				yapilmadiMAILI(emirADI);

				bckp.log_kayit(emirADI, new Date(), "Hata Durumundan Emir Bosaldi...");
				emirtekSIL_HATA(emirADI);
				//emirBOSALT(emirADI);
				//emirTEKYUKLE(emirADI,"");
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			} catch (Exception e) {
				contentPane.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				e.printStackTrace();
			}
		} 
		//////
		//			}
		//		};
		//		Thread t = new Thread(runner, "Code Executer");
		//		t.start();
	}
	private void UploadFTPFiles(String ftpp, String ftpsurucu, String dosyayolu, String dosadi, String kull, String sifre, String port, int zmn) throws IOException, InterruptedException
	{
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(ftpp, Integer.valueOf(port));
			ftp.login(kull, sifre);
			ftp.changeWorkingDirectory(ftpsurucu);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			File secondLocalFile = new File(dosyayolu + dosadi );
			String secondRemoteFile = dosadi;
			InputStream inputStream = new FileInputStream(secondLocalFile);
			OutputStream outputStream = ftp.storeFileStream(secondRemoteFile);
			byte[] bytesIn = new byte[8192]; //4196
			int read = 0;
			long toplam = 0 ;
			int fileLenght = (int) secondLocalFile.length();
			double speedInKBps = 0.00;
			uplpnl.Progres_Bar_Temizle_2();
			uplpnl.RPB2.setMaximum((int) fileLenght);
			uplpnl.RPB2.setStringPainted(true);
			Instant start = Instant.now();
			while ((read = inputStream.read(bytesIn)) != -1) {
				outputStream.write(bytesIn, 0, read);
				toplam += read;
				uplpnl.Progres_Bar_2( (int)toplam);
				//double yuzde = ((double) toplam  / (long) uplpnl.RPB2.getMaximum()) * 100;    
				//uplpnl.RPB2.setString(String.valueOf(FORMATLAMA.doub_2(yuzde))+ " %");
				Instant finish = Instant.now();
				long timeElapsed = Duration.between(start, finish).toMillis();
				int seconds = (int)((timeElapsed / 1000) % 60);
				speedInKBps = ( (toplam * 1000) / (seconds + 1))  ;
				uplpnl.lblHiz.setText(FORMATLAMA.doub_0(speedInKBps /1024) + " KBytes");
			}
			//uplpnl.RPB2.setString("");
			inputStream.close();
			outputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ftp.isConnected()) {
					ftp.logout();
					ftp.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
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
				{
					container.remove(component);
				}
			}
			else if (component.getName() == null)
			{
				container.remove(component);
			}
		}

		container.revalidate();
		container.repaint();
		emirSAYI_COUNT();
	}

	private static void mail_at(List<bilgilendirme_bilgiler> bilgiBilgi,String mesaj )
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
			{
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
				//props.put("mail.smtp.startsls.enable", SSL);
			}

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
			messagePart.setText(mesaj + " -mesaj -2","UTF-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messagePart);


			message.setSentDate(new Date());

			message.setSubject("OBS BACKUP YEDEKLEME" , "UTF-8");
			message.setContent(multipart);
			//message.setSentDate(new Date());
			Transport.send(message);
			message= null;
			session = null;
		}
		catch (Exception ex)
		{

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
								sndrm.setText("Yedeklendi");
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
					bilgilendirme_oku(emirADI, emirADI + "    " + df.format(today) + "     Yedekleme Yapildi",bilgiBilgi);
					bckp.log_kayit(emirADI, new Date(), "Yedekleme Yapildi Maili gonderildi...");
				}
			}
		}

	}
	private void yapilmadiMAILI(String emirADI) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
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
					bilgilendirme_oku(emirADI, emirADI + "    " + df.format(today) + "     Yedekleme Yapilamadi",bilgiBilgi);
					bckp.log_kayit(emirADI, new Date(), "Yedekleme Yapilamadi Maili gonderildi...");
				}
			}
		}
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
								JButton sndrm = (JButton) compo;
								sndrm.doClick();

							}

						}
					}
				}
			}
			component.revalidate();
		}
	}
	@Override
	public Dimension getPreferredSize() {
		Dimension superSz = super.getPreferredSize();
		if (isPreferredSizeSet()) {
			return superSz;
		}
		return new Dimension(900, 700);
	}
}


//Instant start = Instant.now() ;
//Instant stop = start.plusSeconds( 135L ) ;
//Duration d = Duration.between( start , stop ) ;
//long minutesPart = d.toMinutes(); 
//long secondsPart = d.minusMinutes( minutesPart ).getSeconds() ;
//
//System.out.println( "Interval: " + start + "/" + stop );
//System.out.println( "d.toString(): " + d );
//System.out.println( "d.getSeconds(): " + d.getSeconds() );
//System.out.println( "Elapsed: " + minutesPart + "M " + secondsPart + "S" );
//
