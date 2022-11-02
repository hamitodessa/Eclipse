package OBS_C_2025;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
      private BufferedImage image;
  
    public void setImage(BufferedImage image)
    {
      this.image = image;
      repaint();
    }
 
    @Override
	public void paintComponent(Graphics g)
    {
        setBackground(new Color(245, 255, 250));
        super.paintComponent(g);
        if (image != null)
        {
            g.drawImage(image, 0, 0, null);
        }
    }
    public   boolean getImage()
    {
    	
    	if (image == null)
    	{
		return false;
    	}
    	else
    	{
    		return true;
    	}

 	   
    }
}
