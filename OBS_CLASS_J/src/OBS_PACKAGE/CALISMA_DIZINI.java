package OBS_PACKAGE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CALISMA_DIZINI {

	GLOBAL glb = new GLOBAL();
	ResultSet rs = null;
	Connection con ;
	public static DIZIN_BILGILERI ccdb = new DIZIN_BILGILERI();  // Cari Hesap
	public static DIZIN_BILGILERI kcdb = new DIZIN_BILGILERI();  // Kur
    public static DIZIN_BILGILERI fcdb = new DIZIN_BILGILERI();  // Fatura
    public static DIZIN_BILGILERI acdb = new DIZIN_BILGILERI();  // Adres
    public static DIZIN_BILGILERI gcdb = new DIZIN_BILGILERI();  // Gunluk
    public static DIZIN_BILGILERI kacdb = new DIZIN_BILGILERI(); // Kambiyo
    public static DIZIN_BILGILERI smscdb = new DIZIN_BILGILERI();// Kambiyo
    public static DIZIN_BILGILERI ficdb = new DIZIN_BILGILERI(); // Fihrist
    
    public CALISMA_DIZINI(String user,String prog) throws SQLException, ClassNotFoundException
    {
    	if (prog == "Kur")
        { 
        calisilan_bak(user, "Kur");
        int count=0;
		rs.next();
		count = rs.getRow();
		if (count  != 0) 
		{
			kcdb.kod = rs.getString("USER_PROG_KODU").toString();
			kcdb.server =  rs.getString("USER_IP_OBS").toString();
            kcdb.kullanici = rs.getString("USER_SERVER").toString();
            kcdb.sifresi = rs.getString("USER_PWD_SERVER").toString();
            kcdb.yer = rs.getString("YER").toString();
            kcdb.instance = rs.getString("USER_INSTANCE_OBS").toString();
            kcdb.dizin_cins = rs.getString("DIZIN_CINS").toString();
            kcdb.dizin = rs.getString("DIZIN").toString();
            kcdb.han_sql = rs.getString("HANGI_SQL").toString();
            kcdb.cDid = rs.getInt("CDID"); 
            if (new String(kcdb.yer.toString()).equals("L") == true) 
            { 
               kcdb.conn_str =  "localhost;instanceName=" + kcdb.instance + " ; database=OK_Kur" + kcdb.kod ;
            }
            else
            { 
               kcdb.conn_str = kcdb.server + ";instanceName=" + kcdb.instance + " ; database=OK_Kur" + kcdb.kod ;
            }
		}
		else
		{
			 kcdb.kod = "";
             kcdb.server = "";
             kcdb.kullanici = "";
             kcdb.sifresi = "";
             kcdb.yer = "";
             kcdb.instance = "";
             kcdb.dizin_cins = "";
             kcdb.dizin = "";
             kcdb.conn_str = "";
             kcdb.han_sql = "";
             kcdb.cDid = 0;
		}}
		else if  (prog == "Cari Hesap")
		{
			calisilan_bak(user, "Cari Hesap");
	        int count=0;
			rs.next();
			count = rs.getRow();
			
			if (count  != 0) 
			{
				ccdb.kod = rs.getString("USER_PROG_KODU");
				ccdb.server =  rs.getString("USER_IP_OBS");
	            ccdb.kullanici = rs.getString("USER_SERVER");
	            ccdb.sifresi = rs.getString("USER_PWD_SERVER");
	            ccdb.yer = rs.getString("YER");
	            ccdb.instance = rs.getString("USER_INSTANCE_OBS");
	            ccdb.dizin_cins = rs.getString("DIZIN_CINS");
	            ccdb.dizin = rs.getString("DIZIN");
	            ccdb.han_sql = rs.getString("HANGI_SQL").toString();
	            ccdb.cDid = rs.getInt("CDID"); 
	        if (new String(ccdb.yer.toString()).equals("L") == true) 
	            { 
	             ccdb.conn_str = "localhost;instanceName=" + ccdb.instance + " ; database=OK_Car" + ccdb.kod ;
	            }
	            else
	            { 
	             ccdb.conn_str = ccdb.server + ";instanceName=" + ccdb.instance + " ; database=OK_Car" + ccdb.kod ;
	            }
			}
			else
			{
				 ccdb.kod = "";
	             ccdb.server = "";
	             ccdb.kullanici = "";
	             ccdb.sifresi = "";
	             ccdb.yer = "";
	             ccdb.instance = "";
	             ccdb.dizin_cins = "";
	             ccdb.dizin = "";
	             ccdb.conn_str = "";
	             ccdb.han_sql = "";
	             ccdb.cDid = 0;
			}}
			else if  (prog == "Fatura")
			{
			 calisilan_bak(user, "Fatura");
		        int count=0;
				rs.next();
				count = rs.getRow();
				if (count  != 0) 
				{
					fcdb.kod = rs.getString("USER_PROG_KODU").toString();
					fcdb.server =  rs.getString("USER_IP_OBS").toString();
		            fcdb.kullanici = rs.getString("USER_SERVER").toString();
		            fcdb.sifresi = rs.getString("USER_PWD_SERVER").toString();
		            fcdb.yer = rs.getString("YER").toString();
		            fcdb.instance = rs.getString("USER_INSTANCE_OBS").toString();
		            fcdb.dizin_cins = rs.getString("DIZIN_CINS").toString();
		            fcdb.dizin = rs.getString("DIZIN").toString();
		            fcdb.han_sql = rs.getString("HANGI_SQL").toString();
		            fcdb.cDid = rs.getInt("CDID"); 
		            if (new String(fcdb.yer.toString()).equals("L") == true) 
		            { 
		                fcdb.conn_str =  "localhost;instanceName=" + fcdb.instance + " ; database=OK_Fat" + fcdb.kod ;
		            }
		             else
		             { 
		                fcdb.conn_str = fcdb.server + ";instanceName=" + fcdb.instance + " ; database=OK_Fat" + fcdb.kod ;
		             }
				}
				else
				{
					 fcdb.kod = "";
		             fcdb.server = "";
		             fcdb.kullanici = "";
		             fcdb.sifresi = "";
		             fcdb.yer = "";
		             fcdb.instance = "";
		             fcdb.dizin_cins = "";
		             fcdb.dizin = "";
		             fcdb.conn_str = "";
		             fcdb.han_sql = "";
		             fcdb.cDid = 0;
		         }
				}
			else if  (prog == "Adres")
			{
				calisilan_bak(user, "Adres");
		        int count=0;
				rs.next();
				count = rs.getRow();
				if (count  != 0) 
				{
					acdb.kod = rs.getString("USER_PROG_KODU").toString();
					acdb.server =  rs.getString("USER_IP_OBS").toString();
		            acdb.kullanici = rs.getString("USER_SERVER").toString();
		            acdb.sifresi = rs.getString("USER_PWD_SERVER").toString();
		            acdb.yer = rs.getString("YER").toString();
		            acdb.instance = rs.getString("USER_INSTANCE_OBS").toString();
		            acdb.dizin_cins = rs.getString("DIZIN_CINS").toString();
		            acdb.dizin = rs.getString("DIZIN").toString();
		            acdb.han_sql = rs.getString("HANGI_SQL").toString();
		            acdb.cDid = rs.getInt("CDID"); 
		            if (new String(acdb.yer.toString()).equals("L") == true) 
		            { 
		                acdb.conn_str =  "localhost;instanceName=" + acdb.instance + " ; database=OK_Adr" + acdb.kod ;
		             }
		             else
		             { 
		                acdb.conn_str = acdb.server + ";instanceName=" + acdb.instance + " ; database=OK_Adr" + acdb.kod ;
		             }
				}
				else
				{
					 acdb.kod = "";
		             acdb.server = "";
		             acdb.kullanici = "";
		             acdb.sifresi = "";
		             acdb.yer = "";
		             acdb.instance = "";
		             acdb.dizin_cins = "";
		             acdb.dizin = "";
		             acdb.conn_str = "";
		             acdb.han_sql = "";
		             acdb.cDid = 0;
				}}
		else if  (prog == "Gunluk")
		     		{
		     	calisilan_bak(user, "Gunluk");
		     	        int count=0;
		     			rs.next();
		     			count = rs.getRow();
		     			if (count  != 0) 
		     			{
		     				gcdb.kod = rs.getString("USER_PROG_KODU").toString();
		     				gcdb.server =  rs.getString("USER_IP_OBS").toString();
		     	            gcdb.kullanici = rs.getString("USER_SERVER").toString();
		     	            gcdb.sifresi = rs.getString("USER_PWD_SERVER").toString();
		     	            gcdb.yer = rs.getString("YER").toString();
		     	            gcdb.instance = rs.getString("USER_INSTANCE_OBS").toString();
		     	            gcdb.dizin_cins = rs.getString("DIZIN_CINS").toString();
		     	            gcdb.dizin = rs.getString("DIZIN").toString();
		     	            gcdb.han_sql = rs.getString("HANGI_SQL").toString();
		     	            gcdb.cDid = rs.getInt("CDID"); 
		     	           if (new String(gcdb.yer.toString()).equals("L") == true) 
		     	          { 
				                gcdb.conn_str =  "localhost;instanceName=" + gcdb.instance + " ; database=OK_Gun" + gcdb.kod ;
				             }
				             else
				             { 
				                gcdb.conn_str = gcdb.server + ";instanceName=" + gcdb.instance + " ; database=OK_Gun" + gcdb.kod ;
				             }
		     			}
		     			else
		     			{
		     				 gcdb.kod = "";
		     	             gcdb.server = "";
		     	             gcdb.kullanici = "";
		     	             gcdb.sifresi = "";
		     	             gcdb.yer = "";
		     	             gcdb.instance = "";
		     	             gcdb.dizin_cins = "";
		     	             gcdb.dizin = "";
		     	             gcdb.conn_str = "";
		     	             gcdb.han_sql = "";
		     	             gcdb.cDid = 0;	  
		     			}}
		else if  (prog == "Kambiyo")
		{
			 calisilan_bak(user, "Kambiyo");
	        int count=0;
			rs.next();
			count = rs.getRow();
			if (count  != 0) 
			{
				kacdb.kod = rs.getString("USER_PROG_KODU").toString();
				kacdb.server =  rs.getString("USER_IP_OBS").toString();
	            kacdb.kullanici = rs.getString("USER_SERVER").toString();
	            kacdb.sifresi = rs.getString("USER_PWD_SERVER").toString();
	            kacdb.yer = rs.getString("YER").toString();
	            kacdb.instance = rs.getString("USER_INSTANCE_OBS").toString();
	            kacdb.dizin_cins = rs.getString("DIZIN_CINS").toString();
	            kacdb.dizin = rs.getString("DIZIN").toString();
	            kacdb.han_sql = rs.getString("HANGI_SQL").toString();
	            kacdb.cDid = rs.getInt("CDID"); 
	            if (new String(kacdb.yer.toString()).equals("L") == true) 
	            { 
	                kacdb.conn_str =  "localhost;instanceName=" + kacdb.instance + " ; database=OK_Kam" + kacdb.kod ;
	             }
	             else
	             { 
	                kacdb.conn_str = kacdb.server + ";instanceName=" + kacdb.instance + " ; database=OK_Kam" + kacdb.kod ;
	             }
			}
			else
			{
				 kacdb.kod = "";
	             kacdb.server = "";
	             kacdb.kullanici = "";
	             kacdb.sifresi = "";
	             kacdb.yer = "";
	             kacdb.instance = "";
	             kacdb.dizin_cins = "";
	             kacdb.dizin = "";
	             kacdb.conn_str = "";
	             kacdb.han_sql = "" ;
	             kacdb.cDid = 0;
			}}
		else if  (prog == "Sms")
		{
			 calisilan_bak(user, "Sms");
	        int count=0;
			rs.next();
			count = rs.getRow();
			if (count  != 0) 
			{
				smscdb.kod = rs.getString("USER_PROG_KODU").toString();
				smscdb.server =  rs.getString("USER_IP_OBS").toString();
	            smscdb.kullanici = rs.getString("USER_SERVER").toString();
	            smscdb.sifresi = rs.getString("USER_PWD_SERVER").toString();
	            smscdb.yer = rs.getString("YER").toString();
	            smscdb.instance = rs.getString("USER_INSTANCE_OBS").toString();
	            smscdb.dizin_cins = rs.getString("DIZIN_CINS").toString();
	            smscdb.dizin = rs.getString("DIZIN").toString();
	            smscdb.han_sql = rs.getString("HANGI_SQL").toString();
	            smscdb.cDid = rs.getInt("CDID"); 
	            if (new String(smscdb.yer.toString()).equals("L") == true) 
	            { 
	                smscdb.conn_str =  "localhost;instanceName=" + smscdb.instance + " ; database=OK_Sms" + smscdb.kod ;
	             }
	             else
	             { 
	                smscdb.conn_str = smscdb.server + ";instanceName=" + smscdb.instance + " ; database=OK_Sms" + smscdb.kod ;
	             }
			}
			else
			{
				 smscdb.kod = "";
	             smscdb.server = "";
	             smscdb.kullanici = "";
	             smscdb.sifresi = "";
	             smscdb.yer = "";
	             smscdb.instance = "";
	             smscdb.dizin_cins = "";
	             smscdb.dizin = "";
	             smscdb.conn_str = "";
	             smscdb.han_sql = "";
	             smscdb.cDid = 0;
			}}
		else if  (prog == "Fihrist")
		{
			calisilan_bak(user, "Fihrist");
	        int count=0;
			rs.next();
			count = rs.getRow();
			if (count  != 0) 
			{
				ficdb.kod = rs.getString("USER_PROG_KODU").toString();
				ficdb.server =  rs.getString("USER_IP_OBS").toString();
	            ficdb.kullanici = rs.getString("USER_SERVER").toString();
	            ficdb.sifresi = rs.getString("USER_PWD_SERVER").toString();
	            ficdb.yer = rs.getString("YER").toString();
	            ficdb.instance = rs.getString("USER_INSTANCE_OBS").toString();
	            ficdb.dizin_cins = rs.getString("DIZIN_CINS").toString();
	            ficdb.dizin = rs.getString("DIZIN").toString();
	            ficdb.han_sql = rs.getString("HANGI_SQL").toString();
	            ficdb.cDid = rs.getInt("CDID"); 
	            if (new String(ficdb.yer.toString()).equals("L") == true) 
	            { 
	                ficdb.conn_str =  "localhost;instanceName=" + ficdb.instance + " ; database=OK_Fih" + ficdb.kod ;
	             }
	             else
	             { 
	                ficdb.conn_str = ficdb.server + ";instanceName=" + ficdb.instance + " ; database=OK_Fih" + ficdb.kod ;
	             }
			}
			else
			{
				 ficdb.kod = "";
	             ficdb.server = "";
	             ficdb.kullanici = "";
	             ficdb.sifresi = "";
	             ficdb.yer = "";
	             ficdb.instance = "";
	             ficdb.dizin_cins = "";
	             ficdb.dizin = "";
	             ficdb.conn_str = "";
	             ficdb.han_sql = "";
	             ficdb.cDid = 0;
			}}
    	con.close();
    }
    @SuppressWarnings("static-access")
	private  void calisilan_bak(String username,String prg) throws SQLException, ClassNotFoundException {
   		rs = null;
  		Class.forName("org.sqlite.JDBC");
			con = glb.myConnection();
			rs = null ;
			con.setAutoCommit(false);
					PreparedStatement stmt = con.prepareStatement("SELECT * FROM USER_DETAILS WHERE USER_PROG_OBS=? AND USER_NAME=? And CALISAN_MI = 'E'");
					stmt.setString(1, prg.toString());
					stmt.setString(2, username.toString());
					rs = stmt.executeQuery();
    }
}
