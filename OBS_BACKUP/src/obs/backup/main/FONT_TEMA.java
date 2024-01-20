package obs.backup.main;

import java.awt.Cursor;

import javax.swing.JFrame;
public class FONT_TEMA extends JFrame {
	private static final long serialVersionUID = 1L;
	public FONT_TEMA() {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		basla();	}
	private void basla()
	{
		OBS_BACKUP frame = new OBS_BACKUP();
		frame.setVisible(true);
		dispose();
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}
