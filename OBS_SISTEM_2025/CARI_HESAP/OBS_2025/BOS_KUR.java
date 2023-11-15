package OBS_2025;

import java.sql.ResultSet;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import javax.swing.ListSelectionModel;

@SuppressWarnings({"serial" })public class BOS_KUR extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	private static JTable table;

	public BOS_KUR() {
		setClosable(true);
		setTitle("BOS KUR");
		setBounds(100, 100, 145, 551);
		
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
	}
	public static void hisset() 
	{
        try 
        {
        	 ResultSet	rs = null;
        	 rs = c_Access.eksik_kur_okuma(DVZ_CEVIRME.lblkod.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_3),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_4),
						FILTRE.comboBox_1.getItemAt(FILTRE.comboBox_1.getSelectedIndex()));
        	
        	GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
			    return;
			} 
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH ());
			Dimension dd = th.getPreferredSize();
		    dd.height = 30;
		    th.setPreferredSize(dd); 
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);
       }
       catch (Exception ex)
        {
    	   OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
           //JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Eksik Kur Okuma", JOptionPane.ERROR_MESSAGE);
          }
	}
}
