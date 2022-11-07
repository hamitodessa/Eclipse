package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;

public class STOK_ACCESS {
	private static  ISTOK _IStok;
	private static ILOGGER[] _Logger;

	public STOK_ACCESS(ISTOK _IStok, ILOGGER[] _Logger)
	{
		this._IStok = _IStok;
		this._Logger = _Logger;;
	}
	
	public static void baglan() throws SQLException
	{
	
		_IStok.baglan();
	}
	public void fAT_SIFIR_L (String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException 
	{
		 _IStok.fAT_SIFIR_L( kod,dizin_yeri, dizin,  fir_adi, ins, kull, sifre);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
			// _Logger.Logla(mesaj);

	}
	public void fAT_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IStok.fAT_SIFIR_S(server, ins, kull, sifre, kod, fir_adi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public ResultSet envanter_rapor_u_kodu(String t1 , String t2 , String k1 ,String k2 , String f1 ,String f2,
			String m1 ,String m2 ,String anagrup,String altgrup ,String depo, String depohar ,String ure,
			String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException
	{
		return _IStok.envanter_rapor_u_kodu(t1, t2, k1, k2, f1, f2, m1, m2, anagrup, altgrup, depo, depohar, ure, uanagrp, ualtgrp);
	}
	public ResultSet fatura_oku_printer(String fno,String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.fatura_oku_printer(fno, cins);
	}
	public ResultSet parametre_oku(String nerden , String satsut) throws ClassNotFoundException, SQLException
	{
		return _IStok.parametre_oku(nerden, satsut);
	}
	public ResultSet urun_adi_oku (String kodu,String kodbarcode) throws ClassNotFoundException, SQLException
	{
		return _IStok.urun_adi_oku(kodu, kodbarcode);
	}
	public ResultSet stk_barkod_kod_oku(String sira) throws ClassNotFoundException, SQLException
	{
		return _IStok.stk_barkod_kod_oku(sira);
	}
	public ResultSet stk_kod_degisken_oku(String fieldd,String sno,String nerden) throws ClassNotFoundException, SQLException
	{
		return _IStok.stk_kod_degisken_oku(fieldd, sno, nerden);
	}
	public ResultSet urun_kod_degisken_ara(String fieldd,String sno,String nerden,String arama) throws ClassNotFoundException, SQLException 
	{
		return _IStok.urun_kod_degisken_ara(fieldd, sno, nerden, arama);
	}
	public ResultSet stk_kod_alt_grup_degisken_oku (int sno) throws ClassNotFoundException, SQLException 
	{
		return _IStok.stk_kod_alt_grup_degisken_oku(sno);
	}
	public void degisken_degistir(int anagrp,int altgrp, int anaygrp,int altygrp) throws ClassNotFoundException, SQLException
	{
		_IStok.degisken_degistir(anagrp, altgrp, anaygrp, altygrp);
	}
	public  boolean alt_grup_kontrol(int anagrp,int altgrp) throws ClassNotFoundException, SQLException
	{
		return _IStok.alt_grup_kontrol(anagrp, altgrp);
	}
	public void urun_degisken_alt_grup_sil(int  ID ) throws ClassNotFoundException, SQLException
	{
		_IStok.urun_degisken_alt_grup_sil(ID);
	}
	public void urun_kod_degisken_sil(   String hangi_Y,String nerden  ,   int sira) throws ClassNotFoundException, SQLException
	{
		_IStok.urun_kod_degisken_sil(nerden, hangi_Y, sira);
	}
	public void urun_degisken_eski(String fieldd ,String degisken_adi ,String nerden ,String sno ,int ID ) throws ClassNotFoundException, SQLException 
	{
		_IStok.urun_degisken_eski(fieldd, degisken_adi, nerden, sno, ID);
	}
	public void urun_degisken_alt_grup_eski(String alt_grup ,int ana_grup ,int  ID ) throws ClassNotFoundException, SQLException
	{
		_IStok.urun_degisken_alt_grup_eski(alt_grup, ana_grup, ID);
	}
	public void urun_degisken_kayit(String fieldd  ,String nerden,String degisken_adi,String sira) throws ClassNotFoundException, SQLException 
	{
		_IStok.urun_degisken_kayit(fieldd, nerden, degisken_adi, sira);
	}
	public void  urun_degisken_alt_grup_kayit (String alt_grup , int ana_grup ) throws ClassNotFoundException, SQLException
	{
		_IStok.urun_degisken_alt_grup_kayit(alt_grup, ana_grup);
	}
	public void evr_format_irs(String satsut, double Tarih, double Sevk_Tarih , double FIRMA_KODU ,double FIRMA_UNVANI ,
			double VERGI_DAIRESI,double VERGI_NO ,double GIDECEGI_YER, double NOT_1 ,double NOT_2 ,double NOT_3 ,double BASLIK_BOLUM ,
			double BARKOD,double URUN_KODU ,double URUN_ADI ,double DEPO ,double SIMGE , double BIRIM_FIAT  ,
                 double ISKONTO  , double MIKTAR  , double K_D_V  , double TUTAR  , double TUTAR_TOPLAM  ,
                 double ISKONTO_TOPLAMI  , double BAKIYE  , double K_D_V_TOPLAMI  , double BELGE_TOPLAMI  , double YAZI_ILE  , double ALT_BOLUM,String usr  ) throws ClassNotFoundException, SQLException
	{
		_IStok.evr_format_irs(satsut, Tarih, Sevk_Tarih, FIRMA_KODU, FIRMA_UNVANI, VERGI_DAIRESI, VERGI_NO, GIDECEGI_YER, NOT_1, NOT_2, NOT_3, BASLIK_BOLUM, BARKOD, URUN_KODU, URUN_ADI, DEPO, SIMGE, BIRIM_FIAT, ISKONTO, MIKTAR, K_D_V, TUTAR, TUTAR_TOPLAM, ISKONTO_TOPLAMI, BAKIYE, K_D_V_TOPLAMI, BELGE_TOPLAMI, YAZI_ILE, ALT_BOLUM, usr);
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
		_IStok.evr_format_fat(satsut, Tarih, FIRMA_KODU, FIRMA_UNVANI, VERGI_DAIRESI, VERGI_NO, GIDECEGI_YER, NOT_1, NOT_2, NOT_3, BASLIK_BOLUM, BARKOD, URUN_KODU, URUN_ADI, DEPO, IZAHAT, SIMGE, BIRIM_FIAT, ISKONTO, MIKTAR, K_D_V, TUTAR, TUTAR_TOPLAM, ISKONTO_TOPLAMI, BAKIYE, K_D_V_TOPLAMI, BELGE_TOPLAMI, TEVKIFAT_ORANI, AL_TAR_TEV_ED_KDV, TEV_DAH_TOP_TUTAR, BEYAN_ED_KDV, TEV_HAR_TOP_TUT, YAZI_ILE, TEV_KASESI, ALT_BOLUM, N1, N2, N3, N4, N5, N6, N7, N8, N9, N10, usr);
	}
	public ResultSet bos_kur(String kur , String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.bos_kur(kur, cins);
	}
	public int fatura_no_al(String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.fatura_no_al(cins);
	}
	public boolean stok_bak_kontrol(String kodu) throws ClassNotFoundException, SQLException
	{
		return _IStok.stok_bak_kontrol(kodu);
	}
	public boolean stok_bak_kontrol_barcode(String barkodu) throws ClassNotFoundException, SQLException 
	{
		return _IStok.stok_bak_kontrol_barcode(barkodu);
	}
	public ResultSet fatura_oku(String fno,String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.fatura_oku(fno, cins);
	}
	public String aciklama_oku(String evrcins,int satir,String evrno,String gircik) throws ClassNotFoundException, SQLException
	{
		return _IStok.aciklama_oku(evrcins, satir, evrno, gircik);
	}
	public ResultSet irsaliye_no_doldur(String fno,String hareket) throws ClassNotFoundException, SQLException 
	{
		return _IStok.irsaliye_no_doldur(fno, hareket);
	}
	public ResultSet sql_sorgu(String sql) throws ClassNotFoundException, SQLException
	{
		return _IStok.sql_sorgu(sql);
	}
	public void fat_giris_sil(String fno,String cins) throws ClassNotFoundException, SQLException 
	{
		_IStok.fat_giris_sil(fno, cins);
	}
	public void fat_kaydet(String fatno ,String kodu ,int depo ,double  fiat ,double tevkifat  
            , double miktar ,String gircik ,double tutar,double iskonto ,double kdv  
            , String tarih, String izah,String doviz,String  adrfirma ,String carfirma  
            , String ozkod ,double kur ,String cins,int  anagrp,int  altgrp ,String usr) throws ClassNotFoundException, SQLException
	{
		_IStok.fat_kaydet(fatno, kodu, depo, fiat, tevkifat, miktar, gircik, tutar, iskonto, kdv, tarih, izah, doviz, adrfirma, carfirma, ozkod, kur, cins, anagrp, altgrp, usr);
	}
	public void stok_sil(String eno,String ecins,String cins) throws ClassNotFoundException, SQLException
	{
		_IStok.stok_sil(eno, ecins, cins);
	}
	public void stk_kaydet(String evrno ,String evrcins , String tarih,int depo,String urnkodu,double miktar,double fiat ,double tutar
            , double kdvlitut,String hareket ,String izah , int anagrp ,int altgrp ,double kur ,String b1,String doviz 
            , String hspkodu ,String usr) throws ClassNotFoundException, SQLException
	{
		_IStok.stk_kaydet(evrno, evrcins, tarih, depo, urnkodu, miktar, fiat, tutar, kdvlitut, hareket, izah, anagrp, altgrp, kur, b1, doviz, hspkodu, usr);
	}
	public void dipnot_yaz(String eno,String bir,String iki,String uc,String tip,String gircik,String usr) throws ClassNotFoundException, SQLException
	{
		_IStok.dipnot_yaz(eno, bir, iki, uc, tip, gircik, usr);
	}
	public void dipnot_sil(String ino,String cins,String gircik) throws ClassNotFoundException, SQLException
	{
		_IStok.dipnot_sil(ino, cins, gircik);
	}
	public void aciklama_sil(String evrcins,String evrno,String cins) throws ClassNotFoundException, SQLException
	{
		_IStok.aciklama_sil(evrcins, evrno, cins);
	}
	public void aciklama_yaz(String evrcins,int satir,String evrno,String aciklama,String gircik) throws ClassNotFoundException, SQLException 
	{
		_IStok.aciklama_yaz(evrcins, satir, evrno, aciklama, gircik);
	}
	public void  fat_no_yaz(String irsno,String fatno) throws ClassNotFoundException, SQLException
	{
		_IStok.fat_no_yaz(irsno, fatno);
	}
	public ResultSet irs_son_satis_fiati_oku(String kodu,String muskodu,String gircik) throws ClassNotFoundException, SQLException
	{
		return _IStok.irs_son_satis_fiati_oku(kodu, muskodu, gircik);
	}
	public ResultSet son_satis_fiati_oku(String kodu,String muskodu,String gircik) throws ClassNotFoundException, SQLException 
	{
		return _IStok.son_satis_fiati_oku(kodu, muskodu, gircik);
	}
	public ResultSet fat_oz_kod (String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.fat_oz_kod(cins);
	}
	public ResultSet dipnot_oku(String ino,String cins ,String gircik) throws ClassNotFoundException, SQLException
	{
		return _IStok.dipnot_oku(ino, cins, gircik);
	}
	public String son_no_al(String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.son_no_al(cins);
	}
	public ResultSet irsaliye_oku (String ino,String hareket) throws ClassNotFoundException, SQLException
	{
		return _IStok.irsaliye_oku(ino, hareket);
	}
	public ResultSet ggdy_oku() throws ClassNotFoundException, SQLException 
	{
		return _IStok.ggdy_oku();
	}
	public void gdy_sil(Integer gid) throws ClassNotFoundException, SQLException
	{
		_IStok.gdy_sil(gid);
	}public void gdy_kayit(String isim ,String adres,String semt,String sehir,String usr) throws ClassNotFoundException, SQLException
	{
		_IStok.gdy_kayit(isim, adres, semt, sehir, usr);
	}
	public int uretim_fisno_al() throws ClassNotFoundException, SQLException
	{
		return _IStok.uretim_fisno_al();
	}
	public String uret_son_bordro_no_al () throws ClassNotFoundException, SQLException 
	{
		return _IStok.uret_son_bordro_no_al();
	}
	public double son_imalat_fiati_oku(String kodu) throws ClassNotFoundException, SQLException 
	{
		return _IStok.son_imalat_fiati_oku(kodu);
	}
	public double gir_ort_fiati_oku(String kodu,String ilkt,String tarih) throws ClassNotFoundException, SQLException
	{
		return _IStok.gir_ort_fiati_oku(kodu, ilkt, tarih);
	}
	public ResultSet uret_ilk_tarih(String baslangic,String tar,String ukodu) throws ClassNotFoundException, SQLException
	{
		return _IStok.uret_ilk_tarih(baslangic, tar, ukodu);
	}
	public ResultSet imalat_urun_ara (String kodu) throws ClassNotFoundException, SQLException
	{
		return _IStok.imalat_urun_ara(kodu);
	}
	public ResultSet resim_oku(String ukodu) throws ClassNotFoundException, SQLException
	{
		return _IStok.resim_oku(ukodu);
	}
	public ResultSet recete_oku (String rno) throws ClassNotFoundException, SQLException 
	{
		return _IStok.recete_oku(rno);
	}
	public ResultSet stok_oku(String eno,String cins) throws ClassNotFoundException, SQLException
	{
		return _IStok.stok_oku(eno, cins);
	}
}
