package OBS_C_2025;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class IMAGE_RENDERER extends DefaultTableCellRenderer {

	int geni,yuki ;
	public IMAGE_RENDERER(int gen,int yuk) 
	{
		super();
		geni = gen ;
		yuki =yuk;
	}
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) 
	{
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		if(value == null) 
		{
			setIcon(null);
			setBackground(getBackground());
			if (isSelected) {
				cell.setBackground(table.getSelectionBackground());
				cell.setForeground(table.getSelectionForeground());
			} 
			else {
				cell.setBackground(table.getBackground());
			}
			return cell;
		}
		else 
		{
			JLabel lbLabel = new JLabel();
			byte[] bytes = (byte[]) value;
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(bytes).getImage().getScaledInstance(geni, yuki, Image.SCALE_DEFAULT));
			lbLabel.setIcon(imageIcon);
			add(lbLabel);
			return lbLabel;
		}
	}
}
