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
}
