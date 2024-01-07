package obs.gorev.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.CONNECT;
import OBS_C_2025.CheckListItem;
import OBS_C_2025.CheckListRenderer;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GOREV_GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.IConnection;
import OBS_C_2025.IKUR;
import OBS_C_2025.ILOGGER;
import OBS_C_2025.KUR_ACCESS;
import OBS_C_2025.OBS_ORTAK_MSSQL;
import OBS_C_2025.OBS_ORTAK_MYSQL;
import OBS_C_2025.SIFRE_DONDUR;
import OBS_C_2025.Server_Bilgi;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.USER_ISLEMLERI;
import OBS_C_2025.g_bilgiler;

import fih.FIHRIST_ACCESS;

import obs.gorev.theme.Title_Bar;

import javax.swing.JTabbedPane;
import javax.swing.SpinnerDateModel;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import OBS_C_2025.KUR_MSSQL;
import OBS_C_2025.KUR_MYSQL;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({"unused","deprecation","rawtypes","unchecked","static-access"})
public class OBS_GOREV extends JFrame  {

	private static final long serialVersionUID = 1L;
	GLOBAL glb = new GLOBAL();
	GOREV_GLOBAL grvglb = new GOREV_GLOBAL();
	

	private static JSpinner timeBaslangic;
	private static DefaultListModel<CheckListItem> model ;
	BAGLAN bAGLAN = new BAGLAN();
	boolean KUR_DOS_VAR;
	private static boolean KUR_CONN;
	JCheckBox chckbxDurum ;
	private static IKUR _IKur;
	private static ILOGGER[] _IKur_Loger = {};
	boolean surucubilgi = false;
	JList list;
	JLabel lblAciklama;
	public IConnection _IKurCon ;
	static KUR_ACCESS k_Access ;
	public USER_ISLEMLERI uSER_ISL = new USER_ISLEMLERI();
	public SIFRE_DONDUR sDONDUR = new     SIFRE_DONDUR();
	static Timer timerr ;
	String[] kurrakkam;
	private JTextField textKurKullanici;
	int x ,y ;
	public static JButton btntry;
	static JButton btnBuyult;
	static TrayIcon trayIcon = null ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OBS_GOREV frame = new OBS_GOREV();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OBS_GOREV() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setUndecorated(true);
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

		setIconImage(Toolkit.getDefaultToolkit().getImage(OBS_GOREV.class.getResource("/obs/gorev/other/job-24.png")));
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("obs.gorev.theme");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
		FlatMacDarkLaf.setup();
		
		setTitle("OBS MERKEZ KUR");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 293);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		getContentPane().add(new Title_Bar(this), BorderLayout.NORTH);

		JPanel panelalt = new JPanel();
		panelalt.setPreferredSize(new Dimension(0,30));
		getContentPane().add(panelalt, BorderLayout.SOUTH);
		panelalt.setLayout(null);
		
		lblAciklama = new JLabel(".....");
		lblAciklama.setBounds(7, 7, 418, 14);
		panelalt.add(lblAciklama);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1 , BorderLayout.CENTER);
		panel_1.setLayout(null);

		chckbxDurum = new JCheckBox("Aktif / Pasif");
		chckbxDurum.setBounds(149, 33, 99, 23);
		panel_1.add(chckbxDurum);

		JLabel lblNewLabel = new JLabel("Gorev Saati");
		lblNewLabel.setBounds(21, 76, 73, 14);
		panel_1.add(lblNewLabel);

		timeBaslangic = new JSpinner( new SpinnerDateModel() );
		timeBaslangic.setBounds(149, 71, 75, 25);
		panel_1.add(timeBaslangic);
		JSpinner.DateEditor de_timeBaslangic = new JSpinner.DateEditor(timeBaslangic, "HH:mm");
		timeBaslangic.setEditor(de_timeBaslangic);
		Date qweDate = new Date();
		qweDate.setHours(00);
		qweDate.setMinutes(00);
		timeBaslangic.setValue(qweDate);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(287, 11, 136, 198);
		panel_1.add(scrollPane);

		model = new DefaultListModel<>();
		list = new JList(model);
		list.setCellRenderer(new CheckListRenderer());
		list.setBounds(289, 12, 134, 283);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				JList list = (JList) event.getSource();
				int index = list.locationToIndex(event.getPoint());// Get index of item
				CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
				item.setSelected(!item.isSelected()); // Toggle selected state
				list.repaint(list.getCellBounds(index, index));// Repaint cell

			}
		});
		model.addElement( new CheckListItem("USD",""));
		model.addElement( new CheckListItem("EUR",""));
		model.addElement( new CheckListItem("RUB",""));
		model.addElement( new CheckListItem("GBR",""));
		model.addElement( new CheckListItem("CHF",""));
		model.addElement( new CheckListItem("SEK",""));
		model.addElement( new CheckListItem("NOK",""));
		model.addElement( new CheckListItem("SAR",""));

		list.repaint();

		scrollPane.setViewportView(list);

		JButton btnNewButton_1 = new JButton("Kayit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kayit();
					 sirala();
						JOptionPane.showMessageDialog(null, "Kaydedildi......");
				} catch (Exception e1) {

					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnNewButton_1.setBounds(149, 185, 89, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("OBS Kur Kullanici");
		lblNewLabel_1.setBounds(21, 140, 121, 14);
		panel_1.add(lblNewLabel_1);
		
		textKurKullanici = new JTextField();
		textKurKullanici.setColumns(10);
		textKurKullanici.setBounds(149, 137, 106, 20);
		panel_1.add(textKurKullanici);
		/////
		btntry= new JButton("");
		btntry.setVisible(false);
		btntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				systemTRY();
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

		
		//***********************************************************************************
		try {
			if (glb.dos_kontrol(glb.SURUCU + glb.OBS_DOSYA))
			{
				glb.gorev_surucu_kontrol();
				List<g_bilgiler> rSet = grvglb.gorev_bilgi_oku();
				if ( rSet.size() == 0 ) {  
				} 
				else {
					textKurKullanici.setText(rSet.get(0).getObs_kullanici());
					chckbxDurum.setSelected(rSet.get(0).isDurum());
					List<g_bilgiler> gzaman = grvglb.gorev_zaman_oku();
					SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
					Date snyk =  formatter.parse(gzaman.get(0).getBaslangicDate().toString());
					Date zamDate = new Date();
					zamDate.setHours(snyk.getHours());
					zamDate.setMinutes(snyk.getMinutes());
					zamDate.setSeconds(0);
					timeBaslangic.setValue(zamDate);
					calisma_dizini_oku() ;
					kur_doldur();
					if(chckbxDurum.isSelected())
					{
						jobTimerBasla();
					}
				}
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	private void jobTimerBasla() throws ParseException, NumberFormatException, ClassNotFoundException, SQLException
	{
		Calendar calendar = Calendar.getInstance();
		timerr = new Timer();  
		TimerTask tt = new TimerTask() {  
		    @Override  
		    public void run() {  
		    	 //long currennTime = System.currentTimeMillis();
		         //long stopTime = currennTime + 2000;//provide the 2hrs time it should execute 1000*60*60*2
		         //  while(stopTime != System.currentTimeMillis()){
		         //}
		    	try {
					merkez_oku_kayit();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
		    };  
		};  
		timerr.schedule(tt,getTomorrowMorning2AM(),  1000*60*60*24); 
		//merkez_oku_kayit();
	}
	private static Date getTomorrowMorning2AM( ) throws ParseException
	{
		Date datet = (Date) (timeBaslangic.getValue());
		int targetHour = datet.getHours() ;
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
	
		hour =  hour < targetHour ? targetHour - hour : targetHour - hour + 24;
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.HOUR,hour);
		Date ytr = c.getTime();
		ytr.setMinutes(datet.getMinutes());
		ytr.setSeconds(0);
		return ytr;
      }
	void kayit() throws ClassNotFoundException, SQLException
	{
		grvglb.bilgi_kayit( textKurKullanici.getText() , chckbxDurum.isSelected());
		Date date = (Date) (timeBaslangic.getValue());
		grvglb.zaman_kaydet(date);
		grvglb.kur_sil();
		for (int i = 0; i <= list.getModel().getSize() - 1; i++)
		{
			CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
			if (item.isSelected)
			{
				grvglb.kur_kayit(item.toString());
			}
		}
	}
	void kur_doldur() throws ClassNotFoundException, SQLException
	{
		List<g_bilgiler> rSet = grvglb.gorev_kur_oku();
		if ( rSet.size() == 0 ) {  
			//kur cinsi secilmemis
		} 
		else {
			for (int i = 0; i <=  list.getModel().getSize() -1 ;i++) {
				for (int r = 0; r <= rSet.size()-1;r++) {
					CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
					if (item.toString().equals(rSet.get(r).getKur()))
					{
						item.setSelected(true);
					}
				}
			}
		}
		 sirala();
		
	}
	void sirala()
	{
		int dosyaSAYI = 0;
		for (int i = 0; i <= list.getModel().getSize() - 1; i++)
		{
			CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
			if (item.isSelected)
			{
				CheckListItem item2 = item;
				item2.setSelected(true);
				model.remove(i);
				model.insertElementAt(item2, dosyaSAYI);

				dosyaSAYI += 1;
			}
		}
	}
	void calisma_dizini_oku() 
	{
		try 
		{
			bAGLAN.cONNECT(textKurKullanici.getText());//BAGLAN.kurDizin.kULLANICI
			cONN_AKTAR( BAGLAN.kurDizin.hAN_SQL );
			String hangi_sql =  BAGLAN.kurDizin.hAN_SQL;
			_IKur = hangi_sql.equals("MS SQL") ? new KUR_MSSQL() : new KUR_MYSQL();
			if(hangi_sql.equals("") )
			{
				surucubilgi = false ;
				return ;
			}
			surucubilgi = true ;
			lblAciklama.setText(bAGLAN.kurDizin.sERVER +"    Dosya:"+ bAGLAN.kurDizin.kOD);
			kur_calisma_dizini_oku();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	void kur_calisma_dizini_oku() throws ClassNotFoundException, SQLException
	{
		CONNECT s_CONN = new CONNECT( _IKurCon);
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setIns(BAGLAN.kurDizin.iNSTANCE); 
		sBilgi.setKull(BAGLAN.kurDizin.kULLANICI) ;
		sBilgi.setSifre(BAGLAN.kurDizin.sIFRESI);
		sBilgi.setPort(BAGLAN.kurDizin.sERVER);
		sBilgi.setServer( BAGLAN.kurDizin.sERVER);
		sBilgi.setDb("OK_Kur" + BAGLAN.kurDizin.kOD);
		if (BAGLAN.kurDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(sBilgi) == true)   
			{
				KUR_CONN = true ;
				if (s_CONN.Dosya_kontrol_L( sBilgi) == false)
				{
					KUR_DOS_VAR = false;
				}
				else
				{
					KUR_DOS_VAR = true;
				}
			}
			else
			{
				KUR_CONN = false;
			}
		}
		else
		{
			if (s_CONN.Server_kontrol_S(sBilgi) == true)   
			{
				KUR_CONN = true ;
				if (s_CONN.Dosya_kontrol_S( sBilgi) == false)
				{
					KUR_DOS_VAR = false;
				}
				else
				{
					KUR_DOS_VAR = true;
				}
			}
			else
			{
				KUR_CONN = false;
			}
		}
		k_Access = new KUR_ACCESS(_IKur , _IKur_Loger);
		k_Access.baglan();
	}
	private void merkez_oku_kayit() throws NumberFormatException, ClassNotFoundException, SQLException
	{
		for(int i = 0 ;i <= list.getModel().getSize() -1;i++)
		{
			CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
			if (item.isSelected)
			{
				String kurString[] = merkez(item.toString());
				if(! kurString[0].equals(""))
				{
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				String tarString = df.format(new Date());
				k_Access.kur_sil(tarString,item.toString());
				k_Access.kur_kayit(tarString,item.toString() ,
						Double.parseDouble(kurString[0]),Double.parseDouble(kurString[1]),
						0,0,0,0);
				}
			}
		}
	}
	private String[] merkez (String kur)
	{
		try
		{
			kurrakkam =  new String[]{"", ""};
			String bugun = LocalDate.now().toString() ;
			Document document = Jsoup.connect("https://www.tcmb.gov.tr/kurlar/today.xml").get();
			Elements elements = document.select("Currency"); 
			for( Element element : elements ) 
			{
				String KUR = element.attr("Kod");
				if (KUR.equals(kur))
				{
					String ForexBuying = element.select("ForexBuying").first().text();
					String ForexSelling = element.select("ForexSelling").first().text();
					kurrakkam[0] = ForexBuying;
					kurrakkam[1] = ForexSelling;
				}
			}
		}
		catch (Exception ex )
		{

		}
		return kurrakkam;
	}
	private void cONN_AKTAR(String hangi)
	{
	
		switch(hangi) {
		case "MS SQL":
			_IKurCon = new OBS_ORTAK_MSSQL() ;
			break;
		case "MY SQL":
			_IKurCon = new OBS_ORTAK_MYSQL() ;
			break;	
		}
	}
	private void mODUL_AKTAR(String hangi)
	{
		switch(hangi) {
		case "MS SQL":
			_IKur =  new KUR_MSSQL();
			break;
		case "MY SQL":
			_IKur =  new KUR_MYSQL();
			break;	
		}
	}
	public static  void systemTRY()
	{
		PopupMenu popup;
		Image image;
		if (SystemTray.isSupported()) {
			SystemTray tray = SystemTray.getSystemTray();
			image = Toolkit.getDefaultToolkit().getImage(OBS_GOREV.class.getResource("/obs/gorev/other/job-24.png"));
			ActionListener listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnBuyult.doClick();
				}
			};
			ActionListener kapat = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(1);
				}
			};
			popup = new PopupMenu();
			MenuItem buyultITEM = new MenuItem("Buyult");
			buyultITEM.setFont(new Font("Tahoma", Font.PLAIN, 14));
			buyultITEM.addActionListener(listener);
			MenuItem kapatItem = new MenuItem("Kapat");
			kapatItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
			kapatItem.addActionListener(kapat);
			popup.addSeparator();
			popup.add(buyultITEM);
			popup.addSeparator();
			popup.add(kapatItem);
			popup.addSeparator();
			trayIcon = new TrayIcon(image, "OBS_GOREV", popup);
			trayIcon.addActionListener(listener);
			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
			}
		} else {
			btnBuyult.doClick();
		}
		if (trayIcon != null) {
			trayIcon.setImage(Toolkit.getDefaultToolkit().getImage(OBS_GOREV.class.getResource("/obs/gorev/other/job-24.png")));
		}	
	}
}
