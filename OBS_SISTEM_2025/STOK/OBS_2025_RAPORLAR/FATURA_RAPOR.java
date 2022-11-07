package OBS_2025_RAPORLAR;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.FILE_UZANTI;
import OBS_2025.FILTRE;
import OBS_C_2025.FORMATLAMA;

import OBS_C_2025.GRID_TEMIZLE;
import OBS_2025.GuiUtil;
import OBS_2025.OBS_MAIN;

import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
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

import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FATURA_RAPOR extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(oac._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	
	private static JTable table;
	private static JTable table_1;
	private static String qwq1 ="" ;
	private static String qwq2  = "";
	private static String qwq3  = "";
	private static String kjk = "";
	private static String ask = "";
	private static JLabel lbladet;
	private static JLabel lblana;
	public static JSplitPane splitPane ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FATURA_RAPOR frame = new FATURA_RAPOR();
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
	public FATURA_RAPOR() {
		setTitle("FATURA RAPORLAMA");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 1000, 600);
		
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
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent lse) {
			        if (!lse.getValueIsAdjusting()) {
			        	 DefaultTableModel model = (DefaultTableModel)table.getModel();
			        	 if (model.getRowCount() == 0) return ;
			        	 if (table.getSelectedRow()  < 0) return;
			        	 table.setCursor(oac.WAIT_CURSOR);
			        	 detay_doldur(model.getValueAt(table.getSelectedRow() , 0).toString(),model.getValueAt(table.getSelectedRow() , 1).toString());
			        	 table.setCursor(oac.DEFAULT_CURSOR);
			        }
			    }
			});
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 200));
		panel.setMaximumSize(new Dimension(0, 200));
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setDividerSize(0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_1.setLeftComponent(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setGridColor(oac.gridcolor);
		table_1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(table_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_1.setMinimumSize(new Dimension(0, 25));
		panel_1.setMaximumSize(new Dimension(0, 25));
		splitPane_1.setRightComponent(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Kayit Sayisi :");
		label.setBounds(170, 5, 71, 14);
		panel_1.add(label);
		
		lbladet = new JLabel("0");
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setBounds(250, 5, 47, 14);
		panel_1.add(lbladet);
		
		lblana = new JLabel("0");
		lblana.setHorizontalAlignment(SwingConstants.LEFT);
		lblana.setForeground(new Color(0, 0, 128));
		lblana.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblana.setBounds(110, 5, 40, 14);
		panel_1.add(lblana);
		
		JLabel lblFaturaSayisi = new JLabel("Fatura Sayisi :");
		lblFaturaSayisi.setBounds(10, 5, 90, 14);
		panel_1.add(lblFaturaSayisi);
	}
	public static void yenile()
	{
		try {
		String deger;
		String[] parts;
		Font bigFont;
			deger = OBS_C_2025.GLOBAL.setting_oku("STK_RAPORLAMA").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table.setFont(bigFont);
			table_1.setFont(bigFont);
		if ( FILTRE.comboBox_7.getItemAt(FILTRE.comboBox_7.getSelectedIndex()).equals("Fatura No"))
		{
		            filtrele();
		}
		else if ( FILTRE.comboBox_7.getItemAt(FILTRE.comboBox_7.getSelectedIndex()).equals("Fatura_No_Tarih"))    
		{
		         filtre_fat_tarih();
		}
		else
		{
	         filtre_cari_kod();
		}
	} catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Fatura Raporlama", JOptionPane.PLAIN_MESSAGE);
	}
	}
	public static void filtrele()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			grup_cevir() ;
			
				rs = f_Access.fat_rapor(FILTRE.textField_6.getText(),FILTRE.txtZzzzzzzzzz.getText() ,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_12),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_13),
						FILTRE.textField_7.getText(),FILTRE.txtZzzzzzzzzzzz.getText() ,
						FILTRE.textField_12.getText(),FILTRE.textField_13.getText() ,
						FILTRE.textField_9.getText(),FILTRE.textField_15.getText() ,
						 qwq1, qwq2, qwq3, kjk,
						 FILTRE.textField_10.getText(),FILTRE.txtZzzzzzzzzz_1.getText() ,
						 FILTRE.textField_8.getText(),FILTRE.txtZzzzzzzzzzzz_1.getText() ,
						 FILTRE.textField_11.getText(),FILTRE.txtZzz.getText() );
			
			
			GRID_TEMIZLE.grid_temizle(table);
			GRID_TEMIZLE.grid_temizle(table_1);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
				lblana.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			JTableHeader th = table.getTableHeader();
			
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(70);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setCellRenderer(new TARIH());
				tc.setMinWidth(80);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(130);
				
				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(150);
				
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
				lblana.setText(FORMATLAMA.doub_0(table.getRowCount()));
				//***
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				double miktar = 0,tutar = 0,isk_tutar=0 ;
				for (int i = 0 ; i <= mdl.getRowCount()-1;i++)
				{
					 miktar  += (double) mdl.getValueAt(i , 6);
					 tutar  += (double) mdl.getValueAt(i , 7);
					 isk_tutar  += (double) mdl.getValueAt(i , 8);
				}
				Vector<Object> data = new Vector<Object>();
				data.add("");
				data.add("");
				data.add(new Date());
				data.add("");
				data.add("");
				data.add("");
				data.add(miktar);
				data.add(tutar);
				data.add(isk_tutar);
				mdl.addRow(data);

				genislik(940);
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Fatura Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void genislik(int gen)
	{
		for(int i=0;i<OBS_MAIN.desktopPane.getAllFrames().length;i++)
        {   
        JInternalFrame frame=(JInternalFrame) OBS_MAIN.desktopPane.getComponent(i);
        String tit = frame.getTitle();
        if (tit.equals("FATURA RAPORLAMA") )
        	{
        	frame.setSize(gen,600);
        	frame.repaint();
         	}
        }
	}
	private static void grup_cevir()
	{
		try {
		ResultSet	rs = null;
		//***********************ANA GRUP
		if ( FILTRE.comboBox_3.getItemAt(FILTRE.comboBox_3.getSelectedIndex()).equals(""))
		{
            qwq1 = " Like  '%' " ;
		}
        else if  ( FILTRE.comboBox_3.getItemAt(FILTRE.comboBox_3.getSelectedIndex()).equals("Bos Olanlar"))
        {
            qwq1 = " = '' " ;
        }
        else
        {
        	
    			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_3.getItemAt(FILTRE.comboBox_3.getSelectedIndex()));
    			if (!rs.isBeforeFirst() ) {
    			}
    			else
    			{
    				rs.next();
    				qwq1 = "=" + Integer.toString( rs.getInt("AGID_Y"));
    			}
    		
        }
		//***********************ALT GRUP
				if ( FILTRE.comboBox_4.getItemAt(FILTRE.comboBox_4.getSelectedIndex()).equals(""))
				{
		            qwq2 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_4.getItemAt(FILTRE.comboBox_4.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq2 = " = '' " ;
		        }		        else		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_4.getItemAt(FILTRE.comboBox_4.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq2 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		
		        }
				//***********************DEPO
				if ( FILTRE.comboBox_5.getItemAt(FILTRE.comboBox_5.getSelectedIndex()).equals(""))
				{
		            qwq3 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_5.getItemAt(FILTRE.comboBox_5.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq3 = " = '' " ;
		        }		        else		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN", FILTRE.comboBox_5.getItemAt(FILTRE.comboBox_5.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq3 = "=" + Integer.toString( rs.getInt("DPID_Y"));
		    			}
		    		
		        }
				//***********************TUR
					if ( FILTRE.comboBox_6.getItemAt(FILTRE.comboBox_6.getSelectedIndex()).equals("GIREN"))
				{
					kjk = "G" ;
				}
				else 	if ( FILTRE.comboBox_6.getItemAt(FILTRE.comboBox_6.getSelectedIndex()).equals("CIKAN"))
				{
					kjk = "C" ;
				}
				else 	if ( FILTRE.comboBox_6.getItemAt(FILTRE.comboBox_6.getSelectedIndex()).equals(""))
				{
					kjk = "" ;
				}	
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Fatura Raporlama", JOptionPane.ERROR_MESSAGE);
		} 
	}
	public static void filtre_fat_tarih()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			grup_cevir() ;
			//************* hangi adres ****************
            String qw1, qw2, qw3,c_yer;
           if ( FILTRE.comboBox_8.getItemAt(FILTRE.comboBox_8.getSelectedIndex()).equals("Cari_Firma"))
           {
                c_yer = "[OK_Car" + BAGLAN.cariDizin.kOD + "]" ;
                qw1 = " ,(SELECT   UNVAN FROM " + c_yer + ".[dbo].[HESAP] WHERE HESAP.HESAP = FATURA.Cari_Firma  ) as Unvan " ;
                qw2 = " ,(SELECT   VERGI_NO FROM " + c_yer + ".[dbo].[HESAP_DETAY] WHERE HESAP_DETAY.D_HESAP = FATURA.Cari_Firma  ) as Vergi_No " ;
                qw3 = "Fatura_No,Gir_Cik,Tarih, Cari_Firma" ;
           }
            else
            {
                c_yer = "[OK_Adr" + BAGLAN.cariDizin.kOD + "]" ;
                qw1 = " ,(SELECT   Adi FROM " + c_yer + ".[dbo].[Adres] WHERE Adres.M_Kodu = FATURA.Adres_Firma  ) as Unvan " ;
                qw2 = " ,(SELECT   Vergi_No FROM " + c_yer + ".[dbo].[Adres] WHERE Adres.M_Kodu = FATURA.Adres_Firma  ) as Vergi_No " ;
                qw3 = "Fatura_No,Gir_Cik,Tarih, Adres_Firma" ;
            }
			
				rs = f_Access. fat_rapor_fat_tar(FILTRE.textField_6.getText(),FILTRE.txtZzzzzzzzzz.getText() ,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_12),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_13),
						FILTRE.textField_7.getText(),FILTRE.txtZzzzzzzzzzzz.getText() ,
						FILTRE.textField_12.getText(),FILTRE.textField_13.getText() ,
						FILTRE.textField_9.getText(),FILTRE.textField_15.getText() ,
						 qwq1, qwq2, qwq3, kjk,
						 FILTRE.textField_10.getText(),FILTRE.txtZzzzzzzzzz_1.getText() ,
						 FILTRE.textField_8.getText(),FILTRE.txtZzzzzzzzzzzz_1.getText() ,qw1,
						 FILTRE.textField_11.getText(),FILTRE.txtZzz.getText(),qw2,qw3 );
			
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
				lblana.setText("0");
			} 
			else
			{
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(60);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setCellRenderer(new TARIH());
				tc.setMinWidth(80);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(300);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(80);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(120);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(130);
				
				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(130);
				
				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(150);
				
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
				lblana.setText(FORMATLAMA.doub_0(table.getRowCount()));
				//***
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				double miktar = 0,tutar = 0,isk_tutar=0 ,db1=0,db2=0;
				for (int i = 0 ; i <= mdl.getRowCount()-1;i++)
				{
					 miktar  += (double) mdl.getValueAt(i , 5);
					 tutar  += (double) mdl.getValueAt(i , 6);
					 isk_tutar  += (double) mdl.getValueAt(i , 7);
					 db1  += (double) mdl.getValueAt(i ,8);
					 db2  += (double) mdl.getValueAt(i , 9);
				}
				Vector<Object> data = new Vector<Object>();
				data.add("");
				data.add("");
				data.add(new Date());
				data.add("");
				data.add("");
				data.add(miktar);
				data.add(tutar);
				data.add(isk_tutar);
				data.add(db1);
				data.add(db2);
				mdl.addRow(data);
				genislik(1300);
				//***
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Fatura Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void filtre_cari_kod()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			grup_cevir() ;
			//************* hangi adres ****************
            String qw1, qw2, c_yer;
           if ( FILTRE.comboBox_8.getItemAt(FILTRE.comboBox_8.getSelectedIndex()).equals("Cari_Firma"))
           {
                c_yer = "[OK_Car" + BAGLAN.adrDizin.kOD+ "]" ;
                qw1 = " ,(SELECT TOP 1  UNVAN FROM " + c_yer + ".[dbo].[HESAP] WHERE HESAP.HESAP = FATURA.Cari_Firma  ) as Unvan " ;
                qw2 =" Cari_Firma" ;
           }
            else
            {
                c_yer = "[OK_Adr" + BAGLAN.adrDizin.kOD + "]" ;
                qw1 = " ,(SELECT Top 1  Adi FROM " + c_yer + ".[dbo].[Adres] WHERE Adres.M_Kodu = FATURA.Adres_Firma  ) as Unvan " ;
                qw2 = " Adres_Firma" ;
            }
			
				rs = f_Access. fat_rapor_cari_kod(FILTRE.textField_6.getText(),FILTRE.txtZzzzzzzzzz.getText() ,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_12),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_13),
						FILTRE.textField_7.getText(),FILTRE.txtZzzzzzzzzzzz.getText() ,
						FILTRE.textField_12.getText(),FILTRE.textField_13.getText() ,
						FILTRE.textField_9.getText(),FILTRE.textField_15.getText() ,
						 qwq1, qwq2, qwq3, kjk,
						 FILTRE.textField_10.getText(),FILTRE.txtZzzzzzzzzz_1.getText() ,
						 FILTRE.textField_8.getText(),FILTRE.txtZzzzzzzzzzzz_1.getText() ,qw1,
						 FILTRE.textField_11.getText(),FILTRE.txtZzz.getText(),qw2 );
			
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
				lblana.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(60);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(300);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(130);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(130);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(130);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(130);
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
				lblana.setText(FORMATLAMA.doub_0(table.getRowCount()));
				//***
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				double miktar = 0,tutar = 0,isk_tutar=0 ,db1=0,db2=0;
				for (int i = 0 ; i <= mdl.getRowCount()-1;i++)
				{
					 miktar  += (double) mdl.getValueAt(i , 3);
					 tutar  += (double) mdl.getValueAt(i , 4);
					 isk_tutar  += (double) mdl.getValueAt(i , 5);
					 db1  += (double) mdl.getValueAt(i ,6);
					 db2  += (double) mdl.getValueAt(i , 7);
				}
				Vector<Object> data = new Vector<Object>();
				data.add("");
				data.add("");
				data.add("");
				data.add(miktar);
				data.add(tutar);
				data.add(isk_tutar);
				data.add(db1);
				data.add(db2);
				mdl.addRow(data);
				genislik(1130);
				//***
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			}
		} 
		catch (Exception ex) 
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Fatura Raporlama",  JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void detay_doldur(String fatno,String har)
	{
		long startTime = System.currentTimeMillis(); 
		
		try {
			ResultSet	rs = null;
		if (har.equals("Satis"))
		{
			ask = "C";
		}
		else if (har.equals("Alis"))
		{
			ask = "G";
		}
		else
		{
			return;
		}
			
				rs =f_Access.fat_detay_rapor(fatno,ask);
			
			GRID_TEMIZLE.grid_temizle(table_1);
			if (! rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			
			JTableHeader th = table_1.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(80);
			
			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(200);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(3,false));
			tc.setMinWidth(80);
			
			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);
			
			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(80);
			
			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);
			
			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(80);
			
			tc = tcm.getColumn(7);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(80);
			
			tc = tcm.getColumn(8);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(100);
			
			tc = tcm.getColumn(9);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(100);
			
			tc = tcm.getColumn(10);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(90);
			
			tc = tcm.getColumn(11);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(90);
			
			tc = tcm.getColumn(12);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(90);
			
			tc = tcm.getColumn(13);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(110);
			
			tc = tcm.getColumn(14);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(130);
			
			tc = tcm.getColumn(15);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(130);
			
			tc = tcm.getColumn(16);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(130);
			
			tc = tcm.getColumn(17);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);
			
			tc = tcm.getColumn(18);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);
			
			tc = tcm.getColumn(19);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);
			
			tc = tcm.getColumn(20);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(100);
			
			tc = tcm.getColumn(21);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);
			
			tc = tcm.getColumn(22);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(80);
			
			tc = tcm.getColumn(23);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(80);
			
			DefaultTableModel mdl = (DefaultTableModel) table_1.getModel();
			lbladet.setText(FORMATLAMA.doub_0(mdl.getRowCount()));
			//**
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table_1.setRowSelectionInterval(0, 0);
				table_1.setRowHeight(21);
				table_1.setRowSelectionInterval(0,0);
				
				table_1.setSelectionBackground(Color.PINK);
				table_1.setSelectionForeground(Color.BLUE);
				lbladet.setText(FORMATLAMA.doub_0(mdl.getRowCount()));
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Irsaliye Detay", JOptionPane.ERROR_MESSAGE);
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
	///// Progres Bsr olayi
		Runnable runner = new Runnable()
	    { public void run() {
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
		   
		  File outputfile = new File("Fatura_Rapor");
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
					   HSSFSheet sheet = workbook.createSheet("Fatura_Raporlama");
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
						 
						 if ( FILTRE.comboBox_7.getItemAt(FILTRE.comboBox_7.getSelectedIndex()).equals("Fatura No"))
							{
								for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
								{
									 Cell bname = headerRow.createCell(q);
									 if (q == 6 || q ==7 || q == 8 )
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
											   if (s == 2  )
												 {
												   DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
													  hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
													  hname.setCellStyle(solaStyle);
												 }
										   else if (s == 6  )
												 {
											   hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
											   hname.setCellStyle(satirStyle);
												 }
											   else if (s == 7 || s == 8  )
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
							else if ( FILTRE.comboBox_7.getItemAt(FILTRE.comboBox_7.getSelectedIndex()).equals("Fatura_No_Tarih"))    
							{
								for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
								{
									 Cell bname = headerRow.createCell(q);
									 if (q == 0 || q ==1 || q == 2 || q ==3 || q == 4 )
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
											   if (s== 2) 
											   {
											   DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
												  hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
												  hname.setCellStyle(satirStyle);
											   }
											   else  if (s == 5  )
												 {
												   hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
												   hname.setCellStyle(satirStyle);
												 }
										   else if (s == 6 || s == 7 || s == 8 ||  s == 9  )
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
											   if (s == 3  )
												 {
												   hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
												   hname.setCellStyle(satirStyle);
												 }
										   else if (s == 4 || s == 5 || s == 6 ||  s == 7 || s == 8  )
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
					 
					 if ( FILTRE.comboBox_7.getItemAt(FILTRE.comboBox_7.getSelectedIndex()).equals("Fatura No"))
						{
							for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
							{
								 Cell bname = headerRow.createCell(q);
								 if (q == 6 || q ==7 || q == 8 )
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
										   if (s == 2  )
											 {
											   DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
												  hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
												  hname.setCellStyle(solaStyle);
											 }
									   else if (s == 6  )
											 {
										   hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
										   hname.setCellStyle(satirStyle);
											 }
										   else if (s == 7 || s == 8  )
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
						else if ( FILTRE.comboBox_7.getItemAt(FILTRE.comboBox_7.getSelectedIndex()).equals("Fatura_No_Tarih"))    
						{
							for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
							{
								 Cell bname = headerRow.createCell(q);
								 if (q == 0 || q ==1 || q == 2 || q ==3 || q == 4 )
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
										   if (s== 2) 
										   {
										   DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
											  hname.setCellValue(	format.format(mdl.getValueAt(i ,s))) ;
											  hname.setCellStyle(satirStyle);
										   }
										   else  if (s == 5  )
											 {
											   hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
											   hname.setCellStyle(satirStyle);
											 }
									   else if (s == 6 || s == 7 || s == 8 ||  s == 9  )
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
										   if (s == 3  )
											 {
											   hname.setCellValue(  FORMATLAMA.doub_3(Double.parseDouble( mdl.getValueAt(i,s).toString())));
											   hname.setCellStyle(satirStyle);
											 }
									   else if (s == 4 || s == 5 || s == 6 ||  s == 7 || s == 8  )
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
