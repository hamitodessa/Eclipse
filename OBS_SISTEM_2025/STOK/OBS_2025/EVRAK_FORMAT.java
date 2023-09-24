package OBS_2025;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import OBS_C_2025.Degisken;
import OBS_C_2025.DoubleEditor;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.TABLO_RENDERER;

import java.awt.Font;
import java.awt.Color;

@SuppressWarnings({"serial","static-access"})
public class EVRAK_FORMAT extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);

	
	private static JTable table;
	private static JTable table_1;
	private static JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EVRAK_FORMAT frame = new EVRAK_FORMAT();
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
	public EVRAK_FORMAT() {
		setResizable(true);
		setTitle("EVRAK FORMATLAMA");
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,356, 600);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(0, 0, 128));
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Irsaliye Bilgileri", null, scrollPane, null);
		
		table = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {  
				 switch (column) {
		         case 0:
		             return false;
		         default:
		             return true;
		      }
				}
		};
		table.setGridColor(oac.gridcolor);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Tarih", 0, 0},
				{"Sevk Tarih", 0, 0},
				{"Firma Kodu", 0, 0},
				{"Firma Unvani", 0, 0},
				{"Vergi Dairesi", 0, 0},
				{"Vergi No", 0, 0},
				{"Gidecegi Yer", 0, 0},
				{"Not 1", 0, 0},
				{"Not 2", 0, 0},
				{"Not 3", 0, 0},
				{"Irsaliye Baslik", 0, 0},
				{"Barkod", 0, 0},
				{"Urun Kodu", 0, 0},
				{"Urun Adi", 0, 0},
				{"Depo", 0, 0},
				{"Simge", 0, 0},
				{"Birim Fiat", 0, 0},
				{"Iskonto", 0, 0},
				{"Miktar", 0, 0},
				{"Kdv", 0, 0},
				{"Tutar", 0, 0},
				{"Tutar Toplam", 0, 0},
				{"Iskonto Toplami", 0, 0},
				{"Bakiye", 0, 0},
				{"Kdv Toplami", 0, 0},
				{"Belge Toplami", 0, 0},
				{"Yazi Ile", 0, 0},
				{"Irsaliye Alt Bolum", 0, 0},
			},
			new String[] {
				"Ozellik", "Satir", "Sutun"
			}
		));
		TableColumn col ;
		   
	    col = table.getColumnModel().getColumn(0);
		col.setMinWidth(160);
		col.setMaxWidth(160);
	    col.setHeaderRenderer(new SOLA());
	    
	    col = table.getColumnModel().getColumn(1);
		col.setMinWidth(80);
		col.setMaxWidth(80);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
	    col.setHeaderRenderer(new SAGA());
	    
	    col = table.getColumnModel().getColumn(2);
		col.setMinWidth(80);
		col.setMaxWidth(80);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
	    col.setHeaderRenderer(new SAGA());
		
	    JTableHeader th = table.getTableHeader();
	    Dimension dd = table.getPreferredSize();
	    dd.height = 30;
	    th.setPreferredSize(dd); 
		table.setRowSelectionInterval(0, 0);
		table.setRowHeight(22);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Fatura Bilgileri", null, scrollPane_1, null);
		
		table_1 = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {  
				 switch (column) {
		         case 0:
		             return false;
		         default:
		             return true;
		      }
				}
		};
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
					{"Tarih", 0, 0},
					{"Firma Kodu", 0, 0},
					{"Firma Unvani", 0, 0},
					{"Vergi Dairesi", 0, 0},
					{"Vergi No", 0, 0},
					{"Gidecegi Yer", 0, 0},
					{"Irsaliye Tarihi", 0, 0},
					{"Irsaliye No", 0, 0},
					{"Not 3", 0, 0},
					{"Fatura Baslik", 0, 0},
					{"Barkod", 0, 0},
					{"Urun Kodu", 0, 0},
					{"Urun Adi", 0, 0},
					{"Depo", 0, 0},
					{"Izahat", 0, 0},
					{"Simge", 0, 0},
					{"Birim Fiat", 0, 0},
					{"Iskonto", 0, 0},
					{"Miktar", 0, 0},
					{"Kdv", 0, 0},
					{"Tutar", 0, 0},
					{"Tutar Toplam", 0, 0},
					{"Iskonto Toplami", 0, 0},
					{"Bakiye", 0, 0},
					{"Kdv Toplami", 0, 0},
					{"Belge Toplami", 0, 0},
					{"Tevkifat Orani", 0, 0},
					{"Al Tar Tev Ed Kdv", 0, 0},
					{"Tev Dah Top tutar", 0, 0},
					{"Beyan Edilen Kdv", 0, 0},
					{"Tev Har Top Tuatr", 0, 0},
					{"Yazi Ile", 0, 0},
					{"Tev Kase", 0, 0},
					{"Fatura Alt Bolum", 0, 0},
				},
				new String[] {
					"Ozellik", "Satir", "Sutun"
				}
			));
				   
		table_1.setGridColor(oac.gridcolor);
	    col = table_1.getColumnModel().getColumn(0);
		col.setMinWidth(160);
		col.setMaxWidth(160);
	    col.setHeaderRenderer(new SOLA());
	    
	    col = table_1.getColumnModel().getColumn(1);
		col.setMinWidth(80);
		col.setMaxWidth(80);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
	    col.setHeaderRenderer(new SAGA());
	    
	    col =table_1.getColumnModel().getColumn(2);
		col.setMinWidth(80);
		col.setMaxWidth(80);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
	    col.setHeaderRenderer(new SAGA());
		
	    th =table_1.getTableHeader();
	    dd = table_1.getPreferredSize();
	    dd.height = 30;
	    th.setPreferredSize(dd); 
	    table_1.setRowSelectionInterval(0, 0);
	    table_1.setRowHeight(22);
	    table_1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Fatura Dovizli", null, scrollPane_2, null);
		
		table_2 = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {  
				 switch (column) {
		         case 0:
		             return false;
		         default:
		             return true;
		      }
				}
		};
		table_2.setGridColor(oac.gridcolor);
		table_2.setModel(new DefaultTableModel(
				new Object[][] {
					{"Tutar Toplami", 0, 0},
					{"Iskonto Toplami", 0, 0},
					{"KDV Toplami", 0, 0},
					{"Bakiye", 0, 0},
					{"Belge Toplami", 0, 0},
					{"KDV Toplami Yerli", 0, 0},
					{"Bakiye Yerli", 0, 0},
					{"Belge Toplami Yerli", 0, 0},
					{"Odenecek Tutar Yerli", 0, 0},
					{".....", 0, 0},
				},
				new String[] {
					"Ozellik", "Satir", "Sutun"
				}
			));
				   
	    col =table_2.getColumnModel().getColumn(0);
		col.setMinWidth(160);
		col.setMaxWidth(160);
	    col.setHeaderRenderer(new SOLA());
	    
	    col =table_2.getColumnModel().getColumn(1);
		col.setMinWidth(80);
		col.setMaxWidth(80);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
	    col.setHeaderRenderer(new SAGA());
	    
	    col =table_2.getColumnModel().getColumn(2);
		col.setMinWidth(80);
		col.setMaxWidth(80);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
	    col.setHeaderRenderer(new SAGA());
		
	    th =table_2.getTableHeader();
	    dd =table_2.getPreferredSize();
	    dd.height = 30;
	    th.setPreferredSize(dd); 
	    table_2.setRowSelectionInterval(0, 0);
	    table_2.setRowHeight(22);
	    table_2.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane_2.setViewportView(table_2);

		getContentPane().setCursor(oac.WAIT_CURSOR);
		doldur();
		getContentPane().setCursor(oac.DEFAULT_CURSOR);
	}
	private void doldur()
	{
		try {
			 ArrayList<Degisken>  students = new ArrayList<Degisken>();
			for (int i = 1; i <= 44;i++)
			{
				Degisken irs1 = new Degisken();
				irs1.irs_sut = 0 ;
				irs1.irs_sat = 0;
				irs1.fat_sut =0 ;
				irs1.fat_sat = 0;
				 students.add(irs1);
			}
		ResultSet rs =null ;
 		 rs = f_Access.parametre_oku("IRS_EVRAK_FORMAT","SUTUN");
	
		 rs.next();
		 ResultSetMetaData rsmd = rs.getMetaData();
		
		 for (int i =0 ; i <= rsmd.getColumnCount()  - 13;i++)
		 {
			 students.get(i).irs_sut= rs.getDouble(i+2);
		 }
		 /////*** Irsaliye SATIR
		 rs =null ;
		
 		 rs = f_Access.parametre_oku("IRS_EVRAK_FORMAT","SATIR");
		 rs.next();
		rsmd = rs.getMetaData();
		 for (int i = 0 ; i <= rsmd.getColumnCount()  - 13;i++)
		 {
			 students.get(i).irs_sat= rs.getDouble(i+2);
		 }
		 //***** Fatura SUTUN
		 rs =null ;
		
 		 rs = f_Access.parametre_oku("FAT_EVRAK_FORMAT","SUTUN");
		 rs.next();
		rsmd = rs.getMetaData();
		 for (int i = 0 ; i <= rsmd.getColumnCount()  - 3;i++)
		 {
			 students.get(i).fat_sut= rs.getDouble(i+2);
		 }
		 //***Fatura SATIR
		 rs =null ;
		
 		 rs = f_Access.parametre_oku("FAT_EVRAK_FORMAT","SATIR");
	
		 rs.next();
		rsmd = rs.getMetaData();
		 for (int i =0 ; i <= rsmd.getColumnCount()  - 3;i++)
		 {
			 students.get(i).fat_sat= rs.getDouble(i+2);
		 }
		 //***
		 TableModel model = (TableModel) table.getModel();
		 for (int i = 0; i <= model.getRowCount() - 1;i++)
		 {
			 model.setValueAt( students.get(i).irs_sat,i, 1)  ;   
			 model.setValueAt( students.get(i).irs_sut,i, 2)  ;   
		 }
		 model = (TableModel) table_1.getModel();
		 for (int i = 0; i <= model.getRowCount() - 1;i++)
		 {
			 model.setValueAt( students.get(i).fat_sat,i, 1)  ;   
			 model.setValueAt( students.get(i).fat_sut,i, 2)  ;   
		 }
		 model = (TableModel) table_2.getModel();
		 for (int i = 0; i <= 9;i++)
		 {
			 model.setValueAt( students.get(i+34).fat_sat,i, 1)  ;   
			 model.setValueAt( students.get(i+34).fat_sut,i, 2)  ;   
		 }
	}
	catch (Exception ex)
	{
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Evrak Formatlama", JOptionPane.ERROR_MESSAGE);
	}
	}
	public static void kaydet()
	{
		try
		{
		 TableModel mdl = (TableModel) table.getModel();
		 TableModel mdl2 = (TableModel) table_2.getModel();
		
			f_Access.evr_format_irs("SATIR",Double.parseDouble( mdl.getValueAt(0,1).toString()),
					 Double.parseDouble( mdl.getValueAt(1,1).toString()),Double.parseDouble( mdl.getValueAt(2,1).toString()),
					 Double.parseDouble( mdl.getValueAt(3,1).toString()),Double.parseDouble( mdl.getValueAt(4,1).toString()),
					 Double.parseDouble( mdl.getValueAt(5,1).toString()),Double.parseDouble( mdl.getValueAt(6,1).toString()),
					 Double.parseDouble( mdl.getValueAt(7,1).toString()),Double.parseDouble( mdl.getValueAt(8,1).toString()),
					 Double.parseDouble( mdl.getValueAt(9,1).toString()),Double.parseDouble( mdl.getValueAt(10,1).toString()),
					 Double.parseDouble( mdl.getValueAt(11,1).toString()),Double.parseDouble( mdl.getValueAt(12,1).toString()),
					 Double.parseDouble( mdl.getValueAt(13,1).toString()),Double.parseDouble( mdl.getValueAt(14,1).toString()),
					 Double.parseDouble( mdl.getValueAt(15,1).toString()),Double.parseDouble( mdl.getValueAt(16,1).toString()),
					 Double.parseDouble( mdl.getValueAt(17,1).toString()),Double.parseDouble( mdl.getValueAt(18,1).toString()),
					 Double.parseDouble( mdl.getValueAt(19,1).toString()),Double.parseDouble( mdl.getValueAt(20,1).toString()),
					 Double.parseDouble( mdl.getValueAt(21,1).toString()),Double.parseDouble( mdl.getValueAt(22,1).toString()),
					 Double.parseDouble( mdl.getValueAt(23,1).toString()),Double.parseDouble( mdl.getValueAt(24,1).toString()),
					 Double.parseDouble( mdl.getValueAt(25,1).toString()),Double.parseDouble( mdl.getValueAt(26,1).toString()),
					 Double.parseDouble( mdl.getValueAt(27,1).toString()),GLOBAL.KULL_ADI);
			f_Access.evr_format_irs("SUTUN",Double.parseDouble( mdl.getValueAt(0,2).toString()),
					 Double.parseDouble( mdl.getValueAt(1,2).toString()),Double.parseDouble( mdl.getValueAt(2,2).toString()),
					 Double.parseDouble( mdl.getValueAt(3,2).toString()),Double.parseDouble( mdl.getValueAt(4,2).toString()),
					 Double.parseDouble( mdl.getValueAt(5,2).toString()),Double.parseDouble( mdl.getValueAt(6,2).toString()),
					 Double.parseDouble( mdl.getValueAt(7,2).toString()),Double.parseDouble( mdl.getValueAt(8,2).toString()),
					 Double.parseDouble( mdl.getValueAt(9,2).toString()),Double.parseDouble( mdl.getValueAt(10,2).toString()),
					 Double.parseDouble( mdl.getValueAt(11,2).toString()),Double.parseDouble( mdl.getValueAt(12,2).toString()),
					 Double.parseDouble( mdl.getValueAt(13,2).toString()),Double.parseDouble( mdl.getValueAt(14,2).toString()),
					 Double.parseDouble( mdl.getValueAt(15,2).toString()),Double.parseDouble( mdl.getValueAt(16,2).toString()),
					 Double.parseDouble( mdl.getValueAt(17,2).toString()),Double.parseDouble( mdl.getValueAt(18,2).toString()),
					 Double.parseDouble( mdl.getValueAt(19,2).toString()),Double.parseDouble( mdl.getValueAt(20,2).toString()),
					 Double.parseDouble( mdl.getValueAt(21,2).toString()),Double.parseDouble( mdl.getValueAt(22,2).toString()),
					 Double.parseDouble( mdl.getValueAt(23,2).toString()),Double.parseDouble( mdl.getValueAt(24,2).toString()),
					 Double.parseDouble( mdl.getValueAt(25,2).toString()),Double.parseDouble( mdl.getValueAt(26,2).toString()),
					 Double.parseDouble( mdl.getValueAt(27,2).toString()),GLOBAL.KULL_ADI);
			 
			 ////FATURA
			 mdl = (TableModel) table_1.getModel();
			 mdl2 = (TableModel) table_2.getModel();
			 f_Access.evr_format_fat("SATIR",Double.parseDouble( mdl.getValueAt(0,1).toString()),
					 Double.parseDouble( mdl.getValueAt(1,1).toString()),Double.parseDouble( mdl.getValueAt(2,1).toString()),
					 Double.parseDouble( mdl.getValueAt(3,1).toString()),Double.parseDouble( mdl.getValueAt(4,1).toString()),
					 Double.parseDouble( mdl.getValueAt(5,1).toString()),Double.parseDouble( mdl.getValueAt(6,1).toString()),
					 Double.parseDouble( mdl.getValueAt(7,1).toString()),Double.parseDouble( mdl.getValueAt(8,1).toString()),
					 Double.parseDouble( mdl.getValueAt(9,1).toString()),Double.parseDouble( mdl.getValueAt(10,1).toString()),
					 Double.parseDouble( mdl.getValueAt(11,1).toString()),Double.parseDouble( mdl.getValueAt(12,1).toString()),
					 Double.parseDouble( mdl.getValueAt(13,1).toString()),Double.parseDouble( mdl.getValueAt(14,1).toString()),
					 Double.parseDouble( mdl.getValueAt(15,1).toString()),Double.parseDouble( mdl.getValueAt(16,1).toString()),
					 Double.parseDouble( mdl.getValueAt(17,1).toString()),Double.parseDouble( mdl.getValueAt(18,1).toString()),
					 Double.parseDouble( mdl.getValueAt(19,1).toString()),Double.parseDouble( mdl.getValueAt(20,1).toString()),
					 Double.parseDouble( mdl.getValueAt(21,1).toString()),Double.parseDouble( mdl.getValueAt(22,1).toString()),
					 Double.parseDouble( mdl.getValueAt(23,1).toString()),Double.parseDouble( mdl.getValueAt(24,1).toString()),
					 Double.parseDouble( mdl.getValueAt(25,1).toString()),Double.parseDouble( mdl.getValueAt(26,1).toString()),
					 Double.parseDouble( mdl.getValueAt(27,1).toString()),Double.parseDouble( mdl.getValueAt(28,1).toString()),
					 Double.parseDouble( mdl.getValueAt(29,1).toString()),Double.parseDouble( mdl.getValueAt(30,1).toString()),
					 Double.parseDouble( mdl.getValueAt(31,1).toString()),Double.parseDouble( mdl.getValueAt(32,1).toString()),
					 Double.parseDouble( mdl.getValueAt(33,1).toString()),Double.parseDouble( mdl2.getValueAt(0,1).toString()),
					 Double.parseDouble( mdl2.getValueAt(1,1).toString()),Double.parseDouble( mdl2.getValueAt(2,1).toString()),
					 Double.parseDouble( mdl2.getValueAt(3,1).toString()),Double.parseDouble( mdl2.getValueAt(4,1).toString()),
					 Double.parseDouble( mdl2.getValueAt(5,1).toString()),Double.parseDouble( mdl2.getValueAt(6,1).toString()),
					 Double.parseDouble( mdl2.getValueAt(7,1).toString()),Double.parseDouble( mdl2.getValueAt(8,1).toString()),
					 Double.parseDouble( mdl2.getValueAt(9,1).toString()),GLOBAL.KULL_ADI);
			 
			 f_Access.evr_format_fat("SUTUN",Double.parseDouble( mdl.getValueAt(0,2).toString()),
					 Double.parseDouble( mdl.getValueAt(1,2).toString()),Double.parseDouble( mdl.getValueAt(2,2).toString()),
					 Double.parseDouble( mdl.getValueAt(3,2).toString()),Double.parseDouble( mdl.getValueAt(4,2).toString()),
					 Double.parseDouble( mdl.getValueAt(5,2).toString()),Double.parseDouble( mdl.getValueAt(6,2).toString()),
					 Double.parseDouble( mdl.getValueAt(7,2).toString()),Double.parseDouble( mdl.getValueAt(8,2).toString()),
					 Double.parseDouble( mdl.getValueAt(9,2).toString()),Double.parseDouble( mdl.getValueAt(10,2).toString()),
					 Double.parseDouble( mdl.getValueAt(11,2).toString()),Double.parseDouble( mdl.getValueAt(12,2).toString()),
					 Double.parseDouble( mdl.getValueAt(13,2).toString()),Double.parseDouble( mdl.getValueAt(14,2).toString()),
					 Double.parseDouble( mdl.getValueAt(15,2).toString()),Double.parseDouble( mdl.getValueAt(16,2).toString()),
					 Double.parseDouble( mdl.getValueAt(17,2).toString()),Double.parseDouble( mdl.getValueAt(18,2).toString()),
					 Double.parseDouble( mdl.getValueAt(19,2).toString()),Double.parseDouble( mdl.getValueAt(20,2).toString()),
					 Double.parseDouble( mdl.getValueAt(21,2).toString()),Double.parseDouble( mdl.getValueAt(22,2).toString()),
					 Double.parseDouble( mdl.getValueAt(23,2).toString()),Double.parseDouble( mdl.getValueAt(24,2).toString()),
					 Double.parseDouble( mdl.getValueAt(25,2).toString()),Double.parseDouble( mdl.getValueAt(26,2).toString()),
					 Double.parseDouble( mdl.getValueAt(27,2).toString()),Double.parseDouble( mdl.getValueAt(28,2).toString()),
					 Double.parseDouble( mdl.getValueAt(29,2).toString()),Double.parseDouble( mdl.getValueAt(30,2).toString()),
					 Double.parseDouble( mdl.getValueAt(31,2).toString()),Double.parseDouble( mdl.getValueAt(32,2).toString()),
					 Double.parseDouble( mdl.getValueAt(33,2).toString()),Double.parseDouble( mdl2.getValueAt(0,2).toString()),
					 Double.parseDouble( mdl2.getValueAt(1,2).toString()),Double.parseDouble( mdl2.getValueAt(2,2).toString()),
					 Double.parseDouble( mdl2.getValueAt(3,2).toString()),Double.parseDouble( mdl2.getValueAt(4,2).toString()),
					 Double.parseDouble( mdl2.getValueAt(5,2).toString()),Double.parseDouble( mdl2.getValueAt(6,2).toString()),
					 Double.parseDouble( mdl2.getValueAt(7,2).toString()),Double.parseDouble( mdl2.getValueAt(8,2).toString()),
					 Double.parseDouble( mdl2.getValueAt(9,2).toString()),GLOBAL.KULL_ADI);
		
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Evrak formatlama Kayit", JOptionPane.ERROR_MESSAGE);
		}
	}
}
//class degisken {
//	   double irs_sut;
//	   double irs_sat;
//	   double fat_sut;
//	   double fat_sat;
//	}
