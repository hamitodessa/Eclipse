package OBS_C_2025;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CARI_HESAP_MYSQL implements ICARI_HESAP {

	public static Connection con = null;
	public static Connection akt_con = null;
	static Connection SQLitecon = null;
	static Statement stmt = null;
	private GLOBAL gLB = new GLOBAL();
	
	public void baglan() throws SQLException
	{
		String cumle = "jdbc:mysql://" + BAGLAN.cariDizin.cONN_STR + ";";
	    con = DriverManager.getConnection(cumle,BAGLAN.cariDizin.kULLANICI,BAGLAN.cariDizin.sIFRESI);
	}
	public void akt_baglan(String kod) throws SQLException
	{
		String cnnstr = "" ;
		if (new String( BAGLAN.cariDizin.yER.toString()).equals("L") == true) 
        { 
			cnnstr = "localhost:" + BAGLAN.cariDizin.sERVER + " ; database=OK_Car" + kod ;
        }
        else
        { 
        	cnnstr = BAGLAN.cariDizin.sERVER + " ; database=ok_car" + kod ;
        }
		String cumle = "jdbc:mysql://" + cnnstr + ";";
	    akt_con = DriverManager.getConnection(cumle,BAGLAN.cariDizin.kULLANICI,BAGLAN.cariDizin.sIFRESI);
	}
	@Override
	public void cari_sifirdan_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre,String port) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
 		con = null;  
 		String cumle = "";
         cumle = "jdbc:mysql://localhost:" +port ;
         con = DriverManager.getConnection(cumle,kull,sifre);
        String VERITABANI = "ok_car" + kod;
         stmt = null;
         String sql =null;
     	sql = "CREATE DATABASE " + VERITABANI ;
        stmt = con.createStatement();  
        stmt.execute(sql);
         ///
         
        cumle = "jdbc:mysql://localhost/" +VERITABANI ;
        con = DriverManager.getConnection(cumle,kull,sifre);
   
         create_table(fir_adi);
        
         stmt.close();
         con.close();
		
	}

	@Override
	public void cARI_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
 		con = null;  
 		String VERITABANI = "ok_car" + kod;
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
            //
            sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
            stmt = con.createStatement();  
            stmt.executeUpdate(sql);
            cumle = "jdbc:mysql://" + server + ";instanceName=" + ins + ";database=" + VERITABANI + "_LOG" + ";";
            con = DriverManager.getConnection(cumle,kull,sifre);
            create_table_log();
          
            stmt.close();
            con.close();
		
	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql = null;
        sql = "CREATE TABLE `hesap` ("
        		+ "  `HESAP` NVARCHAR(12) NOT NULL,"
        		+ "  `UNVAN` NVARCHAR(50) NULL,"
        		+ "  `KARTON` NVARCHAR(5) NULL,"
        		+ "  `HESAP_CINSI` NVARCHAR(3) NULL,"
        		+ "  `USER` NVARCHAR(15) NULL,"
        		+ "  PRIMARY KEY (`HESAP`),"
        		+ "  UNIQUE INDEX `HESAP_UNIQUE` (`HESAP` ASC) INVISIBLE,"
        		+ "  INDEX `IX_HESAP` (`HESAP` ASC) INVISIBLE);";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);

        sql = "CREATE TABLE `HESAP_DETAY` ( "
	                      + " `D_HESAP` NVARCHAR(12) NOT NULL,"
	                      + " `YETKILI` NVARCHAR(30) NULL,"
                          + " `TC_KIMLIK` NVARCHAR(15) NULL,"
	                      + " `ADRES_1` NVARCHAR(35) NULL,"
	                      + " `ADRES_2` NVARCHAR(35) NULL,"
	                      + " `SEMT` NVARCHAR(15) NULL,"
	                      + " `SEHIR` NVARCHAR(15) NULL,"
	                      + " `VERGI_DAIRESI` NVARCHAR(25) NULL,"
	                      + " `VERGI_NO` NVARCHAR(15) NULL,"
	                      + " `FAX` NVARCHAR(25) NULL,"
	                      + " `TEL_1` NVARCHAR(25) NULL,"
	                      + " `TEL_2` NVARCHAR(25) NULL,"
	                      + " `TEL_3` NVARCHAR(25) NULL,"
	                      + " `OZEL_KOD_1` NVARCHAR(15) NULL,"
	                      + " `OZEL_KOD_2` NVARCHAR(15) NULL,"
	                      + " `OZEL_KOD_3` NVARCHAR(15) NULL,"
	                      + " `ACIKLAMA` NVARCHAR(30) NULL,"
	                      + " `WEB` NVARCHAR(50) NULL,"
	                      + " `E_MAIL` NVARCHAR(30) NULL,"
	                      + " `SMS_GONDER` TINYINT NULL,"
	                      + " `RESIM` MEDIUMBLOB NULL,"
	                      + "  PRIMARY KEY (`D_HESAP`),"
	              		+ "  UNIQUE INDEX `D_HESAP_UNIQUE` (`D_HESAP` ASC) INVISIBLE,"
	              		+ "  INDEX `IX_DHESAP` (`D_HESAP` ASC) INVISIBLE);";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE `SATIRLAR` ("
        		+ "  `SID` INT NOT NULL,"
        		+ "  `HESAP` VARCHAR(12) NOT NULL,"
        		+ "  `TARIH` DATETIME NULL,"
        		+ "  `H` VARCHAR(1) NULL,"
        		+ "  `EVRAK` INT NOT NULL,"
        		+ "  `CINS` VARCHAR(2) NULL,"
        		+ "  `KUR` FLOAT NULL,"
        		+ "  `BORC` FLOAT NULL,"
        		+ "  `ALACAK` FLOAT NULL,"
        		+ "  `KOD` VARCHAR(5) NULL,"
        		+ "  `USER` VARCHAR(15) NULL,"
        		+ "  `SATIRLARcol` VARCHAR(45) NULL,"
        		+ "  PRIMARY KEY (`SID`),"
        		+ "  UNIQUE INDEX `SID_UNIQUE` (`SID` ASC) VISIBLE,"
        		+ "  INDEX `IX_SATIRLAR` (`TARIH` ASC, `EVRAK` ASC, `CINS` ASC, `USER` ASC, `HESAP` ASC) INVISIBLE);";
                         
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        
        sql = "CREATE TABLE `IZAHAT`(	`EVRAK` int NOT NULL,	`IZAHAT` nvarchar(100) NULL,"
        		+ "  PRIMARY KEY (`EVRAK`),"
        		+ "  UNIQUE INDEX `EVRAK_UNIQUE` (`EVRAK` ASC) VISIBLE,"
        		+ "  INDEX `IX_IZAHAT` ( `EVRAK` ASC) INVISIBLE);";
          stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE `EVRAK_NO` (EID INTEGER AUTO_INCREMENT PRIMARY KEY ,`EVRAK` integer )";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE `OZEL` ("
        		+ "  `OZID` INTEGER AUTO_INCREMENT PRIMARY KEY,"
        		+ "  `YONETICI` VARCHAR(25) NULL,"
        		+ "  `YON_SIFRE` VARCHAR(15) NULL,"
        		+ "  `FIRMA_ADI` VARCHAR(50) NULL);";
         stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        JOptionPane.showMessageDialog(null,"Yeni Versiyon Mevcu","ddssdsd" , JOptionPane.PLAIN_MESSAGE);   
       sql = "CREATE TABLE `YETKILER`( "
       		 + " `YETID INTEGER AUTO_INCREMENT PRIMARY KEY,"
	                         + "`KULLANICI` nvarchar(25) NULL,"
	                         + "`KARTON` nvarchar(5) NULL,"
	                         + "`TAM_YETKI` TINYINT NULL,"
	                         + "`GORUNTU` TINYINT NULL);";
       stmt = con.createStatement();  
       stmt.executeUpdate(sql);
        sql= "CREATE TABLE `ANA_GRUP_DEGISKEN`( "
       		 + "`ANA_GRUP` nvarchar(25) NOT NULL,"
	                   + " `USER` nvarchar`(15) NOT NULL,"
	                   + "  PRIMARY KEY (`SID`),"
	           		+ "  UNIQUE INDEX `SID_UNIQUE` (`SID` ASC) VISIBLE,"
	           		+ "  INDEX `IX_SATIRLAR` (`TARIH` ASC, `EVRAK` ASC, `CINS` ASC, `USER` ASC, `HESAP` ASC) INVISIBLE);";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE [dbo].[ALT_GRUP_DEGISKEN]( "
       		 			+ "[ANA_GRUP] [int] NOT NULL, "
	                        + "[ALT_GRUP] [nvarchar](25) NOT NULL, "
	                        + "[USER] [nvarchar](15) NOT NULL) ON [PRIMARY]";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        // ***************EVRAK NO YAZ ************
        sql = "INSERT INTO  EVRAK_NO(EVRAK) VALUES ('0')";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        // ***************OZEL NO YAZ ************
        sql = "INSERT INTO  OZEL(YONETICI,YON_SIFRE,FIRMA_ADI) VALUES ('" + GLOBAL.KULL_ADI  + "','12345' , '" + fir_adi + "')";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
		
	}

	@Override
	public String cari_firma_adi() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
	 	con = null;
		ResultSet	rss = null;
		String cumle = "jdbc:mysql://" + BAGLAN.cariDizin.cONN_STR + ";";
       con = DriverManager.getConnection(cumle,BAGLAN.cariDizin.kULLANICI,BAGLAN.cariDizin.sIFRESI);
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
	public ResultSet ekstre(String hesap, String t1, String t2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet hesap_adi_oku(String hesap) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet hp_pln() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet mizan(String kod, String ilktarih, String sontarih, String ilkhcins, String sonhcins,
			String ilkkar, String sonkar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet kasa_mizan(String kod, String ilktarih, String sontarih) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sqlite_yaz(String tar, int evr, String iza, String kodu, double kur,double borc, double alac, double bak) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sqlite_sil() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet ekstre_sqlite() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet mizan(String h1, String h2, String t1, String t2, String c1, String c2, String k1, String k2,
			String o1, String o2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet fiskon(int evrakno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet cari_sonfisno() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cari_fisno_al() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void evrak_yoket(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean cari_fino_bak(int fisno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void cari_dekont_kaydet(String bhes, String tar, int evrak, String bcins, Double bkur, Double borc,
			String alhes, String acins, Double alkur, Double alacak, String izahat, String kod, String user)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet hsp_pln(String arama) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hsp_sil(String hesap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String kod_ismi(String kodu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hpln_kayit(String kodu, String adi, String karton, String hcins, String usr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hpln_detay_kayit(String kodu, String yet, String ad1, String ad2, String semt, String seh, String vd,
			String vn, String t1, String t2, String t3, String fx, String o1, String o2, String o3, String web,
			String mai, String kim, String acik, boolean sms, InputStream resim) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet dvz_cevirme(String kcins, String hesap, String t1, String t2, String kur,  String islem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet karton_dold(String karton) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet ekstre_arama(String hes, String acik, String gun, String ay, String yil, String kod,String kullanici) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet kasa_kontrol(String hesap, String t1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet dek_mizan(String kod) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int coklu_cari_fisno_al(int adet) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ResultSet gunisl(String t1, String t2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int hesap_plani_kayit_adedi() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void hpln_ilk_detay_kayit(String kodu) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cari_kod_degis_hesap(String t1, String t2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cari_kod_degis_satirlar(String t1, String t2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void yilsonu_hpln_kayit(String kodu, String adi, String karton, String hcins, String usr) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void yilsonu_hpln_detay_kayit(String kodu, String yet, String ad1, String ad2, String semt, String seh,
			String vd, String vn, String t1, String t2, String t3, String fx, String o1, String o2, String o3,
			String web, String mai, String kim, String acik) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet mizan_aktar(String hesap) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int yilsonu_cari_fisno_al() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void yilsonu_cari_dekont_kaydet(String bhes, String tar, int evrak, String bcins, Double bkur, Double borc,
			String alhes, String acins, Double alkur, Double alacak, String izahat, String kod, String user)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int aktar_hesap_plani_kayit_adedi() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void cari_firma_adi_kayit(String fadi) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet sms_cari_pln(String nerden) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet ozel_mizan(String h1, String h2, String t1, String t2, String c1, String c2, String k1, String k2,
			String o1, String o2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet evrak_ogren(String text) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet eksik_kur_okuma(String hesap, String t1, String t2, String kur)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet sql_sorgu(String sql) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String[] cari_adres_oku(String kodu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet hesap_adi_auto(String hesap) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet ekstre_proc(String hesap, String t1, String t2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet gunisl_proc(String t1, String t2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void create_table_log() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "" ;
	    sql = "CREATE TABLE [dbo].[LOGLAMA]( "
		 			+ " [TARIH] [datetime] NOT NULL,"
                    + " [MESAJ] [nvarchar](100) NULL,"
                    + " [EVRAK] [int] NOT NULL,"
                    + " [USER] [nvarchar](15) NULL,"
                    + " CONSTRAINT [IX_TAR] PRIMARY KEY CLUSTERED(	[TARIH] ASC)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, "
                    + " IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]) ON [PRIMARY]";
	    	stmt = con.createStatement();  
	    	stmt.executeUpdate(sql);
		
	}
	

	


}
