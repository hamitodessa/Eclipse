package OBS_PACKAGE;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class FIT_IMAGE {
	public static Image image(Image img , int w , int h)
	{
	BufferedImage resizedimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	Graphics2D g2 = resizedimage.createGraphics();
	g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g2.drawImage(img, 0, 0,w,h,null);
	g2.dispose();
	return resizedimage;
	}
}
