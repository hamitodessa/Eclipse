package OBS_C_2025;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class BACKUP_GLOBAL {
	public Connection S_CONN;
	public Connection MY_CONN; //= new MySqlConnection();
	GLOBAL glb = new GLOBAL();
	private Connection con ;
	public void MySql_baglan(String connstr, String user, String pwd) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String cumle = "jdbc:mysql://" + connstr ;
		MY_CONN = DriverManager.getConnection(cumle,user,pwd);

	}
	public void MsSql_baglan(String connstr, String user, String pwd) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String cumle = "jdbc:sqlserver://" + connstr + ";";
		S_CONN = DriverManager.getConnection(cumle,user,pwd);
	}
	public ResultSet db_ismi() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		//F_CCMD.CommandText = "SELECT name FROM master.dbo.sysdatabases where name like 'OK_ % ' "
		String sql  = "SELECT NAME FROM master.dbo.sysdatabases ORDER BY NAME";
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
		String sql= "";

		sql = "INSERT INTO EMIRLER ([EMIR_ISMI],[DURUM],[EMIR_ACIKLAMA],[INSTANCE],[SON_DURUM] ,[SON_YUKLEME] ,[SQL_YEDEK],[MESAJ],[OLUSTURMA]) "
				+ "VALUES (?,?,?,?, ?,?,?,?,?)";

		stmt = con.prepareStatement(sql);
		stmt.setString(1, eismi);
		stmt.setBoolean(2, drm);
		stmt.setString(3, konu);
		stmt.setString(4, inst);
		stmt.setBoolean(5,  sdrm);

		String str = TARIH_CEVIR.milis_ddMMyyyy(syukl.getTime());
		stmt.setString(6, str);
		stmt.setBoolean(7, sqlyed);
		stmt.setString(8,  mesaj);
		str = TARIH_CEVIR.milis_ddMMyyyy(ilkkayit.getTime());
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


		sql  = "INSERT INTO LOG ([EMIR_ISMI],[TARIH],[ACIKLAMA]) "
				+ "VALUES (?,?,?)";
		stmt = con.prepareStatement(sql);
		String str = TARIH_CEVIR.milis_ddMMyyyy(tar.getTime());
		stmt.setString(1, eismi);
		stmt.setString(2, str);

		if (msj.length() > 150)
		{
			msj = msj.substring(0, 150).toString();
		}
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
		String str = TARIH_CEVIR.milis_ddMMyyyy(yuk.getTime());
		stmt.setString(2, str);
		stmt.setString(3, mesaj);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;

	}
	public void instance_update(String eismi,  String ins)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql = "UPDATE EMIRLER SET INSTANCE = @ins WHERE EMIR_ISMI = '" + eismi + "'";
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
		String sql = "";
		sql = "UPDATE EMIRLER SET DURUM = @dur ,MESAJ = @msg WHERE EMIR_ISMI = '" + eismi + "'";
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
		String sql = "";
		sql = "DELETE FROM EMIRLER  WHERE EMIR_ISMI = '" + eismi + "'";
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
		String sql = "";
		sql = "INSERT INTO DB_ISIM ([EMIR_ISMI],[DB_ADI]) "
				+ "VALUES (?,?)";
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
		con = glb.myBackupConnection();
		String sql = "";
		sql = "DELETE FROM DB_ISIM WHERE EMIR_ISMI = '" + eismi + "'";
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
		String sql = "";
		sql = "INSERT INTO DIGER_DOSYA_ISIM ([EMIR_ISMI],[DOSYA_ADI],[DOSYA_PATH]) "
				+ "VALUES (?,?,?)";
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
		String sql = "";
		sql = "DELETE FROM DIGER_DOSYA_ISIM WHERE EMIR_ISMI = '" + eismi + "'";
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
		String sql = "";
		sql = "DELETE FROM FTP  WHERE EMIR_ISMI =  '" + eismi + "'";
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void ftp_ismi_kayit(String eismi, String hst, String kll, String sif, String sur
			, String prt, int zmn, String esy, String neresi, String surucu)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql = "INSERT INTO FTP ([EMIR_ISMI],[HOST],[KULLANICI],[SIFRE],[SURUCU],[PORT],[ZMN_ASIMI],[ESKI_YEDEK],[NERESI],[SURUCU_YER]) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		stmt.setString(1, eismi);
		stmt.setString(2, hst);
		stmt.setString(3, kll);
		stmt.setString(4, sif);
		stmt.setString(5, sur);
		stmt.setString(6, prt);
		stmt.setInt(7, zmn);
		stmt.setString(8, esy);
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
		String sql = "";
		sql = "DELETE FROM BILGILENDIRME  WHERE EMIR_ISMI =  '" + eismi + "'";
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
		String sql = "";
		sql = "INSERT INTO BILGILENDIRME ([EMIR_ISMI],[DURUM],[GONDERILDIGINDE],[HATA_DURUMUNDA],[GON_ISIM],[GON_HESAP],[ALICI],[KONU],[SMTP],[SMTP_PORT],[KULLANICI],[SIFRE],[SSL],[TSL]) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
		stmt.setString(11, sif);
		stmt.setBoolean(12, ssl);
		stmt.setBoolean(13, tsl);
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
		String sql = "";
		sql = "DELETE FROM YEDEKLEME  WHERE EMIR_ISMI =  '" + eismi + "'";
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void yedekleme_ismi_kayit(String eismi, String saa, boolean pt, boolean sa, boolean ca
			, boolean pe, boolean cu, boolean ct, boolean pz, Time bas, Time bit)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql = "INSERT INTO YEDEKLEME ([EMIR_ISMI],[SAAT],[P_TESI],[SALI],[CARS],[PERS],[CUMA],[C_TESI],[PAZAR],[BASLAMA],[BITIS]) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

		stmt.setString(1, eismi);
		stmt.setString(2, saa);
		stmt.setBoolean(3, pt);
		stmt.setBoolean(4, sa);
		stmt.setBoolean(5, ca);
		stmt.setBoolean(6, pe);
		stmt.setBoolean(7, cu);
		stmt.setBoolean(8, ct);
		stmt.setBoolean(9, pz);
		stmt.setTime(10, bas);
		stmt.setTime(11, bit);

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
		String sql = "";
		sql ="DELETE FROM SERVER  WHERE EMIR_ISMI =  '" + eismi + "'";
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
		String sql = "";
		sql ="DELETE FROM LOG  WHERE EMIR_ISMI =  '" + eismi + "'";
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
		String sql = "";
		sql = "DELETE FROM LOG ";
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void server_ismi_kayit(String eismi, String ins, boolean wi, boolean ser, String kull, String sif, String hsql, String port)throws ClassNotFoundException, SQLException
	{

		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql = "INSERT INTO SERVER ([EMIR_ISMI],[INSTANCE],[WIN],[SERV],[KULLANICI],[SIFRE],[HANGI_SQL],[PORT]) "
				+ "VALUES (?,?,?,?,?,?,?,?)";
		stmt.setString(1, eismi);
		stmt.setString(2, ins);
		stmt.setBoolean(3, wi);
		stmt.setBoolean(4, ser);
		stmt.setString(5, kull);
		stmt.setString(6, sif);
		stmt.setString(7, hsql);
		stmt.setString(8, port);
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
		String sql = "";
		sql = "SELECT * FROM EMIRLER WHERE EMIR_ISMI  = '" + emir + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		stmt.close();
		con.close();
		return count  != 0;
	}
	public ResultSet emir_liste(String siralama)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "";
		if (siralama == "DURUM")
		{
			sql = "SELECT * FROM EMIRLER ORDER BY  DURUM DESC , EMIR_ISMI   ";
		}
		else
		{
			sql = "SELECT * FROM EMIRLER ORDER BY   " + siralama + " DESC ";
		}
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss ;
	}
	public ResultSet emir_tek(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql = "SELECT * FROM EMIRLER WHERE EMIR_ISMI ='" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss ;
	}
	public ResultSet db_liste(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql = "SELECT * FROM DB_ISIM WHERE  EMIR_ISMI= '" + eismi + "' ORDER BY DB_ADI ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss ;
	}
	public ResultSet diger_dosya_liste(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql = "SELECT * FROM DIGER_DOSYA_ISIM WHERE  EMIR_ISMI= '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss ;
	}
	public ResultSet emir_bilgi(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql = "SELECT * FROM EMIRLER WHERE  EMIR_ISMI = '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss ;
	}
	public ResultSet ftp_bilgi(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql = "SELECT * FROM FTP WHERE  EMIR_ISMI= '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss ;
	}
	public String ftp_neresi(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql = "SELECT NERESI FROM FTP WHERE  EMIR_ISMI= '" + eismi + "'";

		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		stmt.close();
		con.close();
		if (count  > 0)
		{
			return rss.getString("NERESI");
		}
		else {
			return "";
		}
	}
	public String surucu_bilgi(String eismi, String nerden)throws ClassNotFoundException, SQLException
	{Class.forName("org.sqlite.JDBC");
	if (con != null && ! con.isClosed()) con.close();
	PreparedStatement stmt = null;
	ResultSet	rss = null;
	con = glb.myBackupConnection();
	String sql = "";
	sql =  "SELECT " + nerden + " FROM FTP WHERE  EMIR_ISMI= '" + eismi + "'";

	stmt = con.prepareStatement(sql);
	rss = stmt.executeQuery();
	rss.next();
	int count=0;
	count = rss.getRow();
	stmt.close();
	con.close();
	if (count  > 0)
	{
		return rss.getString(nerden);
	}
	else {
		return "";
	}
	}
	public ResultSet bilgilendirme_bilgi(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql = "SELECT * FROM BILGILENDIRME WHERE  EMIR_ISMI= '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss ;
	}
	public ResultSet yedekleme_bilgi(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql =  "SELECT * FROM YEDEKLEME WHERE  EMIR_ISMI= '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss ;

	}
	public ResultSet server_bilgi(String eismi)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupConnection();
		String sql = "";
		sql =  "SELECT * FROM SERVER WHERE  EMIR_ISMI= '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss ;
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
	public Boolean DoesFtpDirectoryExist(String dirPath, String kull, String sifre) throws SocketException, IOException  
	{
		FTPClient ftp = new FTPClient();
		ftp.connect(dirPath);
		ftp.login(kull, sifre);
		ftp.changeWorkingDirectory(dirPath);
		int    returnCode = ftp.getReplyCode();
		if (ftp != null && ftp.isConnected()) {
			ftp.logout();
			ftp.disconnect();
		}
		if (returnCode == 550) {
			return false;
		}
		return true;
	}
	public void ftp_sil(String ftpp, String ftpsurucu, String dosadi, String kull, String sifre, String port) throws IOException
	{
		FTPClient ftp = new FTPClient();
		ftp.connect(ftpp);
		ftp.login(kull, sifre);
		ftp.changeWorkingDirectory(ftpsurucu);
		ftp.deleteFile(dosadi);
		ftp.logout();
		ftp.disconnect();	

		// txt = ftpp + ":" + port + "/" + ftpsurucu + "/" + dosadi;
	}

	public List<String> ListRmtFiles(String ftpAddress, String surucu,String ftpUser, String ftpPassword) throws SocketException, IOException
	{
		FTPClient ftp = new FTPClient();
		ftp.connect(ftpAddress);
		ftp.login(ftpUser, ftpPassword);
		ftp.changeWorkingDirectory(surucu);
		FTPFile[] files = ftp.listFiles();

		// iterates over the files and prints details for each
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (FTPFile file : files) {
			String details = file.getName();
			if (file.isDirectory()) {
				details = "[" + details + "]";
			}
			details += "\t\t" + file.getSize();
			details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());
			System.out.println(details);
		}

		ftp.logout();
		ftp.disconnect();
		return null;



		//            List<String> returnValue = new List<String>();
		//            WebRequest request = WebRequest.Create(ftpAddress);
		//            request.Method = WebRequestMethods.Ftp.ListDirectory;
		//            request.Credentials = new NetworkCredential(ftpUser, ftpPassword);
		//            using (WebResponse response = request.GetResponse())
		//            {
		//                using (System.IO.StreamReader reader = new System.IO.StreamReader(response.GetResponseStream()))
		//                {
		//                    string responseString = reader.ReadToEnd();
		//                    string[] fileList = responseString.Split(new char[] { '\r', '\n' }, StringSplitOptions.RemoveEmptyEntries);
		//                    foreach (string file in fileList)
		//                    {
		//                        //Console.WriteLine(file);
		//                        returnValue.Add(file);
		//                    }
		//                }
		//                response.Close();
		//            }
		//            return returnValue;
	}
	public ResultSet log_liste()throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myBackupLogConnection();
		String sql = "";
		sql =  "SELECT * FROM LOG ORDER BY TARIH";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss ;
	}
	public List<String> sur_liste(String surucu)
	{
		File directoryPath = new File(surucu);

		String contents[] = directoryPath.list();
		List<String> ListOfFiles = new ArrayList();
		for(int i=0; i<contents.length; i++) {
			ListOfFiles.add(contents[i]);
			System.out.println(contents[i]);
		}
		return ListOfFiles;
	}

	public void zip_yap(String dosadi, String dosyolu, String dosadi_zip, Boolean Sifrele, String Sifre) throws IOException
	{

		String sourceFile = dosyolu + dosadi;
		FileOutputStream fos = new FileOutputStream(dosadi_zip);
		ZipOutputStream zipOut = new ZipOutputStream(fos);

		File fileToZip = new File(sourceFile);
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
		zipOut.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while((length = fis.read(bytes)) >= 0) {
			zipOut.write(bytes, 0, length);
		}

		zipOut.close();
		fis.close();
		fos.close();



	}

	public void backup_al(String dbismi, String dbyer)
	{
		//            DataSet hdts = new DataSet();
		//            using (SqlCommand F_CCMD = new SqlCommand())
		//            {
		//                F_CCMD.CommandText = "BACKUP DATABASE [" + dbismi + "] TO  DISK = N'C:\\OBS_SISTEM\\BACKUP\\" + dbyer + ".bak' " +
		//                                    " WITH NOFORMAT , NOINIT , NAME = N'" + dbismi + "' , SKIP , NOREWIND  ,NOUNLOAD , STATS = 10 ";
		//                F_CCMD.Connection = S_CONN;
		//                SqlDataAdapter F_ADP = new SqlDataAdapter();
		//                F_ADP.SelectCommand = F_CCMD;
		//                hdts.Tables.Clear();
		//                hdts.Clear();
		//                F_ADP.Fill(hdts);
		//            }
	}
	public void mySQL_backup( String emiradi,String dosya, String kull, String pwd, String port,String nereye,String backupadi,String server)
	{
		//   	String savePath = "C:/OBS_SISTEM/" + dbName +".sql";
		//		String executeCmd = myDUMP +"/mysqldump.exe -u" + dbUser + " -p" + dbPass + " -B " + dbName + " -r " + savePath;
	}


}

