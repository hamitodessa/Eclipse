package OBS_C_2025;

import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;

@SuppressWarnings("serial")
public class Obs_TextFIeld  extends JTextField{
	
	public Obs_TextFIeld(int limit,String plcHolderText)
	{
		putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, plcHolderText);
		JTextFieldRegularPopupMenu.addTo(this);
		setDocument(new JTextFieldLimit(limit));
	}
}
