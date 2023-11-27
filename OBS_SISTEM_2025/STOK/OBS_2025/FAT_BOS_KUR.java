package OBS_2025;

import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TARIH;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

@SuppressWarnings({"serial","static-access"})
public class FAT_BOS_KUR extends JInternalFrame {
	
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);

	private static JTable table;
	private static JComboBox<String> comboBox ;
	private static JComboBox<String> comboBox_1; 
	/**
	 * Create the frame.
	 */
	public FAT_BOS_KUR() {
		setTitle("STOK BOS KUR");
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 178, 600);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 40));
		panel.setMaximumSize(new Dimension(0, 40));
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"USD", "EUR"}));
		comboBox.setBounds(10, 10, 58, 22);
		panel.add(comboBox);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"MA", "MS", "BA", "BS", "SA", "SS"}));
		comboBox_1.setBounds(78, 10, 58, 22);
		panel.add(comboBox_1);
		
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		splitPane.setRightComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);

	}
	public static void hisset() 
	{
        try 
        {
        	 ResultSet	rs = null;
    			rs = f_Access.bos_kur(comboBox.getItemAt(comboBox.getSelectedIndex()).toString(),
						comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString());
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
          // JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Eksik Kur Okuma", JOptionPane.ERROR_MESSAGE);
          }
	}
	
}
