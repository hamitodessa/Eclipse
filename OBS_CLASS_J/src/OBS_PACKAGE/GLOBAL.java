package OBS_PACKAGE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Properties;
import java.util.Set;

import javax.swing.JOptionPane;

import net.sourceforge.chart2d.Dataset;



public class GLOBAL {
	 public static String KULL_ADI = "";
	 public static  int filtre_sayfa ;
	 static boolean result = false;
	 public static  String hangi_mizan = "" ;
	 
	 static String OBS_DOSYA = "OBS_SISTEM.DB";
	 static String SQL_BACKUP = "SQL_BACKUP.DB";
	 static String SQL_LOG = "SQL_LOG.DB";
	 static String SURUCU = "C:\\OBS_SISTEM\\";
     static String DBYERI = "C:\\OBS_DATABASES\\";
     public static String hsp_hsp_kodu =""; 
     public static String stk_kodu =""; 
     public static String irs_no =""; 
     public static String nerden ="";
     public static String yazici[]; 
     public static String g_baslik = "";
     public static String g_legends[];
     public static String g_labelsAxisLabels[];
     public static String g_LabelsAxisTitleText = "";
     public static String g_setNumbersAxisTitleText="";
     public static double min_value =0;

     public static Dataset g_dataSet ;
     static String ayarlar[][]; // = new String[5][5];
     static Connection con = null;
    
   //*************************************************
     public static Connection myConnection() throws SQLException {  
         Connection conn = null;  
          try {  
             conn = DriverManager.getConnection("jdbc:sqlite:" + SURUCU + OBS_DOSYA );  
         } 
         catch (SQLException e) 
         {
        
         }  
           return conn;  
         }  
   //*************************************************
     @SuppressWarnings("unused")
	 private static  Connection myBConnection (){
	    	Connection conn = null;  
	        try{
	        	 conn = DriverManager.getConnection("jdbc:sqlite:" + SURUCU + SQL_BACKUP );
	        } catch (SQLException e) {  
	         }  
	         return conn;  
	        }  
   //*************************************************
     @SuppressWarnings("unused")
	 private static  Connection myLConnection () {
    		Connection conn = null;  
	        try{
	        	conn = DriverManager.getConnection("jdbc:sqlite:" + SURUCU + SQL_LOG);
	        } catch (SQLException e) {  
	        }  
	         return conn;  
	        }  
   //*************************************************
     public static void obs_dosya_olustur() throws Exception {
	    	 try {  
	    		 Class.forName("org.sqlite.JDBC");
	        	 con = myConnection();
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
 	           sorgu = "CREATE TABLE GIDEN_RAPOR (ID	INTEGER PRIMARY KEY AUTOINCREMENT,USER_NAME	CHAR(20) NOT NULL,TARIH	DATE,KONU	CHAR(50),RAPOR	CHAR(50),ALICI	CHAR(50),ACIKLAMA CHAR(100),GONDEREN CHAR(50)); " ;
               tablo_yap(sorgu);
	           sorgu = "CREATE TABLE E_MAIL_BILGILERI (USER_NAME	CHAR(20) NOT NULL,HESAP	CHAR(40),HOST	CHAR(30),PORT	CHAR(20),\n"  
	           		 	+ "	SIFR	CHAR(20),SSL	INTEGER,TSL	INTEGER,GON_MAIL CHAR (40),GON_ISIM CHAR(50) ,PRIMARY KEY(\"USER_NAME\")); " ;
	           tablo_yap(sorgu);
		       sorgu = "CREATE TABLE IP (IPID 	INTEGER PRIMARY KEY AUTOINCREMENT ,IP	CHAR(50) NOT NULL,USER_NAME	CHAR(20) ); " ;
		       tablo_yap(sorgu);
		       sorgu = "CREATE TABLE USER_DETAILS (CDID INTEGER PRIMARY KEY AUTOINCREMENT ,USER_PROG_KODU	CHAR(10) NOT NULL,USER_NAME	CHAR(20),USER_SERVER CHAR(50)," 
			       		+ " USER_PWD_SERVER CHAR(50),USER_INSTANCE_OBS CHAR(50),USER_IP_OBS CHAR(50),USER_PROG_OBS CHAR(20),DIZIN CHAR(200),YER CHAR(1),\n" 
			       		+ " DIZIN_CINS CHAR(1),IZINLI_MI CHAR(1),CALISAN_MI CHAR(1),HANGI_SQL CHAR(10)); " ;
		       tablo_yap(sorgu);
		       sorgu = "CREATE TABLE EKSTRE (TARIH CHAR(10) ,EVRAK INTEGER,IZAHAT CHAR(100),KOD CHAR(10),KUR DOUBLE, BORC DOUBLE,ALACAK DOUBLE ,BAKIYE DOUBLE) ;"  ;
		       tablo_yap(sorgu);
		       sorgu = "CREATE TABLE KUR_CINSI (KUR	CHAR(3)); " ;
		       tablo_yap(sorgu); 
		       sorgu = "CREATE TABLE GOREV_ZAMANI (ZAMAN	DATE );";
		       tablo_yap(sorgu);
		       sorgu = "CREATE TABLE USERS (USER_NAME	CHAR(20),USER_PWD CHAR(50),USER_LEVEL CHAR(2),USER_DB_IZIN CHAR(255),USER_MAIL CHAR(50),USER_YENI_DOSYA_ACMA INTEGER,USER_YENI_DOSYA_ACMA_SERVER INTEGER," 
			       		+ "	PRIMARY KEY(\"USER_NAME\")); " ;
		       tablo_yap(sorgu); 
		       sorgu = "CREATE INDEX IDX_USER_NAME  ON USER_DETAILS  (USER_NAME) ; " ;
		       tablo_yap(sorgu); 
	           Class.forName("org.sqlite.JDBC");
		     	Connection conn = null;
		        conn = myConnection();
	            sorgu = "INSERT INTO USERS(USER_NAME,USER_PWD,USER_LEVEL,USER_DB_IZIN,USER_MAIL,USER_YENI_DOSYA_ACMA,USER_YENI_DOSYA_ACMA_SERVER)  VALUES(?,?,?,?,?,?,?); " ;
		        java.sql.PreparedStatement pstmt = conn.prepareStatement(sorgu) ;
		        		pstmt.setString(1, "Admin");
		        		String encodedString = Base64.getEncoder().encodeToString("obs".getBytes());
		                pstmt.setString(2, encodedString);
		                pstmt.setString(3, "1");
		                pstmt.setString(4, "");
		                pstmt.setString(5, "");
		                pstmt.setInt(6, 1);
		                pstmt.setInt(7, 1);
		                pstmt.executeUpdate();
		                pstmt.close();
		                conn.close();
	    }
	    catch (SQLException ex) {  
	    	JOptionPane.showMessageDialog(null, ex);  
	        }  
		}
	 private static void tablo_yap(String sorgu) throws ClassNotFoundException, SQLException {
	    	Class.forName("org.sqlite.JDBC");
	    	con.close();
	    	con = null;
        	con = myConnection();
        	java.sql.Statement stmt = null;
        	stmt = con.createStatement();  
         	stmt.execute(sorgu);  
         	stmt.close();
            con.close();
		   }
	 public static void surucu_kontrol() {
	    {
	        File tmpDir = new File(SURUCU);
	        boolean exists = tmpDir.exists();
	        if (exists)
	        {    
	        }
	        else
	        {
	        	tmpDir.mkdirs();
	        }
	        tmpDir = new File(DBYERI);
	        exists = tmpDir.exists();
	        if (exists)
	        {       
	        }
	        else
	        {
	        	tmpDir.mkdirs();
	        }
	        tmpDir = new File(SURUCU + OBS_DOSYA);
	        exists = tmpDir.exists();
	        if (exists)
	        {   
	        	// "Dosya Mevcut"
	        	
	        	//Propertis kontrol//
	        	
	        	 tmpDir = new File(SURUCU +  "/admin.properties");
	 	        exists = tmpDir.exists();
	 	        if (exists)
	 	        { 
	 	        	//Prop var
	 	        }
	 	        else
	 	        {
	 	        	//obs_set_olustur();
					set_ilk() ;
	 	        }
	 	        
	        	
	        }
	        else
	        {
	        	try {
					obs_dosya_olustur();
				//obs_set_olustur();
					set_ilk() ;
					
				} catch (Exception e) {
					 JOptionPane.showMessageDialog(null, e.getMessage());
				}
	        }
	    }
	    }
	 public  Boolean user_var(String usr,String pwd) throws ClassNotFoundException, SQLException {
		
		 Class.forName("org.sqlite.JDBC");
		 con = null;
		 result = false;
		 con = myConnection();
		 java.sql.PreparedStatement stmt = con.prepareStatement("SELECT * FROM USERS WHERE USER_NAME=? AND USER_PWD=?");
				stmt.setString(1, usr.toString());
				stmt.setString(2, pwd.toString());
				ResultSet rs = stmt.executeQuery();
				rs.next();
				int count=0;
				count = rs.getRow();
				if (count  != 0) result =true;
			stmt.close();
			con.close();
			return result;
	 }
	 public void user_sil(String usr) throws SQLException, ClassNotFoundException
	 {
		 Class.forName("org.sqlite.JDBC");
		 con.close();
		 con = null;
		 PreparedStatement stmt = null;
		 con = myConnection();
		 String sql = "DELETE FROM USERS WHERE USER_NAME = ? ";
		 stmt = con.prepareStatement(sql);
		 stmt.setString(1,usr);
		 stmt.executeUpdate();
		 stmt.close();
		 con.close();
		
	 }
	 public void user_details_sil(String user) throws ClassNotFoundException, SQLException
	 {
		 Class.forName("org.sqlite.JDBC");
		 con.close();
		 con = null;
		 PreparedStatement stmt = null;
		 con = myConnection();
		 String sql = "DELETE  FROM USER_DETAILS WHERE USER_NAME =?";
		 stmt = con.prepareStatement(sql);
		 stmt.setString(1,user);
		 stmt.executeUpdate();
		 stmt.close();
		 con.close();
	 }
	 public ResultSet user_details_bak() throws ClassNotFoundException, SQLException
	 {
		 Class.forName("org.sqlite.JDBC");
   	 	 con.close();
    	 con = null;
    	 ResultSet	rss = null;
    	 PreparedStatement stmt = null;
    	 con = myConnection();
    	 String sql = "SELECT  * FROM USER_DETAILS WHERE USER_PROG_OBS <> 'Fihrist'  ";
    	 stmt = con.prepareStatement(sql);
    	 rss = stmt.executeQuery();
    	
    	 return rss;
	 }
	 public ResultSet user_isim_doldur() throws ClassNotFoundException, SQLException
	 {
		 
		 Class.forName("org.sqlite.JDBC");
   	 	 con.close();
    	 con = null;
    	 ResultSet	rss = null;
    	 PreparedStatement stmt = null;
    	 con = myConnection();
    	 String sql = "SELECT  USER_NAME FROM USERS ORDER BY USER_NAME";
    	 stmt = con.prepareStatement(sql);
    	 rss = stmt.executeQuery();
    	
    	 return rss;
		
	 }
	 public Boolean user_bak(String usr) throws ClassNotFoundException, SQLException
	 {
		 Class.forName("org.sqlite.JDBC");
		 con.close();
		 con = null;
		 result = false;
		 con = myConnection();
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM USERS WHERE USER_NAME=?");
				stmt.setString(1, usr.toString());
				ResultSet rs = stmt.executeQuery();
				rs.next();
				int count=0;
				count = rs.getRow();
				if (count  != 0) result =true;
			stmt.close();
			con.close();
			 return result;
		 }
	 public static void setting_yaz(String anahtar,String deger)
	 {
		 OutputStream output;
		 	try {
		 		set_doldur();
		 		boolean varmi = false;
				output = new FileOutputStream( GLOBAL.SURUCU + "/admin.properties");
				Properties prop = new Properties();
				 for (int i = 0; i < ayarlar.length   ; i++) 
				 {
					 if (new String(ayarlar[i][0].toString()).equals(anahtar.toString()) == true) 
					 {
						 ayarlar[i][1] = deger;
						 varmi = true;
					 }
				 }
				 	for (int i = 0; i < ayarlar.length   ; i++) {
				 	prop.setProperty(ayarlar[i][0], ayarlar[i][1]);
				 }
				 	if (varmi == false) prop.setProperty(anahtar, deger);
		            prop.store(output, "OBS AYARLAR");
		            output.close();
			} catch (FileNotFoundException e2) {
			} catch (IOException e1) {
			}
	 }
	 public static void set_doldur() throws IOException {
		 InputStream input;
		 Properties prop = new Properties();
		 input = new FileInputStream( GLOBAL.SURUCU + "/admin.properties");
	     prop.load(input);
         final Set<String> keys =  prop.stringPropertyNames(); 
         int kac_satir = 0;
	     for (@SuppressWarnings("unused") final String key : keys) kac_satir += 1 ;
	     ayarlar  = new String[kac_satir][kac_satir];
	     int satir = 0;
	        for (final String key : keys)
	        {
	            final String value = prop.getProperty(key);
	            ayarlar[satir][0] = key;
	            ayarlar[satir][1] = value;
				satir += 1;
	        }
	      input.close();
	    }
	 public static String setting_oku(String anahtar) throws IOException
	 {
		 File tmpDir = new File(SURUCU);
	        boolean exists = tmpDir.exists();
		 tmpDir = new File(SURUCU +  "/admin.properties");
	        exists = tmpDir.exists();
	        if (exists)
	        { 
	        	//Prop var
	        }
	        else
	        {
	        	//obs_set_olustur();
				set_ilk() ;
	        }
	        
		 String donen="";
		 FileInputStream iss;
		 iss =  new FileInputStream( GLOBAL.SURUCU + "/admin.properties");
		 Properties adminProps = new Properties();
		 		adminProps.load(iss);
				donen = adminProps.getProperty(anahtar).toString();
  			    iss.close();
				return donen;
	 }
	 private static void  set_ilk() 
	 {
		 OutputStream output;
		 	try {
				output = new FileOutputStream( GLOBAL.SURUCU + "/admin.properties");
				 Properties prop = new Properties();
				 prop.setProperty("BENI_HATIRLA", "");
				 prop.setProperty("ISIM", "");
				 prop.setProperty("SIFRE", "");	
				 prop.setProperty("CARI_DEKONT_BAKIYE_GOSTER", "-1");
				 prop.setProperty("CARI_ARAMA", "[Calibri,0,12]");
				 prop.setProperty("CARI_DEK_Y_FIS", ",, ");
				 prop.setProperty("CARI_DVZ_CEV", "[Calibri,0,12]");
				 prop.setProperty("CARI_EKSTRE", "[Calibri,0,12]");
				 prop.setProperty("CARI_GUN_ISL", "[Calibri,0,12]");
				 prop.setProperty("CARI_HES_BAK", "[Calibri,0,12]");
				 prop.setProperty("CARI_HSPPLN", "[Calibri,0,12]");
				 prop.setProperty("CARI_HSPPLN_CAG", ",, ");
				 prop.setProperty("CARI_KARTON", "[Calibri,0,12]");
				 prop.setProperty("CARI_MIZAN", "[Calibri,0,12]");
				 prop.setProperty("CARI_MIZ_GRUP", "3");
				 prop.setProperty("STK_FAT_ABILGI", "[Calibri,0,12]");
				 prop.setProperty("STK_FAT_BASLIK","[Calibri,0,12]");
				 prop.setProperty("STK_FAT_DETAY", "[Calibri,0,12]");
				 prop.setProperty("STK_FAT_FIR_BILGI","Cari_Dosya");
				 prop.setProperty("STK_FAT_GIRIS","[Calibri,0,12]");
				 prop.setProperty("STK_FAT_SATIR", "25");
				 prop.setProperty("STK_STOK_KONTROL", "-1");
				 prop.setProperty("STK_YAZI_ILE", "[Calibri,0,12]");
				 prop.setProperty("STK_ISR_ABILGI","[Calibri,0,12]");
				 prop.setProperty("STK_IRS_BASLIK","[Calibri,0,12]");
				 prop.setProperty("STK_IRS_DETAY", "[Calibri,0,12]");
				 prop.setProperty("STK_IRS_FIR_BILGI","[Calibri,0,12]");
				 prop.setProperty("STK_IRS_GIRIS", "[Calibri,0,12]");
				 prop.setProperty("STK_IRS_SATIR", "25");
				 prop.setProperty("STK_RAPORLAMA","[Calibri,0,12]");
				 prop.setProperty("STK_ENV_KUS", "-1");
				 prop.setProperty("STK_ENV_YAZ", "-1");
				 prop.setProperty("STK_IMA_BAS_TAR", "01.01.1900");
				 prop.setProperty("GUN_KON", "-1");
				 prop.setProperty("GUN_KON_ZAM", "1800000");
				 prop.setProperty("GUN_SER_KON", "-1");
				 prop.setProperty("GUN_SER_KON_ZAM", "1800000");
				 prop.setProperty("KAM_CEK_GIR", "25");
				 prop.setProperty("KAM_CEK_CIK", "25");
				 prop.setProperty("KAM_SEN_GIR", "25");
				 prop.setProperty("KAM_SEN_CIK", "25");
				 prop.setProperty("KAM_SAT_KOP", ",, ");
				 prop.setProperty("KAM_SAT_YAP",  ",, ");
				 prop.setProperty("PRG_PARA", "");
				 prop.setProperty("PRG_FILTRE",  ",, ");
				 prop.setProperty("PRG_YENILE",  ",, ");
				 prop.setProperty("PRG_KAYDET",  ",, ");
				 prop.setProperty("PRG_SIL",  ",, ");
				 prop.setProperty("PRG_GORUNUM",  "Metal");
				 prop.setProperty("PRG_GRID_RENK",  "0, 191, 255");
		         prop.store(output, "OBS AYARLAR");
		         output.close();
			} catch (FileNotFoundException e2) {
				 JOptionPane.showMessageDialog(null, e2.toString()  );
			} catch (IOException e1) {
			}
	 }
	 public ResultSet user_db_izinleri(String username,String prg) throws SQLException, ClassNotFoundException
	 {
			Class.forName("org.sqlite.JDBC");
			con.close();
			con = null;
			ResultSet	rss = null;
			PreparedStatement stmt = null;
			con = myConnection();
			String sql = "SELECT * FROM USER_DETAILS WHERE USER_NAME=? AND USER_PROG_OBS=? AND IZINLI_MI='E' ORDER BY CALISAN_MI DESC , USER_PROG_KODU";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, username.toString());
				stmt.setString(2, prg.toString());
				rss = stmt.executeQuery();
				return rss;
	 }
	 public void sifre_degis(String user,String sifre) throws SQLException, ClassNotFoundException
	 {
		 Class.forName("org.sqlite.JDBC");
		 PreparedStatement stmt = null;
		 con.close();
		 con = null;
		 con = myConnection();
		 String sql ="UPDATE  USERS  SET USER_PWD=?  WHERE USER_NAME=?";
		 stmt = con.prepareStatement(sql);
		 String encodedString = Base64.getEncoder().encodeToString(sifre.getBytes());
		 stmt.setString(1, encodedString);
		 stmt.setString(2, user.toString());
		 stmt.executeUpdate();
		 stmt.close();
		 con.close();
	 }
	 public static ResultSet user_details_izinleri(String kull, String modul, String nerde) throws SQLException, ClassNotFoundException
     {
		 	Class.forName("org.sqlite.JDBC");
		 	con.close();
		 	 myConnection().close();
		 	con = null;
			ResultSet	rss = null;
			PreparedStatement stmt = null;
			con = myConnection();
			String sql = "SELECT * FROM USER_DETAILS WHERE USER_NAME  ='" + kull + "' AND USER_PROG_OBS ='" + modul + "' AND " + nerde + " AND  IZINLI_MI ='E' ORDER BY CALISAN_MI DESC";
			stmt = con.prepareStatement(sql);
			rss = stmt.executeQuery();
			return rss;
     }
	 public void calisanmi_degis(String user , String program,String yer ) throws ClassNotFoundException, SQLException
     {
		 Class.forName("org.sqlite.JDBC");
		 con.close();
		 con = null;
		 PreparedStatement stmt = null;
		 con = myConnection();
		 String sql = "UPDATE  USER_DETAILS  SET CALISAN_MI=?  WHERE USER_NAME =?  AND USER_PROG_OBS=?";
		 stmt = con.prepareStatement(sql);
		 stmt.setString(1,"");
		 stmt.setString(2,user);
		 stmt.setString(3,program);
		 stmt.executeUpdate();
		 stmt.close();
		 con.close();
     }
     public void details_yaz(String upk, String usn, String usserver, String sifre, String instance, String ip, String prog
                            ,String dizin, String yer, String dcins, String izli, String calmi, String hsql,String cdid) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
    	 con.close();
		 con = null;
		 PreparedStatement stmt = null;
		 con = myConnection();
       if (!cdid.equals(""))
         { 
    	   	 String sql = "DELETE FROM USER_DETAILS WHERE CDID = " + cdid + " ";
        	 stmt = con.prepareStatement(sql);
             stmt.executeUpdate();
         }
         String sql = "INSERT INTO USER_DETAILS (USER_PROG_KODU,USER_NAME,USER_SERVER,USER_PWD_SERVER,USER_INSTANCE_OBS,USER_IP_OBS," +
                         "USER_PROG_OBS,DIZIN,YER,DIZIN_CINS,IZINLI_MI,CALISAN_MI,HANGI_SQL) ";
             sql += "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
             {
            	 stmt = con.prepareStatement(sql);
            	 stmt.setString(1, upk);
            	 stmt.setString(2, usn);
            	 stmt.setString(3, usserver);
            	 stmt.setString(4, sifre);
            	 stmt.setString(5, instance);
            	 stmt.setString(6, ip);
            	 stmt.setString(7, prog);
            	 stmt.setString(8, dizin);
            	 stmt.setString(9, yer);
            	 stmt.setString(10,dcins);
            	 stmt.setString(11,izli);
            	 stmt.setString(12, calmi);
            	 stmt.setString(13, hsql);
             }
            
             stmt.executeUpdate();
             stmt.close();
             con.close();
     }
     public void user_ekle_degis(String user , String pwd ,String lvl ,String udbi ,String um ,Boolean uyda ,Boolean uydas ) throws ClassNotFoundException, SQLException
     {
     	 Class.forName("org.sqlite.JDBC");
       	 con.close();
   		 con = null;
   		 PreparedStatement stmt = null;
   		 con = myConnection();
   		 String sql = "INSERT INTO USERS (USER_NAME,USER_PWD,USER_LEVEL,USER_DB_IZIN,USER_MAIL,USER_YENI_DOSYA_ACMA,USER_YENI_DOSYA_ACMA_SERVER)" +
   						"VALUES (?,?,?,?,?,?,?)";
   		 {
   			 stmt = con.prepareStatement(sql);
   			 stmt.setString(1, user);
   			String encodedString = Base64.getEncoder().encodeToString(pwd.getBytes());
   			 stmt.setString(2, encodedString);
   			 stmt.setString(3, lvl);
   			 stmt.setString(4, udbi);
   			 stmt.setString(5, um);
   			 stmt.setBoolean(6, uyda);
   			 stmt.setBoolean(7, uydas);
   		 }
   		 stmt.executeUpdate();
   		 stmt.close();
   		 con.close();
     }
     public void ip_dos_kont(String ip) throws ClassNotFoundException, SQLException
     {
     	 Class.forName("org.sqlite.JDBC");
    	 con.close();
		 con = null;
		 ResultSet	rss = null;
		 PreparedStatement stmt = null;
		 con = myConnection();
		 String sql ="SELECT  * FROM IP WHERE IP ='" + ip + "'";
		 stmt = con.prepareStatement(sql);
			rss = stmt.executeQuery();
			int count=0;
			rss.next();
			count = rss.getRow();
			if (count  == 0) 
         {
				stmt = con.prepareStatement("INSERT INTO IP (IP) VALUES (?)");
				stmt.setString(1,ip);
				stmt.executeUpdate();
         }
			stmt.close();
			con.close();
     }
     public static ResultSet  ipp (String kull) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
			con.close();
			con = null;
			ResultSet	rss = null;
			PreparedStatement stmt = null;
			con = myConnection();
			String sql = "SELECT DISTINCT IP FROM IP ";
			stmt = con.prepareStatement(sql);
			rss = stmt.executeQuery();
			return rss;
     }
     public void cd_sil(int cdid) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
    	 con.close();
		 con = null;
		 PreparedStatement stmt = null;
		 con = myConnection();
		 String sql ="DELETE  FROM USER_DETAILS WHERE CDID =" + cdid + "";
		 stmt = con.prepareStatement(sql);
		 stmt.executeUpdate();
		 stmt.close();
		 con.close();	       
     }
     public ResultSet user_ekleme_bak() throws ClassNotFoundException, SQLException
     {
   	 Class.forName("org.sqlite.JDBC");
   	 	 con.close();
    	 con = null;
    	 ResultSet	rss = null;
    	 PreparedStatement stmt = null;
    	 con = myConnection();
    	 String sql = "SELECT  * FROM USERS ORDER BY USER_NAME ";
    	 stmt = con.prepareStatement(sql);
    	 rss = stmt.executeQuery();
    	 return rss;
     }
     public ResultSet mail_bak(String user) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
   	 	 con.close();
    	 con = null;
    	 ResultSet	rss = null;
    	 PreparedStatement stmt = null;
    	 con = myConnection();
    	 String sql = "SELECT  * FROM E_MAIL_BILGILERI WHERE USER_NAME = ? ";
    	 stmt = con.prepareStatement(sql);
    	 stmt.setString(1,user);
    	 rss = stmt.executeQuery();
    	 return rss;
     }
     public void mail_yaz(String hsp,String host,String port,String sifre,String gmail,String ghesap,int ssl,int tsl) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
    	 con.close();
		 con = null;
		 PreparedStatement stmt = null;
		 con = myConnection();
      
    	   	 String sql = "DELETE FROM E_MAIL_BILGILERI WHERE USER_NAME = ? ";
        	 stmt = con.prepareStatement(sql);
        	 stmt.setString(1, GLOBAL.KULL_ADI);
             stmt.executeUpdate();
             stmt = null;
        
   		 sql = "INSERT INTO E_MAIL_BILGILERI (USER_NAME,HESAP,HOST,PORT,SIFR,GON_MAIL,GON_ISIM,SSL,TSL)" +
   						"VALUES (?,?,?,?,?,?,?,?,?)";
   		 
   			 stmt = con.prepareStatement(sql);
   			 stmt.setString(1,  GLOBAL.KULL_ADI);
   			 stmt.setString(2, hsp);
   			 stmt.setString(3, host);
   			 stmt.setString(4, port);
   			 stmt.setString(5, sifre);
   			 stmt.setString(6, gmail);
   			 stmt.setString(7, ghesap);
   			 stmt.setInt(8, ssl);
   			stmt.setInt(9, tsl);
   			stmt.executeUpdate();
   			stmt.close();
   			con.close();
     }
     public void giden_rapor_yaz(Date tar ,String konu,String rapor,String alici,String gond,String aciklama,String uname) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
    	 con.close();
		 con = null;
		 PreparedStatement stmt = null;
		 con = myConnection();
   		 String sql =  "INSERT INTO GIDEN_RAPOR (USER_NAME,TARIH,KONU,RAPOR,ALICI,ACIKLAMA,GONDEREN) " +
   						"VALUES (?,?,?,?,?,?,?)";
    		 stmt = con.prepareStatement(sql);
   			 stmt.setString(1,  uname);
   			 stmt.setDate(2, (java.sql.Date) tar);
   			 stmt.setString(3, konu);
   			 stmt.setString(4, rapor);
   			 stmt.setString(5, alici);
   			 stmt.setString(6, aciklama);
   			 stmt.setString(7, gond);
   			 stmt.executeUpdate();
   			 stmt.close();
   			 con.close();
     }
     public void giden_rapor_sil(int id) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
    	 con.close();
		 con = null;
		 PreparedStatement stmt = null;
		 con = myConnection();
		 String sql = "DELETE  FROM GIDEN_RAPOR WHERE ID  =" + id + "";
		 stmt = con.prepareStatement(sql);
		 stmt.executeUpdate();
		 stmt.close();
		 con.close();	       
     }
     public ResultSet giden_rapor(String usr) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
   	 	 con.close();
    	 con = null;
    	 ResultSet	rss = null;
    	 PreparedStatement stmt = null;
    	 con = myConnection();
    	 String sql = "SELECT ID,TARIH ,KONU ,RAPOR, ALICI ,GONDEREN, ACIKLAMA ,USER_NAME AS [USER] " + 
    			 		"FROM   GIDEN_RAPOR WHERE USER_NAME  LIKE '" + usr + "' ORDER BY TARIH ";
    	 stmt = con.prepareStatement(sql);
    	 rss = stmt.executeQuery();
    	 return rss;
     }
     public ResultSet alici_oku(String usr) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
   	 	 con.close();
    	 con = null;
    	 ResultSet	rss = null;
    	 PreparedStatement stmt = null;
    	 con = myConnection();
    	 String sql = "SELECT DISTINCT ALICI  " + 
    			 		"FROM   GIDEN_RAPOR WHERE USER_NAME  LIKE '" + usr + "' ORDER BY ALICI ";
    	 stmt = con.prepareStatement(sql);
    	 rss = stmt.executeQuery();
    	 return rss;
     }
     public ResultSet  tema_oku() throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
   	 	 con.close();
    	 con = null;
    	 ResultSet	rss = null;
    	 PreparedStatement stmt = null;
    	 con = myConnection();
    	 String sql = "SELECT DISTINCT TEMA FROM ANA_HESAP ";
    	 stmt = con.prepareStatement(sql);
    	 rss = stmt.executeQuery();
    	 return rss;
     }
     public String tema_anahesap(String tema) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
   	 	 con.close();
    	 con = null;
    	 ResultSet	rss = null;
    	 PreparedStatement stmt = null;
    	 con = myConnection();
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
     public ResultSet ttema_oku(String tema) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
   	 	 con.close();
    	 con = null;
    	 ResultSet	rss = null;
    	 PreparedStatement stmt = null;
    	 con = myConnection();
    	 String sql = "SELECT  ARANACAK,HESAP_KODU  FROM HESAPLAR WHERE TEMA ='" + tema + "' ORDER BY ARANACAK";
    	 stmt = con.prepareStatement(sql);
    	 rss = stmt.executeQuery();
    	 return rss;
     }
     public ResultSet temalar_oku(String tema) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
   	 	 con.close();
    	 con = null;
    	 ResultSet	rss = null;
    	 PreparedStatement stmt = null;
    	 con = myConnection();
    	 String sql ="SELECT ARANACAK,YAZILACAK  FROM TEMA WHERE TEMA = '" + tema + "'";
    	 stmt = con.prepareStatement(sql);
    	 rss = stmt.executeQuery();
    	 return rss;
     }
     public void tema_sil(String tema) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
    	 con.close();
		 con = null;
		 PreparedStatement stmt = null;
		 con = myConnection();
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
     public void tema_ana_hes_kayit(String tema,String hesap) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
    	 con.close();
		 con = null;
		 PreparedStatement stmt = null;
		 con = myConnection();
   		 String sql =  "INSERT INTO ANA_HESAP (TEMA,ANA_HESAP) " +
   						"VALUES (?,?)";
    	 stmt = con.prepareStatement(sql);
   		 stmt.setString(1, tema);
   		 stmt.setString(2, hesap);
   		 stmt.executeUpdate();
   		stmt.close();
   		con.close();
     }
     public void tema_tem_hes_kayit(String tema,String aranan,String yazilan) throws SQLException, ClassNotFoundException
     {
    	 Class.forName("org.sqlite.JDBC");
    	 con.close();
		 con = null;
		 PreparedStatement stmt = null;
		 con = myConnection();
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
     public void tema_hes_kayit(String tema,String aranan,String yazilan) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("org.sqlite.JDBC");
    	 con.close();
		 con = null;
		 PreparedStatement stmt = null;
		 con = myConnection();
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
     public boolean internet_kontrol()
     {
    	 boolean result = false ;
    	 try {
    		
             URL url = new URL("http://www.google.com");
             URLConnection connection = url.openConnection();
             connection.connect();
             result = true ;
       
          } 
    	 catch (Exception ex)
    	 {
        	  result = false;
          }
		return result ;
	
     }
}
