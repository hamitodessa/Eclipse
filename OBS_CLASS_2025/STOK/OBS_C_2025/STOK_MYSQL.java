package OBS_C_2025;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import LOGER_KAYIT.DOSYA_MYSQL;
import LOGER_KAYIT.ILOGER_KAYIT;
import LOGER_KAYIT.TXT_LOG;

public class STOK_MYSQL implements ISTOK {

	static Connection con = null;
	static Statement stmt = null;

	public void baglan() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String cumle = "jdbc:mysql://" +  BAGLAN.fatDizin.cONN_STR ;
		DriverManager.setLoginTimeout(0);
		con = DriverManager.getConnection(cumle, BAGLAN.fatDizin.kULLANICI, BAGLAN.fatDizin.sIFRESI);
	}
	@Override
	public void fAT_SIFIR_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String cumle = "";
		cumle = "jdbc:mysql://localhost:" + sbilgi.getPort() ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		String VERITABANI = "ok_fat" + sbilgi.getKod();
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
		vTLOG.Logla(lBILGI, BAGLAN_LOG.fatLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi() );
		vTLOG.Logla(lBILGI, BAGLAN_LOG.fatLogDizin);

		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL" + ".DB") == false)
		{
			String dsy =GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL"+ ".DB" ;
			GLOBAL.create_table_log(dsy,sbilgi.getFir_adi(),BAGLAN_LOG.fatLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		 tEXLOG.Logla(lBILGI, BAGLAN_LOG.fatLogDizin);
		 lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		 tEXLOG.Logla(lBILGI, BAGLAN_LOG.fatLogDizin);
		//
		stmt.close();
		con.close();
	}
	@Override
	public void fAT_SIFIR_S(Server_Bilgi sbilgi)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String VERITABANI = "ok_fat" + sbilgi.getKod();
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
		vTLOG.Logla(lBILGI, BAGLAN_LOG.fatLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		vTLOG.Logla(lBILGI, BAGLAN_LOG.fatLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(  GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.fatLogDizin.mODUL) ) == false)
		{
			String dsy =   GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.fatLogDizin.mODUL) ;
			GLOBAL.create_table_log(dsy,sbilgi.getFir_adi(),BAGLAN_LOG.fatLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.fatLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.fatLogDizin);
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
		sql= "CREATE TABLE `FATURA`( "
				+ "`Fatura_No`  nvarchar (10) NOT NULL,"
				+ " `Kodu`  nvarchar (12) NULL,"
				+ " `Tarih` datetime NULL,"
				+ " `Kdv`  DOUBLE  NULL,"
				+ " `Doviz`  nvarchar (3) NULL,"
				+ " `Miktar`  DOUBLE  NULL,"
				+ " `Fiat`  DOUBLE  NULL,"
				+ " `Tutar`  DOUBLE  NULL,"
				+ " `Kur`  DOUBLE  NULL,"
				+ " `Cari_Firma`  nvarchar (12) NULL,"
				+ " `Iskonto`  DOUBLE  NULL,"
				+ " `Tevkifat`  DOUBLE  NULL,"
				+ " `Ana_Grup`  int  NULL,"
				+ " `Alt_Grup`  int  NULL,"
				+ " `Depo`  int  NULL,"
				+ " `Adres_Firma`  nvarchar (12) NULL,"
				+ " `Ozel_Kod`  nvarchar (10) NULL,"
				+ " `Gir_Cik`  nvarchar (1) NULL,"
				+ " `Izahat`  nvarchar (40) NULL,"
				+ " `Cins`  nvarchar (1) NULL,"
				+ " `USER`  nvarchar (15) NOT NULL,"
				+ " INDEX `IX_FATURA`   (`Fatura_No`,`Kodu`,`Tarih`,`Cari_Firma`,`Gir_Cik`) VISIBLE)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `IRSALIYE`( "
				+ " `Irsaliye_No`  nvarchar (10) NOT NULL,"
				+ " `Kodu`  nvarchar (12) NULL,"
				+ " `Tarih` datetime NULL,"
				+ " `Kdv`  DOUBLE  NULL,"
				+ " `Doviz`  nvarchar (3) NULL,"
				+ " `Kur`  DOUBLE  NULL,"
				+ " `Miktar`  DOUBLE  NULL,"
				+ " `Fiat`  DOUBLE  NULL,"
				+ " `Tutar`  DOUBLE  NULL,"
				+ " `Firma`  nvarchar (12) NULL,"
				+ " `Iskonto`  DOUBLE  NULL,"
				+ " `Fatura_No`  nvarchar (10) NULL,"
				+ " `Sevk_Tarihi` date NULL,"
				+ " `Ana_Grup`  int  NULL,"
				+ " `Alt_Grup`  int  NULL,"
				+ " `Depo`  int  NULL,"
				+ " `Cari_Hesap_Kodu`  nvarchar (12) NULL,"
				+ " `Ozel_Kod`  nvarchar (10) NULL,"
				+ " `Hareket`  nvarchar (1) NULL,"
				+ " `Izahat`  nvarchar (40) NULL,"
				+ " `Cins`  nvarchar (1) NULL,"
				+ " `USER`  nvarchar (15) NOT NULL,"
				+ " INDEX `IX_IRSALIYE`  (`Irsaliye_No`,`Kodu`,`Tarih`,`Firma`,`Hareket`) VISIBLE)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `MAL`( "
				+ " `Kodu`  nvarchar (12) PRIMARY KEY NOT NULL,"
				+ " `Adi`  nvarchar (40) NULL,"
				+ " `Birim`  nvarchar (5) NULL,"
				+ " `Kusurat`  int  NULL,"
				+ " `Resim` MEDIUMBLOB  NULL,"
				+ " `Sinif`  nvarchar (5) NULL,"
				+ " `Ana_Grup`  int  NULL,"
				+ " `Alt_Grup`  int  NULL,"
				+ " `Aciklama_1`  nvarchar (25) NULL,"
				+ " `Aciklama_2`  nvarchar (25) NULL,"
				+ " `Ozel_Kod_1`  int  NULL,"
				+ " `Ozel_Kod_2`  int  NULL,"
				+ " `Ozel_Kod_3`  int  NULL,"
				+ " `KDV`  DOUBLE  NULL,"
				+ " `Barkod`  nvarchar (20) NULL,"
				+ " `Mensei`  int  NULL,"
				+ " `Agirlik`  DOUBLE  NULL,"
				+ " `Depo`  int  NULL,"
				+ " `Fiat`  DOUBLE  NULL,"
				+ " `Fiat_2`  DOUBLE  NULL,"
				+ " `Fiat_3`  DOUBLE  NULL,"
				+ " `Recete`  nvarchar (10) NULL,"
				+ " `USER`  nvarchar (15) NOT NULL,"
				+ " INDEX `IX_MAL`  ( `Adi` ASC, `Ana_Grup` ASC,`Alt_Grup` ASC)  VISIBLE)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `RECETE`( "
				+ " `Recete_No`  nvarchar (10) NOT NULL,"
				+ " `Ana_Grup`  int  NULL,"
				+ " `Alt_Grup`  int  NULL,"
				+ " `Durum` TINYINT NULL,"
				+ " `Tur`  nvarchar (7) NULL,"
				+ " `Kodu`  nvarchar (10) NULL,"
				+ " `Miktar`  DOUBLE  NULL,"
				+ " `USER`  nvarchar (15) NOT NULL) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `OZEL` ("
				+ "  `YONETICI` VARCHAR(25) NULL,"
				+ "  `YON_SIFRE` VARCHAR(15) NULL,"
				+ "  `FIRMA_ADI` VARCHAR(50) NULL,"
				+ " `USER`  nvarchar (15) NOT NULL)" ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `STOK`("
				+ " `Evrak_No`  nvarchar (10) NOT NULL,"
				+ " `Evrak_Cins`  nvarchar (3) NULL,"
				+ " `Tarih` DATETIME NULL,"
				+ " `Depo`  int  NULL,"
				+ " `Urun_Kodu`  nvarchar (12) NULL,"
				+ " `Miktar`  DOUBLE  NULL,"
				+ " `Fiat`  DOUBLE  NULL,"
				+ " `Tutar`  DOUBLE  NULL,"
				+ " `Ana_Grup`  int  NULL,"
				+ " `Alt_Grup`  int  NULL,"
				+ " `Hareket`  nvarchar (1) NULL,"
				+ " `Izahat`  nvarchar (40) NULL,"
				+ " `Hesap_Kodu`  nvarchar (12) NULL,"
				+ " `Kur` DOUBLE NULL,"
				+ " `Doviz`  nvarchar (3) NULL,"
				+ " `Kdvli_Tutar`  DOUBLE  NULL,"
				+ " `B1`  nvarchar (15) NULL,"
				+ " `USER`  nvarchar (40) NOT NULL,"
				+ " INDEX IX_STOK  (`Urun_Kodu`,`Tarih`,`Hareket`) VISIBLE )";
		//    		+ " INDEX IX_Cikan  ( `Urun_Kodu` ASC, `Tarih` ASC)  INCLUDE (	`Miktar`) WHERE (`Hareket`='C') VISIBLE,)"
		//   		+ " INDEX IX_Giren  ( `Urun_Kodu` ASC, `Tarih` ASC)  INCLUDE ( 	`Fiat`) WHERE (`Hareket`='G') VISIBLE )";

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
		sql = "CREATE TABLE `BOZUK_MAL`( "
				+ " `Evrak_No`  nvarchar (10) NOT NULL,"
				+ " `Kodu`  nvarchar (12) NULL,"
				+ " `Tarih` datetime NULL,"
				+ " `Miktar`  DOUBLE  NULL,"
				+ " `Fiat`  DOUBLE  NULL,"
				+ " `Ana_Grup`  int  NULL,"
				+ " `Alt_Grup`  int  NULL,"
				+ " `Depo`  int  NULL,"
				+ " `Ozel_Kod`  nvarchar (10) NULL,"
				+ " `Izahat`  nvarchar (40) NULL,"
				+ " `Cins`  nvarchar (1) NULL,"
				+ " `USER`  nvarchar (15) NOT NULL)";

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
		sql = "CREATE TABLE `OZ_KOD_2_DEGISKEN`("
				+ " `OZ2ID`   INTEGER AUTO_INCREMENT PRIMARY KEY  NOT NULL,"
				+ " `OZ2ID_Y`  int  NOT NULL,"   
				+ " `OZEL_KOD_2`  nvarchar (25) NOT NULL,"
				+ " `USER`  nvarchar (15) NOT NULL)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `DEPOEVRAK`("
				+ " `E_No`  int  PRIMARY KEY  NOT NULL)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `URET_EVRAK`("
				+ " `E_No`  int PRIMARY KEY  NOT NULL)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `ZAYI_EVRAK`("
				+ " `E_No`  int PRIMARY KEY  NOT NULL)";
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
				+ " `TARIH`  DOUBLE  NULL,"
				+ " `SEVK_TARIH`  DOUBLE  NULL,"
				+ " `FIRMA_KODU`  DOUBLE  NULL,"
				+ " `FIRMA_UNVANI`  DOUBLE  NULL,"
				+ " `VERGI_DAIRESI`  DOUBLE  NULL,"
				+ " `VERGI_NO`  DOUBLE  NULL,"
				+ " `GIDECEGI_YER`  DOUBLE  NULL,"
				+ " `NOT_1`  DOUBLE  NULL,"
				+ " `NOT_2`  DOUBLE  NULL,"
				+ " `NOT_3`  DOUBLE  NULL,"
				+ " `BASLIK_BOLUM`  DOUBLE  NULL,"
				+ " `BARKOD`  DOUBLE  NULL,"
				+ " `URUN_KODU`  DOUBLE  NULL,"
				+ " `URUN_ADI`  DOUBLE  NULL,"
				+ " `DEPO`  DOUBLE  NULL,"
				+ " `SIMGE`  DOUBLE  NULL,"
				+ " `BIRIM_FIAT`  DOUBLE  NULL,"
				+ " `ISKONTO`  DOUBLE  NULL,"
				+ " `MIKTAR`  DOUBLE  NULL,"
				+ " `K_D_V`  DOUBLE  NULL,"
				+ " `TUTAR`  DOUBLE  NULL,"
				+ " `TUTAR_TOPLAM`  DOUBLE  NULL,"
				+ " `ISKONTO_TOPLAMI`  DOUBLE  NULL,"
				+ " `BAKIYE`  DOUBLE  NULL,"
				+ " `K_D_V_TOPLAMI`  DOUBLE  NULL,"
				+ " `BELGE_TOPLAMI`  DOUBLE  NULL,"
				+ " `YAZI_ILE`  DOUBLE  NULL,"
				+ " `ALT_BOLUM`  DOUBLE  NULL,"
				+ " `N1`  DOUBLE  NULL,"
				+ " `N2`  DOUBLE  NULL,"
				+ " `N3`  DOUBLE  NULL,"
				+ " `N4`  DOUBLE  NULL,"
				+ " `N5`  DOUBLE  NULL,"
				+ " `N6`  DOUBLE  NULL,"
				+ " `N7`  DOUBLE  NULL,"
				+ " `N8`  DOUBLE  NULL,"
				+ " `N9`  DOUBLE  NULL,"
				+ " `N10`  DOUBLE  NULL,"
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
				+ " `TARIH`  DOUBLE  NULL,"
				+ " `FIRMA_KODU`  DOUBLE  NULL,"
				+ " `FIRMA_UNVANI`  DOUBLE  NULL,"
				+ " `VERGI_DAIRESI`  DOUBLE  NULL,"
				+ " `VERGI_NO`  DOUBLE  NULL,"
				+ " `GIDECEGI_YER`  DOUBLE  NULL,"
				+ " `NOT_1`  DOUBLE  NULL,"
				+ " `NOT_2`  DOUBLE  NULL,"
				+ " `NOT_3`  DOUBLE  NULL,"
				+ " `BASLIK_BOLUM`  DOUBLE  NULL,"
				+ " `BARKOD`  DOUBLE  NULL,"
				+ " `URUN_KODU`  DOUBLE  NULL,"
				+ " `URUN_ADI`  DOUBLE  NULL,"
				+ " `DEPO`  DOUBLE  NULL,"
				+ " `IZAHAT`  DOUBLE  NULL,"
				+ " `SIMGE`  DOUBLE  NULL,"
				+ " `BIRIM_FIAT`  DOUBLE  NULL,"
				+ " `ISKONTO`  DOUBLE  NULL,"
				+ " `MIKTAR`  DOUBLE  NULL,"
				+ " `K_D_V`  DOUBLE  NULL,"
				+ " `TUTAR`  DOUBLE  NULL,"
				+ " `TUTAR_TOPLAM`  DOUBLE  NULL,"
				+ " `ISKONTO_TOPLAMI`  DOUBLE  NULL,"
				+ " `BAKIYE`  DOUBLE  NULL,"
				+ " `K_D_V_TOPLAMI`  DOUBLE  NULL,"
				+ " `BELGE_TOPLAMI`  DOUBLE  NULL,"
				+ " `TEVKIFAT_ORANI`  DOUBLE  NULL,"
				+ " `AL_TAR_TEV_ED_KDV`  DOUBLE  NULL,"
				+ " `TEV_DAH_TOP_TUTAR`  DOUBLE  NULL,"
				+ " `BEYAN_ED_KDV`  DOUBLE  NULL,"
				+ " `TEV_HAR_TOP_TUT`  DOUBLE  NULL,"
				+ " `YAZI_ILE`  DOUBLE  NULL,"
				+ " `TEV_KASESI`  DOUBLE  NULL,"
				+ " `ALT_BOLUM`  DOUBLE  NULL,"
				+ " `N1`  DOUBLE  NULL,"
				+ " `N2`  DOUBLE  NULL,"
				+ " `N3`  DOUBLE  NULL,"
				+ " `N4`  DOUBLE  NULL,"
				+ " `N5`  DOUBLE  NULL,"
				+ " `N6`  DOUBLE  NULL,"
				+ " `N7`  DOUBLE  NULL,"
				+ " `N8`  DOUBLE  NULL,"
				+ " `N9`  DOUBLE  NULL,"
				+ " `N10`  DOUBLE  NULL,"
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
		sql = "INSERT INTO  URET_EVRAK(E_No) VALUES ('0')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "INSERT INTO  ZAYI_EVRAK(E_No) VALUES ('0')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// ***************OZEL NO YAZ *************************
		sql = "INSERT INTO  OZEL(YONETICI,YON_SIFRE,FIRMA_ADI,USER) VALUES ('" + GLOBAL.KULL_ADI  + "','12345' , '" + fir_adi + "','Admin')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}

	@Override
	public String fat_firma_adi() throws ClassNotFoundException, SQLException {
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
	public ResultSet stk_kod_degisken_oku(String fieldd,String sno,String nerden) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  "SELECT " + sno + "  AS KOD , " + fieldd + " FROM " + nerden + "" +
				" ORDER BY " + fieldd + "";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet urun_kod_degisken_ara(String fieldd,String sno,String nerden,String arama) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =   "SELECT  " + fieldd + " FROM " + nerden + " WHERE " + sno + " = N'" + arama + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet stk_kod_alt_grup_degisken_oku (int sno) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =   "SELECT ALID_Y , ALT_GRUP FROM ALT_GRUP_DEGISKEN   " +
				" WHERE ANA_GRUP = N'" + sno + "' ORDER BY ALT_GRUP";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	 
	}
	public void stk_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		String sql = "UPDATE OZEL SET FIRMA_ADI = N'" + fadi + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void urun_degisken_eski(String fieldd ,String degisken_adi ,String nerden ,String sno ,int ID ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		String sql =  "UPDATE " + nerden + "  SET " + fieldd + "  = N'" + degisken_adi + "'  WHERE " + sno + "  = '" + ID + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void urun_degisken_alt_grup_eski(String alt_grup ,int ana_grup ,int  ID ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		String sql = "UPDATE ALT_GRUP_DEGISKEN  SET ALT_GRUP  = N'" + alt_grup + "' , ANA_GRUP  = N'" + ana_grup + "'  WHERE ALID_Y = '" + ID + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void urun_degisken_kayit(String fieldd  ,String nerden,String degisken_adi,String sira) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
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
		sql  =  "INSERT INTO " + nerden + " (" + fieldd + "," + degisken_adi + ", USER ) " +
				" VALUES (?,?,?)" ;
		stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, maks + 1);
		stmt.setString(2, sira);
		stmt.setString(3, GLOBAL.KULL_ADI);
		stmt.executeUpdate();
		stmt.close();
	}
	public void  urun_degisken_alt_grup_kayit (String alt_grup , int ana_grup ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
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
		sql  = "INSERT INTO ALT_GRUP_DEGISKEN (ALID_Y,ALT_GRUP,ANA_GRUP, USER ) " +
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
	public ResultSet stk_urun(String sira,String arama) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT Kodu,Adi,Birim,Kusurat,Sinif,Ana_Grup,Alt_Grup,Aciklama_1,Aciklama_2 ," +
				" Ozel_Kod_1 ,Ozel_Kod_2 ,Barkod,Mensei,Agirlik,Resim,Fiat,Fiat_2,Fiat_3,Recete, USER " +
				" FROM MAL USE INDEX (IX_MAL)  "+ arama +"  ORDER by " + sira ;
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	public void stk_ur_sil(String kodu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =  " DELETE  FROM MAL WHERE Kodu= N'" + kodu + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void stk_ur_kayit(String kodu ,String adi ,String birim ,double kusurat ,String sinif,
			int anagrup, int altgrup, String acik1 ,String acik2 , int ozkod1 , int ozkod2,
			String  barkod ,int mensei,double  agirlik , InputStream resim ,double fiat ,double fiat2 , String recete ,String usr ,double fiat3 ) throws ClassNotFoundException, SQLException, IOException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  = "INSERT INTO MAL (Kodu,Adi,Birim,Kusurat,Sinif,Ana_Grup,Alt_Grup,Aciklama_1,Aciklama_2,Ozel_Kod_1 " +
				" ,Ozel_Kod_2,Barkod,Mensei,Agirlik,Fiat,Fiat_2,Fiat_3,Recete,Kdv,Resim,Depo , Ozel_Kod_3,USER) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, kodu);
		stmt.setString(2, adi);
		stmt.setString(3, birim);
		stmt.setDouble(4, kusurat);
		stmt.setString(5, sinif);
		stmt.setInt(6, anagrup);
		stmt.setInt(7, altgrup);
		stmt.setString(8, acik1);
		stmt.setString(9, acik2);
		stmt.setInt(10, ozkod1);
		stmt.setInt(11, ozkod2);
		stmt.setString(12, barkod);
		stmt.setInt(13, mensei);
		stmt.setDouble(14, agirlik);
		stmt.setDouble(15, fiat);
		stmt.setDouble(16, fiat2);
		stmt.setDouble(17, fiat3);
		stmt.setString(18, recete);
		stmt.setDouble(19, 0.0);
		if (  resim != null)
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int readNum; (readNum = resim.read(buf)) != -1;) 
			{
				bos.write(buf, 0, readNum);
			}
			byte[] bytes = bos.toByteArray();
			stmt.setBytes(20,bytes);
		}
		else
		{
			stmt.setBytes(20,null);
		}
		stmt.setInt(21,0);
		stmt.setInt(22, 0);
		stmt.setString(23, usr);

		stmt.executeUpdate();
		stmt.close();
	}
	public ResultSet ur_kod_bak(String kodu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT Kodu,Adi FROM MAL  USE INDEX (IX_MAL)  WHERE Kodu = N'" + kodu + "'" ;
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	public ResultSet stk_barkod_kod_oku(String sira) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =   "SELECT   Kodu,Barkod FROM MAL USE INDEX (IX_MAL) " +
				" WHERE " + sira + " <> '' " +
				" ORDER by " + sira;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();

		return rss;	

	}
	public ResultSet urun_adi_oku (String kodu,String kodbarcode) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =   "SELECT Kodu,Adi,Birim,Kusurat,Barkod,Depo,Fiat,Fiat_2,Fiat_3,Agirlik, Sinif,Recete," + 
				" (SELECT  ANA_GRUP FROM ANA_GRUP_DEGISKEN    WHERE AGID_Y = MAL.Ana_Grup) AS Ana_Grup ,  " + 
				" (SELECT  ALT_GRUP FROM ALT_GRUP_DEGISKEN    WHERE ALID_Y = MAL.Alt_Grup) AS Alt_Grup,Resim " + 
				"FROM MAL USE INDEX (IX_MAL)  WHERE " + kodbarcode + " = N'" + kodu + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet fat_oz_kod (String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =   "SELECT DISTINCT  Ozel_Kod  " + 
				"  FROM FATURA WHERE Gir_Cik = '" + cins+ "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet son_satis_fiati_oku(String kodu,String muskodu,String gircik) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =    "SELECT  Fiat " +
				" FROM FATURA USE INDEX (IX_FATURA) " +
				" WHERE  Cari_Firma = N'" + muskodu + "'" +
				" AND  Kodu = N'" + kodu + "'" +
				" AND Gir_Cik = '" + gircik + "'" +
				" ORDER BY  Tarih desc   LIMIT 1 ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public void fat_giris_sil(String fno,String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =  " DELETE " +
				" FROM FATURA " +
				" WHERE Fatura_No  ='" + fno + "'" +
				" AND Gir_Cik = '" + cins + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

		stmt.clearParameters();
		sql = " DELETE " +
				" FROM STOK " +
				" WHERE Evrak_No  ='" + fno + "'" +
				" AND Hareket = '" + cins + "'" +
				" AND Evrak_Cins= 'FAT' ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void fat_kaydet(String fatno ,String kodu ,int depo ,double  fiat ,double tevkifat  
			, double miktar ,String gircik ,double tutar,double iskonto ,double kdv  
			, String tarih, String izah,String doviz,String  adrfirma ,String carfirma  
			, String ozkod ,double kur ,String cins,int  anagrp,int  altgrp ,String usr) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="INSERT INTO FATURA (Fatura_No,Kodu,Depo,Fiat,Tevkifat,Miktar,Gir_Cik,Tutar,Iskonto,Kdv,Tarih,Izahat " +
				" ,Doviz,Adres_Firma,Cari_Firma,Ozel_Kod,Kur,Cins,Ana_Grup,Alt_Grup, USER ) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1,fatno);
		stmt.setString(2, kodu);
		stmt.setInt(3,depo);
		stmt.setDouble(4, fiat);
		stmt.setDouble(5, tevkifat);
		stmt.setDouble(6, miktar);
		stmt.setString(7, gircik);
		stmt.setDouble(8, tutar);
		stmt.setDouble(9, iskonto);
		stmt.setDouble(10, kdv);
		stmt.setString(11, tarih);
		stmt.setString(12, izah);
		stmt.setString(13, doviz);
		stmt.setString(14, adrfirma);
		stmt.setString(15, carfirma);
		stmt.setString(16, ozkod);
		stmt.setDouble(17, kur);
		stmt.setString(18, cins);
		stmt.setInt(19,anagrp);
		stmt.setInt(20,altgrp);
		stmt.setString(21,usr);
		stmt.executeUpdate();
		stmt.close();

	}
	public void dipnot_sil(String ino,String cins,String gircik) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = " DELETE " +
				" FROM DPN" +
				" WHERE Evrak_NO = N'" + ino + "'" +
				" AND Tip = N'" + cins + "'" +
				" AND Gir_Cik = '" + gircik + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void dipnot_yaz(String eno,String bir,String iki,String uc,String tip,String gircik,String usr) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="INSERT INTO DPN (Evrak_No,Tip,Bir,Iki,Uc,Gir_Cik, USER ) " +
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
	public void  fat_no_yaz(String irsno,String fatno) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		String sql = "UPDATE IRSALIYE   SET Fatura_No   = N'" + fatno + "'  WHERE Irsaliye_No   = '" + irsno + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void aciklama_sil(String evrcins,String evrno,String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = " DELETE " +
				" FROM ACIKLAMA " +
				" WHERE EVRAK_CINS = N'" + evrcins + "'" +
				" AND EVRAK_NO = N'" + evrno + "'" +
				" AND Gir_Cik = N'" + cins + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void aciklama_yaz(String evrcins,int satir,String evrno,String aciklama,String gircik) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
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
	public void stok_sil(String eno,String ecins,String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =  " DELETE "+
				" FROM STOK" +
				" WHERE Evrak_No  = N'" + eno + "'" +
				" AND Evrak_Cins = '" + ecins + "'" +
				" AND Hareket = '" + cins + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void stk_kaydet(String evrno ,String evrcins , String tarih,int depo,String urnkodu,double miktar,double fiat ,double tutar
			, double kdvlitut,String hareket ,String izah , int anagrp ,int altgrp ,double kur ,String b1,String doviz 
			, String hspkodu ,String usr) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="INSERT INTO STOK (Evrak_No,Evrak_Cins,Tarih,Depo,Urun_Kodu,Miktar,Fiat,Tutar,Kdvli_Tutar,Hareket,Izahat " +
				" ,Ana_Grup,Alt_Grup,Kur,B1,Doviz,Hesap_Kodu, USER ) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, evrno);
		stmt.setString(2, evrcins);
		stmt.setString(3, tarih);
		stmt.setInt(4, depo);
		stmt.setString(5, urnkodu);
		stmt.setDouble(6, miktar);
		stmt.setDouble(7, fiat);
		stmt.setDouble(8, tutar);
		stmt.setDouble(9, kdvlitut);
		stmt.setString(10, hareket);
		stmt.setString(11, izah);
		stmt.setInt(12, anagrp);
		stmt.setInt(13, altgrp);
		stmt.setDouble(14, kur);
		stmt.setString(15, b1);
		stmt.setString(16, doviz);
		stmt.setString(17, hspkodu);
		stmt.setString(18, usr);
		stmt.executeUpdate();
		stmt.close();
	}
	public ResultSet fatura_oku(String fno,String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =     "SELECT  Fatura_No ,FATURA.Kodu,Tarih ,FATURA.Kdv ,Doviz,ABS(Miktar) as Miktar ,FATURA.Fiat,Cari_Firma,Iskonto, " + 
				" Tevkifat,FATURA.Ana_Grup ,FATURA.Alt_Grup ," + 
				" IFNULL((Select DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = FATURA.DEPO ) , '') AS Depo ,Adres_Firma ," +
				" Ozel_Kod ,Gir_Cik ,MAL.Barkod ,Birim ,Izahat,MAL.Adi,Tutar,Kur " +
				" FROM Fatura USE INDEX (IX_FATURA), MAL USE INDEX (IX_MAL) " +
				" WHERE Fatura.Kodu = MAL.Kodu " +
				" AND Fatura_No = N'" + fno + "'" +
				" AND Gir_Cik = '" + cins + "'";
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;	
	}
	public String aciklama_oku(String evrcins,int satir,String evrno,String gircik) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
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
	public ResultSet irsaliye_no_doldur(String fno,String hareket) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;

		String sql =    "SELECT DISTINCT Irsaliye_No,Cari_Hesap_Kodu ,Tarih " +
				" FROM IRSALIYE   " +
				" Where Fatura_No = N'" + fno + "'" +
				" AND Hareket = '" + hareket + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet dipnot_oku(String ino,String cins ,String gircik) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  "SELECT * " +
				" FROM DPN " +
				" WHERE Evrak_NO = N'" + ino + "'" +
				" AND DPN.Tip = N'" + cins + "'" +
				" AND Gir_Cik = '" + gircik + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public int fatura_no_al(String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		int E_NUMBER ;
		String sql = "SELECT max(Fatura_No + 1) AS NO  FROM FATURA WHERE Gir_Cik = '" + cins + "' ";
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
	public boolean stok_bak_kontrol(String kodu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		boolean result =false;
		String sql = "SELECT  IFNULL(SUM(Miktar),-1) as MIKTAR   FROM STOK USE INDEX(IX_STOK) " +
				" WHERE Urun_Kodu = N'" + kodu + "' ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		if (!rss.isBeforeFirst() ) {  	}
		else		{
			rss.next();
			double mik = rss.getDouble("MIKTAR");
			if (mik < 0)
			{			result = true ;			}
			else			{				result = false;		}
		}
		return result ;
	}
	public boolean stok_bak_kontrol_barcode(String barkodu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		boolean result =false;
		String sql ="SELECT IFNULL(SUM(Miktar),-1) AS MIKTAR " +
				" FROM STOK USE INDEX(IX_STOK)  LEFT JOIN MAL ON STOK.Urun_Kodu = MAL.Kodu " +
				" WHERE Barkod = N'" + barkodu + "' ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		if (!rss.isBeforeFirst() ) {  	}
		else		{
			rss.next();
			double mik = rss.getDouble("MIKTAR");
			if (mik < 0)
			{			result = true ;			}
			else			{				result = false;		}
		}
		return result ;

	}
	public void rec_sil(String rno) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =   " DELETE " +
				" FROM RECETE " +
				" WHERE Recete_No  ='" + rno + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void recete_kayit(String recno,boolean durum,String tur,String kodu ,double miktar ,int anagrp,int altgrup ,String usr) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="INSERT INTO RECETE (Recete_No,Durum,Tur,Kodu,Miktar,Ana_Grup,Alt_Grup, USER ) " +
				" VALUES (?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, recno);
		stmt.setBoolean(2, durum);
		stmt.setString(3, tur);
		stmt.setString(4, kodu);
		stmt.setDouble(5, miktar);
		stmt.setInt(6, anagrp);
		stmt.setInt(7, altgrup);
		stmt.setString(8, usr);
		stmt.executeUpdate();
		stmt.close();
	}
	public void kod_recete_yaz(String ukodu,String rec) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		String sql = "UPDATE MAL SET Recete = N'" + rec + "'  WHERE Kodu = N'" + ukodu + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

	}
	public int recete_no_al() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		int E_NUMBER ;
		String sql = "SELECT max(Recete_No + 1) AS NO  FROM Recete  ";
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
	public String recete_son_bordro_no_al () throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String E_NUMBER = "" ;
		String sql =  "SELECT MAX(Recete_No) as NO  FROM RECETE   LIMIT 1";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		if (!rss.isBeforeFirst() ) {  
			E_NUMBER = "0" ;
		}
		else
		{
			rss.next();
			E_NUMBER = rss.getString("NO");
		}
		return E_NUMBER;	
	}
	public ResultSet recete_oku (String rno) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  "SELECT Recete_No,Durum,Tur,Recete.Kodu,MAL.Adi,MAL.Birim ,Miktar , " +
				" (SELECT ANA_GRUP from ANA_GRUP_DEGISKEN WHERE AGID_Y = RECETE.Ana_Grup ) as Ana_Grup , " +
				" (SELECT ALT_GRUP from ALT_GRUP_DEGISKEN WHERE ALID_Y = RECETE.Alt_Grup ) as Alt_Grup , " +
				" MAL.Kusurat ,RECETE. USER  " +
				" FROM RECETE  , MAL USE INDEX (IX_MAL) "+
				" Where RECETE.KODU = MAL.Kodu " +
				" AND Recete_No = N'" + rno + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet urun_arama() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = " SELECT  MAL.Barkod, MAL.Kodu, MAL.Adi, mal.Sinif,mal.Birim,mal.agirlik as Agirlik,  " +
				" IFNULL((SELECT MENSEI FROM MENSEI_DEGISKEN WHERE MENSEI_DEGISKEN.MEID_Y = MAL.Mensei),'') AS Mensei , " +
				" IFNULL((SELECT ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = MAL.Ana_Grup),'') AS Ana_Grup, " +
				" IFNULL((SELECT ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = MAL.Alt_Grup),'') AS Alt_Grup, " +
				" IFNULL((SELECT OZEL_KOD_1 FROM OZ_KOD_1_DEGISKEN WHERE OZ_KOD_1_DEGISKEN.OZ1ID = MAL.Ozel_Kod_1),'') as Ozel_Kod_1, " +
				" IFNULL((SELECT OZEL_KOD_2 FROM OZ_KOD_2_DEGISKEN WHERE OZ_KOD_2_DEGISKEN.OZ2ID = MAL.Ozel_Kod_2),'') as Ozel_Kod_2, " +
				" mal.Aciklama_1,mal.Aciklama_2 " +
				" FROM MAL USE INDEX (IX_MAL) ORDER BY MAL.Kodu ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet imalat_urun_ara (String kodu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT Kodu,Adi,Birim,Sinif,Ana_Grup,Alt_Grup,Aciklama_1,Aciklama_2 ," +
				" Ozel_Kod_1 ,Ozel_Kod_2 ,Barkod,Mensei,Agirlik,Resim,Fiat,Fiat_2,Recete " +
				" FROM MAL USE INDEX (IX_MAL) " +
				" WHERE Kodu = N'" + kodu + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet resim_oku(String ukodu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT Resim FROM MAL USE INDEX (IX_MAL) WHERE Kodu  = N'" + ukodu + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet recete_arama() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = " SELECT  Recete_No As Recete_Kodu ,Kodu,(SELECT Adi FROM MAL WHERE MAL.Kodu = Recete.Kodu) As Adi " +
				" ,(SELECT Birim FROM MAL WHERE MAL.Kodu = Recete.Kodu) As Birim " +
				" ,Miktar " +
				" ,(Select Ana_Grup FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = Recete.Ana_Grup) AS Ana_Grup " +
				" ,(Select Alt_Grup FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = Recete.Alt_Grup) AS Alt_Grup " +
				" ,(Select ACIKLAMA FROM ACIKLAMA WHERE ACIKLAMA.EVRAK_NO = Recete.Recete_No AND ACIKLAMA.EVRAK_CINS = 'REC' ) AS ACIKLAMA " +
				" ,IF(Durum = '1' , 'Aktif' ,'Pasif') AS Durum " +
				" FROM RECETE " +
				" WHERE Tur = 'Giren' ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet stok_oku(String eno,String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT Evrak_No ,Evrak_Cins,Tarih,Urun_Kodu,Miktar,Fiat ,Tutar, Hareket , " +
				" (SELECT DEPO from DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = STOK.Depo ) as Depo , " +
				" (SELECT ANA_GRUP from ANA_GRUP_DEGISKEN WHERE AGID_Y = STOK.Ana_Grup ) as Ana_Grup , " +
				" (SELECT ALT_GRUP from ALT_GRUP_DEGISKEN WHERE ALID_Y = STOK.Alt_Grup ) as Alt_Grup , " +
				" (SELECT Adi FROM MAL  WHERE MAL.Kodu = STOK.Urun_Kodu ) as Adi , " +
				" (SELECT Birim FROM MAL  WHERE MAL.Kodu = STOK.Urun_Kodu ) as Birim , " +
				" (SELECT Barkod FROM MAL  WHERE MAL.Kodu = STOK.Urun_Kodu ) as Barkod , " +
				" Izahat ,Doviz" +
				" FROM STOK  " +
				" WHERE Evrak_No  =N'" + eno + "'" +
				" AND Evrak_Cins = '" + cins + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public String uret_son_bordro_no_al () throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String E_NUMBER = "" ;
		String sql =   "SELECT max(Evrak_No )  as NO FROM STOK  where Evrak_Cins = 'URE' LIMIT 1 ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		E_NUMBER = rss.getString("NO") == null ? "0" :rss.getString("NO") ;
		return E_NUMBER;	
	}
	public int uretim_fisno_al() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		int E_NUMBER ;
		String sql =  "SELECT  E_No FROM URET_EVRAK  ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		E_NUMBER = rss.getInt("E_No");
		E_NUMBER = E_NUMBER + 1 ;
		
		//******** KAYIT
		sql = "UPDATE URET_EVRAK SET E_No =" + E_NUMBER + " ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		//**************
		return E_NUMBER;	
	}
	public double son_imalat_fiati_oku(String kodu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		double fiat=0 ;
		String sql =  "SELECT  Fiat " +
				" FROM STOK USE INDEX (IX_STOK) " +
				" WHERE Evrak_Cins = 'URE' and Hareket ='C'  " +
				" AND Urun_Kodu = N'" + kodu + "' " +
				" ORDER BY  Tarih DESC LIMIT 1";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		if (!rss.isBeforeFirst() ) {  
			fiat = 0 ;
		}
		else
		{
			rss.next();
			fiat = rss.getDouble("Fiat");
		}
		return fiat;	
	}
	public ResultSet uret_ilk_tarih(String baslangic,String tar,String ukodu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql ="SELECT Urun_Kodu ,  Evrak_Cins,Tarih ,Miktar , " +
				" SUM(Miktar) OVER(ORDER BY Tarih  ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) as Miktar_Bakiye " +
				" FROM STOK USE INDEX (IX_STOK)  WHERE  STOK.Tarih >= '" + baslangic + "'  AND STOK.Tarih < '" + tar + " 23:59:59.998'" +
				" And STOK.Urun_Kodu = N'" + ukodu + "' AND Evrak_Cins <> 'DPO'" +
				" Order by Tarih";
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	public double gir_ort_fiati_oku(String kodu,String ilkt,String tarih) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		double fiat=0 ;
		String sql = "SELECT  IFNULL( SUM(Tutar) / SUM(Miktar),0) as Ortalama " +
				" FROM STOK  USE INDEX (IX_STOK) " +
				" WHERE  Urun_Kodu = N'" + kodu + "' " +
				" AND Hareket = 'G' AND Tarih > '" + ilkt + "' AND  Tarih < '" + tarih + " 23:59:59.998'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		if (!rss.isBeforeFirst() ) {  
			fiat = 0 ;
		}
		else
		{
			rss.next();
			fiat = rss.getDouble("Ortalama");
		}
		return fiat;	
	}
	public int irsaliye_no_al(String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		int E_NUMBER ;
		String sql = "SELECT MAX(Irsaliye_No + 1 ) as NO FROM IRSALIYE WHERE Hareket = '" + cins + "' ";
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
	public ResultSet irsaliye_oku (String ino,String hareket) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT Irsaliye_No,IRSALIYE.Kodu ,Tarih ,IRSALIYE.Kdv,Doviz,abs(Miktar) as Miktar,IRSALIYE.Fiat,Firma,Iskonto ," + 
				"Fatura_No ,Sevk_Tarihi,isnull((Select DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = IRSALIYE.DEPO ) , '') AS Depo , " +
				" Cari_Hesap_Kodu,Ozel_Kod ,IRSALIYE.Ana_Grup ,IRSALIYE.Alt_Grup ,Birim,MAL.Barkod,Mal.Adi ,Tutar,Kur,Izahat" +
				" ,CASE Kusurat " +
				" WHEN '0' THEN format(abs([Miktar]),'#,###') " +
				" WHEN '1' THEN format(abs([Miktar]),'#,##0.0') " +
				" WHEN '2' THEN format(abs([Miktar]),'#,##0.00') " +
				" WHEN '3' THEN format(abs([Miktar]),'#,##0.000') " +
				" END as Miktarr " +
				" FROM IRSALIYE USE INDEX (IX_IRSALIYE)) , MAL USE INDEX (IX_MAL) " +
				" Where IRSALIYE.KODU = MAL.Kodu " +
				" AND Irsaliye_No = N'" + ino + "'" +
				" AND Hareket = '" + hareket + "'";
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	public void irs_giris_sil(String ino,String hareket) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =   " DELETE " +
				" FROM IRSALIYE" +
				" WHERE Irsaliye_No  ='" + ino + "'" +
				" AND Hareket = '" + hareket + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void irs_kayit(String irsno ,String kodu ,int depo ,double fiat ,double  iskon ,double miktar,double tutar ,
			double  kdv ,String  tar ,String dvz ,double kur,String firma ,String crhsp ,String sevktar ,String ozkod ,
			int  anagrp ,int altgrp,String fatno,String harek,String cins ,String usr ,String izahat) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="INSERT INTO IRSALIYE (Irsaliye_No,Kodu,Depo,Fiat,Iskonto,Miktar,Tutar,Kdv, Tarih,Doviz,Kur,Firma " +
				",Cari_Hesap_Kodu,Sevk_Tarihi,Ozel_Kod,Ana_Grup,Alt_Grup,Fatura_No,Hareket,Cins, USER ,Izahat) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1,irsno);
		stmt.setString(2, kodu);
		stmt.setInt(3,depo);
		stmt.setDouble(4, fiat);
		stmt.setDouble(5, iskon);
		stmt.setDouble(6, miktar);
		stmt.setDouble(7, tutar);
		stmt.setDouble(8, kdv);
		stmt.setString(9, tar);
		stmt.setString(10,dvz);
		stmt.setDouble(11, kur);
		stmt.setString(12,firma);
		stmt.setString(13,crhsp);
		stmt.setString(14,sevktar);
		stmt.setString(15,ozkod);
		stmt.setInt(16,anagrp);
		stmt.setInt(17,altgrp);
		stmt.setString(18,fatno);
		stmt.setString(19,harek);
		stmt.setString(20,cins);
		stmt.setString(21,usr);
		stmt.setString(22,izahat);
		stmt.executeUpdate();
		stmt.close();
	}
	public ResultSet irs_oz_kod (String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =   "SELECT DISTINCT  Ozel_Kod  " + 
				"  FROM IRSALIYE WHERE Hareket = '" + cins+ "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet irs_son_satis_fiati_oku(String kodu,String muskodu,String gircik) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =    "SELECT   Fiat " +
				" FROM IRSALIYE USE INDEX (IX_IRSALIYE) " +
				" WHERE  Cari_Hesap_Kodu = N'" + muskodu + "'" +
				" AND  Kodu = N'" + kodu + "'" +
				" AND Hareket = '" + gircik + "'" +
				" ORDER BY  Tarih desc  LIMIT 1";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet ggdy_oku() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =     "SELECT Isim,Adres,Semt,Sehir,GID FROM GDY ORDER BY Isim ASC ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public void gdy_sil(Integer gid) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =    " DELETE  FROM GDY  WHERE GID  ='" + gid + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void gdy_kayit(String isim ,String adres,String semt,String sehir,String usr) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="INSERT INTO GDY (Isim,Adres,Semt,Sehir, USER ) " +
				" VALUES (?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, isim);
		stmt.setString(2, adres);
		stmt.setString(3, semt);
		stmt.setString(4, sehir);
		stmt.setString(5, usr);
		stmt.executeUpdate();
		stmt.close();
	}
	public ResultSet parametre_oku(String nerden , String satsut) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =   " SELECT * " +
				" FROM " + nerden + " WHERE SAT_SUT ='"+ satsut + "' ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public void evr_format_irs(String satsut, double Tarih, double Sevk_Tarih , double FIRMA_KODU ,double FIRMA_UNVANI ,
			double VERGI_DAIRESI,double VERGI_NO ,double GIDECEGI_YER, double NOT_1 ,double NOT_2 ,double NOT_3 ,double BASLIK_BOLUM ,
			double BARKOD,double URUN_KODU ,double URUN_ADI ,double DEPO ,double SIMGE , double BIRIM_FIAT  ,
			double ISKONTO  , double MIKTAR  , double K_D_V  , double TUTAR  , double TUTAR_TOPLAM  ,
			double ISKONTO_TOPLAMI  , double BAKIYE  , double K_D_V_TOPLAMI  , double BELGE_TOPLAMI  , double YAZI_ILE  , double ALT_BOLUM,String usr  ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =   "DELETE IRS_EVRAK_FORMAT WHERE SAT_SUT='" + satsut + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		sql  ="INSERT INTO IRS_EVRAK_FORMAT (SAT_SUT,TARIH,SEVK_TARIH,FIRMA_KODU,FIRMA_UNVANI,VERGI_DAIRESI,VERGI_NO,GIDECEGI_YER ," +  //8
				" NOT_1,NOT_2,NOT_3,BASLIK_BOLUM,BARKOD,URUN_KODU,URUN_ADI,DEPO,SIMGE,BIRIM_FIAT,ISKONTO,MIKTAR, " + 
				" K_D_V,TUTAR,TUTAR_TOPLAM,ISKONTO_TOPLAMI,BAKIYE,K_D_V_TOPLAMI,BELGE_TOPLAMI,YAZI_ILE,ALT_BOLUM, " +  //29
				" N1,N2,N3,N4,N5,N6,N7,N8,N9,N10, USER ) " + //11
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1,satsut);
		stmt.setDouble(2, Tarih);
		stmt.setDouble(3, Sevk_Tarih);
		stmt.setDouble(4, FIRMA_KODU);
		stmt.setDouble(5, FIRMA_UNVANI);
		stmt.setDouble(6, VERGI_DAIRESI);
		stmt.setDouble(7, VERGI_NO);
		stmt.setDouble(8,GIDECEGI_YER );
		stmt.setDouble(9,NOT_1 );
		stmt.setDouble(10,NOT_2 );
		stmt.setDouble(11,NOT_3 );
		stmt.setDouble(12,BASLIK_BOLUM );
		stmt.setDouble(13,BARKOD );
		stmt.setDouble(14,URUN_KODU );
		stmt.setDouble(15, URUN_ADI);
		stmt.setDouble(16,DEPO );
		stmt.setDouble(17,SIMGE );
		stmt.setDouble(18,BIRIM_FIAT );
		stmt.setDouble(19,ISKONTO );
		stmt.setDouble(20,MIKTAR );
		stmt.setDouble(21, K_D_V );
		stmt.setDouble(22,TUTAR );
		stmt.setDouble(23,TUTAR_TOPLAM );
		stmt.setDouble(24,ISKONTO_TOPLAMI);
		stmt.setDouble(25, BAKIYE);
		stmt.setDouble(26,K_D_V_TOPLAMI );
		stmt.setDouble(27,BELGE_TOPLAMI);
		stmt.setDouble(28, YAZI_ILE);
		stmt.setDouble(29,ALT_BOLUM );
		stmt.setDouble(30,0 );
		stmt.setDouble(31,0 );
		stmt.setDouble(32,0 );
		stmt.setDouble(33,0 );
		stmt.setDouble(34,0 );
		stmt.setDouble(35,0 );
		stmt.setDouble(36,0 );
		stmt.setDouble(37,0 );
		stmt.setDouble(38,0 );
		stmt.setDouble(39,0 );
		stmt.setString(40,usr );
		stmt.executeUpdate();
		stmt.close();
	}
	public void evr_format_fat(String satsut, double Tarih,  double FIRMA_KODU ,double FIRMA_UNVANI ,
			double VERGI_DAIRESI,double VERGI_NO ,double GIDECEGI_YER, double NOT_1 ,double NOT_2 ,double NOT_3 ,double BASLIK_BOLUM ,
			double BARKOD,double URUN_KODU ,double URUN_ADI ,double DEPO , double IZAHAT,double SIMGE , double BIRIM_FIAT  ,
			double ISKONTO  , double MIKTAR  , double K_D_V  , double TUTAR  , double TUTAR_TOPLAM  ,
			double ISKONTO_TOPLAMI  , double BAKIYE  , double K_D_V_TOPLAMI  , double BELGE_TOPLAMI  ,
			double TEVKIFAT_ORANI  , double AL_TAR_TEV_ED_KDV  , double TEV_DAH_TOP_TUTAR  ,
			double BEYAN_ED_KDV  , double TEV_HAR_TOP_TUT  , double YAZI_ILE  , double TEV_KASESI  , double ALT_BOLUM  ,
			double N1  , double  N2  , double  N3  , double  N4  , double  N5  , double  N6  , double  N7  , double  N8  , double  N9  , double  N10  ,
			String usr  ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =   "DELETE FAT_EVRAK_FORMAT WHERE SAT_SUT='" + satsut + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		sql  ="INSERT INTO FAT_EVRAK_FORMAT (SAT_SUT,TARIH,FIRMA_KODU,FIRMA_UNVANI,VERGI_DAIRESI,VERGI_NO,GIDECEGI_YER ," +  //8
				" NOT_1,NOT_2,NOT_3,BASLIK_BOLUM,BARKOD,URUN_KODU,URUN_ADI,DEPO, IZAHAT,SIMGE,BIRIM_FIAT,ISKONTO,MIKTAR, " + 
				" K_D_V,TUTAR,TUTAR_TOPLAM,ISKONTO_TOPLAMI,BAKIYE,K_D_V_TOPLAMI,BELGE_TOPLAMI," +
				" TEVKIFAT_ORANI,AL_TAR_TEV_ED_KDV,TEV_DAH_TOP_TUTAR,BEYAN_ED_KDV,TEV_HAR_TOP_TUT,YAZI_ILE,TEV_KASESI,ALT_BOLUM," +
				" N1,N2,N3,N4,N5,N6,N7,N8,N9,N10, USER ) " + //11
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1,satsut);
		stmt.setDouble(2, Tarih);
		stmt.setDouble(3, FIRMA_KODU);
		stmt.setDouble(4, FIRMA_UNVANI);
		stmt.setDouble(5, VERGI_DAIRESI);
		stmt.setDouble(6, VERGI_NO);
		stmt.setDouble(7,GIDECEGI_YER );  // 1
		stmt.setDouble(8,NOT_1 );
		stmt.setDouble(9,NOT_2 );
		stmt.setDouble(10,NOT_3 );
		stmt.setDouble(11,BASLIK_BOLUM );
		stmt.setDouble(12,BARKOD );
		stmt.setDouble(13,URUN_KODU );
		stmt.setDouble(14, URUN_ADI);
		stmt.setDouble(15,DEPO );
		stmt.setDouble(16,IZAHAT );
		stmt.setDouble(17,SIMGE );
		stmt.setDouble(18,BIRIM_FIAT );
		stmt.setDouble(19,ISKONTO );
		stmt.setDouble(20,MIKTAR ); //2
		stmt.setDouble(21, K_D_V );
		stmt.setDouble(22,TUTAR );
		stmt.setDouble(23,TUTAR_TOPLAM );
		stmt.setDouble(24,ISKONTO_TOPLAMI);
		stmt.setDouble(25, BAKIYE);
		stmt.setDouble(26,K_D_V_TOPLAMI );
		stmt.setDouble(27,BELGE_TOPLAMI); //3
		stmt.setDouble(28, TEVKIFAT_ORANI);
		stmt.setDouble(29,AL_TAR_TEV_ED_KDV );
		stmt.setDouble(30,TEV_DAH_TOP_TUTAR );
		stmt.setDouble(31,BEYAN_ED_KDV );
		stmt.setDouble(32,TEV_HAR_TOP_TUT );
		stmt.setDouble(33,YAZI_ILE );
		stmt.setDouble(34,TEV_KASESI );
		stmt.setDouble(35,ALT_BOLUM );
		stmt.setDouble(36,N1 );
		stmt.setDouble(37,N2 );
		stmt.setDouble(38,N3 );
		stmt.setDouble(39,N4 );
		stmt.setDouble(40,N5 );
		stmt.setDouble(41,N6 );
		stmt.setDouble(42,N7 );
		stmt.setDouble(43,N8 );
		stmt.setDouble(44,N9 );
		stmt.setDouble(45,N10 );
		stmt.setString(46,usr );
		stmt.executeUpdate();
		stmt.close();
	}
	public ResultSet fat_rapor(String ino1 , String ino2 , String t1 ,String t2 , String m1 ,String m2,
			String k1 ,String k2 ,String tv1 ,String tv2,String anagrup,String altgrup , String depo,String turu ,String ozel1 ,String ozel2,
			String a1 ,String a2,String dvz ,String dvz2) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =   " SELECT Fatura_No ,IF(Gir_Cik = 'C','Satis','Alis') as Hareket,DATE(Tarih) as Tarih,Cari_Firma ,Adres_Firma,Doviz , sum(Miktar) as Miktar ,sum(Fiat * Miktar) as Tutar, " +
				" SUM(Fiat * Miktar) - sum(((Fiat * Miktar) * Iskonto)/100) as Iskontolu_Tutar " +
				" FROM FATURA USE INDEX (IX_FATURA) " +
				" WHERE FATURA.Fatura_No >= N'" + ino1 + "' AND  FATURA.Fatura_No <= N'" + ino2 + "'" +
				" AND FATURA.Tarih >= '" + t1 + "' AND  FATURA.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND FATURA.Cari_Firma >= N'" + m1 + "' AND  FATURA.Cari_Firma <= N'" + m2 + "' " +
				" AND FATURA.Adres_Firma >= N'" + a1 + "' AND  FATURA.Adres_Firma <= N'" + a2 + "' " +
				" AND FATURA.Kodu >= N'" + k1 + "' AND FATURA.Kodu <= N'" + k2 + "' " +
				" AND FATURA.Doviz >= N'" + dvz + "' AND FATURA.Doviz <= N'" + dvz2 + "' " +
				" AND FATURA.Tevkifat >= '" + tv1 + "' AND FATURA.Tevkifat <= '" + tv2 + "' " +
				" AND FATURA.Ozel_Kod >= N'" + ozel1 + "' AND FATURA.Ozel_Kod <= N'" + ozel2 + "' " +
				" AND FATURA.Ana_Grup " + anagrup +
				" AND FATURA.Alt_Grup " + altgrup +
				" AND FATURA.Depo " + depo +
				" AND FATURA.Gir_Cik Like '" + turu + "%'" +
				" GROUP BY Fatura_No,Gir_Cik,Tarih ,Cari_Firma,Adres_Firma,Doviz  " +
				" ORDER BY  Fatura_No";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet fat_rapor_fat_tar(String ino1 , String ino2 , String t1 ,String t2 , String m1 ,String m2,
			String k1 ,String k2 ,String tv1 ,String tv2,String anagrup,String altgrup , String depo,String turu ,String ozel1 ,String ozel2,
			String a1 ,String a2,String bir ,String dvz ,String dvz2,String iki , String grp) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  " SELECT Fatura_No,IF(Gir_Cik = 'C','Satis','Alis') as Hareket,DATE(Tarih) as Tarih " +
				" " + bir + "" +
				" " + iki + "" +
				" , sum( Miktar) as Miktar " +
				" ,sum( Fiat  *  Miktar ) as Tutar " +
				" ,sum((Fiat * Miktar) - ((Fiat * Miktar) * Iskonto)/100) as Iskontolu_Tutar  " +
				" ,sum((((Fiat * Miktar) - ((Fiat * Miktar) * Iskonto)/100) * Fatura.kdv)/100)  AS Kdv_Tutar " +
				" ,sum((Fiat * Miktar) - ((Fiat * Miktar) * Iskonto)/100 +   (((Fatura.Fiat *  Miktar ) - ((Fatura.Fiat *  Miktar ) *  Iskonto ) / 100) * Fatura.kdv ) / 100)    as Toplam_Tutar " +
				" FROM FATURA USE INDEX (IX_FATURA) " +
				" WHERE FATURA.Fatura_No >= '" + ino1 + "' AND  FATURA.Fatura_No <= '" + ino2 + "'" +
				" AND FATURA.Tarih >= '" + t1 + "' AND  FATURA.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND FATURA.Cari_Firma >= N'" + m1 + "' AND  FATURA.Cari_Firma <= N'" + m2 + "' " +
				" AND FATURA.Adres_Firma >= N'" + a1 + "' AND  FATURA.Adres_Firma <= N'" + a2 + "' " +
				" AND FATURA.Kodu >= N'" + k1 + "' AND FATURA.Kodu <= N'" + k2 + "' " +
				" AND FATURA.Doviz >= N'" + dvz + "' AND FATURA.Doviz <= N'" + dvz2 + "' " +
				" AND FATURA.Tevkifat >= '" + tv1 + "' AND FATURA.Tevkifat <= '" + tv2 + "' " +
				" AND FATURA.Ozel_Kod >= N'" + ozel1 + "' AND FATURA.Ozel_Kod <= N'" + ozel2 + "' " +
				" AND FATURA.Ana_Grup " + anagrup +
				" AND FATURA.Alt_Grup " + altgrup +
				" AND FATURA.Depo " + depo +
				" AND FATURA.Gir_Cik Like '" + turu + "%'" +
				" GROUP BY " + grp + "" +
				" ORDER BY  " + grp + "";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet fat_rapor_cari_kod(String ino1 , String ino2 , String t1 ,String t2 , String m1 ,String m2,
			String k1 ,String k2 ,String tv1 ,String tv2,String anagrup,String altgrup , String depo,String turu ,String ozel1 ,String ozel2,
			String a1 ,String a2,String cari_yer ,String dvz ,String dvz2, String grp) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  " SELECT  " + grp + " ,IF(Gir_Cik = 'C','Satis','Alis') as Hareket " +
				"  " + cari_yer + " " +
				" ,sum( Miktar ) as Miktar " +
				" ,sum( Fiat  *  Miktar ) as Tutar " +
				" ,SUM(Fiat * Miktar) - sum(((Fiat * Miktar) * Iskonto)/100) as Iskontolu_Tutar " +
				" ,sum((((Fatura.Fiat *  Miktar ) - ((Fatura.Fiat *  Miktar ) *  Iskonto ) / 100) * Fatura.kdv ) / 100)  AS Kdv_Tutar " +
				" ,SUM(Fiat * Miktar) - sum(((Fiat * Miktar) * Iskonto)/100) +   sum((((Fatura.Fiat *  Miktar ) - ((Fatura.Fiat *  Miktar ) *  Iskonto ) / 100) * Fatura.kdv ) / 100)    as Toplam_Tutar" +
				" FROM FATURA USE INDEX (IX_FATURA) " +
				" WHERE FATURA.Fatura_No >= '" + ino1 + "' AND  FATURA.Fatura_No <= '" + ino2 + "'" +
				" AND FATURA.Tarih >= '" + t1 + "' AND  FATURA.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND FATURA.Cari_Firma >= N'" + m1 + "' AND  FATURA.Cari_Firma <= N'" + m2 + "' " +
				" AND FATURA.Adres_Firma >= N'" + a1 + "' AND  FATURA.Adres_Firma <= N'" + a2 + "' " +
				" AND FATURA.Kodu >= N'" + k1 + "' AND FATURA.Kodu <= N'" + k2 + "' " +
				" AND FATURA.Doviz >= N'" + dvz + "' AND FATURA.Doviz <= N'" + dvz2 + "' " +
				" AND FATURA.Tevkifat >= '" + tv1 + "' AND FATURA.Tevkifat <= '" + tv2 + "' " +
				" AND FATURA.Ozel_Kod >= N'" + ozel1 + "' AND FATURA.Ozel_Kod <= N'" + ozel2 + "' " +
				" AND FATURA.Ana_Grup " + anagrup +
				" AND FATURA.Alt_Grup " + altgrup +
				" AND FATURA.Depo " + depo +
				" AND FATURA.Gir_Cik Like '" + turu + "%'" +
				" GROUP BY " + grp + ",Gir_Cik" +
				" ORDER BY  " + grp + "";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet imalat_rapor(String f1 , String f2 , String t1 ,String t2 , String k1 ,String k2,
			String r1 ,String r2 ,String depo,String anagrp,String altgrp , String b1 ,String b2,
			String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =   "SELECT Evrak_No, DATE(Tarih) as Tarih,Urun_Kodu, Adi , " +
				" CONVERT(Miktar,Double) as Miktar,  Birim ,  CONVERT((Miktar * Mal.Agirlik),double) as Agirlik ," +
				" (SELECT DEPO from DEPO_DEGISKEN WHERE DPID_Y = STOK.DEPO ) as Depo , " +
				" (SELECT ANA_GRUP from ANA_GRUP_DEGISKEN WHERE AGID_Y = STOK.Ana_Grup ) as Ana_Grup , " +
				" (SELECT ALT_GRUP from ALT_GRUP_DEGISKEN WHERE ALID_Y = STOK.Alt_Grup ) as Alt_Grup  , " +
				" Barkod  , " +
				" Recete ,STOK. USER " +
				" FROM STOK USE INDEX (IX_STOK) ,MAL USE INDEX (IX_MAL)" +
				" WHERE Evrak_Cins = 'URE' " +
				" AND MAL.Ana_Grup " + uanagrp +
				" AND MAL.Alt_Grup " + ualtgrp +
				" AND Hareket = 'G' " +
				" AND Stok.Urun_Kodu = MAL.Kodu  " +
				" AND STOK.Evrak_No >= '" + f1 + "' AND  STOK.Evrak_No <= '" + f2 + "'" +
				" AND Tarih >= '" + t1 + "' AND  Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND STOK.Urun_Kodu >= N'" + k1 + "' AND  STOK.Urun_Kodu <= N'" + k2 + "' " +
				" AND Recete >= N'" + r1 + "' AND Recete <= N'" + r2 + "' " +
				" AND Barkod >= N'" + b1 + "' AND Barkod <= N'" + b2 + "' " +
				" AND STOK.Ana_Grup " + anagrp +
				" AND STOK.Alt_Grup " + altgrp +
				" AND STOK.Depo " + depo +
				" ORDER BY Evrak_No ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet envanter_rapor_u_kodu(String t1 , String t2 , String k1 ,String k2 , String f1 ,String f2,
			String m1 ,String m2 ,String anagrup,String altgrup ,String depo, String depohar ,String ure,
			String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String  wee  = "";
		if (depohar.equals("E"))
			wee = " Like '%' " ;
		else
			wee = " <> 'DPO' " ;

		String ure1  = "";
		if(ure.equals("E"))
			ure1 = " Like '%' ";
		else
			ure1 = " <> 'URE' " ;

		String sql =   " SELECT STOK.Urun_Kodu as Kodu  , mal.Adi ,Mal.Birim as Simge, SUM(IF( Hareket = 'G' ,miktar, 0 )) as Giris_Miktari," +
				" SUM(IF( Hareket = 'G' , miktar , 0 ) * mal.Agirlik )  as Giris_Agirlik," +
				" SUM(IF(Hareket = 'C', abs(miktar), 0 )) as Cikis_Miktari, " +
				" SUM(IF(Hareket = 'C', abs(miktar), 0 ) * MAL.Agirlik)  as Cikis_Agirlik, " +
				" SUM(IF( Hareket = 'G' ,miktar, 0 )) -  SUM(IF(Hareket = 'C',abs( miktar), 0 )) as Stok_Miktari," +
				" SUM(IF( Hareket = 'G' ,miktar, 0 )* mal.Agirlik) -  SUM(IF(Hareket = 'C',abs( miktar), 0 )* mal.Agirlik)     as Stok_Agirlik  " +
				" From STOK USE INDEX (IX_STOK) ,MAL USE INDEX (IX_MAL) " +
				" where mal.Kodu = stok.Urun_Kodu " +
				" And   Kodu >= N'" + k1 + "' AND  Kodu <= N'" + k2 + "' " +
				" AND Mal.Ana_Grup " + uanagrp +
				" AND Mal.Alt_Grup " + ualtgrp +
				" AND Stok.Tarih >= '" + t1 + "' AND  Stok.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND Stok.Urun_Kodu >= N'" + k1 + "' AND Stok.Urun_Kodu <= N'" + k2 + "' " +
				" AND Stok.Evrak_No >= '" + f1 + "' AND Stok.Evrak_No <= '" + f2 + "' " +
				" AND Stok.Ana_Grup " + anagrup +
				" AND Stok.Alt_Grup " + altgrup +
				" AND Stok.Depo " + depo +
				" AND Stok.Evrak_Cins " + wee +
				" AND Stok.Evrak_Cins " + ure1 +
				" Group by STOK.Urun_Kodu, Mal.Adi ,Mal.Birim " +
				" ORDER by STOK.Urun_Kodu, Mal.Adi ,Mal.Birim ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	

	}
	public ResultSet envanter_rapor_ana_grup_alt_grup(String t1 , String t2 , String k1 ,String k2 , String f1 ,String f2,
			String m1 ,String m2 ,String anagrup,String altgrup ,String depo, String depohar ,String ure,
			String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String  wee  = "";
		if (depohar.equals("E"))
			wee = " Like '%' " ;
		else
			wee = " <> 'DPO' " ;
		String ure1  = "";
		if(ure.equals("E"))
			ure1 = " Like '%' ";
		else
			ure1 = " <> 'URE' " ;
		String sql =   " SELECT anaDegisken.ANA_GRUP ,  altDegisken.ALT_GRUP, " +
				" SUM(t.Giris_Miktar) as Giris_Miktar,  " +
				" SUM(t.Giris_Agirlik ) as Giris_Agirlik,  " +
				" SUM(t.Cikis_Miktar) as Cikis_Miktar, " +
				" SUM(t.Cikis_Agirlik ) as Cikis_Agirlik, " +
				" SUM(t.Giris_Miktar - abs(t.Cikis_Miktar)) as Stok_Miktar, " +
				" SUM(t.Stok_Agirlik) as Stok_Agirlik " +
				" FROM " +
				" ((SELECT m.KODU,  " +
				" SUM(CASE WHEN s.Hareket = 'G' THEN s.miktar ELSE 0 END) as Giris_Miktar, " +
				" SUM(CASE WHEN s.Hareket = 'G' THEN s.miktar ELSE 0 END) * m.Agirlik  as Giris_Agirlik, " +
				" SUM(CASE WHEN s.Hareket = 'C' THEN abs(s.miktar) ELSE 0 END) as Cikis_Miktar, " +
				" SUM(CASE WHEN s.Hareket = 'C' THEN abs(s.miktar) ELSE 0 END) * m.Agirlik  as Cikis_Agirlik, " +
				" (SUM(CASE WHEN s.Hareket = 'G' THEN s.miktar ELSE 0 END) -  SUM(CASE WHEN s.Hareket = 'C' THEN abs(s.miktar) ELSE 0 END)) * m.Agirlik as Stok_Agirlik, " +
				" m.Ana_Grup,   m.Alt_Grup " +
				" From MAL m LEFT OUTER JOIN STOK s ON m.Kodu = s.Urun_Kodu " +
				" WHERE   Kodu >= N'" + k1 + "' AND  Kodu <= N'" + k2 + "' " +
				" AND m.Ana_Grup " + uanagrp +
				" AND m.Alt_Grup " + ualtgrp +
				" AND s.Tarih >= '" + t1 + "' AND  s.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND s.Urun_Kodu >= N'" + k1 + "' AND s.Urun_Kodu <= N'" + k2 + "' " +
				" AND s.Evrak_No >= '" + f1 + "' AND s.Evrak_No <= '" + f2 + "' " +
				" AND s.Ana_Grup " + anagrup +
				" AND s.Alt_Grup " + altgrup +
				" AND s.Depo " + depo +
				" AND s.Evrak_Cins " + wee +
				" AND s.Evrak_Cins " + ure1 +
				" Group by m.Kodu, m.Agirlik,m.Ana_Grup, m.Alt_Grup " +
				" ) as t  " +
				" LEFT OUTER JOIN ANA_GRUP_DEGISKEN anaDegisken on t.Ana_Grup = anaDegisken.AGID_Y) " +
				" LEFT OUTER JOIN ALT_GRUP_DEGISKEN altDegisken on t.Alt_Grup = altDegisken.ALID_Y " +
				" Group BY anaDegisken.ANA_GRUP, altDegisken.ALT_GRUP " +
				" ORDER BY ANA_GRUP,ALT_GRUP ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	

	}
	public ResultSet stok_rapor(String t1 , String t2 , String t3 , String t4,String k1 ,String k2 , String f1 ,String f2,
			String f3 ,String turu, String depohar ,String ure,String uanagrp,String ualtgrp,String h1,String h2) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String  kjk  = "";
		if (depohar.equals("E"))
			kjk =  " Evrak_Cins Like '%' " ;
		else
			kjk = " Evrak_Cins <> 'DPO' " ;
		String kjk1  = "";
		if(ure.equals("E"))
			kjk1 =  " Evrak_Cins Like '%' ";
		else
			kjk1 = " Evrak_Cins <> 'URE' " ;
		//" IF(STOK.Evrak_Cins= 'URE','',(SELECT  IFNULL(Cari_Firma,'') FROM FATURA  WHERE  Fatura.Fatura_No = " +
		//"STOK.Evrak_No  and Gir_cik = stok.hareket LIMIT 1)) as Hesap_Kodu, " +
		String sql =  " SELECT Urun_Kodu ,  Barkod , Adi,  Izahat,Evrak_No , " +
				" IF(STOK.Evrak_Cins= 'URE','',Hesap_Kodu) as Hesap_Kodu, " +
				" Evrak_Cins,DATE(Tarih) as Tarih ,Miktar ,  Birim , STOK.Fiat ,STOK.Doviz , " +
				" SUM(Miktar) OVER(ORDER BY Tarih  ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) as Miktar_Bakiye , " +
				" Tutar ," +
				" SUM(Tutar) OVER(ORDER BY Tarih  ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) as Tutar_Bakiye , " +
				" IFNULL((SELECT ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = MAL.Ana_Grup),'') Ana_Grup , " +
				" IFNULL((SELECT ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = MAL.Alt_Grup),'') AS Alt_Grup , " +
				" IFNULL((SELECT DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = STOK.Depo),'') AS Depo ,stok. USER  " +
				" FROM STOK USE INDEX (IX_STOK) ,MAL USE INDEX (IX_MAL) " +
				" WHERE  mal.kodu = stok.Urun_Kodu " +
				" AND MAL.Ana_Grup " + uanagrp +
				" AND MAL.Alt_Grup " + ualtgrp +
				" AND STOK.Evrak_No >= '" + t1 + "' AND  STOK.Evrak_No <= '" + t2 + "'" +
				" AND STOK.Tarih >= '" + t3 + "' AND  STOK.Tarih <= '" + t4 + " 23:59:59.998'" +
				" AND  STOK.Urun_Kodu  >= N'" + k1 + "' AND  STOK.Urun_Kodu  <= N'" + k2 + "' " +
				" AND STOK.Ana_Grup " + f1 +
				" AND STOK.Alt_Grup " + f2 +
				" AND STOK.Depo " + f3 +
				" AND STOK.Hesap_Kodu  >= N'" + h1 + "' AND  STOK.Hesap_Kodu  <= N'" + h2 + "'" +
				" AND " + kjk +
				" AND " + kjk1 +
				" AND STOK.Hareket Like '" + turu + "%' " +
				" Order by Tarih ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet baslik_bak(String baslik,String ordr,String jkj,String ch1,String k1,String k2,String f1,String f2,String t1,String t2) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =   "SELECT "+ baslik + "  FROM STOK ,FATURA " +
				" WHERE  STOK.Evrak_No = FATURA.Fatura_No " +
				" AND " + jkj +
				" AND " + ch1 +
				" AND Urun_Kodu between N'" + k1 + "' and N'" + k2 + "'" +
				" AND FATURA.Cari_Firma between N'" + f1 + "' and N'" + f2 + "'" +
				" AND  STOK.Tarih BETWEEN '" + t1 + "'" +
				" AND  '"  + t2 + " 23:59:59.998'" +
				" " + ordr+ " ";
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;	
	}
	public ResultSet grp_urn_kodlu(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");

		ResultSet	rss = null;
		String replaceString=sstr_1.replace("[","");//replaces all occurrences of 'a' to 'e'  
		String replaceString2=replaceString.replace("]","");//replaces all occurrences of 'a' to 'e'  
		String[] tokens =replaceString2.split(",");
		//   String baslik = "IFNULL(YEAR(Tarih),'') as Yil , " + sstr_2 + "," + replaceString2  + ",";

		if(sstr_4.equals(" (ABS(STOK.Miktar) * MAL.Agirlik)  as Agirlik"))
		{
			sstr_4 =  "(ABS(STOK.Miktar) * MAL.Agirlik)" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar) as Tutar"))
		{
			sstr_4 =  "(ABS(Tutar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Miktar) as Miktar"))
		{
			sstr_4 =  "(ABS(Miktar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA)) as Tutar "))
		{
			sstr_4 =  " ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA))" ;
		}
		String cASE = "" ;
		for (String t : tokens)
		{
			cASE  = cASE + "Sum(CASE WHEN  " + sstr_2 + "  = '"  + t.trim() + "' THEN " + sstr_4 + " ELSE 0 END) AS '"+ t.trim() + "',";
		}
		cASE = cASE.substring(0, cASE.length() - 1);
		String sql = 
				"  SELECT "
						+ "    MAL.Kodu as Urun_Kodu, Adi as Urun_Adi , Birim ,  " + 	cASE
						+ " FROM STOK " + kur_dos + ",MAL " 
						+ " WHERE   " + jkj 
						+ " AND " + ch1 
						+ " AND MAL.Ana_Grup " + qwq6 
						+ " AND MAL.Alt_Grup " + qwq7 
						+ " AND Mal.Ozel_Kod_1 " + qwq8 
						+ " AND STOK.Urun_Kodu = MAL.Kodu " 
						+ " AND  MAL.Sinif BETWEEN N'" + s1 + "' and N'" + s2 + "'" 
						+ " AND Urun_Kodu between N'" + k1 + "' and N'" + k2 + "'" 
						+ " AND (select distinct FATURA.Cari_Firma from fatura where fatura.Fatura_No = stok.Evrak_No   " 
						+ " AND " + jkj1 + " )  between N'" + deg1 + "' and N'" + deg2 + "'" 
						+ " AND  STOK.Tarih BETWEEN '" + t1 + "'" 
						+" AND  '" + t2 + " 23:59:59.998'" 
						+ "  GROUP BY Urun_Kodu , Urun_Adi , Birim  ORDER BY  Urun_Kodu  ;";

		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet grp_urn_kodlu_yil(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		//
		ResultSet	rss = null;
		String replaceString=sstr_1.replace('[',' ');//replaces all occurrences of 'a' to 'e'  
		String replaceString2=replaceString.replace(']',' ');//replaces all occurrences of 'a' to 'e'  
		String[] tokens =replaceString2.split(",");
		//   String baslik = "IFNULL(YEAR(Tarih),'') as Yil , " + sstr_2 + "," + replaceString2  + ",";
		if(sstr_4.equals(" (ABS(STOK.Miktar) * MAL.Agirlik)  as Agirlik"))
		{
			sstr_4 =  "(ABS(STOK.Miktar) * MAL.Agirlik)" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar) as Tutar"))
		{
			sstr_4 =  "(ABS(Tutar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Miktar) as Miktar"))
		{
			sstr_4 =  "(ABS(Miktar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA)) as Tutar "))
		{
			sstr_4 =  " ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA))" ;
		}
		String cASE = "" ;
		for (String t : tokens)
		{
			cASE  = cASE + "Sum(CASE WHEN " + sstr_2 + "  = '" + t.trim() + "'  THEN " + sstr_4 + " ELSE 0 END) AS '"+ t.trim() + "',";
		}
		cASE = cASE.substring(0, cASE.length() - 1);
		String sql = 
				"  SELECT "
						+ "    MAL.Kodu as Urun_Kodu, Adi as Urun_Adi , Birim , YEAR(STOK.Tarih) as Yil ," + 	cASE
						+ " FROM STOK " + kur_dos + ",MAL " 
						+ " WHERE   " + jkj 
						+ " AND " + ch1 
						+ " AND MAL.Ana_Grup " + qwq6 
						+ " AND MAL.Alt_Grup " + qwq7 
						+ " AND Mal.Ozel_Kod_1 " + qwq8 
						+ " AND STOK.Urun_Kodu = MAL.Kodu " 
						+ " AND  MAL.Sinif BETWEEN N'" + s1 + "' and N'" + s2 + "'" 
						+ " AND Urun_Kodu between N'" + k1 + "' and N'" + k2 + "'" 
						+ " AND (select distinct FATURA.Cari_Firma from fatura where fatura.Fatura_No = stok.Evrak_No   " 
						+ " AND " + jkj1 + " )  between N'" + deg1 + "' and N'" + deg2 + "'" 
						+ " AND  STOK.Tarih BETWEEN '" + t1 + "'" 
						+" AND  '" + t2 + " 23:59:59.998'" 
						+ "  GROUP BY  Urun_Kodu , Urun_Adi , Birim  ORDER BY Urun_Kodu  ;";
		//
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet grp_mus_kodlu(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");

		ResultSet	rss = null;
		String replaceString=sstr_1.replace('[',' ');//replaces all occurrences of 'a' to 'e'  
		String replaceString2=replaceString.replace(']',' ');//replaces all occurrences of 'a' to 'e'  
		String[] tokens =replaceString2.split(",");
		//   String baslik = "IFNULL(YEAR(Tarih),'') as Yil , " + sstr_2 + "," + replaceString2  + ",";
		if(sstr_4.equals(" (ABS(STOK.Miktar) * MAL.Agirlik)  as Agirlik"))
		{
			sstr_4 =  "(ABS(STOK.Miktar) * MAL.Agirlik)" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar) as Tutar"))
		{
			sstr_4 =  "(ABS(Tutar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Miktar) as Miktar"))
		{
			sstr_4 =  "(ABS(Miktar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA)) as Tutar "))
		{
			sstr_4 =  " ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA))" ;
		}
		String cASE = "" ;
		for (String t : tokens)
		{
			cASE  = cASE + "Sum(CASE WHEN " + sstr_2 + "  = '" + t.trim() + "'  THEN " + sstr_4 + " ELSE 0 END) AS '"+ t.trim() + "',";
		}
		cASE = cASE.substring(0, cASE.length() - 1);
		String sql = 
				"  SELECT "
						+ "   (SELECT  Cari_Firma FROM FATURA  WHERE STOK.Evrak_No = FATURA.Fatura_No   AND "
						+ "Fatura.Gir_Cik = 'C' LIMIT 1 ) as Musteri_Kodu  ,''  as Musteri_Adi  , " + 	cASE
						+ " FROM STOK " + kur_dos + ",MAL " 
						+ " WHERE   " + jkj 
						+ " AND " + ch1 
						+ " AND MAL.Ana_Grup " + qwq6 
						+ " AND MAL.Alt_Grup " + qwq7 
						+ " AND Mal.Ozel_Kod_1 " + qwq8 
						+ " AND STOK.Urun_Kodu = MAL.Kodu " 
						+ " AND  MAL.Sinif BETWEEN N'" + s1 + "' and N'" + s2 + "'" 
						+ " AND Urun_Kodu between N'" + k1 + "' and N'" + k2 + "'" 
						+ " AND (select distinct FATURA.Cari_Firma from fatura where fatura.Fatura_No = stok.Evrak_No   " 
						+ " AND " + jkj1 + " )  between N'" + deg1 + "' and N'" + deg2 + "'" 
						+ " AND  STOK.Tarih BETWEEN '" + t1 + "'" 
						+" AND  '" + t2 + " 23:59:59.998'" 
						+ "  GROUP BY Musteri_Kodu ,Musteri_Adi order by Musteri_Kodu  ;";
		//
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet grp_mus_kodlu_yil(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String replaceString=sstr_1.replace('[',' ');//replaces all occurrences of 'a' to 'e'  
		String replaceString2=replaceString.replace(']',' ');//replaces all occurrences of 'a' to 'e'  
		String[] tokens =replaceString2.split(",");
		//   String baslik = "IFNULL(YEAR(Tarih),'') as Yil , " + sstr_2 + "," + replaceString2  + ",";
		if(sstr_4.equals(" (ABS(STOK.Miktar) * MAL.Agirlik)  as Agirlik"))
		{
			sstr_4 =  "(ABS(STOK.Miktar) * MAL.Agirlik)" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar) as Tutar"))
		{
			sstr_4 =  "(ABS(Tutar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Miktar) as Miktar"))
		{
			sstr_4 =  "(ABS(Miktar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA)) as Tutar "))
		{
			sstr_4 =  " ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA))" ;
		}
		String cASE = "" ;
		for (String t : tokens)
		{
			cASE  = cASE + "Sum(CASE WHEN " + sstr_2 + "  = '" + t.trim() + "'  THEN " + sstr_4 + " ELSE 0 END) AS '"+ t.trim() + "',";
		}
		cASE = cASE.substring(0, cASE.length() - 1);
		String sql = 
				"  SELECT "
						+ "   (SELECT  Cari_Firma FROM FATURA  WHERE STOK.Evrak_No = FATURA.Fatura_No   AND "
						+ "Fatura.Gir_Cik = 'C' LIMIT 1 ) as Musteri_Kodu  ,(SELECT DISTINCT  UNVAN FROM ok_car" +  BAGLAN.cariDizin.kOD + ".HESAP WHERE hesap.hesap = (SELECT Cari_Firma  \r\n"
						+ "FROM FATURA  WHERE STOK.Evrak_No = FATURA.Fatura_No   And   Fatura.Gir_Cik = 'C'  LIMIT 1 )  ) as Musteri_Adi  , YEAR(Tarih) as Yil, " + 	cASE
						+ " FROM STOK " + kur_dos + ",MAL " 
						+ " WHERE   " + jkj 
						+ " AND " + ch1 
						+ " AND MAL.Ana_Grup " + qwq6 
						+ " AND MAL.Alt_Grup " + qwq7 
						+ " AND Mal.Ozel_Kod_1 " + qwq8 
						+ " AND STOK.Urun_Kodu = MAL.Kodu " 
						+ " AND  MAL.Sinif BETWEEN N'" + s1 + "' and N'" + s2 + "'" 
						+ " AND Urun_Kodu between N'" + k1 + "' and N'" + k2 + "'" 
						+ " AND (select distinct FATURA.Cari_Firma from fatura where fatura.Fatura_No = stok.Evrak_No   " 
						+ " AND " + jkj1 + " )  between N'" + deg1 + "' and N'" + deg2 + "'" 
						+ " AND  STOK.Tarih BETWEEN '" + t1 + "'" 
						+" AND  '" + t2 + " 23:59:59.998'" 
						+ "  GROUP BY Musteri_Kodu ,Musteri_Adi,  YEAR(Tarih) order by Musteri_Kodu  ;";
		//

		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet grp_yil_ay(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");

		ResultSet	rss = null;
		String replaceString=sstr_1.replace('[',' ');//replaces all occurrences of 'a' to 'e'  
		String replaceString2=replaceString.replace(']',' ');//replaces all occurrences of 'a' to 'e'  
		String[] tokens =replaceString2.split(",");
		//   String baslik = "IFNULL(YEAR(Tarih),'') as Yil , " + sstr_2 + "," + replaceString2  + ",";
		if(sstr_4.equals(" (ABS(STOK.Miktar) * MAL.Agirlik)  as Agirlik"))
		{
			sstr_4 =  "(ABS(STOK.Miktar) * MAL.Agirlik)" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar) as Tutar"))
		{
			sstr_4 =  "(ABS(Tutar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Miktar) as Miktar"))
		{
			sstr_4 =  "(ABS(Miktar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA)) as Tutar "))
		{
			sstr_4 =  " ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA))" ;
		}
		String cASE = "" ;
		for (String t : tokens)
		{
			cASE  = cASE + "Sum(CASE WHEN " + sstr_2 + "  = '" + t .trim()+ "' THEN " + sstr_4 + " ELSE 0 END) AS '"+ t.trim() + "',";
		}
		cASE = cASE.substring(0, cASE.length() - 1);
		String sql = 
				"  SELECT "
						+ "     YEAR(STOK.Tarih) as Yil ,MONTH(STOK.Tarih)as Ay ," + 	cASE
						+ " FROM STOK " + kur_dos + ",MAL " 
						+ " WHERE   " + jkj 
						+ " AND " + ch1 
						+ " AND MAL.Ana_Grup " + qwq6 
						+ " AND MAL.Alt_Grup " + qwq7 
						+ " AND Mal.Ozel_Kod_1 " + qwq8 
						+ " AND STOK.Urun_Kodu = MAL.Kodu " 
						+ " AND  MAL.Sinif BETWEEN N'" + s1 + "' and N'" + s2 + "'" 
						+ " AND Urun_Kodu between N'" + k1 + "' and N'" + k2 + "'" 
						+ " AND (select distinct FATURA.Cari_Firma from fatura where fatura.Fatura_No = stok.Evrak_No   " 
						+ " AND " + jkj1 + " )  between N'" + deg1 + "' and N'" + deg2 + "'" 
						+ " AND  STOK.Tarih BETWEEN '" + t1 + "'" 
						+" AND  '" + t2 + " 23:59:59.998'" 
						+ "  GROUP BY  Yil,Ay  ORDER BY Yil,Ay  ;";
		//

		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet grp_yil(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String replaceString=sstr_1.replace('[',' ');//replaces all occurrences of 'a' to 'e'  
		String replaceString2=replaceString.replace(']',' ');//replaces all occurrences of 'a' to 'e'  
		String[] tokens =replaceString2.split(",");
		//   String baslik = "IFNULL(YEAR(Tarih),'') as Yil , " + sstr_2 + "," + replaceString2  + ",";
		if(sstr_4.equals(" (ABS(STOK.Miktar) * MAL.Agirlik)  as Agirlik"))
		{
			sstr_4 =  "(ABS(STOK.Miktar) * MAL.Agirlik)" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar) as Tutar"))
		{
			sstr_4 =  "(ABS(Tutar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Miktar) as Miktar"))
		{
			sstr_4 =  "(ABS(Miktar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA)) as Tutar "))
		{
			sstr_4 =  " ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA))" ;
		}
		String cASE = "" ;
		for (String t : tokens)
		{
			cASE  = cASE + "Sum(CASE WHEN " + sstr_2 + "  = '" + t + "'  THEN " + sstr_4 + " ELSE 0 END) AS '"+ t.trim() + "',";
		}
		cASE = cASE.substring(0, cASE.length() - 1);
		String sql = 
				"  SELECT "
						+ "    YEAR(STOK.Tarih) AS 'Year',  " + 	cASE
						+ " FROM STOK " + kur_dos + ",MAL " 
						+ " WHERE   " + jkj 
						+ " AND " + ch1 
						+ " AND MAL.Ana_Grup " + qwq6 
						+ " AND MAL.Alt_Grup " + qwq7 
						+ " AND Mal.Ozel_Kod_1 " + qwq8 
						+ " AND STOK.Urun_Kodu = MAL.Kodu " 
						+ " AND  MAL.Sinif BETWEEN N'" + s1 + "' and N'" + s2 + "'" 
						+ " AND Urun_Kodu between N'" + k1 + "' and N'" + k2 + "'" 
						+ " AND (select distinct FATURA.Cari_Firma from fatura where fatura.Fatura_No = stok.Evrak_No   " 
						+ " AND " + jkj1 + " )  between N'" + deg1 + "' and N'" + deg2 + "'" 
						+ " AND  STOK.Tarih BETWEEN '" + t1 + "'" 
						+" AND  '" + t2 + " 23:59:59.998'" 
						+ "  GROUP BY YEAR ORDER BY YEAR   ;";
		//	+ "  GROUP BY YEAR  WITH ROLLUP ;";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet grp_ana_grup(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");

		ResultSet	rss = null;
		String replaceString=sstr_1.replace('[',' ');//replaces all occurrences of 'a' to 'e'  
		String replaceString2=replaceString.replace(']',' ');//replaces all occurrences of 'a' to 'e'  
		String[] tokens =replaceString2.split(",");
		//   String baslik = "IFNULL(YEAR(Tarih),'') as Yil , " + sstr_2 + "," + replaceString2  + ",";
		if(sstr_4.equals(" (ABS(STOK.Miktar) * MAL.Agirlik)  as Agirlik"))
		{
			sstr_4 =  "(ABS(STOK.Miktar) * MAL.Agirlik)" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar) as Tutar"))
		{
			sstr_4 =  "(ABS(Tutar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Miktar) as Miktar"))
		{
			sstr_4 =  "(ABS(Miktar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA)) as Tutar "))
		{
			sstr_4 =  " ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA))" ;
		}
		String cASE = "" ;
		for (String t : tokens)
		{
			cASE  = cASE + "Sum(CASE WHEN " + sstr_2 + "  = '" + t.trim() + "'  THEN " + sstr_4 + " ELSE 0 END) AS '"+ t.trim() + "',";
		}
		cASE = cASE.substring(0, cASE.length() - 1);
		String sql = 
				"  SELECT "
						+ "    (SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = MAL.Ana_Grup ) as Ana_Grup ," 
						+	"  (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = MAL.Alt_Grup ) as Alt_Grup , " + 	cASE
						+ " FROM STOK " + kur_dos + ",MAL " 
						+ " WHERE   " + jkj 
						+ " AND " + ch1 
						+ " AND MAL.Ana_Grup " + qwq6 
						+ " AND MAL.Alt_Grup " + qwq7 
						+ " AND Mal.Ozel_Kod_1 " + qwq8 
						+ " AND STOK.Urun_Kodu = MAL.Kodu " 
						+ " AND  MAL.Sinif BETWEEN N'" + s1 + "' and N'" + s2 + "'" 
						+ " AND Urun_Kodu between N'" + k1 + "' and N'" + k2 + "'" 
						+ " AND (select distinct FATURA.Cari_Firma from fatura where fatura.Fatura_No = stok.Evrak_No   " 
						+ " AND " + jkj1 + " )  between N'" + deg1 + "' and N'" + deg2 + "'" 
						+ " AND  STOK.Tarih BETWEEN '" + t1 + "'" 
						+" AND  '" + t2 + " 23:59:59.998'" 
						+ "  GROUP BY Ana_Grup , Alt_Grup  ORDER BY Ana_Grup , Alt_Grup ;";
		//
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet grp_ana_grup_yil(String sstr_2,String sstr_4,String kur_dos,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String replaceString=sstr_1.replace('[',' ');//replaces all occurrences of 'a' to 'e'  
		String replaceString2=replaceString.replace(']',' ');//replaces all occurrences of 'a' to 'e'  
		String[] tokens =replaceString2.split(",");
		//   String baslik = "IFNULL(YEAR(Tarih),'') as Yil , " + sstr_2 + "," + replaceString2  + ",";
		if(sstr_4.equals(" (ABS(STOK.Miktar) * MAL.Agirlik)  as Agirlik"))
		{
			sstr_4 =  "(ABS(STOK.Miktar) * MAL.Agirlik)" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar) as Tutar"))
		{
			sstr_4 =  "(ABS(Tutar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Miktar) as Miktar"))
		{
			sstr_4 =  "(ABS(Miktar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA)) as Tutar "))
		{
			sstr_4 =  " ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA))" ;
		}
		String cASE = "" ;
		for (String t : tokens)
		{
			cASE  = cASE + "Sum(CASE WHEN " + sstr_2 + "  = '" + t.trim() + "'  THEN " + sstr_4 + " ELSE 0 END) AS '"+ t.trim() + "',";
		}
		cASE = cASE.substring(0, cASE.length() - 1);
		String sql = 
				"  SELECT "
						+ "    (SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = MAL.Ana_Grup ) as Ana_Grup ," 
						+	"  (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = MAL.Alt_Grup ) as Alt_Grup ,  YEAR(Tarih) as Yil" + 	cASE
						+ " FROM STOK " + kur_dos + ",MAL " 
						+ " WHERE   " + jkj 
						+ " AND " + ch1 
						+ " AND MAL.Ana_Grup " + qwq6 
						+ " AND MAL.Alt_Grup " + qwq7 
						+ " AND Mal.Ozel_Kod_1 " + qwq8 
						+ " AND STOK.Urun_Kodu = MAL.Kodu " 
						+ " AND  MAL.Sinif BETWEEN N'" + s1 + "' and N'" + s2 + "'" 
						+ " AND Urun_Kodu between N'" + k1 + "' and N'" + k2 + "'" 
						+ " AND (select distinct FATURA.Cari_Firma from fatura where fatura.Fatura_No = stok.Evrak_No   " 
						+ " AND " + jkj1 + " )  between N'" + deg1 + "' and N'" + deg2 + "'" 
						+ " AND  STOK.Tarih BETWEEN '" + t1 + "'" 
						+" AND  '" + t2 + " 23:59:59.998'" 
						+ "  GROUP BY Ana_Grup , Alt_Grup,Yil  ORDER BY  Ana_Grup , Alt_Grup,Yil   ;";
		//
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet ima_baslik_bak(String bas ,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String qwq9,String k1,String k2,String t1,String t2,String ordrr) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  "SELECT "+ bas + "  from STOK ,MAL " +
				" WHERE   " + jkj +
				" AND " + ch1 +
				" AND STOK.Urun_Kodu = MAL.Kodu " +
				" AND MAL.Ana_Grup " + qwq6 +
				" AND MAL.Alt_Grup " + qwq7 +
				" AND STOK.Ana_Grup " + qwq8 +
				" AND STOK.Alt_Grup " + qwq9 +
				" AND Urun_Kodu between N'" + k1 + "' and N'" + k2 + "'" +
				" AND  STOK.Tarih BETWEEN '" + t1 + "'" +
				" AND  '" + t2 + " 23:59:59.998'" +
				"" + ordrr + " ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet ima_alt_kod(String slct,String sstr_5,String sstr_2,String sstr_4,String jkj,String ch1,String qwq6,
			String qwq7,String qwq8,String qwq9,String s1 ,String s2,String k1,String k2,String t1,String t2,
			String sstr_1,String ordrr,String sstr_55) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String replaceString=sstr_1.replace("[","");//replaces all occurrences of 'a' to 'e'  
		String replaceString2=replaceString.replace("]","");//replaces all occurrences of 'a' to 'e'  
		String[] tokens =replaceString2.split(",");
		//   String baslik = "IFNULL(YEAR(Tarih),'') as Yil , " + sstr_2 + "," + replaceString2  + ",";
		if(sstr_4.equals(" (ABS(STOK.Miktar) * MAL.Agirlik)  as Agirlik"))
		{
			sstr_4 =  "(ABS(STOK.Miktar) * MAL.Agirlik)" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar) as Tutar"))
		{
			sstr_4 =  "(ABS(Tutar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Miktar) as Miktar"))
		{
			sstr_4 =  "(ABS(Miktar))" ;
		}
		else  if(sstr_4.equals(" ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA)) as Tutar "))
		{
			sstr_4 =  " ABS(STOK.Tutar / IF(k.MA = 0 ,1, k.MA))" ;
		}
		String cASE = "" ;
		for (String t : tokens)
		{
			cASE  = cASE + "Sum(CASE WHEN  " + sstr_2 + "  = '"  + t.trim() + "' THEN " + sstr_4 + " ELSE 0 END) AS '"+ t.trim() + "',";
		}
		cASE = cASE.substring(0, cASE.length() - 1);
		String qwer = "";
		if(sstr_5.length() != 0 )
		{
			qwer =sstr_5.substring(0, sstr_5.length() - 1) ;
		}
		String gRUP = "" ;
		if (ordrr.trim().equals("Yil")  && slct.trim().equals("YEAR(STOK.Tarih) as Yil")) {
			gRUP = "Yil ORDER BY Yil";}
		else   if (ordrr.trim().equals("Urun_Kodu")  && slct.trim().equals("MAL.Kodu as Urun_Kodu, Adi as Urun_Adi")) {
			gRUP = "Urun_Kodu,  Urun_Adi , Birim ORDER BY Urun_Kodu";  }
		else   if (ordrr.trim().equals("Yil,Ay")  && slct.trim().equals("YEAR(STOK.Tarih) as Yil ,MONTH(STOK.Tarih) as Ay")) {
			gRUP = " Yil , Ay ORDER BY Yil, Ay";    }
		else   if (ordrr.trim().equals("Ana_Grup")  && slct.trim().equals("(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = STOK.Ana_Grup ) as Ana_Grup")) {
			gRUP = " Ana_Grup ,Birim  ORDER BY Ana_Grup";  }
		else   if (ordrr.trim().equals("Ana_Grup")  && slct.trim().equals("(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = STOK.Ana_Grup ) as Ana_Grup ,  (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID = STOK.Alt_Grup ) as Alt_Grup")) {
			gRUP = " Ana_Grup ,Alt_Grup,Birim  ORDER BY Ana_Grup , Alt_Grup";  }
		else   if (ordrr.trim().equals("Yil,Ana_Grup ,Alt_Grup")  && slct.trim().equals("(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID = STOK.Ana_Grup ) as Ana_Grup  , (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID = STOK.Alt_Grup ) as Alt_Grup ,   YEAR(STOK.Tarih) as Yil")) {
			gRUP = " Ana_Grup ,Alt_Grup,Yil , Birim  ORDER BY Ana_Grup , Alt_Grup";  }
		else   if (ordrr.trim().equals("Yil_Ay,Ana_Grup ,Alt_Grup")  && slct.trim().equals("(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID = STOK.Ana_Grup ) as Ana_Grup  , (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID = STOK.Alt_Grup ) as Alt_Grup ,  DATE_FORMAT(stok.tarih, '%Y / %m') as Yil_Ay")) {
			gRUP = " Ana_Grup ,Alt_Grup,Yil_Ay ,Birim ORDER BY Ana_Grup ,Alt_Grup,Yil_Ay";  }
		else   if (ordrr.trim().equals("Depo")  && slct.trim().equals("(SELECT DISTINCT  DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = STOK.Depo ) as Depo")) {
			gRUP = " Depo , Birim ORDER BY  Depo , Birim";  }
		String sql = 
				"  SELECT "
						+    slct + qwer  +  "," + 	cASE
						+ " FROM STOK  ,MAL " 
						+ " WHERE   " + jkj 
						+ " AND " + ch1 
						+ " AND MAL.Ana_Grup " + qwq6 
						+ " AND MAL.Alt_Grup " + qwq7 
						+ " AND STOK.Ana_Grup " + qwq8 
						+ " AND STOK.Alt_Grup " + qwq9 
						+ " AND STOK.Urun_Kodu = MAL.Kodu " 
						+ " AND  MAL.Sinif BETWEEN N'" + s1 + "' and N'" + s2 + "'" 
						+ " AND Urun_Kodu between N'" + k1 + "' and N'" + k2 + "'" 
						+ " AND  STOK.Tarih BETWEEN '" + t1 + "'" 
						+ " AND  '" + t2 + " 23:59:59.998'" 
						+ "  GROUP BY " + gRUP  + "   ;";

		//          	+ "  GROUP BY " + slct.split("as")[0]  + "   ;";
		//     		+ "  GROUP BY Urun_Kodu , Urun_Adi , Birim   ;";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet irs_rapor(String ino1 , String ino2 , String t1 ,String t2 , String m1 ,String m2,
			String k1 ,String k2 ,String turu,String anagrup,String altgrup , String ozkod,
			String fat1,String fat2,String a1 ,String a2) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =   "SELECT IRSALIYE.Irsaliye_No, IF(Hareket = 'C','Satis','Alis') as Hareket , DATE(IRSALIYE.Tarih) as Tarih ,IRSALIYE.Cari_Hesap_Kodu,IRSALIYE.Firma as Adres_Firma,  " +
				" mal.Birim, SUM(IRSALIYE.Miktar) AS Miktar ,sum((IRSALIYE.Miktar * IRSALIYE.Fiat)) as Tutar " +
				" FROM  IRSALIYE USE INDEX (IX_IRSALIYE) , MAL USE INDEX (IX_MAL) " +
				" WHERE IRSALIYE.Kodu = mal.Kodu " +
				" AND  IRSALIYE.Irsaliye_No >= '" + ino1 + "' AND  IRSALIYE.Irsaliye_No <= '" + ino2 + "'" +
				" AND  IRSALIYE.Tarih >= '" + t1 + "' AND  IRSALIYE.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND IRSALIYE.Cari_Hesap_Kodu >= N'" + m1 + "' AND  IRSALIYE.Cari_Hesap_Kodu <= N'" + m2 + "' " +
				" AND IRSALIYE.Firma >= N'" + a1 + "' AND  IRSALIYE.Firma <= N'" + a2 + "' " +
				" AND IRSALIYE.Kodu >= N'" + k1 + "' AND IRSALIYE.Kodu <= N'" + k2 + "' " +
				" AND IRSALIYE.Fatura_No >= '" + fat1 + "' AND IRSALIYE.Fatura_No <= '" + fat2 + "' " +
				" AND IRSALIYE.Ana_Grup " + anagrup +
				" AND IRSALIYE.Alt_Grup " + altgrup +
				" AND Ozel_Kod Like N'" + ozkod + "%' " +
				" AND Hareket Like '" + turu + "%' " +
				" GROUP BY IRSALIYE.Irsaliye_No,Hareket  , IRSALIYE.Tarih,IRSALIYE.Cari_Hesap_Kodu,IRSALIYE.Firma,  mal.Birim " +
				" ORDER BY IRSALIYE.Irsaliye_No ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet rec_rapor(String f1,String f2,String k1,String k2,String anagrp,String altgrp,String durum,String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  "SELECT Recete_No,Recete.Kodu, Adi , " +
				" Miktar,  Birim , " +
				" (SELECT ANA_GRUP from ANA_GRUP_DEGISKEN WHERE AGID_Y = RECETE.Ana_Grup ) as Ana_Grup , " +
				" (SELECT ALT_GRUP from ALT_GRUP_DEGISKEN WHERE ALID_Y = RECETE.Alt_Grup ) as Alt_Grup  , " +
				" (Select ACIKLAMA from ACIKLAMA where EVRAK_CINS ='REC' and RECETE.Recete_No = ACIKLAMA.EVRAK_NO AND ACIKLAMA.Gir_Cik='G' ) as Aciklama   , " +
				" IF(Durum = 1,'Aktif', 'Pasif') as Durum,RECETE. USER  " +
				" FROM RECETE ,MAL USE INDEX (IX_MAL)" +
				" WHERE  Tur = 'Giren' " +
				" AND RECETE.Kodu = MAL.Kodu  " +
				" AND MAL.Ana_Grup " + uanagrp +
				" AND MAL.Alt_Grup " + ualtgrp +
				" AND RECETE.Recete_No >= '" + f1 + "' AND  RECETE.Recete_No <= '" + f2 + "'" +
				" AND RECETE.Kodu >= N'" + k1 + "' AND  RECETE.Kodu <= N'" + k2 + "' " +
				" AND RECETE.Ana_Grup " + anagrp +
				" AND RECETE.Alt_Grup " + altgrp +
				" AND RECETE.Durum Like '" + durum + "%'" +
				" ORDER BY Recete_No";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet rec_detay_rapor(String f1,String f2) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT Recete_No,'Cikan' as Turu, Recete.Kodu, Adi , " +
				" Miktar,  Birim , " +
				" (SELECT ANA_GRUP from ANA_GRUP_DEGISKEN WHERE AGID_Y = MAL.Ana_Grup ) as Ana_Grup , " +
				" (SELECT ALT_GRUP from ALT_GRUP_DEGISKEN WHERE ALID_Y = MAL.Alt_Grup ) as Alt_Grup  , " +
				" Barkod  , " +
				" IF(Durum = 1,'Aktif','Pasif') as Durum,RECETE. USER  " +
				" FROM RECETE ,MAL USE INDEX (IX_MAL) " +
				" WHERE  Tur = 'Cikan' " +
				" AND RECETE.Kodu = MAL.Kodu  " +
				" AND RECETE.Recete_No >= '" + f1 + "' AND  RECETE.Recete_No <= '" + f2 + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet irs_detay_rapor(String ino , String turu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =   "SELECT IRSALIYE.Kodu,   mal.Adi, IRSALIYE.Miktar, " +
				" mal.Birim,IRSALIYE.Fiat,(IRSALIYE.Miktar * IRSALIYE.Fiat) as Tutar " +
				" ,IRSALIYE.KDV as Kdv,IRSALIYE.Doviz,    IRSALIYE.Iskonto, " +
				" (select ANA_GRUP from ANA_GRUP_DEGISKEN where ANA_GRUP_DEGISKEN.AGID_Y = IRSALIYE.Ana_Grup) as Ana_Grup , " +
				" (select ALT_GRUP from ALT_GRUP_DEGISKEN where ALT_GRUP_DEGISKEN.ALID_Y = IRSALIYE.Alt_Grup) as Alt_Grup , " +
				" Ozel_Kod  ,Fatura_No " +
				" FROM     IRSALIYE , MAL USE INDEX (IX_MAL) " +
				" WHERE IRSALIYE.Kodu = MAL.Kodu " +
				" AND  IRSALIYE.Irsaliye_No = '" + ino + "'"  +
				" AND Hareket Like '" + turu + "%' " +
				" ORDER BY IRSALIYE.Kodu";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet ort_hes_kodu(String fdf,String qwq6 , String qwq7, String m1 ,String m2,String s1,String s2,
			String k1 ,String k2 ,String t1,String t2,String kurkod,String kurc) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;

		String sql =  "Select  FATURA.Cari_Firma, " + fdf + "," +
				" SUM(abs(Tutar - (abs(tutar) * Iskonto /100))) As Tutar,  " +
				" SUM(abs(Miktar) * Agirlik) As Agirlik, " +
				" sum( (abs(tutar) - (abs(tutar) * Iskonto /100) ) / kurlar.MA) as  " + kurc + "_Tutar  ," +
				"  sum((abs(tutar) - (abs(tutar) * Iskonto /100) ) )   /   NULLIF( (sum(abs(Miktar) * Agirlik)),0) as Ort_Satis ," + 
				"  sum(((abs(tutar - (tutar * Iskonto /100) )) / kurlar.MA ))   /   NULLIF( (sum(abs(Miktar) * Agirlik)),0)   As  Kg_"+ kurc +" " +
				"  FROM  FATURA ,  ok_kur"+ kurkod +".kurlar , MAL  "  + 
				"  WHERE   MAL.Ana_Grup  " + qwq6 +
				" And MAL.Alt_Grup  " + qwq7 +
				" And  MAL.Sinif BETWEEN N'" + s1 + "' and N'" + s2 + "'" + 
				" AND Fatura.Kodu between N'" + k1 + "' and N'" + k2 + "'" +
				" AND Fatura.Gir_Cik = 'C'  " + 
				" and  MAL.Kodu = Fatura.Kodu " +
				" AND FATURA.Cari_Firma  between  N'" + m1 + "' and N'" + m2 + "'" + 
				" AND Fatura.Tarih BETWEEN '" + t1 + "' AND '" + t2 +" 23:59:59.998' " +  
				" AND kurlar.Tarih =DATE(Fatura.Tarih) and kurlar.Kur = '" + kurc + "' " +
				" GROUP BY FATURA.Cari_Firma  " +
				" ORDER BY FATURA.Cari_Firma  ";

		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet ort_hes_ana_kodu(String fdf,String qwq6 , String qwq7, String m1 ,String m2,String s1,String s2,
			String k1 ,String k2 ,String t1,String t2,String kurkod,String kurc) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  "Select  FATURA.Cari_Firma, " + fdf + "," +
				"  (SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = MAL.Ana_Grup ) as Ana_Grup ," +
				"  (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = MAL.Alt_Grup ) as Alt_Grup ," +
				" SUM(abs(Tutar - (abs(tutar) * Iskonto /100))) As Tutar,  " +
				" SUM(abs(Miktar) * Agirlik) As Agirlik, " +
				" sum( (abs(tutar) - (abs(tutar) * Iskonto /100) ) / kurlar.MA) as  " + kurc + "_Tutar  ," +
				"  sum((abs(tutar) - (abs(tutar) * Iskonto /100) ) )   /   NULLIF( (sum(abs(Miktar) * Agirlik)),0) as Ort_Satis ," + 
				"  sum(((abs(tutar - (tutar * Iskonto /100) )) / kurlar.MA ))   /   NULLIF( (sum(abs(Miktar) * Agirlik)),0)   As  Kg_"+ kurc +" " +
				"  FROM  FATURA ,  ok_kur"+ kurkod +".dbo.kurlar , MAL  "  + 
				"  WHERE   MAL.Ana_Grup  " + qwq6 +
				" And MAL.Alt_Grup  " + qwq7 +
				" And  MAL.Sinif BETWEEN N'" + s1 + "' and N'" + s2 + "'" + 
				" AND Fatura.Kodu between N'" + k1 + "' and N'" + k2 + "'" +
				" AND Fatura.Gir_Cik = 'C'  " + 
				" and  MAL.Kodu = Fatura.Kodu " +
				" AND FATURA.Cari_Firma  between  N'" + m1 + "' and N'" + m2 + "'" + 
				" AND Fatura.Tarih BETWEEN '" + t1 + "' AND '" + t2 +" 23:59:59.998' " +  
				" AND kurlar.Tarih = DATE(Fatura.Tarih) and kurlar.Kur = '" + kurc + "' " +
				" GROUP BY FATURA.Cari_Firma , MAL.Ana_Grup,MAL.Alt_Grup " +
				" ORDER BY FATURA.Cari_Firma , MAL.Ana_Grup,MAL.Alt_Grup ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet ort_diger_kodu(String yu,String qwq6 , String qwq7, String m1 ,String m2,String s1,String s2,
			String k1 ,String k2 ,String t1,String t2,String kurkod,String kurc,String iu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  "Select   " + yu + "," +
				" SUM(abs(STOK.Tutar)) As Tutar,  " +
				"  SUM(abs(STOK.Tutar / kurlar.MA)) as "+ kurc +"_Tutar , " +
				" SUM(abs(STOK.Miktar))  As Miktar, " +
				" SUM(abs(STOK.Miktar) * MAL.Agirlik) As Agirlik, " +
				" SUM(abs(STOK.Tutar)) /  IF( sum( abs(STOK.Miktar)  * MAL.Agirlik   ) = 0,1, " +
				" SUM(abs(STOK.Miktar) * MAL.Agirlik)) As Kg_Satis , " +
				" (	 SUM(abs(STOK.Tutar / kurlar.MA)) /      NULLIF(      SUM(abs(STOK.Miktar) * MAL.Agirlik),0))  As Kg_"+ kurc +" " +
				" From STOK,    MAL ,ok_kur" + kurkod +".kurlar  " +
				" WHERE    STOK.Hareket = 'C' " +
				" And Evrak_Cins = 'FAT' " +
				" And MAL.Ana_Grup " + qwq6 +
				" And MAL.Alt_Grup " + qwq7 +
				" And STOK.Urun_Kodu = MAL.Kodu " +
				" And  MAL.Sinif BETWEEN N'" + s1 + "' and N'" + s2 + "'" +
				"  AND Urun_Kodu between N'" + k1 + "' and N'" + k2 + "'" +
				"  AND (select distinct FATURA.Cari_Firma from fatura where fatura.Fatura_No = stok.Evrak_No   " +
				"  AND Fatura.Gir_Cik = 'C' )  between N'" + m1 + "' and N'" + m2 + "'" +
				"  and STOK.Tarih BETWEEN '" + t1 + "'" +
				"  AND '" + t2 + " 23:59:59.998'"  +
				"  AND kurlar.Tarih = DATE(STOK.Tarih) and kurlar.Kur = '" + kurc + "'  " +
				"  GROUP BY  " + iu ;

		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();

		return rss;	
	}
	public ResultSet fat_detay_rapor(String fno , String turu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  "SELECT  Fatura.Kodu, " +
				" (SELECT Adi FROM MAL WHERE FATURA.Kodu = MAL.KODU ) AS Adi , " +
				"  Miktar , " +
				" (SELECT Birim FROM MAL WHERE FATURA.KODU = MAL.KODU ) AS Birim , " +
				" Fatura.Fiat ,Doviz,Fatura.Fiat * Miktar as Tutar , " +
				" Iskonto , " +
				" ((Fatura.Fiat * Miktar) * Iskonto) / 100 as Iskonto_Tutar , " +
				" (Fatura.Fiat *  Miktar ) - ((Fatura.Fiat *  Miktar ) *  Iskonto ) / 100 as Iskontolu_Tutar , " +
				" Fatura.Kdv , " +
				" (((Fatura.Fiat *  Miktar ) - ((Fatura.Fiat *  Miktar ) *  Iskonto ) / 100) * Fatura.kdv ) / 100  AS Kdv_Tutar , " +
				" Tevkifat , " +
				" (((((Fatura.Fiat *  Miktar ) - ((Fatura.Fiat *  Miktar ) *  Iskonto ) / 100) * Fatura.kdv ) / 100)/ 10 ) *  Tevkifat  as Tev_Edilen_KDV , " +
				" ((Fatura.Fiat *  Miktar ) - ((Fatura.Fiat *  Miktar ) *  Iskonto ) / 100) + ((((Fatura.Fiat *  Miktar ) - ((Fatura.Fiat *  Miktar ) *  Iskonto ) / 100) * Fatura.kdv ) / 100) as Tev_Dah_Top_Tutar , " +
				" ((((Fatura.Fiat *  Miktar ) - ((Fatura.Fiat *  Miktar ) *  Iskonto ) / 100) * Fatura.kdv ) / 100) - ((((((Fatura.Fiat *  Miktar ) - ((Fatura.Fiat *  Miktar ) *  Iskonto ) / 100) * Fatura.kdv ) / 100)/ 10 ) *  Tevkifat  ) as Beyan_Edilen_KDV , " +
				" (((Fatura.Fiat *  Miktar ) - ((Fatura.Fiat *  Miktar ) *  Iskonto ) / 100) + ((((Fatura.Fiat *  Miktar ) - ((Fatura.Fiat *  Miktar ) *  Iskonto ) / 100) * Fatura.kdv ) / 100)) - ((((((Fatura.Fiat *  Miktar ) - ((Fatura.Fiat *  Miktar ) *  Iskonto ) / 100) * Fatura.kdv ) / 100)/ 10 ) *  Tevkifat ) as Tev_Har_Top_Tutar , " +
				" IFNULL((SELECT Ana_Grup FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = MAL.Ana_Grup),'') AS Ana_Grup  , " +
				" IFNULL((SELECT Alt_Grup FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = MAL.Alt_Grup),'') AS Alt_Grup  , " +
				" IFNULL((SELECT Depo FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = FATURA.Depo),'') AS Depo , " +
				" Ozel_Kod ,Izahat, IF(Gir_Cik = 'C','Satis','Alis') as Hareket ,FATURA. USER " +
				" FROM FATURA,MAL " +
				" WHERE FATURA.Fatura_No = '" + fno + "' " + 
				" AND FATURA.Gir_Cik Like '" + turu + "%'" +
				" AND FATURA .Kodu = mal.Kodu " ;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet bos_kur(String kur , String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  "SELECT DISTINCT DATE( s.Tarih) as Tarih  " +
				" FROM STOK s left outer join ok_kur" +  BAGLAN.kurDizin.kOD + ".kurlar k on DATE( k.Tarih) = DATE( s.Tarih) and k.Kur = '" + kur + "'" +
				" WHERE k." + cins + " IS NULL OR k." + cins + " =0 " +
				" ORDER BY DATE(s.Tarih)  " ;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet urun_liste(String k1 , String k2,String b1 , String b2,String bi1 , String bi2,String s1 , String s2,
			String anagrp , String altgrp,String mensei,String o1 , String o2,String  depo) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  "SELECT Kodu,Barkod ,Adi,Birim,Kusurat,Sinif, " +
				" IFNULL((SELECT ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = MAL.Ana_Grup ),'') AS Ana_Grup , " +
				" IFNULL((SELECT ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = MAL.Alt_Grup ),'') AS Alt_Grup, " +
				" Aciklama_1, " +
				" Aciklama_2, " +
				" IFNULL((SELECT OZEL_KOD_1 FROM OZ_KOD_1_DEGISKEN WHERE OZ_KOD_1_DEGISKEN.OZ1ID_Y = MAL.Ozel_Kod_1 ),'') AS Ozel_Kod_1 , " +
				" IFNULL((SELECT OZEL_KOD_2 FROM OZ_KOD_2_DEGISKEN WHERE OZ_KOD_2_DEGISKEN.OZ2ID_Y = MAL.Ozel_Kod_2 ),'') AS Ozel_Kod_2 , " +
				" KDV , " +
				" IFNULL((SELECT MENSEI FROM MENSEI_DEGISKEN WHERE MENSEI_DEGISKEN.MEID_Y = MAL.Mensei ),'') AS Mensei , " +
				" Agirlik , " +
				" IFNULL((SELECT DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = MAL.Depo ),'') AS Depo , " +
				" Fiat , " +
				" Fiat_2 , " +
				" Recete , " +
				" Resim " +
				" FROM MAL USE INDEX (IX_MAL)  " +
				" WHERE Kodu >= N'" + k1 + "' AND  Kodu <= N'" + k2 + "'" +
				" AND  Barkod >= N'" + b1 + "' AND  Barkod <= N'" + b2 + "'" +
				" AND Birim >= N'" + bi1 + "' AND  Birim <= N'" + bi2 + "' " +
				" AND Sinif >= N'" + s1 + "' AND Sinif <= N'" + s2 + "' " +
				" AND Ana_Grup " + anagrp +
				" AND Alt_Grup " + altgrp +
				" AND Mensei  " + mensei +
				" AND Ozel_Kod_1 " + o1 +
				" AND Ozel_Kod_2 " + o2 +
				" AND Depo " + depo +
				" ORDER BY Kodu " ;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet uret_doldur(String t1,String t2) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =   "SELECT  STOK.Evrak_No, DATE(Tarih),Urun_Kodu,Miktar,Fiat,Tutar,IF(Hareket = 'C','Cikan','Giren') As Hareket ,Izahat " +
				" , USER  ,ACID " +
				" FROM  STOK USE INDEX (IX_STOK) LEFT JOIN  ACIKLAMA on STOK.Evrak_No = ACIKLAMA.EVRAK_NO and STOK.Hareket = ACIKLAMA .Gir_Cik AND STOK.Evrak_Cins = ACIKLAMA.EVRAK_CINS  " +
				" WHERE STOK.Evrak_Cins = 'URE'  AND Hareket = 'G' " +
				" AND Tarih >= '" + t1 + "' AND Tarih <= '" + t2 + " 23:59:59.998' " +
				" ORDER BY Tarih " ;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public void uret_no_degis(String e_no,String y_no) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  = "UPDATE STOK SET B1 = N'" + y_no + "'  WHERE Evrak_Cins = 'URE' AND Evrak_No = N'" + e_no + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void uret_b1_degis(String y_no) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="UPDATE STOK SET Evrak_No = N'" + y_no + "'  WHERE Evrak_Cins = 'URE' AND B1 = N'" + y_no + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void uret_b1_sifir(String y_no) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String zas = "" ;
		String sql  ="UPDATE STOK SET B1 = '" + zas + "'  WHERE Evrak_Cins = 'URE' AND Evrak_No = N'" + y_no + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void uret_izahat_duzelt(String y_no) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="UPDATE STOK SET Izahat = N'" + y_no + " Nolu Fis Ile Uretim'  WHERE Evrak_Cins = 'URE' AND Evrak_No = N'" + y_no + "' AND Hareket = 'G' ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void uret_izahat_cikis_duzelt(String y_no,String eski_no) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="UPDATE STOK SET Izahat = N'" + y_no + " Nolu Uretimde CIKAN'  WHERE Evrak_Cins = 'URE' AND Evrak_No = N'" + y_no + "' AND Hareket = 'C' AND Izahat Like N'" + eski_no + "%'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void uret_aciklama_duzelt(String y_no,int asid) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="UPDATE ACIKLAMA SET EVRAK_NO = N'" + y_no + "'  WHERE  ACID = '" + asid + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void uretim_fisno_yapilanma_kayit(int nom) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="UPDATE URET_EVRAK SET E_No =" + nom + "  ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public ResultSet sql_sorgu(String sql)  throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public int zayi_fisno_al() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		int E_NUMBER ;
		String sql =  "SELECT  E_No FROM ZAYI_EVRAK  ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		E_NUMBER = rss.getInt("E_No");
		E_NUMBER = E_NUMBER + 1 ;
		//******** KAYIT
		sql = "UPDATE ZAYI_EVRAK SET E_No =" + E_NUMBER + " ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		//**************
		return E_NUMBER;	
	}
	public ResultSet zayi_oku(String eno,String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT Evrak_No ,Evrak_Cins,Tarih,Urun_Kodu,Miktar,Fiat ,Tutar, Hareket , " +
				" (SELECT DEPO from DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = STOK.Depo ) as Depo , " +
				" (SELECT ANA_GRUP from ANA_GRUP_DEGISKEN WHERE AGID_Y = STOK.Ana_Grup ) as Ana_Grup , " +
				" (SELECT ALT_GRUP from ALT_GRUP_DEGISKEN WHERE ALID_Y = STOK.Alt_Grup ) as Alt_Grup , " +
				" (SELECT Adi FROM MAL  WHERE MAL.Kodu = STOK.Urun_Kodu ) as Adi , " +
				" (SELECT Birim FROM MAL  WHERE MAL.Kodu = STOK.Urun_Kodu ) as Birim , " +
				" (SELECT Barkod FROM MAL  WHERE MAL.Kodu = STOK.Urun_Kodu ) as Barkod , " +
				" Izahat ,Doviz" +
				" FROM STOK  " +
				" WHERE Evrak_No  =N'" + eno + "'" +
				" AND Evrak_Cins = '" + cins + "' AND Hareket ='C'";
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;	
	}
	public String zayi_son_bordro_no_al () throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String E_NUMBER = "" ;
		String sql =   "SELECT max(Evrak_No )  as NO FROM STOK  where Evrak_Cins = 'ZAI'  LIMIT 1  ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		E_NUMBER = rss.getString("NO") == null ? "0" :rss.getString("NO") ;
		return E_NUMBER;	
	}
	public ResultSet fatura_oku_printer(String fno,String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =     "SELECT  Fatura_No ,FATURA.Kodu,Tarih ,FATURA.Kdv ,Doviz,FATURA.Fiat,Cari_Firma,Iskonto,Tevkifat,FATURA.Ana_Grup ,FATURA.Alt_Grup ,isnull((Select DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID = FATURA.DEPO ) , '') AS Depo ,Adres_Firma ," +
				" Ozel_Kod ,Gir_Cik ,MAL.Barkod ,Birim ,Izahat,MAL.Adi,Tutar,Kur " +
				" ,CASE Kusurat " +
				" WHEN '0' THEN format(abs([Miktar]),'#,###') " +
				" WHEN '1' THEN format(abs([Miktar]),'#,##0.0') " +
				" WHEN '2' THEN format(abs([Miktar]),'#,##0.00') " +
				" WHEN '3' THEN format(abs([Miktar]),'#,##0.000') " +
				" END as Miktar " +
				" FROM Fatura , MAL USE INDEX (IX_MAL) " +
				" WHERE Fatura.Kodu = MAL.Kodu " +
				" AND Fatura_No = N'" + fno + "'" +
				" AND Gir_Cik = '" + cins + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public String son_no_al(String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String result ;
		String sql = "SELECT max(Fatura_No)  as NO FROM FATURA WHERE Gir_Cik = '" + cins + "' ";
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
	public ResultSet envanter_rapor(String t1,String t2,String k1 ,String k2 ,String f1 ,String f2 , String m1 ,String m2,
			String anagrup,String altgrup,String depo,String depohar,String ure ,
			String uanagrp,String ualtgrp,String calisanpara) throws ClassNotFoundException, SQLException
	{

		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String wee  = "" ;
		if ( depohar.equals("E") )
		{
			wee = " Like '%' " ;
		}
		else
		{
			wee = " <> 'DPO' ";
		}
		String ure1 = "";
		if (ure.equals("E") )
		{
			ure1 = " Like '%' " ;
		}
		else
		{
			ure1 = " <> 'URE' " ;
		}

		String sql =    " SELECT mal.Kodu As Kodu ,mal.Adi as Adi, mal.Birim as Simge, " +
				"case when mal.Kusurat = 0 then Format( IFNULL((SELECT sum(Miktar) FROM STOK d " +
				" WHERE  d.Urun_Kodu=mal.kodu AND d.Hareket='G' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.999'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ) ,0),'N0') " + // N0
				" when mal.Kusurat = 1 then Format( IFNULL((SELECT sum(Miktar) FROM STOK d " +
				" WHERE  d.Urun_Kodu=mal.kodu And d.Hareket='G' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.999'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ) ,0),'N1') " + // N1
				" when mal.Kusurat = 2 then Format( IFNULL((SELECT sum(Miktar) FROM STOK d " +
				" WHERE  d.Urun_Kodu=mal.kodu And d.Hareket='G' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.999'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ) ,0),'N2') " + // N2
				" ELSE Format( IFNULL((SELECT sum(Miktar) FROM STOK d " +
				" WHERE  d.Urun_Kodu=mal.kodu And d.Hareket='G' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.999'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ) ,0),'N3') " +
				" End  As Giris_Miktari  , " +
				" IFNULL((Select  sum(Tutar) " +
				" FROM STOK d " +
				" WHERE  d.Urun_Kodu=mal.kodu And  d.Hareket='G' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.999'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0) as Giris_Tutar, " +
				" CASE WHEN kusurat = 0 THEN format(IFNULL((SELECT sum(abs(miktar)) FROM STOK d " +
				" WHERE  d.Urun_Kodu=mal.kodu AND  d.Hareket ='C' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.999'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0),'N0') " +
				" WHEN kusurat = 1 THEN format(IFNULL((SELECT sum(abs(miktar)) FROM STOK d " +
				" WHERE  d.Urun_Kodu=mal.kodu And  d.Hareket ='C' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.999'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0),'N1') " +
				" WHEN kusurat = 2 THEN format(IFNULL((SELECT sum(abs(miktar)) FROM STOK d " +
				" WHERE  d.Urun_Kodu=mal.kodu And  d.Hareket ='C' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.999'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0),'N2') " +
				" ELSE format(IFNULL((SELECT sum(abs(miktar)) FROM STOK d " +
				" WHERE  d.Urun_Kodu=mal.kodu And  d.Hareket ='C' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0),'N3') " +
				" End As Cikis_Miktari , " +
				" IFNULL((Select sum(IF(d.Doviz = '" + calisanpara + "',abs(Tutar),abs(Tutar)* d.Kur))  " +
				" FROM STOK d " +  
				" WHERE  d.Urun_Kodu=mal.kodu And  d.Hareket='C' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0) as Cikis_Tutar, " +
				" IFNULL((SELECT sum(abs(miktar)) FROM STOK d " +
				" WHERE  d.Urun_Kodu=mal.kodu AND  d.Hareket ='C' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0) *  " +
				" IFNULL((SELECT  sum(Tutar)/ sum(IF(miktar=0 ,1,miktar)) " +
				" FROM STOK d " +
				" WHERE  d.Urun_Kodu=mal.kodu AND  d.Hareket='G' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0) as Cikis_Maliyet, " +
				"" + 
				"  CASE WHEN kusurat = 0 THEN format( IFNULL((SELECT ROUND(SUM(CASE when Hareket = 'G' then miktar else 0 END - CASE when Hareket= 'C' then abs(miktar) else 0 END),3) " +
				" FROM STOK d WHERE  d.Urun_Kodu=mal.kodu " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0),'N0') " +
				" WHEN kusurat = 1 THEN format( IFNULL((SELECT ROUND(SUM(CASE when Hareket = 'G' then miktar else 0 END - CASE when Hareket= 'C' then abs(miktar) else 0 END),3) " +
				" FROM STOK d WHERE  d.Urun_Kodu=mal.kodu " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0),'N1') " +
				" WHEN kusurat = 2 THEN format( IFNULL((SELECT ROUND(SUM(CASE when Hareket = 'G' then miktar else 0 END - CASE when Hareket= 'C' then abs(miktar) else 0 END),3) " +
				" FROM STOK d WHERE  d.Urun_Kodu=mal.kodu " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0),'N2') " +
				" ELSE format( IFNULL((SELECT ROUND(SUM(CASE when Hareket = 'G' then miktar else 0 END - CASE when Hareket= 'C' then abs(miktar) else 0 END),3) " +
				" FROM STOK d WHERE  d.Urun_Kodu=mal.kodu " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0),'N3') " +
				" End  As Stok_Miktari, " +
				" IFNULL((Select  sum(Tutar)/ sum(IF(miktar=0 ,1,miktar)) " +
				" FROM STOK d " +
				" WHERE  d.Urun_Kodu=mal.kodu And  d.Hareket='G' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0) as Maliyet, " +
				" IFNULL(((IFNULL((SELECT sum(miktar) FROM STOK d " +
				"  WHERE  d.Urun_Kodu=mal.kodu AND  d.Hareket='G' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ) ,0) )  - ( IFNULL((SELECT sum(abs(miktar)) FROM STOK d  " +
				" WHERE  d.Urun_Kodu=mal.kodu AND  d.Hareket='C' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" ),0) ) ) * ((SELECT  sum(Tutar)/ sum(IF(miktar=0 ,1,miktar)) " +
				" FROM STOK d " +
				" WHERE  d.Urun_Kodu=mal.kodu AND  d.Hareket='G' " +
				" AND d.Tarih >= '" + t1 + "' AND  d.Tarih <= '" + t2 + " 23:59:59.999'" +
				" AND d.Urun_Kodu >= N'" + k1 + "' AND d.Urun_Kodu <= N'" + k2 + "' " +
				" AND d.Evrak_No >= '" + f1 + "' AND d.Evrak_No <= '" + f2 + "' " +
				" AND d.Ana_Grup " + anagrup +
				" AND d.Alt_Grup " + altgrup +
				" AND d.Depo " + depo +
				" AND d.Evrak_Cins " + wee +
				" AND d.Evrak_Cins " + ure1 +
				" )),0) AS Tutar " +
				" FROM MAL USE INDEX (IX_MAL) " +
				" WHERE   Kodu >= N'" + k1 + "' AND  Kodu <= N'" + k2 + "' " +
				" AND MAL.Ana_Grup " + uanagrp +
				" AND MAL.Alt_Grup " + ualtgrp +
				" GROUP BY mal.kodu,mal.barkod,mal.adi,mal.birim,mal.kusurat " +
				" ORDER BY mal.kodu ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet zayi_rapor(String t1 , String t2 , String k1 , String  k2 , String f1 ,String f2,
			String anagrp,String altgrp, String depo,String uanagrp,String ualtgrp ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;

		String sql =  " SELECT Urun_Kodu ,  Barkod , Adi, Evrak_No , " +
				" DATE(Tarih) ,CONVERT(Miktar,double) ,  Birim , CONVERT(Miktar * (SELECT Agirlik From Mal Where mal.Kodu = stok.Urun_Kodu) ,double)  As Agirlik  ,STOK.Fiat  , " +
				" Tutar ," +
				" IFNULL((SELECT ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = MAL.Ana_Grup),'') Ana_Grup , " +
				" IFNULL((SELECT ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = MAL.Alt_Grup),'') AS Alt_Grup , " +
				" IFNULL((SELECT DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = STOK.Depo),'') AS Depo ,stok. USER  " +
				" FROM STOK USE INDEX (IX_STOK) ,MAL USE INDEX (IX_MAL) " +
				" WHERE  mal.kodu = stok.Urun_Kodu " +
				" AND MAL.Ana_Grup " + uanagrp +
				" AND MAL.Alt_Grup " + ualtgrp +
				" AND STOK.Evrak_No >= '" + f1 + "' AND  STOK.Evrak_No <= '" + f2 + "'" +
				" AND STOK.Tarih >= '" + t1 + "' AND  STOK.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND  STOK.Urun_Kodu  >= N'" + k1 + "' AND  STOK.Urun_Kodu  <= N'" + k2 + "' " +
				" AND STOK.Ana_Grup " + anagrp +
				" AND STOK.Alt_Grup " + altgrp +
				" AND STOK.Depo " + depo +
				" AND  Evrak_Cins = 'ZAI' " +
				" Order by Tarih ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet irsaliye_faturasiz(String hareket ,String arama) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;

		String sql = " Select Irsaliye_No ,Tarih,Kodu ,Doviz,abs(Miktar) as Miktar ,Cari_Hesap_Kodu As Hesap_Kodu,isnull((Select    ANA_GRUP_DEGISKEN.ANA_GRUP   FROM ANA_GRUP_DEGISKEN " +
				" WHERE ANA_GRUP_DEGISKEN.AGID = IRSALIYE.ANA_GRUP ) , '') AS Ana_Grup, " +
				" isnull((Select ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = IRSALIYE.ALT_GRUP ) , '') AS Alt_Grup ," +
				" isnull((Select DEPO FROM DEPO_DEGISKEN WHERE DEPO_DEGISKEN.DPID_Y = IRSALIYE.DEPO ) , '') AS Depo , " +
				" Ozel_Kod " +
				" FROM IRSALIYE  " +
				" WHERE  Fatura_No = '' " +
				" AND Hareket = '" + hareket + "'" +
				" " + arama + "" +
				" ORDER BY Irsaliye_No " ;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public String son_irsno_al(String cins) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String result ;
		String sql = "SELECT max(Irsaliye_No)  as NO FROM IRSALIYE WHERE Hareket = '" + cins + "' ";
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
	public ResultSet envanter_rapor_fifo(String t1,String t2,String k1 ,String k2 ,String f1 ,String f2 , String m1 ,String m2,
			String anagrup,String altgrup,String depo,String depohar,String ure ,
			String uanagrp,String ualtgrp,String calisanpara) throws ClassNotFoundException, SQLException
	{

		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;

		String wee  = "";
		if (depohar.equals("E") )
		{
			wee = " Like '%' " ;
		}
		else
		{
			wee = " <> 'DPO' ";
		}
		String ure1 = "" ;
		if (ure.equals("E") )
		{
			ure1 = " Like '%' ";
		}
		else
		{
			ure1 = " <> 'URE' " ;
		}


		String sql =  ";WITH OrderedIn as  " +
				" ( SELECT Urun_Kodu,Miktar,STOK.Fiat,ROW_NUMBER()OVER (PARTITION BY Urun_Kodu ORDER BY  Tarih asc) as S_No " +
				" FROM [STOK] WITH ( INDEX ( IX_Giren ) ),[MAL] " +
				" where stok.Urun_Kodu = mal.Kodu AND Miktar <> 0   and Hareket = 'G' " +
				" AND STOK.Tarih BETWEEN  '" + t1 + "' AND   '" + t2 + " 23:59:59.998'" +
				" AND Urun_Kodu = N'" + k1 + "'" +
				" AND Evrak_No >= '" + f1 + "' AND Evrak_No <= '" + f2 + "' " +
				" AND STOK.Ana_Grup " + anagrup +
				" AND STOK.Alt_Grup " + altgrup +
				" and mal.Ana_Grup  " + uanagrp + " " +
				" and mal.Alt_Grup  " + ualtgrp + " " +
				" AND STOK.Depo " + depo +
				" AND Evrak_Cins " + wee +
				" AND Evrak_Cins " + ure1 +
				" ),  RunningTotals as " +
				"(select Urun_Kodu , Miktar, Fiat as price, cast(Miktar as varchar(100)) as Total " +
				",cast(0 as varchar(100)) as PrevTotal,S_No from OrderedIn where S_No = 1  " +
				" union all " +
				" select rt.Urun_Kodu  ,oi.Miktar ,oi.Fiat as price " +
				" ,cast(rt.Total + oi.Miktar as varchar(100)),cast(rt.Total as varchar(100)),oi.S_No " +
				" from " +
				" RunningTotals rt " +
				" inner join OrderedIn oi " +
				" on rt.Urun_Kodu  = oi.Urun_Kodu " +
				" and rt.S_No = oi.S_No - 1)" +
				" , TotalOut  as   ( select Urun_Kodu,SUM(abs(Miktar)) as Qty  " +
				" FROM [STOK] WITH ( INDEX ( IX_Cikan ) ) ,[MAL] USE INDEX (IX_MAL)  where  stok.Urun_Kodu = mal.Kodu AND  Hareket = 'C'    " +
				" AND STOK.Tarih BETWEEN  '" + t1 + "' AND   '" + t2 + " 23:59:59.998'" +
				" AND Urun_Kodu = N'" + k1 + "'" +
				" AND Evrak_No >= '" + f1 + "' AND Evrak_No <= '" + f2 + "' " +
				" AND STOK.Ana_Grup " + anagrup +
				" and mal.Ana_Grup  " + uanagrp + " " +
				" and mal.Alt_Grup  " + ualtgrp + " " +
				" AND STOK.Alt_Grup " + altgrup +
				" AND STOK.Depo " + depo +
				" AND Evrak_Cins " + wee +
				" AND Evrak_Cins " + ure1 +
				" group by Urun_Kodu ) " +
				" Select  " +
				" rt.Urun_Kodu as Kodu" +
				" , (SELECT Adi from  [MAL] where Kodu = rt.Urun_Kodu ) as Adi " +
				" , (SELECT Birim from [MAL] where Kodu=rt.Urun_Kodu ) as Simge " +
				" , CASE " +
				" WHEN (SELECT Kusurat from [MAL] where Kodu=rt.Urun_Kodu )   = 0  THEN format( SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total end),'N0') " +
				" WHEN (SELECT Kusurat from [MAL] where Kodu=rt.Urun_Kodu )  = 1  THEN format( SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total end),'N1') " +
				" WHEN (SELECT Kusurat from [MAL] where Kodu=rt.Urun_Kodu )  = 2  THEN format( SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total end),'N2') " +
				" else  format( SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total end),'N3') " +
				" END   as Giris_Miktari " +
				" ,(select sum(Miktar * price) from RunningTotals  where Urun_Kodu = rt.Urun_Kodu) AS Giris_Tutar " +
				" , CASE " +
				" WHEN (SELECT Kusurat from [MAL] where Kodu=rt.Urun_Kodu )   = 0  THEN format( SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total end) - " +
				" SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) " +
				" THEN rt.Miktar ELSE rt.Total - COALESCE(out.Qty,0) END),'N0') " +
				" WHEN (SELECT Kusurat from [MAL] where Kodu=rt.Urun_Kodu )   = 1  THEN format( SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total end) - " +
				" SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) " +
				" THEN rt.Miktar ELSE rt.Total - COALESCE(out.Qty,0) END),'N1') " +
				" WHEN (SELECT Kusurat from [MAL] where Kodu=rt.Urun_Kodu )   = 2  THEN format( SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total end) - " +
				" SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) " +
				" THEN rt.Miktar ELSE rt.Total - COALESCE(out.Qty,0) END),'N2') " +
				" ELSE format( SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total end) - " +
				" SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) " +
				" THEN rt.Miktar ELSE rt.Total - COALESCE(out.Qty,0) END),'N3') " +
				" END as Cikis_Miktari " +
				" ,(SELECT sum(IF(Doviz = '" + calisanpara + "',abs(Tutar),abs(Tutar)* Kur)) " +
				" From [STOK] WITH ( INDEX ( IX_Cikan ) ) " +
				" where Hareket ='C'  " +
				" AND Urun_Kodu = rt.Urun_Kodu   " +
				" AND STOK.Tarih BETWEEN '" + t1 + "' AND   '" + t2 + " 23:59:59.998'" +
				" AND Urun_Kodu = N'" + k1 + "'" +
				" AND Evrak_No >= '" + f1 + "' AND Evrak_No <= '" + f2 + "' " +
				" AND STOK.Ana_Grup " + anagrup +
				" AND STOK.Alt_Grup " + altgrup +
				" AND STOK.Depo " + depo +
				" AND Evrak_Cins " + wee +
				" AND Evrak_Cins " + ure1 +
				" group by Urun_Kodu ) as Cikis_Tutar  " + 
				" ,(select sum(Miktar * price) from RunningTotals  where Urun_Kodu = rt.Urun_Kodu) -  " +
				" SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total - COALESCE(out.Qty,0) END)        * " +
				" round(SUM(CASE WHEN PrevTotal > COALESCE(out.Qty, 0) Then rt.Miktar Else rt.Total - COALESCE(out.Qty, 0) End * (price)) /  " +
				" SUM(CASE WHEN PrevTotal > COALESCE(out.Qty, 0) Then rt.Miktar Else rt.Total - COALESCE(out.Qty,0) End),2)  " + 
				" As Cikis_Maliyet" +
				" ,CASE " +
				" WHEN (SELECT Kusurat from [MAL] where Kodu=rt.Urun_Kodu ) = 0  THEN format( SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total - COALESCE(out.Qty,0) END),'N0') " +
				" WHEN (SELECT Kusurat from [MAL] where Kodu=rt.Urun_Kodu ) = 1  THEN format( SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total - COALESCE(out.Qty,0) END),'N1') " +
				" WHEN (SELECT Kusurat from [MAL] where Kodu=rt.Urun_Kodu ) = 2  THEN format( SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total - COALESCE(out.Qty,0) END),'N2') " +
				" ELSE format( SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total - COALESCE(out.Qty,0) END),'N3') " +
				" END as Stok_Miktari  " +
				" , round(SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total - COALESCE(out.Qty,0) END * (price)) / " +
				" SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total - COALESCE(out.Qty,0) END),2)  " +
				" as Maliyet " +
				", SUM(CASE WHEN PrevTotal > COALESCE(out.Qty,0) THEN rt.Miktar ELSE rt.Total - COALESCE(out.Qty,0) END)        * " +
				" round(SUM(CASE WHEN PrevTotal > COALESCE(out.Qty, 0) Then rt.Miktar Else rt.Total - COALESCE(out.Qty, 0) End * (price)) /  " +
				" SUM(CASE WHEN PrevTotal > COALESCE(out.Qty, 0) Then rt.Miktar Else rt.Total - COALESCE(out.Qty,0) End),2) " +
				" as Tutar " +
				" FROM " +
				" RunningTotals rt left join TotalOut out On  rt.Urun_Kodu = out.Urun_Kodu  " +
				" WHERE " +
				" rt.Total > COALESCE(out.Qty,0) " +
				" GROUP BY rt.Urun_Kodu " +
				" Order by rt.Urun_Kodu asc   option (maxrecursion 0) ";



		PreparedStatement stmt = con.prepareStatement(sql);

		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet envanter_rapor_fifo_2(String t1,String t2,String k1 ,String k2 ,String f1 ,String f2 , String m1 ,String m2,
			String anagrup,String altgrup,String depo,String depohar,String ure ,
			String uanagrp,String ualtgrp,String ukodu,String calisanpara) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String wee  = "";
		if (depohar.equals("E") )
		{
			wee = " Like '%' " ;
		}
		else
		{
			wee = " <> 'DPO' ";
		}
		String ure1 = "" ;
		if (ure.equals("E") )
		{
			ure1 = " Like '%' ";
		}
		else
		{
			ure1 = " <> 'URE' " ;
		}
		String sql = " SELECT Urun_Kodu ,Evrak_No , " +
				" IF(STOK.Evrak_Cins= 'URE','',(SELECT  IFNULL(Cari_Firma,'') FROM FATURA  WHERE  Fatura.Fatura_No = " +
				"STOK.Evrak_No  and Gir_cik = stok.hareket LIMIT 1 )) as Hesap_Kodu, " +
				" Evrak_Cins,Tarih ,Miktar ,  Birim , STOK.Fiat , " +
				" SUM(Miktar) OVER(ORDER BY Tarih  ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) as Miktar_Bakiye , " +
				" Tutar ,Doviz," +
				" IF(Doviz = '" + calisanpara + "',Tutar,(Tutar * Kur)) as TL_Tutar ," +
				" SUM(IF(Doviz = '" + calisanpara + "',Tutar,(Tutar * Kur))) OVER(ORDER BY Tarih  ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) as Tutar_Bakiye , " +
				" stok. USER ,MAL.Kusurat " +
				" FROM STOK ,MAL " +
				" WHERE  mal.kodu = stok.Urun_Kodu " +
				" AND MAL.Ana_Grup " + uanagrp +
				" AND MAL.Alt_Grup " + ualtgrp +
				"  AND STOK.Evrak_No >= '" + f1 + "' AND  STOK.Evrak_No <= '" + f2 + "'" +
				" AND STOK.Tarih BETWEEN '" + t1 + "' AND  '" + t2 + " 23:59:59.998'" +
				" AND STOK.Urun_Kodu = N'" + ukodu + "'" +
				" AND STOK.Ana_Grup " + anagrup +
				" AND STOK.Alt_Grup " + altgrup +
				" AND STOK.Depo " + depo +
				" AND Evrak_Cins " + wee +
				" AND Evrak_Cins " + ure1 +
				" Order by Tarih " ;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public BigDecimal envanter_rapor_lifo(String t1,String t2,String k1 ,String k2 ,String f1 ,String f2 , String m1 ,String m2,
			String anagrup,String altgrup,String depo,String depohar,String ure ,
			String uanagrp,String ualtgrp,String calisanpara) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String wee  = "";
		String wee1  = "";
		if (depohar.equals("E") ) 
		{
			wee = "%" ;
			wee1 = "''";
		}
		else
		{
			wee = "%";
			wee1 = "DPO";
		}
		String ure1 = "" ;
		String ure2 = "" ;
		if (ure.equals("E") )
		{
			ure1 = "%";
			ure2 = "''";
		}
		else
		{
			ure1 = "%";
			ure2 = "URE" ;
		}
		CallableStatement cstmt = con.prepareCall("{call LIFO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		cstmt.setString(1, calisanpara);
		cstmt.setString(2, t1);
		cstmt.setString(3, t2);
		cstmt.setString(4, k1);
		cstmt.setString(5, f1);
		cstmt.setString(6, f2);
		cstmt.setString(7, anagrup);
		cstmt.setString(8, altgrup);
		cstmt.setString(9, uanagrp);
		cstmt.setString(10, ualtgrp);
		cstmt.setString(11, depo);
		cstmt.setString(12, wee); 
		cstmt.setString(13, wee1);
		cstmt.setString(14, ure1);
		cstmt.setString(15, ure2);
		cstmt.registerOutParameter(16, Types.DECIMAL);
		cstmt.execute();

		BigDecimal maliyet = cstmt.getBigDecimal(16);

		return   maliyet ;
	}
	///// YIL SONU
	public void ana_yaz(String ydosya,String kull) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =  " INSERT INTO ok_fat" + ydosya + ".ANA_GRUP_DEGISKEN " +
				"  ( AGID_Y,ANA_GRUP , USER  ) " +
				"  SELECT  AGID_Y,ANA_GRUP  ,'" + kull + "' " +
				"  FROM ANA_GRUP_DEGISKEN ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
	}
	public void alt_yaz(String ydosya,String kull) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =  " INSERT INTO ok_fat" + ydosya + ".ALT_GRUP_DEGISKEN " +
				" ( ALID_Y,ANA_GRUP,ALT_GRUP , USER  ) " +
				" SELECT  ALID_Y,ANA_GRUP  ,ALT_GRUP ,'" + kull + "' " +
				" FROM ALT_GRUP_DEGISKEN ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
	}
	public void gidecegi_yer(String ydosya,String kull) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =  " INSERT INTO ok_fat" + ydosya + ".GDY " +
				" ( Isim,Adres,Semt,Sehir, USER   ) " +
				" SELECT Isim,Adres,Semt,Sehir ,'" + kull + "' " +
				" FROM GDY ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
	}
	public void evr_for(String ydosya,String kull) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "" ;
		sql = "DELETE ok_fat" + ydosya + ".IRS_EVRAK_FORMAT ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

		sql = "DELETE ok_fat"  + ydosya + ".FAT_EVRAK_FORMAT ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

		sql = " INSERT INTO ok_fat" +  ydosya  + ".FAT_EVRAK_FORMAT " +
				" ( `SAT_SUT`,`TARIH` ,`FIRMA_KODU`      ,`FIRMA_UNVANI`      ,`VERGI_DAIRESI`      ,`VERGI_NO`      ,`GIDECEGI_YER`      ,`NOT_1`      ,`NOT_2`      ,`NOT_3`      ,`BASLIK_BOLUM`      ,`BARKOD` " +
				" ,`URUN_KODU`      ,`URUN_ADI`      ,`DEPO`      ,`IZAHAT`      ,`SIMGE`      ,`BIRIM_FIAT`      ,`ISKONTO`      ,`MIKTAR`      ,`K_D_V`      ,`TUTAR`      ,`TUTAR_TOPLAM` " +
				" ,`ISKONTO_TOPLAMI`      ,`BAKIYE`      ,`K_D_V_TOPLAMI`      ,`BELGE_TOPLAMI`      ,`TEVKIFAT_ORANI`      ,`AL_TAR_TEV_ED_KDV`      ,`TEV_DAH_TOP_TUTAR`      ,`BEYAN_ED_KDV` " +
				" ,`TEV_HAR_TOP_TUT`      ,`YAZI_ILE`      ,`TEV_KASESI`      ,`ALT_BOLUM`      ,`N1`      ,`N2`      ,`N3`      ,`N4`      ,`N5`      ,`N6`      ,`N7`      ,`N8`      ,`N9`      ,`N10`, USER   ) " +
				" SELECT `SAT_SUT`      ,`TARIH`      ,`FIRMA_KODU`      ,`FIRMA_UNVANI`      ,`VERGI_DAIRESI`      ,`VERGI_NO`      ,`GIDECEGI_YER`      ,`NOT_1`      ,`NOT_2`      ,`NOT_3`      ,`BASLIK_BOLUM` " +
				" ,`BARKOD`      ,`URUN_KODU`      ,`URUN_ADI`      ,`DEPO`      ,`IZAHAT`      ,`SIMGE`      ,`BIRIM_FIAT`      ,`ISKONTO`      ,`MIKTAR`      ,`K_D_V`      ,`TUTAR`      ,`TUTAR_TOPLAM` " +
				" ,`ISKONTO_TOPLAMI`      ,`BAKIYE`      ,`K_D_V_TOPLAMI`      ,`BELGE_TOPLAMI`      ,`TEVKIFAT_ORANI`      ,`AL_TAR_TEV_ED_KDV`      ,`TEV_DAH_TOP_TUTAR`      ,`BEYAN_ED_KDV`      ,`TEV_HAR_TOP_TUT` " +
				" ,`YAZI_ILE`      ,`TEV_KASESI`      ,`ALT_BOLUM`      ,`N1`      ,`N2`      ,`N3`      ,`N4`      ,`N5`      ,`N6`      ,`N7`      ,`N8`      ,`N9`      ,`N10`,'" + kull + "' " +
				" FROM FAT_EVRAK_FORMAT ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

		sql = " INSERT INTO ok_fat" + ydosya + ".IRS_EVRAK_FORMAT " +
				" ( `SAT_SUT`      ,`TARIH`      ,`SEVK_TARIH`      ,`FIRMA_KODU`      ,`FIRMA_UNVANI`      ,`VERGI_DAIRESI`      ,`VERGI_NO`      ,`GIDECEGI_YER`      ,`NOT_1`      ,`NOT_2`      ,`NOT_3` " +
				" ,`BASLIK_BOLUM`      ,`BARKOD`      ,`URUN_KODU`      ,`URUN_ADI`      ,`DEPO`      ,`SIMGE`      ,`BIRIM_FIAT`      ,`ISKONTO`      ,`MIKTAR`      ,`K_D_V`      ,`TUTAR`      ,`TUTAR_TOPLAM` " +
				" ,`ISKONTO_TOPLAMI`      ,`BAKIYE`      ,`K_D_V_TOPLAMI`      ,`BELGE_TOPLAMI`      ,`YAZI_ILE`      ,`ALT_BOLUM`      ,`N1`      ,`N2`      ,`N3`      ,`N4`      ,`N5` " +
				" ,`N6`      ,`N7`      ,`N8`      ,`N9`      ,`N10`, USER  ) " +
				" SELECT `SAT_SUT`      ,`TARIH`      ,`SEVK_TARIH`      ,`FIRMA_KODU`      ,`FIRMA_UNVANI`      ,`VERGI_DAIRESI`      ,`VERGI_NO`      ,`GIDECEGI_YER`      ,`NOT_1`      ,`NOT_2`      ,`NOT_3` " +
				" ,`BASLIK_BOLUM`      ,`BARKOD`      ,`URUN_KODU`      ,`URUN_ADI`      ,`DEPO`      ,`SIMGE`      ,`BIRIM_FIAT`      ,`ISKONTO`      ,`MIKTAR`      ,`K_D_V`      ,`TUTAR`      ,`TUTAR_TOPLAM` " +
				" ,`ISKONTO_TOPLAMI`      ,`BAKIYE`      ,`K_D_V_TOPLAMI`      ,`BELGE_TOPLAMI`      ,`YAZI_ILE`      ,`ALT_BOLUM`      ,`N1`      ,`N2`      ,`N3`      ,`N4`      ,`N5` " +
				" ,`N6`      ,`N7`      ,`N8`      ,`N9`      ,`N10`,'" + kull + "' " +
				" FROM  IRS_EVRAK_FORMAT ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

	}
	public void mal_yaz(String ydosya,String kull,String eski_ukodu,String yen_ukodu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =   " INSERT INTO ok_fat" + ydosya + ".MAL " +
				" ( Kodu, Adi, Birim, Kusurat,Resim,Sinif,Ana_Grup ,Alt_Grup ,Aciklama_1 ,Aciklama_2 ,Ozel_Kod_1 ,Ozel_Kod_2,Ozel_Kod_3  ,KDV , " + 
				" Barkod,Mensei,Agirlik,Depo,Fiat,Fiat_2 ,Fiat_3 ,Recete ,  USER   ) " +
				" SELECT '" + yen_ukodu + "',Adi,Birim,Kusurat,Resim, Sinif, Ana_Grup, Alt_Grup,Aciklama_1,Aciklama_2,Ozel_Kod_1,Ozel_Kod_2,Ozel_Kod_3,KDV, " +  
				" Barkod,Mensei,Agirlik,Depo,Fiat,Fiat_2 ,Fiat_3,Recete ,'" + kull + "' " +
				" FROM MAL Where Kodu =N'" + eski_ukodu + "' ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
	}
	public void ysonu_fat_kaydet(String fatno ,String kodu ,int depo ,double  fiat ,double tevkifat  
			, double miktar ,String gircik ,double tutar,double iskonto ,double kdv  
			, String tarih, String izah,String doviz,String  adrfirma ,String carfirma  
			, String ozkod ,double kur ,String cins,int  anagrp,int  altgrp ,String usr,String ydosya) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="INSERT INTO ok_fat" + ydosya + ".FATURA (Fatura_No,Kodu,Depo,Fiat,Tevkifat,Miktar,Gir_Cik,Tutar,Iskonto,Kdv,Tarih,Izahat " +
				" ,Doviz,Adres_Firma,Cari_Firma,Ozel_Kod,Kur,Cins,Ana_Grup,Alt_Grup, USER ) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1,fatno);
		stmt.setString(2, kodu);
		stmt.setInt(3,depo);
		stmt.setDouble(4, fiat);
		stmt.setDouble(5, tevkifat);
		stmt.setDouble(6, miktar);
		stmt.setString(7, gircik);
		stmt.setDouble(8, tutar);
		stmt.setDouble(9, iskonto);
		stmt.setDouble(10, kdv);
		stmt.setString(11, tarih);
		stmt.setString(12, izah);
		stmt.setString(13, doviz);
		stmt.setString(14, adrfirma);
		stmt.setString(15, carfirma);
		stmt.setString(16, ozkod);
		stmt.setDouble(17, kur);
		stmt.setString(18, cins);
		stmt.setInt(19,anagrp);
		stmt.setInt(20,altgrp);
		stmt.setString(21,usr);
		stmt.executeUpdate();
		stmt.close();

	}
	public void ysonu_stk_kaydet(String evrno ,String evrcins , String tarih,int depo,String urnkodu,double miktar,double fiat ,double tutar
			, double kdvlitut,String hareket ,String izah , int anagrp ,int altgrp ,double kur ,String b1,String doviz 
			, String hspkodu ,String usr,String ydosya) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="INSERT INTO ok_fat" + ydosya + ".STOK (Evrak_No,Evrak_Cins,Tarih,Depo,Urun_Kodu,Miktar,Fiat,Tutar,Kdvli_Tutar,Hareket,Izahat " +
				" ,Ana_Grup,Alt_Grup,Kur,B1,Doviz,Hesap_Kodu, USER ) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, evrno);
		stmt.setString(2, evrcins);
		stmt.setString(3, tarih);
		stmt.setInt(4, depo);
		stmt.setString(5, urnkodu);
		stmt.setDouble(6, miktar);
		stmt.setDouble(7, fiat);
		stmt.setDouble(8, tutar);
		stmt.setDouble(9, kdvlitut);
		stmt.setString(10, hareket);
		stmt.setString(11, izah);
		stmt.setInt(12, anagrp);
		stmt.setInt(13, altgrp);
		stmt.setDouble(14, kur);
		stmt.setString(15, b1);
		stmt.setString(16, doviz);
		stmt.setString(17, hspkodu);
		stmt.setString(18, usr);
		stmt.executeUpdate();
		stmt.close();
	}
	/////**************
	public void degisken_degistir(int anagrp,int altgrp, int anaygrp,int altygrp) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		String sql = "UPDATE MAL  SET Ana_Grup = '" + anaygrp + "'  , Alt_Grup = '" + altygrp + "'  WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		sql = "UPDATE BOZUK_MAL  SET Ana_Grup = '" + anaygrp + "'  , Alt_Grup = '" + altygrp + "'  WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		sql = "UPDATE FATURA  SET Ana_Grup = '" + anaygrp + "'  , Alt_Grup = '" + altygrp + "'  WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		sql = "UPDATE IRSALIYE  SET Ana_Grup = '" + anaygrp + "'  , Alt_Grup = '" + altygrp + "'  WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		sql = "UPDATE RECETE  SET Ana_Grup = '" + anaygrp + "'  , Alt_Grup = '" + altygrp + "'  WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		sql = "UPDATE STOK  SET Ana_Grup = '" + anaygrp + "'  , Alt_Grup = '" + altygrp + "'  WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void urun_degisken_alt_grup_sil(int  ID ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		String sql = "DELETE ALT_GRUP_DEGISKEN    WHERE ALID_Y = '" + ID + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void urun_kod_degisken_sil(   String hangi_Y,String nerden  ,   int sira) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		String sql = "DELETE " + nerden  + "    WHERE "+ hangi_Y +" = '" + sira + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public  boolean alt_grup_kontrol(int anagrp,int altgrp) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		ResultSet	rs = null;
		boolean result = true;
		String sql  = "" ;
		PreparedStatement stmt =null;
		sql = "SELECT  *  FROM MAL   WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		stmt = con.prepareStatement(sql);
		rs = stmt. executeQuery();
		if (!rs.isBeforeFirst() )   result = false ;
		sql = "SELECT *  FROM BOZUK_MAL    WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		rs = stmt. executeQuery();
		if (!rs.isBeforeFirst() )   result = false ;
		sql = "SELECT * FROM FATURA    WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		stmt = con.prepareStatement(sql);
		rs = stmt. executeQuery();
		if (!rs.isBeforeFirst() )   result = false ;
		sql = "SELECT * FROM IRSALIYE    WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		stmt = con.prepareStatement(sql);
		rs = stmt. executeQuery();
		if (!rs.isBeforeFirst() )   result = false ;
		sql = "SELECT * FROM RECETE  WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		stmt = con.prepareStatement(sql);
		rs = stmt. executeQuery();
		if (!rs.isBeforeFirst() )   result = false ;
		sql = "SELECT * FROM STOK   WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		stmt = con.prepareStatement(sql);
		rs = stmt. executeQuery();
		if (!rs.isBeforeFirst() )   result = false ;
		return result;
	}
	//
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
	public ResultSet envanter_rapor_u_kodu_oncekitarih(String t1, String t2, String k1, String k2, String f1, String f2,
			String m1, String m2, String anagrup, String altgrup, String depo, String depohar, String ure,
			String uanagrp, String ualtgrp) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		ResultSet	rss = null;
		String  wee  = "";
		if (depohar.equals("E"))
			wee = " Like '%' " ;
		else
			wee = " <> 'DPO' " ;

		String ure1  = "";
		if(ure.equals("E"))
			ure1 = " Like '%' ";
		else
			ure1 = " <> 'URE' " ;

		String sql =   " SELECT m.Kodu as Kodu  , m.Adi ,m.Birim as Simge, " + 
				" IFNULL(((SELECT SUM(IF( Hareket = 'G' , miktar , 0 ) * m.Agirlik )   FROM STOK s USE INDEX (IX_STOK)  " +
				" where   m.Kodu = s.Urun_Kodu " +
				" AND s.Tarih < '" + t1 + "' " +
				" AND s.Urun_Kodu >= N'" + k1 + "' AND s.Urun_Kodu <= N'" + k2 + "' " +
				" AND s.Evrak_No >= '" + f1 + "' AND s.Evrak_No <= '" + f2 + "' " +
				" AND s.Ana_Grup " + anagrup +
				" AND s.Alt_Grup " + altgrup +
				" AND s.Depo " + depo +
				" AND s.Evrak_Cins " + wee +
				" AND s.Evrak_Cins " + ure1 +
				" ) - " +
				" (SELECT SUM(IF( Hareket = 'C' , abs(miktar) , 0 ) * m.Agirlik )   FROM STOK s USE INDEX (IX_STOK)   " +
				" where m.Kodu = s.Urun_Kodu " +
				" AND s.Tarih < '" + t1 + "' " +
				" AND s.Urun_Kodu >= N'" + k1 + "' AND s.Urun_Kodu <= N'" + k2 + "' " +
				" AND s.Evrak_No >= '" + f1 + "' AND s.Evrak_No <= '" + f2 + "' " +
				" AND s.Ana_Grup " + anagrup +
				" AND s.Alt_Grup " + altgrup +
				" AND s.Depo " + depo +
				" AND s.Evrak_Cins " + wee +
				" AND s.Evrak_Cins " + ure1 +
				" )),0) as Onceki_Bakiye ," +  // Onceki_Bakiye
				
				//******************************************************************
				" IFNULL((SELECT SUM(IF( Hareket = 'G' , miktar , 0 ) * m.Agirlik )   FROM  STOK s USE INDEX (IX_STOK)  " +
				" where m.Kodu = s.Urun_Kodu " +
				" AND s.Tarih >= '" + t1 + "' AND  s.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND s.Urun_Kodu >= N'" + k1 + "' AND s.Urun_Kodu <= N'" + k2 + "' " +
				" AND s.Evrak_No >= '" + f1 + "' AND s.Evrak_No <= '" + f2 + "' " +
				" AND s.Ana_Grup " + anagrup +
				" AND s.Alt_Grup " + altgrup +
				" AND s.Depo " + depo +
				" AND s.Evrak_Cins " + wee +
				" AND s.Evrak_Cins " + ure1 +
				"  ),0) as Periyot_Giris_Agirlik," + // Periyot_Giris_Agirlik
				
				
				" IFNULL((SELECT SUM(IF( Hareket = 'C' ,abs( miktar) , 0 ) * m.Agirlik )   FROM  STOK s  USE INDEX(IX_STOK)  " +
				" where m.Kodu = s.Urun_Kodu  " +
				" AND s.Tarih >= '" + t1 + "' AND  s.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND s.Urun_Kodu >= N'" + k1 + "' AND s.Urun_Kodu <= N'" + k2 + "' " +
				" AND s.Evrak_No >= '" + f1 + "' AND s.Evrak_No <= '" + f2 + "' " +
				" AND s.Ana_Grup " + anagrup +
				" AND s.Alt_Grup " + altgrup +
				" AND s.Depo " + depo +
				" AND s.Evrak_Cins " + wee +
				" AND s.Evrak_Cins " + ure1 +
				"  ),0) as Periyot_Cikis_Agirlik, " + // Periyot_Cikis_Agirlik
				//
				" IFNULL((SELECT SUM(IF( Hareket = 'G' , miktar , 0 ) * m.Agirlik )   FROM  STOK s USE INDEX (IX_STOK) " +
				" where m.Kodu = s.Urun_Kodu " +
				" AND s.Tarih >= '" + t1 + "' AND  s.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND s.Urun_Kodu >= N'" + k1 + "' AND s.Urun_Kodu <= N'" + k2 + "' " +
				" AND s.Evrak_No >= '" + f1 + "' AND s.Evrak_No <= '" + f2 + "' " +
				" AND s.Ana_Grup " + anagrup +
				" AND s.Alt_Grup " + altgrup +
				" AND s.Depo " + depo +
				" AND s.Evrak_Cins " + wee +
				" AND s.Evrak_Cins " + ure1 +
				"  ) - " +
				" (SELECT SUM(IF( Hareket = 'C' ,abs( miktar) , 0 ) * m.Agirlik )   FROM  STOK s USE INDEX (IX_STOK)  " +
				" where m.Kodu = s.Urun_Kodu  " +
				" AND s.Tarih >= '" + t1 + "' AND  s.Tarih <= '" + t2 + " 23:59:59.998'" +
				" AND s.Urun_Kodu >= N'" + k1 + "' AND s.Urun_Kodu <= N'" + k2 + "' " +
				" AND s.Evrak_No >= '" + f1 + "' AND s.Evrak_No <= '" + f2 + "' " +
				" AND s.Ana_Grup " + anagrup +
				" AND s.Alt_Grup " + altgrup +
				" AND s.Depo " + depo +
				" AND s.Evrak_Cins " + wee +
				" AND s.Evrak_Cins " + ure1 +
				"  ),0)  as Periyot_Stok_Agirlik , " +
				//
				//" SUM(IF( Hareket = 'G' ,miktar, 0 )* mal.Agirlik) -  SUM(IF(Hareket = 'C',abs( miktar), 0 )* mal.Agirlik)     as Periyot_Stok_Agirlik , " + //Periyot_Stok_Agirlik
				" IFNULL(((SELECT SUM(IF( Hareket = 'G' , miktar , 0 ) * m.Agirlik )   " +
				" FROM  STOK s USE INDEX (IX_STOK) "+ 
				" where m.Kodu = s.Urun_Kodu   " +
				" AND s.Tarih < '" + t1 + "'  " + 
				" AND s.Urun_Kodu >= N'" + k1 + "' AND s.Urun_Kodu <= N'" + k2 + "' " +
				" AND s.Evrak_No >= '" + f1 + "' AND s.Evrak_No <= '" + f2 + "' " +
				" AND s.Ana_Grup " + anagrup +
				" AND s.Alt_Grup " + altgrup +
				" AND s.Depo " + depo +
				" AND s.Evrak_Cins " + wee +
				" AND s.Evrak_Cins " + ure1 +
				"   ) -  (SELECT SUM(IF( Hareket = 'C' , abs(miktar) , 0 ) * m.Agirlik )   "+
				" FROM  STOK s USE INDEX(IX_STOK)   where m.Kodu = s.Urun_Kodu  "+
				" AND s.Tarih < '" + t1 + "'  " + 
				" AND s.Urun_Kodu >= N'" + k1 + "' AND s.Urun_Kodu <= N'" + k2 + "' " +
				" AND s.Evrak_No >= '" + f1 + "' AND s.Evrak_No <= '" + f2 + "' " +
				" AND s.Ana_Grup " + anagrup +
				" AND s.Alt_Grup " + altgrup +
				" AND s.Depo " + depo +
				" AND s.Evrak_Cins " + wee +
				" AND s.Evrak_Cins " + ure1 +
				"   ) +  " +
				" (SELECT SUM(IF( Hareket = 'G' , miktar , 0 ) * m.Agirlik )   FROM  STOK s USE INDEX (IX_STOK)  " + 
				" where m.Kodu = s.Urun_Kodu  " + 
				" AND s.Tarih >= '" + t1 + "' AND  s.Tarih <= '" + t2 + " 23:59:59.998'  " +
				" AND s.Urun_Kodu >= N'" + k1 + "' AND s.Urun_Kodu <= N'" + k2 + "' " +
				" AND s.Evrak_No >= '" + f1 + "' AND s.Evrak_No <= '" + f2 + "' " +
				" AND s.Ana_Grup " + anagrup +
				" AND s.Alt_Grup " + altgrup +
				" AND s.Depo " + depo +
				" AND s.Evrak_Cins " + wee +
				" AND s.Evrak_Cins " + ure1 +
				"  ) -  " +
				" (SELECT SUM(IF( Hareket = 'C' ,abs( miktar) , 0 ) * m.Agirlik )   FROM  STOK s USE INDEX (IX_STOK)  " + 
				" where m.Kodu = s.Urun_Kodu  " + 
				" AND s.Tarih >= '" + t1 + "' AND  s.Tarih <= '" + t2 + " 23:59:59.998' "+ 
				" AND s.Urun_Kodu >= N'" + k1 + "' AND s.Urun_Kodu <= N'" + k2 + "' " +
				" AND s.Evrak_No >= '" + f1 + "' AND s.Evrak_No <= '" + f2 + "' " +
				" AND s.Ana_Grup " + anagrup +
				" AND s.Alt_Grup " + altgrup +
				" AND s.Depo " + depo +
				" AND s.Evrak_Cins " + wee +
				" AND s.Evrak_Cins " + ure1 +
				"  ) " +
				" ),0) as BAKIYE " +  
				//" SUM(IF( Hareket = 'G' ,miktar, 0 )* mal.Agirlik) -  SUM(IF(Hareket = 'C',abs( miktar), 0 )* mal.Agirlik)),0) as BAKIYE " +
				" From   MAL m  USE INDEX (IX_MAL) " +
				" where   m.Kodu >= N'" + k1 + "' AND  m.Kodu <= N'" + k2 + "' " +
				" AND m.Ana_Grup " + uanagrp +
				" AND m.Alt_Grup " + ualtgrp +
				" Group by m.Kodu, m.Adi ,m.Birim " +
				" ORDER by m.Kodu, m.Adi ,m.Birim ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;		}
}
