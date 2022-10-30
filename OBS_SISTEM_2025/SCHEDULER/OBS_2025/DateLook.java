package OBS_2025;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.UIManager;
import javax.swing.filechooser.*;
import java.io.*;


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
 *  Applications main class
 */
public class DateLook extends JFrame {

  JPanel contentPane;
  private DateLookPanel date_look_panel;
  private EventMemory event_memory;
  private AlarmHandler alarm_handler;
  private java.util.Timer alarm_checker = new java.util.Timer();


  /**
   *  Constructor for the DateLook object
   */
  public DateLook() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      // create .datelook-folder if not existing and
      // move old (<1.8) config-files to there
      String dir_name = FileSystemView.getFileSystemView().getDefaultDirectory().getAbsolutePath() + File.separatorChar + ".datelook";
      String old_dir_name = FileSystemView.getFileSystemView().getDefaultDirectory().getAbsolutePath();
  //    System.out.println(old_dir_name);
      File dir = new File(dir_name);
      if (dir.exists() & !(dir.isDirectory())) {
        dir.delete();  // delete .datelook
      }
      if (!(dir.exists())) {
        dir.mkdir();
        File old_data_file = new File(old_dir_name + File.separatorChar + ".datelook.vcs");
        File old_rc_file = new File(old_dir_name + File.separatorChar + ".datelookrc");
        File old_syncrc_file = new File(old_dir_name + File.separatorChar + ".datelooksyncrc");
        File new_data_file = new File(dir_name + File.separatorChar + "dates.vcs");
        File new_rc_file = new File(dir_name + File.separatorChar + "rc");
        File new_syncrc_file = new File(dir_name + File.separatorChar + "syncrc");
        if (old_data_file.exists()) {
          old_data_file.renameTo(new_data_file);
        }
        if (old_rc_file.exists()) {
          old_rc_file.renameTo(new_rc_file);
        }
        if (old_syncrc_file.exists()) {
          old_syncrc_file.renameTo(new_syncrc_file);
        }
        HelpFrame.get_instance().help(null);   // started automatically if first use
      }

      date_look_panel = new DateLookPanel(this);
      event_memory = new EventMemory(date_look_panel);
      event_memory.read_data_file();     // read dates from file and store in memory
      date_look_panel.set_event_memory(event_memory);

      //setIconImage(Toolkit.getDefaultToolkit().createImage(DateLook.class.getResource("dl.png")));
   setIconImage(Toolkit.getDefaultToolkit().getImage(LOGIN.class.getResource("/SCH_ICONS/dl.png")));
      Rectangle rect = (new Settings().get_position_and_size());
      this.setLocation(rect.x, rect.y);
      this.setSize(rect.width, rect.height);
      this.setTitle("DateLook 1.9.9");

      contentPane = (JPanel) this.getContentPane();
      contentPane.add(date_look_panel);
      this.addKeyListener(date_look_panel);

      alarm_handler = new AlarmHandler(event_memory);

      long timer_rate = 5 * 1000;   // check every 5 second
      alarm_checker.scheduleAtFixedRate(alarm_handler, (long) (timer_rate - Math.IEEEremainder((new GregorianCalendar()).getTime().getTime(), timer_rate)), timer_rate);
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
//      event_memory.save();
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
   * @param  args  command line arguments (not used).
   */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    new DateLook().setVisible(true);
  }
}

