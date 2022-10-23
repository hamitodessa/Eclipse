package OBS_PACKAGE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KAMBIYO_MYSQL implements IKAMBIYO {

	static Connection con = null;
	static Statement stmt = null;
	
	
	public void baglan() throws SQLException
	{
		String cumle = "jdbc:mysql://" + CONNECTION.caridizinbilgi.conn_str + ";";
	    con = DriverManager.getConnection(cumle,CONNECTION.caridizinbilgi.kullanici,CONNECTION.caridizinbilgi.sifresi);
	}
	@Override
	public void kam_sifirdan_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
 		con = null;  
 		String cumle = "";
         cumle = "jdbc:mysql://localhost;instanceName=" + ins + ";";
         con = DriverManager.getConnection(cumle,kull,sifre);
         String VERITABANI = "OK_Kam" + kod;
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
	public void kam_sifirdan_S(String server, String ins, String kull, String sifre, String kod, String fir_adi)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
 		con = null;  
 		String VERITABANI = "OK_Kam" + kod;
 		String cumle = "";
 		stmt = null;
        String sql =null;
 		cumle = "jdbc:mysql://" + server + ";instanceName=" + ins + ";";
 		con = DriverManager.getConnection(cumle,kull,sifre);
            sql = "CREATE DATABASE [" + VERITABANI + "]";
            stmt = con.createStatement();  
            stmt.executeUpdate(sql);
            cumle = "jdbc:mysql://" + server + ";instanceName=" + ins + ";database=" + VERITABANI + ";";
            con = DriverManager.getConnection(cumle,kull,sifre);
            create_table(fir_adi);
            stmt.close();
            con.close();
		
	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql =null;
        sql = "CREATE TABLE CEK(Cek_No  nvarchar(10) CONSTRAINT PKeyCID PRIMARY KEY ,Vade date,Giris_Bordro  nvarchar(10),Cikis_Bordro  nvarchar(10) ,Giris_Tarihi date , Cikis_Tarihi date , Giris_Musteri nvarchar(12),Cikis_Musteri nvarchar(12),Banka nvarchar(25),Sube nvarchar(25),Tutar float ,Cins nvarchar(3), Durum nvarchar(1),T_Tarih date , Seri_No nvarchar(15),Ilk_Borclu nvarchar(30),Cek_Hesap_No nvarchar(15),Giris_Ozel_Kod nvarchar(15) ,Cikis_Ozel_Kod nvarchar(15),[USER] nvarchar(15))";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE SENET(Senet_No  nvarchar(10)CONSTRAINT PKeyIID PRIMARY KEY,Vade date,Giris_Bordro  nvarchar(10),Cikis_Bordro  nvarchar(10) ,Giris_Tarihi date , Cikis_Tarihi date , Giris_Musteri nvarchar(12),Cikis_Musteri nvarchar(12),Tutar float ,Cins nvarchar(3), Durum nvarchar(1),T_Tarih date , Ilk_Borclu nvarchar(30),Sehir nvarchar(15),Giris_Ozel_Kod nvarchar(15) ,Cikis_Ozel_Kod nvarchar(15),[USER] nvarchar(15))";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE EVRAK(EVRAK nvarchar(5),NO integer )";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE ACIKLAMA(ACID int IDENTITY(1,1) CONSTRAINT PKeyACID PRIMARY KEY,EVRAK_CINS nvarchar(3) ,SATIR int ,EVRAK_NO nvarchar(10) ,ACIKLAMA nvarchar(50) ,Gir_Cik nvarchar(1))";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE OZEL(OZID int identity(1,1) CONSTRAINT PKeyOZID PRIMARY KEY,YONETICI nvarchar(25), YON_SIFRE nvarchar(15) , FIRMA_ADI nvarchar(50))";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE YETKILER(YETID int identity(1,1) CONSTRAINT PKeyYETID PRIMARY KEY,KULLANICI nvarchar(25), HESAP nvarchar(12), TAM_YETKI bit, GORUNTU bit )";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        // ***************OZEL NO YAZ *************************
        sql = "INSERT INTO  OZEL(YONETICI,YON_SIFRE,FIRMA_ADI) VALUES ('" + GLOBAL.KULL_ADI  + "','12345' , '" + fir_adi + "')";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        // ***************CEK GIRIS EVRAK NO YAZ **************
        sql = "INSERT INTO  EVRAK(EVRAK,NO) VALUES ('CEK_G','0')";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        // ***************CEK CIKIS EVRAK NO YAZ **************
        sql = "INSERT INTO  EVRAK(EVRAK,NO) VALUES ('CEK_C','0')";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        // ***************SENET GIRIS EVRAK NO YAZ ************
        sql = "INSERT INTO  EVRAK(EVRAK,NO) VALUES ('SEN_G','0')";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        // ***************SENET CIKIS EVRAK NO YAZ ************
        sql = "INSERT INTO  EVRAK(EVRAK,NO) VALUES ('SEN_C','0')";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
	}

	@Override
	public String kam_firma_adi() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	 	con = null;
		ResultSet	rss = null;
		String cumle = "jdbc:sqlserver://" + CONNECTION.kamdizinbilgi.conn_str + ";";
        con = DriverManager.getConnection(cumle,CONNECTION.kamdizinbilgi.kullanici,CONNECTION.kamdizinbilgi.sifresi);
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
	public String kam_son_bordro_no_al(String cins ,String tur) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int kam_bordro_no_al(String cins) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void bordro_sil(String cins, String bno, String tur) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cek_kayit(String cno ,String vade,String gbo, String gmus ,String gtar,String gozk  
            , String ctar , String cbno ,String cmus ,String cozk ,String bank ,String sube 
            , double tut ,String cins ,String serno ,String ilkb ,String chesn 
            , String drm ,String ttarih ,String usr ) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void kam_aciklama_sil(String evrcins, String evrno, String cins) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void kam_aciklama_yaz(String evrcins, int satir, String evrno, String aciklama, String gircik) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet kam_bordno(String cins, String bno, String gircik) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String kam_aciklama_oku(String evrcins, String satir, String evrno, String gircik) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet cek_kontrol(String cek) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void bordro_cikis_sil(String bno, String ceksen) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void bordro_cikis_yaz(String ceksenfrom, String ceksencins_where, String cekno, String cmus, String cbor,
			String ctar, String ozkod) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void kam_durum_yaz(String numara, String ceksen_from, String ceksen_where, String durum, String ttarih) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void kam_firma_adi_kayit(String fadi) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet cek_rapor(String cekno1, String cekno2, String tar1, String tar2, String gbord1, String gbord2,
			String gtar1, String gtar2, String cbord1, String cbord2, String ctar1, String ctar2, String gmus1,
			String gmus2, String cmus1, String cmus2, String cin1, String cin2, String dur1, String dur2, String ttar1,
			String ttar2, String gozel, String cozel) {
		// TODO Auto-generated method stub
		return null;
	}

}
