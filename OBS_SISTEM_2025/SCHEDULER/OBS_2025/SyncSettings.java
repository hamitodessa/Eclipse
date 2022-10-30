package OBS_2025;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

import javax.swing.filechooser.*;
import java.util.Base64;


/*
 *  Title:        DateLook
 *  Copyright:    Copyright (c) 2005
 *  Author:       Rene Ewald
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details. You should have
 *  received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
/**
 *  Description of the Class
 */
public class SyncSettings {

  private int protocol = protFTP;
  /**
   *  Description of the Field
   */
  public final static int protNone = 0;
  /**
   *  Description of the Field
   */
  public final static int protFTP = 1;
  /**
   *  Description of the Field
   */
  public final static int protFile = 2;

  private String user_name = "";
  private String ftp_host_path_name = "";  // host/path for FTP-protocol
  private String path_name = "";  // path for file-protocol
  private String password = "";
  private String sync_settings_file_name = "";


  /**
   *  Constructor for the SyncSettings object
   */
  public SyncSettings() {
    try {
      sync_settings_file_name = FileSystemView.getFileSystemView().getDefaultDirectory().getAbsolutePath()
           + File.separatorChar + ".datelook" + File.separatorChar + "syncrc";
      File sync_settings_file = new File(sync_settings_file_name);

      if (sync_settings_file.canRead() & !sync_settings_file.isDirectory()) {
        // read file
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(sync_settings_file_name));
        protocol = in.readInt();
        user_name = (decode_BASE64((String) in.readObject()));
        password = (decode_BASE64((String) in.readObject()));
        ftp_host_path_name = ((String) in.readObject());
        path_name = ((String) in.readObject());
        in.close();
      }
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
  public String get_path_name() {
    return path_name;
  }


  /**
   *  Description of the Method
   *
   * @param  prot       Description of the Parameter
   * @param  user       Description of the Parameter
   * @param  pw         Description of the Parameter
   * @param  ftp_hpath  Description of the Parameter
   * @param  fs_path    Description of the Parameter
   */
  public void save(int prot, String user, String pw, String ftp_hpath, String fs_path) {
    try {
      new File(sync_settings_file_name).delete();
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(sync_settings_file_name));
      out.writeInt(prot);
      out.writeObject(encode_BASE64(user));
      out.writeObject(encode_BASE64(pw));
      out.writeObject(ftp_hpath);
      out.writeObject(fs_path);
      out.close();
    }
    catch (Exception b) {
      b.printStackTrace();
    }
  }


  /**
   *  Description of the Method
   *
   * @param  p  Description of the Parameter
   * @return    Description of the Return Value
   */
  private String decode_BASE64(String p) {
    try {
    	byte[] decoded = Base64.getDecoder().decode(p);
    	String decodedStr = new String(decoded, StandardCharsets.UTF_8);
    	return new String(decodedStr);
      //return new String(new sun.misc.BASE64Decoder().decodeBuffer(p));
    }
    catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }


  /**
   *  Description of the Method
   *
   * @param  p  Description of the Parameter
   * @return    Description of the Return Value
 * @throws UnsupportedEncodingException 
   */
  private String encode_BASE64(String p) {
	  
	  byte[] bytes = p.getBytes(StandardCharsets.UTF_8);
	  String encoded = Base64.getEncoder().encodeToString(bytes);
	  return encoded;
	  
    //return new sun.misc.BASE64Encoder().encode(p.getBytes());
  }
}

