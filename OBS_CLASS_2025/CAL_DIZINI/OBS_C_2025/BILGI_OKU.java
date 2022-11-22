package OBS_C_2025;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class BILGI_OKU {
	
	static ResultSet rs = null;
	static Connection con ;
	private GLOBAL gLB = new GLOBAL();
	public  void bILGI_OKU(String uSER,String pROG ,   DIZIN_BILGILERI dIZIN, String  dOSYA_BASLANGIC) throws SQLException, ClassNotFoundException
	{
		 rs = null;
	 		Class.forName("org.sqlite.JDBC");
				con = gLB.myConnection();
				rs = null ;
				con.setAutoCommit(false);
						PreparedStatement stmt = con.prepareStatement("SELECT * FROM USER_DETAILS WHERE USER_PROG_OBS=? AND USER_NAME=? And CALISAN_MI = 'E'");
						stmt.setString(1, pROG.toString());
						stmt.setString(2, uSER.toString());
						rs = stmt.executeQuery();
						 int count=0;
							rs.next();
							count = rs.getRow();
							if (count  != 0) 
							{
								dIZIN.kOD = rs.getString("USER_PROG_KODU").toString();
								dIZIN.sERVER =  rs.getString("USER_IP_OBS").toString();
								dIZIN.kULLANICI = rs.getString("USER_SERVER").toString();
								byte[] decodedBytes = Base64.getDecoder().decode(rs.getString("USER_PWD_SERVER").toString());
								String decodedString = new String(decodedBytes);
								dIZIN.sIFRESI = decodedString;
								dIZIN.yER = rs.getString("YER").toString();
								dIZIN.iNSTANCE = rs.getString("USER_INSTANCE_OBS").toString();
								dIZIN.dIZIN_CINS = rs.getString("DIZIN_CINS").toString();
								dIZIN.dIZIN = rs.getString("DIZIN").toString();
								dIZIN.hAN_SQL = rs.getString("HANGI_SQL").toString();
								dIZIN.cDID= rs.getInt("CDID"); 
								dIZIN.lOG = rs.getBoolean("LOG");
								dIZIN.lOGLAMA_YERI = rs.getString("LOG_YERI").toString();
					            if (new String(dIZIN.yER.toString()).equals("L") == true) 
					            { 
					            	  if (new String(dIZIN.hAN_SQL.toString()).equals("MS SQL") == true) 
					            	  {
					            		  dIZIN.cONN_STR =  "localhost;instanceName=" + dIZIN.iNSTANCE + " ; database=" + dOSYA_BASLANGIC  + dIZIN.kOD ;
					            	  }
					            	  else   if (new String(dIZIN.hAN_SQL.toString()).equals("MY SQL") == true) 
					            	  {
					            		  dIZIN.cONN_STR =  "localhost:" + dIZIN.sERVER + " ; database=" + dOSYA_BASLANGIC  + dIZIN.kOD ;
					            	  }
					            }
					            else
					            { 
					            	 if (new String(dIZIN.hAN_SQL.toString()).equals("MS SQL") == true) 
					            	  {
					            		 dIZIN.cONN_STR = dIZIN.sERVER + ";instanceName=" +dIZIN.iNSTANCE + " ; database=" +  dOSYA_BASLANGIC +dIZIN.kOD ;
					            	  }
					            	 else  if (new String(dIZIN.hAN_SQL.toString()).equals("MS SQL") == true) 
					            	 {
					            		 dIZIN.cONN_STR = dIZIN.sERVER + " ; database=" +  dOSYA_BASLANGIC +dIZIN.kOD ;
					            	 }
					            }
							}
							con.close();
	}
}
