package OBS_C_2025;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
								dIZIN.sIFRESI = rs.getString("USER_PWD_SERVER").toString();
								dIZIN.yER = rs.getString("YER").toString();
								dIZIN.iNSTANCE = rs.getString("USER_INSTANCE_OBS").toString();
								dIZIN.dIZIN_CINS = rs.getString("DIZIN_CINS").toString();
								dIZIN.dIZIN = rs.getString("DIZIN").toString();
								dIZIN.hAN_SQL = rs.getString("HANGI_SQL").toString();
								dIZIN.cDID= rs.getInt("CDID"); 
					            if (new String(dIZIN.yER.toString()).equals("L") == true) 
					            { 
					            	dIZIN.cONN_STR =  "localhost;instanceName=" + dIZIN.iNSTANCE + " ; database=" + dOSYA_BASLANGIC  + dIZIN.kOD ;
					            }
					            else
					            { 
					            	dIZIN.cONN_STR = dIZIN.sERVER + ";instanceName=" +dIZIN.iNSTANCE + " ; database=" +  dOSYA_BASLANGIC +dIZIN.kOD ;
					            }
							}
	}
}
