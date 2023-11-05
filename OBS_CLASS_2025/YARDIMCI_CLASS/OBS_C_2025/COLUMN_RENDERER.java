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
	public COLUMN_RENDERER(Color backgroundColorr, Color foregroundColorr,String nerdenn) 
	{
		super();
		backgroundColor = backgroundColorr;
		foregroundColor = foregroundColorr;
		nerden = nerdenn;
	}
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) 
	{
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (column !=0)
		{
			if( value.toString().length() != 0  )
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
				
				
				String[] token = value.toString().split(",");
				String ewqString = "";
				for(int i=0;i<=token.length-1;i++)
				{
					String qazString = token[i].substring(1);
					qazString = qazString.substring(0,qazString.length()-1);
					ewqString += "<br>" + qazString;
				}
				String html = "<html><body ><strong>"
						+ "Tiklama da Gorev Detaylari sag tarafta goruntuleme......." + "</strong>"
						+ ewqString.toString() ;
				setToolTipText(String.format(html));
			}
			else 
			{
				cell.setBackground(table.getBackground());
				cell.setForeground(table.getForeground());
			}
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
		return cell;
	}
}

