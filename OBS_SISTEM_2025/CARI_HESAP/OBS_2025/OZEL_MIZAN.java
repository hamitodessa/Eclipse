package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.mail.util.ByteArrayDataSource;
import javax.swing.DefaultRowSorter;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.RowSorter.SortKey;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.commons.lang.StringUtils;
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
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FILE_UZANTI;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import javax.swing.JSplitPane;
import javax.swing.JPanel;

@SuppressWarnings({"serial" , "static-access" , "deprecation","resource", "unused"})
public class OZEL_MIZAN extends JInternalFrame {
	public static JTable table;
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	private static JLabel lblonceki ;
	private static JLabel lblborc ;
	private static JLabel lblalacak ;
	private static JLabel lblbakkvartal ;
	private static JLabel lblbakiye ;
	public static JSplitPane splitPane;
	private JLabel lblNewLabel;
	private static JLabel lblNewLabel_1;


	public OZEL_MIZAN() 
	{

		setResizable(true);
		setTitle("CARI OZEL MIZAN");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0,0, 1150, 600);

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		splitPane.setLeftComponent(scrollPane);

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				String status = (String)getValueAt(row,0);
				int deger = 0 ;
				try {
					deger = Integer.parseInt( GLOBAL.setting_oku("CARI_MIZ_GRUP").toString());
				} catch (Exception e) {
					e.printStackTrace();}
				if (status.length() == deger) 
				{
					c.setBackground(oac.satBackColor);
					c.setFont(new Font(table.getFont().getFontName(),1 ,12));
				} else 
				{
					c.setBackground(super.getBackground());
					c.setForeground(super.getForeground());
				}
				if (col == 7)
				{
					if (getValueAt(row,7) != null)
					{
						c.setFont(new Font(table.getFont().getFontName(),1 ,table.getFont().getSize()));
						if (status.length() == deger) 
						{
							c.setForeground(oac.satForeColor);
						}
						else {
							if ((double)getValueAt(row,7) < 0)
							{
								c.setForeground(new Color(128,0,0));
							}
						}
					}
				}
				else 
				{
					if (status.length() == deger) 
					{
						c.setForeground(oac.satForeColor);
					}
					else {
						c.setForeground(super.getForeground());
					}
				}
				if (isRowSelected(row)) {
					c.setBackground(table.getSelectionBackground());
					c.setForeground(table.getSelectionForeground());
                } 
				return c;
			}
		};
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

		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					boolean varmi = OBS_MAIN.pencere_bak("EKSTRE");
					if (varmi  ) 
					{
						try {
							OBS_MAIN.pencere_aktiv_yap("EKSTRE");
						} catch (PropertyVetoException e1) {
							e1.printStackTrace();
						}
					}
					else
					{
						JInternalFrame internalFrame;
						internalFrame  = new EKSTRE();
						OBS_MAIN.desktopPane.add(internalFrame);
						internalFrame.setVisible(true);
					}
					try 
					{
						FILTRE.txtkodu.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
						EKSTRE.hisset();
					} 
					catch (NumberFormatException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setBorder(null);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		panel.setLayout(null);

		lblbakiye = new JLabel("0.00");
		lblbakiye.setForeground(new Color(0, 0, 128));
		lblbakiye.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblbakiye.setHorizontalAlignment(SwingConstants.RIGHT);
		lblbakiye.setBounds(1003, 5, 117, 14);
		panel.add(lblbakiye);

		lblbakkvartal = new JLabel("0.00");
		lblbakkvartal.setForeground(new Color(0, 0, 128));
		lblbakkvartal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblbakkvartal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblbakkvartal.setBounds(883, 6, 117, 14);
		panel.add(lblbakkvartal);

		lblalacak = new JLabel("0.00");
		lblalacak.setForeground(new Color(0, 0, 128));
		lblalacak.setHorizontalAlignment(SwingConstants.RIGHT);
		lblalacak.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblalacak.setBounds(763, 6, 117, 14);
		panel.add(lblalacak);
		splitPane.setRightComponent(panel);

		lblborc = new JLabel("0.00");
		lblborc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblborc.setForeground(new Color(0, 0, 128));
		lblborc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblborc.setBounds(643, 6, 117, 14);
		panel.add(lblborc);

		lblonceki = new JLabel("0.00");
		lblonceki.setHorizontalAlignment(SwingConstants.RIGHT);
		lblonceki.setForeground(new Color(0, 0, 128));
		lblonceki.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblonceki.setBounds(523, 6, 117, 14);
		panel.add(lblonceki);
		
		lblNewLabel = new JLabel("Satir Sayisi :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 5, 85, 14);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(100, 5, 51, 14);
		panel.add(lblNewLabel_1);

	}
	public static void hisset ()  
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			//**************
			String o1 = "" ;
			String o2 = "" ;
			lblNewLabel_1.setText("0");
			String hangi_tur = FILTRE.comboBox.getItemAt(FILTRE.comboBox.getSelectedIndex());
			if (BAGLAN.cariDizin.hAN_SQL.equals("MS SQL"))
			{
				if (hangi_tur.equals("Borclu Hesaplar") )
				{ o1 = "HAVING  (ROUND(ISNULL( (SELECT SUM(SATIRLAR.ALACAK) - SUM(SATIRLAR.BORC)  FROM SATIRLAR     "
						+ "WHERE   SATIRLAR.HESAP    = s.HESAP     			 ) ,0),2)  )  < 0 " ; }
				else if (hangi_tur.equals("Alacakli Hesaplar"))  
				{ o1 = "HAVING  (ROUND(ISNULL( (SELECT SUM(SATIRLAR.ALACAK) - SUM(SATIRLAR.BORC)  FROM SATIRLAR     "
						+ "WHERE   SATIRLAR.HESAP    = s.HESAP     			 ) ,0),2)  )  > 0 " ; }
				else if (hangi_tur.equals( "Bakiyesi 0 Olanlar" ))     
				{ o1 = "HAVING  (ROUND(ISNULL( (SELECT SUM(SATIRLAR.ALACAK) - SUM(SATIRLAR.BORC)  FROM SATIRLAR     "
						+ "WHERE   SATIRLAR.HESAP    = s.HESAP     			 ) ,0),2)  )  = 0 " ; }
				else if (hangi_tur.equals( "Bakiyesi 0 Olmayanlar" ))
				{ o1 ="HAVING  (ROUND(ISNULL( (SELECT SUM(SATIRLAR.ALACAK) - SUM(SATIRLAR.BORC)  FROM SATIRLAR    "
						+ "WHERE   SATIRLAR.HESAP    = s.HESAP     			 ) ,0),2)  ) <> 0 " ; }
				o2 = " ORDER BY s.HESAP ASC " ;  
			}			
			else if (BAGLAN.cariDizin.hAN_SQL.equals("MY SQL"))
			{
				if (hangi_tur.equals("Borclu Hesaplar") )
				{ 
					o1= " HAVING BAKIYE  < 0 " ;
				}
				else if (hangi_tur.equals("Alacakli Hesaplar"))  
				{
					o1= " HAVING BAKIYE  > 0 " ;
				}
				else if (hangi_tur.equals( "Bakiyesi 0 Olanlar" ))     
				{
					o1= " HAVING BAKIYE  =  0 " ;
				}
				else if (hangi_tur.equals( "Bakiyesi 0 Olmayanlar" ))
				{ 
					o1= " HAVING BAKIYE  <>  0 " ;
				}
				o2 = " ORDER BY s.HESAP  " ;  
			}
			//**************
			rs = c_Access.ozel_mizan(FILTRE.txtilk.getText(),FILTRE.txtson.getText() ,
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) ,
					FILTRE.txticins.getText(),FILTRE.txtscins.getText() ,
					FILTRE.txtikarton.getText(),FILTRE.txtskarton.getText() ,
					o1 , o2);
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				return;
			} 
			table.setModel(DbUtils.resultSetToTableModel(rs));

			lblNewLabel_1.setText(FORMATLAMA.doub_0(table.getRowCount()));
			ara_ayir();
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(95);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(350);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);

			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);

			tc = tcm.getColumn(7);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();

			//***
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			double borc = 0,alacak = 0,bakiye = 0,bakkvartal =0,onceki = 0 ;
			for (int i = 0 ; i <= mdl.getRowCount()-1;i++)
			{
				onceki  += (double) mdl.getValueAt(i , 3);
				borc  += (double) mdl.getValueAt(i , 4);
				alacak  += (double) mdl.getValueAt(i , 5);
				bakkvartal  += (double)   (mdl.getValueAt(i , 6) == null ? 0 :(double)mdl.getValueAt(i , 6) );
				bakiye  +=  (double)   (mdl.getValueAt(i , 7) == null ? 0 :(double)mdl.getValueAt(i , 7) );
			}
			lblonceki.setText(FORMATLAMA.doub_2(onceki));
			lblborc.setText(FORMATLAMA.doub_2(borc));
			lblalacak.setText(FORMATLAMA.doub_2(alacak));
			lblbakkvartal.setText(FORMATLAMA.doub_2(bakkvartal));
			lblbakiye.setText(FORMATLAMA.doub_2(bakiye));
			
			
			//***

			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);

			int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
			table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
			table.setRowSelectionInterval(lastRow, lastRow);

			table.repaint();
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			try {
				String deger;
				String[] parts;
				Font bigFont;
				deger = GLOBAL.setting_oku("CARI_MIZAN").toString();
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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private static void ara_ayir() throws NumberFormatException, IOException
	{
		try
		{
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			Vector<Object> data = new Vector<Object>();
			int satir = table.getRowCount() - 1 ;
			int deger = Integer.parseInt( GLOBAL.setting_oku("CARI_MIZ_GRUP").toString());
			String onceki = "" ;
			for (int i = 1; i <= satir   ; i ++)
			{
				if (  model.getValueAt((i) - 1 , 0).toString().length() < deger  )
				{
					int kj = 0 ;
					kj = deger - model.getValueAt((i) - 1 , 0).toString().length() ;
					onceki =  model.getValueAt((i) - 1 , 0).toString() + StringUtils.repeat(" ", kj);
				}
				else
				{
					onceki = model.getValueAt((i) - 1 , 0).toString().substring(0, deger   );

				}

				if (! model.getValueAt(i , 0).toString().substring(0, model.getValueAt(i  , 0).toString().length() < deger  ? model.getValueAt(i  , 0).toString().length()  : deger ).equals(onceki)) 
				{
					data = new Vector<Object>();
					data.add(model.getValueAt(i , 0).toString().substring(0, model.getValueAt(i  , 0).toString().length() < deger  ? model.getValueAt(i  , 0).toString().length()  : deger ));
					data.add(isimoku(model.getValueAt(i , 0).toString().substring(0, model.getValueAt(i  , 0).toString().length() < deger  ? model.getValueAt(i  , 0).toString().length()  : deger )));

					data.add("---");
					double doub = 0 ;
					data.add(doub);
					data.add(doub);
					data.add(doub);
					model.addRow(data);
				}
			}
			//*****
			data = new Vector<Object>();
			if (model.getValueAt(0 , 0).toString().length() < deger)
			{
				int kj = 0 ;
				kj = deger  - model.getValueAt(0 , 0).toString().trim().length() ;
				//	String str_ =  model.getValueAt(0 , 0).toString() +  StringUtils.repeat("-", kj)     ;
				String str_ =  model.getValueAt(0 , 0).toString()     ;
				data.add(str_);
				data.add(isimoku(model.getValueAt(0 , 0).toString()));
			}
			else
			{
				data.add(model.getValueAt(0 , 0).toString().substring(0,deger));
				data.add(isimoku(model.getValueAt(0 , 0).toString().substring(0,deger)));

			}
			data.add("---");
			double doub = 0 ;
			data.add(doub);
			data.add(doub);
			data.add(doub);
			data.add(doub);
			data.add(doub);
			model.addRow(data);


			//*****
			table.setAutoCreateRowSorter(true);
			DefaultRowSorter<?, ?> sorter = ((DefaultRowSorter<?, ?>)table.getRowSorter()); 
			ArrayList<SortKey> list = new ArrayList<SortKey>();
			list.add( new RowSorter.SortKey(0, SortOrder.ASCENDING) );
			list.add( new RowSorter.SortKey(2,SortOrder.ASCENDING) );
			sorter.setSortKeys(list);
			sorter.sort();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
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

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");  
			LocalDateTime now = LocalDateTime.now();  
			String zaman = dtf.format(now)  ;

			File outputfile = new File("Ozel_Mizan");
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
				HSSFSheet sheet = workbook.createSheet("Ozel_Mizan");
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
				sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -2));
				Cell baslikname = baslikRow.createCell(0);
				baslikname.setCellValue(BAGLAN.cariDizin.fIRMA_ADI );
				baslikname.setCellStyle(acikStyle);

				Cell tarih = baslikRow.createCell(7);
				SimpleDateFormat DateFor = new SimpleDateFormat("dd.MM.yyyy");
				tarih.setCellValue(DateFor.format(new Date() ));
				tarih.setCellStyle(satirStyle);

				Row acikRow = sheet.createRow(1);
				sheet.addMergedRegion(new CellRangeAddress(1,1,6,mdl.getColumnCount() -1));
				String yazi = "Periyot :" + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_2)  + " - " + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_2_1);
				Cell acik  = acikRow.createCell(6);
				acik.setCellStyle(satirStyle);
				acik.setCellValue(yazi );
				//  BOS SATIR
				Row bosRow = sheet.createRow(2);
				//
				int sutun = 2 ;
				//
				Row headerRow = sheet.createRow(3);
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
					Row satirRow = sheet.createRow(i+4);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						if ( mdl.getValueAt(i, s) != null)
						{
							////////////
							if (s > sutun)
							{
								hname.setCellStyle(satirStyle2);
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
				Row topRow = sheet.createRow( mdl.getRowCount()+4);
				Cell hname = topRow.createCell(3);
				hname.setCellValue(lblonceki.getText().toString());
				hname.setCellStyle(satirStyle);
				hname = topRow.createCell(4);
				hname.setCellValue( lblborc.getText().toString());
				hname.setCellStyle(satirStyle);
				hname = topRow.createCell(5);
				hname.setCellValue(lblalacak.getText().toString());
				hname.setCellStyle(satirStyle);
				hname = topRow.createCell(6);
				hname.setCellValue(lblbakkvartal.getText().toString());
				hname.setCellStyle(satirStyle);
				hname = topRow.createCell(7);
				hname.setCellValue(lblbakiye.getText().toString());
				hname.setCellStyle(satirStyle);

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
				XSSFSheet sheet = workbook.createSheet("Ozel_Mizan");
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
				sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -2));
				Cell baslikname = baslikRow.createCell(0);

				baslikname.setCellValue(BAGLAN.cariDizin.fIRMA_ADI);
				baslikname.setCellStyle(acikStyle);

				Cell tarih = baslikRow.createCell(7);
				SimpleDateFormat DateFor = new SimpleDateFormat("dd.MM.yyyy");
				tarih.setCellValue(DateFor.format(new Date() ));
				tarih.setCellStyle(satirStyle);

				Row acikRow = sheet.createRow(1);
				sheet.addMergedRegion(new CellRangeAddress(1,1,6,mdl.getColumnCount() -1));
				String yazi = "Periyot :" + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_2)  + " - " + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_2_1);
				Cell acik  = acikRow.createCell(6);
				acik.setCellStyle(satirStyle);
				acik.setCellValue(yazi );
				//  BOS SATIR
				Row bosRow = sheet.createRow(2);
				//
				int sutun = 2 ;
				Row headerRow = sheet.createRow(3);
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
					Row satirRow = sheet.createRow(i+4);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						if ( mdl.getValueAt(i, s) != null)
						{
							if (s > sutun)
							{
								hname.setCellStyle(satirStyle2);
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
				Row topRow = sheet.createRow( mdl.getRowCount()+4);
				Cell hname = topRow.createCell(3);
				hname.setCellValue(lblonceki.getText().toString());
				hname.setCellStyle(satirStyle);
				hname = topRow.createCell(4);
				hname.setCellValue( lblborc.getText().toString());
				hname.setCellStyle(satirStyle);
				hname = topRow.createCell(5);
				hname.setCellValue(lblalacak.getText().toString());
				hname.setCellStyle(satirStyle);
				hname = topRow.createCell(6);
				hname.setCellValue(lblbakkvartal.getText().toString());
				hname.setCellStyle(satirStyle);
				hname = topRow.createCell(7);
				hname.setCellValue(lblbakiye.getText().toString());
				hname.setCellStyle(satirStyle);

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
			
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO, "Aktarma Islemi Tamamlandi....." );
			//JOptionPane.showMessageDialog(null, "Aktarma Islemi Tamamlandi.....","Ozel Mizan", JOptionPane.PLAIN_MESSAGE);
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
			//JOptionPane.showMessageDialog(null, ex.getMessage(),"Ozel Mizan", JOptionPane.PLAIN_MESSAGE);
		}
	}
	public static void mail_at() throws IOException
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Ozel_Mizan");
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
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -2));
		Cell baslikname = baslikRow.createCell(0);

		baslikname.setCellValue(BAGLAN.cariDizin.fIRMA_ADI);
		baslikname.setCellStyle(acikStyle);

		Cell tarih = baslikRow.createCell(7);
		SimpleDateFormat DateFor = new SimpleDateFormat("dd.MM.yyyy");
		tarih.setCellValue(DateFor.format(new Date() ));
		tarih.setCellStyle(satirStyle);

		Row acikRow = sheet.createRow(1);
		sheet.addMergedRegion(new CellRangeAddress(1,1,6,mdl.getColumnCount() -1));
		String yazi = "Periyot :" + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_2)  + " - " + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_2_1);
		Cell acik  = acikRow.createCell(6);
		acik.setCellStyle(satirStyle);
		acik.setCellValue(yazi );

		//  BOS SATIR
		Row bosRow = sheet.createRow(2);
		//
		int sutun = 2 ;
		Row headerRow = sheet.createRow(3);
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
			Row satirRow = sheet.createRow(i+4);
			for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
			{
				Cell hname = satirRow.createCell(s);
				if ( mdl.getValueAt(i, s) != null)
				{
					if (s > sutun)
					{
						hname.setCellStyle(satirStyle2);
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
		}			// ALT TOPLAM
		Row topRow = sheet.createRow( mdl.getRowCount()+4);
		Cell hname = topRow.createCell(3);
		hname.setCellValue(lblonceki.getText().toString());
		hname.setCellStyle(satirStyle);
		hname = topRow.createCell(4);
		hname.setCellValue( lblborc.getText().toString());
		hname.setCellStyle(satirStyle);
		hname = topRow.createCell(5);
		hname.setCellValue(lblalacak.getText().toString());
		hname.setCellStyle(satirStyle);
		hname = topRow.createCell(6);
		hname.setCellValue(lblbakkvartal.getText().toString());
		hname.setCellStyle(satirStyle);
		hname = topRow.createCell(7);
		hname.setCellValue(lblbakiye.getText().toString());
		hname.setCellStyle(satirStyle);

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
	private static String isimoku(String kod) {
		String sonuc = "" ;
		try
		{
			ResultSet	rs = null;
			rs = c_Access.hesap_adi_oku(kod);
			if (!rs.isBeforeFirst() ) {  
				sonuc = "" ;
			} 
			else
			{
				rs.next();
				sonuc=rs.getString("UNVAN");
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
			//JOptionPane.showMessageDialog(null, ex.getMessage(),"Ozel Mizan Raporlama", JOptionPane.ERROR_MESSAGE);
		}
		return sonuc ;
	}	
}
