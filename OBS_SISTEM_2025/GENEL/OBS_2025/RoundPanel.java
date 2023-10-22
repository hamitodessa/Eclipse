package OBS_2025;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RoundPanel extends JPanel {
    private Color clrColor ;
    private int radius ;
    public RoundPanel(Color clr, int radi) {
    	clrColor = clr ;
    	radius = radi ;
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
    	
          Dimension arcs = new Dimension(radius,radius);
          int width = getWidth();
          int height = getHeight();
          Graphics2D graphics = (Graphics2D) g;
          graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


          //Draws the rounded opaque panel with borders.
          graphics.setColor(getBackground());
          graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
          graphics.setColor(clrColor);
          graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
          super.paint(g);
    }
}
