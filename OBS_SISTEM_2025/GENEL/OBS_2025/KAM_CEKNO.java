package OBS_2025;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import OBS_C_2025.JTextFieldLimit;

public class KAM_CEKNO extends DefaultCellEditor {

    private static final Border red = new LineBorder(Color.red);
    private static final Border black = new LineBorder(Color.black);
    private JTextField textField;
    private String turu= "" ;
    boolean varmi = false;
    public int satir ;

    public KAM_CEKNO(JTextField textField,String tur) {
        super(textField);
        turu = tur ;
       
        this.textField = textField;
        this.textField.setHorizontalAlignment(JTextField.LEFT);
        this.textField.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (e.getClickCount() == 2) 
				{
        			//  System.out.println("mouse="+tur);
        			
				}
        	}
        });
        textField.addFocusListener(new FocusListener() {
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
  	    this.textField.setDocument(new JTextFieldLimit(10));
        //this.textField.getDocument().addDocumentListener(new DocumentListener() 
     
      this.textField.setFont(new Font("Tahoma", Font.BOLD, 12));
      this.textField.setForeground( Color.BLUE);
    }

    @Override
    public boolean stopCellEditing() {
        try {
        	if (turu.equals("CG")) { 
  			  varmi =  CEK_GIRIS.cek_kontrol(textField.getText(),turu);
  				 if (varmi) textField.setText(""); 
  			 }
        	else if (turu.equals("CC")) { 
    			  varmi =  CEK_CIKIS.cek_kontrol(textField.getText(),turu,satir);
   				 if (varmi) textField.setText(""); 
        	}
        } catch (NumberFormatException e) {
            return false;
        }
        return super.stopCellEditing();
    }
    @Override
    public Component getTableCellEditorComponent(JTable table,
        Object value, boolean isSelected, int row, int column) {
        textField.setBorder(black);
        satir = row ;
        return super.getTableCellEditorComponent(
            table, value, isSelected, row, column);
    }
    
}
