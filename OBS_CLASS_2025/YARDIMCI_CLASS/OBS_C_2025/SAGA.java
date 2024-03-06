package OBS_C_2025;

import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

public class SAGA extends JLabel implements TableCellRenderer {
	  	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	  	      boolean hasFocus, int rowIndex, int vColIndex) {
	  		//Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
	  		Border border = BorderFactory.createLineBorder(null);
	  		setText(value.toString());
	  	    setToolTipText((String) value);
	  	    setResizable(false);
	  	    setHorizontalAlignment(JLabel.RIGHT);
	  	    setVerticalAlignment(JLabel.CENTER);
	  	    //setForeground(new Color(0, 0, 128));
	        setBorder(border);
	        setFont(new Font(table.getFont().getFontName(), Font.BOLD, 12));
	  	    return this;
	  	  }

		private void setResizable(boolean b) {
		}

}
