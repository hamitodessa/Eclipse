import java.util.*;
import java.awt.*;
import java.applet.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
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
 *  Synchronizer that control the synchronization with a<br>
 *  remote database.
 */
public class Synchronizer extends Thread {

  private SyncDialog sync_dialog;
  private JProgressBar progress_bar;
  private int protocol = Settings.protNone;
  private String user_name = "";
  private String password = "";
  private String sync_host_path_name = "";


  /**
   *  Constructor for the Synchronizer object
   *
   * @param  sd  synchronisation dialog
   * @param  p   protocol type
   * @param  u   user name
   * @param  pw  password
   * @param  hp  host + path name
   * @param  pb  a progress bar that shows the progress
   */
  public Synchronizer(SyncDialog sd, int p, String u, String pw, String hp, JProgressBar pb) {
    sync_dialog = sd;
    protocol = p;
    user_name = u;
    password = pw;
    sync_host_path_name = hp;
    progress_bar = pb;
  }


  /**
   *  Main processing method for the Synchronizer object
   */
  public void run() {
    InputStreamReader in = null;
    OutputStreamWriter out = null;
    URL url = null;
    File net_file = null;
    long in_length = 0;

    // reset progress bar
    progress_bar.setValue(0);
    progress_bar.setString("connecting!");
    progress_bar.setStringPainted(true);

    if (sync_host_path_name.length() > 0) {
      try {
        try {
          // read and synchronise database
          if (protocol == Settings.protFTP) {
            url = new URL("ftp://" + user_name + ":" + password + "@" + sync_host_path_name);
            URLConnection connection = url.openConnection();
            in = new InputStreamReader(connection.getInputStream());
            in_length = connection.getContentLength();
          }
          else {
            //protocol == SyncSettings.protFile
            net_file = new File(sync_host_path_name);
            in = new InputStreamReader(new FileInputStream(net_file), "US-ASCII");
            in_length = net_file.length();
          }
          progress_bar.setString("synchronising!");
          EventMemory.get_instance(null).import_vCalendar(in, Math.max(in_length, 1), true, progress_bar);
          in.close();
        }
        catch (Exception x) {
          // error during read/synchronise
          progress_bar.setString(x.getMessage());
        }

        // write updated database back
        progress_bar.setValue(0);
        progress_bar.setString("connecting!");
        if (protocol == Settings.protFTP) {
          URLConnection connection = url.openConnection();
          connection.setDoOutput(true);
          out = new OutputStreamWriter(connection.getOutputStream(), "US-ASCII");
        }
        else {
          out = new OutputStreamWriter(new FileOutputStream(net_file), "US-ASCII");
        }
        progress_bar.setString("writing!");
        int[] i = new int[EventMemory.get_instance(null).get_size()];
        for (int k = 0; k < i.length; k++) {
          i[k] = k;
        }
        // array for all events built
        progress_bar.setStringPainted(true);
        EventMemory.get_instance(null).export_vCalendar(out, i, true, progress_bar, true);
        out.close();
        sync_dialog.sync_panel.unlock_input();
        sync_dialog.dispose();
      }
      catch (Exception e) {
        progress_bar.setString(e.getMessage());
        sync_dialog.sync_panel.unlock_input();
      }
    }
    else {
      progress_bar.setString("enter a valid URL!");
      sync_dialog.sync_panel.unlock_input();
    }
  }
}

