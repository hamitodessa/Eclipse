package OBS_2025;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import OBS_C_2025.Obs_TextFIeld;


@SuppressWarnings({"serial","unused","static-access"})
public class COKLU_GIRIS_HSP extends DefaultCellEditor {
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static final Border red = new LineBorder(Color.red);
	private static final Border black = new LineBorder(Color.black);
	private Obs_TextFIeld textField;
	private String turu= "" ;
	public COKLU_GIRIS_HSP(Obs_TextFIeld textField,String tur) {
		super(textField);
		turu = tur ;
		this.textField = textField;
		this.textField.setHorizontalAlignment(JTextField.LEFT);
		this.textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textField.setText( oac.hsp_hsp_kodu);
					} catch (Exception ex) {} 
				}
			}
		});
		this.textField .getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				if (tur.equals("C")) return;
				DISTAN_AKTAR.doldur(textField.getText(),tur);
			}
			public void removeUpdate(DocumentEvent e) {
				if (tur.equals("C")) return;
				DISTAN_AKTAR.doldur(textField.getText(),tur);
			}
			public void insertUpdate(DocumentEvent e) {
				if (tur.equals("C")) return;
				DISTAN_AKTAR.doldur(textField.getText(),tur);
			}
		});
		this.textField.setFont(new Font("Tahoma", Font.BOLD, 12));
	}
	@Override
	public boolean stopCellEditing() {
		try {
			if (turu.equals("C")) {
			}
			else
				DISTAN_AKTAR.doldur(textField.getText(),turu);
		} catch (NumberFormatException e) {
			return false;
		}
		return super.stopCellEditing();
	}
	@Override
	public Component getTableCellEditorComponent(JTable table,
			Object value, boolean isSelected, int row, int column) {
		textField.setBorder(black);
		return super.getTableCellEditorComponent(
				table, value, isSelected, row, column);
	}
}