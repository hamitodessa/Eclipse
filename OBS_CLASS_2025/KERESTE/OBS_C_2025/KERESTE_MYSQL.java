package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		sql = "CREATE TABLE `GDY`( "
				+ "  `GID`   INTEGER AUTO_INCREMENT PRIMARY KEY  NOT NULL,"
				+ "  `Isim`  nvarchar (50) NULL, "
				+ "  `Adres`  nvarchar (50) NULL, "
				+ "  `Semt`  nvarchar (50) NULL, "
				+ "  `Sehir`  nvarchar (50) NULL, "
				+ "  `USER`  nvarchar (15) NOT NULL, "
				+ "  INDEX `IX_GDY` (  `Isim` ASC,  `USER` ASC ) VISIBLE)";
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
				+ " `Konsimento` nvarchar(10) NULL,"
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
		sql = "CREATE TABLE `DEPOEVRAK`("
				+ " `E_No`  int  PRIMARY KEY  NOT NULL)";
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
		// ***************EVRAK NO YAZ ************
		sql = "INSERT INTO  DEPOEVRAK(E_No) VALUES ('0')";
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public String ker_firma_adi() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet kod_pln() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void kod_kayit(String kodu, String aciklama) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kod_sil(String kod) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet kons_pln() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void kons_kayit(String kons, String aciklama) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kons_sil(String kons) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String kod_adi(String kod) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet ker_kod_degisken_oku(String fieldd, String sno, String nerden)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet ker_kod_degisken_ara(String fieldd, String sno, String nerden, String arama)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet ker_kod_alt_grup_degisken_oku(int sno) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean alt_grup_kontrol(int anagrp, int altgrp) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ker_degisken_alt_grup_sil(int ID) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ker_kod_degisken_sil(String nerden, String hangi_Y, int sira)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ker_degisken_eski(String fieldd, String degisken_adi, String nerden, String sno, int ID)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ker_degisken_kayit(String fieldd, String nerden, String degisken_adi, String sira)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ker_degisken_alt_grup_kayit(String alt_grup, int ana_grup) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ker_degisken_alt_grup_eski(String alt_grup, int ana_grup, int ID)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet ker_oz_kod(String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String son_no_al(String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ker_giris_sil(String eno) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ker_kaydet(KER_BILGI kBILGI, String user) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet ker_oku(String eno, String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dipnot_sil(String ino, String cins, String gircik) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dipnot_yaz(String eno, String bir, String iki, String uc, String tip, String gircik, String usr)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet dipnot_oku(String ino, String cins, String gircik) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aciklama_sil(String evrcins, String evrno, String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aciklama_yaz(String evrcins, int satir, String evrno, String aciklama, String gircik)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String aciklama_oku(String evrcins, int satir, String evrno, String gircik)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet paket_oku(String pno) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ker_cikis_sil(String eno) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void ker_cikis_kaydet(KER_BILGI kBILGI) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int evrak_no_al(String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultSet baslik_bak(String baslik, String ordr, String jkj, String k1, String k2, String f1,
			String f2, String t1, String t2,String dURUM) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet grp_rapor(String gruplama, String sstr_2, String sstr_4, String kur_dos, String qwq6, String qwq7,
			String qwq8, String k1, String k2, String s1, String s2, String jkj, String t1, String t2, String sstr_5,
			String sstr_1, String orderBY, String dURUM) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet stok_rapor(KER_RAPOR_BILGI ker_rap_BILGI) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet ker_barkod_kod_oku(String sira) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
