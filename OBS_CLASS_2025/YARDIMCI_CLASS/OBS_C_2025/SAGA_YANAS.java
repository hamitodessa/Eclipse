package OBS_C_2025;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class SAGA_YANAS extends JLabel implements TableCellRenderer {
	  /**
* 
*/
private static final long serialVersionUID = 1L;


	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int rowIndex, int vColIndex) {
		setText(value.toString());
	    setHorizontalAlignment(JLabel.RIGHT);
	    setVerticalAlignment(JLabel.BOTTOM);
	    setFont(new Font(table.getFont().getFontName(), table.getFont().getStyle(), table.getFont().getSize()));
	    return this;
	  }

}

