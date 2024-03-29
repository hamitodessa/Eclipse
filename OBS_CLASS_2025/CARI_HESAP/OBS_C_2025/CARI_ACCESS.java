package OBS_C_2025;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@SuppressWarnings("static-access")
public class CARI_ACCESS {
	private static ICARI_HESAP _ICari;
	private static ILOGGER[] _Logger;
	
	private GLOBAL gLB = new GLOBAL();
	
	public CARI_ACCESS(ICARI_HESAP _ICari, ILOGGER[] _Logger)
	{
		this._ICari = _ICari;
		this._Logger = _Logger;
	}
	public static void baglan() throws SQLException, ClassNotFoundException
	{
	 _ICari.baglan();
	}
	public void akt_baglan(String kod, String port) throws SQLException
	{
		_ICari.akt_baglan(kod , port);
	}
	public String cari_firma_adi() throws ClassNotFoundException, SQLException {
		return  _ICari.cari_firma_adi();
	}
	public void cari_sifirdan_L (Server_Bilgi sbilgi , lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException 
	{
		 _ICari.cari_sifirdan_L( sbilgi);
			 for ( ILOGGER  _Logger : _Logger )
				 _Logger.Logla(lBILGI, dBILGI);
			 
			 for ( ILOGGER  _Logger : _Logger )
			 {
				 lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
				  	_Logger.Logla(lBILGI, dBILGI);
			 }
	}
	public void cARI_SIFIR_S(Server_Bilgi sbilgi, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		 _ICari.cARI_SIFIR_S(sbilgi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(lBILGI, dBILGI);
		 for ( ILOGGER  _Logger : _Logger )
		 {
			 lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
			  	_Logger.Logla(lBILGI, dBILGI);
		 }
	}
	public ResultSet  cari_sonfisno( ) throws ClassNotFoundException, SQLException
	{
		return _ICari.cari_sonfisno();
	}
	public ResultSet dek_mizan(String kod) throws ClassNotFoundException, SQLException
	{
		return _ICari.dek_mizan(kod);
	}
	public int cari_fisno_al() throws ClassNotFoundException, SQLException
	{
		return _ICari.cari_fisno_al();
	}
	public boolean cari_fino_bak(int fisno) throws ClassNotFoundException, SQLException
	{
		return _ICari.cari_fino_bak(fisno);
	}
	public void evrak_yoket(int num, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_ICari.evrak_yoket(num);
		if (BAGLAN.cariDizin.lOG == true)
		{
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(lBILGI, dBILGI);
		}
	}
	public void cari_dekont_kaydet(dEKONT_BILGI dbILGI,  lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws SQLException, ClassNotFoundException
	{
		_ICari.cari_dekont_kaydet(dbILGI);
		if (BAGLAN.cariDizin.lOG == true)
		{
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(lBILGI, dBILGI);
		}
	}
	public ResultSet fiskon(int evrakno) throws ClassNotFoundException, SQLException
	{
		return _ICari.fiskon(evrakno);
	}
	public ResultSet hp_pln () throws ClassNotFoundException, SQLException
	{
		return _ICari.hp_pln();
	}
	public ResultSet yilsonu_hp_pln () throws ClassNotFoundException, SQLException
	{
		return _ICari.yilsonu_hp_pln();
	}
	public ResultSet hesap_adi_oku(String hesap) throws ClassNotFoundException, SQLException 
	{
		return _ICari.hesap_adi_oku(hesap);
	}
	public ResultSet kasa_mizan(String kod,String ilktarih,String sontarih) throws ClassNotFoundException, SQLException
	{
		return _ICari.kasa_mizan(kod, ilktarih, sontarih);
	}
	public ResultSet ekstre_mizan (String kod,String ilktarih,String sontarih,String ilkhcins,String sonhcins,String ilkkar,String sonkar) throws ClassNotFoundException, SQLException
	{
		return _ICari.ekstre_mizan(kod, ilktarih, sontarih, ilkhcins, sonhcins, ilkkar, sonkar);
	}
	public ResultSet ekstre(String hesap , String t1 ,String t2) throws ClassNotFoundException, SQLException
	{
		return _ICari.ekstre(hesap, t1, t2);
	}
	public void sqlite_sil() throws ClassNotFoundException, SQLException
	{
		Connection SQLitecon = null;
		Class.forName("org.sqlite.JDBC");
		SQLitecon = null;
		PreparedStatement stmt = null;
		SQLitecon = gLB.myEkstreConnection();
		String sql = "DELETE FROM EKSTRE ";
		stmt = SQLitecon.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		SQLitecon.close();
		SQLitecon = null ;
		}
	public String kod_ismi(String kodu) throws ClassNotFoundException, SQLException
	{
		return _ICari.kod_ismi(kodu);
	}
	public ResultSet hsp_pln(String arama) throws ClassNotFoundException, SQLException
	{
		return _ICari.hsp_pln(arama);
	}
	public void hsp_sil(String hesap, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_ICari.hsp_sil(hesap);
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(lBILGI, dBILGI);
	}
	public void hpln_kayit(String kodu,String adi,String karton,String hcins,String usr,  lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_ICari.hpln_kayit(kodu, adi, karton, hcins, usr);
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(lBILGI, dBILGI);
	}
	public void hpln_detay_kayit(String kodu ,String yet ,String ad1 ,String ad2 ,String semt,String seh  , String vd , String vn 
			, String t1 ,String t2 ,String t3 ,String fx ,String o1 ,String o2 ,String o3 , String web 
			,String mai ,String kim  ,String acik ,boolean sms , InputStream  resim ) throws ClassNotFoundException, SQLException, IOException
	{
		_ICari.hpln_detay_kayit(kodu, yet, ad1, ad2, semt, seh, vd, vn, t1, t2, t3, fx, o1, o2, o3, web, mai, kim, acik, sms, resim);
	}
	public ResultSet ekstre_arama(String hes , String acik , String gun ,String ay,String yil ,String kod,String kullanici 
			,  lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(lBILGI, dBILGI);
		return _ICari.ekstre_arama(hes, acik, gun, ay, yil, kod, kullanici);
	}
	public ResultSet eksik_kur_okuma(String hesap,String t1,String t2,String kur) throws ClassNotFoundException, SQLException
	{
		return _ICari.eksik_kur_okuma(hesap, t1, t2, kur);
	}
	public ResultSet dvz_cevirme(String kcins,String hesap,String t1,String t2,String kur,String islem,String hKUR) throws ClassNotFoundException, SQLException
	{
		return _ICari.dvz_cevirme(kcins, hesap, t1, t2, kur, islem,hKUR);
	}
	public int coklu_cari_fisno_al (int adet) throws ClassNotFoundException, SQLException
	{
		return _ICari.coklu_cari_fisno_al(adet);
	}
	public ResultSet gunisl(String t1,String t2) throws ClassNotFoundException, SQLException
	{
		return _ICari.gunisl(t1, t2);
	}
	public ResultSet karton_dold(String karton) throws ClassNotFoundException, SQLException
	{
		return _ICari.karton_dold(karton);
	}
	public ResultSet kasa_kontrol(String hesap,String t1) throws ClassNotFoundException, SQLException
	{
		return _ICari.kasa_kontrol(hesap, t1);
	}
	public void cari_kod_degis_hesap(String t1,String t2,  lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(lBILGI, dBILGI);
		_ICari.cari_kod_degis_hesap(t1, t2);
	}
	public void cari_kod_degis_satirlar(String t1,String t2) throws ClassNotFoundException, SQLException
	{
		_ICari.cari_kod_degis_satirlar(t1, t2);
	}
	public ResultSet mizan(String h1 , String h2,String t1,String t2,String c1,String c2,String k1, String k2, String o1 , String o2) throws ClassNotFoundException, SQLException
	{
		return _ICari.mizan(h1, h2, t1, t2, c1, c2, k1, k2, o1, o2);
	}
	public int hesap_plani_kayit_adedi () throws ClassNotFoundException, SQLException
	{
		return _ICari.hesap_plani_kayit_adedi();
	}
	public int yilsonu_hesap_plani_kayit_adedi () throws ClassNotFoundException, SQLException
	{
		return _ICari.yilsonu_hesap_plani_kayit_adedi();
	}
	public void hpln_ilk_detay_kayit(String kodu) throws ClassNotFoundException, SQLException 
	{
		_ICari.hpln_ilk_detay_kayit(kodu);
	}
	public ResultSet ozel_mizan(String h1 , String h2,String t1,String t2,String c1,String c2,String k1, String k2, String o1 , String o2) throws ClassNotFoundException, SQLException
	{
		return _ICari.ozel_mizan(h1, h2, t1, t2, c1, c2, k1, k2, o1, o2);
	}
	public void yilsonu_hpln_kayit(String kodu,String adi,String karton,String hcins,String usr) throws ClassNotFoundException, SQLException
	{
		_ICari.yilsonu_hpln_kayit(kodu, adi, karton, hcins, usr);
	}
	public void yilsonu_hpln_detay_kayit(String kodu ,String yet ,String ad1 ,String ad2 ,String semt,String seh  , String vd , String vn 
			, String t1 ,String t2 ,String t3 ,String fx ,String o1 ,String o2 ,String o3 , String web 
			,String mai ,String kim  ,String acik   ) throws ClassNotFoundException, SQLException, IOException
	{
		_ICari.yilsonu_hpln_detay_kayit(kodu, yet, ad1, ad2, semt, seh, vd, vn, t1, t2, t3, fx, o1, o2, o3, web, mai, kim, acik);
	}
	public ResultSet mizan_aktar (String hesap) throws ClassNotFoundException, SQLException
	{
		return _ICari.mizan_aktar(hesap);
	}
	public int yilsonu_cari_fisno_al() throws ClassNotFoundException, SQLException
	{
		return _ICari.yilsonu_cari_fisno_al();
	}
	public void yilsonu_cari_dekont_kaydet(String bhes,String tar,int evrak,String bcins,Double bkur,Double borc ,
			String alhes,String acins,Double alkur,Double alacak,String izahat,String kod,String user
			,  lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_ICari.yilsonu_cari_dekont_kaydet(bhes, tar, evrak, bcins, bkur, borc, alhes, acins, alkur, alacak, izahat, kod, user);
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(lBILGI, dBILGI);
	}
	public ResultSet ekstre_sqlite() throws ClassNotFoundException, SQLException
	{
		return _ICari.ekstre_sqlite();
	}
	public ResultSet sms_cari_pln(String nerden) throws ClassNotFoundException, SQLException
	{
		return _ICari.sms_cari_pln(nerden);
	}
	public ResultSet evrak_ogren(String text) throws ClassNotFoundException, SQLException
	{
		return _ICari.evrak_ogren(text);
	}
	public ResultSet sql_sorgu(String sql,  lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(lBILGI, dBILGI);
		return _ICari.sql_sorgu(sql);
	}
	public String[] cari_adres_oku (String kodu) throws ClassNotFoundException, SQLException 
	{
		return _ICari.cari_adres_oku(kodu);
	}
		public void cari_firma_adi_kayit(String fadi, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_ICari.cari_firma_adi_kayit(fadi);
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(lBILGI, dBILGI);
	}
	public ResultSet karton_mizan(String h1 , String h2,String t1,String t2,String c1,String c2,String k1, String k2, String o1 , String o2) throws ClassNotFoundException, SQLException
	{
		return _ICari.karton_mizan(h1, h2, t1, t2, c1, c2, k1, k2, o1, o2);
	}
	public ResultSet ekstre_proc(String hesap , String t1 ,String t2) throws ClassNotFoundException, SQLException
	{
		return _ICari.ekstre_proc(hesap, t1, t2);
	}
	public int cari_tah_fisno_al(String tur) throws ClassNotFoundException, SQLException
	{
		return _ICari.cari_tah_fisno_al(tur);
	}
	public void tah_ayar_kayit(String adi ,String adr1 ,String adr2 ,String vdvn ,String amail,String diger,InputStream  resim ,InputStream kase) throws ClassNotFoundException, SQLException, IOException
	{
		_ICari.tah_ayar_kayit(adi, adr1, adr2, vdvn, amail, diger, resim,kase);
	}
	public void tah_ayar_sil() throws ClassNotFoundException, SQLException
	{
		_ICari.tah_ayar_sil();
	}
	public ResultSet tah_ayar_oku () throws ClassNotFoundException, SQLException
	{
		return _ICari.tah_ayar_oku();
	}
	public void tah_kayit(int cins ,Integer tur , String evrak , String tarih ,String ckodu ,String akodu ,String aciklama,double tutar ,String dvzcins,String posbanka ) throws ClassNotFoundException, SQLException, IOException
	{
		_ICari.tah_kayit(cins, tur, evrak, tarih, ckodu, akodu, aciklama, tutar,dvzcins,posbanka);
	}
	public ResultSet tah_oku (String no , int cins) throws ClassNotFoundException, SQLException
	{
		return _ICari.tah_oku(no, cins);
	}
	public void tah_sil (String no , int cins) throws ClassNotFoundException, SQLException
	{
		_ICari.tah_sil(no, cins);
	}
	public void tah_cek_kayit(String evr, int cins  , String bnk , String sb ,String sr ,String hsp ,String brcl,String tar ,double tut) throws ClassNotFoundException, SQLException, IOException
	{
		_ICari.tah_cek_kayit(evr, cins,  bnk, sb, sr, hsp, brcl, tar, tut);
	}
	public ResultSet tah_cek_doldur (String no, int cins) throws ClassNotFoundException, SQLException
	{
		return _ICari.tah_cek_doldur( no,cins);
	}
	public void tah_cek_sil (String no , int cins) throws ClassNotFoundException, SQLException
	{
		_ICari.tah_cek_sil(no, cins);
	}
	public void tah_cek_kayit_aktar (String no , int cins) throws ClassNotFoundException, SQLException
	{
		_ICari.tah_cek_kayit_aktar(no, cins);
	}
	public ResultSet tah_listele ( int cins,int tur,String ilktarih,String sontarih,String ilkevr,String sonevr,String ilkck,String sonck,String pos) throws ClassNotFoundException, SQLException
	{
		return _ICari.tah_listele(cins, tur, ilktarih, sontarih, ilkevr, sonevr, ilkck, sonck,pos);
	}
	public void yilsonu_tahsilat_bilgi_kayit() throws ClassNotFoundException, SQLException
	{
		_ICari.yilsonu_tahsilat_bilgi_kayit();
	}
	public ResultSet pos_banka_oku () throws ClassNotFoundException, SQLException
	{
		return _ICari.pos_banka_oku();
	}
	public int cari_tahsonfisno(int cins) throws ClassNotFoundException, SQLException
	{
		return _ICari.cari_tahsonfisno(cins);
	}
	public ResultSet banka_sube(String nerden) throws ClassNotFoundException, SQLException
	{
		return _ICari.banka_sube(nerden);
	}
	public void cari_kod_degis_tahsilat(String t1,String t2) throws ClassNotFoundException, SQLException
	{
		_ICari.cari_kod_degis_tahsilat(t1, t2);
	}

}
