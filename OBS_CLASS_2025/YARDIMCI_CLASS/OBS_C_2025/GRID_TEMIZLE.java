package OBS_C_2025;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GRID_TEMIZLE {

	
	public static void grid_temizle(JTable tbl)
	{
		DefaultTableModel model = (DefaultTableModel)tbl.getModel();
		model.setRowCount(0);
	}
}
