import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;


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
 *  Color Frame
 */
public class ColourFrame extends JFrame {

  private JPanel contentPane;
  private EditorFrame my_editor_frame;
  private ColourPanel colour_panel;


  /**
   *  Construct the frame
   *
   * @param  ef   editor frame if created from this or null<br>
   *              ef == null - frame used to edit predef colours<br>
   *              ef != null - frame used to choose a colour for an event
   *
   * @param  c    if opened from ef the current colour of the event otherwise null
   */
  public ColourFrame(EditorFrame ef, Color c) {
    my_editor_frame = ef; // if != null then the predefined colours are NOT editable

    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      contentPane = (JPanel) this.getContentPane();
      setIconImage(Toolkit.getDefaultToolkit().createImage(EditorFrame.class.getResource("dl.png")));
      this.setSize(new Dimension(14 * DateLookPanel.slot_height + DateLookPanel.frame_decor_width,
          10 * DateLookPanel.slot_height + DateLookPanel.frame_decor_height));
      if (my_editor_frame == null) {
        this.setTitle("PredefColour Editor");
      }
      else {
        this.setTitle("Colour Chooser");
      }
      colour_panel = new ColourPanel(ef, c, this);
      contentPane.add(colour_panel);
      this.addKeyListener(colour_panel);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   *  Handle window event
   *
   * @param  e  window event
   */
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      if (my_editor_frame != null) {
        my_editor_frame.delete_colour_frame(null);
      }
      else {
        DateLookPanel.get_instance(null).set_colour_frame(null);
      }
    }
  }


  /**
   *  Frame disposes
   */
  public void dispose() {
    if (my_editor_frame != null) {
      my_editor_frame.delete_colour_frame(null);
    }
    else {
      DateLookPanel.get_instance(null).set_colour_frame(null);
    }
    super.dispose();
  }


  /**
   *  Colour Panel
   */
  public static class ColourPanel extends RPanel {

    private EditorFrame my_editor_frame;
    private ArrayList<RComponent> mouse_receiver_list = new ArrayList<RComponent>(); // all RComponents with mouse response (no RButtons!)
    private ArrayList<Renderer> renderer_list = new ArrayList<Renderer>();  // all RComponents and RButtons to be drawn

    // all RComponents
    private RComponent[] red_rc = new RComponent[8];
    private RComponent[] green_rc = new RComponent[8];
    private RComponent[] blue_rc = new RComponent[8];
    private RButton[] colour_button = new RButton[8];

    private RButton save_button;
    private RButton cancel_button;

    private RTextField[] colour_text = new RTextField[8];

    /**
     *  Constructor for the ColourPanel object
     *
     * @param  ef  event editor colour frame or null
     * @param  c   current colour of event or null
     * @param  pw  parent window
     */
    public ColourPanel(EditorFrame ef, Color c, Window pw) {
      super(pw, true);
      my_editor_frame = ef;

      if (c != null) {
        // colour[0] is the colour of current event if there is any
        Settings.get_instance(null).set_colour(0, new Color(c.getRed() / 5 * 5, c.getGreen() / 5 * 5, c.getBlue() / 5 * 5));
      }

      String select_button_string = "";
      if (my_editor_frame != null) {
        select_button_string = "select";
      }

      for (int i = 1; i < 8; i++) {
        // colour choosers
        red_rc[i] = new RComponent(this, new Color(240, 220, 220), new Color(255, 50, 50), Settings.get_instance(null).get_colour(i).getRed(), 255, 0, 5, null, "", 340, 40 + i * 120, 120, 48);
        green_rc[i] = new RComponent(this, new Color(220, 240, 220), new Color(50, 255, 50), Settings.get_instance(null).get_colour(i).getGreen(), 255, 0, 5, null, "", 480, 40 + i * 120, 120, 48);
        blue_rc[i] = new RComponent(this, new Color(200, 220, 240), new Color(100, 150, 255), Settings.get_instance(null).get_colour(i).getBlue(), 255, 0, 5, null, "", 620, 40 + i * 120, 120, 48);
        colour_button[i] = new RButton(this, Settings.get_instance(null).get_colour(i), Settings.get_instance(null).get_colour(i), Settings.get_instance(null).get_colour(i), select_button_string, 760, 40 + i * 120, 220, 48);

        // colour_text
        colour_text[i] = new RTextField(20, 30 + i * 120, 300, 48);
        colour_text[i].setText(Settings.get_instance(null).get_label(i));
        colour_text[i].addKeyListener(this);
        this.add(colour_text[i]);

        // add to renderer_list
        renderer_list.add(red_rc[i]);
        renderer_list.add(green_rc[i]);
        renderer_list.add(blue_rc[i]);
        renderer_list.add(colour_button[i]);

        if (my_editor_frame == null) {
          colour_text[i].setBackground(Color.white);
          colour_text[i].setEditable(true);
          mouse_receiver_list.add(red_rc[i]);
          mouse_receiver_list.add(green_rc[i]);
          mouse_receiver_list.add(blue_rc[i]);
        }
        else {
          colour_text[i].setBackground(bg_color);
          colour_text[i].setEditable(false);
        }
      }

      if (my_editor_frame == null) {
        // create button for save and cancel
        save_button = new RButton(this, Color.gray, Color.orange, Color.red, "save", 20, 35, 470, 48);
        cancel_button = new RButton(this, Color.gray, Color.orange, Color.red, "cancel", 510, 35, 470, 48);
      }
      else {
        // create row for current colour (index = 0)
        // colour choosers
        red_rc[0] = new RComponent(this, new Color(240, 220, 220), new Color(255, 50, 50), Settings.get_instance(null).get_colour(0).getRed(), 255, 0, 5, null, "", 340, 40, 120, 48);
        green_rc[0] = new RComponent(this, new Color(220, 240, 220), new Color(50, 255, 50), Settings.get_instance(null).get_colour(0).getGreen(), 255, 0, 5, null, "", 480, 40, 120, 48);
        blue_rc[0] = new RComponent(this, new Color(200, 220, 240), new Color(100, 150, 255), Settings.get_instance(null).get_colour(0).getBlue(), 255, 0, 5, null, "", 620, 40, 120, 48);
        colour_button[0] = new RButton(this, Settings.get_instance(null).get_colour(0), Settings.get_instance(null).get_colour(0), Settings.get_instance(null).get_colour(0), select_button_string, 760, 40, 220, 48);

        renderer_list.add(red_rc[0]);
        renderer_list.add(green_rc[0]);
        renderer_list.add(blue_rc[0]);
        renderer_list.add(colour_button[0]);

        mouse_receiver_list.add(red_rc[0]);
        mouse_receiver_list.add(green_rc[0]);
        mouse_receiver_list.add(blue_rc[0]);

        // colour_text
        colour_text[0] = new RTextField(20, 30, 300, 48);
        colour_text[0].setEditable(false);
        colour_text[0].setText("current");
        colour_text[0].setBackground(Color.white);
        colour_text[0].addKeyListener(this);
        this.add(colour_text[0]);
      }
    }


    /**
     *  Paint component
     *
     * @param  g  Graphics object
     */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      for (int i = 0; i < renderer_list.size(); i++) {
        // draw all labels
        ((Renderer) renderer_list.get(i)).draw(g2);
      }
      if (my_editor_frame == null) {
        save_button.draw(g2);
        cancel_button.draw(g2);
      }
    }


    /**
     *  Check for pressed key and handles it.<br>
     *  F1 - opens HelpFrame<br>
     *  ctrl-Q - closes the ColourFrame and if PredefColourEditor saves
     *  predefined colours<br>
     *  ctrl-S - closes the ColourFrame and if PredefColourEditor saves
     *  predefined colours<br>
     *  ctrl-C - closes the ColourFrame without any saving<br>
     *
     *
     * @param  e  key event
     */
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_F1) {
        if (my_editor_frame != null) {
          HelpFrame.get_instance().help("ColourChooser");
        }
        else {
          HelpFrame.get_instance().help("Predefined_colours");
        }
      }
      else {
        if (e.getModifiers() == InputEvent.CTRL_MASK) {
          if ((e.getKeyCode() == KeyEvent.VK_Q) || (e.getKeyCode() == KeyEvent.VK_S)) {
            if (my_editor_frame == null) {
              for (int i = 1; i < 8; i++) {
                Settings.get_instance(null).set_colour(i, new Color(red_rc[i].get_value(), green_rc[i].get_value(), blue_rc[i].get_value()));
                Settings.get_instance(null).set_label(i, colour_text[i].getText());
              }
              Settings.get_instance(null).save_colour_settings();
            }
            else
                if (my_editor_frame != null) {
              my_editor_frame.delete_colour_frame(new Color(red_rc[0].get_value(), green_rc[0].get_value(), blue_rc[0].get_value()));
            }
            parent_window.dispose();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_C) {
            parent_window.dispose();
            return;
          }
        }
      }
    }


    /**
     *  Handle mouse click.<br>
     *  Check whether buttons are hit and if true handle the action.<br>
     *  if ColourFrame works as predefColourEditor then<br>
     *  save button - saves the predefined colours and closes the ColourFrame
     *  <br>
     *  cancel button - closes the ColourFrame without saving<br>
     *  else (ColouFrame works as ColourChooser for the current event)<br>
     *  on of the eight colour buttons - closes the ColourFrame and set set
     *  colour of event to this colour
     *
     * @param  e  mouse event
     */
    public void mouseClicked(MouseEvent e) {
      for (int i = 0; i < mouse_receiver_list.size(); i++) {
        ((RComponent) mouse_receiver_list.get(i)).mouse_clicked(e);
      }
      if (my_editor_frame != null) {
        for (int i = 0; i < 8; i++) {
          if (colour_button[i].mouse_clicked(e)) {
            if (my_editor_frame != null) {
              my_editor_frame.delete_colour_frame(new Color(red_rc[i].get_value(), green_rc[i].get_value(), blue_rc[i].get_value()));
            }
            parent_window.dispose();
          }
        }
      }
      else {
        if (save_button.mouse_clicked(e)) {
          for (int i = 1; i < 8; i++) {
            Settings.get_instance(null).set_colour(i, new Color(red_rc[i].get_value(), green_rc[i].get_value(), blue_rc[i].get_value()));
            Settings.get_instance(null).set_label(i, colour_text[i].getText());
          }
          Settings.get_instance(null).save_colour_settings();
          parent_window.dispose();
          return;
        }
        if (cancel_button.mouse_clicked(e)) {
          parent_window.dispose();
          return;
        }
      }
      this.colour_set();
    }


    /**
     *  Handle "mouse pressed" event
     *
     * @param  e  mouse event
     */
    public void mousePressed(MouseEvent e) {
      for (int i = 0; i < mouse_receiver_list.size(); i++) {
        ((RComponent) mouse_receiver_list.get(i)).mouse_pressed(e);
      }
      if (my_editor_frame != null) {
        for (int i = 0; i < 8; i++) {
          colour_button[i].mouse_pressed(e);
        }
      }
      else {
        save_button.mouse_pressed(e);
        cancel_button.mouse_pressed(e);
      }
      this.colour_set();
      this.repaint();
    }


    /**
     *  Handle "mouse released" event
     *
     * @param  e  mouse event
     */
    public void mouseReleased(MouseEvent e) {
      for (int i = 0; i < mouse_receiver_list.size(); i++) {
        ((RComponent) mouse_receiver_list.get(i)).mouse_released(e);
      }
      if (my_editor_frame == null) {
        save_button.mouse_released(e);
        cancel_button.mouse_released(e);
      }
      else {
        for (int i = 0; i < 8; i++) {
          colour_button[i].mouse_released(e);
        }
      }
      this.repaint();
    }


    /**
     *  Handle "mouse moved" event
     *
     * @param  e  mouse event
     */
    public void mouseMoved(MouseEvent e) {
      for (int i = 0; i < mouse_receiver_list.size(); i++) {
        ((RComponent) mouse_receiver_list.get(i)).mouse_over(e);
      }
      if (my_editor_frame != null) {
        for (int i = 0; i < 8; i++) {
          colour_button[i].mouse_over2(e);
        }
      }
      else {
        save_button.mouse_over(e);
        cancel_button.mouse_over(e);
      }
      this.repaint();
    }


    /**
     *  Handle "mouse wheel" event
     *
     * @param  e  mouse wheel event
     */
    public void mouseWheelMoved(MouseWheelEvent e) {
      for (int i = 0; i < mouse_receiver_list.size(); i++) {
        ((RComponent) mouse_receiver_list.get(i)).mouse_wheel_rotate(e);
      }
      this.colour_set();
      this.repaint();
    }


    /**
     *  Set the colour of colour-buttons if changed.
     */
    private void colour_set() {
      Color tmp_colour;
      if (my_editor_frame == null) {
        for (int i = 1; i < 8; i++) {
          tmp_colour = new Color(red_rc[i].get_value(), green_rc[i].get_value(), blue_rc[i].get_value());
          colour_button[i].set_bg_colour(tmp_colour);
        }
      }
      else {
        tmp_colour = new Color(red_rc[0].get_value(), green_rc[0].get_value(), blue_rc[0].get_value());
        colour_button[0].set_bg_colour(tmp_colour);
        colour_button[0].set_high_light_colour(tmp_colour);
      }
    }
  }
}

