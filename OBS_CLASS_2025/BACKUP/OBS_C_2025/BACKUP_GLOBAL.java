package OBS_C_2025;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.InvalidKeyException;
import java.security.KeyStore.TrustedCertificateEntry;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.hsqldb.lib.CountdownInputStream;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.EncryptionMethod;


@SuppressWarnings({"static-access","unused","rawtypes","unchecked","deprecation"})
public class BACKUP_GLOBAL {
	public Connection S_CONN;
	public Connection MY_CONN; //= new MySqlConnection();
	GLOBAL glb = new GLOBAL();
	private Connection con ;
	public void MySql_baglan( String user, String pwd,String port) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String cumle = "jdbc:mysql://localhost:" + port ;
		MY_CONN = DriverManager.getConnection(cumle,user,pwd);
	}
	public boolean MySql_server_test( String user, String pwd, String port) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String porttString="" ;
		Connection con ;
		try
		{
			String cumle = "jdbc:mysql://localhost:" + port ;
			con = DriverManager.getConnection(cumle,user,pwd);
			return true ;
		} 
		catch (SQLException e)
		{  
			return false;
		}  
	}
	public void MsSql_baglan(String inss, String user, String pwd, String port) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String porttString="" ;
		if ( ! port.equals("") )
			porttString= ":" + port;
		try
		{
			String cumle = "";
			cumle = "jdbc:sqlserver://localhost" + porttString  +";instanceName=" + inss + ";";
			S_CONN = DriverManager.getConnection(cumle,user,pwd);
		} 
		catch (SQLException e)
		{  
			S_CONN = null ;
		}  
	}
	public boolean MsSql_server_test(String inss, String user, String pwd, String port) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String porttString="" ;
		Connection con ;
		if ( ! port.equals("") )
			porttString= ":" + port;
		try
		{
			String cumle = "";
			cumle = "jdbc:sqlserver://localhost" + porttString  +";instanceName=" + inss + ";";
			con = DriverManager.getConnection(cumle,user,pwd);
			return true ;
		} 
		catch (SQLException e)
		{  
			return false;
		}  
	}
	public ResultSet db_ismi() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		//F_CCMD.CommandText = "SELECT name FROM master.dbo.sysdatabases where name like 'OK_ % ' "
		String sql  = "SELECT NAME FROM master.dbo.sysdatabases  Where NAME <> 'tempdb' ORDER BY NAME ASC ";
		PreparedStatement stmt = S_CONN.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss ;
	}
	public ResultSet db_ismiMySql()throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Statement stmt = null;
		ResultSet rss = null;
		stmt = MY_CONN.createStatement();
		rss = stmt.executeQuery("SHOW DATABASES;");
		return rss;
	}
	public void genel_kayit(String eismi, boolean drm, String konu, String inst, boolean sdrm, Date syukl, boolean sqlyed, String mesaj, Date ilkkayit)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "INSERT INTO EMIRLER (EMIR_ISMI,DURUM,EMIR_ACIKLAMA,INSTANCE,SON_DURUM ,SON_YUKLEME ,SQL_YEDEK,MESAJ,OLUSTURMA) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, eismi);
		stmt.setBoolean(2, drm);
		stmt.setString(3, konu);
		stmt.setString(4, inst);
		stmt.setBoolean(5,  sdrm);
		String str = TARIH_CEVIR.milis_yyyymmss(syukl.getTime());
		stmt.setString(6, str);
		stmt.setBoolean(7, sqlyed);
		stmt.setString(8,  mesaj);
		str = TARIH_CEVIR.milis_yyyymmss(ilkkayit.getTime());
		stmt.setString(9, str);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void log_kayit(String eismi, Date tar, String msj)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupLogConnection();
		String sql = "";
		sql  = "INSERT INTO LOG (EMIR_ISMI,TARIH,ACIKLAMA) VALUES (?,?,?)";
		stmt = con.prepareStatement(sql);
		String str = TARIH_CEVIR.milis_yyyymmss(tar.getTime());
		stmt.setString(1, eismi);
		stmt.setString(2, str);
		if (msj.length() > 150)
			msj = msj.substring(0, 150).toString();
		stmt.setString(3, msj);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void genel_kayit_durum(String eismi, Boolean drm, Date yuk, String mesaj)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql = "UPDATE EMIRLER SET SON_DURUM = @dur,SON_YUKLEME = @yuk ,MESAJ = @msg WHERE EMIR_ISMI = '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.setBoolean(1, drm);
		String str = TARIH_CEVIR.milis_yyyymmss(yuk.getTime());
		stmt.setString(2, str);
		stmt.setString(3, mesaj);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void pid_kayit(int pid)throws ClassNotFoundException, SQLException, IOException
	{
		File file = new File(glb.SURUCU + glb.BACKUP_PID);
		if (!file.exists())
			file.createNewFile();
		FileWriter myWriter = new FileWriter(glb.SURUCU + glb.BACKUP_PID);
		myWriter.write(String.valueOf(pid));
		myWriter.close();
	}
	public int pid_oku() throws ClassNotFoundException, SQLException, IOException 
	{
		int pidno = 0 ;
		File file = new File(glb.SURUCU + glb.BACKUP_PID); 
		if (file.exists()) 
		{
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				pidno = Integer.valueOf(myReader.nextLine());
			}
			myReader.close();
		}
		return pidno;
	}
	public void ayar_kayit(String dil, String tema,int sifrele,String sifre,int prg_sifrele,int win_start)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "INSERT INTO AYARLAR (DIL,TEMA,SIFRELE,SIFRE,PRG_SIFRELE,WIN_START) VALUES (?,?,?,?,?,?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, dil);
		stmt.setString(2, tema);
		stmt.setInt(3, sifrele);
		String encodedString = "";
		byte[] qaz;
		try {
			qaz = ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(sifre);
			encodedString = Arrays.toString(qaz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		stmt.setString(4, encodedString);
		stmt.setInt(5, prg_sifrele);
		stmt.setInt(6, win_start);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void ayar_sil()throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql = "DELETE FROM AYARLAR ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public String[] ayar_oku() throws ClassNotFoundException, SQLException 
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "SELECT * FROM AYARLAR ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		String[] ayarlar = new String[6];
		while (rss.next())
		{
			ayarlar[0] = rss.getString("DIL");
			ayarlar[1] = rss.getString("TEMA");
			ayarlar[2] = rss.getString("SIFRELE");
			ayarlar[3] = rss.getString("SIFRE");
			ayarlar[4] = rss.getString("PRG_SIFRELE");
			ayarlar[5] = rss.getString("WIN_START");
		}
		stmt.close();
		con.close();
		return ayarlar;
	}
	public void instance_update(String eismi,  String ins)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "UPDATE EMIRLER SET INSTANCE = @ins WHERE EMIR_ISMI = '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, ins);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void durum_kayit_durum(String eismi, Boolean drm, String mesaj)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "UPDATE EMIRLER SET DURUM = @dur ,MESAJ = @msg WHERE EMIR_ISMI = '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.setBoolean(1, drm);
		stmt.setString(2, mesaj);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void genel_kayit_sil(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "DELETE FROM EMIRLER  WHERE EMIR_ISMI = '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void db_ismi_kayit(String eismi, String dad)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "INSERT INTO DB_ISIM (EMIR_ISMI,DB_ADI) VALUES (?,?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, eismi);
		stmt.setString(2, dad);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void db_adi_kayit_sil(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con =  glb.myBackupConnection();
		String sql = "DELETE FROM DB_ISIM WHERE EMIR_ISMI = '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void diger_dosya_ismi_kayit(String eismi, String dad, String dpath)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "INSERT INTO DIGER_DOSYA_ISIM (EMIR_ISMI,DOSYA_ADI,DOSYA_PATH) VALUES (?,?,?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, eismi);
		stmt.setString(2, dad);
		stmt.setString(3, dpath);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void diger_dosya_adi_kayit_sil(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "DELETE FROM DIGER_DOSYA_ISIM WHERE EMIR_ISMI = '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void ftp_kayit_sil(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "DELETE FROM FTP  WHERE EMIR_ISMI =  '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void ftp_ismi_kayit(String eismi, String hst, String kll, String sif, String sur
			, String prt, int zmn, int esy, String neresi, String surucu)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "INSERT INTO FTP (EMIR_ISMI,HOST,KULLANICI,SIFRE,SURUCU,PORT,ZMN_ASIMI,ESKI_YEDEK,NERESI,SURUCU_YER) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, eismi);
		stmt.setString(2, hst);
		stmt.setString(3, kll);
		byte[] qaz = null;
		try {
			qaz = ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(sif);
		} catch (Exception e) {
			e.printStackTrace();
		}
		stmt.setString(4, Arrays.toString(qaz));
		stmt.setString(5, sur);
		stmt.setString(6, prt);
		stmt.setInt(7, zmn);
		stmt.setInt(8, esy);
		stmt.setString(9, neresi);
		stmt.setString(10, surucu);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void bilgilendirme_kayit_sil(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "DELETE FROM BILGILENDIRME  WHERE EMIR_ISMI =  '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void bilgilendirme_ismi_kayit(String eismi, boolean drm, boolean gon, boolean hta, String gismi
			, String ghes, String alic, String konu, String smtp, String smtppo
			, String kull, String sif, boolean ssl, boolean tsl)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "INSERT INTO BILGILENDIRME (EMIR_ISMI,DURUM,GONDERILDIGINDE,HATA_DURUMUNDA,GON_ISIM,GON_HESAP, " + 
				" ALICI,KONU,SMTP,SMTP_PORT,KULLANICI,SIFRE,SSL,TSL) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, eismi);
		stmt.setBoolean(2, drm);
		stmt.setBoolean(3, gon);
		stmt.setBoolean(4, hta);
		stmt.setString(5, gismi);
		stmt.setString(6, ghes);
		stmt.setString(7, alic);
		stmt.setString(8, konu);
		stmt.setString(9, smtp);
		stmt.setString(10, smtppo);
		stmt.setString(11, kull);
		byte[] qaz = null;
		try {
			qaz = ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(sif);
		} catch (Exception e) {
			e.printStackTrace();
		}
		stmt.setString(12, Arrays.toString(qaz));
		stmt.setBoolean(13, ssl);
		stmt.setBoolean(14, tsl);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void yedekleme_kayit_sil(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "DELETE FROM YEDEKLEME  WHERE EMIR_ISMI =  '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void yedekleme_ismi_kayit(String eismi, String saa, boolean pt, boolean sa, boolean ca
			, boolean pe, boolean cu, boolean ct, boolean pz, Date bas, Date bit)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "INSERT INTO YEDEKLEME (EMIR_ISMI,SAAT,P_TESI,SALI,CARS,PERS,CUMA,C_TESI,PAZAR,BASLAMA,BITIS) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, eismi);
		stmt.setString(2, saa);
		stmt.setBoolean(3, pt);
		stmt.setBoolean(4, sa);
		stmt.setBoolean(5, ca);
		stmt.setBoolean(6, pe);
		stmt.setBoolean(7, cu);
		stmt.setBoolean(8, ct);
		stmt.setBoolean(9, pz);
		String str = TARIH_CEVIR.milismmss(bas.getTime());
		stmt.setString(10, str);
		str = TARIH_CEVIR.milismmss(bit.getTime());
		stmt.setString(11, str);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void server_kayit_sil(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql ="DELETE FROM SERVER WHERE EMIR_ISMI ='" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void log_kayit_sil(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupLogConnection();
		String sql ="DELETE FROM LOG  WHERE EMIR_ISMI =  '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void log_kayit_komple_sil()throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupLogConnection();
		String sql = "DELETE FROM LOG ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void server_ismi_kayit(String eismi, String ins, boolean wi, boolean ser, String kull, String sif, String hsql, String port,String mydump)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "INSERT INTO SERVER (EMIR_ISMI,INSTANCE,WIN,SERV,KULLANICI,SIFRE,HANGI_SQL,PORT,MY_DUMP) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, eismi);
		stmt.setString(2, ins);
		stmt.setBoolean(3, wi);
		stmt.setBoolean(4, ser);
		stmt.setString(5, kull);
		byte[] qaz = null;
		try {
			qaz = ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(sif);
		} catch (Exception e) {
			e.printStackTrace();
		}
		stmt.setString(6, Arrays.toString(qaz));
		stmt.setString(7, hsql);
		stmt.setString(8, port);
		stmt.setString(9, mydump );
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public Boolean emir_kontrol(String emir)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "SELECT * FROM EMIRLER WHERE EMIR_ISMI = '" + emir + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		stmt.close();
		con.close();
		return count  != 0;
	}
	public List<emir_bilgiler> emir_liste(String siralama)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "";
		if (siralama == "DURUM")
		{
			sql = "SELECT * FROM EMIRLER ORDER BY  DURUM DESC , EMIR_ISMI COLLATE NOCASE ASC  ";
		}
		else
		{
			sql = "SELECT * FROM EMIRLER ORDER BY   " + siralama + " COLLATE NOCASE ASC ";
		}
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		List<emir_bilgiler> emirBilgi = new ArrayList<emir_bilgiler>();
		int i = 0 ;
		while (rss.next())
		{     
			try 
			{
				Date sonyuk =	new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rss.getString("SON_YUKLEME"));
				Date olus =	new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rss.getString("OLUSTURMA"));
				emir_bilgiler liste  = new emir_bilgiler(
						rss.getString("EMIR_ISMI") ,
						rss.getInt("DURUM") == 0 ? false:true,
						rss.getString("EMIR_ACIKLAMA"),
						rss.getString("INSTANCE"),
						rss.getInt("SON_DURUM")== 0 ? false:true,
						sonyuk, 
						rss.getInt("SQL_YEDEK")== 0 ? false:true,
						rss.getString("MESAJ"),
						olus);
				emirBilgi.add(liste);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		stmt.close();
		con.close();
		return emirBilgi ;
	}
	public List<emir_bilgiler> emir_liste_download()throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "SELECT * FROM EMIRLER , FTP "
				 	+ " WHERE FTP.EMIR_ISMI = EMIRLER.EMIR_ISMI AND FTP.NERESI = 'FTP' " 
				 	+ " ORDER BY EMIRLER.EMIR_ISMI COLLATE NOCASE ASC ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		List<emir_bilgiler> emirBilgi = new ArrayList<emir_bilgiler>();
		int i = 0 ;
		while (rss.next())
		{     
			try 
			{
				Date sonyuk =	new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rss.getString("SON_YUKLEME"));
				Date olus =	new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rss.getString("OLUSTURMA"));
				emir_bilgiler liste  = new emir_bilgiler(
						rss.getString("EMIR_ISMI") ,
						rss.getInt("DURUM") == 0 ? false:true,
						rss.getString("EMIR_ACIKLAMA"),
						rss.getString("INSTANCE"),
						rss.getInt("SON_DURUM")== 0 ? false:true,
						sonyuk, 
						rss.getInt("SQL_YEDEK")== 0 ? false:true,
						rss.getString("MESAJ"),
						olus);
				emirBilgi.add(liste);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		stmt.close();
		con.close();
		return emirBilgi ;
	}
	public List<emir_bilgiler> emir_tek(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		con = glb.myBackupConnection();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "SELECT * FROM EMIRLER WHERE EMIR_ISMI ='" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		List<emir_bilgiler> emirBilgi = new ArrayList<emir_bilgiler>();
		int i = 0 ;
		while (rss.next())
		{     
			try {
				Date sonyuk = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rss.getString("SON_YUKLEME"));
				Date olus = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rss.getString("OLUSTURMA"));
				emir_bilgiler liste  = new emir_bilgiler(
						rss.getString("EMIR_ISMI") ,
						rss.getInt("DURUM") == 0 ? false:true,
						rss.getString("EMIR_ACIKLAMA"),
						rss.getString("INSTANCE"),
						rss.getInt("SON_DURUM") == 0 ? false:true,
						sonyuk, 
						rss.getInt("SQL_YEDEK")== 0 ? false:true,
						rss.getString("MESAJ"),
						olus);
				emirBilgi.add(liste);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		stmt.close();
		con.close();
		return emirBilgi ;
	}
	public List<String> db_liste(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "SELECT * FROM DB_ISIM WHERE  EMIR_ISMI= '" + eismi + "' ORDER BY DB_ADI COLLATE NOCASE ASC ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		List<String>  dbadi =new ArrayList<String>();
		while (rss.next())
		{              
			dbadi.add(rss.getString("DB_ADI").toString());
		}
		stmt.close();
		con.close();
		return dbadi ;
	}
	public List<db_List> diger_dosya_liste(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "SELECT * FROM DIGER_DOSYA_ISIM WHERE  EMIR_ISMI= '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		List<db_List> dbliste = new ArrayList<db_List>();
		int i = 0 ;
		while (rss.next())
		{     
			db_List liste  = new db_List(rss.getString("DOSYA_ADI") ,rss.getString("DOSYA_PATH"));
			dbliste.add(liste);
		}
		stmt.close();
		con.close();
		return dbliste ;
	}
	public List<ftp_bilgiler> ftp_bilgi(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "SELECT * FROM FTP WHERE  EMIR_ISMI= '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		List<ftp_bilgiler> ftpBilgi = new ArrayList<ftp_bilgiler>();
		int i = 0 ;
		while (rss.next())
		{     
			ftp_bilgiler liste  = new ftp_bilgiler(
					rss.getString("EMIR_ISMI") ,
					rss.getString("NERESI"),
					rss.getString("HOST"),
					rss.getString("KULLANICI"),
					rss.getString("SIFRE"),
					rss.getString("SURUCU"),
					rss.getString("PORT"),
					rss.getString("ZMN_ASIMI"),
					rss.getString("ESKI_YEDEK"),
					rss.getString("SURUCU_YER"));
			ftpBilgi.add(liste);
		}
		stmt.close();
		con.close();
		return ftpBilgi ;
	}
	public String ftp_neresi(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "SELECT NERESI FROM FTP WHERE  EMIR_ISMI= '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		String sonucString = "" ;
		if (count  > 0)
			sonucString =  rss.getString("NERESI");
		stmt.close();
		con.close();
		return sonucString;
	}
	public String backup_sifre_oku() throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "SELECT SIFRE FROM YONETICI ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		String sonucString = "" ;
		String decodedString =  rss.getString("SIFRE");
		String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
		byte[] bytes = new byte[byteValues.length];
		for (int i=0, len=bytes.length; i<len; i++) {
			bytes[i] = Byte.parseByte(byteValues[i].trim());     
		}
		sonucString = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes);
		stmt.close();
		con.close();
		return sonucString;
	}
	public String surucu_bilgi(String eismi, String nerden)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql =  "SELECT " + nerden + " FROM FTP WHERE  EMIR_ISMI= '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		String qweString = "" ;
		if (count  > 0)
			qweString = rss.getString(nerden);
		stmt.close();
		con.close();
		return qweString ;
	}
	public List<bilgilendirme_bilgiler> bilgilendirme_bilgi(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "SELECT * FROM BILGILENDIRME WHERE  EMIR_ISMI= '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		List<bilgilendirme_bilgiler> bilgiBilgi = new ArrayList<bilgilendirme_bilgiler>();
		int i = 0 ;
		while (rss.next())
		{     
			bilgilendirme_bilgiler liste  = new bilgilendirme_bilgiler(rss.getString("EMIR_ISMI") ,
					rss.getInt("DURUM") == 0 ? false:true,
							rss.getInt("GONDERILDIGINDE") == 0 ? false:true,
							rss.getInt("HATA_DURUMUNDA") == 0 ? false:true ,
							rss.getString("GON_ISIM"),
							rss.getString("GON_HESAP"),
							rss.getString("ALICI"),
							rss.getString("KONU"),
							rss.getString("SMTP"),
							rss.getString("SMTP_PORT"),
							rss.getString("KULLANICI"),
							rss.getString("SIFRE"),
							rss.getInt("SSL") == 0 ? false:true ,
							rss.getInt("TSL") == 0 ? false:true );
			bilgiBilgi.add(liste);
		}
		stmt.close();
		con.close();
		return bilgiBilgi ;
	}
	public List<yedekleme_bilgiler> yedekleme_bilgi(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql =  "SELECT * FROM YEDEKLEME WHERE  EMIR_ISMI= '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		List<yedekleme_bilgiler> bilgiBilgi = new ArrayList<yedekleme_bilgiler>();
		int i = 0 ;
		while (rss.next())
		{     
			try {
				Date bas =	new SimpleDateFormat("HH:mm").parse(rss.getString("BASLAMA"));
				Date bit= new SimpleDateFormat("HH:mm").parse(rss.getString("BITIS"));
				yedekleme_bilgiler liste  = new yedekleme_bilgiler(
						rss.getString("EMIR_ISMI") ,
						rss.getString("SAAT"),
						rss.getInt("P_TESI") == 0 ? false:true,
						rss.getInt("SALI") == 0 ? false:true,
						rss.getInt("CARS") == 0 ? false:true ,
						rss.getInt("PERS") == 0 ? false:true ,
						rss.getInt("CUMA") == 0 ? false:true ,
						rss.getInt("C_TESI") == 0 ? false:true ,
						rss.getInt("PAZAR") == 0 ? false:true ,
						bas,
						bit);
				bilgiBilgi.add(liste);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		stmt.close();
		con.close();
		return bilgiBilgi ;
	}
	public void sifre_degis(String sifre) throws SQLException, ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		if (con != null && ! con.isClosed()) con.close();
		con = glb.myBackupConnection();
		String sql ="UPDATE  YONETICI  SET SIFRE=? ";
		stmt = con.prepareStatement(sql);
		byte[] qaz;
		qaz = ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(sifre);
		stmt.setString(1, Arrays.toString(qaz));
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public List<server_bilgiler> server_bilgi(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql =  "SELECT * FROM SERVER WHERE  EMIR_ISMI= '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		List<server_bilgiler> bilgiBilgi = new ArrayList<server_bilgiler>();
		while (rss.next())
		{     
			server_bilgiler liste  = new server_bilgiler(
					rss.getString("EMIR_ISMI") ,
					rss.getString("HANGI_SQL"),
					rss.getString("INSTANCE"),
					rss.getInt("WIN") == 0 ? false:true,
					rss.getInt("SERV") == 0 ? false:true,
					rss.getString("KULLANICI"),
					rss.getString("SIFRE"),
					rss.getString("PORT"),
					rss.getString("MY_DUMP"));
			bilgiBilgi.add(liste);
		}
		stmt.close();
		con.close();
		return bilgiBilgi ;
	}
	public String default_path()throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql  = "Select InstanceDefaultDataPath = serverproperty('InstanceDefaultDataPath')  ";
		PreparedStatement stmt = S_CONN.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		return rss.getString("InstanceDefaultDataPath");
	}
	public Boolean DoesFtpDirectoryExist(String ftpp ,String dirPath,int port ,String kull, String sifre) throws SocketException, IOException  
	{
		FTPClient ftp = new FTPClient();
		ftp.connect(ftpp, port);
		ftp.login(kull, sifre);
		ftp.changeWorkingDirectory(dirPath);
		int returnCode = ftp.getReplyCode();
		if (ftp != null && ftp.isConnected()) {
			ftp.logout();
			ftp.disconnect();
		}
		boolean result = false;
		if (returnCode == 530) {
			result = false;
		}
		else if (returnCode == 250) {
			result= true;
		}
		return result;
	}
	public Boolean DoesFtpExist(String ftpp ,int port ,String kull, String sifre) throws SocketException, IOException  
	{
		FTPClient ftp = new FTPClient();
		ftp.connect(ftpp, port);
		ftp.login(kull, sifre);
		int returnCode = ftp.getReplyCode();
		boolean result = false;
		if (ftp != null && ftp.isConnected()) {
			ftp.logout();
			ftp.disconnect();
		}
		if (returnCode == 530) {
			result = false;
		}
		else if (returnCode == 230) {
			result= true;
		}
		return result;
	}
	public void ftp_sil(String ftpp, String ftpsurucu, String dosadi, String kull, String sifre, int port) throws IOException
	{
		FTPClient ftp = new FTPClient();
		ftp.connect(ftpp, port);
		ftp.login(kull, sifre);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.enterLocalPassiveMode();
		ftp.changeWorkingDirectory(ftpsurucu);
		ftp.deleteFile(dosadi);
		ftp.logout();
		ftp.disconnect();	
	}
	public List<remote_filelist> ListRmtFiles(String ftpAddress, String surucu,String ftpUser, String ftpPassword,int port) throws SocketException, IOException
	{
		FTPClient ftp = new FTPClient();
		ftp.connect(ftpAddress, port);
		ftp.login(ftpUser, ftpPassword);
		ftp.enterLocalPassiveMode();
		ftp.changeWorkingDirectory(surucu);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		FTPFile[] files = ftp.listFiles();
		List<remote_filelist> filelists= new ArrayList<remote_filelist>();
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (FTPFile file : files)
		{
			if(file.getName().toString().equals(".") || file.getName().toString().equals(".."))
			{
			}
			else 
			{
				if (! file.isDirectory()) 
				{
					remote_filelist	filebilgi = new remote_filelist(file.getName().toString(),(int) file.getSize(), dateFormater.format(file.getTimestamp().getTime()),"");
					filelists.add(filebilgi);
				}
			}
		}
		ftp.logout();
		ftp.disconnect();
		return filelists;
	}
	public DefaultTableModel emir_liste() throws SQLException, ClassNotFoundException, ParseException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "SELECT * FROM EMIRLER ORDER BY   EMIR_ISMI COLLATE NOCASE ASC  ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("EMIR_ISMI");
		model.addColumn("DURUM");
		model.addColumn("MESAJ");
		model.addColumn("INSTANCE");
		model.addColumn("SON_YUKLEME");
		int i = 0 ;
		while (rss.next())
		{     
			Vector data = new Vector();
			SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
			Date sonyuk =	new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rss.getString("SON_YUKLEME"));
			data.add(rss.getString("EMIR_ISMI") );
			data.add(rss.getInt("DURUM") == 0 ? Boolean.FALSE:Boolean.TRUE);
			data.add(rss.getString("MESAJ"));
			data.add(rss.getString("INSTANCE"));
			data.add(form.format(sonyuk));
			model.addRow(data);
		}
		stmt.close();
		con.close();
		return model ;
	}
	public DefaultTableModel log_liste(String eismi)throws ClassNotFoundException, SQLException, ParseException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupLogConnection();
		String where = "" ;
		if(! eismi.equals("Hepsi"))
			where = " WHERE EMIR_ISMI = '" + eismi + "'";
		String sql =  "SELECT TARIH,ACIKLAMA,EMIR_ISMI FROM LOG " 
				+ where
				+ " ORDER BY TARIH COLLATE NOCASE ASC";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		DefaultTableModel model =new DefaultTableModel(new String[] {"TARIH", "ACIKLAMA", "EMIR_ISMI"},0);
		while(rss.next())
		{
			Vector<String> data = new Vector<String>();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
			SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
			Date date = formatter.parse(rss.getString("TARIH"));
			data.add( form.format(date));
			data.add( rss.getString("ACIKLAMA"));
			data.add( rss.getString("EMIR_ISMI"));
			model.addRow(data);
		}
		stmt.close();
		con.close();
		return model ;
	}
	public ArrayList<String> log_isim() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupLogConnection();
		String sql =  "SELECT DISTINCT EMIR_ISMI FROM LOG ORDER BY EMIR_ISMI COLLATE NOCASE ASC";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		ArrayList<String> listeStrings = new ArrayList<String>();
		while(rss.next())
		{
			listeStrings.add(rss.getString("EMIR_ISMI"));
		}
		stmt.close();
		con.close();
		return listeStrings;
	}
	public DefaultTableModel file_liste(String ftpAddress, String surucu,String ftpUser, String ftpPassword,int port)throws ClassNotFoundException, SQLException, ParseException, SocketException, IOException
	{
		FTPClient ftp = new FTPClient();
		ftp.connect(ftpAddress, port);
		ftp.login(ftpUser, ftpPassword);
		ftp.enterLocalPassiveMode();
		ftp.changeWorkingDirectory(surucu);
		FTPFile[] files = ftp.listFiles();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("SEC");
		model.addColumn("DOSYA_ADI");
		model.addColumn("BOYUT Bytes");
		model.addColumn("TARIH");
		SimpleDateFormat dateFormater = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		for (FTPFile file : files) {
			if( file.getName().toString().equals(".") ||  file.getName().toString().equals(".."))
			{
			}
			else {
				if (! file.isDirectory()) 
				{
					Vector data = new Vector();
					data.add( Boolean.FALSE );
					data.add(file.getName().toString());
					data.add((int) file.getSize());
					data.add(dateFormater.format(file.getTimestamp().getTime()));
					model.addRow(data);
				}
			}
		}
		ftp.logout();
		ftp.disconnect();
		return model ;
	}
	public List<remote_filelist> sur_liste(String surucu)
	{
		List<remote_filelist> filelists= new ArrayList<remote_filelist>();
		File directoryPath = new File(surucu);
		String contents[] = directoryPath.list();
		for(int i=0; i<contents.length; i++) {
			remote_filelist 	ListOfFiles = new remote_filelist(contents[i].toString(),0,"",surucu);
			filelists.add(ListOfFiles);
		}
		return filelists;
	}
	public void zip_yap_sifrele(String dosadi, String dosyolu, String dosadi_zip, Boolean sifrele, String sifre) 
	{
		ZipParameters zipParameters = new ZipParameters();
		zipParameters.setCompressionLevel(CompressionLevel.HIGHER);
		ZipFile zipFile ;
		if(sifrele)
		{
			zipParameters.setEncryptFiles(true);
			zipParameters.setEncryptionMethod(EncryptionMethod.AES);
			zipFile = new ZipFile(dosyolu + dosadi_zip, sifre.toCharArray());
		}
		else {
			zipFile = new ZipFile(dosyolu + dosadi_zip);
		}
		try {
			zipFile.addFile(new File(dosyolu + dosadi), zipParameters);
			zipFile.close();
		} catch (Exception ex) 
		{
			try 
			{
				log_kayit("System", new Date(), ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void diger_zip_yap_sifrele(String okumadosyaadii, String dosyolu, String dosadi_zip, Boolean sifrele, String sifre) 
	{
		ZipParameters zipParameters = new ZipParameters();
		zipParameters.setCompressionLevel(CompressionLevel.HIGHER);
		ZipFile zipFile ;
		if(sifrele)
		{
		zipParameters.setEncryptFiles(true);
		zipParameters.setEncryptionMethod(EncryptionMethod.AES);
		zipFile = new ZipFile(dosyolu + dosadi_zip, sifre.toCharArray());
		}
		else 
		{
			zipFile = new ZipFile(dosyolu + dosadi_zip);
		}
		try {
			zipFile.addFile(new File(okumadosyaadii), zipParameters);
			zipFile.close();
		} catch (Exception ex) {
			try {
				log_kayit("System", new Date(), ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void zip_folder_sifrele(Path sourceFolderPath, Path zipPath, Boolean sifrele, String sifre) 
	{
		ZipParameters zipParameters = new ZipParameters();
		zipParameters.setCompressionLevel(CompressionLevel.HIGHER);
		ZipFile zipFile ;
		if(sifrele)
		{
			zipParameters.setEncryptFiles(true);
			zipParameters.setEncryptionMethod(EncryptionMethod.AES);
			zipFile = new ZipFile(zipPath.toString(), sifre.toCharArray());
		}
		else {
			zipFile = new ZipFile(zipPath.toString());
		}
		try {
			zipFile.addFolder(new File(sourceFolderPath.toString()), zipParameters);
			zipFile.close();
		} catch (Exception ex) {
			try {
				log_kayit("System", new Date(), ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void backup_al(String dbismi, String dbyer) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "BACKUP DATABASE [" + dbismi + "] TO DISK = N'" + glb.BACKUP_YERI +  dbyer + ".bak' WITH NAME = N'" + dbismi + "'";
		Statement stmt ;
		stmt = S_CONN.createStatement();  
		stmt.execute(sql);  
		stmt.close();
	}
	public void mySQL_backup( String myDUMP, String dbUser, String dbPass, String dbName,String savePath) throws IOException, InterruptedException
	{
		String executeCmd = myDUMP +"\\mysqldump.exe -u" + dbUser + " -p" + dbPass + " -B " + dbName + " -r " + savePath;
		Process runtimeProcess;
		Runtime runtime = Runtime.getRuntime();
		runtimeProcess = runtime.exec(executeCmd,null);
		int processComplete = runtimeProcess.waitFor();
		//		System.out.println(processComplete); //0 completed
	}
	public void kopyala(String eski, String yeni) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		Statement stmt = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql =  "INSERT INTO EMIRLER  (EMIR_ISMI, DURUM,EMIR_ACIKLAMA,INSTANCE,SON_DURUM,SON_YUKLEME,SQL_YEDEK,MESAJ,OLUSTURMA)" 
				+ "SELECT '" + yeni + "', DURUM,EMIR_ACIKLAMA,INSTANCE,SON_DURUM,SON_YUKLEME,SQL_YEDEK,MESAJ,OLUSTURMA FROM EMIRLER  " 
				+ "WHERE EMIR_ISMI = '" + eski + "' " ;
		stmt = con.createStatement();  
		stmt.execute(sql);  
		// '**************** YEDEKLEME
		sql =  "INSERT INTO YEDEKLEME  (EMIR_ISMI, SAAT ,P_TESI ,SALI  ,CARS ,PERS ,CUMA ,C_TESI ,PAZAR ,BASLAMA ,BITIS )" 
				+ " SELECT '" + yeni + "',  SAAT ,P_TESI ,SALI  ,CARS ,PERS ,CUMA ,C_TESI ,PAZAR ,BASLAMA ,BITIS  FROM YEDEKLEME  " 
				+ " WHERE EMIR_ISMI = '" + eski + "' " ;
		stmt = con.createStatement();  
		stmt.execute(sql);  
		//'**************** FTP
		sql =  "INSERT INTO FTP  (EMIR_ISMI, NERESI ,HOST  ,KULLANICI ,SIFRE ,SURUCU ,PORT ,ZMN_ASIMI ,ESKI_YEDEK,SURUCU_YER )" 
				+ " SELECT '" + yeni + "',  NERESI ,HOST  ,KULLANICI ,SIFRE ,SURUCU ,PORT ,ZMN_ASIMI ,ESKI_YEDEK,SURUCU_YER  FROM FTP  " 
				+ " WHERE EMIR_ISMI = '" + eski + "' " ;
		stmt = con.createStatement();  
		stmt.execute(sql);  
		//'**************** BILGILENDIRME
		sql =  "INSERT INTO BILGILENDIRME  (EMIR_ISMI, DURUM ,GONDERILDIGINDE ,HATA_DURUMUNDA ,GON_ISIM,GON_HESAP , ALICI , KONU , SMTP, SMTP_PORT, KULLANICI , SIFRE, SSL,TSL )" 
				+ " SELECT '" + yeni + "',  DURUM ,GONDERILDIGINDE ,HATA_DURUMUNDA ,GON_ISIM,GON_HESAP , ALICI , KONU , SMTP, SMTP_PORT, KULLANICI , SIFRE, SSL,TSL  FROM BILGILENDIRME  " 
				+ " WHERE EMIR_ISMI = '" + eski + "' " ;
		stmt = con.createStatement();  
		stmt.execute(sql);  
		//'**************** SERVER
		sql =  "INSERT INTO SERVER  (EMIR_ISMI,HANGI_SQL ,INSTANCE,WIN ,SERV ,KULLANICI,SIFRE , PORT ,MY_DUMP  )" 
				+ " SELECT '" + yeni + "', HANGI_SQL ,INSTANCE,WIN ,SERV ,KULLANICI,SIFRE , PORT ,MY_DUMP   FROM SERVER  " 
				+ " WHERE EMIR_ISMI = '" + eski + "' " ;
		stmt = con.createStatement();  
		stmt.execute(sql);  
		//'**************** DB ISIM
		sql =  "INSERT INTO DB_ISIM  (EMIR_ISMI,DB_ADI  )" 
				+ " SELECT '" + yeni + "', DB_ADI   FROM DB_ISIM  " 
				+ " WHERE EMIR_ISMI = '" + eski + "' " ;
		stmt = con.createStatement();  
		stmt.execute(sql);  
		//'**************** DIGER DOSYA ISIM
		sql =  "INSERT INTO DIGER_DOSYA_ISIM  (EMIR_ISMI,DOSYA_ADI ,DOSYA_PATH   )" 
				+ " SELECT '" + yeni + "', DOSYA_ADI ,DOSYA_PATH   FROM DIGER_DOSYA_ISIM  " 
				+ " WHERE EMIR_ISMI = '" + eski + "' " ;
		stmt = con.createStatement();  
		stmt.execute(sql);  
		stmt.close();
		con.close();
	}
}

//public  void zipFolder(Path sourceFolderPath, Path zipPath) throws Exception {
//ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()));
//Files.walkFileTree(sourceFolderPath, new SimpleFileVisitor<Path>() 
//{
//	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//		zos.putNextEntry(new ZipEntry(sourceFolderPath.relativize(file).toString()));
//		Files.copy(file, zos);
//		zos.closeEntry();
//		return FileVisitResult.CONTINUE;
//	}
//});
//zos.close();
//}


//public void diger_zip_yap(String okumadosyaadii, String dosyolu, String dosadi_zip) throws IOException
//{
//	String sourceFile = okumadosyaadii;
//	FileOutputStream fos = new FileOutputStream(dosyolu + dosadi_zip);
//	ZipOutputStream zipOut = new ZipOutputStream(fos);
//	File fileToZip = new File(sourceFile);
//	FileInputStream fis = new FileInputStream(fileToZip);
//	ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
//	zipOut.putNextEntry(zipEntry);
//	byte[] bytes = new byte[1024];
//	int length;
//	while((length = fis.read(bytes)) >= 0) {
//		zipOut.write(bytes, 0, length);
//	}
//	zipOut.close();
//	fis.close();
//	fos.close();
//}

//public void zip_yap(String dosadi, String dosyolu, String dosadi_zip) throws IOException
//{
//	String sourceFile = dosyolu + dosadi;
//	FileOutputStream fos = new FileOutputStream(dosyolu +dosadi_zip);
//	ZipOutputStream zipOut = new ZipOutputStream(fos);
//	File fileToZip = new File(sourceFile);
//	FileInputStream fis = new FileInputStream(fileToZip);
//	ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
//	zipOut.putNextEntry(zipEntry);
//	byte[] bytes = new byte[1024];
//	int length;
//	while((length = fis.read(bytes)) >= 0) {
//		zipOut.write(bytes, 0, length);
//	}
//	zipOut.close();
//	fis.close();
//	fos.close();
//}


