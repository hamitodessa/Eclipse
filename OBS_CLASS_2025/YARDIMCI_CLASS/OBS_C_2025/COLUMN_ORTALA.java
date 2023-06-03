package OBS_C_2025;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class COLUMN_ORTALA extends JLabel implements TableCellRenderer {
	  	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	  	      boolean hasFocus, int rowIndex, int vColIndex) {
	  	    setHorizontalAlignment(JLabel.CENTER);
	  	    setText(value.toString());
	  	    setFont(table.getFont());
	  	    return this;
	  	  }
}
