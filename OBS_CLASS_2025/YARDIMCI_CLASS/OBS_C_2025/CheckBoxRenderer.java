package OBS_C_2025;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {

    public CheckBoxRenderer() {
      setHorizontalAlignment(JLabel.CENTER);
    }
   
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
    	
        if (isSelected)
      {
        setForeground(table.getSelectionForeground());
         setBackground(table.getSelectionBackground());
      } else 
      {
        setForeground(table.getForeground());
        setBackground(table.getBackground());
      }
         if ( value != null)
        {
    	if (value.toString().equals("0") )
    	{
     		setSelected(false);
    	}
		else if (value.toString().equals("1"))
		{ 
				setSelected(true);
				}
		else if (value.toString().equals("false")) 
		{ 
		
			setSelected(false);}
		else if (value.toString().equals("true"))
		{
		
			setSelected(true);
		}
        }
        //	   setSelected((value != null && ((Boolean) value).booleanValue()));

   
      return this;
    }
}
