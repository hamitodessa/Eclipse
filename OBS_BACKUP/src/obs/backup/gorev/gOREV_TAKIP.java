package obs.backup.gorev;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import OBS_C_2025.BACKUP_GLOBAL;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({"serial","deprecation"})
public class gOREV_TAKIP extends JPanel implements Runnable{
	public TimerTask tt;
	
	JLabel lblNewLabel;
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

	Timer timerr = new Timer();  
	public JLabel lblemirISMI ;
	private static JLabel lblDosyaSayisi;
	private static JLabel lblSurucu;
	private static JLabel lblSonYedek;
	private static JLabel lblAciklama;
	private static JLabel lblDurum;
	private static JLabel lblBASLAMA;
	private static JLabel lblBITIS;
	private static JLabel lblKACDAKKA;
	private static JLabel lblSonDurum;
	private static JLabel lblGelecekYedekleme ;
	private static JLabel lblKalanZaman;

	BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	public gOREV_TAKIP(String emirADI) {

		eADI = emirADI;
		setBorder(new CompoundBorder(new EmptyBorder(5, 5, 0, 5), BorderFactory.createLineBorder(new Color(235, 235, 235))));
		setName(emirADI); 
		setPreferredSize(new Dimension(700,185));
		setLayout(null);

		JButton btnDuzelt = new JButton("Emir Duzelt");
		btnDuzelt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tt.cancel();
				OBS_BACKUP.gelenISIM = eADI ;
				OBS_BACKUP.btnNewButton_2.doClick();
				System.out.println("=*="+eADI);
				
			}
		});
		btnDuzelt.setName("btnDuzelt");
		btnDuzelt.setBounds(586, 26, 110, 23);
		add(btnDuzelt);

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tt.cancel();
			}
			
		});
		btnSil.setBounds(586, 69, 110, 23);
		add(btnSil);

		JButton btnYedekle = new JButton("Yedekle");
		btnYedekle.setBounds(586, 104, 110, 23);
		add(btnYedekle);

		JButton btnIndir = new JButton("Indir");
		btnIndir.setBounds(586, 133, 110, 23);
		add(btnIndir);

		lblemirISMI = new JLabel("New label");
		lblemirISMI.setForeground(new Color(0, 0, 128));
		lblemirISMI.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblemirISMI.setBounds(240, 19, 221, 16);
		lblemirISMI.setText(eADI);
		add(lblemirISMI);

		lblSonDurum = new JLabel(".....");
		lblSonDurum.setBounds(240, 47, 336, 14);
		add(lblSonDurum);

		lblDosyaSayisi = new JLabel("0");
		lblDosyaSayisi.setBounds(240, 68, 336, 14);
		add(lblDosyaSayisi);

		lblSurucu = new JLabel(".....");
		lblSurucu.setBounds(240, 89, 336, 14);
		add(lblSurucu);

		lblSonYedek = new JLabel(".....");
		lblSonYedek.setBounds(240, 111, 336, 14);
		add(lblSonYedek);

		lblGelecekYedekleme = new JLabel(".....");
		lblGelecekYedekleme.setBounds(240, 132, 148, 14);
		add(lblGelecekYedekleme);

		lblAciklama = new JLabel(".....");
		lblAciklama.setBounds(240, 153, 336, 14);
		add(lblAciklama);

		JLabel lblNewLabel_6 = new JLabel("~");
		lblNewLabel_6.setBounds(397, 132, 20, 14);
		add(lblNewLabel_6);

		lblKalanZaman = new JLabel("0");
		lblKalanZaman.setBounds(412, 132, 164, 14);
		add(lblKalanZaman);

		JLabel lblNewLabel = new JLabel("Son Durum");
		lblNewLabel.setBounds(131, 47, 83, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Dosya Sayisi");
		lblNewLabel_1.setBounds(131, 68, 99, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Surucu");
		lblNewLabel_1_1.setBounds(131, 89, 99, 14);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Son Yedekleme");
		lblNewLabel_1_2.setBounds(131, 111, 99, 14);
		add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Gel.Yedekleme");
		lblNewLabel_1_3.setBounds(131, 132, 99, 14);
		add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Aciklama");
		lblNewLabel_1_4.setBounds(131, 153, 99, 14);
		add(lblNewLabel_1_4);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(gOREV_TAKIP.class.getResource("/obs/backup/icons/icons8-backup-100.png")));
		lblNewLabel_2.setBounds(10, 66, 100, 100);
		add(lblNewLabel_2);

		lblDurum = new JLabel(".....");
		lblDurum.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDurum.setForeground(new Color(128, 0, 0));
		lblDurum.setBounds(35, 16, 195, 14);
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

		JButton btn1 = new JButton(".....");
		btn1.setVisible(false);
		btn1.setBounds(0, 11, 23, 23);
		add(btn1);

		JButton btn2 = new JButton(".....");
		btn2.setVisible(false);
		btn2.setBounds(0, 31, 23, 23);
		add(btn2);

		JButton btnyenidenBASLAT = new JButton(".....");
		btnyenidenBASLAT.setBounds(20, 31, 23, 23);
		btnyenidenBASLAT.setVisible(false);
		add(btnyenidenBASLAT);
		try {
			ilkBasla();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}


	@Override
	public void run() {
		 tt = new TimerTask() {  
			@Override  
			public void run() {  
				try {
					
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
					int hours   = (int) ((DakikaFarki / (1000*60*60)) % 24);
					lblKalanZaman.setText(String.format("%02d:%02d:%02d", hours,minutes ,seconds	));
					
					
					String simDI = df.format(aDate);
					if (simDI.equals(lblGelecekYedekleme.getText())) // YEDEKLEME ZAMANI 
					{
					    yedekSirasinaKoy();
					}
					
				} catch (Exception e) {

					OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, e.getMessage());
				}
			};  
		};
		timerr.schedule(tt,0,  100);;  

	}
	private void yedekSirasinaKoy() throws ClassNotFoundException, SQLException
	{
		tt.cancel();
		lblSonDurum.setText("Yedekleme Sirasina Konuldu");
	    bckp.log_kayit(eADI, new Date(), "Yedekleme Sirasina Konuldu.....");
	    OBS_BACKUP.gorevLER.add(eADI);
	}
	private void basla() throws ClassNotFoundException, ParseException, SQLException
	{
		GunLERE_BAK();
		if (! lblDurum.getText().equals("Pasiv Durumda"))
		{
			run();
		}
		
	}
	private void ilkBasla() throws ClassNotFoundException, SQLException
	{
		try
		{

			bckp.log_kayit(eADI, new Date(), "Emir ilk Load....");
			emirBILGIYUKLE();
			basla();
		}
		catch (Exception ex)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			bckp.log_kayit(eADI, new Date(), ex.getMessage());
			OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
			//hataDURUMU();
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
		ResultSet dtss = bckp.emir_tek(eADI);
		dtss.next();
		if(dtss.getString("SON_YUKLEME").equals("1900-01-01 00:00:00"))
		{
			lblSonYedek.setText("01.01.1900 00:00:00");
		}
		else {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss", Locale.ENGLISH);
			Date snyk =  formatter.parse(dtss.getString("SON_YUKLEME"));
			lblSonYedek.setText( df.format(snyk));
		}

		lblAciklama.setText(dtss.getString("EMIR_ACIKLAMA"));
		if ((Boolean) dtss.getBoolean("DURUM") == false)
		{
			lblDurum.setVisible(true);
			lblDurum.setText("Pasiv Durumda");
		}
		else
		{
			lblDurum.setVisible(false);
			lblDurum.setText(".....");
		}
		Boolean kontrol = (Boolean)   dtss.getBoolean("SON_DURUM");
		if (kontrol)
		{
			lblSonDurum.setForeground(Color.GREEN);
			lblSonDurum.setText("Yedeklendi");
		}
		else
		{
			lblSonDurum.setText(dtss.getString("MESAJ"));
			lblSonDurum.setForeground(Color.RED);
		}
		String insTANCE = dtss.getString("INSTANCE") ;
		boolean SQL_YEDEK_MI = dtss.getBoolean("SQL_YEDEK");
		if (SQL_YEDEK_MI)
		{
			if (bckp.ftp_neresi(eADI).equals("FTP"))
			{
				// btnINDIR.Enabled = true;
				System.out.println("248");
				ResultSet dtds ;
				dtds = bckp.db_liste(eADI);
				dtds.next();
				int count = dtds .getRow();
				if (count == 0 ) {  
					lblDosyaSayisi.setText("0 Adet Dosya");
				}
				else {
					lblDosyaSayisi.setText(count + " Adet Dosya - " + insTANCE);
					lblSurucu.setText(bckp.surucu_bilgi(eADI, "HOST") + "\\" + bckp.surucu_bilgi(eADI, "SURUCU").replace("/", "\\"));
				}
			}
			else
			{
				// btnINDIR.Enabled = false;
				ResultSet dtds ;
				dtds = bckp.db_liste(eADI);
				dtds.next();
				int count = dtds .getRow();

				if (count == 0 ) {  
					lblDosyaSayisi.setText("0 Adet Dosya");
					lblSurucu.setText(bckp.surucu_bilgi(eADI, "SURUCU_YER"));
				}
				else
				{
					lblDosyaSayisi.setText(count +  " Adet Dosya...");
					lblSurucu.setText(bckp.surucu_bilgi(eADI, "SURUCU_YER"));
				}
			}
		}
		else  //' Diger Dosya
		{
			if (bckp.ftp_neresi(eADI) == "FTP")
			{
				//btnINDIR.Enabled = true;
				ResultSet dtds ;
				dtds = bckp.diger_dosya_liste(lblemirISMI.getText());
				dtds.next();
				int count = dtds .getRow();
				if (count == 0 ) {  
					lblDosyaSayisi.setText("0 Adet Dosya");
				}
				else
				{
					lblDosyaSayisi.setText(count +  " Adet Dosya...");
					lblSurucu.setText(bckp.surucu_bilgi(eADI,"HOST") + "\\" + bckp.surucu_bilgi(eADI, "SURUCU").replace("/", "\\"));
				}
			}
			else
			{
				//btnINDIR.Enabled = false;
				ResultSet dtds = bckp.diger_dosya_liste(eADI);
				dtds.next();
				int count = dtds .getRow();
				if (count == 0 ) {  
					lblDosyaSayisi.setText("0 Adet Dosya");
					lblSurucu.setText(bckp.surucu_bilgi(eADI, "SURUCU_YER"));
				}
				else
				{
					lblDosyaSayisi.setText(count +  " Adet Dosya...");
					lblSurucu.setText(bckp.surucu_bilgi(lblemirISMI.getText(), "SURUCU_YER"));
				}
			}
		}
		ResultSet dysss = bckp.yedekleme_bilgi(lblemirISMI.getText());
		dysss .next();
		int count = dysss .getRow();
		if (count ==0 ) {  

		}

		else
		{
			lblBASLAMA.setText(dysss.getString("BASLAMA"));
			lblBITIS.setText(dysss.getString("BITIS"));
			lblKACDAKKA.setText(dysss.getString("SAAT"));
		}

	}
	private void GunLERE_BAK() throws ParseException, SQLException, ClassNotFoundException
	{

		LocalDate localDate = LocalDate.now(); // today
		java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		System.out.println(localDate.getDayOfWeek().name());   // Gun ISMI
		///

		int hangiGUNDEYIZ = (int) dayOfWeek.getValue();
		Boolean varmi = false;
		//

		ResultSet rss = bckp.yedekleme_bilgi(eADI);
		rss.next();
		for(int i = 0;i <= gunKONTROL.length - 1;i++)
		{
			gunKONTROL[i] = (boolean) rss.getBoolean(i + 2);
		}
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
		dtmBITIS =  formatter.parse(rss.getString("BITIS"));
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
		dtmBASLAMA = formatter.parse(rss.getString("BASLAMA"));

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

//		JSpinner tsnot = new JSpinner( new SpinnerDateModel() );
//		JSpinner.DateEditor de_timetsnot = new JSpinner.DateEditor(tsnot, "dd.MM.yyyy HH:mm:ss");
//		tsnot .setEditor(de_timetsnot);
//		Date q1 = new Date();
//		tsnot.setValue(q1);
//		tsNOW  = tsnot;
	
		dateNOW  = new Date();
		dateBIT = (Date) (tsbit.getValue());
		dateBAS = (Date) (tsbas.getValue());
		if (hangiGUNDEYIZ == 1)
		{
			hangiGUN_P_TESI(varmi, 0, false);
		}
		else if (hangiGUNDEYIZ == 2)
		{
			hangiGUN_SALI(varmi, 0, false);
		}
		if (hangiGUNDEYIZ == 3)
		{
			hangiGUN_CARS(varmi, 0, false);
		}
		if (hangiGUNDEYIZ == 4)
		{
			hangiGUN_PERS(varmi, 0, false);
		}
		if (hangiGUNDEYIZ == 5)
		{
			hangiGUN_CUMA(varmi, 0, false);
		}
		if (hangiGUNDEYIZ == 6)
		{
			hangiGUN_C_TESI(varmi, 0, false);
		}
		if (hangiGUNDEYIZ == 0)
		{
			hangiGUN_PAZAR(varmi, 0, false);
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

							//
							if (dateNOW == dateBIT || dateNOW.after(dateBIT))
							{
								hangiGUN_SALI(varmi, kacGUN + 1, true);
								return;
							}
							else if (dateNOW.after(dateBAS) && dateNOW.before(dateBIT) )
							{
								guniciKONTROL();
								return; 
							}
							else if (dateNOW.before(dateBAS) || dateNOW == dateBAS)
							{
								guniciKONTROL();
								return;
							}
							else
							{
								guniciKONTROL();

								bckp.log_kayit(eADI,new Date(), "p.tesi else  now=" + tsNOW + "=bas=" + tsbas + "=bit=" + tsbit);
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
							else if (dateNOW.after(dateBAS) && dateNOW.before(dateBIT) )
							{
								guniciKONTROL();
								return; 
							}
							else if (dateNOW.before(dateBAS) || dateNOW == dateBAS)
							{
								guniciKONTROL();
								return;
							}
							else
							{
								guniciKONTROL();

								bckp.log_kayit(eADI, new Date(), "sali else  now=" + tsNOW + "=bas=" + tsbas + "=bit=" + tsbit);
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
							else if (dateNOW.after(dateBAS) && dateNOW.before(dateBIT) )
							{
								guniciKONTROL();
								return; 
							}
							else if (dateNOW.before(dateBAS) || dateNOW == dateBAS)
							{
								guniciKONTROL();
								return;
							}
							else
							{
								guniciKONTROL();

								bckp.log_kayit(eADI, new Date(), "Cars else  now=" + tsNOW + "=bas=" + tsbas + "=bit=" + tsbit);
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
							else if (dateNOW.after(dateBAS) && dateNOW.before(dateBIT) )
							{
								guniciKONTROL();
								return;
							}
							else if (dateNOW.before(dateBAS) || dateNOW == dateBAS)
							{
								guniciKONTROL();
								return;
							}
							else
							{
								guniciKONTROL();

								bckp.log_kayit(eADI, new Date(), "Pers else  now=" + tsNOW + "=bas=" + tsbas + "=bit=" + tsbit);
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
		System.out.println("dk=="+gunKONTROL[4]);
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
							else if (dateNOW.after(dateBAS) && dateNOW.before(dateBIT) )
							{
								guniciKONTROL();
								return;
							}
							else if (dateNOW.before(dateBAS) || dateNOW == dateBAS)
							{

								guniciKONTROL();
								return;
							}
							else
							{
								guniciKONTROL();

								bckp.log_kayit(eADI, new Date(), "cuma else  now=" + tsNOW + "=bas=" + tsbas + "=bit=" + tsbit);
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
							else if (dateNOW.after(dateBAS) && dateNOW.before(dateBIT) )
							{
								guniciKONTROL();
								return; 
							}
							else if (dateNOW.before(dateBAS) || dateNOW == dateBAS)
							{
								guniciKONTROL();
								return;
							}
							else
							{
								guniciKONTROL();

								bckp.log_kayit(eADI, new Date(), "Ctesi else  now=" + tsNOW + "=bas=" + tsbas + "=bit=" + tsbit);
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
							else if (dateNOW.after(dateBAS) && dateNOW.before(dateBIT) )
							{
								guniciKONTROL();
								return;
							}
							else if (dateNOW.before(dateBAS) || dateNOW == dateBAS)
							{
								guniciKONTROL();
								return;
							}
							else
							{
								guniciKONTROL();

								bckp.log_kayit(eADI, new Date(), "pazar else  now=" + tsNOW + "=bas=" + tsbas + "=bit=" + tsbit);
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
	private void gunEKLE(Date basLA, int gun)
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
	private void guniciKONTROL() throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
		Date basLAMA =  formatter.parse(lblBASLAMA.getText());
		Date basLAMA_1 = new Date();
		basLAMA_1 = new Date(new Date().getYear()  ,  new Date().getMonth()  ,  new Date().getDate()  ,  basLAMA.getHours() ,  basLAMA.getMinutes() , basLAMA.getSeconds());
		formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
		lblGelecekYedekleme.setText( formatter.format(basLAMA_1));
		Date gelYEDEK =  formatter.parse(lblGelecekYedekleme.getText());// Gelecek Yedekleme
		Date sonYEDE =  formatter.parse(lblSonYedek.getText());// Son Yedekleme
		Date sonYEDEK = new Date();
		sonYEDEK = new Date(gelYEDEK.getYear() , gelYEDEK.getMonth() , gelYEDEK.getDate() , sonYEDE.getHours() , sonYEDE.getMinutes() , sonYEDE.getSeconds());
		formatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
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