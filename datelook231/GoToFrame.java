import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.*;


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
 *  Goto Frame usewd to switch the main window to a certain date
 */
public class GoToFrame extends JFrame {

  JPanel contentPane;
  private GoToPanel go_to_panel;


  /**
   *  Construct the frame
   *
   */
  public GoToFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      go_to_panel = new GoToPanel(this);
      contentPane = (JPanel) this.getContentPane();
      setIconImage(Toolkit.getDefaultToolkit().createImage(GoToFrame.class.getResource("dl.png")));
      this.setSize(8 * DateLookPanel.slot_height + DateLookPanel.frame_decor_width,
          3 * DateLookPanel.slot_height + DateLookPanel.frame_decor_height);
      this.setTitle("GoTo");
      contentPane.add(go_to_panel);
      this.addKeyListener(go_to_panel);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   *  Process window event
   *
   * @param  e  window event
   */
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      DateLookPanel.get_instance(null).set_go_to_frame(null);
    }
  }


  /**
   *  GoToPanel
   */
  public static class GoToPanel extends RPanel {

    private RComponent day_field;
    private RComponent month_field;
    private RComponent year_field;
    private RButton go_button;
    private RButton cancel_button;


    /**
     *  Constructor for the GoToPanel object
     *
     * @param  pw  parent window
     */
    public GoToPanel(Window pw) {
      super(pw, true);

      long ms = (new GregorianCalendar()).getTime().getTime();
      int rel_font = 85;

      // day of month
      day_field = new RComponent(this, bg_color, Color.orange, Converter.ms2day(ms),
          31, 1, 1, null, ".", 52, 128, 150, rel_font);

      // month
      month_field = new RComponent(this, bg_color, Color.orange, Converter.ms2month(ms),
          11, 0, 1, Converter.longMonthNames, "", 205, 128, 490, rel_font);

      // year
      year_field = new RComponent(this, bg_color, Color.orange, Converter.ms2year(ms),
          2500, 1, 1, null, "", 695, 128, 255, rel_font);
      go_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "go to", 52, 575, 320, rel_font);
      cancel_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "cancel", 410, 575, 320, rel_font);
    }


    /**
     *  Paint component
     *
     * @param  g  Graphics object
     */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      day_field.draw(g2);
      month_field.draw(g2);
      year_field.draw(g2);
      go_button.draw(g2);
      cancel_button.draw(g2);
    }


    /**
     *  Check for pressed key and handles it.<br>
     *  F1 - opens HelpFrame<br>
     *  ctrl-Q - closes the frame<br>
     *  ctrl-S - closes the frame<br>
     *  ctrl-G - shifts the visible space of time in the main window to choosen date
     *
     * @param  e  key event
     */
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_F1) {
        HelpFrame.get_instance().help("How_to_use");
      }
      else {
        if (e.getModifiers() == InputEvent.CTRL_MASK) {
          if (e.getKeyCode() == KeyEvent.VK_Q) {
            DateLookPanel.get_instance(null).set_go_to_frame(null);
            parent_window.dispose();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_C) {
            DateLookPanel.get_instance(null).set_go_to_frame(null);
            parent_window.dispose();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_G) {
            go_to();
            return;
          }
        }
      }
    }


    /**
     *  Handle "mouse clicked" event
     *
     * @param  e  mouse event
     */
    public void mouseClicked(MouseEvent e) {
      day_field.mouse_clicked(e);
      month_field.mouse_clicked(e);
      year_field.mouse_clicked(e);
      if (go_button.mouse_clicked(e)) {
        DateLookPanel.get_instance(null).set_first_rendered_hour_UTC_ms(
            Converter.hmdmy2ms(0, 0, day_field.get_value(), month_field.get_value(), year_field.get_value()));
        DateLookPanel.get_instance(null).repaint();
        DateLookPanel.get_instance(null).set_go_to_frame(null);
        parent_window.dispose();
      }
      else if (cancel_button.mouse_clicked(e)) {
        DateLookPanel.get_instance(null).set_go_to_frame(null);
        parent_window.dispose();
      }
      this.repaint();
    }


    /**
     *  Handle "mouse pressed" event
     *
     * @param  e  mouse event
     */
    public void mousePressed(MouseEvent e) {
      go_button.mouse_pressed(e);
      cancel_button.mouse_pressed(e);
      this.repaint();
    }


    /**
     *  Handle "mouse released" event
     *
     * @param  e  mouse event
     */
    public void mouseReleased(MouseEvent e) {
      go_button.mouse_released(e);
      cancel_button.mouse_released(e);
      this.repaint();
    }


    /**
     *  Handle "mouse moved" event
     *
     * @param  e  mouse event
     */
    public void mouseMoved(MouseEvent e) {
      day_field.mouse_over(e);
      month_field.mouse_over(e);
      year_field.mouse_over(e);
      go_button.mouse_over(e);
      cancel_button.mouse_over(e);
      this.repaint();
    }


    /**
     *  Handle "mouse wheel" event
     *
     * @param  e  mouse wheel event
     */
    public void mouseWheelMoved(MouseWheelEvent e) {
      day_field.mouse_wheel_rotate(e);
      month_field.mouse_wheel_rotate(e);
      year_field.mouse_wheel_rotate(e);
      this.repaint();
    }


    /**
     *  shift the visible space of time in the main window to the choosen date<br>
     *  and close this frame
     */
    private void go_to() {
      DateLookPanel.get_instance(null).set_first_rendered_hour_UTC_ms(
          Converter.hmdmy2ms(0, 0, day_field.get_value(), month_field.get_value(), year_field.get_value()));
      DateLookPanel.get_instance(null).repaint();
      DateLookPanel.get_instance(null).set_go_to_frame(null);
      parent_window.dispose();
    }
  }
}

