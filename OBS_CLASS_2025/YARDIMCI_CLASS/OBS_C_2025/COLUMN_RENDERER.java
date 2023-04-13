package OBS_C_2025;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class COLUMN_RENDERER  extends DefaultTableCellRenderer {
		   Color backgroundColor, foregroundColor;
		   public COLUMN_RENDERER(Color backgroundColor, Color foregroundColor) {
		      super();
		      this.backgroundColor = backgroundColor;
		      this.foregroundColor = foregroundColor;
		   }
		   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
		      Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		      cell.setBackground(backgroundColor);
		      cell.setForeground(foregroundColor);
		      if (column ==0)
		      {
		      setHorizontalAlignment( JLabel.CENTER );
		      }
		      return cell;
		   }
		}

