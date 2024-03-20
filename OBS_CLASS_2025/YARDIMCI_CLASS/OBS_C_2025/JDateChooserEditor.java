package OBS_C_2025;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class JDateChooserEditor extends DefaultCellEditor
{
	public JDateChooserEditor(JCheckBox checkBox)
	{
		super(checkBox);

	}
	JDateChooser date;
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) 
	{
		String tar = "" ;
		try
		{
			if (value.toString().length() >  10)
				tar = dateFormater(value.toString() , "dd.MM.yyyy", "EEE MMM dd kk:mm:ss zzzz yyyy" );
			else
				tar = value.toString();
			date = new JDateChooser(TARIH_CEVIR.tarih(tar));
			((JTextField)date.getDateEditor()).setBackground(new Color(224,224,224));
			date.getComponent(1).addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
				}
				@Override
				public void keyPressed(KeyEvent e) {
					if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
					{	
						if (table.isEditing())
						     table.getCellEditor().stopCellEditing();
					}
				}
				@Override
				public void keyReleased(KeyEvent e) {
				}
			});

			date.setDateFormatString("dd.MM.yyyy");
		}
		catch (Exception ex)
		{
			date = new JDateChooser();
			return date;
		}
		return date;
	}
	public Object getCellEditorValue() 
	{
		return new String(((JTextField)date.getDateEditor().getUiComponent()).getText());
	}
	public boolean stopCellEditing()
	{
		return super.stopCellEditing();
	}
	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
	public static String dateFormater(String dateFromJSON, String expectedFormat, String oldFormat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
		Date date = null;
		String convertedDate = null;
		try {
			date = dateFormat.parse(dateFromJSON);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(expectedFormat);
			convertedDate = simpleDateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertedDate;
	}
}