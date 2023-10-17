package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import LOGER_KAYIT.DOSYA_MSSQL;
import LOGER_KAYIT.DOSYA_MYSQL;
import LOGER_KAYIT.SQLITE_LOG;
import LOGER_KAYIT.TXT_LOG;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LOGLAMA_RAPOR extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JSplitPane splitPane ;
	static JTable table;
	static JComboBox<String> comboBox = new JComboBox<String>();
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static DOSYA_MYSQL mYSQL = new DOSYA_MYSQL ();
	static DOSYA_MSSQL mSSQL = new DOSYA_MSSQL ();
	static  SQLITE_LOG sQLITE = new SQLITE_LOG();
	static TXT_LOG tEXT = new TXT_LOG();
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static 	JDateChooser dateChooser ;
	private static JDateChooser dateChooser_1 ;
	private static JLabel lblSatir = new JLabel("0");
	static JComboBox<String> cmbLog = new JComboBox<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGLAMA_RAPOR frame = new LOGLAMA_RAPOR();
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
	@SuppressWarnings("static-access")
	public LOGLAMA_RAPOR() {
		setTitle("LOG RAPORLAMA");
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(0, 0, 1063, 600);
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new LineBorder(new Color(0, 191, 255)));
		leftPanel.setMinimumSize(new Dimension(0, 50));
		leftPanel.setMaximumSize(new Dimension(0, 50));
		ScrollPaneWin11 centerPanel = new ScrollPaneWin11();
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new LineBorder(new Color(0, 191, 255)));
		rightPanel.setMinimumSize(new Dimension(0, 20));
		rightPanel.setMaximumSize(new Dimension(0, 20));
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, leftPanel, centerPanel);
		leftPanel.setLayout(null);
		JSplitPane sp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp, rightPanel);
		rightPanel.setLayout(null);
		
		lblSatir = new JLabel("0");
		lblSatir.setBounds(97, 3, 47, 14);
		lblSatir.setForeground(new Color(0, 0, 128));
		lblSatir.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSatir.setHorizontalAlignment(SwingConstants.RIGHT);
		rightPanel.add(lblSatir);
		
		
		JLabel lblNewLabel_1 = new JLabel("Satir Sayisi :");
		lblNewLabel_1.setBounds(10, 3, 76, 14);
		rightPanel.add(lblNewLabel_1);
		sp2.setDividerSize(1);
		sp2.setResizeWeight(1.0);
		getContentPane().add(sp2, BorderLayout.CENTER);

		comboBox = new JComboBox<String>();
		comboBox.setForeground(new Color(0, 0, 139));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Cari Hesap", "Fatura", "Kambiyo", "Adres", "Kur", "Sms-Mail", "Gunluk","Kereste"}));
		comboBox.setBounds(10, 11, 120, 22);
		leftPanel.add(comboBox);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(270, 12, 130, 20);dateChooser.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dateChooser.setDate(new Date());
				}
			}
		});
		dateChooser.setDateFormatString("dd.MM.yyyy");
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 14));
		dateChooser.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		leftPanel.add(dateChooser);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(405, 12, 130, 20);
		dateChooser_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dateChooser_1.setDate(new Date());
				}
			}
		});
		dateChooser_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		dateChooser_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		leftPanel.add(dateChooser_1);

		textField = new JTextField();
		textField.setBounds(541, 12, 237, 20);
		leftPanel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(820, 12, 58, 20);
		leftPanel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("Evrak");
		lblNewLabel.setBounds(785, 15, 37, 14);
		leftPanel.add(lblNewLabel);

		textField_2 = new JTextField();
		textField_2.setText("");
		textField_2.setBounds(940, 12, 63, 20);
		leftPanel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblKullanici = new JLabel("Kullanici");
		lblKullanici.setBounds(885, 15, 52, 14);
		leftPanel.add(lblKullanici);
		
		cmbLog = new JComboBox<String>();
		cmbLog.setModel(new DefaultComboBoxModel<String>(new String[] {"Veritabani", "Dosya", "Text Dosya"}));
		cmbLog.setForeground(new Color(165, 42, 42));
		cmbLog.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbLog.setBounds(140, 11, 120, 22);
		leftPanel.add(cmbLog);
		table = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.getTableHeader().setReorderingAllowed(false);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		//	table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		centerPanel.setViewportView(table);
	}
	public static void hisset()
	{
		try 
		{
			long startTime = System.currentTimeMillis(); 
			ResultSet	rs = null;
			boolean txtmi = false;
			/////////////CARI///////////////////////////////////////////////
			if (  comboBox.getSelectedItem().toString().equals("Cari Hesap"))
			{
				if(cmbLog.getSelectedItem().toString().equals("Veritabani"))
				{
					if(BAGLAN.cariDizin.hAN_SQL.equals("MS SQL"))
					{
						rs  = 	mSSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.cariLogDizin);
					}
					else 
						if(BAGLAN.cariDizin.hAN_SQL.equals("MY SQL"))
						{
							rs  = 	mYSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
									"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
									BAGLAN_LOG.cariLogDizin);
						}
				}
				else if(cmbLog.getSelectedItem().toString().equals("Dosya")) //SQLITE Dosyasi
				{
					rs  = 	sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.cariLogDizin);
				}
				else if(cmbLog.getSelectedItem().toString().equals("Text Dosya")) //Text  Dosyasi
				{
					DefaultTableModel tbm   = 	tEXT.log_txt_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.cariLogDizin);
					table.setModel(tbm);
					txtmi = true ;
				}
			}
			/////////////STOK///////////////////////////////////////////////
			else if (  comboBox.getSelectedItem().toString().equals("Fatura"))
			{
				if(cmbLog.getSelectedItem().toString().equals("Veritabani"))
				{
					if(BAGLAN.fatDizin.hAN_SQL.equals("MS SQL"))
					{
						rs  = 	mSSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.fatLogDizin);
					}
					else if(BAGLAN.fatDizin.hAN_SQL.equals("MY SQL"))
					{
						rs  = 	mYSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.fatLogDizin);
					}
				}
				else if(cmbLog.getSelectedItem().toString().equals("Dosya"))//sQLITE Dosyasi
				{
					rs  = sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.fatLogDizin);
				}
				else if(cmbLog.getSelectedItem().toString().equals("Text Dosya")) //Text  Dosyasi
				{
					DefaultTableModel tbm   = 	tEXT.log_txt_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.fatLogDizin);
					table.setModel(tbm);
					txtmi = true ;
				}
			}
			/////////////KAMBIYO///////////////////////////////////////////////
			else if (  comboBox.getSelectedItem().toString().equals("Kambiyo"))
			{
				if(cmbLog.getSelectedItem().toString().equals("Veritabani"))
				{
					if(BAGLAN.kamDizin.hAN_SQL.equals("MS SQL"))
					{
						rs  = 	mSSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.kamLogDizin);
					}
					else if(BAGLAN.kamDizin.hAN_SQL.equals("MY SQL"))
					{
						rs  = 	mYSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.kamLogDizin);
					}
				}
				else if(cmbLog.getSelectedItem().toString().equals("Dosya"))// sQLITE Dosyasi
				{
					rs  = sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.kamLogDizin);
				}
				else if(cmbLog.getSelectedItem().toString().equals("Text Dosya")) //Text  Dosyasi
				{
					DefaultTableModel tbm   = 	tEXT.log_txt_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.kamLogDizin);
					table.setModel(tbm);
					txtmi = true ;
				}
			}
			/////////////ADRES///////////////////////////////////////////////
			else if (  comboBox.getSelectedItem().toString().equals("Adres"))
			{
				if(cmbLog.getSelectedItem().toString().equals("Veritabani"))
				{
					if(BAGLAN.adrDizin.hAN_SQL.equals("MS SQL"))
					{
						rs  = 	mSSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.adrLogDizin);
					}
					else if(BAGLAN.adrDizin.hAN_SQL.equals("MY SQL"))
					{
						rs  = 	mYSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.adrLogDizin);
					}
				}
				else if(cmbLog.getSelectedItem().toString().equals("Dosya"))// sQLITE Dosyas
				{
					rs  = 	sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.adrLogDizin);
				}
				else if(cmbLog.getSelectedItem().toString().equals("Text Dosya")) //Text  Dosyasi
				{
					DefaultTableModel tbm   = 	tEXT.log_txt_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.adrLogDizin);
					table.setModel(tbm);
					txtmi = true ;
				}
			}
			/////////////KUR///////////////////////////////////////////////
			else if (  comboBox.getSelectedItem().toString().equals("Kur"))
			{
				if(cmbLog.getSelectedItem().toString().equals("Veritabani"))
				{
					if(BAGLAN.kurDizin.hAN_SQL.equals("MS SQL"))
					{
						rs  = 	mSSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.kurLogDizin);
					}
					else if(BAGLAN.kurDizin.hAN_SQL.equals("MY SQL"))
					{
						rs  = 	mYSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.kurLogDizin);
					}
				}
				else if(cmbLog.getSelectedItem().toString().equals("Dosya"))// sQLITE Dosyas
				{
					rs  = 	sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.kurLogDizin);
				}
				else if(cmbLog.getSelectedItem().toString().equals("Text Dosya")) //Text  Dosyasi
				{
					DefaultTableModel tbm   = 	tEXT.log_txt_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.kurLogDizin);
					table.setModel(tbm);
					txtmi = true ;
				}
			}
			/////////////SMS MAIL///////////////////////////////////////////////
			else if (  comboBox.getSelectedItem().toString().equals("Sms-Mail"))
			{
				if(cmbLog.getSelectedItem().toString().equals("Veritabani"))
				{
					if(BAGLAN.smsDizin.hAN_SQL.equals("MS SQL"))
					{
						rs  = 	mSSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.smsLogDizin);
					}
					else if(BAGLAN.smsDizin.hAN_SQL.equals("MY SQL"))
					{
						rs  = 	mYSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.smsLogDizin);
					}
				}
				else if(cmbLog.getSelectedItem().toString().equals("Dosya"))// sQLITE Dosyas
				{
					rs  = 	sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.smsLogDizin);
				}
				else if(cmbLog.getSelectedItem().toString().equals("Text Dosya")) //Text  Dosyasi
				{
					DefaultTableModel tbm   = 	tEXT.log_txt_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.smsLogDizin);
					table.setModel(tbm);
					txtmi = true ;
				}
			}
			/////////////GUNLUK///////////////////////////////////////////////
			else if (  comboBox.getSelectedItem().toString().equals("Gunluk"))
			{
				if(cmbLog.getSelectedItem().toString().equals("Veritabani"))
				{
					if(BAGLAN.gunDizin.hAN_SQL.equals("MS SQL"))
					{
						rs  = 	mSSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.gunLogDizin);
					}
					else if(BAGLAN.gunDizin.hAN_SQL.equals("MY SQL"))
					{
						rs  = 	mYSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.gunLogDizin);
					}
				}
				else if(cmbLog.getSelectedItem().toString().equals("Dosya"))// sQLITE Dosyas
				{
					rs  = 	sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.gunLogDizin);
				}
				else if(cmbLog.getSelectedItem().toString().equals("Text Dosya")) //Text  Dosyasi
				{
					DefaultTableModel tbm   = 	tEXT.log_txt_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.gunLogDizin);
					table.setModel(tbm);
					txtmi = true ;
				}
			}
			/////////////KERESTE///////////////////////////////////////////////
			else if (  comboBox.getSelectedItem().toString().equals("Kereste"))
			{
				if(cmbLog.getSelectedItem().toString().equals("Veritabani"))
				{
					if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
					{
						rs  = 	mSSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.kerLogDizin);
					}
					else if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
					{
						rs  = 	mYSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								BAGLAN_LOG.kerLogDizin);
					}
				}
				else if(cmbLog.getSelectedItem().toString().equals("Dosya"))// sQLITE Dosyas
				{
					rs  = 	sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.kerLogDizin);
				}
				else if(cmbLog.getSelectedItem().toString().equals("Text Dosya")) //Text  Dosyasi
				{
					DefaultTableModel tbm   = 	tEXT.log_txt_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.kerLogDizin);
					table.setModel(tbm);
					txtmi = true ;
				}
			}
			
			if(txtmi == false )
			{
				if (!rs.isBeforeFirst() ) 
				{  
					GRID_TEMIZLE.grid_temizle(table);
					lblSatir.setText("0");
					OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + 0 + " saniye");
					txtmi = false;
					return;
				}
			} 
			if(txtmi == true)
			{

			}
			else
			{
				GRID_TEMIZLE.grid_temizle(table);
				table.setModel(DbUtils.resultSetToTableModel(rs));
			}
			txtmi = false ;
			lblSatir.setText(String.format("%,d %n" ,  table.getRowCount()));
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(140);
			tc.setMaxWidth(140);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(100);
			tc.setMaxWidth(100);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(100);
			tc.setMaxWidth(100);


			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			table.setRowHeight(21);
			if(table.getRowCount() > 0)
			{
				table.setRowSelectionInterval(0, 0);
				int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				table.setRowSelectionInterval(lastRow, lastRow);
			}
			//table.setSelectionBackground(Color.PINK);
			//table.setSelectionForeground(Color.BLUE);
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			String deger;
			String[] parts;
			Font bigFont;
			try {
				deger = GLOBAL.setting_oku("CARI_ARAMA").toString();
				deger = deger.substring(1, deger.length()-1);
				parts = deger.split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				table.setFont(bigFont);
			} catch (IOException e) {

				e.printStackTrace();
			} 
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Loglama", JOptionPane.ERROR_MESSAGE);
		}
	}
}
