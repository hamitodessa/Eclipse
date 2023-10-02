package OBS_C_2025;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TARIH extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if( value instanceof Date) 
		{
			value = f.format(value);
		}
		else
		{
			try 
			{
				if(value != null &&  ! value.toString().equals(""))
				{
					Date date1 = new SimpleDateFormat("yyyy.MM.dd").parse(value.toString());
					SimpleDateFormat DateFor = new SimpleDateFormat("dd.MM.yyyy");
					value = DateFor.format(date1);
				}
			} catch (Exception e) 
			{
				e.printStackTrace();			
			}  
		}
		setHorizontalAlignment(JLabel.LEFT);
		setVerticalAlignment(JLabel.BOTTOM);
		return super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
	}
}


