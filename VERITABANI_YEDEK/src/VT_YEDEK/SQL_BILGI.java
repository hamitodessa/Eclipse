package VT_YEDEK;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;


import OBS_C_2025.CONNECT;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.OBS_ORTAK_MSSQL;
import OBS_C_2025.OBS_ORTAK_MYSQL;
import OBS_C_2025.SQL_BACKUP;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


@SuppressWarnings({ "static-access", "serial" })
public class SQL_BILGI extends JDialog {
	VT_ANA_CLASS oac = new VT_ANA_CLASS();
	SQL_BACKUP bck = new SQL_BACKUP();
	private JPanel contentPane;
	private JTextField txtMSUSER;
	private JPasswordField txtMSPWD;
	private JTextField txtMYUSER;
	private JPasswordField txtMYPWD;
	private JTextField txtPort;
	JTabbedPane tabbedPane ;
	JComboBox<String> comboBox;
	private JTextField txtDUMP;
	private JTextField txtPORT;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SQL_BILGI dialog = new SQL_BILGI();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SQL_BILGI() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//				
				//				try {
				//					EMIR frame = new EMIR();
				//					frame.setVisible(true);
				//				} catch (Exception e1) {
				//					// TODO Auto-generated catch block
				//					e1.printStackTrace();
				//				}
			}
			@Override
			public void windowOpened(WindowEvent e) {

				contentPane.setCursor(oac.WAIT_CURSOR);
				if(EMIR. cmbSQL.getSelectedItem().toString() == "MS SQL")
				{
					tabbedPane.setSelectedIndex(0);
					//tabbedPane.setEnabledAt(1, false);
				}
				else
				{
					tabbedPane.setSelectedIndex(1);
					//tabbedPane.setEnabledAt(0, false);

				}
				//grid_doldur();
				//doldur_kutu(tblCari,0);
				contentPane.setCursor(oac.DEFAULT_CURSOR);

			}
		});
		setTitle("SQL SERVER BAGLANTI");
		setResizable(false);
		setBounds(100, 100, 534, 342);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);


		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		tabbedPane.addTab("MS SQL", null, panel, null);

		comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		comboBox.setBounds(72, 25, 164, 22);
		panel.add(comboBox);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Baglanti Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(72, 58, 372, 171);
		panel_2.setLayout(null);
		panel.add(panel_2);

		JLabel lblNewLabel = new JLabel("Kullanici");
		lblNewLabel.setBounds(10, 50, 74, 14);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sifre");
		lblNewLabel_1.setBounds(10, 82, 46, 14);
		panel_2.add(lblNewLabel_1);

		txtMSUSER = new JTextField();
		txtMSUSER.setBounds(98, 47, 130, 20);
		panel_2.add(txtMSUSER);
		txtMSUSER.setColumns(10);

		txtMSPWD = new JPasswordField();
		txtMSPWD.setEchoChar('*');
		txtMSPWD.setBounds(98, 79, 130, 20);
		panel_2.add(txtMSPWD);

		JButton btnNewButton = new JButton("Baglanti Test");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(oac.WAIT_CURSOR);
				try {
					msSQL_BAGLAN();
				} catch (HeadlessException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				contentPane.setCursor(oac.DEFAULT_CURSOR);
			}
		});
		btnNewButton.setBounds(98, 117, 130, 23);
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Kaydet");
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(oac.WAIT_CURSOR);
				try {
					if (comboBox.getSelectedItem() == null || txtMSUSER.getText().equals("") || oac.sDONDUR.sDONDUR(txtMSPWD).equals("") )
					{
						JOptionPane.showMessageDialog(null, "Bos Bilgi Mevcut........", "Server Baglanti", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						msSQL_KAYDET();
						EMIR.serBILGILERI();
						dispose();
					}

				} catch (HeadlessException | InvalidKeyException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | SQLException e1) {
					e1.printStackTrace();
				}	
				contentPane.setCursor(oac.DEFAULT_CURSOR);
			}
		});
		btnNewButton_1.setBounds(228, 240, 89, 23);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Vazgec");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(355, 240, 89, 23);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("Port");
		lblNewLabel_3.setBounds(280, 29, 58, 14);
		panel.add(lblNewLabel_3);
		
		txtPORT = new JTextField();
		txtPORT.setBounds(348, 26, 96, 20);
		panel.add(txtPORT);
		txtPORT.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("MY SQL", null, panel_1, null);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(null, "Baglanti Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2_1.setBounds(20, 42, 424, 114);
		panel_1.add(panel_2_1);

		JLabel lblNewLabel_2 = new JLabel("Kullanici");
		lblNewLabel_2.setBounds(10, 33, 74, 14);
		panel_2_1.add(lblNewLabel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Sifre");
		lblNewLabel_1_1.setBounds(10, 60, 46, 14);
		panel_2_1.add(lblNewLabel_1_1);

		txtMYUSER = new JTextField();
		txtMYUSER.setColumns(10);
		txtMYUSER.setBounds(98, 30, 130, 20);
		panel_2_1.add(txtMYUSER);

		txtMYPWD = new JPasswordField();
		txtMYPWD.setEchoChar('*');
		txtMYPWD.setBounds(98, 57, 130, 20);
		panel_2_1.add(txtMYPWD);

		JButton btnNewButton_3 = new JButton("Baglanti Test");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(oac.WAIT_CURSOR);
				try {
					mySQL_BAGLAN();
				} catch (HeadlessException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				contentPane.setCursor(oac.DEFAULT_CURSOR);
			}
		});
		btnNewButton_3.setBounds(98, 83, 130, 23);
		panel_2_1.add(btnNewButton_3);

		JButton btnNewButton_1_1 = new JButton("Kaydet");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(oac.WAIT_CURSOR);
				try {
					if (txtPort.getText().equals("") || txtMYUSER.getText().equals("") || oac.sDONDUR.sDONDUR(txtMYPWD).equals("") )
					{
						JOptionPane.showMessageDialog(null, "Bos Bilgi Mevcut........", "Server Baglanti", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						mySQL_KAYDET();
						EMIR.serBILGILERI();
						dispose();
						dispose();
					}

				} catch (HeadlessException | InvalidKeyException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				contentPane.setCursor(oac.DEFAULT_CURSOR);
			}
		});
		btnNewButton_1_1.setBounds(228, 240, 89, 23);
		panel_1.add(btnNewButton_1_1);

		JButton btnNewButton_2_1 = new JButton("Vazgec");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2_1.setBounds(355, 240, 89, 23);
		panel_1.add(btnNewButton_2_1);

		txtPort = new JTextField();
		txtPort.setBounds(170, 11, 130, 20);
		panel_1.add(txtPort);
		txtPort.setColumns(10);

		JLabel lblServer = new JLabel("Server / Port");
		lblServer.setBounds(72, 14, 78, 14);
		panel_1.add(lblServer);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "MySql Dump ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(20, 156, 477, 80);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		txtDUMP = new JTextField();
		txtDUMP.setBounds(13, 22, 454, 20);
		panel_3.add(txtDUMP);
		txtDUMP.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Surucu");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Surucu Seciniz");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Surucu Sec");
				chooser.setApproveButtonToolTipText("Surucu Sec");
				chooser.setApproveButtonMnemonic('s');
				contentPane.setCursor(oac.DEFAULT_CURSOR);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					txtDUMP.setText(chooser.getSelectedFile().toString());
				}
				else {
				}
			}
		});
		btnNewButton_4.setBounds(13, 45, 78, 23);
		panel_3.add(btnNewButton_4);
		dOLDUR();
	}
	private void dOLDUR()
	{
		if (VT_ANA_CLASS.EMIR_ADI.equals("")) return ;
		try
		
		{
			ResultSet rss = bck.serBILGI(VT_ANA_CLASS.EMIR_ADI);
			if (!rss.isBeforeFirst() )
			{  
			temIZLE();
			}
			else
			{
				rss.next() ;
				if (rss.getString("HANGI_SQL").toString().equals("MS SQL"))
				{
					comboBox.setSelectedItem(rss.getString("INSTANCE").toString()) ;
					txtMSUSER.setText( rss.getString("KULLANICI").toString()) ;
					txtMSPWD.setText(ENCRYPT_DECRYPT_STRING.dCRYPT_manual(rss.getBytes("SIFRE"))) ;
					txtPORT.setText(rss.getString("PORT"));
				}
				else
				{
					txtPort.setText(rss.getString("INSTANCE").toString()) ;
					txtMYUSER.setText( rss.getString("KULLANICI").toString()) ;
					txtMYPWD.setText(ENCRYPT_DECRYPT_STRING.dCRYPT_manual(rss.getBytes("SIFRE"))) ;
				}
			
			}
		} 
	catch (Exception e1)
	{

		e1.printStackTrace();
	}	
	}
	private void temIZLE()
	{
		comboBox.setSelectedItem("") ;
		txtMSUSER.setText("") ;
		txtMSPWD.setText("") ;
		txtPort.setText("") ; 
		txtMYUSER.setText("") ; 
		txtMYPWD.setText("") ;
		txtPORT.setText("");
	}
	private void msSQL_KAYDET() throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		if(oac.EMIR_ADI.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Emir Adi Bos Olamaz........", "Server Baglanti", JOptionPane.ERROR_MESSAGE);
			return ;
		}
		bck.server_kayit_sil(oac.EMIR_ADI);
		bck.server_ismi_kayit(oac.EMIR_ADI,"MS SQL" ,comboBox.getSelectedItem().toString(), false, true, txtMSUSER.getText(), oac.sDONDUR.sDONDUR(txtMSPWD),txtPORT.getText());
	}
	private void mySQL_KAYDET() throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		if(oac.EMIR_ADI.equals("") || txtDUMP.getText().equals("") )
		{
			JOptionPane.showMessageDialog(null, "Emir Adi veya MySQL Dump yeribos olamaz Bos Olamaz........", "Server Baglanti", JOptionPane.ERROR_MESSAGE);
			return ;
		}
		bck.server_kayit_sil(oac.EMIR_ADI);
		bck.dump_sil(txtDUMP.getText());
		bck.server_ismi_kayit(oac.EMIR_ADI,"MY SQL" ,txtPort.getText(), false, true, txtMYUSER.getText(), oac.sDONDUR.sDONDUR(txtMYPWD),"");
		bck.dump_kayit(oac.EMIR_ADI,txtDUMP.getText());
	}
	private void msSQL_BAGLAN() throws HeadlessException, ClassNotFoundException
	{
		cONN_AKTAR();
		CONNECT s_CONN = new CONNECT(oac._IConn);
		int hangi = tabbedPane.getSelectedIndex();
		if (hangi == 0) // MSSQL
		{
			if (comboBox.getSelectedItem() == null || txtMSUSER.getText().equals("") || oac.sDONDUR.sDONDUR(txtMSPWD).equals("") )
			{			}
			else
			{
				if(	s_CONN.Server_kontrol_L(comboBox.getSelectedItem().toString(),txtMSUSER.getText(), oac.sDONDUR.sDONDUR(txtMSPWD) ,"") == true)
				{ // BAGLANDI
					JOptionPane.showMessageDialog(null, "MsSQL Baglanti Saglandi........", "Server Baglanti", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{// BAGLANMADI
					JOptionPane.showMessageDialog(null, "MsSQL Baglanti Saglanmadi........", "Server Baglanti", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	private void mySQL_BAGLAN() throws HeadlessException, ClassNotFoundException
	{
		cONN_AKTAR();
		CONNECT s_CONN = new CONNECT(oac._IConn);
		int hangi = tabbedPane.getSelectedIndex();
		if (hangi ==1) // MSSQL
		{
			if (txtPort.getText().equals("") || txtMYUSER.getText().equals("") || oac.sDONDUR.sDONDUR(txtMYPWD).equals("") )
			{	}
			else
			{
				if(	s_CONN.Server_kontrol_L("",txtMYUSER.getText(), oac.sDONDUR.sDONDUR(txtMYPWD) ,txtPort.getText()) == true)
				{ // BAGLANDI
					JOptionPane.showMessageDialog(null, "MySQL Baglanti Saglandi........", "Server Baglanti", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{// BAGLANMADI
					JOptionPane.showMessageDialog(null, "MySQL Baglanti Saglanmadi........", "Server Baglanti", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	private void cONN_AKTAR()
	{
		int hangi = tabbedPane.getSelectedIndex();
		if (hangi == 0)
		{
			oac._IConn = new OBS_ORTAK_MSSQL();
		}
		else if (hangi == 1)
		{
			oac._IConn = new OBS_ORTAK_MYSQL();
		}
	}
}
