package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import LOGER_KAYIT.DOSYA_MYSQL;
import LOGER_KAYIT.ILOGER_KAYIT;
import LOGER_KAYIT.TXT_LOG;

public class KERESTE_MYSQL implements IKERESTE {
	static Connection con = null;
	static Statement stmt = null;

	@Override
	public void baglan() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String cumle = "jdbc:mysql://" +  BAGLAN.kerDizin.cONN_STR ;
		con = DriverManager.getConnection(cumle, BAGLAN.kerDizin.kULLANICI, BAGLAN.kerDizin.sIFRESI);
		
	}

	@Override
	public void kER_SIFIR_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String cumle = "";
		cumle = "jdbc:mysql://localhost:" + sbilgi.getPort() ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		String VERITABANI = "ok_ker" + sbilgi.getKod();
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
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi() );
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);

		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL" + ".DB") == false)
		{
			String dsy =GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL"+ ".DB" ;
			GLOBAL.create_table_log(dsy,sbilgi.getFir_adi(),BAGLAN_LOG.kerLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		 tEXLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);
		 lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		 tEXLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);
		//
		stmt.close();
		con.close();
		
	}

	@Override
	public void kER_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String VERITABANI = "ok_ker" + sbilgi.getKod();
		String cumle = "";
		stmt = null;
		String sql =null;
		cumle = "jdbc:mysql://" + sbilgi.getServer() ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		sql = "CREATE DATABASE " + VERITABANI ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:mysql://" + sbilgi.getServer() + "/" + VERITABANI ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table(sbilgi.getFir_adi());
		//
		sql = "CREATE DATABASE " + VERITABANI + "_log" ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:mysql://" + sbilgi.getServer() + "/" + VERITABANI + "_log" ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MYSQL();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(  GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.kerLogDizin.mODUL) ) == false)
		{
			String dsy =   GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.kerLogDizin.mODUL) ;
			GLOBAL.create_table_log(dsy,sbilgi.getFir_adi(),BAGLAN_LOG.kerLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);
		//
		stmt.close();
		con.close();

		
	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql = null;
		sql = "CREATE TABLE `PAKET_NO`( "
				+ "  `Pak_No` int NOT NULL,"
				+ "  `Konsimento` nvarchar(15) PRIMARY KEY NOT NULL,"
				+ "  INDEX `IX_PKN` (  `Konsimento` ASC) VISIBLE)";		
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `DPN`( "
				+ " `DID`   INTEGER AUTO_INCREMENT PRIMARY KEY  NOT NULL,"
				+ "  `Evrak_No`  nvarchar (10) NOT NULL,"
				+ "  `Tip`  nvarchar (1) NULL,"
				+ "  `Bir`  nvarchar (40) NULL,"
				+ "  `Iki`  nvarchar (40) NULL,"
				+ "  `Uc`  nvarchar (40) NULL,"
				+ " `Gir_Cik`  nvarchar (1) NULL,"
				+ " `USER`  nvarchar (15) NOT NULL,"
				+ "  INDEX `IX_DPN` (  `Evrak_No` ASC,  `Gir_Cik` ASC ) VISIBLE)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `NAKLIYECI`("
				+ " `NAKID`  INTEGER AUTO_INCREMENT PRIMARY KEY  NOT NULL,"
				+ " `NAKID_Y` int  NOT NULL,"  
				+ " `UNVAN` nvarchar(50) NOT NULL,"
				+ " `USER` nvarchar(15) NOT NULL,"
				+ " INDEX `IX_NAKL` (  `NAKID_Y` ASC ) VISIBLE)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql= "CREATE TABLE `KERESTE`( "
				+ "`Evrak_No` nvarchar(10) NOT NULL,"
				+ " `Barkod` nvarchar(20) NULL,"
				+ " `Kodu` nvarchar(16) NOT NULL,"
				+ " `Paket_No` nvarchar (10) NULL,"
				+ " `Konsimento` nvarchar(15) NULL,"
				+ " `Miktar` float NULL,"
				+ " `Tarih` datetime NULL,"
				+ " `Kdv` float NULL,"
				+ " `Doviz` nvarchar(3) NULL,"
				+ " `Fiat` float NULL,"
				+ " `Tutar` float NULL,"
				+ " `Kur` float NULL,"
				+ " `Cari_Firma` nvarchar(12) NULL,"
				+ " `Adres_Firma` nvarchar(12) NULL,"
				+ " `Iskonto` float NULL,"
				+ " `Tevkifat` float NULL,"
				+ " `Ana_Grup` int NULL,"
				+ " `Alt_Grup` int NULL,"
				+ " `Mensei` int NULL,"
				+ " `Depo` int NULL,"
				+ " `Ozel_Kod` nvarchar(10) NULL,"
				+ " `Izahat` nvarchar(40) NULL,"
				+ " `Nakliyeci` int NULL,"
				+ " `USER` nvarchar(15) NOT NULL,"
				+ " `Cikis_Evrak` nvarchar(10) NULL,"
				+ " `CTarih` datetime NULL,"
				+ " `CKdv` float NULL,"
				+ " `CDoviz` nvarchar(3) NULL,"
				+ " `CFiat` float NULL,"
				+ " `CTutar` float NULL,"
				+ " `CKur` float NULL,"
				+ " `CCari_Firma` nvarchar(12) NULL,"
				+ " `CAdres_Firma` nvarchar(12) NULL,"
				+ " `CIskonto` float NULL,"
				+ " `CTevkifat` float NULL,"
				+ " `CAna_Grup` int NULL,"
				+ " `CAlt_Grup` int NULL,"
				+ " `CDepo` int NULL,"
				+ " `COzel_Kod` nvarchar(10) NULL,"
				+ " `CIzahat` nvarchar(40) NULL,"
				+ " `CNakliyeci` int NULL,"
				+ " `CUSER` nvarchar(15)  NULL,"
				+ " `Satir` int NOT NULL,"
				+ " INDEX `IX_KERESTE` (  `Evrak_No`,`Kodu`,`Tarih`,`Paket_No`,`Konsimento`,`Cari_Firma`,`Cikis_Evrak` ) VISIBLE)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `OZEL` ("
				+ "  `YONETICI` VARCHAR(25) NULL,"
				+ "  `YON_SIFRE` VARCHAR(15) NULL,"
				+ "  `FIRMA_ADI` VARCHAR(50) NULL,"
				+ " `USER`  nvarchar (15) NOT NULL)" ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `MENSEI_DEGISKEN`("
				+ " `MEID` INTEGER AUTO_INCREMENT PRIMARY KEY  NOT NULL,"
				+ " `MEID_Y`  int   NOT NULL,"   
				+ " `MENSEI`  nvarchar (25) NOT NULL,"
				+ " `USER`  nvarchar (15) NOT NULL)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE  `ANA_GRUP_DEGISKEN`("
				+ " `AGID`  INTEGER AUTO_INCREMENT PRIMARY KEY  NOT NULL,"
				+ " `AGID_Y`  int  NOT NULL,"  
				+ " `ANA_GRUP`  nvarchar (25) NOT NULL,"
				+ " `USER`  nvarchar (15) NOT NULL)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE  `ALT_GRUP_DEGISKEN`("
				+ " `ALID` INTEGER AUTO_INCREMENT PRIMARY KEY  NOT NULL,"
				+ " `ALID_Y`  int  NOT NULL,"  
				+ " `ANA_GRUP`  int  NOT NULL,"
				+ " `ALT_GRUP`  nvarchar (25) NOT NULL,"
				+ " `USER`  nvarchar (15) NOT NULL)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE  `ACIKLAMA`("
				+ " `ACID`  INTEGER AUTO_INCREMENT PRIMARY KEY  NOT NULL,"
				+ " `EVRAK_CINS`  nvarchar (3) NULL,"
				+ " `SATIR`  int  NULL,"
				+ " `EVRAK_NO`  nvarchar (10) NULL,"
				+ " `ACIKLAMA`  nvarchar (50) NULL,"
				+ " `Gir_Cik`  nvarchar (1) NULL,"
				+ " INDEX IX_ACIKLAMA  (`EVRAK_CINS` ASC, `EVRAK_NO` ASC, `Gir_Cik` ASC)  VISIBLE )";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `KOD_ACIKLAMA`("
				+ " `KOD` nvarchar(2) NOT NULL,"
				+ " `ACIKLAMA` nvarchar(50) NULL,"
				+ " INDEX IX_KOD_ACIKLAMA  (`KOD` ASC)  VISIBLE )";
		stmt = con.createStatement(); 
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `KONS_ACIKLAMA`("
				+ " `KONS` nvarchar(10) NOT NULL,"
				+ " `ACIKLAMA` nvarchar(50) NULL,"
				+ " INDEX IX_KONS_ACIKLAMA  (`KONS` ASC)  VISIBLE )";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE  `DEPO_DEGISKEN`("
				+ " `DPID` INTEGER AUTO_INCREMENT PRIMARY KEY  NOT NULL,"
				+ " `DPID_Y`  int   NOT NULL,"   
				+ " `DEPO`  nvarchar (25) NOT NULL,"
				+ " `USER`  nvarchar (15) NOT NULL)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE  `OZ_KOD_1_DEGISKEN`("
				+ " `OZ1ID`  INTEGER AUTO_INCREMENT PRIMARY KEY  NOT NULL,"
				+ " `OZ1ID_Y`  int   NOT NULL,"  
				+ " `OZEL_KOD_1`  nvarchar (25) NOT NULL,"
				+ " `USER`  nvarchar (15) NOT NULL)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `YETKILER`("
				+ " `KULLANICI`  nvarchar (25) NULL,"
				+ " `HESAP`  nvarchar (12) NULL,"
				+ " `TAM_YETKI` TINYINT NULL,"
				+ " `GORUNTU` TINYINT NULL,"
				+ " `LEVEL`  int  NOT NULL,"
				+ " `USER`  nvarchar (15) NOT NULL)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `IRS_EVRAK_FORMAT`("
				+ " `SAT_SUT` nchar(5) NULL,"
				+ " `TARIH`  float  NULL,"
				+ " `SEVK_TARIH`  float  NULL,"
				+ " `FIRMA_KODU`  float  NULL,"
				+ " `FIRMA_UNVANI`  float  NULL,"
				+ " `VERGI_DAIRESI`  float  NULL,"
				+ " `VERGI_NO`  float  NULL,"
				+ " `GIDECEGI_YER`  float  NULL,"
				+ " `NOT_1`  float  NULL,"
				+ " `NOT_2`  float  NULL,"
				+ " `NOT_3`  float  NULL,"
				+ " `BASLIK_BOLUM`  float  NULL,"
				+ " `BARKOD`  float  NULL,"
				+ " `URUN_KODU`  float  NULL,"
				+ " `URUN_ADI`  float  NULL,"
				+ " `DEPO`  float  NULL,"
				+ " `SIMGE`  float  NULL,"
				+ " `BIRIM_FIAT`  float  NULL,"
				+ " `ISKONTO`  float  NULL,"
				+ " `MIKTAR`  float  NULL,"
				+ " `K_D_V`  float  NULL,"
				+ " `TUTAR`  float  NULL,"
				+ " `TUTAR_TOPLAM`  float  NULL,"
				+ " `ISKONTO_TOPLAMI`  float  NULL,"
				+ " `BAKIYE`  float  NULL,"
				+ " `K_D_V_TOPLAMI`  float  NULL,"
				+ " `BELGE_TOPLAMI`  float  NULL,"
				+ " `YAZI_ILE`  float  NULL,"
				+ " `ALT_BOLUM`  float  NULL,"
				+ " `N1`  float  NULL,"
				+ " `N2`  float  NULL,"
				+ " `N3`  float  NULL,"
				+ " `N4`  float  NULL,"
				+ " `N5`  float  NULL,"
				+ " `N6`  float  NULL,"
				+ " `N7`  float  NULL,"
				+ " `N8`  float  NULL,"
				+ " `N9`  float  NULL,"
				+ " `N10`  float  NULL,"
				+ " `USER`  nvarchar (15) NOT NULL)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "INSERT INTO  IRS_EVRAK_FORMAT(SAT_SUT ,TARIH,SEVK_TARIH,FIRMA_KODU,FIRMA_UNVANI,VERGI_DAIRESI ,VERGI_NO  ,GIDECEGI_YER,NOT_1 ,NOT_2 ,NOT_3,BASLIK_BOLUM,BARKOD,URUN_KODU ,URUN_ADI , DEPO,SIMGE ,BIRIM_FIAT ,ISKONTO ,MIKTAR,K_D_V ,TUTAR ,TUTAR_TOPLAM ,ISKONTO_TOPLAMI  ,BAKIYE ,K_D_V_TOPLAMI ,BELGE_TOPLAMI , YAZI_ILE,ALT_BOLUM, N1 ,N2 ,N3 ,N4 ,N5 ,N6 ,N7 ,N8 ,N9 ,N10,`USER` ) VALUES ('SATIR','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','Admin')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "INSERT INTO  IRS_EVRAK_FORMAT(SAT_SUT ,TARIH,SEVK_TARIH,FIRMA_KODU,FIRMA_UNVANI,VERGI_DAIRESI ,VERGI_NO  ,GIDECEGI_YER,NOT_1 ,NOT_2 ,NOT_3,BASLIK_BOLUM,BARKOD,URUN_KODU ,URUN_ADI , DEPO,SIMGE ,BIRIM_FIAT ,ISKONTO ,MIKTAR,K_D_V ,TUTAR ,TUTAR_TOPLAM ,ISKONTO_TOPLAMI  ,BAKIYE ,K_D_V_TOPLAMI ,BELGE_TOPLAMI , YAZI_ILE,ALT_BOLUM, N1 ,N2 ,N3 ,N4 ,N5 ,N6 ,N7 ,N8 ,N9 ,N10,`USER` ) VALUES ('SUTUN','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','Admin')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `FAT_EVRAK_FORMAT`( "
				+ " `SAT_SUT` nchar (5) NULL,"
				+ " `TARIH`  float  NULL,"
				+ " `FIRMA_KODU`  float  NULL,"
				+ " `FIRMA_UNVANI`  float  NULL,"
				+ " `VERGI_DAIRESI`  float  NULL,"
				+ " `VERGI_NO`  float  NULL,"
				+ " `GIDECEGI_YER`  float  NULL,"
				+ " `NOT_1`  float  NULL,"
				+ " `NOT_2`  float  NULL,"
				+ " `NOT_3`  float  NULL,"
				+ " `BASLIK_BOLUM`  float  NULL,"
				+ " `BARKOD`  float  NULL,"
				+ " `URUN_KODU`  float  NULL,"
				+ " `URUN_ADI`  float  NULL,"
				+ " `DEPO`  float  NULL,"
				+ " `IZAHAT`  float  NULL,"
				+ " `SIMGE`  float  NULL,"
				+ " `BIRIM_FIAT`  float  NULL,"
				+ " `ISKONTO`  float  NULL,"
				+ " `MIKTAR`  float  NULL,"
				+ " `K_D_V`  float  NULL,"
				+ " `TUTAR`  float  NULL,"
				+ " `TUTAR_TOPLAM`  float  NULL,"
				+ " `ISKONTO_TOPLAMI`  float  NULL,"
				+ " `BAKIYE`  float  NULL,"
				+ " `K_D_V_TOPLAMI`  float  NULL,"
				+ " `BELGE_TOPLAMI`  float  NULL,"
				+ " `TEVKIFAT_ORANI`  float  NULL,"
				+ " `AL_TAR_TEV_ED_KDV`  float  NULL,"
				+ " `TEV_DAH_TOP_TUTAR`  float  NULL,"
				+ " `BEYAN_ED_KDV`  float  NULL,"
				+ " `TEV_HAR_TOP_TUT`  float  NULL,"
				+ " `YAZI_ILE`  float  NULL,"
				+ " `TEV_KASESI`  float  NULL,"
				+ " `ALT_BOLUM`  float  NULL,"
				+ " `N1`  float  NULL,"
				+ " `N2`  float  NULL,"
				+ " `N3`  float  NULL,"
				+ " `N4`  float  NULL,"
				+ " `N5`  float  NULL,"
				+ " `N6`  float  NULL,"
				+ " `N7`  float  NULL,"
				+ " `N8`  float  NULL,"
				+ " `N9`  float  NULL,"
				+ " `N10`  float  NULL,"
				+ " `USER`  nvarchar (15) NULL)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "INSERT INTO  FAT_EVRAK_FORMAT(SAT_SUT,TARIH,FIRMA_KODU,FIRMA_UNVANI,VERGI_DAIRESI ,VERGI_NO ,GIDECEGI_YER ,NOT_1 ,NOT_2 ,NOT_3,BASLIK_BOLUM,BARKOD,URUN_KODU ,URUN_ADI , DEPO ,IZAHAT,SIMGE ,BIRIM_FIAT ,ISKONTO ,MIKTAR,K_D_V ,TUTAR ,TUTAR_TOPLAM ,ISKONTO_TOPLAMI  ,BAKIYE ,K_D_V_TOPLAMI ,BELGE_TOPLAMI , YAZI_ILE,TEVKIFAT_ORANI ,AL_TAR_TEV_ED_KDV ,TEV_DAH_TOP_TUTAR , BEYAN_Ed_KDV ,TEV_HAR_TOP_TUT,TEV_KASESI,ALT_BOLUM,N1 ,N2 ,N3 ,N4 ,N5 ,N6 ,N7 ,N8 ,N9 ,N10,USER ) VALUES " + " ('SATIR','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','Admin')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "INSERT INTO  FAT_EVRAK_FORMAT(SAT_SUT,TARIH,FIRMA_KODU,FIRMA_UNVANI,VERGI_DAIRESI ,VERGI_NO ,GIDECEGI_YER ,NOT_1 ,NOT_2 ,NOT_3,BASLIK_BOLUM,BARKOD,URUN_KODU ,URUN_ADI , DEPO ,IZAHAT,SIMGE ,BIRIM_FIAT ,ISKONTO ,MIKTAR,K_D_V ,TUTAR ,TUTAR_TOPLAM ,ISKONTO_TOPLAMI  ,BAKIYE ,K_D_V_TOPLAMI ,BELGE_TOPLAMI , YAZI_ILE,TEVKIFAT_ORANI ,AL_TAR_TEV_ED_KDV ,TEV_DAH_TOP_TUTAR , BEYAN_Ed_KDV ,TEV_HAR_TOP_TUT,TEV_KASESI,ALT_BOLUM,N1 ,N2 ,N3 ,N4 ,N5 ,N6 ,N7 ,N8 ,N9 ,N10,USER) VALUES " + " ('SUTUN','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','Admin')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// ***************OZEL NO YAZ *************************
		sql = "INSERT INTO  OZEL(YONETICI,YON_SIFRE,FIRMA_ADI,USER) VALUES ('" + GLOBAL.KULL_ADI  + "','12345' , '" + fir_adi + "','Admin')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
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
	@Override
	public void kER_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE OZEL SET FIRMA_ADI = N'" + fadi + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}

	@Override
	public String ker_firma_adi() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
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

	@Override
	public ResultSet kod_pln() throws ClassNotFoundException, SQLException {
		ResultSet	rss = null;
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM KOD_ACIKLAMA   ORDER BY KOD ");
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public void kod_kayit(String kodu, String aciklama) throws ClassNotFoundException, SQLException {
		String sql  = "INSERT INTO KOD_ACIKLAMA (KOD,ACIKLAMA) " +
				" VALUES (?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, kodu);
		stmt.setString(2, aciklama);
		stmt.executeUpdate();
		stmt.close();
		
	}

	@Override
	public void kod_sil(String kod) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM KOD_ACIKLAMA WHERE KOD =N'" + kod + "'" ;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}

	@Override
	public ResultSet kons_pln() throws ClassNotFoundException, SQLException {
		ResultSet	rss = null;
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM KONS_ACIKLAMA   ORDER BY KONS ");
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public void kons_kayit(String kons, String aciklama,int paket_no) throws ClassNotFoundException, SQLException {
		String sql  = "INSERT INTO KONS_ACIKLAMA (KONS,ACIKLAMA) " +
				" VALUES (?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, kons);
		stmt.setString(2, aciklama);
		stmt.executeUpdate();
		
		
		sql  = "INSERT INTO PAKET_NO (Evrak_No,Konsimento) " +
				" VALUES (?,?)" ;
		stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, paket_no);
		stmt.setString(2, kons);
		stmt.executeUpdate();
		
		
		stmt.close();
		
	}

	@Override
	public int kons_sil(String kons) throws ClassNotFoundException, SQLException {
		 
		String sql = "DELETE FROM KONS_ACIKLAMA WHERE KONS =N'" + kons + "'" ;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		///
		ResultSet	rss = null;
		stmt = con.prepareStatement("SELECT Evrak_No FROM PAKET_NO  WHERE Konsimento =N'" + kons + "' ");
		rss = stmt.executeQuery();
		int result ;
		if (!rss.isBeforeFirst() ) {  
			result = 0 ;
		}
		else
		{
			rss.next();
			result = rss.getInt("Evrak_No");
		}
		///
		sql = "DELETE FROM PAKET_NO WHERE Konsimento =N'" + kons + "'" ;
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
		return  result  ;	

	}

	@Override
	public String kod_adi(String kod) throws ClassNotFoundException, SQLException {
		
		 
		ResultSet	rss = null;
		PreparedStatement stmt = con.prepareStatement("SELECT ACIKLAMA FROM KOD_ACIKLAMA  WHERE KOD =N'" + kod+ "' ");
		rss = stmt.executeQuery();
		String result ;
		if (!rss.isBeforeFirst() ) {  
			result = "" ;
		}
		else
		{
			rss.next();
			result = rss.getString("ACIKLAMA");
		}
		return result;	
		
	}

	@Override
	public ResultSet ker_kod_degisken_oku(String fieldd, String sno, String nerden)
			throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String sql =  "SELECT " + sno + "  AS KOD , " + fieldd + " FROM " + nerden + "" +
				" ORDER BY " + fieldd + "";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public ResultSet ker_kod_degisken_ara(String fieldd, String sno, String nerden, String arama)
			throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String sql =   "SELECT  " + fieldd + " FROM " + nerden + " WHERE " + sno + " = N'" + arama + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public ResultSet ker_kod_alt_grup_degisken_oku(int sno) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String sql =   "SELECT ALID_Y , ALT_GRUP FROM ALT_GRUP_DEGISKEN   " +
				" WHERE ANA_GRUP = N'" + sno + "' ORDER BY ALT_GRUP";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	 
	}

	@Override
	public boolean alt_grup_kontrol(int anagrp, int altgrp) throws ClassNotFoundException, SQLException {
		  
		ResultSet	rs = null;
		boolean result = true;
		String sql  = "" ;
		PreparedStatement stmt =null;
		sql = "SELECT  *  FROM KERESTE     WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		stmt = con.prepareStatement(sql);
		rs = stmt. executeQuery();
		if (!rs.isBeforeFirst() )   result = false ;
		return result;
	}

	@Override
	public void ker_degisken_alt_grup_sil(int ID) throws ClassNotFoundException, SQLException {
		  
		String sql = "DELETE ALT_GRUP_DEGISKEN    WHERE ALID_Y = '" + ID + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}

	@Override
	public void ker_kod_degisken_sil(String nerden, String hangi_Y, int sira)
			throws ClassNotFoundException, SQLException {
		  
		String sql = "DELETE " + nerden  + "    WHERE "+ hangi_Y +" = '" + sira + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}

	@Override
	public void ker_degisken_eski(String fieldd, String degisken_adi, String nerden, String sno, int ID)
			throws ClassNotFoundException, SQLException {
		  
		String sql =  "UPDATE " + nerden + "  SET " + fieldd + "  = N'" + degisken_adi + "'  WHERE " + sno + "  = '" + ID + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}

	@Override
	public void ker_degisken_kayit(String fieldd, String nerden, String degisken_adi, String sira)
			throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		int maks = 0 ;
		String sql =   "SELECT max(" +fieldd +")  as maks  FROM "+ nerden+ "" ; 
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		if (count  != 0)  
		{
			maks  = rss.getInt("maks");
		}
		else
		{
			maks  = 0 ;
		}
		sql  =  "INSERT INTO " + nerden + " (" + fieldd + "," + degisken_adi + ",USER) " +
				" VALUES (?,?,?)" ;
		stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, maks + 1);
		stmt.setString(2, sira);
		stmt.setString(3, GLOBAL.KULL_ADI);
		stmt.executeUpdate();
		stmt.close();
		
	}

	@Override
	public void ker_degisken_alt_grup_kayit(String alt_grup, int ana_grup) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		int maks =0 ;
		String sql =   "SELECT max(ALID_Y)  AS ALID_Y  FROM ALT_GRUP_DEGISKEN   " ;// +
		//	" WHERE ALT_GRUP = N'" + alt_grup + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		if (count  != 0) 
		{ 
			maks  = rss.getInt("ALID_Y");
		}
		else
		{
			maks  = 0 ;
		}
		sql  = "INSERT INTO ALT_GRUP_DEGISKEN (ALID_Y,ALT_GRUP,ANA_GRUP,USER) " +
				" VALUES (?,?,?,?)" ;
		stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setInt(1,maks + 1);
		stmt.setString(2, alt_grup);
		stmt.setInt(3,ana_grup);
		stmt.setString(4, GLOBAL.KULL_ADI);
		stmt.executeUpdate();
		stmt.close(); 
		
	}

	@Override
	public void ker_degisken_alt_grup_eski(String alt_grup, int ana_grup, int ID)
			throws ClassNotFoundException, SQLException {
		  
		String sql = "UPDATE ALT_GRUP_DEGISKEN  SET ALT_GRUP  = N'" + alt_grup + "' , ANA_GRUP  = N'" + ana_grup + "'  WHERE ALID_Y = '" + ID + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}

	
	@Override
	public String son_no_al(String cins) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String result ;
		String sql ;
		if (cins.equals("G")) {
			sql = "SELECT max(Evrak_No)  as NO FROM KERESTE  ";
		}
		else {
			sql = "SELECT max(Cikis_Evrak)  as NO FROM KERESTE  ";
		}
		
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		if (!rss.isBeforeFirst() ) {  
			result = ""  ;
		}
		else
		{
			rss.next();
			result = rss.getString("NO");
		}
		return result ;	
	}

	@Override
	public void ker_giris_sil(String eno) throws ClassNotFoundException, SQLException {
		 
		String sql =  " DELETE " +
				" FROM KERESTE " +
				" WHERE Evrak_No  ='" + eno + "'" ;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}

	@Override
	public void ker_kaydet(KER_BILGI kBILGI, String user) throws ClassNotFoundException, SQLException {
		 
		String sql  ="INSERT INTO KERESTE (Evrak_No,Barkod,Kodu,Paket_No,Konsimento,Miktar,Tarih,Kdv,Doviz,Fiat,Tutar,Kur,Cari_Firma,Adres_Firma,Iskonto " + //15
				" ,Tevkifat,Ana_Grup,Alt_Grup,Depo,Ozel_Kod,Izahat,Nakliyeci,USER,Cikis_Evrak,CTarih,CKdv,CDoviz,CFiat,CTutar,Ckur,CCari_Firma,CAdres_Firma " + //17
				" ,CIskonto,CTevkifat,CAna_Grup,CAlt_Grup,CDepo,COzel_Kod,CIzahat,CNakliyeci,CUSER,Mensei,Satir) " + //9
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		///////////////////
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1,kBILGI.getEvrak_No());
		stmt.setString(2, kBILGI.getBarkod());
		stmt.setString(3,kBILGI.getKodu());
		stmt.setString(4,kBILGI.getPaket_No());
		stmt.setString(5,kBILGI.getKonsimento());
		stmt.setDouble(6, kBILGI.getMiktar());
		stmt.setString(7,kBILGI.getTarih());
		stmt.setDouble(8, kBILGI.getKdv());
		stmt.setString(9,kBILGI.getDoviz());
		stmt.setDouble(10, kBILGI.getFiat());
		stmt.setDouble(11, kBILGI.getTutar());
		stmt.setDouble(12, kBILGI.getKur());
		stmt.setString(13,kBILGI.getCari_Firma());
		stmt.setString(14,kBILGI.getAdres_Firma());
		stmt.setDouble(15, kBILGI.getIskonto());
		stmt.setDouble(16, kBILGI.getTevkifat());
		stmt.setInt(17, kBILGI.getAna_Grup());
		stmt.setInt(18, kBILGI.getAlt_Grup());
		stmt.setInt(19, kBILGI.getDepo());
		stmt.setInt(20,kBILGI.getOzel_Kod());
		stmt.setString(21,kBILGI.getIzahat());
		stmt.setInt(22, kBILGI.getNakliyeci());
		stmt.setString(23,  user);
		stmt.setString(24,kBILGI.getCikis_Evrak());
		if(!kBILGI.getCTarih().equals(""))
		{
			stmt.setString(25,kBILGI.getCTarih());
		}
		else {
			stmt.setString(25,"1900.01.01");
		}
		stmt.setDouble(26, kBILGI.getCKdv());
		stmt.setString(27,kBILGI.getCDoviz());
		stmt.setDouble(28, kBILGI.getCFiat());
		stmt.setDouble(29, kBILGI.getCTutar());
		stmt.setDouble(30, kBILGI.getCKur());
		stmt.setString(31,kBILGI.getCCari_Firma());
		stmt.setString(32,kBILGI.getCAdres_Firma());
		stmt.setDouble(33, kBILGI.getCIskonto());
		stmt.setDouble(34, kBILGI.getCTevkifat());
		stmt.setInt(35, kBILGI.getCAna_Grup());
		stmt.setInt(36, kBILGI.getCAlt_Grup());
		stmt.setInt(37, kBILGI.getCDepo());
		stmt.setInt(38,kBILGI.getCOzel_Kod());
		stmt.setString(39,kBILGI.getCIzahat());
		stmt.setInt(40, kBILGI.getCNakliyeci());
		stmt.setString(41,  kBILGI.getCUSER());
		stmt.setInt(42, kBILGI.getMensei());
		stmt.setInt(43, kBILGI.getSatir());
		stmt.executeUpdate();
		stmt.close();
		
	}

	@Override
	public ResultSet ker_oku(String eno, String cins) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String sql = "";
		if (cins.equals("G")) 
		{
			 sql = "SELECT    Evrak_No  , Barkod  , Kodu , Paket_No , Konsimento  , Miktar , Tarih , Kdv  , Doviz  , Fiat   , Tutar  , Kur   , Cari_Firma , Adres_Firma   , Iskonto  , Tevkifat  "
					+ "	,Ana_Grup   , Alt_Grup  ,  Mensei , Depo   , Ozel_Kod  , Izahat   , Nakliyeci  ,USER "
					+ "	,Cikis_Evrak   , CTarih    , CKdv  , CDoviz   , CFiat  , CTutar  , CKur  , CCari_Firma  , CAdres_Firma  , CIskonto   , CTevkifat  "
					+ "	,CAna_Grup     , CAlt_Grup   ,CDepo  , COzel_Kod    , CIzahat   , CNakliyeci   , CUSER " 
					+ " FROM KERESTE   " 
					+ " WHERE Evrak_No  = N'" + eno + "'  ORDER BY  Satir"  ; // ORDER BY Paket_No ,Kodu , Satir
		}
		else {
			sql = "SELECT    Evrak_No  , Barkod  , Kodu , Paket_No , Konsimento  , Miktar , Tarih , Kdv  , Doviz  , Fiat   , Tutar  , Kur   , Cari_Firma , Adres_Firma   , Iskonto  , Tevkifat  "
					+ "	,Ana_Grup   , Alt_Grup  , Mensei  ,IFNULL((Select DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = KERESTE.Depo ) , '') AS Depo  , Ozel_Kod  , Izahat   , Nakliyeci  ,USER "
					+ "	,Cikis_Evrak   , CTarih    , CKdv  , CDoviz   , CFiat  , CTutar  , CKur  , CCari_Firma  , CAdres_Firma  , CIskonto   , CTevkifat  "
					+ "	,CAna_Grup     , CAlt_Grup   ,IFNULL((Select DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = KERESTE.CDepo ) , '') AS CDepo  , COzel_Kod    , CIzahat   , CNakliyeci   , CUSER ,Satir" 
					+ " FROM KERESTE   " 
					+ " WHERE Cikis_Evrak  = N'" + eno + "' ORDER BY  Satir " ; //ORDER BY Paket_No ,Satir
		}
		
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;	
	}

	@Override
	public void dipnot_sil(String ino, String cins, String gircik) throws ClassNotFoundException, SQLException {
		String sql = " DELETE " +
				" FROM DPN" +
				" WHERE Evrak_NO = N'" + ino + "'" +
				" AND Tip = N'" + cins + "'" +
				" AND Gir_Cik = '" + gircik + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}

	@Override
	public void dipnot_yaz(String eno, String bir, String iki, String uc, String tip, String gircik, String usr)
			throws ClassNotFoundException, SQLException {
		 
		String sql  ="INSERT INTO DPN (Evrak_No,Tip,Bir,Iki,Uc,Gir_Cik,USER) " +
				" VALUES (?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, eno);
		stmt.setString(2, tip);
		stmt.setString(3, bir);
		stmt.setString(4, iki);
		stmt.setString(5, uc);
		stmt.setString(6,gircik);
		stmt.setString(7, usr);
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	public ResultSet dipnot_oku(String ino, String cins, String gircik) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String sql =  "SELECT * " +
				" FROM DPN   " +
				" WHERE Evrak_NO = N'" + ino + "'" +
				" AND DPN.Tip = N'" + cins + "'" +
				" AND Gir_Cik = '" + gircik + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}

	@Override
	public void aciklama_sil(String evrcins, String evrno, String cins) throws ClassNotFoundException, SQLException {
		 
		String sql = " DELETE " +
				" FROM ACIKLAMA " +
				" WHERE EVRAK_CINS = N'" + evrcins + "'" +
				" AND EVRAK_NO = N'" + evrno + "'" +
				" AND Gir_Cik = N'" + cins + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}

	@Override
	public void aciklama_yaz(String evrcins, int satir, String evrno, String aciklama, String gircik)
			throws ClassNotFoundException, SQLException {
		 
		String sql  ="INSERT INTO ACIKLAMA (EVRAK_CINS,SATIR,EVRAK_NO,ACIKLAMA,Gir_Cik) " +
				" VALUES (?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, evrcins);
		stmt.setInt(2, satir);
		stmt.setString(3, evrno);
		stmt.setString(4, aciklama);
		stmt.setString(5, gircik);
		stmt.executeUpdate();
		stmt.close();
		
	}

	@Override
	public String aciklama_oku(String evrcins, int satir, String evrno, String gircik)
			throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String result ;
		String sql =     "SELECT * " +
				" FROM ACIKLAMA " +
				" WHERE EVRAK_NO = N'" + evrno + "'" +
				" AND SATIR = '" + satir + "'" +
				" AND EVRAK_CINS = '" + evrcins + "'" +
				" AND Gir_Cik = '" + gircik + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		if (!rss.isBeforeFirst() ) {  
			result = "" ;
		}
		else
		{
			rss.next();
			result = rss.getString("ACIKLAMA");
		}
		return result;	
	}

	@Override
	public ResultSet paket_oku(String pno) throws ClassNotFoundException, SQLException {
		 
		String[] token = pno.toString().split("-");
		ResultSet	rss = null;
		String sql = "SELECT    Evrak_No  , Barkod  , Kodu , Paket_No , Konsimento  , Miktar , Cikis_Evrak   , CTarih    , CKdv  , CDoviz   , CFiat  , CTutar  , CKur  " 
				+ ", CCari_Firma  , CAdres_Firma  , CIskonto   , CTevkifat , CAna_Grup     , CAlt_Grup   "
				+ "	 ,IFNULL((Select DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = KERESTE.CDepo ) , '') AS CDepo  , COzel_Kod    , CIzahat   , CNakliyeci   , CUSER ,Satir" 
				+ " FROM KERESTE   " 
				+ " WHERE Paket_No = N'" + token[0] + "' AND Konsimento = N'"+ token[1]  +"' ORDER BY Satir" ;
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);

		return rss;
	}

	@Override
	public void ker_cikis_sil(String eno) throws ClassNotFoundException, SQLException {
		 
		String sql = "UPDATE KERESTE SET Cikis_Evrak = '', CTarih = '1900.01.01',CKdv = 0,CDoviz ='',CFiat=0,Ctutar=0,CKur=0,CCari_Firma='',CAdres_Firma='' ," 
				+ " CIskonto=0,CTevkifat=0,CAna_Grup=0,CAlt_Grup=0,CDepo=0,COzel_Kod='',CIzahat='',CNakliyeci=0,CUSER=''"
				+ " WHERE Cikis_Evrak  ='" + eno + "'" ;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}

	@Override
	public void ker_cikis_kaydet(KER_BILGI kBILGI) throws ClassNotFoundException, SQLException {
		 
		String[] token = kBILGI.getPaket_No().toString().split("-");
		String sql = "UPDATE KERESTE SET " 
				+ " Cikis_Evrak = '"+ kBILGI.getCikis_Evrak() +"', CTarih = '"+ kBILGI.getCTarih() + "', " 
				+ " CKdv = '"+ kBILGI.getCKdv() + "',CDoviz ='"+ kBILGI.getCDoviz() + "', CFiat='"+ kBILGI.getCFiat() + "',Ctutar='"+ kBILGI.getCTutar() + "', " 
				+ " CKur='"+ kBILGI.getCKur() + "',CCari_Firma = '"+ kBILGI.getCCari_Firma() +"',CAdres_Firma='"+ kBILGI.getCAdres_Firma() +"' ," 
				+ " CIskonto="+ kBILGI.getCIskonto() + ",CTevkifat="+ kBILGI.getCTevkifat() + ",CAna_Grup="+ kBILGI.getCAna_Grup() + ",CAlt_Grup="+ kBILGI.getCAlt_Grup() + ", " 
				+ " CDepo="+ kBILGI.getCDepo() + ",COzel_Kod='"+ kBILGI.getCOzel_Kod() +"',CIzahat='"+ kBILGI.getCIzahat() +"',CNakliyeci="+ kBILGI.getCNakliyeci() + ", " 
				+ " CUSER='"+ kBILGI.getCUSER() +"'"
				+ " WHERE Paket_No  ='" + token[0] + "'"
				+ " AND Satir = '"+ kBILGI.getSatir() + "'" ;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}

	@Override
	public int evrak_no_al(String cins) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		int E_NUMBER ;
		String sql ="" ;
		if (cins.equals("G")) {
			sql = "SELECT max(Evrak_No + 1) AS NO  FROM KERESTE  ";
		}
		else {
			sql = "SELECT max(Cikis_Evrak + 1) AS NO  FROM KERESTE  ";
		}
		
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		if (!rss.isBeforeFirst() ) {  
			E_NUMBER = 0 ;
		}
		else
		{
			rss.next();
			E_NUMBER = rss.getInt("NO");
		}
		return E_NUMBER;	
	}

	@Override
	public ResultSet baslik_bak(String baslik, String ordr, String jkj, String k1, String k2, String f1,
			String f2, String t1, String t2,String dURUM,String e1,String e2) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String[] token = k1.toString().split("-");
		String ilks ,ilkk,ilkb,ilkg;
		ilks = token[0];
		ilkk = token[1];
		ilkb = token[2];
		ilkg = token[3];
		token = k2.toString().split("-");
		String sons,sonk,sonb,song;
		sons = token[0];
		sonk = token[1];
		sonb = token[2];
		song = token[3];
		String qweString = "";
		if(dURUM.equals("C"))
		{
			qweString = " Cikis_Evrak " ;
		}
		else {
			qweString = " Evrak_No " ;
		}
		String sql =   "SELECT "+ baslik + "  FROM KERESTE   " +
				" WHERE   " + jkj +
				" SUBSTRING(KERESTE.Kodu, 1, 2) >= '"+ilks +"' AND SUBSTRING(KERESTE.Kodu, 1, 2) <= '"+ sons +"' AND" +
				" SUBSTRING(KERESTE.Kodu, 4, 3) >= '"+ilkk +"' AND SUBSTRING(KERESTE.Kodu, 4, 3) <= '"+ sonk +"' AND" +
				" SUBSTRING(KERESTE.Kodu, 8, 4) >= '"+ilkb +"' AND SUBSTRING(KERESTE.Kodu, 8, 4) <= '"+ sonb +"' AND" +
				" SUBSTRING(KERESTE.Kodu, 13, 4) >= '"+ilkg +"' AND SUBSTRING(KERESTE.Kodu, 13, 4) <= '"+ song +"' " +
				" AND " + dURUM + "Cari_Firma between N'" + f1 + "' AND N'" + f2 + "'" +
				" AND " + qweString  + " between N'" + e1 + "' AND N'" + e2 + "'" +
				" AND " + dURUM + "Tarih BETWEEN '" + t1 + "'" +
				" AND  '"  + t2 + " 23:59:59.998'" +
				" " + ordr + " ";
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;	
	}

	@Override
	public ResultSet grp_rapor(String gruplama,String sstr_2, String sstr_4, String kur_dos, String qwq6,
			String qwq7, String qwq8, String k1, String k2, String s1, String s2, String jkj,
			String t1, String t2, String sstr_5, String sstr_1,String orderBY,String dURUM,String ko1, String ko2,String dpo,String grup,
			String e1 , String e2) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String[] token = k1.toString().split("-");
		String ilks ,ilkk,ilkb,ilkg;
		ilks = token[0];
		ilkk = token[1];
		ilkb = token[2];
		ilkg = token[3];
		token = k2.toString().split("-");
		String sons,sonk,sonb,song;
		sons = token[0];
		sonk = token[1];
		sonb = token[2];
		song = token[3];
		if(qwq6.equals(" Like  '%' "))
		{
			qwq6 =  " " ;
		}
		else {
			qwq6 = dURUM + "Ana_Grup " + qwq6 ;
		}
		if(qwq7.equals(" Like  '%' "))
		{
			qwq7 =  " " ;
		}
		else {
			qwq7 = " AND "+ dURUM + "Alt_Grup " + qwq7 ;
		}
		if(qwq8.equals(" Like  '%' "))
		{
			qwq8 =  " " ;
		}
		else {
			qwq8 = " AND "+ dURUM + "Ozel_Kod " + qwq8 ;
		}
		if(dpo.equals(" Like  '%' "))
		{
			dpo =  " " ;
		}
		else {
			dpo = " AND "+ dURUM + "Depo " + dpo + " AND ";
		}
		
		///
		String replaceString=sstr_1.replace('[',' ');//replaces all occurrences of 'a' to 'e'  
		String replaceString2=replaceString.replace(']',' ');//replaces all occurrences of 'a' to 'e'  
		String[] tokens =replaceString2.split(",");
		String cASE = "" ;
		for (String t : tokens)
		{
			cASE  = cASE + "Sum(CASE WHEN " + sstr_2 + "  = '" + t.trim() + "'  THEN " + sstr_4 + " ELSE 0 END) AS '"+ t.trim() + "',";
		}
		cASE = cASE.substring(0, cASE.length() - 1);
		String qweString = "" ;
		if(dURUM.equals("C"))
		{
			qweString = " Cikis_Evrak " ;
		}
		else {
			qweString = " Evrak_No " ;
		}
		///
		String sql =   "SELECT " + gruplama + " ," + cASE +
				" FROM KERESTE   " + kur_dos + 
				" WHERE   " + jkj + " " +
				   qwq6 + " "+qwq7 + " " +qwq8 + " " + dpo +
				" SUBSTRING(KERESTE.Kodu, 1, 2) >= '"+ilks +"' AND SUBSTRING(KERESTE.Kodu, 1, 2) <= '"+ sons +"' AND" +
				" SUBSTRING(KERESTE.Kodu, 4, 3) >= '"+ilkk +"' AND SUBSTRING(KERESTE.Kodu, 4, 3) <= '"+ sonk +"' AND" +
				" SUBSTRING(KERESTE.Kodu, 8, 4) >= '"+ilkb +"' AND SUBSTRING(KERESTE.Kodu, 8, 4) <= '"+ sonb +"' AND" +
				" SUBSTRING(KERESTE.Kodu, 13, 4) >= '"+ilkg +"' AND SUBSTRING(KERESTE.Kodu, 13, 4) <= '"+ song +"' " +
				" AND "+ dURUM + "Cari_Firma between N'" + s1 + "' AND N'" + s2 + "'" +
				" AND " + qweString  + " between N'" + e1 + "' AND N'" + e2 + "'" +
				" AND Konsimento between N'" + ko1 + "' AND N'" + ko2 + "'" +
				" AND  KERESTE."+ dURUM + "Tarih BETWEEN '" +t1 + "'" + " AND  '" + t2 + " 23:59:59.998'" +
				" GROUP BY " + grup ;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public ResultSet stok_rapor(KER_RAPOR_BILGI ker_rap_BILGI) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		
		String[] token = ker_rap_BILGI.getGKodu1().toString().split("-");
		String ilks ,ilkk,ilkb,ilkg;
		ilks = token[0];
		ilkk = token[1];
		ilkb = token[2];
		ilkg = token[3];
		token = ker_rap_BILGI.getGKodu2().toString().split("-");
		String sons,sonk,sonb,song;
		sons = token[0];
		sonk = token[1];
		sonb = token[2];
		song = token[3];
		String sql =  " SELECT  Evrak_No  "
				+ " , Barkod  "
				+ " , Kodu  "
				+ " , Paket_No  "
				+ " , Konsimento  "
				+ " , Miktar  "
				+ " ,(((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)  as m3"
				+ " ,DATE_FORMAT(Tarih, '%Y.%m.%d')  AS TARIH "
				+ " , Kdv  "
				+ " , Doviz  "
				+ " , Fiat  "
				+ " , Tutar  "
				+ " , Kur  "
				+ " , Cari_Firma  "
				+ " , Adres_Firma  "
				+ " , Iskonto  "
				+ " , Tevkifat  "
				+ " ,IFNULL((SELECT ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = KERESTE.Ana_Grup),'') Ana_Grup "
				+ " ,IFNULL((SELECT ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = KERESTE.Alt_Grup),'') AS Alt_Grup "
				+ " ,(SELECT MENSEI FROM MENSEI_DEGISKEN WHERE MENSEI_DEGISKEN.MEID_Y = KERESTE.Mensei ) as Mensei  " 
				+ " ,(SELECT DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = KERESTE.Depo ) as Depo  " 
				+ " ,IFNULL((SELECT OZEL_KOD_1 FROM OZ_KOD_1_DEGISKEN WHERE OZ_KOD_1_DEGISKEN.OZ1ID_Y = KERESTE.Ozel_Kod),'') Ozel_Kod "
				+ " , Izahat  "
				+ " ,(SELECT UNVAN FROM NAKLIYECI WHERE NAKLIYECI.NAKID_Y = KERESTE.Nakliyeci ) as Nakliyeci  " 
				+ " ,USER "
				+ " , Cikis_Evrak  "
				+ " , IF(DATE_FORMAT(CTarih, '%Y.%m.%d')= '1900.01.01','',DATE_FORMAT(CTarih, '%Y.%m.%d'))  AS CTARIH "
				+ " , CKdv  "
				+ " , CDoviz  "
				+ " , CFiat  "
				+ " , CTutar  "
				+ " , CKur  "
				+ " , CCari_Firma  "
				+ " , CAdres_Firma  "
				+ " , CIskonto  "
				+ " , CTevkifat  "
				+ " ,IFNULL((SELECT ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = KERESTE.CAna_Grup),'') AS C_Ana_Grup "
				+ "	,IFNULL((SELECT ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = KERESTE.CAlt_Grup),'') AS C_Alt_Grup "
				+ " ,IFNULL((SELECT DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = KERESTE.CDepo),'') AS C_Depo "
				+ " ,IFNULL((SELECT OZEL_KOD_1 FROM OZ_KOD_1_DEGISKEN WHERE OZ_KOD_1_DEGISKEN.OZ1ID_Y = KERESTE.COzel_Kod),'') COzel_Kod "
				+ " , CIzahat  "
				+ " ,(SELECT UNVAN FROM NAKLIYECI WHERE NAKLIYECI.NAKID_Y = KERESTE.CNakliyeci ) as C_Nakliyeci  " 
				+ " , CUSER  " 
				+ " FROM KERESTE  " //WITH (INDEX (IX_KERESTE)) 
				+ " WHERE " 
				+ " Tarih BETWEEN '" + ker_rap_BILGI.getGTarih1() + "'" + " AND  '" + ker_rap_BILGI.getGTarih2() + " 23:59:59.998' AND" 
				+ " SUBSTRING(KERESTE.Kodu, 1, 2) >= '"+ilks +"' AND SUBSTRING(KERESTE.Kodu, 1, 2) <= '"+ sons +"' AND" 
				+ " SUBSTRING(KERESTE.Kodu, 4, 3) >= '"+ilkk +"' AND SUBSTRING(KERESTE.Kodu, 4, 3) <= '"+ sonk +"' AND" 
				+ " SUBSTRING(KERESTE.Kodu, 8, 4) >= '"+ilkb +"' AND SUBSTRING(KERESTE.Kodu, 8, 4) <= '"+ sonb +"' AND" 
				+ " SUBSTRING(KERESTE.Kodu, 13, 4) >= '"+ilkg +"' AND SUBSTRING(KERESTE.Kodu, 13, 4) <= '"+ song +"' AND " 
				+ " Paket_No between N'" + ker_rap_BILGI.getPaket_No1() + "' AND N'" + ker_rap_BILGI.getPaket_No2() + "' AND " 
				+ " Cari_Firma between N'" + ker_rap_BILGI.getGCari_Firma1() + "' AND N'" + ker_rap_BILGI.getGCari_Firma2() + "' AND" 
				+ " Evrak_No between N'" + ker_rap_BILGI.getEvrak_No1() + "' AND N'" + ker_rap_BILGI.getEvrak_No2() + "' AND" 
				+ " Ana_Grup " + ker_rap_BILGI.getGAna_Grup()  + " AND" 
				+ " Alt_Grup " + ker_rap_BILGI.getGAlt_Grup()  + " AND" 
				+ " Depo " + ker_rap_BILGI.getDepo()  + " AND" 
				+ " Ozel_Kod " + ker_rap_BILGI.getOzel_Kod() + " AND" 
				+ " CTarih BETWEEN '" + ker_rap_BILGI.getCTarih1() + "'" + " AND  '" + ker_rap_BILGI.getCTarih2() + " 23:59:59.998' AND" 
				+ " CCari_Firma between N'" + ker_rap_BILGI.getCCari_Firma1() + "' AND N'" + ker_rap_BILGI.getCCari_Firma2() + "' AND" 
				+ " Cikis_Evrak between N'" + ker_rap_BILGI.getCEvrak_No1() + "' AND N'" + ker_rap_BILGI.getCEvrak_No2() + "' AND" 
				+ " CAna_Grup " + ker_rap_BILGI.getCAna_Grup()  + " AND" 
				+ " CAlt_Grup " + ker_rap_BILGI.getCAlt_Grup()  + " AND" 
				+ " CDepo " + ker_rap_BILGI.getCDepo()  + " AND " 
				+ " COzel_Kod " + ker_rap_BILGI.getCOzel_Kod() ; 
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public ResultSet ker_barkod_kod_oku(String sira) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String sql =   "SELECT  DISTINCT Paket_No , Konsimento FROM KERESTE   " +
				" WHERE Cikis_Evrak = '' " +
				" ORDER by " + sira;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public String kons_adi(String kons) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		PreparedStatement stmt = con.prepareStatement("SELECT ACIKLAMA FROM KONS_ACIKLAMA  WHERE KONS =N'" + kons + "' ");
		rss = stmt.executeQuery();
		String result ;
		if (!rss.isBeforeFirst() ) {  
			result = "" ;
		}
		else
		{
			rss.next();
			result = rss.getString("ACIKLAMA");
		}
		return result;	
	}

	@Override
	public ResultSet fat_rapor(KER_RAPOR_BILGI ker_rap_BILGI) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String[] token = ker_rap_BILGI.getGKodu1().toString().split("-");
		String ilks ,ilkk,ilkb,ilkg;
		ilks = token[0];
		ilkk = token[1];
		ilkb = token[2];
		ilkg = token[3];
		token = ker_rap_BILGI.getGKodu2().toString().split("-");
		String sons,sonk,sonb,song;
		sons = token[0];
		sonk = token[1];
		sonb = token[2];
		song = token[3];
		String hANGI = "" ;
		String eVRAKNO = "" ;
		String aLsAT = "" ;
		String dURUM = "" ;
		if (ker_rap_BILGI.getgIRcIK().equals("G"))
		{
			hANGI = "" ;
			eVRAKNO = "Evrak_No" ;
			aLsAT = "Alis" ;
			dURUM =   " Cikis_Evrak between N'" + ker_rap_BILGI.getCEvrak_No1() + "' AND N'" + ker_rap_BILGI.getCEvrak_No2() + "' AND" ;
		}
		else 
		{
			hANGI = "C" ;
			eVRAKNO = "Cikis_Evrak" ;
			aLsAT = "Satis";
			dURUM =   " Cikis_Evrak <> '' AND" ;   //  DATE_FORMAT(" + hANGI + "Tarih, '%Y.%m.%d')  AS TARIH
		}
		String sql =   " SELECT "+ eVRAKNO +"   ,'"+ aLsAT +"' as Hareket,DATE_FORMAT(" + hANGI + "Tarih, '%Y.%m.%d')  AS Tarih ,(SELECT   UNVAN FROM  ok_car" + BAGLAN.cariDizin.kOD + ".HESAP WHERE HESAP.HESAP = KERESTE." + hANGI + "Cari_Firma  ) as Unvan  ," + hANGI + "Adres_Firma as Adres_Firma," + hANGI + "Doviz as Doviz, " 
				+" SUM( (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000))  as m3 ," 
				+" SUM(((((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3) ,DECIMAL)  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4) ,DECIMAL)  ) * Miktar)/1000000000)) * "+ hANGI+"Fiat ) as Tutar, " 
				+" SUM(((((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) * "+ hANGI+"Fiat  ) - SUM(((((((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3) ,DECIMAL)  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) * "+ hANGI+"Fiat  ) * " + hANGI + "Iskonto)/100) as Iskontolu_Tutar ," 
				+" SUM((((" + hANGI+"Fiat * (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) - (("+ hANGI+"Fiat * (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) * "+ hANGI+"Iskonto)/100) * "+ hANGI+"Kdv)/100)  AS Kdv_Tutar ," 
				+" SUM((" + hANGI+"Fiat * (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3) ,DECIMAL)  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4) ,DECIMAL)  ) * Miktar)/1000000000)) - (("+ hANGI+"Fiat * (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) * "+ hANGI+"Iskonto)/100 +   ((("+ hANGI+"Fiat * (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) - (("+ hANGI+"Fiat * (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) * "+ hANGI+"Iskonto) / 100) * "+ hANGI+"Kdv ) / 100)    as Toplam_Tutar " 				
				+" FROM KERESTE   " 
				+" WHERE " 
				+" Tarih BETWEEN '" + ker_rap_BILGI.getGTarih1() + "'" + " AND  '" + ker_rap_BILGI.getGTarih2() + " 23:59:59.998' AND" 
				+" SUBSTRING(KERESTE.Kodu, 1, 2) >= '"+ilks +"' AND SUBSTRING(KERESTE.Kodu, 1, 2) <= '"+ sons +"' AND" 
				+" SUBSTRING(KERESTE.Kodu, 4, 3) >= '"+ilkk +"' AND SUBSTRING(KERESTE.Kodu, 4, 3) <= '"+ sonk +"' AND" 
				+" SUBSTRING(KERESTE.Kodu, 8, 4) >= '"+ilkb +"' AND SUBSTRING(KERESTE.Kodu, 8, 4) <= '"+ sonb +"' AND" 
				+" SUBSTRING(KERESTE.Kodu, 13, 4) >= '"+ilkg +"' AND SUBSTRING(KERESTE.Kodu, 13, 4) <= '"+ song +"' AND " 
				+" Paket_No between N'" + ker_rap_BILGI.getPaket_No1() + "' AND N'" + ker_rap_BILGI.getPaket_No2() + "' AND " 
				+" Cari_Firma between N'" + ker_rap_BILGI.getGCari_Firma1() + "' AND N'" + ker_rap_BILGI.getGCari_Firma2() + "' AND" 
				+" Evrak_No between N'" + ker_rap_BILGI.getEvrak_No1() + "' AND N'" + ker_rap_BILGI.getEvrak_No2() + "' AND" 
				+" Ana_Grup " + ker_rap_BILGI.getGAna_Grup()  + " AND" 
				+" Alt_Grup " + ker_rap_BILGI.getGAlt_Grup()  + " AND" 
				+" Depo " + ker_rap_BILGI.getDepo()  + " AND" 
				+" Ozel_Kod " + ker_rap_BILGI.getOzel_Kod() + " AND" 
				+" CTarih BETWEEN '" + ker_rap_BILGI.getCTarih1() + "'" + " AND  '" + ker_rap_BILGI.getCTarih2() + " 23:59:59.998' AND" 
				+" CCari_Firma between N'" + ker_rap_BILGI.getCCari_Firma1() + "' AND N'" + ker_rap_BILGI.getCCari_Firma2() + "' AND" 
				+" " + dURUM
				+" CAna_Grup " + ker_rap_BILGI.getCAna_Grup()  + " AND" 
				+" CAlt_Grup " + ker_rap_BILGI.getCAlt_Grup()  + " AND" 
				+" CDepo " + ker_rap_BILGI.getCDepo()  + " AND " 
				+" COzel_Kod " + ker_rap_BILGI.getCOzel_Kod() 
				+" GROUP BY " + eVRAKNO + ", " + hANGI + "Tarih ," + hANGI + "Cari_Firma," + hANGI + "Adres_Firma," + hANGI + "Doviz  " 
				+" ORDER BY  " + eVRAKNO;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public ResultSet fat_detay_rapor(String fno, String turu) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String fAT_TUR = "" ;
		String hANGI = "" ;
		String eVRAKNO = "" ;
		if(turu.equals("Alis"))
		{
			fAT_TUR = " Evrak_No = '" + fno + "' " ;
			hANGI = "" ;
			eVRAKNO = "Evrak_No" ;
		}
		else {
			fAT_TUR = " Cikis_Evrak = '" + fno + "' " ;
			hANGI = "C" ;
			eVRAKNO = "Cikis_Evrak" ;
		}
		String sql =  " SELECT  " + eVRAKNO + "  "
				+ " , Barkod  "
				+ " , Kodu  "
				+ " , Paket_No  "
				+ " , Miktar  "
				+ " ,(((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3) ,DECIMAL)  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)  as m3"
				+ " , " + hANGI + "Kdv  "
				+ " , " + hANGI + "Doviz  "
				+ " , " + hANGI + "Fiat  "
				+ " , " + hANGI + "Tutar  "
				+ " , " + hANGI + "Kur  "
				+ " , " + hANGI + "Cari_Firma  "
				+ " , " + hANGI + "Adres_Firma  "
				+ " , " + hANGI + "Iskonto  "
				+ " , " + hANGI + "Tevkifat  "
				+ " ,IFNULL((SELECT ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = KERESTE." + hANGI + "Ana_Grup),'') AS " + hANGI + "_Ana_Grup "
				+ "	,IFNULL((SELECT ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = KERESTE." + hANGI + "Alt_Grup),'') AS " + hANGI + "_Alt_Grup "
				+ " ,IFNULL((SELECT MENSEI FROM MENSEI_DEGISKEN WHERE MENSEI_DEGISKEN.MEID_Y = KERESTE." + hANGI + "Mensei),'') AS " + hANGI + "_Mensei "
				+ " ,IFNULL((SELECT DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = KERESTE." + hANGI + "Depo),'') AS " + hANGI + "_Depo "
				+ " ,IFNULL((SELECT OZEL_KOD_1 FROM OZ_KOD_1_DEGISKEN WHERE OZ_KOD_1_DEGISKEN.OZ1ID_Y = KERESTE."+ hANGI +"Ozel_Kod),'') Ozel_Kod "
				+ " , " + hANGI + "Izahat  "
				+ " ,(SELECT UNVAN FROM NAKLIYECI WHERE NAKLIYECI.NAKID_Y = KERESTE." + hANGI + "Nakliyeci ) as " + hANGI + "_Nakliyeci  " 
				+ " , " + hANGI + "USER  " 
				+ " FROM KERESTE    " 
				+ " WHERE "  + fAT_TUR  ; 
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public ResultSet fat_rapor_fat_tar(KER_RAPOR_BILGI ker_rap_BILGI, String qw1, String qw2, String qw3)
			throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String[] token = ker_rap_BILGI.getGKodu1().toString().split("-");
		String ilks ,ilkk,ilkb,ilkg;
		ilks = token[0];
		ilkk = token[1];
		ilkb = token[2];
		ilkg = token[3];
		token = ker_rap_BILGI.getGKodu2().toString().split("-");
		String sons,sonk,sonb,song;
		sons = token[0];
		sonk = token[1];
		sonb = token[2];
		song = token[3];
		String hANGI = "" ;
		String eVRAKNO = "" ;
		String aLsAT = "" ;
		String dURUM = "" ;
		if (ker_rap_BILGI.getgIRcIK().equals("G"))
		{
			hANGI = "" ;
			eVRAKNO = "Evrak_No" ;
			aLsAT = "Alis" ;
		    dURUM =   " Cikis_Evrak between N'" + ker_rap_BILGI.getCEvrak_No1() + "' AND N'" + ker_rap_BILGI.getCEvrak_No2() + "' AND" ;
		}
		else {
			hANGI = "C" ;
			eVRAKNO = "Cikis_Evrak" ;
			aLsAT = "Satis";
			dURUM =   " Cikis_Evrak <> '' AND" ;  

		}
		String sql =  " SELECT "+ eVRAKNO +",'"+ aLsAT +"' as Hareket,DATE_FORMAT(" + hANGI + "Tarih, '%Y.%m.%d')  AS Tarih " 
				+" " + qw1 + "" 
				+" " + qw2 + " , " 
				+" SUM( (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000))  as m3 " 
				+" ,SUM(" + hANGI+"Fiat * (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) as Tutar " 
				+" ,SUM((" + hANGI+"Fiat * (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3) ,DECIMAL)  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) - (("+ hANGI+"Fiat *   (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) * "+ hANGI+"Iskonto)/100) as Iskontolu_Tutar  " 
				+" ,SUM((((" + hANGI+"Fiat * (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) - (("+ hANGI+"Fiat * (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) * "+ hANGI+"Iskonto)/100) * "+ hANGI+"Kdv)/100)  AS Kdv_Tutar " 
				+" ,SUM((" + hANGI+"Fiat * (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3) ,DECIMAL)  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) - (("+ hANGI+"Fiat *   (((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) * "+ hANGI+"Iskonto)/100 +   ((("+ hANGI+"Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) - (("+ hANGI+"Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL ) ) * Miktar)/1000000000)) * "+ hANGI+"Iskonto) / 100) * "+ hANGI+"Kdv ) / 100)    as Toplam_Tutar " 
				+" FROM KERESTE   " 
				+" WHERE " 
				+" Tarih BETWEEN '" + ker_rap_BILGI.getGTarih1() + "'" + " AND  '" + ker_rap_BILGI.getGTarih2() + " 23:59:59.998' AND" 
				+" SUBSTRING(KERESTE.Kodu, 1, 2) >= '"+ilks +"' AND SUBSTRING(KERESTE.Kodu, 1, 2) <= '"+ sons +"' AND" 
				+" SUBSTRING(KERESTE.Kodu, 4, 3) >= '"+ilkk +"' AND SUBSTRING(KERESTE.Kodu, 4, 3) <= '"+ sonk +"' AND" 
				+" SUBSTRING(KERESTE.Kodu, 8, 4) >= '"+ilkb +"' AND SUBSTRING(KERESTE.Kodu, 8, 4) <= '"+ sonb +"' AND" 
				+" SUBSTRING(KERESTE.Kodu, 13, 4) >= '"+ilkg +"' AND SUBSTRING(KERESTE.Kodu, 13, 4) <= '"+ song +"' AND " 
				+" Paket_No between N'" + ker_rap_BILGI.getPaket_No1() + "' AND N'" + ker_rap_BILGI.getPaket_No2() + "' AND " 
				+" Cari_Firma between N'" + ker_rap_BILGI.getGCari_Firma1() + "' AND N'" + ker_rap_BILGI.getGCari_Firma2() + "' AND" 
				+" Evrak_No between N'" + ker_rap_BILGI.getEvrak_No1() + "' AND N'" + ker_rap_BILGI.getEvrak_No2() + "' AND" 
				+" Ana_Grup " + ker_rap_BILGI.getGAna_Grup()  + " AND" 
				+" Alt_Grup " + ker_rap_BILGI.getGAlt_Grup()  + " AND" 
				+" Depo " + ker_rap_BILGI.getDepo()  + " AND" 
				+" Ozel_Kod " + ker_rap_BILGI.getOzel_Kod() + " AND" 
				+" CTarih BETWEEN '" + ker_rap_BILGI.getCTarih1() + "'" + " AND  '" + ker_rap_BILGI.getCTarih2() + " 23:59:59.998' AND" 
				+" CCari_Firma between N'" + ker_rap_BILGI.getCCari_Firma1() + "' AND N'" + ker_rap_BILGI.getCCari_Firma2() + "' AND" 
				+" " + dURUM
				+" CAna_Grup " + ker_rap_BILGI.getCAna_Grup()  + " AND" 
				+" CAlt_Grup " + ker_rap_BILGI.getCAlt_Grup()  + " AND" 
				+" CDepo " + ker_rap_BILGI.getCDepo()  + " AND " 
				+" COzel_Kod " + ker_rap_BILGI.getCOzel_Kod() 
				+" GROUP BY " + qw3 + "" 
				+" ORDER BY  " + qw3 + "";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public ResultSet fat_rapor_cari_kod(KER_RAPOR_BILGI ker_rap_BILGI, String qw1, String qw2, String qw3)
			throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String[] token = ker_rap_BILGI.getGKodu1().toString().split("-");
		String ilks ,ilkk,ilkb,ilkg;
		ilks = token[0];
		ilkk = token[1];
		ilkb = token[2];
		ilkg = token[3];
		token = ker_rap_BILGI.getGKodu2().toString().split("-");
		String sons,sonk,sonb,song;
		sons = token[0];
		sonk = token[1];
		sonb = token[2];
		song = token[3];
		String hANGI = "" ;
		String aLsAT = "" ;
		String dURUM = "" ;
		if (ker_rap_BILGI.getgIRcIK().equals("G"))
		{
			hANGI = "" ;
			aLsAT = "Alis" ;
		    dURUM =   " Cikis_Evrak between N'" + ker_rap_BILGI.getCEvrak_No1() + "' AND N'" + ker_rap_BILGI.getCEvrak_No2() + "' AND" ;
		}
		else {
			hANGI = "C" ;
			aLsAT = "Satis";
			dURUM =   " Cikis_Evrak <> '' AND" ;
		}
		String sql =  " SELECT  " + qw2 + " ,'"+ aLsAT +"' as Hareket " 
				+"  " + qw1 + " ," 
				+" SUM( (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000))  as m3 ," 
				+" SUM(" + hANGI+"Fiat *    (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) as Tutar ," 
				+" SUM((" + hANGI+"Fiat *   (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) - (("+ hANGI+"Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) * "+ hANGI+"Iskonto)/100) as Iskontolu_Tutar  ," 
				+" SUM((((" + hANGI+"Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) - (("+ hANGI+"Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) * "+ hANGI+"Iskonto)/100) * "+ hANGI+"Kdv)/100)  AS Kdv_Tutar ," 
				+" SUM((" + hANGI+"Fiat *   (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) - (("+ hANGI+"Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) * "+ hANGI+"Iskonto)/100 +   ((("+ hANGI+"Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) - (("+ hANGI+"Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) * "+ hANGI+"Iskonto) / 100) * "+ hANGI+"Kdv ) / 100)    as Toplam_Tutar " 				
				+" FROM KERESTE   " 
				+" WHERE " 
				+" Tarih BETWEEN '" + ker_rap_BILGI.getGTarih1() + "'" + " AND  '" + ker_rap_BILGI.getGTarih2() + " 23:59:59.998' AND" 
				+" SUBSTRING(KERESTE.Kodu, 1, 2) >= '"+ilks +"' AND SUBSTRING(KERESTE.Kodu, 1, 2) <= '"+ sons +"' AND" 
				+" SUBSTRING(KERESTE.Kodu, 4, 3) >= '"+ilkk +"' AND SUBSTRING(KERESTE.Kodu, 4, 3) <= '"+ sonk +"' AND" 
				+" SUBSTRING(KERESTE.Kodu, 8, 4) >= '"+ilkb +"' AND SUBSTRING(KERESTE.Kodu, 8, 4) <= '"+ sonb +"' AND" 
				+" SUBSTRING(KERESTE.Kodu, 13, 4) >= '"+ilkg +"' AND SUBSTRING(KERESTE.Kodu, 13, 4) <= '"+ song +"' AND " 
				+" Paket_No between N'" + ker_rap_BILGI.getPaket_No1() + "' AND N'" + ker_rap_BILGI.getPaket_No2() + "' AND " 
				+" Cari_Firma between N'" + ker_rap_BILGI.getGCari_Firma1() + "' AND N'" + ker_rap_BILGI.getGCari_Firma2() + "' AND" 
				+" Evrak_No between N'" + ker_rap_BILGI.getEvrak_No1() + "' AND N'" + ker_rap_BILGI.getEvrak_No2() + "' AND" 
				+" Ana_Grup " + ker_rap_BILGI.getGAna_Grup()  + " AND" 
				+" Alt_Grup " + ker_rap_BILGI.getGAlt_Grup()  + " AND" 
				+" Depo " + ker_rap_BILGI.getDepo()  + " AND" 
				+" Ozel_Kod " + ker_rap_BILGI.getOzel_Kod() + " AND" 
				+" CTarih BETWEEN '" + ker_rap_BILGI.getCTarih1() + "'" + " AND  '" + ker_rap_BILGI.getCTarih2() + " 23:59:59.998' AND" 
				+" CCari_Firma between N'" + ker_rap_BILGI.getCCari_Firma1() + "' AND N'" + ker_rap_BILGI.getCCari_Firma2() + "' AND" 
				+" " + dURUM
				+" CAna_Grup " + ker_rap_BILGI.getCAna_Grup()  + " AND" 
				+" CAlt_Grup " + ker_rap_BILGI.getCAlt_Grup()  + " AND" 
				+" CDepo " + ker_rap_BILGI.getCDepo()  + " AND " 
				+" COzel_Kod " + ker_rap_BILGI.getCOzel_Kod() 
				+" GROUP BY " + qw2  
				+" ORDER BY  " + qw2 + "";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public ResultSet urun_detay(KER_RAPOR_BILGI ker_rap_BILGI) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String[] token = ker_rap_BILGI.getGKodu1().toString().split("-");
		String ilks ,ilkk,ilkb,ilkg;
		ilks = token[0];
		if (ilks.equals("00")) {
			ilks = "";
		}
		ilkk = token[1];
		if (ilkk.equals("000")) {
			ilkk = "";
		}
		ilkb = token[2];
		if (ilkb.equals("0000")) {
			ilkb = "";
		}
		ilkg = token[3];
		if (ilkg.equals("0000")) {
			ilkg = "";
		}
		String sql =  " SELECT CAST(0 AS UNSIGNED),Evrak_No  "
				+ " ,Barkod  "
				+ " ,Kodu  "
				+ " ,Paket_No  "
				+ " ,Konsimento  "
				+ " ,Miktar  "
				+ " ,(((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT( SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL)  ) * Miktar)/1000000000)  as m3"
				+ " ,DATE_FORMAT(Tarih, '%Y.%m.%d')  AS TARIH "
				+ " ,Kdv  "
				+ " ,Doviz  "
				+ " ,Fiat  "
				+ " ,Tutar  "
				+ " ,Kur  "
				+ " ,Cari_Firma  "
				+ " ,Adres_Firma  "
				+ " ,Iskonto  "
				+ " ,Tevkifat  "
				+ " ,IFNULL((SELECT ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = KERESTE.Ana_Grup),'') Ana_Grup "
				+ " ,IFNULL((SELECT ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = KERESTE.Alt_Grup),'') AS Alt_Grup "
				+ " ,IFNULL((SELECT MENSEI FROM MENSEI_DEGISKEN WHERE MENSEI_DEGISKEN.MEID_Y = KERESTE.Mensei),'') AS Mensei "
				+ " ,(SELECT DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = KERESTE.Depo ) as Depo  " 
				+ " ,IFNULL((SELECT OZEL_KOD_1 FROM OZ_KOD_1_DEGISKEN WHERE OZ_KOD_1_DEGISKEN.OZ1ID_Y = KERESTE.Ozel_Kod),'') Ozel_Kod "
				+ " ,Izahat  "
				+ " ,(SELECT UNVAN FROM NAKLIYECI WHERE NAKLIYECI.NAKID_Y = KERESTE.Nakliyeci ) as Nakliyeci  " 
				+ " ,USER "
				+ " ,Cikis_Evrak  "
				+ " ,IF(DATE_FORMAT(CTarih, '%Y.%m.%d')= '1900.01.01','',DATE_FORMAT(CTarih, '%Y.%m.%d'))  AS CTARIH "
				+ " ,CKdv  "
				+ " ,CDoviz  "
				+ " ,CFiat  "
				+ " ,CTutar  "
				+ " ,CKur  "
				+ " ,CCari_Firma  "
				+ " ,CAdres_Firma  "
				+ " ,CIskonto  "
				+ " ,CTevkifat  "
				+ " ,IFNULL((SELECT ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = KERESTE.CAna_Grup),'') AS C_Ana_Grup "
				+ "	,IFNULL((SELECT ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = KERESTE.CAlt_Grup),'') AS C_Alt_Grup "
				+ " ,IFNULL((SELECT DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = KERESTE.CDepo),'') AS C_Depo "
				+ " ,IFNULL((SELECT OZEL_KOD_1 FROM OZ_KOD_1_DEGISKEN WHERE OZ_KOD_1_DEGISKEN.OZ1ID_Y = KERESTE.COzel_Kod),'') COzel_Kod "
				+ " ,CIzahat  "
				+ " ,(SELECT UNVAN FROM NAKLIYECI WHERE NAKLIYECI.NAKID_Y = KERESTE.CNakliyeci ) as C_Nakliyeci  " 
				+ " ,CUSER ,Satir" 
				+ " FROM KERESTE    " 
				+ " WHERE " 
				+ " SUBSTRING(KERESTE.Kodu, 1, 2) like '"+ ilks +"%'  AND" 
				+ " SUBSTRING(KERESTE.Kodu, 4, 3) like '"+ilkk +"%' AND" 
				+ " SUBSTRING(KERESTE.Kodu, 8, 4) like '"+ilkb +"%' AND" 
				+ " SUBSTRING(KERESTE.Kodu, 13, 4) like '"+ilkg +"%'  AND" 
				+ " Paket_No like N'"+ ker_rap_BILGI.getPaket_No1().toString()+ "%' AND " 
				+ " Konsimento like N'"+ ker_rap_BILGI.getKonsimento1().toString() + "%'"  ; 
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public void ker_kod_degis(String paket_No, String kon, String yenikod,int satir) throws ClassNotFoundException, SQLException {
		 
		String sql = "UPDATE KERESTE  " 
				+ " SET  Kodu = CONCAT(N'" + yenikod + "' , SUBSTRING (Kodu, 3,14))" 
				+ " WHERE  Paket_No = N'" + paket_No + "' AND Konsimento = N'" + kon + "' AND " 
				+ " Satir =" + satir;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}

	@Override
	public void degisken_degistir(int anagrp, int altgrp, int anaygrp, int altygrp,String durum)
			throws ClassNotFoundException, SQLException {
		String sql = "" ;
		if(durum.equals("G"))
		{
			sql = "UPDATE KERESTE  SET Ana_Grup = '" + anaygrp + "'  , Alt_Grup = '" + altygrp + "'  WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		}
		else if(durum.equals("C"))
		{
			sql = "UPDATE KERESTE  SET CAna_Grup = '" + anaygrp + "'  , CAlt_Grup = '" + altygrp + "'  WHERE CAna_Grup = '" + anagrp + "'  AND  CAlt_Grup = '" + altgrp + "' ";
		}
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}

	@Override
	public String[] kod_aciklama_bul(String paket, String kons) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		PreparedStatement stmt = con.prepareStatement("SELECT DISTINCT (SELECT ACIKLAMA FROM KOD_ACIKLAMA WHERE Kod = SUBSTRING(KERESTE.Kodu,0,3)) as ACIKLAMA ,"
					+ " (SELECT ACIKLAMA FROM KONS_ACIKLAMA WHERE KONS = '" + kons + "') as KONS_ACIKLAMA, Kodu"
					+ " FROM KERESTE   " 
					+ " WHERE Paket_No ='" + paket + "' and  Konsimento = '" + kons + "' ");
		rss = stmt.executeQuery();
		String result[] = {"","",""} ;
		if (!rss.isBeforeFirst() ) {  
			result[0] = "" ;
			result[1] = "" ;
			result[2] = "" ;
		}
		else
		{
			rss.next();
			result[0] = rss.getString("ACIKLAMA");
			result[1] = rss.getString("KONS_ACIKLAMA");
			result[2] = rss.getString("Kodu");
		}
		return result;	
	}

	@Override
	public ResultSet ort_diger_kodu(String yu, String qwq6, String qwq7, String m1, String m2, String s1, String s2,
			String k1, String k2, String t1, String t2, String kurkod, String kurc, String iu,String hANGI,boolean kurlu)
			throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String[] token = k1.toString().split("-");
		String ilks ,ilkk,ilkb,ilkg;
		ilks = token[0];
		ilkk = token[1];
		ilkb = token[2];
		ilkg = token[3];
		token = k2.toString().split("-");
		String sons,sonk,sonb,song;
		sons = token[0];
		sonk = token[1];
		sonb = token[2];
		song = token[3];
		String dURUM = "" ;
		if (hANGI.equals("") )
		{
			hANGI = "" ;
		    dURUM =    " " ;
		}
		else {
			hANGI = "C" ;
			dURUM =   " Cikis_Evrak <> '' AND" ;
		}
		String sql = "" ;
		if(kurlu)
		{
		sql =  "SELECT  " + yu + "," +
				" SUM((" + hANGI + "Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000) - (   (KERESTE."+hANGI+"Tutar * Kereste.Iskonto)/100)    ) ) As Tutar,  " +
				" SUM((" + hANGI + "Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000) - (   (KERESTE."+hANGI+"Tutar * Kereste.Iskonto)/100)    )  / kurlar.MA) as "+ kurc +"_Tutar , " +
				" SUM(KERESTE.Miktar)  As Miktar, " +
				" SUM(((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000) As m3, " +
				" SUM(Kereste." + hANGI + "Tutar - ((KERESTE." + hANGI + "Tutar * Kereste.Iskonto)/100)    ) /  IF(( sum(((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)  ) = 0,1, " +
				" SUM(((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) As m3_Ort_Fiat , " +
				" (SUM((" + hANGI + "Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000) - (   (" + hANGI+"Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000) * Kereste.Iskonto)/100)    )  / kurlar.MA) /      NULLIF(      SUM(((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000),0))  As m3_Ort_Fiat_"+ kurc +" " +
				" FROM KERESTE ,OK_Kur" + kurkod +".kurlar  " +
				" WHERE    " +
				" " + dURUM +
				" KERESTE." + hANGI + "Tarih BETWEEN '" + t1 + "'" + " AND  '" + t2 + " 23:59:59.998' AND" +
				" SUBSTRING(KERESTE.Kodu, 1, 2) >= '" + ilks + "' AND SUBSTRING(KERESTE.Kodu, 1, 2) <= '"+ sons +"' AND" +
				" SUBSTRING(KERESTE.Kodu, 4, 3) >= '" + ilkk + "' AND SUBSTRING(KERESTE.Kodu, 4, 3) <= '"+ sonk +"' AND" +
				" SUBSTRING(KERESTE.Kodu, 8, 4) >= '" + ilkb + "' AND SUBSTRING(KERESTE.Kodu, 8, 4) <= '"+ sonb +"' AND" +
				" SUBSTRING(KERESTE.Kodu, 13, 4) >= '" + ilkg + "' AND SUBSTRING(KERESTE.Kodu, 13, 4) <= '"+ song +"' AND " + 
				" " + hANGI + "Cari_Firma between N'" + m1 + "' AND N'" + s2 + "' AND" +
				" Konsimento between N'" + s1 + "' AND N'" + m2 + "'" +
				" AND kurlar.Tarih = DATE( KERESTE."+ hANGI +"Tarih) and kurlar.Kur = '" + kurc + "'  " +
				" GROUP BY  " + iu ;
		}
		else 
		{
			kurc = "";
			sql =  "SELECT  " + yu + "," +
					" SUM((" + hANGI + "Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000) - (   (KERESTE."+hANGI+"Tutar * Kereste.Iskonto)/100)    ) ) As Tutar,  " +
					" SUM((" + hANGI + "Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000) - (   (KERESTE."+hANGI+"Tutar * Kereste.Iskonto)/100)    ) ) as _Tutar , " +
					" SUM(KERESTE.Miktar)  As Miktar, " +
					" SUM(((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000) As m3, " +
					" SUM(Kereste." + hANGI + "Tutar - ((KERESTE." + hANGI + "Tutar * Kereste.Iskonto)/100)    ) /  IF(( sum(((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)  ) = 0,1, " +
					" SUM(((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000)) As m3_Ort_Fiat , " +
					" (SUM(" + hANGI + "Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000) - ((" + hANGI+"Fiat * (((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  * " + 
					" CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000) * Kereste.Iskonto)/100))) / NULLIF(SUM(((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL)  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000),0))  As m3_Ort_Fiat_"+ kurc +" " +
					" FROM KERESTE   " +
					" WHERE    " +
					" " + dURUM +
					" KERESTE." + hANGI + "Tarih BETWEEN '" + t1 + "'" + " AND  '" + t2 + " 23:59:59.998' AND" +
					" SUBSTRING(KERESTE.Kodu, 1, 2) >= '" + ilks + "' AND SUBSTRING(KERESTE.Kodu, 1, 2) <= '" + sons + "' AND" +
					" SUBSTRING(KERESTE.Kodu, 4, 3) >= '" + ilkk + "' AND SUBSTRING(KERESTE.Kodu, 4, 3) <= '" + sonk + "' AND" +
					" SUBSTRING(KERESTE.Kodu, 8, 4) >= '" + ilkb + "' AND SUBSTRING(KERESTE.Kodu, 8, 4) <= '" + sonb + "' AND" +
					" SUBSTRING(KERESTE.Kodu, 13, 4) >= '" + ilkg + "' AND SUBSTRING(KERESTE.Kodu, 13, 4) <= '" + song + "' AND " + 
					" " + hANGI + "Cari_Firma between N'" + m1 + "' AND N'" + s2 + "' AND" +
					" Konsimento between N'" + s1 + "' AND N'" + m2 + "'" +
					" GROUP BY  " + iu ;

		}
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public boolean kons_kontrol(String kons) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		PreparedStatement stmt = con.prepareStatement("SELECT count(KONS) as KONS  FROM KONS_ACIKLAMA  WHERE KONS =N'" + kons + "' ");
		rss = stmt.executeQuery();
		boolean result =false;
		if (!rss.isBeforeFirst() ) {  
			result = false ;
		}
		else
		{
			rss.next();
			if( rss.getInt("KONS") ==0 )
			{
				result = false ;
			}
			else {
				result = true;
			}
		}
		return result;	
	}

	@Override
	public void ker_toplu_kaydet(JTable table,int degisken[], KER_RAPOR_BILGI keBilgi, String user)
			throws ClassNotFoundException, SQLException {
		 
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		String sql  ="INSERT INTO KERESTE (Evrak_No,Barkod,Kodu,Paket_No,Konsimento,Miktar,Tarih,Kdv,Doviz,Fiat,Tutar,Kur,Cari_Firma,Adres_Firma,Iskonto " + //15
				" ,Tevkifat,Ana_Grup,Alt_Grup,Depo,Ozel_Kod,Izahat,Nakliyeci,USER,Cikis_Evrak,CTarih,CKdv,CDoviz,CFiat,CTutar,Ckur,CCari_Firma,CAdres_Firma " + //17
				" ,CIskonto,CTevkifat,CAna_Grup,CAlt_Grup,CDepo,COzel_Kod,CIzahat,CNakliyeci,CUSER,Mensei,Satir) " + //9
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		String  izahat ="";
		double  miktar=0 , tutar =0,fiat =0, isk =0, kdv=0;
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.seteVRAK(keBilgi.getEvrak_No1());	
		for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
		{
			if (! mdl.getValueAt(i,1).toString().equals(""))
			{
				miktar = Double.parseDouble( mdl.getValueAt(i,3).toString());
				tutar =Double.parseDouble(mdl.getValueAt(i,10).toString());
				izahat =  mdl.getValueAt(i,11) .toString();
				fiat = Double.parseDouble( mdl.getValueAt(i,7).toString());
				isk = Double.parseDouble( mdl.getValueAt(i,8).toString());
				kdv =Double.parseDouble( mdl.getValueAt(i,9).toString());
				lBILGI.setmESAJ( " Fatura Kayit" +  mdl.getValueAt(i,1).toString() + " Mik=" + miktar + " Tut=" + tutar);
				stmt.setString(1,keBilgi.getEvrak_No1());
				stmt.setString(2, mdl.getValueAt(i,0).toString());
				stmt.setString(3,mdl.getValueAt(i,1).toString());
				stmt.setString(4, mdl.getValueAt(i,2).toString());
				stmt.setString(5,mdl.getValueAt(i,6).toString());
				stmt.setDouble(6, miktar);
				stmt.setString(7,keBilgi.getGTarih1());
				stmt.setDouble(8, kdv);
				stmt.setString(9,keBilgi.getCCari_Firma1());
				stmt.setDouble(10, fiat);
				stmt.setDouble(11, tutar);
				stmt.setDouble(12, keBilgi.getdOUBLE1());
				stmt.setString(13,keBilgi.getGCari_Firma1());
				stmt.setString(14,keBilgi.getGCari_Firma2());
				stmt.setDouble(15, isk);
				stmt.setDouble(16, keBilgi.getdOUBLE2());
				stmt.setInt(17, degisken[0]);
				stmt.setInt(18, degisken[1]);
				stmt.setInt(19, degisken[4]);
				stmt.setInt(20,degisken[3]);
				stmt.setString(21,izahat);
				stmt.setInt(22, degisken[2]);
				stmt.setString(23,  user);
				stmt.setString(24, mdl.getValueAt(i,12).toString());
				if(!mdl.getValueAt(i,13).toString().equals(""))
				{
					stmt.setString(25,mdl.getValueAt(i,13).toString());
				}
				else {
					stmt.setString(25,"1900.01.01");
				}
				stmt.setDouble(26, Double.parseDouble( mdl.getValueAt(i,14).toString()));
				stmt.setString(27, mdl.getValueAt(i,15).toString());
				stmt.setDouble(28, Double.parseDouble( mdl.getValueAt(i,16).toString()));
				stmt.setDouble(29, Double.parseDouble( mdl.getValueAt(i,17).toString()));
				stmt.setDouble(30, Double.parseDouble( mdl.getValueAt(i,18).toString()));
				stmt.setString(31,mdl.getValueAt(i,19).toString());
				stmt.setString(32,mdl.getValueAt(i,20).toString());
				stmt.setDouble(33, Double.parseDouble( mdl.getValueAt(i,21).toString()));
				stmt.setDouble(34,Double.parseDouble( mdl.getValueAt(i,22).toString()));
				stmt.setInt(35, Integer.parseInt(mdl.getValueAt(i,23).toString()));
				stmt.setInt(36, Integer.parseInt(mdl.getValueAt(i,24).toString()));
				stmt.setInt(37,Integer.parseInt(mdl.getValueAt(i,25).toString()));
				stmt.setInt(38,Integer.parseInt(mdl.getValueAt(i,26).toString()));
				stmt.setString(39, mdl.getValueAt(i,27).toString());
				stmt.setInt(40, Integer.parseInt(mdl.getValueAt(i,28).toString()));
				stmt.setString(41,  mdl.getValueAt(i,29).toString());
				stmt.setInt(42, degisken[5]);
				stmt.setInt(43, i);
				stmt.addBatch();
				if ((i ) % 1000 == 0) 
				{
					stmt.executeBatch();
				}
			}
		}
		stmt.executeBatch();
		stmt.close();
	}

	@Override
	public ResultSet paket_ara(KER_RAPOR_BILGI ker_rap_BILGI) throws ClassNotFoundException, SQLException {
		 
		ResultSet	rss = null;
		String[] token = ker_rap_BILGI.getGKodu1().toString().split("-");
		String ilks ,ilkk,ilkb,ilkg;
		ilks = token[0];
		if (ilks.equals("00")) {
			ilks = "";
		}
		ilkk = token[1];
		if (ilkk.equals("000")) {
			ilkk = "";
		}
		ilkb = token[2];
		if (ilkb.equals("0000")) {
			ilkb = "";
		}
		ilkg = token[3];
		if (ilkg.equals("0000")) {
			ilkg = "";
		}
		String sql =  " SELECT  Evrak_No  "
				+ " , Barkod  "
				+ " , Kodu  "
				+ " , Paket_No  "
				+ " , Konsimento  "
				+ " , Miktar  "
				+ " ,(((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL)) * Miktar)/1000000000)  as m3"
				+ " , Tarih  "
				+ " , Kdv  "
				+ " , Doviz  "
				+ " , Fiat  "
				+ " , Tutar  "
				+ " , Kur  "
				+ " , Cari_Firma  "
				+ " , Adres_Firma  "
				+ " , Iskonto  "
				+ " , Tevkifat  "
				+ " ,IFNULL((SELECT ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = KERESTE.Ana_Grup),'') Ana_Grup "
				+ " ,IFNULL((SELECT ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = KERESTE.Alt_Grup),'') AS Alt_Grup "
				+ " ,IFNULL((SELECT MENSEI FROM MENSEI_DEGISKEN WHERE MENSEI_DEGISKEN.MEID_Y = KERESTE.Mensei),'') AS Mensei "
				+ " ,(SELECT DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = KERESTE.Depo ) as Depo  " 
				+ " ,IFNULL((SELECT OZEL_KOD_1 FROM OZ_KOD_1_DEGISKEN WHERE OZ_KOD_1_DEGISKEN.OZ1ID_Y = KERESTE.Ozel_Kod),'') Ozel_Kod "
				+ " , Izahat  "
				+ " ,(SELECT UNVAN FROM NAKLIYECI WHERE NAKLIYECI.NAKID_Y = KERESTE.Nakliyeci ) as Nakliyeci  " 
				+ " ,USER "
				+ " FROM KERESTE    " 
				+ " WHERE " 
				+ " SUBSTRING(KERESTE.Kodu, 1, 2) like '"+ ilks +"%'  AND" 
				+ " SUBSTRING(KERESTE.Kodu, 4, 3) like '"+ilkk +"%' AND" 
				+ " SUBSTRING(KERESTE.Kodu, 8, 4) like '"+ilkb +"%' AND" 
				+ " SUBSTRING(KERESTE.Kodu, 13, 4) like '"+ilkg +"%'  AND" 
				+ " Paket_No like N'"+ ker_rap_BILGI.getPaket_No1().toString()+ "%' AND " 
				+ " Konsimento like N'"+ ker_rap_BILGI.getKonsimento1().toString() + "%' " 
				+ " AND Cikis_Evrak = '' "; 
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public int paket_no_al(String kons) throws ClassNotFoundException, SQLException {
		ResultSet	rss = null;
		int E_NUMBER ;
		String sql = "SELECT  Pak_No FROM PAKET_NO  ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		E_NUMBER = rss.getInt("Pak_No");
		E_NUMBER = E_NUMBER + 1 ;
		//******** KAYIT
		sql = "UPDATE PAKET_NO SET Pak_No =" + E_NUMBER + "  WHERE Konsimento = N'" + kons+ "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		//**************
		return E_NUMBER;	

	}

	@Override
	public ResultSet ortalama_genislik(String gruplama, String sstr_2, String sstr_4, String kur_dos, String qwq6,
			String qwq7, String qwq8, String k1, String k2, String s1, String s2, String jkj, String t1, String t2,
			String sstr_5, String sstr_1, String orderBY, String dURUM, String ko1, String ko2, String dpo, String grup,
			String e1, String e2) throws ClassNotFoundException, SQLException {
		ResultSet	rss = null;
		String[] token = k1.toString().split("-");
		String ilks ,ilkk,ilkb,ilkg;
		ilks = token[0];
		ilkk = token[1];
		ilkb = token[2];
		ilkg = token[3];
		token = k2.toString().split("-");
		String sons,sonk,sonb,song;
		sons = token[0];
		sonk = token[1];
		sonb = token[2];
		song = token[3];
		if(qwq6.equals(" Like  '%' "))
		{
			qwq6 =  " " ;
		}
		else {
			qwq6 = dURUM + "Ana_Grup " + qwq6 ;
		}
		if(qwq7.equals(" Like  '%' "))
		{
			qwq7 =  " " ;
		}
		else {
			qwq7 = " AND "+ dURUM + "Alt_Grup " + qwq7 ;
		}
		if(qwq8.equals(" Like  '%' "))
		{
			qwq8 =  " " ;
		}
		else {
			qwq8 = " AND "+ dURUM + "Ozel_Kod " + qwq8 ;
		}
		if(dpo.equals(" Like  '%' "))
		{
			dpo =  " " ;
		}
		else {
			dpo = " AND "+ dURUM + "Depo " + dpo + " AND ";
		}
		
		///
		String replaceString=sstr_1.replace('[',' ');//replaces all occurrences of 'a' to 'e'  
		String replaceString2=replaceString.replace(']',' ');//replaces all occurrences of 'a' to 'e'  
		String[] tokens =replaceString2.split(",");
		String cASE = "" ;
		for (String t : tokens)
		{
			cASE  = cASE + "Sum(CASE WHEN " + sstr_2 + "  = '" + t.trim() + "'  THEN " + sstr_4 + " ELSE 0 END) AS '"+ t.trim() + "',";
		}
		cASE = cASE.substring(0, cASE.length() - 1);
		String qweString = "" ;
		if(dURUM.equals("C"))
		{
			qweString = " Cikis_Evrak " ;
		}
		else {
			qweString = " Evrak_No " ;
		}
		///
		String sql =   " SELECT Paket_No , Konsimento ,SUBSTRING(KERESTE.Kodu, 8, 4) as Boy ,SUBSTRING(KERESTE.Kodu, 4, 3) as Kal , " +
				" Sum( ((CONVERT( SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT( SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)/1000000000) as m3 , " +
				" ((Sum(((CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3),DECIMAL )  *  CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL) * CONVERT(SUBSTRING(KERESTE.Kodu, 13, 4),DECIMAL )  ) * Miktar)) / sum(miktar)) " +
				" / CONVERT(SUBSTRING(KERESTE.Kodu, 8, 4),DECIMAL)) / CONVERT(SUBSTRING(KERESTE.Kodu, 4, 3) ,DECIMAL) as Ort_Gen " + 
				"  FROM KERESTE " +
				" WHERE   " + jkj + " " +
				qwq6 + " "+qwq7 + " " +qwq8 + " " + dpo +
				" SUBSTRING(KERESTE.Kodu, 1, 2) >= '"+ilks +"' AND SUBSTRING(KERESTE.Kodu, 1, 2) <= '"+ sons +"' AND" +
				" SUBSTRING(KERESTE.Kodu, 4, 3) >= '"+ilkk +"' AND SUBSTRING(KERESTE.Kodu, 4, 3) <= '"+ sonk +"' AND" +
				" SUBSTRING(KERESTE.Kodu, 8, 4) >= '"+ilkb +"' AND SUBSTRING(KERESTE.Kodu, 8, 4) <= '"+ sonb +"' AND" +
				" SUBSTRING(KERESTE.Kodu, 13, 4) >= '"+ilkg +"' AND SUBSTRING(KERESTE.Kodu, 13, 4) <= '"+ song +"' " +
				" AND "+ dURUM + "Cari_Firma between N'" + s1 + "' AND N'" + s2 + "'" +
				" AND " + qweString  + " between N'" + e1 + "' AND N'" + e2 + "'" +
				" AND Konsimento between N'" + ko1 + "' AND N'" + ko2 + "'" +
				" AND  KERESTE."+ dURUM + "Tarih BETWEEN '" +t1 + "'" + " AND  '" + t2 + " 23:59:59.998'" +
				" GROUP by PAKET_NO , Konsimento ,SUBSTRING(KERESTE.Kodu, 8, 4),SUBSTRING(KERESTE.Kodu, 4, 3) " +
				" ORDER by PAKET_NO "	 ;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public ResultSet bos_kur(String kur, String cins,String dURUM) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  "SELECT DISTINCT DATE( s."+ dURUM + "Tarih) as Tarih  " +
				" FROM KERESTE s left outer join ok_kur" +  BAGLAN.kurDizin.kOD + ".kurlar k on DATE( k.Tarih) = DATE( s."+ dURUM + "Tarih) and k.Kur = '" + kur + "'" +
				" WHERE YEAR(KERESTE." + dURUM + "Tarih) <> '1900' AND  k." + cins + " IS NULL OR k." + cins + " =0 " +
				" ORDER BY DATE(s." + dURUM + "Tarih)  " ;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public ResultSet sql_sorgu(String sql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
}
