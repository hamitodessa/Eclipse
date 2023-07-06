package OBS_2025_RAPORLAR;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.mail.util.ByteArrayDataSource;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;
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

import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.STOK_ACCESS;
import OBS_2025.FILTRE;
import OBS_2025.GuiUtil;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.FILE_UZANTI;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_2025.OBS_MAIN;

import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings({"serial" , "static-access"})
public class IMALAT_RAPORLAMA extends JInternalFrame {
	private static JTable table;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(oac._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	private static String qwq1 ="" ;
	private static String qwq2  = "";
	private static String qwq3  = "";
	private static String qwq6  = "";
	private static String qwq7  = "";
	public static JSplitPane splitPane;
	private static JLabel lbladet ;
	private static JLabel lblagirlik ;
	private static JLabel lblmiktar ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IMALAT_RAPORLAMA frame = new IMALAT_RAPORLAMA();
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
	public IMALAT_RAPORLAMA() {
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("IMALAT RAPORLAMA");
		setBounds(0, 0,1200,600);
		
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
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Kayit Sayisi :");
		label.setBounds(10, 5, 85, 14);
		panel.add(label);
		
		lbladet = new JLabel("0");
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setBounds(100, 5, 71, 14);
		panel.add(lbladet);
		
		lblagirlik = new JLabel("0.000");
		lblagirlik.setHorizontalAlignment(SwingConstants.RIGHT);
		lblagirlik.setForeground(new Color(0, 0, 128));
		lblagirlik.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblagirlik.setBounds(553, 5, 100, 14);
		panel.add(lblagirlik);
		
		lblmiktar = new JLabel("0.000");
		lblmiktar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblmiktar.setForeground(new Color(0, 0, 128));
		lblmiktar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblmiktar.setBounds(405, 5, 95, 14);
		panel.add(lblmiktar);

	}
	public static void hisset()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			grup_cevir() ;
			
				rs = f_Access.imalat_rapor(FILTRE.textField_14.getText(),FILTRE.textField_21.getText() ,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_14),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_15),
						FILTRE.textField_17.getText(),FILTRE.textField_23.getText() ,
						FILTRE.textField_27.getText(),FILTRE.textField_28.getText() ,
						 qwq3, qwq1, qwq2,
						 FILTRE.textField_16.getText(),FILTRE.textField_22.getText() ,
						qwq6,qwq7 );
			
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lblmiktar.setText(FORMATLAMA.doub_3(0));
				lblagirlik.setText(FORMATLAMA.doub_3(0));
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setCellRenderer(new TARIH());
				tc.setMinWidth(80);

				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(80);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(170);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(80);

				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(80);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(10);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
				tc = tcm.getColumn(11);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
				tc = tcm.getColumn(12);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
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
		
				//***
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				double miktar = 0,agirlik=0 ;
				for (int i = 0 ; i <= mdl.getRowCount()-1;i++)
				{
					 miktar  += (double) mdl.getValueAt(i , 4);
					 agirlik  += (double) mdl.getValueAt(i , 6);
				}
				lblmiktar.setText(FORMATLAMA.doub_3(miktar));
				lblagirlik.setText(FORMATLAMA.doub_3(agirlik));
				lbladet.setText(FORMATLAMA.doub_0(mdl.getRowCount()));
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
				
				String deger;
				String[] parts;
				Font bigFont;
					deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
					deger = deger.substring(1, deger.length()-1);
					parts = deger.split(",");
					bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
					table.setFont(bigFont);
				 
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Imalat Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void grup_cevir()
	{
		try {
		ResultSet	rs = null;
		//***********************ANA GRUP
		if ( FILTRE.comboBox_12.getItemAt(FILTRE.comboBox_12.getSelectedIndex()).equals(""))
		{
            qwq1 = " Like  '%' " ;
		}
        else if  ( FILTRE.comboBox_12.getItemAt(FILTRE.comboBox_12.getSelectedIndex()).equals("Bos Olanlar"))
        {
            qwq1 = " = '' " ;
        }
        else
        {
        	
    			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_12.getItemAt(FILTRE.comboBox_12.getSelectedIndex()));
    			if (!rs.isBeforeFirst() ) {
    			}
    			else
    			{
    				rs.next();
    				qwq1 =  "= " + Integer.toString( rs.getInt("AGID_Y") );
    			}
    		
        }
		//***********************ALT GRUP
				if ( FILTRE.comboBox_13.getItemAt(FILTRE.comboBox_13.getSelectedIndex()).equals(""))
				{
		            qwq2 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_13.getItemAt(FILTRE.comboBox_13.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq2 = " = '' " ;
		        }		        else		        {
		        	
		    			rs =f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_13.getItemAt(FILTRE.comboBox_13.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq2 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		
		        }
				//***********************DEPO
				if ( FILTRE.comboBox_11.getItemAt(FILTRE.comboBox_11.getSelectedIndex()).equals(""))
				{
		            qwq3 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_11.getItemAt(FILTRE.comboBox_11.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq3 = " = '' " ;
		        }		      
		        else		      
		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN", FILTRE.comboBox_11.getItemAt(FILTRE.comboBox_11.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq3 ="=" + Integer.toString( rs.getInt("DPID_Y"));
		    			}
		    		
		        }
				//** Urun Ana grup
				if ( FILTRE.comboBox_9.getItemAt(FILTRE.comboBox_9.getSelectedIndex()).equals(""))
				{
		            qwq6 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_9.getItemAt(FILTRE.comboBox_9.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq6 = " = '' " ;
		        }
		        else
		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_9.getItemAt(FILTRE.comboBox_9.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq6 = "=" + Integer.toString( rs.getInt("AGID_Y"));
		    			}
		    		
		        }
				//** Urun Alt Grup
				if ( FILTRE.comboBox_10.getItemAt(FILTRE.comboBox_10.getSelectedIndex()).equals(""))
				{
		            qwq7 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_10.getItemAt(FILTRE.comboBox_10.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq7 = " = '' " ;
		        }		        else		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_10.getItemAt(FILTRE.comboBox_10.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq7 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		
		        }
				
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(),"Imalat Raporlama", JOptionPane.ERROR_MESSAGE);
		} 
	}
	public static void excell_aktar()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		
		if (mdl.getRowCount() == 0 )
		{
		JOptionPane.showMessageDialog(null, "Aktarilacak Bilgi Yok.....","Stok Detay", JOptionPane.PLAIN_MESSAGE);
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
		   
		  File outputfile = new File("Imalat_Rapor");
		  fileChooser.setSelectedFile(outputfile);
		  int returnVal = fileChooser.showSaveDialog(null);
		  if ( returnVal != JFileChooser.APPROVE_OPTION )
		  {
			  return;
		  }
			GuiUtil.setWaitCursor(splitPane,true);
			  String uzanti ="";
			  File excelFile =  FILE_UZANTI. getSelectedFileWithExtension(fileChooser);
			 uzanti  = excelFile.getName().substring(excelFile.getName().lastIndexOf("."));
		    	  if  (uzanti.equals(".xls") )
		    	  {
		    		  HSSFWorkbook workbook = new HSSFWorkbook();
					   HSSFSheet sheet = workbook.createSheet("Imalat_Rapor");
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
						   
						   baslikname.setCellValue( BAGLAN.fatDizin.fIRMA_ADI );
						   baslikname.setCellStyle(acikStyle);

						 Row headerRow = sheet.createRow(1);
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							 Cell bname = headerRow.createCell(q);
							 if (q == 4 || q ==6 )
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
							 Row satirRow = sheet.createRow(i+2);
							for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
							{
								   Cell hname = satirRow.createCell(s);
								   if ( mdl.getValueAt(i, s) != null)
								   {
									   if (s== 4) 
									   {
										   hname.setCellStyle(satirStyle3);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									   else  if (s== 6) 
									   {
										   hname.setCellStyle(satirStyle2);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									
									   else  if (s== 1) 
									   {
									   DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
										  hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
										  hname.setCellStyle(solaStyle);
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
					   XSSFSheet sheet = workbook.createSheet("Imalat_Rapor");
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
					   XSSFCellStyle satirStyle3 = workbook.createCellStyle();
					   XSSFCellStyle satirStyle2 = workbook.createCellStyle();
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
						   
						   baslikname.setCellValue(BAGLAN.fatDizin.fIRMA_ADI );
						   baslikname.setCellStyle(acikStyle);
					 Row headerRow = sheet.createRow(1);
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							 Cell bname = headerRow.createCell(q);
							 if (q == 4 || q ==6)
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
									   if (s== 4) 
									   {
										   hname.setCellStyle(satirStyle3);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									   else  if (s== 6) 
									   {
										   hname.setCellStyle(satirStyle2);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									 
									   else  if (s== 1) 
									   {
									   DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
										  hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
										  hname.setCellStyle(solaStyle);
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
		    		GuiUtil.setWaitCursor(splitPane,false);
			JOptionPane.showMessageDialog(null, "Aktarma Islemi Tamamlandi.....","Imalat Rapor", JOptionPane.PLAIN_MESSAGE);
	  }
	  catch (Exception ex)
	  {
			JOptionPane.showMessageDialog(null,  ex.getMessage(),"Excell Aktarma", JOptionPane.ERROR_MESSAGE);
	  }
	    }
	
	@SuppressWarnings("resource")
	public static void mail_at() throws IOException 
	{
	
		  //************************************** XLXS *****************************************************
		  XSSFWorkbook workbook = new XSSFWorkbook();
		   XSSFSheet sheet = workbook.createSheet("Imalat_Rapor");
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
		   XSSFCellStyle satirStyle3 = workbook.createCellStyle();
		   XSSFCellStyle satirStyle2 = workbook.createCellStyle();
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
			   
			   baslikname.setCellValue(BAGLAN.fatDizin.fIRMA_ADI );
			   baslikname.setCellStyle(acikStyle);
		 Row headerRow = sheet.createRow(1);
			for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
			{
				 Cell bname = headerRow.createCell(q);
				 if (q == 4 || q ==6)
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
						   if (s== 4) 
						   {
							   hname.setCellStyle(satirStyle3);
							   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
						   }
						   else  if (s== 6) 
						   {
							   hname.setCellStyle(satirStyle2);
							   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
						   }
						 
						   else  if (s== 1) 
						   {
						   DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
							  hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
							  hname.setCellStyle(solaStyle);
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

}
