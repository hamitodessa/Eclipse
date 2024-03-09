package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import OBS_C_2025.BACKUP_RESTORE;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.FIT_IMAGE;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;

import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;
import com.healthmarketscience.jackcess.crypt.CryptCodecProvider;
import com.healthmarketscience.jackcess.impl.CodecHandler;
import com.healthmarketscience.jackcess.impl.CodecProvider;
import com.healthmarketscience.jackcess.impl.DefaultCodecProvider;
import com.healthmarketscience.jackcess.impl.PageChannel;
import javax.swing.border.LineBorder;
import java.awt.Color;




@SuppressWarnings({"serial","unused"})
public class MSSQL_TO_MYSQL extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	Connection MS_conn = null;  
	Connection Yukleme_MS_conn = null;  
	Connection Yukleme_MS_conn_Izahat = null;  
	Connection Yukleme_MS_conn_Kur = null;  
	Connection MY_conn = null;  
	boolean vt = false;
	boolean ds = false;
	boolean tx = false;
	boolean em = false;
	private JTextField msUSER;
	private JTextField msSifre;
	private JTextField msServer;
	private JTextField msInstance;
	private JTextField msPort;
	private JTextField myUser;
	private JTextField mySifre;
	private JTextField myServer;
	private JCheckBox msLokal;
	private JCheckBox myLokal ;
	private JTextField txtYukleme;
	private JTextField txtEvrak;
	public MSSQL_TO_MYSQL() {
		setTitle("MS SQL TO MY SQL");
		setClosable(true);
		setBounds(100, 100, 892, 529);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(MSSQL_TO_MYSQL.class.getResource("/ICONLAR/icons8-data-transfer-30.png")), 16, 16));//
		textField = new JTextField();
		textField.setText("019");
		textField.setBounds(274, 33, 96, 20);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Dosya");
		lblNewLabel.setBounds(209, 36, 48, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("MSSQL");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(274, 11, 96, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("MYSQL");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(380, 11, 96, 14);
		panel.add(lblNewLabel_1_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(380, 33, 96, 20);
		panel.add(textField_1);

		JButton btnNewButton_1 = new JButton("CARI BAGLAN");
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
		btnNewButton_1.setBounds(28, 253, 184, 23);
		panel.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("KUR BAGLAN");
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
		btnNewButton_1_1.setBounds(222, 253, 143, 23);
		panel.add(btnNewButton_1_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(28, 287, 182, 167);
		panel_1.setLayout(null);
		panel.add(panel_1);

		JButton btnNewButton = new JButton("Hesap Plani");
		btnNewButton.setBounds(10, 11, 159, 23);
		panel_1.add(btnNewButton);

		JButton btnHesapDetay = new JButton("Hesap Detay");
		btnHesapDetay.setBounds(10, 40, 159, 23);
		panel_1.add(btnHesapDetay);

		JButton btnSatirlar = new JButton("Satirlar");
		btnSatirlar.setBounds(10, 70, 159, 23);
		panel_1.add(btnSatirlar);

		JButton btnIzahat = new JButton("Izahat");
		btnIzahat.setBounds(10, 100, 159, 23);
		panel_1.add(btnIzahat);

		JButton btnOzel = new JButton("Ozel");
		btnOzel.setBounds(10, 130, 159, 23);
		panel_1.add(btnOzel);

		RoundPanel panel_1_1 = new RoundPanel(new Color(0, 128, 128),15);
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(222, 287, 143, 167);
		panel.add(panel_1_1);

		JButton btnNewButton_2 = new JButton("Kur");
		btnNewButton_2.setBounds(10, 11, 123, 23);
		panel_1_1.add(btnNewButton_2);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBounds(375, 289, 143, 199);
		panel.add(panel_1_1_1);

		JButton btnNewButton_2_1 = new JButton("Mal");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mal();
				} catch (ClassNotFoundException | SQLException e1) {
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
		btnNewButton_2_1_1.setBounds(10, 35, 123, 23);
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
		btnNewButton_2_1_1_1.setBounds(10, 60, 123, 23);
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
		btnNewButton_2_1_1_1_1.setBounds(10, 85, 123, 23);
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
		btnNewButton_2_1_1_1_1_1.setBounds(10, 110, 123, 23);
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
		btnNewButton_2_1_1_1_1_1_1.setBounds(10, 135, 123, 23);
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
		btnNewButton_2_1_1_1_1_1_1_1.setBounds(10, 160, 123, 23);
		panel_1_1_1.add(btnNewButton_2_1_1_1_1_1_1_1);

		JButton btnNewButton_1_1_1 = new JButton("STOK BAGLAN");
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
		btnNewButton_1_1_1.setBounds(375, 255, 143, 23);
		panel.add(btnNewButton_1_1_1);
		
		
		
	
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "My Sql Baglanti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(459, 66, 244, 178);
		panel.add(panel_2);
		
		myLokal = new JCheckBox("Lokal");
		myLokal.setBounds(10, 29, 97, 23);
		panel_2.add(myLokal);
		myLokal.setSelected(true);
		
		JLabel lblNewLabel_2_1 = new JLabel("Kullanici");
		lblNewLabel_2_1.setBounds(10, 61, 69, 14);
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Sifre");
		lblNewLabel_3_1.setBounds(10, 92, 69, 14);
		panel_2.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Server / Port");
		lblNewLabel_4_1.setBounds(10, 119, 80, 14);
		panel_2.add(lblNewLabel_4_1);
		
		myServer = new JTextField();
		myServer.setText("78.189.76.247:3306");
		myServer.setBounds(100, 116, 125, 20);
		panel_2.add(myServer);
		myServer.setColumns(10);
		
		mySifre = new JTextField();
		mySifre.setText("197227oOk");
		mySifre.setBounds(100, 89, 125, 20);
		panel_2.add(mySifre);
		mySifre.setColumns(10);
		
		myUser = new JTextField();
		myUser.setText("root");
		myUser.setBounds(100, 58, 125, 20);
		panel_2.add(myUser);
		myUser.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Ms Sql Baglanti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(28, 61, 249, 183);
		panel.add(panel_3);
		
		msLokal = new JCheckBox("Lokal");
		msLokal.setBounds(10, 15, 97, 23);
		panel_3.add(msLokal);
		msLokal.setSelected(true);
		
		JLabel lblNewLabel_2 = new JLabel("Kullanici");
		lblNewLabel_2.setBounds(10, 53, 80, 14);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sifre");
		lblNewLabel_3.setBounds(10, 78, 80, 14);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Server");
		lblNewLabel_4.setBounds(10, 105, 80, 14);
		panel_3.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Instance");
		lblNewLabel_5.setBounds(10, 130, 80, 14);
		panel_3.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Port");
		lblNewLabel_6.setBounds(10, 155, 80, 14);
		panel_3.add(lblNewLabel_6);
		
		msPort = new JTextField();
		msPort.setBounds(100, 152, 125, 20);
		panel_3.add(msPort);
		msPort.setColumns(10);
		
		msInstance = new JTextField();
		msInstance.setText("SQLEXPRESS");
		msInstance.setBounds(100, 126, 125, 20);
		panel_3.add(msInstance);
		msInstance.setColumns(10);
		
		msServer = new JTextField();
		msServer.setText("78.189.76.247:1433");
		msServer.setBounds(100, 102, 125, 20);
		panel_3.add(msServer);
		msServer.setColumns(10);
		
		msSifre = new JTextField();
		msSifre.setText("197227oOk");
		msSifre.setBounds(100, 75, 125, 20);
		panel_3.add(msSifre);
		msSifre.setColumns(10);
		
		msUSER = new JTextField();
		msUSER.setText("sa");
		msUSER.setBounds(100, 48, 125, 20);
		panel_3.add(msUSER);
		msUSER.setColumns(10);
		
		JButton btnNewButton_1_1_1_1 = new JButton("KERESTE BAGLAN");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kereste_baglan();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_1_1_1.setBounds(540, 255, 143, 23);
		panel.add(btnNewButton_1_1_1_1);
		
		JPanel panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.setLayout(null);
		panel_1_1_1_1.setBounds(540, 289, 143, 199);
		panel.add(panel_1_1_1_1);
		
		JButton btnNewButton_2_1_2 = new JButton("Aciklama");
		btnNewButton_2_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ker_aciklama() ;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1_2.setBounds(10, 11, 123, 23);
		panel_1_1_1_1.add(btnNewButton_2_1_2);
		
		JButton btnNewButton_2_1_1_2 = new JButton("Kod - Kons");
		btnNewButton_2_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kod_kons() ;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1_1_2.setBounds(10, 35, 123, 23);
		panel_1_1_1_1.add(btnNewButton_2_1_1_2);
		
		JButton btnNewButton_2_1_1_1_2 = new JButton("Degiskenler");
		btnNewButton_2_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ker_degisken() ;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1_1_1_2.setBounds(10, 60, 123, 23);
		panel_1_1_1_1.add(btnNewButton_2_1_1_1_2);
		
		JButton btnNewButton_2_1_1_1_1_2 = new JButton("Kereste");
		btnNewButton_2_1_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kereste() ;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1_1_1_1_2.setBounds(10, 85, 123, 23);
		panel_1_1_1_1.add(btnNewButton_2_1_1_1_1_2);
		
		JButton btnNewButton_4 = new JButton("Baglan");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				baglan_yukleme();
			}
		});
		btnNewButton_4.setBounds(706, 255, 149, 23);
		panel.add(btnNewButton_4);
		
		RoundPanel panel_4 = new RoundPanel(new Color(0, 128, 128),15);
		panel_4.setBounds(706, 287, 149, 201);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Cari Yuklme");
		btnNewButton_3.setBounds(10, 11, 117, 23);
		panel_4.add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("hsppln");
		btnNewButton_5.setBounds(10, 38, 117, 23);
		panel_4.add(btnNewButton_5);
		
		txtEvrak = new JTextField();
		txtEvrak.setBounds(10, 102, 86, 20);
		panel_4.add(txtEvrak);
		txtEvrak.setText("0");
		txtEvrak.setColumns(10);
		
		txtYukleme = new JTextField();
		txtYukleme.setBounds(10, 71, 86, 20);
		panel_4.add(txtYukleme);
		txtYukleme.setText("555");
		txtYukleme.setColumns(10);
		
		JButton btnNewButton_6 = new JButton("Kur Baglan");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kur_yukleme();
			}
		});
		btnNewButton_6.setBounds(7, 133, 89, 23);
		panel_4.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Kur Yukle");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kur_yukle();
			}
		});
		btnNewButton_7.setBounds(10, 167, 89, 23);
		panel_4.add(btnNewButton_7);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hsp_pln_yukleme();
					hsp_detay_yukleme();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cari_yukleme();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
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
		if (textField.getText().equals("")) return;
		if (textField_1.getText().equals("")) return;
		if (msUSER.getText().equals("")) return;
		if (msSifre.getText().equals("")) return;
		if (msInstance.getText().equals("")) return;
		
		if (myUser.getText().equals("")) return;
		if (mySifre.getText().equals("")) return;
		if (myServer.getText().equals("")) return;
	
		
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
	public void kereste_baglan() throws ClassNotFoundException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		mssql_baglan("Ker");
		mysql_baglan("ker");
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void mssql_baglan(String modul) throws ClassNotFoundException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try
		{
			String cumle = "";
			String serverString = "" ;
			String port = "" ;
			if (msLokal.isSelected())
			{
				if ( ! msPort.getText().toString().equals("") )
				{
					port =  ":" + msPort.getText() ;
				}
				serverString = "localhost" + port ;
			}
			else
			{
				serverString = msServer.getText()  ;	
			}
			cumle = "jdbc:sqlserver://" + serverString + ";instanceName=" + msInstance.getText() + ";database=OK_" + modul + textField.getText() ;
			MS_conn = DriverManager.getConnection(cumle,msUSER.getText(),msSifre.getText()); //"sa","197227oOk"
			JOptionPane.showMessageDialog(null,"Ms SQL Baglanti Saglandi", "MS SQL baglan", JOptionPane.INFORMATION_MESSAGE);
		} 
		catch (Exception e)
		{  
			JOptionPane.showMessageDialog(null,e.getMessage(), "MS SQL baglan", JOptionPane.ERROR_MESSAGE);
		}  
	}
	void mysql_baglan(String modul) throws ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String serverString = "" ;
		if (myLokal.isSelected())
		{
			
			serverString = "localhost:" + myServer.getText() ;
		}
		else
		{
			serverString = myServer.getText()  ;	
		}
		
		String url = "jdbc:mysql://"+ serverString +"/ok_" + modul + textField_1.getText()  ; //pointing to no database.
		try 
		{
			MY_conn = DriverManager.getConnection(url, myUser.getText(),mySifre.getText()); // "root","197227oOk"
			JOptionPane.showMessageDialog(null,"My SQL Baglanti Saglandi", "MY SQL baglan", JOptionPane.INFORMATION_MESSAGE);
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
		//PreparedStatement stmt2 = MS_conn.prepareStatement("SELECT * FROM HESAP WITH (INDEX (IX_HESAP))  ORDER BY HESAP ");
		//rs = stmt2.executeQuery();
		
		String sql1  = "INSERT INTO HESAP (HESAP,UNVAN,KARTON,HESAP_CINSI,USER) " +
				" VALUES (?,?,?,?,?)" ;
		PreparedStatement stmt2 = MY_conn.prepareStatement(sql1);
		int satir  = 0 ;
		while(rss.next())
		{
			stmt2.setString(1, rss.getString("HESAP"));
			stmt2.setString(2, rss.getString("UNVAN"));
			stmt2.setString(3, rss.getString("KARTON"));
			stmt2.setString(4,rss.getString("HESAP_CINSI"));
			stmt2.setString(5, rss.getString(4));
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 300 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();
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
		
		sql  = "INSERT INTO HESAP_DETAY (D_HESAP,YETKILI,ADRES_1,ADRES_2,SEMT,SEHIR,VERGI_DAIRESI,VERGI_NO,TEL_1,TEL_2, " + 
				" TEL_3,FAX,OZEL_KOD_1,OZEL_KOD_2,OZEL_KOD_3,WEB,E_MAIL,TC_KIMLIK,ACIKLAMA,SMS_GONDER,RESIM)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt2 = MY_conn.prepareStatement(sql);
		int satir = 0 ;
		
		while(rss.next()){
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
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 300 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();
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
		////
		
		int satir = 0 ;
		sql  = "INSERT INTO SATIRLAR (HESAP,TARIH,H,EVRAK,CINS,KUR,BORC,ALACAK,KOD,USER) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?)" ;
		stmt2 = null;
		stmt2 = MY_conn.prepareStatement(sql);

		while(rss.next()){
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
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 1000 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();

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
		sql  = "INSERT INTO IZAHAT (EVRAK,IZAHAT) " +
				" VALUES (?,?)" ;
		stmt2 = MY_conn.prepareStatement(sql);
		int satir = 0 ;
		while(rss.next()){
			stmt2.setInt(1, rss.getInt("EVRAK"));
			stmt2.setString(2,  rss.getString("IZAHAT"));
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 1000 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();

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
		sql  ="INSERT INTO KURLAR (Tarih,Kur,MA,MS,SA,SS,BA,BS) " +
				" VALUES (?,?,?,?,?,?,?,?)" ;
		stmt2= null;
		stmt2 = MY_conn.prepareStatement(sql);
		int satir = 0 ;
		while(rss.next()){
			stmt2.setDate(1,  rss.getDate("Tarih"));
			stmt2.setString(2,  rss.getString("Kur"));
			stmt2.setDouble(3, rss.getDouble("MA"));
			stmt2.setDouble(4, rss.getDouble("MS"));
			stmt2.setDouble(5, rss.getDouble("SA"));
			stmt2.setDouble(6, rss.getDouble("SS"));
			stmt2.setDouble(7,rss.getDouble("BA"));
			stmt2.setDouble(8, rss.getDouble("BS"));
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 1000 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();

		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void mal () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		ResultSet	rs = null;
		ResultSet	rss = null;
		String sql = "SELECT * FROM MAL Order by Kodu     ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		sql  = "INSERT INTO MAL (Kodu,Adi,Birim,Kusurat,Sinif,Ana_Grup,Alt_Grup,Aciklama_1,Aciklama_2,Ozel_Kod_1 " +
				" ,Ozel_Kod_2,Barkod,Mensei,Agirlik,Fiat,Fiat_2,Fiat_3,Recete,Kdv,Resim,Depo , Ozel_Kod_3,USER) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		
		PreparedStatement stmt2 = MY_conn.prepareStatement(sql);
		int satir = 0 ;
		while(rss.next()){
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
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 1000 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();

		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void diger () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		ResultSet	rss = null;
		String sql = "SELECT * FROM ACIKLAMA   ORDER BY  EVRAK_NO ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		sql  ="INSERT INTO ACIKLAMA (EVRAK_CINS,SATIR,EVRAK_NO,ACIKLAMA,Gir_Cik) " +
				" VALUES (?,?,?,?,?)" ;
		PreparedStatement stmt2 = MY_conn.prepareStatement(sql);
		int satir= 0 ;
		while(rss.next())
		{
			stmt2.setString(1,  rss.getString("EVRAK_CINS"));
			stmt2.setInt(2, rss.getInt("SATIR"));
			stmt2.setString(3, rss.getString("EVRAK_NO"));
			stmt2.setString(4,  rss.getString("ACIKLAMA"));
			stmt2.setString(5,  rss.getString("Gir_Cik"));
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 1000 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();

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
		String sql = "SELECT * FROM BOZUK_MAL    Order by Evrak_No ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		sql  = "INSERT INTO MAL (Evrak_No,Kodu,Tarih,Miktar,Fiat,Ana_Grup,Alt_Grup,Depo,Ozel_Kod " +
				" ,Izahat,Cins,,USER) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt2 = MY_conn.prepareStatement(sql);
		int satir = 0 ;
		while(rss.next()){
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
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 1000 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();

		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void fatura () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		ResultSet	rs = null;
		ResultSet	rss = null;

		String sql = "SELECT * FROM FATURA  Order by Fatura_No ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		sql  ="INSERT INTO FATURA (Fatura_No,Kodu,Depo,Fiat,Tevkifat,Miktar,Gir_Cik,Tutar,Iskonto,Kdv,Tarih,Izahat " +
				" ,Doviz,Adres_Firma,Cari_Firma,Ozel_Kod,Kur,Cins,Ana_Grup,Alt_Grup, USER ) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;

		PreparedStatement stmt2 = MY_conn.prepareStatement(sql);
		int satir = 0 ;
		while(rss.next()){
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
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 1000 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void irsaliye () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		ResultSet	rs = null;
		ResultSet	rss = null;
	
		String sql = "SELECT * FROM IRSALIYE  Order by Irsaliye_No  ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		sql  ="INSERT INTO IRSALIYE (Irsaliye_No,Kodu,Depo,Fiat,Iskonto,Miktar,Tutar,Kdv, Tarih,Doviz,Kur,Firma " +
				",Cari_Hesap_Kodu,Sevk_Tarihi,Ozel_Kod,Ana_Grup,Alt_Grup,Fatura_No,Hareket,Cins, USER ,Izahat) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		
		PreparedStatement stmt2 = MY_conn.prepareStatement(sql);
		int satir = 0 ;
		while(rss.next()){
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
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 1000 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();

		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void recete () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		ResultSet	rs = null;
		ResultSet	rss = null;
		
		String sql = "SELECT * FROM RECETE  Order by Recete_No  ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		sql  ="INSERT INTO RECETE (Recete_No,Durum,Tur,Kodu,Miktar,Ana_Grup,Alt_Grup, USER ) " +
				" VALUES (?,?,?,?,?,?,?,?)" ;
		
		PreparedStatement stmt2 = MY_conn.prepareStatement(sql);
		int satir = 0 ;
		while(rss.next()){
			stmt2.setString(1,rss.getString("Recete_No"));
			stmt2.setBoolean(2, rss.getBoolean("Durum"));
			stmt2.setString(3, rss.getString("Tur"));
			stmt2.setString(4, rss.getString("Kodu"));
			stmt2.setFloat(5, rss.getFloat("Miktar"));
			stmt2.setInt(6, rss.getInt("Ana_Grup"));
			stmt2.setInt(7, rss.getInt("Alt_Grup"));
			stmt2.setString(8,rss.getString("USER"));
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 1000 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();

		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void  stok () throws ClassNotFoundException, SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		ResultSet	rs = null;
		ResultSet	rss = null;
		
		String sql = "SELECT * FROM STOK  ORDER BY Tarih ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		sql  ="INSERT INTO STOK (Evrak_No,Evrak_Cins,Tarih,Depo,Urun_Kodu,Miktar,Fiat,Tutar,Kdvli_Tutar,Hareket,Izahat " +
				" ,Ana_Grup,Alt_Grup,Kur,B1,Doviz,Hesap_Kodu, USER ) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		
		PreparedStatement stmt2 = MY_conn.prepareStatement(sql);
		int satir = 0 ;
		while(rss.next()){
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
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 1000 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();

		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	private void ker_aciklama() throws SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		ResultSet	rss = null;
		String sql = "SELECT * FROM ACIKLAMA   ORDER BY  EVRAK_NO ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		sql  ="INSERT INTO ACIKLAMA (EVRAK_CINS,SATIR,EVRAK_NO,ACIKLAMA,Gir_Cik) " +
				" VALUES (?,?,?,?,?)" ;
		PreparedStatement stmt2 = MY_conn.prepareStatement(sql);
		int satir= 0 ;
		while(rss.next())
		{
			stmt2.setString(1,  rss.getString("EVRAK_CINS"));
			stmt2.setInt(2, rss.getInt("SATIR"));
			stmt2.setString(3, rss.getString("EVRAK_NO"));
			stmt2.setString(4,  rss.getString("ACIKLAMA"));
			stmt2.setString(5,  rss.getString("Gir_Cik"));
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 1000 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	private void kod_kons() throws SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		ResultSet	rss = null;
		String sql = "SELECT * FROM KOD_ACIKLAMA   ORDER BY  KOD ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		sql  ="INSERT INTO KOD_ACIKLAMA (KOD,ACIKLAMA) " +
				" VALUES (?,?)" ;
		PreparedStatement stmt2 = MY_conn.prepareStatement(sql);
		int satir= 0 ;
		while(rss.next())
		{
			stmt2.setString(1,  rss.getString("KOD"));
			stmt2.setString(2,  rss.getString("ACIKLAMA"));
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 1000 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();
		// KONS
		rss = null;
		sql = "SELECT * FROM KONS_ACIKLAMA   ORDER BY  KONS ";
		stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		sql  ="INSERT INTO KONS_ACIKLAMA (KONS,ACIKLAMA) " +
				" VALUES (?,?)" ;
		stmt2 = MY_conn.prepareStatement(sql);
		satir= 0 ;
		while(rss.next())
		{
			stmt2.setString(1,  rss.getString("KONS"));
			stmt2.setString(2,  rss.getString("ACIKLAMA"));
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 1000 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	private void ker_degisken() throws SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		ResultSet	rss = null;
		//ALt GRUP
		String sql = "SELECT * FROM ALT_GRUP_DEGISKEN    ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = null;
		rss = stmt.executeQuery(sql);
		PreparedStatement stmt2 = MY_conn.prepareStatement(sql);
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
		//NAKLIYECI
		sql = "SELECT * FROM NAKLIYECI    ";
		rss = null;
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  ="INSERT INTO NAKLIYECI  (NAKID_Y,UNVAN,USER) " +
					" VALUES (?,?,?)" ;
			stmt2= null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setInt(1,  rss.getInt("NAKID_Y"));
			stmt2.setString(2,  rss.getString("UNVAN"));
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
		//PAKET NO
		sql = "SELECT * FROM PAKET_NO   ";
		rss = null;
		rss = stmt.executeQuery(sql);
		while(rss.next())
		{
			sql  = "INSERT INTO PAKET_NO (Pak_No , Konsimento) " +
					" VALUES (?,?)" ;

			stmt2 = null;
			stmt2 = MY_conn.prepareStatement(sql);
			stmt2.setInt(1, rss.getInt("Pak_No"));
			stmt2.setString(2,  rss.getString("Konsimento"));
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
	getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	private void kereste() throws SQLException
	{
		Runnable runner = new Runnable()
		{ public void run() {
			try {
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		ResultSet	rss = null;
		String sql = "SELECT * FROM KERESTE  ORDER BY  Evrak_No , Satir "; // 
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		sql  ="INSERT INTO KERESTE (Evrak_No,Barkod,Kodu,Paket_No,Konsimento,Miktar,Tarih,Kdv,Doviz,Fiat,Tutar,Kur,Cari_Firma,Adres_Firma,Iskonto " + //15
				" ,Tevkifat,Ana_Grup,Alt_Grup,Depo,Ozel_Kod,Izahat,Nakliyeci,USER,Cikis_Evrak,CTarih,CKdv,CDoviz,CFiat,CTutar,Ckur,CCari_Firma,CAdres_Firma " + //17
				" ,CIskonto,CTevkifat,CAna_Grup,CAlt_Grup,CDepo,COzel_Kod,CIzahat,CNakliyeci,CUSER,Mensei,Satir,CSatir) " + //9
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;

		PreparedStatement stmt2 = MY_conn.prepareStatement(sql);
		int satir= 0 ;
		stmt2 = MY_conn.prepareStatement(sql);
		while(rss.next())
		{
			stmt2.setString(1,rss.getString("Evrak_No"));
			stmt2.setString(2, rss.getString("Barkod"));
			stmt2.setString(3,rss.getString("Kodu"));
			stmt2.setString(4,rss.getString("Paket_No"));
			stmt2.setString(5,rss.getString("Konsimento"));
			stmt2.setDouble(6, rss.getDouble("Miktar"));
			Timestamp timestamp =rss.getTimestamp("Tarih");
			Date    date1 = null;
			String formatli = "";
			if (timestamp != null)
			{date1 = new java.util.Date(timestamp.getTime());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss.ss");
			formatli = formatter.format(date1);
			}

			stmt2.setString(7,formatli);
			stmt2.setDouble(8, rss.getDouble("Kdv"));
			stmt2.setString(9,rss.getString("Doviz"));
			stmt2.setDouble(10,rss.getDouble("Fiat"));
			stmt2.setDouble(11,rss.getDouble("Tutar"));
			stmt2.setDouble(12, rss.getDouble("Kur"));
			stmt2.setString(13,rss.getString("Cari_Firma"));
			stmt2.setString(14,rss.getString("Adres_Firma"));
			stmt2.setDouble(15, rss.getDouble("Iskonto"));
			stmt2.setDouble(16, rss.getDouble("Tevkifat"));
			stmt2.setInt(17, rss.getInt("Ana_Grup"));
			stmt2.setInt(18,  rss.getInt("Alt_Grup"));
			stmt2.setInt(19,  rss.getInt("Depo"));
			stmt2.setInt(20, rss.getInt("Ozel_Kod"));
			stmt2.setString(21,rss.getString("Izahat"));
			stmt2.setInt(22,  rss.getInt("Nakliyeci"));
			stmt2.setString(23,  rss.getString("USER"));
			stmt2.setString(24,rss.getString("Cikis_Evrak"));
			if(! rss.getTimestamp("CTarih").toString().equals("1900-01-01 00:00:00.0"))
			{
			timestamp =rss.getTimestamp("CTarih");
			date1 = null;
			formatli = "";
			if (timestamp != null)
			{date1 = new java.util.Date(timestamp.getTime());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss.ss");
			formatli = formatter.format(date1);
			}
			stmt2.setString(25,formatli);
			}
			else {
				stmt2.setString(25,"1900-01-01");
			}
			stmt2.setDouble(26,rss.getDouble("CKdv"));
			stmt2.setString(27,rss.getString("CDoviz"));
			stmt2.setDouble(28, rss.getDouble("CFiat"));
			stmt2.setDouble(29, rss.getDouble("CTutar"));
			stmt2.setDouble(30, rss.getDouble("CKur"));
			stmt2.setString(31,rss.getString("CCari_Firma"));
			stmt2.setString(32,rss.getString("CAdres_Firma"));
			stmt2.setDouble(33, rss.getDouble("CIskonto"));
			stmt2.setDouble(34,  rss.getInt("CTevkifat"));
			stmt2.setInt(35, rss.getInt("CAna_Grup"));
			stmt2.setInt(36, rss.getInt("CAlt_Grup"));
			stmt2.setInt(37,  rss.getInt("CDepo"));
			stmt2.setInt(38,rss.getInt("COzel_Kod"));
			stmt2.setString(39,rss.getString("CIzahat"));
			stmt2.setInt(40, rss.getInt("CNakliyeci"));
			stmt2.setString(41,  rss.getString("CUSER"));
			stmt2.setInt(42,  rss.getInt("Mensei"));
			stmt2.setInt(43,  rss.getInt("Satir"));
			stmt2.setInt(44,  rss.getInt("CSatir"));
			stmt2.addBatch();
			satir +=1 ;
			if ((satir ) % 200 == 0) 
			{
				stmt2.executeBatch();
			}
		}
		stmt2.executeBatch();
		stmt2.close();
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kereste Aktar", JOptionPane.ERROR_MESSAGE);   
		}
		}
		};
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	private void baglan_yukleme() {
		
		if (textField.getText().equals("")) return;
		if (txtYukleme.getText().equals("")) return;
		if (txtEvrak.getText().equals("")) return;
		if (msUSER.getText().equals("")) return;
		if (msSifre.getText().equals("")) return;
		if (msInstance.getText().equals("")) return;
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try {
			mssql_baglan("Car");
			yukleme_mssql_baglan("Car");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void yukleme_mssql_baglan(String modul) throws ClassNotFoundException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try
		{
			String cumle = "";
			String serverString = "" ;
			String port = "" ;
			if (msLokal.isSelected())
			{
				if ( ! msPort.getText().toString().equals("") )
				{
					port =  ":" + msPort.getText() ;
				}
				serverString = "localhost" + port ;
			}
			else
			{
				serverString = msServer.getText()  ;	
			}
			cumle = "jdbc:sqlserver://" + serverString + ";instanceName=" + msInstance.getText() + ";database=OK_" + modul + txtYukleme.getText() ;
			Yukleme_MS_conn = DriverManager.getConnection(cumle,msUSER.getText(),msSifre.getText()); //"sa","197227oOk"
			Yukleme_MS_conn_Izahat = DriverManager.getConnection(cumle,msUSER.getText(),msSifre.getText()); //"sa","197227oOk"
			JOptionPane.showMessageDialog(null,"Yukleme Ms SQL Baglanti Saglandi", "MS SQL baglan", JOptionPane.INFORMATION_MESSAGE);
		} 
		catch (Exception e)
		{  
			JOptionPane.showMessageDialog(null,e.getMessage(), "MS SQL baglan", JOptionPane.ERROR_MESSAGE);
		}  
	}
	private void cari_yukleme() throws SQLException
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		ResultSet	rs = null;
		ResultSet	rss = null;
		PreparedStatement stmt2;
		PreparedStatement stmtizahat;
		String sql = "SELECT [HESAP] ,[TARIH] ,[H] ,SATIRLAR.[EVRAK],[CINS] ,[KUR] ,[BORC],[ALACAK]  ,[KOD],[USER] ,IZAHAT "
					+ " FROM [OK_Car019].[dbo].[SATIRLAR] left join [OK_Car019].[dbo].[IZAHAT] on SATIRLAR.EVRAK = IZAHAT.EVRAK "
					+ " ORDER BY  SATIRLAR.TARIH ,SATIRLAR.EVRAK , H DESC ";
		Statement stmt = MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		int satir = 0 ;
		sql  = "INSERT INTO SATIRLAR (HESAP,TARIH,H,EVRAK,CINS,KUR,BORC,ALACAK,KOD,[USER]) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?)" ;
		stmt2 = null;
		stmt2 = Yukleme_MS_conn.prepareStatement(sql);
		String sklizahat  = "INSERT INTO IZAHAT (EVRAK,IZAHAT) " +
				" VALUES (?,?)" ;
		stmtizahat = null;
		stmtizahat = Yukleme_MS_conn_Izahat.prepareStatement(sklizahat);
		int eVRAKK = Integer.parseInt(txtEvrak.getText());
	    int  kont = 0;
	    Timestamp timestamp ;
	    Date date1 ;
	    Calendar cal ;
	    for (int kackere = 1;kackere <= 11; kackere++) {
	    	while(rss.next()){
	    		stmt2.setString(1, rss.getString("HESAP"));
	    		if (kont != rss.getInt("EVRAK"))
	    		{
	    			eVRAKK +=1 ;
	    			stmtizahat.setInt(1, eVRAKK);
	    			stmtizahat.setString(2,  rss.getString("IZAHAT"));
	    			stmtizahat.addBatch();
	    			if ((satir ) % 3000 == 0) 
	    			{
	    				stmtizahat.executeBatch();
	    			}
	    		}
	    		kont = rss.getInt("EVRAK") ;
	    		timestamp =rss.getTimestamp("TARIH");
	    		date1 = new java.util.Date(timestamp.getTime());
	    		cal = Calendar.getInstance();
	    		cal.setTime(date1);
	    		cal.add(Calendar.YEAR,kackere + 7);
	    		java.sql.Timestamp timestamp1 = new java.sql.Timestamp(cal.getTimeInMillis());
	    		stmt2.setTimestamp(2, timestamp1);
	    		stmt2.setString(3,  rss.getString("H"));
	    		stmt2.setInt(4, eVRAKK);
	    		stmt2.setString(5,rss.getString("CINS"));
	    		stmt2.setDouble(6,rss.getDouble("KUR"));
	    		stmt2.setDouble(7,rss.getDouble("BORC"));
	    		stmt2.setDouble(8,rss.getDouble("ALACAK"));
	    		stmt2.setString(9,rss.getString("KOD"));
	    		stmt2.setString(10,rss.getString("USER"));
	    		stmt2.addBatch();
	    		satir +=1 ;
	    		if ((satir ) % 3000 == 0) 
	    		{
	    			stmt2.executeBatch();
	    		}
	    	}
	    	satir = 0;
	    	rss.beforeFirst();
	    }
	    
		stmt2.executeBatch();
		stmtizahat.executeBatch();
		stmt2.close();
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	void hsp_pln_yukleme() throws ClassNotFoundException, SQLException
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
		
		
		String sklizahat  =  "INSERT INTO HESAP (HESAP,UNVAN,KARTON,HESAP_CINSI,[USER]) " +
				" VALUES (?,?,?,?,?)" ;
		PreparedStatement stmtizahat = Yukleme_MS_conn.prepareStatement(sql);
		stmtizahat = Yukleme_MS_conn_Izahat.prepareStatement(sklizahat);
		
		int satir  = 0 ;
		while(rss.next())
		{
			stmtizahat.setString(1, rss.getString("HESAP"));
			stmtizahat.setString(2, rss.getString("UNVAN"));
			stmtizahat.setString(3, rss.getString("KARTON"));
			stmtizahat.setString(4,rss.getString("HESAP_CINSI"));
			stmtizahat.setString(5, rss.getString(4));
			stmtizahat.addBatch();
			satir +=1 ;
			if ((satir ) % 300 == 0) 
			{
				stmtizahat.executeBatch();
			}
		}
		stmtizahat.executeBatch();
		stmtizahat.close();
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	void hsp_detay_yukleme() throws ClassNotFoundException, SQLException
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
		
		sql  = "INSERT INTO HESAP_DETAY (D_HESAP,YETKILI,ADRES_1,ADRES_2,SEMT,SEHIR,VERGI_DAIRESI,VERGI_NO,TEL_1,TEL_2, " + 
				" TEL_3,FAX,OZEL_KOD_1,OZEL_KOD_2,OZEL_KOD_3,WEB,E_MAIL,TC_KIMLIK,ACIKLAMA,SMS_GONDER,RESIM)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmtizahat = Yukleme_MS_conn.prepareStatement(sql);
		stmtizahat = Yukleme_MS_conn_Izahat.prepareStatement(sql);
		
		int satir = 0 ;
		
		while(rss.next()){
			stmtizahat.setString(1,rss.getString("D_HESAP"));
			stmtizahat.setString(2,rss.getString("YETKILI"));
			stmtizahat.setString(3, rss.getString("ADRES_1"));
			stmtizahat.setString(4, rss.getString("ADRES_2"));
			stmtizahat.setString(5, rss.getString("SEMT"));
			stmtizahat.setString(6, rss.getString("SEHIR"));
			stmtizahat.setString(7, rss.getString("VERGI_DAIRESI"));
			stmtizahat.setString(8, rss.getString("VERGI_NO"));
			stmtizahat.setString(9, rss.getString("TEL_1"));
			stmtizahat.setString(10, rss.getString("TEL_2"));
			stmtizahat.setString(11, rss.getString("TEL_3"));
			stmtizahat.setString(12, rss.getString("FAX"));
			stmtizahat.setString(13, rss.getString("OZEL_KOD_1"));
			stmtizahat.setString(14, rss.getString("OZEL_KOD_2"));
			stmtizahat.setString(15, rss.getString("OZEL_KOD_3"));
			stmtizahat.setString(16, rss.getString("WEB"));
			stmtizahat.setString(17, rss.getString("E_MAIL"));
			stmtizahat.setString(18, rss.getString("TC_KIMLIK"));
			stmtizahat.setString(19, rss.getString("ACIKLAMA"));
			stmtizahat.setBoolean(20, rss.getBoolean("SMS_GONDER"));
			if (  rss.getBytes("RESIM") != null)
			{

				stmtizahat.setBytes(21,rss.getBytes("RESIM"));
			}
			else
			{
				stmtizahat.setBytes(21,null);
			}
			stmtizahat.addBatch();
			satir +=1 ;
			if ((satir ) % 300 == 0) 
			{
				stmtizahat.executeBatch();
			}
		}
		stmtizahat.executeBatch();
		stmtizahat.close();
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	private void kur_yukleme()
	{
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try {
			mssql_baglan_yuklme("Kur");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	private void mssql_baglan_yuklme(String modul)
	{
		try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String cumle = "";
		String serverString = "" ;
		String port = "" ;
		if (msLokal.isSelected())
		{
			if ( ! msPort.getText().toString().equals("") )
			{
				port =  ":" + msPort.getText() ;
			}
			serverString = "localhost" + port ;
		}
		else
		{
			serverString = msServer.getText()  ;	
		}
		cumle = "jdbc:sqlserver://" + serverString + ";instanceName=" + msInstance.getText() + ";database=OK_" + modul + textField.getText() ;
		Yukleme_MS_conn = DriverManager.getConnection(cumle,msUSER.getText(),msSifre.getText()); //"sa","197227oOk"

		cumle = "jdbc:sqlserver://" + serverString + ";instanceName=" + msInstance.getText() + ";database=OK_" + modul + txtYukleme.getText() ;

		Yukleme_MS_conn_Kur = DriverManager.getConnection(cumle,msUSER.getText(),msSifre.getText()); //"sa","197227oOk"
		JOptionPane.showMessageDialog(null,"Yukleme Ms SQL Baglanti Saglandi", "MS SQL baglan", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void kur_yukle()
	{
		try {
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

			ResultSet	rs = null;
			ResultSet	rss = null;
			PreparedStatement stmt2; 
			String sql = "SELECT * FROM Kurlar   WHERE Kur = 'USD' and month(tarih) = '01' ORDER BY  Tarih ";
			Statement stmt = Yukleme_MS_conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rss = stmt.executeQuery(sql);
			sql  ="INSERT INTO KURLAR (Tarih,Kur,MA,MS,SA,SS,BA,BS) " +
					" VALUES (?,?,?,?,?,?,?,?)" ;
			stmt2= null;
			stmt2 =Yukleme_MS_conn_Kur.prepareStatement(sql);
			int satir = 0 ;
			Timestamp timestamp ;
			Date date1 ;
			Calendar cal ;
			int kackere ;
			for (kackere = 1;kackere <= 3; kackere++) {
				System.err.println(kackere);
				while(rss.next())
				{
					timestamp = rss.getTimestamp("TARIH");
					date1 = new java.util.Date(timestamp.getTime());
					cal = Calendar.getInstance();
					cal.setTime(date1);
					cal.add(Calendar.YEAR,kackere + 7);
					java.sql.Timestamp timestamp1 = new java.sql.Timestamp(cal.getTimeInMillis());
					System.out.println(timestamp1);
					stmt2.setTimestamp(1, timestamp1);
					stmt2.setString(2, rss.getString("Kur"));
					stmt2.setDouble(3, rss.getDouble("MA"));
					stmt2.setDouble(4, rss.getDouble("MS"));
					stmt2.setDouble(5, rss.getDouble("SA"));
					stmt2.setDouble(6, rss.getDouble("SS"));
					stmt2.setDouble(7, rss.getDouble("BA"));
					stmt2.setDouble(8, rss.getDouble("BS"));
					stmt2.addBatch();
					satir +=1 ;
					if ((satir ) % 500 == 0) 
					{
						stmt2.executeBatch();
					}

				}
				satir = 0 ;
				stmt2.executeBatch();
				rss.beforeFirst();
			}
			//stmt2.executeBatch();
			stmt2.close();

			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
	
