package OBS_2025_RAPORLAR;

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
import javax.swing.ImageIcon;
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

import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.FILE_UZANTI;
import OBS_C_2025.FIT_IMAGE;
import OBS_2025.FILTRE;
import OBS_2025.GuiUtil;
import OBS_2025.OBS_MAIN;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

@SuppressWarnings({"serial" , "static-access","unused"})
public class ORTALAMA_FIAT extends JInternalFrame {
	
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	
	private static JTable table;
	private static String qwq6  = "";
	private static String qwq7  = "";
	private static JLabel lbladet;
	public static JSplitPane splitPane ;
	static String sstr_1 = "" ;
	static String sstr_2 = "" ;
	static String sstr_4 = "" ;
	static String sstr_5 = "" ;
	static String yu = "" ;
	static String iu = "" ;
	private static  String  fdf  = "" ;
	private static long startTime ;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public ORTALAMA_FIAT() {
		setTitle("ORTALAMA SATIS");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 900, 600);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(ORTALAMA_FIAT.class.getResource("/ICONLAR/icons8-approximately-not-equal-30.png")), 16, 16));//
		
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
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK : KeyEvent.ALT_DOWN_MASK) ) != 0))
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
		//panel.setBorder(new LineBorder(null));
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kayit Sayisi :");
		lblNewLabel.setBounds(10, 7, 85, 14);
		panel.add(lblNewLabel);
		
		lbladet = new JLabel("0");
		//lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setBounds(100, 7, 71, 14);
		panel.add(lbladet);
	}
	public static void yenile ()
	{
		 try
		 {
		 	GRID_TEMIZLE.grid_temizle(table);
				 if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Hesap Kodu-Ana_Alt_Grup"))
					{
					  mus_ana_kodlu();
					}
				 else  if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Hesap Kodu"))
					{
					 mus_kodlu();
					}
				 else 
					{
					 diger_kodlu();
					}
		 }
     catch (Exception ex)
		 {
    	 GRID_TEMIZLE.grid_temizle(table);
    	 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
    	}
	}
	private static void mus_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
 			grup_cevir() ;
		
					 rs = f_Access.ort_hes_kodu(fdf,qwq6,qwq7,
							 FILTRE.textField_59.getText(),FILTRE.textField_60.getText() ,
							 FILTRE.textField_56.getText(),FILTRE.textField_57.getText() ,
							 FILTRE.textField_51.getText(),FILTRE.textField_52.getText() ,
							 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_26),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_27),  BAGLAN.kurDizin.kOD ,
							 FILTRE.comboBox_50.getItemAt(FILTRE.comboBox_50.getSelectedIndex()) );
					
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
			
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(250);
				tc.setMaxWidth(250);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(90);
				
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
				lbladet.setText(FORMATLAMA.doub_0(table.getRowCount()));
				fontt();
			}
		} 
		catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
	private static void mus_ana_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
 			grup_cevir() ;
			
					 rs = f_Access.ort_hes_ana_kodu(fdf,qwq6,qwq7,
							 FILTRE.textField_59.getText(),FILTRE.textField_60.getText() ,
							 FILTRE.textField_56.getText(),FILTRE.textField_57.getText() ,
							 FILTRE.textField_51.getText(),FILTRE.textField_52.getText() ,
							 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_26),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_27), BAGLAN.kurDizin.kOD ,
							 FILTRE.comboBox_50.getItemAt(FILTRE.comboBox_50.getSelectedIndex()) );
				
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
			
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(250);
				tc.setMaxWidth(250);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(90);
				
				
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
				//table.setSelectionBackground(Color.PINK);
				//table.setSelectionForeground(Color.BLUE);
				fontt();
			}
		} 
		catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
	private static void diger_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			grup_cevir() ;
			
					 rs = f_Access.ort_diger_kodu(yu,qwq6,qwq7,
							 FILTRE.textField_59.getText(),FILTRE.textField_60.getText() ,
							 FILTRE.textField_56.getText(),FILTRE.textField_57.getText() ,
							 FILTRE.textField_51.getText(),FILTRE.textField_52.getText() ,
							 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_26),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_27),  BAGLAN.kurDizin.kOD ,
							 FILTRE.comboBox_50.getItemAt(FILTRE.comboBox_50.getSelectedIndex()),iu );
				
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
				if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Urun Kodu"))
	            {
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(90);
					
					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(250);
					tc.setMaxWidth(250);
					sut = 2 ;
					
	            }
				else if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Yil"))
	            {
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(60);
					sut = 1 ;
	            }
				else if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Yil_Ay"))
	            {
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(60);
					
					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(50);
					
					sut = 2 ;
	            }
				else if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Urun Ana Grup"))
	            {
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(150);
					
					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(150);
					
					sut = 2 ;
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
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(sut + 3);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(sut + 4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(sut + 5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(90);
				
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
				//table.setSelectionBackground(Color.PINK);
				//table.setSelectionForeground(Color.BLUE);
				fontt();
			}
		} 
		catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
	private static void grup_cevir()
	{
		try {
		ResultSet	rs = null;
				//** Urun Ana grup
				if ( FILTRE.comboBox_56.getItemAt(FILTRE.comboBox_56.getSelectedIndex()).equals(""))
				{
		            qwq6 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_56.getItemAt(FILTRE.comboBox_56.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq6 = " = '' " ;
		        }
		        else
		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_56.getItemAt(FILTRE.comboBox_56.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq6 = "=" + Integer.toString( rs.getInt("AGID_Y"));
		    			}
		    		
		        }
				//** Urun Alt Grup
				if ( FILTRE.comboBox_57.getItemAt(FILTRE.comboBox_57.getSelectedIndex()).equals(""))
				{
		            qwq7 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_57.getItemAt(FILTRE.comboBox_57.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq7 = " = '' " ;
		        }		        else		      
		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_57.getItemAt(FILTRE.comboBox_57.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq7 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		
		        }
			//**
			 fdf = " (SELECT DISTINCT  UNVAN FROM [OK_Car" + BAGLAN.cariDizin.kOD + "].[dbo].[HESAP] WHERE hesap.hesap = FATURA.Cari_Firma   ) as Cari_Adi " ;
			 //'**************************************************
			 if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Urun Ana Grup"))
				{ 
	                yu = " (SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = MAL.Ana_Grup ) as Ana_Grup " +
	                     " , (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = MAL.Alt_Grup ) as Alt_Grup ";
	                iu = " mal.Ana_Grup ,  mal.Alt_Grup order by mal.Ana_Grup ";
				}
	            else  if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Urun Kodu"))
	            {
	                yu = " Stok.Urun_Kodu , Mal.Adi ";
	                iu = " Stok.Urun_Kodu, Mal.Adi order by Stok.Urun_Kodu  ";
	            }
	            else  if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Yil"))
	            {
	                yu = " datepart(yyyy,STOK.Tarih) as Yil ";
	                iu = "  datepart(yyyy,STOK.Tarih) order by datepart(yyyy,STOK.Tarih)  ";
	            }
	            else  if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Yil_Ay"))
	            {
	                yu = " datepart(yyyy,STOK.Tarih) as Yil, datepart(mm,STOK.Tarih) as Ay ";
	                iu = "  datepart(yyyy,STOK.Tarih) , datepart(mm,STOK.Tarih) order by datepart(yyyy,STOK.Tarih),datepart(mm,STOK.Tarih)  ";
	            }
	            //'************************************
		} 
		catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
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
	public static void excell_aktar()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		
		if (mdl.getRowCount() == 0 )
		{
			
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Aktarilacak Bilgi Yok.....");
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
		public void run() 
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

				File outputfile = new File("Ortalama_Fiyat");
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
					HSSFSheet sheet = workbook.createSheet("Ortalama_Fiyat");
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
					sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -1));
					Cell baslikname = baslikRow.createCell(0);

					baslikname.setCellValue( BAGLAN.fatDizin.fIRMA_ADI );
					baslikname.setCellStyle(acikStyle);

					Row headerRow = sheet.createRow(1);

					if ( FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).equals("Urun Kodu"))
					{
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 2 || q ==3 || q == 4 || q ==5 || q == 6 || q ==7  )
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
						for (int i =0;i <= mdl.getRowCount() -1 ;i++)
						{
							Progres_Bar(mdl.getRowCount() , i);
							Row satirRow = sheet.createRow(i+2);
							for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s == 4  || s == 5 )
									{
										hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else if (s == 2 || s == 3 || s == 6 || s == 7  )
									{
										hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else
									{
										hname.setCellValue( mdl.getValueAt(i,s).toString());
										hname.setCellStyle(solaStyle);
									}
								}
							}
						}
					}
					else if ( FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).equals("Hesap Kodu"))    
					{
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 )
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerSolaStyle);
							}
							else
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerStyle);
							}
						}
						for (int i =0;i <= mdl.getRowCount() -1 ;i++)
						{
							Progres_Bar(mdl.getRowCount() , i);
							Row satirRow = sheet.createRow(i+2);
							for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s == 3  )
									{
										hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else if (s == 2 || s == 4 || s == 5 ||  s == 6  )
									{
										hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else
									{
										hname.setCellValue( mdl.getValueAt(i,s).toString());
										hname.setCellStyle(solaStyle);
									}
								}
							}
						}
					}
					else if ( FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).equals("Hesap Kodu-Ana_Alt_Grup"))   
					{
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 || q == 2 || q == 3)
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerSolaStyle);
							}
							else
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerStyle);
							}
						}
						for (int i =0;i <= mdl.getRowCount() -1 ;i++)
						{
							Progres_Bar(mdl.getRowCount() , i);
							Row satirRow = sheet.createRow(i+2);
							for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s == 5  )
									{
										hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else if (s == 4 || s == 6 || s == 7 || s == 8  )
									{
										hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else
									{
										hname.setCellValue( mdl.getValueAt(i,s).toString());
										hname.setCellStyle(solaStyle);
									}
								}
							}
						}
					}
					else if ( FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).equals("Yil"))   
					{
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 )
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerSolaStyle);
							}
							else
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerStyle);
							}
						}
						for (int i =0;i <= mdl.getRowCount() -1 ;i++)
						{
							Progres_Bar(mdl.getRowCount() , i);
							Row satirRow = sheet.createRow(i+2);
							for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s == 3 || s == 4 )
									{
										hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else if (s == 1 || s == 2 || s == 5 || s == 6  )
									{
										hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else
									{
										hname.setCellValue( mdl.getValueAt(i,s).toString());
										hname.setCellStyle(solaStyle);
									}
								}
							}
						}
					} 
					else   
					{
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q == 1 )
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerSolaStyle);
							}
							else
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerStyle);
							}
						}
						for (int i =0;i <= mdl.getRowCount() -1 ;i++)
						{
							Progres_Bar(mdl.getRowCount() , i);
							Row satirRow = sheet.createRow(i+2);
							for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s == 4 || s == 5 )
									{
										hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else if (s == 2 || s == 3 || s == 6 || s == 7  )
									{
										hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else
									{
										hname.setCellValue( mdl.getValueAt(i,s).toString());
										hname.setCellStyle(solaStyle);
									}
								}
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
					XSSFSheet sheet = workbook.createSheet("Ortalama_Fiyat");
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
					sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -1));
					Cell baslikname = baslikRow.createCell(0);

					baslikname.setCellValue( BAGLAN.fatDizin.fIRMA_ADI);
					baslikname.setCellStyle(acikStyle);
					Row headerRow = sheet.createRow(1);

					if ( FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).equals("Urun Kodu"))
					{
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 2 || q ==3 || q == 4 || q ==5 || q == 6 || q ==7  )
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
						for (int i =0;i <= mdl.getRowCount() -1 ;i++)
						{
							Progres_Bar(mdl.getRowCount() , i);
							Row satirRow = sheet.createRow(i+2);
							for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s == 4  || s == 5 )
									{
										hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else if (s == 2 || s == 3 || s == 6 || s == 7  )
									{
										hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else
									{
										hname.setCellValue( mdl.getValueAt(i,s).toString());
										hname.setCellStyle(solaStyle);
									}

								}
							}
						}
					}
					else if ( FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).equals("Hesap Kodu"))    
					{
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 )
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerSolaStyle);
							}
							else
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerStyle);
							}
						}
						for (int i =0;i <= mdl.getRowCount() -1 ;i++)
						{
							Progres_Bar(mdl.getRowCount() , i);
							Row satirRow = sheet.createRow(i+2);
							for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s == 3  )
									{
										hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else if (s == 2 || s == 4 || s == 5 ||  s == 6  )
									{
										hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else
									{
										hname.setCellValue( mdl.getValueAt(i,s).toString());
										hname.setCellStyle(solaStyle);
									}
								}
							}
						}
					}
					else if ( FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).equals("Hesap Kodu-Ana_Alt_Grup"))   
					{
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 || q == 2 || q == 3)
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerSolaStyle);
							}
							else
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerStyle);
							}
						}
						for (int i =0;i <= mdl.getRowCount() -1 ;i++)
						{
							Progres_Bar(mdl.getRowCount() , i);
							Row satirRow = sheet.createRow(i+2);
							for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s == 5  )
									{
										hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else if (s == 4 || s == 6 || s == 7 || s == 8  )
									{
										hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else
									{
										hname.setCellValue( mdl.getValueAt(i,s).toString());
										hname.setCellStyle(solaStyle);
									}
								}
							}
						}
					}
					else if ( FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).equals("Yil"))   
					{
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 )
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerSolaStyle);
							}
							else
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerStyle);
							}
						}
						for (int i =0;i <= mdl.getRowCount() -1 ;i++)
						{
							Progres_Bar(mdl.getRowCount() , i);
							Row satirRow = sheet.createRow(i+2);
							for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s == 3 || s == 4 )
									{
										hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else if (s == 1 || s == 2 || s == 5 || s == 6  )
									{
										hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else
									{
										hname.setCellValue( mdl.getValueAt(i,s).toString());
										hname.setCellStyle(solaStyle);
									}
								}
							}
						}
					} 
					else   
					{
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q == 1 )
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerSolaStyle);
							}
							else
							{
								bname.setCellValue(mdl.getColumnName(q));
								bname.setCellStyle(headerStyle);
							}
						}
						for (int i =0;i <= mdl.getRowCount() -1 ;i++)
						{
							Progres_Bar(mdl.getRowCount() , i);
							Row satirRow = sheet.createRow(i+2);
							for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s == 4 || s == 5 )
									{
										hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else if (s == 2 || s == 3 || s == 6 || s == 7  )
									{
										hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										hname.setCellStyle(satirStyle);
									}
									else
									{
										hname.setCellValue( mdl.getValueAt(i,s).toString());
										hname.setCellStyle(solaStyle);
									}
								}
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
			}
			catch (Exception ex)
			{
				 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			}
		}
		};
		//// Progress Bar
		Thread t = new Thread(runner, "Code Executer");
		t.start();
		//
	}
	@SuppressWarnings("resource")
	public static void mail_at()
	{
		try {
		  //************************************** XLXS *****************************************************
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Ortalama_Fiyat");
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
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -1));
			Cell baslikname = baslikRow.createCell(0);

			baslikname.setCellValue( BAGLAN.fatDizin.fIRMA_ADI);
			baslikname.setCellStyle(acikStyle);
			Row headerRow = sheet.createRow(1);
			 
			if ( FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).equals("Urun Kodu"))
			{
				for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
				{
					Cell bname = headerRow.createCell(q);
					if (q == 2 || q ==3 || q == 4 || q ==5 || q == 6 || q ==7  )
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
				for (int i =0;i <= mdl.getRowCount() -1 ;i++)
				{
					Progres_Bar(mdl.getRowCount() , i);
					Row satirRow = sheet.createRow(i+2);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						if ( mdl.getValueAt(i, s) != null)
						{
							if (s == 4  || s == 5 )
							{
								hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
								hname.setCellStyle(satirStyle);
							}
							else if (s == 2 || s == 3 || s == 6 || s == 7  )
							{
								hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
								hname.setCellStyle(satirStyle);
							}
							else
							{
								hname.setCellValue( mdl.getValueAt(i,s).toString());
								hname.setCellStyle(solaStyle);
							}
						}
					}
				}
			}
			else if ( FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).equals("Hesap Kodu"))    
			{
				for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
				{
					Cell bname = headerRow.createCell(q);
					if (q == 0 || q ==1 )
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerSolaStyle);
					}
					else
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerStyle);
					}
				}
				for (int i =0;i <= mdl.getRowCount() -1 ;i++)
				{
					Progres_Bar(mdl.getRowCount() , i);
					Row satirRow = sheet.createRow(i+2);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						if ( mdl.getValueAt(i, s) != null)
						{
							if (s == 3  )
							{
								hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
								hname.setCellStyle(satirStyle);
							}
							else if (s == 2 || s == 4 || s == 5 ||  s == 6  )
							{
								hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
								hname.setCellStyle(satirStyle);
							}
							else
							{
								hname.setCellValue( mdl.getValueAt(i,s).toString());
								hname.setCellStyle(solaStyle);
							}
						}
					}
				}
			}
			else if ( FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).equals("Hesap Kodu-Ana_Alt_Grup"))   
			{
				for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
				{
					Cell bname = headerRow.createCell(q);
					if (q == 0 || q ==1 || q == 2 || q == 3)
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerSolaStyle);
					}
					else
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerStyle);
					}
				}
				for (int i =0;i <= mdl.getRowCount() -1 ;i++)
				{
					Progres_Bar(mdl.getRowCount() , i);
					Row satirRow = sheet.createRow(i+2);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						if ( mdl.getValueAt(i, s) != null)
						{
							if (s == 5  )
							{
								hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
								hname.setCellStyle(satirStyle);
							}
							else if (s == 4 || s == 6 || s == 7 || s == 8  )
							{
								hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
								hname.setCellStyle(satirStyle);
							}
							else
							{
								hname.setCellValue( mdl.getValueAt(i,s).toString());
								hname.setCellStyle(solaStyle);
							}
						}
					}
				}
			}
			else if ( FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).equals("Yil"))   
			{
				for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
				{
					Cell bname = headerRow.createCell(q);
					if (q == 0 )
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerSolaStyle);
					}
					else
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerStyle);
					}
				}
				for (int i =0;i <= mdl.getRowCount() -1 ;i++)
				{
					Progres_Bar(mdl.getRowCount() , i);
					Row satirRow = sheet.createRow(i+2);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						if ( mdl.getValueAt(i, s) != null)
						{
							if (s == 3 || s == 4 )
							{
								hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
								hname.setCellStyle(satirStyle);
							}
							else if (s == 1 || s == 2 || s == 5 || s == 6  )
							{
								hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
								hname.setCellStyle(satirStyle);
							}
							else
							{
								hname.setCellValue( mdl.getValueAt(i,s).toString());
								hname.setCellStyle(solaStyle);
							}
						}
					}
				}
			} 
			else   
			{
				for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
				{
					Cell bname = headerRow.createCell(q);
					if (q == 0 || q == 1 )
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerSolaStyle);
					}
					else
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerStyle);
					}
				}
				for (int i =0;i <= mdl.getRowCount() -1 ;i++)
				{
					Progres_Bar(mdl.getRowCount() , i);
					Row satirRow = sheet.createRow(i+2);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						if ( mdl.getValueAt(i, s) != null)
						{
							if (s == 4 || s == 5 )
							{
								hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
								hname.setCellStyle(satirStyle);
							}
							else if (s == 2 || s == 3 || s == 6 || s == 7  )
							{
								hname.setCellValue(  FORMATLAMA.doub_2(Double.parseDouble( mdl.getValueAt(i,s).toString())));
								hname.setCellStyle(satirStyle);
							}
							else
							{
								hname.setCellValue( mdl.getValueAt(i,s).toString());
								hname.setCellStyle(solaStyle);
							}
						}
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
		catch (Exception ex)
		{
	//				JOptionPane.showMessageDialog(null, "Excell Aktarma.....","Imalat Grup Raporlama", JOptionPane.ERROR_MESSAGE);
		}
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
