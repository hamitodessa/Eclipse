package OBS_C_2025;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


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

	public static  ImageIcon formIcon(ImageIcon img, int w , int h)
	{
		ImageIcon formImage = img;
		Image image = formImage.getImage();
		Image newImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		formImage = new ImageIcon(newImage);
		return formImage;
	}
	public static  Image panelIcon(ImageIcon img, int w , int h)
	{
		Image image = img.getImage();
		Image newImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return newImage;
	}
}
