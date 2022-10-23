package OBS_PACKAGE;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class SOLA_BOLD extends JLabel implements TableCellRenderer {
	
	  	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	  	      boolean hasFocus, int rowIndex, int vColIndex) {
	  		setText(value.toString());
	  	    setResizable(false);
	  	    setFont(new Font("Tahoma", Font.BOLD, 12));
	  	    setHorizontalAlignment(JLabel.LEFT);
	  	    setForeground(Color.BLUE);
	  		//Border border = BorderFactory.createLineBorder(	table.getGridColor());
	  	    
	  	    setBorder(table.getBorder());
	  	    return this;
	  	  }

		private void setResizable(boolean b) {
			// TODO Auto-generated method stub
			
		}
	   

}

