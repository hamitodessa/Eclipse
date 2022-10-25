import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.*;


/*
 *  Title:        DateLook
 *  Copyright:    Copyright (c) 2005
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
 *  Frame is opened if an alarm time of an event has been reached.
 */
public class AlarmFrame extends JFrame {

  JPanel contentPane;
  private AlarmPanel alarm_panel;


  /**
   *  Construct the frame
   *
   * @param  t  event for that the alerm should be given
   */
  public AlarmFrame(Event t) {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      alarm_panel = new AlarmPanel(t, this);
      contentPane = (JPanel) this.getContentPane();
      setIconImage(Toolkit.getDefaultToolkit().createImage(AlarmFrame.class.getResource("dl.png")));
      this.setSize(17 * DateLookPanel.slot_height + DateLookPanel.frame_decor_width,
          (9 * DateLookPanel.slot_height) / 2 + DateLookPanel.frame_decor_height);
      this.setTitle("DateLook Alarm");
      contentPane.add(alarm_panel);
      this.addKeyListener(alarm_panel);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   *  the panel containing all fields and buttons (edit, close).
   */
  public static class AlarmPanel extends RPanel {

    private Event event;
    private RButton edit_button;
    private RButton close_button;
    private RComponent date_field;
    private RComponent text_field;


    /**
     *  Constructor for the AlarmPanel object
     *
     * @param  t   event for that the alerm should be given
     * @param  pw  parent window
     */
    public AlarmPanel(Event t, Window pw) {
      super(pw, true);
      event = t;

      // set shown begin time to begin to present period of cyclic event
      long b = Converter.UTCplusPeriod2UTC(event.get_begin_UTC_ms(),
          event.get_period(), event.get_alarm_counter(),
          event.get_period_multiplier());

      // time
      String[] st = {Converter.ms2hm(b) + "  " + Converter.ms2dmyl(b)};
      date_field = new RComponent(this, bg_color, Color.orange, 0, 0, 0, 1, st, "", 15, 80, 970, 40);

      // text
      String[] tt = {event.get_summary()};
      text_field = new RComponent(this, event.get_renderer_color(), Color.orange,
          0, 0, 0, 1, tt, "", 15, 340, 970, 60);

      // edit button
      edit_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "edit", 15, 730, 150, 40);

      // close button
      close_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "close", 178, 730, 150, 40);
    }


    /**
     *  Paint component
     *
     * @param  g  Graphics object
     */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      edit_button.draw(g2);
      close_button.draw(g2);
      date_field.draw(g2);
      text_field.draw(g2);
    }


    /**
     *  Check for pressed key and handles it.<br>
     *  F1 - opens HelpFrame<br>
     *  ctrl-Q - closes the AlarmFrame<br>
     *  ctrl-C - closes the AlarmFrame<br>
     *  ctrl-S - opens Editor and closes AlarmFrame
     *
     * @param  e  key event
     */
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_F1) {
        HelpFrame.get_instance().help("Alarm");
      }
      else {
        if (e.getModifiers() == InputEvent.CTRL_MASK) {
          if (e.getKeyCode() == KeyEvent.VK_Q) {
            parent_window.dispose();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_C) {
            parent_window.dispose();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_E) {
            edit();
            return;
          }
        }
      }
    }


    /**
     *  Handle mouse click.<br>
     *  Check whether buttons are hit and if true handle the action.<br>
     *  edit button - open EditorFrame and close AlarmFrame<br>
     *  close button - close AlarmFrame
     *
     * @param  e  mouse event
     */
    public void mouseClicked(MouseEvent e) {
      if (edit_button.mouse_clicked(e)) {
        edit();
      }
      if (close_button.mouse_clicked(e)) {
        parent_window.dispose();
        return;
      }
    }


    /**
     *  Handle mouse press.
     *
     * @param  e  mouse event
     */
    public void mousePressed(MouseEvent e) {
      edit_button.mouse_pressed(e);
      close_button.mouse_pressed(e);
      this.repaint();
    }


    /**
     *  Handle mouse release.
     *
     * @param  e  mouse event
     */
    public void mouseReleased(MouseEvent e) {
      edit_button.mouse_released(e);
      close_button.mouse_released(e);
      this.repaint();
    }


    /**
     *  Handle mouse move.
     *
     * @param  e  mouse event
     */
    public void mouseMoved(MouseEvent e) {
      edit_button.mouse_over(e);
      close_button.mouse_over(e);
      this.repaint();
    }


    /**
     *  Open editor window.
     */
    private void edit() {
      if (event.get_my_editor_frame() == null) {
        EditorFrame ed = new EditorFrame(event, null, false, true);
        event.set_my_editor_frame(ed);
        ed.setLocation((int) this.getLocationOnScreen().getX() + 20,
            (int) this.getLocationOnScreen().getY() + 20);
        ed.setVisible(true);
        parent_window.dispose();
        return;
      }
    }
  }
}

