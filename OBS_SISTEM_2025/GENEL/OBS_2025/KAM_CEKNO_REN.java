package OBS_2025;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class KAM_CEKNO_REN extends DefaultTableCellRenderer{
	 
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value,
	         boolean isSelected, boolean hasFocus, int row, int col){
	     super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, col );
	             this.setForeground( Color.BLUE);
	             this.setFont(new Font("Tahoma", Font.BOLD, 12));
	 
	     return this;
	 }
	 }