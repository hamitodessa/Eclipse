package OBS_C_2025;

import java.awt.Component;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TARIH_SAATLI extends DefaultTableCellRenderer {
	
	private static final long serialVersionUID = 1L;
	SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
    	
       // if( value instanceof Date) {    	setHorizontalAlignment(JLabel.LEFT);
    	setVerticalAlignment(JLabel.BOTTOM);
        value = f.format(value);
       // }
        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
    }
 }

