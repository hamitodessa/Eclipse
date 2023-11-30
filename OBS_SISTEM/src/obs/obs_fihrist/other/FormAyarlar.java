package obs.obs_fihrist.other;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.CONNECT;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.OBS_ORTAK_MSSQL;
import OBS_C_2025.OBS_ORTAK_MYSQL;
import OBS_C_2025.OBS_ORTAK_SQLITE;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.Server_Bilgi;
import OBS_C_2025.USER_ISLEMLERI;
import fih.FIHRIST_ACCESS;
import fih.FIHRIST_MSSQL;
import fih.FIHRIST_MYSQL;
import fih.FIHRIST_SQLITE;
import net.proteanit.sql.DbUtils;
import obs.classes.aNA_Class;
import obs.obs_fihrist.OBS_FIHRIST;

import raven.toast.Notifications;

import java.awt.Color;
import java.awt.Cursor;

@SuppressWarnings({"static-access","unused","serial"})
public class FormAyarlar extends javax.swing.JPanel{

	boolean FIH_DOS_VAR;
	BAGLAN bAGLAN = new BAGLAN();
	aNA_Class oac = new aNA_Class();
	FIHRIST_ACCESS  fih_Access ;
	
	private static JCheckBox chckbxS ;
	private static JCheckBox chckbxL ;
	private static JComboBox<String> cmbInstance ;
	private static JComboBox<String> cmbip ;
	private static JComboBox<String> cmbhangisql ;

	private JButton btnServer ;
	private JButton btnVtKontrol ;
	
	private static JTextField txtIp;
	private static JTextField txtUser;
	private static JTextField txtcdid;
	private static JPasswordField txtPwd;
	private static JTextField txtKodu;
	private JTable table_1;
	 
	 public FormAyarlar() {
	 	setLayout(new BorderLayout(0, 0));
	 	
	 	add(new Title_Bar(), BorderLayout.NORTH);
	 	JSplitPane splitPane = new JSplitPane();
	 	add(splitPane, BorderLayout.CENTER);
		 	
	 	ScrollPaneWin11 scrollPane_3 = new ScrollPaneWin11();
		scrollPane_3.setMinimumSize(new Dimension(300, 0));
		scrollPane_3.setMaximumSize(new Dimension(300, 0));


		splitPane.setLeftComponent(scrollPane_3);

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(290,580));
		scrollPane_3.setViewportView(panel_3);
		panel_3.setLayout(null);

		cmbhangisql = new JComboBox<String>();
		cmbhangisql.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String hangi = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex())  ;
				if (hangi == "MS SQL")
				{
					chckbxS.setEnabled(true);
					chckbxL.setEnabled(true);
					cmbInstance.setEnabled(true);
					cmbip.setEnabled(true);
					txtIp.setEnabled(true);
					txtUser.setEnabled(true);
					txtPwd.setEnabled(true);
					btnServer.setEnabled(true);
				}
				else if (hangi == "MY SQL")
				{
					chckbxS.setEnabled(true);
					chckbxL.setEnabled(true);
					cmbInstance.setEnabled(false);
					cmbip.setEnabled(true);
					txtIp.setEnabled(true);
					txtUser.setEnabled(true);
					txtPwd.setEnabled(true);
					btnServer.setEnabled(true);
				}
				else if (hangi == "SQ LITE")
				{
					chckbxS.setEnabled(false);
					chckbxL.setEnabled(true);
					cmbInstance.setEnabled(false);
					cmbip.setEnabled(false);
					txtIp.setEnabled(false);
					txtUser.setEnabled(false);
					txtPwd.setEnabled(false);
					btnServer.setEnabled(false);
					btnVtKontrol.setEnabled(true);
				}
			}
		});
		cmbhangisql.setModel(new DefaultComboBoxModel<String>(new String[] {"MS SQL", "MY SQL", "SQ LITE"}));
		cmbhangisql.setForeground(new Color(0, 0, 139));
		cmbhangisql.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbhangisql.setBounds(88, 55, 157, 22);
		panel_3.add(cmbhangisql);

		JLabel lblNewLabel_1_1 = new JLabel("Kodu");
		lblNewLabel_1_1.setBounds(10, 88, 68, 14);
		panel_3.add(lblNewLabel_1_1);

		txtKodu = new JTextField();
		txtKodu.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtKodu.setColumns(10);
		txtKodu.setBounds(88, 83, 78, 20);
		panel_3.add(txtKodu);

		chckbxL = new JCheckBox("Lokal");
		chckbxL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxL.isSelected())
				{
					chckbxS.setSelected(false);
				}
				else
				{
					chckbxS.setSelected(true);
				}
			}
		});
		chckbxL.setSelected(true);
		chckbxL.setBounds(88, 107, 65, 23);
		panel_3.add(chckbxL);

		chckbxS = new JCheckBox("Server");
		chckbxS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxS.isSelected())
				{
					chckbxL.setSelected(false);
				}
				else
				{
					chckbxL.setSelected(true);
				}
			}
		});
		chckbxS.setSelected(false);
		chckbxS.setBounds(167, 107, 78, 23);
		panel_3.add(chckbxS);

		JLabel lblInstance = new JLabel("Instance");
		lblInstance.setBounds(10, 160, 68, 14);
		panel_3.add(lblInstance);

		cmbInstance = new JComboBox<String>();
		cmbInstance.setEditable(true);
		cmbInstance.setBounds(88, 155, 157, 22);
		panel_3.add(cmbInstance);

		JLabel lblServer = new JLabel("Server / Port");
		lblServer.setBounds(10, 185, 78, 14);
		panel_3.add(lblServer);

		txtIp = new JTextField();
		txtIp.setText("");
		txtIp.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtIp.setColumns(10);
		txtIp.setBounds(88, 180, 157, 20);
		panel_3.add(txtIp);

		JLabel lblKayitserver = new JLabel("Serverler");
		lblKayitserver.setBounds(10, 210, 78, 14);
		panel_3.add(lblKayitserver);

		cmbip = new JComboBox<String>();
		cmbip.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbip.setEditable(true);
		cmbip.setBounds(88, 205, 157, 22);
		panel_3.add(cmbip);

		JLabel lblSifre = new JLabel("Kullanici");
		lblSifre.setBounds(10, 240, 68, 14);
		panel_3.add(lblSifre);

		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtUser.setColumns(10);
		txtUser.setBounds(88, 235, 157, 20);
		panel_3.add(txtUser);

		JLabel lblKullanici = new JLabel("Sifre");
		lblKullanici.setBounds(10, 263, 68, 14);
		panel_3.add(lblKullanici);

		txtPwd = new JPasswordField();
		txtPwd.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPwd.setBounds(88, 258, 157, 20);
		panel_3.add(txtPwd);

		txtcdid = new JTextField();
		txtcdid.setBounds(10, 108, 32, 20);
		txtcdid.setText("");
		txtcdid.setVisible(false);
		panel_3.add(txtcdid);
	       
		/////////////////////
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(88, 17, 138, 27);

		//////////////////////
		btnServer = new JButton("");
		btnServer.setToolTipText("Server Kontrol");
		btnServer.setIcon(new ImageIcon(OBS_FIHRIST.class.getResource("/obs/icon/png/server.png")));
		btnServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					if(txtKodu.getText().toString().equals(""))
						return;
					if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()).toString().equals("MS SQL"))
					{
						if (cmbInstance.getSelectedItem().toString() == null)
							return;
					}
					try {
						setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						server_control();
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					} catch  (Exception ex)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage().toString() );
					}
				}
			}
		});

		toolBar.add(btnServer);

		btnVtKontrol = new JButton("");
		btnVtKontrol.setToolTipText("Veritabani Kontrol");
		btnVtKontrol.setEnabled(false);
		btnVtKontrol.setIcon(new ImageIcon(OBS_FIHRIST.class.getResource("/obs/icon/png/db.png")));
		btnVtKontrol.addActionListener(new ActionListener() {  // VERITABANI KONTROL
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(txtKodu.getText().toString().equals(""))
						return;
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					database_kontrol();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					String	qwe = BAGLAN.fihDizin.yER.equals("S") ?  BAGLAN.fihDizin.sERVER : "Lokal" ;
					//lblbilgi.setText (BAGLAN.fihDizin.kOD + "  /  " + qwe  + " / "+ BAGLAN.fihDizin.hAN_SQL );
				}
				catch  (Exception ex)
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage().toString() );
				}
			}
		});

		toolBar.add(btnVtKontrol);
		JButton btnSil = new JButton("");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtcdid.getText().toString().equals(""))
					return ;
				int g = JOptionPane.showOptionDialog(null, "Kayit Silinecek..........?" ,
						"Calisma Dizini ", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, 	new String[] {"Yes", "No"}, "No");
				if(g ==  1) {
					return;
				}
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					oac.uSER_ISL.cd_sil(Integer.parseInt(txtcdid.getText()) );
					ayar_doldur();
					//lblbilgi.setText ("");
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception ex)
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					OBS_FIHRIST.mesaj_goster(1000,Notifications.Type.ERROR,  ex.getMessage().toString() );
				}
			}
		});
		btnSil.setToolTipText("Sil");
		btnSil.setIcon(new ImageIcon(OBS_FIHRIST.class.getResource("/obs/icon/png/sil.png")));
		toolBar.add(btnSil);

		JButton btnYeni = new JButton("");
		btnYeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kutu_temizle();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnYeni.setToolTipText("Yeni");
		btnYeni.setIcon(new ImageIcon(OBS_FIHRIST.class.getResource("/obs/icon/png/yeni.png")));
		toolBar.add(btnYeni);

		///////////////////////
		panel_3.add(toolBar);

		///////AYAR TABLO***********************
		ScrollPaneWin11 scrollPane_4 = new ScrollPaneWin11();
		splitPane.setRightComponent(scrollPane_4);

		table_1 = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					if (table_1.getRowCount() == 0) return ;
					if (table_1.getSelectedRow()  < 0) return;
					try {
						kutu_temizle();
						doldur_kutu(table_1,table_1.getSelectedRow());
					} catch (Exception e1) {
						OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
					}
				}
			}
		});
		table_1.setShowHorizontalLines(true);
		table_1.setShowVerticalLines(true);
		table_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPane_4.setViewportView(table_1);
		ayar_doldur();
	    }
		void ayar_doldur() {
			try {
				GRID_TEMIZLE.grid_temizle(table_1);
				ResultSet	rs = null;
				rs = oac.uSER_ISL.user_db_izinleri("Admin", "Fihrist");
				if (!rs.isBeforeFirst() ) {  
					return;
				} 
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
				table_1.removeColumn(table_1.getColumnModel().getColumn(0));
				table_1.removeColumn(table_1.getColumnModel().getColumn(1));
				table_1.removeColumn(table_1.getColumnModel().getColumn(1));
				table_1.removeColumn(table_1.getColumnModel().getColumn(1));
				table_1.removeColumn(table_1.getColumnModel().getColumn(1));
				table_1.removeColumn(table_1.getColumnModel().getColumn(3));
				table_1.removeColumn(table_1.getColumnModel().getColumn(4));
				table_1.removeColumn(table_1.getColumnModel().getColumn(4));
				table_1.removeColumn(table_1.getColumnModel().getColumn(4));
				table_1.removeColumn(table_1.getColumnModel().getColumn(5));
				table_1.removeColumn(table_1.getColumnModel().getColumn(5));
				JTableHeader th = table_1.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc = tcm.getColumn(0);
				tc.setHeaderValue( "Kodu" );
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(70);
				tc.setMaxWidth(70);

				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setHeaderValue( "Ip" );
				tc.setMinWidth(200);
				//tc.setMaxWidth(200);

				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setHeaderValue( "Modul" );

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setHeaderValue( "Yer" );
				tc.setMinWidth(50);
				tc.setMaxWidth(50);

				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setHeaderValue( "SQL" );
				tc.setMinWidth(80);
				tc.setMaxWidth(80);

				th.repaint();
				table_1.setRowHeight(22);
				table_1.setRowSelectionInterval(0, 0);
				doldur_kutu(table_1,0);
			} catch (Exception e) {
				OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.WARNING, e.getMessage());
			}
		}
		private static  void doldur_kutu( JTable grd,int satir) throws ClassNotFoundException, SQLException 
		{
			if (grd.getRowCount()== 0 ) {  
				kutu_temizle();
				return;
			} 
			ip_doldur();
			txtKodu.setText(grd.getModel().getValueAt(satir, 1).toString());
			txtIp.setText(grd.getModel().getValueAt(satir, 6).toString());
			txtUser.setText(grd.getModel().getValueAt(satir, 3).toString());
			String decodedString = grd.getModel().getValueAt(satir, 4).toString();
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) {
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			try {
				txtPwd.setText( ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes));
			} catch (Exception e) {
				e.printStackTrace();
			}
			txtcdid.setText(grd.getModel().getValueAt(satir, 0).toString());
			cmbhangisql.setSelectedItem(grd.getModel().getValueAt(satir, 13).toString());
			cmbInstance.removeAllItems();
			cmbInstance.addItem(grd.getModel().getValueAt(satir, 5).toString());
			if (grd.getModel().getValueAt(satir, 9).equals("L"))
			{
				chckbxL.setSelected(true);
				chckbxS.setSelected(false);
			}
			else
			{
				chckbxS.setSelected(true);
				chckbxL.setSelected(false);
			}
		}
		private static void kutu_temizle() throws ClassNotFoundException, SQLException
		{
			txtKodu.setText("");
			txtIp.setText("");
			txtUser.setText("");
			txtPwd.setText("");
			txtcdid.setText("");
			cmbInstance.removeAllItems();
			chckbxL.setSelected(true);
			chckbxS.setSelected(false);
			cmbhangisql.setSelectedItem("MS SQL");
			ip_doldur();
		}
		private static  void ip_doldur() throws ClassNotFoundException, SQLException
		{
			cmbip.removeAllItems();
			ResultSet	rs = null;
			USER_ISLEMLERI usr = new USER_ISLEMLERI();
			rs = usr.ipp("Admin");
			if (!rs.isBeforeFirst() ) {  
				return;
			} 
			else
			{
				cmbip.addItem("");
				while (rs.next())
				{
					cmbip.addItem(rs.getString("IP"));
				}
			}
		}
		private void server_control() throws HeadlessException, ClassNotFoundException
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			cONN_AKTAR(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));
			CONNECT s_CONN = new CONNECT(oac._IFihristCon);
			if (chckbxL.isSelected() )
			{
				Server_Bilgi sBilgi = new Server_Bilgi() ;
				sBilgi.setIns(cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString());
				sBilgi.setKull(txtUser.getText()); 
				sBilgi.setSifre( oac.sDONDUR.sDONDUR(txtPwd)); 
				sBilgi.setPort(txtIp.getText()); 
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				if ( s_CONN.Server_kontrol_L( sBilgi) == true  )
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					btnVtKontrol.setEnabled(true);
				}
				else
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.WARNING,  "Baglanti Saglanamadi........" );
					btnVtKontrol.setEnabled(false);
				}
			}
			else
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Server_Bilgi sBilgi = new Server_Bilgi() ;
				sBilgi.setServer(txtIp.getText());
				sBilgi.setIns(cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString() );
				sBilgi.setKull(txtUser.getText()); 
				sBilgi.setSifre( oac.sDONDUR.sDONDUR(txtPwd));
				if (s_CONN.Server_kontrol_S(sBilgi ) == true)
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					btnVtKontrol.setEnabled(true);
				}
				else
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.WARNING,  "Baglanti Saglanamadi........" );
					btnVtKontrol.setEnabled(false);
				}
			}
		}
		private void cONN_AKTAR(String hangi)
		{
			if(hangi.equals("MS SQL"))
			{
				oac._IFihristCon = new OBS_ORTAK_MSSQL() ;
			}
			else if(hangi.equals("MY SQL"))
			{
				oac._IFihristCon = new OBS_ORTAK_MYSQL() ;
			}
			else if(hangi.equals("SQ LITE"))
			{
				oac._IFihristCon = new OBS_ORTAK_SQLITE() ;
			}
		}
		private void mODUL_AKTAR(String hangi)
		{
			if(hangi.equals("MS SQL"))
			{
				oac._IFihrist =  new FIHRIST_MSSQL();
			}
			else if(hangi.equals("MY SQL"))
			{
				oac._IFihrist =  new FIHRIST_MYSQL();
			}
			else if(hangi.equals("SQ LITE"))
			{
				oac._IFihrist =  new FIHRIST_SQLITE();
			}
		}
		private  void database_kontrol() throws ClassNotFoundException, HeadlessException, SQLException, IOException
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			cONN_AKTAR(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));	 
			mODUL_AKTAR(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));
			CONNECT s_CONN = new CONNECT(oac._IFihristCon);
			String program = "";
			String modul = "";
			modul = "Fihrist";
			program = "OK_Fih" + txtKodu.getText();
			if(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()).equals("SQ LITE"))
			{
				BAGLAN.fihDizin.cONN_STR = GLOBAL.DBYERI +program  + ".DB" ;   //SQLITE
			}
			if (chckbxL.isSelected())
			{
				lokal_dosya(s_CONN,program,modul);//LOCAL DOSYA KONTROL \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
			}
			else  // Server
			{
				server_dosya(s_CONN,program,modul);//SERVER DOSYA KONTROL \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
			}
		}
		private void lokal_dosya(CONNECT s_CONN,String program,String modul) throws HeadlessException, ClassNotFoundException, SQLException, IOException
		{
			Server_Bilgi sBilgi = new Server_Bilgi() ;
			sBilgi.setDb(program);
			sBilgi.setKull(txtUser.getText());
			sBilgi.setSifre(oac.sDONDUR.sDONDUR(txtPwd));
			sBilgi.setIns(cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString() ); 
			sBilgi.setPort(txtIp.getText());
			if ( s_CONN.Dosya_kontrol_L(sBilgi) == true)
			{
				mdb_yaz();
				ayar_doldur();
				BAGLAN bAGLAN = new BAGLAN();
				bAGLAN.cONNECT("Admin");
				fih_Access = new FIHRIST_ACCESS(oac._IFihrist );
				fih_Access.baglan();
				//doldur();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.INFO,   "Veritabani Baglantisi gerceklestirildi" );
			}
			else
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				int g =  JOptionPane.showOptionDialog( null,  "Yeni Dosya Olusturulsunmu............?", "Dosya Olusturma",   JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,	 	null,    	oac.options,  	 	oac.options[1]); 
				if(g != 0 ) { return;	}
				dosya_olustur_L();
				mdb_yaz();
				ayar_doldur();
				BAGLAN bAGLAN = new BAGLAN();
				bAGLAN.cONNECT("Admin");
				fih_Access = new FIHRIST_ACCESS(oac._IFihrist );
				fih_Access.baglan();
				//doldur();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				OBS_FIHRIST. mesaj_goster(5000,Notifications.Type.INFO,    "Dosya Olusturuldu ..." );
			}
		}
		private  void dosya_olustur_L() throws IOException, ClassNotFoundException, SQLException
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			cONN_AKTAR(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));
			mODUL_AKTAR(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));
			fih_Access = new FIHRIST_ACCESS(oac._IFihrist);
			Server_Bilgi sbilgi = new Server_Bilgi();
			sbilgi.setKod(txtKodu.getText());
			sbilgi.setIns(cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString());
			sbilgi.setKull(txtUser.getText());
			sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtPwd));
			sbilgi.setPort(txtIp.getText()); 
			sbilgi.setDizin_yeri("default");
			sbilgi.setDizin("");
			fih_Access.fihrist_sifirdan_L(sbilgi);
		}
		private void server_dosya(CONNECT s_CONN,String program,String modul) throws HeadlessException, ClassNotFoundException, SQLException, IOException
		{
			Server_Bilgi sBilgi = new Server_Bilgi() ;
			sBilgi.setServer(txtIp.getText());;
			sBilgi.setIns(cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString()); ;
			sBilgi.setKull(txtUser.getText()); ;
			sBilgi.setSifre( oac.sDONDUR.sDONDUR(txtPwd));;
			sBilgi.setDb( program); ;
			sBilgi.setPort(txtIp.getText());
			if ( s_CONN.Dosya_kontrol_S(sBilgi) ==true)
			{
				mdb_yaz();
				ayar_doldur();
				BAGLAN bAGLAN = new BAGLAN();
				bAGLAN.cONNECT("Admin");
				fih_Access = new FIHRIST_ACCESS(oac._IFihrist );
				fih_Access.baglan();
				//FormFihrist.doldur();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.INFO,    "Dosya Baglanti Kuruldu ..." );
			}
			else
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				int g =  JOptionPane.showOptionDialog( null,  "Yeni Dosya Olusturulsunmu............?", "Dosya Olusturma",   JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,null, oac.options, 	oac.options[1]); 
				if(g != 0 ) { return;	}
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					dosya_olustur_S();
					mdb_yaz();
					ayar_doldur();
					BAGLAN bAGLAN = new BAGLAN();
					bAGLAN.cONNECT("Admin");
					fih_Access = new FIHRIST_ACCESS(oac._IFihrist );
					fih_Access.baglan();
					//FormFihrist.doldur();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.INFO,    "Dosya Olusturuldu ..." );
				}
			}
		}
		private void mdb_yaz() throws ClassNotFoundException, SQLException
		{
			if(! txtIp.getText().toString().equals(""))
			{
				oac.uSER_ISL.ip_dos_kont(txtIp.getText());
			}
			oac.uSER_ISL.calisanmi_degis("Admin","Fihrist",chckbxL.isSelected() ? "L" : "S"); // CaLISANMI DOSYA KONTROLU
			oac.uSER_ISL.details_yaz(txtKodu.getText(),"Admin",txtUser.getText(), oac.sDONDUR.sDONDUR(txtPwd),
					cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString() , 
					txtIp.getText(), "Fihrist","", chckbxL.isSelected() ? "L" : "S",  "D" , "E", "E",
					cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()), 
					txtcdid.getText(), 0, "false,false,false,false");
		}
		private void dosya_olustur_S()throws ClassNotFoundException, SQLException
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			cONN_AKTAR(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));
			mODUL_AKTAR( cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));
			fih_Access = new FIHRIST_ACCESS(oac._IFihrist);
			Server_Bilgi sbilgi = new Server_Bilgi();
			sbilgi.setKod(txtKodu.getText());;
			sbilgi.setIns(cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString());;
			sbilgi.setKull(txtUser.getText()); ;
			sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtPwd)); ;
			sbilgi.setServer(txtIp.getText()); ;
			sbilgi.setDizin_yeri("default");;
			sbilgi.setDizin("");;
			fih_Access.fihrist_SIFIR_S(sbilgi);
		}
}
