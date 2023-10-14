package KER_RAPOR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
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
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.SOLA_ORTA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

@SuppressWarnings({"serial","static-access","unused","deprecation"})
public class KER_ORT_SATIS extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KERESTE_ACCESS ker_Access = new KERESTE_ACCESS(OBS_SIS_2025_ANA_CLASS._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);

	private static JTable table;
	private static String qwq6  = "";
	private static String qwq7  = "";
	private static JLabel lbladet;
	private static String ozkod ="";
	private static String hANGI = "" ;
	public static JSplitPane splitPane ;
	static String sstr_1 = "" ;
	static String sstr_2 = "" ;
	static String sstr_4 = "" ;
	static String sstr_5 = "" ;
	static String yu = "" ;
	static String iu = "" ;
	private static 	long startTime ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KER_ORT_SATIS frame = new KER_ORT_SATIS();
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

	public KER_ORT_SATIS() {
		setTitle("KERESTE ORTALAMA SATIS");
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
		if(oac.gridcolor.toString() != "java.awt.Color[r=255,g=255,b=255]") 
		{
			table.setGridColor(oac.gridcolor);
		}

		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {

					String[] parts;
					String deger ;
					deger = GLOBAL.setting_oku("PRG_FILTRE").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
							OBS_MAIN.btnFiltre.doClick();
						}
					}
				}
				catch (Exception ex)
				{

				}
			}
		});
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Satir Sayisi :");
		lblNewLabel.setBounds(10, 7, 85, 14);
		panel.add(lblNewLabel);

		lbladet = new JLabel("0");
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setBounds(100, 7, 71, 14);
		panel.add(lbladet);

	}
	public static void yenile ()
	{
		try
		{
			diger_kodlu();

		}
		catch (Exception ex)
		{
			GRID_TEMIZLE.grid_temizle(table);
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Ortalama Fiat", JOptionPane.ERROR_MESSAGE);	
		}
	}
	private static void diger_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			grup_cevir() ;
			boolean kurlu = FILTRE.chckbxNewCheckBox_5.isSelected() ? true : false ;
			rs = ker_Access.ort_diger_kodu(yu,qwq6,qwq7,
					FILTRE.textField_96.getText(),FILTRE.textField_97.getText() ,
					FILTRE.textField_95.getText(),FILTRE.textField_98.getText() ,
					FILTRE.formattedTextField_3.getText(),FILTRE.formattedTextField_1_2.getText() ,
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1_2),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1_2), 
					BAGLAN.kurDizin.kOD ,
					FILTRE.comboBox_82.getItemAt(FILTRE.comboBox_82.getSelectedIndex()),
					iu ,hANGI,kurlu);

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

				int sut = 0 ;
				if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Sinif"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(90);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(250);
					tc.setMaxWidth(250);
					sut = 2 ;

				}
				if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Sinif-Kal"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(90);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(50);
					tc.setMaxWidth(50);
					sut = 2 ;

				}
				if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Sinif-Boy"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(90);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(50);
					tc.setMaxWidth(50);
					sut = 2 ;

				}
				if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Sinif-Gen"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(90);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(50);
					tc.setMaxWidth(50);
					sut = 2 ;

				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Kodu"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(100);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(250);
					tc.setMaxWidth(250);
					sut = 2 ;

				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Yil"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(60);
					sut = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Yil_Ay"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(60);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(50);

					sut = 2 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Ana Grup"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(175);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(175);

					sut = 2 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Hesap Kodu"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(100);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(200);

					sut = 2 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Hesap Kodu-Ana_Alt_Grup"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(100);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(175);

					tc = tcm.getColumn(2);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(175);

					sut = 3 ;
				}
				tc = tcm.getColumn(sut +0 );
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);

				tc = tcm.getColumn(sut +1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);

				tc = tcm.getColumn(sut + 2);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(0,false)); // 3 idi
				tc.setMinWidth(90);

				tc = tcm.getColumn(sut + 3);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(90);

				tc = tcm.getColumn(sut + 4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(110);

				tc = tcm.getColumn(sut + 5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(110);

				Dimension dd = th.getPreferredSize();
				dd.height = 30;
				th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				table.setRowSelectionInterval(lastRow, lastRow);
				lbladet.setText(FORMATLAMA.doub_0(table.getRowCount()));
				table.setSelectionBackground(Color.PINK);
				table.setSelectionForeground(Color.BLUE);
				fontt();
			}
		} 
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Ortalama Fiat Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void grup_cevir()
	{
		try 
		{
			ResultSet	rs = null;

			if ( FILTRE.comboBox_83.getItemAt(FILTRE.comboBox_83.getSelectedIndex()).equals("GIREN"))
			{
				hANGI= "" ;
			}
			else if ( FILTRE.comboBox_83.getItemAt(FILTRE.comboBox_83.getSelectedIndex()).equals("CIKAN"))
			{
				hANGI= "C" ;
			}
			else 
			{
				hANGI= "" ;
			}
			//** Urun Ana grup
			if ( FILTRE.comboBox_78_3.getItemAt(FILTRE.comboBox_78_3.getSelectedIndex()).equals(""))
			{
				qwq6 = " Like  '%' " ;
			}
			else if  ( FILTRE.comboBox_78_3.getItemAt(FILTRE.comboBox_78_3.getSelectedIndex()).equals("Bos Olanlar"))
			{
				qwq6 = " = '' " ;
			}
			else
			{
				rs = ker_Access.ker_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_78_3.getItemAt(FILTRE.comboBox_78_3.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					qwq6 = "=" + Integer.toString( rs.getInt("AGID_Y"));
				}

			}
			//** Urun Alt Grup

			if ( FILTRE.comboBox_79_3.getItemAt(FILTRE.comboBox_79_3.getSelectedIndex()).equals(""))
			{
				qwq7 = " Like  '%' " ;
			}
			else if  ( FILTRE.comboBox_79_3.getItemAt(FILTRE.comboBox_79_3.getSelectedIndex()).equals("Bos Olanlar"))
			{
				qwq7 = " = '' " ;
			}		        else		      
			{

				rs = ker_Access.ker_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_79_3.getItemAt(FILTRE.comboBox_79_3.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					qwq7 ="=" + Integer.toString( rs.getInt("ALID_Y"));
				}

			}
			//**
			//** OZ1 OKU
			if ( FILTRE.comboBox_80_5.getItemAt(FILTRE.comboBox_80_5.getSelectedIndex()).equals(""))
			{
				ozkod = " Like  '%' " ;
			}
			else if  ( FILTRE.comboBox_80_5.getItemAt(FILTRE.comboBox_80_5.getSelectedIndex()).equals("Bos Olanlar"))
			{
				ozkod = " = '' " ;
			}		        
			else
			{
				rs =ker_Access.ker_kod_degisken_ara("OZ1ID_Y", "OZEL_KOD_1", "OZ_KOD_1_DEGISKEN", FILTRE.comboBox_80_5.getItemAt(FILTRE.comboBox_80_5.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					ozkod="=" + Integer.toString( rs.getInt("OZ1ID_Y"));
				}
			}
			//'**************************************************
			if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Ana Grup"))
			{ 
				yu = " (SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y =  KERESTE."+hANGI+"Ana_Grup ) as Ana_Grup " +
						" , (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y =  KERESTE."+hANGI+"Alt_Grup ) as Alt_Grup ";
				iu = " KERESTE."+hANGI+"Ana_Grup ,  KERESTE."+hANGI+"Alt_Grup order by KERESTE."+hANGI+"Ana_Grup ";
			}
			else  if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Sinif"))
			{
				yu = " SUBSTRING(KERESTE.Kodu,1, 2) as Sinif, (SELECT ACIKLAMA FROM KOD_ACIKLAMA  WHERE KOD = SUBSTRING(KERESTE.Kodu,1, 2) ) as Adi ";
				iu = " SUBSTRING(KERESTE.Kodu,1, 2)  order by SUBSTRING(KERESTE.Kodu,1, 2)  ";
			}
			else  if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Sinif-Kal"))
			{
				yu = " SUBSTRING(KERESTE.Kodu,1, 2) as Sinif, SUBSTRING(KERESTE.Kodu, 4, 3) as Kal ";
				iu = " SUBSTRING(KERESTE.Kodu,1, 2) ,  SUBSTRING(KERESTE.Kodu, 4, 3)  order by SUBSTRING(KERESTE.Kodu,1, 2) , SUBSTRING(KERESTE.Kodu, 4, 3) ";
			}
			else  if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Sinif-Boy"))
			{
				yu = " SUBSTRING(KERESTE.Kodu,1, 2) as Sinif, SUBSTRING(KERESTE.Kodu, 8, 4) as Boy ";
				iu = " SUBSTRING(KERESTE.Kodu,1, 2) ,  SUBSTRING(KERESTE.Kodu, 8, 4)  order by SUBSTRING(KERESTE.Kodu,1, 2) , SUBSTRING(KERESTE.Kodu, 8, 4) ";
			}
			else  if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Sinif-Gen"))
			{
				yu = " SUBSTRING(KERESTE.Kodu,1, 2) as Sinif, SUBSTRING(KERESTE.Kodu, 13, 4) as Gen ";
				iu = " SUBSTRING(KERESTE.Kodu,1, 2) ,  SUBSTRING(KERESTE.Kodu, 13, 4)  order by SUBSTRING(KERESTE.Kodu,1, 2) , SUBSTRING(KERESTE.Kodu, 13, 4) ";
			}
			else  if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Kodu"))
			{
				yu = " KERESTE.Kodu, (SELECT ACIKLAMA FROM KOD_ACIKLAMA  WHERE KOD = SUBSTRING(KERESTE.Kodu,1, 2) ) as Adi ";
				iu = " KERESTE.Kodu  order by KERESTE.Kodu  ";
			}
			else  if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Yil"))
			{
				if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
				{
					yu = " datepart(yyyy,KERESTE."+hANGI+"Tarih) as Yil ";
					iu = "  datepart(yyyy,KERESTE."+hANGI+"Tarih) order by datepart(yyyy,KERESTE."+hANGI+"Tarih)  ";
				}
				else if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
				{
					yu = " YEAR(KERESTE."+hANGI+"Tarih) as Yil "; 
					iu = "  YEAR(KERESTE."+hANGI+"Tarih) order by YEAR(KERESTE."+hANGI+"Tarih)  ";
				}
			}
			else  if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Yil_Ay"))
			{
				if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
				{
					yu = " datepart(yyyy,KERESTE."+hANGI+"Tarih) as Yil, datepart(mm,KERESTE."+hANGI+"Tarih) as Ay ";
					iu = "  datepart(yyyy,KERESTE."+hANGI+"Tarih) , datepart(mm,KERESTE."+hANGI+"Tarih) order by datepart(yyyy,KERESTE."+hANGI+"Tarih),datepart(mm,KERESTE."+hANGI+"Tarih)  ";
				}
				else if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
				{
					yu = " YEAR(KERESTE."+hANGI+"Tarih) as Yil, MONTH(KERESTE."+hANGI+"Tarih) as Ay ";
					iu = "  YEAR(KERESTE."+hANGI+"Tarih) , MONTH(KERESTE."+hANGI+"Tarih) order by YEAR(KERESTE."+hANGI+"Tarih),MONTH(KERESTE."+hANGI+"Tarih) ";
				}
			}
			else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Hesap Kodu-Ana_Alt_Grup"))
			{ 
				yu = " KERESTE."+ hANGI+"Cari_Firma,(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y =  KERESTE."+hANGI+"Ana_Grup ) as Ana_Grup " +
						" , (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y =  KERESTE."+hANGI+"Alt_Grup ) as Alt_Grup ";
				iu = " KERESTE."+ hANGI+"Cari_Firma,KERESTE."+hANGI+"Ana_Grup ,  KERESTE."+hANGI+"Alt_Grup order by KERESTE."+hANGI+"Ana_Grup ";
			}
			else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Hesap Kodu"))
			{ 
				if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
				{
					yu = "   KERESTE."+ hANGI+"Cari_Firma,(SELECT DISTINCT  UNVAN FROM OK_Car" + BAGLAN.cariDizin.kOD + ".dbo.HESAP WHERE hesap.hesap = KERESTE."+hANGI+"Cari_Firma   ) as Cari_Adi  ";
					iu = " KERESTE."+ hANGI+"Cari_Firma ORDER BY  KERESTE."+ hANGI+"Cari_Firma";
					
				}
				else if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
				{
					yu = "   KERESTE."+ hANGI+"Cari_Firma,(SELECT DISTINCT  UNVAN FROM OK_Car" + BAGLAN.cariDizin.kOD + ".HESAP WHERE hesap.hesap = KERESTE."+hANGI+"Cari_Firma   ) as Cari_Adi  ";
					iu = " KERESTE."+ hANGI+"Cari_Firma ORDER BY  KERESTE."+ hANGI+"Cari_Firma";
					
				}
			}
			//'************************************
		} 
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Ort Raporlama", JOptionPane.ERROR_MESSAGE);
		} 
	}
	private static void fontt()
	{
		String deger;
		String[] parts;
		Font bigFont;
		try {
			deger = GLOBAL.setting_oku("KER_RAPORLAMA").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table.setFont(bigFont);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void excell_aktar()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();

		if (mdl.getRowCount() == 0 )
		{
			JOptionPane.showMessageDialog(null, "Aktarilacak Bilgi Yok.....","Grup Raporlama", JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			write() ;	
		}
	}
	@SuppressWarnings("resource")
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
			fileChooser.setCurrentDirectory(new java.io.File("C:\\OBS_SISTEM\\"));
			fileChooser.setApproveButtonText("Kaydet");
			fileChooser.setDialogTitle("Excell Kayit");   

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");  
			LocalDateTime now = LocalDateTime.now();  
			String zaman = dtf.format(now)  ;

			File outputfile = new File("Ortalama_Fiat_Rapor");
			fileChooser.setSelectedFile(outputfile);
			int returnVal = fileChooser.showSaveDialog(null);
			if ( returnVal != JFileChooser.APPROVE_OPTION )
			{
				return;
			}
			GuiUtil.setWaitCursor(splitPane,true);
			//
			String uzanti ="";
			File excelFile =  FILE_UZANTI. getSelectedFileWithExtension(fileChooser);
			uzanti  = excelFile.getName().substring(excelFile.getName().lastIndexOf("."));
			//
			if  (uzanti.equals(".xls") )
			{
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Ortalama_Fiat_Rapor");
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
				int sutun = 0 ;
				if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Kodu"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Sinif"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Sinif-Kal"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Sinif-Boy"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Sinif-Gen"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Yil"))
				{
					sutun = 0 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Yil-Ay"))
				{
					sutun = 1 ;
				}

				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Hesap Kodu"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Hesap Kodu-Ana_Alt_Grup"))
				{
					sutun = 2 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Ana Grup"))
				{
					sutun = 1 ;
				}
				Row headerRow = sheet.createRow(1);
				for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
				{
					Cell bname = headerRow.createCell(q);
					if (q > sutun)
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
				for (int i =0;i< mdl.getRowCount() ;i++)
				{
					Row satirRow = sheet.createRow(i+2);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						if ( mdl.getValueAt(i, s) != null)
						{
							if (s == sutun +1 || s == sutun +2 ||s == sutun +5 || s == sutun +6)
							{
								hname.setCellStyle(satirStyle2);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
							}
							else if (s == sutun +3 )
							{
								hname.setCellStyle(satirStylemik);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));	
							}
							else if (s == sutun +4 )
							{
								hname.setCellStyle(satirStyle3);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
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
				for (int i=0; i<= mdl.getColumnCount()-1; i++){
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
				XSSFSheet sheet = workbook.createSheet("Kereste_Grup_Raporlama");
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
				//
				int sutun = 0 ;
				if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Kodu"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Sinif"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Sinif-Kal"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Sinif-Boy"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Sinif-Gen"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Yil"))
				{
					sutun = 0 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Yil-Ay"))
				{
					sutun = 1 ;
				}

				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Hesap Kodu"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Hesap Kodu-Ana_Alt_Grup"))
				{
					sutun = 2 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Ana Grup"))
				{
					sutun = 1 ;
				}

				Row headerRow = sheet.createRow(1);
				for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
				{
					Cell bname = headerRow.createCell(q);
					if (q > sutun)
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
				for (int i =0;i< mdl.getRowCount() ;i++)
				{
					Row satirRow = sheet.createRow(i+2);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						if ( mdl.getValueAt(i, s) != null)
						{
							if (s == sutun +1 || s == sutun +2 ||s == sutun +5 || s == sutun +6)
							{
								hname.setCellStyle(satirStyle2);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
							}
							else if (s == sutun +3 )
							{
								hname.setCellStyle(satirStylemik);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));	
							}
							else if (s == sutun +4 )
							{
								hname.setCellStyle(satirStyle3);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
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
				for (int i=0; i<= mdl.getColumnCount()-1; i++){
					sheet.autoSizeColumn(i);
				}
				FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile()  + "_" + zaman + uzanti));
				workbook.write(out);
				out.close();
				//**************************************
			}
			GuiUtil.setWaitCursor(splitPane,false);
			JOptionPane.showMessageDialog(null, "Aktarma Islemi Tamamlandi.....","Ortalama Fiat Raporlama", JOptionPane.PLAIN_MESSAGE);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Excell Aktarma.....","Ortalama Fiat Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}

	@SuppressWarnings("resource")
	public static void  mail_at()
	{
		try {
			//************************************** XLXS *****************************************************
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Kereste_Grup_Raporlama");
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
			//
			int sutun = 0 ;
			if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Kodu"))
			{
				sutun = 1 ;
			}
			else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Sinif"))
			{
				sutun = 1 ;
			}
			else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Sinif-Kal"))
			{
				sutun = 1 ;
			}
			else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Sinif-Boy"))
			{
				sutun = 1 ;
			}
			else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Sinif-Gen"))
			{
				sutun = 1 ;
			}
			else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Yil"))
			{
				sutun = 0 ;
			}
			else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Yil-Ay"))
			{
				sutun = 1 ;
			}

			else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Hesap Kodu"))
			{
				sutun = 1 ;
			}
			else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Hesap Kodu-Ana_Alt_Grup"))
			{
				sutun = 2 ;
			}
			else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).equals("Ana Grup"))
			{
				sutun = 1 ;
			}

			//
			Row headerRow = sheet.createRow(1);
			for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
			{
				Cell bname = headerRow.createCell(q);
				if (q > sutun)
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
			for (int i =0;i< mdl.getRowCount() ;i++)
			{
				Row satirRow = sheet.createRow(i+2);
				for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
				{
					Cell hname = satirRow.createCell(s);
					if ( mdl.getValueAt(i, s) != null)
					{
						if (s == sutun +1 || s == sutun +2 ||s == sutun +5 || s == sutun +6)
						{
							hname.setCellStyle(satirStyle2);
							hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
						}
						else if (s == sutun +3 )
						{
							hname.setCellStyle(satirStylemik);
							hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));	
						}
						else if (s == sutun +4 )
						{
							hname.setCellStyle(satirStyle3);
							hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
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
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Excell Aktarma.....","Ortalama Fiat Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
}
