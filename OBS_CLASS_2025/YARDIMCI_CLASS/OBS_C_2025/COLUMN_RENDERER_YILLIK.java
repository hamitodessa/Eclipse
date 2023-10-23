package OBS_C_2025;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class COLUMN_RENDERER_YILLIK  extends DefaultTableCellRenderer 
{
	Color backgroundColor, foregroundColor;
	public COLUMN_RENDERER_YILLIK(Color backgroundColor, Color foregroundColor) 
	{
		super();
		this.backgroundColor = backgroundColor;
		this.foregroundColor = foregroundColor;
	}
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) 
	{
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if( value.toString() != ""  )
		{
			if (column !=0)
			{
				cell.setBackground(backgroundColor);
				cell.setForeground(foregroundColor); 
				Font fnt = new Font(table.getFont().getFontName(), 1 ,12);
				cell.setFont(fnt);
				Border border = BorderFactory.createMatteBorder(1, 1, 1, 0, new Color(0, 60, 102));
				setBorder(border);
			}
			else if (column == 0)
			{
				setHorizontalAlignment( JLabel.LEFT);
				cell.setBackground(backgroundColor);
				cell.setForeground(foregroundColor); 
			}
		} 
		else 
		{
			cell.setBackground(table.getBackground());
			cell.setForeground(table.getForeground());
		}
		return cell;
	}
}

