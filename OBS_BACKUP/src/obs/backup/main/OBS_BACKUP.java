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
import com.formdev.flatlaf.themes.FlatMacDarkLaf;


import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.CheckListItem;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.GLOBAL;
import javazoom.jl.player.Player;
import obs.backup.gorev.gOREV_TAKIP;
import obs.backup.other.Bilgilendirme;
import obs.backup.other.EmirAnaGiris;
import obs.backup.other.EmirKopyala;
import obs.backup.other.ServerBilgileri;
import obs.backup.other.SunucuAyarlari;
import obs.backup.other.Title_Bar;
import obs.backup.other.UploadPanel;
import obs.backup.other.YedeklemeAraligi;
import raven.toast.Notifications;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
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
import OBS_C_2025.SIFRE_DONDUR;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.bilgilendirme_bilgiler;
import OBS_C_2025.emir_bilgiler;
import OBS_C_2025.ftp_bilgiler;
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

@SuppressWarnings({ "static-access" })
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
	UploadPanel uplpnl ;
	
	public static JButton btnYeni_Gorev;
	public static JButton btnNewButton_2;
	public static JButton btnGorevler;
	private static JLabel lblemirSAYI;
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
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(150,0));
		splitPane.setLeftComponent(panel);
		
		btnGorevler = new JButton("Gorevler");
		btnGorevler.setPreferredSize(new Dimension(0,25));
		btnGorevler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gelenISIM = "" ;
				try {
				emir_yukle("EMIR_ISMI") ;
				tabbedPane.setSelectedIndex(0);
				} catch (Exception e1) {

					e1.printStackTrace();
				}
		
//				container.removeAll();
//					gOREV_TAKIP x = new gOREV_TAKIP("hamit");
//					//x.setBounds(0, 0, 400, 100);
//					
//					container.add(x);
//					gOREV_TAKIP x2 = new gOREV_TAKIP("aden");
//					//x2.setBounds(0, 110, 400, 100);
//					container.add(x2);
//				for (int i = 0; i < 15; i++) {
//					gOREV_TAKIP x = new gOREV_TAKIP(String.valueOf(i + 1));
//			          x.setLocation(0, 45 *i);
//			         container.add(x);
//			      }
//					pack();
				}
			});
		panel.setLayout(new GridLayout(20, 1, 0, 0));
		panel.add(btnGorevler);
		
		JButton btnNewButton_1 = new JButton("Kompon.bulma");
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {

				Component[] components = container.getComponents();
				for (Component component : components) {
					System.out.println(component.getName());
					if (component.getName().toString().equals("hamit")) {
						JPanel qweJPanel = (JPanel) component ; 

						Component[] componentt = qweJPanel.getComponents();
						for (Component compo : componentt) {

							System.out.println(compo.getName());
						}
					}
					
				}
			
			}
		});
		
		btnYeni_Gorev = new JButton("Yeni Gorev");
		btnYeni_Gorev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gelenISIM = "" ;
				tabbedPane.setSelectedIndex(1);
				tabbedPane_1.setSelectedIndex(0);
				emirAnaGirisPanel.emirDOLDUR();
			}
		});
		panel.add(btnYeni_Gorev);
		btnNewButton_1.setPreferredSize(new Dimension(0,25));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("alt pan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

//				uplpnl.setPreferredSize(new Dimension(0,100));
//				uplpnl.setMaximumSize(new Dimension(0,100));
//				uplpnl.revalidate();
				try {
					yedekLEE_SQL("hamit");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				Component[] components = container.getComponents();
//				for (Component component : components) {
//					System.out.println(component.getName());
//						component.setPreferredSize(new Dimension(00,175));		
//						component.repaint();
//				}
//				container.repaint();
			}
		});
		panel.add(btnNewButton);
		
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		container = new JPanel(); 
		scrollPane.setViewportView(container);
	
		container.setLayout(new GridLayout(15, 1, 5, 5));
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
		    		//System.out.println("Bekleeyen Emir Sayisi:" + gorevLER.size());
					yEDEKLE();
			
				} catch (Exception e) {

					e.printStackTrace();
				}
		    };  
		};  
		timerr.scheduleAtFixedRate(tt, 1000, 1000);
		//timerr.schedule(tt,100, 1000);
	}
	private void yEDEKLE() throws ClassNotFoundException, SQLException
	{
		String hataEMIR = "";
		try
		{
			timerr.cancel();;
			for (int g = 0; g < gorevLER.size(); g++)
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
			bckp.ftp_ismi_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), sunucuayarPanel.textHost.getText(), sunucuayarPanel.textKull.getText(), sdon.sDONDUR(sunucuayarPanel.textSifre), sunucuayarPanel.textFtpSurucu.getText(),  sunucuayarPanel.textPort.getText(),Integer.parseInt( sunucuayarPanel.textZmnasm.getText()) , sunucuayarPanel.textEskisilme.getText(), neresi,  sunucuayarPanel.textSurucu.getText());
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

				for (int i = 0; i<=  emirliste.size() -1 ; i++)
				{
					emirTEKYUKLE( emirliste.get(i).getEMIR_ISMI());
				}
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		catch (Exception ex)
		{
			contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), ex.getMessage());
			mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());	
		}
	}
	public void emirTEKYUKLE(String eismi)
	{
		if (eismi.equals("")) return;
		gOREV_TAKIP emir = new gOREV_TAKIP(eismi);

		emir.setName(eismi);
		emir.lblemirISMI.setText(eismi);
		container.add(emir);
		emirSAYI_COUNT();
	}
	private void emirINDEXLEME(String eismi)
	{
		if (eismi.equals("")) return;
		gOREV_TAKIP emir = new gOREV_TAKIP(eismi);

		emir.setName(eismi);
		emir.lblemirISMI.setText(eismi);
		container.add(emir);
		emirSAYI_COUNT();
	  //  radScrollablePanel1.Controls.SetChildIndex(emir, emirINDEX);
	    
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
	public static void emirSIL(String eadi) throws ClassNotFoundException, SQLException
	{
		Component[] components = container.getComponents();
		for (Component component : components) 
		{
			if (component.getName().toString().equals(eadi)) 
			{
				container.remove(component);
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
		
			System.out.println(component.getName());
				component.setPreferredSize(new Dimension(00,60));
			
		
		}
		container.repaint();
		
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
		
				////
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
		    emirBOSALT(emirADI);
		    bckp.log_kayit(emirADI, new Date(), "Dosya Secilmemis...");
		    bckp.log_kayit(emirADI, new Date(), "Emir Yuklendi...");
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
        	ftp_yukleme(emirADI,ftpBilgi);
        }

	}
	private void ftp_yukleme(String emirADI,List<ftp_bilgiler> ftpBilgi) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NumberFormatException, SocketException, IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Runnable runner = new Runnable()
		{ 
			@SuppressWarnings("deprecation")
			public void run() {
		/////
		try {
			///
		
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

		        //emirBOSALT(emirADI);
		        emirBOSALT(emirADI);
		        bckp.log_kayit(emirADI, new Date(), "Internet Baglantisi Yok...");
		   
		        bckp.log_kayit(emirADI, new Date(), "Emir Yuklendi...");
		    
		        return;
		    }
		   
			boolean result =   bckp.DoesFtpDirectoryExist(ftp ,surucu,Integer.valueOf(port),kull, sifre);
			
		    //
		    if (result == false)
		    {
		        bckp.genel_kayit_durum(emirADI, false, new Date(), "Yedeklenmedi FTP Surucu Bulunamadi...");
		        bckp.log_kayit(emirADI, new Date(), "Yedeklenmedi FTP Surucu Bulunamadi...");
		       
		        uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
				
		        emirBOSALT(emirADI);
		        bckp.log_kayit(emirADI, new Date(), "FTP Surucu Bulunamadi...");
		        bckp.log_kayit(emirADI, new Date(), "Emir Yuklendi...");
	 
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
   				bckp.MySql_baglan( serverBilgi.get(0).getINSTANCE(),sifre,serverBilgi.get(0).getPORT());   
   			 
		    }
	
		    String tarr =  TARIH_CEVIR.tarihddMMyyyyHHmm(new Date());
		    bckp.log_kayit(emirADI, new Date(), "Yedeklemeye Baslandi....");
//		    {
		        String dosADI = "";
		        List<String> dbliste = bckp.db_liste(emirADI);
		        UploadPanel.RPB1.setMaximum(dbliste.size());
		        UploadPanel.RPB1.setStringPainted(true);
				
		        for (int i = 0; i <= dbliste.size() - 1; i++)
		        {
		        	
		            dosADI = dbliste.get(i); // Dosya Adi
		            UploadPanel.Progres_Bar_1( i + 1);
		            //UploadPanel.RPB2.setString("Backup Aliniyor.........");
		            if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
		            {
		        	   
		                bckp.backup_al(dosADI, tarr + "_" + dosADI);
		            }
		            else
		            {
		                bckp.mySQL_backup(emirADI, dosADI, serverBilgi.get(0).getKULLANICI(), sqlsifre, serverBilgi.get(0).getPORT(), glb.BACKUP_YERI, tarr + "_" + dosADI, serverBilgi.get(0).getINSTANCE());
		            }
//		            RPB2.Text = "";
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
//		            RPB2.Text = "Zip Haline Getiriliyor.........";
	                bckp.zip_yap(dosya, glb.BACKUP_YERI, dzip, false, "");
//		            RPB2.Text = "";
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
	            	bckp.log_kayit(emirADI, new Date(), dosADI + ".sql" + " Dosyasi Silindi...");
		            bckp.log_kayit(emirADI, new Date(), dosADI + " ZIP Dosyasi Silindi...");
		        }
//		    }
		        
//		    //radScrollablePanel1.Controls[0].Controls[emirADI].Controls[0].Controls["RadLabel11"].Text = DateTime.Now.ToString("dd.MM.yyyy HH:mm:ss tt");
//		    //radScrollablePanel1.Controls[0].Controls[emirADI].Controls[0].Controls["RadLabel13"].ForeColor = Color.Green;
//		    //radScrollablePanel1.Controls[0].Controls[emirADI].Controls[0].Controls["RadLabel13"].Text = "Yedeklendi";
		        Date nowwDate = new Date();
		        Component[] components = container.getComponents();
		        for (Component component : components) {
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
		        	component.revalidate();
		        }
		    bckp.genel_kayit_durum(emirADI, true,nowwDate, "Yedeklendi.....");
//		    
		    System.out.println(eskiyedek);
		    if (eskiyedek > 0) // **************FTP ESKILERI SIL
		    {
		       UploadPanel.Progres_Bar_Temizle_1();
		       UploadPanel.Progres_Bar_Temizle_2();
		        List<String> ls = new ArrayList<String>();
		      
		        dosADI = "";
		        int gunfark;
		        
		        ls = bckp.ListRmtFiles( ftp , surucu, kull, sifre);
		        UploadPanel.RPB1.setMaximum(dbliste.size());
		        UploadPanel.RPB1.setStringPainted(true);
		        UploadPanel.RPB2.setMaximum(ls.size());
		        UploadPanel.RPB2.setStringPainted(true);
		        for (int i = 0; i <= dbliste.size() - 1; i++)
		        {
		        	
		            dosADI = dbliste.get(i);
		            UploadPanel. Progres_Bar_1( i + 1);
		           
		            for (int r = 0; r <= ls.size() - 1; r++)
		            {
		            	
		            	UploadPanel.  Progres_Bar_2( r + 1);
		                String ftpDOSYA = ls.get(r);
		                String test1 = "";
		                
		                if (ftpDOSYA.toString().length() > 13)
		                {
		                	test1 ="";
		                	int lastIndxDot = ftpDOSYA.lastIndexOf('.');
		                	if (lastIndxDot != -1) {
		                		test1 = ftpDOSYA.substring(0, lastIndxDot);
		                	}
		                	ftpDOSYA = test1.substring(13, lastIndxDot);
		                }
		               
		                if (ftpDOSYA.toString().equals(dosADI))
		                {
		                    String[] token = ls.get(r).split("\t");
		                    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
		                    String dateInString = token[4].substring(8,10) +"."+token[4].substring(5,7) +"."+ token[4].substring(0,4);
		                    Date ftar = formatter.parse(dateInString);
		                    long dateBeforeInMs = ftar.getTime();
		                    long dateAfterInMs = new Date().getTime();
		                    long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
		                    long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
		                    //long daysDiff1 = TimeUnit.HOURS.convert(timeDiff, TimeUnit.MILLISECONDS);
		                    gunfark = (int)daysDiff;
		                    if (gunfark > eskiyedek)
		                    {
		                        bckp.log_kayit(emirADI, new Date(), ls.get(r) + " FTP ye Silmeye Gitti...");
		                        bckp.ftp_sil( ftp, surucu, ls.get(r), kull, sifre, port);
		                        bckp.log_kayit(emirADI, new Date(), dosADI + " Dosya FTP Eski Tarihli Silindi...");
		                    }
		                }
		            }
		        }
		    }
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
		                bckp.log_kayit(emirADI, new Date(), "Yedekleme Yapildi Maili Gonderildi...");
		            }
		        }
		    }
		
		       uplpnl.setPreferredSize(new Dimension(0,00));
				uplpnl.setMaximumSize(new Dimension(0,0));
				uplpnl.revalidate();
		         ////
					}
					catch (Exception ex)
					{
					} 
		         //////
					}
				};
				Thread t = new Thread(runner, "Code Executer");
				t.start();
	}
	private void bilgilendirme_oku(String emir, String mesaj ,List<bilgilendirme_bilgiler> bilgiBilgi) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
	   
	    if (bilgiBilgi.size() > 0)
	    {
	       
	        mail_at( bilgiBilgi , mesaj);
	    }
	}
	private void diger_dosya(String eismi, String aciklama)
	{
		
	}
	private void UploadFTPFiles(String ftpp, String ftpsurucu, String dosyayolu, String dosadi, String kull, String sifre, String port, int zmn) throws IOException
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
			byte[] bytesIn = new byte[4096];
			int read = 0;
			long toplam = 0 ;
			int fileLenght = (int) secondLocalFile.length();
			double speedInKBps = 0.00;
			UploadPanel.Progres_Bar_Temizle_2();
			UploadPanel.RPB2.setMaximum((int) fileLenght);
			UploadPanel.RPB2.setStringPainted(true);
			Instant start = Instant.now();

			while ((read = inputStream.read(bytesIn)) != -1) {
				outputStream.write(bytesIn, 0, read);
				toplam += read;
				UploadPanel.Progres_Bar_2( (int)toplam);
				Instant finish = Instant.now();
				long timeElapsed = Duration.between(start, finish).toMillis();
				int seconds = (int)((timeElapsed / 1000) % 60);
				speedInKBps = ( (toplam * 1000) / (seconds + 1))  ;
				UploadPanel.lblHiz.setText(FORMATLAMA.doub_0(speedInKBps /1024) + " KBytes");
			}
			inputStream.close();
			outputStream.close();
		} catch (IOException | InterruptedException ex) {
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
			if (component.getName().toString().equals(emirADI)) 
			{
				container.remove(component);
			}
		}
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
