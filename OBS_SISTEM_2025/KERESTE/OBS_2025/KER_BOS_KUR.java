package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.SOLA;
import OBS_C_2025.TARIH;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

@SuppressWarnings({"serial","static-access"})
public class KER_BOS_KUR extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KERESTE_ACCESS ker_Access = new KERESTE_ACCESS(OBS_SIS_2025_ANA_CLASS._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);

	private static JTable table;
	private static JComboBox<String> comboBox ;
	private static JComboBox<String> comboBox_1; 
	private static JComboBox<String> comboBox_2 ;

	public KER_BOS_KUR() {
		setTitle("KERESTE BOS KUR");
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 191, 600);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(KER_BOS_KUR.class.getResource("/ICONLAR/icons8-currency-exchange-30.png")), 16, 16));//
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 65));
		panel.setMaximumSize(new Dimension(0, 65));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

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
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"GIRIS", "CIKIS"}));
		comboBox_2.setBounds(10, 38, 73, 22);
		panel.add(comboBox_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

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
        	 String dURUMString = "" ;
        	 //comboBox_2
        	 if(comboBox_2.getItemAt(comboBox_2.getSelectedIndex()).toString().equals("GIRIS"))
        		 dURUMString = "" ;
        	 else {
				dURUMString = "C" ;
			}
    			rs = ker_Access.bos_kur(comboBox.getItemAt(comboBox.getSelectedIndex()).toString(),
						comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString(),dURUMString);
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
    	   OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
          }
	}
}
