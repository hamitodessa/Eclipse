package OBS_2025_RAPORLAR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
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
//import OBS_PACKAGE.CONNECTION;
import OBS_C_2025.FILE_UZANTI;
import OBS_C_2025.FIT_IMAGE;
import OBS_2025.FILTRE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.MaterialTabbed;
import OBS_2025.GuiUtil;

import OBS_2025.OBS_MAIN;

import OBS_C_2025.SAGA;
import OBS_C_2025.SAGA_YANAS;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;
 
@SuppressWarnings({ "static-access", "serial" })
public class ENVANTER extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);

	public static JTable table;
	private static String qwq1 ="" ;
	private static String qwq2  = "";
	private static String qwq3  = "";
	private static String qwq4  = "";
	private static String qwq5  = "";
	private static String qwq6  = "";
	private static String qwq7  = "";

	private static String awq1 ="" ;
	private static String awq2  = "";
	private static String awq3  = "";
	private static String awq4  = "";
	private static String awq5  = "";
	private static String awq6  = "";
	private static String awq7  = "";

	private static JLabel lbl1;
	private static JLabel lbl2 ;
	private static JLabel lbl3 ;
	private static JLabel lbl4 ;
	private static JLabel lbl5 ;
	private static JLabel lbl6 ;
	private static JLabel lbl7 ;

	private static JLabel lbladet;
	public static JSplitPane splitPane ;
	public static JSplitPane splitPane_1 ;
	private static MaterialTabbed tabbedPane;
	//private JScrollPane scrollPane_3;
	//private JScrollPane scrollPane_4;
	private static JTable table_1;
	private static JTable table_2;
	private JSplitPane splitPane_2;
	//private JScrollPane scrollPane_2;
	private static JTable table_3;
	//private JScrollPane scrollPane_5;
	private static JTable table_4;
	private static int sat_sayi = 0;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public ENVANTER() {
		setTitle("ENVANTER DOKUM");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 1228, 600);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(ENVANTER.class.getResource("/ICONLAR/icons8-hashtag-activity-feed-30.png")), 16, 16));//
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		tabbedPane = new MaterialTabbed();
		tabbedPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		splitPane.setLeftComponent(tabbedPane);

		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		tabbedPane.add(scrollPane);
		tabbedPane.setTitleAt(0, "Agir");

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			table.setGridColor(oac.gridcolor);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//table.setRowSelectionAllowed(false);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);

		splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setDividerSize(0);

		tabbedPane.addTab("FIFO", null, 	splitPane_1, null);

		ScrollPaneWin11 scrollPane_3 = new ScrollPaneWin11();
		scrollPane_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		splitPane_1.setRightComponent(scrollPane_3);

		table_2 = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }

			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				if (col == 5)
				{
					if (getValueAt(row,5) != null)
					{
						double tut = (double)getValueAt(row,5);
						if (tut < 0)
						{
							c.setForeground(new Color(128,0,0));
							c.setBackground(Color.CYAN);
							Font fnt = new Font(table_2.getFont().getFontName(),1 ,table_2.getFont().getSize());
							c.setFont(fnt);
						}
						else
						{
							c.setBackground(super.getBackground());
							c.setForeground(super.getForeground());
						}
					}
				}
				if (col == 9)
				{
					if (getValueAt(row,9) != null)
					{
						double tut = (double)getValueAt(row,9);
						if (tut < 0)
						{
							c.setForeground(new Color(128,0,0));
							c.setBackground(Color.CYAN);
							Font fnt = new Font(table_2.getFont().getFontName(),1 ,table_2.getFont().getSize());
							c.setFont(fnt);
						}
						else
						{
							c.setBackground(super.getBackground());
							c.setForeground(super.getForeground());
						}
					}
				}
				return c;
			}
		};
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_2.setRowSelectionAllowed(false);
		table_2.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table_2.setGridColor(oac.gridcolor);
		}
		table_2.setShowHorizontalLines(true);
		table_2.setShowVerticalLines(true);
		scrollPane_3.setViewportView(table_2);

		ScrollPaneWin11 scrollPane_4 = new ScrollPaneWin11();
		scrollPane_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		scrollPane_4.setMinimumSize(new Dimension(0, 70));
		scrollPane_4.setMaximumSize(new Dimension(0, 70));
		splitPane_1.setLeftComponent(scrollPane_4);

		table_1 = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				if (col == 9)
				{
					c.setForeground(Color.RED);
					c.setBackground(Color.WHITE);
					Font fnt = new Font(table_1.getFont().getFontName(),1 ,table_1.getFont().getSize());
					c.setFont(fnt);
				}
				return c;
			}
		};
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setRowSelectionAllowed(false);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table_1.setGridColor(oac.gridcolor);
		}

		table_1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table_1.setShowHorizontalLines(true);
		table_1.setShowVerticalLines(true);
		scrollPane_4.setViewportView(table_1);

		splitPane_2 = new JSplitPane();
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		tabbedPane.addTab("LIFO", null, splitPane_2, null);

		ScrollPaneWin11 scrollPane_2 = new ScrollPaneWin11();
		scrollPane_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		scrollPane_2.setMinimumSize(new Dimension(0, 70));
		scrollPane_2.setMaximumSize(new Dimension(0, 70));
		splitPane_2.setLeftComponent(scrollPane_2);

		table_3 = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }

			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);

				if (col == 9)
				{
					c.setForeground(Color.RED);
					c.setBackground(Color.white);
					Font fnt = new Font(table_3.getFont().getFontName(),1 ,table_3.getFont().getSize());
					c.setFont(fnt);
				}
				return c;
			}
		};
		table_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_3.setRowSelectionAllowed(false);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table_3.setGridColor(oac.gridcolor);
		}
		table_3.setShowHorizontalLines(true);
		table_3.setShowVerticalLines(true);
		table_3.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane_2.setViewportView(table_3);

		ScrollPaneWin11 scrollPane_5 = new ScrollPaneWin11();
		scrollPane_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		splitPane_2.setRightComponent(scrollPane_5);

		table_4 = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }

			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				if (col == 5)
				{
					if (getValueAt(row,5) != null)
					{
						double tut = (double)getValueAt(row,5);
						if (tut < 0)
						{
							c.setForeground(new Color(128,0,0));
							c.setBackground(Color.CYAN);
							Font fnt = new Font(table_4.getFont().getFontName(),1 ,table_4.getFont().getSize());
							c.setFont(fnt);
						}
						else
						{
							c.setBackground(super.getBackground());
							c.setForeground(super.getForeground());
						}
					}
				}
				if (col == 9)
				{
					if (getValueAt(row,9) != null)
					{
						double tut = (double)getValueAt(row,9);
						if (tut < 0)
						{
							c.setForeground(new Color(128,0,0));
							c.setBackground(Color.CYAN);
							Font fnt = new Font(table_4.getFont().getFontName(),1 ,table_4.getFont().getSize());
							c.setFont(fnt);
						}
						else
						{
							c.setBackground(super.getBackground());
							c.setForeground(super.getForeground());
						}
					}
				}
				return c;
			}
		};
		table_4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_4.setRowSelectionAllowed(false);
		table_4.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table_4.setGridColor(oac.gridcolor);
		}

		scrollPane_5.setViewportView(table_4);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);

		lbl3 = new JLabel("0.000");
		lbl3.setHorizontalAlignment(SwingConstants.RIGHT);
		//lbl3.setForeground(new Color(0, 0, 128));
		lbl3.setFont(new Font("Arial Narrow", Font.BOLD, 12));
		lbl3.setBounds(589, 5, 107, 14);
		panel.add(lbl3);

		lbl2 = new JLabel("0.00");
		lbl2.setHorizontalAlignment(SwingConstants.RIGHT);
		//lbl2.setForeground(new Color(0, 0, 128));
		lbl2.setFont(new Font("Arial Narrow", Font.BOLD, 12));
		lbl2.setBounds(489, 5, 107, 14);
		panel.add(lbl2);

		lbl1 = new JLabel("0.000");
		lbl1.setHorizontalAlignment(SwingConstants.RIGHT);
		//lbl1.setForeground(new Color(0, 0, 128));
		lbl1.setFont(new Font("Arial Narrow", Font.BOLD, 12));
		lbl1.setBounds(380, 5, 117, 14);
		panel.add(lbl1);

		JLabel lblNewLabel = new JLabel("Kayit Sayisi :");
		lblNewLabel.setBounds(10, 5, 81, 14);
		panel.add(lblNewLabel);

		lbladet = new JLabel("0");
		//lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setBounds(101, 5, 61, 14);
		panel.add(lbladet);

		lbl4 = new JLabel("0.00");
		lbl4.setHorizontalAlignment(SwingConstants.RIGHT);
		//lbl4.setForeground(new Color(0, 0, 128));
		lbl4.setFont(new Font("Arial Narrow", Font.BOLD, 12));
		lbl4.setBounds(690, 5, 107, 14);
		panel.add(lbl4);

		lbl5 = new JLabel("0.00");
		lbl5.setHorizontalAlignment(SwingConstants.RIGHT);
		//lbl5.setForeground(new Color(0, 0, 128));
		lbl5.setFont(new Font("Arial Narrow", Font.BOLD, 12));
		lbl5.setBounds(790, 5, 107, 14);
		panel.add(lbl5);

		lbl6 = new JLabel("0.000");
		lbl6.setHorizontalAlignment(SwingConstants.RIGHT);
		//lbl6.setForeground(new Color(0, 0, 128));
		lbl6.setFont(new Font("Arial Narrow", Font.BOLD, 12));
		lbl6.setBounds(889, 5, 107, 14);
		panel.add(lbl6);

		lbl7 = new JLabel("0.00");
		lbl7.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl7.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		lbl7.setBounds(1050, 5, 145, 14);
		panel.add(lbl7);
	    final boolean showTabsHeader = false;
	    tabbedPane.setUI(new javax.swing.plaf.metal.MetalTabbedPaneUI()
	    {
	        @Override
	        protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
	            if (showTabsHeader) {return super.calculateTabAreaHeight(tabPlacement, horizRunCount, maxTabHeight);
	            } else {return 0;}	        }
	      protected void paintTabArea(Graphics g,int tabPlacement,int selectedIndex){}
	    });
	    for(int i = 0;i <= tabbedPane.getTabCount() - 1;i++)
	    	tabbedPane.setEnabledAt(i, false);
	}
	public static void hisset()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			tabbedPane.setSelectedIndex(0);
			grup_cevir() ;


			if (FILTRE.comboBox_65.getItemAt(FILTRE.comboBox_65.getSelectedIndex()).equals("Urun Kodu"))
			{
				rs = f_Access.envanter_rapor(
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_28),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_29),
						FILTRE.textField_72.getText(),FILTRE.textField_73.getText() ,
						FILTRE.textField_71.getText(),FILTRE.textField_74.getText() ,
						"","",qwq1, qwq2, qwq3,qwq4,qwq5,qwq6,qwq7, GLOBAL.setting_oku("PRG_PARA").toString() );
			}


			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbl1.setText(FORMATLAMA.doub_3(0));
				lbl2.setText(FORMATLAMA.doub_3(0));
				lbl3.setText(FORMATLAMA.doub_3(0));
				lbl4.setText(FORMATLAMA.doub_3(0));
				lbl5.setText(FORMATLAMA.doub_3(0));
				lbl6.setText(FORMATLAMA.doub_3(0));
				lbl7.setText(FORMATLAMA.doub_3(0));
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;

				//**
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				double d1 = 0,d2 = 0,d3 = 0,d4 = 0,d5 = 0,d6 = 0,d7 = 0 ;
				if (FILTRE.comboBox_65.getItemAt(FILTRE.comboBox_65.getSelectedIndex()).equals("Urun Kodu"))
				{
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
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new SAGA_YANAS());
					tc.setMinWidth(100);

					tc = tcm.getColumn(4);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(2,false));
					tc.setMinWidth(100);

					tc = tcm.getColumn(5);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new SAGA_YANAS());
					tc.setMinWidth(100);

					tc = tcm.getColumn(6);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(2,false));
					tc.setMinWidth(100);

					tc = tcm.getColumn(7);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(2,false));
					tc.setMinWidth(100);

					tc = tcm.getColumn(8);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new SAGA_YANAS());
					tc.setMinWidth(100);

					tc = tcm.getColumn(9);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(2,false));
					tc.setMinWidth(100);

					tc = tcm.getColumn(10);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(2,true));
					tc.setMinWidth(100);


					for (int i = 0 ; i <= mdl.getRowCount()-1;i++)
					{
						d1 += DecimalFormat.getNumberInstance().parse((String) mdl.getValueAt(i , 3)).doubleValue();
						d2 += (double) mdl.getValueAt(i , 4);
						d3 +=  DecimalFormat.getNumberInstance().parse((String) mdl.getValueAt(i , 5)).doubleValue();
						d4 += (double) mdl.getValueAt(i , 6);
						d5 += (double) mdl.getValueAt(i , 7);
						d6 += DecimalFormat.getNumberInstance().parse((String) mdl.getValueAt(i , 8)).doubleValue();
						d7 += (double) mdl.getValueAt(i , 10);
					}
				}
				//***
				lbl1.setText(FORMATLAMA.doub_3(d1));
				lbl2.setText(FORMATLAMA.doub_3(d2));
				lbl3.setText(FORMATLAMA.doub_3(d3));
				lbl4.setText(FORMATLAMA.doub_3(d4));
				lbl5.setText(FORMATLAMA.doub_3(d5));
				lbl6.setText(FORMATLAMA.doub_3(d6));
				lbl7.setText(FORMATLAMA.doub_3(d7));
				//***

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
		}
	}
	public static void fifo()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			tabbedPane.setSelectedIndex(1);
			grup_cevir() ;

			rs = f_Access.envanter_rapor_fifo(
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_28),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_29),
					FILTRE.textField_72.getText(),FILTRE.textField_73.getText() ,
					FILTRE.textField_71.getText(),FILTRE.textField_74.getText() ,
					"","",qwq1, qwq2, qwq3,qwq4,qwq5,qwq6,qwq7, GLOBAL.setting_oku("PRG_PARA").toString() );

			GRID_TEMIZLE.grid_temizle(table_1);
			GRID_TEMIZLE.grid_temizle(table_2);
			if (!rs.isBeforeFirst() ) {  
				lbl1.setText(FORMATLAMA.doub_3(0));
				lbl2.setText(FORMATLAMA.doub_2(0));
				lbl3.setText(FORMATLAMA.doub_3(0));
				lbl4.setText(FORMATLAMA.doub_2(0));
				lbl5.setText(FORMATLAMA.doub_2(0));
				lbl6.setText(FORMATLAMA.doub_3(0));
				lbl7.setText(FORMATLAMA.doub_2(0));
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
				JTableHeader th = table_1.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;

				//**
				DefaultTableModel mdl = (DefaultTableModel) table_1.getModel();
				double d1 = 0,d2 = 0,d3 = 0,d4 = 0,d5 = 0,d6 = 0,d7 = 0 ;

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
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new SAGA_YANAS());
				tc.setMinWidth(100);

				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new SAGA_YANAS());
				tc.setMinWidth(100);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new SAGA_YANAS());
				tc.setMinWidth(100);

				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(10);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(100);


				for (int i = 0 ; i <= mdl.getRowCount()-1;i++)
				{
					d1 += DecimalFormat.getNumberInstance().parse((String) mdl.getValueAt(i , 3)).doubleValue();
					d2 += (double) mdl.getValueAt(i , 4);
					d3 +=  DecimalFormat.getNumberInstance().parse((String) mdl.getValueAt(i , 5)).doubleValue();
					d4 += (double) mdl.getValueAt(i , 6);
					d5 += (double) mdl.getValueAt(i , 7);
					d6 += DecimalFormat.getNumberInstance().parse((String) mdl.getValueAt(i , 8)).doubleValue();
					d7 += (double) mdl.getValueAt(i , 10);
				}

				//***
				lbl1.setText(FORMATLAMA.doub_3(d1));
				lbl2.setText(FORMATLAMA.doub_2(d2));
				lbl3.setText(FORMATLAMA.doub_3(d3));
				lbl4.setText(FORMATLAMA.doub_2(d4));
				lbl5.setText(FORMATLAMA.doub_2(d5));
				lbl6.setText(FORMATLAMA.doub_3(d6));
				lbl7.setText(FORMATLAMA.doub_2(d7));
				//***

				lbladet.setText(FORMATLAMA.doub_0(mdl.getRowCount()));
				//**
				Dimension dd = th.getPreferredSize();
				dd.height = 30;
				th.setPreferredSize(dd); 
				th.repaint();
				table_1.setRowSelectionInterval(0, 0);
				table_1.setRowHeight(21);

				int lastRow = table_1.convertRowIndexToView(table_1.getRowCount() - 1);
				table_1.scrollRectToVisible(table_1.getCellRect(table_1.getRowCount()-1, 0, true));
				table_1.setRowSelectionInterval(lastRow, lastRow);



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
				table_1.setFont(bigFont);

				//
				if( table_1.getRowCount() > 0 )
				{
					GRID_TEMIZLE.grid_temizle(table_2);
					table_1.setRowSelectionInterval(0, 0);
					fifo_alt_doldur(0);
				}
				else
				{
					GRID_TEMIZLE.grid_temizle(table_2);
				}
				//
			}
		} catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
	private static void fifo_alt_doldur(int satir)
	{
		try 
		{
			DefaultTableModel mdl = (DefaultTableModel) table_1.getModel();
			ResultSet	rs = null;

			rs = f_Access.envanter_rapor_fifo_2(
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_28),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_29),
					FILTRE.textField_72.getText(),FILTRE.textField_73.getText() ,
					FILTRE.textField_71.getText(),FILTRE.textField_74.getText() ,
					"", "", qwq1, qwq2, qwq3,qwq4,qwq5,qwq6,qwq7,mdl.getValueAt(satir , 0).toString(), GLOBAL.setting_oku("PRG_PARA").toString() );

			if (!rs.isBeforeFirst() ) {  
				GRID_TEMIZLE.grid_temizle(table_2);
			} 
			else
			{
				table_2.setModel(DbUtils.resultSetToTableModel(rs));
				JTableHeader th = table_2.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;

				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(80);
				tc.setMaxWidth(80);

				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(70);

				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setCellRenderer(new TARIH());
				tc.setMinWidth(80);

				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(80);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);

				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(80);

				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(80);

				tc = tcm.getColumn(10);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);

				tc = tcm.getColumn(11);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(12);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(13);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);

				table_2.removeColumn(table_2.getColumnModel().getColumn(14));
				Dimension dd = th.getPreferredSize();
				dd.height = 30;
				th.setPreferredSize(dd); 
				th.repaint();
				table_2.setRowSelectionInterval(0, 0);
				table_2.setRowHeight(21);

				String deger;
				String[] parts;
				Font bigFont;
				deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
				deger = deger.substring(1, deger.length()-1);
				parts = deger.split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				table_2.setFont(bigFont);
			}
		} catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}

	}
	public static void lifo()
	{

		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			tabbedPane.setSelectedIndex(2);
			grup_cevir() ;

			rs =f_Access.envanter_rapor_fifo(
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_28),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_29),
					FILTRE.textField_72.getText(),FILTRE.textField_72.getText() ,
					FILTRE.textField_71.getText(),FILTRE.textField_74.getText() ,
					"","",qwq1, qwq2, qwq3,qwq4,qwq5,qwq6,qwq7, GLOBAL.setting_oku("PRG_PARA").toString() );

			GRID_TEMIZLE.grid_temizle(table_3);
			GRID_TEMIZLE.grid_temizle(table_4);
			if (!rs.isBeforeFirst() ) {  
				lbl1.setText(FORMATLAMA.doub_3(0));
				lbl2.setText(FORMATLAMA.doub_2(0));
				lbl3.setText(FORMATLAMA.doub_3(0));
				lbl4.setText(FORMATLAMA.doub_2(0));
				lbl5.setText(FORMATLAMA.doub_2(0));
				lbl6.setText(FORMATLAMA.doub_3(0));
				lbl7.setText(FORMATLAMA.doub_2(0));
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table_3.setModel(DbUtils.resultSetToTableModel(rs));
				JTableHeader th = table_3.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;

				//**
				DefaultTableModel mdl = (DefaultTableModel) table_3.getModel();
				double d1 = 0,d2 = 0,d3 = 0,d4 = 0,d5 = 0,d6 = 0 ;

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
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new SAGA_YANAS());
				tc.setMinWidth(100);

				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new SAGA_YANAS());
				tc.setMinWidth(100);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new SAGA_YANAS());
				tc.setMinWidth(100);

				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(10);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(100);

				for (int i = 0 ; i <= mdl.getRowCount()-1;i++)
				{
					d1 += DecimalFormat.getNumberInstance().parse((String) mdl.getValueAt(i , 3)).doubleValue();
					d2 += (double) mdl.getValueAt(i , 4);
					d3 +=  DecimalFormat.getNumberInstance().parse((String) mdl.getValueAt(i , 5)).doubleValue();
					d4 += (double) mdl.getValueAt(i , 6);
					d5 += (double) mdl.getValueAt(i , 7);
					d6 += DecimalFormat.getNumberInstance().parse((String) mdl.getValueAt(i , 8)).doubleValue();
				}

				DefaultTableModel model = (DefaultTableModel) table_3.getModel();
				model.setValueAt(0.00, 0, 9);
				model.setValueAt(0.00, 0,10);
				//***
				lbl1.setText(FORMATLAMA.doub_3(d1));
				lbl2.setText(FORMATLAMA.doub_2(d2));
				lbl3.setText(FORMATLAMA.doub_3(d3));
				lbl4.setText(FORMATLAMA.doub_2(d4));
				lbl5.setText(FORMATLAMA.doub_2(d5));
				lbl6.setText(FORMATLAMA.doub_3(d6));

				//***

				lbladet.setText(FORMATLAMA.doub_0(mdl.getRowCount()));
				//**
				Dimension dd = th.getPreferredSize();
				dd.height = 30;
				th.setPreferredSize(dd); 
				th.repaint();
				table_3.setRowSelectionInterval(0, 0);
				table_3.setRowHeight(21);

				int lastRow = table_3.convertRowIndexToView(table_3.getRowCount() - 1);
				table_3.scrollRectToVisible(table_3.getCellRect(table_3.getRowCount()-1, 0, true));
				table_3.setRowSelectionInterval(lastRow, lastRow);


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
				table_3.setFont(bigFont);
				// LIFO AL
				BigDecimal maliyet  ;
				maliyet = f_Access.envanter_rapor_lifo(
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_28) , TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_29) ,
						FILTRE.textField_72.getText() , FILTRE.textField_72.getText()  ,
						FILTRE.textField_71.getText() , FILTRE.textField_74.getText(),
						"","",awq1, awq2, awq3,awq4,awq5,awq6,awq7,   GLOBAL.setting_oku("PRG_PARA").toString());

				model.setValueAt(maliyet, 0, 9);
				BigDecimal tutar = maliyet.multiply(new BigDecimal(Double.parseDouble(model.getValueAt(0,8).toString())));
				model.setValueAt(tutar.doubleValue() , 0, 10);

				lbl7.setText(FORMATLAMA.doub_2(tutar.doubleValue()));
				//
				if( table_3.getRowCount() > 0 )
				{
					GRID_TEMIZLE.grid_temizle(table_4);
					table_3.setRowSelectionInterval(0, 0);
					lifo_alt_doldur(0);
				}
				else
				{
					GRID_TEMIZLE.grid_temizle(table_2);
				}
				//
			}
		} 
		catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
	private static void lifo_alt_doldur(int satir)
	{
		try 
		{
			DefaultTableModel mdl = (DefaultTableModel) table_3.getModel();
			ResultSet	rs = null;

			rs = f_Access.envanter_rapor_fifo_2(
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_28),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_29),
					FILTRE.textField_72.getText(),FILTRE.textField_73.getText() ,
					FILTRE.textField_71.getText(),FILTRE.textField_74.getText() ,
					"", "", qwq1, qwq2, qwq3,qwq4,qwq5,qwq6,qwq7,mdl.getValueAt(satir , 0).toString(), GLOBAL.setting_oku("PRG_PARA").toString() );

			if (!rs.isBeforeFirst() ) {  
				GRID_TEMIZLE.grid_temizle(table_4);
			} 
			else
			{
				table_4.setModel(DbUtils.resultSetToTableModel(rs));
				JTableHeader th = table_4.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;

				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(80);
				tc.setMaxWidth(80);

				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(70);

				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setCellRenderer(new TARIH());
				tc.setMinWidth(80);

				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(80);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);

				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(80);

				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);

				tc = tcm.getColumn(10);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);

				tc = tcm.getColumn(11);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);

				tc = tcm.getColumn(12);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);

				tc = tcm.getColumn(13);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);

				table_4.removeColumn(table_4.getColumnModel().getColumn(14));
				Dimension dd = th.getPreferredSize();
				dd.height = 30;
				th.setPreferredSize(dd); 
				th.repaint();
				table_4.setRowSelectionInterval(0, 0);
				table_4.setRowHeight(21);

				String deger;
				String[] parts;
				Font bigFont;
				deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
				deger = deger.substring(1, deger.length()-1);
				parts = deger.split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				table_4.setFont(bigFont);
			}
		} catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}

	}
	private static void grup_cevir()
	{
		try {
			ResultSet	rs = null;
			//***********************ANA GRUP
			if ( FILTRE.comboBox_60.getItemAt(FILTRE.comboBox_60.getSelectedIndex()).equals(""))
			{
				qwq1 = " Like  '%' " ;
				awq1 = "%" ;
			}
			else if  ( FILTRE.comboBox_60.getItemAt(FILTRE.comboBox_60.getSelectedIndex()).equals("Bos Olanlar"))
			{
				qwq1 = " = '' " ;
				awq1 = "''" ;
			}
			else
			{

				rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_60.getItemAt(FILTRE.comboBox_60.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					qwq1 =  "= " + Integer.toString( rs.getInt("AGID_Y") );
					awq1 =   Integer.toString( rs.getInt("AGID_Y")  ) ;
				}

			}
			//***********************ALT GRUP
			if ( FILTRE.comboBox_61.getItemAt(FILTRE.comboBox_61.getSelectedIndex()).equals(""))
			{
				qwq2 = " Like  '%' " ;
				awq2 = "%" ;
			}
			else if  ( FILTRE.comboBox_61.getItemAt(FILTRE.comboBox_61.getSelectedIndex()).equals("Bos Olanlar"))
			{
				qwq2 = " = '' " ;
				awq2 = "''" ;
			}		        else		        {

				rs =f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_61.getItemAt(FILTRE.comboBox_61.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					qwq2 ="=" + Integer.toString( rs.getInt("ALID_Y"));
					awq2 = Integer.toString( rs.getInt("ALID_Y")) ;
				}

			}
			//***********************DEPO
			if ( FILTRE.comboBox_62.getItemAt(FILTRE.comboBox_62.getSelectedIndex()).equals(""))
			{
				qwq3 = " Like  '%' " ;
				awq3 = "%" ;
			}
			else if  ( FILTRE.comboBox_62.getItemAt(FILTRE.comboBox_62.getSelectedIndex()).equals("Bos Olanlar"))
			{
				qwq3 = " = '' " ;
				awq3 = "''" ;
			}		      
			else		      
			{

				rs = f_Access.urun_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN", FILTRE.comboBox_62.getItemAt(FILTRE.comboBox_62.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					qwq3 ="=" + Integer.toString( rs.getInt("DPID_Y"));
					awq3 = Integer.toString( rs.getInt("DPID_Y")) ;
				}

			}
			//** Urun Ana grup
			if ( FILTRE.comboBox_63.getItemAt(FILTRE.comboBox_63.getSelectedIndex()).equals(""))
			{
				qwq6 = " Like  '%' " ;
				awq6 = "%" ;
			}
			else if  ( FILTRE.comboBox_63.getItemAt(FILTRE.comboBox_63.getSelectedIndex()).equals("Bos Olanlar"))
			{
				qwq6 = " = '' " ;
				awq6 = "''" ;
			}
			else
			{

				rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_63.getItemAt(FILTRE.comboBox_63.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					qwq6 = "=" + Integer.toString( rs.getInt("AGID_Y"));
					awq6 = Integer.toString( rs.getInt("AGID_Y")) ;
				}

			}
			//** Urun Alt Grup
			if ( FILTRE.comboBox_64.getItemAt(FILTRE.comboBox_64.getSelectedIndex()).equals(""))
			{
				qwq7 = " Like  '%' " ;
				awq7 = "%" ;
			}
			else if  ( FILTRE.comboBox_64.getItemAt(FILTRE.comboBox_64.getSelectedIndex()).equals("Bos Olanlar"))
			{
				qwq7 = " = '' " ;
				awq7 = "''" ;
			}		        else		      
			{

				rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_64.getItemAt(FILTRE.comboBox_64.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					qwq7 ="=" + Integer.toString( rs.getInt("ALID_Y"));
					awq7 =Integer.toString( rs.getInt("ALID_Y")) ;
				}

			}
			//***
			if (FILTRE.checkBox_2.isSelected())
			{
				qwq4 = "E" ;
				awq4 = "E" ;
			}
			else
			{
				qwq4 = "H";
				awq4 = "H" ;
			}
			if (FILTRE.checkBox_3.isSelected())
			{
				qwq5 = "E" ;
				awq5 = "E" ;
			}
			else
			{
				qwq5 = "H";
				awq5 = "H";
			}

		} catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		} 
	}
	public static void excell_aktar()
	{

		if ( FILTRE.comboBox_66.getItemAt(FILTRE.comboBox_66.getSelectedIndex()).equals("Agirlikli Ortalama"))
		{
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			if (mdl.getRowCount() == 0 )
			{
				 OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Aktarilacak Bilgi Yok.....");
			}
			else
			{
				sat_sayi = table.getRowCount()-1 ;
				write() ;	
			}
		}
		else  if ( FILTRE.comboBox_66.getItemAt(FILTRE.comboBox_66.getSelectedIndex()).equals("FIFO"))
		{
			DefaultTableModel mdl = (DefaultTableModel) table_2.getModel();
			if (mdl.getRowCount() == 0 )
			{
				 OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Aktarilacak Bilgi Yok.....");
			}
			else
			{
				sat_sayi = table_2.getRowCount()-1 ;
				write() ;	
			}
		}
		else

		{
			DefaultTableModel mdl = (DefaultTableModel) table_3.getModel();
			if (mdl.getRowCount() == 0 )
			{
				 OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Aktarilacak Bilgi Yok.....");
			}
			else
			{
				sat_sayi = table_4.getRowCount()-1 ;
				write() ;	
			}
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
				fileChooser.setCurrentDirectory(new java.io.File("C:\\OBS_SISTEM\\"));
				fileChooser.setApproveButtonText("Kaydet");
				fileChooser.setDialogTitle("Excell Kayit");   

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");  
				LocalDateTime now = LocalDateTime.now();  
				String zaman = dtf.format(now)  ;

				File outputfile = new File("Envanter_Rapor");
				fileChooser.setSelectedFile(outputfile);
				int returnVal = fileChooser.showSaveDialog(null);
				if ( returnVal != JFileChooser.APPROVE_OPTION )
				{
					return;
				}
				Progres_Bar_Temizle();
				OBS_MAIN.progressBar.setMaximum(sat_sayi);
				OBS_MAIN.progressBar.setStringPainted(true);
				GuiUtil.setWaitCursor(splitPane,true);
				String uzanti ="";
				File excelFile =  FILE_UZANTI. getSelectedFileWithExtension(fileChooser);
				uzanti  = excelFile.getName().substring(excelFile.getName().lastIndexOf("."));
				if  (uzanti.equals(".xls") )
				{
					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet ;
					if ( FILTRE.comboBox_66.getItemAt(FILTRE.comboBox_66.getSelectedIndex()).equals("Agirlikli Ortalama"))
					{
						sheet = workbook.createSheet("Envanter_Agirlikli_Ortalama");
					}
					else  if ( FILTRE.comboBox_66.getItemAt(FILTRE.comboBox_66.getSelectedIndex()).equals("FIFO"))
					{
						sheet = workbook.createSheet("Envanter_FIFO");
					}
					else
					{
						sheet = workbook.createSheet("Envanter_LIFO");
					}
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
					HSSFCellStyle satirStyle2= workbook.createCellStyle();
					HSSFFont satirFont = workbook.createFont();
					satirFont.setFontName("Arial Narrow");
					satirFont. setFontHeight((short)(10*20));
					satirStyle.setFont(satirFont);
					satirStyle.setAlignment(HorizontalAlignment.RIGHT);
					satirStyle3.setFont(satirFont);
					satirStyle2.setFont(satirFont);
					satirStyle3.setAlignment(HorizontalAlignment.RIGHT);
					satirStyle2.setAlignment(HorizontalAlignment.RIGHT);
					satirStyle3.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
					satirStyle2.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));

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
					Cell baslikname = baslikRow.createCell(0);
					baslikname.setCellValue( BAGLAN.fatDizin.fIRMA_ADI );
					baslikname.setCellStyle(acikStyle);
					Row headerRow = sheet.createRow(1);

					if ( FILTRE.comboBox_66.getItemAt(FILTRE.comboBox_66.getSelectedIndex()).equals("Agirlikli Ortalama"))
					{
						sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -1));
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 || q == 2 )
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
									if (s == 4 || s == 6 || s == 7  || s == 9 || s == 10)
									{
										hname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl.getValueAt(i,s).toString()).doubleValue());
										hname.setCellStyle(satirStyle2);
									}
									else if (s == 3 || s == 5 || s == 8  )
									{
										hname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl.getValueAt(i,s).toString()).doubleValue());
										hname.setCellStyle(satirStyle3);
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
					else if ( FILTRE.comboBox_66.getItemAt(FILTRE.comboBox_66.getSelectedIndex()).equals("FIFO"))    
					{
						DefaultTableModel  mdl1 = (DefaultTableModel) table_1.getModel();
						mdl = (DefaultTableModel) table_2.getModel();
						sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -2));
						//
						for (int q =0;q<= mdl1.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 || q == 2   )
							{
								bname.setCellValue(mdl1.getColumnName(q));
								bname.setCellStyle(headerSolaStyle);
							}
							else
							{
								bname.setCellValue(mdl1.getColumnName(q));
								bname.setCellStyle(headerStyle);
							}
						}
						Row t1satirRow = sheet.createRow(2);
						Cell t1bname = t1satirRow.createCell(0);
						t1bname.setCellValue( mdl1.getValueAt(0,0).toString());
						t1bname.setCellStyle(solaStyle);
						t1bname = t1satirRow.createCell(1);
						t1bname.setCellValue( mdl1.getValueAt(0,1).toString());
						t1bname.setCellStyle(solaStyle);
						t1bname = t1satirRow.createCell(2);
						t1bname.setCellValue( mdl1.getValueAt(0,2).toString());
						t1bname.setCellStyle(solaStyle);
						t1bname = t1satirRow.createCell(3);
						t1bname.setCellValue( DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,3).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle3);
						t1bname = t1satirRow.createCell(4);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,4).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(5);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,5).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle3);
						t1bname = t1satirRow.createCell(6);
						t1bname.setCellValue( DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,6).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(7);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,7).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(8);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,8).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle3);
						t1bname = t1satirRow.createCell(9);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,9).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(10);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,10).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1satirRow = sheet.createRow(3);
						//***************
						headerRow = sheet.createRow(4);
						for (int q =0;q<= mdl.getColumnCount()-2 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 || q == 2 || q ==3 || q == 4 || q == 6 || q == 10  )
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
							Row satirRow = sheet.createRow(i+5);
							for (int s =0;s<= mdl.getColumnCount()-2 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s== 4) 
									{
										DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
										hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
										hname.setCellStyle(solaStyle);
									}
									else  if (s == 5 || s == 8 )
									{
										hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
										hname.setCellStyle(satirStyle3);
									}
									else if (s == 7 || s == 9  ||  s == 11  ||  s == 12 )
									{
										hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
										hname.setCellStyle(satirStyle2);
									}
									else
									{
										hname.setCellValue( mdl.getValueAt(i,s).toString());
										hname.setCellStyle(solaStyle);
									}
								}
							}
						}
						//**********************
					}
					else  // LIFO
					{
						DefaultTableModel  mdl1 = (DefaultTableModel) table_3.getModel();
						mdl = (DefaultTableModel) table_4.getModel();
						sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -2));
						//
						for (int q =0;q<= mdl1.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 || q == 2   )
							{
								bname.setCellValue(mdl1.getColumnName(q));
								bname.setCellStyle(headerSolaStyle);
							}
							else
							{
								bname.setCellValue(mdl1.getColumnName(q));
								bname.setCellStyle(headerStyle);
							}
						}
						Row t1satirRow = sheet.createRow(2);
						Cell t1bname = t1satirRow.createCell(0);
						t1bname.setCellValue( mdl1.getValueAt(0,0).toString());
						t1bname.setCellStyle(solaStyle);
						t1bname = t1satirRow.createCell(1);
						t1bname.setCellValue( mdl1.getValueAt(0,1).toString());
						t1bname.setCellStyle(solaStyle);
						t1bname = t1satirRow.createCell(2);
						t1bname.setCellValue( mdl1.getValueAt(0,2).toString());
						t1bname.setCellStyle(solaStyle);
						t1bname = t1satirRow.createCell(3);
						t1bname.setCellValue( DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,3).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle3);
						t1bname = t1satirRow.createCell(4);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,4).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(5);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,5).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle3);
						t1bname = t1satirRow.createCell(6);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,6).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(7);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,7).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(8);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,8).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle3);
						t1bname = t1satirRow.createCell(9);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,9).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(10);
						t1bname.setCellValue( DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,10).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1satirRow = sheet.createRow(3);

						//
						headerRow = sheet.createRow(4);
						for (int q =0;q<= mdl.getColumnCount()-2 ;q++)
						{

							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 || q == 2 || q ==3 || q == 4 || q == 6 || q == 10  )
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
							Row satirRow = sheet.createRow(i+5);
							for (int s =0;s<= mdl.getColumnCount()-2 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s== 4) 
									{
										DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
										hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
										hname.setCellStyle(satirStyle);
									}
									else  if (s == 5 || s == 8 )
									{
										hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
										hname.setCellStyle(satirStyle3);
									}
									else if (s == 7 || s == 9  ||  s == 11  ||  s == 12 )
									{
										hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
										hname.setCellStyle(satirStyle2);
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
					XSSFSheet sheet ;
					if ( FILTRE.comboBox_66.getItemAt(FILTRE.comboBox_66.getSelectedIndex()).equals("Agirlikli Ortalama"))
					{
						sheet = workbook.createSheet("Envanter_Agirlikli_Ortalama");
					}
					else  if ( FILTRE.comboBox_66.getItemAt(FILTRE.comboBox_66.getSelectedIndex()).equals("FIFO"))
					{
						sheet = workbook.createSheet("Envanter_FIFO");
					}
					else
					{
						sheet = workbook.createSheet("Envanter_LIFO");
					}
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
					satirStyle3.setAlignment(HorizontalAlignment.RIGHT);
					satirStyle2.setAlignment(HorizontalAlignment.RIGHT);
					satirStyle3.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
					satirStyle2.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));

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

					Cell baslikname = baslikRow.createCell(0);

					baslikname.setCellValue(BAGLAN.fatDizin.fIRMA_ADI);
					baslikname.setCellStyle(acikStyle);
					Row headerRow = sheet.createRow(1);


					if ( FILTRE.comboBox_66.getItemAt(FILTRE.comboBox_66.getSelectedIndex()).equals("Agirlikli Ortalama"))
					{
						sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -1));
						for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 || q == 2 )
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
									if (s == 4 || s == 6 || s == 7  || s == 9 || s == 10)
									{

										hname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl.getValueAt(i,s).toString()).doubleValue());
										hname.setCellStyle(satirStyle2);
									}
									else if (s == 3 || s == 5 || s == 8  )
									{
										hname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl.getValueAt(i,s).toString()).doubleValue());
										hname.setCellStyle(satirStyle3);
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
					else if ( FILTRE.comboBox_66.getItemAt(FILTRE.comboBox_66.getSelectedIndex()).equals("FIFO"))    
					{
						DefaultTableModel  mdl1 = (DefaultTableModel) table_1.getModel();
						mdl = (DefaultTableModel) table_2.getModel();
						sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -2));
						//
						for (int q =0;q<= mdl1.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 || q == 2   )
							{
								bname.setCellValue(mdl1.getColumnName(q));
								bname.setCellStyle(headerSolaStyle);
							}
							else
							{
								bname.setCellValue(mdl1.getColumnName(q));
								bname.setCellStyle(headerStyle);
							}
						}
						Row t1satirRow = sheet.createRow(2);
						Cell t1bname = t1satirRow.createCell(0);
						t1bname.setCellValue( mdl1.getValueAt(0,0).toString());
						t1bname.setCellStyle(solaStyle);
						t1bname = t1satirRow.createCell(1);
						t1bname.setCellValue( mdl1.getValueAt(0,1).toString());
						t1bname.setCellStyle(solaStyle);
						t1bname = t1satirRow.createCell(2);
						t1bname.setCellValue( mdl1.getValueAt(0,2).toString());
						t1bname.setCellStyle(solaStyle);
						t1bname = t1satirRow.createCell(3);
						t1bname.setCellValue( DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,3).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle3);
						t1bname = t1satirRow.createCell(4);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,4).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(5);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,5).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle3);
						t1bname = t1satirRow.createCell(6);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,6).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(7);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,7).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(8);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,8).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle3);
						t1bname = t1satirRow.createCell(9);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,9).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(10);
						t1bname.setCellValue( DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,10).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1satirRow = sheet.createRow(3);
						//***************
						headerRow = sheet.createRow(4);
						for (int q =0;q<= mdl.getColumnCount()-2 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 || q == 2 || q ==3 || q == 4 || q == 6 || q == 10  )
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
							Row satirRow = sheet.createRow(i+5);
							for (int s =0;s<= mdl.getColumnCount()-2 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s== 4) 
									{
										DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
										hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
										hname.setCellStyle(solaStyle);
									}
									else  if (s == 5 || s == 8 )
									{
										hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
										hname.setCellStyle(satirStyle3);
									}
									else if (s == 7 || s == 9  ||  s == 11  ||  s == 12 )
									{
										hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
										hname.setCellStyle(satirStyle2);
									}
									else
									{
										hname.setCellValue( mdl.getValueAt(i,s).toString());
										hname.setCellStyle(solaStyle);
									}
								}
							}
						}
						//**********************
					}
					else  // LIFO
					{
						DefaultTableModel  mdl1 = (DefaultTableModel) table_3.getModel();
						mdl = (DefaultTableModel) table_4.getModel();
						sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -2));
						//
						for (int q =0;q<= mdl1.getColumnCount()-1 ;q++)
						{
							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 || q == 2   )
							{
								bname.setCellValue(mdl1.getColumnName(q));
								bname.setCellStyle(headerSolaStyle);
							}
							else
							{
								bname.setCellValue(mdl1.getColumnName(q));
								bname.setCellStyle(headerStyle);
							}
						}
						Row t1satirRow = sheet.createRow(2);
						Cell t1bname = t1satirRow.createCell(0);
						t1bname.setCellValue( mdl1.getValueAt(0,0).toString());
						t1bname.setCellStyle(solaStyle);
						t1bname = t1satirRow.createCell(1);
						t1bname.setCellValue( mdl1.getValueAt(0,1).toString());
						t1bname.setCellStyle(solaStyle);
						t1bname = t1satirRow.createCell(2);
						t1bname.setCellValue( mdl1.getValueAt(0,2).toString());
						t1bname.setCellStyle(solaStyle);
						t1bname = t1satirRow.createCell(3);
						t1bname.setCellValue( DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,3).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle3);
						t1bname = t1satirRow.createCell(4);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,4).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(5);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,5).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle3);
						t1bname = t1satirRow.createCell(6);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,6).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(7);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,7).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(8);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,8).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle3);
						t1bname = t1satirRow.createCell(9);
						t1bname.setCellValue(DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,9).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1bname = t1satirRow.createCell(10);
						t1bname.setCellValue( DecimalFormat.getNumberInstance().parse(mdl1.getValueAt(0,10).toString()).doubleValue());
						t1bname.setCellStyle(satirStyle2);
						t1satirRow = sheet.createRow(3);

						//
						headerRow = sheet.createRow(4);
						for (int q =0;q<= mdl.getColumnCount()-2 ;q++)
						{

							Cell bname = headerRow.createCell(q);
							if (q == 0 || q ==1 || q == 2 || q ==3 || q == 4 || q == 6 || q == 10  )
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
							Row satirRow = sheet.createRow(i+5);
							for (int s =0;s<= mdl.getColumnCount()-2 ;s++)
							{
								Cell hname = satirRow.createCell(s);
								if ( mdl.getValueAt(i, s) != null)
								{
									if (s== 4) 
									{
										DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
										hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
										hname.setCellStyle(satirStyle);
									}
									else  if (s == 5 || s == 8 )
									{
										hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
										hname.setCellStyle(satirStyle3);
									}
									else if (s == 7 || s == 9  ||  s == 11  ||  s == 12 )
									{
										hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
										hname.setCellStyle(satirStyle2);
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
