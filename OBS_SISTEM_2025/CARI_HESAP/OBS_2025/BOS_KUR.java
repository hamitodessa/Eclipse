package OBS_2025;

import java.awt.EventQueue;
import java.sql.ResultSet;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SOLA;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.TARIH_SAATLI;
import net.proteanit.sql.DbUtils;
import javax.swing.ListSelectionModel;

public class BOS_KUR extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(oac._ICar , oac._ICari_Loger);

	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BOS_KUR frame = new BOS_KUR();
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
	public BOS_KUR() {
		setClosable(true);
		setTitle("BOS KUR");
		setBounds(100, 100, 145, 551);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
           JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Eksik Kur Okuma", JOptionPane.ERROR_MESSAGE);
          }
	}
}
