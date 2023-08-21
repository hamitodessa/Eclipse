package OBS_C_2025;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TABLO_TEXTBOX  extends DefaultCellEditor {

   
    private JTextField textField;
   

    public TABLO_TEXTBOX (JTextField textField,int karakter,Font font,int alignment) {
        super(textField);
      
        this.textField = textField;
        //this.textField.setHorizontalAlignment(JTextField.LEFT);
        this.textField.setHorizontalAlignment(alignment);
              
        this.textField.setDocument(new  JTextFieldLimit(karakter));
        this.textField.setFont(font);
      
        this.textField.addFocusListener(new FocusListener() {
       
			@Override
			public void focusGained(FocusEvent e) {
			textField.setText(textField.getText());
			textField.select(0,textField.getText().length());
				
			}

			@Override
			public void focusLost(FocusEvent e) {
			textField.select(0, 0);
				
			}
        	
        });
         
    }

    @Override
    
    public boolean stopCellEditing() {
       
        return super.stopCellEditing();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table,
        Object value, boolean isSelected, int row, int column) {
       
    	textField.setText(value.toString());
    	
        return super.getTableCellEditorComponent(
            table, value, isSelected, row, column);
    }
    
}