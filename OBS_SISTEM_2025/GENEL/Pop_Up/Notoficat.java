package Pop_Up;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.*;

@SuppressWarnings("serial")
public class Notoficat extends JPanel {

	/**
	 * Create the panel.
	 */
	public Notoficat() {
		setBackground(Color.WHITE);
		setOpaque(false);
		setMinimumSize(new Dimension(200, 300));
		setMaximumSize(new Dimension(200, 300));
		setLayout(new MigLayout("inset 0,fillx,wrap","[fill]"));
	}
@Override
protected void paintComponent(Graphics grphcs)
{
	Graphics2D g2 = (Graphics2D) grphcs.create();
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g2.setColor(getBackground());
	int header = 10 ;
	AffineTransform tran = new AffineTransform();
	tran.translate(getWidth() -25,5);
	tran.rotate(Math.toRadians(45));
    Path2D  p = new Path2D.Double(new RoundRectangle2D.Double(0,0,20,20,5,5),tran);
	java.awt.geom.Area area = new java.awt.geom.Area(p);
	area.add(new java.awt.geom.Area(new RoundRectangle2D.Double(0,header,getWidth(),getHeight() - header,10,10)));
	g2.fill(area);
	g2.dispose();
	super.paintComponent(grphcs);
}
}
