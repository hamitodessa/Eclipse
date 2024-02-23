package OBS_C_2025;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class U_KODU_RENDERER extends DefaultTableCellRenderer 
{
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) 
	{
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		setText(value.toString());
  	    setToolTipText((String) value);
  	    setHorizontalAlignment(JLabel.LEFT);
  	    setVerticalAlignment(JLabel.BOTTOM);
  	    //setForeground(new Color(0, 0, 128));
        setFont(new Font(table.getFont().getFontName(), Font.BOLD, table.getFont().getSize()));
		return cell;
	}
}

