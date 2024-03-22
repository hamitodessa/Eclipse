package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.TARIH_CEVIR;
import raven.toast.Notifications;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Cursor;

import javax.swing.JButton;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class DOKUM_AKTAR extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private Obs_TextFIeld textDosya;
	private Obs_TextFIeld textPort;
	private Obs_TextFIeld textInstance;
	private Obs_TextFIeld textIp;
	private Obs_TextFIeld textPassword;
	private Obs_TextFIeld textUser;
	private Obs_TextFIeld textCKodu;

	private JCheckBox msLokal;
	private JButton btnAktar;
	
	private JDateChooser dateBitis;
	private JDateChooser dateBaslama;
	
	private JLabel lblIsim ;
	
	Connection MS_conn = null;  
	
	public DOKUM_AKTAR() {
		setBounds(100, 100, 510, 313);
		setTitle("DOKUM AKTAR");
		setClosable(true);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(MSSQL_TO_MYSQL.class.getResource("/ICONLAR/transfer-16.png")), 16, 16));//

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),  "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblAlinacakDosya = new JLabel("Alinacak Dosya");
		lblAlinacakDosya.setBounds(10, 25, 97, 14);
		panel.add(lblAlinacakDosya);
		
		textDosya = new Obs_TextFIeld();
		textDosya.setColumns(10);
		textDosya.setBounds(107, 22, 75, 20);
		panel.add(textDosya);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Baglanti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(215, 11, 269, 210);
		panel.add(panel_3);
		
		msLokal = new JCheckBox("Lokal");
		msLokal.setSelected(true);
		msLokal.setBounds(100, 21, 97, 23);
		panel_3.add(msLokal);
		
		JLabel lblNewLabel_2 = new JLabel("Kullanici");
		lblNewLabel_2.setBounds(10, 54, 80, 14);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sifre");
		lblNewLabel_3.setBounds(10, 79, 80, 14);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Server");
		lblNewLabel_4.setBounds(10, 104, 80, 14);
		panel_3.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Instance");
		lblNewLabel_5.setBounds(10, 129, 80, 14);
		panel_3.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Port");
		lblNewLabel_6.setBounds(10, 154, 80, 14);
		panel_3.add(lblNewLabel_6);
		
		textPort = new Obs_TextFIeld();
		textPort.setColumns(10);
		textPort.setBounds(100, 150, 159, 20);
		panel_3.add(textPort);
		
		textInstance = new Obs_TextFIeld();
		textInstance.setColumns(10);
		textInstance.setBounds(100, 125, 159, 20);
		panel_3.add(textInstance);
		
		textIp = new Obs_TextFIeld();
		textIp.setColumns(10);
		textIp.setBounds(100, 100, 159, 20);
		panel_3.add(textIp);
		
		textPassword = new Obs_TextFIeld();
		textPassword.setColumns(10);
		textPassword.setBounds(100, 75, 159, 20);
		panel_3.add(textPassword);
		
		textUser = new Obs_TextFIeld();
		textUser.setColumns(10);
		textUser.setBounds(100, 50, 159, 20);
		panel_3.add(textUser);
		
		JButton btnNewButton = new JButton("Baglan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dosBAGLAN();
			}
		});
		btnNewButton.setBounds(100, 175, 159, 23);
		panel_3.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Hesap");
		lblNewLabel.setBounds(10, 198, 55, 14);
		panel.add(lblNewLabel);
		
		textCKodu = new Obs_TextFIeld();
		textCKodu.setBounds(75, 195, 118, 20);
		textCKodu.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				if(btnAktar.isEnabled())
					isimoku_ekstre();
			}
			public void removeUpdate(DocumentEvent e) {
				if(btnAktar.isEnabled())
					isimoku_ekstre();
			}
			public void insertUpdate(DocumentEvent e) {
				if(btnAktar.isEnabled())
					isimoku_ekstre();
			}
		});
		panel.add(textCKodu);
		textCKodu.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ilk Tarih");
		lblNewLabel_1.setBounds(10, 128, 64, 14);
		panel.add(lblNewLabel_1);
		
		dateBaslama = new JDateChooser();
		dateBaslama.setBounds(75, 122, 118, 20);
		((JTextField)dateBaslama.getDateEditor()).setBackground(oac.dtcColor);
		JCalendar qweCalendar =  dateBaslama.getJCalendar();
		qweCalendar.getYearChooser().getSpinner().setBackground(oac.dtcColor);
		dateBaslama.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateBaslama.setDate(new Date());
			}
		});
		dateBaslama.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateBaslama.setDateFormatString("dd.MM.yyyy");
		dateBaslama.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateBaslama.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateBaslama));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, -1); 
						dateBaslama.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateBaslama));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
						dateBaslama.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel.add(dateBaslama);
		
		JLabel lblNewLabel_7 = new JLabel("Son Tarih");
		lblNewLabel_7.setBounds(10, 159, 64, 14);
		panel.add(lblNewLabel_7);
		
		dateBitis = new JDateChooser();
		dateBitis.setBounds(75, 153, 118, 20);
		((JTextField)dateBitis.getDateEditor()).setBackground(oac.dtcColor);
		qweCalendar =  dateBitis.getJCalendar();
		qweCalendar.getYearChooser().getSpinner().setBackground(oac.dtcColor);
		dateBitis.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateBitis.setDateFormatString("dd.MM.yyyy");
		dateBitis.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateBitis.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateBitis.setDate(new Date());
			}
		});
		dateBitis.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateBitis));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, -1); 
						dateBitis.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateBitis));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
						dateBitis.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel.add(dateBitis);
		
		btnAktar = new JButton("Aktar");
		btnAktar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					akTAR();
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnAktar.setEnabled(false);
		btnAktar.setBounds(75, 249, 409, 23);
		panel.add(btnAktar);
		
		lblIsim = new JLabel("");
		lblIsim.setBounds(75, 223, 409, 14);
		panel.add(lblIsim);

	}
	private void dosBAGLAN()
	{
		if (textDosya.getText().equals("")) return;
		if (textUser.getText().equals("")) return;
		if (textPassword.getText().equals("")) return;
		//if (textIp.getText().equals("")) return;
		//if (textInstance.getText().equals("")) return;
		//if (textPort.getText().equals("")) return;
		try {
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if( BAGLAN.cariDizin.hAN_SQL.equals("MS SQL"))
			{
				String cumle = "";
				String serverString = "" ;
				String port = "" ;
				if (msLokal.isSelected())
				{
					if ( ! textPort.getText().toString().equals(""))
						port =  ":" + textPort.getText() ;
					serverString = "localhost" + port ;
				}
				else
					serverString = textIp.getText()  ;	
				cumle = "jdbc:sqlserver://" + serverString + ";instanceName=" + textInstance.getText() + ";database=OK_Car" + textDosya.getText() ;
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				MS_conn = DriverManager.getConnection(cumle,textUser.getText(),textPassword.getText()); 
			}
			else 
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				String serverString = "" ;
				if (msLokal.isSelected())
					serverString = "localhost:" + textPort.getText() ;
				else
					serverString = textIp.getText() +":" + textPort.getText() ;	
				String url = "jdbc:mysql://"+ serverString +"/ok_car" +  textDosya.getText()  ; 
				MS_conn = DriverManager.getConnection(url, textUser.getText(),textPassword.getText());
			}
			btnAktar.setEnabled(true);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO,"Baglanti Saglandi" );
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		} catch (Exception e) {
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,"Baglanti Hata" );
			btnAktar.setEnabled(false);
		}
	}
	private void akTAR() {
		try 
		{
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if( BAGLAN.cariDizin.hAN_SQL.equals("MY SQL"))
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				ResultSet	rss = null;
				String tARIH = "" ;
				String t1 = TARIH_CEVIR.tarih_geri(dateBaslama);
				String t2 = TARIH_CEVIR.tarih_geri(dateBitis);
				if(! t1.equals("1900.01.01") || ! t2.equals("2100.12.31"))
					tARIH = "  AND TARIH BETWEEN  '" + t1 + "' AND '" + t2 + " 23:59:59.998'" ;
				String sql = " SELECT TARIH ,SATIRLAR.EVRAK ," +  
						" IFNULL( IZAHAT.IZAHAT,'') AS IZAHAT,KOD,KUR, BORC , ALACAK , "  + 
						" SUM(ALACAK-BORC) OVER(ORDER BY TARIH  ROWS BETWEEN UNBOUNDED PRECEDING And CURRENT ROW)  AS BAKIYE ,USER "  + 
						" FROM SATIRLAR  USE INDEX (IX_SATIRLAR)  LEFT JOIN IZAHAT  USE INDEX (IX_IZAHAT)  " + 
						" ON SATIRLAR.EVRAK = IZAHAT.EVRAK WHERE  HESAP =N'" + textCKodu.getText() + "'" + 
						tARIH + 
						" ORDER BY TARIH   ";
				PreparedStatement stmt = MS_conn.prepareStatement(sql);
				rss = stmt.executeQuery();

				GRID_TEMIZLE.grid_temizle(DISTAN_AKTAR.tblexcell);
				DefaultTableModel defaultModel = (DefaultTableModel) DISTAN_AKTAR.tblexcell.getModel();
				Date  tar  ;
				while (rss.next())
				{
					tar = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rss.getString("TARIH"));
					defaultModel.addRow(new Object[]{tar, rss.getString("IZAHAT")  ,"", rss.getDouble("BORC") ,rss.getDouble("ALACAK"),"",""});
				}
			}
			else 
			{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				ResultSet	rss = null;
				String tARIH = "" ;
				String t1 = TARIH_CEVIR.tarih_geri(dateBaslama);
				String t2 = TARIH_CEVIR.tarih_geri(dateBitis);
				if(! t1.equals("1900.01.01") || ! t2.equals("2100.12.31"))
					tARIH = "  AND TARIH BETWEEN  '" + t1 + "' AND '" + t2 + " 23:59:59.998'" ;
				String sql = " SELECT TARIH,SATIRLAR.EVRAK ,IZAHAT,KOD,KUR,BORC,ALACAK, "  + 
						"  CAST(SUM(ALACAK-BORC) OVER(ORDER BY TARIH  ROWS BETWEEN UNBOUNDED PRECEDING And CURRENT ROW)  AS DECIMAL(30,2))  AS BAKIYE ,[USER] "  + 
						"  FROM SATIRLAR  LEFT JOIN IZAHAT   " + 
						"  ON SATIRLAR.EVRAK = IZAHAT.EVRAK WHERE  HESAP =N'" + textCKodu.getText() + "'" + 
						tARIH + 
						"  ORDER BY TARIH   ";
				PreparedStatement stmt = MS_conn.prepareStatement(sql);
				rss = stmt.executeQuery();

				GRID_TEMIZLE.grid_temizle(DISTAN_AKTAR.tblexcell);
				DefaultTableModel defaultModel = (DefaultTableModel) DISTAN_AKTAR.tblexcell.getModel();
				Date  tar  ;
				while (rss.next())
				{
					tar = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rss.getString("TARIH"));
					defaultModel.addRow(new Object[]{tar, rss.getString("IZAHAT")  ,"", rss.getDouble("BORC") ,rss.getDouble("ALACAK"),"",""});
				}
			}
			DISTAN_AKTAR.lblsatir.setText(FORMATLAMA.doub_0(DISTAN_AKTAR.tblexcell.getRowCount()));
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			OBS_MAIN.mesaj_goster(7000,Notifications.Type.INFO,Integer.toString(DISTAN_AKTAR.tblexcell.getRowCount()) + " Satir Aktarildi" );
			dispose();
		} catch (Exception ex) {
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void isimoku_ekstre()
	{
		try 
		{
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			ResultSet	rss = null;
			if( BAGLAN.cariDizin.hAN_SQL.equals("MY SQL"))
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				String sql = "SELECT HESAP, HESAP_CINSI,  KARTON, UNVAN FROM HESAP WHERE HESAP = N'" + textCKodu.getText() + "'"; 
				PreparedStatement stmt = MS_conn.prepareStatement(sql);
				rss = stmt.executeQuery();
			}
			else 
			{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String sql = "SELECT HESAP, HESAP_CINSI,  KARTON, UNVAN FROM HESAP WHERE HESAP = N'" + textCKodu.getText() + "'"; 
				PreparedStatement stmt = MS_conn.prepareStatement(sql);
				rss = stmt.executeQuery();
			}
			if (!rss.isBeforeFirst() ) {  
				lblIsim.setText("");
			} 
			else
			{
				rss.next();
				lblIsim.setText( rss.getString("UNVAN"));
			}
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		} catch (Exception ex) {
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
}

