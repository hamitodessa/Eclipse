package OBS_C_2025;


import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;



public interface ICARI_HESAP {

	public void baglan() throws SQLException, ClassNotFoundException;
	public void akt_baglan(String kod, String port) throws SQLException;
	public void cari_sifirdan_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void cARI_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void create_table(String fir_adi) throws SQLException;
	public void create_table_log() throws SQLException;
	public String cari_firma_adi() throws ClassNotFoundException, SQLException;
	public ResultSet ekstre(String hesap , String t1 ,String t2) throws ClassNotFoundException, SQLException;
	public ResultSet hesap_adi_oku(String hesap) throws ClassNotFoundException, SQLException;
	public ResultSet hp_pln () throws ClassNotFoundException, SQLException;
	public ResultSet yilsonu_hp_pln () throws ClassNotFoundException, SQLException;
	public ResultSet ekstre_mizan (String kod,String ilktarih,String sontarih,String ilkhcins,String sonhcins,String ilkkar,String sonkar) throws ClassNotFoundException, SQLException;
	public ResultSet kasa_mizan(String kod,String ilktarih,String sontarih) throws ClassNotFoundException, SQLException;
	public void sqlite_sil() throws ClassNotFoundException, SQLException;
	public ResultSet ekstre_sqlite() throws ClassNotFoundException, SQLException;
	public ResultSet mizan(String h1 , String h2,String t1,String t2,String c1,String c2,String k1, String k2, String o1 , String o2) throws ClassNotFoundException, SQLException;
	public ResultSet fiskon(int evrakno) throws ClassNotFoundException, SQLException;
	public ResultSet cari_sonfisno() throws ClassNotFoundException, SQLException;
	public int cari_fisno_al() throws ClassNotFoundException, SQLException;
	public void evrak_yoket(int num) throws ClassNotFoundException, SQLException;
	public boolean cari_fino_bak(int fisno) throws ClassNotFoundException, SQLException;
	public void cari_dekont_kaydet(dEKONT_BILGI dBilgi) throws SQLException, ClassNotFoundException;
	public ResultSet hsp_pln(String arama) throws ClassNotFoundException, SQLException;
	public void hsp_sil(String hesap) throws ClassNotFoundException, SQLException;
	public String kod_ismi(String kodu) throws ClassNotFoundException, SQLException;
	public void hpln_kayit(String kodu,String adi,String karton,String hcins,String usr) throws ClassNotFoundException, SQLException;
	public void hpln_detay_kayit(String kodu ,String yet ,String ad1 ,String ad2 ,String semt,String seh  , String vd , String vn 
			, String t1 ,String t2 ,String t3 ,String fx ,String o1 ,String o2 ,String o3 , String web 
			,String mai ,String kim  ,String acik ,boolean sms , InputStream  resim ) throws ClassNotFoundException, SQLException, IOException;
	public ResultSet dvz_cevirme(String kcins,String hesap,String t1,String t2,String kur,String islem,String hKUR) throws ClassNotFoundException, SQLException;
	public ResultSet karton_dold(String karton) throws ClassNotFoundException, SQLException;
	public ResultSet ekstre_arama(String hes , String acik , String gun ,String ay,String yil ,String kod ,String kullanici) throws ClassNotFoundException, SQLException;
	public ResultSet kasa_kontrol(String hesap,String t1) throws ClassNotFoundException, SQLException;
	public ResultSet dek_mizan(String kod) throws ClassNotFoundException, SQLException;
	public int coklu_cari_fisno_al (int adet) throws ClassNotFoundException, SQLException;
	public ResultSet gunisl(String t1,String t2) throws ClassNotFoundException, SQLException;
	public int hesap_plani_kayit_adedi () throws ClassNotFoundException, SQLException;
	public void hpln_ilk_detay_kayit(String kodu) throws ClassNotFoundException, SQLException;
	public void cari_kod_degis_hesap(String t1,String t2) throws ClassNotFoundException, SQLException;
	public void cari_kod_degis_satirlar(String t1,String t2) throws ClassNotFoundException, SQLException;
	public void yilsonu_hpln_kayit(String kodu,String adi,String karton,String hcins,String usr) throws ClassNotFoundException, SQLException;
	public void yilsonu_hpln_detay_kayit(String kodu ,String yet ,String ad1 ,String ad2 ,String semt,String seh  , String vd , String vn 
			, String t1 ,String t2 ,String t3 ,String fx ,String o1 ,String o2 ,String o3 , String web 
			,String mai ,String kim  ,String acik  ) throws ClassNotFoundException, SQLException, IOException;
	public int yilsonu_hesap_plani_kayit_adedi () throws ClassNotFoundException, SQLException;
	public ResultSet mizan_aktar (String hesap) throws ClassNotFoundException, SQLException;
	public int yilsonu_cari_fisno_al() throws ClassNotFoundException, SQLException;
	public void yilsonu_cari_dekont_kaydet(String bhes,String tar,int evrak,String bcins,Double bkur,Double borc ,
			String alhes,String acins,Double alkur,Double alacak,String izahat,String kod,String user) throws SQLException, ClassNotFoundException;
	public int aktar_hesap_plani_kayit_adedi () throws ClassNotFoundException, SQLException;
	public void cari_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException;
	public ResultSet sms_cari_pln(String nerden) throws ClassNotFoundException, SQLException;
	public ResultSet ozel_mizan(String h1 , String h2,String t1,String t2,String c1,String c2,String k1, String k2, String o1 , String o2) throws ClassNotFoundException, SQLException;
	public ResultSet evrak_ogren(String text) throws ClassNotFoundException, SQLException;
	public ResultSet eksik_kur_okuma(String hesap,String t1,String t2,String kur) throws ClassNotFoundException, SQLException;
	public ResultSet sql_sorgu(String sql)  throws ClassNotFoundException, SQLException;
	public String[] cari_adres_oku (String kodu) throws ClassNotFoundException, SQLException;
	public ResultSet hesap_adi_auto(String hesap) throws ClassNotFoundException, SQLException;
	public ResultSet ekstre_proc(String hesap , String t1 ,String t2) throws ClassNotFoundException, SQLException;
	public ResultSet gunisl_proc(String t1 ,String t2) throws ClassNotFoundException, SQLException;
	public ResultSet karton_mizan(String h1 , String h2,String t1,String t2,String c1,String c2,String k1, String k2, String o1 , String o2) throws ClassNotFoundException, SQLException;
	public int cari_tah_fisno_al(String tur) throws ClassNotFoundException, SQLException;
	public void tah_ayar_kayit(String adi ,String adr1 ,String adr2 ,String vdvn ,String amail,String diger,InputStream  resim ,InputStream kase) throws ClassNotFoundException, SQLException, IOException;
	public void tah_ayar_sil() throws ClassNotFoundException, SQLException;
	public ResultSet tah_ayar_oku () throws ClassNotFoundException, SQLException;
	public void tah_kayit(int cins ,int tur , String evrak , String tarih ,String ckodu ,String akodu ,String aciklama,double tutar ,String dvzcins) throws ClassNotFoundException, SQLException, IOException;
	public ResultSet tah_oku (String no , int cins) throws ClassNotFoundException, SQLException;
	public void tah_sil (String no , int cins) throws ClassNotFoundException, SQLException;
	public void tah_cek_kayit(String evr, int cins  , String bnk , String sb ,String sr ,String hsp ,String brcl,String tar ,double tut) throws ClassNotFoundException, SQLException, IOException;
	public ResultSet tah_cek_doldur (String no, int cins) throws ClassNotFoundException, SQLException;
	public void tah_cek_sil (String no , int cins) throws ClassNotFoundException, SQLException;
	public void tah_cek_kayit_aktar (String no , int cins) throws ClassNotFoundException, SQLException;
	public ResultSet tah_listele ( int cins,int tur,String ilktarih,String sontarih,String ilkevr,String sonevr,String ilkck,String sonck) throws ClassNotFoundException, SQLException;
	public void yilsonu_tahsilat_bilgi_kayit() throws ClassNotFoundException, SQLException;
}

