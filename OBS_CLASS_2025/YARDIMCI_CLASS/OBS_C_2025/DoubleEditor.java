package OBS_C_2025;

import java.awt.*;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.text.*;
import java.awt.event.*;

@SuppressWarnings({"serial","unused"})
public class DoubleEditor extends DefaultCellEditor
{
    JFormattedTextField ftf;
    DecimalFormat decimalFormat;
  
    public DoubleEditor(int kesir)
    {
        super(new JFormattedTextField());
       ftf = (JFormattedTextField)getComponent();
        ftf.setBorder(new LineBorder(Color.BLACK));
        if (kesir == 0)
        {
        decimalFormat = new DecimalFormat( "##,###,##0" );
        }
        else if (kesir == 1)
        {
        decimalFormat = new DecimalFormat( "##,###,##0.0" );
        }
        else if (kesir == 2)
        {
        decimalFormat = new DecimalFormat( "##,###,##0.00" );
        }
        else  if (kesir == 3)
        {
        decimalFormat = new DecimalFormat( "##,###,##0.000" );
        }
        NumberFormatter intFormatter = new NumberFormatter(decimalFormat);
        intFormatter.setFormat(decimalFormat);
        ftf.setFormatterFactory(new DefaultFormatterFactory(intFormatter));
        ftf.setValue(0);
        ftf.setFocusLostBehavior(JFormattedTextField.PERSIST);
        ftf.setHorizontalAlignment(JFormattedTextField.RIGHT);
        ftf.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "check");
       
        ftf.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			ftf.setText(ftf.getText());
			ftf.select(0, ftf.getText().length());
			}
			@Override
			public void focusLost(FocusEvent e) {
		
			ftf.select(0, 0);
			}
        });
        ftf.getActionMap().put("check", new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (!ftf.isEditValid())  //The text is invalid.
                {
                    if (userSaysRevert())
                    {
                        ftf.postActionEvent(); //inform the editor
                    }
                }
                else
                    try
                    {
                        ftf.commitEdit();     //so use it.
                        ftf.postActionEvent(); //stop editing
                    }
                    catch (java.text.ParseException exc) { }
            }
        });
    }
    @Override
    public boolean isCellEditable(EventObject event)
    {
        JTable table = (JTable)event.getSource();
        return true;
    }

    public Component getTableCellEditorComponent(
        JTable table, Object value, boolean isSelected, int row, int column)
    {
         JFormattedTextField ftf = (JFormattedTextField)super.getTableCellEditorComponent(
            table, value, isSelected, row, column);
        ftf.setValue(value);
     	ftf.setText(ftf.getText());
		ftf.select(0, ftf.getText().length());
        ftf.setHorizontalAlignment(JFormattedTextField.RIGHT);
         return ftf;
    }

    //Override to ensure that the value remains an Integer.
	

	public Object getCellEditorValue()
    {
        JFormattedTextField ftf = (JFormattedTextField)getComponent();
        Object o = ftf.getValue();
        if (o instanceof Double)
        {
            return o;
        }
        else if (o instanceof Number)
        {
            return new Double(((Number)o).doubleValue());
        }
        else
        {
            try
            {
                return decimalFormat.parseObject(o.toString());
            }
            catch (ParseException exc)
            {
                System.err.println("Girilen Deger: Ayristirilamiyor : " + o);
                return null;
            }
        }
    }
    public boolean stopCellEditing()
    {
        JFormattedTextField ftf = (JFormattedTextField)getComponent();
        if (ftf.isEditValid())
        {
            try
            {
                ftf.commitEdit();
             }
            catch (java.text.ParseException exc) { }
        }
        else
        {
            if (!userSaysRevert())  //user wants to edit
            {
                return false; //don't let the editor go away
            }
         }

         return super.stopCellEditing();
    }

    protected boolean userSaysRevert() {
    	Toolkit.getDefaultToolkit().beep();
    	ftf.selectAll();
    	Object[] options = {"Duzelt",
    	"Geri Al"};
    	int answer = JOptionPane.showOptionDialog(
    			SwingUtilities.getWindowAncestor(ftf),
    			"Değer,bir rakkam olmalıdır "
    		  + "Düzenlemeye devam edebilirsiniz "
    		  + "veya son geçerli değere dön.",
    			"Geçersiz Metin Girildi",
    			JOptionPane.YES_NO_OPTION,
    			JOptionPane.ERROR_MESSAGE,
    			null,
    			options,
    			options[1]);

    	if (answer == 1) { //Revert!
    		ftf.setValue(ftf.getValue());
    		return true;
    	}
    	return false;
    }
}

