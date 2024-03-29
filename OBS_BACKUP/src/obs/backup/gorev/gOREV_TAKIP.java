package obs.backup.gorev;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.db_List;
import OBS_C_2025.emir_bilgiler;
import OBS_C_2025.ftp_bilgiler;
import OBS_C_2025.yedekleme_bilgiler;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.awt.event.ActionEvent;

@SuppressWarnings({"serial","deprecation"})
public  class gOREV_TAKIP extends JPanel { 
	public TimerTask tt;
	Timer timerr;
	JLabel lblNewLabel;
	boolean timerDURDUR = false;
	private String eADI="";
	boolean[] gunKONTROL = { false, false, false, false, false, false, false };
	JSpinner tsbas = new JSpinner();
	JSpinner tsbit = new JSpinner();
	JSpinner tsNOW = new JSpinner();
	Date dtmBASLAMA;
	Date dtmBITIS;


	Date dateNOW ;
	Date dateBIT ;
	Date dateBAS;

	private JButton btn1 ;
	
	public JButton btnStart ;
	public JButton btnStop;
	public JButton btnYedekle;
	
	public JLabel lblemirISMI ;
	private JLabel lblDosyaSayisi;
	private JLabel lblSurucu;
	private JLabel lblSonYedek;
	private JLabel lblAciklama;
	private JLabel lblDurum;
	private JLabel lblBASLAMA;
	private JLabel lblBITIS;
	private JLabel lblKACDAKKA;
	private JLabel lblSonDurum;
	private JLabel lblGelecekYedekleme ;
	private JLabel lblKalanZaman;
	private JLabel lblNewLabel_6 ;

	BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	public JButton btnyenidenBASLAT;
	public JButton btnHepsiPasivden;
	public gOREV_TAKIP(String emirADI) {
		
		eADI = emirADI;
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		//setBorder(new CompoundBorder(new EmptyBorder(5, 5, 0, 5), BorderFactory.createLineBorder(new Color(235, 235, 235))));
		setName(emirADI); 
		setPreferredSize(new Dimension(800,175));
		setLayout(null);

		JButton btnDuzelt = new JButton( dilSecenek.dil(OBS_BACKUP.dILS,"Emir Duzelt")    );
		btnDuzelt.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDuzelt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (OBS_BACKUP.backupTime ) return;
				if(tt != null)
				{
					timerDURDUR=true;
					tt.cancel();
				}
				OBS_BACKUP.gelenISIM = eADI ;
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				OBS_BACKUP.emirAnaGirisPanel.emirDOLDUR();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnDuzelt.setName("btnDuzelt");
		btnDuzelt.setBounds(675, 26, 110, 25);
		add(btnDuzelt);

		JButton btnSil = new JButton( dilSecenek.dil(OBS_BACKUP.dILS,"Sil")   );
		btnSil.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (OBS_BACKUP.backupTime ) return;
				int g = JOptionPane.showOptionDialog(null, dilSecenek.dil(OBS_BACKUP.dILS,"Emir Dosyadan Silinecek ..?")  ,
						"OBS BACKUP ", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, 	new String[] {"Yes", "No"}, "No");
				if(g ==  1) {
					return;
				}
				timerDURDUR = true;
				tt.cancel();
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					OBS_BACKUP.emirSil(eADI);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSil.setBounds(675, 75, 110, 25);
		add(btnSil);

		btnYedekle = new JButton(dilSecenek.dil(OBS_BACKUP.dILS,"Yedekle") );
		btnYedekle.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnYedekle.setName("btnYedekle");
		btnYedekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (OBS_BACKUP.backupTime ) return;
					if(lblDurum.getText().equals( dilSecenek.dil(OBS_BACKUP.dILS,"Pasiv Durumda") )) return;
					yedekSirasinaKoy();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnYedekle.setBounds(675, 104, 110, 25);
		add(btnYedekle);

		lblemirISMI = new JLabel("New label");
		lblemirISMI.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblemirISMI.setBounds(240, 19, 400, 16);
		lblemirISMI.setText(eADI);
		add(lblemirISMI);

		lblSonDurum = new JLabel(".....");
		lblSonDurum.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSonDurum.setBounds(240, 47, 400, 14);
		lblSonDurum.setName("lblSonDurum"); 
		add(lblSonDurum);

		lblDosyaSayisi = new JLabel("0");
		lblDosyaSayisi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDosyaSayisi.setBounds(240, 68, 200, 14);
		add(lblDosyaSayisi);

		lblSurucu = new JLabel(".....");
		lblSurucu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSurucu.setBounds(240, 89, 400, 14);
		add(lblSurucu);

		lblSonYedek = new JLabel(".....");
		lblSonYedek.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSonYedek.setBounds(240, 111, 175, 14);
		lblSonYedek.setName("lblSonYedek"); 
		add(lblSonYedek);

		lblGelecekYedekleme = new JLabel(".....");
		lblGelecekYedekleme.setBounds(240, 132, 175, 14);
		lblGelecekYedekleme.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblGelecekYedekleme);

		lblAciklama = new JLabel(".....");
		lblAciklama.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAciklama.setBounds(240, 153, 520, 14);
		add(lblAciklama);

		lblNewLabel_6 = new JLabel("~");
		lblNewLabel_6.setBounds(450, 132, 20, 14);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblNewLabel_6);

		lblKalanZaman = new JLabel("0");
		lblKalanZaman.setBounds(465, 132, 164, 14);
		lblKalanZaman.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblKalanZaman);

		JLabel lblNewLabel = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS,"Son Durum") );
		lblNewLabel.setBounds(131, 47, 83, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS,"Dosya Sayisi") );
		lblNewLabel_1.setBounds(131, 68, 99, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS,"Surucu") );
		lblNewLabel_1_1.setBounds(131, 89, 99, 14);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS,"Son Yedekleme") );
		lblNewLabel_1_2.setBounds(131, 111, 99, 14);
		add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS,"Gel.Yedekleme") );
		lblNewLabel_1_3.setBounds(131, 132, 99, 14);
		add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS,"Aciklama"));
		lblNewLabel_1_4.setBounds(131, 153, 99, 14);
		add(lblNewLabel_1_4);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(gOREV_TAKIP.class.getResource("/obs/backup/icons/icons8-backup-100.png")));
		lblNewLabel_2.setBounds(20, 65, 100, 100);
		add(lblNewLabel_2);

		lblDurum = new JLabel("");
		lblDurum.setName("lblDurum");
		lblDurum.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDurum.setForeground(Color.RED);
		lblDurum.setBounds(40, 16, 195, 14);
		add(lblDurum);

		lblBASLAMA = new JLabel(".....");
		lblBASLAMA.setVisible(false);
		lblBASLAMA.setBounds(43, 39, 18, 18);
		add(lblBASLAMA);

		lblBITIS = new JLabel(".....");
		lblBITIS.setVisible(false);
		lblBITIS.setBounds(69, 39, 18, 18);
		add(lblBITIS);

		lblKACDAKKA = new JLabel(".....");
		lblKACDAKKA.setVisible(false);
		lblKACDAKKA.setBounds(97, 39, 18, 18);
		add(lblKACDAKKA);

		btn1 = new JButton();
		btn1.setName("⇈");
		btn1.setIcon(new ImageIcon(gOREV_TAKIP.class.getResource("/obs/backup/icons/double-up-16.png")));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btn1.getName().equals("⇈"))
				{
					OBS_BACKUP.gorevYukari(eADI);
					btn1.setIcon(new ImageIcon(gOREV_TAKIP.class.getResource("/obs/backup/icons/down-16.png")));
					btn1.setName("⇊");
				}
				else
				{
					OBS_BACKUP.gorevAsagi(emirADI);
					btn1.setName( "⇈");
				}
			}
		});
		btn1.setVisible(true);
		btn1.setBounds(10, 11, 23, 23);
		add(btn1);

		btnStart = new JButton("");
		btnStart.setName("btnStart");
		btnStart.setToolTipText("Baslat");
		btnStart.setBounds(740, 133, 45, 25);
		btnStart.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/start16.png")));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (OBS_BACKUP.backupTime ) return;
				if(! lblDurum.getText().equals(dilSecenek.dil(OBS_BACKUP.dILS,"Pasiv Durumda") ))
				{
					timerDURDUR = false;
					run();
				}
				else 
				{
					OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING,emirADI + 
							dilSecenek.dil(OBS_BACKUP.dILS,"- Isimli Emir Pasiv Durumda !!! ,  Oncelikle aktivlestirin...."));       
				}
			}
		});
		add(btnStart);
		
		btnStop = new JButton("");
		btnStop.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/stop16.png")));
		btnStop.setName("btnStop");
		btnStop.setToolTipText("Durdur");
		btnStop.setBounds(675, 133, 45, 25);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (OBS_BACKUP.backupTime ) return;
				if(tt != null)
				{
					timerDURDUR = true;
				}
			}
		});
		add(btnStop);

		btnyenidenBASLAT = new JButton(".....");
		btnyenidenBASLAT.setBounds(20, 31, 23, 23);
		btnyenidenBASLAT.setVisible(false);
		btnyenidenBASLAT.setName("btnyenidenBASLAT");
		btnyenidenBASLAT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ilkBasla();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		add(btnyenidenBASLAT);
		
		btnHepsiPasivden = new JButton("");
		btnHepsiPasivden.setBounds(20, 31, 23, 23);
		btnHepsiPasivden.setVisible(false);
		btnHepsiPasivden.setName("btnHepsiPasivden");
		btnHepsiPasivden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timerDURDUR=true;
				try {
					ilkBasla();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		add(btnHepsiPasivden);
		
		addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(getPreferredSize().height == 175)
				{
					btn1.setIcon(new ImageIcon(gOREV_TAKIP.class.getResource("/obs/backup/icons/double-up-16.png")));
					lblNewLabel_6.setBounds(450, 132, 20, 14);
					lblKalanZaman.setBounds(465, 132, 164, 14);
				}
				else {
					btn1.setIcon(new ImageIcon(gOREV_TAKIP.class.getResource("/obs/backup/icons/down-16.png")));
					lblNewLabel_6.setBounds(450, 47, 20, 14);
					lblKalanZaman.setBounds(465, 47, 164, 14);
				}
			}
		});
		try {
			tt= null;
			ilkBasla();
		} catch (Exception ex) {
		}
	}
	public void run() {
		if (lblDurum.getText().equals(dilSecenek.dil(OBS_BACKUP.dILS,"Pasiv Durumda") ))
		{
			timerDURDUR = true;
			return;
		}
		timerr = new Timer();  
		tt = new TimerTask() {  
			@Override  
			public void run() {  
				try {
					if(timerDURDUR == true)
					{
						tt.cancel();
						return;
					}
					JSpinner tsnot = new JSpinner( new SpinnerDateModel() );
					JSpinner.DateEditor de_timetsnot = new JSpinner.DateEditor(tsnot, "dd.MM.yyyy HH:mm:ss");
					tsnot .setEditor(de_timetsnot);
					Date q1 = new Date();
					tsnot.setValue(q1);
					Date aDate = 	(Date) (tsnot.getValue());
					SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
					Date dt = new Date();
					dt =  df.parse(lblGelecekYedekleme.getText());//
					long DakikaFarki = dt.getTime() - aDate.getTime();
					int seconds = (int) (DakikaFarki / 1000) % 60 ;
					int minutes = (int) ((DakikaFarki / (1000*60)) % 60);
					//int hours   = (int) ((DakikaFarki / (1000*60*60)) % 24);
					int hours = (int)TimeUnit.HOURS.convert(DakikaFarki, TimeUnit.MILLISECONDS); //Saat Goter
					//int days = (int)TimeUnit.DAYS.convert(DakikaFarki,TimeUnit.MILLISECONDS); // Gun Goster
					//lblKalanZaman.setText(String.format("%d:%02d:%02d:%02d",days, hours,minutes ,seconds));//Gun Goster
					lblKalanZaman.setText(String.format("%02d:%02d:%02d", hours,minutes ,seconds)); // Saat Goster
					String simDI = df.format(aDate);
					if (simDI.equals(lblGelecekYedekleme.getText())) // YEDEKLEME ZAMANI 
					{
						yedekSirasinaKoy();
					}
					else 
					{
						if(Integer.signum((int) DakikaFarki) == -1)
							eksiTARIH();
					}
				} catch (Exception e) 
				{
					timerDURDUR=true;
					tt.cancel();
					try 
					{
						emirBILGIYUKLE();
						basla();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			};  
		};
		timerr.schedule(tt,0,  1000);;  
	}
	private void eksiTARIH() throws ClassNotFoundException, SQLException
	{
		bckp.log_kayit(eADI, new Date(),"Eksi Tarih" );
		if(tt != null)
		{
			timerDURDUR = true;
			tt.cancel();
		}
		ilkBasla();
	}
	private void yedekSirasinaKoy() throws ClassNotFoundException, SQLException, InterruptedException
	{
		if(tt != null)
		{
			timerDURDUR=true;
			tt.cancel();
		}
		bckp.log_kayit(eADI, new Date(),(int) ProcessHandle.current().pid()  + " / "+ dilSecenek.dil(OBS_BACKUP.dILS,"Yedekleme Sirasina Konuldu") );
		lblSonDurum.setText(dilSecenek.dil(OBS_BACKUP.dILS,"Yedekleme Sirasina Konuldu"));
		lblSonDurum.setForeground(Color.GREEN);
	    OBS_BACKUP.gorevLER.add(eADI);
	}
	private void basla() throws ClassNotFoundException, ParseException, SQLException
	{
		GunLERE_BAK();
		if (! lblDurum.getText().equals(dilSecenek.dil(OBS_BACKUP.dILS,"Pasiv Durumda")))
			run();
	}
	public void ilkBasla() 
	{
		try
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			emirBILGIYUKLE();
			basla();
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		catch (Exception ex)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			try {
				bckp.log_kayit(eADI, new Date(), ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void emirBILGIYUKLE() throws SQLException, ClassNotFoundException, ParseException
	{
		if (eADI.equals(""))
		{
			Component[] components = OBS_BACKUP.container.getComponents();
			for (Component component : components) {
				if (component.getName().toString().equals(eADI)) {
					OBS_BACKUP.container.remove(component);
				}
			}
		}
		 List<emir_bilgiler> emirBilgiler =  bckp.emir_tek(eADI);
		if(emirBilgiler.get(0).getSON_YUKLEME().toString().equals("Mon Jan 01 00:00:00 TRT 1900"))
		{
			lblSonYedek.setText("01.01.1900 00:00:00");
			lblSonYedek.setVisible(false);
		}
		else 
		{
			lblSonYedek.setVisible(true);
			SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
			Date snyk =  formatter.parse(emirBilgiler.get(0).getSON_YUKLEME().toString());
			lblSonYedek.setText( df.format(snyk));
		}
		lblAciklama.setText(emirBilgiler.get(0).getEMIR_ACIKLAMA());
		if (emirBilgiler.get(0).isDURUM() == false)
		{
			lblDurum.setVisible(true);
			timerDURDUR = true;
			lblDurum.setText(dilSecenek.dil(OBS_BACKUP.dILS,"Pasiv Durumda"));
		}
		else
		{
			lblDurum.setVisible(false);
			lblDurum.setText("");
			timerDURDUR = false;
		}
		Boolean kontrol = emirBilgiler.get(0).isSON_DURUM();
		if (kontrol)
		{
			lblSonDurum.setForeground(Color.GREEN);
			lblSonDurum.setText(dilSecenek.dil(OBS_BACKUP.dILS,"Yedeklendi"));
			lblSonDurum.setToolTipText("");
		}
		else
		{
			lblSonDurum.setText(emirBilgiler.get(0).getMESAJ());
			lblSonDurum.setForeground(Color.RED);
			lblSonDurum.setToolTipText(lblSonDurum.getText());
		}
		String insTANCE = emirBilgiler.get(0).getINSTANCE() ;
		boolean SQL_YEDEK_MI = emirBilgiler.get(0).isSQL_YEDEK();
		List<ftp_bilgiler> ftpBilgi = new ArrayList<ftp_bilgiler>();
		ftpBilgi = bckp.ftp_bilgi(eADI);
		
		if (SQL_YEDEK_MI)
		{
			if (ftpBilgi.get(0).getNERESI().equals("FTP"))
			{
				List<String> dbliste = bckp.db_liste(eADI);
				if (dbliste.size() == 0 ) {  
					lblDosyaSayisi.setText(dilSecenek.dil(OBS_BACKUP.dILS,"0 Adet Dosya") );
				}
				else 
				{
					lblDosyaSayisi.setText(dbliste.size() + dilSecenek.dil(OBS_BACKUP.dILS," Adet Dosya - ")  + insTANCE);
					lblSurucu.setText(ftpBilgi.get(0).getHOST() + File.separator + ftpBilgi.get(0).getSURUCU().replace("/", File.separator));
				}
			}
			else
			{
				List<String> dbliste = bckp.db_liste(eADI);
				if (dbliste.size() == 0 ) 
				{  
					lblDosyaSayisi.setText("0 Adet Dosya");
					lblSurucu.setText(ftpBilgi.get(0).getSURUCU_YER());
				}
				else
				{
					lblDosyaSayisi.setText(dbliste.size() + dilSecenek.dil(OBS_BACKUP.dILS," Adet Dosya")  );
					lblSurucu.setText(ftpBilgi.get(0).getSURUCU_YER());
				}
			}
		}
		else  //' Diger Dosya
		{
			if (ftpBilgi.get(0).getNERESI().equals("FTP"))
			{
				List<db_List> dosliste = bckp.diger_dosya_liste(lblemirISMI.getText());
				if (dosliste.size() == 0 ) {  
					lblDosyaSayisi.setText(dilSecenek.dil(OBS_BACKUP.dILS,"0 Adet Dosya"));
				}
				else
				{
					lblDosyaSayisi.setText(dosliste.size() +  dilSecenek.dil(OBS_BACKUP.dILS," Adet Dosya"));
					lblSurucu.setText(ftpBilgi.get(0).getHOST() + File.separator + ftpBilgi.get(0).getSURUCU().replace("/", File.separator));
				}
			}
			else
			{
				List<db_List> dosliste = bckp.diger_dosya_liste(lblemirISMI.getText());
				if (dosliste.size() == 0 ) 
				{  
					lblDosyaSayisi.setText("0 Adet Dosya");
					lblSurucu.setText(ftpBilgi.get(0).getSURUCU_YER());
				}
				else
				{
					lblDosyaSayisi.setText(dosliste.size() +   dilSecenek.dil(OBS_BACKUP.dILS," Adet Dosya"));
					lblSurucu.setText(ftpBilgi.get(0).getSURUCU_YER());
				}
			}
		}
		 List<yedekleme_bilgiler> yedekBilgiler =  bckp.yedekleme_bilgi(lblemirISMI.getText());
		 if ( yedekBilgiler.size() == 0) {
		}

		else
		{
			SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
			Date basl =  formatter.parse(yedekBilgiler.get(0).getBASLAMA().toString());
			Date bitis =  formatter.parse(yedekBilgiler.get(0).getBITIS().toString());
			lblBASLAMA.setText(df.format(basl));
			lblBITIS.setText(df.format(bitis));
			lblKACDAKKA.setText(yedekBilgiler.get(0).getSAAT());
		}
	}
	private void GunLERE_BAK() throws ParseException, SQLException, ClassNotFoundException
	{
		LocalDate localDate = LocalDate.now(); // today
		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		//System.out.println(localDate.getDayOfWeek().name());   // Gun ISMI

		int hangiGUNDEYIZ = (int) dayOfWeek.getValue();
		Boolean varmi = false;
		List<yedekleme_bilgiler> yedekBilgiler =  bckp.yedekleme_bilgi(lblemirISMI.getText());
		gunKONTROL[0] = yedekBilgiler.get(0).isP_TESI();
		gunKONTROL[1] = yedekBilgiler.get(0).isSALI();
		gunKONTROL[2] = yedekBilgiler.get(0).isCARS();
		gunKONTROL[3] = yedekBilgiler.get(0).isPERS();
		gunKONTROL[4] = yedekBilgiler.get(0).isCUMA();
		gunKONTROL[5] = yedekBilgiler.get(0).isC_TESI();
		gunKONTROL[6] = yedekBilgiler.get(0).isPAZAR();

		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		dtmBITIS = formatter.parse(yedekBilgiler.get(0).getBITIS().toString());
		//
		JSpinner tsbtt = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor de_timeBaslangic = new JSpinner.DateEditor(tsbtt, "HH:mm");
		tsbtt .setEditor(de_timeBaslangic);
		Date qweDate = new Date();
		qweDate.setHours(dtmBITIS.getHours());
		qweDate.setMinutes(dtmBITIS.getMinutes());
		qweDate.setSeconds(0);
		tsbtt.setValue(qweDate);
		tsbit = tsbtt ;
		dtmBASLAMA = formatter.parse(yedekBilgiler.get(0).getBASLAMA().toString());
		////
		JSpinner tsbass = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor de_bass = new JSpinner.DateEditor(tsbass, "HH:mm");
		tsbass .setEditor(de_bass);
		qweDate = new Date();
		qweDate.setHours(dtmBASLAMA.getHours());
		qweDate.setMinutes(dtmBASLAMA.getMinutes());
		qweDate.setSeconds(0);
		tsbass.setValue(qweDate);
		tsbas = tsbass;
		dateNOW  = new Date();
		dateBIT = (Date) (tsbit.getValue());
		dateBAS = (Date) (tsbas.getValue());
		
		switch (hangiGUNDEYIZ) 
		{
		case 1 -> hangiGUN_P_TESI(varmi, 0, false);
		case 2 -> hangiGUN_SALI(varmi, 0, false);
		case 3 -> hangiGUN_CARS(varmi, 0, false);
		case 4 -> hangiGUN_PERS(varmi, 0, false);
		case 5 -> hangiGUN_CUMA(varmi, 0, false);
		case 6 -> hangiGUN_C_TESI(varmi, 0, false);
		case 7 -> hangiGUN_PAZAR(varmi, 0, false);
		}
	}
	private void hangiGUN_P_TESI(Boolean varmi, int kacGUN, Boolean disardan) throws ClassNotFoundException, SQLException, ParseException
	{
		if (gunKONTROL[0])
		{
			for (int i = 2; i < 9; i++) 
			{
				if (gunKONTROL[i-2])
				{
					if (disardan == false)
					{
						if (kacGUN == 0)
						{
							if (dateNOW == dateBIT || dateNOW.after(dateBIT))
							{
								hangiGUN_SALI(varmi, kacGUN + 1, true);
								return;
							}
							else
							{
								guniciKONTROL();
								return;
							}
						}
					}
					gunEKLE(dtmBASLAMA, kacGUN);
					return;
				}
			}
		}
		else
		{ 
			hangiGUN_SALI(varmi, kacGUN + 1, false);
		}
	}
	private void hangiGUN_SALI(Boolean varmi, int kacGUN, Boolean disardan) throws ClassNotFoundException, SQLException, ParseException
	{
		if (gunKONTROL[1])
		{
			for (int i = 3; i < 9; i++)
			{
				if (gunKONTROL[i-2])
				{
					if (disardan == false)
					{
						if (kacGUN == 0)
						{
							if (dateNOW == dateBIT || dateNOW.after(dateBIT))
							{
								hangiGUN_CARS(varmi, kacGUN + 1, true);
								return;
							}
							else
							{
								guniciKONTROL();
								return;
							}
						}
					}
					gunEKLE(dtmBASLAMA, kacGUN);
					return;
				}
			}
		}
		else
		{
			hangiGUN_CARS(varmi, kacGUN + 1, false);
		}
	}
	private void hangiGUN_CARS(Boolean varmi, int kacGUN, Boolean disardan) throws ClassNotFoundException, SQLException, ParseException
	{
		if (gunKONTROL[2])  // Persembeden Say 
		{
			for (int i = 4; i < 9; i++)
			{
				if (gunKONTROL[i-2])
				{
					if (disardan == false)
					{
						if (kacGUN == 0)
						{
							if (dateNOW == dateBIT || dateNOW.after(dateBIT))
							{
								hangiGUN_PERS(varmi, kacGUN + 1, true);
								return;
							}
							else
							{
								guniciKONTROL();
								return;
							}
						}
					}
					gunEKLE(dtmBASLAMA, kacGUN);
					return;
				}
			}
		}
		else
		{
			hangiGUN_PERS(varmi, kacGUN + 1, false);
		}
	}
	private void hangiGUN_PERS(Boolean varmi, int kacGUN, Boolean disardan) throws ClassNotFoundException, SQLException, ParseException
	{
		if (gunKONTROL[3])
		{
			for (int i = 5; i < 9; i++)
			{
				if (gunKONTROL[i-2])
				{
					if (disardan == false)
					{
						if (kacGUN == 0)
						{
							if (dateNOW == dateBIT || dateNOW.after(dateBIT))
							{
								hangiGUN_CUMA(varmi, kacGUN + 1, true);
								return;
							}
							else 
							{
								guniciKONTROL();
								return;
							}
						}
					}
					gunEKLE(dtmBASLAMA, kacGUN);
					return; 
				}
			}
		}
		else
		{
			hangiGUN_CUMA(varmi, kacGUN + 1, false);
		}
	}
	private void hangiGUN_CUMA(Boolean varmi, int kacGUN, Boolean disardan) throws ClassNotFoundException, SQLException, ParseException
	{
		if (gunKONTROL[4])  //  C.tesiden Say 
		{
			for (int i = 6; i < 9; i++)
			{

				if (gunKONTROL[i-2])
				{
					if (disardan == false)
					{
						if (kacGUN == 0)
						{

							if (dateNOW == dateBIT || dateNOW.after(dateBIT))
							{
								hangiGUN_C_TESI(varmi, kacGUN + 1, true);
								return;
							}
							else
							{
								guniciKONTROL();
								return;
							}
						}
					}
					gunEKLE(dtmBASLAMA, kacGUN);
					return;
				}
			}
		}
		else
		{
			hangiGUN_C_TESI(varmi, kacGUN + 1, false);
		}
	}
	private void hangiGUN_C_TESI(Boolean varmi, int kacGUN, Boolean disardan) throws ClassNotFoundException, SQLException, ParseException
	{
		if (gunKONTROL[5]) // 
		{
			for (int i = 7; i < 9; i++)
			{
				if (gunKONTROL[i-2])
				{
					if (disardan == false)
					{
						if (kacGUN == 0)
						{
							if (dateNOW == dateBIT || dateNOW.after(dateBIT))
							{
								hangiGUN_PAZAR(varmi, kacGUN + 1, true);
								return;
							}
							else
							{
								guniciKONTROL();
								return;
							}
						}
					}
					gunEKLE(dtmBASLAMA, kacGUN);
					return;
				}
			}
		}
		else
		{
			hangiGUN_PAZAR(varmi, kacGUN + 1, false);
		}
	}
	private void hangiGUN_PAZAR(Boolean varmi, int kacGUN, Boolean disardan) throws ClassNotFoundException, SQLException, ParseException
	{
		if (gunKONTROL[6])
		{
			for (int i = 8; i < 9; i++)
			{
				if (gunKONTROL[i-2])
				{
					if (disardan == false)
					{
						if (kacGUN == 0)
						{
							if (dateNOW == dateBIT || dateNOW.after(dateBIT))
							{
								hangiGUN_P_TESI(varmi, kacGUN + 1, true);
								return;
							}
							else
							{
								guniciKONTROL();
								return;
							}
						}
					}
					gunEKLE(dtmBASLAMA, kacGUN);
					return;
				}
			}
		}
		else
		{
			hangiGUN_P_TESI(varmi, kacGUN + 1, true);
		}
	}
	private void gunEKLE(Date basLA, int gun) throws ClassNotFoundException, SQLException
	{
		Date date1 = basLA; 
		Date dt = new Date();
		dt = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), date1.getHours(), date1.getMinutes(), 0);
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, gun);
		dt = c.getTime();
		Date now = dt ;
		Date dtt = new Date();
		dtt = new Date(now.getYear(), now.getMonth(), now.getDate(), dt.getHours(), dt.getMinutes(), 0);
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		lblGelecekYedekleme.setText( df.format(dtt));
	}
	private void guniciKONTROL() throws ParseException, ClassNotFoundException, SQLException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
		Date basLAMA =  formatter.parse(lblBASLAMA.getText());
		Date basLAMA_1 = new Date();
		basLAMA_1 = new Date(new Date().getYear()  ,  new Date().getMonth()  ,  new Date().getDate()  ,  basLAMA.getHours() ,  basLAMA.getMinutes() , basLAMA.getSeconds());
		formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
		lblGelecekYedekleme.setText( formatter.format(basLAMA_1));
		Date gelYEDEK =  formatter.parse(lblGelecekYedekleme.getText());// Gelecek Yedekleme
		Date sonYEDE =  formatter.parse(lblSonYedek.getText());// Son Yedekleme
		Date sonYEDEK = new Date();
		sonYEDEK = new Date(gelYEDEK.getYear() , gelYEDEK.getMonth() , gelYEDEK.getDate() , sonYEDE.getHours() , sonYEDE.getMinutes() , sonYEDE.getSeconds());
		formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
		Date biTIS  = formatter.parse(lblBITIS.getText());
		Date biTISS = new Date();
		biTISS = new Date(sonYEDEK.getYear() ,sonYEDEK.getMonth() , sonYEDEK.getDate() , biTIS.getHours() , biTIS.getMinutes() , biTIS.getSeconds());
		do
		{
			if (new Date().before(basLAMA_1))
			{
				Date baSL =  formatter.parse(lblBASLAMA.getText());
				gunEKLE(new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate() , baSL.getHours(), baSL.getMinutes(), 0), 0);
				return;
			}
			Calendar c = Calendar.getInstance(); 
			c.setTime(basLAMA_1); 
			c.add(Calendar.MINUTE,Integer.valueOf(lblKACDAKKA.getText()));
			basLAMA_1 = c.getTime();
			Date suAN = new Date(new Date().getYear(), new Date().getMonth(),new Date().getDate(), new Date().getHours(), new Date().getMinutes(), 0);
			if (basLAMA_1.after(suAN))
			{
				if (basLAMA_1.after(biTISS)  || basLAMA_1 == biTISS)
				{
					if (new Date().before(biTISS))
					{
						gunEKLE(formatter.parse(lblBITIS.getText()), 0);
						return;
					}
					else
					{
						gunEKLE(formatter.parse(lblBASLAMA.getText()), 1);
						return;
					}
				}
				else
				{
					gunEKLE(basLAMA_1, 0);
					return;
				}
			}
		} while (basLAMA_1.before(biTISS) || basLAMA_1 == biTISS);
	}
}