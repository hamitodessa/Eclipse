package OBS_2025;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.net.*;


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
 *  Opens a window with help text if not already open and scrolls to the
 *  required help topic.<br>
 *  Singleton class.
 */
public class HelpFrame extends JFrame implements KeyListener {

  private JEditorPane help_pane = null;
  private static HelpFrame instance = null;


  /**
   *  If not already instanciated the HelpFrame will be created.
   *
   * @return    Description of the Return Value
   */
  public static HelpFrame get_instance() {
    if (instance == null) {
      // create instance of HelpFrame
      instance = new HelpFrame();
      instance.enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try {
        instance.help_pane = new JEditorPane();
        instance.setIconImage(Toolkit.getDefaultToolkit().createImage(HelpFrame.class.getResource("dl.png")));
        instance.setSize(new Dimension(550, 400));
        instance.setTitle("DateLook Help");

        JScrollPane scrollPane = new JScrollPane();

        ((JPanel) (instance.getContentPane())).add(scrollPane);
        instance.addKeyListener(instance);
        instance.help_pane.addKeyListener(instance);
        instance.help_pane.setContentType("text/html");
        instance.help_pane.setEditable(false);
        instance.help_pane.addHyperlinkListener(
          new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
              if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                instance.help_pane.scrollToReference(e.getDescription().substring(1));
              }
            }
          });
        instance.help_pane.read(HelpFrame.class.getResourceAsStream("help.htm"), "Help");
        scrollPane.getViewport().add(instance.help_pane, null);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      instance.setLocation(70, 70);
      instance.setVisible(false);
    }
    return instance;
  }


  /**
   *  HelpFrame set visible and scrolled to the required topic.
   *
   * @param  link_description  Description of the Parameter
   */
  public void help(String link_description) {
    if (!isVisible()) {
      setLocation(70, 70);
      setVisible(true);
    }
    requestFocus();
    if (link_description != null) {
      help_pane.scrollToReference(link_description);
    }
    else {
      help_pane.scrollToReference("top");
    }
  }


  /**
   *  Process window event.<br>
   *  If window closing button pressed hide the frame.
   *
   * @param  e  window evnet
   */
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      this.setVisible(false);
      return;
    }
    super.processWindowEvent(e);
  }


  /**
   *  Check for pressed key and handles it.<br>
   *  ctrl-C - closes the frame<br>
   *  ctrl-Q - closes the frame<br>
   *
   *
   * @param  e  key event
   */
  public void keyPressed(KeyEvent e) {
    if (e.getModifiers() == InputEvent.CTRL_MASK) {
      if (e.getKeyCode() == KeyEvent.VK_Q) {
        this.setVisible(false);
        return;
      }
      if (e.getKeyCode() == KeyEvent.VK_C) {
        this.setVisible(false);
        return;
      }
    }
  }


  /**
   *  Dummy
   *
   * @param  e  key event
   */
  public void keyReleased(KeyEvent e) {
  }


  /**
   *  Dummy
   *
   * @param  e  key event
   */
  public void keyTyped(KeyEvent e) {
  }
}

