package OBS_PACKAGE;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class STOK_MYSQL implements ISTOK {

	static Connection con = null;
	static Statement stmt = null;
	
	public void baglan() throws SQLException
	{
		String cumle = "jdbc:mysqlserver://" + CONNECTION.fatdizinbilgi.conn_str + ";";
	    con = DriverManager.getConnection(cumle,CONNECTION.fatdizinbilgi.kullanici,CONNECTION.fatdizinbilgi.sifresi);
	}
	@Override
	public void fat_sifirdan_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
 		con = null;  
 		String cumle = "";
         cumle = "jdbc:mysql://localhost;instanceName=" + ins + ";";
         con = DriverManager.getConnection(cumle,kull,sifre);
         String VERITABANI = "OK_Fat" + kod;
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
	public void fat_sifirdan_S(String server, String ins, String kull, String sifre, String kod, String fir_adi)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
 		con = null;  
 		 String VERITABANI = "OK_Fat" + kod;
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
		String sql = null;
        sql = "CREATE TABLE [dbo].[DPN]( "
                      + " [DID] [int] IDENTITY(1,1) NOT NULL,"
                      + "  [Evrak_No] [nvarchar](10) NOT NULL,"
                      + "  [Tip] [nvarchar](1) NULL,"
                      + "  [Bir] [nvarchar](40) NULL,"
                      + "  [Iki] [nvarchar](40) NULL,"
                      + "  [Uc] [nvarchar](40) NULL,"
                      + " [Gir_Cik] [nvarchar](1) NULL,"
                      + " [USER] [nvarchar](15) NOT NULL,"
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
       sql= "CREATE TABLE [dbo].[FATURA]( "
       				+ "[Fatura_No] [nvarchar](10) NOT NULL,"
               		+ " [Kodu] [nvarchar](12) NULL,"
               		+ " [Tarih] [datetime] NULL,"
               		+ " [Kdv] [float] NULL,"
                    + " [Doviz] [nvarchar](3) NULL,"
                    + " [Miktar] [float] NULL,"
                    + " [Fiat] [float] NULL,"
                    + " [Tutar] [float] NULL,"
                    + " [Kur] [float] NULL,"
                    + " [Cari_Firma] [nvarchar](12) NULL,"
                    + " [Iskonto] [float] NULL,"
                    + " [Tevkifat] [float] NULL,"
                    + " [Ana_Grup] [int] NULL,"
                    + " [Alt_Grup] [int] NULL,"
                    + " [Depo] [int] NULL,"
                    + " [Adres_Firma] [nvarchar](12) NULL,"
                    + " [Ozel_Kod] [nvarchar](10) NULL,"
                    + " [Gir_Cik] [nvarchar](1) NULL,"
                    + " [Izahat] [nvarchar](40) NULL,"
                    + " [Cins] [nvarchar](1) NULL,"
                    + " [USER] [nvarchar](15) NOT NULL,"
                    + " INDEX IX_FATURA NONCLUSTERED (Fatura_No,Kodu,Tarih,Cari_Firma,Gir_Cik)) ";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
       sql = "CREATE TABLE [dbo].[IRSALIYE]( "
       		+ " [Irsaliye_No] [nvarchar](10) NOT NULL,"
       		+ " [Kodu] [nvarchar](12) NULL,"
       		+ " [Tarih] [datetime] NULL,"
       		+ " [Kdv] [float] NULL,"
       		+ " [Doviz] [nvarchar](3) NULL,"
       		+ " [Kur] [float] NULL,"
       		+ " [Miktar] [float] NULL,"
       		+ " [Fiat] [float] NULL,"
       		+ " [Tutar] [float] NULL,"
       		+ " [Firma] [nvarchar](12) NULL,"
       		+ " [Iskonto] [float] NULL,"
       		+ " [Fatura_No] [nvarchar](10) NULL,"
       		+ " [Sevk_Tarihi] [date] NULL,"
       		+ " [Ana_Grup] [int] NULL,"
       		+ " [Alt_Grup] [int] NULL,"
       		+ " [Depo] [int] NULL,"
       		+ " [Cari_Hesap_Kodu] [nvarchar](12) NULL,"
       		+ " [Ozel_Kod] [nvarchar](10) NULL,"
       		+ " [Hareket] [nvarchar](1) NULL,"
       		+ " [Izahat] [nvarchar](40) NULL,"
       		+ " [Cins] [nvarchar](1) NULL,"
       		+ " [USER] [nvarchar](15) NOT NULL,"
       		+ " INDEX IX_IRSALIYE NONCLUSTERED (Irsaliye_No,Kodu,Tarih,Firma,Hareket) )";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
       sql = "CREATE TABLE [dbo].[MAL]( "
       		+ " [Kodu] [nvarchar](12) NOT NULL,"
       		+ " [Adi] [nvarchar](40) NULL,"
       		+ " [Birim] [nvarchar](5) NULL,"
       		+ " [Kusurat] [int] NULL,"
       		+ " [Resim] [image] NULL,"
       		+ " [Sinif] [nvarchar](5) NULL,"
       		+ " [Ana_Grup] [int] NULL,"
       		+ " [Alt_Grup] [int] NULL,"
       		+ " [Aciklama_1] [nvarchar](25) NULL,"
       		+ " [Aciklama_2] [nvarchar](25) NULL,"
       		+ " [Ozel_Kod_1] [int] NULL,"
       		+ " [Ozel_Kod_2] [int] NULL,"
       		+ " [Ozel_Kod_3] [int] NULL,"
       		+ " [KDV] [float] NULL,"
       		+ " [Barkod] [nvarchar](20) NULL,"
       		+ " [Mensei] [int] NULL,"
       		+ " [Agirlik] [float] NULL,"
       		+ " [Depo] [int] NULL,"
       		+ " [Fiat] [float] NULL,"
       		+ " [Fiat_2] [float] NULL,"
       		+ " [Fiat_3] [float] NULL,"
       		+ " [Recete] [nvarchar](10) NULL,"
       		+ " [USER] [nvarchar](15) NOT NULL,"
       		+ " CONSTRAINT [IX_Kodu] PRIMARY KEY CLUSTERED ("
       		+ " [Kodu] ASC"
       		+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS"
       		+ " = ON) ON [PRIMARY]) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
       sql = "CREATE NONCLUSTERED INDEX [IX_MAL] ON [dbo].[MAL]( "
       		+ " [Adi] ASC,"
       		+ " [Ana_Grup] ASC,"
       		+ " [Alt_Grup] ASC"
       		+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, "
       		+ " ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
       sql = "CREATE TABLE [dbo].[RECETE]( "
       		+ " [Recete_No] [nvarchar](10) NOT NULL,"
       		+ " [Ana_Grup] [int] NULL,"
       		+ " [Alt_Grup] [int] NULL,"
       		+ " [Durum] [bit] NULL,"
       		+ " [Tur] [nvarchar](7) NULL,"
       		+ " [Kodu] [nvarchar](10) NULL,"
       		+ " [Miktar] [float] NULL,"
       		+ " [USER] [nvarchar](15) NOT NULL) ON [PRIMARY]";
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
       sql = "CREATE TABLE [dbo].[STOK]("
       		+ " [Evrak_No] [nvarchar](10) NOT NULL,"
       		+ " [Evrak_Cins] [nvarchar](3) NULL,"
       		+ " [Tarih] [datetime] NULL,"
       		+ " [Depo] [int] NULL,"
       		+ " [Urun_Kodu] [nvarchar](12) NULL,"
       		+ " [Miktar] [float] NULL,"
       		+ " [Fiat] [float] NULL,"
       		+ " [Tutar] [float] NULL,"
       		+ " [Ana_Grup] [int] NULL,"
       		+ " [Alt_Grup] [int] NULL,"
       		+ " [Hareket] [nvarchar](1) NULL,"
       		+ " [Izahat] [nvarchar](40) NULL,"
       		+ " [Hesap_Kodu] [nvarchar](12) NULL,"
       		+ " [Kur] [FLOAT] NULL,"
       		+ " [Doviz] [nvarchar](3) NULL,"
       		+ " [Kdvli_Tutar] [float] NULL,"
       		+ " [B1] [nvarchar](15) NULL,"
       		+ " [USER] [nvarchar](40) NOT NULL,"
       		+ " INDEX IX_STOK NONCLUSTERED (Urun_Kodu,Tarih,Hareket))";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
       sql = "CREATE NONCLUSTERED INDEX [IX_Cikan] ON [dbo].[STOK]( "
       		+ " [Urun_Kodu] ASC,"
       		+ " [Tarih] ASC)"
       		+ " INCLUDE ( 	[Miktar]) "
       		+ " WHERE ([Hareket]='C')"
       		+ " WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF,"
       		+ " ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
       sql = "CREATE NONCLUSTERED INDEX [IX_Giren] ON [dbo].[STOK]( "
       		+ " [Urun_Kodu] ASC,"
       		+ " [Tarih] ASC)"
       		+ " INCLUDE ( 	[Fiat]) "
       		+ " WHERE ([Hareket]='G')"
       		+ " WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF,"
       		+ " ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100)";
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
       sql = "CREATE NONCLUSTERED INDEX [IX_ACIKLAMA] ON [dbo].[ACIKLAMA]( "
       		+ " [EVRAK_CINS] ASC,"
       		+ " [EVRAK_NO] ASC,"
       		+ " [Gir_Cik] ASC"
       		+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, "
       		+ " ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
       sql = "CREATE TABLE [dbo].[BOZUK_MAL]( "
       		+ " [Evrak_No] [nvarchar](10) NOT NULL,"
       		+ " [Kodu] [nvarchar](12) NULL,"
       		+ " [Tarih] [datetime] NULL,"
       		+ " [Miktar] [float] NULL,"
       		+ " [Fiat] [float] NULL,"
       		+ " [Ana_Grup] [int] NULL,"
       		+ " [Alt_Grup] [int] NULL,"
       		+ " [Depo] [int] NULL,"
       		+ " [Ozel_Kod] [nvarchar](10) NULL,"
       		+ " [Izahat] [nvarchar](40) NULL,"
       		+ " [Cins] [nvarchar](1) NULL,"
       		+ " [USER] [nvarchar](15) NOT NULL"
       		+ "  ) ON [PRIMARY]";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
       sql = "CREATE NONCLUSTERED INDEX [IX_BOZUK_MAL] ON [dbo].[BOZUK_MAL]( "
       		+ " [Evrak_No] ASC,"
       		+ " [Kodu] ASC,"
       		+ " [Tarih] ASC"
       		+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF,"
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
       sql = "CREATE TABLE [dbo].[OZ_KOD_2_DEGISKEN]("
       		+ " [OZ2ID] [int] IDENTITY(1,1) NOT NULL,"
       		+ " [OZ2ID_Y] [int] NOT NULL,"   
       		+ " [OZEL_KOD_2] [nvarchar](25) NOT NULL,"
       		+ " [USER] [nvarchar](15) NOT NULL,"
       		+ " CONSTRAINT [PKeyOZ2ID] PRIMARY KEY CLUSTERED ("
       		+ " [OZ2ID] ASC"
       		+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS "
       		+ " = ON) ON [PRIMARY]) ON [PRIMARY]";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
       sql = "CREATE TABLE [dbo].[DEPOEVRAK]("
       		+ " [E_No] [int] NOT NULL"
       		+ " ) ON [PRIMARY]";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
       sql = "CREATE TABLE [dbo].[URET_EVRAK]("
       		+ " [E_No] [int] NULL"
       		+ " ) ON [PRIMARY]";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
       sql = "CREATE TABLE [dbo].[ZAYI_EVRAK]("
          		+ " [E_No] [int] NULL"
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
       sql = "INSERT INTO  URET_EVRAK(E_No) VALUES ('0')";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
       sql = "INSERT INTO  ZAYI_EVRAK(E_No) VALUES ('0')";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
       // ***************OZEL NO YAZ *************************
       sql = "INSERT INTO  OZEL(YONETICI,YON_SIFRE,FIRMA_ADI,[USER]) VALUES ('" + GLOBAL.KULL_ADI  + "','12345' , '" + fir_adi + "','Admin')";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
	}

	@Override
	public String fat_firma_adi() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		 	con = null;
			ResultSet	rss = null;
			String cumle = "jdbc:mysql://" + CONNECTION.fatdizinbilgi.conn_str + ";";
      con = DriverManager.getConnection(cumle,CONNECTION.fatdizinbilgi.kullanici,CONNECTION.fatdizinbilgi.sifresi);
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
	public ResultSet stk_kod_degisken_oku(String fieldd, String sno, String nerden)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet urun_kod_degisken_ara(String fieldd, String sno, String nerden, String arama)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet stk_kod_alt_grup_degisken_oku(int sno) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void stk_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void urun_degisken_eski(String fieldd, String degisken_adi, String nerden, String sno, int ID)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void urun_degisken_alt_grup_eski(String alt_grup, int ana_grup, int ID)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void urun_degisken_kayit(String fieldd, String nerden, String degisken_adi, String sira)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void urun_degisken_alt_grup_kayit(String alt_grup, int ana_grup)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet stk_urun(String sira,String arama) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void stk_ur_sil(String kodu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stk_ur_kayit(String kodu, String adi, String birim, double kusurat, String sinif, int anagrup,
			int altgrup, String acik1, String acik2, int ozkod1, int ozkod2, String barkod, int mensei, double agirlik,
			InputStream resim, double fiat, double fiat2, String recete, String usr, double fiat3)
			throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet ur_kod_bak(String kodu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet stk_barkod_kod_oku(String sira) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet urun_adi_oku(String kodu, String kodbarcode) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet fat_oz_kod(String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet son_satis_fiati_oku(String kodu, String muskodu, String gircik)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void fat_giris_sil(String fno, String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void fat_kaydet(String fatno, String kodu, int depo, double fiat, double tevkifat, double miktar,
			String gircik, double tutar, double iskonto, double kdv, String tarih, String izah, String doviz,
			String adrfirma, String carfirma, String ozkod, double kur, String cins, int anagrp, int altgrp, String usr)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
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
	public void fat_no_yaz(String irsno, String fatno) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
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
	public void stok_sil(String eno, String ecins, String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stk_kaydet(String evrno, String evrcins, String tarih, int depo, String urnkodu, double miktar,
			double fiat, double tutar, double kdvlitut, String hareket, String izah, int anagrp, int altgrp, double kur,
			String b1, String doviz, String hspkodu, String usr) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet fatura_oku(String fno, String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String aciklama_oku(String evrcins, int satir, String evrno, String gircik)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet irsaliye_no_doldur(String fno, String hareket) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet dipnot_oku(String ino, String cins, String gircik) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int fatura_no_al(String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean stok_bak_kontrol(String kodu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean stok_bak_kontrol_barcode(String barkodu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void rec_sil(String rno) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void recete_kayit(String recno, boolean durum, String tur, String kodu, double miktar, int anagrp,
			int altgrup, String usr) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void kod_recete_yaz(String ukodu, String rec) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int recete_no_al() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String recete_son_bordro_no_al() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet recete_oku(String rno) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet urun_arama() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet imalat_urun_ara(String kodu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet resim_oku(String ukodu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet recete_arama() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet stok_oku(String eno, String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String uret_son_bordro_no_al() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int uretim_fisno_al() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double son_imalat_fiati_oku(String kodu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ResultSet uret_ilk_tarih(String baslangic, String tar, String ukodu)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double gir_ort_fiati_oku(String kodu, String ilkt, String tarih)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int irsaliye_no_al(String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ResultSet irsaliye_oku(String ino, String hareket) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void irs_giris_sil(String ino, String hareket) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void irs_kayit(String irsno, String kodu, int depo, double fiat, double iskon, double miktar, double tutar,
			double kdv, String tar, String dvz, double kur, String firma, String crhsp, String sevktar, String ozkod,
			int anagrp, int altgrp, String fatno, String harek, String cins, String usr, String izahat)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet irs_oz_kod(String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet irs_son_satis_fiati_oku(String kodu, String muskodu, String gircik)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet ggdy_oku() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void gdy_sil(Integer gid) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void gdy_kayit(String isim, String adres, String semt, String sehir, String usr)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet parametre_oku(String nerden, String satsut) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void evr_format_irs(String satsut, double Tarih, double Sevk_Tarih, double FIRMA_KODU, double FIRMA_UNVANI,
			double VERGI_DAIRESI, double VERGI_NO, double GIDECEGI_YER, double NOT_1, double NOT_2, double NOT_3,
			double BASLIK_BOLUM, double BARKOD, double URUN_KODU, double URUN_ADI, double DEPO, double SIMGE,
			double BIRIM_FIAT, double ISKONTO, double MIKTAR, double K_D_V, double TUTAR, double TUTAR_TOPLAM,
			double ISKONTO_TOPLAMI, double BAKIYE, double K_D_V_TOPLAMI, double BELGE_TOPLAMI, double YAZI_ILE,
			double ALT_BOLUM, String usr) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void evr_format_fat(String satsut, double Tarih, double FIRMA_KODU, double FIRMA_UNVANI,
			double VERGI_DAIRESI, double VERGI_NO, double GIDECEGI_YER, double NOT_1, double NOT_2, double NOT_3,
			double BASLIK_BOLUM, double BARKOD, double URUN_KODU, double URUN_ADI, double DEPO, double IZAHAT,
			double SIMGE, double BIRIM_FIAT, double ISKONTO, double MIKTAR, double K_D_V, double TUTAR,
			double TUTAR_TOPLAM, double ISKONTO_TOPLAMI, double BAKIYE, double K_D_V_TOPLAMI, double BELGE_TOPLAMI,
			double TEVKIFAT_ORANI, double AL_TAR_TEV_ED_KDV, double TEV_DAH_TOP_TUTAR, double BEYAN_ED_KDV,
			double TEV_HAR_TOP_TUT, double YAZI_ILE, double TEV_KASESI, double ALT_BOLUM, double N1, double N2,
			double N3, double N4, double N5, double N6, double N7, double N8, double N9, double N10, String usr)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet fat_rapor(String ino1, String ino2, String t1, String t2, String m1, String m2, String k1,
			String k2, String tv1, String tv2, String anagrup, String altgrup, String depo, String turu, String ozel1,
			String ozel2, String a1, String a2, String dvz, String dvz2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet fat_rapor_fat_tar(String ino1, String ino2, String t1, String t2, String m1, String m2, String k1,
			String k2, String tv1, String tv2, String anagrup, String altgrup, String depo, String turu, String ozel1,
			String ozel2, String a1, String a2, String bir, String dvz, String dvz2, String iki, String grp)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet fat_rapor_cari_kod(String ino1, String ino2, String t1, String t2, String m1, String m2, String k1,
			String k2, String tv1, String tv2, String anagrup, String altgrup, String depo, String turu, String ozel1,
			String ozel2, String a1, String a2, String cari_yer, String dvz, String dvz2, String grp)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet imalat_rapor(String f1, String f2, String t1, String t2, String k1, String k2, String r1,
			String r2, String depo, String anagrp, String altgrp, String b1, String b2, String uanagrp, String ualtgrp)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet envanter_rapor_u_kodu(String t1, String t2, String k1, String k2, String f1, String f2, String m1,
			String m2, String anagrup, String altgrup, String depo, String depohar, String ure, String uanagrp,
			String ualtgrp) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet envanter_rapor_ana_grup_alt_grup(String t1, String t2, String k1, String k2, String f1, String f2,
			String m1, String m2, String anagrup, String altgrup, String depo, String depohar, String ure,
			String uanagrp, String ualtgrp) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet stok_rapor(String t1, String t2, String t3, String t4, String k1, String k2, String f1, String f2,
			String f3, String turu, String depohar, String ure, String uanagrp, String ualtgrp)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet baslik_bak(String baslik,String ordr,String jkj, String ch1, String k1, String k2, String f1, String f2, String t1,
			String t2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet grp_urn_kodlu(String sstr_2, String sstr_4, String kur_dos, String jkj, String ch1, String qwq6,
			String qwq7, String qwq8, String s1, String s2, String k1, String k2, String jkj1, String deg1, String deg2,
			String t1, String t2, String sstr_5, String sstr_1) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet grp_urn_kodlu_yil(String sstr_2, String sstr_4, String kur_dos, String jkj, String ch1,
			String qwq6, String qwq7, String qwq8, String s1, String s2, String k1, String k2, String jkj1, String deg1,
			String deg2, String t1, String t2, String sstr_5, String sstr_1)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet grp_mus_kodlu(String sstr_2, String sstr_4, String kur_dos, String jkj, String ch1, String qwq6,
			String qwq7, String qwq8, String s1, String s2, String k1, String k2, String jkj1, String deg1, String deg2,
			String t1, String t2, String sstr_5, String sstr_1) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet grp_mus_kodlu_yil(String sstr_2, String sstr_4, String kur_dos, String jkj, String ch1,
			String qwq6, String qwq7, String qwq8, String s1, String s2, String k1, String k2, String jkj1, String deg1,
			String deg2, String t1, String t2, String sstr_5, String sstr_1)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet grp_yil_ay(String sstr_2, String sstr_4, String kur_dos, String jkj, String ch1, String qwq6,
			String qwq7, String qwq8, String s1, String s2, String k1, String k2, String jkj1, String deg1, String deg2,
			String t1, String t2, String sstr_5, String sstr_1) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet grp_yil(String sstr_2, String sstr_4, String kur_dos, String jkj, String ch1, String qwq6,
			String qwq7, String qwq8, String s1, String s2, String k1, String k2, String jkj1, String deg1, String deg2,
			String t1, String t2, String sstr_5, String sstr_1) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet grp_ana_grup(String sstr_2, String sstr_4, String kur_dos, String jkj, String ch1, String qwq6,
			String qwq7, String qwq8, String s1, String s2, String k1, String k2, String jkj1, String deg1, String deg2,
			String t1, String t2, String sstr_5, String sstr_1) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet grp_ana_grup_yil(String sstr_2, String sstr_4, String kur_dos, String jkj, String ch1, String qwq6,
			String qwq7, String qwq8, String s1, String s2, String k1, String k2, String jkj1, String deg1, String deg2,
			String t1, String t2, String sstr_5, String sstr_1) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet ima_baslik_bak(String bas, String jkj, String ch1, String qwq6, String qwq7, String qwq8,
			String qwq9, String k1, String k2, String t1, String t2, String ordrr)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet ima_alt_kod(String slct, String sstr_5, String sstr_2, String sstr_4, String jkj, String ch1,
			String qwq6, String qwq7, String qwq8, String qwq9, String s1, String s2, String k1, String k2, String t1,
			String t2, String sstr_1, String ordrr,String sstr_55) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet irs_rapor(String ino1, String ino2, String t1, String t2, String m1, String m2, String k1,
			String k2, String turu, String anagrup, String altgrup, String ozkod, String fat1, String fat2, String a1,
			String a2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet rec_rapor(String f1, String f2, String k1, String k2, String anagrp, String altgrp, String durum,
			String uanagrp, String ualtgrp) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet rec_detay_rapor(String f1, String f2)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet irs_detay_rapor(String ino , String turu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet ort_hes_kodu(String fdf,String qwq6 , String qwq7, String m1 ,String m2,String s1,String s2,
			String k1 ,String k2 ,String t1,String t2,String kurkod,String kurc) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet ort_hes_ana_kodu(String fdf, String qwq6, String qwq7, String m1, String m2, String s1, String s2,
			String k1, String k2, String t1, String t2, String kurkod, String kurc)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet ort_diger_kodu(String yu, String qwq6, String qwq7, String m1, String m2, String s1, String s2,
			String k1, String k2, String t1, String t2, String kurkod, String kurc, String iu)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet bos_kur(String kur, String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet urun_liste(String k1, String k2, String b1, String b2, String bi1, String bi2, String s1,
			String s2, String anagrp, String altgrp, String mensei, String o1, String o2, String depo)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet uret_doldur(String t1, String t2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void uret_no_degis(String e_no, String y_no) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void uret_b1_degis(String y_no) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void uret_b1_sifir(String y_no) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void uret_izahat_duzelt(String y_no) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void uret_izahat_cikis_duzelt(String y_no, String eski_no) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void uret_aciklama_duzelt(String y_no, int asid) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void uretim_fisno_yapilanma_kayit(int nom) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet sql_sorgu(String sql) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet zayi_oku(String eno, String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String zayi_son_bordro_no_al() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String son_no_al(String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet envanter_rapor(String t1, String t2, String k1, String k2, String f1, String f2, String m1,
			String m2, String anagrup, String altgrup, String depo, String depohar, String ure, String uanagrp,
			String ualtgrp, String calisanpara) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet zayi_rapor(String t1, String t2, String k1, String k2, String f1, String f2, String anagrp,
			String altgrp, String depo, String uanagrp, String ualtgrp) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet irsaliye_faturasiz(String hareket,String arama) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String son_irsno_al(String cins) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet envanter_rapor_fifo(String t1, String t2, String k1, String k2, String f1, String f2, String m1,
			String m2, String anagrup, String altgrup, String depo, String depohar, String ure, String uanagrp,
			String ualtgrp, String calisanpara) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet envanter_rapor_fifo_2(String t1, String t2, String k1, String k2, String f1, String f2, String m1,
			String m2, String anagrup, String altgrup, String depo, String depohar, String ure, String uanagrp,
			String ualtgrp, String ukodu, String calisanpara) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BigDecimal envanter_rapor_lifo(String t1, String t2, String k1, String k2, String f1, String f2, String m1,
			String m2, String anagrup, String altgrup, String depo, String depohar, String ure, String uanagrp,
			String ualtgrp, String calisanpara) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void ana_yaz(String ydosya, String kull) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void alt_yaz(String ydosya, String kull) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void gidecegi_yer(String ydosya, String kull) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void evr_for(String ydosya, String kull) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mal_yaz(String ydosya, String kull, String eski_ukodu, String yen_ukodu)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void ysonu_fat_kaydet(String fatno, String kodu, int depo, double fiat, double tevkifat, double miktar,
			String gircik, double tutar, double iskonto, double kdv, String tarih, String izah, String doviz,
			String adrfirma, String carfirma, String ozkod, double kur, String cins, int anagrp, int altgrp, String usr,
			String ydosya) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void ysonu_stk_kaydet(String evrno, String evrcins, String tarih, int depo, String urnkodu, double miktar,
			double fiat, double tutar, double kdvlitut, String hareket, String izah, int anagrp, int altgrp, double kur,
			String b1, String doviz, String hspkodu, String usr, String ydosya)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void degisken_degistir(int anagrp, int altgrp, int anaygrp, int altygrp)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void urun_degisken_alt_grup_sil(int ID) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean alt_grup_kontrol(int anagrp, int altgrp) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void urun_kod_degisken_sil(String nerden, String hangi_Y, int sira)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	



}
