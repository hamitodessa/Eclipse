import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
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
 *  Dialog window for synchronization of local and remote database via FTP or a
 *  net file system
 */
public class SyncDialog extends JDialog {

  private JPanel contentPane;
  /**
   *  The panel
   */
  public SyncPanel sync_panel;


  /**
   *  Constructor for the SyncDialog object
   *
   * @param  p   parent frame
   */
  public SyncDialog(JFrame p) {
    super(p, true);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      contentPane = (JPanel) this.getContentPane();
      this.setSize(14 * DateLookPanel.slot_height + DateLookPanel.frame_decor_width,
          (7 * DateLookPanel.slot_height) + DateLookPanel.frame_decor_height);
      this.setTitle("Synchroniser");
      sync_panel = new SyncPanel(this);
      contentPane.add(sync_panel);
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
    // catch the window close button when sync is in progress
    if (sync_panel.progress_bar.getValue() == 0) {
      super.processWindowEvent(e);
    }
  }


  /**
   *  Panel that contains components to control the synchronization<br>
   *  of local and remote database.
   */
  public static class SyncPanel extends RPanel {

    private RButton sync_button;
    private RButton close_button;
    private RComponent protocol_entry;
    private RComponent UID_text;
    private RComponent PW_text;
    private RComponent HP_text;
    private RTextField user_name_text_field;
    private RPasswordField password_text_field;
    private RTextField ftp_host_path_text_field;    // only one is visibale
    private RTextField path_text_field_1;           // depending on the protocol
    private RTextField path_text_field_2;
    private RProgressBar progress_bar;
    private final static int rel_font_size = 48;


    /**
     *  Constructor for the SyncPanel object
     *
     * @param  pw  parent window
     */
    public SyncPanel(SyncDialog pw) {
      super(pw, true);

      String[] sp = {"No server", "FTP-server", "file-server 1", "file-server 2"};
      protocol_entry = new RComponent(this, bg_color, Color.orange, Settings.get_instance(null).get_protocol(), 3, 1, 1, sp, "", 20, 50, 280, rel_font_size);
      String[] sa = {"UID:"};
      UID_text = new RComponent(this, bg_color, bg_color, 0, 0, 0, 1, sa, "", 20, 230, 180, rel_font_size);
      String[] sb = {"PW:"};
      PW_text = new RComponent(this, bg_color, bg_color, 0, 0, 0, 1, sb, "", 520, 230, 180, rel_font_size);
      String[] sc = {"H/P:"};
      HP_text = new RComponent(this, bg_color, bg_color, 0, 0, 0, 1, sc, "", 20, 430, 180, rel_font_size);

      user_name_text_field = new RTextField(170, 220, 330, rel_font_size);
      user_name_text_field.setText(Settings.get_instance(null).get_user_name());
      user_name_text_field.setToolTipText("Enter the user name.");
      user_name_text_field.addKeyListener(this);
      ftp_host_path_text_field = new RTextField(170, 420, 810, rel_font_size);
      ftp_host_path_text_field.setText(Settings.get_instance(null).get_ftp_host_path_name());
      ftp_host_path_text_field.setToolTipText("Enter hostname/path.");
      ftp_host_path_text_field.addKeyListener(this);
      path_text_field_1 =  new RTextField(170, 420, 810, rel_font_size);
      path_text_field_1.setText(Settings.get_instance(null).get_path_name1());
      path_text_field_1.setToolTipText("Enter path.");
      path_text_field_1.addKeyListener(this);
      path_text_field_2 =  new RTextField(170, 420, 810, rel_font_size);
      path_text_field_2.setText(Settings.get_instance(null).get_path_name2());
      path_text_field_2.setToolTipText("Enter path.");
      path_text_field_2.addKeyListener(this);
      password_text_field = new RPasswordField(Settings.get_instance(null).get_password(), 650, 220, 330, rel_font_size);
      password_text_field.setToolTipText("Enter password.");
      password_text_field.addKeyListener(this);
      if (protocol_entry.get_value() == Settings.protFTP) {
        path_text_field_1.setVisible(false);
        path_text_field_2.setVisible(false);
      }
      else if (protocol_entry.get_value() == Settings.protFile1) {
        path_text_field_2.setVisible(false);
        user_name_text_field.setVisible(false);
        password_text_field.setVisible(false);
        ftp_host_path_text_field.setVisible(false);
      } 
      else {
        path_text_field_1.setVisible(false);
        user_name_text_field.setVisible(false);
        password_text_field.setVisible(false);
        ftp_host_path_text_field.setVisible(false);
      }
      progress_bar = new RProgressBar(20, 620, 960, rel_font_size);
      progress_bar.setBackground(Color.white);
      progress_bar.setForeground(new Color(0, 0, 90));
      this.add(user_name_text_field);
      this.add(password_text_field);
      this.add(ftp_host_path_text_field);
      this.add(path_text_field_1);
      this.add(path_text_field_2);
      this.add(progress_bar);
      sync_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "sync", 18, 830, 180, rel_font_size);
      close_button = new RButton(this, new Color(0, 50, 100), Color.orange, Color.red, "close", 802, 830, 180, rel_font_size);
    }


    /**
     *  Paint component
     *
     * @param  g  Graphics object
     */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      sync_button.draw(g2);
      close_button.draw(g2);
      protocol_entry.draw(g2);
      UID_text.draw(g2);
      PW_text.draw(g2);
      HP_text.draw(g2);
    }


    /**
     *  Unock all buttons and text fields for input.
     */
    public void unlock_input() {
      // unlock close-button of EventManagerFrame (parent)
      ((EventTableFrame) (parent_window.getOwner())).set_close_button_locked(false);

      // unlock input
      user_name_text_field.setEditable(true);
      password_text_field.setEditable(true);
      ftp_host_path_text_field.setEditable(true);
      path_text_field_1.setEditable(true);
      path_text_field_2.setEditable(true);
      this.addMouseListener(this);
      this.addMouseWheelListener(this);
      this.addMouseMotionListener(this);
      this.addKeyListener(this);
      user_name_text_field.addKeyListener(this);
      ftp_host_path_text_field.addKeyListener(this);
      path_text_field_1.addKeyListener(this);
      path_text_field_2.addKeyListener(this);
      password_text_field.addKeyListener(this);
    }


    /**
     *  Lock all buttons and text fields for input.
     */
    private void lock_input() {
      // lock close-button of EventManagerFrame (parent)
      ((EventTableFrame) (parent_window.getOwner())).set_close_button_locked(true);

      // lock input
      user_name_text_field.setEditable(false);
      password_text_field.setEditable(false);
      ftp_host_path_text_field.setEditable(false);
      path_text_field_1.setEditable(false);
      path_text_field_2.setEditable(false);
      this.removeMouseListener(this);
      this.removeMouseWheelListener(this);
      this.removeMouseMotionListener(this);
      this.removeKeyListener(this);
      user_name_text_field.removeKeyListener(this);
      ftp_host_path_text_field.removeKeyListener(this);
      path_text_field_1.removeKeyListener(this);
      path_text_field_2.removeKeyListener(this);
      password_text_field.removeKeyListener(this);
    }


    /**
     *  Check for pressed key and handles it.<br>
     *  F1 - opens HelpFrame<br>
     *  ctrl-Q - saves the sync-settings and closes the dialog<br>
     *  ctrl-C - closes dialog<br>
     *  ctrl-S - starts syncronization
     *
     * @param  e  key event
     */
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_F1) {
        HelpFrame.get_instance().help("Synchronisation");
      }
      else {
        if (e.getModifiers() == InputEvent.CTRL_MASK) {
          if (e.getKeyCode() == KeyEvent.VK_Q) {
            Settings.get_instance(null).save_sync_settings(protocol_entry.get_value(), user_name_text_field.getText(),
                new String(password_text_field.getPassword()), ftp_host_path_text_field.getText(),
                path_text_field_1.getText(), path_text_field_2.getText());
            parent_window.dispose();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_C) {
            parent_window.dispose();
            return;
          }
          if (e.getKeyCode() == KeyEvent.VK_S) {
            start_sync();
            return;
          }
        }
      }
    }


    /**
     *  Handle mouse click.<br>
     *  Check whether buttons/entries are hit and if true handle the action.<br>
     *  close button - save sync settings and close dialog<br>
     *  sync button - start synchronization<br>
     *  protocol entry - toggle between FTP- or net file system
     *
     * @param  e  mouse event
     */
    public void mouseClicked(MouseEvent e) {
      if (sync_button.mouse_clicked(e)) {
        start_sync();
      }
      else if (close_button.mouse_clicked(e)) {
        Settings.get_instance(null).save_sync_settings(protocol_entry.get_value(), user_name_text_field.getText(),
            new String(password_text_field.getPassword()), ftp_host_path_text_field.getText(),
            path_text_field_1.getText(), path_text_field_2.getText());
        parent_window.dispose();
      }
      else if (protocol_entry.mouse_clicked(e)) {
        if (protocol_entry.get_value() == Settings.protFTP) {
          user_name_text_field.setVisible(true);
          password_text_field.setVisible(true);
          ftp_host_path_text_field.setVisible(true);
          path_text_field_1.setVisible(false);
          path_text_field_2.setVisible(false);
        }
        else if (protocol_entry.get_value() == Settings.protFile1) {
          user_name_text_field.setVisible(false);
          password_text_field.setVisible(false);
          ftp_host_path_text_field.setVisible(false);
          path_text_field_2.setVisible(false);
          path_text_field_1.setVisible(true);
        }
        else {
          user_name_text_field.setVisible(false);
          password_text_field.setVisible(false);
          ftp_host_path_text_field.setVisible(false);
          path_text_field_1.setVisible(false);
          path_text_field_2.setVisible(true);
        }
      }
    }


    /**
     *  Handle "mouse pressed" event.
     *
     * @param  e  mouse event
     */
    public void mousePressed(MouseEvent e) {
      sync_button.mouse_pressed(e);
      close_button.mouse_pressed(e);
      protocol_entry.mouse_pressed(e);
      this.repaint();
    }


    /**
     *  Handle "mouse released" event.
     *
     * @param  e  mouse event
     */
    public void mouseReleased(MouseEvent e) {
      sync_button.mouse_released(e);
      close_button.mouse_released(e);
      protocol_entry.mouse_released(e);
      this.repaint();
    }


    /**
     *  Handle "mouse moved" event.
     *
     * @param  e  mouse event
     */
    public void mouseMoved(MouseEvent e) {
      sync_button.mouse_over(e);
      close_button.mouse_over(e);
      protocol_entry.mouse_over(e);
      this.repaint();
    }


    /**
     *  Handle "mouse wheel" event.
     *
     * @param  e  mouse wheel event
     */
    public void mouseWheelMoved(MouseWheelEvent e) {
      if (protocol_entry.mouse_wheel_rotate(e)) {
        if (protocol_entry.get_value() == Settings.protFTP) {
          user_name_text_field.setVisible(true);
          password_text_field.setVisible(true);
          ftp_host_path_text_field.setVisible(true);
          path_text_field_1.setVisible(false);
          path_text_field_2.setVisible(false);
        }
        else if (protocol_entry.get_value() == Settings.protFile1) {
          user_name_text_field.setVisible(false);
          password_text_field.setVisible(false);
          ftp_host_path_text_field.setVisible(false);
          path_text_field_2.setVisible(false);
          path_text_field_1.setVisible(true);
        }
        else {
          user_name_text_field.setVisible(false);
          password_text_field.setVisible(false);
          ftp_host_path_text_field.setVisible(false);
          path_text_field_1.setVisible(false);
          path_text_field_2.setVisible(true);
        }      }
      this.repaint();
      this.requestFocus();
    }


    /**
     *  Start synchronization.
     */
    private void start_sync() {
      // save values first
      Settings.get_instance(null).save_sync_settings(protocol_entry.get_value(), user_name_text_field.getText(),
          new String(password_text_field.getPassword()), ftp_host_path_text_field.getText(),
          path_text_field_1.getText(), path_text_field_2.getText());

      String host_path;
      if (protocol_entry.get_value() == Settings.protFTP) {
        host_path = ftp_host_path_text_field.getText();
      }
      else if (protocol_entry.get_value() == Settings.protFile1) {
        host_path = path_text_field_1.getText();
      }
      else {
        host_path = path_text_field_2.getText();
      }
      Synchronizer synchronizer = new Synchronizer((SyncDialog) parent_window,
          protocol_entry.get_value(), user_name_text_field.getText(),
          new String(password_text_field.getPassword()),
          host_path, progress_bar);
      this.lock_input();

      // start synchronisation
      synchronizer.start();
    }
  }
}

