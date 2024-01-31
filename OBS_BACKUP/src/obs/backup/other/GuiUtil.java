package obs.backup.other;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class GuiUtil {

	 private static Cursor defCursor  = Cursor.getDefaultCursor();
	 private static Cursor waitCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	 public static void setWaitCursor(Component c, boolean yesno)
	  {
		Cursor.getPredefinedCursor(yesno ? Cursor.WAIT_CURSOR :Cursor.DEFAULT_CURSOR);
	    c.setCursor(yesno ? waitCursor : defCursor);
	    getFrame(c).setCursor(yesno ? waitCursor : defCursor);
	  }
	  public static void setWaitCursor(boolean yesno)
	  {
	    Frame.getFrames()[0].setCursor(yesno ? waitCursor : defCursor);
	  }
	  public static Frame getFrame(Component c)
	  {
	    Object obj = c;
	    while (!(obj instanceof Frame))
	    		obj = ((Component)obj).getParent();
	    return (Frame)obj;
	  }
	  public static Color cloneColor(Color c)
	  {
	    return new Color(c.getRed(), c.getGreen(), c.getBlue());
	  }
	  public static final void setTextAntiAliasing(Graphics g, boolean yesno)
	  {
	    Object obj = yesno ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON
	                 : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
	    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, obj);
	  }
	  public static final void setAntiAliasing(Graphics g, boolean yesno)
	  {
	    Object obj = yesno ? RenderingHints.VALUE_ANTIALIAS_ON
	                 : RenderingHints.VALUE_ANTIALIAS_OFF;
	    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, obj);
	  }
}
