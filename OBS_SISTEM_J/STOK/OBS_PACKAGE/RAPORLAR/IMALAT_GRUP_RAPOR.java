package OBS_PACKAGE.RAPORLAR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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

import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat;

import OBS_PACKAGE.CONNECTION;
import OBS_PACKAGE.FILE_UZANTI;
import OBS_PACKAGE.FILTRE;
import OBS_PACKAGE.FORMATLAMA;
import OBS_PACKAGE.GLOBAL;
import OBS_PACKAGE.GRID_TEMIZLE;
import OBS_PACKAGE.GuiUtil;
import OBS_PACKAGE.OBS_MAIN;
import OBS_PACKAGE.OBS_SIS_ANA_CLAS;
import OBS_PACKAGE.PRINT_YAPMA;
import OBS_PACKAGE.SAGA;
import OBS_PACKAGE.SOLA;
import OBS_PACKAGE.TABLO_RENDERER;
import OBS_PACKAGE.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import net.sourceforge.chart2d.Dataset;

public class IMALAT_GRUP_RAPOR extends JInternalFrame {
	
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	private static JTable table;
	private static String qwq6  = "";
	private static String qwq7  = "";
	private static String qwq8  = "";
	private static String qwq9  = "";
	static String sstr_1 = "" ;
	static String sstr_2 = "" ;
	static String sstr_4 = "" ;
	static String sstr_5 = "" ;
	static String sstr_55 = "" ;
	private static  String  jkj  = "" ;
	private static String ch1 ="";
	private static 	long startTime;
	private static int kusur = 0 ;
	private static JLabel lbladet;
	public static JSplitPane splitPane ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IMALAT_GRUP_RAPOR frame = new IMALAT_GRUP_RAPOR();
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
	public IMALAT_GRUP_RAPOR() {
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setTitle("IMALAT GRUP RAPOR");
		setBounds(0, 0,1100, 600);
		
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
		
		JLabel lblNewLabel = new JLabel("Satir Sayisi :");
		lblNewLabel.setBounds(10, 7, 71, 14);
		panel.add(lblNewLabel);
		
		lbladet = new JLabel("0");
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setBounds(91, 7, 71, 14);
		panel.add(lbladet);

	}
	public static void yenile ()
	{
		 try
		 {
		 		GRID_TEMIZLE.grid_temizle(table);
		 		
		 		lbladet.setText(FORMATLAMA.doub_0(0));
				 if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Urun Kodu"))
					{
					 	baslik_bak();
					 	if (! sstr_1.equals(""))
						 urun_kodlu();
					}
				 else   if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Depo"))
					{
					 	baslik_bak();
					 	if (! sstr_1.equals(""))
						depo_kodlu();
					}
				 else   if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Yil"))
					{
					 	baslik_bak();
					 	if (! sstr_1.equals(""))
					   yil_kodlu();
					}
				 else  if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Ana_Grup"))
					{
						baslik_bak();
					 	if (! sstr_1.equals(""))
						ana_grp();
					}
				 else  if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Alt_Grup"))
					{
						baslik_bak();
					 	if (! sstr_1.equals(""))
						alt_grp();
					}
				 else   if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Alt_Grup_Yil"))
					{
					 	baslik_bak();
					 	if (! sstr_1.equals(""))
						 alt_grp_yil();
					}
				 else if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Alt_Grup_Yil_Ay"))
					{
					 	baslik_bak();
					 	if (! sstr_1.equals(""))
						 alt_grp_yil_ay();
					}
				 else  if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Yil_Ay"))
					{
					 	baslik_bak();
					 	if (! sstr_1.equals(""))
						 yil_ay();
					}
		 }
     catch (Exception ex)
		 {
    	 GRID_TEMIZLE.grid_temizle(table);
    		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Raporlama",JOptionPane.ERROR_MESSAGE);
    	}
	}
	private static void urun_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			deg_cevir();
 			//grup_cevir() ;
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
			{
				sstr_55= sstr_5 ;
				sstr_5 = ",'" + sstr_5 + "' as Birim," ;
				rs = oac.sTOK_MSSQL.ima_alt_kod("MAL.Kodu as Urun_Kodu, Adi as Urun_Adi",sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1,"Urun_Kodu",sstr_55);
			}
			else
			{
				sstr_55= sstr_5 ;
				sstr_5 = ",'" + sstr_5 + "' as Birim," ;
				rs = oac.sTOK_MYSQL.ima_alt_kod("MAL.Kodu as Urun_Kodu, Adi as Urun_Adi",sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1,"Urun_Kodu",sstr_55);
			}
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				mdll.addColumn("Toplam");
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(231);
				tc.setMaxWidth(231);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
								
				kusurr();
				for (int i = 3;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				//**
				topla(3);
				//**
				alt_bolum();
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Urun Kodlu Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void depo_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			 deg_cevir();
 			//grup_cevir() ;
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
			{
				sstr_55= sstr_5 ;
				sstr_5 = ",'" + sstr_5 + "' as Birim," ;
				rs = oac.sTOK_MSSQL.ima_alt_kod("(SELECT DISTINCT  DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = STOK.Depo ) as Depo",sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1," Depo",sstr_55);
			}
			else
			{
				sstr_55= sstr_5 ;
				sstr_5 = ",'" + sstr_5 + "' as Birim," ;
				rs = oac.sTOK_MYSQL.ima_alt_kod("(SELECT DISTINCT  DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = STOK.Depo ) as Depo",sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1," Depo",sstr_55);
			}
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				 
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(60);
				tc.setMaxWidth(60);
				
				kusurr();
				for (int i = 2;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				
				//**
				topla(2);
				//**
				alt_bolum();
				
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Depo Kodlu Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void yil_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			 deg_cevir();
 			//grup_cevir() ;
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
			{
				sstr_55= sstr_5 ;
				sstr_5 = "" ;
				rs = oac.sTOK_MSSQL.ima_alt_kod("datepart(yyyy,STOK.Tarih) as Yil,",sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1," Yil",sstr_55);
			}
			else
			{
				sstr_55= sstr_5 ;
				sstr_5 = "" ;
				rs = oac.sTOK_MYSQL.ima_alt_kod("datepart(yyyy,STOK.Tarih) as Yil,",sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1," Yil",sstr_55);
			}
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				 
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				
				kusurr();
				for (int i = 1;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				
				//**
				topla(1);
				//**
				alt_bolum();
				
				fontt();
			}
		
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Yil Kodlu Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void ana_grp() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			 deg_cevir();
 			//grup_cevir() ;
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
			{
				sstr_55= sstr_5 ;
				sstr_5 = ",'" + sstr_5 + "' as Birim," ;
				rs = oac.sTOK_MSSQL.ima_alt_kod("(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = STOK.Ana_Grup ) as Ana_Grup  "
						,sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1," Ana_Grup",sstr_55);
			}
			else
			{
				rs = oac.sTOK_MYSQL.ima_alt_kod("(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = STOK.Ana_Grup ) as Ana_Grup , "
						+ " (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID = STOK.Alt_Grup ) as Alt_Grup " 
						,sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1," Ana_Grup",sstr_55);
			}
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				 
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
			
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				tc.setMaxWidth(50);
				
				kusurr();
				for (int i = 2;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				
				//**
				topla(2);
				//**
				alt_bolum();
				
				fontt();
			}
		
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Yil Kodlu Raporlama",JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void alt_grp() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			 deg_cevir();
 			//grup_cevir() ;
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
			{
				sstr_55= sstr_5 ;
				sstr_5 = ",'" + sstr_5 + "' as Birim," ;
				rs = oac.sTOK_MSSQL.ima_alt_kod("(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = STOK.Ana_Grup ) as Ana_Grup , "
						+ " (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID = STOK.Alt_Grup ) as Alt_Grup " 
						,sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1," Ana_Grup",sstr_55);
			}
			else
			{
				rs = oac.sTOK_MYSQL.ima_alt_kod("(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = STOK.Ana_Grup ) as Ana_Grup , "
						+ " (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID = STOK.Alt_Grup ) as Alt_Grup " 
						,sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1," Ana_Grup",sstr_55);
			}
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				 
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				tc.setMaxWidth(150);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				tc.setMaxWidth(50);
				
				kusurr();
				for (int i = 3;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				
				//**
				topla(3);
				//**
				alt_bolum();
				
				fontt();
			}
		
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Yil Kodlu Raporlama",JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void alt_grp_yil() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			 deg_cevir();
 			//grup_cevir() ;
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
			{
				sstr_55= sstr_5 ;
				sstr_5 = ",'" + sstr_5 + "' as Birim," ;
				rs = oac.sTOK_MSSQL.ima_alt_kod("(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID = STOK.Ana_Grup ) as Ana_Grup  ,"  + 
						" (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID = STOK.Alt_Grup ) as Alt_Grup , "  + 
						"  datepart(yyyy,STOK.Tarih) as Yil",sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1," Yil,Ana_Grup ,Alt_Grup",sstr_55);
			}
			else
			{
				sstr_55= sstr_5 ;
				sstr_5 = ",'" + sstr_5 + "' as Birim," ;
				rs = oac.sTOK_MYSQL.ima_alt_kod("(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID = STOK.Ana_Grup ) as Ana_Grup  ,"  + 
						" (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID = STOK.Alt_Grup ) as Alt_Grup , "  + 
						"  datepart(yyyy,STOK.Tarih) as Yil",sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1," Yil,Ana_Grup ,Alt_Grup",sstr_55);
			}
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				 
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(231);
				tc.setMaxWidth(231);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
				
				kusurr();
				for (int i = 4;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				
				//**
				topla(4);
				//**
				alt_bolum();
				
				fontt();
			}
		
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Yil Kodlu Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void alt_grp_yil_ay() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			 deg_cevir();
 			//grup_cevir() ;
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
			{
				sstr_55= sstr_5 ;
				sstr_5 = ",'" + sstr_5 + "' as Birim," ;
				rs = oac.sTOK_MSSQL.ima_alt_kod("(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID = STOK.Ana_Grup ) as Ana_Grup  ,"  + 
						" (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID = STOK.Alt_Grup ) as Alt_Grup , "  + 
						" format (stok.tarih,'yyyy / MM') as Yil_Ay",sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1," Yil_Ay,Ana_Grup ,Alt_Grup",sstr_55);
			}
			else
			{
				rs = oac.sTOK_MYSQL.ima_alt_kod("(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID = STOK.Ana_Grup ) as Ana_Grup  ,"  + 
						" (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID = STOK.Alt_Grup ) as Alt_Grup , "  + 
						" format (stok.tarih,'yyyy / MM') as Yil_Ay",sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1," Yil_Ay,Ana_Grup ,Alt_Grup",sstr_55);
			}
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				 
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(200);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(200);
				tc.setMaxWidth(200);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
				
				kusurr();
				for (int i = 4;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				
				//**
				topla(4);
				//**
				alt_bolum();
				
				fontt();
			}
		
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Yil Kodlu Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void yil_ay() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			deg_cevir();
 			//grup_cevir() ;
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
			{
				sstr_55= sstr_5 ;
				sstr_5 = "" ;
				rs = oac.sTOK_MSSQL.ima_alt_kod(" datepart(yyyy,STOK.Tarih) as Yil ,datepart(mm,STOK.Tarih) as Ay,",sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						 sstr_1," Yil,Ay",sstr_55);
			}
			else
			{
				sstr_55= sstr_5 ;
				sstr_5 = "" ;
				rs = oac.sTOK_MYSQL.ima_alt_kod("datepart(yyyy,STOK.Tarih) as Yil ,datepart(mm,STOK.Tarih) as Ay,",sstr_5,sstr_2,sstr_4, jkj, ch1,  qwq6,  qwq7,  qwq8,qwq9,
						FILTRE.textField_42.getText(), FILTRE.textField_43.getText(),
						FILTRE.textField_39.getText(),FILTRE.textField_40.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
						sstr_1," Yil,Ay",sstr_55);
			}
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				 
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(80);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				tc.setMaxWidth(50);
				kusurr();
				for (int i = 2;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				
				//**
				topla(2);
				//**
				alt_bolum();
				fontt();
			}
		
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Yil Ay Kodlu Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void baslik_bak()
	{
	 try {
	   grup_cevir();
	     ResultSet rs= null ;
	     if (FILTRE.comboBox_36.getItemAt(FILTRE.comboBox_36.getSelectedIndex()).equals("Yil"))
	     {
	    	 if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
				{
	    			rs = oac.sTOK_MSSQL.ima_baslik_bak("DISTINCT datepart(yyyy,STOK.Tarih)",jkj,ch1,
	    					qwq6,qwq7,qwq8,qwq9,FILTRE.textField_39.getText(),FILTRE.textField_40.getText() ,
	    					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
	    					"order by datepart(yyyy,STOK.Tarih)");
				}
	    	 else
	    	 {
	    		 rs = oac.sTOK_MYSQL.ima_baslik_bak("DISTINCT datepart(yyyy,STOK.Tarih)",jkj,ch1,
	    					qwq6,qwq7,qwq8,qwq9,FILTRE.textField_39.getText(),FILTRE.textField_40.getText() ,
	    					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
	    					"order by datepart(yyyy,STOK.Tarih)");
	    	 }
	           sstr_2 =" datepart(yyyy,STOK.Tarih)" ;
	     }
	     else if (FILTRE.comboBox_36.getItemAt(FILTRE.comboBox_36.getSelectedIndex()).equals("Ay"))
	     {
	    	 if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
				{
	    			rs = oac.sTOK_MSSQL.ima_baslik_bak("DISTINCT datepart(mm,STOK.Tarih)",jkj,ch1,
	    					qwq6,qwq7,qwq8,qwq9,FILTRE.textField_39.getText(),FILTRE.textField_40.getText() ,
	    					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
	    					"order by datepart(mm,STOK.Tarih)");
				}
	    	 else
	    	 {
	    		 rs = oac.sTOK_MYSQL.ima_baslik_bak("DISTINCT datepart(mm,STOK.Tarih)",jkj,ch1,
	    					qwq6,qwq7,qwq8,qwq9,FILTRE.textField_39.getText(),FILTRE.textField_40.getText() ,
	    					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
	    					"order by datepart(mm,STOK.Tarih)");
	    	 }
	           sstr_2 = "datepart(mm,STOK.Tarih)";
	     }
	     else if (FILTRE.comboBox_36.getItemAt(FILTRE.comboBox_36.getSelectedIndex()).equals("Gun"))
	     {
	    	 if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
				{
	    			rs = oac.sTOK_MSSQL.ima_baslik_bak("DISTINCT datepart(dd,STOK.Tarih)",jkj,ch1,
	    					qwq6,qwq7,qwq8,qwq9,FILTRE.textField_39.getText(),FILTRE.textField_40.getText() ,
	    					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
	    					"order by datepart(dd,STOK.Tarih)");
				}
	    	 else
	    	 {
	    		 rs = oac.sTOK_MYSQL.ima_baslik_bak("DISTINCT datepart(dd,STOK.Tarih)",jkj,ch1,
	    					qwq6,qwq7,qwq8,qwq9,FILTRE.textField_39.getText(),FILTRE.textField_40.getText() ,
	    					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
	    					"order by datepart(dd,STOK.Tarih)");
	    	 }
	           sstr_2 = "datepart(dd,STOK.Tarih)";
	     }
	     else if (FILTRE.comboBox_36.getItemAt(FILTRE.comboBox_36.getSelectedIndex()).equals("Depo"))
	     {
	    	 if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
				{
	    			rs = oac.sTOK_MSSQL.ima_baslik_bak("DISTINCT ISNULL((SELECT DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID = STOK.Depo),'---') as Depo",jkj,ch1,
	    					qwq6,qwq7,qwq8,qwq9,FILTRE.textField_39.getText(),FILTRE.textField_40.getText() ,
	    					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
	    					" order by Depo ");
				}
	    	 else
	    	 {
	    		 rs = oac.sTOK_MYSQL.ima_baslik_bak("DISTINCT ISNULL((SELECT DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = STOK.Depo),'---') as Depo",jkj,ch1,
	    					qwq6,qwq7,qwq8,qwq9,FILTRE.textField_39.getText(),FILTRE.textField_40.getText() ,
	    					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
	    					" order by Depo ");
	    	 }
	           sstr_2 = " ISNULL((SELECT DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y= STOK.Depo) ,'---') ";
	     }
	     else if (FILTRE.comboBox_36.getItemAt(FILTRE.comboBox_36.getSelectedIndex()).equals("Ana Grup"))
	     {
	    	 if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
				{
	    			rs = oac.sTOK_MSSQL.ima_baslik_bak("DISTINCT ISNULL((SELECT ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = STOK.Ana_Grup),'---') as Ana_Grup",jkj,ch1,
	    					qwq6,qwq7,qwq8,qwq9,FILTRE.textField_39.getText(),FILTRE.textField_40.getText() ,
	    					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
	    					"  order by Ana_Grup  ");
				}
	    	 else
	    	 {
	    		 rs = oac.sTOK_MYSQL.ima_baslik_bak("DISTINCT ISNULL((SELECT ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = STOK.Ana_Grup),'---') as Ana_Grup",jkj,ch1,
	    					qwq6,qwq7,qwq8,qwq9,FILTRE.textField_39.getText(),FILTRE.textField_40.getText() ,
	    					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_22),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_23),
	    					"  order by Ana_Grup  ");
	    	 }
	           sstr_2 = "  ISNULL((SELECT ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = STOK.Ana_Grup),'---')  ";
	     }
	        sstr_1 = "";
        String text = "" ;
	       while (rs.next())
	       {
	                	 text = text +  "[" + rs.getString(1).toString() + "]" + " , " ;
	        }
	          sstr_1 =  text.equals("") ?   "":    text.substring(0, text.length() - 2);
	 }
     catch (Exception ex)
		 {
    		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Imalat Grup Raporlama", JOptionPane.ERROR_MESSAGE);
    	}
	}
	private static void grup_cevir() 
	{
		try {
		ResultSet	rs = null;
		  if (FILTRE.comboBox_41.getItemAt(FILTRE.comboBox_41.getSelectedIndex()).equals("Giren"))
		     {
		    	 jkj = " STOK.Hareket = 'G'  " ;
		     }
		     else
		     {
		    	 jkj = " STOK.Hareket = 'C'  " ;
		     }
		     ch1 = " Evrak_Cins = 'URE' " ;
				//** Urun Ana grup
				if ( FILTRE.comboBox_38.getItemAt(FILTRE.comboBox_38.getSelectedIndex()).equals(""))
				{
		            qwq6 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_38.getItemAt(FILTRE.comboBox_38.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq6 = " = '' " ;
		        }
		        else
		        {
		        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		    		{
		    			rs = oac.sTOK_MSSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_38.getItemAt(FILTRE.comboBox_38.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq6 = "=" + Integer.toString( rs.getInt("AGID_Y"));
		    			}
		    		}
		    		else
		    		{
		    			rs = oac.sTOK_MYSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_38.getItemAt(FILTRE.comboBox_38.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    			rs.next();
		    			qwq6 = "=" + Integer.toString(rs.getInt("AGID_Y"));
		    			}
		    		}
		        }
				//** Urun Alt Grup
				if ( FILTRE.comboBox_37.getItemAt(FILTRE.comboBox_37.getSelectedIndex()).equals(""))
				{
		            qwq7 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_37.getItemAt(FILTRE.comboBox_37.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq7 = " = '' " ;
		        }		        else		      
		        {
		        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		    		{
		    			rs = oac.sTOK_MSSQL.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_37.getItemAt(FILTRE.comboBox_37.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq7 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		}
		    		else
		    		{
		    			rs = oac.sTOK_MYSQL.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_37.getItemAt(FILTRE.comboBox_37.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    			rs.next();
		    			qwq7 ="=" + Integer.toString(rs.getInt("ALID_Y"));
		    			}
		    		}
		        }
				//**  Ana grup
				if ( FILTRE.comboBox_40.getItemAt(FILTRE.comboBox_40.getSelectedIndex()).equals(""))
				{
		            qwq8 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_40.getItemAt(FILTRE.comboBox_40.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq8 = " = '' " ;
		        }
		        else
		        {
		        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		    		{
		    			rs = oac.sTOK_MSSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_40.getItemAt(FILTRE.comboBox_40.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq8 = "=" + Integer.toString( rs.getInt("AGID_Y"));
		    			}
		    		}
		    		else
		    		{
		    			rs = oac.sTOK_MYSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_40.getItemAt(FILTRE.comboBox_40.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    			rs.next();
		    			qwq8 = "=" + Integer.toString(rs.getInt("AGID_Y"));
		    			}
		    		}
		        }
				//**  Alt Grup
				if ( FILTRE.comboBox_39.getItemAt(FILTRE.comboBox_39.getSelectedIndex()).equals(""))
				{
		            qwq9 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_39.getItemAt(FILTRE.comboBox_39.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq9 = " = '' " ;
		        }		        else		      
		        {
		        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		    		{
		    			rs = oac.sTOK_MSSQL.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_39.getItemAt(FILTRE.comboBox_39.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq9 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		}
		    		else
		    		{
		    			rs = oac.sTOK_MYSQL.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_39.getItemAt(FILTRE.comboBox_39.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    			rs.next();
		    			qwq9 ="=" + Integer.toString(rs.getInt("ALID_Y"));
		    			}
		    		}
		        }
		} 
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Imalat Grup Raporlama", JOptionPane.PLAIN_MESSAGE);
		} 
	}
	private static void kusurr()
	{
		if (FILTRE.comboBox_34.getItemAt(FILTRE.comboBox_34.getSelectedIndex()).equals("Agirlik"))
		{
			kusur = 3 ;
		}
		else if (FILTRE.comboBox_34.getItemAt(FILTRE.comboBox_34.getSelectedIndex()).equals("Tutar"))
		{
			kusur = 2 ;
		} 
		if (FILTRE.comboBox_34.getItemAt(FILTRE.comboBox_34.getSelectedIndex()).equals("Miktar"))
		{
			kusur = 3 ;
		}
	}
	private static void fontt()
	{
		String deger;
		String[] parts;
		Font bigFont;
			try {
				deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
				deger = deger.substring(1, deger.length()-1);
				parts = deger.split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				table.setFont(bigFont);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	private static void alt_bolum()
	{
		 int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
		lbladet.setText(FORMATLAMA.doub_0(lastRow));
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		table.setRowSelectionInterval(lastRow, lastRow);
		table.setSelectionBackground(Color.PINK);
		table.setSelectionForeground(Color.BLUE);
		long endTime = System.currentTimeMillis();
		long estimatedTime = endTime - startTime;
		double seconds = (double)estimatedTime/1000; 
		OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
	}
	private static void topla(int aa)
	{
		double top = 0;
		 DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		 for (int w = 0; w <= mdl.getRowCount()-1;w++)	
		 {
			 top=0 ;
			 for (int a = aa;a<=table.getColumnCount() -2;a++)
			 {
				 if( mdl.getValueAt(w,a) != null)
					{
					
					top += Double.parseDouble( mdl.getValueAt(w,a).toString());
					}
			 }
			 mdl.setValueAt(top,w, mdl.getColumnCount()-1) ;
		 }
		 //**************************************************************
		 mdl.addRow(new Object[]{});
		 top = 0 ;
		 for (int i = aa;i<=table.getColumnCount() -1;i++)
			{
			for (int q = 0 ;q <= mdl.getRowCount()-2;q++)	
			{
			if( mdl.getValueAt(q,i) != null)
				{
				top += Double.parseDouble( mdl.getValueAt(q,i).toString());
				}
			}
			mdl.setValueAt(top,mdl.getRowCount()-1, i) ;
			top = 0 ;
			}
	}
	private static void deg_cevir()
	{
	    if (FILTRE.comboBox_34.getItemAt(FILTRE.comboBox_34.getSelectedIndex()).equals("Tutar"))
	       {
	    	 sstr_4 = " ABS(STOK.Tutar) as Tutar" ;
	         sstr_5 = "Tutar" ;
	       }
			else  if (FILTRE.comboBox_34.getItemAt(FILTRE.comboBox_34.getSelectedIndex()).equals("Miktar"))
			{
				 sstr_4 = " ABS(STOK.Miktar) as Miktar" ;
			     sstr_5 = "Miktar";
			}
	  		else  if (FILTRE.comboBox_34.getItemAt(FILTRE.comboBox_34.getSelectedIndex()).equals("Agirlik"))
	  		{
	  			 sstr_4 = " (ABS(STOK.Miktar) * MAL.Agirlik)  as Agirlik" ;
	  	         sstr_5 = "Agirlik";
	  		}
	}
	public static void grafik()
	{
DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		
		if (mdl.getRowCount() == 0 )
		{
		JOptionPane.showMessageDialog(null, "Aktarilacak Bilgi Yok.....","Imalat Grup Raporlama", JOptionPane.PLAIN_MESSAGE);
		GLOBAL.g_baslik = "";
		return;
		}
		
		if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Yil"))
		{
			GLOBAL.g_baslik = "IMALAT GRUP RAPORLAMA YIL";
			//***LEGENDS
			DefaultTableModel mdll = (DefaultTableModel) table.getModel();
			GLOBAL.g_legends = new String[mdll.getRowCount() -1];
			for (int i = 0;i<=mdll.getRowCount() -2 ;i++)
			{
				GLOBAL.g_legends[i] = mdll.getValueAt(i,0).toString() ;
			}
			//*** g_labelsAxisLabels
			GLOBAL.g_labelsAxisLabels = new String[mdll.getColumnCount() - 2];
			for (int i = 1;i<=mdll.getColumnCount() -2;i++)
			{
				GLOBAL.g_labelsAxisLabels[i-1] =  mdll.getColumnName(i).toString();
			}
			//*** g_LabelsAxisTitleText
			GLOBAL.g_LabelsAxisTitleText = FILTRE.comboBox_36.getItemAt(FILTRE.comboBox_36.getSelectedIndex()) ;
			//*** g_setNumbersAxisTitleText
			GLOBAL.g_setNumbersAxisTitleText = FILTRE.comboBox_34.getItemAt(FILTRE.comboBox_34.getSelectedIndex()) ;
			//*** Dataset
			GLOBAL.g_dataSet = new Dataset (mdll.getRowCount() -1,mdll.getColumnCount() -2,1);
			 for (int i= 0 ;i<=mdll.getRowCount() -2 ;i++)
			 {
				 for (int y = 1;y<=mdll.getColumnCount() -2;y++)
				 {
						double asd =  mdll.getValueAt(i,y) == null ? 0: Double.parseDouble( mdll.getValueAt(i,y).toString());
						GLOBAL.g_dataSet.set (i,  y-1, 0,(float) asd )  ; 
				 }
			 }
		} 
		
	}
	public static void excell_aktar()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		
		if (mdl.getRowCount() == 0 )
		{
		JOptionPane.showMessageDialog(null, "Aktarilacak Bilgi Yok.....","Imalat Grup Raporlama", JOptionPane.PLAIN_MESSAGE);
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
		  
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");  
		   LocalDateTime now = LocalDateTime.now();  
		   String zaman = dtf.format(now)  ;
		   
		  File outputfile = new File("Imalat_Grup_Rapor");
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
					   HSSFSheet sheet = workbook.createSheet("Imalat_Grup_Raporlama");
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
					   HSSFCellStyle satirStyle3 = workbook.createCellStyle();
					   HSSFCellStyle satirStyle2 = workbook.createCellStyle();
					   HSSFFont satirFont = workbook.createFont();
					   satirFont.setFontName("Arial Narrow");
					   satirFont. setFontHeight((short)(10*20));
					   satirStyle.setFont(satirFont);
					   satirStyle.setAlignment(HorizontalAlignment.RIGHT);
					   
					   satirStyle3.setFont(satirFont);
					   satirStyle2.setFont(satirFont);
					   satirStyle3.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
					   satirStyle2.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
					   satirStyle3.setAlignment(HorizontalAlignment.RIGHT);
					   satirStyle2.setAlignment(HorizontalAlignment.RIGHT);
					   
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
						   
						   baslikname.setCellValue( CONNECTION.fatdizinbilgi.firma_adi );
						   baslikname.setCellStyle(acikStyle);
						   //
							int sutun = 0 ;
							if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Yil"))
							{
								sutun = 0 ;
							}
							else if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Urun Kodu"))
							{
								sutun = 2 ;
							}
							else if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Yil_Ay"))
							{
								sutun = 1 ;
							}
							else if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Alt_Grup"))
							{
								sutun = 1 ;
							}
							else if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Alt_Grup_Yil"))
							{
								sutun = 3 ;
							}
							else if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Alt_Grup_Yil_Ay"))
							{
								sutun = 3 ;
							}
							else if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Depo"))
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
									   ////////////
									   if (s > sutun)
									   {
										   if (FILTRE.comboBox_34.getItemAt(FILTRE.comboBox_34.getSelectedIndex()).equals("Tutar"))
									  		 {
											   hname.setCellStyle(satirStyle2);
									  		 }
										   else  if (FILTRE.comboBox_34.getItemAt(FILTRE.comboBox_34.getSelectedIndex()).equals("Miktar"))
										  	 {
											   hname.setCellStyle(satirStyle3);
										  	 }
											else  if (FILTRE.comboBox_34.getItemAt(FILTRE.comboBox_34.getSelectedIndex()).equals("Agirlik"))
											{
												   hname.setCellStyle(satirStyle3);
											 }
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
								
								
									   }
									   else
									   {
										   hname.setCellValue( mdl.getValueAt(i,s).toString());
										   hname.setCellStyle(solaStyle); 
									   }
									   ///////////
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
					   //
					   FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile() + "_" + zaman + uzanti));
					   workbook.write(out);
					   out.close();
		    	  }
		    	  else
		    	  {
		    		  //************************************** XLXS *****************************************************
		    		  XSSFWorkbook workbook = new XSSFWorkbook();
					   XSSFSheet sheet = workbook.createSheet("Imalat_Grup_Raporlama");
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
					   XSSFCellStyle satirStyle2 = workbook.createCellStyle();
					   XSSFCellStyle satirStyle3 = workbook.createCellStyle();
					   XSSFFont satirFont = workbook.createFont();
					   satirFont.setFontName("Arial Narrow");
					   satirFont. setFontHeight((short)(10*20));
					   satirStyle.setFont(satirFont);
					   satirStyle.setAlignment(HorizontalAlignment.RIGHT);
					   
					   satirStyle3.setFont(satirFont);
					   satirStyle2.setFont(satirFont);
					   satirStyle3.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
					   satirStyle2.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
					   satirStyle3.setAlignment(HorizontalAlignment.RIGHT);
					   satirStyle2.setAlignment(HorizontalAlignment.RIGHT);
					   
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
						   
						   baslikname.setCellValue( CONNECTION.fatdizinbilgi.firma_adi );
						   baslikname.setCellStyle(acikStyle);
						   //
							int sutun = 0 ;
							if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Yil"))
							{
								sutun = 0 ;
							}
							else if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Urun Kodu"))
							{
								sutun = 2 ;
							}
							else if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Yil_Ay"))
							{
								sutun = 1 ;
							}
							else if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Alt_Grup"))
							{
								sutun = 1 ;
							}
							else if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Alt_Grup_Yil"))
							{
								sutun = 3 ;
							}
							else if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Alt_Grup_Yil_Ay"))
							{
								sutun = 3 ;
							}
							else if (FILTRE.comboBox_35.getItemAt(FILTRE.comboBox_35.getSelectedIndex()).equals("Depo"))
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
									   ////////////
									   if (s > sutun)
									   {
										   if (FILTRE.comboBox_34.getItemAt(FILTRE.comboBox_34.getSelectedIndex()).equals("Tutar"))
									  		 {
											   hname.setCellStyle(satirStyle2);
									  		 }
										   else  if (FILTRE.comboBox_34.getItemAt(FILTRE.comboBox_34.getSelectedIndex()).equals("Miktar"))
										  	 {
											   hname.setCellStyle(satirStyle3);
										  	 }
											else  if (FILTRE.comboBox_34.getItemAt(FILTRE.comboBox_34.getSelectedIndex()).equals("Agirlik"))
											{
												   hname.setCellStyle(satirStyle3);
											 }
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
							
									   }
									   else
									   {
										   hname.setCellValue( mdl.getValueAt(i,s).toString());
										   hname.setCellStyle(solaStyle); 
									   }
									
									   ///////////
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
					   //
					   FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile()  + "_" + zaman + uzanti));
					    workbook.write(out);
					   out.close();
		    		  //**************************************
		    	  }
		    		GuiUtil.setWaitCursor(splitPane,false);
			JOptionPane.showMessageDialog(null, "Aktarma Islemi Tamamlandi.....","Imalat Grup Raporlama", JOptionPane.PLAIN_MESSAGE);
	  }
	  catch (Exception ex)
	  {
			JOptionPane.showMessageDialog(null, "Excell Aktarma.....","Imalat Grup Raporlama", JOptionPane.PLAIN_MESSAGE);
	  }
	  }
	
}

