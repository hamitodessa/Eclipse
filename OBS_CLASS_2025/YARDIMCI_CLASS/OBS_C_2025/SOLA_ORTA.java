package OBS_C_2025;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class SOLA_ORTA extends DefaultTableCellRenderer 
{
	 @Override
	 public Component getTableCellRendererComponent(JTable table,
	            Object value, boolean isSelected, boolean hasFocus,
	            int row, int column) {
		 
	     super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		 if (value == null) value = "" ;
  	   
  	    setHorizontalAlignment(JLabel.LEFT);
  	    setVerticalAlignment(JLabel.BOTTOM);
        setFont(new Font(table.getFont().getFontName(), table.getFont().getStyle(), table.getFont().getSize()));
		return this;
	}
}




//setHorizontalAlignment(JLabel.LEFT);
//setVerticalAlignment(JLabel.BOTTOM);
//setFont(new Font(table.getFont().getFontName(), table.getFont().getStyle(), table.getFont().getSize()));