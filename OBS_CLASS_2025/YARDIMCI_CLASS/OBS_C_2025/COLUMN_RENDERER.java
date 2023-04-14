package OBS_C_2025;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class COLUMN_RENDERER  extends DefaultTableCellRenderer 
{
	Color backgroundColor, foregroundColor;
	public COLUMN_RENDERER(Color backgroundColor, Color foregroundColor) 
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
				Font fnt = new Font(table.getFont().getFontName(), 1 ,10);
				cell.setFont(fnt);
				Border border = BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 60, 102));
				setBorder(border);
			}
			else if (column ==0)
			{
				setHorizontalAlignment( JLabel.CENTER );
				cell.setBackground(backgroundColor);
				cell.setForeground(foregroundColor); 
			}
		} 
		else 
		{
			cell.setBackground(Color.WHITE);
			cell.setForeground(Color.WHITE);
		}
		return cell;
	}
}

