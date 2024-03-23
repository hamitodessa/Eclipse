package OBS_C_2025;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.TableCellEditor;




@SuppressWarnings("serial")
public class KambiyoCombo extends AbstractCellEditor implements TableCellEditor {

	private JComboBox<String> editor;
	private ArrayList<String>  masterValues;

	public KambiyoCombo(ArrayList<String> masterValues1,JTable table,String nerden,int limit) 
	{
		editor = new JComboBox<String>();
		masterValues = new ArrayList<String> () ;
		masterValues = masterValues1;
		editor.setEditable(true);

		JTextField editorComponent = (JTextField)  editor.getEditor().getEditorComponent();
		editorComponent.setDocument (new JTextFieldLimit(limit));

		editorComponent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(editor.getEditor().getItem() == null ) return;
				editor.showPopup();
				Object child =  editor.getAccessibleContext().getAccessibleChild(0);
				BasicComboPopup popup = (BasicComboPopup)child;
				JList<Object> list = popup.getList();
				for (int i = 0; i < list.getModel().getSize(); i++)
				{
					if(editor.getEditor().getItem() != null)
					{
						String value =  list.getModel().getElementAt(i).toString();
						if (value.toString().startsWith(editor.getEditor().getItem().toString()))
						{
							list.setSelectedIndex(i);
							list.scrollRectToVisible(list.getCellBounds(i, i+3));
							return;
						}
					}
				}
			}
			public void keyPressed(KeyEvent e) {

			}
		});

		editorComponent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {


			}});
		editor.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {


				editor.setSelectedItem(editorComponent.getText());
				if(table.getCellEditor() != null)
					table.getCellEditor().stopCellEditing();
			}
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
	}
	public Object getCellEditorValue() {
		return editor.getSelectedItem();
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(masterValues.toArray(new String[masterValues.size()]));
		for (int index = 0; index < table.getRowCount(); index++) {
			if (index != row) {
				//String cellValue = (String) table.getValueAt(index, 0);
				//if (! cellValue.equals(""))
				//model.removeElement(cellValue);
			}
		}
		editor.setModel(model);
		editor.setSelectedItem(value.toString());
		return editor;
	}
}	
