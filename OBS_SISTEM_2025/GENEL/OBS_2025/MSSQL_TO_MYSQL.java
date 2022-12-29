package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import LOGER_KAYIT.TXT_LOG;

import OBS_C_2025.BACKUP_RESTORE;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.TARIH_CEVIR;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MSSQL_TO_MYSQL extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	Connection MS_conn = null;  
	Connection MY_conn = null;  
	boolean vt = false;
	boolean ds = false;
	boolean tx = false;
	boolean em = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MSSQL_TO_MYSQL frame = new MSSQL_TO_MYSQL();
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
	public MSSQL_TO_MYSQL() {
		setClosable(true);
		setBounds(100, 100, 671, 482);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(207, 33, 96, 20);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Dosya");
		lblNewLabel.setBounds(142, 36, 48, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("MSSQL");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(207, 11, 96, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("MYSQL");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(313, 11, 96, 14);
		panel.add(lblNewLabel_1_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(313, 33, 96, 20);
		panel.add(textField_1);

		JButton btnNewButton_1 = new JButton("BAGLAN");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					baglan();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(35, 79, 159, 23);
		panel.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("BAGLAN");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kur_baglan();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_1.setBounds(229, 79, 123, 23);
		panel.add(btnNewButton_1_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(28, 113, 182, 167);
		panel_1.setLayout(null);
		panel.add(panel_1);

		JButton btnNewButton = new JButton("Hesap Plani");
		btnNewButton.setBounds(10, 11, 159, 23);
		panel_1.add(btnNewButton);

		JButton btnHesapDetay = new JButton("Hesap Detay");
		btnHesapDetay.setBounds(10, 41, 159, 23);
		panel_1.add(btnHesapDetay);

		JButton btnSatirlar = new JButton("Satirlar");
		btnSatirlar.setBounds(10, 71, 159, 23);
		panel_1.add(btnSatirlar);

		JButton btnIzahat = new JButton("Izahat");
		btnIzahat.setBounds(10, 101, 159, 23);
		panel_1.add(btnIzahat);

		JButton btnOzel = new JButton("Ozel");
		btnOzel.setBounds(10, 131, 159, 23);
		panel_1.add(btnOzel);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(222, 113, 143, 167);
		panel.add(panel_1_1);

		JButton btnNewButton_2 = new JButton("Kur");
		btnNewButton_2.setBounds(10, 11, 123, 23);
		panel_1_1.add(btnNewButton_2);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBounds(375, 113, 143, 199);
		panel.add(panel_1_1_1);

		JButton btnNewButton_2_1 = new JButton("Mal");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mal();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1.setBounds(10, 11, 123, 23);
		panel_1_1_1.add(btnNewButton_2_1);

		JButton btnNewButton_2_1_1 = new JButton("Diger");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					diger();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1_1.setBounds(10, 33, 123, 23);
		panel_1_1_1.add(btnNewButton_2_1_1);

		JButton btnNewButton_2_1_1_1 = new JButton("Bozuk Mal");
		btnNewButton_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bozukmal();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1_1_1.setBounds(10, 59, 123, 23);
		panel_1_1_1.add(btnNewButton_2_1_1_1);

		JButton btnNewButton_2_1_1_1_1 = new JButton("Fatura");
		btnNewButton_2_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fatura();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1_1_1_1.setBounds(10, 81, 123, 23);
		panel_1_1_1.add(btnNewButton_2_1_1_1_1);

		JButton btnNewButton_2_1_1_1_1_1 = new JButton("Irsaliye");
		btnNewButton_2_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					irsaliye();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1_1_1_1_1.setBounds(10, 106, 123, 23);
		panel_1_1_1.add(btnNewButton_2_1_1_1_1_1);

		JButton btnNewButton_2_1_1_1_1_1_1 = new JButton("Recete");
		btnNewButton_2_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					recete();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1_1_1_1_1_1.setBounds(10, 133, 123, 23);
		panel_1_1_1.add(btnNewButton_2_1_1_1_1_1_1);

		JButton btnNewButton_2_1_1_1_1_1_1_1 = new JButton("Stok");
		btnNewButton_2_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					stok();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1_1_1_1_1_1_1.setBounds(10, 165, 123, 23);
		panel_1_1_1.add(btnNewButton_2_1_1_1_1_1_1_1);

		JButton btnNewButton_1_1_1 = new JButton("BAGLAN");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					stk_baglan();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_1_1.setBounds(382, 79, 123, 23);
		panel.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_3 = new JButton("sifre");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qwe;
				 try {
//				byte[]  qaz =	ENCRYPT_DECRYPT_STRING.eNCRYPT_manual("oOk271972") ;
//				System.out.println("Array=" +Arrays.toString(qaz));
//				byte[] ewq = {-90, -12, -3, -79, 32, -23, 64, -33, -73, 94, -3, 26, -36, -105, 120, -46};
//				qwe = 	 ENCRYPT_DECRYPT_STRING.dCRYPT_manual(ewq) ;
//				System.out.println(qwe);
			
			
			 String str = "oOk271972";

		      // string to byte[]
		      byte[] bytes = ENCRYPT_DECRYPT_STRING.eNCRYPT_manual("oOk271972");

		      System.out.println("Text : " + str);
		      System.out.println("Text [Byte Format] : " + bytes);

		      // no, don't do this, it returns the address of the object in memory
		      System.out.println("Text [Byte Format] toString() : " + bytes.toString());

		      // convert byte[] to string
		      String s = 	 new String( ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes)) ;
		     
		      System.out.println("Output : " +  s);
		      
		      
				} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
						| UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
				
				
		
				/////
			}
		});
		btnNewButton_3.setBounds(35, 340, 89, 23);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("backup");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				BACKUP_RESTORE.Backupdbtosql("ok_car019","hamit","197227oOk", "C:/Program Files/MySQL/MySQL Workbench 8.0");
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		btnNewButton_4.setBounds(162, 340, 89, 23);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Date qwe = new Date();
				
				System.out.println(qwe.getHours() + ":" +qwe.getMinutes()+ ":" + qwe.getSeconds());
			}
		});
		btnNewButton_5.setBounds(276, 340, 89, 23);
		panel.add(btnNewButton_5);
		
			btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kur();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnOzel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ozel();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnIzahat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					izahat();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSatirlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					satirlar();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnHesapDetay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hsp_detay();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hsp_pln();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

	}
	public void baglan() throws ClassNotFoundException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		mssql_baglan("Car");
		mysql_baglan("Car");
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	public void kur_baglan() throws ClassNotFoundException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		mssql_baglan("Kur");
		mysql_baglan("Kur");
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	public void stk_baglan() throws ClassNotFoundException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		mssql_baglan("Fat");
		mysql_baglan("Fat");
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void mssql_baglan(String modul) throws ClassNotFoundException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try
		{
			String cumle = "";
			cumle = "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;database=OK_" + modul + textField.getText() ;
			MS_conn = DriverManager.getConnection(cumle,"sa","197227oOk");
		} 
		catch (Exception e)
		{  
			JOptionPane.showMessageDialog(null,e.getMessage(), "MS SQL baglan", JOptionPane.ERROR_MESSAGE);
		}  
	}
	void mysql_baglan(String modul) throws ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/ok_" + modul + textField_1.getText()  ; //pointing to no database.
		try 
		{
			MY_conn = DriverManager.getConnection(url, "root","197227oOk");

		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(), "My SQL baglan", JOptionPane.ERROR_MESSAGE);
		}

	}
	void hsp_pln() throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT * FROM HESAP WITH (INDEX (IX_HESAP))  ORDER BY HESAP ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);

		///
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rs = null;
		PreparedStatement stmt2 = MS_conn.prepareStatement("SELECT * FROM HESAP WITH (INDEX (IX_HESAP))  ORDER BY HESAP ");
		rs = stmt2.executeQuery();

		while(rss.next()){
			String sql1  = "INSERT INTO HESAP (HESAP,UNVAN,KARTON,HESAP_CINSI,USER) " +
					" VALUES (?,?,?,?,?)" ;
			stmt2 = null;
			stmt2 = MY_conn.prepareStatement(sql1);
			stmt2.setString(1, rss.getString("HESAP"));
			stmt2.setString(2, rss.getString("UNVAN"));
			stmt2.setString(3, rss.getString("KARTON"));
			stmt2.setString(4,rss.getString("HESAP_CINSI"));
			stmt2.setString(5, rss.getString(4));
			stmt2.executeUpdate();
		}
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void hsp_detay() throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT * FROM HESAP_DETAY  ORDER BY D_HESAP ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);

		///
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rs = null;
		PreparedStatement stmt2;

		while(rss.next()){
			sql  = "INSERT INTO HESAP_DETAY (D_HESAP,YETKILI,ADRES_1,ADRES_2,SEMT,SEHIR,VERGI_DAIRESI,VERGI_NO,TEL_1,TEL_2, " + 
					" TEL_3,FAX,OZEL_KOD_1,OZEL_KOD_2,OZEL_KOD_3,WEB,E_MAIL,TC_KIMLIK,ACIKLAMA,SMS_GONDER,RESIM)" +
					" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
			stmt2 = null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1,rss.getString("D_HESAP"));
			stmt2.setString(2,rss.getString("YETKILI"));
			stmt2.setString(3, rss.getString("ADRES_1"));
			stmt2.setString(4, rss.getString("ADRES_2"));
			stmt2.setString(5, rss.getString("SEMT"));
			stmt2.setString(6, rss.getString("SEHIR"));
			stmt2.setString(7, rss.getString("VERGI_DAIRESI"));
			stmt2.setString(8, rss.getString("VERGI_NO"));
			stmt2.setString(9, rss.getString("TEL_1"));
			stmt2.setString(10, rss.getString("TEL_2"));
			stmt2.setString(11, rss.getString("TEL_3"));
			stmt2.setString(12, rss.getString("FAX"));
			stmt2.setString(13, rss.getString("OZEL_KOD_1"));
			stmt2.setString(14, rss.getString("OZEL_KOD_2"));
			stmt2.setString(15, rss.getString("OZEL_KOD_3"));
			stmt2.setString(16, rss.getString("WEB"));
			stmt2.setString(17, rss.getString("E_MAIL"));
			stmt2.setString(18, rss.getString("TC_KIMLIK"));
			stmt2.setString(19, rss.getString("ACIKLAMA"));
			stmt2.setBoolean(20, rss.getBoolean("SMS_GONDER"));
			if (  rss.getBytes("RESIM") != null)
			{

				stmt2.setBytes(21,rss.getBytes("RESIM"));
			}
			else
			{
				stmt2.setBytes(21,null);
			}
			stmt2.executeUpdate();
		}
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void satirlar() throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rs = null;
		ResultSet	rss = null;
		PreparedStatement stmt2;

		String sql = "SELECT * FROM SATIRLAR    ORDER BY EVRAK ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);

		while(rss.next()){
			sql  = "INSERT INTO SATIRLAR (HESAP,TARIH,H,EVRAK,CINS,KUR,BORC,ALACAK,KOD,USER) " +
					" VALUES (?,?,?,?,?,?,?,?,?,?)" ;
			stmt2 = null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1, rss.getString("HESAP"));
			Timestamp timestamp =rss.getTimestamp("TARIH");
			Date    date1 = null;
			String formatli = "";
			if (timestamp != null)
			{date1 = new java.util.Date(timestamp.getTime());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss.ss");
			formatli = formatter.format(date1);
			}
			stmt2.setString(2, formatli);
			stmt2.setString(3,  rss.getString("H"));
			stmt2.setInt(4, rss.getInt("EVRAK"));
			stmt2.setString(5,  rss.getString("CINS"));
			stmt2.setDouble(6,  rss.getDouble("KUR"));
			stmt2.setDouble(7,  rss.getDouble("BORC"));
			stmt2.setDouble(8,  rss.getDouble("ALACAK"));
			stmt2.setString(9, rss.getString("KOD"));
			stmt2.setString(10, rss.getString("USER"));
			stmt2.executeUpdate();
		}
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void izahat () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rs = null;
		ResultSet	rss = null;
		PreparedStatement stmt2;

		String sql = "SELECT * FROM IZAHAT    ORDER BY  EVRAK ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  = "INSERT INTO IZAHAT (EVRAK,IZAHAT) " +
					" VALUES (?,?)" ;
			stmt2 = null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setInt(1, rss.getInt("EVRAK"));
			stmt2.setString(2,  rss.getString("IZAHAT"));
			stmt2.executeUpdate();
		}
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void ozel () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rs = null;
		ResultSet	rss = null;
		PreparedStatement stmt2;

		String sql = "SELECT * FROM OZEL    ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  = "INSERT INTO OZEL (YONETICI,YON_SIFRE,FIRMA_ADI) " +
					" VALUES (?,?,?)" ;

			stmt2 = null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1, rss.getString("YONETICI"));
			stmt2.setString(2,  rss.getString("YON_SIFRE"));
			stmt2.setString(3,  rss.getString("FIRMA_ADI"));
			stmt2.executeUpdate();
		}

		// YETKILER
		sql = "SELECT * FROM YETKILER    ";
		stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  = "INSERT INTO YETKILER (KULLANICI,KARTON,TAM_YETKI,GORUNTU) " +
					" VALUES (?,?,?,?)" ;
			stmt2 = null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1, rss.getString("YONETICI"));
			stmt2.setString(2,  rss.getString("YON_SIFRE"));
			stmt2.setBoolean(3,  rss.getBoolean("TAM_YETKI"));
			stmt2.setBoolean(4,  rss.getBoolean("GORUNTU"));
			stmt2.executeUpdate();
		}
		// ana grp
		sql = "SELECT * FROM ANA_GRUP_DEGISKEN    ";
		stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  = "INSERT INTO ANA_GRUP_DEGISKEN (ANA_GRUP,USER) " +
					" VALUES (?,?)" ;
			stmt2 = null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1, rss.getString("ANA_GRUP"));
			stmt2.setString(2,  rss.getString("USER"));
			stmt2.executeUpdate();
		}
		// ana grp
		sql = "SELECT * FROM ALT_GRUP_DEGISKEN    ";
		stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  = "INSERT INTO ALT_GRUP_DEGISKEN (ANA_GRUP,ALT_GRUP,USER) " +
					" VALUES (?,?,?)" ;
			stmt2 = null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setInt(1, rss.getInt("ANA_GRUP"));
			stmt2.setString(2, rss.getString("ALT_GRUP"));
			stmt2.setString(3,  rss.getString("USER"));
			stmt2.executeUpdate();
		}
		// evrak
		sql = "SELECT * FROM EVRAK_NO    ";
		stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  = "UPDATE EVRAK_NO SET EVRAK =" +  rss.getInt("EVRAK") + "  WHERE EID = 1";;
			stmt2 =MY_conn.prepareStatement(sql);
			stmt2.executeUpdate();
		}
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void kur () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		ResultSet	rs = null;
		ResultSet	rss = null;
		PreparedStatement stmt2; 
		String sql = "SELECT * FROM Kurlar    ORDER BY  Tarih ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  ="INSERT INTO KURLAR (Tarih,Kur,MA,MS,SA,SS,BA,BS) " +
					" VALUES (?,?,?,?,?,?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setDate(1,  rss.getDate("Tarih"));
			stmt2.setString(2,  rss.getString("Kur"));
			stmt2.setDouble(3, rss.getDouble("MA"));
			stmt2.setDouble(4, rss.getDouble("MS"));
			stmt2.setDouble(5, rss.getDouble("SA"));
			stmt2.setDouble(6, rss.getDouble("SS"));
			stmt2.setDouble(7,rss.getDouble("BA"));
			stmt2.setDouble(8, rss.getDouble("BS"));
			stmt2.executeUpdate();
		}
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void mal () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		ResultSet	rs = null;
		ResultSet	rss = null;
		PreparedStatement stmt2; 
		String sql = "SELECT * FROM MAL Order by Kodu     ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  = "INSERT INTO MAL (Kodu,Adi,Birim,Kusurat,Sinif,Ana_Grup,Alt_Grup,Aciklama_1,Aciklama_2,Ozel_Kod_1 " +
					" ,Ozel_Kod_2,Barkod,Mensei,Agirlik,Fiat,Fiat_2,Fiat_3,Recete,Kdv,Resim,Depo , Ozel_Kod_3,USER) " +
					" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1, rss.getString("Kodu"));
			stmt2.setString(2, rss.getString("Adi"));
			stmt2.setString(3,  rss.getString("Birim"));
			stmt2.setDouble(4,  rss.getDouble("Kusurat"));
			stmt2.setString(5,  rss.getString("Sinif"));
			stmt2.setInt(6,  rss.getInt("Ana_Grup"));
			stmt2.setInt(7, rss.getInt("Alt_Grup"));
			stmt2.setString(8,  rss.getString("Aciklama_1"));
			stmt2.setString(9,  rss.getString("Aciklama_2"));
			stmt2.setInt(10,  rss.getInt("Ozel_Kod_1"));
			stmt2.setInt(11, rss.getInt("Ozel_Kod_2"));
			stmt2.setString(12, rss.getString("Barkod"));
			stmt2.setInt(13, rss.getInt("Mensei"));
			stmt2.setDouble(14, rss.getDouble("Agirlik"));
			stmt2.setDouble(15, rss.getDouble("Fiat"));
			stmt2.setDouble(16, rss.getDouble("Fiat_2"));
			stmt2.setDouble(17, rss.getDouble("Fiat_3"));
			stmt2.setString(18, rss.getString("Recete"));
			stmt2.setDouble(19,  rss.getDouble("Kdv"));
			if (  rss.getBytes("RESIM") != null)
			{
				stmt2.setBytes(20,rss.getBytes("RESIM"));
			}
			else
			{
				stmt2.setBytes(20,null);
			}
			stmt2.setInt(21, rss.getInt("Depo"));
			stmt2.setInt(22, rss.getInt("Ozel_Kod_3"));
			stmt2.setString(23, rss.getString("USER"));
			stmt2.executeUpdate();
		}
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void diger () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		ResultSet	rss = null;
		PreparedStatement stmt2; 
		String sql = "SELECT * FROM ACIKLAMA   ORDER BY  EVRAK_NO ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  ="INSERT INTO ACIKLAMA (EVRAK_CINS,SATIR,EVRAK_NO,ACIKLAMA,Gir_Cik) " +
					" VALUES (?,?,?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1,  rss.getString("EVRAK_CINS"));
			stmt2.setInt(2, rss.getInt("SATIR"));
			stmt2.setString(3, rss.getString("EVRAK_NO"));
			stmt2.setString(4,  rss.getString("ACIKLAMA"));
			stmt2.setString(5,  rss.getString("Gir_Cik"));
			stmt2.executeUpdate();
		}
		//ALt GRUP
		sql = "SELECT * FROM ALT_GRUP_DEGISKEN    ";
		rss = null;
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  ="INSERT INTO ALT_GRUP_DEGISKEN (ALID_Y,ANA_GRUP,ALT_GRUP,USER) " +
					" VALUES (?,?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setInt(1,  rss.getInt("ALID_Y"));
			stmt2.setInt(2, rss.getInt("ANA_GRUP"));
			stmt2.setString(3, rss.getString("ALT_GRUP"));
			stmt2.setString(4,  rss.getString("USER"));
			stmt2.executeUpdate();
		}
		//ANA GRUP
		sql = "SELECT * FROM ANA_GRUP_DEGISKEN    ";
		rss = null;
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  ="INSERT INTO ANA_GRUP_DEGISKEN (AGID_Y,ANA_GRUP,USER) " +
					" VALUES (?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setInt(1,  rss.getInt("AGID_Y"));
			stmt2.setString(2,  rss.getString("ANA_GRUP"));
			stmt2.setString(3,  rss.getString("USER"));
			stmt2.executeUpdate();
		}
		//DEPO
		sql = "SELECT * FROM DEPO_DEGISKEN    ";
		rss = null;
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  ="INSERT INTO DEPO_DEGISKEN  (DPID_Y,DEPO,USER) " +
					" VALUES (?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setInt(1,  rss.getInt("DPID_Y"));
			stmt2.setString(2,  rss.getString("DEPO"));
			stmt2.setString(3,  rss.getString("USER"));
			stmt2.executeUpdate();
		}
		//DEPO EVRAK
		sql = "SELECT * FROM DEPOEVRAK    ";
		rss = null;
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  = "UPDATE DEPOEVRAK SET E_No =" +  rss.getInt("E_No")  ;
			stmt2 =MY_conn.prepareStatement(sql);
			stmt2.executeUpdate();
		}
		//DPN
		sql = "SELECT * FROM DPN  ORDER BY Evrak_No ";
		rss = null;
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  ="INSERT INTO DPN  (Evrak_No,Tip,Bir,Iki,Uc,Gir_Cik,USER) " +
					" VALUES (?,?,?,?,?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1,  rss.getString("Evrak_No"));
			stmt2.setString(2, rss.getString("Tip"));
			stmt2.setString(3,  rss.getString("Bir"));
			stmt2.setString(4,  rss.getString("Iki"));
			stmt2.setString(5,  rss.getString("Uc"));
			stmt2.setString(6,  rss.getString("Gir_Cik"));
			stmt2.setString(7,  rss.getString("USER"));
			stmt2.executeUpdate();


		}

		//GDY
		sql = "SELECT * FROM GDY   ";
		rss = null;
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  ="INSERT INTO GDY  (Isim,Adres,Semt,Sehir,USER) " +
					" VALUES (?,?,?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1,  rss.getString("Isim"));
			stmt2.setString(2, rss.getString("Adres"));
			stmt2.setString(3,  rss.getString("Semt"));
			stmt2.setString(4,  rss.getString("Sehir"));
			stmt2.setString(5,  rss.getString("USER"));
			stmt2.executeUpdate();
		}
		//MENSEI
		sql = "SELECT * FROM MENSEI_DEGISKEN    ";
		rss = null;
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  ="INSERT INTO MENSEI_DEGISKEN  (MENSEI,USER,MEID_Y) " +
					" VALUES (?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);

			stmt2.setString(1,  rss.getString("MENSEI"));
			stmt2.setString(2,  rss.getString("USER"));
			stmt2.setInt(3,  rss.getInt("MEID_Y"));
			stmt2.executeUpdate();
		}					 
		//OZ KOD !
		sql = "SELECT * FROM OZ_KOD_1_DEGISKEN    ";
		rss = null;
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  ="INSERT INTO OZ_KOD_1_DEGISKEN  (OZ1ID_Y,OZEL_KOD_1,USER) " +
					" VALUES (?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setInt(1,  rss.getInt("OZ1ID_Y"));
			stmt2.setString(2,  rss.getString("OZEL_KOD_1"));
			stmt2.setString(3,  rss.getString("USER"));
			stmt2.executeUpdate();
		}					 
		//OZ KOD2
		sql = "SELECT * FROM OZ_KOD_2_DEGISKEN    ";
		rss = null;
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  ="INSERT INTO OZ_KOD_2_DEGISKEN  (OZ2ID_Y,OZEL_KOD_2,USER) " +
					" VALUES (?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setInt(1,  rss.getInt("OZ2ID_Y"));
			stmt2.setString(2,  rss.getString("OZEL_KOD_2"));
			stmt2.setString(3,  rss.getString("USER"));
			stmt2.executeUpdate();
		}		

		//OZEL
		sql = "SELECT * FROM OZEL    ";
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  = "INSERT INTO OZEL (YONETICI,YON_SIFRE,FIRMA_ADI,USER) " +
					" VALUES (?,?,?,?)" ;

			stmt2 = null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1, rss.getString("YONETICI"));
			stmt2.setString(2,  rss.getString("YON_SIFRE"));
			stmt2.setString(3,  rss.getString("FIRMA_ADI"));
			stmt2.setString(4,  rss.getString("USER"));
			stmt2.executeUpdate();
		}
		//URET EVRAK
		sql = "SELECT * FROM URET_EVRAK    ";
		rss = null;
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  = "UPDATE URET_EVRAK SET E_No =" +  rss.getInt("E_No")  ;
			stmt2 =MY_conn.prepareStatement(sql);
			stmt2.executeUpdate();
		}						 
		// YETKILER
		sql = "SELECT * FROM YETKILER    ";
		stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  = "INSERT INTO YETKILER (KULLANICI,HESAP,TAM_YETKI,GORUNTU,LEVEL,USER) " +
					" VALUES (?,?,?,?,?,?)" ;
			stmt2 = null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1, rss.getString("KULLANICI"));
			stmt2.setString(2,  rss.getString("HESAP"));
			stmt2.setBoolean(3,  rss.getBoolean("TAM_YETKI"));
			stmt2.setBoolean(4,  rss.getBoolean("GORUNTU"));
			stmt2.setInt(5,  rss.getInt("LEVEL"));
			stmt2.setString(6, rss.getString("USER"));
			stmt2.executeUpdate();
		}				
		// ZAYI EVRAK

		sql = "SELECT * FROM ZAYI_EVRAK    ";
		rss = null;
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  = "UPDATE ZAYI_EVRAK SET E_No =" +  rss.getInt("E_No")  ;
			stmt2 =MY_conn.prepareStatement(sql);
			stmt2.executeUpdate();
		}			
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	void bozukmal () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		ResultSet	rs = null;
		ResultSet	rss = null;
		PreparedStatement stmt2; 
		String sql = "SELECT * FROM BOZUK_MAL    Order by Evrak_No ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  = "INSERT INTO MAL (Evrak_No,Kodu,Tarih,Miktar,Fiat,Ana_Grup,Alt_Grup,Depo,Ozel_Kod " +
					" ,Izahat,Cins,,USER) " +
					" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1, rss.getString("Evrak_No"));
			stmt2.setString(2, rss.getString("Kodu"));
			stmt2.setDate(3,  rss.getDate("Tarih"));
			stmt2.setFloat(4,  rss.getFloat("Miktar"));
			stmt2.setInt(5,  rss.getInt("Fiat"));
			stmt2.setInt(6,  rss.getInt("Ana_Grup"));
			stmt2.setInt(7, rss.getInt("Alt_Grup"));
			stmt2.setInt(8,  rss.getInt("Depo"));
			stmt2.setString(9,  rss.getString("Ozel_Kod"));
			stmt2.setInt(10,  rss.getInt("Izahat"));
			stmt2.setInt(11, rss.getInt("Cins"));
			stmt2.setString(12, rss.getString("USER"));
			stmt2.executeUpdate();
		}
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void fatura () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		ResultSet	rs = null;
		ResultSet	rss = null;
		PreparedStatement stmt2; 
		String sql = "SELECT * FROM FATURA  Order by Fatura_No ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  ="INSERT INTO FATURA (Fatura_No,Kodu,Depo,Fiat,Tevkifat,Miktar,Gir_Cik,Tutar,Iskonto,Kdv,Tarih,Izahat " +
					" ,Doviz,Adres_Firma,Cari_Firma,Ozel_Kod,Kur,Cins,Ana_Grup,Alt_Grup, USER ) " +
					" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1, rss.getString("Fatura_No"));
			stmt2.setString(2, rss.getString("Kodu"));
			stmt2.setInt(3,rss.getInt("Depo"));
			stmt2.setFloat(4, rss.getFloat("Fiat"));
			stmt2.setFloat(5,rss.getFloat("Tevkifat"));
			stmt2.setFloat(6, rss.getFloat("Miktar"));
			stmt2.setString(7, rss.getString("Gir_Cik"));
			stmt2.setFloat(8, rss.getFloat("Tutar"));
			stmt2.setFloat(9, rss.getFloat("Iskonto"));
			stmt2.setFloat(10, rss.getFloat("Kdv"));
			stmt2.setDate(11, rss.getDate("Tarih"));
			stmt2.setString(12, rss.getString("Izahat"));
			stmt2.setString(13, rss.getString("Doviz"));
			stmt2.setString(14, rss.getString("Adres_Firma"));
			stmt2.setString(15, rss.getString("Cari_Firma"));
			stmt2.setString(16,rss.getString("Ozel_Kod"));
			stmt2.setFloat(17,rss.getFloat("Kur"));
			stmt2.setString(18, rss.getString("Cins"));
			stmt2.setInt(19,rss.getInt("Ana_Grup"));
			stmt2.setInt(20,rss.getInt("Alt_Grup"));
			stmt2.setString(21,rss.getString("USER"));
			stmt2.executeUpdate();
		}
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void irsaliye () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		ResultSet	rs = null;
		ResultSet	rss = null;
		PreparedStatement stmt2; 
		String sql = "SELECT * FROM IRSALIYE  Order by Irsaliye_No  ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  ="INSERT INTO IRSALIYE (Irsaliye_No,Kodu,Depo,Fiat,Iskonto,Miktar,Tutar,Kdv, Tarih,Doviz,Kur,Firma " +
					",Cari_Hesap_Kodu,Sevk_Tarihi,Ozel_Kod,Ana_Grup,Alt_Grup,Fatura_No,Hareket,Cins, USER ,Izahat) " +
					" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1,rss.getString("Irsaliye_No"));
			stmt2.setString(2, rss.getString("Kodu"));
			stmt2.setInt(3,rss.getInt("Depo"));
			stmt2.setFloat(4,rss.getFloat("Fiat"));
			stmt2.setFloat(5, rss.getFloat("Iskonto"));
			stmt2.setFloat(6, rss.getFloat("Miktar"));
			stmt2.setFloat(7, rss.getFloat("Tutar"));
			stmt2.setFloat(8, rss.getFloat("Kdv"));
			stmt2.setDate(9, rss.getDate("Tarih"));
			stmt2.setString(10,rss.getString("Doviz"));
			stmt2.setFloat(11, rss.getFloat("Kur"));
			stmt2.setString(12,rss.getString("Firma"));
			stmt2.setString(13,rss.getString("Cari_Hesap_Kodu"));
			stmt2.setDate(14,rss.getDate("Sevk_Tarihi"));
			stmt2.setString(15,rss.getString("Ozel_Kod"));
			stmt2.setInt(16,rss.getInt("Ana_Grup"));
			stmt2.setInt(17,rss.getInt("Alt_Grup"));
			stmt2.setString(18,rss.getString("Fatura_No"));
			stmt2.setString(19,rss.getString("Hareket"));
			stmt2.setString(20,rss.getString("Cins"));
			stmt2.setString(21,rss.getString("USER"));
			stmt2.setString(22,rss.getString("Izahat"));
			stmt2.executeUpdate();
		}
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void recete () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		ResultSet	rs = null;
		ResultSet	rss = null;
		PreparedStatement stmt2; 
		String sql = "SELECT * FROM RECETE  Order by Recete_No  ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  ="INSERT INTO RECETE (Recete_No,Durum,Tur,Kodu,Miktar,Ana_Grup,Alt_Grup, USER ) " +
					" VALUES (?,?,?,?,?,?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1,rss.getString("Recete_No"));
			stmt2.setBoolean(2, rss.getBoolean("Durum"));
			stmt2.setString(3, rss.getString("Tur"));
			stmt2.setString(4, rss.getString("Kodu"));
			stmt2.setFloat(5, rss.getFloat("Miktar"));
			stmt2.setInt(6, rss.getInt("Ana_Grup"));
			stmt2.setInt(7, rss.getInt("Alt_Grup"));
			stmt2.setString(8,rss.getString("USER"));
			stmt2.executeUpdate();
		}
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void  stok () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		ResultSet	rs = null;
		ResultSet	rss = null;
		PreparedStatement stmt2; 
		String sql = "SELECT * FROM STOK  ORDER BY Tarih ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		while(rss.next()){
			sql  ="INSERT INTO STOK (Evrak_No,Evrak_Cins,Tarih,Depo,Urun_Kodu,Miktar,Fiat,Tutar,Kdvli_Tutar,Hareket,Izahat " +
					" ,Ana_Grup,Alt_Grup,Kur,B1,Doviz,Hesap_Kodu, USER ) " +
					" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setString(1, rss.getString("Evrak_No"));
			stmt2.setString(2, rss.getString("Evrak_Cins"));
			stmt2.setDate(3, rss.getDate("Tarih"));
			stmt2.setInt(4,rss.getInt("Depo"));
			stmt2.setString(5, rss.getString("Urun_Kodu"));
			stmt2.setFloat(6, rss.getFloat("Miktar"));
			stmt2.setFloat(7, rss.getFloat("Fiat"));
			stmt2.setFloat(8, rss.getFloat("Tutar"));
			stmt2.setFloat(9, rss.getFloat("Kdvli_Tutar"));
			stmt2.setString(10,rss.getString("Hareket"));
			stmt2.setString(11, rss.getString("Izahat"));
			stmt2.setInt(12, rss.getInt("Ana_Grup"));
			stmt2.setInt(13, rss.getInt("Alt_Grup"));
			stmt2.setFloat(14, rss.getFloat("Kur"));
			stmt2.setString(15,  rss.getString("B1"));
			stmt2.setString(16, rss.getString("Doviz"));
			stmt2.setString(17,  rss.getString("Hesap_Kodu"));
			stmt2.setString(18,  rss.getString("USER"));
			stmt2.executeUpdate();

		}
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}

