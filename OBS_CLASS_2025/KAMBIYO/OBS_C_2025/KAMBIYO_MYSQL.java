package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import LOGER_KAYIT.DOSYA_MYSQL;
import LOGER_KAYIT.ILOGER_KAYIT;
import LOGER_KAYIT.TXT_LOG;

public class KAMBIYO_MYSQL implements IKAMBIYO {

	static Connection con = null;
	static Statement stmt = null;


	public void baglan() throws SQLException
	{
		String cumle = "jdbc:mysql://" + BAGLAN.kamDizin.cONN_STR ;
		//DriverManager.setLoginTimeout(0);
		con = DriverManager.getConnection(cumle,BAGLAN.kamDizin.kULLANICI,BAGLAN.kamDizin.sIFRESI);
	}
	@Override
	public void kAM_SIFIR_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String cumle = "";
		cumle = "jdbc:mysql://localhost:" + sbilgi.getPort() ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		String VERITABANI = "ok_kam" + sbilgi.getKod();
		stmt = null;
		String sql =null;
		sql = "CREATE DATABASE " + VERITABANI ;
		stmt = con.createStatement();  
		stmt.execute(sql);
		cumle = "jdbc:mysql://localhost/" +VERITABANI ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table(sbilgi.getFir_adi());
		//
		sql = "CREATE DATABASE " + VERITABANI + "_log" ;
		stmt = con.createStatement();  
		stmt.execute(sql);
		cumle = "jdbc:mysql://localhost/" +VERITABANI + "_log" ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MYSQL();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kamLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kamLogDizin);

		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL" + ".DB") == false)
		{
			String dsy =GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL"+ ".DB" ;
			GLOBAL.create_table_log(dsy ,sbilgi.getFir_adi(),BAGLAN_LOG.kamLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.kamLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.kamLogDizin);
		//
		stmt.close();
		con.close();
	}
	@Override
	public void kAM_SIFIR_S(Server_Bilgi sbilgi)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String VERITABANI = "ok_kam" + sbilgi.getKod();
		String cumle = "";
		stmt = null;
		String sql =null;
		cumle = "jdbc:mysql://" +  sbilgi.getServer() ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		sql = "CREATE DATABASE " + VERITABANI ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:mysql://" +  sbilgi.getServer() + "/" + VERITABANI ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table(sbilgi.getFir_adi());
		//
		sql = "CREATE DATABASE " + VERITABANI + "_log" ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:mysql://" +  sbilgi.getServer() + "/" + VERITABANI + "_log" ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MYSQL();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kamLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kamLogDizin);

		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol( GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.kamLogDizin.mODUL) ) == false)
		{
			String dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.kamLogDizin.mODUL) ;
			GLOBAL.create_table_log(dsy ,sbilgi.getFir_adi(),BAGLAN_LOG.kamLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.kamLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.kamLogDizin);
		//
		stmt.close();
		con.close();
	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql =null;
		sql = "CREATE TABLE `CEK`(`Cek_No`  nvarchar(10)  PRIMARY KEY  ,`Vade` DATE ,`Giris_Bordro`  nvarchar(10),`Cikis_Bordro`  nvarchar(10) ," 
				+	" `Giris_Tarihi` DATE , `Cikis_Tarihi` DATE , `Giris_Musteri` nvarchar(12),`Cikis_Musteri` nvarchar(12),`Banka` nvarchar(25),`Sube` nvarchar(25), " 
				+ " `Tutar` DOUBLE ,`Cins` nvarchar(3), `Durum` nvarchar(1),`T_Tarih` DATE , `Seri_No` nvarchar(15),`Ilk_Borclu` nvarchar(30),`Cek_Hesap_No` nvarchar(15), "
				+ " `Giris_Ozel_Kod` nvarchar(15) ,`Cikis_Ozel_Kod` nvarchar(15),`USER` nvarchar(15))";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `SENET`(`Senet_No`  nvarchar(10)  PRIMARY KEY,`Vade` DATE,`Giris_Bordro`  nvarchar(10),`Cikis_Bordro`  nvarchar(10) ," 
				+ " `Giris_Tarihi` DATE , `Cikis_Tarihi` DATE , `Giris_Musteri` nvarchar(12),`Cikis_Musteri` nvarchar(12),`Tutar` DOUBLE ,`Cins` nvarchar(3), `Durum` nvarchar(1), "
				+ " `T_Tarih` DATE , `Ilk_Borclu` nvarchar(30),`Sehir` nvarchar(15),`Giris_Ozel_Kod` nvarchar(15) ,`Cikis_Ozel_Kod` nvarchar(15),`USER` nvarchar(15))";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `EVRAK`(`EVRAK` nvarchar(5),`NO` integer )";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `ACIKLAMA`(`ACID`  INTEGER AUTO_INCREMENT PRIMARY KEY,`EVRAK_CINS` nvarchar(3) ,`SATIR` int ,`EVRAK_NO` nvarchar(10) ,`ACIKLAMA` nvarchar(50) ,`Gir_Cik` nvarchar(1))";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `OZEL` ("
				+ "  `OZID` INTEGER AUTO_INCREMENT PRIMARY KEY,"
				+ "  `YONETICI` VARCHAR(25) NULL,"
				+ "  `YON_SIFRE` VARCHAR(15) NULL,"
				+ "  `FIRMA_ADI` VARCHAR(50) NULL)";  
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `YETKILER`( "
				+ " `YETID` INTEGER AUTO_INCREMENT PRIMARY KEY,"
				+ "`KULLANICI` nvarchar(25) NULL,"
				+ "`KARTON` nvarchar(5) NULL,"
				+ "`TAM_YETKI` TINYINT NULL,"
				+ "`GORUNTU` TINYINT NULL);";      
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// ***************OZEL NO YAZ *************************
		sql = "INSERT INTO  `OZEL` (`YONETICI`,`YON_SIFRE`,`FIRMA_ADI`) VALUES ('" + GLOBAL.KULL_ADI  + "','12345' , '" + fir_adi + "')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// ***************CEK GIRIS EVRAK NO YAZ **************
		sql = "INSERT INTO  `EVRAK`(`EVRAK`,`NO`) VALUES ('CEK_G','0')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// ***************CEK CIKIS EVRAK NO YAZ **************
		sql = "INSERT INTO  `EVRAK`(`EVRAK`,`NO`) VALUES ('CEK_C','0')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// ***************SENET GIRIS EVRAK NO YAZ ************
		sql = "INSERT INTO  `EVRAK`(`EVRAK`,`NO`) VALUES ('SEN_G','0')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// ***************SENET CIKIS EVRAK NO YAZ ************
		sql = "INSERT INTO  `EVRAK`(`EVRAK`,`NO`) VALUES ('SEN_C','0')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}

	@Override
	public String kam_firma_adi() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String cumle = "jdbc:mysql://" +  BAGLAN.kamDizin.cONN_STR + ";";
		con = DriverManager.getConnection(cumle, BAGLAN.kamDizin.kULLANICI, BAGLAN.kamDizin.sIFRESI);
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

	public String kam_son_bordro_no_al(String cins ,String tur) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement("SELECT  MAX("+ tur + ") AS Expr1  FROM  " + cins );
		rss = stmt.executeQuery();
		rss.next();
		String result;
		if ( rss.getString("Expr1") != null ) 
		{
			result = rss.getString("Expr1");
		}
		else
		{
			result = "";
		}
		return result;	
	}
	public int kam_bordro_no_al(String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		int E_NUMBER ;
		String sql = "SELECT  NO  FROM EVRAK    WHERE EVRAK = '" + cins + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		E_NUMBER = rss.getInt("NO");
		E_NUMBER = E_NUMBER + 1 ;
		//******** KAYIT
		sql = "UPDATE EVRAK SET NO =" + E_NUMBER + "  WHERE EVRAK = '"+ cins + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		//**************
		return E_NUMBER;	

	}
	public void bordro_sil(String cins ,String bno,String tur) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = " DELETE " +
				" FROM " + cins + "" +
				" WHERE "+ tur +"  ='" + bno + "'" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

	}
	public void cek_kayit(String cno ,String vade,String gbo, String gmus ,String gtar,String gozk  
			, String ctar , String cbno ,String cmus ,String cozk ,String bank ,String sube 
			, double tut ,String cins ,String serno ,String ilkb ,String chesn 
			, String drm ,String ttarih ,String usr ) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  = "INSERT INTO CEK ( Cek_No,Vade,Giris_Bordro,Giris_Musteri,Giris_Tarihi,Giris_Ozel_Kod,Cikis_Tarihi " +
				" ,Cikis_Bordro,Cikis_Musteri,Cikis_Ozel_Kod,Banka,Sube,Tutar,Cins,Seri_No " +
				" ,Ilk_Borclu,Cek_Hesap_No,Durum,T_Tarih,USER)" +
				"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;

		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, cno);
		stmt.setString(2, vade);
		stmt.setString(3,gbo);
		stmt.setString(4, gmus);
		stmt.setString(5, gtar);
		stmt.setString(6, gozk);
		stmt.setString(7, ctar);
		stmt.setString(8,cbno);
		stmt.setString(9, cmus);
		stmt.setString(10, cozk);
		stmt.setString(11, bank);
		stmt.setString(12, sube);
		stmt.setDouble(13, tut);
		stmt.setString(14, cins);
		stmt.setString(15, serno);
		stmt.setString(16, ilkb);
		stmt.setString(17, chesn);
		stmt.setString(18, drm);
		stmt.setString(19, ttarih);
		stmt.setString(20, usr);
		stmt.executeUpdate();
		stmt.close();

	}
	public void kam_aciklama_sil(String evrcins,String evrno,String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = " DELETE  FROM ACIKLAMA " +
				" WHERE EVRAK_CINS = N'" + evrcins + "'" +
				" AND EVRAK_NO = N'" + evrno + "'" +
				" AND Gir_Cik = N'" + cins + "'" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

	}
	public void kam_aciklama_yaz(String evrcins,int satir,String evrno,String aciklama,String gircik) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  = "INSERT INTO ACIKLAMA (EVRAK_CINS,SATIR,EVRAK_NO,ACIKLAMA,Gir_Cik) " +
				"VALUES (?,?,?,?,?)" ;

		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, evrcins);
		stmt.setInt(2, satir);
		stmt.setString(3,evrno);
		stmt.setString(4, aciklama);
		stmt.setString(5, gircik);

		stmt.executeUpdate();
		stmt.close();

	}
	public ResultSet kam_bordno(String cins,String bno,String gircik) throws ClassNotFoundException, SQLException
	{

		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT * " +
				" FROM " + cins + " WHERE " + gircik + " = N'" + bno + "'" +
				" ORDER BY Vade ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;

	}
	public String kam_aciklama_oku(String evrcins,String satir,String evrno,String gircik) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT * " +
				" FROM ACIKLAMA " +
				" WHERE EVRAK_NO = N'" + evrno + "'" +
				" AND SATIR = '" + satir + "'" +
				" AND EVRAK_CINS = '" + evrcins + "'" +
				" AND Gir_Cik = '" + gircik + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();

		String result;
		if (rss.getRow() != 0) 
		{
			result = rss.getString("ACIKLAMA");
		}
		else
		{
			result = "";
		}
		return result;	

	}
	public ResultSet cek_kontrol(String cek) throws ClassNotFoundException, SQLException

	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT Banka, Cek_No, Cikis_Bordro, Cikis_Musteri, Cikis_Tarihi, Cins, Durum, Giris_Bordro, Giris_Musteri, Giris_Tarihi, Ilk_Borclu, " +
				" Seri_No, Sube, T_Tarih, Tutar, Vade, Cek_Hesap_No ,Cikis_Ozel_Kod,Giris_Ozel_Kod " +
				" FROM cek WHERE Cek_No ='" + cek + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;

	}
	public void bordro_cikis_sil(String bno,String ceksen) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "UPDATE "+ ceksen + " SET Cikis_Bordro = '', Cikis_Musteri = '' , Cikis_Tarihi = '1900.01.01' " +
				"  WHERE Cikis_Bordro  ='" + bno + "'" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

	}
	public void bordro_cikis_yaz(String ceksenfrom,String ceksencins_where,String cekno,String cmus ,
			String cbor,String ctar,String ozkod) throws ClassNotFoundException, SQLException
	{

		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "UPDATE "+ ceksenfrom + " SET Cikis_Bordro = '"+ cbor + "', Cikis_Musteri = '"+ cmus + "'" + 
				",  Cikis_Tarihi = '"+ ctar + "', Cikis_Ozel_Kod = '" + ozkod + "'" +
				" WHERE " + ceksencins_where + "  ='" + cekno + "'" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void kam_durum_yaz(String numara,String ceksen_from,String ceksen_where,String durum,String ttarih) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "UPDATE "+ ceksen_from + " SET Durum = '"+ durum + "', T_Tarih = '"+ ttarih + "'" + 
				" WHERE " + ceksen_where + "  ='" + numara + "'" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void kam_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "UPDATE OZEL SET FIRMA_ADI = N'" + fadi + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public ResultSet cek_rapor( String cekno1,  String cekno2 ,  String tar1 , String tar2 , String gbord1 , String gbord2 
			,  String gtar1 ,  String gtar2 , String cbord1 , String cbord2 , String ctar1, String ctar2 
			,  String gmus1 , String gmus2 , String cmus1 , String cmus2 , String cin1 , String cin2 
			,  String dur1 ,  String dur2 ,  String ttar1 ,  String ttar2 , String gozel , String cozel ) throws ClassNotFoundException, SQLException

	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = " SELECT  Cek_No, Vade, Giris_Bordro,Giris_Tarihi, " +
				" Giris_Musteri, Banka, Sube, Cins, Tutar, Durum, " +
				" IF(T_Tarih = '1900.01.01', '',DATE_FORMAT(T_Tarih,  '%d.%m.%Y') ) as T_Tarih, " +
				" Giris_Ozel_Kod ,Cikis_Bordro , " + 
				" IF(Cikis_Tarihi = '1900.01.01', '',DATE_FORMAT(Cikis_Tarihi,  '%d.%m.%Y') ) as Cikis_Tarihi," +
				" Cikis_Musteri, Cikis_Ozel_Kod,CEK.USER " +
				" FROM CEK " +
				" WHERE Cek_No >='" + cekno1 + "' AND Cek_No <='" + cekno2 + "'" +
				" AND Vade >='" + tar1 + "' AND Vade <='" + tar2 + "'" +
				" AND Giris_Bordro >='" + gbord1 + "' AND Giris_Bordro <='" + gbord2 + "'" +
				" AND Giris_Tarihi >='" + gtar1 + "' AND Giris_Tarihi <='" + gtar2 + "'" +
				" AND Cikis_Bordro >='" + cbord1 + "' AND Cikis_Bordro <='" + cbord2 + "'" +
				" AND Cikis_Tarihi >='" + ctar1 + "' AND Cikis_Tarihi <='" + ctar2 + "'" +
				" AND Giris_Musteri >='" + gmus1 + "' AND Giris_Musteri <='" + gmus2 + "'" +
				" AND Cikis_Musteri >='" + cmus1 + "' AND Cikis_Musteri <='" + cmus2 + "'" +
				" AND Durum >='" + dur1 + "' AND Durum <='" + dur2 + "'" +
				" AND T_Tarih >='" + ttar1 + "'  AND T_Tarih <='" + ttar2 + "'" +
				" AND Cins >='" + cin1 + "' AND Cins <='" + cin2 + "'" +
				" AND Giris_Ozel_Kod  LIKE '" + gozel + "'" +
				" AND Cikis_Ozel_Kod  LIKE '" + cozel + "'" +
				" ORDER BY Cek_No ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	@Override
	public void create_table_log() throws SQLException {
		String sql = "" ;
		sql = "CREATE TABLE  `loglama` ("
				+ "  `TARIH` DATETIME NOT NULL,"
				+ "  `MESAJ` VARCHAR(100) NULL,"
				+ "  `EVRAK` VARCHAR(15) NULL,"
				+ "  `USER_NAME` VARCHAR(15) NULL,"
				+ "  INDEX `IX_LOGLAMA` (`TARIH` ASC, `USER_NAME` ASC) VISIBLE);";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}
	private void kONTROL() throws SQLException, ClassNotFoundException
	{
		if(con.isClosed())    
			baglan();
	}
}
