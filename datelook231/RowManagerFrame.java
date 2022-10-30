import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*;
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
 *  Row Manager Frame, <br>
 *  used to change row labels and the number of rows.
 */
public class RowManagerFrame extends JFrame {

  private JPanel contentPane;
  private RowManagerPanel row_manager_panel;


  /**
   *  Construct the frame
   *
   */
  public RowManagerFrame() {

    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      contentPane = (JPanel) this.getContentPane();
      setIconImage(Toolkit.getDefaultToolkit().createImage(EditorFrame.class.getResource("dl.png")));
      this.setSize(new Dimension(8 * DateLookPanel.slot_height + DateLookPanel.frame_decor_width,
          27 * DateLookPanel.slot_height + DateLookPanel.frame_decor_height));
      this.setTitle("Row Manager");
      row_manager_panel = new RowManagerPanel(this);
      contentPane.add(row_manager_panel);
      this.addKeyListener(row_manager_panel);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   *  Process window event
   *
   * @param  e  Window event
   */
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      DateLookPanel.get_instance(null).set_row_manager_frame(null);
    }
  }


  /**
   *  Dispose frame
   */
  public void dispose() {
    DateLookPanel.get_instance(null).set_row_manager_frame(null);
    super.dispose();
  }


  /**
   *  Row Manager Panel
   */
  public static class RowManagerPanel extends RPanel {
    private int max_row_number = 20;
    private RComponent label_rows;      // text "Visible rows"
    private RComponent number_of_rows;  // number of shown rows in the main-window
    private RComponent[] row_number = new RComponent[max_row_number];     // array of fields with the number of row 
    private RTextField[] row_label_field = new RTextField[max_row_number]; // array of fields to edit the label of a row
    private RButton save_button;
    private RButton cancel_button;
  
    /**
     *  Constructor for the RowManagerPanel object
     *
     */
    public RowManagerPanel(RowManagerFrame rmf) {
      super(rmf, true);
      
      String[] st = {""};
      label_rows = new RComponent(this, bg_color, bg_color, 0, 0, 0, 0, st, "Visible rows", 50, 17, 500, 85);
      number_of_rows = new RComponent(this, bg_color, Color.orange, Settings.get_instance(null).get_number_of_slots() - 5, max_row_number, 5, 1, null, "", 550, 20, 150, 85);
      
      for (int i = 0; i < max_row_number; i++) {
        row_number[i] = new RComponent(this, bg_color, bg_color, i + 1, i + 1, i + 1, 0, null, "", 50, 62 + i * 44, 150, 85);
        row_label_field[i] = new RTextField(200, 62 + i * 44, 750, 85);
        row_label_field[i].setText(Settings.get_instance(null).get_row_label(i));
        row_label_field[i].addKeyListener(this);
        row_label_field[i].setBackground(Color.white);
        row_label_field[i].setEditable(true);
        this.add(row_label_field[i]);
      }
      
      save_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "save", 50, 955, 400, 85);
      cancel_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "cancel", 550, 955, 400, 85);
    }


    /**
     *  Paint component
     *
     * @param  g  Graphics object
     */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      label_rows.draw(g2);
      number_of_rows.draw(g2);
      for (int i = 0; i < max_row_number; i++) {
        row_number[i].draw(g2);
      }
      save_button.draw(g2);
      cancel_button.draw(g2);
    }


    /**
     *  Handle pressed key.
     *
     *  F1 - opens HelpFrame<br>
     *  ctrl-Q - saves row settings and closes frame<br>
     *  ctrl-S - saves row settings and closes frame<br>
     *  ctrl-C - closes frame without saving of row settings<br>
     *
     * @param  e  key event
     */
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_F1) {
        HelpFrame.get_instance().help("Row_manager");
      }
      else {
        if (e.getModifiers() == InputEvent.CTRL_MASK) {
          if ((e.getKeyCode() == KeyEvent.VK_Q) || (e.getKeyCode() == KeyEvent.VK_S)) {
            save();
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
     *  Handle "mouse click" event
     *
     * @param  e  mouse event
     */
    public void mouseClicked(MouseEvent e) {
      number_of_rows.mouse_clicked(e);
      if (save_button.mouse_clicked(e)) {
        this.save();
        parent_window.dispose();
        return;
      }
      if (cancel_button.mouse_clicked(e)) {
        parent_window.dispose();
        return;
      }
      this.repaint();
    }

    
    /**
     *  Handle "mouse pressed" event
     *
     * @param  e  mouse event
     */
    public void mousePressed(MouseEvent e) {
      save_button.mouse_pressed(e);
      cancel_button.mouse_pressed(e);
      this.repaint();
    }


    /**
     *  Handle "mouse released" event
     *
     * @param  e  mouse event
     */
    public void mouseReleased(MouseEvent e) {
      save_button.mouse_released(e);
      cancel_button.mouse_released(e);
      this.repaint();
    }
    

    /**
     *  Handle "mouse moved" event
     *
     * @param  e  mouse event
     */
    public void mouseMoved(MouseEvent e) {
      number_of_rows.mouse_over(e);
      save_button.mouse_over(e);
      cancel_button.mouse_over(e);
      this.repaint();
    }
    
    
    /**
     *  Handle "mouse wheel" event
     *
     * @param  e  mouse wheel event
     */
    public void mouseWheelMoved(MouseWheelEvent e) {
      number_of_rows.mouse_wheel_rotate(e);
      this.repaint();
    }
    
    
    /**
     *  Save changed row settings
     *
     */
    private void save() {
      for (int i = 0; i < max_row_number; i++) {
        Settings.get_instance(null).set_row_label(i, row_label_field[i].getText());
      }
      Settings.get_instance(null).save_row_settings(number_of_rows.get_value() + 5);
      DateLookPanel.get_instance(null).set_row_number(number_of_rows.get_value());
      DateLookPanel.get_instance(null).changed();
    }
  }
}

