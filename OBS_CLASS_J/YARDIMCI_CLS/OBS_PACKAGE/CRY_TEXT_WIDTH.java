package OBS_PACKAGE;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class CRY_TEXT_WIDTH {
	public static int uzunluk (String txt, Font fnt)
	{
		Canvas c = new Canvas();
		FontMetrics fm = c.getFontMetrics(fnt);
		int width = fm.stringWidth(txt);
		return width ;
	}
	
}
