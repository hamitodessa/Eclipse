package OBS_2025;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxEditor;

@SuppressWarnings({ "serial", "rawtypes","unchecked" })
public class Java2sAutoComboBox extends JComboBox    {

	String nerden = "";
	List dnm;
	private class AutoTextFieldEditor extends BasicComboBoxEditor  
	{
		private Java2sAutoTextField getAutoTextFieldEditor() {
			return (Java2sAutoTextField) editor;
		}
		AutoTextFieldEditor(List<String> list) {
			editor = new Java2sAutoTextField(list, Java2sAutoComboBox.this);
		}
	}
	public Java2sAutoComboBox(List<String> list,String nerde) {
		isFired = false;
		nerden = nerde;
		dnm = list;
		autoTextFieldEditor = new AutoTextFieldEditor(list);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		setForeground(Color.BLUE);
		setEditable(true);
		setModel(new DefaultComboBoxModel(list.toArray()) 
		{
			protected void fireContentsChanged(Object obj, int i, int j) {
				if (!isFired)
					super.fireContentsChanged(obj, i, j);
			}
		});
		setEditor(autoTextFieldEditor);
	}
	public boolean isCaseSensitive() {
		return autoTextFieldEditor.getAutoTextFieldEditor().isCaseSensitive();
	}
	public void setCaseSensitive(boolean flag) {
		autoTextFieldEditor.getAutoTextFieldEditor().setCaseSensitive(flag);
	}
	public boolean isStrict() {
		return autoTextFieldEditor.getAutoTextFieldEditor().isStrict();
	}
	public void setStrict(boolean flag) {
		autoTextFieldEditor.getAutoTextFieldEditor().setStrict(flag);
	}
	public List getDataList() {
		return autoTextFieldEditor.getAutoTextFieldEditor().getDataList();
	}

	public void setDataList(List list) {
		autoTextFieldEditor.getAutoTextFieldEditor().setDataList(list);
		setModel(new DefaultComboBoxModel(list.toArray()));
	}
	void setSelectedValue(Object obj) {
		if (isFired) {
			return;
		} else {
			isFired = true;
			autoTextFieldEditor.setItem(obj);
			if (nerden.equals("fatura"))
			{
				FATURA.bilgi_doldur(obj.toString());
			}
			else if (nerden.equals("kereste"))
			{

			}
			else if (nerden.equals("recete"))
			{
				RECETE.bilgi_doldur(obj.toString());
			}

			else if (nerden.equals("imalat"))
			{

				IMALAT.bilgi_doldur(obj.toString());
			}
			else if (nerden.equals("irsaliye"))
			{
				IRSALIYE.bilgi_doldur(obj.toString());
			}
			else if (nerden.equals("zayi"))
			{
				ZAYI.bilgi_doldur(obj.toString());
			}
			else if (nerden.equals("toplu"))
			{
				// ZAYI.bilgi_doldur(obj.toString());
			}
			fireItemStateChanged(new ItemEvent(this, 701, selectedItemReminder,   1));
			isFired = false;
			return;
		}
	}

	protected void fireActionEvent() {
		if (!isFired)
			super.fireActionEvent();
	}
	private AutoTextFieldEditor autoTextFieldEditor;
	private boolean isFired;
}
