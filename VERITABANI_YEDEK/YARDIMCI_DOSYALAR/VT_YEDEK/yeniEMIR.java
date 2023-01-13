package VT_YEDEK;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import OBS_C_2025.SQL_BACKUP;

public class yeniEMIR extends JPanel implements Runnable{

	
	static SQL_BACKUP sqll = new SQL_BACKUP();
	static JLabel lblgel ;
	static  JLabel lblson ;
	static JPanel p ;
	static ResultSet rss  ;
	static boolean varmi = false;
	Date    sonrakitar = new Date();
	static int hangiGUNDEYIZ ;
	public static String denemADI = "" ;
	 static Timer timer;
	 JLabel qwe1;
	 
	public  JPanel yeniEMIR(String emirAdi)
	{
		
		p = new JPanel();
		p.setName( emirAdi);
	
		p.setBorder(new TitledBorder(emirAdi));
		JLabel qwe = new JLabel(emirAdi);
		
		p.add(qwe);
		
	
		 qwe1 = new JLabel("saat");
		p.add(qwe1);
		return p ;
	}
	private void basla() {
	
	{
		TimerTask timerTask = new TimerTask() {
		@Override
		public void run()
		{
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String simDI = df.format(new Date());
			qwe1.setText(simDI);
			
		}
	};
	timer = new Timer(true);
	timer.schedule(timerTask, 0, 1000);
	}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		basla();
		
		
		
	}

}
