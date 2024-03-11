package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.FILE_UZANTI;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import com.toedter.calendar.JDateChooser;

import LOGER_KAYIT.DOSYA_MSSQL;
import LOGER_KAYIT.DOSYA_MYSQL;
import LOGER_KAYIT.SQLITE_LOG;
import LOGER_KAYIT.TXT_LOG;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

@SuppressWarnings({ "static-access", "serial" ,"resource"})
public class LOGLAMA_RAPOR extends JInternalFrame {
	/**
	 * 
	 */
	static JSplitPane splitPane ;
	static JTable table;
	static JComboBox<String> comboBox = new JComboBox<String>();
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static DOSYA_MYSQL mYSQL = new DOSYA_MYSQL ();
	static DOSYA_MSSQL mSSQL = new DOSYA_MSSQL ();
	static  SQLITE_LOG sQLITE = new SQLITE_LOG();
	static TXT_LOG tEXT = new TXT_LOG();
	private static Obs_TextFIeld textField;
	private static Obs_TextFIeld textField_1;
	private static Obs_TextFIeld textField_2;
	private static JDateChooser dateChooser ;
	private static JDateChooser dateChooser_1 ;
	private static JLabel lblSatir = new JLabel("0");
	static JComboBox<String> cmbLog = new JComboBox<String>();
	
	private static JSplitPane sp2 ;
	
	
	public LOGLAMA_RAPOR() {
		setTitle("LOG RAPORLAMA");
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(0, 0, 1063, 600);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(LOGLAMA_RAPOR.class.getResource("/ICONLAR/icons8-data-sheet-filled-30.png")), 16, 16));//
		ScrollPaneWin11 scrollPaneust = new ScrollPaneWin11();
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(1033,50));
		scrollPaneust.setViewportView(leftPanel);

		//leftPanel.setBorder(new LineBorder(null));
		leftPanel.setMinimumSize(new Dimension(0, 50));
		leftPanel.setMaximumSize(new Dimension(0, 50));
		ScrollPaneWin11 centerPanel = new ScrollPaneWin11();
		JPanel rightPanel = new JPanel();
		//rightPanel.setBorder(new LineBorder(null));
		rightPanel.setMinimumSize(new Dimension(0, 30));
		rightPanel.setMaximumSize(new Dimension(0, 30));
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPaneust, centerPanel);
		sp.setDividerSize(0);
		leftPanel.setLayout(null);
		sp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp, rightPanel);
		rightPanel.setLayout(null);

		lblSatir = new JLabel("0");
		lblSatir.setBounds(97, 3, 47, 14);
		//lblSatir.setForeground(new Color(0, 0, 128));
		lblSatir.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSatir.setHorizontalAlignment(SwingConstants.RIGHT);
		rightPanel.add(lblSatir);


		JLabel lblNewLabel_1 = new JLabel("Satir Sayisi :");
		lblNewLabel_1.setBounds(10, 3, 76, 14);
		rightPanel.add(lblNewLabel_1);
		sp2.setDividerSize(0);
		sp2.setResizeWeight(1.0);
		getContentPane().add(sp2, BorderLayout.CENTER);

		comboBox = new JComboBox<String>();
		//comboBox.setForeground(new Color(0, 0, 139));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Cari Hesap", "Fatura", "Kambiyo", "Adres", "Kur", "Sms-Mail", "Gunluk","Kereste"}));
		comboBox.setBounds(10, 11, 120, 27);
		leftPanel.add(comboBox);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(270, 11, 130, 27);dateChooser.getComponent(1).addMouseListener(new MouseAdapter() {
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
		dateChooser_1.setBounds(405, 11, 130, 27);
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

		textField = new Obs_TextFIeld();
		textField.setBounds(541, 12, 237, 20);
		leftPanel.add(textField);
		textField.setColumns(10);

		textField_1 = new Obs_TextFIeld();
		textField_1.setBounds(820, 12, 58, 20);
		leftPanel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("Evrak");
		lblNewLabel.setBounds(785, 15, 37, 14);
		leftPanel.add(lblNewLabel);

		textField_2 = new Obs_TextFIeld();
		textField_2.setText("");
		textField_2.setBounds(940, 12, 63, 20);
		leftPanel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblKullanici = new JLabel("Kullanici");
		lblKullanici.setBounds(885, 15, 52, 14);
		leftPanel.add(lblKullanici);

		cmbLog = new JComboBox<String>();
		cmbLog.setModel(new DefaultComboBoxModel<String>(new String[] {"Veritabani", "Dosya", "Text Dosya"}));
		cmbLog.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbLog.setBounds(140, 11, 120, 27);
		leftPanel.add(cmbLog);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.getTableHeader().setReorderingAllowed(false);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
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
			if(txtmi == false)
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
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			String deger;
			String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("CARI_ARAMA").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table.setFont(bigFont);
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
		}
	}
	public static void excell_aktar()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		if (mdl.getRowCount() == 0 )
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, "Aktarilacak Bilgi Yok....." );
		}
		else
		{
			write() ;	
		}
	}
	public static void write()
	{
		try 
		{
			UIManager.put("FileChooser.cancelButtonText", "Vazgec");
			UIManager.put("FileChooser.saveButtonText", "Kaydet");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.resetChoosableFileFilters();
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileFilter xls = new FileNameExtensionFilter("Microsoft Excel 97-2003 Worksheet (.xls)", "xls");
			FileFilter xlxs = new FileNameExtensionFilter("Microsoft Excel Worksheet (.xlsx) ", "xlsx");
			fileChooser.addChoosableFileFilter(xls);
			fileChooser.addChoosableFileFilter(xlxs);
			fileChooser.setCurrentDirectory(new java.io.File("."));
			fileChooser.setApproveButtonText("Kaydet");
			fileChooser.setDialogTitle("Excell Kayit");   
			File outputfile = new File("Loglama");
			fileChooser.setSelectedFile(outputfile);

			
			int returnVal = fileChooser.showSaveDialog(null);
			if ( returnVal != JFileChooser.APPROVE_OPTION )
			{
				return;
			}
			GuiUtil.setWaitCursor(sp2,true);
			String uzanti ="";
			File excelFile =  FILE_UZANTI. getSelectedFileWithExtension(fileChooser);
			uzanti  = excelFile.getName().substring(excelFile.getName().lastIndexOf("."));
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");  
			LocalDateTime now = LocalDateTime.now();  
			String zaman = dtf.format(now)  ;

			//
			if  (uzanti.equals(".xls") )
			{
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Loglama");
				HSSFFont headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setColor(IndexedColors.BLUE.getIndex()); 
				HSSFCellStyle headerStyle = workbook.createCellStyle();
				HSSFCellStyle headerSolaStyle = workbook.createCellStyle();
				headerStyle.setFont(headerFont);
				headerStyle.setAlignment(HorizontalAlignment.RIGHT);

				HSSFFont solaFont = workbook.createFont();
				solaFont.setFontName("Arial Narrow");
				solaFont. setFontHeight((short)(10*20));
				HSSFCellStyle solaStyle = workbook.createCellStyle();
				solaStyle.setFont(solaFont);
				solaStyle.setAlignment(HorizontalAlignment.LEFT);

				HSSFFont headerSolaFont = workbook.createFont();
				headerSolaFont.setBold(true);
				headerSolaFont.setColor(IndexedColors.BLUE.getIndex()); 
				headerSolaStyle.setFont(headerSolaFont);
				headerSolaStyle.setAlignment(HorizontalAlignment.LEFT);

				HSSFCellStyle satirStyle = workbook.createCellStyle();
				
				HSSFFont satirFont = workbook.createFont();
				satirFont.setFontName("Arial Narrow");
				satirFont. setFontHeight((short)(10*20));
				satirStyle.setFont(satirFont);
				satirStyle.setAlignment(HorizontalAlignment.RIGHT);

				
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				HSSFCellStyle acikStyle = workbook.createCellStyle();
				HSSFFont acikFont = workbook.createFont();
				acikFont.setColor(IndexedColors.RED.getIndex()); 
				acikFont.setBold(true);
				acikFont.setFontName("Arial");
				acikFont. setFontHeight((short)(22*20));
				acikStyle.setFont(acikFont);
				acikStyle.setAlignment(HorizontalAlignment.CENTER);

				Row baslikRow = sheet.createRow(0);
				sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -2));
				Cell baslikname = baslikRow.createCell(0);
				baslikname.setCellValue(BAGLAN.cariDizin.fIRMA_ADI );
				baslikname.setCellStyle(acikStyle);

				Cell tarih = baslikRow.createCell(3);
				SimpleDateFormat DateFor = new SimpleDateFormat("dd.MM.yyyy");
				tarih.setCellValue(DateFor.format(new Date() ));
				tarih.setCellStyle(satirStyle);

				Row headerRow = sheet.createRow(1);
				for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
				{
					Cell bname = headerRow.createCell(q);
					bname.setCellValue(mdl.getColumnName(q));
					bname.setCellStyle(headerSolaStyle);
				}
				for (int i =0;i< mdl.getRowCount() ;i++)
				{
					Row satirRow = sheet.createRow(i+2);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						hname.setCellValue( mdl.getValueAt(i,s).toString());
						hname.setCellStyle(solaStyle); 
					}
				}
				for (int i=0; i<= mdl.getColumnCount()-1; i++){
					sheet.autoSizeColumn(i);
				}
				//
				FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile() + "_" + zaman + uzanti));
				workbook.write(out);
				out.close();
			}
			else
			{
				//************************************** XLXS *****************************************************
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("Loglama");
				XSSFFont headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setColor(IndexedColors.BLUE.getIndex()); 
				XSSFCellStyle headerStyle = workbook.createCellStyle();
				XSSFCellStyle headerSolaStyle = workbook.createCellStyle();
				headerStyle.setFont(headerFont);
				headerStyle.setAlignment(HorizontalAlignment.RIGHT);

				XSSFFont solaFont = workbook.createFont();
				solaFont.setFontName("Arial Narrow");
				solaFont. setFontHeight((short)(10*20));
				XSSFCellStyle solaStyle = workbook.createCellStyle();
				solaStyle.setFont(solaFont);
				solaStyle.setAlignment(HorizontalAlignment.LEFT);

				XSSFFont headerSolaFont = workbook.createFont();
				headerSolaFont.setBold(true);
				headerSolaFont.setColor(IndexedColors.BLUE.getIndex()); 
				headerSolaStyle.setFont(headerSolaFont);
				headerSolaStyle.setAlignment(HorizontalAlignment.LEFT);

				XSSFCellStyle satirStyle = workbook.createCellStyle();
				
				XSSFFont satirFont = workbook.createFont();
				satirFont.setFontName("Arial Narrow");
				satirFont. setFontHeight((short)(10*20));
				satirStyle.setFont(satirFont);
				satirStyle.setAlignment(HorizontalAlignment.RIGHT);

				

				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				XSSFCellStyle acikStyle = workbook.createCellStyle();
				XSSFFont acikFont = workbook.createFont();
				acikFont.setColor(IndexedColors.RED.getIndex()); 
				acikFont.setBold(true);
				acikFont.setFontName("Arial");
				acikFont. setFontHeight((short)(22*20));
				acikStyle.setFont(acikFont);
				acikStyle.setAlignment(HorizontalAlignment.CENTER);

				Row baslikRow = sheet.createRow(0);
				sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -2));
				Cell baslikname = baslikRow.createCell(0);

				baslikname.setCellValue(BAGLAN.cariDizin.fIRMA_ADI);
				baslikname.setCellStyle(acikStyle);

				Cell tarih = baslikRow.createCell(3);
				SimpleDateFormat DateFor = new SimpleDateFormat("dd.MM.yyyy");
				tarih.setCellValue(DateFor.format(new Date() ));
				tarih.setCellStyle(satirStyle);

				Row headerRow = sheet.createRow(1);
				for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
				{
					Cell bname = headerRow.createCell(q);
					bname.setCellValue(mdl.getColumnName(q));
					bname.setCellStyle(headerSolaStyle);
				}
				for (int i =0;i< mdl.getRowCount() ;i++)
				{
					Row satirRow = sheet.createRow(i+2);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						hname.setCellValue( mdl.getValueAt(i,s).toString());
						hname.setCellStyle(solaStyle); 
					}
				}
				for (int i=0; i<= mdl.getColumnCount()-1; i++){
					sheet.autoSizeColumn(i);
				}
				//
				FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile()  + "_" + zaman + uzanti));
				workbook.write(out);
				out.close();
				//**************************************
			}
			GuiUtil.setWaitCursor(sp2,false);
			
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO, "Aktarma Islemi Tamamlandi....." );
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	public static void mail_at() throws IOException
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Loglama");
		XSSFFont headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.BLUE.getIndex()); 
		XSSFCellStyle headerStyle = workbook.createCellStyle();
		XSSFCellStyle headerSolaStyle = workbook.createCellStyle();
		headerStyle.setFont(headerFont);
		headerStyle.setAlignment(HorizontalAlignment.RIGHT);

		XSSFFont solaFont = workbook.createFont();
		solaFont.setFontName("Arial Narrow");
		solaFont. setFontHeight((short)(10*20));
		XSSFCellStyle solaStyle = workbook.createCellStyle();
		solaStyle.setFont(solaFont);
		solaStyle.setAlignment(HorizontalAlignment.LEFT);

		XSSFFont headerSolaFont = workbook.createFont();
		headerSolaFont.setBold(true);
		headerSolaFont.setColor(IndexedColors.BLUE.getIndex()); 
		headerSolaStyle.setFont(headerSolaFont);
		headerSolaStyle.setAlignment(HorizontalAlignment.LEFT);

		XSSFCellStyle satirStyle = workbook.createCellStyle();
		
		XSSFFont satirFont = workbook.createFont();
		satirFont.setFontName("Arial Narrow");
		satirFont. setFontHeight((short)(10*20));
		satirStyle.setFont(satirFont);
		satirStyle.setAlignment(HorizontalAlignment.RIGHT);

		

		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		XSSFCellStyle acikStyle = workbook.createCellStyle();
		XSSFFont acikFont = workbook.createFont();
		acikFont.setColor(IndexedColors.RED.getIndex()); 
		acikFont.setBold(true);
		acikFont.setFontName("Arial");
		acikFont. setFontHeight((short)(22*20));
		acikStyle.setFont(acikFont);
		acikStyle.setAlignment(HorizontalAlignment.CENTER);

		Row baslikRow = sheet.createRow(0);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -2));
		Cell baslikname = baslikRow.createCell(0);

		baslikname.setCellValue(BAGLAN.cariDizin.fIRMA_ADI);
		baslikname.setCellStyle(acikStyle);

		Cell tarih = baslikRow.createCell(3);
		SimpleDateFormat DateFor = new SimpleDateFormat("dd.MM.yyyy");
		tarih.setCellValue(DateFor.format(new Date() ));
		tarih.setCellStyle(satirStyle);

		Row headerRow = sheet.createRow(1);
		for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
		{
			Cell bname = headerRow.createCell(q);
			bname.setCellValue(mdl.getColumnName(q));
			bname.setCellStyle(headerSolaStyle);
		}
		for (int i =0;i< mdl.getRowCount() ;i++)
		{
			Row satirRow = sheet.createRow(i + 2);
			for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
			{
				Cell hname = satirRow.createCell(s);
				hname.setCellValue( mdl.getValueAt(i,s).toString());
				hname.setCellStyle(solaStyle); 
			}
		}		
		for (int i=0; i<= mdl.getColumnCount()-1; i++){
			sheet.autoSizeColumn(i);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		workbook.write(bos);
		byte[] byteArray= bos.toByteArray();
		InputStream in = new ByteArrayInputStream(byteArray);
		oac.ds = new ByteArrayDataSource(in, "application/x-any");
		bos.close();
	}
}
