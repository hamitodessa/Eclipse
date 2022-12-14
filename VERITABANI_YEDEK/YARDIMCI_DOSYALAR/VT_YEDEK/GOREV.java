package VT_YEDEK;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import OBS_C_2025.SQL_BACKUP;

public class GOREV {

	static SQL_BACKUP sqll = new SQL_BACKUP();
	static JLabel lblgel ;
	static  JLabel lblson ;
	static JPanel p ;
	static ResultSet rss  ;
	static boolean varmi = false;
	Date    sonrakitar = new Date();
	static int hangiGUNDEYIZ ;
	public static String denemADI = "" ;
	static Timer timer ;
	@SuppressWarnings("static-access")
	public static  JPanel getShowRoomPanel(String emirAdi ,String sonDurum,int dosyaSayisi,String sonYEDEK , String gelYEDEK,
			String acikLAMA,String durUM,String surUCU) throws InterruptedException, NumberFormatException, ClassNotFoundException, SQLException, ParseException
	{
		p = new JPanel(new GridBagLayout());
		p.setName( emirAdi);
		denemADI = emirAdi ;
		p.setBorder(new TitledBorder(emirAdi));
		((javax.swing.border.TitledBorder) p.getBorder()).setTitleFont(new Font("Arial", Font.BOLD, 14));
		((TitledBorder) p.getBorder()).setTitleColor(Color.BLUE);
		//  jPanel1.repaint();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;
		p.add(new JLabel("Son Durum                      		" ), gbc);
		gbc.gridx = 1 ;
		
		JLabel sDURUM = new JLabel(sonDurum);
	
		p.add(sDURUM, gbc);

		gbc.gridx = 2;
		p.add(new JLabel("          Son Yedekleme " ) , gbc);

		gbc.gridx = 3;
		lblson = new JLabel(sonYEDEK );
		lblson.setForeground(Color.green.darker());
		p.add(lblson, gbc);

		gbc.gridy = 1;
		gbc.gridx = 0;
		p.add(new JLabel("Dosya Sayisi  "), gbc);

		gbc.gridx = 1;
		p.add(new JLabel(Integer.toString(dosyaSayisi) + " Adet Dosya" ), gbc);

		gbc.gridx = 2;
		p.add(new JLabel("          Gelecek Yedekleme " ), gbc);
		gbc.gridx = 3;

		lblgel = new JLabel(gelYEDEK );
		lblgel.setName("lblgel");
		lblgel.setForeground(Color.RED);
		p.add(lblgel, gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.weightx = 0.0;
		gbc.gridwidth = 2;
		p.add(new JLabel("Surucu / FTP :" ), gbc);

		gbc.gridx = 1;
		p.add(new JLabel(surUCU), gbc);

		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.weightx = 0.0;
		gbc.gridwidth = 3;
		p.add(new JLabel("Aciklama:" ), gbc);

		gbc.gridx = 1;
		p.add(new JLabel(acikLAMA ), gbc);


		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.weightx = 0.0;
		gbc.gridwidth = 3;
		p.add(new JLabel( ), gbc);

		gbc.gridx = 0;
		p.add(new JLabel("Durum" ), gbc);
		gbc.gridx = 1;
		p.add(new JLabel(durUM), gbc);

		gbc.gridy = 6;
		gbc.gridx = 0;
		JButton btnNewButton = new JButton("Emir Duzelt");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VT_ANA_CLASS.yENI_EMIR = false ;
				VT_ANA_CLASS.EMIR_ADI= emirAdi;
				BASLA.durdur();
				EMIR emr = new EMIR();
				emr.setVisible(true);
			}
		});
		p.add(btnNewButton, gbc);
		gbc.gridx = 1;
		JButton btnsil = new JButton("Emir Sil");
		btnsil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BASLA.durdur();
				String[] options = {"Tamam......       		!	", "Vazgec......       		!	"};
				int g =  JOptionPane.showOptionDialog( null,  emirAdi +  " Isimli Emir Islem Dosyadan Silinecek ..?", "Cari Dosyasindan Evrak Silme",   JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,	   			 	null,   	options,   	options[1]); 
				if(g != 0 ) 
				{
					try {
						BASLA.baslat();
						basla();
					} catch (InterruptedException | NumberFormatException | ClassNotFoundException | SQLException | ParseException e1) {

						e1.printStackTrace();
					}
					BASLA.baslat();
					return;
				}
				for (int i = 0; i < BASLA.gorevLER.size(); i++) 
				{
					String  emir = BASLA.gorevLER.get(i) ;

					if (emir.equals(emirAdi))
					{
						BASLA.gorevLER.remove(emir);
					}
				}
				SQL_BACKUP sqll = new SQL_BACKUP();
				String emr =emirAdi;

				try {
					sqll.Logla(emr, "Emir Silme Islemine Baslandi...");
					sqll.genel_kayit_sil(emr);
					sqll.db_adi_kayit_sil(emr);
					sqll.ftp_kayit_sil(emr);
					sqll.bilgilendirme_kayit_sil(emr);
					sqll.yedekleme_kayit_sil(emr);
					sqll.server_kayit_sil(emr);
					sqll.diger_dosya_adi_kayit_sil(emr);
					sqll.Logla(emr, "Emir Islemi Silindi...");

					BASLA.emirDOLDUR();
				} catch (ClassNotFoundException | SQLException | NumberFormatException | InterruptedException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		p.add(btnsil, gbc);
		
		//
		gbc.gridx = 2;
		JButton btnkontrol = new JButton(emirAdi );
		//btnkontrol.setName(emirAdi +"kontROL");
		btnkontrol.setVisible(false);
		btnkontrol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					basla();
				} catch (InterruptedException | NumberFormatException | ClassNotFoundException | SQLException | ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		p.add(btnkontrol, gbc);
		rss = sqll.yedeklemeBILGI(emirAdi);
		if (VT_ANA_CLASS.emirDENMI)
		{
			gunLERE_BAK(); 
		}
		else
		{
			sonRAKI_YEDEK(Integer.parseInt(rss.getString("SAAT")));
		}
		VT_ANA_CLASS.emirDENMI = false ;
		sqll.ccon.close();
		basla();
		return p;
	}
	public static void basla() throws InterruptedException, NumberFormatException, ClassNotFoundException, SQLException, ParseException
	{
//		TimerTask timerTask = new TimerTask() {
//			@Override
//			public void run()
//			{
				DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
				String simDI = df.format(new Date());
				System.out.println(simDI +"=227="+ lblgel.getText()  + ":00"+ denemADI + " gorev run");
				if (simDI.equals(lblgel.getText() + ":00")) // YEDEKLEME ZAMANI 
				{
					yedekLEE();
					
				}
//			}
//		};
//		timer = new Timer(true);
//		timer.schedule(timerTask, 0, 1000);
	}
	@SuppressWarnings("deprecation")
	private static void   sonRAKI_YEDEK(int kacDAKKA) throws NumberFormatException, SQLException, ParseException, InterruptedException, ClassNotFoundException
	{
		ResultSet rss = sqll.yedeklemeBILGI(denemADI);
		DateFormat dff = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String basLA= dff.format(rss.getDate("BASLAMA"));
		Date basLAA=new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(basLA);  
		Date basLAAA= new Date() ;  
		basLAAA.setHours(basLAA.getHours());
		basLAAA.setMinutes(basLAA.getMinutes());
		long basLAAAL = basLAAA.getTime();

		dff = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String biTI= dff.format(rss.getDate("BITIS"));
		Date biTIS=new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(biTI);  
		Date biTISS = new Date();
		biTISS.setHours(biTIS.getHours());
		biTISS.setMinutes(biTIS.getMinutes());
		long biTISL =biTISS.getTime();
		sqll.ccon.close();
		///////
		Date kontROL = new Date();;
		long kontROLL ;
		kontROLL  = kontROL.getTime();
		//System.out.println(kontROL +"=266  gorev="+ biTISS);
		if  (kontROLL > biTISL )
		{
			gunLERE_BAK();
		}
		else
		{
			long ekle = TimeUnit.MINUTES.toMillis(kacDAKKA);
			for (long i = basLAAAL  ; i  <= biTISL; i += ekle)
			{ 
				Date suAN = new Date();;
				long suANL ;
				suAN.setSeconds(0);
				suANL = suAN.getTime();
				if ( i > suANL)
				{
					Date currentDate = new Date(i);
					DateFormat  df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
					lblgel.setText(df.format(currentDate));
					break ;
				}
			}
		}
	}
	private static void gunLERE_BAK() throws SQLException, ParseException, InterruptedException, NumberFormatException, ClassNotFoundException
	{
		LocalDate date = LocalDate.now();
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		hangiGUNDEYIZ = dayOfWeek.getValue(); // 6
		//String dayOfWeekName = dayOfWeek.name(); // SATURDAY
		varmi = false ;
		if(hangiGUNDEYIZ == 1)
		{
			hangiGUN_P_TESI();
		}
		else if(hangiGUNDEYIZ == 2)
		{
			hangiGUN_SALI();
		}
		if(hangiGUNDEYIZ == 3)
		{
			hangiGUN_CARS();
		}
		if(hangiGUNDEYIZ == 4)
		{
			hangiGUN_PERS();
		}
		if(hangiGUNDEYIZ == 5)
		{
			hangiGUN_CUMA();
		}
		if(hangiGUNDEYIZ == 6)
		{
			hangiGUN_C_TESI();
		}
		if(hangiGUNDEYIZ == 6)
		{
			hangiGUN_PAZAR();
		}
	}
	private static void hangiGUN_P_TESI() throws SQLException, ParseException, InterruptedException, NumberFormatException, ClassNotFoundException
	{
		if (rss.getBoolean("P_TESI"))
		{
			for(int i= 4 ; i < 10; i++)
			{
				if(rss.getBoolean(i))
				{
					varmi = true;
					gunEKLE(rss.getDate("BASLAMA") ,(i-2) - hangiGUNDEYIZ);
					break ;
				}
			}
			if (varmi ==false)
			{
				gunEKLE(rss.getDate("BASLAMA") ,7);
			}
		}
		else
		{  //   BUGUN YOK DIGER GUN BAK 
			hangiGUN_SALI();
		}
	}
	private static void hangiGUN_SALI() throws SQLException, ParseException, InterruptedException, NumberFormatException, ClassNotFoundException
	{
		if (rss.getBoolean("SALI"))
		{
			for(int i= 5 ; i < 10; i++)
			{
				if(rss.getBoolean(i))
				{
					varmi = true;
					gunEKLE(rss.getDate("BASLAMA") ,i-4); // cars 5
					break ;
				}
			}
			if (varmi ==false)
			{
				for(int i= 3 ; i < 5; i++)
				{
					if(rss.getBoolean(i))
					{
						varmi = true;
						gunEKLE(rss.getDate("BASLAMA") ,(i-2) - hangiGUNDEYIZ);
						break ;
					}
				}
			}
		}
		else
		{
			hangiGUN_CARS();
		}
	}
	private static void hangiGUN_CARS() throws ParseException, InterruptedException, SQLException, NumberFormatException, ClassNotFoundException
	{
		if (rss.getBoolean("CARS"))
		{
			for(int i= 6 ; i < 10; i++)
			{
				if(rss.getBoolean(i))
				{
					varmi = true;
					gunEKLE(rss.getDate("BASLAMA") ,i - 5); //pers 6
					break ;
				}
			}
			if (varmi ==false)
			{
				for(int i= 3 ; i <5; i++)
				{
					if(rss.getBoolean(i))
					{
						varmi = true;
						gunEKLE(rss.getDate("BASLAMA") ,(i-2) - hangiGUNDEYIZ);
						break ;
					}
				}
			}
		}
		else
		{
			hangiGUN_PERS();
		}
	}
	private static void hangiGUN_PERS() throws SQLException, ParseException, InterruptedException, NumberFormatException, ClassNotFoundException
	{
		if (rss.getBoolean("PERS"))
		{
			for(int i= 7 ; i < 10; i++)
			{
				if(rss.getBoolean(i))
				{
					varmi = true;
					gunEKLE(rss.getDate("BASLAMA") ,i -6); //cuma 7
					break ;
				}
			}
			if (varmi ==false)
			{
				for(int i= 3 ; i < 6; i++)
				{
					if(rss.getBoolean(i))
					{
						varmi = true;
						gunEKLE(rss.getDate("BASLAMA") ,(i-2) - hangiGUNDEYIZ);
						break ;
					}
				}
			}
		}
		else
		{
			System.out.println("386");
			hangiGUN_CUMA();
		}
	}
	private static void hangiGUN_CUMA() throws SQLException, ParseException, InterruptedException, NumberFormatException, ClassNotFoundException
	{
		if (rss.getBoolean("CUMA"))
		{
			for(int i= 8 ; i <10; i++)
			{
				if(rss.getBoolean(i))
				{
					varmi = true;
					gunEKLE(rss.getDate("BASLAMA") ,i - 7); //c.tesi 8
					break ;
				}
			}
			if (varmi ==false)
			{
				for(int i= 3 ; i < 8; i++)
				{
					if(rss.getBoolean(i))
					{
						varmi = true;
						gunEKLE(rss.getDate("BASLAMA") , (i-2) - hangiGUNDEYIZ);
						break ;
					}
				}
			}
		}
		else
		{
			hangiGUN_C_TESI();
		}
	}
	private static void hangiGUN_C_TESI() throws SQLException, ParseException, InterruptedException, NumberFormatException, ClassNotFoundException
	{
		if (rss.getBoolean("C_TESI"))
		{
			for(int i= 9 ; i < 10; i++)
			{
				if(rss.getBoolean(i))
				{
					varmi = true;
					gunEKLE(rss.getDate("BASLAMA") ,i-8); // pazar 9  
					break ;
				}
			}
			if (varmi ==false)
			{
				for(int i= 3 ; i < 9; i++)
				{
					if(rss.getBoolean(i))
					{
						varmi = true;
						gunEKLE(rss.getDate("BASLAMA") ,(i-2) - hangiGUNDEYIZ);
						break ;
					}
				}
			}
		}
		else
		{
			hangiGUN_PAZAR();
		}
	}
	private static void hangiGUN_PAZAR() throws SQLException, ParseException, InterruptedException, NumberFormatException, ClassNotFoundException
	{
		if (rss.getBoolean("PAZAR"))
		{
			for(int i= 3 ; i < 10; i++){
				if(rss.getBoolean(i ))
				{
					gunEKLE(rss.getDate("BASLAMA") ,(i-2) - hangiGUNDEYIZ);
					break ;
				}
			}
		}
		else
		{
			gunEKLE(rss.getDate("BASLAMA") ,0);
		}
	}
	@SuppressWarnings("deprecation")
	private static void gunEKLE(Date basLA, int gun) throws ParseException, InterruptedException, NumberFormatException, ClassNotFoundException, SQLException
	{
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String simDI = df.format(basLA);
		Date date1=new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(simDI);  

		Date dtt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dtt); 
		c.add(Calendar.DATE, gun );
		dtt = c.getTime();

		dtt.setHours(date1.getHours());
		dtt.setMinutes(date1.getMinutes());
		dtt.setSeconds(0);

		df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		lblgel.setText(df.format(dtt));
		basla();
	}
	private static void yedekLEE() throws ClassNotFoundException, SQLException, NumberFormatException, ParseException, InterruptedException
	{
		System.out.println(denemADI + "=Gorev  Ekleme Oncesi Gorev Adedi  ===" +BASLA.gorevLER.size());
		
		//BASLA.timerr.cancel();
		BASLA.gorevLER.add(denemADI);
		//BASLA.baslat();
		System.out.println("Gorev  Ekleme Sonrasi  Gorev Adedi  ===" +BASLA.gorevLER.size());
		ResultSet rss = sqll.yedeklemeBILGI(denemADI);
		int  saat = Integer.parseInt(rss.getString("SAAT")) ;
		sqll.ccon.close();
		sonRAKI_YEDEK(saat);

	}
}
