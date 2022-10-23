package OBS_PACKAGE;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ADRES_MYSQL implements IADRES {

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
		Class.forName("com.mysql.jdbc.Driver");
		con = null;  
		String cumle = "";
		cumle = "jdbc:mysql://localhost;instanceName=" + ins + ";";
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
        cumle = "jdbc:mysql://localhost;instanceName=" + ins + ";database=" + VERITABANI + ";";
        con = DriverManager.getConnection(cumle,kull,sifre);
        create_table(fir_adi);
        stmt.close();
        con.close();
		
	}

	@Override
	public void adr_sifirdan_S(String server, String ins, String kull, String sifre, String kod, String fir_adi)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;  
		 String VERITABANI = "OK_Adr" + kod;
		String cumle = "";
		stmt = null;
        String sql =null;
		cumle = "jdbc:mysql://" + server + ";instanceName=" + ins + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
           sql = "CREATE DATABASE [" + VERITABANI + "]";
           stmt = con.createStatement();  
           stmt.executeUpdate(sql);
           cumle = "jdbc:mysql://" + server + ";instanceName=" + ins + ";databaseName=" + VERITABANI + ";";
           con = DriverManager.getConnection(cumle,kull,sifre);
           create_table(fir_adi);
           stmt.close();
           con.close();
		
	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql = null;
        sql = "CREATE TABLE [dbo].[Adres]( "
                      + " [M_Kodu] [nvarchar](12) NOT NULL, "
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
                      + " [E_Mail] [nvarchar](30) NULL,"
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
                      + " CONSTRAINT [PKeyKodu] PRIMARY KEY CLUSTERED (	[M_Kodu] ASC "
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
		Class.forName("com.mysql.jdbc.Driver");
	 	con = null;
		ResultSet	rss = null;
		String cumle = "jdbc:mysql://" + CONNECTION.adrdizinbilgi.conn_str + ";";
        con = DriverManager.getConnection(cumle,CONNECTION.adrdizinbilgi.kullanici,CONNECTION.adrdizinbilgi.sifresi);
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

	@Override
	public void adr_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet adres(String sira, String arama) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String kod_ismi(String kodu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void adres_kayit(String kodu, String adi, String adr1, String adr2, String semt, String sehir, String vd,
			String vn, String fax, String tel1, String tel2, String ozel, String yet, String e_ma, String n1, String n2,
			String n3, InputStream resim, String tel3, String acik, boolean sms, boolean mailg, String ok1, String ok2,
			String web, String pkodu, String usr) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sil(String kod ,String adi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet adr_hpl() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet sms_adr_hpl(String nerden) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet gdy_oku(String kod) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String[] adres_oku(String kodu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
