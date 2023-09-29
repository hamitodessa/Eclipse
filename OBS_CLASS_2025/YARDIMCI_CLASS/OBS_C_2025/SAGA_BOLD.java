package OBS_C_2025;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class SAGA_BOLD extends DefaultTableCellRenderer {
	  /**
* 
*/
private static final long serialVersionUID = 1L;
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int rowIndex, int vColIndex) {
		 super.getTableCellRendererComponent(table, value, isSelected, hasFocus, rowIndex, vColIndex);
		 
		setText(value.toString());
	    setHorizontalAlignment(JLabel.RIGHT);
	    setVerticalAlignment(JLabel.BOTTOM);
	    setForeground(new Color(139, 0, 0));
	    setFont(new Font(table.getFont().getFontName(), Font.BOLD, 12));
	    return this;
	  }

}