package OBS_2025;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import OBS_C_2025.GLOBAL;

public class Tema_Cari {

	static Connection con ;
	
	   public ResultSet  tema_oku() throws ClassNotFoundException, SQLException
	     {
	    	 Class.forName("org.sqlite.JDBC");
	   	 	 con.close();
	    	 con = null;
	    	 ResultSet	rss = null;
	    	 PreparedStatement stmt = null;
	    	 con = GLOBAL.myConnection();
	    	 String sql = "SELECT DISTINCT TEMA FROM ANA_HESAP ";
	    	 stmt = con.prepareStatement(sql);
	    	 rss = stmt.executeQuery();
	    	 return rss;
	     }
	     public static String tema_anahesap(String tema) throws ClassNotFoundException, SQLException
	     {
	    	 Class.forName("org.sqlite.JDBC");
	   	 	 con.close();
	    	 con = null;
	    	 ResultSet	rss = null;
	    	 PreparedStatement stmt = null;
	    	 con = GLOBAL.myConnection();
	    	 String sql = "SELECT ANA_HESAP FROM ANA_HESAP WHERE TEMA = '" + tema + "'";
	    	 stmt = con.prepareStatement(sql);
	    	 rss = stmt.executeQuery();
	    	 String hesap = "" ;
	    	 rss.next();
			 int 	count = rss.getRow();
			 if (count  != 0) 
			 {
				hesap = rss.getString("ANA_HESAP");	
			 }
			 rss.close();
			 con.close();
			 return hesap ;
	     }
	     public static ResultSet ttema_oku(String tema) throws ClassNotFoundException, SQLException
	     {
	    	 Class.forName("org.sqlite.JDBC");
	   	 	 con.close();
	    	 con = null;
	    	 ResultSet	rss = null;
	    	 PreparedStatement stmt = null;
	    	 con = GLOBAL.myConnection();
	    	 String sql = "SELECT  ARANACAK,HESAP_KODU  FROM HESAPLAR WHERE TEMA ='" + tema + "' ORDER BY ARANACAK";
	    	 stmt = con.prepareStatement(sql);
	    	 rss = stmt.executeQuery();
	    	 return rss;
	     }
	     public static ResultSet temalar_oku(String tema) throws ClassNotFoundException, SQLException
	     {
	    	 Class.forName("org.sqlite.JDBC");
	   	 	 con.close();
	    	 con = null;
	    	 ResultSet	rss = null;
	    	 PreparedStatement stmt = null;
	    	 con = GLOBAL.myConnection();
	    	 String sql ="SELECT ARANACAK,YAZILACAK  FROM TEMA WHERE TEMA = '" + tema + "'";
	    	 stmt = con.prepareStatement(sql);
	    	 rss = stmt.executeQuery();
	    	 return rss;
	     }
	     public static void tema_sil(String tema) throws ClassNotFoundException, SQLException
	     {
	    	 Class.forName("org.sqlite.JDBC");
	    	 con.close();
			 con = null;
			 PreparedStatement stmt = null;
			 con = GLOBAL.myConnection();
			 String sql = "" ;
	    	  sql = "DELETE    FROM TEMA WHERE TEMA = '" + tema + "'";
	         stmt = con.prepareStatement(sql);
	         stmt.executeUpdate();
	         stmt = null;
	             
	         sql = "DELETE    FROM ANA_HESAP WHERE TEMA = '" + tema + "'";
	         stmt = con.prepareStatement(sql);
	         stmt.executeUpdate();
	         stmt = null;
	            
	         sql = "DELETE    FROM HESAPLAR WHERE TEMA = '" + tema + "'";
	         stmt = con.prepareStatement(sql);
	         stmt.executeUpdate();
	         stmt.close();
	    	 con.close();
	     }
	     public static void tema_ana_hes_kayit(String tema,String hesap) throws ClassNotFoundException, SQLException
	     {
	    	 Class.forName("org.sqlite.JDBC");
	    	 con.close();
			 con = null;
			 PreparedStatement stmt = null;
			 con = GLOBAL.myConnection();
	   		 String sql =  "INSERT INTO ANA_HESAP (TEMA,ANA_HESAP) " +
	   						"VALUES (?,?)";
	    	 stmt = con.prepareStatement(sql);
	   		 stmt.setString(1, tema);
	   		 stmt.setString(2, hesap);
	   		 stmt.executeUpdate();
	   		stmt.close();
	   		con.close();
	     }
	     public static void tema_tem_hes_kayit(String tema,String aranan,String yazilan) throws SQLException, ClassNotFoundException
	     {
	    	 Class.forName("org.sqlite.JDBC");
	    	 con.close();
			 con = null;
			 PreparedStatement stmt = null;
			 con = GLOBAL.myConnection();
	   		 String sql = "INSERT INTO TEMA (TEMA,ARANACAK,YAZILACAK) " +
	   						"VALUES (?,?,?)";
	    	 stmt = con.prepareStatement(sql);
	   		 stmt.setString(1,  tema);
	   		 stmt.setString(2, aranan);
	   		 stmt.setString(3, yazilan);
	   		 stmt.executeUpdate();
	   		 stmt.close();
	   		 con.close();
	     }
	     public static void tema_hes_kayit(String tema,String aranan,String yazilan) throws ClassNotFoundException, SQLException
	     {
	    	 Class.forName("org.sqlite.JDBC");
	    	 con.close();
			 con = null;
			 PreparedStatement stmt = null;
			 con = GLOBAL.myConnection();
	   		 String sql = "INSERT INTO HESAPLAR (TEMA,ARANACAK,HESAP_KODU) " +
	   						"VALUES (?,?,?)";
	    	 stmt = con.prepareStatement(sql);
	   		 stmt.setString(1,  tema);
	   		 stmt.setString(2, aranan);
	   		 stmt.setString(3, yazilan);
	   		 stmt.executeUpdate();
	   		 stmt.close();
	   		 con.close();
	     }

}
