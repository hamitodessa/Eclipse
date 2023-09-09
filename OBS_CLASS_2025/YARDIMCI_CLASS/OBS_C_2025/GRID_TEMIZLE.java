package OBS_C_2025;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GRID_TEMIZLE {

	
	public static void grid_temizle(JTable tbl)
	{
		DefaultTableModel model = (DefaultTableModel)tbl.getModel();
	   //while (model.getRowCount() > 0)
	   //{
	   //    for (int i = 0; i < model.getRowCount(); ++i){
	   //        model.removeRow(i);
	   //     }
	   // }
		model.setRowCount(0);
	}
}
//dtm.setRowCount(0);