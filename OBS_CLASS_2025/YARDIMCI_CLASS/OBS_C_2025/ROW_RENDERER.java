package OBS_C_2025;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class ROW_RENDERER extends DefaultTableCellRenderer {
	   Color backgroundColor, foregroundColor;
	   public ROW_RENDERER(Color backgroundColor, Color foregroundColor) {
	      super();
	      this.backgroundColor = backgroundColor;
	      this.foregroundColor = foregroundColor;
	   }
	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
	      Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	      
	      if (row ==1)
	      {
	      cell.setBackground(backgroundColor);
	      cell.setForeground(foregroundColor);
	      }
	      else
	      {
	    	  cell.setBackground(table.getBackground());
	          cell.setForeground(table.getForeground());
	      }
	     
	      return cell;
	   }
	}

