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

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.TARIH_CEVIR;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.awt.event.ActionEvent;

public class MSSQL_TO_MYSQL extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;

	Connection MS_conn = null;  
	Connection MY_conn = null;  
	
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
		btnNewButton_1.setBounds(75, 79, 222, 23);
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
		btnNewButton_1_1.setBounds(307, 79, 143, 23);
		panel.add(btnNewButton_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(75, 113, 222, 167);
		panel_1.setLayout(null);
		panel.add(panel_1);
		
		JButton btnNewButton = new JButton("Hesap Plani");
		btnNewButton.setBounds(10, 11, 202, 23);
		panel_1.add(btnNewButton);
		
		JButton btnHesapDetay = new JButton("Hesap Detay");
		btnHesapDetay.setBounds(10, 41, 202, 23);
		panel_1.add(btnHesapDetay);
		
		JButton btnSatirlar = new JButton("Satirlar");
		btnSatirlar.setBounds(10, 71, 202, 23);
		panel_1.add(btnSatirlar);
		
		JButton btnIzahat = new JButton("Izahat");
		btnIzahat.setBounds(10, 101, 202, 23);
		panel_1.add(btnIzahat);
		
		JButton btnOzel = new JButton("Ozel");
		btnOzel.setBounds(10, 131, 202, 23);
		panel_1.add(btnOzel);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(307, 113, 143, 167);
		panel.add(panel_1_1);
		
		JButton btnNewButton_2 = new JButton("Kur");
		btnNewButton_2.setBounds(10, 11, 123, 23);
		panel_1_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kur();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOzel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ozel();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnIzahat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					izahat();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSatirlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					satirlar();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnHesapDetay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hsp_detay();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hsp_pln();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
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
		stmt2.setDate(2, rss.getDate("TARIH"));
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
						sql  = "UPDATE EVRAK_NO SET EVRAK =" +  rss.getInt("EVRAK") + "  WHERE EID = 1"; ;
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
}
