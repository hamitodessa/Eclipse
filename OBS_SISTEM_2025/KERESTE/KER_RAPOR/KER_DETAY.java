package KER_RAPOR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.mail.util.ByteArrayDataSource;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
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

import OBS_2025.FILTRE;
import OBS_2025.GuiUtil;
import OBS_2025.OBS_MAIN;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.FILE_UZANTI;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.KER_RAPOR_BILGI;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

@SuppressWarnings({ "serial", "static-access" ,"resource"})
public class KER_DETAY extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	
	static KERESTE_ACCESS ker_Access = new KERESTE_ACCESS(oac._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);

	private static JTable table;
	private static String gana ="" ;
	private static String galt  = "";
	private static String cana  = "";
	private static String calt  = "";
	private static String goz  = "";
	private static String coz  = "";
	private static String gdpo  = "";
	private static String cdpo  = "";
	
	private static JLabel lbladet;
	private static JLabel lblm3;
	private static JLabel lblmiktar;
	public static JSplitPane splitPane ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KER_DETAY frame = new KER_DETAY();
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

	public KER_DETAY() {
		setTitle("KERESTE DETAY RAPOR");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1100,600);

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.setGridColor(oac.gridcolor);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kayit Sayisi :");
		lblNewLabel.setBounds(10, 5, 85, 14);
		panel.add(lblNewLabel);
		
		lbladet = new JLabel("0");
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setBounds(100, 5, 40, 14);
		panel.add(lbladet);
		
		lblm3 = new JLabel("0.000");
		lblm3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblm3.setForeground(new Color(0, 0, 128));
		lblm3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblm3.setBounds(557, 5, 74, 14);
		panel.add(lblm3);
		
		lblmiktar = new JLabel("0");
		lblmiktar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblmiktar.setForeground(new Color(0, 0, 128));
		lblmiktar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblmiktar.setBounds(450, 5, 104, 14);
		panel.add(lblmiktar);
	}
	public static void hisset()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			lblmiktar.setText(FORMATLAMA.doub_0( 0));
			lblm3.setText(FORMATLAMA.doub_3( 0));
			grup_cevir() ;
			KER_RAPOR_BILGI ker_BILGI = new KER_RAPOR_BILGI();
			ker_BILGI.setGTarih1(TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1_1));
			ker_BILGI.setGTarih2(TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1_1));
			ker_BILGI.setGKodu1(FILTRE.formattedTextField_2.getText());
			ker_BILGI.setGKodu2(FILTRE.formattedTextField_1_1.getText());
			ker_BILGI.setPaket_No1(FILTRE.textField_88.getText());
			ker_BILGI.setPaket_No2(FILTRE.textField_88_1.getText());
			ker_BILGI.setGCari_Firma1(FILTRE.textField_84.getText());
			ker_BILGI.setGCari_Firma2(FILTRE.textField_85.getText());
			ker_BILGI.setEvrak_No1(FILTRE.textField_90.getText());
			ker_BILGI.setEvrak_No2(FILTRE.textField_91.getText());
			ker_BILGI.setGAna_Grup(gana);
			ker_BILGI.setGAlt_Grup(galt);
			ker_BILGI.setDepo(gdpo);
			ker_BILGI.setOzel_Kod(goz);
			ker_BILGI.setCTarih1(TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1_1_1));
			ker_BILGI.setCTarih2(TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1_1_1));
			ker_BILGI.setCCari_Firma1(FILTRE.textField_86.getText());
			ker_BILGI.setCCari_Firma2(FILTRE.textField_87.getText());
			ker_BILGI.setCEvrak_No1(FILTRE.textField_92.getText());
			ker_BILGI.setCEvrak_No2(FILTRE.textField_93.getText());
			ker_BILGI.setCAna_Grup(cana);
			ker_BILGI.setCAlt_Grup(calt);
			ker_BILGI.setCDepo(cdpo);
			ker_BILGI.setCOzel_Kod(coz);
			//

			rs = ker_Access.stok_rapor(ker_BILGI);
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;

				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(120);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(0,true));
				tc.setMinWidth(60);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SOLA());
				tc.setCellRenderer(new TARIH());
				tc.setMinWidth(80);

				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(10);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(11);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(12);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(13);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(14);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(15);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(16);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(17);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(18);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(19);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				
				
				tc = tcm.getColumn(20);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(21);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(22);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(23);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(24);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(25);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(26);
				tc.setHeaderRenderer(new SOLA());
				tc.setCellRenderer(new TARIH());
				tc.setMinWidth(80);

				tc = tcm.getColumn(27);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(28);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(29);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(30);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(31);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(32);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(33);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(34);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(35);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(36);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(37);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(38);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(39);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(40);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(41);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(42);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);


				lbladet.setText(FORMATLAMA.doub_0(mdl.getRowCount()));
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
				topla();
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

				String deger;
				String[] parts;
				Font bigFont;
				deger = GLOBAL.setting_oku("KER_RAPORLAMA").toString();
				deger = deger.substring(1, deger.length()-1);
				parts = deger.split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				table.setFont(bigFont);

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kereste Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void topla()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		double  m3 = 0.00  ;
		double urunmiktar = 0  ;
		for (int  i = 0 ; i <= table.getRowCount() -1 ; i ++)
		{
			urunmiktar += Double.parseDouble(mdl.getValueAt(i, 5).toString());
			m3 += Double.parseDouble( mdl.getValueAt(i,6).toString());
		}
		lblmiktar.setText(FORMATLAMA.doub_0( urunmiktar));
		lblm3.setText(FORMATLAMA.doub_3( m3));
	}
	private static void grup_cevir()
	{
		try {
		ResultSet	rs = null;
		//***********************GIRIS ANA GRUP
		if ( FILTRE.comboBox_78_1.getItemAt(FILTRE.comboBox_78_1.getSelectedIndex()).equals(""))
		{
			gana = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_78_1.getItemAt(FILTRE.comboBox_78_1.getSelectedIndex()).equals("Bos Olanlar"))
		{
			gana = " = '' " ;
		}
		else
		{
			rs = ker_Access.ker_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_78_1.getItemAt(FILTRE.comboBox_78_1.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				gana =  "= " + Integer.toString( rs.getInt("AGID_Y") );
			}
		}
		//***********************GIRIS ALT GRUP
		if ( FILTRE.comboBox_79_1.getItemAt(FILTRE.comboBox_79_1.getSelectedIndex()).equals(""))
		{
			galt = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_79_1.getItemAt(FILTRE.comboBox_79_1.getSelectedIndex()).equals("Bos Olanlar"))
		{
			galt = " = '' " ;
		}
		else
		{
			rs = ker_Access.ker_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_79_1.getItemAt(FILTRE.comboBox_79_1.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				galt ="=" + Integer.toString( rs.getInt("ALID_Y"));
			}
		}
		//***********************CIKIS ANA GRUP
		if ( FILTRE.comboBox_78_2.getItemAt(FILTRE.comboBox_78_2.getSelectedIndex()).equals(""))
		{
			cana = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_78_2.getItemAt(FILTRE.comboBox_78_2.getSelectedIndex()).equals("Bos Olanlar"))
		{
			cana = " = '' " ;
		}
		else
		{
			rs = ker_Access.ker_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_78_2.getItemAt(FILTRE.comboBox_78_2.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				cana =  "= " + Integer.toString( rs.getInt("AGID_Y") );
			}
		}
		//***********************CIKIS ALT GRUP
		if ( FILTRE.comboBox_79_2.getItemAt(FILTRE.comboBox_79_2.getSelectedIndex()).equals(""))
		{
			calt = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_79_2.getItemAt(FILTRE.comboBox_79_2.getSelectedIndex()).equals("Bos Olanlar"))
		{
			calt = " = '' " ;
		}
		else
		{
			rs = ker_Access.ker_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_79_2.getItemAt(FILTRE.comboBox_79_2.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				calt ="=" + Integer.toString( rs.getInt("ALID_Y"));
			}

		}
		//***********************GIRIS OZEL KOD
		if ( FILTRE.comboBox_80_1.getItemAt(FILTRE.comboBox_80_1.getSelectedIndex()).equals(""))
		{
			goz = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_80_1.getItemAt(FILTRE.comboBox_80_1.getSelectedIndex()).equals("Bos Olanlar"))
		{
			goz = " = '' " ;
		}		      
		else		      
		{
			rs = ker_Access.ker_kod_degisken_ara("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN", FILTRE.comboBox_80_1.getItemAt(FILTRE.comboBox_80_1.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				goz ="=" + Integer.toString( rs.getInt("OZ1ID_Y"));
			}
		}
		//***********************CIKIS OZEL KOD
		if ( FILTRE.comboBox_80_2.getItemAt(FILTRE.comboBox_80_2.getSelectedIndex()).equals(""))
		{
			coz = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_80_2.getItemAt(FILTRE.comboBox_80_2.getSelectedIndex()).equals("Bos Olanlar"))
		{
			coz = " = '' " ;
		}		      
		else		      
		{
			rs = ker_Access.ker_kod_degisken_ara("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN", FILTRE.comboBox_80_2.getItemAt(FILTRE.comboBox_80_2.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				coz ="=" + Integer.toString( rs.getInt("OZ1ID_Y"));
			}
		}
		//***********************GIRIS DEPO
		if ( FILTRE.comboBox_80_3.getItemAt(FILTRE.comboBox_80_3.getSelectedIndex()).equals(""))
		{
			gdpo = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_80_3.getItemAt(FILTRE.comboBox_80_3.getSelectedIndex()).equals("Bos Olanlar"))
		{
			gdpo = " = '' " ;
		}		      
		else		      
		{
			rs = ker_Access.ker_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN", FILTRE.comboBox_80_3.getItemAt(FILTRE.comboBox_80_3.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				gdpo ="=" + Integer.toString( rs.getInt("DPID_Y"));
			}
		}
		//***********************CIKIS DEPO
		if ( FILTRE.comboBox_80_4.getItemAt(FILTRE.comboBox_80_4.getSelectedIndex()).equals(""))
		{
			cdpo = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_80_4.getItemAt(FILTRE.comboBox_80_4.getSelectedIndex()).equals("Bos Olanlar"))
		{
			cdpo = " = '' " ;
		}		      
		else		      
		{
			rs = ker_Access.ker_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN", FILTRE.comboBox_80_4.getItemAt(FILTRE.comboBox_80_4.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				cdpo ="=" + Integer.toString( rs.getInt("DPID_Y"));
			}
		}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kereste Detay", JOptionPane.ERROR_MESSAGE);
		} 
	}
	public static void excell_aktar()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		
		if (mdl.getRowCount() == 0 )
		{
		JOptionPane.showMessageDialog(null, "Aktarilacak Bilgi Yok.....","Kereste Detay", JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			write() ;	
		}
	}
	public static void write()
	{
		///// Progres Bsr olayi
		Runnable runner = new Runnable()
		{ 
		public void run() {
			/////  
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

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");  
				LocalDateTime now = LocalDateTime.now();  
				String zaman = dtf.format(now)  ;

				File outputfile = new File("Kereste_Detay");
				fileChooser.setSelectedFile(outputfile);
				int returnVal = fileChooser.showSaveDialog(null);
				if ( returnVal != JFileChooser.APPROVE_OPTION )
				{
					return;
				}
				Progres_Bar_Temizle();
				OBS_MAIN.progressBar.setMaximum(table.getRowCount()-1);
				OBS_MAIN.progressBar.setStringPainted(true);
				GuiUtil.setWaitCursor(splitPane,true);
				String uzanti ="";
				File excelFile =  FILE_UZANTI. getSelectedFileWithExtension(fileChooser);
				uzanti  = excelFile.getName().substring(excelFile.getName().lastIndexOf("."));
				if  (uzanti.equals(".xls") )
				{
					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook.createSheet("Kereste_Detay");
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
					HSSFCellStyle satirStylemik = workbook.createCellStyle();
					HSSFCellStyle satirStyle3 = workbook.createCellStyle();
					HSSFCellStyle satirStyle2 = workbook.createCellStyle();
					HSSFFont satirFont = workbook.createFont();
					satirFont.setFontName("Arial Narrow");
					satirFont. setFontHeight((short)(10*20));
					satirStyle.setFont(satirFont);
					satirStyle.setAlignment(HorizontalAlignment.RIGHT);
					satirStyle3.setFont(satirFont);
					satirStyle2.setFont(satirFont);
					satirStylemik.setFont(satirFont);
					satirStyle3.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
					satirStyle2.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
					satirStylemik.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0"));
					satirStyle3.setAlignment(HorizontalAlignment.RIGHT);
					satirStyle2.setAlignment(HorizontalAlignment.RIGHT);
					satirStylemik.setAlignment(HorizontalAlignment.RIGHT);
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
					sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -1));
					Cell baslikname = baslikRow.createCell(0);

					baslikname.setCellValue( BAGLAN.kerDizin.fIRMA_ADI );
					baslikname.setCellStyle(acikStyle);

					Row headerRow = sheet.createRow(1);
					for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
					{
						Cell bname = headerRow.createCell(q);
						if (q == 5 || q ==6 || q == 8 || q == 10  || q == 11 || q == 12 || q == 15|| q == 16 || q == 27  || q == 29 || q == 30|| q == 31 || q == 34|| q == 35)
						{
							bname.setCellValue(mdl.getColumnName(q));
							bname.setCellStyle(headerStyle);
						}
						else
						{
							bname.setCellValue(mdl.getColumnName(q));
							bname.setCellStyle(headerSolaStyle);
						}
					}
					//**********
					for (int i =0;i <= mdl.getRowCount() -1 ;i++)
					{
						Progres_Bar(mdl.getRowCount() , i);
						Row satirRow = sheet.createRow(i+2);
						for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
						{
							
							Cell hname = satirRow.createCell(s);
							if ( mdl.getValueAt(i, s) != null)
							{
								if (s== 5) 
								{
									hname.setCellStyle(satirStylemik);
									hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
								}
								else  if (s== 6) 
								{
									hname.setCellStyle(satirStyle3);
									hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
								}
								else  if (s == 5 || s ==6 || s == 8 || s == 10  || s == 11 || s == 12 || s == 15|| s == 16 || s == 27  || s == 29 || s == 30|| s == 31 || s == 34|| s == 35)
								{
									hname.setCellStyle(satirStyle2);
									hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
								}
								else  if (s== 7 ) 
								{
									if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
									{
										DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
										hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
										hname.setCellStyle(solaStyle);
									}
									else if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
									{
										hname.setCellValue(	TARIH_CEVIR.tarih_ters(mdl.getValueAt(i ,s).toString())) ;
										hname.setCellStyle(solaStyle);
									}
								}
								else  if (s== 26) 
								{
									if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
									{
										if( ! mdl.getValueAt(i ,s).equals(""))
										{
											hname.setCellValue(	mdl.getValueAt(i ,s).toString()) ;
											hname.setCellStyle(solaStyle);
										}
									}
									else if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
									{
										if( ! mdl.getValueAt(i ,s).toString().equals(""))
										{
										hname.setCellValue(	TARIH_CEVIR.tarih_ters(mdl.getValueAt(i ,s).toString())) ;
										hname.setCellStyle(solaStyle);
										}
									}
								}
								else
								{
									hname.setCellValue( mdl.getValueAt(i,s).toString());
									hname.setCellStyle(solaStyle); 
								}
							}
							else
							{
								hname.setCellValue("");
								hname.setCellStyle(satirStyle);
							}
						}
					}
					//**********
					for (int i=0; i<= mdl.getColumnCount()-1; i++)
					{
						sheet.autoSizeColumn(i);
					}
					FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile() + "_" + zaman + uzanti));
					workbook.write(out);
					out.close();
				}
				else
				{
					//************************************** XLXS *****************************************************
					XSSFWorkbook workbook = new XSSFWorkbook();
					XSSFSheet sheet = workbook.createSheet("Kereste_Detay");
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
					XSSFCellStyle satirStylemik = workbook.createCellStyle();
					XSSFCellStyle satirStyle3 = workbook.createCellStyle();
					XSSFCellStyle satirStyle2 = workbook.createCellStyle();
					XSSFFont satirFont = workbook.createFont();
					satirFont.setFontName("Arial Narrow");
					satirFont. setFontHeight((short)(10*20));
					satirStyle.setFont(satirFont);
					satirStyle.setAlignment(HorizontalAlignment.RIGHT);
					satirStyle3.setFont(satirFont);
					satirStyle2.setFont(satirFont);
					satirStylemik.setFont(satirFont);
					satirStyle3.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
					satirStyle2.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
					satirStylemik.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0"));
					satirStyle3.setAlignment(HorizontalAlignment.RIGHT);
					satirStyle2.setAlignment(HorizontalAlignment.RIGHT);
					satirStylemik.setAlignment(HorizontalAlignment.RIGHT);
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
					sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -1));
					Cell baslikname = baslikRow.createCell(0);

					baslikname.setCellValue(BAGLAN.kerDizin.fIRMA_ADI );
					baslikname.setCellStyle(acikStyle);
					Row headerRow = sheet.createRow(1);
					for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
					{
						Cell bname = headerRow.createCell(q);
						if (q == 5 || q ==6 || q == 8 || q == 10  || q == 11 || q == 12 || q == 15|| q == 16 || q == 27  || q == 29 || q == 30|| q == 31 || q == 34|| q == 35)
						{
							bname.setCellValue(mdl.getColumnName(q));
							bname.setCellStyle(headerStyle);
						}
						else
						{
							bname.setCellValue(mdl.getColumnName(q));
							bname.setCellStyle(headerSolaStyle);
						}
					}
					//

					for (int i =0;i <= mdl.getRowCount()-1 ;i++)
					{
						Progres_Bar(mdl.getRowCount(), i);
						Row satirRow = sheet.createRow(i+2);
						for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
						{
							Cell hname = satirRow.createCell(s);
							if ( mdl.getValueAt(i, s) != null)
							{
								if (s== 5) 
								{
									hname.setCellStyle(satirStylemik);
									hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
								}
								else  if (s== 6) 
								{
									hname.setCellStyle(satirStyle3);
									hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
								}
								else  if (s == 5 || s ==6 || s == 8 || s == 10  || s == 11 || s == 12 || s == 15|| s == 16 || s == 27  || s == 29 || s == 30|| s == 31 || s == 34|| s == 35)
								{
									hname.setCellStyle(satirStyle2);
									hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
								}
								else  if (s== 7 ) 
								{
									if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
									{
										DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
										hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
										hname.setCellStyle(solaStyle);
									}
									else if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
									{
										hname.setCellValue(	TARIH_CEVIR.tarih_ters(mdl.getValueAt(i ,s).toString())) ;
										hname.setCellStyle(solaStyle);
									}
								}
								else  if (s== 26) 
								{
									if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
									{
										if( ! mdl.getValueAt(i ,s).equals(""))
										{
											hname.setCellValue(	mdl.getValueAt(i ,s).toString()) ;
											hname.setCellStyle(solaStyle);
										}
									}
									else if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
									{
										if( ! mdl.getValueAt(i ,s).toString().equals(""))
										{
										hname.setCellValue(	TARIH_CEVIR.tarih_ters(mdl.getValueAt(i ,s).toString())) ;
										hname.setCellStyle(solaStyle);
										}
									}
								}
								else
								{
									hname.setCellValue( mdl.getValueAt(i,s).toString());
									hname.setCellStyle(solaStyle); 
								}
							}
							else
							{
								hname.setCellValue("");
								hname.setCellStyle(satirStyle);
							}
						}
					}
					for (int i=0; i<= mdl.getColumnCount()-1; i++)
					{
						sheet.autoSizeColumn(i);
					}
					FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile()  + "_" + zaman + uzanti));
					workbook.write(out);
					out.close();
				}
				Progres_Bar_Temizle();
				GuiUtil.setWaitCursor(splitPane,false);
				JOptionPane.showMessageDialog(null, "Aktarma Islemi Tamamlandi.....","Stok Detay", JOptionPane.PLAIN_MESSAGE);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null,  ex.getMessage(),"Excell Aktarma", JOptionPane.ERROR_MESSAGE);
			}
		}
		};
		//// Progress Bar
		Thread t = new Thread(runner, "Code Executer");
		t.start();
		//
	}
	public static void mail_at() throws IOException
	{
		//************************************** XLXS *****************************************************
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Kereste_Detay");
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
		XSSFCellStyle satirStylemik = workbook.createCellStyle();
		XSSFCellStyle satirStyle3 = workbook.createCellStyle();
		XSSFCellStyle satirStyle2 = workbook.createCellStyle();
		XSSFFont satirFont = workbook.createFont();
		satirFont.setFontName("Arial Narrow");
		satirFont. setFontHeight((short)(10*20));
		satirStyle.setFont(satirFont);
		satirStyle.setAlignment(HorizontalAlignment.RIGHT);
		satirStyle3.setFont(satirFont);
		satirStyle2.setFont(satirFont);
		satirStylemik.setFont(satirFont);
		satirStyle3.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
		satirStyle2.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
		satirStylemik.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0"));
		satirStyle3.setAlignment(HorizontalAlignment.RIGHT);
		satirStyle2.setAlignment(HorizontalAlignment.RIGHT);
		satirStylemik.setAlignment(HorizontalAlignment.RIGHT);
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
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -1));
		Cell baslikname = baslikRow.createCell(0);

		baslikname.setCellValue(BAGLAN.kerDizin.fIRMA_ADI );
		baslikname.setCellStyle(acikStyle);
		Row headerRow = sheet.createRow(1);
		for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
		{
			Cell bname = headerRow.createCell(q);
			if (q == 5 || q ==6 || q == 8 || q == 10  || q == 11 || q == 12 || q == 15|| q == 16 || q == 27  || q == 29 || q == 30|| q == 31 || q == 34|| q == 35)
			{
				bname.setCellValue(mdl.getColumnName(q));
				bname.setCellStyle(headerStyle);
			}
			else
			{
				bname.setCellValue(mdl.getColumnName(q));
				bname.setCellStyle(headerSolaStyle);
			}
		}
		//

		for (int i =0;i <= mdl.getRowCount()-1 ;i++)
		{

			Row satirRow = sheet.createRow(i+2);
			for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
			{
				Cell hname = satirRow.createCell(s);
				if ( mdl.getValueAt(i, s) != null)
				{
					if (s== 5) 
					{
						hname.setCellStyle(satirStylemik);
						hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
					}
					else  if (s== 6) 
					{
						hname.setCellStyle(satirStyle3);
						hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
					}
					else  if (s == 5 || s ==6 || s == 8 || s == 10  || s == 11 || s == 12 || s == 15|| s == 16 || s == 27  || s == 29 || s == 30|| s == 31 || s == 34|| s == 35)
					{
						hname.setCellStyle(satirStyle2);
						hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
					}
								else  if (s== 7 ) 
								{
									if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
									{
										DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
										hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
										hname.setCellStyle(solaStyle);
									}
									else if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
									{
										hname.setCellValue(	TARIH_CEVIR.tarih_ters(mdl.getValueAt(i ,s).toString())) ;
										hname.setCellStyle(solaStyle);
									}
								}
								else  if (s== 26) 
								{
									if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
									{
										if( ! mdl.getValueAt(i ,s).equals(""))
										{
											hname.setCellValue(	mdl.getValueAt(i ,s).toString()) ;
											hname.setCellStyle(solaStyle);
										}
									}
									else if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
									{
										if( ! mdl.getValueAt(i ,s).toString().equals(""))
										{
										hname.setCellValue(	TARIH_CEVIR.tarih_ters(mdl.getValueAt(i ,s).toString())) ;
										hname.setCellStyle(solaStyle);
										}
									}
								}
					else
					{
						hname.setCellValue( mdl.getValueAt(i,s).toString());
						hname.setCellStyle(solaStyle); 
					}
				}
				else
				{
					hname.setCellValue("");
					hname.setCellStyle(satirStyle);
				}
			}
		}
		for (int i=0; i<= mdl.getColumnCount()-1; i++)
		{
			sheet.autoSizeColumn(i);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		workbook.write(bos);
		byte[] byteArray= bos.toByteArray();
		InputStream in = new ByteArrayInputStream(byteArray);
		oac.ds = new ByteArrayDataSource(in, "application/x-any");
		bos.close();
	}
	static void Progres_Bar(int max, int deger) throws InterruptedException
	{
		OBS_MAIN.progressBar.setValue(deger);
	}
	static void Progres_Bar_Temizle()
	{
		OBS_MAIN.progressBar.setMaximum(0);
		OBS_MAIN.progressBar.setValue(0);
		OBS_MAIN.progressBar.setStringPainted(false);
	}
}
