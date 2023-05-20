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
public class COLUMN_RENDERER  extends DefaultTableCellRenderer 
{
	Color backgroundColor, foregroundColor;
	String nerden;
	public COLUMN_RENDERER(Color backgroundColor, Color foregroundColor,String nerden) 
	{
		super();
		this.backgroundColor = backgroundColor;
		this.foregroundColor = foregroundColor;
		this.nerden = nerden;
	}
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) 
	{
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if( value.toString() != ""  )
		{
			if (column !=0)
			{
				if(nerden.equals("yillik"))
				{
					setHorizontalAlignment( JLabel.CENTER);
				}
				else
				{
					setHorizontalAlignment( JLabel.LEFT);
				}
				cell.setBackground(backgroundColor);
				cell.setForeground(foregroundColor); 
				Font fnt = new Font(table.getFont().getFontName(), 1 ,10);
				cell.setFont(fnt);
				Border border = BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 60, 102));
				setBorder(border);
				setToolTipText("Tiklama da Gorev Detaylari sag tarafta goruntuleme.......");
			}
			else if (column ==0)
			{
				setHorizontalAlignment( JLabel.CENTER);
				cell.setBackground(backgroundColor);
				cell.setForeground(foregroundColor); 
				Font fnt = new Font(table.getFont().getFontName(), 1 ,12);
				cell.setFont(fnt);
				setToolTipText("");
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

