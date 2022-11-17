package OBS_2025;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import OBS_C_2025.GLOBAL;

public class Tema_Cari {

	static Connection con ;
	
	public  void obs_tema_dosya_olustur() throws Exception {
	   	 try {  
	   		 Class.forName("org.sqlite.JDBC");
	       	 con = GLOBAL.myTemaConnection();
	       	 con.close();
	            	  String sorgu= null;
	            	  sorgu = "CREATE TABLE ANA_HESAP (TEMA CHAR (25) NOT NULL,	ANA_HESAP CHAR(12) NULL) " ;
	    	           tablo_yap(sorgu);
	    	           sorgu = "CREATE TABLE TEMA ([TEMA] CHAR(25) NOT NULL,	ARANACAK CHAR(30) NULL, " +
	    	           			" YAZILACAK CHAR(30) NULL) " ;
	    	           tablo_yap(sorgu);
	    	           sorgu = "CREATE TABLE HESAPLAR (TEMA CHAR(25) NOT NULL,ARANACAK CHAR(30) NULL, " +
	    	           			" HESAP_KODU CHAR(12) NULL) " ;
	    	           tablo_yap(sorgu);
	   }
	   catch (SQLException ex) {  
	   	JOptionPane.showMessageDialog(null, ex);  
	       }  
		}
	private void tablo_yap(String sorgu) throws ClassNotFoundException, SQLException {
	   	Class.forName("org.sqlite.JDBC");
	   	con.close();
	   	con = null;
	   	con = GLOBAL.myTemaConnection();
	   	java.sql.Statement stmt = null;
	   	stmt = con.createStatement();  
	    	stmt.execute(sorgu);  
	    	stmt.close();
	       con.close();
		   }
	   public static ResultSet  tema_oku() throws ClassNotFoundException, SQLException
	     {
	    	 Class.forName("org.sqlite.JDBC");
	   	 	 con.close();
	    	 con = null;
	    	 ResultSet	rss = null;
	    	 PreparedStatement stmt = null;
	    	 con = GLOBAL.myTemaConnection();
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
	    	 con = GLOBAL.myTemaConnection();
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
	    	 con =GLOBAL.myTemaConnection();
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
	    	 con =GLOBAL.myTemaConnection();
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
			 con = GLOBAL.myTemaConnection();
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
			 con = GLOBAL.myTemaConnection();
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
			 con = GLOBAL.myTemaConnection();
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
			 con = GLOBAL.myTemaConnection();
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
