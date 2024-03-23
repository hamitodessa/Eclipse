package OBS_2025;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.TableCellEditor;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
@SuppressWarnings({"serial","static-access"})
public class ComboBoxTableCellEditor extends AbstractCellEditor implements TableCellEditor {

	private JComboBox<String> editor;
	private ArrayList<String>  masterValues;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	public ComboBoxTableCellEditor(ArrayList<String> masterValues1,JTable table,String nerden) 
	{
		editor = new JComboBox<String>();
		AutoCompleteDecorator.decorate(editor);
		masterValues = new ArrayList<String> () ;
		masterValues = masterValues1;
		editor.setEditable(true);
		editor.setFont(new Font("Tahoma", Font.BOLD, 12));
		//editor.setForeground(new Color(0, 0, 128));
		
		JTextField editorComponent = (JTextField)  editor.getEditor().getEditorComponent();
		editorComponent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(editor.getEditor().getItem() == null ) return;
				editor.showPopup();
				Object child =  editor.getAccessibleContext().getAccessibleChild(0);
				BasicComboPopup popup = (BasicComboPopup)child;
				JList<Object> list = popup.getList();
				boolean result = false;
				for (int i = 0; i < list.getModel().getSize(); i++)
				{
					String value =  list.getModel().getElementAt(i).toString();
					if ( value.toString().startsWith(editor.getEditor().getItem().toString()))
						result = true;
				}
				if (result == false)
					editor.getEditor().setItem( editor.getEditor().getItem().toString().substring(0, editor.getEditor().getItem().toString().length() - 1));
				//***********************************
				for (int i = 0; i < list.getModel().getSize(); i++)
				{
					if(editor.getEditor().getItem() != null)
					{
					String value =  list.getModel().getElementAt(i).toString();
					if (value.toString().startsWith(editor.getEditor().getItem().toString()))
					{
						list.setSelectedIndex(i);
						list.scrollRectToVisible(list.getCellBounds(i, i+3));
						if (nerden.equals("fatura"))
							FATURA.bilgi_doldur( value);
						else  if (nerden.equals("imalat"))
							IMALAT.bilgi_doldur( value);
						else  if (nerden.equals("toplu"))
							COKLU_IMALAT.bilgi_doldur(editorComponent.getText().toString());
						else if (nerden.equals("zayi"))
							ZAYI.bilgi_doldur(editorComponent.getText().toString());
						else if (nerden.equals("irsaliye"))
							IRSALIYE.bilgi_doldur(editorComponent.getText().toString());
						else if (nerden.equals("recete"))
							RECETE.bilgi_doldur(editorComponent.getText().toString());
						else if (nerden.equals("kam_cikis"))
							CEK_CIKIS.cek_kontrol(editorComponent.getText().toString(),table.getSelectedRow());
						else if (nerden.equals("ker_cikis"))
						{
							try {
								KERESTE_CIKIS.kod_aciklama_bul(editorComponent.getText().toString());
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						return;
					}
				}
				}
			}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 27)
				{
					editor.getEditor().setItem("");
					if (nerden.equals("ker_cikis"))
					{
						try {
							KERESTE_CIKIS.kod_aciklama_bul(editorComponent.getText().toString());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		editorComponent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					if (table.getSelectedRow() < 0 ) return ;
					if (nerden.equals("ker_cikis"))
					{
						PAKET_ARA arm = new PAKET_ARA();
						arm.setSize(1000, 400);
						final Toolkit toolkit = Toolkit.getDefaultToolkit();
						final Dimension screenSize = toolkit.getScreenSize();
						final int x = (screenSize.width - arm.getWidth()) / 2;
						final int y = (screenSize.height - arm.getHeight()) / 2;
						arm.setLocation(x, y);
						arm.setVisible(true);
						editor.setSelectedItem(oac.stk_kodu);
						table.getCellEditor().stopCellEditing();
						KERESTE_CIKIS.pakkont(editorComponent.getText().toString());
					}
					else {
						URUN_ARAMA arm ;
						arm = new URUN_ARAMA();
						arm.setVisible(true);
						editor.setSelectedItem(oac.stk_kodu);
						FATURA.bilgi_doldur(oac.stk_kodu);
						if(table.getCellEditor() != null)
							table.getCellEditor().stopCellEditing();
					}
				}
			}});
		editor.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				
				if (nerden.equals("fatura"))
					FATURA.bilgi_doldur( editorComponent.getText().toString());
				else  if (nerden.equals("imalat"))
					IMALAT.bilgi_doldur(editorComponent.getText().toString());
				else  if (nerden.equals("toplu"))
					COKLU_IMALAT.bilgi_doldur(editorComponent.getText().toString());
				else if (nerden.equals("zayi"))
					ZAYI.bilgi_doldur(editorComponent.getText().toString());
				else if (nerden.equals("irsaliye"))
					IRSALIYE.bilgi_doldur(editorComponent.getText().toString());
				else if (nerden.equals("recete"))
					RECETE.bilgi_doldur(editorComponent.getText().toString());
				else if (nerden.equals("kam_cikis"))
					CEK_CIKIS.cek_kontrol(editorComponent.getText().toString(),table.getSelectedRow());
				else if (nerden.equals("ker_cikis"))
				{
					try {
						KERESTE_CIKIS.kod_aciklama_bul(editorComponent.getText().toString());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					KERESTE_CIKIS.pakkont(editorComponent.getText().toString());
				}
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
