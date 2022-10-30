import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;


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
 *  Stores GUI-settings such as position and size of main window,<br>
 *  extended view or simple view and the number of visible hours<br>
 *  in a file and read this file back if needed.<br>
 *  Furthermore it stores the settings for the synchronisation,<br>
 *  the predefined colours and the row labels.
 */
public class Settings {
  private static Settings instance = null;
  
  // GUI settings
  private int frame_x = 50; // x position on screen
  private int frame_y = 50; // y position on screen
  private int frame_width = 700;  // frame width
  private int slot_height = 20;  // slot height
  private int decor_height = 20; // frame decoration height
  private long number_of_rendered_hours = 7 * 24;
  private String gui_settings_file_name = "";
  private boolean ext_view = false;
  private boolean show_row_labels = false;

  // sync settings
  private int protocol = protFTP;
  /**
   *  No protocoll
   */
  public final static int protNone = 0;
  /**
   *  File transfer protocol
   */
  public final static int protFTP = 1;
  /**
   *  direct access via file system 1
   */
  public final static int protFile1 = 2;
  /**
   *  direct access via file system 2
   */
  public final static int protFile2 = 3;

  private String user_name = "";
  private String ftp_host_path_name = "";  // host/path for FTP-protocol
  private String path_name1 = "";  // path for file-protocol
  private String path_name2 = "";  // path for file-protocol
  private String password = "";
  private String sync_settings_file_name = "";

  // colour settings
  // arrays to store colour settings (predefined colours and labels) after read from colourrc-file
  private String colour_settings_file_name = "";
  private String[] label = {"current", "family", "sports", "hobby", "health", "work", "business", "travel"};
  private Color[] colours = {Color.blue, Color.blue, Color.green, Color.yellow, Color.red, Color.pink, Color.orange, Color.cyan};

  
  // row settings (name and number)
  private String row_settings_file_name = "";
  private String[] row_label = {"Heinz", "Egon", "Kurt", "Gustav", "Paul", "Otto", "Emil", "Horst", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
  private int number_of_slots = 10;
  
  /**
   *  If not already instanciated the Settings object will be created.
   *
   * @param  settings_dir_name  path of settings directory to be used or null for default dir;<br>
   *                            parameter will only be used for first call, if there were no instance<br>
   *                            before.
   */
  public static Settings get_instance(String settings_dir_name) {
    if (instance == null) {
      // create instance
      instance = new Settings();
      try {
        if (settings_dir_name == null) {
          // use user's default directory
          settings_dir_name = FileSystemView.getFileSystemView().getDefaultDirectory().getAbsolutePath();
        }
        else {
          File set_dir = new File(settings_dir_name);
          if (!(set_dir.exists()) || !(set_dir.isDirectory())) {
            // use user's default directory
            settings_dir_name = FileSystemView.getFileSystemView().getDefaultDirectory().getAbsolutePath();
          }
        }
        
        // create .datelook-folder if not exists
        settings_dir_name = settings_dir_name + File.separatorChar + ".datelook";
        File dir = new File(settings_dir_name);
        if (dir.exists() && !(dir.isDirectory())) {
          dir.delete();  // delete .datelook
        }
        if (!(dir.exists())) {
          dir.mkdir();
          HelpFrame.get_instance().help(null);   // started automatically if first use
        }
        System.out.println("Used setting directory: '" + settings_dir_name + "'");
        
        // handle GUI-settings
        instance.gui_settings_file_name = settings_dir_name + File.separatorChar + "guirc";
        File settings_file = new File(instance.gui_settings_file_name);
  
        if (settings_file.canRead() && !settings_file.isDirectory()) {
          // read GUI settings file
          ObjectInputStream in = new ObjectInputStream(new FileInputStream(instance.gui_settings_file_name));
          instance.frame_x = in.readInt();
          instance.frame_y = in.readInt();
          instance.frame_width = in.readInt();
          instance.slot_height = in.readInt();
          instance.decor_height = in.readInt();  
          instance.number_of_rendered_hours = in.readLong();
          instance.ext_view = in.readBoolean();
          instance.show_row_labels = in.readBoolean();
          in.close();
        }
        
        // handle sync settings
        instance.sync_settings_file_name = settings_dir_name + File.separatorChar + "syncrc";
        File sync_settings_file = new File(instance.sync_settings_file_name);
  
        if (sync_settings_file.canRead() && !sync_settings_file.isDirectory()) {
          // read sync settings file
          ObjectInputStream in = new ObjectInputStream(new FileInputStream(instance.sync_settings_file_name));
          instance.protocol = in.readInt();
          instance.user_name = (instance.decode_BASE64((String) in.readObject()));
          instance.password = (instance.decode_BASE64((String) in.readObject()));
          instance.ftp_host_path_name = ((String) in.readObject());
          instance.path_name1 = ((String) in.readObject());
          instance.path_name2 = ((String) in.readObject());
          in.close();
        }
        
        // handle colour settings
        instance.colour_settings_file_name = settings_dir_name + File.separatorChar + "colourrc";
        File colour_settings_file = new File(instance.colour_settings_file_name);

        if (colour_settings_file.canRead() && !colour_settings_file.isDirectory()) {
          //read predefined colours file
          ObjectInputStream in = new ObjectInputStream(new FileInputStream(instance.colour_settings_file_name));
          instance.label = ((String[]) in.readObject());
          instance.colours = ((Color[]) in.readObject());
          in.close();
        }

        // handle row settings
        instance.row_settings_file_name = settings_dir_name + File.separatorChar + "rowrc";
        File row_settings_file = new File(instance.row_settings_file_name);

        if (row_settings_file.canRead() && !row_settings_file.isDirectory()) {
          //read row number and labels
          ObjectInputStream in = new ObjectInputStream(new FileInputStream(instance.row_settings_file_name));
          instance.row_label = ((String[]) in.readObject());
          instance.number_of_slots = in.readInt();
          in.close();
        }
      }
      catch (Exception b) {
        b.printStackTrace();
      }
    }
    return instance;
  }

  
  /**
   *  Get stored x position of main frame 
   *
   * @return    x position of frame
   */
  public int get_frame_x() {
    return frame_x;
  }


  /**
   *  Get stored y position of main frame 
   *
   * @return    y position of frame
   */
  public int get_frame_y() {
    return frame_y;
  }


  /**
   *  Get stored main frame width 
   *
   * @return    frame width
   */
  public int get_frame_width() {
    return frame_width;
  }
  

  /**
   *  Get stored slot height 
   *
   * @return    slot height 
   */
  public int get_slot_height() {
    return slot_height ;
  }

  
  /**
   *  Get stored decor height of main frame
   *
   * @return    decor height 
   */
  public int get_decor_height() {
    return decor_height ;
  }
  

  /**
   *  Get stored number of rendererd hours in main frame
   *
   * @return    number of rendererd hours
   */
  public long get_number_of_rendered_hours() {
    return number_of_rendered_hours;
  }


  /**
   *  Get stored view mode
   *
   * @return    false - simple view<br>
   *            true - extended view
   */
  public boolean get_ext_view() {
    return ext_view;
  }


  /**
   *  Get whether row labels are to be show
   *
   * @return    false - row labels not shown<br>
   *            true - row labels are shown
   */
  public boolean get_show_row_labels() {
    return show_row_labels;
  }


  /**
   *  Save GUI settings
   *
   * @param  x          x position of main frame on screen
   * @param  y          y position of main frame on screen
   * @param  width      main frame width
   * @param  sh         slot height
   * @param  dh         main frame decoration height
   * @param  nor_hours  number of rendered hours
   * @param  eview      false - simple view, true - extended view<br>
   * @param  rl         false - don't show row labels, true - show it<br>
   *      
   */
  public void save_gui_settings(int x, int y, int width, int sh, int dh, long nor_hours, boolean eview, boolean rl) {
    frame_x = x;
    frame_y = y;
    frame_width = width;
    slot_height = sh;
    decor_height = dh;
    number_of_rendered_hours = nor_hours;
    ext_view = eview;
    show_row_labels = rl;
    
    try {
      new File(gui_settings_file_name).delete();
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(gui_settings_file_name));
      out.writeInt(x);
      out.writeInt(y);
      out.writeInt(width);
      out.writeInt(sh);
      out.writeInt(dh);
      out.writeLong(nor_hours);
      out.writeBoolean(eview);
      out.writeBoolean(rl);
      out.close();
    }
    catch (Exception b) {
      b.printStackTrace();
    }
  }
  
  /**
   *  Gets the protocol attribute of the SyncSettings object
   *
   * @return    The protocol value
   */
  public int get_protocol() {
    return protocol;
  }


  /**
   *  Gets the user_name attribute of the SyncSettings object
   *
   * @return    The user_name value
   */
  public String get_user_name() {
    return user_name;
  }


  /**
   *  Gets the password attribute of the SyncSettings object
   *
   * @return    The password value
   */
  public String get_password() {
    return password;
  }


  /**
   *  Gets the ftp_host_path_name attribute of the SyncSettings object
   *
   * @return    The ftp_host_path_name value
   */
  public String get_ftp_host_path_name() {
    return ftp_host_path_name;
  }


  /**
   *  Gets the path_name attribute of the SyncSettings object
   *
   * @return    The path_name value
   */
  public String get_path_name1() {
    return path_name1;
  }


  /**
   *  Gets the path_name attribute of the SyncSettings object
   *
   * @return    The path_name value
   */
  public String get_path_name2() {
    return path_name2;
  }


  /**
   *  Save the sync settings
   *
   * @param  prot       protocol type
   * @param  user       user name
   * @param  pw         password
   * @param  ftp_hpath  ftp: host and path
   * @param  fs_path    noftp: host and path
   */
  public void save_sync_settings(int prot, String user, String pw, String ftp_hpath, String fs_path1, String fs_path2) {
    protocol = prot;
    user_name = user;
    password = pw;
    ftp_host_path_name = ftp_hpath;
    path_name1 = fs_path1;
    path_name2 = fs_path2;
    
    try {
      new File(sync_settings_file_name).delete();
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(sync_settings_file_name));
      out.writeInt(prot);
      out.writeObject(encode_BASE64(user));
      out.writeObject(encode_BASE64(pw));
      out.writeObject(ftp_hpath);
      out.writeObject(fs_path1);
      out.writeObject(fs_path2);
      out.close();
    }
    catch (Exception b) {
      b.printStackTrace();
    }
  }


  /**
   *  Decode string (BASE64)
   *
   * @param  p  string to be decoded
   * @return    decoded string
   */
  private String decode_BASE64(String p) {
    try {
      return new String(java.util.Base64.getDecoder().decode(p));
    }
    catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }


  /**
   *  Encode string (BASE64)
   *
   * @param  p  string to be encoded
   * @return    encoded string
   */
  private String encode_BASE64(String p) {
    return java.util.Base64.getEncoder().encodeToString(p.getBytes());
  }
  
  
  /**
   *  Saves the predefined colours and attached colour labels
   */
  public void save_colour_settings() {
    try {
      new File(colour_settings_file_name).delete();
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(colour_settings_file_name));
      out.writeObject(label);
      out.writeObject(colours);
      out.close();
    }
    catch (Exception b) {
      b.printStackTrace();
    }
  }
  
  
  /**
   *  Gets the predefined colour with index i
   *
   * @param   i index
   * @return  c colour
   */
  public Color get_colour(int i) {
    return colours[i];
  }


  /**
   *  Sets the predefined colour with index i
   *
   * @param   i index
   * @param   c colour
   */
  public void set_colour(int i, Color c) {
    colours[i] = c;
  }

  
  /**
   *  Gets the label for predefined colour with index i
   *
   * @param   i index
   * @return  c the label
   */
  public String get_label(int i) {
    return label[i];
  }


  /**
   *  Sets the label for predefined colour with index i
   *
   * @param   i index
   * @param   l the label
   */
  public void set_label(int i, String l) {
    label[i] = l;
  }


  /**
   *  Save row labels and number of slots.<br>
   *  (number of displayed rows = number of slots - 5)
   *
   * @param  ns   number of slots for calendar und dates in simple view
   */
  public void save_row_settings(int ns) {
    number_of_slots = ns;
    
    try {
      new File(row_settings_file_name).delete();
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(row_settings_file_name));
      out.writeObject(row_label);
      out.writeInt(ns);
      out.close();
    }
    catch (Exception b) {
      b.printStackTrace();
    }
  }

  
  /**
   *  Get number of slots
   *
   * @return    number of slots for calendar und dates in simple view
   *
   */
  public int get_number_of_slots() {
    return number_of_slots;
  }

  
  /**
   *  Get row label
   *
   * @param   i   index
   * @return  label of the row
   *
   */
  public String get_row_label(int i) {
    return row_label[i];
  }

  
  /**
   *  Sets the label of the row with index i
   *
   * @param   i index
   * @param   l the labe
   */
  public void set_row_label(int i, String l) {
    row_label[i] = l;
  }
}

