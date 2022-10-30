import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.UIManager;
import javax.swing.filechooser.*;
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
 *  Applications main class
 */
public class DateLook extends JFrame {

  JPanel contentPane;
  private AlarmHandler alarm_handler;
  private java.util.Timer alarm_checker = new java.util.Timer();
  private boolean database_locked;

  /**
   *  Constructor for the DateLook object
   */
  public DateLook(String[] args) {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      // check args for "-s" and open propper settings
      int i;
      for (i = 0; i < args.length; i++) {
        if (args[i].equals("-s") && (args.length > i + 1)) {
          Settings.get_instance(args[i + 1]); // create the settings singleton object here
          break;
        }
      }
      Settings.get_instance(null); // if not already created then create settings here with the
                                   // default directory

      DateLookPanel.get_instance(this);
      
      // check args for "-d" and open the propper database
      boolean done = false;
      for (i = 0; i < args.length; i++) {
        if (args[i].equals("-d") && (args.length > i + 1)) {
          EventMemory.get_instance(args[i + 1]);
          this.setTitle("DateLook 2.3.1 " + args[i + 1]);
          done = true;
          break;
        }
      }
      if (!done) {
        EventMemory.get_instance(null);
        this.setTitle("DateLook 2.3.1");
      }
      
      database_locked = false;
      if (EventMemory.get_instance(null).read_data_file() == false) {     // read dates from file and store in memory
        // database is locked
        database_locked = true;
      }
      
      setIconImage(Toolkit.getDefaultToolkit().createImage(DateLook.class.getResource("dl.png")));
      this.setLocation(Settings.get_instance(null).get_frame_x(), Settings.get_instance(null).get_frame_y());
      this.setSize(Settings.get_instance(null).get_frame_width(), 
                   Settings.get_instance(null).get_decor_height() + Settings.get_instance(null).get_number_of_slots() * Settings.get_instance(null).get_slot_height());
      
      contentPane = (JPanel) this.getContentPane();
      if (database_locked == false) {
        contentPane.add(DateLookPanel.get_instance(null));
        this.addKeyListener(DateLookPanel.get_instance(null));
      
        alarm_handler = new AlarmHandler();
      
        long timer_rate = 5 * 1000;   // check every 5 second
        alarm_checker.scheduleAtFixedRate(alarm_handler, (long) (timer_rate - Math.IEEEremainder((new GregorianCalendar()).getTime().getTime(), timer_rate)), timer_rate);
      }
      else {
        // display the error text on panel
        JTextArea error_msg = new JTextArea("Error: database file locked! Can't start DateLook. Stop other instances of DateLook using same database or if there are no other instances then delete '" + EventMemory.get_instance(null).get_lock_file_name() + "'.");
        error_msg.setLineWrap(true);
        error_msg.setEditable(false);
        contentPane.add(error_msg);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   *  Process window event.<br>
   *  If closing event is received the database will be saved to file<br>
   *  and DateLook will be left.
   *
   * @param  e  window event
   */
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      if (!database_locked) {
        EventMemory.get_instance(null).save(true);
      }
      super.processWindowEvent(e);
      System.exit(0);
    }
    else {
      super.processWindowEvent(e);
    }
  }


  /**
   *  Main method
   *
   * @param  args  first parameter is the name of the local database file,<br>
   *               following parameters are ignored,<br>
   *               if no parameter is given the default database file is used.
   */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    new DateLook(args).setVisible(true);
  }
}

