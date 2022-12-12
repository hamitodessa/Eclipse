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
import javax.swing.JScrollPane;
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

public class LOGLAMA_RAPOR extends JInternalFrame {
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
	public LOGLAMA_RAPOR() {
		setTitle("LOG RAPORLAMA");
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(0, 0, 1250, 600);
		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 50));
		panel.setMaximumSize(new Dimension(0, 50));
		panel.setLayout(null);

		comboBox = new JComboBox<String>();
		comboBox.setForeground(new Color(0, 0, 255));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Cari Hesap", "Fatura", "Kambiyo","Adres","Kur","Sms-Mail"}));
		comboBox.setBounds(10, 11, 142, 22);
		panel.add(comboBox);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(178, 12, 130, 20);dateChooser.getComponent(1).addMouseListener(new MouseAdapter() {
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
		panel.add(dateChooser);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(320, 12, 130, 20);dateChooser.getComponent(1).addMouseListener(new MouseAdapter() {
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
		panel.add(dateChooser_1);

		textField = new JTextField();
		textField.setBounds(455, 12, 351, 20);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(912, 12, 96, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("Evrak");
		lblNewLabel.setBounds(854, 15, 48, 14);
		panel.add(lblNewLabel);

		textField_2 = new JTextField();
		textField_2.setText("");
		textField_2.setBounds(1108, 12, 96, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblKullanici = new JLabel("Kullanici");
		lblKullanici.setBounds(1050, 15, 48, 14);
		panel.add(lblKullanici);
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.setGridColor(oac.gridcolor);


		//	table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane.setViewportView(table);
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
				if(BAGLAN.cariDizin.lOGLAMA_YERI.equals("Veritabani Kayit"))
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
				else if(BAGLAN.cariDizin.lOGLAMA_YERI.equals("Dosya")) //SQLITE Dosyasi
				{
					rs  = 	sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.cariLogDizin);
				}
				else if(BAGLAN.cariDizin.lOGLAMA_YERI.equals("Text Dosya")) //Text  Dosyasi
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
				if(BAGLAN.fatDizin.lOGLAMA_YERI.equals("Veritabani Kayit"))
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
				else if(BAGLAN.fatDizin.lOGLAMA_YERI.equals("Dosya"))//sQLITE Dosyasi
				{
					rs  = sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.fatLogDizin);
				}
				else if(BAGLAN.fatDizin.lOGLAMA_YERI.equals("Text Dosya")) //Text  Dosyasi
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
				if(BAGLAN.kamDizin.lOGLAMA_YERI.equals("Veritabani Kayit"))
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
				else if(BAGLAN.kamDizin.lOGLAMA_YERI.equals("Dosya"))// sQLITE Dosyasi
				{
					rs  = sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.kamLogDizin);
				}
				else if(BAGLAN.kamDizin.lOGLAMA_YERI.equals("Text Dosya")) //Text  Dosyasi
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
				if(BAGLAN.adrDizin.lOGLAMA_YERI.equals("Veritabani Kayit"))
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
				else if(BAGLAN.adrDizin.lOGLAMA_YERI.equals("Dosya"))// sQLITE Dosyas
				{
					rs  = 	sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.adrLogDizin);
				}
				else if(BAGLAN.adrDizin.lOGLAMA_YERI.equals("Text Dosya")) //Text  Dosyasi
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
				if(BAGLAN.kurDizin.lOGLAMA_YERI.equals("Veritabani Kayit"))
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
				else if(BAGLAN.kurDizin.lOGLAMA_YERI.equals("Dosya"))// sQLITE Dosyas
				{
					rs  = 	sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.kurLogDizin);
				}
				else if(BAGLAN.kurDizin.lOGLAMA_YERI.equals("Text Dosya")) //Text  Dosyasi
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
				if(BAGLAN.smsDizin.lOGLAMA_YERI.equals("Veritabani Kayit"))
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
				else if(BAGLAN.smsDizin.lOGLAMA_YERI.equals("Dosya"))// sQLITE Dosyas
				{
					rs  = 	sQLITE.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.smsLogDizin);
				}
				else if(BAGLAN.smsDizin.lOGLAMA_YERI.equals("Text Dosya")) //Text  Dosyasi
				{
					DefaultTableModel tbm   = 	tEXT.log_txt_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
							"%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
							BAGLAN_LOG.smsLogDizin);
					table.setModel(tbm);
					txtmi = true ;
				}
			}
			if(txtmi == false )
			{
				if (!rs.isBeforeFirst() ) {  
					GRID_TEMIZLE.grid_temizle(table);
					//		lblNewLabel_1.setText("0");
					OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + 0 + " saniye");
					return;
				}


				if(txtmi == true)
				{

				}
				else
				{
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}

			} 
			//	GRID_TEMIZLE.grid_temizle(table);
			txtmi = false ;
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;


			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			//tc.setCellRenderer(new TARIH_SAATLI());
			tc.setMinWidth(140);
			tc.setMaxWidth(140);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			//tc.setMinWidth(600);

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
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);


			int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
			table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
			table.setRowSelectionInterval(lastRow, lastRow);

			table.setSelectionBackground(Color.PINK);
			table.setSelectionForeground(Color.BLUE);
			//			lblNewLabel_1.setText(FORMATLAMA.doub_0(table.getRowCount()));
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
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Cari Arama", JOptionPane.ERROR_MESSAGE);
		}
	}
}
