package obs.backup.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;


import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.CheckListItem;
import OBS_C_2025.GLOBAL;
import javazoom.jl.player.Player;
import obs.backup.gorev.gOREV_TAKIP;
import obs.backup.other.Bilgilendirme;
import obs.backup.other.EmirAnaGiris;
import obs.backup.other.EmirKopyala;
import obs.backup.other.ServerBilgileri;
import obs.backup.other.SunucuAyarlari;
import obs.backup.other.Title_Bar;
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
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import OBS_C_2025.SIFRE_DONDUR;
import OBS_C_2025.db_List;
import OBS_C_2025.emir_bilgiler;

@SuppressWarnings({ "static-access" })
public class OBS_BACKUP extends JFrame {

	GLOBAL glb = new GLOBAL();
	public static List<String> gorevLER = new ArrayList<String>();
	public static String gelenISIM ="";
	static BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public static JPanel container ;
	private JPanel panel_2 ;
	public static JTabbedPane tabbedPane ;
	public static JTabbedPane tabbedPane_1;
	int x ,y ;
	public static YedeklemeAraligi yedekaraligiPanel;
	public static SunucuAyarlari sunucuayarPanel;
	public static Bilgilendirme bilgilendirmePanel;
	public static ServerBilgileri serverBilgileriPanel;
	public static EmirKopyala emirKopyalaPanel ;
	public static EmirAnaGiris emirAnaGirisPanel;
	
	private static JLabel lblNewLabel_6 ;
	private static JLabel lblNewLabel_5;
	
	public static JButton btnYeni_Gorev;
	public static JButton btnNewButton_2;


	//private static JList list;
	//private static DefaultListModel<CheckListItem> model ;
	
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
		
		JButton btnGorevler = new JButton("Gorevler");
		btnGorevler.setPreferredSize(new Dimension(0,25));
		btnGorevler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
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
				
				OBS_BACKUP.gelenISIM = "" ;
				tabbedPane.setSelectedIndex(1);
				tabbedPane_1.setSelectedIndex(0);
			
			}
		});
		panel.add(btnYeni_Gorev);
		btnNewButton_1.setPreferredSize(new Dimension(0,25));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("alt pan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
			}
		});
		panel.add(btnNewButton);
		
		btnNewButton_2 = new JButton("emir duzelt");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emirAnaGirisPanel.emirDOLDUR();
			}
		});
		panel.add(btnNewButton_2);
		
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
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane_1, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 128, 128));
		panel_2.setMinimumSize(new Dimension(0,100));
		panel_2.setPreferredSize(new Dimension(0,100));
		splitPane_1.setRightComponent(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		
		splitPane_1.setLeftComponent(scrollPane);
		
		container = new JPanel(); 
		scrollPane.setViewportView(container);
	
		container.setLayout(new GridLayout(10, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Yeni Gorev", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel_3.add(tabbedPane_1, BorderLayout.CENTER);
		
		
		JPanel altPane = new JPanel();
		altPane.setPreferredSize(new Dimension(0,25));
		contentPane.add(altPane, BorderLayout.SOUTH);
		
		
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
		} catch (Exception e1) {
			//System.out.println(e1.getMessage());
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
				 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
		         bckp.genel_kayit(emirAnaGirisPanel.txtEmir.getText(), emirAnaGirisPanel.chckbxDurum.isSelected(),emirAnaGirisPanel. textAciklama.getText(), lblNewLabel_6.getText(), sondurum, sonyuk,emirAnaGirisPanel. chckbxServerDosya.isSelected(), mesaj, ilkkayit);
		     }
		     else
		     {
		         bckp.genel_kayit(emirAnaGirisPanel.txtEmir.getText(),emirAnaGirisPanel. chckbxDurum.isSelected(),emirAnaGirisPanel. textAciklama.getText(), lblNewLabel_6.getText(), false, sonyuk, emirAnaGirisPanel. chckbxServerDosya.isSelected(), mesaj, ilkkayit);
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
		    bckp.bilgilendirme_ismi_kayit(emirAnaGirisPanel.txtEmir.getText(),bilgilendirmePanel.chckbxAktifPasif.isSelected() ,bilgilendirmePanel.chckbxIslem.isSelected(), bilgilendirmePanel.chckbxHata.isSelected(), bilgilendirmePanel.textGonIsim.getText(), bilgilendirmePanel.textGonHesap.getText() , bilgilendirmePanel.textAlici.getText(), bilgilendirmePanel.textKonu.getText(), bilgilendirmePanel.textSmtp.getText(), bilgilendirmePanel.textPort.getText(),bilgilendirmePanel.textKull.getText(), response, bilgilendirmePanel.chckbxSSL.isSelected(), bilgilendirmePanel.chckbxTSL.isSelected());
		    bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText(), new Date(), "Bilgilendirme Bilgileri  Kaydedildi...");
		    tabbedPane_1.setSelectedIndex(1);
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
		lblNewLabel_5.setText(Integer.toString(0));
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
		lblNewLabel_6.setText("Ms Sql");
		try
		{
			bckp.server_kayit_sil(emirAnaGirisPanel.txtEmir.getText().toString());
			bckp.server_ismi_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), serverBilgileriPanel.textMSServer.getText(), true, true, serverBilgileriPanel.textMSkull.getText(), response, "Ms Sql", "");
			bckp.log_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), new Date(), "SQL SERVER Instance Bilgileri Kaydedildi...");
			bckp.instance_update(emirAnaGirisPanel.txtEmir.getText().toString(), "Ms Sql");
			tabbedPane_1.setSelectedIndex(1);
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
		lblNewLabel_5.setText(Integer.toString(0));
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
		lblNewLabel_6.setText("My Sql");
		try
		{
			bckp.server_kayit_sil(emirAnaGirisPanel.txtEmir.getText().toString());
			bckp.server_ismi_kayit(emirAnaGirisPanel.txtEmir.getText().toString(), "localhost", true, true, serverBilgileriPanel.textMykull.getText(), response, "My Sql",  serverBilgileriPanel.textMYPort.getText());
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
	     String hataISMI = "";
	     try
	     {
	    	 contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    	 container.removeAll();
	        // DURUM DESC , EMIR_ISMI 
	        ///
	    	
	        List<emir_bilgiler> emirliste = bckp.emir_liste(siralama);
	      
			if (emirliste.size() == 0 ) {  
				 contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		     }
	        else {

	        	for (int i = 0; i<=  emirliste.size() -1 ; i++)
	             {
	        	
	                 hataISMI = emirliste.get(i).getEMIR_ISMI() ;
	                 //RadLabelElement2.Text = dtss.Tables[0].Rows.Count.ToString("N0");
	           
	                 emirTEKYUKLE( emirliste.get(i).getEMIR_ISMI());
	             }
	        	 contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
	        
	        
	     }
	     catch (Exception ex)
	     {
	    		System.out.println(ex.getMessage());
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
	     //RadLabelElement2.Text = radScrollablePanel1.Controls[0].Controls.Count.ToString("N0");
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
	public static void okuma(String isim)
	{
		System.out.println(isim);
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
