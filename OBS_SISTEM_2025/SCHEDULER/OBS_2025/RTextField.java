package OBS_2025;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.font.*;


/*
 *  Title:        DateLook
 *  Copyright:    Copyright (c) 2001 - 2011
 *  Author:       Rene Ewald
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License as
 *  published by the Free Software Foundation; either version 2 of
 *  the License, or (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details. You should have
 *  received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
/**
 *  Rene's TextField.<br>
 *  This TextField scales proportional to its parent panel.
 */
public class RTextField extends JTextField implements RInterface {
  private int x_relative;
  private int y_relative;
  private int w_relative;
  private int font_size_relative;
  
  
  /**
   *  Constructor for the RTextField object
   *
   * @param  x    relative position on panel x, range 0 - 1000, 0 - left, 1000 right border
   * @param  y    relative position on panel y, range 0 - 1000, 0 - top, 1000 bottom border
   * @param  w    relative width, range 0 - 1000, 0 - 0, 1000 - 100% of panel
   * @param  fs   relative font size (fs * panel_width / 1000 = real font size)
   */
  public RTextField(int x, int y, int w, int fs) {
    super();
    x_relative = x;
    y_relative = y;
    w_relative = w;
    font_size_relative = fs;
  }
  
  
  /**
   *  Parent resized<br>
   *  resizes the RTextField
   */
  public void parent_panel_resized() {
    Component parent = this.getParent();
    Graphics2D g2 = (Graphics2D) parent.getGraphics();
    if (g2 != null) {
      Font font = new Font("SansSerif", Font.PLAIN, font_size_relative * this.getParent().getWidth() / 1000);
      Rectangle2D bounds = font.getStringBounds("0", g2.getFontRenderContext());
      this.setFont(font);
      this.setBounds(parent.getWidth() * x_relative / 1000, parent.getHeight() * y_relative / 1000, 
                             parent.getWidth() * w_relative / 1000, (int) bounds.getHeight() * 14 / 10);
    }
  }
}

