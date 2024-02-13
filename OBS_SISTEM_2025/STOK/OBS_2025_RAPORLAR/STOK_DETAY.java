package OBS_2025_RAPORLAR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.mail.util.ByteArrayDataSource;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
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
import OBS_C_2025.FILE_UZANTI;
import OBS_2025.FILTRE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_2025.GuiUtil;
import OBS_2025.OBS_MAIN;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

@SuppressWarnings({"serial" , "static-access","deprecation"})
public class STOK_DETAY extends JInternalFrame {
	
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);

	private static JTable table;
	private static String qwq1 ="" ;
	private static String qwq2  = "";
	private static String qwq3  = "";
	private static String qwq4  = "";
	private static String qwq5  = "";
	private static String qwq6  = "";
	private static String qwq7  = "";
	private static String kjk = "" ; 
	
	private static JLabel lbladet;
	public static JSplitPane splitPane ;

	public STOK_DETAY() {
		setTitle("STOK DETAY");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1100, 600);
		
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		splitPane.setLeftComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}
		table.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						table.requestFocusInWindow();
					}
				});
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});

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
							getContentPane().setCursor(oac.WAIT_CURSOR);
							OBS_MAIN.btnFiltre.doClick();
							getContentPane().setCursor(oac.DEFAULT_CURSOR);
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

	}
	public static void hisset()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			grup_cevir() ;
			
				rs = f_Access.stok_rapor(FILTRE.textField_25.getText(),FILTRE.textField_30.getText() ,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_18),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_19),
						FILTRE.textField_26.getText(),FILTRE.textField_29.getText() ,
						 qwq1, qwq2, qwq3, kjk, qwq4, qwq5, qwq6, qwq7,FILTRE.textField_80.getText(),FILTRE.textField_81.getText() );
			
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
				tc.setMaxWidth(90);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(170);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(200);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(60);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SOLA());
				tc.setCellRenderer(new TARIH());
				tc.setMinWidth(80);
				
				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(60);
				
				tc = tcm.getColumn(10);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(11);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(60);
				
				tc = tcm.getColumn(12);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(13);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(14);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(15);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(16);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(17);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(18);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
		
			lbladet.setText(FORMATLAMA.doub_0(mdl.getRowCount()));
			//**
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				
				int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				table.setRowSelectionInterval(lastRow, lastRow);
				
				//table.setSelectionBackground(Color.PINK);
				//table.setSelectionForeground(Color.BLUE);
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
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null,  ex.getMessage(), "Imalat Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void grup_cevir()
	{
		try {
		ResultSet	rs = null;
		//***********************ANA GRUP
		if ( FILTRE.comboBox_20.getItemAt(FILTRE.comboBox_20.getSelectedIndex()).equals(""))
		{
            qwq1 = " Like  '%' " ;
		}
        else if  ( FILTRE.comboBox_20.getItemAt(FILTRE.comboBox_20.getSelectedIndex()).equals("Bos Olanlar"))
        {
            qwq1 = " = '' " ;
        }
        else
        {
        	
    			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_20.getItemAt(FILTRE.comboBox_20.getSelectedIndex()));
    			if (!rs.isBeforeFirst() ) {
    			}
    			else
    			{
    				rs.next();
    				qwq1 =  "= " + Integer.toString( rs.getInt("AGID_Y") );
    			}
    		
        }
		//***********************ALT GRUP
				if ( FILTRE.comboBox_21.getItemAt(FILTRE.comboBox_21.getSelectedIndex()).equals(""))
				{
		            qwq2 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_21.getItemAt(FILTRE.comboBox_21.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq2 = " = '' " ;
		        }		        else		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_21.getItemAt(FILTRE.comboBox_21.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq2 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		
		        }
				//***********************DEPO
				if ( FILTRE.comboBox_22.getItemAt(FILTRE.comboBox_22.getSelectedIndex()).equals(""))
				{
		            qwq3 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_22.getItemAt(FILTRE.comboBox_22.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq3 = " = '' " ;
		        }		      
		        else		      
		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN", FILTRE.comboBox_22.getItemAt(FILTRE.comboBox_22.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq3 ="=" + Integer.toString( rs.getInt("DPID_Y"));
		    			}
		    		
		        }
				//** Urun Ana grup
				if ( FILTRE.comboBox_23.getItemAt(FILTRE.comboBox_23.getSelectedIndex()).equals(""))
				{
		            qwq6 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_23.getItemAt(FILTRE.comboBox_23.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq6 = " = '' " ;
		        }
		        else
		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_23.getItemAt(FILTRE.comboBox_23.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq6 = "=" + Integer.toString( rs.getInt("AGID_Y"));
		    			}
		    		
		        }
				//** Urun Alt Grup
				if ( FILTRE.comboBox_24.getItemAt(FILTRE.comboBox_24.getSelectedIndex()).equals(""))
				{
		            qwq7 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_24.getItemAt(FILTRE.comboBox_24.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq7 = " = '' " ;
		        }		        else		      
		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_24.getItemAt(FILTRE.comboBox_24.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq7 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		
		        }
				//***
				if (FILTRE.checkBox.isSelected())
	                    qwq4 = "E" ;
	                else
	                    qwq4 = "H";
				if (FILTRE.checkBox_1.isSelected())
	                    qwq5 = "E" ;
	                else
	                    qwq5 = "H";
	       //**
				if ( FILTRE.comboBox_25.getItemAt(FILTRE.comboBox_25.getSelectedIndex()).equals(""))
				                kjk = "" ;
				 else if ( FILTRE.comboBox_25.getItemAt(FILTRE.comboBox_25.getSelectedIndex()).equals("Giren"))
			                    kjk = "G";
			     else if ( FILTRE.comboBox_25.getItemAt(FILTRE.comboBox_25.getSelectedIndex()).equals("Cikan"))
			                    kjk = "C";
		} catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null,  ex.getMessage(), "Stok Detay", JOptionPane.ERROR_MESSAGE);
		} 
	}
	public static void excell_aktar()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		
		if (mdl.getRowCount() == 0 )
		{
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Aktarilacak Bilgi Yok.....");
		//JOptionPane.showMessageDialog(null, "Aktarilacak Bilgi Yok.....","Stok Detay", JOptionPane.PLAIN_MESSAGE);
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
	    { @SuppressWarnings("resource")
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
		   
		  File outputfile = new File("Stok_Detay");
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
					   HSSFSheet sheet = workbook.createSheet("Grup_Raporlama");
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
							 if (q == 8 || q ==10 || q == 12 || q == 13 || q == 14)
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
									   if (s== 8) 
									   {
										   hname.setCellStyle(satirStyle3);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									   else  if (s== 10) 
									   {
										   hname.setCellStyle(satirStyle2);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									   else  if (s== 12) 
									   {
										   hname.setCellStyle(satirStyle3);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									   else  if (s== 13) 
									   {
										   hname.setCellStyle(satirStyle2);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									   else  if (s== 14) 
									   {
										   hname.setCellStyle(satirStyle2);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									   else  if (s== 7) 
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
					   XSSFSheet sheet = workbook.createSheet("Grup_Raporlama");
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
							 if (q == 8 || q ==10 || q == 12 || q == 13 || q == 14)
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
									   if (s== 8) 
									   {
										   hname.setCellStyle(satirStyle3);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									   else  if (s== 10) 
									   {
										   hname.setCellStyle(satirStyle2);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									   else  if (s== 12) 
									   {
										   hname.setCellStyle(satirStyle3);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									   else  if (s== 13) 
									   {
										   hname.setCellStyle(satirStyle2);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									   else  if (s== 14) 
									   {
										   hname.setCellStyle(satirStyle2);
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
								
									   }
									   else  if (s== 7) 
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
				 Progres_Bar_Temizle();
		    		GuiUtil.setWaitCursor(splitPane,false);
		    		OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO,"Aktarma Islemi Tamamlandi.....");
			//JOptionPane.showMessageDialog(null, "Aktarma Islemi Tamamlandi.....","Stok Detay", JOptionPane.PLAIN_MESSAGE);
	  }
	  catch (Exception ex)
	  {
		  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null,  ex.getMessage(),"Excell Aktarma", JOptionPane.ERROR_MESSAGE);
	  }
	    }
	    };
	    //// Progress Bar
	    Thread t = new Thread(runner, "Code Executer");
	    t.start();
	    //
	  }
	@SuppressWarnings("resource")
	public static void mail_at() throws IOException
	{
		  //************************************** XLXS *****************************************************
		  XSSFWorkbook workbook = new XSSFWorkbook();
		   XSSFSheet sheet = workbook.createSheet("Stok_Detay");
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
				 if (q == 8 || q ==10 || q == 12 || q == 13 || q == 14)
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
						   if (s== 8) 
						   {
							   hname.setCellStyle(satirStyle3);
							   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
						   }
						   else  if (s== 10) 
						   {
							   hname.setCellStyle(satirStyle2);
							   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
						   }
						   else  if (s== 12) 
						   {
							   hname.setCellStyle(satirStyle3);
							   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
						   }
						   else  if (s== 13) 
						   {
							   hname.setCellStyle(satirStyle2);
							   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
						   }
						   else  if (s== 14) 
						   {
							   hname.setCellStyle(satirStyle2);
							   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
					
						   }
						   else  if (s== 7) 
						   {
						   DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
							  hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
							  hname.setCellStyle(satirStyle);
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
