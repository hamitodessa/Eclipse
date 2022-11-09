package OBS_C_2025;

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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Properties;
import java.util.Set;

import javax.swing.JOptionPane;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;





public class GLOBAL {
	
	static String OBS_DOSYA = "OBS_SISTEM_2025.DB";
	static String SQL_BACKUP = "SQL_BACKUP.DB";
	static String SQL_LOG = "SQL_LOG.DB";
	static String SURUCU = "C:\\OBS_SISTEM\\";
    static String DBYERI = "C:\\OBS_DATABASES\\";
    static Connection con ;
    static String ayarlar[][]; // = new String[5][5];
    public static String KULL_ADI = "";
    public static  int filtre_sayfa ;
    public static  String hangi_mizan = "" ;
    public static String yazici[]; 
    public static String nerden ="";
    
    public static String g_baslik = "";
    public static String g_legends[];
    public static String g_labelsAxisLabels[];
    public static String g_LabelsAxisTitleText = "";
    public static String g_setNumbersAxisTitleText="";
    public static double min_value =0;
  
	 //*************************************************
public static  Connection myConnection() throws SQLException
    {  
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
public  void obs_dosya_olustur() throws Exception {
   	 try {  
   		 Class.forName("org.sqlite.JDBC");
       	 con = myConnection();
       	 con.close();
            	  String sorgu= null;
           sorgu = "CREATE TABLE GIDEN_RAPOR (ID	INTEGER PRIMARY KEY AUTOINCREMENT,USER_NAME	CHAR(20) NOT NULL,TARIH	DATE,KONU	CHAR(50),RAPOR	CHAR(50),ALICI	CHAR(50),ACIKLAMA CHAR(100),GONDEREN CHAR(50)); " ;
          tablo_yap(sorgu);
          sorgu = "CREATE TABLE E_MAIL_BILGILERI (USER_NAME	CHAR(20) NOT NULL,HESAP	CHAR(40),HOST	CHAR(30),PORT	CHAR(20),\n"  
          		 	+ "	SIFR	CHAR(20),SSL	INTEGER,TSL	INTEGER,GON_MAIL CHAR (40),GON_ISIM CHAR(50) ,PRIMARY KEY(\"USER_NAME\")); " ;
          tablo_yap(sorgu);
	       sorgu = "CREATE TABLE IP (IPID 	INTEGER PRIMARY KEY AUTOINCREMENT ,IP	CHAR(50) NOT NULL,USER_NAME	CHAR(20) ); " ;
	       tablo_yap(sorgu);
	       sorgu = "CREATE TABLE USER_DETAILS (CDID INTEGER PRIMARY KEY AUTOINCREMENT ,USER_PROG_KODU	CHAR(10) NOT NULL,USER_NAME	CHAR(20),USER_SERVER CHAR(50)," 
		       		+ " USER_PWD_SERVER CHAR(50),USER_INSTANCE_OBS CHAR(50),USER_IP_OBS CHAR(50),USER_PROG_OBS CHAR(20),DIZIN CHAR(200),YER CHAR(1),\n" 
		       		+ " DIZIN_CINS CHAR(1),IZINLI_MI CHAR(1),CALISAN_MI CHAR(1),HANGI_SQL CHAR(10),LOG INTEGER , LOG_YERI CHAR(20)); " ;
	       tablo_yap(sorgu);
	       sorgu = "CREATE TABLE EKSTRE (TARIH CHAR(10) ,EVRAK INTEGER,IZAHAT CHAR(100),KOD CHAR(10),KUR DOUBLE, BORC DOUBLE,ALACAK DOUBLE ,BAKIYE DOUBLE) ;"  ;
	       tablo_yap(sorgu);
	       sorgu = "CREATE TABLE USERS (USER_NAME	CHAR(20),USER_PWD CHAR(50),USER_LEVEL CHAR(2),USER_DB_IZIN CHAR(255),USER_MAIL CHAR(50),USER_YENI_DOSYA_ACMA INTEGER,USER_YENI_DOSYA_ACMA_SERVER INTEGER," 
		       		+ "	PRIMARY KEY(\"USER_NAME\")); " ;
	       tablo_yap(sorgu); 
	       sorgu = "CREATE TABLE LOG_MAIL (USER_NAME	CHAR(20),E_MAIL 	CHAR(50), AKTIV  INTEGER,	PRIMARY KEY(\"USER_NAME\")); " ;
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
private void tablo_yap(String sorgu) throws ClassNotFoundException, SQLException {
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
       	// "Dosya Mevcut"	//Propertis kontrol//
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
       		GLOBAL gLB = new GLOBAL();
       		gLB.obs_dosya_olustur();
			//obs_set_olustur();
				set_ilk() ;
			} catch (Exception e) {
				 JOptionPane.showMessageDialog(null, e.getMessage());
			}
       }
   }
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
