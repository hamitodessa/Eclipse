package VT_YEDEK;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import OBS_C_2025.SQL_BACKUP;

public class GOREV {

	static Timer timer ;
	static SQL_BACKUP sqll = new SQL_BACKUP();
	static JLabel lblgel ;
	static  JLabel lblson ;
	static JPanel p ;
	static ResultSet rss  ;
	static boolean varmi = false;
	public static  JPanel getShowRoomPanel(String emirAdi ,String sonDurum,int dosyaSayisi,String sonYEDEK , String gelYEDEK,
			String acikLAMA,String durUM,String surUCU) throws InterruptedException, NumberFormatException, ClassNotFoundException, SQLException, ParseException
	{
			durdur();
	        p = new JPanel(new GridBagLayout());
	        p.setBorder(new TitledBorder(emirAdi));
	        ((javax.swing.border.TitledBorder) p.getBorder()).setTitleFont(new Font("Arial", Font.BOLD, 14));
	         ((TitledBorder) p.getBorder()).setTitleColor(Color.BLUE);
	  //  jPanel1.repaint();
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5);
	        gbc.anchor = GridBagConstraints.WEST;

	        p.add(new JLabel("Son Durum:" ), gbc);
	        gbc.gridx = 1 ;
	        p.add(new JLabel(sonDurum), gbc);
	
	        gbc.gridx = 2;
	        p.add(new JLabel("          Son Yedekleme :" ) , gbc);
	        
	        gbc.gridx = 3;
	        lblson = new JLabel(sonYEDEK );
	        lblson.setForeground(Color.green.darker());
	        p.add(lblson, gbc);

	        gbc.gridy = 1;
	        gbc.gridx = 0;
	        p.add(new JLabel("Dosya Sayisi   :"), gbc);

	        gbc.gridx = 1;
	        p.add(new JLabel(Integer.toString(dosyaSayisi) + " Adet Dosya" ), gbc);

	        gbc.gridx = 2;
	        p.add(new JLabel("          Gelecek Yedekleme :" ), gbc);
	        gbc.gridx = 3;
	        
	        lblgel = new JLabel(gelYEDEK );
	        lblgel.setForeground(Color.RED);
	        p.add(lblgel, gbc);

	        gbc.gridy = 2;
	        gbc.gridx = 0;
	        gbc.weightx = 0.0;
	        gbc.gridwidth = 2;
	        p.add(new JLabel("Surucu / FTP  :" ), gbc);
	        
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

					timer.cancel();
					timer.purge();
					VT_ANA_CLASS.yENI_EMIR = false ;
					VT_ANA_CLASS.EMIR_ADI= emirAdi;
					EMIR emr = new EMIR();
					emr.setVisible(true);
					//   System.out.println("="+BASLA.gorevLER.size());
					//	BASLA.gorevLER.add(emirAdi);
					//   System.out.println("==" +BASLA.gorevLER.size());
				}
			});
	        p.add(btnNewButton, gbc);
	        rss = sqll.yedeklemeBILGI(  ((javax.swing.border.TitledBorder) p.getBorder()).getTitle());
	        sonRAKI_YEDEK();
	       basla();
	        return p;
	    }
	public static void basla() throws InterruptedException
	{
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
				String simDI = df.format(new Date());
				System.out.println(simDI + "=basla= " + lblgel.getText()+ ":00" ) ;
				if (simDI.equals(lblgel.getText() + ":00")) // YEDEKLEME ZAMANI 
				{
					timer.cancel();
					timer.purge();
					//YEDEKLE
					try {
						yedekLE();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					//
					DateFormat dff = new SimpleDateFormat("dd.MM.yyyy HH:mm");				
					simDI = dff.format(new Date());
					lblson.setText(simDI);
				}

			}
		};
		timer = new Timer();
		timer.schedule(timerTask, 0, 1000);
	}
	private static void durdur()
	{
		if (timer != null)
		{	
			timer.cancel();
			timer.purge();
		}
	}
	private static void sonrakiYEDEK() throws ClassNotFoundException, SQLException, NumberFormatException, ParseException, InterruptedException
	{
		LocalDate date = LocalDate.now();
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		String str = dayOfWeek.name(); // SATURDAY

		rss = sqll.yedeklemeBILGI(  ((javax.swing.border.TitledBorder) p.getBorder()).getTitle());
		if (str.equals("MONDAY")) 
		{
			if (rss.getBoolean("P_TESI"))
			{
				yedekLE();
			}
		}
		if (str.equals("TUESDAY")) 
		{
			if (rss.getBoolean("SALI"))
			{
				yedekLE();
			}
		}
		if (str.equals("WEDNESDAY")) 
		{
			if (rss.getBoolean("CARS"))
			{
				yedekLE();
			}
		}
		if (str.equals("THURSDAY")) 
		{
			if (rss.getBoolean("PERS"))
			{
				yedekLE();
			}
		}
		if (str.equals("FRIDAY")) 
		{
			if (rss.getBoolean("CUMA"))
			{
				yedekLE();
			}
		}
		if (str.equals("SATURDAY")) 
		{
			if (rss.getBoolean("C_TESI"))
			{
				yedekLE();
			}
		}
		if (str.equals("SUNDAY")) 
		{
			if (rss.getBoolean("PAZAR"))
			{
				yedekLE();
			}
		}
		// SONRAKI
		sonRAKI_YEDEK();
	}
	@SuppressWarnings("deprecation")
	private static void  sonRAKI_YEDEK() throws NumberFormatException, SQLException, ParseException, InterruptedException
	{
		int kacDAKKA = Integer.parseInt(rss.getString("SAAT"));

		LocalDateTime dateTime = LocalDateTime.now().plus(Duration.of(kacDAKKA, ChronoUnit.MINUTES));
		Date gelecekSAAT  = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		long gelecekSAATL = gelecekSAAT.getTime();
		//
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String simDI = df.format(rss.getDate("BITIS"));
		Date date1=new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(simDI);  

		Date sonSAAT =new Date();;
		sonSAAT.setHours(date1.getHours());
		sonSAAT.setMinutes(date1.getMinutes());
		sonSAAT.setSeconds(0);
		long sonSAATL = sonSAAT.getTime();
		//
		System.out.println( gelecekSAAT + "=253=" + sonSAAT);
		System.out.println( gelecekSAATL + "=254=" + sonSAATL);
		if (gelecekSAATL <= sonSAATL)   // SON yedekleme saatinden kucuk
		{
			df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			lblgel.setText(df.format( gelecekSAAT));
		}
		else
		{
		gunLERE_BAK();
		}
	}
private static void gunLERE_BAK() throws SQLException, ParseException, InterruptedException
{
	LocalDate date = LocalDate.now();
	DayOfWeek dayOfWeek = date.getDayOfWeek();
	int hangiGUNDEYIZ = dayOfWeek.getValue(); // 6
	//String dayOfWeekName = dayOfWeek.name(); // SATURDAY

	System.out.println("270=" + hangiGUNDEYIZ);
	if(hangiGUNDEYIZ == 1)
	{
		//if (rss.getBoolean("P_TESI"))
		//{
			
			for(int i= 4 ; i < 10; i++)
			{
				if(rss.getBoolean(i))
				{
					varmi = true;
					System.out.println("280=" + i);
					gunEKLE(rss.getDate("BASLAMA") ,i-3); // Sali 4
					break ;
				}
			}
			if (varmi ==false)
			{
				gunEKLE(rss.getDate("BASLAMA") ,7);
			}
		//}
	}
	else if(hangiGUNDEYIZ == 2)
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
						gunEKLE(rss.getDate("BASLAMA") ,i+2);
						break ;
					}
				}
			}
		}
	}
	else if(hangiGUNDEYIZ == 3)
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
						gunEKLE(rss.getDate("BASLAMA") ,i-2);
						break ;
					}
				}
			}
		}
	}
	else if(hangiGUNDEYIZ == 4)
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
						gunEKLE(rss.getDate("BASLAMA") ,i-2);
						break ;
					}
				}
			}
		}
	}
	else if(hangiGUNDEYIZ == 5)
	{
		if (rss.getBoolean("CUMA"))
		{
				for(int i= 8 ; i < 9; i++)
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
				for(int i= 3 ; i < 7; i++)
				{
					if(rss.getBoolean(i))
					{
						varmi = true;
						gunEKLE(rss.getDate("BASLAMA") ,i-2);
						break ;
					}
				}
			}
		}
	}
	else if(hangiGUNDEYIZ == 6)
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
				for(int i= 3 ; i < 8; i++)
				{
					if(rss.getBoolean(i))
					{
						varmi = true;
						gunEKLE(rss.getDate("BASLAMA") ,i-1);
						break ;
					}
				}
			}
		}
	}
	else if(hangiGUNDEYIZ == 7)
	{
		if (rss.getBoolean("PAZAR"))
		{
			for(int i= 3 ; i < 10; i++){
				if(rss.getBoolean(i ))
				{
					gunEKLE(rss.getDate("BASLAMA") ,i-2);
					break ;
				}
			}
		}
		else
		{
			/////
			for(int i= 3 ; i < 9; i++){
				if(rss.getBoolean(i ))
				{
				gunEKLE(rss.getDate("BASLAMA") ,i-2);
					break ;
				}
			}
			////
		}
	}
}
	@SuppressWarnings("deprecation")
	private static void gunEKLE(Date basLA, int gun) throws ParseException, InterruptedException
	{
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String simDI = df.format(basLA);
		Date date1=new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(simDI);  


		Date dtt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dtt); 
		c.add(Calendar.DATE, gun);
		dtt = c.getTime();

		System.out.println("468");
		dtt.setHours(date1.getHours());
		dtt.setMinutes(date1.getMinutes());
		dtt.setSeconds(0);

		df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	
		lblgel.setText(df.format(dtt));
		 basla();
	}
	private static void yedekLE()
	{
		System.out.println("Yedekleme..");  
	}
}
