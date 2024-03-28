package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import LOGER_KAYIT.DOSYA_MSSQL;
import LOGER_KAYIT.ILOGER_KAYIT;
import LOGER_KAYIT.TXT_LOG;


public class KAMBIYO_MSSQL implements IKAMBIYO{

	static Connection con = null;
	static Statement stmt = null;

	public void baglan() throws SQLException
	{
		String cumle = "jdbc:sqlserver://" + BAGLAN.kamDizin.cONN_STR + ";";
		DriverManager.setLoginTimeout(0);
		con = DriverManager.getConnection(cumle,BAGLAN.kamDizin.kULLANICI,BAGLAN.kamDizin.sIFRESI);
	}
	@Override
	public void kAM_SIFIR_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String cumle = "";
		if ( ! sbilgi.getPort().toString().equals(""))
			sbilgi.setPort(  ":" + sbilgi.getPort() );
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		String VERITABANI = "OK_Kam" + sbilgi.getKod();
		stmt = null;
		String sql =null;
		if (sbilgi.getDizin_yeri() == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "', FILENAME = N'" + sbilgi.getDizin() 	+ "\\" + VERITABANI + ".mdf' ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table(sbilgi.getFir_adi());
		//
		if (sbilgi.getDizin_yeri() == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "_LOG" + "', FILENAME = N'" + sbilgi.getDizin() 	+ "\\" + VERITABANI + "_LOG" + ".mdf ' ) ";
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + "_LOG" + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MSSQL();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kamLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kamLogDizin);

		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU+ VERITABANI + "_mSSQL" + ".DB") == false)
		{
			String dsy = GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+ ".DB" ;
			GLOBAL.create_table_log(dsy ,sbilgi.getFir_adi(),BAGLAN_LOG.kamLogDizin);
		}
		//ACCESS LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU+ VERITABANI + "_mSSQL" + ".accdb") == false)
		{
			String dsy = GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+ ".accdb" ;
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
	public void kAM_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String VERITABANI = "OK_Kam" + sbilgi.getKod();
		String cumle = "";
		stmt = null;
		String sql =null;
		cumle = "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		sql = "CREATE DATABASE [" + VERITABANI + "]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table(sbilgi.getFir_adi());
		//
		sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
		cumle = "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + "_LOG" + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MSSQL();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kamLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi() );
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kamLogDizin);

		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + GLOBAL.char_degis(  BAGLAN_LOG.kamLogDizin.mODUL) ) == false)
		{
			String dsy =   GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.kamLogDizin.mODUL)  ;
			GLOBAL.create_table_log(dsy ,sbilgi.getFir_adi(),BAGLAN_LOG.kamLogDizin);
		}
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + GLOBAL.char_degis(  BAGLAN_LOG.kamLogDizin.mODULADI_ACCDB) ) == false)
		{
			String dsy =   GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.kamLogDizin.mODULADI_ACCDB)  ;
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
		sql = "CREATE TABLE CEK(Cek_No  nvarchar(10) CONSTRAINT PKeyCID PRIMARY KEY ,Vade date,Giris_Bordro  nvarchar(10),Cikis_Bordro  nvarchar(10) ,Giris_Tarihi date , Cikis_Tarihi date , Giris_Musteri nvarchar(12),Cikis_Musteri nvarchar(12),Banka nvarchar(25),Sube nvarchar(25),Tutar float ,Cins nvarchar(3), Durum nvarchar(1),T_Tarih date , Seri_No nvarchar(15),Ilk_Borclu nvarchar(30),Cek_Hesap_No nvarchar(15),Giris_Ozel_Kod nvarchar(15) ,Cikis_Ozel_Kod nvarchar(15),[USER] nvarchar(15))";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE NONCLUSTERED INDEX [IX_CEK] "
				+"	ON [dbo].[CEK] ([Cek_No],[Vade])"
				+"	INCLUDE ([Giris_Bordro],[Cikis_Bordro])";
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

		ResultSet	rss = null;
		String cumle = "jdbc:sqlserver://" +  BAGLAN.kamDizin.cONN_STR + ";";
		con = DriverManager.getConnection(cumle, BAGLAN.kamDizin.kULLANICI, BAGLAN.kamDizin.sIFRESI);
		PreparedStatement stmt = con.prepareStatement("SELECT *  FROM OZEL ");
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		String result;
		result = count  != 0 ? rss.getString("FIRMA_ADI") : "" ;
		return result;	
	}
	public String kam_son_bordro_no_al(String cins ,String tur) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement("SELECT  MAX("+ tur + ") AS Expr1  FROM  " + cins );
		rss = stmt.executeQuery();
		rss.next();
		String result;
		result = rss.getString("Expr1") != null ? rss.getString("Expr1") : "" ;
		return result;	
	}
	public int kam_bordro_no_al(String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		int E_NUMBER ;
		String sql = "SELECT  NO  FROM EVRAK  WITH (HOLDLOCK, ROWLOCK)  WHERE EVRAK = '" + cins + "'";
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO CEK ( Cek_No,Vade,Giris_Bordro,Giris_Musteri,Giris_Tarihi,Giris_Ozel_Kod,Cikis_Tarihi " +
				" ,Cikis_Bordro,Cikis_Musteri,Cikis_Ozel_Kod,Banka,Sube,Tutar,Cins,Seri_No " +
				" ,Ilk_Borclu,Cek_Hesap_No,Durum,T_Tarih,[USER])" +
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
			result = rss.getString("ACIKLAMA");
		else
			result = "";
		return result;	
	}
	public ResultSet cek_kontrol(String cek) throws ClassNotFoundException, SQLException

	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		String sql = "UPDATE "+ ceksen + " SET Cikis_Bordro = '', Cikis_Musteri = '' , Cikis_Tarihi = '1900.01.01' " +
				"  WHERE Cikis_Bordro  ='" + bno + "'" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void bordro_cikis_yaz(String ceksenfrom,String ceksencins_where,String cekno,String cmus ,
			String cbor,String ctar,String ozkod) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		String sql = "UPDATE "+ ceksenfrom + " SET Cikis_Bordro = '"+ cbor + "', Cikis_Musteri = '"+ cmus + "'" + 
				",  Cikis_Tarihi = '"+ ctar + "', Cikis_Ozel_Kod = '" + ozkod + "'" +
				" WHERE " + ceksencins_where + "  ='" + cekno + "'" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void kam_durum_yaz(String numara,String ceksen_from,String ceksen_where,String durum,String ttarih) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		String sql = "UPDATE "+ ceksen_from + " SET Durum = '"+ durum + "', T_Tarih = '"+ ttarih + "'" + 
				" WHERE " + ceksen_where + "  ='" + numara + "'" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void kam_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT  Cek_No, Vade, Giris_Bordro,Giris_Tarihi, " +
				" Giris_Musteri, Banka, Sube, Cins, Tutar, CASE Durum  WHEN '1' THEN 'Iade'  WHEN '2' THEN 'Protesto' WHEN '3' THEN 'Tahsil' END as Durum, " +
				" IIF(T_Tarih = '1900.01.01', '',right(T_Tarih,2) +  '.' + Substring(Convert(nvarchar, T_Tarih), 6, 2) + '.' + left(T_Tarih,4) ) as T_Tarih, " +
				" Giris_Ozel_Kod ,Cikis_Bordro , " + 
				" IIF(Cikis_Tarihi = '1900.01.01', '',right(Cikis_Tarihi,2) +  '.' + Substring(Convert(nvarchar, Cikis_Tarihi), 6, 2) + '.' + left(Cikis_Tarihi,4) ) as Cikis_Tarihi," +
				" Cikis_Musteri, Cikis_Ozel_Kod,CEK.[USER] " +
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
		sql = "CREATE TABLE [dbo].[LOGLAMA]("
				+ "	[TARIH] [datetime] NOT NULL,"
				+ "	[MESAJ] [nchar](100) NOT NULL,"
				+ "	[EVRAK] [nchar](15) NOT NULL,"
				+ "	[USER_NAME] [nchar](15) NULL"
				+ ") ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE NONCLUSTERED INDEX [IX_LOGLAMA] ON [dbo].[LOGLAMA](	[TARIH] ASC,	[EVRAK] ASC , [USER_NAME] ASC "
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}
	private void kONTROL() throws SQLException, ClassNotFoundException
	{
		if(! con.isValid(0))
			baglan();
	}
	@Override
	public ResultSet kalan_cek_liste() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT  Cek_No " +
				" FROM CEK " +
				" WHERE " +
				" Cikis_Bordro =''" +
				" ORDER BY Cek_No ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	@Override
	public ResultSet banka_sube(String nerden) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT  DISTINCT " + nerden +
				" FROM CEK " +
				" ORDER BY " + nerden;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
}
