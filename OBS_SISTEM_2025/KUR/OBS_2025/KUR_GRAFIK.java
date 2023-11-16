package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JInternalFrame;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jfree.data.category.DefaultCategoryDataset;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.KUR_ACCESS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;


@SuppressWarnings("serial")
public class KUR_GRAFIK extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KUR_ACCESS k_Access = new KUR_ACCESS(OBS_SIS_2025_ANA_CLASS._IKur , OBS_SIS_2025_ANA_CLASS._IKur_Loger);
	public static ScrollPaneWin11 scrollPane;
	private static JTable table;

	@SuppressWarnings({ "removal", "static-access" })
	public KUR_GRAFIK()   {
		setTitle("KUR GRAFIK");
		setClosable(true);
		setBounds(0, 0, 275, 600);
		scrollPane = new ScrollPaneWin11();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addColumn("Tarih", new String []{""});
		model.addColumn("Cins", new String []{""});
		model.addColumn("Kur",new Double [] {new Double( 0.0000 )});
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
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

			rs = k_Access.kur_yil_graf_rapor(FILTRE.comboBox_74.getSelectedItem().toString(),
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32), TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32_1));

			if (!rs.isBeforeFirst() ) {  
				return ;
			} 
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			while (rs.next()) {

				rss = k_Access.kur_graf_rapor(FILTRE.comboBox_74.getSelectedItem().toString(),
						rs.getString("TARIH").toString() + "." + "1" + "." + "1",
						rs.getString("TARIH").toString() + "." + "1" + "." + "10",
						FILTRE.comboBox_72.getItemAt(FILTRE.comboBox_72.getSelectedIndex()),"YEAR");

				if (!rss.isBeforeFirst() ) 
				{  

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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
			//JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kur Grafik", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void grafik_kur()
	{
		GLOBAL.gkusurat = 3;
		if (FILTRE.comboBox_73.getItemAt(FILTRE.comboBox_73.getSelectedIndex()).equals("Yil"))
		{
			GLOBAL.g_baslik = "KUR GRAFIK YILLARA GORE";
			//***LEGENDS
			DefaultTableModel mdll = (DefaultTableModel) table.getModel();
			GLOBAL.g_legends = "Yillar";

			//*** g_setNumbersAxisTitleText
			GLOBAL.g_setNumbersAxisTitleText = "Kur" ;
			ArrayList<Double> kur = new ArrayList<Double>();
			for (int y = 0;y<mdll.getRowCount() - 1 ;y++)
			{
				kur.add(  mdll.getValueAt(y,2) == null ? 0: Double.parseDouble( mdll.getValueAt(y,2).toString()));
			}
			GLOBAL.min_value =  Collections.min(kur) - (Collections.min(kur) * .10) ;
			GLOBAL.max_value = Collections.max(kur) + (Collections.max(kur) * .10) ;

			///
			Double asd = 0.00 ;
			GLOBAL.dataset = new DefaultCategoryDataset();  
			String series1 = "YILLAR";  
			// String series2 = "Unique Visitor";  
			for (int y = 1;y<=mdll.getRowCount() ;y++)
			{
				asd =  mdll.getValueAt(y-1,2) == null ? 0: Double.parseDouble( mdll.getValueAt(y-1,2).toString());
				GLOBAL.dataset.addValue(asd, series1, mdll.getValueAt(y-1,0).toString());  
			}
		}
		else if (FILTRE.comboBox_73.getItemAt(FILTRE.comboBox_73.getSelectedIndex()).equals("Ay"))
		{
			GLOBAL.g_baslik = "KUR GRAFIK AYLARA GORE";
			//***LEGENDS
			DefaultTableModel mdll = (DefaultTableModel) table.getModel();
			GLOBAL.g_legends = "Aylar";

			//*** g_setNumbersAxisTitleText
			GLOBAL.g_setNumbersAxisTitleText = "Kur" ;
			//*** Dataset
			GLOBAL.min_value = Double.parseDouble( mdll.getValueAt(0,2).toString());
			///
			String series1 = "AYLAR";  
			// String series2 = "Unique Visitor";  
			ArrayList<Double> kur = new ArrayList<Double>();
			for (int y = 0;y<mdll.getRowCount() - 1 ;y++)
			{
				kur.add(  mdll.getValueAt(y,2) == null ? 0: Double.parseDouble( mdll.getValueAt(y,2).toString()));
			}
			GLOBAL.min_value =  Collections.min(kur) - (Collections.min(kur) * .10) ;
			GLOBAL.max_value = Collections.max(kur) + (Collections.max(kur) * .10) ;
			///
			GLOBAL.dataset = new DefaultCategoryDataset();  
			Double asd = 0.00 ;
			for (int y = 1;y<=mdll.getRowCount() ;y++)
			{
				asd =  mdll.getValueAt(y-1,2) == null ? 0: Double.parseDouble( mdll.getValueAt(y-1,2).toString());
				GLOBAL.dataset.addValue(asd, series1, mdll.getValueAt(y-1,0).toString());  
			}
			///
		}
		else if (FILTRE.comboBox_73.getItemAt(FILTRE.comboBox_73.getSelectedIndex()).equals("Gun"))
		{
			GLOBAL.g_baslik = "KUR GRAFIK Gunlere GORE";
			//***LEGENDS
			DefaultTableModel mdll = (DefaultTableModel) table.getModel();
			GLOBAL.g_legends = "Gunler";
			//*** g_setNumbersAxisTitleText
			GLOBAL.g_setNumbersAxisTitleText = "Kur" ;
			//*** Dataset
			String series1 = "GUNLER";  
			ArrayList<Double> kur = new ArrayList<Double>();
			for (int y = 0;y<mdll.getRowCount() - 1 ;y++)
			{
				kur.add(  mdll.getValueAt(y,2) == null ? 0: Double.parseDouble( mdll.getValueAt(y,2).toString()));
			}
			GLOBAL.min_value =  Collections.min(kur) - (Collections.min(kur) * .10) ;
			GLOBAL.max_value = Collections.max(kur) + (Collections.max(kur) * .10) ;
			///
			Double asd = 0.00 ;
			GLOBAL.dataset = new DefaultCategoryDataset();  
			for (int y = 1;y<=mdll.getRowCount() ;y++)
			{
				asd =  mdll.getValueAt(y-1,2) == null ? 0: Double.parseDouble( mdll.getValueAt(y-1,2).toString());
				GLOBAL.dataset.addValue(asd, series1, mdll.getValueAt(y-1,0).toString());  
			}
			///
		}
	}
	private static void ay_goster()
	{
		try {
			ResultSet rss =null;
			DefaultTableModel model = (DefaultTableModel) table.getModel();	
			//  GRID_TEMIZLE.grid_temizle(table);
			for (int i =1; i <= 12;i++) {

				rss = k_Access.kur_graf_rapor(FILTRE.comboBox_74.getSelectedItem().toString(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32).substring(0,4)  + "." + i + "." + "1",
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32).substring(0,4)+ "." + i + "." + "10",
						FILTRE.comboBox_72.getItemAt(FILTRE.comboBox_72.getSelectedIndex()),"MONTH");

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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
			//JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kur Grafik", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void gun_goster()
	{
		try {
			ResultSet rss =null;

			rss =k_Access.kur_graf_rapor(FILTRE.comboBox_74.getSelectedItem().toString(),
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32)  ,
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_32_1) ,
					FILTRE.comboBox_72.getItemAt(FILTRE.comboBox_72.getSelectedIndex()),"DAY");

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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
			//JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kur Grafik", JOptionPane.ERROR_MESSAGE);
		}
	}
}
