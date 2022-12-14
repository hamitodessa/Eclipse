package OBS_PACKAGE;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ADRES_MSSQL implements IADRES {

	static Connection con = null;
	static Statement stmt = null;
	
	public void baglan() throws SQLException
	{
		String cumle = "jdbc:sqlserver://" + CONNECTION.adrdizinbilgi.conn_str + ";";
	    con = DriverManager.getConnection(cumle,CONNECTION.adrdizinbilgi.kullanici,CONNECTION.adrdizinbilgi.sifresi);
	}
	@Override
	public void adr_sifirdan_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String cumle = "";
		cumle = "jdbc:sqlserver://localhost;instanceName=" + ins + ";";
        con = DriverManager.getConnection(cumle,kull,sifre);
        String VERITABANI = "OK_Adr" + kod;
        stmt = null;
        String sql =null;
        if (dizin_yeri == "default")
        	sql = "CREATE DATABASE [" + VERITABANI + "]";
        else
        	sql = "CREATE DATABASE [" + VERITABANI + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "', FILENAME = N'" + dizin 	+ "\\" + VERITABANI + ".mdf  ) " + " LOG ON " + " ( NAME = N'" + VERITABANI + "_log', FILENAME = N'" + dizin + "\\" + VERITABANI + "_log.ldf' ) ";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        cumle = "jdbc:sqlserver://localhost;instanceName=" + ins + ";database=" + VERITABANI + ";";
        con = DriverManager.getConnection(cumle,kull,sifre);
        create_table(fir_adi);
        stmt.close();
        con.close();
		
	}
	@Override
	public void adr_sifirdan_S(String server, String ins, String kull, String sifre, String kod, String fir_adi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		 String VERITABANI = "OK_Adr" + kod;
		String cumle = "";
		stmt = null;
        String sql =null;
		cumle = "jdbc:sqlserver://" + server + ";instanceName=" + ins + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
           sql = "CREATE DATABASE [" + VERITABANI + "]";
           stmt = con.createStatement();  
           stmt.executeUpdate(sql);
           cumle = "jdbc:sqlserver://" + server + ";instanceName=" + ins + ";databaseName=" + VERITABANI + ";";
           con = DriverManager.getConnection(cumle,kull,sifre);
           create_table(fir_adi);
           stmt.close();
           con.close();
		
	}
	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql = null;
        sql = "CREATE TABLE [dbo].[Adres]( "
        			  + " [ID] [int] IDENTITY(1,1) NOT NULL ,"
                      + " [M_Kodu] [nvarchar](12)  NULL, "
                      + " [Adi] [nvarchar](35) NULL,"
                      + " [Adres_1] [nvarchar](35) NULL,"
                      + " [Adres_2] [nvarchar](35) NULL,"
                      + " [Semt] [nvarchar](25) NULL,"
                      + " [Sehir] [nvarchar](25) NULL,"
                      + " [Posta_Kodu] [nvarchar](10) NULL,"
                      + " [Vergi_Dairesi] [nvarchar](25) NULL,"
                      + " [Vergi_No] [nvarchar](15) NULL,"
                      + " [Fax] [nvarchar](25) NULL,"
                      + " [Tel_1] [nvarchar](25) NULL,"
                      + " [Tel_2] [nvarchar](25) NULL,"
                      + " [Tel_3] [nvarchar](25) NULL,"
                      + " [Ozel] [nvarchar](30) NULL,"
                      + " [Yetkili] [nvarchar](30) NULL,"
                      + " [E_Mail] [nvarchar](50) NULL,"
                      + " [Not_1] [nvarchar](30) NULL,"
                      + " [Not_2] [nvarchar](30) NULL,"
                      + " [Not_3] [nvarchar](30) NULL,"
                      + " [Aciklama] [nvarchar](50) NULL,"
                      + " [Sms_Gonder] [bit] NULL,"
                      + " [Mail_Gonder] [bit] NULL,"
                      + " [Ozel_Kod_1] [nvarchar](15) NULL,"
                      + " [Ozel_Kod_2] [nvarchar](15) NULL,"
                      + " [Web] [nvarchar](50) NULL,"
                      + " [USER] [nvarchar](15) NULL,"
                      + " [Resim] [image] NULL,"
                      + " CONSTRAINT [PKeyID] PRIMARY KEY CLUSTERED (	[ID] ASC "
                      + " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, "
                      + " ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE OZEL(OZID int identity(1,1) CONSTRAINT PKeyOZID PRIMARY KEY,YONETICI nvarchar(25), YON_SIFRE nvarchar(15) , FIRMA_ADI nvarchar(50))";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE YETKILER(YETID int identity(1,1) CONSTRAINT PKeyYETID PRIMARY KEY,KULLANICI nvarchar(25), HESAP nvarchar(12), TAM_YETKI bit, GORUNTU bit )";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        // ***************OZEL NO YAZ ************
        sql = "INSERT INTO  OZEL(YONETICI,YON_SIFRE,FIRMA_ADI) VALUES ('" + GLOBAL.KULL_ADI   + "','12345' , '" + fir_adi + "')";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
		
	}
	@Override
	public String adr_firma_adi() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
   	    PreparedStatement stmt = con.prepareStatement("SELECT *  FROM OZEL ");
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		String result;
		if (count  != 0) 
		{
			result = rss.getString("FIRMA_ADI");
		}
		else
		{
			result = "";
		}
		return result;	
	}
	public void adr_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        String sql = "UPDATE OZEL SET FIRMA_ADI = N'" + fadi + "'";
        PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public ResultSet adres(String sira,String arama) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
        String sql = " SELECT M_Kodu,Adi,Adres_1,Adres_2,Semt,Sehir,Vergi_Dairesi ," +
                " Vergi_No, Fax,Tel_1,Tel_2,Tel_3,Ozel,Yetkili,E_Mail,Not_1,Not_2,Not_3 ,Aciklama,Sms_Gonder,Mail_Gonder,Ozel_Kod_1,Ozel_Kod_2" +
                " ,Web ,Posta_Kodu ,Resim" +
                " FROM Adres " +
                  arama +
                " ORDER by " + sira;
        Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	public String kod_ismi(String kodu) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
   	    PreparedStatement stmt = con.prepareStatement("SELECT Adi  FROM Adres WHERE M_Kodu =N'" + kodu + "'");
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		String result="";
		if (count  != 0) 
		{
			result = rss.getString("Adi");
		}
		else
		{
			result = "";
		}
		return result;	
	}
	public void adres_kayit(String kodu ,String adi ,String adr1 ,String adr2 ,String semt,String sehir  , String vd , String vn ,
			String fax, String tel1 ,String tel2 ,String ozel ,String yet ,String e_ma,String n1 ,String n2 ,String n3 ,  InputStream  resim   
			,String tel3 ,String acik   ,boolean sms  ,boolean mailg,String ok1 ,String ok2,String web ,String pkodu ,String usr) throws ClassNotFoundException, SQLException, IOException

	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO Adres (M_Kodu,Adi,Adres_1,Adres_2,Semt,Sehir,Posta_Kodu,Vergi_Dairesi,Vergi_No,Fax,Tel_1" +
		        " ,Tel_2,Tel_3,Ozel,Yetkili,E_Mail,Not_1,Not_2,Not_3,Aciklama,Sms_Gonder,Mail_Gonder,Ozel_Kod_1,Ozel_Kod_2,Web,[USER],Resim) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, kodu);
		stmt.setString(2,adi);
		stmt.setString(3, adr1);
		stmt.setString(4, adr2);
		stmt.setString(5, semt);
		stmt.setString(6, sehir);
		stmt.setString(7, pkodu);
		stmt.setString(8, vd);
		stmt.setString(9, vn);
		stmt.setString(10, fax);
		stmt.setString(11, tel1);
		stmt.setString(12, tel2);
		stmt.setString(13, tel3);
		stmt.setString(14, ozel);
		stmt.setString(15, yet);
		stmt.setString(16, e_ma);
		stmt.setString(17, n1);
		stmt.setString(18, n2);
		stmt.setString(19, n3);
		stmt.setString(20, acik);
		stmt.setBoolean(21, sms);
		stmt.setBoolean(22, mailg);
		stmt.setString(23, ok1);
		stmt.setString(24, ok2);
		stmt.setString(25, web);
		stmt.setString(26, usr);
		if (  resim != null)
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int readNum; (readNum = resim.read(buf)) != -1;)
			{
				bos.write(buf, 0, readNum);
			}
			byte[] bytes = bos.toByteArray();
			stmt.setBytes(27,bytes);
		}
		else
		{
			stmt.setBytes(27,null);
		}
		stmt.executeUpdate();
		stmt.close();
	}
	public void sil(String kod ,String adi) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        String sql = " DELETE " +
		" FROM Adres WHERE M_Kodu = '" + kod.trim()  + "' AND Adi = '" + adi +  "'" ;
        PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

	}
	public ResultSet adr_hpl() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
        String sql = "SELECT M_Kodu,Adi  FROM Adres  ORDER BY M_Kodu";
        Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;
		
	}
	public ResultSet sms_adr_hpl(String nerden) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
        String sql = "SELECT " + nerden + " ,Adi ,'' AS GRUP ,'' AS DURUM ,M_Kodu ," + 
        				" '' as GON_ZAMANI,[USER] FROM Adres   WHERE Mail_Gonder = 'TRUE' ORDER BY M_Kodu ";
        Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	public ResultSet gdy_oku(String kod) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
        String sql = " SELECT M_Kodu,Adi,Adres_1,Adres_2,Semt,Sehir,Vergi_Dairesi ," +
                " Vergi_No, Fax,Tel_1,Tel_2,Ozel,Yetkili,E_Mail,Not_1,Not_2,Not_3 " +
                " FROM Adres " +
                " WHERE M_Kodu = N'" + kod + "'";
        Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	public String[] adres_oku (String kodu) throws ClassNotFoundException, SQLException
	{
		String[] bilgi = new String[6];
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT M_Kodu,Adi,Adres_1,Adres_2,Semt,Sehir,Vergi_Dairesi ," +
                " Vergi_No, Fax,Tel_1,Tel_2,Ozel,Yetkili,E_Mail,Not_1,Not_2,Not_3 " +
                " FROM Adres " +
                " WHERE M_Kodu = N'" + kodu + "'" ;
     	PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		if (!rss.isBeforeFirst() ) {  
			
		}
		
		else
		{
			rss.next();
			bilgi[0] = rss.getString("Adres_1");
			bilgi[1] = rss.getString("Adres_2");
			bilgi[2] = rss.getString("Semt");
			bilgi[3] = rss.getString("Sehir");
			bilgi[4] = rss.getString("Vergi_Dairesi");
			bilgi[5] = rss.getString("Vergi_No");
		}
		return bilgi;

	}
}
