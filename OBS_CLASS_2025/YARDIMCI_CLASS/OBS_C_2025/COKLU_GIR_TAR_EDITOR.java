package OBS_C_2025;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings({"serial","unused"})
public class COKLU_GIR_TAR_EDITOR extends DefaultCellEditor {

	private static final Border red = new LineBorder(Color.red);
    private static final Border black = new LineBorder(Color.black);
    private JTextField textField;
   

    public COKLU_GIR_TAR_EDITOR(JTextField textField) {
        super(textField);
       
        this.textField = textField;
        this.textField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
			textField.setText(	dateFormater(textField.getText() , "dd.MM.yyyy", "EEE MMM dd kk:mm:ss zzzz yyyy" ));
	 		textField.select(0, textField.getText().length());
			}
			@Override
			public void focusLost(FocusEvent e) {
				textField.select(0, 0);
				
			}
        });
        
        this.textField.setHorizontalAlignment(JTextField.LEFT);
        
        this.textField .getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			   // DISTAN_AKTAR.tar(textField.getText());
			  }
			  public void removeUpdate(DocumentEvent e) {
			    //DISTAN_AKTAR.tar(textField.getText());
			  }
			  public void insertUpdate(DocumentEvent e) {
			    //DISTAN_AKTAR.tar(textField.getText());
			  }
			});
      //this.textField.setFont(new Font("Tahoma", Font.BOLD, 12));
         
    }

    @Override
    public Component getTableCellEditorComponent(JTable table,
        Object value, boolean isSelected, int row, int column) {
        textField.setBorder(black);
        return super.getTableCellEditorComponent(
            table, value, isSelected, row, column);
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
