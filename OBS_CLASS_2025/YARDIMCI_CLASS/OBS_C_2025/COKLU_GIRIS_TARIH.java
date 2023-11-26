package OBS_C_2025;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class COKLU_GIRIS_TARIH extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 1L;
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col){
		super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, col );
		if(value instanceof Date){
			String strDate = new SimpleDateFormat("dd.MM.yyyy").format((Date)value);
			this.setText(strDate);
		}
		return this;
	}
}

