package OBS_C_2025;

import javax.swing.JTextField;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatClientProperties;

@SuppressWarnings("serial")
public class Obs_TextFIeld  extends JTextField{
	
	
	public Obs_TextFIeld()
	{
		putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		JTextFieldRegularPopupMenu.addTo(this);
		UIManager.put( "TextComponent.arc", 5 );
	}
	public Obs_TextFIeld(int limit,String plcHolderText)
	{
		putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, plcHolderText);
		JTextFieldRegularPopupMenu.addTo(this);
		setDocument(new JTextFieldLimit(limit));
		UIManager.put( "TextComponent.arc", 5 );
	}
	
	public Obs_TextFIeld(int limit)
	{
		putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		JTextFieldRegularPopupMenu.addTo(this);
		setDocument(new JTextFieldLimit(limit));
		UIManager.put( "TextComponent.arc", 5 );
		
//		addFocusListener(new FocusListener() {
//			@Override
//			public void focusGained(FocusEvent e) {
//			select(0,getText().length());
//			}
//			@Override
//			public void focusLost(FocusEvent e) {
//			select(0, 0);
//			}
//	    });
	}
}
