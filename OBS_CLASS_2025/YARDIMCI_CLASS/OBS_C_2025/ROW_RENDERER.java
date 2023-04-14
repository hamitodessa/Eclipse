package OBS_C_2025;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

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
	      else if (row ==0)
	      {
	    	  cell.setBackground(new Color(188, 225, 244));
	          cell.setForeground(new Color(29, 125, 175));
	          Font fnt = new Font(table.getFont().getFontName(), 1 ,12);
				cell.setFont(fnt);
	      }
	     
	      return cell;
	   }
	}

