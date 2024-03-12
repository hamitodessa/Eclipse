package OBS_C_2025;

import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TABLO_RENDERER extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	private static final DecimalFormat formatter0 = new DecimalFormat( "##,###,##0" );
	private static final DecimalFormat formatter1 = new DecimalFormat( "##,###,##0.0" );
	private static final DecimalFormat formatter2 = new DecimalFormat( "##,###,##0.00" );
	private static final DecimalFormat formatter3 = new DecimalFormat( "##,###,##0.000" );
	private static final DecimalFormat formatter4 = new DecimalFormat( "##,###,##0.0000" );
	public int kesir;

	public  boolean bold ;
	public TABLO_RENDERER(int kesirr, boolean boldr) {
		kesir = kesirr ;
		bold = boldr ;
	}
	@Override
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value == null) value = 0 ;
		
		switch (kesir) {
        case 0 -> value = formatter0.format((Number)value);
        case 1 -> value = formatter1.format((Number)value);
        case 2 -> value = formatter2.format((Number)value);
        case 3 -> value = formatter3.format((Number)value);
        case 4 -> value = formatter4.format((Number)value);
 		}
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (bold)
			setFont (new Font(table.getFont().getFontName(),1 ,table.getFont().getSize())); //12
		else
			setFont (new Font(table.getFont().getFontName(),0 ,table.getFont().getSize()));
		setHorizontalAlignment(JLabel.RIGHT);
		setVerticalAlignment(JLabel.BOTTOM);
		return this;
	}
}
