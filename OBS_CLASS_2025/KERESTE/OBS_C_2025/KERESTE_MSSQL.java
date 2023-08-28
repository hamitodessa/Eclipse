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

public class KERESTE_MSSQL implements IKERESTE {
	static Connection con = null;
	static Statement stmt = null;

	@Override
	public void baglan() throws SQLException, ClassNotFoundException {
		
		String cumle = "jdbc:sqlserver://" + BAGLAN.kerDizin.cONN_STR + ";";
		con = DriverManager.getConnection(cumle,BAGLAN.kerDizin.kULLANICI,BAGLAN.kerDizin.sIFRESI);
		
	}

	@Override
	public void kER_SIFIR_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String cumle = "";
		if ( ! sbilgi.getPort().toString().equals("") )
		{
			sbilgi.setPort(  ":" + sbilgi.getPort() );
		}
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		String VERITABANI = "OK_Ker" + sbilgi.getKod();
		stmt = null;
		String sql =null;
		if (sbilgi.getDizin_yeri() == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "', FILENAME = N'" +  sbilgi.getDizin()	+ "\\" + VERITABANI + ".mdf ' ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table(sbilgi.getFir_adi());
		//
		if (sbilgi.getDizin_yeri() == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG"+ "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "_LOG" + "', FILENAME = N'" +  sbilgi.getDizin()	+ "\\" + VERITABANI + "_LOG" + ".mdf' ) ";
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost " + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + "_LOG" + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MSSQL();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);

		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+ ".DB") == false)
		{
			String dsy = GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+ ".DB" ;
			GLOBAL.create_table_log(dsy,sbilgi.getFir_adi(),BAGLAN_LOG.kerLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi() );
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);
		//
		stmt.close();
		con.close();
		
	}

	@Override
	public void kER_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql = null;
		sql = "CREATE TABLE [dbo].[DPN]( "
				+ " [DID] [int] IDENTITY(1,1) NOT NULL,"
				+ "  [Evrak_No] [nvarchar](10) NOT NULL,"
				+ "  [Tip] [nvarchar](1) NULL,"
				+ "  [Bir] [nvarchar](40) NULL,"
				+ "  [Iki] [nvarchar](40) NULL,"
				+ "  [Uc] [nvarchar](40) NULL,"
				+ "  [Gir_Cik] [nvarchar](1) NULL,"
				+ "  [USER] [nvarchar](15) NOT NULL,"
				+ "  CONSTRAINT [PKeyDID] PRIMARY KEY CLUSTERED ("
				+ "  [DID] ASC"
				+ "  )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS"
				+ "  = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE NONCLUSTERED INDEX [IX_DPN] ON [dbo].[DPN]( "
				+ "   [Evrak_No] ASC, "
				+ "  [Gir_Cik] ASC "
				+ "  )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, "
				+ "  ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[GDY]( "
				+ "  [GID] [int] IDENTITY(1,1) NOT NULL, "
				+ "  [Isim] [nvarchar](50) NULL, "
				+ "  [Adres] [nvarchar](50) NULL, "
				+ "  [Semt] [nvarchar](50) NULL, "
				+ "  [Sehir] [nvarchar](50) NULL, "
				+ "  [USER] [nvarchar](15) NOT NULL, "
				+ "  CONSTRAINT [PKeyGID] PRIMARY KEY CLUSTERED ( "
				+ "  [GID] ASC "
				+ "  )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS "
				+ "  = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[NAKLIYECI]("
				+ " [NAKID] [int] IDENTITY(1,1) NOT NULL,"
				+ " [NAKID_Y] [int]  NOT NULL,"  
				+ " [UNVAN] [nvarchar](50) NOT NULL,"
				+ " [USER] [nvarchar](15) NOT NULL,"
				+ " CONSTRAINT [PKeyNAKID] PRIMARY KEY CLUSTERED ("
				+ " [NAKID] ASC"
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS"
				+ " = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		
		sql= "CREATE TABLE [dbo].[KERESTE]( "
				+ "[Evrak_No] [nvarchar](10) NOT NULL,"
				+ " [Barkod] [nvarchar](20) NULL,"
				+ " [Kodu] [nvarchar](16) NOT NULL,"
				+ " [Paket_No] [nvarchar] (10) NULL,"
				+ " [Konsimento] [nvarchar](10) NULL,"
				+ " [Miktar] [float] NULL,"
				+ " [Tarih] [datetime] NULL,"
				+ " [Kdv] [float] NULL,"
				+ " [Doviz] [nvarchar](3) NULL,"
				+ " [Fiat] [float] NULL,"
				+ " [Tutar] [float] NULL,"
				+ " [Kur] [float] NULL,"
				+ " [Cari_Firma] [nvarchar](12) NULL,"
				+ " [Adres_Firma] [nvarchar](12) NULL,"
				+ " [Iskonto] [float] NULL,"
				+ " [Tevkifat] [float] NULL,"
				+ " [Ana_Grup] [int] NULL,"
				+ " [Alt_Grup] [int] NULL,"
				+ " [Depo] [int] NULL,"
				+ " [Ozel_Kod] [nvarchar](10) NULL,"
				+ " [Gir_Cik] [nvarchar](1) NULL,"
				+ " [Izahat] [nvarchar](40) NULL,"
				+ " [Cins] [nvarchar](1) NULL,"
				+ " [Nakliyeci] [int] NULL,"
				+ " [USER] [nvarchar](15) NOT NULL,"
				+ " INDEX IX_KERESTE NONCLUSTERED (Evrak_No,Kodu,Tarih,Paket_No,Konsimento,Cari_Firma,Gir_Cik)) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[OZEL]("
				+ " [YONETICI] [nvarchar](25) NULL,"
				+ " [YON_SIFRE] [nvarchar](15) NULL,"
				+ " [FIRMA_ADI] [nvarchar](50) NULL,"
				+ " [USER] [nvarchar](15) NOT NULL"
				+ " ) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[MENSEI_DEGISKEN]("
				+ " [MEID] [int] IDENTITY(1,1) NOT NULL,"
				+ " [MEID_Y] [int]  NOT NULL,"   
				+ " [MENSEI] [nvarchar](25) NOT NULL,"
				+ " [USER] [nvarchar](15) NOT NULL,"
				+ " CONSTRAINT [PKeyMEID] PRIMARY KEY CLUSTERED ("
				+ " [MEID] ASC"
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS"
				+ " = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[ANA_GRUP_DEGISKEN]("
				+ " [AGID] [int] IDENTITY(1,1) NOT NULL,"
				+ " [AGID_Y] [int] NOT NULL,"  
				+ " [ANA_GRUP] [nvarchar](25) NOT NULL,"
				+ " [USER] [nvarchar](15) NOT NULL,"
				+ "  CONSTRAINT [PKeyAGID] PRIMARY KEY CLUSTERED ("
				+ " [AGID] ASC"
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS"
				+ " = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[ALT_GRUP_DEGISKEN]("
				+ " [ALID] [int] IDENTITY(1,1) NOT NULL,"
				+ " [ALID_Y] [int] NOT NULL,"  
				+ " [ANA_GRUP] [int] NOT NULL,"
				+ " [ALT_GRUP] [nvarchar](25) NOT NULL,"
				+ " [USER] [nvarchar](15) NOT NULL,"
				+ " CONSTRAINT [PKeyALID] PRIMARY KEY CLUSTERED ("
				+ " [ALID] ASC"
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS"
				+ " = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[ACIKLAMA]("
				+ " [ACID] [int] IDENTITY(1,1) NOT NULL,"
				+ " [EVRAK_CINS] [nvarchar](3) NULL,"
				+ " [SATIR] [int] NULL,"
				+ " [EVRAK_NO] [nvarchar](10) NULL,"
				+ " [ACIKLAMA] [nvarchar](50) NULL,"
				+ " [Gir_Cik] [nvarchar](1) NULL,"
				+ " CONSTRAINT [PKeyACID] PRIMARY KEY CLUSTERED ("
				+ " [ACID] ASC"
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS"
				+ " = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[KOD_ACIKLAMA]("
				+ " [KOD] [nvarchar](2) NOT NULL,"
				+ " [ACIKLAMA] [nvarchar](50) NULL,"
				+ " CONSTRAINT [KOD] PRIMARY KEY CLUSTERED ([KOD] ASC)WITH (PAD_INDEX = OFF,"
				+ " STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]"
				+ ") ON [PRIMARY]";
		stmt = con.createStatement(); 
		stmt.executeUpdate(sql);
		
		sql = "CREATE TABLE [dbo].[KONS_ACIKLAMA]("
				+ " [KONS] [nvarchar](10) NOT NULL,"
				+ " [ACIKLAMA] [nvarchar](50) NULL,"
				+ " CONSTRAINT [KONS] PRIMARY KEY CLUSTERED ([KONS] ASC)WITH (PAD_INDEX = OFF, "
				+ " STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS= ON) ON [PRIMARY]" 
				+ ") ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE NONCLUSTERED INDEX [IX_ACIKLAMA] ON [dbo].[ACIKLAMA]( "
				+ " [EVRAK_CINS] ASC,"
				+ " [EVRAK_NO] ASC,"
				+ " [Gir_Cik] ASC"
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, "
				+ " ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[DEPO_DEGISKEN]("
				+ " [DPID] [int] IDENTITY(1,1) NOT NULL,"
				+ " [DPID_Y] [int]  NOT NULL,"   
				+ " [DEPO] [nvarchar](25) NOT NULL,"
				+ " [USER] [nvarchar](15) NOT NULL,"
				+ " CONSTRAINT [PKeyDPID] PRIMARY KEY CLUSTERED ("
				+ " [DPID] ASC"
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS"
				+ "  = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[OZ_KOD_1_DEGISKEN]("
				+ " [OZ1ID] [int] IDENTITY(1,1) NOT NULL,"
				+ " [OZ1ID_Y] [int]  NOT NULL,"  
				+ " [OZEL_KOD_1] [nvarchar](25) NOT NULL,"
				+ " [USER] [nvarchar](15) NOT NULL,"
				+ " CONSTRAINT [PKeyOZ1ID] PRIMARY KEY CLUSTERED ("
				+ " [OZ1ID] ASC"
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS"
				+ " = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[DEPOEVRAK]("
				+ " [E_No] [int] NOT NULL"
				+ " ) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[YETKILER]("
				+ " [KULLANICI] [nvarchar](25) NULL,"
				+ " [HESAP] [nvarchar](12) NULL,"
				+ " [TAM_YETKI] [bit] NULL,"
				+ " [GORUNTU] [bit] NULL,"
				+ " [LEVEL] [int] NOT NULL,"
				+ " [USER] [nvarchar](15) NOT NULL"
				+ " ) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[IRS_EVRAK_FORMAT]("
				+ " [SAT_SUT] [nchar](5) NULL,"
				+ " [TARIH] [float] NULL,"
				+ " [SEVK_TARIH] [float] NULL,"
				+ " [FIRMA_KODU] [float] NULL,"
				+ " [FIRMA_UNVANI] [float] NULL,"
				+ " [VERGI_DAIRESI] [float] NULL,"
				+ " [VERGI_NO] [float] NULL,"
				+ " [GIDECEGI_YER] [float] NULL,"
				+ " [NOT_1] [float] NULL,"
				+ " [NOT_2] [float] NULL,"
				+ " [NOT_3] [float] NULL,"
				+ " [BASLIK_BOLUM] [float] NULL,"
				+ " [BARKOD] [float] NULL,"
				+ " [URUN_KODU] [float] NULL,"
				+ " [URUN_ADI] [float] NULL,"
				+ " [DEPO] [float] NULL,"
				+ " [SIMGE] [float] NULL,"
				+ " [BIRIM_FIAT] [float] NULL,"
				+ " [ISKONTO] [float] NULL,"
				+ " [MIKTAR] [float] NULL,"
				+ " [K_D_V] [float] NULL,"
				+ " [TUTAR] [float] NULL,"
				+ " [TUTAR_TOPLAM] [float] NULL,"
				+ " [ISKONTO_TOPLAMI] [float] NULL,"
				+ " [BAKIYE] [float] NULL,"
				+ " [K_D_V_TOPLAMI] [float] NULL,"
				+ " [BELGE_TOPLAMI] [float] NULL,"
				+ " [YAZI_ILE] [float] NULL,"
				+ " [ALT_BOLUM] [float] NULL,"
				+ " [N1] [float] NULL,"
				+ " [N2] [float] NULL,"
				+ " [N3] [float] NULL,"
				+ " [N4] [float] NULL,"
				+ " [N5] [float] NULL,"
				+ " [N6] [float] NULL,"
				+ " [N7] [float] NULL,"
				+ " [N8] [float] NULL,"
				+ " [N9] [float] NULL,"
				+ " [N10] [float] NULL,"
				+ " [USER] [nvarchar](15) NOT NULL"
				+ " ) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "INSERT INTO  IRS_EVRAK_FORMAT(SAT_SUT ,TARIH,SEVK_TARIH,FIRMA_KODU,FIRMA_UNVANI,VERGI_DAIRESI ,VERGI_NO  ,GIDECEGI_YER,NOT_1 ,NOT_2 ,NOT_3,BASLIK_BOLUM,BARKOD,URUN_KODU ,URUN_ADI , DEPO,SIMGE ,BIRIM_FIAT ,ISKONTO ,MIKTAR,K_D_V ,TUTAR ,TUTAR_TOPLAM ,ISKONTO_TOPLAMI  ,BAKIYE ,K_D_V_TOPLAMI ,BELGE_TOPLAMI , YAZI_ILE,ALT_BOLUM, N1 ,N2 ,N3 ,N4 ,N5 ,N6 ,N7 ,N8 ,N9 ,N10,[USER] ) VALUES ('SATIR','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','Admin')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "INSERT INTO  IRS_EVRAK_FORMAT(SAT_SUT ,TARIH,SEVK_TARIH,FIRMA_KODU,FIRMA_UNVANI,VERGI_DAIRESI ,VERGI_NO  ,GIDECEGI_YER,NOT_1 ,NOT_2 ,NOT_3,BASLIK_BOLUM,BARKOD,URUN_KODU ,URUN_ADI , DEPO,SIMGE ,BIRIM_FIAT ,ISKONTO ,MIKTAR,K_D_V ,TUTAR ,TUTAR_TOPLAM ,ISKONTO_TOPLAMI  ,BAKIYE ,K_D_V_TOPLAMI ,BELGE_TOPLAMI , YAZI_ILE,ALT_BOLUM, N1 ,N2 ,N3 ,N4 ,N5 ,N6 ,N7 ,N8 ,N9 ,N10,[USER] ) VALUES ('SUTUN','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','Admin')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[FAT_EVRAK_FORMAT]( "
				+ " [SAT_SUT] [nchar](5) NULL,"
				+ " [TARIH] [float] NULL,"
				+ " [FIRMA_KODU] [float] NULL,"
				+ " [FIRMA_UNVANI] [float] NULL,"
				+ " [VERGI_DAIRESI] [float] NULL,"
				+ " [VERGI_NO] [float] NULL,"
				+ " [GIDECEGI_YER] [float] NULL,"
				+ " [NOT_1] [float] NULL,"
				+ " [NOT_2] [float] NULL,"
				+ " [NOT_3] [float] NULL,"
				+ " [BASLIK_BOLUM] [float] NULL,"
				+ " [BARKOD] [float] NULL,"
				+ " [URUN_KODU] [float] NULL,"
				+ " [URUN_ADI] [float] NULL,"
				+ " [DEPO] [float] NULL,"
				+ " [IZAHAT] [float] NULL,"
				+ " [SIMGE] [float] NULL,"
				+ " [BIRIM_FIAT] [float] NULL,"
				+ " [ISKONTO] [float] NULL,"
				+ " [MIKTAR] [float] NULL,"
				+ " [K_D_V] [float] NULL,"
				+ " [TUTAR] [float] NULL,"
				+ " [TUTAR_TOPLAM] [float] NULL,"
				+ " [ISKONTO_TOPLAMI] [float] NULL,"
				+ " [BAKIYE] [float] NULL,"
				+ " [K_D_V_TOPLAMI] [float] NULL,"
				+ " [BELGE_TOPLAMI] [float] NULL,"
				+ " [TEVKIFAT_ORANI] [float] NULL,"
				+ " [AL_TAR_TEV_ED_KDV] [float] NULL,"
				+ " [TEV_DAH_TOP_TUTAR] [float] NULL,"
				+ " [BEYAN_ED_KDV] [float] NULL,"
				+ " [TEV_HAR_TOP_TUT] [float] NULL,"
				+ " [YAZI_ILE] [float] NULL,"
				+ " [TEV_KASESI] [float] NULL,"
				+ " [ALT_BOLUM] [float] NULL,"
				+ " [N1] [float] NULL,"
				+ " [N2] [float] NULL,"
				+ " [N3] [float] NULL,"
				+ " [N4] [float] NULL,"
				+ " [N5] [float] NULL,"
				+ " [N6] [float] NULL,"
				+ " [N7] [float] NULL,"
				+ " [N8] [float] NULL,"
				+ " [N9] [float] NULL,"
				+ " [N10] [float] NULL,"
				+ " [USER] [nvarchar](15) NULL"
				+ "  ) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "INSERT INTO  FAT_EVRAK_FORMAT(SAT_SUT,TARIH,FIRMA_KODU,FIRMA_UNVANI,VERGI_DAIRESI ,VERGI_NO ,GIDECEGI_YER ,NOT_1 ,NOT_2 ,NOT_3,BASLIK_BOLUM,BARKOD,URUN_KODU ,URUN_ADI , DEPO ,IZAHAT,SIMGE ,BIRIM_FIAT ,ISKONTO ,MIKTAR,K_D_V ,TUTAR ,TUTAR_TOPLAM ,ISKONTO_TOPLAMI  ,BAKIYE ,K_D_V_TOPLAMI ,BELGE_TOPLAMI , YAZI_ILE,TEVKIFAT_ORANI ,AL_TAR_TEV_ED_KDV ,TEV_DAH_TOP_TUTAR , BEYAN_Ed_KDV ,TEV_HAR_TOP_TUT,TEV_KASESI,ALT_BOLUM,N1 ,N2 ,N3 ,N4 ,N5 ,N6 ,N7 ,N8 ,N9 ,N10,[USER] ) VALUES " + " ('SATIR','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','Admin')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "INSERT INTO  FAT_EVRAK_FORMAT(SAT_SUT,TARIH,FIRMA_KODU,FIRMA_UNVANI,VERGI_DAIRESI ,VERGI_NO ,GIDECEGI_YER ,NOT_1 ,NOT_2 ,NOT_3,BASLIK_BOLUM,BARKOD,URUN_KODU ,URUN_ADI , DEPO ,IZAHAT,SIMGE ,BIRIM_FIAT ,ISKONTO ,MIKTAR,K_D_V ,TUTAR ,TUTAR_TOPLAM ,ISKONTO_TOPLAMI  ,BAKIYE ,K_D_V_TOPLAMI ,BELGE_TOPLAMI , YAZI_ILE,TEVKIFAT_ORANI ,AL_TAR_TEV_ED_KDV ,TEV_DAH_TOP_TUTAR , BEYAN_Ed_KDV ,TEV_HAR_TOP_TUT,TEV_KASESI,ALT_BOLUM,N1 ,N2 ,N3 ,N4 ,N5 ,N6 ,N7 ,N8 ,N9 ,N10,[USER]) VALUES " + " ('SUTUN','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','Admin')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// ***************EVRAK NO YAZ ************
		sql = "INSERT INTO  DEPOEVRAK(E_No) VALUES ('0')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// ***************OZEL NO YAZ *************************
		sql = "INSERT INTO  OZEL(YONETICI,YON_SIFRE,FIRMA_ADI,[USER]) VALUES ('" + GLOBAL.KULL_ADI  + "','12345' , '" + fir_adi + "','Admin')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		
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

	

	@Override
	public void kER_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String ker_firma_adi() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;
		ResultSet	rss = null;
		String cumle = "jdbc:sqlserver://" +  BAGLAN.kerDizin.cONN_STR + ";";
		con = DriverManager.getConnection(cumle, BAGLAN.kerDizin.kULLANICI, BAGLAN.kerDizin.sIFRESI);
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM KOD_ACIKLAMA   ORDER BY KOD ");
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public void kod_kayit(String kodu, String aciklama) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "DELETE FROM KOD_ACIKLAMA WHERE KOD =N'" + kod + "'" ;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}

	@Override
	public ResultSet kons_pln() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM KONS_ACIKLAMA   ORDER BY KONS ");
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public void kons_kayit(String kons, String aciklama) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO KONS_ACIKLAMA (KONS,ACIKLAMA) " +
				" VALUES (?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, kons);
		stmt.setString(2, aciklama);
		stmt.executeUpdate();
		stmt.close();
		
	}

	@Override
	public void kons_sil(String kons) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "DELETE FROM KONS_ACIKLAMA WHERE KONS =N'" + kons + "'" ;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}

	@Override
	public String kod_adi(String kod) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql =   "SELECT  " + fieldd + " FROM " + nerden + " WHERE " + sno + " = N'" + arama + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public ResultSet ker_kod_alt_grup_degisken_oku(int sno) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql =   "SELECT ALID_Y , ALT_GRUP FROM ALT_GRUP_DEGISKEN   " +
				" WHERE ANA_GRUP = N'" + sno + "' ORDER BY ALT_GRUP";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	 
	}

	@Override
	public boolean alt_grup_kontrol(int anagrp, int altgrp) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		ResultSet	rs = null;
		boolean result = true;
		String sql  = "" ;
		PreparedStatement stmt =null;
		sql = "SELECT  *  FROM KERESTE   WHERE Ana_Grup = '" + anagrp + "'  AND  Alt_Grup = '" + altgrp + "' ";
		stmt = con.prepareStatement(sql);
		rs = stmt. executeQuery();
		if (!rs.isBeforeFirst() )   result = false ;
		return result;
	}

	@Override
	public void ker_degisken_alt_grup_sil(int ID) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		String sql = "DELETE ALT_GRUP_DEGISKEN    WHERE ALID_Y = '" + ID + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}

	@Override
	public void ker_kod_degisken_sil(String nerden, String hangi_Y, int sira)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		String sql = "DELETE " + nerden  + "    WHERE "+ hangi_Y +" = '" + sira + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}

	@Override
	public void ker_degisken_eski(String fieldd, String degisken_adi, String nerden, String sno, int ID)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		String sql =  "UPDATE " + nerden + "  SET " + fieldd + "  = N'" + degisken_adi + "'  WHERE " + sno + "  = '" + ID + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}

	@Override
	public void ker_degisken_kayit(String fieldd, String nerden, String degisken_adi, String sira)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
		sql  =  "INSERT INTO " + nerden + " (" + fieldd + "," + degisken_adi + ",[USER]) " +
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
		sql  = "INSERT INTO ALT_GRUP_DEGISKEN (ALID_Y,ALT_GRUP,ANA_GRUP,[USER]) " +
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		String sql = "UPDATE ALT_GRUP_DEGISKEN  SET ALT_GRUP  = N'" + alt_grup + "' , ANA_GRUP  = N'" + ana_grup + "'  WHERE ALID_Y = '" + ID + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}

	@Override
	public ResultSet ker_oz_kod(String cins) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql =   "SELECT DISTINCT  Ozel_Kod  " + 
				"  FROM KERESTE WHERE Gir_Cik = '" + cins+ "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}

	@Override
	public String son_no_al(String cins) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String result ;
		String sql = "SELECT max(Evrak_No)  as NO FROM KERESTE WHERE Gir_Cik = '" + cins + "' ";
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
}
