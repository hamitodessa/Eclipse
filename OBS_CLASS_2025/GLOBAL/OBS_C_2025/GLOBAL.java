package OBS_C_2025;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

import java.net.DatagramSocket;
import java.net.InetAddress;


import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.swing.JOptionPane;
import org.jfree.data.category.DefaultCategoryDataset;
import LOGER_KAYIT.ILOGER_KAYIT;
import LOGER_KAYIT.SQLITE_LOG;

import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;


import OBS_2025.Tema_Cari;
@SuppressWarnings({"unused","resource"})
public class GLOBAL {

	static final String OBS_DOSYA = System.getProperty("user.name") + "_OBS_SISTEM_2025.DB";
	static final String EKSTRE_DOSYA = System.getProperty("user.name") + "_EKSTRE.DB";
	public static final String SURUCU = "C:\\OBS_SISTEM\\";
	public static final String LOG_SURUCU =  "C:\\OBS_SISTEM\\LOGLAMA\\";
	public static final String DBYERI = "C:\\OBS_DATABASES\\";
	static Connection con ;
	static Connection Ekstrecon ;
	static String ayarlar[][]; // = new String[5][5];
	public static String KULL_ADI = "";
	public static String Log_Mail ="";
	public static int filtre_sayfa ;
	public static String hangi_mizan = "" ;
	public static String hangi_fatura = "" ;
	public static String yazici[]; 
	public static String nerden ="";
	public static String g_baslik = "";
	public static String g_legends;
	public static String g_setNumbersAxisTitleText="";
	public static double min_value =0;
	public static double max_value =0;
	public static int gkusurat = 0;
	public static DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
	//*************************************************
	public static  Connection myConnection() throws SQLException
	{  
		Connection conn = null;  
		try 
		{  
			conn = DriverManager.getConnection("jdbc:sqlite:" + SURUCU + OBS_DOSYA );  
		} 
		catch (SQLException e) 
		{	}  
		return conn;  
	}  
	public static Connection   myEkstreConnection ()
	{
			Connection conn = null;  
			try 
			{  
				conn = DriverManager.getConnection("jdbc:sqlite:" + SURUCU + EKSTRE_DOSYA );  
			} 
			catch (SQLException e) 
			{	
			}  
			return conn;  
	}
	public static  void obs_dosya_olustur() throws Exception 
	{
		try 
		{  
			Class.forName("org.sqlite.JDBC");
			con = myConnection();
			con.close();
			String sorgu= null;
			sorgu = "CREATE TABLE GIDEN_RAPOR (ID	INTEGER PRIMARY KEY AUTOINCREMENT,USER_NAME	CHAR(20) NOT NULL,TARIH	DATE,KONU	CHAR(50),RAPOR	CHAR(50),ALICI	CHAR(50),ACIKLAMA CHAR(100),GONDEREN CHAR(50)); " ;
			tablo_yap(sorgu);
			sorgu = "CREATE TABLE E_MAIL_BILGILERI (USER_NAME	CHAR(20) NOT NULL,HESAP	CHAR(40),HOST	CHAR(30),PORT	CHAR(20),"  
					+ "	SIFR	BLOB,SSL	INTEGER,TSL	INTEGER,GON_MAIL CHAR (40),GON_ISIM CHAR(50) ,PRIMARY KEY(\"USER_NAME\")); " ;
			tablo_yap(sorgu);
			sorgu = "CREATE TABLE IP (IPID 	INTEGER PRIMARY KEY AUTOINCREMENT ,IP	CHAR(50) NOT NULL,USER_NAME	CHAR(20) ); " ;
			tablo_yap(sorgu);
			sorgu = "CREATE TABLE USER_DETAILS (CDID INTEGER PRIMARY KEY AUTOINCREMENT ,USER_PROG_KODU	CHAR(10) NOT NULL,USER_NAME	CHAR(20),USER_SERVER CHAR(50)," 
					+ " USER_PWD_SERVER BLOB,USER_INSTANCE_OBS CHAR(50),USER_IP_OBS CHAR(50),USER_PROG_OBS CHAR(20),DIZIN CHAR(200),YER CHAR(1)," 
					+ " DIZIN_CINS CHAR(1),IZINLI_MI CHAR(1),CALISAN_MI CHAR(1),HANGI_SQL CHAR(10),LOG INTEGER , LOG_YERI CHAR(75)); " ;
			tablo_yap(sorgu);
			sorgu = "CREATE TABLE USERS (USER_NAME	CHAR(20),USER_PWD BLOB,USER_LEVEL CHAR(2),USER_DB_IZIN CHAR(255),USER_MAIL CHAR(50),USER_YENI_DOSYA_ACMA INTEGER,USER_YENI_DOSYA_ACMA_SERVER INTEGER," 
					+ "	PRIMARY KEY(\"USER_NAME\")); " ;
			tablo_yap(sorgu); 
			sorgu = "CREATE TABLE LOG_MAIL (USER_NAME	CHAR(20),E_MAIL 	CHAR(50), AKTIV  INTEGER); " ;
			tablo_yap(sorgu); 
			//sorgu = "CREATE INDEX IDX_USER_NAME  ON USER_DETAILS  (USER_NAME) ; " ;
			//tablo_yap(sorgu); 
			Class.forName("org.sqlite.JDBC");
			Connection conn = null;
			conn = myConnection();
			sorgu = "INSERT INTO USERS(USER_NAME,USER_PWD,USER_LEVEL,USER_DB_IZIN,USER_MAIL,USER_YENI_DOSYA_ACMA,USER_YENI_DOSYA_ACMA_SERVER)  VALUES(?,?,?,?,?,?,?); " ;
			java.sql.PreparedStatement pstmt = conn.prepareStatement(sorgu) ;
			pstmt.setString(1, "Admin");
			byte[]  qaz =	ENCRYPT_DECRYPT_STRING.eNCRYPT_manual("obs") ;
			String encodedString = Arrays.toString(qaz);
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
		catch (SQLException ex) 
		{  
			JOptionPane.showMessageDialog(null, ex.getMessage());  
		}  
	}
	public static  void ekstre_dosya_olustur() throws Exception 
	{
		Class.forName("org.sqlite.JDBC");
		Ekstrecon = myEkstreConnection();
		String sorgu= null;
		sorgu = "CREATE TABLE EKSTRE (TARIH CHAR(10) ,EVRAK INTEGER,IZAHAT CHAR(100),KOD CHAR(10),KUR DOUBLE, BORC DOUBLE,ALACAK DOUBLE ,BAKIYE DOUBLE) ;"  ;
		java.sql.Statement stmt = null;
		stmt = Ekstrecon.createStatement();  
		stmt.execute(sorgu);  
		stmt.close();
		Ekstrecon.close();
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
	public static void surucu_kontrol() 
	{
		File tmpDir = new File(SURUCU);
		boolean exists = tmpDir.exists();
		if (exists)
		{    
			tmpDir = new File(LOG_SURUCU);
			exists = tmpDir.exists();
			if (! exists)
			{
				tmpDir.mkdirs();
				File logDir = new File(LOG_SURUCU);
				logDir.mkdirs();
			}
		}
		else
		{
			tmpDir.mkdirs();
			File logDir = new File(LOG_SURUCU);
			logDir.mkdirs();
			mail_at();
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
		if (dos_kontrol(SURUCU + OBS_DOSYA))
		{   
			if (! dos_kontrol(SURUCU + EKSTRE_DOSYA))
			{ 
				try {
					ekstre_dosya_olustur();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (dos_kontrol(SURUCU +  "/" + System.getProperty("user.name") +".properties"))
			{ 
				//Prop var
			}
			else
			{
				set_ilk() ;//obs_set_olustur();
			}
		}
		else
		{
			try {
				obs_dosya_olustur();
				ekstre_dosya_olustur();
				Tema_Cari.dosya_yap();
				set_ilk() ; //obs_set_olustur();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Surucu Kontrol    " + e.getMessage());
			}
		}
	}
	public static void setting_yaz(String anahtar,String deger)
	{
		OutputStream output;
		try {
			set_doldur();
			boolean varmi = false;
			output = new FileOutputStream( GLOBAL.SURUCU + "/" + System.getProperty("user.name") + ".properties");
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
		} catch (Exception e1) {
		}
	}
	
	public static void set_doldur() throws IOException {
		InputStream input;
		Properties prop = new Properties();
		input = new FileInputStream( GLOBAL.SURUCU + "/" + System.getProperty("user.name") + ".properties");
		prop.load(input);
		final Set<String> keys =  prop.stringPropertyNames(); 
		int kac_satir = 0;
		for ( final String key : keys) kac_satir += 1 ;
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
		if (dos_kontrol(SURUCU +  "/"+ System.getProperty("user.name")+".properties"))
		{ 
			//Prop var
		}
		else
		{
			set_ilk() ; //obs_set_olustur();
		}
		String donen="";
		FileInputStream iss;
		iss =  new FileInputStream( GLOBAL.SURUCU + "/"+ System.getProperty("user.name")+".properties");
		Properties adminProps = new Properties();
		adminProps.load(iss);
		donen = adminProps.getProperty(anahtar).toString();
		iss.close();
		return donen;
	}
	public static void create_table_log(String dosya,String fadi,DIZIN_BILGILERI dBILGI) throws SQLException, ClassNotFoundException 
	{
		String sql = "" ;
		sql = "CREATE TABLE LOGLAMA("
				+ "	TARIH DATE NOT NULL,"
				+ "	MESAJ CHAR(100) NOT NULL,"
				+ "	EVRAK CHAR(15) NOT NULL,"
				+ "	USER_NAME CHAR(15) NULL"
				+ ") ";
		Connection sQLITEconn = DriverManager.getConnection("jdbc:sqlite:" + dosya  ) ;
		Statement stmt =sQLITEconn.createStatement();  
		
		stmt.execute(sql);
		stmt.close();
		sql = "CREATE INDEX IX_LOGLAMA  ON LOGLAMA  (TARIH,EVRAK) ; " ;
		stmt = sQLITEconn.createStatement();  
		stmt.execute(sql);
		sQLITEconn.close();

		ILOGER_KAYIT  sQLOG = new SQLITE_LOG();
		lOG_BILGI lbilgi = new lOG_BILGI();
		lbilgi.setmESAJ("Dosya Olusturuldu");
		lbilgi.seteVRAK("");
		sQLOG.Logla(lbilgi, dBILGI);
		lbilgi.setmESAJ("Firma Adi:" + fadi);
		lbilgi.seteVRAK("");
		sQLOG.Logla(lbilgi, dBILGI);
	}
	private static void  set_ilk() 
	{
		OutputStream output;
		try {
			output = new FileOutputStream( GLOBAL.SURUCU + "/" + System.getProperty("user.name") + ".properties");
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
			prop.setProperty("CARI_GUNLUK", "[Calibri,0,12]");
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
			prop.setProperty("GUN_KON_ZAM", "60");
			prop.setProperty("GUN_SER_KON", "-1");
			prop.setProperty("GUN_SER_KON_ZAM", "60");
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
			prop.setProperty("PRG_GORUNUM",  "McWinLookAndFeel");
			prop.setProperty("PRG_GRID_RENK",  "255, 255, 255");
			prop.setProperty("PRG_GRID_FORE_RENK", "255,255,255");
			prop.setProperty("PRG_GRID_BACK_RENK", "73, 113, 131");
			prop.setProperty("SOL_BOSLUK", "60");
			prop.setProperty("SAG_BOSLUK", "0");
			prop.setProperty("UST_BOSLUK", "40");
			prop.setProperty("ALT_BOSLUK", "0");
			prop.setProperty("ETIKET_YUK", "85");
			prop.setProperty("ETIKET_GEN", "240");
			prop.setProperty("ETIKET_ARA_BOSLUK", "0");
			prop.setProperty("ETIKET_YAZIM", "Yatay");
			prop.setProperty("KER_FIR_BILGI","Cari_Dosya");
			prop.setProperty("KER_GIRIS","[Calibri,0,12]");
			prop.setProperty("KER_FAT_SATIR", "25");
			prop.setProperty("KER_STOK_KONTROL", "-1");
			prop.setProperty("KER_RAPORLAMA","[Calibri,0,12]");
			prop.setProperty("GRAFIK_DEGER_GOSTER", "-1");
			prop.store(output, "OBS AYARLAR");
			output.close();
			byte[] qaz;
			try {
				qaz = ENCRYPT_DECRYPT_STRING.eNCRYPT_manual("obs");
				String encodedString = Arrays.toString(qaz);
				System.setProperty("obs_password", encodedString);

			} catch (Exception e) {
				e.printStackTrace();
			}
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
	public static boolean dos_kontrol(String dosya)
	{
		File  tmpDir = new File( dosya);
		boolean exists = tmpDir.exists();
		if (exists)
		{   
			return true;
		}
		else
		{
			return false;
		}
	}
	public void dos_sil(String dosya)
	{
		 File myObj = new File(dosya); 
		 myObj.delete() ; 
	}
	public static String char_degis (String degisken)
	{
		return degisken.replace(":","_");
	}
	public static boolean validCheck(String value)
	{
		boolean result ;
        if(value.trim().length() == 0) {
          result = false;
        }
        else {
        	 result = true;
		}
	
		return result ;
	}
	private static void mail_at()
	{
		try {
			String[] to = { "info@okumus.gen.tr" };
			MimeBodyPart messagePart = null ;
			Properties props = System.getProperties();
			props.put("mail.smtp.host", "mail.okumus.gen.tr");
			props.put("mail.smtp.user", "info@okumus.gen.tr");
			props.put("mail.smtp.password","oOk271972");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");

			Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("info@okumus.gen.tr", "oOk271972");
				}
			});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("info@okumus.gen.tr" ,"Java Yukleme"));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}
			for (int i = 0; i < toAddress.length; i++) {
				message.setRecipient(RecipientType.TO,  toAddress[i]);
			}
			messagePart = new MimeBodyPart();
			DatagramSocket socket = new DatagramSocket();
			socket.connect(new InetSocketAddress("google.com", 80));
			messagePart.setText("Ilk Yukleme Java  " +  socket.getInetAddress().getHostAddress(),"UTF-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messagePart);
			message.setSubject("Java Yukleme", "UTF-8");
			message.setContent(multipart);
			message.setSentDate(new Date());
			Transport.send(message);
			message= null;
			session = null;
		}
		catch (Exception ex)
		{
			
		}
	}
}
