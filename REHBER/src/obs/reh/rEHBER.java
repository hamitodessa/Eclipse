package obs.reh;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import OBS_C_2025.ADRES_MSSQL;
import OBS_C_2025.ADRES_MYSQL;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.CARI_HESAP_MSSQL;
import OBS_C_2025.CARI_HESAP_MYSQL;
import OBS_C_2025.CONNECT;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GUNLUK_MSSQL;
import OBS_C_2025.GUNLUK_MYSQL;
import OBS_C_2025.KAMBIYO_MSSQL;
import OBS_C_2025.KAMBIYO_MYSQL;
import OBS_C_2025.KERESTE_MSSQL;
import OBS_C_2025.KERESTE_MYSQL;
import OBS_C_2025.KUR_MSSQL;
import OBS_C_2025.KUR_MYSQL;
import OBS_C_2025.LOG_MAIL_OKU;
import OBS_C_2025.OBS_ORTAK_MSSQL;
import OBS_C_2025.OBS_ORTAK_MYSQL;
import OBS_C_2025.SMS_MSSQL;
import OBS_C_2025.SMS_MYSQL;
import OBS_C_2025.STOK_MSSQL;
import OBS_C_2025.STOK_MYSQL;
import OBS_C_2025.Server_Bilgi;
import fih.FIHRIST_MSSQL;
import fih.FIHRIST_MYSQL;
import obs.ayarlar.aNA_Class;

import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import fih.FIHRIST_ACCESS;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JToolBar;

public class rEHBER extends JFrame {

	private static final long serialVersionUID = 1L;
	boolean FIH_DOS_VAR;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JPasswordField passwordField;
	
	
	BAGLAN bAGLAN = new BAGLAN();
	aNA_Class oac = new aNA_Class();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rEHBER frame = new rEHBER();
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
	public rEHBER() {
		
		 try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		scrollPane.setViewportView(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Rehber", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setMinimumSize(new Dimension(0, 200));
		scrollPane_1.setMaximumSize(new Dimension(0, 200));
		splitPane.setLeftComponent(scrollPane_1);
		
		JPanel panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 33, 900, 5);
		panel_2.add(separator);
		
		JLabel lblNewLabel = new JLabel("Adi");
		lblNewLabel.setBounds(10, 11, 48, 14);
		panel_2.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(68, 7, 372, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("|<<");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(68, 37, 60, 23);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<<");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(128, 37, 54, 23);
		panel_2.add(btnNewButton_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(new Color(0, 0, 205));
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(188, 39, 60, 20);
		panel_2.add(textField_1);
		
		JButton btnNewButton_2 = new JButton(">>");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(258, 37, 54, 23);
		panel_2.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton(">>|");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(308, 37, 60, 23);
		panel_2.add(btnNewButton_3);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 65, 900, 5);
		panel_2.add(separator_1);
		
		JLabel lblNewLabel_1 = new JLabel("Adi");
		lblNewLabel_1.setBounds(10, 81, 48, 14);
		panel_2.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(68, 78, 372, 20);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Tel_1");
		lblNewLabel_2.setBounds(10, 110, 48, 14);
		panel_2.add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(68, 107, 148, 20);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tel_2");
		lblNewLabel_2_1.setBounds(234, 107, 48, 14);
		panel_2.add(lblNewLabel_2_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(292, 104, 148, 20);
		panel_2.add(textField_4);
		
		JLabel lblNewLabel_2_2 = new JLabel("Tel_3");
		lblNewLabel_2_2.setBounds(450, 107, 48, 14);
		panel_2.add(lblNewLabel_2_2);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(508, 104, 148, 20);
		panel_2.add(textField_5);
		
		JLabel lblNewLabel_2_3 = new JLabel("Tel_4");
		lblNewLabel_2_3.setBounds(666, 107, 48, 14);
		panel_2.add(lblNewLabel_2_3);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(724, 104, 148, 20);
		panel_2.add(textField_6);
		
		JLabel lblNewLabel_2_4 = new JLabel("Fax");
		lblNewLabel_2_4.setBounds(10, 136, 48, 14);
		panel_2.add(lblNewLabel_2_4);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(68, 133, 148, 20);
		panel_2.add(textField_7);
		
		JLabel lblNewLabel_2_5 = new JLabel("Mail");
		lblNewLabel_2_5.setBounds(234, 135, 48, 14);
		panel_2.add(lblNewLabel_2_5);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(292, 132, 364, 20);
		panel_2.add(textField_8);
		
		JLabel lblNewLabel_2_6 = new JLabel("Not");
		lblNewLabel_2_6.setBounds(10, 164, 48, 14);
		panel_2.add(lblNewLabel_2_6);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(68, 161, 148, 20);
		panel_2.add(textField_9);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_2);
		
		table = new JTable();
		scrollPane_2.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Ayarlar", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Veritabani");
		lblNewLabel_3.setBounds(10, 39, 84, 14);
		panel_1.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(118, 35, 126, 22);
		panel_1.add(comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("Kodu");
		lblNewLabel_4.setBounds(10, 99, 48, 14);
		panel_1.add(lblNewLabel_4);
		
		textField_10 = new JTextField();
		textField_10.setBounds(118, 96, 96, 20);
		panel_1.add(textField_10);
		textField_10.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Lokal");
		chckbxNewCheckBox.setBounds(266, 35, 99, 23);
		panel_1.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Server");
		chckbxNewCheckBox_1.setBounds(378, 35, 99, 23);
		panel_1.add(chckbxNewCheckBox_1);
		
		JLabel lblInstance = new JLabel("Instance");
		lblInstance.setBounds(9, 150, 68, 14);
		panel_1.add(lblInstance);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(118, 145, 157, 22);
		panel_1.add(comboBox_1);
		
		JLabel lblServer = new JLabel("Server / Port");
		lblServer.setBounds(9, 180, 78, 14);
		panel_1.add(lblServer);
		
		textField_11 = new JTextField();
		textField_11.setText("");
		textField_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_11.setColumns(10);
		textField_11.setBounds(118, 175, 157, 20);
		panel_1.add(textField_11);
		
		JLabel lblKayitserver = new JLabel("Serverler");
		lblKayitserver.setBounds(9, 210, 78, 14);
		panel_1.add(lblKayitserver);
		
		JComboBox<String> cmbip = new JComboBox<String>();
		cmbip.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbip.setEditable(true);
		cmbip.setBounds(118, 205, 157, 22);
		panel_1.add(cmbip);
		
		JLabel lblSifre = new JLabel("Kullanici");
		lblSifre.setBounds(9, 240, 68, 14);
		panel_1.add(lblSifre);
		
		textField_12 = new JTextField();
		textField_12.setText("");
		textField_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_12.setColumns(10);
		textField_12.setBounds(118, 235, 157, 20);
		panel_1.add(textField_12);
		
		passwordField = new JPasswordField();
		passwordField.setText("");
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 11));
		passwordField.setBounds(118, 265, 157, 20);
		panel_1.add(passwordField);
		
		JLabel lblKullanici = new JLabel("Sifre");
		lblKullanici.setBounds(9, 270, 68, 14);
		panel_1.add(lblKullanici);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(601, 39, 138, 27);
		panel_1.add(toolBar);
		
		//************SURUCU KONTROL**************************
		GLOBAL.surucu_kontrol();
		calisma_dizini_oku() ;
		FIHRIST_ACCESS  fih_Access = new FIHRIST_ACCESS(aNA_Class._IFihrist );
		fih_Access.baglan();
	}
	void calisma_dizini_oku() 
	{
		try 
		{
			int say = 9;
			bAGLAN.cONNECT("Admin");
			// Cari
			cONN_AKTAR( BAGLAN.fihDizin.hAN_SQL );
			String hangi_sql =  BAGLAN.cariDizin.hAN_SQL;
			oac._IFihristCon = oac._IConn ;
			oac._IFihrist = hangi_sql.equals("MS SQL") ? new  FIHRIST_MSSQL() : new  FIHRIST_MYSQL();
		


			fihrist_calisma_dizini_oku();
		

		} catch (Exception e) {
			//OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, e.getMessage());
		}
	}
	private void cONN_AKTAR( String dIZIN )
	{
		oac._IConn = dIZIN.equals("MS SQL") ? new OBS_ORTAK_MSSQL() : new OBS_ORTAK_MYSQL();
	}
	void fihrist_calisma_dizini_oku() throws ClassNotFoundException, SQLException
	{
		CONNECT s_CONN = new CONNECT( oac._IFihristCon);
		if (BAGLAN.fihDizin.yER.equals(""))
		{
			aNA_Class.FIHRIST_CONN  = false;
			FIH_DOS_VAR = false;
			return;
		}
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setIns(BAGLAN.fihDizin.iNSTANCE); 
		sBilgi.setKull(BAGLAN.fihDizin.kULLANICI) ;
		sBilgi.setSifre(BAGLAN.fihDizin.sIFRESI);
		sBilgi.setPort(BAGLAN.fihDizin.sERVER);
		sBilgi.setServer( BAGLAN.fihDizin.sERVER);
		sBilgi.setDb("OK_Fih" + BAGLAN.fihDizin.kOD);
		if (BAGLAN.fihDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(sBilgi) == true)   
			{
				if (s_CONN.Dosya_kontrol_L( sBilgi) == false)
				{
					FIH_DOS_VAR = false;
				}
				else
				{
					FIH_DOS_VAR = true;
					aNA_Class.FIHRIST_CONN = true ;
				}
			}
			else
			{
				aNA_Class.FIHRIST_CONN = false;
			}
		}
		else if (s_CONN.Server_kontrol_S(sBilgi) == true )
		{
			if (s_CONN.Dosya_kontrol_S(sBilgi) == false)
			{
				FIH_DOS_VAR = false;
			}
			else
			{
			FIH_DOS_VAR = true;
			aNA_Class.FIHRIST_CONN = true;
			}
		}
		else
		{
			aNA_Class.FIHRIST_CONN = false;
		}
	}
}
