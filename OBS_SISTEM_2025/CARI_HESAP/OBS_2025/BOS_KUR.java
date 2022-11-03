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

import net.proteanit.sql.DbUtils;
import javax.swing.ListSelectionModel;

public class BOS_KUR extends JInternalFrame {
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
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
        	if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
			{
				rs = oac.cARI_HESAP_MSSQL.eksik_kur_okuma(DVZ_CEVIRME.lblkod.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_3),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_4),
						FILTRE.comboBox_1.getItemAt(FILTRE.comboBox_1.getSelectedIndex()));
			}
			else
			{
				rs = oac.cARI_HESAP_MYSQL.eksik_kur_okuma(DVZ_CEVIRME.lblkod.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_3),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_4),
						FILTRE.comboBox_1.getItemAt(FILTRE.comboBox_1.getSelectedIndex()));
				}
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
