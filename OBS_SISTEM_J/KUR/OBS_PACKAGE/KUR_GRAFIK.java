package OBS_PACKAGE;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import net.proteanit.sql.DbUtils;
import net.sourceforge.chart2d.Chart2D;
import net.sourceforge.chart2d.Chart2DProperties;
import net.sourceforge.chart2d.Dataset;
import net.sourceforge.chart2d.GraphChart2DProperties;
import net.sourceforge.chart2d.GraphProperties;
import net.sourceforge.chart2d.LBChart2D;
import net.sourceforge.chart2d.LegendProperties;
import net.sourceforge.chart2d.MultiColorsProperties;
import net.sourceforge.chart2d.Object2DProperties;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


public class KUR_GRAFIK extends JInternalFrame {

	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	private static JTable table;
	public static JScrollPane scrollPane ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KUR_GRAFIK frame = new KUR_GRAFIK();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws PropertyVetoException 
	 */
	public KUR_GRAFIK()   {
		setTitle("KUR GRAFIK");
		setClosable(true);
		setBounds(0, 0, 275, 600);
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.setGridColor(oac.gridcolor);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.addColumn("Tarih", new String []{""});
	    model.addColumn("Cins", new String []{""});
	    model.addColumn("Kur",new Double [] {new Double( 0.0000 )});
	    duzenle();
	    GRID_TEMIZLE.grid_temizle(table);
		scrollPane.setViewportView(table);
	}
	public static void filtrele()
	{
	    GRID_TEMIZLE.grid_temizle(table);
		 duzenle();
		
		if (FILTRE.comboBox_73.getItemAt(FILTRE.comboBox_73.getSelectedIndex()).equals("Yil"))
		{
			yil_goster();
		}
		else if (FILTRE.comboBox_73.getItemAt(FILTRE.comboBox_73.getSelectedIndex()).equals("Ay"))
		{
			ay_goster();
		}
		else if (FILTRE.comboBox_73.getItemAt(FILTRE.comboBox_73.getSelectedIndex()).equals("Gun"))
		{
			gun_goster();
		}
	}
	private static void duzenle()
	{
		 JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
			
				tc.setMinWidth(70);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);

				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(4,false));
				tc.setMinWidth(70);
				
			Dimension dd = th.getPreferredSize();
		    dd.height = 30;
		    th.setPreferredSize(dd); 
			th.repaint();
		
			table.setRowHeight(21);
	}
	private static void yil_goster()
	{
		try {
		ResultSet rs =null;
		ResultSet rss =null;
		 if (CONNECTION.kurdizinbilgi.han_sql.equals("MS SQL"))
	     {
			 rs = oac.kUR_MSSQL.kur_yil_graf_rapor(FILTRE.comboBox_74.getSelectedItem().toString(),
					 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32), TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32_1));
	     }
		 else
		 {
			 rs = oac.kUR_MYSQL.kur_yil_graf_rapor(FILTRE.comboBox_74.getSelectedItem().toString(),
					 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32), TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32_1));
		 }
		if (!rs.isBeforeFirst() ) {  
			return ;
		} 
	  //  GRID_TEMIZLE.grid_temizle(table);
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	while (rs.next()) {
		 if (CONNECTION.kurdizinbilgi.han_sql.equals("MS SQL"))
	     {
			 rss = oac.kUR_MSSQL.kur_graf_rapor(FILTRE.comboBox_74.getSelectedItem().toString(),
				rs.getString("TARIH").toString() + "." + "1" + "." + "1",
				rs.getString("TARIH").toString() + "." + "1" + "." + "10",
				FILTRE.comboBox_72.getItemAt(FILTRE.comboBox_72.getSelectedIndex()),"YEAR");
	     }
		 else
		 {
			 rss = oac.kUR_MYSQL.kur_graf_rapor(FILTRE.comboBox_74.getSelectedItem().toString(),
						rs.getString("TARIH").toString() + "." + "1" + "." + "1",
						rs.getString("TARIH").toString() + "." + "1" + "." + "10",
						FILTRE.comboBox_72.getItemAt(FILTRE.comboBox_72.getSelectedIndex()),"YEAR");
		 }
		if (!rss.isBeforeFirst() ) 
		{  
		//	System.out.println("Bos");
		} 
		else
		{
			rss.next();
		      model.addRow(new Object[]{rss.getString("Tarih"), rss.getString("Kur")  ,rss.getDouble(	FILTRE.comboBox_72.getItemAt(FILTRE.comboBox_72.getSelectedIndex()))});
		}
	}

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kur Grafik", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void grafik_kur()
	{
		if (FILTRE.comboBox_73.getItemAt(FILTRE.comboBox_73.getSelectedIndex()).equals("Yil"))
		{
			GLOBAL.g_baslik = "KUR GRAFIK YILLARA GORE";
			//***LEGENDS
			DefaultTableModel mdll = (DefaultTableModel) table.getModel();
			GLOBAL.g_legends = new String[1];
			GLOBAL.g_legends[0] = "Yil"  ;
			//*** g_labelsAxisLabels
			GLOBAL.g_labelsAxisLabels = new String[mdll.getRowCount()];
			//System.out.println("col =" +  (mdll.getColumnCount() - 2));
			for (int i = 0;i<mdll.getRowCount()   ;i++)
			{
				GLOBAL.g_labelsAxisLabels[i] = mdll.getValueAt(i,0).toString() ;
			}
			//*** g_LabelsAxisTitleText
			GLOBAL.g_LabelsAxisTitleText ="Yillar";
			//*** g_setNumbersAxisTitleText
			GLOBAL.g_setNumbersAxisTitleText = "Kur" ;
			GLOBAL.min_value = Double.parseDouble( mdll.getValueAt(0,2).toString());
			//*** Dataset
			GLOBAL.g_dataSet = new Dataset (1,mdll.getRowCount() ,1);
				 for (int y = 1;y<=mdll.getRowCount() ;y++)
				 {
						double asd =  mdll.getValueAt(y-1,2) == null ? 0: Double.parseDouble( mdll.getValueAt(y-1,2).toString());
						GLOBAL.g_dataSet.set (0,  y-1, 0,(float) asd   )  ; 
						if (asd < GLOBAL.min_value )
						{
							GLOBAL.min_value = asd ;
						}
				
				 }
		}
		else if (FILTRE.comboBox_73.getItemAt(FILTRE.comboBox_73.getSelectedIndex()).equals("Ay"))
		{
			GLOBAL.g_baslik = "KUR GRAFIK AYLARA GORE";
			//***LEGENDS
			DefaultTableModel mdll = (DefaultTableModel) table.getModel();
			GLOBAL.g_legends = new String[1];
			GLOBAL.g_legends[0] = "Ay"  ;
			//*** g_labelsAxisLabels
			GLOBAL.g_labelsAxisLabels = new String[mdll.getRowCount()];
			for (int i = 0;i<mdll.getRowCount()   ;i++)
			{
				GLOBAL.g_labelsAxisLabels[i] = mdll.getValueAt(i,0).toString() ;
			}
			//*** g_LabelsAxisTitleText
			GLOBAL.g_LabelsAxisTitleText ="Aylar";
			//*** g_setNumbersAxisTitleText
			GLOBAL.g_setNumbersAxisTitleText = "Kur" ;
			//*** Dataset
			GLOBAL.min_value = Double.parseDouble( mdll.getValueAt(0,2).toString());
			GLOBAL.g_dataSet = new Dataset (1,mdll.getRowCount() ,1);
				 for (int y = 1;y<=mdll.getRowCount() ;y++)
				 {
						double asd =  mdll.getValueAt(y-1,2) == null ? 0: Double.parseDouble( mdll.getValueAt(y-1,2).toString());
						GLOBAL.g_dataSet.set (0,  y-1, 0,(float) asd   )  ; 
						if (asd < GLOBAL.min_value )
						{
							GLOBAL.min_value = asd ;
						}
				 }
		}
		else if (FILTRE.comboBox_73.getItemAt(FILTRE.comboBox_73.getSelectedIndex()).equals("Gun"))
		{
			GLOBAL.g_baslik = "KUR GRAFIK Gunlere GORE";
			//***LEGENDS
			DefaultTableModel mdll = (DefaultTableModel) table.getModel();
			GLOBAL.g_legends = new String[1];
			GLOBAL.g_legends[0] = "Gun"  ;
			//*** g_labelsAxisLabels
			GLOBAL.g_labelsAxisLabels = new String[mdll.getRowCount()];
			//System.out.println("col =" +  (mdll.getColumnCount() - 2));
			for (int i = 0;i<mdll.getRowCount()   ;i++)
			{
				GLOBAL.g_labelsAxisLabels[i] = mdll.getValueAt(i,0).toString() ;
			}
			//*** g_LabelsAxisTitleText
			GLOBAL.g_LabelsAxisTitleText ="Gunler";
			//*** g_setNumbersAxisTitleText
			GLOBAL.g_setNumbersAxisTitleText = "Kur" ;
			//*** Dataset
			GLOBAL.min_value = Double.parseDouble( mdll.getValueAt(0,2).toString());
			GLOBAL.g_dataSet = new Dataset (1,mdll.getRowCount() ,1);
				 for (int y = 1;y<=mdll.getRowCount() ;y++)
				 {
						double asd =  mdll.getValueAt(y-1,2) == null ? 0: Double.parseDouble( mdll.getValueAt(y-1,2).toString());
						GLOBAL.g_dataSet.set (0,  y-1, 0,(float) asd   )  ; 
						if (asd < GLOBAL.min_value )
						{
							GLOBAL.min_value = asd ;
						}
				 }
		}
	}
	private static void ay_goster()
	{
		try {
		ResultSet rss =null;
		DefaultTableModel model = (DefaultTableModel) table.getModel();	
	  //  GRID_TEMIZLE.grid_temizle(table);
	for (int i =1; i <= 12;i++) {
		 if (CONNECTION.kurdizinbilgi.han_sql.equals("MS SQL"))
	     {
			 rss = oac.kUR_MSSQL.kur_graf_rapor(FILTRE.comboBox_74.getSelectedItem().toString(),
					 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32).substring(0,4)  + "." + i + "." + "1",
					 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32).substring(0,4)+ "." + i + "." + "10",
				FILTRE.comboBox_72.getItemAt(FILTRE.comboBox_72.getSelectedIndex()),"MONTH");
	     }
		 else
		 {
		 rss = oac.kUR_MYSQL.kur_graf_rapor(FILTRE.comboBox_74.getSelectedItem().toString(),
				 	TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32).substring(0,4) + "." + "1" + "." + "1",
				 	TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32).substring(0,4) + "." + "1" + "." + "10",
						FILTRE.comboBox_72.getItemAt(FILTRE.comboBox_72.getSelectedIndex()),"MONTH");
		 }
		if (!rss.isBeforeFirst() ) 
		{  
		//	System.out.println("Bos");
		} 
		else
		{
			rss.next();
		      model.addRow(new Object[]{rss.getString("Tarih"), rss.getString("Kur")  ,rss.getDouble(	FILTRE.comboBox_72.getItemAt(FILTRE.comboBox_72.getSelectedIndex()))});
		}
	}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kur Grafik", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void gun_goster()
	{
		try {
		ResultSet rss =null;
		 if (CONNECTION.kurdizinbilgi.han_sql.equals("MS SQL"))
	     {
			 rss = oac.kUR_MSSQL.kur_graf_rapor(FILTRE.comboBox_74.getSelectedItem().toString(),
					 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32)  ,
					 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32_1) ,
				FILTRE.comboBox_72.getItemAt(FILTRE.comboBox_72.getSelectedIndex()),"DAY");
	     }
		 else
		 {
			 rss = oac.kUR_MYSQL.kur_graf_rapor(FILTRE.comboBox_74.getSelectedItem().toString(),
					 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32)  + "." + "1" + "." + "1",
					 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32_1) + "." + "1" + "." + "10",
						FILTRE.comboBox_72.getItemAt(FILTRE.comboBox_72.getSelectedIndex()),"MONTH");
		 }
		if (!rss.isBeforeFirst() ) 
		{  
		//	System.out.println("Bos");
		} 
		else
		{
			table.setModel(DbUtils.resultSetToTableModel(rss));		
		}
	 duzenle();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kur Grafik", JOptionPane.ERROR_MESSAGE);
		}
	}
	

}
